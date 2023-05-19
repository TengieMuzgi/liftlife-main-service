package com.liftlife.liftlife.trainingModule.trainingSession;

import com.google.cloud.firestore.WriteResult;
import com.liftlife.liftlife.trainingModule.trainingSession.template.TemplateTrainingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainings/sessions")
public class TrainingSessionController {
    private final TemplateTrainingSessionService templateTrainingSessionService;
    private final TrainingSessionService trainingSessionService;

    //TODO - handle ExecutionException, InterruptedException in repo template

    @Autowired
    public TrainingSessionController(TemplateTrainingSessionService templateTrainingSessionService, TrainingSessionService trainingSessionService) {
        this.templateTrainingSessionService = templateTrainingSessionService;
        this.trainingSessionService = trainingSessionService;
    }

    @PostMapping("/insertTemplate")
    public ResponseEntity<String> insertTemplate(@RequestBody TrainingSession trainingSession) {
        return templateTrainingSessionService.insert(trainingSession);
    }

    @GetMapping("/findTemplate")
    public ResponseEntity<TrainingSession> findTemplateById(@RequestParam("id") String id) {
        return ResponseEntity.ok(templateTrainingSessionService.findByID(id));
    }

    @GetMapping("/findTemplates")
    public ResponseEntity<List<TrainingSession>> findTemplatesForCoach(
            @RequestParam String coachId) {
        return ResponseEntity.ok(templateTrainingSessionService.findAllByCoach(coachId));

    }

    @GetMapping("/findTemplateByTrainer")
    public ResponseEntity<List<TrainingSession>> findTemplateByTrainer(@RequestParam("trainer") String trainerId) {
        return ResponseEntity.ok(templateTrainingSessionService.findByTrainer(trainerId));
    }

    @GetMapping("/findTemplateByDate")
    public ResponseEntity<List<TrainingSession>> findTemplateByDate(@RequestParam("date") String date) {
        return ResponseEntity.ok(templateTrainingSessionService.findByDate(date));
    }

    @DeleteMapping
    public void delete(String documentId) {
        templateTrainingSessionService.delete(documentId);
    }


    //NON TEMPLATE:

    @PostMapping("/insertSession")
    public ResponseEntity<String> insertSession(
            @RequestBody TrainingSession trainingSession,
            @RequestParam String planId,
            @RequestParam String dayId) {
        return ResponseEntity.ok(trainingSessionService.insertSession(trainingSession, planId, dayId));
    }

    @GetMapping("/findSessionsForDay")
    public ResponseEntity<List<TrainingSession>> findSessionsForDay(
            @RequestParam String planId,
            @RequestParam String dayId) {
        return ResponseEntity.ok(trainingSessionService.findSessionsForDay(planId, dayId));
    }

    @PutMapping("/updateSession")
    public ResponseEntity<WriteResult> updateSession(
            @RequestBody TrainingSession trainingSession,
            @RequestParam String planId,
            @RequestParam String dayId) {
        return ResponseEntity.ok(trainingSessionService.updateSession(trainingSession, planId, dayId));
    }

    @DeleteMapping("/deleteSession")
    public ResponseEntity deleteSession(
            @RequestParam String sessionId,
            @RequestParam String planId,
            @RequestParam String dayId) {
        trainingSessionService.deleteSession(sessionId, planId, dayId);
        return ResponseEntity.ok().build();
    }
}
