package com.liftlife.liftlife.trainingModule.trainingDay;

import com.google.cloud.firestore.WriteResult;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class TrainingDayService {
    private final TrainingDayRepository trainingDayRepository;

    public TrainingDayService(TrainingDayRepository trainingDayRepository) {
        this.trainingDayRepository = trainingDayRepository;
    }

    public List<TrainingDay> findDaysForPlan(String planId) {
        LinkedHashMap<String, String> documentToSubCollection = buildPathToSubCollection(planId);
        return trainingDayRepository.findAll(documentToSubCollection);
    }

    public String insert(TrainingDay trainingDay, String planId) {
        LinkedHashMap<String, String> documentToSubCollection = buildPathToSubCollection(planId);
        return trainingDayRepository.insert(trainingDay, documentToSubCollection);
    }

    public void delete(String dayId, String planId) {
        LinkedHashMap<String, String> documentToSubCollection = buildPathToSubCollection(planId);
        trainingDayRepository.delete(dayId, documentToSubCollection);
    }

    public WriteResult updateDay(TrainingDay trainingDay, String planId) {
        LinkedHashMap<String, String> documentToSubCollection = buildPathToSubCollection(planId);
        return trainingDayRepository.update(trainingDay, documentToSubCollection);
    }

    private static LinkedHashMap<String, String> buildPathToSubCollection(String planId) {
        LinkedHashMap<String, String> documentToSubCollection = new LinkedHashMap<>();
        documentToSubCollection.put(planId, "trainingday");
        return documentToSubCollection;
    }
}
