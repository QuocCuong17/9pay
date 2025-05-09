package com.google.firebase.storage.network;

import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;

/* loaded from: classes4.dex */
abstract class ResumableNetworkRequest extends NetworkRequest {
    static final String COMMAND = "X-Goog-Upload-Command";
    static final String CONTENT_TYPE = "X-Goog-Upload-Header-Content-Type";
    static final String OFFSET = "X-Goog-Upload-Offset";
    static final String PROTOCOL = "X-Goog-Upload-Protocol";

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResumableNetworkRequest(StorageReferenceUri storageReferenceUri, FirebaseApp firebaseApp) {
        super(storageReferenceUri, firebaseApp);
    }
}
