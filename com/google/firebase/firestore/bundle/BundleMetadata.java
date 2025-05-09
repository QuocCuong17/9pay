package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.model.SnapshotVersion;

/* loaded from: classes4.dex */
public class BundleMetadata implements BundleElement {
    private final String bundleId;
    private final SnapshotVersion createTime;
    private final int schemaVersion;
    private final long totalBytes;
    private final int totalDocuments;

    public BundleMetadata(String str, int i, SnapshotVersion snapshotVersion, int i2, long j) {
        this.bundleId = str;
        this.schemaVersion = i;
        this.createTime = snapshotVersion;
        this.totalDocuments = i2;
        this.totalBytes = j;
    }

    public String getBundleId() {
        return this.bundleId;
    }

    public int getSchemaVersion() {
        return this.schemaVersion;
    }

    public SnapshotVersion getCreateTime() {
        return this.createTime;
    }

    public int getTotalDocuments() {
        return this.totalDocuments;
    }

    public long getTotalBytes() {
        return this.totalBytes;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BundleMetadata bundleMetadata = (BundleMetadata) obj;
        if (this.schemaVersion == bundleMetadata.schemaVersion && this.totalDocuments == bundleMetadata.totalDocuments && this.totalBytes == bundleMetadata.totalBytes && this.bundleId.equals(bundleMetadata.bundleId)) {
            return this.createTime.equals(bundleMetadata.createTime);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((((this.bundleId.hashCode() * 31) + this.schemaVersion) * 31) + this.totalDocuments) * 31;
        long j = this.totalBytes;
        return ((hashCode + ((int) (j ^ (j >>> 32)))) * 31) + this.createTime.hashCode();
    }
}
