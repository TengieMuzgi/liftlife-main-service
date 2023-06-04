package com.liftlife.liftlife.trainingModule.day;

import com.google.cloud.firestore.WriteResult;
import com.liftlife.liftlife.trainingModule.trainingDay.TrainingDay;
import com.liftlife.liftlife.trainingModule.trainingDay.TrainingDayRepository;
import com.liftlife.liftlife.trainingModule.trainingDay.TrainingDayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.mockito.Mockito.*;

public class TrainingDayServiceTest {
    @Mock
    private TrainingDayRepository trainingDayRepository;

    private TrainingDayService trainingDayService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        trainingDayService = new TrainingDayService(trainingDayRepository);
    }

    @Test
    public void findDaysForPlanTest() {
        String planId = "planId";
        LinkedHashMap<String, String> documentToSubCollection = new LinkedHashMap<>();
        documentToSubCollection.put(planId, "trainingday");
        List<TrainingDay> days = new ArrayList<>();
        TrainingDay day1 = new TrainingDay();
        day1.setDayOfWeek(1);
        TrainingDay day2 = new TrainingDay();
        day2.setDayOfWeek(2);
        days.add(day1);
        days.add(day2);

        when(trainingDayRepository.findAll(documentToSubCollection)).thenReturn(days);

        List<TrainingDay> result = trainingDayService.findDaysForPlan(planId);

        verify(trainingDayRepository, times(1)).findAll(documentToSubCollection);
        verifyNoMoreInteractions(trainingDayRepository);

        assert result == days;
    }

    @Test
    public void insertTest() {
        String planId = "planId";
        LinkedHashMap<String, String> documentToSubCollection = new LinkedHashMap<>();
        documentToSubCollection.put(planId, "trainingday");
        TrainingDay trainingDay = new TrainingDay();
        trainingDay.setDayOfWeek(1);

        when(trainingDayRepository.insert(trainingDay, documentToSubCollection)).thenReturn("dayId");

        String result = trainingDayService.insert(trainingDay, planId);

        verify(trainingDayRepository, times(1)).insert(trainingDay, documentToSubCollection);
        verifyNoMoreInteractions(trainingDayRepository);

        assert result.equals("dayId");
    }

    @Test
    public void deleteTest() {
        String dayId = "dayId";
        String planId = "planId";
        LinkedHashMap<String, String> documentToSubCollection = new LinkedHashMap<>();
        documentToSubCollection.put(planId, "trainingday");

        trainingDayService.delete(dayId, planId);

        verify(trainingDayRepository, times(1)).delete(dayId, documentToSubCollection);
        verifyNoMoreInteractions(trainingDayRepository);
    }

    @Test
    public void updateDayTest() {
        String planId = "planId";
        LinkedHashMap<String, String> documentToSubCollection = new LinkedHashMap<>();
        documentToSubCollection.put(planId, "trainingday");
        TrainingDay trainingDay = new TrainingDay();
        trainingDay.setDayOfWeek(1);

        when(trainingDayRepository.update(trainingDay, documentToSubCollection)).thenReturn(null);

        trainingDayService.updateDay(trainingDay, planId);

        verify(trainingDayRepository, times(1)).update(trainingDay, documentToSubCollection);
        verifyNoMoreInteractions(trainingDayRepository);
    }
}
