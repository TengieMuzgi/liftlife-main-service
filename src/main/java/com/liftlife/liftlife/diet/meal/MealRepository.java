package com.liftlife.liftlife.diet.meal;

import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.liftlife.liftlife.database.FirestoreMapper;
import com.liftlife.liftlife.database.FirestoreRepositoryTemplate;

import java.util.List;
import java.util.concurrent.ExecutionException;



public class MealRepository extends FirestoreRepositoryTemplate<Meal> {

    public MealRepository(String dietId, FirestoreMapper mapper) {
        super(Meal.class,
                FirestoreClient.getFirestore().collection("diet").document(dietId).collection("meal"),
                mapper);
    }

    public List<Meal> findMealByName(String name) {
        return super.findByField("name", name);
    }

    public void deleteMeal(String mealId){
        super.delete(mealId);
    }

    public void deleteMeal(Meal meal){
        super.delete(meal);
    }

    public Meal findMealById(String mealId) throws ExecutionException, InterruptedException {
        return super.findById(mealId);
    }

    public WriteResult updateMeal(Meal meal) throws ExecutionException, InterruptedException {
        return super.update(meal);
    }

    public String insertMeal(Meal meal) throws ExecutionException, InterruptedException {
        return super.insert(meal);
    }




}
