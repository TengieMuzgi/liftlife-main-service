package com.liftlife.liftlife.security;

import com.google.firebase.auth.FirebaseAuthException;
import com.liftlife.liftlife.security.util.LoginRequest;
import com.liftlife.liftlife.security.util.RegisterRequest;
import com.liftlife.liftlife.userModule.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthService authService;



    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    //    @PostMapping("/authenticate")
//    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
//        try {
//            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(request.getToken());
//            String uid = decodedToken.getUid();
//
//            // wykonaj logikę autentykacji
//
//            return ResponseEntity.ok("Użytkownik autoryzowany");
//        } catch (FirebaseAuthException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Błąd autentykacji: " + e.getMessage());
//        }
//    }

}
