package com.liftlife.liftlife.trainingModule.trainingSession;

import com.google.cloud.firestore.WriteResult;
import com.liftlife.liftlife.trainingModule.exercise.Exercise;
import com.liftlife.liftlife.trainingModule.exercise.TemplateExerciseRepository;
import com.liftlife.liftlife.util.database.AttributeList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.mockito.Mockito.*;

public class TrainingSessionServiceTest {
    @Mock
    private TrainingSessionRepository repository;
    @Mock
    private TemplateExerciseRepository templateExerciseRepository;

    private TrainingSessionService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new TrainingSessionService(repository);
    }

    @Test
    public void insertSessionTest() {
        String planId = "planId";
        String dayId = "dayId";
        TrainingSession trainingSession = new TrainingSession();

        LinkedHashMap<String, String> documentToSubCollection = new LinkedHashMap<>();
        documentToSubCollection.put(planId, "trainingday");
        documentToSubCollection.put(dayId, "trainingsession");

        when(repository.insert(trainingSession, documentToSubCollection)).thenReturn("sessionId");

        String result = service.insertSession(trainingSession, planId, dayId);

        verify(repository, times(1)).insert(trainingSession, documentToSubCollection);
        verifyNoMoreInteractions(repository);

        assert result.equals("sessionId");
    }

    @Test
    public void findSessionsForDayTest() {
        String planId = "planId";
        String dayId = "dayId";

        LinkedHashMap<String, String> documentToSubCollection = new LinkedHashMap<>();
        documentToSubCollection.put(planId, "trainingday");
        documentToSubCollection.put(dayId, "trainingsession");

        List<TrainingSession> sessions = new ArrayList<>();
        sessions.add(new TrainingSession());
        sessions.add(new TrainingSession());

        when(repository.findAll(documentToSubCollection)).thenReturn(sessions);

        List<TrainingSession> result = service.findSessionsForDay(planId, dayId);

        verify(repository, times(1)).findAll(documentToSubCollection);
        verifyNoMoreInteractions(repository);

        Assertions.assertEquals(sessions, result);
    }


    @Test
    public void updateSessionTest() {
        String planId = "planId";
        String dayId = "dayId";
        TrainingSession trainingSession = new TrainingSession();

        LinkedHashMap<String, String> documentToSubCollection = new LinkedHashMap<>();
        documentToSubCollection.put(planId, "trainingday");
        documentToSubCollection.put(dayId, "trainingsession");

        when(repository.update(trainingSession, documentToSubCollection)).thenReturn(null);

        service.updateSession(trainingSession, planId, dayId);

        verify(repository, times(1)).update(trainingSession, documentToSubCollection);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void deleteSessionTest() {
        String planId = "planId";
        String dayId = "dayId";
        String sessionId = "sessionId";

        LinkedHashMap<String, String> documentToSubCollection = new LinkedHashMap<>();
        documentToSubCollection.put(planId, "trainingday");
        documentToSubCollection.put(dayId, "trainingsession");

        service.deleteSession(sessionId, planId, dayId);

        verify(repository, times(1)).delete(sessionId, documentToSubCollection);
        verifyNoMoreInteractions(repository);
    }
}
