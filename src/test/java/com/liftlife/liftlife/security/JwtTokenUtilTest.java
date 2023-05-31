//package com.liftlife.liftlife.security.util;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@TestPropertySource(properties = {
//        "jwt.secret=testValue",
//        "jwt.expiration=3600000"
//})
//class JwtTokenUtilTest {
//
//    @Mock
//    private UserDetails userDetails;
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private Long expiration;
//
//    @InjectMocks
//    private JwtTokenUtil jwtTokenUtil;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        ReflectionTestUtils.setField(jwtTokenUtil, "secret", this.secret);
//        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", this.expiration);
//        userDetails = new User("testuser", "testpassword", new ArrayList<>());
//    }
//
//    @Test
//    void generateToken_shouldGenerateValidJwtToken() {
//        String username = "testuser";
//        String token = jwtTokenUtil.generateToken(userDetails);
//
//        assertNotNull(token);
//
//        boolean isTokenValid = jwtTokenUtil.validateToken(token, userDetails);
//
//        assertTrue(isTokenValid);
//
//        String subject = jwtTokenUtil.getClaimFromToken(token, Claims::getSubject);
//
//        assertEquals(username, subject);
//    }
//
//    @Test
//    public void getClaimFromToken_shouldReturnValidClaimValue() {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("name", "John Doe");
//        claims.put("role", "admin");
//        String token = jwtTokenUtil.generateToken(userDetails);
//
//        String nameClaim = jwtTokenUtil.getClaimFromToken(token, claimss -> claims.get("name")).toString();
//        System.out.println("Name: " + nameClaim);
//        assertEquals("John Doe", nameClaim);
//
//        String roleClaim = jwtTokenUtil.getClaimFromToken(token, claimss -> claims.get("role")).toString();
//        assertEquals("admin", roleClaim);
//    }
//
//    @Test
//    void isTokenExpired_shouldReturnTrueForExpiredToken() {
//        Date now = new Date();
//        Date expirationDate = new Date(now.getTime() - 10000);
//
//        String token = Jwts.builder()
//                .setClaims(new HashMap<>())
//                .setSubject("testsubject")
//                .setIssuedAt(now)
//                .setExpiration(expirationDate)
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact();
//
//        assertTrue(jwtTokenUtil.isTokenExpired(token));
//    }
//
//    @Test
//    void isTokenExpired_shouldReturnFalseForValidToken() {
//        Date now = new Date();
//        Date expirationDate = new Date(now.getTime() + expiration * 1000);
//
//        String token = Jwts.builder()
//                .setClaims(new HashMap<>())
//                .setSubject("testsubject")
//                .setIssuedAt(now)
//                .setExpiration(expirationDate)
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact();
//
//        assertFalse(jwtTokenUtil.isTokenExpired(token));
//    }
//
//    @Test
//    void getExpirationDateFromToken_shouldReturnValidDate() {
//        Date now = new Date();
//        Date expirationDate = new Date(now.getTime() + expiration * 1000);
//
//        String token = Jwts.builder()
//                .setClaims(new HashMap<>())
//                .setSubject("testsubject")
//                .setIssuedAt(now)
//                .setExpiration(expirationDate)
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact();
//
//        Date expirationFromToken = jwtTokenUtil.getExpirationDateFromToken(token);
//
//        assertEquals(expirationDate.toString(), expirationFromToken.toString());
//    }
//
//    @Test
//    void validateToken_shouldReturnFalseForInvalidToken() {
//        String invalidToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmb28iLCJleHAiOjE2MjIzNTY1MzQsImlhdCI6MTYyMjMyMDUzNH0" +
//                ".XOtVjuiEKDg6m367hUA1qc9owqzkCe2OmsBB4_mRFcU";
//
//        assertFalse(jwtTokenUtil.validateToken(invalidToken, userDetails));
//    }
//
//    @Test
//    void validateToken_shouldReturnFalseForExpiredToken() {
//        String username = "testuser";
//
//        Date now = new Date();
//        Date expirationDate = new Date(now.getTime() - 1000);
//
//        String token = Jwts.builder()
//                .setClaims(new HashMap<>())
//                .setSubject(username)
//                .setIssuedAt(now)
//                .setExpiration(expirationDate)
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact();
//
//        assertFalse(jwtTokenUtil.validateToken(token, userDetails));
//    }
//
//    @Test
//    void validateToken_shouldReturnTrueForValidToken() {
//        String username = "testuser";
//
//        Date now = new Date();
//        Date expirationDate = new Date(now.getTime() + expiration * 1000);
//
//        String token = Jwts.builder()
//                .setClaims(new HashMap<>())
//                .setSubject(username)
//                .setIssuedAt(now)
//                .setExpiration(expirationDate)
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact();
//
//        assertTrue(jwtTokenUtil.validateToken(token, userDetails));
//    }
//}