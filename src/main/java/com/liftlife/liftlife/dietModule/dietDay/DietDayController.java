package com.liftlife.liftlife.dietModule.dietDay;

import com.liftlife.liftlife.dietModule.dietDay.meal.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dietDay")
public class DietDayController {

    private final DietDayRepository dietDayRepository;

    @Autowired
    public DietDayController(DietDayRepository repository) {
        this.dietDayRepository = repository;
    }

    @GetMapping("/find")
    public DietDay getById(@RequestParam("id") String dietId) {
        return dietDayRepository.findById(dietId);
    }

    @GetMapping("/meal/find")
    public Meal getById(@RequestParam("dietId") String dietId, @RequestParam("mealId") String mealId) {
        return dietDayRepository.findMealById(dietId, mealId);
    }
}
