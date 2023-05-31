package com.liftlife.liftlife.userModule.user;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.liftlife.liftlife.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> registerWithToken(@RequestParam String token) {
        return userService.verifyWithToken(token);
    }

    @GetMapping
    public UserRecord retrieveUserDetails() throws FirebaseAuthException {
        return AuthService.getCurrentUser();
    }

}
