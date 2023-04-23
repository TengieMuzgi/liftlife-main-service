package com.liftlife.liftlife.product;

import com.liftlife.liftlife.utils.database.FirestoreEntity;
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
