package com.liftlife.liftlife.trainingModule.trainingSession;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TemplateTrainingSessionControllerTest {

    @Mock
    private TemplateTrainingSessionService templateTrainingSessionService;

    @InjectMocks
    private TemplateTrainingSessionController templateTrainingSessionController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenTrainingSession_whenInsert_thenStatus200IsRecieved() {
        TrainingSession trainingSession = new TrainingSession();
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Training session created successfully", HttpStatus.CREATED);
        when(templateTrainingSessionService.insert(trainingSession)).thenReturn(responseEntity);

        ResponseEntity<String> result = templateTrainingSessionController.insert(trainingSession);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("Training session created successfully", result.getBody());
    }

    @Test
    public void givenTrainingSession_whenFindById_thenStatus200IsRecieved() {
        String id = "1dasd2dasd3";
        TrainingSession trainingSession = new TrainingSession();
        ResponseEntity<TrainingSession> responseEntity = new ResponseEntity<>(trainingSession, HttpStatus.OK);
        when(templateTrainingSessionService.findByID(id)).thenReturn(trainingSession);

        ResponseEntity<TrainingSession> result = templateTrainingSessionController.findById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(trainingSession, result.getBody());
    }






}
