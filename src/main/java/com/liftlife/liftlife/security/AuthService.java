package com.liftlife.liftlife.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.liftlife.liftlife.security.util.LoginRequest;
import com.liftlife.liftlife.security.util.RegisterRequest;
import com.liftlife.liftlife.userModule.user.User;
import com.liftlife.liftlife.userModule.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private AuthenticationManager authenticationManager;
    private DBUserDetailsServiceImpl userDetailsService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    public AuthService(AuthenticationManager authenticationManager, DBUserDetailsServiceImpl userDetailsService,
                       UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    public String login(LoginRequest loginRequest) {

        User user = (User) userDetailsService.loadUserByUsername(loginRequest.getEmail());
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwtToken = jwtTokenUtil.generateToken(user);
            return jwtToken;
        }
        else {
            throw new BadCredentialsException("Invalid login details");
        }
    }

    public String register(RegisterRequest registerRequest) {
        User user = new User(
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()),
                false,
                registerRequest.getRole(),
                registerRequest.getRegisterDate());

        String uid = userRepository.insert(user);

        return "User created with id: " + uid;
    }

}
