package com.liftlife.liftlife.trainingModule.trainingSession;
import com.google.cloud.firestore.WriteResult;
import com.liftlife.liftlife.trainingModule.trainingActivity.TrainingActivityService;
import com.liftlife.liftlife.trainingModule.trainingSession.TrainingSession;
import com.liftlife.liftlife.trainingModule.trainingSession.TrainingSessionService;
import com.liftlife.liftlife.trainingModule.trainingSession.template.TemplateTrainingSessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TrainingSessionControllerTest {
    @Mock
    private TemplateTrainingSessionService templateTrainingSessionService;
    @Mock
    private TrainingSessionService trainingSessionService;
    @Mock
    private TrainingActivityService trainingActivityService;

    private TrainingSessionController trainingSessionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        trainingSessionController = new TrainingSessionController(templateTrainingSessionService, trainingSessionService, trainingActivityService);
    }

    @Test
    public void insertTemplateTest() {
        TrainingSession trainingSession = new TrainingSession();

        when(templateTrainingSessionService.insert(trainingSession)).thenReturn(ResponseEntity.ok("Template inserted"));

        ResponseEntity<String> response = trainingSessionController.insertTemplate(trainingSession);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Template inserted", response.getBody());

        verify(templateTrainingSessionService, times(1)).insert(trainingSession);
        verifyNoMoreInteractions(templateTrainingSessionService);
    }

    @Test
    public void findTemplateByIdTest() {
        String sessionId = "sessionId";
        TrainingSession trainingSession = new TrainingSession();

        when(templateTrainingSessionService.findByID(sessionId)).thenReturn(trainingSession);

        ResponseEntity<TrainingSession> response = trainingSessionController.findTemplateById(sessionId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(trainingSession, response.getBody());

        verify(templateTrainingSessionService, times(1)).findByID(sessionId);
        verifyNoMoreInteractions(templateTrainingSessionService);
    }

    @Test
    public void findTemplatesForCoachTest() {
        String coachId = "coachId";
        List<TrainingSession> trainingSessions = new ArrayList<>();

        when(templateTrainingSessionService.findAllByCoach(coachId)).thenReturn(trainingSessions);

        ResponseEntity<List<TrainingSession>> response = trainingSessionController.findTemplatesForCoach(coachId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(trainingSessions, response.getBody());

        verify(templateTrainingSessionService, times(1)).findAllByCoach(coachId);
        verifyNoMoreInteractions(templateTrainingSessionService);
    }

    @Test
    public void findTemplateByTrainerTest() {
        String trainerId = "trainerId";
        List<TrainingSession> trainingSessions = new ArrayList<>();

        when(templateTrainingSessionService.findByTrainer(trainerId)).thenReturn(trainingSessions);

        ResponseEntity<List<TrainingSession>> response = trainingSessionController.findTemplateByTrainer(trainerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(trainingSessions, response.getBody());

        verify(templateTrainingSessionService, times(1)).findByTrainer(trainerId);
        verifyNoMoreInteractions(templateTrainingSessionService);
    }

    @Test
    public void findTemplateByDateTest() {
        String date = "2023-06-01";
        List<TrainingSession> trainingSessions = new ArrayList<>();

        when(templateTrainingSessionService.findByDate(date)).thenReturn(trainingSessions);

        ResponseEntity<List<TrainingSession>> response = trainingSessionController.findTemplateByDate(date);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(trainingSessions, response.getBody());

        verify(templateTrainingSessionService, times(1)).findByDate(date);
        verifyNoMoreInteractions(templateTrainingSessionService);
    }

    @Test
    public void deleteSessionTest() {
        String sessionId = "sessionId";

        ResponseEntity response = trainingSessionController.deleteSession(sessionId, null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verifyNoMoreInteractions(templateTrainingSessionService);
    }

    @Test
    public void insertSessionTest() {
        TrainingSession trainingSession = new TrainingSession();
        String planId = "planId";
        String dayId = "dayId";

        when(trainingSessionService.insertSession(trainingSession, planId, dayId)).thenReturn("sessionInserted");

        ResponseEntity<String> response = trainingSessionController.insertSession(trainingSession, planId, dayId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("sessionInserted", response.getBody());

        verify(trainingSessionService, times(1)).insertSession(trainingSession, planId, dayId);
        verifyNoMoreInteractions(trainingSessionService);
    }

    @Test
    public void findSessionsForDayTest() {
        String planId = "planId";
        String dayId = "dayId";
        List<TrainingSession> sessions = new ArrayList<>();

        when(trainingSessionService.findSessionsForDay(planId, dayId)).thenReturn(sessions);

        ResponseEntity<List<TrainingSession>> response = trainingSessionController.findSessionsForDay(planId, dayId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sessions, response.getBody());

        verify(trainingSessionService, times(1)).findSessionsForDay(planId, dayId);
        verifyNoMoreInteractions(trainingSessionService);
    }

    @Test
    public void updateSessionTest() {
        TrainingSession trainingSession = new TrainingSession();
        String planId = "planId";
        String dayId = "dayId";

        WriteResult writeResult = mock(WriteResult.class);
        ResponseEntity<WriteResult> expectedResponse = ResponseEntity.ok(writeResult);

        when(trainingSessionService.updateSession(trainingSession, planId, dayId)).thenReturn(writeResult);

        ResponseEntity<WriteResult> response = trainingSessionController.updateSession(trainingSession, planId, dayId);

        assertEquals(expectedResponse, response);

        verify(trainingSessionService, times(1)).updateSession(trainingSession, planId, dayId);
        verifyNoMoreInteractions(trainingSessionService);
    }
}
