package com.liftlife.liftlife.security.jwt;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Component;

@Component
public class FirebaseTokenProvider {

    public FirebaseToken verifyToken(String token) {
        FirebaseToken decodedToken = null;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Decoded token: " + decodedToken);
        return decodedToken;
    }

//    public String generateToken(Authentication authentication) {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        userDetails.get
//
//        Instant now = Instant.now();
//        Instant expiryDate = now.plus(Duration.ofDays(1));
//
//
//        Map<String, Object> additionalClaims = new HashMap<String, Object>();
//        String customToken = FirebaseAuth.getInstance().createCustomToken();
//
//        return "";
//    }
}
