package com.liftlife.liftlife.user.user;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.liftlife.liftlife.common.UserRole;
import com.liftlife.liftlife.security.AuthService;
import com.liftlife.liftlife.user.admin.Admin;
import com.liftlife.liftlife.user.admin.AdminRepository;
import com.liftlife.liftlife.user.client.Client;
import com.liftlife.liftlife.user.client.ClientRepository;
import com.liftlife.liftlife.user.trainer.Trainer;
import com.liftlife.liftlife.user.trainer.TrainerRepository;
import com.liftlife.liftlife.user.utils.RegistrationToken;
import com.liftlife.liftlife.user.utils.RegistrationTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Value("${liftlife.service.url}")
    private String appUrl;
    private TrainerRepository trainerRepository;
    private AdminRepository adminRepository;
    private RegistrationTokenRepository registrationTokenRepository;
    private ClientRepository clientRepository;
    private FirebaseAuth firebaseAuth;

    @Autowired
    public UserService(TrainerRepository trainerRepository, AdminRepository adminRepository,
                       RegistrationTokenRepository registrationTokenRepository, ClientRepository clientRepository,
                       FirebaseAuth firebaseAuth) {
        this.trainerRepository = trainerRepository;
        this.adminRepository = adminRepository;
        this.registrationTokenRepository = registrationTokenRepository;
        this.clientRepository = clientRepository;
        this.firebaseAuth = firebaseAuth;
    }

    public ResponseEntity<String> generateRegistrationToken() {
        String authId = AuthService.getCurrentUserAuthId();
        if (trainerRepository.isPresentByAuthId(authId)) {
            Trainer trainer = trainerRepository.findByAuthId(authId);
            RegistrationToken registrationToken = trainer.generateVerificationToken();
            registrationTokenRepository.insert(registrationToken);
            return ResponseEntity.ok().body(registrationToken.getToken());
        } else if(adminRepository.isPresentByAuthId(AuthService.getCurrentUserAuthId())) {
            Admin admin = adminRepository.findByAuthId(authId);
            RegistrationToken registrationToken = admin.generateVerificationToken();
            registrationTokenRepository.insert(registrationToken);
            return ResponseEntity.ok().body(registrationToken.getToken());
        }
        else return ResponseEntity.badRequest().body("Cannot create registration token for user with this role");
    }

    public ResponseEntity<String> verifyWithToken(String token) {
        if(!registrationTokenRepository.isPresent(token)) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        RegistrationToken registrationToken = registrationTokenRepository.findByToken(token);
        String currentUserAuthId = AuthService.getCurrentUserAuthId();

        if(registrationToken.getCreationRole().equals(UserRole.TRAINER)) {
            Trainer trainer = trainerRepository.findById(registrationToken.getCreationId());
            Client newClient = new Client(currentUserAuthId, UserRole.CLIENT, trainer.getDocumentId());
            trainer.addNewClient(clientRepository.insert(newClient));

            trainerRepository.update(trainer);
            verifyUserEmail(currentUserAuthId);
            registrationTokenRepository.delete(registrationToken.getDocumentId());
            return ResponseEntity.ok().body("Email successfully verified");
        } else if(registrationToken.getCreationRole().equals(UserRole.ADMIN)) {
            Admin admin = adminRepository.findById(registrationToken.getCreationId());
            Trainer newTrainer = new Trainer(currentUserAuthId, UserRole.TRAINER);

            trainerRepository.insert(newTrainer);
            verifyUserEmail(currentUserAuthId);
            registrationTokenRepository.delete(registrationToken.getDocumentId());
            return ResponseEntity.ok().body("Email successfully verified");
        } else {
            return ResponseEntity.badRequest().body("Invalid token");
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
