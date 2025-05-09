package com.google.firebase.firestore.index;

import com.google.firebase.firestore.model.DocumentKey;
import java.util.Arrays;
import java.util.Objects;

/* loaded from: classes4.dex */
final class AutoValue_IndexEntry extends IndexEntry {
    private final byte[] arrayValue;
    private final byte[] directionalValue;
    private final DocumentKey documentKey;
    private final int indexId;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_IndexEntry(int i, DocumentKey documentKey, byte[] bArr, byte[] bArr2) {
        this.indexId = i;
        Objects.requireNonNull(documentKey, "Null documentKey");
        this.documentKey = documentKey;
        Objects.requireNonNull(bArr, "Null arrayValue");
        this.arrayValue = bArr;
        Objects.requireNonNull(bArr2, "Null directionalValue");
        this.directionalValue = bArr2;
    }

    @Override // com.google.firebase.firestore.index.IndexEntry
    public int getIndexId() {
        return this.indexId;
    }

    @Override // com.google.firebase.firestore.index.IndexEntry
    public DocumentKey getDocumentKey() {
        return this.documentKey;
    }

    @Override // com.google.firebase.firestore.index.IndexEntry
    public byte[] getArrayValue() {
        return this.arrayValue;
    }

    @Override // com.google.firebase.firestore.index.IndexEntry
    public byte[] getDirectionalValue() {
        return this.directionalValue;
    }

    public String toString() {
        return "IndexEntry{indexId=" + this.indexId + ", documentKey=" + this.documentKey + ", arrayValue=" + Arrays.toString(this.arrayValue) + ", directionalValue=" + Arrays.toString(this.directionalValue) + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IndexEntry)) {
            return false;
        }
        IndexEntry indexEntry = (IndexEntry) obj;
        if (this.indexId == indexEntry.getIndexId() && this.documentKey.equals(indexEntry.getDocumentKey())) {
            boolean z = indexEntry instanceof AutoValue_IndexEntry;
            if (Arrays.equals(this.arrayValue, z ? ((AutoValue_IndexEntry) indexEntry).arrayValue : indexEntry.getArrayValue())) {
                if (Arrays.equals(this.directionalValue, z ? ((AutoValue_IndexEntry) indexEntry).directionalValue : indexEntry.getDirectionalValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((((this.indexId ^ 1000003) * 1000003) ^ this.documentKey.hashCode()) * 1000003) ^ Arrays.hashCode(this.arrayValue)) * 1000003) ^ Arrays.hashCode(this.directionalValue);
    }
}
