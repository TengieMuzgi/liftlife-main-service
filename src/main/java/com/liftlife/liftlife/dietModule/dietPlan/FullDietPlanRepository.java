package com.liftlife.liftlife.dietModule.dietPlan;

import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@DependsOn("firestoreConnector")
public class FullDietPlanRepository extends FirestoreRepositoryTemplate<FullDietPlan> {

    public FullDietPlanRepository(){
        super(FullDietPlan.class);
    }

    public List<FullDietPlan> findDietByTrainer(String trainerId) {
        return super.findByField("trainerId", trainerId);
    }
}
