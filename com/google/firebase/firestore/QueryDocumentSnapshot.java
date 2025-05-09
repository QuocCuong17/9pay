package com.google.firebase.firestore;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Preconditions;
import java.util.Map;

/* loaded from: classes4.dex */
public class QueryDocumentSnapshot extends DocumentSnapshot {
    private QueryDocumentSnapshot(FirebaseFirestore firebaseFirestore, DocumentKey documentKey, Document document, boolean z, boolean z2) {
        super(firebaseFirestore, documentKey, document, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static QueryDocumentSnapshot fromDocument(FirebaseFirestore firebaseFirestore, Document document, boolean z, boolean z2) {
        return new QueryDocumentSnapshot(firebaseFirestore, document.getKey(), document, z, z2);
    }

    @Override // com.google.firebase.firestore.DocumentSnapshot
    public Map<String, Object> getData() {
        Map<String, Object> data = super.getData();
        Assert.hardAssert(data != null, "Data in a QueryDocumentSnapshot should be non-null", new Object[0]);
        return data;
    }

    @Override // com.google.firebase.firestore.DocumentSnapshot
    public Map<String, Object> getData(DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        Map<String, Object> data = super.getData(serverTimestampBehavior);
        Assert.hardAssert(data != null, "Data in a QueryDocumentSnapshot should be non-null", new Object[0]);
        return data;
    }

    @Override // com.google.firebase.firestore.DocumentSnapshot
    public <T> T toObject(Class<T> cls) {
        T t = (T) super.toObject(cls);
        Assert.hardAssert(t != null, "Object in a QueryDocumentSnapshot should be non-null", new Object[0]);
        return t;
    }

    @Override // com.google.firebase.firestore.DocumentSnapshot
    public <T> T toObject(Class<T> cls, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        T t = (T) super.toObject(cls, serverTimestampBehavior);
        Assert.hardAssert(t != null, "Object in a QueryDocumentSnapshot should be non-null", new Object[0]);
        return t;
    }
}
