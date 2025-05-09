package com.google.firebase.storage.network;

import android.net.Uri;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;

/* loaded from: classes4.dex */
public class ResumableUploadQueryRequest extends ResumableNetworkRequest {
    private final Uri uploadURL;

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return "POST";
    }

    public ResumableUploadQueryRequest(StorageReferenceUri storageReferenceUri, FirebaseApp firebaseApp, Uri uri) {
        super(storageReferenceUri, firebaseApp);
        this.uploadURL = uri;
        super.setCustomHeader("X-Goog-Upload-Protocol", "resumable");
        super.setCustomHeader("X-Goog-Upload-Command", "query");
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    public Uri getURL() {
        return this.uploadURL;
    }
}
