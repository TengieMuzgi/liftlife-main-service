package com.liftlife.liftlife.trainingModule.activity;

import com.liftlife.liftlife.trainingModule.trainingActivity.TrainingActivity;
import com.liftlife.liftlife.trainingModule.trainingActivity.TrainingActivityRepository;
import com.liftlife.liftlife.trainingModule.trainingActivity.TrainingActivityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TrainingActivityServiceTest {

    @Mock
    private TrainingActivityRepository repository;

    private TrainingActivityService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new TrainingActivityService(repository);
    }

    @Test
    public void findByMondayForClientTest() {
        LocalDate monday = LocalDate.of(2023, 1, 2);
        String clientId = "clientId";

        List<TrainingActivity> activities = new ArrayList<>();
        activities.add(new TrainingActivity());
        activities.add(new TrainingActivity());

        when(repository.findByMondayForClient(monday, clientId)).thenReturn(activities);

        List<TrainingActivity> result = service.findByMondayForClient(monday, clientId);

        verify(repository, times(1)).findByMondayForClient(monday, clientId);
        verifyNoMoreInteractions(repository);

        Assertions.assertEquals(activities, result);
    }

    @Test
    public void insertClientActivityTest() {
        TrainingActivity trainingActivity = new TrainingActivity();
        trainingActivity.setWeeksMonday(LocalDate.now().with(DayOfWeek.MONDAY));

        String expectedId = "activityId";

        when(repository.insert(trainingActivity)).thenReturn(expectedId);

        String result = service.insertClientActivity(trainingActivity);

        verify(repository, times(1)).insert(trainingActivity);
        verifyNoMoreInteractions(repository);

        Assertions.assertEquals(expectedId, result);
    }
}
