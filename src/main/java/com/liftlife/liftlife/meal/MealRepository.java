package com.liftlife.liftlife.meal;

import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.liftlife.liftlife.database.FirestoreMapper;
import com.liftlife.liftlife.database.FirestoreRepositoryTemplate;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
