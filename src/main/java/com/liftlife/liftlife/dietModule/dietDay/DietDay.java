package com.liftlife.liftlife.dietModule.dietDay;

import com.liftlife.liftlife.util.database.FirestoreEntity;
import lombok.*;

/*
POJO class representing one day in week in diet plan. Contains meals, that should be
eaten at each time of day.
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DietDay extends FirestoreEntity {
    private String name;
    private int dayOfWeek;
    private transient int caloriesSum;
    private boolean isTemplate;
    private String trainerId;

    public DietDay(String name, int dayOfWeek, int caloriesSum, boolean isTemplate, String trainerId, String documentId) {
        super(documentId);
        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.caloriesSum = caloriesSum;
        this.isTemplate = isTemplate;
        this.trainerId = trainerId;
    }
}
