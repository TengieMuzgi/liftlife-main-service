package com.liftlife.liftlife.security.jwt;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.liftlife.liftlife.common.UserRole;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class JwtTokenFilter extends OncePerRequestFilter {

    private FirebaseTokenProvider firebaseTokenProvider;

    public JwtTokenFilter(FirebaseTokenProvider firebaseTokenProvider) {
        this.firebaseTokenProvider = firebaseTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            FirebaseToken firebaseToken = verifyToken(token);

            if (firebaseToken != null) {
                String userId = firebaseToken.getUid();

                List<GrantedAuthority> authorities = extractRole(userId, firebaseToken);
                System.out.println("Logged user: " + userId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private FirebaseToken verifyToken(String token) {
        FirebaseToken firebaseToken = null;
        try {
            firebaseToken = firebaseTokenProvider.verifyToken(token);
        } catch (RuntimeException e) {
            logger.warn("Request with invalid token was sent");
        }
        return firebaseToken;
    }

    private List<GrantedAuthority> extractRole(String userId, FirebaseToken firebaseToken) {
        String role = (String) firebaseToken.getClaims().get("role");

        //search in db if role not in token - on first log there is no role in token
        if(role == null) {
            try {
                role = (String)  FirebaseAuth.getInstance().getUser(userId).getCustomClaims().get("role");
            } catch (FirebaseAuthException | NullPointerException e) {
                logger.info("Cannot get role for user with id" + userId);
                return Collections.emptyList();
            }
        }
        List<GrantedAuthority> authorities = Collections.emptyList();
        if(role == null) {
            return authorities;
        } else if(role.equals(UserRole.COACH.name())) {
            authorities = Collections.singletonList(new SimpleGrantedAuthority(UserRole.COACH.name()));
            return authorities;
        } else if (role.equals(UserRole.ADMIN.name())) {
            authorities = Collections.singletonList(new SimpleGrantedAuthority(UserRole.ADMIN.name()));
            return authorities;
        } else if (role.equals(UserRole.CLIENT.name())) {
            authorities = Collections.singletonList(new SimpleGrantedAuthority(UserRole.CLIENT.name()));
            return authorities;
        }
        return authorities;
    }
}

