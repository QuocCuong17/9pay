package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;

/* loaded from: classes4.dex */
public class BundleDocument implements BundleElement {
    private MutableDocument document;

    public BundleDocument(MutableDocument mutableDocument) {
        this.document = mutableDocument;
    }

    public DocumentKey getKey() {
        return this.document.getKey();
    }

    public MutableDocument getDocument() {
        return this.document;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.document.equals(((BundleDocument) obj).document);
    }

    public int hashCode() {
        return this.document.hashCode();
    }
}
