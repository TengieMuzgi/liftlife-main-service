package com.liftlife.liftlife.trainingModule.trainingDay;

import com.google.cloud.firestore.WriteResult;
import com.liftlife.liftlife.trainingModule.trainingSession.TrainingSession;
import com.liftlife.liftlife.trainingModule.trainingSession.TrainingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainings/days")
public class TrainingDayController {
    private final TrainingDayService trainingDayService;
    private final TrainingSessionService trainingSessionService;

    @Autowired
    public TrainingDayController(
            TrainingDayService trainingDayService,
            TrainingSessionService trainingSessionService) {
        this.trainingDayService = trainingDayService;
        this.trainingSessionService = trainingSessionService;
    }

    @GetMapping("/findForPlan")
    public ResponseEntity<List<TrainingDay>> findDaysForPlan(
            @RequestParam String planId) {
        List<TrainingDay> days = trainingDayService.findDaysForPlan(planId);
        for (TrainingDay day : days) {
            List<TrainingSession> sessions =
                    trainingSessionService.findSessionsForDay(day.getDocumentId(), planId);
            day.setTrainingSessions(sessions);
        }
        return ResponseEntity.ok(days);
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertDay(
            @RequestBody TrainingDay trainingDay,
            @RequestParam(name = "planId") String planId) {
        return ResponseEntity.ok(trainingDayService.insert(trainingDay, planId));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteDay(
            @RequestParam String dayId,
            @RequestParam String planId) {
        trainingDayService.delete(dayId, planId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<WriteResult> updateDay(
            @RequestBody TrainingDay trainingDay,
            @RequestParam String planId) {
        return ResponseEntity.ok(trainingDayService.updateDay(trainingDay, planId));
    }

}
