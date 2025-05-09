package com.google.firebase.firestore.model;

import com.google.firebase.Timestamp;

/* loaded from: classes4.dex */
public final class SnapshotVersion implements Comparable<SnapshotVersion> {
    public static final SnapshotVersion NONE = new SnapshotVersion(new Timestamp(0, 0));
    private final Timestamp timestamp;

    public SnapshotVersion(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    @Override // java.lang.Comparable
    public int compareTo(SnapshotVersion snapshotVersion) {
        return this.timestamp.compareTo(snapshotVersion.timestamp);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SnapshotVersion) && compareTo((SnapshotVersion) obj) == 0;
    }

    public int hashCode() {
        return getTimestamp().hashCode();
    }

    public String toString() {
        return "SnapshotVersion(seconds=" + this.timestamp.getSeconds() + ", nanos=" + this.timestamp.getNanoseconds() + ")";
    }
}
