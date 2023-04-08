package com.liftlife.liftlife.training;


import com.liftlife.liftlife.database.FirestoreEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Training extends FirestoreEntity {

    //:TODO change to LocalDate
    private String date;
    private Integer duration;
    private List<String> exercises;
    private String name;
    private boolean isTemplate;
    private String trainerId;

    public LocalDate getDate(){
        try{
            return LocalDate.parse(date);
        }catch (DateTimeParseException e){
            return null;
        }
    }
}
