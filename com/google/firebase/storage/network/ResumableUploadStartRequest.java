package com.google.firebase.storage.network;

import android.net.Uri;
import android.text.TextUtils;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class ResumableUploadStartRequest extends ResumableNetworkRequest {
    private final String contentType;
    private final JSONObject metadata;

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return "POST";
    }

    public ResumableUploadStartRequest(StorageReferenceUri storageReferenceUri, FirebaseApp firebaseApp, JSONObject jSONObject, String str) {
        super(storageReferenceUri, firebaseApp);
        this.metadata = jSONObject;
        this.contentType = str;
        if (TextUtils.isEmpty(str)) {
            this.mException = new IllegalArgumentException("mContentType is null or empty");
        }
        super.setCustomHeader("X-Goog-Upload-Protocol", "resumable");
        super.setCustomHeader("X-Goog-Upload-Command", "start");
        super.setCustomHeader("X-Goog-Upload-Header-Content-Type", str);
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    public Uri getURL() {
        String authority = getStorageReferenceUri().getGsUri().getAuthority();
        Uri.Builder buildUpon = getStorageReferenceUri().getHttpBaseUri().buildUpon();
        buildUpon.appendPath("b");
        buildUpon.appendPath(authority);
        buildUpon.appendPath("o");
        return buildUpon.build();
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected Map<String, String> getQueryParameters() {
        HashMap hashMap = new HashMap();
        hashMap.put("name", getPathWithoutBucket());
        hashMap.put("uploadType", "resumable");
        return hashMap;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected JSONObject getOutputJSON() {
        return this.metadata;
    }
}
