package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.mutation.FieldMask;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public class OverlayedDocument {

    @Nullable
    private FieldMask mutatedFields;
    private Document overlayedDocument;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OverlayedDocument(Document document, FieldMask fieldMask) {
        this.overlayedDocument = document;
        this.mutatedFields = fieldMask;
    }

    public Document getDocument() {
        return this.overlayedDocument;
    }

    @Nullable
    public FieldMask getMutatedFields() {
        return this.mutatedFields;
    }
}
