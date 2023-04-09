package com.liftlife.liftlife.diet;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.liftlife.liftlife.LiftlifeApplication;
import com.liftlife.liftlife.database.FirestoreMapper;
import com.liftlife.liftlife.database.FirestoreRepositoryTemplate;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MealsRepositoryHelper extends FirestoreRepositoryTemplate {

    public MealsRepositoryHelper(String dietId, FirestoreMapper firestoreMapper) {
        super(FirestoreClient.getFirestore().collection("diets").document(dietId).collection("meals"),
                firestoreMapper);
    }

    public void deleteMeal(String mealId){
        super.deleteTemplate(mealId);
    }

    public void deleteMeal(Meal meal){
        super.deleteTemplate(meal);
    }

    public Meal findMealById(String mealId) throws ExecutionException, InterruptedException {
        return super.findOneByIdTemplate(mealId, Meal.class);
    }

    public WriteResult updateMeal(Meal meal) throws ExecutionException, InterruptedException {
        return super.updateTemplate(meal);
    }

    public String insertMeal(Meal meal) throws ExecutionException, InterruptedException {
        return super.insertTemplate(meal);
    }

    public List<Meal> findMealByName(String name) throws ExecutionException, InterruptedException {
        return super.findByField("name",name, Meal.class);
    }




}
