package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.FieldIndex;
import java.util.Objects;

/* loaded from: classes4.dex */
final class AutoValue_FieldIndex_IndexOffset extends FieldIndex.IndexOffset {
    private final DocumentKey documentKey;
    private final int largestBatchId;
    private final SnapshotVersion readTime;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_FieldIndex_IndexOffset(SnapshotVersion snapshotVersion, DocumentKey documentKey, int i) {
        Objects.requireNonNull(snapshotVersion, "Null readTime");
        this.readTime = snapshotVersion;
        Objects.requireNonNull(documentKey, "Null documentKey");
        this.documentKey = documentKey;
        this.largestBatchId = i;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex.IndexOffset
    public SnapshotVersion getReadTime() {
        return this.readTime;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex.IndexOffset
    public DocumentKey getDocumentKey() {
        return this.documentKey;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex.IndexOffset
    public int getLargestBatchId() {
        return this.largestBatchId;
    }

    public String toString() {
        return "IndexOffset{readTime=" + this.readTime + ", documentKey=" + this.documentKey + ", largestBatchId=" + this.largestBatchId + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldIndex.IndexOffset)) {
            return false;
        }
        FieldIndex.IndexOffset indexOffset = (FieldIndex.IndexOffset) obj;
        return this.readTime.equals(indexOffset.getReadTime()) && this.documentKey.equals(indexOffset.getDocumentKey()) && this.largestBatchId == indexOffset.getLargestBatchId();
    }

    public int hashCode() {
        return ((((this.readTime.hashCode() ^ 1000003) * 1000003) ^ this.documentKey.hashCode()) * 1000003) ^ this.largestBatchId;
    }
}
