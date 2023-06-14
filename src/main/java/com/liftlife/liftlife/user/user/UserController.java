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
        System.out.println(token.getToken());
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

    @GetMapping("/coach/clients")
    public ResponseEntity<List<ClientDto>> getMyClients() {
        return userService.getMyClients();
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
    public ResponseEntity<Object> updateAge(@RequestBody int value) {
        return userService.updateAge(value);
    }

    @PutMapping("/client/update/weight")
    public ResponseEntity<Object> updateWeight(@RequestBody float value) {
        return userService.updateWeight(value);
    }

    @PutMapping("/client/update/height")
    public ResponseEntity<Object> updateHeight(@RequestBody float value) {
        return userService.updateHeight(value);
    }

    @PutMapping("/client/update/physique")
    public ResponseEntity<Object> updatePhysique(@RequestBody PhysiqueDto physiqueDto) {
        return userService.updatePhysique(physiqueDto);
    }
}
