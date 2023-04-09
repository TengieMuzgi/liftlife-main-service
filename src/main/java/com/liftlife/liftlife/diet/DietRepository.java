package com.liftlife.liftlife.diet;

import com.google.cloud.firestore.WriteResult;
import com.liftlife.liftlife.database.FirestoreMapper;
import com.liftlife.liftlife.database.FirestoreRepositoryTemplate;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
@DependsOn("firestoreConnector")
public class DietRepository extends FirestoreRepositoryTemplate {

    public DietRepository(FirestoreMapper firestoreMapper) {
        super("diets", firestoreMapper);
    }

    public void deleteDiet(String dietId){
        super.deleteTemplate(dietId);
    }

    public void deleteDiet(Diet diet){
        super.deleteTemplate(diet);
    }

    public WriteResult updateDiet(Diet diet) throws ExecutionException, InterruptedException {
        return super.updateTemplate(diet);
    }

    public String insertDiet(Diet diet) throws ExecutionException, InterruptedException {
        return super.insertTemplate(diet);
    }

    public Diet findDietById(String dietId) throws ExecutionException, InterruptedException {
        return super.findOneByIdTemplate(dietId, Diet.class);
    }

    public List<Diet> findDietByName(String name) throws ExecutionException, InterruptedException {
        return super.findByField("name",name, Diet.class);
    }

    public List<Diet> findDietByTemplate(boolean template) throws ExecutionException, InterruptedException {
        return super.findByField("isTemplate",template, Diet.class);
    }

    public List<Diet> findDietByTrainer(String trainerId) throws ExecutionException, InterruptedException {
        return super.findByField("trainerId",trainerId, Diet.class);
    }

    //meals

    public void deleteMeal(String dietId, String mealId){
        MealsRepositoryHelper helper = new MealsRepositoryHelper(dietId, super.getFirestoreMapper());
        helper.deleteMeal(mealId);
    }

    public void deleteMeal(String dietId, Meal meal){
        MealsRepositoryHelper helper = new MealsRepositoryHelper(dietId, super.getFirestoreMapper());
        helper.deleteMeal(meal);
    }

    public String insertMeal(String dietId, Meal meal) throws ExecutionException, InterruptedException {
        MealsRepositoryHelper helper = new MealsRepositoryHelper(dietId, super.getFirestoreMapper());
        return helper.insertMeal(meal);
    }

    public WriteResult updateMeal(String dietId, Meal meal) throws ExecutionException, InterruptedException {
        MealsRepositoryHelper helper = new MealsRepositoryHelper(dietId, super.getFirestoreMapper());
        return helper.updateMeal(meal);
    }

    public Meal findMealById(String dietId, String mealId) throws ExecutionException, InterruptedException {
        MealsRepositoryHelper helper = new MealsRepositoryHelper(dietId, super.getFirestoreMapper());
        return helper.findMealById(mealId);
    }

    public List<Meal> findMealByName(String dietId, String name) throws ExecutionException, InterruptedException {
        MealsRepositoryHelper helper = new MealsRepositoryHelper(dietId, super.getFirestoreMapper());
        return helper.findMealByName(name);
    }


}
