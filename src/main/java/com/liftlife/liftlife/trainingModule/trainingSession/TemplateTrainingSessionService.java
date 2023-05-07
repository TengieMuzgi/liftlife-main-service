package com.liftlife.liftlife.trainingModule.trainingSession;

import com.liftlife.liftlife.trainingModule.exercise.TemplateExerciseRepository;
import com.liftlife.liftlife.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TemplateTrainingSessionService {

    private final TemplateTrainingSessionRepository templateTrainingSessionRepository;

    private final TemplateExerciseRepository templateExerciseRepository;

    @Autowired
    public TemplateTrainingSessionService(TemplateTrainingSessionRepository templateTrainingSessionRepository,
                                          TemplateExerciseRepository templateExerciseRepository) {
        this.templateTrainingSessionRepository = templateTrainingSessionRepository;
        this.templateExerciseRepository = templateExerciseRepository;
    }

    public ResponseEntity<String> insert(TrainingSession trainingSession) {
        return ResponseEntity.ok().body("Training inserted with ID "
                + templateTrainingSessionRepository.insert(trainingSession));
    }

    public TrainingSession findByID(String id) {
        TrainingSession trainingSession = templateTrainingSessionRepository.findById(id);

        if(trainingSession == null)
            throw new NotFoundException("Training with ID " + id + " not found");

        return trainingSession;
    }

    public List<TrainingSession> findByTrainer(String trainerId) {
        List<TrainingSession> trainingSessionList = templateTrainingSessionRepository.findByTrainer(trainerId);

        if(trainingSessionList.isEmpty())
            throw new NotFoundException("Trainings for trainer with ID " + trainerId + " not found");

        return trainingSessionList;
    }

    public List<TrainingSession> findByTemplate(boolean template) {
        List<TrainingSession> trainingSessionList = templateTrainingSessionRepository.findByTemplate(template);

        if(trainingSessionList.isEmpty())
            throw new NotFoundException("Trainings templates not found");

        return trainingSessionList;
    }

    public List<TrainingSession> findByDate(String date) {
        List<TrainingSession> trainingSessionList = templateTrainingSessionRepository.findByDate(date);

        if(trainingSessionList.isEmpty())
            throw new NotFoundException("Trainings by date not found");

        return trainingSessionList;
    }


    public List<TrainingSession> findAllAvailable(String coachId) throws ExecutionException, InterruptedException {
        return templateTrainingSessionRepository.findAll();
    }

    public void delete(String documentId) {
        templateTrainingSessionRepository.delete(documentId);
    }
}
