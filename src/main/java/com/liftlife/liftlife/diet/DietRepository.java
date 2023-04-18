package com.liftlife.liftlife.diet;

import com.google.cloud.firestore.WriteResult;
import com.liftlife.liftlife.database.FirestoreRepositoryTemplate;
import com.liftlife.liftlife.meal.Meal;
import com.liftlife.liftlife.meal.MealRepository;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@DependsOn("firestoreConnector")
public class DietRepository extends FirestoreRepositoryTemplate<Diet> {

    //TODO - to rework after changes in MealRepository

    public DietRepository() {
        super(Diet.class);
    }

    public List<Diet> findDietByName(String name) {
        return super.findByField("name", name);
    }

    public List<Diet> findDietByTemplate(boolean template) {
        return super.findByField("isTemplate", template);
    }

    public List<Diet> findDietByTrainer(String trainerId) {
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

    public String insertMeal(String dietId, Meal meal) {
        MealRepository helper = new MealRepository(dietId, super.getFirestoreMapper());
        return helper.insert(meal);
    }

    public WriteResult updateMeal(String dietId, Meal meal) {
        MealRepository helper = new MealRepository(dietId, super.getFirestoreMapper());
        return helper.update(meal);
    }

    public Meal findMealById(String dietId, String mealId) {
        MealRepository helper = new MealRepository(dietId, super.getFirestoreMapper());
        return helper.findById(mealId);
    }

    public List<Meal> findMealByName(String dietId, String name) {
        MealRepository helper = new MealRepository(dietId, super.getFirestoreMapper());
        return helper.findMealByName(name);
    }


}
