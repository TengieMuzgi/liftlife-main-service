package com.liftlife.liftlife.dietModule.dietPlan;

import com.liftlife.liftlife.dietModule.dietDay.DietDay;
import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@DependsOn("firestoreConnector")
public class DietPlanRepository extends FirestoreRepositoryTemplate<DietPlan> {

    public DietPlanRepository(){
        super(DietPlan.class);
    }

    public List<DietPlan> findDietByTrainer(String trainerId) {
        return super.findByField("trainerId", trainerId);
    }
}
