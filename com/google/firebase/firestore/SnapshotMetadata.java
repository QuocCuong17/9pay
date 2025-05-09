package com.google.firebase.firestore;

/* loaded from: classes4.dex */
public class SnapshotMetadata {
    private final boolean hasPendingWrites;
    private final boolean isFromCache;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SnapshotMetadata(boolean z, boolean z2) {
        this.hasPendingWrites = z;
        this.isFromCache = z2;
    }

    public boolean hasPendingWrites() {
        return this.hasPendingWrites;
    }

    public boolean isFromCache() {
        return this.isFromCache;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SnapshotMetadata)) {
            return false;
        }
        SnapshotMetadata snapshotMetadata = (SnapshotMetadata) obj;
        return this.hasPendingWrites == snapshotMetadata.hasPendingWrites && this.isFromCache == snapshotMetadata.isFromCache;
    }

    public int hashCode() {
        return ((this.hasPendingWrites ? 1 : 0) * 31) + (this.isFromCache ? 1 : 0);
    }

    public String toString() {
        return "SnapshotMetadata{hasPendingWrites=" + this.hasPendingWrites + ", isFromCache=" + this.isFromCache + '}';
    }
}
