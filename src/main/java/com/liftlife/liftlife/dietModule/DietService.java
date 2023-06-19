package com.liftlife.liftlife.dietModule;


import com.liftlife.liftlife.dietModule.dietDay.DietDay;
import com.liftlife.liftlife.dietModule.dietDay.DietDayRepository;
import com.liftlife.liftlife.dietModule.dietDay.FullDietDay;
import com.liftlife.liftlife.dietModule.dietDay.FullDietDayRepository;
import com.liftlife.liftlife.dietModule.dietDay.meal.Meal;
import com.liftlife.liftlife.dietModule.dietPlan.DietPlan;
import com.liftlife.liftlife.dietModule.dietPlan.DietPlanRepository;
import com.liftlife.liftlife.dietModule.dietPlan.FullDietPlan;
import com.liftlife.liftlife.dietModule.dietPlan.FullDietPlanRepository;
import com.liftlife.liftlife.dietModule.product.ProductRepository;
import com.liftlife.liftlife.util.database.DietEntity;
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
    private FullDietDayRepository fullDietDayRepository;
    private FullDietPlanRepository fullDietPlanRepository;

    @Autowired
    public DietService(ProductRepository productRepository, DietDayRepository dietDayRepository,
                       DietPlanRepository dietPlanRepository,
                       FullDietPlanRepository fullDietPlanRepository, FullDietDayRepository fullDietDayRepository) {
        this.productRepository = productRepository;
        this.dietDayRepository = dietDayRepository;
        this.dietPlanRepository = dietPlanRepository;
        this.fullDietDayRepository = fullDietDayRepository;
        this.fullDietPlanRepository = fullDietPlanRepository;
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


    public <T extends DietEntity> T findById(String id, DietServiceType type){
        T entity = null;

        switch(type){
            case DIET_DAY : entity = (T) dietDayRepository.findById(id); break;
            //case PRODUCT: entity = (T) productRepository.findById(id); break;
            case DIET_PLAN: entity = (T) dietPlanRepository.findById(id); break;
        }

        //System.out.println(type);

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


    public <T extends DietEntity> List<T> findByFields(DietServiceType type, Map<String, Object> fields){
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

    public <T extends DietEntity> List<T> findByTrainer(DietServiceType type, String id){
        List<T> entityList = new ArrayList<>();

        switch(type){
            case DIET_DAY : entityList = (List<T>) dietDayRepository.findDietByTrainer(id);; break;
            //case PRODUCT: entityList = (List<T>) productRepository.findByFields(fields); break;
            case DIET_PLAN: entityList = (List<T>) dietPlanRepository.findDietByTrainer(id); break;
        }

        if(entityList.isEmpty())
            throw new NotFoundException("No objects found");

        return entityList;
    }

    public List<DietDay> findDayByTrainer(String id){
        List<DietDay> diets = dietDayRepository.findDietByTrainer(id);

        if(diets.isEmpty())
            throw new NotFoundException("No diets for trainerId " + id + " were found");

        return diets;
    }

    public List<DietPlan> findPlanByTrainer(String id){
        List<DietPlan> diets = dietPlanRepository.findDietByTrainer(id);

        if(diets.isEmpty())
            throw new NotFoundException("No diets for trainerId " + id + " were found");

        return diets;
    }

    public DietPlan findDietPlan(String id){
        DietPlan dietPlan = dietPlanRepository.findById(id);
        List<DietDay> dietDays = new ArrayList<>();

        for(String dietId: dietPlan.getDietDays()){
            if(dietDayRepository.findById(dietId) != null)
                dietDays.add(dietDayRepository.findById(dietId));
        }

        return dietPlan;
    }

    public <T extends DietEntity> ResponseEntity<String> update(T object) {

        if(object == null)
            return ResponseEntity.badRequest().body("Object is null");

        switch(object.getClass().getSimpleName()){
            case "DietDay" : return ResponseEntity.ok().body("DietDay updated "
                    + dietDayRepository.update((DietDay) object).getUpdateTime());
            /*
            case "Product" : return ResponseEntity.ok().body("Product updated with ID "
                    + productRepository.update((Product) object));

             */
            case "DietPlan" : return ResponseEntity.ok().body("DietPlan updated "
                    + dietPlanRepository.update((DietPlan) object).getUpdateTime());
            default: return ResponseEntity.badRequest().body("Class cannot be recognized");
        }
    }

    public ResponseEntity<String> update(String dietId, Meal meal){
        if(meal == null)
            return ResponseEntity.badRequest().body("Object is null");

        return ResponseEntity.ok().body("meal updated " + dietDayRepository.updateMeal(dietId, meal).getUpdateTime());
    }

    public <T extends DietEntity> ResponseEntity<String> delete(T object){
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

    public <T extends DietEntity> ResponseEntity<String> delete(String objectId, DietServiceType type){

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
        return ResponseEntity.ok().body("Meal deleted with ID "
                + meal.getDocumentId());
    }

    public ResponseEntity<String> delete(String dietId, String mealId){

        dietDayRepository.deleteMeal(dietId, mealId);
        return ResponseEntity.ok().body("Meal deleted with ID "
                + mealId);
    }

    public ResponseEntity<String> createMeal(String dietId, Meal meal){
        return ResponseEntity.ok().body(dietDayRepository.insertMeal(dietId, meal));
    }

    public <T extends DietEntity> ResponseEntity<String> createTemplate(T object){
        if(object == null)
            return ResponseEntity.badRequest().body("Object is null");

        switch(object.getClass().getSimpleName()){
            case "DietDay" : return ResponseEntity.ok().body("Created DietDay template with ID " + dietDayRepository.insert((DietDay) object));
            case "DietPlan" : return ResponseEntity.ok().body("Created DietPlan template with ID " + dietPlanRepository.insert((DietPlan) object));
            default: return ResponseEntity.badRequest().body("Class cannot be recognized");
        }
    }

    public ResponseEntity<String> createForUser(DietPlan dietPlan){
        if(dietPlan == null)
            return ResponseEntity.badRequest().body("Object is null");

        StringBuilder response = new StringBuilder();
        String dietPlanId = dietPlanRepository.insert(dietPlan);
        response.append("Created DietPlan with ID ").append(dietPlanId);
        response.append(" ");

        return ResponseEntity.ok().body(response.toString());
    }

    //TODO implement function
    public ResponseEntity<String> addPlanToUser(DietPlan dietPlan, Long userId){
        return null;
    }

    DietDay convertDietDay(FullDietDay day){
        DietDay dietDay =  new DietDay(day.getName(),day.getDayOfWeek(),day.getCaloriesSum(),day.isTemplate(),day.getTrainerId(), day.getDocumentId());
        dietDay.setDocumentId(dietDay.getDocumentId());
        return dietDay;
    }

    DietPlan convertDietPlan(FullDietPlan fullPlan){
        DietPlan plan = new DietPlan(fullPlan.getName(),fullPlan.getUserId(),fullPlan.getTrainerId(),new ArrayList<>());
        List<String> ids = new ArrayList<>();
        for(FullDietDay day: fullPlan.getDietDays()){
            ids.add(day.getDocumentId());
        }
        plan.setDietDays(ids);
        plan.setDocumentId(fullPlan.getDocumentId());
        return plan;
    }
    //TODO handling no object with this id
    <T extends DietEntity, Q extends DietEntity> T returnFull(Q object){

        if(object == null)
            return null;

        switch(object.getClass().getSimpleName()){
            case "DietDay" : FullDietDay fullDay = new FullDietDay(((DietDay) object).getName(),((DietDay) object).getDayOfWeek(),((DietDay) object).getCaloriesSum(),
                        ((DietDay) object).isTemplate(), ((DietDay) object).getTrainerId(), new ArrayList<>());
                fullDay.setMeals(dietDayRepository.findMeals(object.getDocumentId()));
                fullDay.setDocumentId(object.getDocumentId());
                return (T) fullDay;
            //case PRODUCT: entity = (T) productRepository.findById(id); break;
            case "DietPlan": FullDietPlan fullPlan = new FullDietPlan(((DietPlan) object).getName(), ((DietPlan) object).getUserId(),
                        ((DietPlan) object).getTrainerId(),new ArrayList<FullDietDay>());
                List<FullDietDay> days = new ArrayList<>();
                //System.out.println(object.toString());
                for(String day: ((DietPlan) object).getDietDays()){
                    //System.out.println(day);
                    DietDay dietDay = dietDayRepository.findById(day);
                    FullDietDay fullDietDay = new FullDietDay(((DietDay) object).getName(),dietDay.getDayOfWeek(),dietDay.getCaloriesSum(),
                            dietDay.isTemplate(),dietDay.getTrainerId(),new ArrayList<>());
                    List<Meal> meals = dietDayRepository.findMeals(dietDay.getDocumentId());
                    fullDietDay.setMeals(meals);
                    fullDietDay.setDocumentId(dietDay.getDocumentId());
                    //System.out.println("saved");
                    days.add(fullDietDay);
                }
                //System.out.println("ssss");
                fullPlan.setDietDays(days);
                fullPlan.setDocumentId(object.getDocumentId());
                return (T) fullPlan;
        }

        return null;
    }


    public <T extends DietEntity> T findFullById(String id, DietServiceType type) {
        T entity = null;

        switch(type){
            case DIET_DAY : entity = (T) dietDayRepository.findById(id);
                //System.out.println(entity);
                return returnFull(entity);
            //case PRODUCT: entity = (T) productRepository.findById(id); break;
            case DIET_PLAN: entity = (T) dietPlanRepository.findById(id);
                //System.out.println("plan " + entity.getDocumentId());
                return returnFull(entity);
        }

        //System.out.println(type);

        if(entity == null)
            throw new NotFoundException("Object with ID " + id + " not found");

        return entity;
    }

    public <T extends DietEntity> List<T> findFullByFields(DietServiceType type, Map<String, Object> fields) {
        List<T> entityList = new ArrayList<>();

        switch(type){
            case DIET_DAY : entityList = (List<T>) dietDayRepository.findByFields(fields);
            List<T> fullDietDays = new ArrayList<>();
            for(T day: entityList){
                fullDietDays.add(returnFull(day));
            }
            return fullDietDays;
            //case PRODUCT: entityList = (List<T>) productRepository.findByFields(fields); break;
            case DIET_PLAN: entityList = (List<T>) dietPlanRepository.findByFields(fields);
            List<T> fullDietPlans = new ArrayList<>();
            for(T day: entityList){
                fullDietPlans.add(returnFull(day));
            }
            return fullDietPlans;
        }

        if(entityList.isEmpty())
            throw new NotFoundException("No objects found");

        return entityList;
    }

    public List<FullDietDay> findFullByTemplate() {
        List<DietDay> diets = dietDayRepository.findDietByTemplate(true);

        if(diets.isEmpty())
            throw new NotFoundException("No template diets were found");
        List<FullDietDay> fullDietDays = new ArrayList<>();
        for(DietDay day: diets){
            fullDietDays.add(returnFull(day));
        }

        return fullDietDays;
    }

    public <T extends DietEntity> List<T> findFullByTrainer(DietServiceType type, String id) {

        List<T> entityList;

        switch(type){
            case DIET_DAY : entityList = (List<T>) dietDayRepository.findDietByTrainer(id);
                List<T> fullDietDays = new ArrayList<>();
                for(T day: entityList){
                    fullDietDays.add(returnFull(day));
                }
                return fullDietDays;
            //case PRODUCT: entityList = (List<T>) productRepository.findByFields(fields); break;
            case DIET_PLAN: entityList = (List<T>) dietPlanRepository.findDietByTrainer(id);
            List<T> fullDietPlans = new ArrayList<>();
                for(T day: entityList){
                    fullDietPlans.add(returnFull(day));
                }
                return fullDietPlans;
        }


        throw new NotFoundException("No objects found");
    }

    public <T extends DietEntity> ResponseEntity<String> updateFull(T object) {

        if(object == null)
            return ResponseEntity.badRequest().body("Object is null");

        switch(object.getClass().getSimpleName()){
            case "FullDietDay" :
                dietDayRepository.update(convertDietDay((FullDietDay) object));
                for(Meal meal: ((FullDietDay) object).getMeals()){
                    dietDayRepository.updateMeal(object.getDocumentId(), meal);
                }
                return ResponseEntity.ok().body("DietDay fully updated");
            /*
            case "Product" : return ResponseEntity.ok().body("Product updated with ID "
                    + productRepository.update((Product) object));

             */
            case "FullDietPlan" :
                //System.out.println("1");
                dietPlanRepository.update(convertDietPlan((FullDietPlan) object));
                //System.out.println("2");
                for(FullDietDay day: ((FullDietPlan) object).getDietDays()){
                    //System.out.println("3");
                    dietDayRepository.update(convertDietDay(day));
                    //System.out.println("4");
                    for(Meal meal: day.getMeals()){
                        //System.out.println("5");
                        dietDayRepository.updateMeal(day.getDocumentId(), meal);
                        //System.out.println("6");
                    }
                }
                //System.out.println("7");
                return ResponseEntity.ok().body("DietPlan updated");
            default: return ResponseEntity.badRequest().body("Class cannot be recognized");
        }
    }

    public ResponseEntity<String> deleteFull(String objectId, DietServiceType type) {
        switch(type){
            case DIET_DAY : dietDayRepository.delete(objectId);
                return ResponseEntity.ok().body("DietDay deleted with ID "
                        + objectId);
                /*
            case PRODUCT : productRepository.delete(objectId);
                return ResponseEntity.ok().body("Product deleted with ID "
                        + objectId);

                 */
            case DIET_PLAN :
                FullDietPlan fullDietPlan = returnFull(dietPlanRepository.findById(objectId));
                for(FullDietDay day: fullDietPlan.getDietDays()){
                    dietDayRepository.delete(day.getDocumentId());
                }
                dietPlanRepository.delete(objectId);
                return ResponseEntity.ok().body("DietPlan fully deleted with ID "
                        + objectId);
            default: return ResponseEntity.badRequest().body("Class cannot be recognized");
        }
    }

    public <T extends DietEntity> ResponseEntity<String> createFullTemplate(T object) {
        if(object == null)
            return ResponseEntity.badRequest().body("Object is null");

        switch(object.getClass().getSimpleName()){
            case "FullDietDay" : DietDay day = new DietDay(((FullDietDay) object).getName(), ((FullDietDay) object).getDayOfWeek(),((FullDietDay) object).getCaloriesSum(),
                    ((FullDietDay) object).isTemplate(), ((FullDietDay) object).getTrainerId(), object.getDocumentId());
                    String id = dietDayRepository.insert(day);
                    for(Meal meal: ((FullDietDay) object).getMeals()){
                        dietDayRepository.insertMeal(id,meal);
                    }
                return ResponseEntity.ok().body("Created FullDietDay template");
            case "FullDietPlan" :
                List<String> ids = new ArrayList<>();
                DietPlan plan = new DietPlan(((FullDietPlan) object).getName(), ((FullDietPlan) object).getUserId(),
                        ((FullDietPlan) object).getTrainerId(),ids);
                for(FullDietDay dietDay: ((FullDietPlan) object).getDietDays()){
                    ids.add(dietDay.getDocumentId());
                    id = dietDayRepository.insert(convertDietDay(dietDay));
                    List<Meal> meals = dietDay.getMeals();
                    for(Meal meal: meals){
                        dietDayRepository.insertMeal(id,meal);
                    }
                }
                plan.setDietDays(ids);
                dietPlanRepository.insert(plan);
                return ResponseEntity.ok().body("Created DietPlan template with ID");
            default: return ResponseEntity.badRequest().body("Class cannot be recognized");
        }
    }

    public ResponseEntity<String> createFullForUser(FullDietPlan dietPlan) {
        if(dietPlan == null)
            return ResponseEntity.badRequest().body("Object is null");

        StringBuilder response = new StringBuilder();
        List<String> ids = new ArrayList<>();
        DietPlan plan = new DietPlan(dietPlan.getName(), dietPlan.getUserId(),
                dietPlan.getTrainerId(),ids);
        for(FullDietDay dietDay: dietPlan.getDietDays()){
            ids.add(dietDay.getDocumentId());
            String id = dietDayRepository.insert(convertDietDay(dietDay));
            List<Meal> meals = dietDay.getMeals();
            for(Meal meal: meals){
                dietDayRepository.insertMeal(id,meal);
            }
        }
        plan.setDietDays(ids);
        String dietPlanId =  dietPlanRepository.insert(plan);
        response.append("Created DietPlan with ID ").append(dietPlanId);
        response.append(" ");

        return ResponseEntity.ok().body(response.toString());
    }
}
