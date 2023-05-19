package com.liftlife.liftlife.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.liftlife.liftlife.security.util.LoginRequest;
import com.liftlife.liftlife.security.util.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/register")
    public ResponseEntity<String> registerGet() throws FirebaseAuthException {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail("test2@mail.com")
                .setPassword("pwdpwd");

        UserRecord userRecord = auth.createUser(request);


        return ResponseEntity.ok("User utworzony: " + auth.createCustomToken(userRecord.getUid()));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest)  {
        return authService.register(registerRequest);
    }
}
