package com.google.firebase.storage.network;

import android.net.Uri;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;
import com.tekartik.sqflite.Constant;

/* loaded from: classes4.dex */
public class ResumableUploadCancelRequest extends ResumableNetworkRequest {
    public static boolean cancelCalled = false;
    private final Uri uploadURL;

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return "POST";
    }

    public ResumableUploadCancelRequest(StorageReferenceUri storageReferenceUri, FirebaseApp firebaseApp, Uri uri) {
        super(storageReferenceUri, firebaseApp);
        cancelCalled = true;
        this.uploadURL = uri;
        super.setCustomHeader("X-Goog-Upload-Protocol", "resumable");
        super.setCustomHeader("X-Goog-Upload-Command", Constant.PARAM_CANCEL);
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    public Uri getURL() {
        return this.uploadURL;
    }
}
