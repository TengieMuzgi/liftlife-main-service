package com.liftlife.liftlife.dietModule.diet;

import com.google.cloud.firestore.WriteResult;
import com.liftlife.liftlife.dietModule.diet.meal.Meal;
import com.liftlife.liftlife.utils.database.FirestoreRepositoryTemplate;
import com.liftlife.liftlife.dietModule.diet.meal.MealRepository;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@DependsOn("firestoreConnector")
public class DietRepository extends FirestoreRepositoryTemplate<DietDay> {

    public DietRepository() {
        super(DietDay.class);
    }

    public List<DietDay> findDietByName(String name) {
        return super.findByField("name", name);
    }

    public List<DietDay> findDietByTemplate(boolean template) {
        return super.findByField("isTemplate", template);
    }

    public List<DietDay> findDietByTrainer(String trainerId) {
        return super.findByField("trainerId", trainerId);
    }

    //meals

    public void deleteMeal(String dietId, String mealId){
        MealRepository helper = new MealRepository(dietId, getFirestoreMapper());
        helper.delete(mealId);
    }

    public void deleteMeal(String dietId, Meal meal){
        MealRepository helper = new MealRepository(dietId, getFirestoreMapper());
        helper.delete(meal);
    }

    public String insertMeal(String dietId, Meal meal) {
        MealRepository helper = new MealRepository(dietId, getFirestoreMapper());
        return helper.insert(meal);
    }

    public WriteResult updateMeal(String dietId, Meal meal) {
        MealRepository helper = new MealRepository(dietId, getFirestoreMapper());
        return helper.update(meal);
    }

    public Meal findMealById(String dietId, String mealId) {
        MealRepository helper = new MealRepository(dietId, getFirestoreMapper());
        return helper.findById(mealId);
    }

    public List<Meal> findMealByName(String dietId, String name) {
        MealRepository helper = new MealRepository(dietId,getFirestoreMapper());
        return helper.findMealByName(name);
    }




}
