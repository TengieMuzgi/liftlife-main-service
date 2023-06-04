package com.liftlife.liftlife.util.database;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.liftlife.liftlife.dietModule.dietDay.DietDay;
import com.liftlife.liftlife.dietModule.dietDay.FullDietDay;
import com.liftlife.liftlife.dietModule.dietPlan.DietPlan;
import com.liftlife.liftlife.dietModule.dietPlan.FullDietPlan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DietPlan.class, name = "Plan"),
        @JsonSubTypes.Type(value = DietDay.class, name = "Day"),
        @JsonSubTypes.Type(value = FullDietDay.class, name = "FullDay"),
        @JsonSubTypes.Type(value = FullDietPlan.class, name = "FullPlan")
})
public class DietEntity extends FirestoreEntity {
    public DietEntity(String documentId) {
        super(documentId);
    }
}