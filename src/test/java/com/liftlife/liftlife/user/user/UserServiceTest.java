package com.liftlife.liftlife.user.user;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.liftlife.liftlife.common.CoachSpecialization;
import com.liftlife.liftlife.common.UserRole;
import com.liftlife.liftlife.dto.ClientDto;
import com.liftlife.liftlife.dto.CoachDto;
import com.liftlife.liftlife.security.AuthService;
import com.liftlife.liftlife.user.admin.Admin;
import com.liftlife.liftlife.user.admin.AdminRepository;
import com.liftlife.liftlife.user.client.Client;
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
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


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
    @Mock
    private Bucket firebaseBucket;
    @Mock
    private Blob blob;
    @InjectMocks
    private UserService userService;

    @BeforeAll
    public static void beforeAll() {
        Mockito.mockStatic(AuthService.class);
        when(AuthService.getCurrentUserAuthId()).thenReturn("authId");
    }

    @Test
    public void generateRegistrationToken_WithCoach_ShouldReturnToken() {
        // given
        String authId = "authId";
        Coach coach = mock(Coach.class); //new Trainer();
        RegistrationToken token = new RegistrationToken();

        when(coachRepository.findById(authId)).thenReturn(coach);
        when(coach.generateVerificationToken()).thenReturn(token);
        when(AuthService.getCurrentUserRole()).thenReturn(UserRole.COACH);

        // when
        ResponseEntity<String> response = userService.generateRegistrationToken();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token.getToken(), response.getBody());
    }

    @Test
    public void generateRegistrationToken_WithClientRole_ShouldReturnBadRequest() {
        // given
        when(AuthService.getCurrentUserRole()).thenReturn(UserRole.CLIENT);

        // when
        ResponseEntity<String> response = userService.generateRegistrationToken();

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Cannot create registration token for user with this role", response.getBody());
    }

    //verifyWithToken

    @Test
    public void verifyWithToken_WithInvalidToken_ShouldReturnBadRequest() throws FirebaseAuthException {
        // given
        String token = "invalidToken";
        when(registrationTokenRepository.isPresent(token)).thenReturn(false);

        // when
        ResponseEntity<String> response = userService.verifyWithToken(token);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid token", response.getBody());
    }

    @Test
    void getCoachListTest() throws FirebaseAuthException {
        // Given
        Coach coach1 = new Coach("coachId1", UserRole.COACH);
        coach1.setSpecialization(CoachSpecialization.FITNESS);
        Coach coach2 = new Coach("coachId2", UserRole.COACH);
        coach2.setSpecialization(CoachSpecialization.PERSONAL);
        List<Coach> coachList = Arrays.asList(coach1, coach2);

        UserRecord userRecord1 = Mockito.mock(UserRecord.class);
        UserRecord userRecord2 = Mockito.mock(UserRecord.class);

        when(coachRepository.findAll()).thenReturn(coachList);

        when(firebaseAuth.getUser(coach1.getDocumentId())).thenReturn(userRecord1);
        when(userRecord1.getDisplayName()).thenReturn("First Last");
        when(userRecord1.getEmail()).thenReturn("first@example.com");
        when(firebaseBucket.get(userRecord1.getUid())).thenReturn(blob);

        when(firebaseAuth.getUser(coach2.getDocumentId())).thenReturn(userRecord2);
        when(userRecord2.getDisplayName()).thenReturn("Second Last");
        when(userRecord2.getEmail()).thenReturn("second@example.com");
        when(firebaseBucket.get(userRecord2.getUid())).thenReturn(blob);

        // When
        ResponseEntity<List<CoachDto>> response = userService.getCoachList();

        // Then
        assertEquals(2, response.getBody().size());
    }

    @Test
    void changeCoachDescriptionTest() {
        // Given
        String newDescription = "New Description";
        String userId = "user123";

        Coach coach = new Coach(userId, UserRole.COACH);

        when(AuthService.getCurrentUserAuthId()).thenReturn(userId);
        when(coachRepository.findById(userId)).thenReturn(coach);

        // When
        ResponseEntity<String> response = userService.changeCoachDescription(newDescription);

        // Then
        verify(coachRepository, times(1)).update(coach);
        assertEquals("", response.getBody());
    }

    @Test
    void changeCoachSpecializationTest() {
        // Given
        String newSpecialization = "New Specialization";
        String userId = "user123";

        Coach coach = new Coach(userId, UserRole.COACH);

        when(AuthService.getCurrentUserAuthId()).thenReturn(userId);
        when(coachRepository.findById(userId)).thenReturn(coach);

        // When
        ResponseEntity<String> response = userService.changeCoachSpecialization(newSpecialization);

        // Then
        verify(coachRepository, times(1)).update(coach);
        assertEquals("", response.getBody());
    }

    @Test
    void getSpecializationsTest() {
        // Given
        // Assuming two CoachSpecialization enum members exist with non-null descriptions
        int expectedSize = 6;

        // When
        List<String> specializations = userService.getSpecializations();

        // Then
        assertEquals(expectedSize, specializations.size());
    }

    @Test
    void getClientDtoTest() throws FirebaseAuthException {
        // Given
        String userId = "user123";
        String coachId = "coach123";
        Date registerDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(registerDate);

        UserRecord userRecord = Mockito.mock(UserRecord.class);
        Client client = new Client(userId, UserRole.CLIENT, coachId);

        when(AuthService.getCurrentUser()).thenReturn(userRecord);
        when(userRecord.getDisplayName()).thenReturn("First Last");
        when(userRecord.getUid()).thenReturn(userId);
        when(clientRepository.findById(userId)).thenReturn(client);
        when(firebaseBucket.get(userId)).thenReturn(blob);

        // When
        ResponseEntity<ClientDto> response = userService.getClientDto();

        // Then
        assertEquals(userId, response.getBody().getId());
        assertEquals("First", response.getBody().getFirstName());
        assertEquals("Last", response.getBody().getLastName());
        assertEquals(formattedDate, response.getBody().getRegisterDate());
    }

    @Test
    void getMyCoachTest() throws FirebaseAuthException {
        // Given
        String clientId = "client123";
        String coachId = "coach123";

        UserRecord clientRecord = Mockito.mock(UserRecord.class);
        UserRecord coachRecord = Mockito.mock(UserRecord.class);
        Client client = new Client(clientId, UserRole.CLIENT, coachId);
        Coach coach = new Coach(coachId, UserRole.COACH);

        when(AuthService.getCurrentUser()).thenReturn(clientRecord);
        when(clientRecord.getUid()).thenReturn(clientId);
        when(clientRepository.findById(clientId)).thenReturn(client);
        when(coachRepository.findById(coachId)).thenReturn(coach);
        when(firebaseAuth.getUser(coach.getDocumentId())).thenReturn(coachRecord);
        when(coachRecord.getDisplayName()).thenReturn("First Last");
        when(firebaseBucket.get(coachRecord.getUid())).thenReturn(blob);

        // When
        ResponseEntity<CoachDto> response = userService.getMyCoach();

        // Then
        assertEquals(coachId, response.getBody().getId());
        assertEquals("First", response.getBody().getFirstName());
        assertEquals("Last", response.getBody().getLastName());
    }

    /*
        @Test
    public void generateRegistrationToken_WithAdmin_ShouldReturnToken() {
        // given
        String authId = "authId";
        Admin admin = mock(Admin.class); //new Trainer();
        RegistrationToken token = new RegistrationToken();

        when(AuthService.getCurrentUserRole()).thenReturn(UserRole.ADMIN);
        when(adminRepository.findById(authId)).thenReturn(admin);
        when(admin.generateVerificationToken()).thenReturn(token);

        // when
        ResponseEntity<String> response = userService.generateRegistrationToken();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token.getToken(), response.getBody());
    }

        @Test
    void verifyWithToken_WhenValidCoachToken() throws FirebaseAuthException {
        String token = "someToken";
        String authId = "authId";
        RegistrationToken registrationToken = new RegistrationToken(authId, UserRole.COACH, authId);
        when(registrationTokenRepository.isPresent(token)).thenReturn(true);
        when(registrationTokenRepository.findByToken(token)).thenReturn(registrationToken);
        when(AuthService.getCurrentUser().getPhotoUrl()).thenReturn(null);

        ResponseEntity<String> response = userService.verifyWithToken(token);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Email successfully verified", response.getBody());
    }

    @Test
    public void verifyWithToken_WithClientToken_ShouldReturnBadRequest() throws FirebaseAuthException {
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

     */

}
