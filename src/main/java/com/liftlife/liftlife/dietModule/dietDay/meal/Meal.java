package com.liftlife.liftlife.dietModule.dietDay.meal;

import com.liftlife.liftlife.util.database.FirestoreEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Meal extends FirestoreEntity {
    private String name;
    private List<String> products;
}
