package com.liftlife.liftlife.diet;

import com.liftlife.liftlife.diet.meal.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diet")
public class DietController {

    private final DietRepository dietRepository;

    @Autowired
    public DietController(DietRepository repository) {
        this.dietRepository = repository;
    }

    @GetMapping("/find")
    public Diet getById(@RequestParam("id") String dietId) {
        return dietRepository.findById(dietId);
    }

    @GetMapping("/meal/find")
    public Meal getById(@RequestParam("dietId") String dietId, @RequestParam("mealId") String mealId) {
        return dietRepository.findMealById(dietId, mealId);
    }
}
