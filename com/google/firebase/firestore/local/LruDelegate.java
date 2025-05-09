package com.google.firebase.firestore.local;

import android.util.SparseArray;
import com.google.firebase.firestore.util.Consumer;

/* loaded from: classes4.dex */
public interface LruDelegate {
    void forEachOrphanedDocumentSequenceNumber(Consumer<Long> consumer);

    void forEachTarget(Consumer<TargetData> consumer);

    long getByteSize();

    LruGarbageCollector getGarbageCollector();

    long getSequenceNumberCount();

    int removeOrphanedDocuments(long j);

    int removeTargets(long j, SparseArray<?> sparseArray);
}
