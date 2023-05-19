package com.liftlife.liftlife.trainingModule.trainingSession;

import com.google.cloud.firestore.WriteResult;
import com.liftlife.liftlife.trainingModule.exercise.Exercise;
import com.liftlife.liftlife.trainingModule.exercise.TemplateExerciseRepository;
import com.liftlife.liftlife.util.database.AttributeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class TrainingSessionService {
    private final TrainingSessionRepository repository;
    private final TemplateExerciseRepository templateExerciseRepository;

    @Autowired
    public TrainingSessionService(TrainingSessionRepository repository, TemplateExerciseRepository templateExerciseRepository) {
        this.repository = repository;
        this.templateExerciseRepository = templateExerciseRepository;
    }

    public String insertSession(TrainingSession trainingSession, String planId, String dayId) {
        LinkedHashMap<String, String> documentToSubCollection = buildPathToSubCollection(planId, dayId);
        return repository.insert(trainingSession, documentToSubCollection);
    }

    public List<TrainingSession> findSessionsForDay(String planId, String dayId) {
        LinkedHashMap<String, String> documentToSubCollection = buildPathToSubCollection(planId, dayId);
        List<TrainingSession> sessions = repository.findAll(documentToSubCollection);
        for (TrainingSession session : sessions) {
            fetchExercises(session);
        }
        return sessions;
    }

    public WriteResult updateSession(TrainingSession trainingSession, String planId, String dayId) {
        LinkedHashMap<String, String> documentToSubCollection = buildPathToSubCollection(planId, dayId);
        return repository.update(trainingSession, documentToSubCollection);
    }

    public void deleteSession(String sessionId, String planId, String dayId) {
        LinkedHashMap<String, String> documentToSubCollection = buildPathToSubCollection(planId, dayId);
        repository.delete(sessionId, documentToSubCollection);
    }

    private LinkedHashMap<String, String> buildPathToSubCollection(String planId, String dayId) {
        LinkedHashMap<String, String> documentToSubCollection = new LinkedHashMap<>();
        documentToSubCollection.put(planId, "trainingday");
        documentToSubCollection.put(dayId, "trainingsession");
        return documentToSubCollection;
    }

    private void fetchExercises(TrainingSession session) {
        AttributeList<Exercise> exercises = new AttributeList<>();  //Try to do it better
        for (Exercise exercise: session.getExercises()) {
            exercises.add(templateExerciseRepository.findById(exercise.getDocumentId()));
        }
        session.setExercises(exercises);
    }
}
