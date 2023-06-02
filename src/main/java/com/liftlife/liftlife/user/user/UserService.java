package com.liftlife.liftlife.user.user;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.liftlife.liftlife.common.CoachSpecialization;
import com.liftlife.liftlife.common.UserRole;
import com.liftlife.liftlife.dto.CoachDto;
import com.liftlife.liftlife.security.AuthService;
import com.liftlife.liftlife.user.admin.Admin;
import com.liftlife.liftlife.user.admin.AdminRepository;
import com.liftlife.liftlife.user.client.Client;
import com.liftlife.liftlife.user.client.ClientRepository;
import com.liftlife.liftlife.user.coach.Coach;
import com.liftlife.liftlife.user.coach.CoachRepository;
import com.liftlife.liftlife.user.utils.RegistrationToken;
import com.liftlife.liftlife.user.utils.RegistrationTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    @Value("${liftlife.service.url}")
    private String appUrl;
    private CoachRepository coachRepository;
    private AdminRepository adminRepository;
    private RegistrationTokenRepository registrationTokenRepository;
    private ClientRepository clientRepository;
    private FirebaseAuth firebaseAuth;

    @Autowired
    public UserService(CoachRepository coachRepository, AdminRepository adminRepository,
                       RegistrationTokenRepository registrationTokenRepository, ClientRepository clientRepository,
                       FirebaseAuth firebaseAuth) {
        this.coachRepository = coachRepository;
        this.adminRepository = adminRepository;
        this.registrationTokenRepository = registrationTokenRepository;
        this.clientRepository = clientRepository;
        this.firebaseAuth = firebaseAuth;
    }

    public ResponseEntity<String> generateRegistrationToken() {
        String authId = AuthService.getCurrentUserAuthId();
        UserRole role = AuthService.getCurrentUserRole();
        System.out.printf(role.name());
        if (role.equals(UserRole.COACH)) {
            Coach coach = coachRepository.findById(authId);
            RegistrationToken registrationToken = coach.generateVerificationToken();
            registrationTokenRepository.insert(registrationToken);
            return ResponseEntity.ok().body(registrationToken.getToken());
        } else if(role.equals(UserRole.ADMIN)) {
            Admin admin = adminRepository.findById(authId);
            RegistrationToken registrationToken = admin.generateVerificationToken();
            registrationTokenRepository.insert(registrationToken);
            return ResponseEntity.ok().body(registrationToken.getToken());
        }
        else return ResponseEntity.badRequest().body("Cannot create registration token for user with this role");
    }

    public ResponseEntity<String> verifyWithToken(String token) throws FirebaseAuthException {
        if(!registrationTokenRepository.isPresent(token)) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        RegistrationToken registrationToken = registrationTokenRepository.findByToken(token);
        String currentUserId = AuthService.getCurrentUserAuthId();

        if(registrationToken.getCreationRole().equals(UserRole.COACH)) {
            Coach coach = coachRepository.findById(registrationToken.getCreationId());
            Client newClient = new Client(currentUserId, UserRole.CLIENT, coach.getDocumentId());
            coach.addNewClient(clientRepository.insert(newClient));

            this.setUserRole(currentUserId, "CLIENT");

            coachRepository.update(coach);
            verifyUserEmail(currentUserId);
            registrationTokenRepository.delete(registrationToken.getDocumentId());
            return ResponseEntity.ok().body("Email successfully verified");
        } else if(registrationToken.getCreationRole().equals(UserRole.ADMIN)) {
            Coach newCoach = new Coach(currentUserId, UserRole.COACH);

            this.setUserRole(currentUserId, "COACH");

            coachRepository.insert(newCoach);
            verifyUserEmail(currentUserId);
            registrationTokenRepository.delete(registrationToken.getDocumentId());
            return ResponseEntity.ok().body("Email successfully verified");
        } else {
            return ResponseEntity.badRequest().body("Invalid token");
        }
    }

    public ResponseEntity<List<CoachDto>> getCoachList() throws FirebaseAuthException {
        List<Coach> coachList = coachRepository.findAll();
        List<CoachDto> coachDtoList = new ArrayList<>();

        for (Coach coach: coachList) {
            UserRecord userRecord = firebaseAuth.getUser(coach.getDocumentId());
            String[] name = userRecord.getDisplayName().split(" ");
            CoachSpecialization specialization = coach.getSpecialization();
            coachDtoList.add(new CoachDto(
                    coach.getDocumentId(),
                    name[0],
                    name[1],
                    specialization,
                    coach.getDescription(),
                    userRecord.getEmail()
            ));
        }
//        //grouping by specialization
//        for (CoachDto object : coachDtoList) {
//            CoachSpecialization specialization = object.getSpecialization();
//            List<CoachDto> group = groupedObjects.getOrDefault(specialization, new ArrayList<>());
//            group.add(object);
//            groupedObjects.put(specialization, group);
//        }
        return ResponseEntity.ok().body(coachDtoList);
    }

    public ResponseEntity<String> changeCoachDescription(String description) {
        Coach coach = coachRepository.findById(AuthService.getCurrentUserAuthId());
        coach.setDescription(description);
        coachRepository.update(coach);

        return ResponseEntity.ok().body("");
    }

    public ResponseEntity<String> changeCoachSpecialization(CoachSpecialization specialization) {
        Coach coach = coachRepository.findById(AuthService.getCurrentUserAuthId());
        coach.setSpecialization(specialization);
        coachRepository.update(coach);

        return ResponseEntity.ok().body("");
    }

    private void setUserRole(String id, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        try {
            firebaseAuth.setCustomUserClaims(id, claims);
        } catch (FirebaseAuthException e) {
            log.error("Error while setting custom user claim - assigning user role");
        }
    }

    private void verifyUserEmail(String authId) {
        UserRecord user = null;
        try {
            user = firebaseAuth.getUser(authId);
            user = firebaseAuth.updateUser(user.updateRequest().setEmailVerified(true));
        } catch (FirebaseAuthException e) {
            log.error("Error during email verification for user: " + authId);
            throw new RuntimeException("Error during email verification for user: " + authId);
        }
    }
}
