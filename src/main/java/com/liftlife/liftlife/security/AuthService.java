package com.liftlife.liftlife.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.liftlife.liftlife.security.util.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {
    private FirebaseAuth firebaseAuth;

    @Autowired
    public AuthService(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    public ResponseEntity<String> register(RegisterRequest registerRequest) {
        if(!isRegisterRequestValid(registerRequest))
            return ResponseEntity.badRequest().body("Register request is invalid");

        if(checkIfUserExists(registerRequest.getEmail()))  //409 resource conflict
            return ResponseEntity.status(409).body("User with email: " + registerRequest.getEmail() + " is already registered!");

        if(!verifyPassword(registerRequest.getPassword(), registerRequest.getPasswordRepeated()))
            return ResponseEntity.badRequest().body("Password is not matching conditions");

        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(registerRequest.getEmail())
                .setEmailVerified(false)
                .setPassword(registerRequest.getPassword())
                .setDisplayName(registerRequest.getFirstName() + registerRequest.getLastName());

        try{
            UserRecord createdUser = firebaseAuth.createUser(request);
            //201 - created
            return ResponseEntity.status(201).body("User created with id: " + createdUser.getUid());
        } catch (FirebaseAuthException e) {
            log.warn("Error registering user with email: " + registerRequest.getEmail());
            return ResponseEntity.internalServerError().body("User with email" + registerRequest.getEmail() + " not registered");
        }
    }

    public static UserRecord getCurrentUser() throws FirebaseAuthException {
        String uid = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return FirebaseAuth.getInstance().getUser(uid);
    }

    public static String getCurrentUserAuthId() {
        String uid = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return uid;
    }

//    public void createAdmin() {
//        Admin admin = new Admin("5mZB564wq4QyfSEAJYFTpNFiVQT2", UserRole.ADMIN);
//        adminRepository.insert(admin);
//    }

    private boolean checkIfUserExists(String email) {
        try {
            if(firebaseAuth.getUserByEmail(email) != null)
                return true;
        } catch (FirebaseAuthException e) {
            return false;
        }
        return false;
    }

    private boolean verifyPassword(String password, String repeatedPassword) {
        if(!password.equals(repeatedPassword))
            return false;

        if (password.length() < 8 || password.length() > 25) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }

            if (hasUppercase && hasLowercase && hasDigit) {
                break;
            }
        }

        return hasUppercase && hasLowercase && hasDigit;
    }

    private boolean isRegisterRequestValid(RegisterRequest request) {
        if (request == null)
            return false;

        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            return false;
        }

        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            return false;
        }

        if (request.getPasswordRepeated() == null || request.getPasswordRepeated().isEmpty()) {
            return false;
        }

        if (!request.getPassword().equals(request.getPasswordRepeated())) {
            return false;
        }

        if (request.getFirstName() == null || request.getFirstName().isEmpty()) {
            return false;
        }

        if (request.getLastName() == null || request.getLastName().isEmpty()) {
            return false;
        }

        return true;
    }

}
