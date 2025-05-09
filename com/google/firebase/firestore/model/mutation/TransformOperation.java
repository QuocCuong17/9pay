package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firestore.v1.Value;

/* loaded from: classes4.dex */
public interface TransformOperation {
    Value applyToLocalView(Value value, Timestamp timestamp);

    Value applyToRemoteDocument(Value value, Value value2);

    Value computeBaseValue(Value value);
}
