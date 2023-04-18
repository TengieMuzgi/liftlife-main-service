package com.liftlife.liftlife.diet;

import com.liftlife.liftlife.meal.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/diet")
public class DietController {

    private final DietRepository dietRepository;

    @Autowired
    public DietController(DietRepository repository) {
        this.dietRepository = repository;
    }

    @GetMapping("/getById")
    public Diet getById(@RequestParam("id") String dietId) {
        return dietRepository.findById(dietId);
    }

    @GetMapping("/meal/getByName")
    public List<Meal> getByName(@RequestParam("dietId") String dietId, @RequestParam("name") String name) {
        return dietRepository.findMealByName(dietId, name);
    }
}
