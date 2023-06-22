package com.liftlife.liftlife.user.coach;

import com.liftlife.liftlife.common.CoachSpecialization;
import com.liftlife.liftlife.dto.ClientDto;
import com.liftlife.liftlife.security.AuthService;
import com.liftlife.liftlife.user.client.Client;
import com.liftlife.liftlife.user.client.ClientRepository;
import com.liftlife.liftlife.user.user.UserService;
import com.liftlife.liftlife.util.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CoachService {

    private UserService userService;
    private CoachRepository coachRepository;
    private ClientRepository clientRepository;

    @Autowired
    public CoachService(UserService userService, CoachRepository coachRepository, ClientRepository clientRepository) {
        this.userService = userService;
        this.coachRepository = coachRepository;
        this.clientRepository = clientRepository;
    }

    public List<String> getSpecializations() {
        List<String> specializations = new ArrayList<>();

        for (CoachSpecialization myEnum : CoachSpecialization.values()) {
            if (myEnum.getDescription() != null) {
                specializations.add(myEnum.getDescription());
            }
        }
        return specializations;
    }

    public ResponseEntity<String> changeCoachDescription(Map<String, String> body) {
        String description = body.get("description");
        if(description == null)
            return ResponseEntity.badRequest().body("Description is null");

        Coach coach = coachRepository.findById(AuthService.getCurrentUserAuthId());
        coach.setDescription(description);
        coachRepository.update(coach);

        return ResponseEntity.ok().body("");
    }

    public ResponseEntity<String> changeCoachSpecialization(Map<String, String> body) {
        String specialization = body.get("specialization");
        if(specialization == null)
            return ResponseEntity.badRequest().body("Specialization is null");

        Coach coach = coachRepository.findById(AuthService.getCurrentUserAuthId());
        coach.setSpecialization(CoachSpecialization.getSpecializationFromString(specialization));
        coachRepository.update(coach);

        return ResponseEntity.ok().body("");
    }

    public ResponseEntity<List<ClientDto>> getMyClients() {
        List<Client> clientList = clientRepository.findByField("coachId", AuthService.getCurrentUserAuthId());
        List<ClientDto> resultList = new ArrayList<>();

        try {
            for(Client client : clientList) {
                resultList.add(userService.extractClientDto(client));
            }

            return ResponseEntity.ok().body(resultList);
        } catch (NotFoundException e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.accepted().body(resultList);
    }

    public ResponseEntity<Object> getDto() {
        try {
            return ResponseEntity.ok().body(userService.extractCoachDto(AuthService.getCurrentUserAuthId()));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<Object> getMyClient(String clientId) {
        try {
            return ResponseEntity.ok(userService.extractClientDto(clientId));
        } catch (NotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<String> getDescription() {
        Coach coach = coachRepository.findById(AuthService.getCurrentUserAuthId());
        return ResponseEntity.ok().body(coach.getDescription());
    }

}
