package com.liftlife.liftlife.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.liftlife.liftlife.security.util.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private FirebaseAuth firebaseAuth;

    @Autowired
    public AuthService(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    public void login(LoginRequest loginRequest) throws FirebaseAuthException {
        UserRecord user = firebaseAuth.getUserByEmail(loginRequest.getEmail());
        

        System.out.println("User: " + user.getUid());
    }

}
