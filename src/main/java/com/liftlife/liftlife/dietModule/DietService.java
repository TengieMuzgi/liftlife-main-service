package com.liftlife.liftlife.dietModule;



import com.liftlife.liftlife.dietModule.dietDay.DietDay;
import com.liftlife.liftlife.dietModule.dietDay.DietDayRepository;
import com.liftlife.liftlife.dietModule.dietDay.meal.Meal;
import com.liftlife.liftlife.dietModule.dietPlan.DietPlanRepository;
import com.liftlife.liftlife.dietModule.product.ProductRepository;
import com.liftlife.liftlife.util.database.FirestoreEntity;
import com.liftlife.liftlife.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DietService {

    //TODO coordinate all services with current user (UserService should has access to all services and deliver them their data)

    private ProductRepository productRepository;
    private DietDayRepository dietDayRepository;
    private DietPlanRepository dietPlanRepository;

    @Autowired
    public DietService(ProductRepository productRepository, DietDayRepository dietDayRepository, DietPlanRepository dietPlanRepository) {
        this.productRepository = productRepository;
        this.dietDayRepository = dietDayRepository;
        this.dietPlanRepository = dietPlanRepository;
    }

    //draft
    /*
    CLIENT
    can only view
    findBy id/name diet/meal
    ADMIN
    adding/updating/removing templates and products (all products are templates)
    findBy template -> id all
    TRAINER
    adding/updating/removing their templates
    creating diet/meals from their template/general template/from scratch and adding to client
    updating/deleting diet/meal for client
    findBy id/name/isTemplate/theirId

    ???
    updating/removing/adding data in particular repositories
    adding/removing products in meals
    findBy id/template/trainer ...
    adding/removing/updating diets/meals for clients/ in trainer templates
    creating new meal/diet (template/for user)
    deleting meals/diets\
    !!admin only!! creating/updating/deleting templates
    create from template
    */

    /*
    public Diet findDietById(String id){
        Diet diet = dietRepository.findById(id);

        if(diet == null)
            throw new NotFoundException("Diet with ID " + id + " not found");

        return diet;
    }

    public Meal findMealById(String dietId, String mealId){
        Meal meal = dietRepository.findMealById(dietId, mealId);

        if(meal == null)
            throw new NotFoundException("Meal with ID " + mealId + " not found");

        return meal;
    }

    public Product findProductById(String id){
        Product product = productRepository.findById(id);

        if(product == null)
            throw new NotFoundException("Product with ID " + id + " not found");

        return product;
    }
     */

    public Meal findById(String dietId, String mealId){
        Meal meal = dietDayRepository.findMealById(dietId, mealId);

        if(meal == null)
            throw new NotFoundException("Meal with ID " + mealId + " not found");

        return meal;
    }


    public <T extends FirestoreEntity> T findById(String id, DietServiceType type){
        T entity = null;

        switch(type){
            case DIET_DAY : entity = (T) dietDayRepository.findById(id); break;
            case PRODUCT: entity = (T) productRepository.findById(id); break;
            case DIET_PLAN: entity = (T) dietPlanRepository.findById(id); break;
        }

        if(entity == null)
            throw new NotFoundException("Object with ID " + id + " not found");

        return entity;
    }

    public List<Meal> findByName(String dietId, String name){
        List<Meal> meals = dietDayRepository.findMealByName(dietId, name);

        if(meals.isEmpty())
            throw new NotFoundException("Meals with name " + name + " not found");

        return meals;
    }


    public List<Meal> findByFields(String dietId, Map<String, Object> fields){
        List<Meal> meals = dietDayRepository.findMealByFields(dietId, fields);

        if(meals.isEmpty())
            throw new NotFoundException("No meals found");

        return meals;
    }


    public <T extends FirestoreEntity> List<T> findByFields(DietServiceType type, Map<String, Object> fields){
        List<T> entityList = new ArrayList<>();

        switch(type){
            case DIET_DAY : entityList = (List<T>) dietDayRepository.findByFields(fields); break;
            case PRODUCT: entityList = (List<T>) productRepository.findByFields(fields); break;
            case DIET_PLAN: entityList = (List<T>) dietPlanRepository.findByFields(fields); break;
        }

        if(entityList.isEmpty())
            throw new NotFoundException("No objects found");

        return entityList;
    }

    public List<DietDay> findByTemplate(){
        List<DietDay> diets = dietDayRepository.findDietByTemplate(true);

        if(diets.isEmpty())
            throw new NotFoundException("No template diets were found");

        return diets;
    }

    public List<DietDay> findByTrainer(String id){
        List<DietDay> diets = dietDayRepository.findDietByTrainer(id);

        if(diets.isEmpty())
            throw new NotFoundException("No diets for trainerId " + id + " were found");

        return diets;
    }
}
