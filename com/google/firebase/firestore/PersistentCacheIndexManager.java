package com.google.firebase.firestore;

import com.google.firebase.firestore.core.FirestoreClient;

/* loaded from: classes4.dex */
public final class PersistentCacheIndexManager {
    private FirestoreClient client;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PersistentCacheIndexManager(FirestoreClient firestoreClient) {
        this.client = firestoreClient;
    }

    public void enableIndexAutoCreation() {
        this.client.setIndexAutoCreationEnabled(true);
    }

    public void disableIndexAutoCreation() {
        this.client.setIndexAutoCreationEnabled(false);
    }

    public void deleteAllIndexes() {
        this.client.deleteAllFieldIndexes();
    }
}
