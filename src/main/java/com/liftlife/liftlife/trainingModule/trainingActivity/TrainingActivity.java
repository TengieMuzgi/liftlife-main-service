package com.liftlife.liftlife.trainingModule.trainingActivity;

import com.liftlife.liftlife.util.database.FirestoreEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Past;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrainingActivity extends FirestoreEntity {
    @NotNull(message = "The clientId must not be null")
    private String clientId;

    @NotNull(message = "The sessionId must not be null")
    private String sessionId;

    private String info;

    @Past(message = "The weeksMonday must be a future date")
    private LocalDate weeksMonday;
    private Date timeStamp;
}
