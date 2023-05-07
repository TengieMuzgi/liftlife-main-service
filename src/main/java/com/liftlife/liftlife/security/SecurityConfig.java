package com.liftlife.liftlife.security;

import com.liftlife.liftlife.userModule.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DBAuthenticationProvider dbAuthenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//
//        http.csrf().disable().authorizeHttpRequests()
//                .requestMatchers("/auth/**").permitAll()
//                .requestMatchers("/api/**").permitAll()
//                .anyRequest().authenticated();
        http.authorizeRequests(authorization -> authorization
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/api/**").hasAnyAuthority("USER", "ADMIN")
                        .anyRequest()
                        .authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .httpBasic();
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(dbAuthenticationProvider);
        return authenticationManagerBuilder.build();
    }

}
