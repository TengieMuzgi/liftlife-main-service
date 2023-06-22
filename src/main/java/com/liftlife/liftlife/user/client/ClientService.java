package com.liftlife.liftlife.user.client;

import com.liftlife.liftlife.dto.CoachDto;
import com.liftlife.liftlife.dto.PhysiqueDto;
import com.liftlife.liftlife.security.AuthService;
import com.liftlife.liftlife.user.user.UserService;
import com.liftlife.liftlife.util.exception.NotFoundException;
import com.liftlife.liftlife.util.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@Slf4j
public class ClientService {

    private ClientRepository clientRepository;
    private UserService userService;

    @Autowired
    public ClientService(ClientRepository clientRepository, UserService userService) {
        this.clientRepository = clientRepository;
        this.userService = userService;
    }

    public ResponseEntity<Object> getDto() {
        try {
            return ResponseEntity.ok().body(userService.extractClientDto(AuthService.getCurrentUserAuthId()));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<CoachDto> getMyCoach() {
        Client client = clientRepository.findById(AuthService.getCurrentUserAuthId());
        try {
            return ResponseEntity.ok().body(userService.extractCoachDto(client.getCoachId()));
        } catch (UserNotFoundException e) {
            log.warn(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Object> updateAge(Map<String, Integer> body) {
        Integer age = body.get("age");
        if(age == null)
            return ResponseEntity.badRequest().body("Wrong format");

        Client client = clientRepository.findById(AuthService.getCurrentUserAuthId());
        client.setAge(age);
        clientRepository.update(client);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> updateWeight(Map<String, Float> body) {
        Float weight = body.get("weight");
        if(weight == null)
            return ResponseEntity.badRequest().body("Wrong format");

        Client client = clientRepository.findById(AuthService.getCurrentUserAuthId());
        client.setWeight(weight);
        clientRepository.update(client);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> updateHeight(Map<String, Float> body) {
        Float height = body.get("height");
        if(height == null)
            return ResponseEntity.badRequest().body("Wrong format");

        Client client = clientRepository.findById(AuthService.getCurrentUserAuthId());
        client.setHeight(height);
        clientRepository.update(client);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> updatePhysique(PhysiqueDto physiqueDto) {
        Client client = clientRepository.findById(AuthService.getCurrentUserAuthId());

        try {
            client.setAge(Integer.parseInt(physiqueDto.getAge()));
            client.setWeight(Float.parseFloat(physiqueDto.getWeight()));
            client.setHeight(Float.parseFloat(physiqueDto.getHeight()));
        } catch (NumberFormatException e) {
            log.error("Error parsing physique");
            return ResponseEntity.badRequest().body("Error parsing physique");
        }

        clientRepository.update(client);
        return ResponseEntity.ok().build();
    }
}
