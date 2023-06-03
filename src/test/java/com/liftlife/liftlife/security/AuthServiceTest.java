package com.liftlife.liftlife.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    private FirebaseAuth firebaseAuth;
    @InjectMocks
    private AuthService authService;

    private RegisterRequest validRequest;

    @BeforeEach
    public void setUp() {
        validRequest = new RegisterRequest();
        validRequest.setEmail("test@example.com");
        validRequest.setPassword("Test1234");
        validRequest.setPasswordRepeated("Test1234");
        validRequest.setFirstName("John");
        validRequest.setLastName("Doe");
    }

    @Test
    public void shouldReturnBadRequestIfRequestInvalid() throws FirebaseAuthException {
        RegisterRequest invalidRequest = new RegisterRequest();
        ResponseEntity<String> response = authService.register(invalidRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void shouldReturnConflictIfUserExists() throws FirebaseAuthException {
        Mockito.when(firebaseAuth.getUserByEmail(validRequest.getEmail())).thenReturn(Mockito.mock(UserRecord.class));
        ResponseEntity<String> response = authService.register(validRequest);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void shouldReturnBadRequestIfPasswordInvalid() throws FirebaseAuthException {
        validRequest.setPassword("test");
        ResponseEntity<String> response = authService.register(validRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void shouldReturnCreatedIfUserRegistered() throws FirebaseAuthException {
        Mockito.when(firebaseAuth.createUser(Mockito.any(UserRecord.CreateRequest.class))).thenReturn(Mockito.mock(UserRecord.class));
        ResponseEntity<String> response = authService.register(validRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void shouldReturnInternalServerErrorIfRegistrationFails() throws FirebaseAuthException {
        Mockito.when(firebaseAuth.createUser(Mockito.any(UserRecord.CreateRequest.class))).thenThrow(FirebaseAuthException.class);
        ResponseEntity<String> response = authService.register(validRequest);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}