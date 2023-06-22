package com.liftlife.liftlife.user.user;

import com.google.firebase.auth.FirebaseAuthException;
import com.liftlife.liftlife.dto.CoachDto;
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

}