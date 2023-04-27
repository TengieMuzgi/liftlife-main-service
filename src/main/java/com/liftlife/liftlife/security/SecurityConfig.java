package com.liftlife.liftlife.security;

//import com.example.aipms.service.DBUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private FirebaseTokenProvider firebaseTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorization -> {
                            try {
                                authorization
                                                .requestMatchers("/api/**").authenticated()
                                                .anyRequest().permitAll()
                                                .and()
                                                .apply(new JwtConfigurer(firebaseTokenProvider));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .csrf(csrf -> csrf.disable())
                .httpBasic();
        return http.build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(new DBAuthenticationProvider());
//        return authenticationManagerBuilder.build();
//    }

}
