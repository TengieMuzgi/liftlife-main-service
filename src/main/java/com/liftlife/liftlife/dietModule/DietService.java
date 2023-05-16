package com.liftlife.liftlife.dietModule;



import com.google.cloud.firestore.WriteResult;
import com.liftlife.liftlife.dietModule.dietDay.DietDay;
import com.liftlife.liftlife.dietModule.dietDay.DietDayRepository;
import com.liftlife.liftlife.dietModule.dietDay.meal.Meal;
import com.liftlife.liftlife.dietModule.dietPlan.DietPlan;
import com.liftlife.liftlife.dietModule.dietPlan.DietPlanRepository;
import com.liftlife.liftlife.dietModule.product.Product;
import com.liftlife.liftlife.dietModule.product.ProductRepository;
import com.liftlife.liftlife.trainingModule.trainingSession.TrainingSession;
import com.liftlife.liftlife.userModule.user.ReferenceType;
import com.liftlife.liftlife.userModule.user.UserRepository;
import com.liftlife.liftlife.util.database.FirestoreEntity;
import com.liftlife.liftlife.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
            //case PRODUCT: entity = (T) productRepository.findById(id); break;
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
            //case PRODUCT: entityList = (List<T>) productRepository.findByFields(fields); break;
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

    public <T extends FirestoreEntity> ResponseEntity<String> update(T object) {

        if(object == null)
            return ResponseEntity.badRequest().body("Object is null");

        switch(object.getClass().getSimpleName()){
            case "DietDay" : return ResponseEntity.ok().body("DietDay updated with ID "
                    + dietDayRepository.update((DietDay) object));
            /*
            case "Product" : return ResponseEntity.ok().body("Product updated with ID "
                    + productRepository.update((Product) object));

             */
            case "DietPlan" : return ResponseEntity.ok().body("DietPlan updated with ID "
                    + dietPlanRepository.update((DietPlan) object));
            default: return ResponseEntity.badRequest().body("Class cannot be recognized");
        }
    }

    public ResponseEntity<String> update(String dietId, Meal meal){
        if(meal == null)
            return ResponseEntity.badRequest().body("Object is null");

        return ResponseEntity.ok().body("meal updated with ID " + dietDayRepository.updateMeal(dietId, meal));
    }

    public <T extends FirestoreEntity> ResponseEntity<String> delete(T object){
        if(object == null)
            return ResponseEntity.badRequest().body("Object is null");

        switch(object.getClass().getSimpleName()){
            case "DietDay" : dietDayRepository.delete((DietDay) object);
                    return ResponseEntity.ok().body("DietDay deleted with ID "
                    + object.getDocumentId());
                    /*
            case "Product" : productRepository.delete((Product) object);
                    return ResponseEntity.ok().body("Product deleted with ID "
                    + object.getDocumentId());

                     */
            case "DietPlan" : dietPlanRepository.delete((DietPlan) object);
            return ResponseEntity.ok().body("DietPlan deleted with ID "
                    + object.getDocumentId());
            default: return ResponseEntity.badRequest().body("Class cannot be recognized");
        }
    }

    public <T extends FirestoreEntity> ResponseEntity<String> delete(String objectId, DietServiceType type){

        switch(type){
            case DIET_DAY : dietDayRepository.delete(objectId);
                return ResponseEntity.ok().body("DietDay deleted with ID "
                        + objectId);
                /*
            case PRODUCT : productRepository.delete(objectId);
                return ResponseEntity.ok().body("Product deleted with ID "
                        + objectId);

                 */
            case DIET_PLAN : dietPlanRepository.delete(objectId);
                return ResponseEntity.ok().body("DietPlan deleted with ID "
                        + objectId);
            default: return ResponseEntity.badRequest().body("Class cannot be recognized");
        }
    }

    public ResponseEntity<String> delete(String dietId, Meal meal){
        if(meal == null)
            return ResponseEntity.badRequest().body("Object is null");

        dietDayRepository.deleteMeal(dietId, meal);
        return ResponseEntity.ok().body("DietPlan deleted with ID "
                + meal.getDocumentId());
    }

    public ResponseEntity<String> delete(String dietId, String mealId){

        dietDayRepository.deleteMeal(dietId, mealId);
        return ResponseEntity.ok().body("DietPlan deleted with ID "
                + mealId);
    }

    public <T extends FirestoreEntity> ResponseEntity<String> createTemplate(T object){
        if(object == null)
            return ResponseEntity.badRequest().body("Object is null");

        switch(object.getClass().getSimpleName()){
            case "DietDay" : return ResponseEntity.ok().body(dietDayRepository.insert((DietDay) object));
            case "DietPlan" : return ResponseEntity.ok().body(dietPlanRepository.insert((DietPlan) object));
            default: return ResponseEntity.badRequest().body("Class cannot be recognized");
        }
    }

    public ResponseEntity<String> createForUser(DietPlan dietPlan, String userId){
        if(dietPlan == null)
            return ResponseEntity.badRequest().body("Object is null");

        StringBuilder response = new StringBuilder();
        String dietPlanId = dietPlanRepository.insert(dietPlan);
        response.append(dietPlanId);
        response.append(" ");
        UserRepository userRepository = new UserRepository();
        response.append(userRepository.addToUser(userId,new ArrayList<>(List.of(dietPlanId)), ReferenceType.DIET));

        return ResponseEntity.ok().body(response.toString());
    }

    //TODO implement function
    public ResponseEntity<String> addPlanToUser(DietPlan dietPlan, Long userId){
        return null;
    }


}
