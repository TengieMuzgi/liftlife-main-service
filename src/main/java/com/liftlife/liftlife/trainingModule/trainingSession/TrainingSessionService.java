package com.liftlife.liftlife.trainingModule.trainingSession;

import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class TrainingSessionService {
    private final TrainingSessionRepository repository;

    @Autowired
    public TrainingSessionService(TrainingSessionRepository repository) {
        this.repository = repository;
    }

    public String insertSession(TrainingSession trainingSession, String planId, String dayId) {
        LinkedHashMap<String, String> documentToSubCollection = buildPathToSubCollection(planId, dayId);
        return repository.insert(trainingSession, documentToSubCollection);
    }

    public List<TrainingSession> findSessionsForDay(String planId, String dayId) {
           LinkedHashMap<String, String> documentToSubCollection = buildPathToSubCollection(planId, dayId);
        return repository.findAll(documentToSubCollection);
    }

    public WriteResult  updateSession(TrainingSession trainingSession, String planId, String dayId) {
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
}
