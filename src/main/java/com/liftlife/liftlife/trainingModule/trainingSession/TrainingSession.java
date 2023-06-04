package com.liftlife.liftlife.trainingModule.trainingSession;

import com.liftlife.liftlife.util.database.FirestoreEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrainingSession extends FirestoreEntity {
    @Min(value = 0, message = "The startHour must be greater than or equal to 0")
    @Max(value = 23, message = "The startHour must be less than or equal to 23")
    private int startHour;

    @Min(value = 0, message = "The finishHour must be greater than or equal to 0")
    @Max(value = 23, message = "The finishHour must be less than or equal to 23")
    private int finishHour;

    @NotEmpty(message = "The name must not be empty")
    private String name;

    private List<String> exercises;

    private transient boolean isTemplate;
}
