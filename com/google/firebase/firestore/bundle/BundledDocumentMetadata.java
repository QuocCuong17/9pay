package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.SnapshotVersion;
import java.util.List;

/* loaded from: classes4.dex */
public class BundledDocumentMetadata implements BundleElement {
    private final boolean exists;
    private final DocumentKey key;
    private final List<String> queries;
    private final SnapshotVersion readTime;

    public BundledDocumentMetadata(DocumentKey documentKey, SnapshotVersion snapshotVersion, boolean z, List<String> list) {
        this.key = documentKey;
        this.readTime = snapshotVersion;
        this.exists = z;
        this.queries = list;
    }

    public DocumentKey getKey() {
        return this.key;
    }

    public SnapshotVersion getReadTime() {
        return this.readTime;
    }

    public boolean exists() {
        return this.exists;
    }

    public List<String> getQueries() {
        return this.queries;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BundledDocumentMetadata bundledDocumentMetadata = (BundledDocumentMetadata) obj;
        if (this.exists == bundledDocumentMetadata.exists && this.key.equals(bundledDocumentMetadata.key) && this.readTime.equals(bundledDocumentMetadata.readTime)) {
            return this.queries.equals(bundledDocumentMetadata.queries);
        }
        return false;
    }

    public int hashCode() {
        return (((((this.key.hashCode() * 31) + this.readTime.hashCode()) * 31) + (this.exists ? 1 : 0)) * 31) + this.queries.hashCode();
    }
}
