package com.liftlife.liftlife.trainingModule.trainingSession;

import com.liftlife.liftlife.util.database.FirestoreEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

/*
POJO class representing one training session, that exists in client's/coach's calendar.
Is a part of training day.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrainingSession extends FirestoreEntity {
    @Min(0)
    @Max(23)
    private int startHour;
    @Min(0)
    @Max(23)
    private int finishHour;
    @NotEmpty
    private String name;
    private List<String> exercises;
    private transient boolean isTemplate;
}
