package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.remote.TestingHooks;
import java.util.Objects;

/* loaded from: classes4.dex */
final class AutoValue_TestingHooks_ExistenceFilterMismatchInfo extends TestingHooks.ExistenceFilterMismatchInfo {
    private final TestingHooks.ExistenceFilterBloomFilterInfo bloomFilter;
    private final String databaseId;
    private final int existenceFilterCount;
    private final int localCacheCount;
    private final String projectId;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_TestingHooks_ExistenceFilterMismatchInfo(int i, int i2, String str, String str2, TestingHooks.ExistenceFilterBloomFilterInfo existenceFilterBloomFilterInfo) {
        this.localCacheCount = i;
        this.existenceFilterCount = i2;
        Objects.requireNonNull(str, "Null projectId");
        this.projectId = str;
        Objects.requireNonNull(str2, "Null databaseId");
        this.databaseId = str2;
        this.bloomFilter = existenceFilterBloomFilterInfo;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterMismatchInfo
    int localCacheCount() {
        return this.localCacheCount;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterMismatchInfo
    int existenceFilterCount() {
        return this.existenceFilterCount;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterMismatchInfo
    String projectId() {
        return this.projectId;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterMismatchInfo
    String databaseId() {
        return this.databaseId;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterMismatchInfo
    TestingHooks.ExistenceFilterBloomFilterInfo bloomFilter() {
        return this.bloomFilter;
    }

    public String toString() {
        return "ExistenceFilterMismatchInfo{localCacheCount=" + this.localCacheCount + ", existenceFilterCount=" + this.existenceFilterCount + ", projectId=" + this.projectId + ", databaseId=" + this.databaseId + ", bloomFilter=" + this.bloomFilter + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TestingHooks.ExistenceFilterMismatchInfo)) {
            return false;
        }
        TestingHooks.ExistenceFilterMismatchInfo existenceFilterMismatchInfo = (TestingHooks.ExistenceFilterMismatchInfo) obj;
        if (this.localCacheCount == existenceFilterMismatchInfo.localCacheCount() && this.existenceFilterCount == existenceFilterMismatchInfo.existenceFilterCount() && this.projectId.equals(existenceFilterMismatchInfo.projectId()) && this.databaseId.equals(existenceFilterMismatchInfo.databaseId())) {
            TestingHooks.ExistenceFilterBloomFilterInfo existenceFilterBloomFilterInfo = this.bloomFilter;
            if (existenceFilterBloomFilterInfo == null) {
                if (existenceFilterMismatchInfo.bloomFilter() == null) {
                    return true;
                }
            } else if (existenceFilterBloomFilterInfo.equals(existenceFilterMismatchInfo.bloomFilter())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((((this.localCacheCount ^ 1000003) * 1000003) ^ this.existenceFilterCount) * 1000003) ^ this.projectId.hashCode()) * 1000003) ^ this.databaseId.hashCode()) * 1000003;
        TestingHooks.ExistenceFilterBloomFilterInfo existenceFilterBloomFilterInfo = this.bloomFilter;
        return hashCode ^ (existenceFilterBloomFilterInfo == null ? 0 : existenceFilterBloomFilterInfo.hashCode());
    }
}
