package com.liftlife.liftlife.trainingModule.trainingSession.template;

import com.liftlife.liftlife.trainingModule.exercise.Exercise;
import com.liftlife.liftlife.trainingModule.exercise.TemplateExerciseRepository;
import com.liftlife.liftlife.trainingModule.trainingSession.TrainingSession;
import com.liftlife.liftlife.util.database.AttributeList;
import com.liftlife.liftlife.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<TrainingSession> trainingSessionList = templateTrainingSessionRepository.findTrainingByTrainer(trainerId);

        if(trainingSessionList.isEmpty())
            throw new NotFoundException("Trainings for trainer with ID " + trainerId + " not found");

        return trainingSessionList;
    }

    public List<TrainingSession> findByTemplate(boolean template) {
        List<TrainingSession> trainingSessionList = templateTrainingSessionRepository.findTrainingByTemplate(template);

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

    public void delete(String documentId) {
        templateTrainingSessionRepository.delete(documentId);
    }

    public List<TrainingSession> findAllByCoach(String trainerId) {
        List<TrainingSession> sessions = templateTrainingSessionRepository.findAll();
        for (TrainingSession session : sessions) {
            fetchExercises(session);
        }
        return sessions;
    }

    private void fetchExercises(TrainingSession session) {
        AttributeList<Exercise> exercises = new AttributeList<>();  //Try to do it better
        for (Exercise exercise: session.getExercises()) {
            exercises.add(templateExerciseRepository.findById(exercise.getDocumentId()));
        }
        session.setExercises(exercises);
    }
}
