package com.liftlife.liftlife.meal;

import com.google.firebase.cloud.FirestoreClient;
import com.liftlife.liftlife.database.FirestoreMapper;
import com.liftlife.liftlife.database.FirestoreRepositoryTemplate;

import java.util.List;

public class MealRepository extends FirestoreRepositoryTemplate<Meal> {

    //TODO - repository to rework

    public MealRepository(String dietId, FirestoreMapper firestoreMapper) {
        super(FirestoreClient.getFirestore().collection("diets").document(dietId).collection("meals"),
                firestoreMapper);
    }

    public List<Meal> findMealByName(String name) {
        return super.findByField("name", name);
    }




}
