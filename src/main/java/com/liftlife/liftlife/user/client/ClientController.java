package com.liftlife.liftlife.user.client;

import com.liftlife.liftlife.dto.CoachDto;
import com.liftlife.liftlife.dto.PhysiqueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user/client")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/info")
    public ResponseEntity<Object> getClientData() {
        return clientService.getDto();
    }

    @GetMapping("/getCoach")
    public ResponseEntity<CoachDto> getMyCoach() {
        return clientService.getMyCoach();
    }

    @PutMapping("/update/age")
    public ResponseEntity<Object> updateAge(@RequestBody Map<String, Integer> body) {
        return clientService.updateAge(body);
    }

    @PutMapping("/update/weight")
    public ResponseEntity<Object> updateWeight(@RequestBody Map<String, Float> body) {
        return clientService.updateWeight(body);
    }

    @PutMapping("/update/height")
    public ResponseEntity<Object> updateHeight(@RequestBody Map<String, Float> body) {
        return clientService.updateHeight(body);
    }

    @PutMapping("/update/physique")
    public ResponseEntity<Object> updatePhysique(@RequestBody PhysiqueDto physiqueDto) {
        return clientService.updatePhysique(physiqueDto);
    }
}
