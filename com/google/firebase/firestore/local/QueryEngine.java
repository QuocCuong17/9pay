package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.local.IndexManager;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public class QueryEngine {
    private static final int DEFAULT_INDEX_AUTO_CREATION_MIN_COLLECTION_SIZE = 100;
    private static final double DEFAULT_RELATIVE_INDEX_READ_COST_PER_DOCUMENT = 2.0d;
    private static final String LOG_TAG = "QueryEngine";
    private IndexManager indexManager;
    private boolean initialized;
    private LocalDocumentsView localDocumentsView;
    private boolean indexAutoCreationEnabled = false;
    private int indexAutoCreationMinCollectionSize = 100;
    private double relativeIndexReadCostPerDocument = DEFAULT_RELATIVE_INDEX_READ_COST_PER_DOCUMENT;

    public void initialize(LocalDocumentsView localDocumentsView, IndexManager indexManager) {
        this.localDocumentsView = localDocumentsView;
        this.indexManager = indexManager;
        this.initialized = true;
    }

    public void setIndexAutoCreationEnabled(boolean z) {
        this.indexAutoCreationEnabled = z;
    }

    public ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingQuery(Query query, SnapshotVersion snapshotVersion, ImmutableSortedSet<DocumentKey> immutableSortedSet) {
        Assert.hardAssert(this.initialized, "initialize() not called", new Object[0]);
        ImmutableSortedMap<DocumentKey, Document> performQueryUsingIndex = performQueryUsingIndex(query);
        if (performQueryUsingIndex != null) {
            return performQueryUsingIndex;
        }
        ImmutableSortedMap<DocumentKey, Document> performQueryUsingRemoteKeys = performQueryUsingRemoteKeys(query, immutableSortedSet, snapshotVersion);
        if (performQueryUsingRemoteKeys != null) {
            return performQueryUsingRemoteKeys;
        }
        QueryContext queryContext = new QueryContext();
        ImmutableSortedMap<DocumentKey, Document> executeFullCollectionScan = executeFullCollectionScan(query, queryContext);
        if (executeFullCollectionScan != null && this.indexAutoCreationEnabled) {
            createCacheIndexes(query, queryContext, executeFullCollectionScan.size());
        }
        return executeFullCollectionScan;
    }

    private void createCacheIndexes(Query query, QueryContext queryContext, int i) {
        if (queryContext.getDocumentReadCount() < this.indexAutoCreationMinCollectionSize) {
            Logger.debug(LOG_TAG, "SDK will not create cache indexes for query: %s, since it only creates cache indexes for collection contains more than or equal to %s documents.", query.toString(), Integer.valueOf(this.indexAutoCreationMinCollectionSize));
            return;
        }
        Logger.debug(LOG_TAG, "Query: %s, scans %s local documents and returns %s documents as results.", query.toString(), Integer.valueOf(queryContext.getDocumentReadCount()), Integer.valueOf(i));
        if (queryContext.getDocumentReadCount() > this.relativeIndexReadCostPerDocument * i) {
            this.indexManager.createTargetIndexes(query.toTarget());
            Logger.debug(LOG_TAG, "The SDK decides to create cache indexes for query: %s, as using cache indexes may help improve performance.", query.toString());
        }
    }

    @Nullable
    private ImmutableSortedMap<DocumentKey, Document> performQueryUsingIndex(Query query) {
        if (query.matchesAllDocuments()) {
            return null;
        }
        Target target = query.toTarget();
        IndexManager.IndexType indexType = this.indexManager.getIndexType(target);
        if (indexType.equals(IndexManager.IndexType.NONE)) {
            return null;
        }
        if (query.hasLimit() && indexType.equals(IndexManager.IndexType.PARTIAL)) {
            return performQueryUsingIndex(query.limitToFirst(-1L));
        }
        List<DocumentKey> documentsMatchingTarget = this.indexManager.getDocumentsMatchingTarget(target);
        Assert.hardAssert(documentsMatchingTarget != null, "index manager must return results for partial and full indexes.", new Object[0]);
        ImmutableSortedMap<DocumentKey, Document> documents = this.localDocumentsView.getDocuments(documentsMatchingTarget);
        FieldIndex.IndexOffset minOffset = this.indexManager.getMinOffset(target);
        ImmutableSortedSet<Document> applyQuery = applyQuery(query, documents);
        if (needsRefill(query, documentsMatchingTarget.size(), applyQuery, minOffset.getReadTime())) {
            return performQueryUsingIndex(query.limitToFirst(-1L));
        }
        return appendRemainingResults(applyQuery, query, minOffset);
    }

    @Nullable
    private ImmutableSortedMap<DocumentKey, Document> performQueryUsingRemoteKeys(Query query, ImmutableSortedSet<DocumentKey> immutableSortedSet, SnapshotVersion snapshotVersion) {
        if (query.matchesAllDocuments() || snapshotVersion.equals(SnapshotVersion.NONE)) {
            return null;
        }
        ImmutableSortedSet<Document> applyQuery = applyQuery(query, this.localDocumentsView.getDocuments(immutableSortedSet));
        if (needsRefill(query, immutableSortedSet.size(), applyQuery, snapshotVersion)) {
            return null;
        }
        if (Logger.isDebugEnabled()) {
            Logger.debug(LOG_TAG, "Re-using previous result from %s to execute query: %s", snapshotVersion.toString(), query.toString());
        }
        return appendRemainingResults(applyQuery, query, FieldIndex.IndexOffset.createSuccessor(snapshotVersion, -1));
    }

    private ImmutableSortedSet<Document> applyQuery(Query query, ImmutableSortedMap<DocumentKey, Document> immutableSortedMap) {
        ImmutableSortedSet<Document> immutableSortedSet = new ImmutableSortedSet<>(Collections.emptyList(), query.comparator());
        Iterator<Map.Entry<DocumentKey, Document>> it = immutableSortedMap.iterator();
        while (it.hasNext()) {
            Document value = it.next().getValue();
            if (query.matches(value)) {
                immutableSortedSet = immutableSortedSet.insert(value);
            }
        }
        return immutableSortedSet;
    }

    private boolean needsRefill(Query query, int i, ImmutableSortedSet<Document> immutableSortedSet, SnapshotVersion snapshotVersion) {
        Document minEntry;
        if (!query.hasLimit()) {
            return false;
        }
        if (i != immutableSortedSet.size()) {
            return true;
        }
        if (query.getLimitType() == Query.LimitType.LIMIT_TO_FIRST) {
            minEntry = immutableSortedSet.getMaxEntry();
        } else {
            minEntry = immutableSortedSet.getMinEntry();
        }
        if (minEntry == null) {
            return false;
        }
        return minEntry.hasPendingWrites() || minEntry.getVersion().compareTo(snapshotVersion) > 0;
    }

    private ImmutableSortedMap<DocumentKey, Document> executeFullCollectionScan(Query query, QueryContext queryContext) {
        if (Logger.isDebugEnabled()) {
            Logger.debug(LOG_TAG, "Using full collection scan to execute query: %s", query.toString());
        }
        return this.localDocumentsView.getDocumentsMatchingQuery(query, FieldIndex.IndexOffset.NONE, queryContext);
    }

    private ImmutableSortedMap<DocumentKey, Document> appendRemainingResults(Iterable<Document> iterable, Query query, FieldIndex.IndexOffset indexOffset) {
        ImmutableSortedMap<DocumentKey, Document> documentsMatchingQuery = this.localDocumentsView.getDocumentsMatchingQuery(query, indexOffset);
        for (Document document : iterable) {
            documentsMatchingQuery = documentsMatchingQuery.insert(document.getKey(), document);
        }
        return documentsMatchingQuery;
    }

    void setIndexAutoCreationMinCollectionSize(int i) {
        this.indexAutoCreationMinCollectionSize = i;
    }

    void setRelativeIndexReadCostPerDocument(double d) {
        this.relativeIndexReadCostPerDocument = d;
    }
}
