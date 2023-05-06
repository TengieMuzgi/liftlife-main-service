package com.liftlife.liftlife.security;

import com.google.firebase.auth.FirebaseAuthException;
import com.liftlife.liftlife.security.util.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws FirebaseAuthException {


//        List<GrantedAuthority> authorities = Collections.emptyList();
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null, authorities);
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        return null;
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
