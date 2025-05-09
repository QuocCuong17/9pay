package com.google.firebase.storage.network;

import androidx.browser.trusted.sharing.ShareTarget;
import com.beust.jcommander.Parameters;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes4.dex */
public class GetNetworkRequest extends NetworkRequest {
    private static final String TAG = "GetNetworkRequest";

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return ShareTarget.METHOD_GET;
    }

    public GetNetworkRequest(StorageReferenceUri storageReferenceUri, FirebaseApp firebaseApp, long j) {
        super(storageReferenceUri, firebaseApp);
        if (j != 0) {
            super.setCustomHeader("Range", "bytes=" + j + Parameters.DEFAULT_OPTION_PREFIXES);
        }
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected Map<String, String> getQueryParameters() {
        return Collections.singletonMap("alt", ShareConstants.WEB_DIALOG_PARAM_MEDIA);
    }
}
