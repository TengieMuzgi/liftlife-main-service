package com.liftlife.liftlife.repository;

import com.google.cloud.firestore.*;
import com.liftlife.liftlife.trainingModule.exercise.Exercise;
import com.google.api.core.ApiFuture;
import com.liftlife.liftlife.util.database.FirestoreMapper;
import com.liftlife.liftlife.util.database.FirestoreSubRepositoryTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

class FirestoreSubRepositoryTemplateTest {
    private FirestoreSubRepositoryTemplate<Exercise> firestoreSubRepository;
    private CollectionReference mainCollectionReference;
    private FirestoreMapper firestoreMapper;

    @BeforeEach
    void setUp() {
        mainCollectionReference = mock(CollectionReference.class);
        firestoreMapper = mock(FirestoreMapper.class);
        firestoreSubRepository = new FirestoreSubRepositoryTemplate<>(mainCollectionReference, firestoreMapper, Exercise.class);
    }

    @Test
    void findById_ExistingDocument_ReturnsMappedObject() throws Exception {
        // Arrange
        String documentId = "123";
        LinkedHashMap<String, String> documentIdsToSubCollections = new LinkedHashMap<>();
        documentIdsToSubCollections.put("mainDocumentId", "subCollection");
        DocumentReference subCollectionDocumentReference = mock(DocumentReference.class);
        CollectionReference subCollectionReference = mock(CollectionReference.class);
        DocumentSnapshot documentSnapshot = mock(DocumentSnapshot.class);
        Map<String, Object> data = new HashMap<>();
        data.put("documentId", documentId);
        data.put("name", "Exercise 1");
        when(mainCollectionReference.document("mainDocumentId")).thenReturn(subCollectionDocumentReference);
        when(subCollectionDocumentReference.collection("subCollection")).thenReturn(subCollectionReference);
        when(subCollectionReference.document(documentId)).thenReturn(subCollectionDocumentReference);
        when(subCollectionDocumentReference.get()).thenReturn(mock(ApiFuture.class));
        when(subCollectionDocumentReference.get().get()).thenReturn(documentSnapshot);
        when(documentSnapshot.exists()).thenReturn(true);
        when(documentSnapshot.getData()).thenReturn(data);
        when(firestoreMapper.mapToObject(documentSnapshot, Exercise.class)).thenReturn(new Exercise());

        // Act
        Exercise exercise = firestoreSubRepository.findById(documentId, documentIdsToSubCollections);

        // Assert
        assertNotNull(exercise);
        assertEquals(documentId, exercise.getDocumentId());
    }
    // Dodaj więcej testów dla pozostałych metod w FirestoreSubRepositoryTemplate
}
