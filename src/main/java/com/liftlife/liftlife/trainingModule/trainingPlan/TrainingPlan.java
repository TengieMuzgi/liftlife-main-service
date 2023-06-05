package com.liftlife.liftlife.trainingModule.trainingPlan;

import com.liftlife.liftlife.util.database.FirestoreEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrainingPlan extends FirestoreEntity {
    @NotNull(message = "The name must not be null")
    private String name;

    @NotNull(message = "The clientId must not be null")
    private String clientId;

    @NotNull(message = "The coachId must not be null")
    private String coachId;
}
