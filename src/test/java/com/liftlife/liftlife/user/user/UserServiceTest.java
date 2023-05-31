package com.liftlife.liftlife.user.user;

import com.google.firebase.auth.FirebaseAuth;
import com.liftlife.liftlife.common.UserRole;
import com.liftlife.liftlife.security.AuthService;
import com.liftlife.liftlife.user.admin.Admin;
import com.liftlife.liftlife.user.admin.AdminRepository;
import com.liftlife.liftlife.user.client.ClientRepository;
import com.liftlife.liftlife.user.trainer.Trainer;
import com.liftlife.liftlife.user.trainer.TrainerRepository;
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
    private TrainerRepository trainerRepository;
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

    @Test
    public void generateRegistrationToken_WithTrainer_ShouldReturnToken() {
        // given
        String authId = "authId";
        Trainer trainer = mock(Trainer.class); //new Trainer();
        RegistrationToken token = new RegistrationToken();

        Mockito.when(trainerRepository.isPresentByAuthId(authId)).thenReturn(true);
        Mockito.when(trainerRepository.findByAuthId(authId)).thenReturn(trainer);
        Mockito.when(trainer.generateVerificationToken()).thenReturn(token);

        // when
        ResponseEntity<String> response = userService.generateRegistrationToken();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token.getToken(), response.getBody());
    }

    @Test
    public void generateRegistrationToken_WithAdmin_ShouldReturnToken() {
        // given
        String authId = "authId";
        Admin admin = mock(Admin.class);
        RegistrationToken token = new RegistrationToken();

        Mockito.when(adminRepository.isPresentByAuthId(authId)).thenReturn(true);
        Mockito.when(adminRepository.findByAuthId(authId)).thenReturn(admin);
        Mockito.when(admin.generateVerificationToken()).thenReturn(token);

        // when
        ResponseEntity<String> response = userService.generateRegistrationToken();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token.getToken(), response.getBody());
    }

    @Test
    public void generateRegistrationToken_WithInvalidRole_ShouldReturnBadRequest() {
        // given
        Mockito.when(trainerRepository.isPresentByAuthId(Mockito.anyString())).thenReturn(false);
        Mockito.when(adminRepository.isPresentByAuthId(Mockito.anyString())).thenReturn(false);

        // when
        ResponseEntity<String> response = userService.generateRegistrationToken();

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Cannot create registration token for user with this role", response.getBody());
    }

    //verifyWithToken

    @Test
    public void verifyWithToken_WithInvalidToken_ShouldReturnBadRequest() {
        // given
        String token = "invalidToken";
        Mockito.when(registrationTokenRepository.isPresent(token)).thenReturn(false);

        // when
        ResponseEntity<String> response = userService.verifyWithToken(token);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid token", response.getBody());
    }

    @Test
    public void verifyWithToken_WithClientToken_ShouldReturnBadRequest() {
        // given
        String clientToken = "clientToken";
        Mockito.when(registrationTokenRepository.isPresent(clientToken)).thenReturn(true);
        Mockito.when(registrationTokenRepository.findByToken(clientToken)).thenReturn(new RegistrationToken(
                clientToken, UserRole.CLIENT, "uid"
        ));

        // when
        ResponseEntity<String> response = userService.verifyWithToken(clientToken);

        /// then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid token", response.getBody());
    }

//    @Test
//    public void testVerifyWithTokenWhenTokenIsTrainer() throws Exception {
//        String token = "token";
//        String authId = "authId";
//
//        RegistrationToken registrationToken = mock(RegistrationToken.class);
//        when(registrationToken.getCreationRole()).thenReturn(UserRole.TRAINER);
//
//        when(registrationTokenRepository.isPresent(token)).thenReturn(true);
//        when(registrationTokenRepository.findByToken(token)).thenReturn(registrationToken);
//
//
//        Trainer trainer = mock(Trainer.class);
//        when(trainerRepository.findById(any())).thenReturn(trainer);
//        Mockito.when(trainerRepository.update(trainer)).thenReturn(null);
//
//
//        UserRecord userRecord = mock(UserRecord.class);
//        when(firebaseAuth.getUser(any())).thenReturn(userRecord);
//        Mockito.when(firebaseAuth.updateUser(any())).thenReturn(userRecord);
//
//        ResponseEntity<String> response = userService.verifyWithToken(token);
//
//        assertEquals(ResponseEntity.ok().body("Email successfully verified"), response);
//    }
//
//    @Test
//    public void verifyWithToken_ValidTrainerToken_ShouldReturn200() throws Exception {
//        // given
//        String token = "token";
//        String authId = "authId";
//        Trainer trainer = new Trainer(authId, UserRole.TRAINER);
//
//        Mockito.when(registrationTokenRepository.isPresent(token)).thenReturn(true);
//        Mockito.when(registrationTokenRepository.findByToken(token)).thenReturn(new RegistrationToken(
//                token, UserRole.TRAINER, "uid"
//        ));
//        Mockito.when(trainerRepository.findById("uid")).thenReturn(trainer);
//        Mockito.when(trainerRepository.update(trainer)).thenReturn(null);
//
//
//        // when
//        ResponseEntity<String> response = userService.verifyWithToken(token);
//
//        // then
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Email successfully verified", response.getBody());
//    }
}
