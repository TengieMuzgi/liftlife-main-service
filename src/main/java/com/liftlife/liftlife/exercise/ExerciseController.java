package com.liftlife.liftlife.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class ExerciseController {
    private final ExerciseRepository repository;

    @Autowired
    public ExerciseController(ExerciseRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/saveExample")
    public void saveExampleString() throws ExecutionException, InterruptedException {
        Exercise exercise = new Exercise("chest", "test", 10, "chest-exercise", false);
        repository.insertExercise(exercise);
    }

    @GetMapping("/findExample")
    public Exercise getExample() throws ExecutionException, InterruptedException {
        return repository.findExerciseByDocumentId("2W2m1IR0boK6QYQ882sU");
    }
}
