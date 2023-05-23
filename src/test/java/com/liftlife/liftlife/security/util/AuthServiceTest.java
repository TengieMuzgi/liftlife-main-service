//package com.liftlife.liftlife.security.util;
//
//import com.liftlife.liftlife.common.UserRole;
//import com.liftlife.liftlife.security.AuthService;
//import com.liftlife.liftlife.userModule.user.User;
//import com.liftlife.liftlife.userModule.user.UserRepository;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
//
//import java.util.Date;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class AuthServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    private AuthService userService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testRegisterNewUser() {
//        // given
//        RegisterRequest request = new RegisterRequest();
//        request.setEmail("test@test.com");
//        request.setPassword("test123");
//        request.setRole(UserRole.CLIENT);
//
//        User user = new User(
//                request.getEmail(),
//                passwordEncoder.encode(request.getPassword()),
//                false,
//                request.getRole(),
//                new Date());
//
//        when(userRepository.isPresent(request.getEmail())).thenReturn(false);
//        when(userRepository.insert(any(User.class))).thenReturn("x");
//
//        // when
//        ResponseEntity<String> response = userService.register(request);
//
//        // then
//        assertThat(response.getStatusCodeValue()).isEqualTo(201);
//        assertThat(response.getBody()).isEqualTo("User created with id: x");
//    }
//
//    @Test
//    public void testRegisterExistingUser() {
//        // given
//        RegisterRequest request = new RegisterRequest();
//        request.setEmail("test@test.com");
//        request.setPassword("test123");
//        request.setRole(UserRole.CLIENT);
//
//        when(userRepository.isPresent(request.getEmail())).thenReturn(true);
//
//        // when
//        ResponseEntity<String> response = userService.register(request);
//
//        // then
//        assertThat(response.getStatusCodeValue()).isEqualTo(202);
//        assertThat(response.getBody()).isEqualTo("User with email: test@test.com is already registered!");
//    }
//}