package com.liftlife.liftlife.trainingModule.trainingDay;

import com.liftlife.liftlife.trainingModule.trainingSession.TrainingSession;
import com.liftlife.liftlife.util.database.FirestoreEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrainingDay extends FirestoreEntity {
    @NotNull(message = "The dayOfWeek must not be null")
    @Min(value = 1, message = "The dayOfWeek must be greater than or equal to 1")
    @Max(value = 7, message = "The dayOfWeek must be less than or equal to 7")
    private int dayOfWeek;
    private transient List<TrainingSession> trainingSessions;
}
