//package com.liftlife.liftlife.security.jwt;
//
//import com.google.firebase.auth.FirebaseToken;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.List;
//
//public class JwtTokenFilter extends OncePerRequestFilter {
//
//    private FirebaseTokenProvider firebaseTokenProvider;
//
//    public JwtTokenFilter(FirebaseTokenProvider firebaseTokenProvider) {
//        this.firebaseTokenProvider = firebaseTokenProvider;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            String token = authorizationHeader.substring(7);
//            FirebaseToken firebaseToken = firebaseTokenProvider.verifyToken(token);
//            if (firebaseToken != null) {
//                String userId = firebaseToken.getUid();
//                List<GrantedAuthority> authorities = Collections.emptyList();
//                System.out.println("User: " + userId);
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null, authorities);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
