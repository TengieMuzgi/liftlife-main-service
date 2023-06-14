package com.liftlife.liftlife.user.user;

import com.google.firebase.auth.FirebaseAuthException;
import com.liftlife.liftlife.dto.ClientDto;
import com.liftlife.liftlife.dto.CoachDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public ResponseEntity<String> registerWithToken(@RequestParam String token) throws FirebaseAuthException {
        return userService.verifyWithToken(token);
    }

    @GetMapping("/coaches")
    public ResponseEntity<List<CoachDto>> getCoachesBySpecialization() throws FirebaseAuthException {
        return userService.getCoachList();
    }

    @PostMapping("/coach/change/description")
    public ResponseEntity<String> changeCoachDescription(@RequestBody String description) {
        return userService.changeCoachDescription(description);
    }

    @PostMapping("/coach/change/specialization")
    public ResponseEntity<String> changeCoachSpecialization(@RequestBody String specialization) {
        return userService.changeCoachSpecialization(specialization);
    }

    @GetMapping("/coach/specializations")
    public ResponseEntity<List<String>> getSpecializations() {
        return ResponseEntity.ok().body(userService.getSpecializations());
    }

    @PostMapping("/picture/insert")
    public ResponseEntity<String> changeProfilePicture(@RequestParam("image") MultipartFile file) {
        return userService.changeProfilePicture(file);
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
    public ResponseEntity<Object> updateAge(@RequestBody int age) {
        return userService.updateAge(age);
    }

    @PutMapping("/client/update/weight")
    public ResponseEntity<Object> updateWeight(@RequestBody float weight) {
        return userService.updateWeight(weight);
    }

    @PutMapping("/client/update/height")
    public ResponseEntity<Object> updateHeight(@RequestBody float height) {
        return userService.updateHeight(height);
    }
}
