package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.FieldIndex;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
final class AutoValue_FieldIndex extends FieldIndex {
    private final String collectionGroup;
    private final int indexId;
    private final FieldIndex.IndexState indexState;
    private final List<FieldIndex.Segment> segments;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_FieldIndex(int i, String str, List<FieldIndex.Segment> list, FieldIndex.IndexState indexState) {
        this.indexId = i;
        Objects.requireNonNull(str, "Null collectionGroup");
        this.collectionGroup = str;
        Objects.requireNonNull(list, "Null segments");
        this.segments = list;
        Objects.requireNonNull(indexState, "Null indexState");
        this.indexState = indexState;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex
    public int getIndexId() {
        return this.indexId;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex
    public String getCollectionGroup() {
        return this.collectionGroup;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex
    public List<FieldIndex.Segment> getSegments() {
        return this.segments;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex
    public FieldIndex.IndexState getIndexState() {
        return this.indexState;
    }

    public String toString() {
        return "FieldIndex{indexId=" + this.indexId + ", collectionGroup=" + this.collectionGroup + ", segments=" + this.segments + ", indexState=" + this.indexState + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldIndex)) {
            return false;
        }
        FieldIndex fieldIndex = (FieldIndex) obj;
        return this.indexId == fieldIndex.getIndexId() && this.collectionGroup.equals(fieldIndex.getCollectionGroup()) && this.segments.equals(fieldIndex.getSegments()) && this.indexState.equals(fieldIndex.getIndexState());
    }

    public int hashCode() {
        return ((((((this.indexId ^ 1000003) * 1000003) ^ this.collectionGroup.hashCode()) * 1000003) ^ this.segments.hashCode()) * 1000003) ^ this.indexState.hashCode();
    }
}
