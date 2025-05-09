package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.remote.TestingHooks;

/* loaded from: classes4.dex */
final class AutoValue_TestingHooks_ExistenceFilterBloomFilterInfo extends TestingHooks.ExistenceFilterBloomFilterInfo {
    private final boolean applied;
    private final int bitmapLength;
    private final BloomFilter bloomFilter;
    private final int hashCount;
    private final int padding;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_TestingHooks_ExistenceFilterBloomFilterInfo(BloomFilter bloomFilter, boolean z, int i, int i2, int i3) {
        this.bloomFilter = bloomFilter;
        this.applied = z;
        this.hashCount = i;
        this.bitmapLength = i2;
        this.padding = i3;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterBloomFilterInfo
    BloomFilter bloomFilter() {
        return this.bloomFilter;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterBloomFilterInfo
    boolean applied() {
        return this.applied;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterBloomFilterInfo
    int hashCount() {
        return this.hashCount;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterBloomFilterInfo
    int bitmapLength() {
        return this.bitmapLength;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterBloomFilterInfo
    int padding() {
        return this.padding;
    }

    public String toString() {
        return "ExistenceFilterBloomFilterInfo{bloomFilter=" + this.bloomFilter + ", applied=" + this.applied + ", hashCount=" + this.hashCount + ", bitmapLength=" + this.bitmapLength + ", padding=" + this.padding + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TestingHooks.ExistenceFilterBloomFilterInfo)) {
            return false;
        }
        TestingHooks.ExistenceFilterBloomFilterInfo existenceFilterBloomFilterInfo = (TestingHooks.ExistenceFilterBloomFilterInfo) obj;
        BloomFilter bloomFilter = this.bloomFilter;
        if (bloomFilter != null ? bloomFilter.equals(existenceFilterBloomFilterInfo.bloomFilter()) : existenceFilterBloomFilterInfo.bloomFilter() == null) {
            if (this.applied == existenceFilterBloomFilterInfo.applied() && this.hashCount == existenceFilterBloomFilterInfo.hashCount() && this.bitmapLength == existenceFilterBloomFilterInfo.bitmapLength() && this.padding == existenceFilterBloomFilterInfo.padding()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        BloomFilter bloomFilter = this.bloomFilter;
        return (((((((((bloomFilter == null ? 0 : bloomFilter.hashCode()) ^ 1000003) * 1000003) ^ (this.applied ? 1231 : 1237)) * 1000003) ^ this.hashCount) * 1000003) ^ this.bitmapLength) * 1000003) ^ this.padding;
    }
}
