package com.liftlife.liftlife.controller;

import com.liftlife.liftlife.database.ExerciseRepository;
import com.liftlife.liftlife.entity.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class Controller {
    private final ExerciseRepository repository;

    @Autowired
    public Controller(ExerciseRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/saveExample")
    public void saveExampleString() throws ExecutionException, InterruptedException {
        Exercise exercise = new Exercise("chest", "test", 10, "chest-exercise", false);
        repository.insertExercise(exercise);
    }
}
