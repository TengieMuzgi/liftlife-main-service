package com.liftlife.liftlife.dietModule.product;

import com.liftlife.liftlife.util.database.FirestoreEntity;
import lombok.*;

/*
POJO class representing single product present in client's meal. Atomic part of diet.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product extends FirestoreEntity {
    String name;
    int calories;
}
