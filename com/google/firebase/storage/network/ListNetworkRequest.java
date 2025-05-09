package com.google.firebase.storage.network;

import android.net.Uri;
import android.text.TextUtils;
import androidx.browser.trusted.sharing.ShareTarget;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.firebase.FirebaseApp;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.google.firebase.storage.internal.StorageReferenceUri;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class ListNetworkRequest extends NetworkRequest {
    private final Integer maxPageSize;
    private final String nextPageToken;

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return ShareTarget.METHOD_GET;
    }

    public ListNetworkRequest(StorageReferenceUri storageReferenceUri, FirebaseApp firebaseApp, Integer num, String str) {
        super(storageReferenceUri, firebaseApp);
        this.maxPageSize = num;
        this.nextPageToken = str;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    public Uri getURL() {
        return Uri.parse(getStorageReferenceUri().getHttpBaseUri() + "/b/" + getStorageReferenceUri().getGsUri().getAuthority() + "/o");
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected Map<String, String> getQueryParameters() {
        HashMap hashMap = new HashMap();
        String pathWithoutBucket = getPathWithoutBucket();
        if (!pathWithoutBucket.isEmpty()) {
            hashMap.put("prefix", pathWithoutBucket + RemoteSettings.FORWARD_SLASH_STRING);
        }
        hashMap.put(TtmlNode.RUBY_DELIMITER, RemoteSettings.FORWARD_SLASH_STRING);
        Integer num = this.maxPageSize;
        if (num != null) {
            hashMap.put("maxResults", Integer.toString(num.intValue()));
        }
        if (!TextUtils.isEmpty(this.nextPageToken)) {
            hashMap.put("pageToken", this.nextPageToken);
        }
        return hashMap;
    }
}
