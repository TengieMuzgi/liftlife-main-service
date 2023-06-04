package com.liftlife.liftlife.trainingModule.exercise;

import com.liftlife.liftlife.util.database.FirestoreEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Exercise extends FirestoreEntity {
    private String name;

    @NotNull(message = "The body part must not be null")
    private String bodyPart;

    private String description;

    @Positive(message = "The number of reps must be a positive value")
    private int numberOfReps;

    @Positive(message = "The number of sets must be a positive value")
    private int numberOfSets;

    private transient boolean isTemplate;
}