package com.liftlife.liftlife.training;

import com.liftlife.liftlife.database.FirestoreEntity;
import com.liftlife.liftlife.exercise.Exercise;
import com.liftlife.liftlife.exercise.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/training")
public class TrainingController{
    private final TrainingRepository repository;

    @Autowired
    public TrainingController(TrainingRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/saveExample")
    public String saveExampleString(@RequestBody Training newTraining) throws ExecutionException, InterruptedException {
        return repository.insertTraining(newTraining);
    }

    @GetMapping("/findExample")
    public Training getExample(@RequestParam("id") String documentId) throws ExecutionException, InterruptedException {
        return repository.findTrainingById(documentId);
    }

    @GetMapping("/findTrainer")
    public List<Training> getByTrainer(@RequestParam("trainer") String trainerId) throws ExecutionException, InterruptedException {
        return repository.findTrainingByTrainer(trainerId);
    }

    @GetMapping("/findTemplate")
    public List<Training> getByTemplate(@RequestParam("template") boolean template) throws ExecutionException, InterruptedException {
        return repository.findTrainingByTemplate(template);
    }

}
