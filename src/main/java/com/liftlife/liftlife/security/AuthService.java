package com.liftlife.liftlife.security;

import com.liftlife.liftlife.security.util.JwtTokenUtil;
import com.liftlife.liftlife.security.util.LoginRequest;
import com.liftlife.liftlife.security.util.RegisterRequest;
import com.liftlife.liftlife.userModule.user.User;
import com.liftlife.liftlife.userModule.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {
    private DBUserDetailsServiceImpl userDetailsService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthService( DBUserDetailsServiceImpl userDetailsService,
                       UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public ResponseEntity<String> login(LoginRequest loginRequest) {

        User user = (User) userDetailsService.loadUserByUsername(loginRequest.getEmail());
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
//            Line below calls Auth Manager which is automatically called by request with auth in header,
//            returns same token that is created above
//            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(token);

            String jwtToken = jwtTokenUtil.generateToken(user);
            return ResponseEntity.ok(jwtToken);
        }
        else
            return ResponseEntity.accepted().body("Bad credentials");
    }

    public ResponseEntity<String> register(RegisterRequest registerRequest) {
        if(userRepository.isPresent(registerRequest.getEmail())) {
            return ResponseEntity.accepted().body("User with email: " + registerRequest.getEmail() + " is already registered!");
        }

        User user = new User(
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()),
                false,
                registerRequest.getRole(),
                new Date());

        String uid = userRepository.insert(user);
        user.setDocumentId(uid);

        //201 - created
        return ResponseEntity.status(201).body("User created with id: " + uid);
    }
}
