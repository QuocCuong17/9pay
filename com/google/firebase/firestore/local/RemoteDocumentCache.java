package com.google.firebase.firestore.local;

import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public interface RemoteDocumentCache {
    void add(MutableDocument mutableDocument, SnapshotVersion snapshotVersion);

    MutableDocument get(DocumentKey documentKey);

    Map<DocumentKey, MutableDocument> getAll(Iterable<DocumentKey> iterable);

    Map<DocumentKey, MutableDocument> getAll(String str, FieldIndex.IndexOffset indexOffset, int i);

    Map<DocumentKey, MutableDocument> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset indexOffset, @Nonnull Set<DocumentKey> set);

    Map<DocumentKey, MutableDocument> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset indexOffset, @Nonnull Set<DocumentKey> set, @Nullable QueryContext queryContext);

    void removeAll(Collection<DocumentKey> collection);

    void setIndexManager(IndexManager indexManager);
}
