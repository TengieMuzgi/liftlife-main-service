package com.liftlife.liftlife.trainingModule.day;
import com.google.cloud.firestore.WriteResult;
import com.liftlife.liftlife.trainingModule.trainingDay.TrainingDay;
import com.liftlife.liftlife.trainingModule.trainingDay.TrainingDayController;
import com.liftlife.liftlife.trainingModule.trainingDay.TrainingDayService;
import com.liftlife.liftlife.trainingModule.trainingSession.TrainingSession;
import com.liftlife.liftlife.trainingModule.trainingSession.TrainingSessionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TrainingDayControllerTest {

    @Mock
    private TrainingDayService trainingDayService;

    @Mock
    private TrainingSessionService trainingSessionService;

    private TrainingDayController trainingDayController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        trainingDayController = new TrainingDayController(trainingDayService, trainingSessionService);
    }

    @Test
    public void findDaysForPlanTest() {
        String planId = "planId";
        List<TrainingDay> days = new ArrayList<>();
        TrainingDay day1 = new TrainingDay();
        day1.setDocumentId("day1Id");
        TrainingDay day2 = new TrainingDay();
        day2.setDocumentId("day2Id");
        days.add(day1);
        days.add(day2);

        List<TrainingSession> sessions = new ArrayList<>();
        TrainingSession session1 = new TrainingSession();
        session1.setDocumentId("session1Id");
        TrainingSession session2 = new TrainingSession();
        session2.setDocumentId("session2Id");
        sessions.add(session1);
        sessions.add(session2);

        when(trainingDayService.findDaysForPlan(planId)).thenReturn(days);
        when(trainingSessionService.findSessionsForDay(planId, day1.getDocumentId())).thenReturn(sessions);
        when(trainingSessionService.findSessionsForDay(planId, day2.getDocumentId())).thenReturn(new ArrayList<>());

        ResponseEntity<List<TrainingDay>> response = trainingDayController.findDaysForPlan(planId);
        List<TrainingDay> result = response.getBody();

        verify(trainingDayService, times(1)).findDaysForPlan(planId);
        verify(trainingSessionService, times(1)).findSessionsForDay(planId, day1.getDocumentId());
        verify(trainingSessionService, times(1)).findSessionsForDay(planId, day2.getDocumentId());
        verifyNoMoreInteractions(trainingDayService, trainingSessionService);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(sessions, result.get(0).getTrainingSessions());
        Assertions.assertTrue(result.get(1).getTrainingSessions().isEmpty());
    }

    @Test
    public void insertDayTest() {
        String planId = "planId";
        TrainingDay trainingDay = new TrainingDay();

        when(trainingDayService.insert(trainingDay, planId)).thenReturn("dayId");

        ResponseEntity<String> response = trainingDayController.insertDay(trainingDay, planId);
        String result = response.getBody();

        verify(trainingDayService, times(1)).insert(trainingDay, planId);
        verifyNoMoreInteractions(trainingDayService);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("dayId", result);
    }

    @Test
    public void deleteDayTest() {
        String dayId = "dayId";
        String planId = "planId";

        ResponseEntity response = trainingDayController.deleteDay(dayId, planId);

        verify(trainingDayService, times(1)).delete(dayId, planId);
        verifyNoMoreInteractions(trainingDayService);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateDayTest() {
        String planId = "planId";
        TrainingDay trainingDay = new TrainingDay();

        when(trainingDayService.updateDay(trainingDay, planId)).thenReturn(mock(WriteResult.class));

        ResponseEntity<WriteResult> response = trainingDayController.updateDay(trainingDay, planId);
        WriteResult result = response.getBody();

        verify(trainingDayService, times(1)).updateDay(trainingDay, planId);
        verifyNoMoreInteractions(trainingDayService);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(result);
    }
}
