package com.google.firebase.database.core.persistence;

import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.utilities.Clock;
import com.google.firebase.database.core.utilities.ImmutableTree;
import com.google.firebase.database.core.utilities.Predicate;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.QueryParams;
import com.google.firebase.database.core.view.QuerySpec;
import com.google.firebase.database.logging.LogWrapper;
import com.google.firebase.database.snapshot.ChildKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public class TrackedQueryManager {
    private final Clock clock;
    private long currentQueryId;
    private final LogWrapper logger;
    private final PersistenceStorageEngine storageLayer;
    private ImmutableTree<Map<QueryParams, TrackedQuery>> trackedQueryTree = new ImmutableTree<>(null);
    private static final Predicate<Map<QueryParams, TrackedQuery>> HAS_DEFAULT_COMPLETE_PREDICATE = new Predicate<Map<QueryParams, TrackedQuery>>() { // from class: com.google.firebase.database.core.persistence.TrackedQueryManager.1
        @Override // com.google.firebase.database.core.utilities.Predicate
        public boolean evaluate(Map<QueryParams, TrackedQuery> map) {
            TrackedQuery trackedQuery = map.get(QueryParams.DEFAULT_PARAMS);
            return trackedQuery != null && trackedQuery.complete;
        }
    };
    private static final Predicate<Map<QueryParams, TrackedQuery>> HAS_ACTIVE_DEFAULT_PREDICATE = new Predicate<Map<QueryParams, TrackedQuery>>() { // from class: com.google.firebase.database.core.persistence.TrackedQueryManager.2
        @Override // com.google.firebase.database.core.utilities.Predicate
        public boolean evaluate(Map<QueryParams, TrackedQuery> map) {
            TrackedQuery trackedQuery = map.get(QueryParams.DEFAULT_PARAMS);
            return trackedQuery != null && trackedQuery.active;
        }
    };
    private static final Predicate<TrackedQuery> IS_QUERY_PRUNABLE_PREDICATE = new Predicate<TrackedQuery>() { // from class: com.google.firebase.database.core.persistence.TrackedQueryManager.3
        @Override // com.google.firebase.database.core.utilities.Predicate
        public boolean evaluate(TrackedQuery trackedQuery) {
            return !trackedQuery.active;
        }
    };
    private static final Predicate<TrackedQuery> IS_QUERY_UNPRUNABLE_PREDICATE = new Predicate<TrackedQuery>() { // from class: com.google.firebase.database.core.persistence.TrackedQueryManager.4
        @Override // com.google.firebase.database.core.utilities.Predicate
        public boolean evaluate(TrackedQuery trackedQuery) {
            return !TrackedQueryManager.IS_QUERY_PRUNABLE_PREDICATE.evaluate(trackedQuery);
        }
    };

    private static void assertValidTrackedQuery(QuerySpec querySpec) {
        Utilities.hardAssert(!querySpec.loadsAllData() || querySpec.isDefault(), "Can't have tracked non-default query that loads all data");
    }

    private static QuerySpec normalizeQuery(QuerySpec querySpec) {
        return querySpec.loadsAllData() ? QuerySpec.defaultQueryAtPath(querySpec.getPath()) : querySpec;
    }

    public TrackedQueryManager(PersistenceStorageEngine persistenceStorageEngine, LogWrapper logWrapper, Clock clock) {
        this.currentQueryId = 0L;
        this.storageLayer = persistenceStorageEngine;
        this.logger = logWrapper;
        this.clock = clock;
        resetPreviouslyActiveTrackedQueries();
        for (TrackedQuery trackedQuery : persistenceStorageEngine.loadTrackedQueries()) {
            this.currentQueryId = Math.max(trackedQuery.f82id + 1, this.currentQueryId);
            cacheTrackedQuery(trackedQuery);
        }
    }

    private void resetPreviouslyActiveTrackedQueries() {
        try {
            this.storageLayer.beginTransaction();
            this.storageLayer.resetPreviouslyActiveTrackedQueries(this.clock.millis());
            this.storageLayer.setTransactionSuccessful();
        } finally {
            this.storageLayer.endTransaction();
        }
    }

    public TrackedQuery findTrackedQuery(QuerySpec querySpec) {
        QuerySpec normalizeQuery = normalizeQuery(querySpec);
        Map<QueryParams, TrackedQuery> map = this.trackedQueryTree.get(normalizeQuery.getPath());
        if (map != null) {
            return map.get(normalizeQuery.getParams());
        }
        return null;
    }

    public void removeTrackedQuery(QuerySpec querySpec) {
        QuerySpec normalizeQuery = normalizeQuery(querySpec);
        TrackedQuery findTrackedQuery = findTrackedQuery(normalizeQuery);
        Utilities.hardAssert(findTrackedQuery != null, "Query must exist to be removed.");
        this.storageLayer.deleteTrackedQuery(findTrackedQuery.f82id);
        Map<QueryParams, TrackedQuery> map = this.trackedQueryTree.get(normalizeQuery.getPath());
        map.remove(normalizeQuery.getParams());
        if (map.isEmpty()) {
            this.trackedQueryTree = this.trackedQueryTree.remove(normalizeQuery.getPath());
        }
    }

    public void setQueryActive(QuerySpec querySpec) {
        setQueryActiveFlag(querySpec, true);
    }

    public void setQueryInactive(QuerySpec querySpec) {
        setQueryActiveFlag(querySpec, false);
    }

    private void setQueryActiveFlag(QuerySpec querySpec, boolean z) {
        TrackedQuery trackedQuery;
        QuerySpec normalizeQuery = normalizeQuery(querySpec);
        TrackedQuery findTrackedQuery = findTrackedQuery(normalizeQuery);
        long millis = this.clock.millis();
        if (findTrackedQuery != null) {
            trackedQuery = findTrackedQuery.updateLastUse(millis).setActiveState(z);
        } else {
            Utilities.hardAssert(z, "If we're setting the query to inactive, we should already be tracking it!");
            long j = this.currentQueryId;
            this.currentQueryId = 1 + j;
            trackedQuery = new TrackedQuery(j, normalizeQuery, millis, false, z);
        }
        saveTrackedQuery(trackedQuery);
    }

    public void setQueryCompleteIfExists(QuerySpec querySpec) {
        TrackedQuery findTrackedQuery = findTrackedQuery(normalizeQuery(querySpec));
        if (findTrackedQuery == null || findTrackedQuery.complete) {
            return;
        }
        saveTrackedQuery(findTrackedQuery.setComplete());
    }

    public void setQueriesComplete(Path path) {
        this.trackedQueryTree.subtree(path).foreach(new ImmutableTree.TreeVisitor<Map<QueryParams, TrackedQuery>, Void>() { // from class: com.google.firebase.database.core.persistence.TrackedQueryManager.5
            @Override // com.google.firebase.database.core.utilities.ImmutableTree.TreeVisitor
            public Void onNodeValue(Path path2, Map<QueryParams, TrackedQuery> map, Void r3) {
                Iterator<Map.Entry<QueryParams, TrackedQuery>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    TrackedQuery value = it.next().getValue();
                    if (!value.complete) {
                        TrackedQueryManager.this.saveTrackedQuery(value.setComplete());
                    }
                }
                return null;
            }
        });
    }

    public boolean isQueryComplete(QuerySpec querySpec) {
        if (includedInDefaultCompleteQuery(querySpec.getPath())) {
            return true;
        }
        if (querySpec.loadsAllData()) {
            return false;
        }
        Map<QueryParams, TrackedQuery> map = this.trackedQueryTree.get(querySpec.getPath());
        return map != null && map.containsKey(querySpec.getParams()) && map.get(querySpec.getParams()).complete;
    }

    public PruneForest pruneOldQueries(CachePolicy cachePolicy) {
        List<TrackedQuery> queriesMatching = getQueriesMatching(IS_QUERY_PRUNABLE_PREDICATE);
        long calculateCountToPrune = calculateCountToPrune(cachePolicy, queriesMatching.size());
        PruneForest pruneForest = new PruneForest();
        if (this.logger.logsDebug()) {
            this.logger.debug("Pruning old queries.  Prunable: " + queriesMatching.size() + " Count to prune: " + calculateCountToPrune, new Object[0]);
        }
        Collections.sort(queriesMatching, new Comparator<TrackedQuery>() { // from class: com.google.firebase.database.core.persistence.TrackedQueryManager.6
            @Override // java.util.Comparator
            public int compare(TrackedQuery trackedQuery, TrackedQuery trackedQuery2) {
                return Utilities.compareLongs(trackedQuery.lastUse, trackedQuery2.lastUse);
            }
        });
        for (int i = 0; i < calculateCountToPrune; i++) {
            TrackedQuery trackedQuery = queriesMatching.get(i);
            pruneForest = pruneForest.prune(trackedQuery.querySpec.getPath());
            removeTrackedQuery(trackedQuery.querySpec);
        }
        for (int i2 = (int) calculateCountToPrune; i2 < queriesMatching.size(); i2++) {
            pruneForest = pruneForest.keep(queriesMatching.get(i2).querySpec.getPath());
        }
        List<TrackedQuery> queriesMatching2 = getQueriesMatching(IS_QUERY_UNPRUNABLE_PREDICATE);
        if (this.logger.logsDebug()) {
            this.logger.debug("Unprunable queries: " + queriesMatching2.size(), new Object[0]);
        }
        Iterator<TrackedQuery> it = queriesMatching2.iterator();
        while (it.hasNext()) {
            pruneForest = pruneForest.keep(it.next().querySpec.getPath());
        }
        return pruneForest;
    }

    private static long calculateCountToPrune(CachePolicy cachePolicy, long j) {
        return j - Math.min((long) Math.floor(((float) j) * (1.0f - cachePolicy.getPercentOfQueriesToPruneAtOnce())), cachePolicy.getMaxNumberOfQueriesToKeep());
    }

    public Set<ChildKey> getKnownCompleteChildren(Path path) {
        Utilities.hardAssert(!isQueryComplete(QuerySpec.defaultQueryAtPath(path)), "Path is fully complete.");
        HashSet hashSet = new HashSet();
        Set<Long> filteredQueryIdsAtPath = filteredQueryIdsAtPath(path);
        if (!filteredQueryIdsAtPath.isEmpty()) {
            hashSet.addAll(this.storageLayer.loadTrackedQueryKeys(filteredQueryIdsAtPath));
        }
        Iterator<Map.Entry<ChildKey, ImmutableTree<Map<QueryParams, TrackedQuery>>>> it = this.trackedQueryTree.subtree(path).getChildren().iterator();
        while (it.hasNext()) {
            Map.Entry<ChildKey, ImmutableTree<Map<QueryParams, TrackedQuery>>> next = it.next();
            ChildKey key = next.getKey();
            ImmutableTree<Map<QueryParams, TrackedQuery>> value = next.getValue();
            if (value.getValue() != null && HAS_DEFAULT_COMPLETE_PREDICATE.evaluate(value.getValue())) {
                hashSet.add(key);
            }
        }
        return hashSet;
    }

    public void ensureCompleteTrackedQuery(Path path) {
        TrackedQuery complete;
        if (includedInDefaultCompleteQuery(path)) {
            return;
        }
        QuerySpec defaultQueryAtPath = QuerySpec.defaultQueryAtPath(path);
        TrackedQuery findTrackedQuery = findTrackedQuery(defaultQueryAtPath);
        if (findTrackedQuery == null) {
            long j = this.currentQueryId;
            this.currentQueryId = 1 + j;
            complete = new TrackedQuery(j, defaultQueryAtPath, this.clock.millis(), true, false);
        } else {
            Utilities.hardAssert(!findTrackedQuery.complete, "This should have been handled above!");
            complete = findTrackedQuery.setComplete();
        }
        saveTrackedQuery(complete);
    }

    public boolean hasActiveDefaultQuery(Path path) {
        return this.trackedQueryTree.rootMostValueMatching(path, HAS_ACTIVE_DEFAULT_PREDICATE) != null;
    }

    public long countOfPrunableQueries() {
        return getQueriesMatching(IS_QUERY_PRUNABLE_PREDICATE).size();
    }

    void verifyCache() {
        List<TrackedQuery> loadTrackedQueries = this.storageLayer.loadTrackedQueries();
        final ArrayList arrayList = new ArrayList();
        this.trackedQueryTree.foreach(new ImmutableTree.TreeVisitor<Map<QueryParams, TrackedQuery>, Void>() { // from class: com.google.firebase.database.core.persistence.TrackedQueryManager.7
            @Override // com.google.firebase.database.core.utilities.ImmutableTree.TreeVisitor
            public Void onNodeValue(Path path, Map<QueryParams, TrackedQuery> map, Void r3) {
                Iterator<TrackedQuery> it = map.values().iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next());
                }
                return null;
            }
        });
        Collections.sort(arrayList, new Comparator<TrackedQuery>() { // from class: com.google.firebase.database.core.persistence.TrackedQueryManager.8
            @Override // java.util.Comparator
            public int compare(TrackedQuery trackedQuery, TrackedQuery trackedQuery2) {
                return Utilities.compareLongs(trackedQuery.f82id, trackedQuery2.f82id);
            }
        });
        Utilities.hardAssert(loadTrackedQueries.equals(arrayList), "Tracked queries out of sync.  Tracked queries: " + arrayList + " Stored queries: " + loadTrackedQueries);
    }

    private boolean includedInDefaultCompleteQuery(Path path) {
        return this.trackedQueryTree.findRootMostMatchingPath(path, HAS_DEFAULT_COMPLETE_PREDICATE) != null;
    }

    private Set<Long> filteredQueryIdsAtPath(Path path) {
        HashSet hashSet = new HashSet();
        Map<QueryParams, TrackedQuery> map = this.trackedQueryTree.get(path);
        if (map != null) {
            for (TrackedQuery trackedQuery : map.values()) {
                if (!trackedQuery.querySpec.loadsAllData()) {
                    hashSet.add(Long.valueOf(trackedQuery.f82id));
                }
            }
        }
        return hashSet;
    }

    private void cacheTrackedQuery(TrackedQuery trackedQuery) {
        assertValidTrackedQuery(trackedQuery.querySpec);
        Map<QueryParams, TrackedQuery> map = this.trackedQueryTree.get(trackedQuery.querySpec.getPath());
        if (map == null) {
            map = new HashMap<>();
            this.trackedQueryTree = this.trackedQueryTree.set(trackedQuery.querySpec.getPath(), map);
        }
        TrackedQuery trackedQuery2 = map.get(trackedQuery.querySpec.getParams());
        Utilities.hardAssert(trackedQuery2 == null || trackedQuery2.f82id == trackedQuery.f82id);
        map.put(trackedQuery.querySpec.getParams(), trackedQuery);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveTrackedQuery(TrackedQuery trackedQuery) {
        cacheTrackedQuery(trackedQuery);
        this.storageLayer.saveTrackedQuery(trackedQuery);
    }

    private List<TrackedQuery> getQueriesMatching(Predicate<TrackedQuery> predicate) {
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<Path, Map<QueryParams, TrackedQuery>>> it = this.trackedQueryTree.iterator();
        while (it.hasNext()) {
            for (TrackedQuery trackedQuery : it.next().getValue().values()) {
                if (predicate.evaluate(trackedQuery)) {
                    arrayList.add(trackedQuery);
                }
            }
        }
        return arrayList;
    }
}
