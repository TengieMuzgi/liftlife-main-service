package com.liftlife.liftlife.trainingModule.trainingSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainingSession")
public class TrainingSessionController {
    private TrainingSessionService trainingSessionService;

    //TODO - handle ExecutionException, InterruptedException in repo template

    @Autowired
    public TrainingSessionController(TrainingSessionService trainingSessionService) {
        this.trainingSessionService = trainingSessionService;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody TrainingSession trainingSession) {
        return trainingSessionService.insert(trainingSession);
    }

    @GetMapping("/find")
    public ResponseEntity<TrainingSession> findById(@RequestParam("id") String id) {
        return ResponseEntity.ok(trainingSessionService.findByID(id));
    }

    @GetMapping("/findByTrainer")
    public ResponseEntity<List<TrainingSession>> findByTrainer(@RequestParam("trainer") String trainerId) {
        return ResponseEntity.ok(trainingSessionService.findByTrainer(trainerId));
    }

    @GetMapping("/findByTemplate")
    public ResponseEntity<List<TrainingSession>> findByTemplate(@RequestParam("template") boolean template) {
        return ResponseEntity.ok(trainingSessionService.findByTemplate(template));
    }

    @GetMapping("/findDate")
    public ResponseEntity<List<TrainingSession>> findByDate(@RequestParam("date") String date) {
        return ResponseEntity.ok(trainingSessionService.findByDate(date));
    }



}
