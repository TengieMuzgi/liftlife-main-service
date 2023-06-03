package com.liftlife.liftlife.user.user;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.liftlife.liftlife.common.UserRole;
import com.liftlife.liftlife.security.AuthService;
import com.liftlife.liftlife.user.admin.Admin;
import com.liftlife.liftlife.user.admin.AdminRepository;
import com.liftlife.liftlife.user.client.ClientRepository;
import com.liftlife.liftlife.user.coach.Coach;
import com.liftlife.liftlife.user.coach.CoachRepository;
import com.liftlife.liftlife.user.utils.RegistrationToken;
import com.liftlife.liftlife.user.utils.RegistrationTokenRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private CoachRepository coachRepository;
    @Mock
    private AdminRepository adminRepository;
    @Mock
    private RegistrationTokenRepository registrationTokenRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private FirebaseAuth firebaseAuth;

    @InjectMocks
    private UserService userService;

    @BeforeAll
    public static void beforeAll() {
        Mockito.mockStatic(AuthService.class);
        Mockito.when(AuthService.getCurrentUserAuthId()).thenReturn("authId");
    }

//    @Test
//    public void generateRegistrationToken_WithCoach_ShouldReturnToken() {
//        // given
//        String authId = "authId";
//        Coach coach = mock(Coach.class); //new Trainer();
//        RegistrationToken token = new RegistrationToken();
//
//        Mockito.when(coachRepository.isPresentById(authId)).thenReturn(true);
//        Mockito.when(coachRepository.findById(authId)).thenReturn(coach);
//        Mockito.when(coach.generateVerificationToken()).thenReturn(token);
//
//        // when
//        ResponseEntity<String> response = userService.generateRegistrationToken();
//
//        // then
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(token.getToken(), response.getBody());
//    }
//
//    @Test
//    public void generateRegistrationToken_WithAdmin_ShouldReturnToken() {
//        // given
//        String authId = "authId";
//        Admin admin = mock(Admin.class);
//        RegistrationToken token = new RegistrationToken();
//
//        Mockito.when(adminRepository.isPresentById(authId)).thenReturn(true);
//        Mockito.when(adminRepository.findById(authId)).thenReturn(admin);
//        Mockito.when(admin.generateVerificationToken()).thenReturn(token);
//
//        // when
//        ResponseEntity<String> response = userService.generateRegistrationToken();
//
//        // then
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(token.getToken(), response.getBody());
//    }
//
//    @Test
//    public void generateRegistrationToken_WithInvalidRole_ShouldReturnBadRequest() {
//        // given
//        Mockito.when(coachRepository.isPresentById(Mockito.anyString())).thenReturn(false);
//        Mockito.when(adminRepository.isPresentById(Mockito.anyString())).thenReturn(false);
//
//        // when
//        ResponseEntity<String> response = userService.generateRegistrationToken();
//
//        // then
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Cannot create registration token for user with this role", response.getBody());
//    }
//
//    //verifyWithToken
//
//    @Test
//    public void verifyWithToken_WithInvalidToken_ShouldReturnBadRequest() throws FirebaseAuthException {
//        // given
//        String token = "invalidToken";
//        Mockito.when(registrationTokenRepository.isPresent(token)).thenReturn(false);
//
//        // when
//        ResponseEntity<String> response = userService.verifyWithToken(token);
//
//        // then
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Invalid token", response.getBody());
//    }
//
//    @Test
//    public void verifyWithToken_WithClientToken_ShouldReturnBadRequest() throws FirebaseAuthException {
//        // given
//        String clientToken = "clientToken";
//        Mockito.when(registrationTokenRepository.isPresent(clientToken)).thenReturn(true);
//        Mockito.when(registrationTokenRepository.findByToken(clientToken)).thenReturn(new RegistrationToken(
//                clientToken, UserRole.CLIENT, "uid"
//        ));
//
//        // when
//        ResponseEntity<String> response = userService.verifyWithToken(clientToken);
//
//        /// then
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Invalid token", response.getBody());
//    }


}
