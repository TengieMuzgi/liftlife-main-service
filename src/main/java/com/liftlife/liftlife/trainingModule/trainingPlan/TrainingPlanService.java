package com.liftlife.liftlife.trainingModule.trainingPlan;

import com.liftlife.liftlife.trainingModule.trainingDay.TrainingDay;
import com.liftlife.liftlife.trainingModule.trainingDay.TrainingDayRepository;
import com.liftlife.liftlife.trainingModule.trainingDay.TrainingDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingPlanService {
    private final TrainingPlanRepository trainingPlanRepository;
    private final TrainingDayService trainingDayService;

    @Autowired
    public TrainingPlanService(TrainingPlanRepository trainingPlanRepository, TrainingDayRepository trainingDayRepository, TrainingDayService trainingDayService) {
        this.trainingPlanRepository = trainingPlanRepository;
        this.trainingDayService = trainingDayService;
    }

    public List<TrainingPlan> findPlansForCoach(String coachId) {
        return trainingPlanRepository.findPlansForCoach(coachId);
    }

    public String insertPlan(TrainingPlan trainingPlan) {
        String planId = trainingPlanRepository.insert(trainingPlan);
        for (int i = 0; i < 7; i++) {
            trainingDayService.insert(new TrainingDay(String.valueOf(i)), planId);
        }
        return planId;
    }

    public void deletePlan(String trainingPlanId) {
        trainingPlanRepository.delete(trainingPlanId);
    }

    public List<TrainingPlan> findPlansForClient(String clientId) {
        return trainingPlanRepository.findPlansForClient(clientId);
    }
}
