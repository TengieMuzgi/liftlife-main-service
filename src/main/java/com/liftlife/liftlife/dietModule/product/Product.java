package com.liftlife.liftlife.dietModule.product;

import com.liftlife.liftlife.util.database.DietEntity;
import lombok.*;

/*
POJO class representing single product present in client's meal. Atomic part of diet.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product extends DietEntity {
    String name;
    int calories;
}
