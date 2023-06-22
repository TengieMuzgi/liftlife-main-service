//package com.liftlife.liftlife.user.user;
//
//import com.google.firebase.auth.FirebaseAuthException;
//import com.liftlife.liftlife.dto.ClientDto;
//import com.liftlife.liftlife.dto.CoachDto;
//import com.liftlife.liftlife.dto.TokenDto;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockMultipartFile;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class UserControllerTest {
//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    private UserController userController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void generateRegistrationToken_shouldReturnToken() {
//        String expectedToken = "someToken";
//        ResponseEntity<String> expectedResponse = ResponseEntity.ok(expectedToken);
//        when(userService.generateRegistrationToken()).thenReturn(expectedResponse);
//
//        ResponseEntity<String> response = userController.generateRegistrationToken();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(expectedToken, response.getBody());
//        verify(userService, times(1)).generateRegistrationToken();
//    }
//
//    @Test
//    void registerWithToken_shouldReturnToken() throws FirebaseAuthException {
//        String token = "someToken";
//        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Registered successfully");
//        when(userService.verifyWithToken(token)).thenReturn(expectedResponse);
//
//        ResponseEntity<String> response = userController.registerWithToken(new TokenDto(token));
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Registered successfully", response.getBody());
//        verify(userService, times(1)).verifyWithToken(token);
//    }
//
//    @Test
//    void getCoachesBySpecialization_shouldReturnCoachesList() throws FirebaseAuthException {
//        List<CoachDto> expectedCoaches = Arrays.asList(
//                new CoachDto("1", "John", "Doe", "Specialization 1", "Description 1", "john.doe@example.com", true),
//                new CoachDto("2", "Jane", "Smith", "Specialization 2", "Description 2", "jane.smith@example.com", false)
//        );
//        ResponseEntity<List<CoachDto>> expectedResponse = ResponseEntity.ok(expectedCoaches);
//        when(userService.getCoachList()).thenReturn(expectedResponse);
//
//        ResponseEntity<List<CoachDto>> response = userController.getCoachesBySpecialization();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(expectedCoaches, response.getBody());
//        verify(userService, times(1)).getCoachList();
//    }
//
//    @Test
//    void changeCoachDescription_shouldReturnSuccessMessage() {
//        String description = "New description";
//        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Description changed successfully");
//        when(userService.changeCoachDescription(Map.of("description", description))).thenReturn(expectedResponse);
//
//        ResponseEntity<String> response = userController.changeCoachDescription(Map.of("description", description));
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Description changed successfully", response.getBody());
//        verify(userService, times(1)).changeCoachDescription(Map.of("description", description));
//    }
//
//    @Test
//    void changeCoachSpecialization_shouldReturnSuccessMessage() {
//        String specialization = "New specialization";
//        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Specialization changed successfully");
//        when(userService.changeCoachSpecialization(Map.of("specialization", specialization))).thenReturn(expectedResponse);
//
//        ResponseEntity<String> response = userController.changeCoachSpecialization(Map.of("specialization", specialization));
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Specialization changed successfully", response.getBody());
//        verify(userService, times(1)).changeCoachSpecialization(Map.of("specialization", specialization));
//    }
//
//    @Test
//    void getSpecializations_shouldReturnSpecializationsList() {
//        List<String> expectedSpecializations = Arrays.asList("Personal Coach", "Weight loss Coach", "Physique Coach",
//                "Fitness Coach", "Athletic Coach", "Performance Coach");
//        List<String> expectedResponse =expectedSpecializations;
//        when(userService.getSpecializations()).thenReturn(expectedResponse);
//
//        ResponseEntity<List<String>> response = userController.getSpecializations();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(expectedSpecializations, response.getBody());
//        verify(userService, times(1)).getSpecializations();
//    }
//
//    @Test
//    void changeProfilePicture_shouldReturnSuccessMessage() {
//        MockMultipartFile file = new MockMultipartFile("image", "test.png", "image/png", "Some image data".getBytes());
//        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Profile picture changed successfully");
//        when(userService.changeProfilePicture(file)).thenReturn(expectedResponse);
//
//        ResponseEntity<String> response = userController.changeProfilePicture(file);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Profile picture changed successfully", response.getBody());
//        verify(userService, times(1)).changeProfilePicture(file);
//    }
//
//    @Test
//    void getClientData_shouldReturnClientDto() {
//        ClientDto expectedClientDto = new ClientDto("1", "John", "Doe", new Date().toString(), true, 22, 80, 170);
//        ResponseEntity<ClientDto> expectedResponse = ResponseEntity.ok(expectedClientDto);
//        when(userService.getClientDto()).thenReturn(expectedResponse);
//
//        ResponseEntity<ClientDto> response = userController.getClientData();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(expectedClientDto, response.getBody());
//        verify(userService, times(1)).getClientDto();
//    }
//
//    @Test
//    void getMyCoach_shouldReturnCoachDto() {
//        CoachDto expectedCoachDto = new CoachDto("1", "Jane", "Smith", "Specialization 1", "Description 1", "jane.smith@example.com", true);
//        ResponseEntity<CoachDto> expectedResponse = ResponseEntity.ok(expectedCoachDto);
//        when(userService.getMyCoach()).thenReturn(expectedResponse);
//
//        ResponseEntity<CoachDto> response = userController.getMyCoach();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(expectedCoachDto, response.getBody());
//        verify(userService, times(1)).getMyCoach();
//    }
//}
