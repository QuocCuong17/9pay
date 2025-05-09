package com.google.firebase.firestore.local;

import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.model.mutation.Overlay;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.util.Assert;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class LocalDocumentsView {
    private final DocumentOverlayCache documentOverlayCache;
    private final IndexManager indexManager;
    private final MutationQueue mutationQueue;
    private final RemoteDocumentCache remoteDocumentCache;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocalDocumentsView(RemoteDocumentCache remoteDocumentCache, MutationQueue mutationQueue, DocumentOverlayCache documentOverlayCache, IndexManager indexManager) {
        this.remoteDocumentCache = remoteDocumentCache;
        this.mutationQueue = mutationQueue;
        this.documentOverlayCache = documentOverlayCache;
        this.indexManager = indexManager;
    }

    RemoteDocumentCache getRemoteDocumentCache() {
        return this.remoteDocumentCache;
    }

    MutationQueue getMutationQueue() {
        return this.mutationQueue;
    }

    DocumentOverlayCache getDocumentOverlayCache() {
        return this.documentOverlayCache;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Document getDocument(DocumentKey documentKey) {
        Overlay overlay = this.documentOverlayCache.getOverlay(documentKey);
        MutableDocument baseDocument = getBaseDocument(documentKey, overlay);
        if (overlay != null) {
            overlay.getMutation().applyToLocalView(baseDocument, FieldMask.EMPTY, Timestamp.now());
        }
        return baseDocument;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableSortedMap<DocumentKey, Document> getDocuments(Iterable<DocumentKey> iterable) {
        return getLocalViewOfDocuments(this.remoteDocumentCache.getAll(iterable), new HashSet());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableSortedMap<DocumentKey, Document> getLocalViewOfDocuments(Map<DocumentKey, MutableDocument> map, Set<DocumentKey> set) {
        HashMap hashMap = new HashMap();
        populateOverlays(hashMap, map.keySet());
        ImmutableSortedMap<DocumentKey, Document> emptyDocumentMap = DocumentCollections.emptyDocumentMap();
        for (Map.Entry<DocumentKey, OverlayedDocument> entry : computeViews(map, hashMap, set).entrySet()) {
            emptyDocumentMap = emptyDocumentMap.insert(entry.getKey(), entry.getValue().getDocument());
        }
        return emptyDocumentMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<DocumentKey, OverlayedDocument> getOverlayedDocuments(Map<DocumentKey, MutableDocument> map) {
        HashMap hashMap = new HashMap();
        populateOverlays(hashMap, map.keySet());
        return computeViews(map, hashMap, new HashSet());
    }

    private Map<DocumentKey, OverlayedDocument> computeViews(Map<DocumentKey, MutableDocument> map, Map<DocumentKey, Overlay> map2, Set<DocumentKey> set) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        for (MutableDocument mutableDocument : map.values()) {
            Overlay overlay = map2.get(mutableDocument.getKey());
            if (set.contains(mutableDocument.getKey()) && (overlay == null || (overlay.getMutation() instanceof PatchMutation))) {
                hashMap.put(mutableDocument.getKey(), mutableDocument);
            } else if (overlay != null) {
                hashMap2.put(mutableDocument.getKey(), overlay.getMutation().getFieldMask());
                overlay.getMutation().applyToLocalView(mutableDocument, overlay.getMutation().getFieldMask(), Timestamp.now());
            } else {
                hashMap2.put(mutableDocument.getKey(), FieldMask.EMPTY);
            }
        }
        hashMap2.putAll(recalculateAndSaveOverlays(hashMap));
        HashMap hashMap3 = new HashMap();
        for (Map.Entry<DocumentKey, MutableDocument> entry : map.entrySet()) {
            hashMap3.put(entry.getKey(), new OverlayedDocument(entry.getValue(), (FieldMask) hashMap2.get(entry.getKey())));
        }
        return hashMap3;
    }

    private Map<DocumentKey, FieldMask> recalculateAndSaveOverlays(Map<DocumentKey, MutableDocument> map) {
        List<MutationBatch> allMutationBatchesAffectingDocumentKeys = this.mutationQueue.getAllMutationBatchesAffectingDocumentKeys(map.keySet());
        HashMap hashMap = new HashMap();
        TreeMap treeMap = new TreeMap();
        for (MutationBatch mutationBatch : allMutationBatchesAffectingDocumentKeys) {
            for (DocumentKey documentKey : mutationBatch.getKeys()) {
                MutableDocument mutableDocument = map.get(documentKey);
                if (mutableDocument != null) {
                    hashMap.put(documentKey, mutationBatch.applyToLocalView(mutableDocument, hashMap.containsKey(documentKey) ? (FieldMask) hashMap.get(documentKey) : FieldMask.EMPTY));
                    int batchId = mutationBatch.getBatchId();
                    if (!treeMap.containsKey(Integer.valueOf(batchId))) {
                        treeMap.put(Integer.valueOf(batchId), new HashSet());
                    }
                    ((Set) treeMap.get(Integer.valueOf(batchId))).add(documentKey);
                }
            }
        }
        HashSet hashSet = new HashSet();
        for (Map.Entry entry : treeMap.descendingMap().entrySet()) {
            HashMap hashMap2 = new HashMap();
            for (DocumentKey documentKey2 : (Set) entry.getValue()) {
                if (!hashSet.contains(documentKey2)) {
                    Mutation calculateOverlayMutation = Mutation.calculateOverlayMutation(map.get(documentKey2), (FieldMask) hashMap.get(documentKey2));
                    if (calculateOverlayMutation != null) {
                        hashMap2.put(documentKey2, calculateOverlayMutation);
                    }
                    hashSet.add(documentKey2);
                }
            }
            this.documentOverlayCache.saveOverlays(((Integer) entry.getKey()).intValue(), hashMap2);
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recalculateAndSaveOverlays(Set<DocumentKey> set) {
        recalculateAndSaveOverlays(this.remoteDocumentCache.getAll(set));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset indexOffset, QueryContext queryContext) {
        ResourcePath path = query.getPath();
        if (query.isDocumentQuery()) {
            return getDocumentsMatchingDocumentQuery(path);
        }
        if (query.isCollectionGroupQuery()) {
            return getDocumentsMatchingCollectionGroupQuery(query, indexOffset, queryContext);
        }
        return getDocumentsMatchingCollectionQuery(query, indexOffset, queryContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset indexOffset) {
        return getDocumentsMatchingQuery(query, indexOffset, null);
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingDocumentQuery(ResourcePath resourcePath) {
        ImmutableSortedMap<DocumentKey, Document> emptyDocumentMap = DocumentCollections.emptyDocumentMap();
        Document document = getDocument(DocumentKey.fromPath(resourcePath));
        return document.isFoundDocument() ? emptyDocumentMap.insert(document.getKey(), document) : emptyDocumentMap;
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingCollectionGroupQuery(Query query, FieldIndex.IndexOffset indexOffset, QueryContext queryContext) {
        Assert.hardAssert(query.getPath().isEmpty(), "Currently we only support collection group queries at the root.", new Object[0]);
        String collectionGroup = query.getCollectionGroup();
        ImmutableSortedMap<DocumentKey, Document> emptyDocumentMap = DocumentCollections.emptyDocumentMap();
        Iterator<ResourcePath> it = this.indexManager.getCollectionParents(collectionGroup).iterator();
        while (it.hasNext()) {
            Iterator<Map.Entry<DocumentKey, Document>> it2 = getDocumentsMatchingCollectionQuery(query.asCollectionQueryAtPath(it.next().append(collectionGroup)), indexOffset, queryContext).iterator();
            while (it2.hasNext()) {
                Map.Entry<DocumentKey, Document> next = it2.next();
                emptyDocumentMap = emptyDocumentMap.insert(next.getKey(), next.getValue());
            }
        }
        return emptyDocumentMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocalDocumentsResult getNextDocuments(String str, FieldIndex.IndexOffset indexOffset, int i) {
        Map<DocumentKey, Overlay> emptyMap;
        Map<DocumentKey, MutableDocument> all = this.remoteDocumentCache.getAll(str, indexOffset, i);
        if (i - all.size() > 0) {
            emptyMap = this.documentOverlayCache.getOverlays(str, indexOffset.getLargestBatchId(), i - all.size());
        } else {
            emptyMap = Collections.emptyMap();
        }
        int i2 = -1;
        for (Overlay overlay : emptyMap.values()) {
            if (!all.containsKey(overlay.getKey())) {
                all.put(overlay.getKey(), getBaseDocument(overlay.getKey(), overlay));
            }
            i2 = Math.max(i2, overlay.getLargestBatchId());
        }
        populateOverlays(emptyMap, all.keySet());
        return LocalDocumentsResult.fromOverlayedDocuments(i2, computeViews(all, emptyMap, Collections.emptySet()));
    }

    private void populateOverlays(Map<DocumentKey, Overlay> map, Set<DocumentKey> set) {
        TreeSet treeSet = new TreeSet();
        for (DocumentKey documentKey : set) {
            if (!map.containsKey(documentKey)) {
                treeSet.add(documentKey);
            }
        }
        map.putAll(this.documentOverlayCache.getOverlays(treeSet));
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingCollectionQuery(Query query, FieldIndex.IndexOffset indexOffset, QueryContext queryContext) {
        Map<DocumentKey, Overlay> overlays = this.documentOverlayCache.getOverlays(query.getPath(), indexOffset.getLargestBatchId());
        Map<DocumentKey, MutableDocument> documentsMatchingQuery = this.remoteDocumentCache.getDocumentsMatchingQuery(query, indexOffset, overlays.keySet(), queryContext);
        for (Map.Entry<DocumentKey, Overlay> entry : overlays.entrySet()) {
            if (!documentsMatchingQuery.containsKey(entry.getKey())) {
                documentsMatchingQuery.put(entry.getKey(), MutableDocument.newInvalidDocument(entry.getKey()));
            }
        }
        ImmutableSortedMap<DocumentKey, Document> emptyDocumentMap = DocumentCollections.emptyDocumentMap();
        for (Map.Entry<DocumentKey, MutableDocument> entry2 : documentsMatchingQuery.entrySet()) {
            Overlay overlay = overlays.get(entry2.getKey());
            if (overlay != null) {
                overlay.getMutation().applyToLocalView(entry2.getValue(), FieldMask.EMPTY, Timestamp.now());
            }
            if (query.matches(entry2.getValue())) {
                emptyDocumentMap = emptyDocumentMap.insert(entry2.getKey(), entry2.getValue());
            }
        }
        return emptyDocumentMap;
    }

    private MutableDocument getBaseDocument(DocumentKey documentKey, Overlay overlay) {
        if (overlay == null || (overlay.getMutation() instanceof PatchMutation)) {
            return this.remoteDocumentCache.get(documentKey);
        }
        return MutableDocument.newInvalidDocument(documentKey);
    }
}
