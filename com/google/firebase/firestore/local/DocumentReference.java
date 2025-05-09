package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Util;
import java.util.Comparator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class DocumentReference {
    static final Comparator<DocumentReference> BY_KEY = new Comparator() { // from class: com.google.firebase.firestore.local.DocumentReference$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return DocumentReference.lambda$static$0((DocumentReference) obj, (DocumentReference) obj2);
        }
    };
    static final Comparator<DocumentReference> BY_TARGET = new Comparator() { // from class: com.google.firebase.firestore.local.DocumentReference$$ExternalSyntheticLambda1
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return DocumentReference.lambda$static$1((DocumentReference) obj, (DocumentReference) obj2);
        }
    };
    private final DocumentKey key;
    private final int targetOrBatchId;

    public DocumentReference(DocumentKey documentKey, int i) {
        this.key = documentKey;
        this.targetOrBatchId = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DocumentKey getKey() {
        return this.key;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getId() {
        return this.targetOrBatchId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$static$0(DocumentReference documentReference, DocumentReference documentReference2) {
        int compareTo = documentReference.key.compareTo(documentReference2.key);
        return compareTo != 0 ? compareTo : Util.compareIntegers(documentReference.targetOrBatchId, documentReference2.targetOrBatchId);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$static$1(DocumentReference documentReference, DocumentReference documentReference2) {
        int compareIntegers = Util.compareIntegers(documentReference.targetOrBatchId, documentReference2.targetOrBatchId);
        return compareIntegers != 0 ? compareIntegers : documentReference.key.compareTo(documentReference2.key);
    }
}
