package com.liftlife.liftlife.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {
    private final ExerciseRepository repository;

    @Autowired
    public ExerciseController(ExerciseRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/insert")
    public String saveExampleString(@RequestBody Exercise newExercise) {
        return repository.insert(newExercise);
    }

    @GetMapping("/find")
    public Exercise getExample(@RequestParam("id") String documentId) {
        return repository.findById(documentId);
    }

    @GetMapping("/findBodyPart")
    public List<Exercise> getByBodyPart(@RequestParam("bodyPart") String bodyPart) {
        return repository.findExerciseByBodyPart(bodyPart);
    }
}
