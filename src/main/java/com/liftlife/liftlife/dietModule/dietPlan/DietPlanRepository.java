package com.liftlife.liftlife.dietModule.dietPlan;

import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

@Repository
@DependsOn("firestoreConnector")
public class DietPlanRepository extends FirestoreRepositoryTemplate<DietPlan> {

    public DietPlanRepository(){
        super(DietPlan.class);
    }
}
