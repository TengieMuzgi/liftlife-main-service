package com.liftlife.liftlife.util.database;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.liftlife.liftlife.dietModule.dietDay.DietDay;
import com.liftlife.liftlife.dietModule.dietDay.FullDietDay;
import com.liftlife.liftlife.dietModule.dietPlan.DietPlan;
import com.liftlife.liftlife.dietModule.dietPlan.FullDietPlan;
import com.liftlife.liftlife.trainingModule.exercise.Exercise;
import com.liftlife.liftlife.trainingModule.trainingDay.TrainingDay;
import com.liftlife.liftlife.trainingModule.trainingPlan.TrainingPlan;
import com.liftlife.liftlife.trainingModule.trainingSession.TrainingSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DietPlan.class, name = "Plan"),
        @JsonSubTypes.Type(value = DietDay.class, name = "Day"),
        @JsonSubTypes.Type(value = FullDietDay.class, name = "FullDay"),
        @JsonSubTypes.Type(value = FullDietPlan.class, name = "FullPlan"),
        @JsonSubTypes.Type(value = TrainingPlan.class, name = "TrainingPlan"),
        @JsonSubTypes.Type(value = TrainingDay.class, name = "TrainingDay"),
        @JsonSubTypes.Type(value = TrainingSession.class, name = "TrainingSession"),
        @JsonSubTypes.Type(value = Exercise.class, name = "Exercise")
})
public abstract class FirestoreEntity implements Serializable {
    private transient String documentId;
}
