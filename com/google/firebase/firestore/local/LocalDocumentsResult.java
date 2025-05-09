package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import java.util.Map;

/* loaded from: classes4.dex */
public final class LocalDocumentsResult {
    private final int batchId;
    private final ImmutableSortedMap<DocumentKey, Document> documents;

    LocalDocumentsResult(int i, ImmutableSortedMap<DocumentKey, Document> immutableSortedMap) {
        this.batchId = i;
        this.documents = immutableSortedMap;
    }

    public static LocalDocumentsResult fromOverlayedDocuments(int i, Map<DocumentKey, OverlayedDocument> map) {
        ImmutableSortedMap<DocumentKey, Document> emptyDocumentMap = DocumentCollections.emptyDocumentMap();
        for (Map.Entry<DocumentKey, OverlayedDocument> entry : map.entrySet()) {
            emptyDocumentMap = emptyDocumentMap.insert(entry.getKey(), entry.getValue().getDocument());
        }
        return new LocalDocumentsResult(i, emptyDocumentMap);
    }

    public int getBatchId() {
        return this.batchId;
    }

    public ImmutableSortedMap<DocumentKey, Document> getDocuments() {
        return this.documents;
    }
}
