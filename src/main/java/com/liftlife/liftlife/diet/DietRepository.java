package com.liftlife.liftlife.diet;

import com.google.cloud.firestore.WriteResult;
import com.liftlife.liftlife.database.FirestoreRepositoryTemplate;
import com.liftlife.liftlife.meal.Meal;
import com.liftlife.liftlife.meal.MealRepository;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
@DependsOn("firestoreConnector")
public class DietRepository extends FirestoreRepositoryTemplate<Diet> {

    public DietRepository() {
        super(Diet.class);
    }

    public List<Diet> findDietByName(String name) throws ExecutionException, InterruptedException {
        return super.findByField("name", name);
    }

    public List<Diet> findDietByTemplate(boolean template) throws ExecutionException, InterruptedException {
        return super.findByField("isTemplate", template);
    }

    public List<Diet> findDietByTrainer(String trainerId) throws ExecutionException, InterruptedException {
        return super.findByField("trainerId", trainerId);
    }

    //meals

    public void deleteMeal(String dietId, String mealId){
        MealRepository helper = new MealRepository(dietId, super.getFirestoreMapper());
        helper.delete(mealId);
    }

    public void deleteMeal(String dietId, Meal meal){
        MealRepository helper = new MealRepository(dietId, super.getFirestoreMapper());
        helper.delete(meal);
    }

    public String insertMeal(String dietId, Meal meal) throws ExecutionException, InterruptedException {
        MealRepository helper = new MealRepository(dietId, super.getFirestoreMapper());
        return helper.insert(meal);
    }

    public WriteResult updateMeal(String dietId, Meal meal) throws ExecutionException, InterruptedException {
        MealRepository helper = new MealRepository(dietId, super.getFirestoreMapper());
        return helper.update(meal);
    }

    public Meal findMealById(String dietId, String mealId) throws ExecutionException, InterruptedException {
        MealRepository helper = new MealRepository(dietId, super.getFirestoreMapper());
        return helper.findById(mealId);
    }

    public List<Meal> findMealByName(String dietId, String name) throws ExecutionException, InterruptedException {
        MealRepository helper = new MealRepository(dietId, super.getFirestoreMapper());
        return helper.findMealByName(name);
    }


}
