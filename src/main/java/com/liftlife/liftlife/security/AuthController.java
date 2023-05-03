package com.liftlife.liftlife.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.liftlife.liftlife.userModule.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthController {

    private FirebaseAuth firebaseAuth;

    @Autowired
    public AuthController(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(request.getToken());
            String uid = decodedToken.getUid();

            // wykonaj logikę autentykacji

            return ResponseEntity.ok("Użytkownik autoryzowany");
        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Błąd autentykacji: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws FirebaseAuthException {
        String email = loginRequest.getEmail();
        String pwd = loginRequest.getPassword();
        UserRecord user = firebaseAuth.getUserByEmail(email);
        System.out.println("User: " + user.getUid());

//        List<GrantedAuthority> authorities = Collections.emptyList();
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null, authorities);
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        return null;
    }
}
