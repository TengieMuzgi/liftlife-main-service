package com.liftlife.liftlife.diets;

import com.liftlife.liftlife.database.FirestoreEntity;
import com.liftlife.liftlife.diets.diet.Diet;
import com.liftlife.liftlife.diets.diet.DietRepository;
import com.liftlife.liftlife.diets.diet.meal.Meal;
import com.liftlife.liftlife.diets.product.Product;
import com.liftlife.liftlife.diets.product.ProductRepository;
import com.liftlife.liftlife.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DietService {

    //TODO coordinate all services with current user (UserService should has access to all services and deliver them their data)

    private ProductRepository productRepository;
    private DietRepository dietRepository;

    @Autowired
    public DietService(ProductRepository productRepository, DietRepository dietRepository) {
        this.productRepository = productRepository;
        this.dietRepository = dietRepository;
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
        Meal meal = dietRepository.findMealById(dietId, mealId);

        if(meal == null)
            throw new NotFoundException("Meal with ID " + mealId + " not found");

        return meal;
    }

    public <T extends FirestoreEntity> T findById(String id, DietServiceType type){
        T entity = null;

        switch(type){
            case DIET : entity = (T) dietRepository.findById(id); break;
            case PRODUCT: entity = (T) productRepository.findById(id); break;
        }

        if(entity == null)
            throw new NotFoundException("Object with ID " + id + " not found");

        return entity;
    }

    public <Q> List<Meal> findByName(String dietId, String name){
        List<Meal> meals = dietRepository.findMealByName(dietId, name);

        if(meals.isEmpty())
            throw new NotFoundException("Meals with name " + name + " not found");

        return meals;
    }

    public <T extends FirestoreEntity> List<T> findByName(DietServiceType type, String name){
        List<T> entityList = new ArrayList<>();

        switch(type){
            case DIET : entityList = (List<T>) dietRepository.findDietByName(name); break;
            case PRODUCT: entityList = (List<T>) productRepository.findProductByName(name); break;
        }

        if(entityList.isEmpty())
            throw new NotFoundException("Object with name " + name + " not found");

        return entityList;
    }

    public List<Diet> findByTemplate(){
        List<Diet> diets = dietRepository.findDietByTemplate(true);

        if(diets.isEmpty())
            throw new NotFoundException("No template diets were found");

        return diets;
    }

    public List<Diet> findByTrainer(String id){
        List<Diet> diets = dietRepository.findDietByTrainer(id);

        if(diets.isEmpty())
            throw new NotFoundException("No diets for trainerId " + id + " were found");

        return diets;
    }
}
