package com.liftlife.liftlife.trainingModule.trainingSession;

import com.liftlife.liftlife.trainingModule.exercise.Exercise;
import com.liftlife.liftlife.trainingModule.exercise.TemplateExerciseRepository;
import com.liftlife.liftlife.trainingModule.trainingSession.template.TemplateTrainingSessionRepository;
import com.liftlife.liftlife.trainingModule.trainingSession.template.TemplateTrainingSessionService;
import com.liftlife.liftlife.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TemplateTrainingSessionServiceTest {

    @Mock
    private TemplateTrainingSessionRepository templateTrainingSessionRepository;
    @Mock
    private TemplateExerciseRepository templateExerciseRepository;

    private TemplateTrainingSessionService templateTrainingSessionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        templateTrainingSessionService = new TemplateTrainingSessionService(templateTrainingSessionRepository,
                templateExerciseRepository);
    }

    @Test
    public void insertTest() {
        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setName("Session 1");

        when(templateTrainingSessionRepository.insert(trainingSession)).thenReturn("sessionId");

        ResponseEntity<String> response = templateTrainingSessionService.insert(trainingSession);
        String result = response.getBody();

        verify(templateTrainingSessionRepository, times(1)).insert(trainingSession);
        verifyNoMoreInteractions(templateTrainingSessionRepository);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals("Training inserted with ID sessionId", result);
    }

    @Test
    public void findByIDTest_existingTrainingSession() {
        String sessionId = "sessionId";
        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setDocumentId(sessionId);

        when(templateTrainingSessionRepository.findById(sessionId)).thenReturn(trainingSession);

        TrainingSession result = templateTrainingSessionService.findByID(sessionId);

        verify(templateTrainingSessionRepository, times(1)).findById(sessionId);
        verifyNoMoreInteractions(templateTrainingSessionRepository);

        Assertions.assertEquals(trainingSession, result);
    }

    @Test
    public void findByIDTest_nonExistingTrainingSession() {
        String sessionId = "sessionId";

        when(templateTrainingSessionRepository.findById(sessionId)).thenReturn(null);

        Assertions.assertThrows(NotFoundException.class,
                () -> templateTrainingSessionService.findByID(sessionId));

        verify(templateTrainingSessionRepository, times(1)).findById(sessionId);
        verifyNoMoreInteractions(templateTrainingSessionRepository);
    }

    @Test
    public void findByTrainerTest_existingTrainingSessions() {
        String trainerId = "trainerId";
        List<TrainingSession> trainingSessions = new ArrayList<>();
        trainingSessions.add(new TrainingSession());
        trainingSessions.add(new TrainingSession());

        when(templateTrainingSessionRepository.findTrainingByTrainer(trainerId)).thenReturn(trainingSessions);

        List<TrainingSession> result = templateTrainingSessionService.findByTrainer(trainerId);

        verify(templateTrainingSessionRepository, times(1)).findTrainingByTrainer(trainerId);
        verifyNoMoreInteractions(templateTrainingSessionRepository);

        Assertions.assertEquals(trainingSessions, result);
    }

    @Test
    public void findByTrainerTest_nonExistingTrainingSessions() {
        String trainerId = "trainerId";

        when(templateTrainingSessionRepository.findTrainingByTrainer(trainerId)).thenReturn(new ArrayList<>());

        Assertions.assertThrows(NotFoundException.class,
                () -> templateTrainingSessionService.findByTrainer(trainerId));

        verify(templateTrainingSessionRepository, times(1)).findTrainingByTrainer(trainerId);
        verifyNoMoreInteractions(templateTrainingSessionRepository);
    }

    @Test
    public void findByTemplateTest_existingTrainingSessions() {
        List<TrainingSession> trainingSessions = new ArrayList<>();
        trainingSessions.add(new TrainingSession());
        trainingSessions.add(new TrainingSession());

        when(templateTrainingSessionRepository.findTrainingByTemplate(true)).thenReturn(trainingSessions);

        List<TrainingSession> result = templateTrainingSessionService.findByTemplate(true);

        verify(templateTrainingSessionRepository, times(1)).findTrainingByTemplate(true);
        verifyNoMoreInteractions(templateTrainingSessionRepository);

        Assertions.assertEquals(trainingSessions, result);
    }

    @Test
    public void findByTemplateTest_nonExistingTrainingSessions() {
        when(templateTrainingSessionRepository.findTrainingByTemplate(true)).thenReturn(new ArrayList<>());

        Assertions.assertThrows(NotFoundException.class,
                () -> templateTrainingSessionService.findByTemplate(true));

        verify(templateTrainingSessionRepository, times(1)).findTrainingByTemplate(true);
        verifyNoMoreInteractions(templateTrainingSessionRepository);
    }

    @Test
    public void findByDateTest_existingTrainingSessions() {
        String date = "2023-06-04";
        List<TrainingSession> trainingSessions = new ArrayList<>();
        trainingSessions.add(new TrainingSession());
        trainingSessions.add(new TrainingSession());

        when(templateTrainingSessionRepository.findByDate(date)).thenReturn(trainingSessions);

        List<TrainingSession> result = templateTrainingSessionService.findByDate(date);

        verify(templateTrainingSessionRepository, times(1)).findByDate(date);
        verifyNoMoreInteractions(templateTrainingSessionRepository);

        Assertions.assertEquals(trainingSessions, result);
    }

    @Test
    public void findByDateTest_nonExistingTrainingSessions() {
        String date = "2023-06-04";

        when(templateTrainingSessionRepository.findByDate(date)).thenReturn(new ArrayList<>());

        Assertions.assertThrows(NotFoundException.class,
                () -> templateTrainingSessionService.findByDate(date));

        verify(templateTrainingSessionRepository, times(1)).findByDate(date);
        verifyNoMoreInteractions(templateTrainingSessionRepository);
    }

    @Test
    public void deleteTest() {
        String documentId = "sessionId";

        templateTrainingSessionService.delete(documentId);

        verify(templateTrainingSessionRepository, times(1)).delete(documentId);
        verifyNoMoreInteractions(templateTrainingSessionRepository);
    }

    @Test
    public void findAllByCoachTest() {
        String trainerId = "trainerId";
        List<TrainingSession> trainingSessions = new ArrayList<>();
        trainingSessions.add(new TrainingSession());
        trainingSessions.add(new TrainingSession());

        when(templateTrainingSessionRepository.findAll()).thenReturn(trainingSessions);

        List<TrainingSession> result = templateTrainingSessionService.findAllByCoach(trainerId);

        verify(templateTrainingSessionRepository, times(1)).findAll();
        verifyNoMoreInteractions(templateTrainingSessionRepository);

        Assertions.assertEquals(trainingSessions, result);
    }
}
