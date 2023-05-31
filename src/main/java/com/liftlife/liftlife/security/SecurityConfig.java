package com.liftlife.liftlife.security;

import com.liftlife.liftlife.security.jwt.FirebaseTokenProvider;
import com.liftlife.liftlife.security.jwt.JwtConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private FirebaseTokenProvider firebaseTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests(authorization -> authorization
//                        .requestMatchers("/auth/**").permitAll()
//                        .requestMatchers("/api/**").hasAnyAuthority("USER", "ADMIN")
//                        .anyRequest()
//                        .authenticated()
//                )
//                .csrf(csrf -> csrf.disable())
//                .httpBasic();
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(firebaseTokenProvider));
        return http.build();
    }


}
