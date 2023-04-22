package com.liftlife.liftlife.diets.product;

import com.liftlife.liftlife.database.FirestoreEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product extends FirestoreEntity {
    String name;
    int calories;
}
