package com.google.firebase.firestore.core;

import com.google.firebase.firestore.ListenerRegistration;

/* loaded from: classes4.dex */
public class ListenerRegistrationImpl implements ListenerRegistration {
    private final AsyncEventListener<ViewSnapshot> asyncEventListener;
    private final FirestoreClient client;
    private final QueryListener queryListener;

    public ListenerRegistrationImpl(FirestoreClient firestoreClient, QueryListener queryListener, AsyncEventListener<ViewSnapshot> asyncEventListener) {
        this.client = firestoreClient;
        this.queryListener = queryListener;
        this.asyncEventListener = asyncEventListener;
    }

    @Override // com.google.firebase.firestore.ListenerRegistration
    public void remove() {
        this.asyncEventListener.mute();
        this.client.stopListening(this.queryListener);
    }
}
