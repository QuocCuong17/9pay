package com.google.firebase.firestore.model.mutation;

import com.google.firebase.firestore.model.DocumentKey;

/* loaded from: classes4.dex */
public abstract class Overlay {
    public abstract int getLargestBatchId();

    public abstract Mutation getMutation();

    public static Overlay create(int i, Mutation mutation) {
        return new AutoValue_Overlay(i, mutation);
    }

    public DocumentKey getKey() {
        return getMutation().getKey();
    }
}
