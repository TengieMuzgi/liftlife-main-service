package com.liftlife.liftlife.dietModule.dietDay.meal;

import com.liftlife.liftlife.util.database.DietEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Meal extends DietEntity {
    private String name;
    private List<String> products;
}
