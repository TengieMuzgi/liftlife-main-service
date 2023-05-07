package com.liftlife.liftlife.trainingModule.trainingSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/trainings/TemplateTrainingSession/")
public class TemplateTrainingSessionController {
    private TemplateTrainingSessionService templateTrainingSessionService;

    //TODO - handle ExecutionException, InterruptedException in repo template

    @Autowired
    public TemplateTrainingSessionController(TemplateTrainingSessionService templateTrainingSessionService) {
        this.templateTrainingSessionService = templateTrainingSessionService;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody TrainingSession trainingSession) {
        return templateTrainingSessionService.insert(trainingSession);
    }

    @GetMapping("/find")
    public ResponseEntity<TrainingSession> findById(@RequestParam("id") String id) {
        return ResponseEntity.ok(templateTrainingSessionService.findByID(id));
    }

    @GetMapping("/findByTrainer")
    public ResponseEntity<List<TrainingSession>> findByTrainer(@RequestParam("trainer") String trainerId) {
        return ResponseEntity.ok(templateTrainingSessionService.findByTrainer(trainerId));
    }

    @GetMapping("/findByTemplate")
    public ResponseEntity<List<TrainingSession>> findByTemplate(@RequestParam("template") boolean template) {
        return ResponseEntity.ok(templateTrainingSessionService.findByTemplate(template));
    }

    @GetMapping("/findDate")
    public ResponseEntity<List<TrainingSession>> findByDate(@RequestParam("date") String date) {
        return ResponseEntity.ok(templateTrainingSessionService.findByDate(date));
    }

    @GetMapping("")
    public ResponseEntity<List<TrainingSession>> findAllAvailable(@RequestParam String coachId) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(templateTrainingSessionService.findAllAvailable(coachId));
    }

    @DeleteMapping
    public void delete(String documentId) {
        templateTrainingSessionService.delete(documentId);
    }

}
