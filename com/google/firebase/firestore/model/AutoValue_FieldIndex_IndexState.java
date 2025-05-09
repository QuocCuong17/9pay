package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.FieldIndex;
import java.util.Objects;

/* loaded from: classes4.dex */
final class AutoValue_FieldIndex_IndexState extends FieldIndex.IndexState {
    private final FieldIndex.IndexOffset offset;
    private final long sequenceNumber;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_FieldIndex_IndexState(long j, FieldIndex.IndexOffset indexOffset) {
        this.sequenceNumber = j;
        Objects.requireNonNull(indexOffset, "Null offset");
        this.offset = indexOffset;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex.IndexState
    public long getSequenceNumber() {
        return this.sequenceNumber;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex.IndexState
    public FieldIndex.IndexOffset getOffset() {
        return this.offset;
    }

    public String toString() {
        return "IndexState{sequenceNumber=" + this.sequenceNumber + ", offset=" + this.offset + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldIndex.IndexState)) {
            return false;
        }
        FieldIndex.IndexState indexState = (FieldIndex.IndexState) obj;
        return this.sequenceNumber == indexState.getSequenceNumber() && this.offset.equals(indexState.getOffset());
    }

    public int hashCode() {
        long j = this.sequenceNumber;
        return ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.offset.hashCode();
    }
}
