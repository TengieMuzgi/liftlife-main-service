package com.liftlife.liftlife.repository;

import com.google.cloud.firestore.DocumentSnapshot;
import com.liftlife.liftlife.trainingModule.exercise.Exercise;
import com.liftlife.liftlife.util.database.FirestoreMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FirestoreMapperTest {
    private FirestoreMapper firestoreMapper;

    @BeforeEach
    void setUp() {
        firestoreMapper = new FirestoreMapper();
    }

    @Test
    void mapToObject_ValidDocumentSnapshot_ReturnsMappedObject() {
        // Arrange
        DocumentSnapshot documentSnapshot = mock(DocumentSnapshot.class);
        Map<String, Object> data = new HashMap<>();
        data.put("documentId", "123");
        data.put("name", "Exercise 1");
        when(documentSnapshot.getData()).thenReturn(data);

        // Act
        Exercise exercise = firestoreMapper.mapToObject(documentSnapshot, Exercise.class);

        // Assert
        assertNotNull(exercise);
        assertEquals("Exercise 1", exercise.getName());
    }

    @Test
    void objectToMap_ValidObject_ReturnsMappedMap() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setDocumentId("456");
        exercise.setName("Exercise 2");

        // Act
        Map<String, Object> map = firestoreMapper.objectToMap(exercise);

        // Assert
        assertNotNull(map);
        assertEquals("Exercise 2", map.get("name"));
    }

    @Test
    void mapToJson_ValidObject_ReturnsMappedJsonString() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setDocumentId("789");
        exercise.setName("Exercise 3");

        // Act
        String json = firestoreMapper.mapToJson(exercise);

        // Assert
        assertNotNull(json);
        assertTrue(json.contains("\"name\":\"Exercise 3\""));
    }
}