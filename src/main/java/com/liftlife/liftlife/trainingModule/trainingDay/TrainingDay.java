package com.liftlife.liftlife.trainingModule.trainingDay;

import com.liftlife.liftlife.trainingModule.trainingSession.TrainingSession;
import com.liftlife.liftlife.util.database.FirestoreEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrainingDay extends FirestoreEntity {
    private transient List<TrainingSession> trainingSessions;
    public TrainingDay(String documentId) {
        super(documentId);
    }
}
