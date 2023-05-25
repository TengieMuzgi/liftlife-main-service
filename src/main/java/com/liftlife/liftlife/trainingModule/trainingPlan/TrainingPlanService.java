package com.liftlife.liftlife.trainingModule.trainingPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingPlanService {
    private final TrainingPlanRepository trainingPlanRepository;

    @Autowired
    public TrainingPlanService(TrainingPlanRepository trainingPlanRepository) {
        this.trainingPlanRepository = trainingPlanRepository;
    }

    public List<TrainingPlan> findPlansForCoach(String coachId) {
        return trainingPlanRepository.findPlansForCoach(coachId);
    }

    public String insertPlan(TrainingPlan trainingPlan) {
        return trainingPlanRepository.insert(trainingPlan);
    }

    public void deletePlan(String trainingPlanId) {
        trainingPlanRepository.delete(trainingPlanId);
    }
}
