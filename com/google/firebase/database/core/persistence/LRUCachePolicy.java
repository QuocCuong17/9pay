package com.google.firebase.database.core.persistence;

/* loaded from: classes4.dex */
public class LRUCachePolicy implements CachePolicy {
    private static final long MAX_NUMBER_OF_PRUNABLE_QUERIES_TO_KEEP = 1000;
    private static final float PERCENT_OF_QUERIES_TO_PRUNE_AT_ONCE = 0.2f;
    private static final long SERVER_UPDATES_BETWEEN_CACHE_SIZE_CHECKS = 1000;
    public final long maxSizeBytes;

    @Override // com.google.firebase.database.core.persistence.CachePolicy
    public long getMaxNumberOfQueriesToKeep() {
        return 1000L;
    }

    @Override // com.google.firebase.database.core.persistence.CachePolicy
    public float getPercentOfQueriesToPruneAtOnce() {
        return 0.2f;
    }

    @Override // com.google.firebase.database.core.persistence.CachePolicy
    public boolean shouldCheckCacheSize(long j) {
        return j > 1000;
    }

    public LRUCachePolicy(long j) {
        this.maxSizeBytes = j;
    }

    @Override // com.google.firebase.database.core.persistence.CachePolicy
    public boolean shouldPrune(long j, long j2) {
        return j > this.maxSizeBytes || j2 > 1000;
    }
}
