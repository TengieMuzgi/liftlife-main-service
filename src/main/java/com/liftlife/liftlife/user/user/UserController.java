package com.liftlife.liftlife.user.user;

import com.google.firebase.auth.FirebaseAuthException;
import com.liftlife.liftlife.dto.ClientDto;
import com.liftlife.liftlife.dto.CoachDto;
import com.liftlife.liftlife.dto.PhysiqueDto;
import com.liftlife.liftlife.dto.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/token/generate")
    public ResponseEntity<String> generateRegistrationToken() {
        return userService.generateRegistrationToken();
    }

    @PostMapping("/token/verify")
    public ResponseEntity<String> registerWithToken(@RequestBody TokenDto token) throws FirebaseAuthException {
        return userService.verifyWithToken(token.getToken());
    }

    @GetMapping("/coaches")
    public ResponseEntity<List<CoachDto>> getCoachesBySpecialization() throws FirebaseAuthException {
        return userService.getCoachList();
    }

    @PostMapping("/picture/insert")
    public ResponseEntity<String> changeProfilePicture(@RequestParam("image") MultipartFile file) {
        return userService.changeProfilePicture(file);
    }

    @PostMapping("/coach/change/description")
    public ResponseEntity<String> changeCoachDescription(@RequestBody Map<String, String> body) {
        return userService.changeCoachDescription(body);
    }

    @PostMapping("/coach/change/specialization")
    public ResponseEntity<String> changeCoachSpecialization(@RequestBody Map<String, String> body) {
        return userService.changeCoachSpecialization(body);
    }

    @GetMapping("/coach/specializations")
    public ResponseEntity<List<String>> getSpecializations() {
        return ResponseEntity.ok().body(userService.getSpecializations());
    }

    @GetMapping("/coach/clients")
    public ResponseEntity<List<ClientDto>> getMyClients() {
        return userService.getMyClients();
    }

    @GetMapping("/coach/client")
    public ResponseEntity<Object> getMyClient(@RequestParam String clientId) {
        return userService.getMyClient(clientId);
    }

    @GetMapping("/coach/info")
    public ResponseEntity<Object> getCoachData() {
        return userService.getCoachDto();
    }

    @GetMapping("/coach/description")
    public ResponseEntity<String> getMyDescription() {
        return userService.getCoachDescription();
    }

    @GetMapping("/client/info")
    public ResponseEntity<ClientDto> getClientData() {
        return userService.getClientDto();
    }

    @GetMapping("/client/getCoach")
    public ResponseEntity<CoachDto> getMyCoach() {
        return userService.getMyCoach();
    }

    @PutMapping("/client/update/age")
    public ResponseEntity<Object> updateAge(@RequestBody Map<String, Integer> body) {
        return userService.updateAge(body);
    }

    @PutMapping("/client/update/weight")
    public ResponseEntity<Object> updateWeight(@RequestBody Map<String, Float> body) {
        return userService.updateWeight(body);
    }

    @PutMapping("/client/update/height")
    public ResponseEntity<Object> updateHeight(@RequestBody Map<String, Float> body) {
        return userService.updateHeight(body);
    }

    @PutMapping("/client/update/physique")
    public ResponseEntity<Object> updatePhysique(@RequestBody PhysiqueDto physiqueDto) {
        return userService.updatePhysique(physiqueDto);
    }
}