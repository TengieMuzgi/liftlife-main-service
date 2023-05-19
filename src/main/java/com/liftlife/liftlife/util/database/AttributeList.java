package com.liftlife.liftlife.util.database;

import java.util.ArrayList;

/*
Empty subclass of ArrayList created for "coloring" attributes
for serialization and deserialization with ChildSerializer.
Should be used when List of FirestoreEntities is part of other FirestoreEntity.
i.e. Exercises in TrainingSession should be stored in AttributeList to be properly serialized/deserialized.
Allows to save normal List of Exercises into database without turning it into List of Strings.
*/
public class AttributeList<T extends FirestoreEntity> extends ArrayList<T> {
}
