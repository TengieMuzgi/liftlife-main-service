package com.liftlife.liftlife.trainingModule.trainingPlan;

import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainingPlanRepository extends FirestoreRepositoryTemplate<TrainingPlan> {
    public TrainingPlanRepository() {
        super(TrainingPlan.class);
    }

    public List<TrainingPlan> findPlansForCoach(String coachId) {
        return super.findByField("coachId", coachId);
    }

    public List<TrainingPlan> findPlansForClient(String clientId) {
        return super.findByField("clientId", clientId);
    }
}
