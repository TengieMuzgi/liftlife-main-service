package com.liftlife.liftlife.user.coach;

import com.liftlife.liftlife.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/coach")
public class CoachController {

    private CoachService coachService;

    @Autowired
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @PostMapping("change/description")
    public ResponseEntity<String> changeCoachDescription(@RequestBody Map<String, String> body) {
        return coachService.changeCoachDescription(body);
    }

    @PostMapping("/change/specialization")
    public ResponseEntity<String> changeCoachSpecialization(@RequestBody Map<String, String> body) {
        return coachService.changeCoachSpecialization(body);
    }

    @GetMapping("/specializations")
    public ResponseEntity<List<String>> getSpecializations() {
        return ResponseEntity.ok().body(coachService.getSpecializations());
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientDto>> getMyClients() {
        return coachService.getMyClients();
    }

    @GetMapping("/client")
    public ResponseEntity<Object> getMyClient(@RequestParam String clientId) {
        return coachService.getMyClient(clientId);
    }

    @GetMapping("/info")
    public ResponseEntity<Object> getCoachData() {
        return coachService.getDto();
    }

    @GetMapping("/description")
    public ResponseEntity<String> getMyDescription() {
        return coachService.getDescription();
    }
}
