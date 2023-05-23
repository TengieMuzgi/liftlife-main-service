package com.liftlife.liftlife.security.jwt;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import org.springframework.security.core.context.SecurityContextHolder;
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
}
