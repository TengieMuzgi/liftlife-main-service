package com.liftlife.liftlife.security;

import com.liftlife.liftlife.userModule.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DBAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private DBUserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String givenUsername = authentication.getName();
        String givenPassword = authentication.getCredentials().toString();
        User user = (User) userDetailsService.loadUserByUsername(givenUsername);
        System.out.println("DB: " + user.getUsername());
        System.out.println("DB: " + user.getDocumentId());
        System.out.println("DB: " + user.getPassword());
        System.out.println("DB a: " + authentication.getCredentials().toString());

        if (passwordEncoder.matches(givenPassword, user.getPassword())){
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
            return token;
        }
        else {
            throw new BadCredentialsException("Invalid login details");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
