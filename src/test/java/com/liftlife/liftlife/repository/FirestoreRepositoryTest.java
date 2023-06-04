package com.liftlife.liftlife.repository;

import com.google.cloud.firestore.*;
import com.liftlife.liftlife.trainingModule.exercise.Exercise;
import com.google.api.core.ApiFuture;
import com.liftlife.liftlife.util.database.FirestoreMapper;
import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

class FirestoreRepositoryTemplateTest {
    private FirestoreRepositoryTemplate<Exercise> firestoreRepository;
    private CollectionReference collectionReference;
    private FirestoreMapper firestoreMapper;

    @BeforeEach
    void setUp() {
        collectionReference = mock(CollectionReference.class);
        firestoreMapper = mock(FirestoreMapper.class);
        firestoreRepository = new FirestoreRepositoryTemplate<>(Exercise.class, collectionReference, firestoreMapper);
    }

    @Test
    void insert_ValidObject_ReturnsDocumentId() throws Exception {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setName("Exercise 1");
        Map<String, Object> json = Collections.singletonMap("name", "Exercise 1");
        DocumentReference documentReference = mock(DocumentReference.class);
        ApiFuture<DocumentReference> inserted = mock(ApiFuture.class);
        when(firestoreMapper.objectToMap(exercise)).thenReturn(json);
        when(collectionReference.add(json)).thenReturn(inserted);
        when(inserted.get()).thenReturn(documentReference);
        when(documentReference.getId()).thenReturn("123");

        // Act
        String documentId = firestoreRepository.insert(exercise);

        // Assert
        assertNotNull(documentId);
        assertEquals("123", documentId);
    }

    @Test
    void findById_ExistingDocument_ReturnsMappedObject() throws Exception {
        // Arrange
        String documentId = "123";
        DocumentReference documentReference = mock(DocumentReference.class);
        DocumentSnapshot documentSnapshot = mock(DocumentSnapshot.class);
        Map<String, Object> data = new HashMap<>();
        data.put("documentId", documentId);
        data.put("name", "Exercise 1");
        when(collectionReference.document(documentId)).thenReturn(documentReference);
        when(documentReference.get()).thenReturn(mock(ApiFuture.class));
        when(documentReference.get().get()).thenReturn(documentSnapshot);
        when(documentSnapshot.exists()).thenReturn(true);
        when(documentSnapshot.getData()).thenReturn(data);
        when(firestoreMapper.mapToObject(documentSnapshot, Exercise.class)).thenReturn(new Exercise());

        // Act
        Exercise exercise = firestoreRepository.findById(documentId);

        // Assert
        assertNotNull(exercise);
        assertEquals(documentId, exercise.getDocumentId());
    }
    // Dodaj więcej testów dla pozostałych metod w FirestoreRepositoryTemplate
}