package com.liftlife.liftlife.trainingModule.trainingActivity;

import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public class TrainingActivityRepository extends FirestoreRepositoryTemplate<TrainingActivity> {
    public TrainingActivityRepository() {
        super(TrainingActivity.class);
    }

    public List<TrainingActivity> findByMondayForClient(LocalDate monday, String clientId) {
        Map<String, String> nameToValue = Map.of("weeksMonday", monday.toString(), "clientId", clientId);
        return super.findByFields(nameToValue);
    }
}
