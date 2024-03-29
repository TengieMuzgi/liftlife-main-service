package com.liftlife.liftlife.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.liftlife.liftlife.common.UserRole;
import com.liftlife.liftlife.util.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@Slf4j
public class AuthService {
    private FirebaseAuth firebaseAuth;

    @Autowired
    public AuthService(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    public ResponseEntity<String> register(RegisterRequest registerRequest) {
        if(!isRegisterRequestValid(registerRequest)) {
            return ResponseEntity.badRequest().body("Register request is invalid");
        }

        if(checkIfUserExists(registerRequest.getEmail())) { //409 resource conflict
            return ResponseEntity.status(409).body("User with email: " + registerRequest.getEmail() + " is already registered!");
        }

        if(!verifyPassword(registerRequest.getPassword(), registerRequest.getPasswordRepeated())) {
            return ResponseEntity.badRequest().body("Password is not matching conditions");
        }

        if(!isEmailValid(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Email is not matching requirements");
        }

        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(registerRequest.getEmail())
                .setEmailVerified(false)
                .setPassword(registerRequest.getPassword())
                .setDisplayName(registerRequest.getFirstName() + " " + registerRequest.getLastName());

        try{
            UserRecord createdUser = firebaseAuth.createUser(request);
            //201 - created
            return ResponseEntity.status(201).body("User created with id: " + createdUser.getUid());
        } catch (FirebaseAuthException e) {
            log.warn("Error registering user with email: " + registerRequest.getEmail());
            return ResponseEntity.internalServerError().body("User with email" + registerRequest.getEmail() + " not registered");
        }
    }

    public static UserRecord getCurrentUser() {
        try {
            String uid = AuthService.getCurrentUserAuthId();
            return FirebaseAuth.getInstance().getUser(uid);
        } catch (FirebaseAuthException e) {
            throw new UserNotFoundException("Cannot find current logged user");
        }
    }

    public static String getCurrentUserAuthId() {
        String uid = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return uid;
    }

    public static UserRole getCurrentUserRole() {
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty())
            return null;
        else {
            List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            String role = authorities.get(0).getAuthority();
            return UserRole.valueOf(role);
        }
    }

    public static String extractCurrentUserRole(UserRecord userRecord) {
        String role = (String) userRecord.getCustomClaims().get("role");
        return role;
    }

    private boolean isEmailValid(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);

        return pattern.matcher(email).matches();
    }

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
