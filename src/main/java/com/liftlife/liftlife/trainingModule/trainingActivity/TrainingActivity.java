package com.liftlife.liftlife.trainingModule.trainingActivity;

import com.liftlife.liftlife.util.database.FirestoreEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrainingActivity extends FirestoreEntity {
    @NotNull
    private String clientId;
    @NotNull
    private String sessionId;
    private String info;
    private LocalDate weeksMonday;
    private Date timeStamp;
}
