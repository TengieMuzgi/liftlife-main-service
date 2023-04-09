package com.liftlife.liftlife.diet;

import com.liftlife.liftlife.training.TrainingRepository;
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

    private final DietRepository repository;

    @Autowired
    public DietController(DietRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getById")
    public Diet getById(@RequestParam("id") String dietId) throws ExecutionException, InterruptedException {
        return repository.findDietById(dietId);
    }

    @GetMapping("/meal/getByName")
    public List<Meal> getByName(@RequestParam("dietId") String dietId, @RequestParam("name") String name) throws ExecutionException, InterruptedException {
        return repository.findMealByName(dietId, name);
    }
}
