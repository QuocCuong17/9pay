package com.google.firebase.database.core.persistence;

/* loaded from: classes4.dex */
public interface CachePolicy {
    public static final CachePolicy NONE = new CachePolicy() { // from class: com.google.firebase.database.core.persistence.CachePolicy.1
        @Override // com.google.firebase.database.core.persistence.CachePolicy
        public long getMaxNumberOfQueriesToKeep() {
            return Long.MAX_VALUE;
        }

        @Override // com.google.firebase.database.core.persistence.CachePolicy
        public float getPercentOfQueriesToPruneAtOnce() {
            return 0.0f;
        }

        @Override // com.google.firebase.database.core.persistence.CachePolicy
        public boolean shouldCheckCacheSize(long j) {
            return false;
        }

        @Override // com.google.firebase.database.core.persistence.CachePolicy
        public boolean shouldPrune(long j, long j2) {
            return false;
        }
    };

    long getMaxNumberOfQueriesToKeep();

    float getPercentOfQueriesToPruneAtOnce();

    boolean shouldCheckCacheSize(long j);

    boolean shouldPrune(long j, long j2);
}
