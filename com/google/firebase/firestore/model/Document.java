package com.google.firebase.firestore.model;

import com.google.firestore.v1.Value;
import java.util.Comparator;

/* loaded from: classes4.dex */
public interface Document {
    public static final Comparator<Document> KEY_COMPARATOR = new Comparator() { // from class: com.google.firebase.firestore.model.Document$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            int compareTo;
            compareTo = ((Document) obj).getKey().compareTo(((Document) obj2).getKey());
            return compareTo;
        }
    };

    ObjectValue getData();

    Value getField(FieldPath fieldPath);

    DocumentKey getKey();

    SnapshotVersion getReadTime();

    SnapshotVersion getVersion();

    boolean hasCommittedMutations();

    boolean hasLocalMutations();

    boolean hasPendingWrites();

    boolean isFoundDocument();

    boolean isNoDocument();

    boolean isUnknownDocument();

    boolean isValidDocument();

    MutableDocument mutableCopy();

    /* renamed from: com.google.firebase.firestore.model.Document$-CC, reason: invalid class name */
    /* loaded from: classes4.dex */
    public final /* synthetic */ class CC {
        static {
            Comparator<Document> comparator = Document.KEY_COMPARATOR;
        }
    }
}
