package com.liftlife.liftlife.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class ExerciseController {
    private final ExerciseRepository repository;

    @Autowired
    public ExerciseController(ExerciseRepository repository) {
        this.repository = repository;
    }

    @PutMapping("/saveExample")
    public String saveExampleString(@RequestBody Exercise newExercise) throws ExecutionException, InterruptedException {
        Exercise exercise = new Exercise("chest", "test", 10, "chest-exercise", false);
        exercise.setDocumentId("i shouldn't be seen");
        return repository.insertExercise(exercise);
    }

    @GetMapping("/findExample")
    public Exercise getExample(@PathVariable String documentId) throws ExecutionException, InterruptedException {
        return repository.findExerciseByDocumentId("2W2m1IR0boK6QYQ882sU");
    }
}
