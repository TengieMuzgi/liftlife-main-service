package com.liftlife.liftlife.trainingModule.trainingDay;

import com.liftlife.liftlife.util.database.FirestoreSubRepositoryTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingDayRepository extends FirestoreSubRepositoryTemplate<TrainingDay> {
    public TrainingDayRepository() {
        super(TrainingDay.class, "trainingplan");
    }
}
