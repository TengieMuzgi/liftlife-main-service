package com.liftlife.liftlife.util.database;

public class ServiceTemplate {
    protected <T extends FirestoreEntity,
            Q extends FirestoreEntity> T findChild(
                    Q parent,
                    FirestoreRepositoryTemplate childRepository) {
        return null;
    }
}
