package com.google.firebase.storage.network;

import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;

/* loaded from: classes4.dex */
public class DeleteNetworkRequest extends NetworkRequest {
    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return "DELETE";
    }

    public DeleteNetworkRequest(StorageReferenceUri storageReferenceUri, FirebaseApp firebaseApp) {
        super(storageReferenceUri, firebaseApp);
    }
}
