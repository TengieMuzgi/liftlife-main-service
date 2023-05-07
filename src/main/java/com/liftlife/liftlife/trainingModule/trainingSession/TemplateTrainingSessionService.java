package com.liftlife.liftlife.trainingModule.trainingSession;

import com.liftlife.liftlife.trainingModule.exercise.TemplateExerciseRepository;
import com.liftlife.liftlife.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingSessionService {

    private final TrainingSessionRepository trainingSessionRepository;

    private final TemplateExerciseRepository templateExerciseRepository;

    @Autowired
    public TrainingSessionService(TrainingSessionRepository trainingSessionRepository,
                                  TemplateExerciseRepository templateExerciseRepository) {
        this.trainingSessionRepository = trainingSessionRepository;
        this.templateExerciseRepository = templateExerciseRepository;
    }

    public ResponseEntity<String> insert(TrainingSession trainingSession) {
        return ResponseEntity.ok().body("Training inserted with ID "
                + trainingSessionRepository.insert(trainingSession));
    }

    public TrainingSession findByID(String id) {
        TrainingSession trainingSession = trainingSessionRepository.findById(id);

        if(trainingSession == null)
            throw new NotFoundException("Training with ID " + id + " not found");

        return trainingSession;
    }

    public List<TrainingSession> findByTrainer(String trainerId) {
        List<TrainingSession> trainingSessionList = trainingSessionRepository.findTrainingByTrainer(trainerId);

        if(trainingSessionList.isEmpty())
            throw new NotFoundException("Trainings for trainer with ID " + trainerId + " not found");

        return trainingSessionList;
    }

    public List<TrainingSession> findByTemplate(boolean template) {
        List<TrainingSession> trainingSessionList = trainingSessionRepository.findTrainingByTemplate(template);

        if(trainingSessionList.isEmpty())
            throw new NotFoundException("Trainings templates not found");

        return trainingSessionList;
    }

    public List<TrainingSession> findByDate(String date) {
        List<TrainingSession> trainingSessionList = trainingSessionRepository.findByDate(date);

        if(trainingSessionList.isEmpty())
            throw new NotFoundException("Trainings by date not found");

        return trainingSessionList;
    }

}
