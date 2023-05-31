package com.liftlife.liftlife.security;

import com.liftlife.liftlife.common.UserRole;
import com.liftlife.liftlife.user.admin.Admin;
import com.liftlife.liftlife.user.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest)  {
        return authService.register(registerRequest);
    }

//    @GetMapping
//    public void createAdmin() {
//        Admin admin = new Admin("5mZB564wq4QyfSEAJYFTpNFiVQT2", UserRole.ADMIN);
//        adminRepository.insert(admin);
//    }

}
