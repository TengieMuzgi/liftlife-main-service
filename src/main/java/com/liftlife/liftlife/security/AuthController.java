package com.liftlife.liftlife.security;

import com.google.firebase.auth.FirebaseAuth;
import com.liftlife.liftlife.user.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private FirebaseAuth firebaseAuth;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest)  {
        return authService.register(registerRequest);
    }

//    @GetMapping
//    public String getRole() throws FirebaseAuthException {
//        return AuthService.getCurrentUserRole();
//    }

//    @GetMapping("/name")
//    public String getDisplayName() throws FirebaseAuthException {
//        return firebaseAuth.getUser("MuA1nRxWA2OOpS0pNOSpZxMcKan2").getDisplayName();
//    }


    //hardcoded method for admin creation
//    @GetMapping
//    public void createAdmin() throws FirebaseAuthException {
//        Admin admin = new Admin("5mZB564wq4QyfSEAJYFTpNFiVQT2", UserRole.ADMIN);
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("role", "ADMIN");
//        firebaseAuth.setCustomUserClaims("5mZB564wq4QyfSEAJYFTpNFiVQT2", claims);
//        adminRepository.insert(admin);
//    }

}
