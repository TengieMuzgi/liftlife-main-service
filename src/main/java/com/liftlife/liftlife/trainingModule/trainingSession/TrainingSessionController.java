package com.liftlife.liftlife.trainingModule.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training")
public class TrainingController{
    private TrainingService trainingService;

    //TODO - handle ExecutionException, InterruptedException in repo template

    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody TrainingSession trainingSession) {
        return trainingService.insert(trainingSession);
    }

    @GetMapping("/find")
    public ResponseEntity<TrainingSession> findById(@RequestParam("id") String id) {
        return ResponseEntity.ok(trainingService.findByID(id));
    }

    @GetMapping("/findByTrainer")
    public ResponseEntity<List<TrainingSession>> findByTrainer(@RequestParam("trainer") String trainerId) {
        return ResponseEntity.ok(trainingService.findByTrainer(trainerId));
    }

    @GetMapping("/findByTemplate")
    public ResponseEntity<List<TrainingSession>> findByTemplate(@RequestParam("template") boolean template) {
        return ResponseEntity.ok(trainingService.findByTemplate(template));
    }

    @GetMapping("/findDate")
    public ResponseEntity<List<TrainingSession>> findByDate(@RequestParam("date") String date) {
        return ResponseEntity.ok(trainingService.findByDate(date));
    }



}
