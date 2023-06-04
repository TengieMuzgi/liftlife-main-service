package com.liftlife.liftlife.dietModule;

import com.liftlife.liftlife.dietModule.dietDay.DietDay;
import com.liftlife.liftlife.dietModule.dietDay.FullDietDay;
import com.liftlife.liftlife.dietModule.dietDay.meal.Meal;
import com.liftlife.liftlife.dietModule.dietPlan.DietPlan;
import com.liftlife.liftlife.dietModule.dietPlan.FullDietPlan;
import com.liftlife.liftlife.util.database.DietEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dietDay")
public class DietController {

    private final DietService dietService;

    @Autowired
    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    @GetMapping("/meals/{dietId}/{mealId}")
    public ResponseEntity<Meal> getMealById(@PathVariable String dietId, @PathVariable String mealId) {
        Meal meal = dietService.findById(dietId, mealId);
        return ResponseEntity.ok(meal);
    }

    @GetMapping("/{type}/{id}")
    public <T extends DietEntity> ResponseEntity<T> getById(@PathVariable String type, @PathVariable String id) {
        DietServiceType serviceType = DietServiceType.valueOf(type.toUpperCase());
        T entity = dietService.findById(id, serviceType);
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/full/{type}/{id}")
    public <T extends DietEntity> ResponseEntity<T> getFullById(@PathVariable String type, @PathVariable String id) {
        DietServiceType serviceType = DietServiceType.valueOf(type.toUpperCase());
        T entity = dietService.findFullById(id, serviceType);
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/meals/name/{dietId}")
    public ResponseEntity<List<Meal>> getMealsByName(@PathVariable String dietId, @RequestParam String name) {
        List<Meal> meals = dietService.findByName(dietId, name);
        return ResponseEntity.ok(meals);
    }

    @GetMapping("/meals/{dietId}")
    public ResponseEntity<List<Meal>> getMealsByFields(@PathVariable String dietId, @RequestBody Map<String, Object> fields) {
        List<Meal> meals = dietService.findByFields(dietId, fields);
        return ResponseEntity.ok(meals);
    }

    @GetMapping("/{type}")
    public <T extends DietEntity> ResponseEntity<List<T>> getByFields(@PathVariable String type, @RequestBody Map<String, Object> fields) {
        DietServiceType serviceType = DietServiceType.valueOf(type.toUpperCase());
        List<T> entities = dietService.findByFields(serviceType, fields);
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/full/{type}")
    public <T extends DietEntity> ResponseEntity<List<T>> getFullByFields(@PathVariable String type, @RequestBody Map<String, Object> fields) {
        DietServiceType serviceType = DietServiceType.valueOf(type.toUpperCase());
        List<T> entities = dietService.findFullByFields(serviceType, fields);
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/template")
    public ResponseEntity<List<DietDay>> getTemplateDiets() {
        List<DietDay> diets = dietService.findByTemplate();
        return ResponseEntity.ok(diets);
    }

    @GetMapping("/full/template")
    public ResponseEntity<List<FullDietDay>> getFullTemplateDiets() {
        List<FullDietDay> diets = dietService.findFullByTemplate();
        return ResponseEntity.ok(diets);
    }

    @GetMapping("/trainer/{type}/{id}")
    public <T extends DietEntity> ResponseEntity<List<T>> getByTrainerId(@PathVariable String type, @PathVariable String id) {
        DietServiceType serviceType = DietServiceType.valueOf(type.toUpperCase());
        List<T> diets = dietService.findByTrainer(serviceType, id);
        return ResponseEntity.ok(diets);
    }

    @GetMapping("/full/trainer/{type}/{id}")
    public <T extends DietEntity> ResponseEntity<List<T>> getFullByTrainerId(@PathVariable String type, @PathVariable String id) {
        DietServiceType serviceType = DietServiceType.valueOf(type.toUpperCase());
        List<T> diets = dietService.findFullByTrainer(serviceType, id);
        return ResponseEntity.ok(diets);
    }

    @GetMapping("/trainer/day/{id}")
    public ResponseEntity<List<DietDay>> getDietsByTrainerId(@PathVariable String id) {
        List<DietDay> diets = dietService.findDayByTrainer(id);
        return ResponseEntity.ok(diets);
    }

    @GetMapping("/trainer/plan/{id}")
    public ResponseEntity<List<DietPlan>> getDietsPlanByTrainerId(@PathVariable String id) {
        List<DietPlan> diets = dietService.findPlanByTrainer(id);
        return ResponseEntity.ok(diets);
    }

    @GetMapping("/plan/{id}")
    public ResponseEntity<DietPlan> getDietsPlanById(@PathVariable String id) {
        DietPlan dietPlan = dietService.findDietPlan(id);
        return ResponseEntity.ok(dietPlan);
    }

    //TODO get full dietPlan and DietDay;;; ?add trainer id in plan?

    @PutMapping("/update")
    public <T extends DietEntity> ResponseEntity<String> update(@RequestBody T entity) {
        return dietService.update(entity);
    }

    @PutMapping("/full/update")
    public <T extends DietEntity> ResponseEntity<String> updateFull(@RequestBody T entity) {
        return dietService.updateFull(entity);
    }

    @PutMapping("/{dietId}/update")
    public ResponseEntity<String> updateMeal(@PathVariable String dietId, @RequestBody Meal meal) {
        return dietService.update(dietId, meal);
    }

    @DeleteMapping("/delete/{type}/{id}")
    public ResponseEntity<String> deleteEntityById(@PathVariable String type, @PathVariable String id) {
        DietServiceType serviceType = DietServiceType.valueOf(type.toUpperCase());
        return dietService.delete(id, serviceType);
    }

    @DeleteMapping("/full/delete/{type}/{id}")
    public ResponseEntity<String> deleteFullEntityById(@PathVariable String type, @PathVariable String id) {
        DietServiceType serviceType = DietServiceType.valueOf(type.toUpperCase());
        return dietService.deleteFull(id, serviceType);
    }

    @DeleteMapping("/delete/{dietId}/meals/{mealId}")
    public ResponseEntity<String> deleteMeal(@PathVariable String dietId, @PathVariable String mealId) {
        ResponseEntity<String> response = dietService.delete(dietId, mealId);
        return response;
    }

    @PostMapping("/{dietId}/createMeal")
    public ResponseEntity<String> createMeal(@PathVariable String dietId, @RequestBody Meal meal) {
        return dietService.createMeal(dietId, meal);
    }

    @PostMapping("/create")
    public <T extends DietEntity> ResponseEntity<String> createTemplate( @RequestBody T template) {
        //System.out.println(template.toString());
        return dietService.createTemplate(template);
    }

    @PostMapping("/full/create")
    public <T extends DietEntity> ResponseEntity<String> createFullTemplate( @RequestBody T template) {
        //System.out.println(template.toString());
        return dietService.createFullTemplate(template);
    }

    /*
    @PostMapping("/create/{userId}")
    public ResponseEntity<String> createDietPlanForUser(@RequestBody DietPlan dietPlan) {
        return dietService.createForUser(dietPlan);
    }

     */

    /*
    @PostMapping("/full/create/{userId}")
    public ResponseEntity<String> createFullDietPlanForUser(@RequestBody FullDietPlan dietPlan, @PathVariable String userId) {
        return dietService.createFullForUser(dietPlan, userId);
    }

     */
}
