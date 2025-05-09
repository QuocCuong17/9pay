package com.google.firebase.storage.internal;

import android.net.Uri;
import com.google.firebase.emulators.EmulatedServiceSettings;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.google.firebase.storage.network.NetworkRequest;

/* loaded from: classes4.dex */
public class StorageReferenceUri {
    private final Uri gsUri;
    private final Uri httpBaseUri;
    private final Uri httpUri;

    public StorageReferenceUri(Uri uri) {
        this(uri, null);
    }

    public StorageReferenceUri(Uri uri, EmulatedServiceSettings emulatedServiceSettings) {
        Uri parse;
        this.gsUri = uri;
        if (emulatedServiceSettings == null) {
            parse = NetworkRequest.PROD_BASE_URL;
        } else {
            parse = Uri.parse("http://" + emulatedServiceSettings.getHost() + ":" + emulatedServiceSettings.getPort() + "/v0");
        }
        this.httpBaseUri = parse;
        Uri.Builder appendEncodedPath = parse.buildUpon().appendPath("b").appendEncodedPath(uri.getAuthority());
        String normalizeSlashes = Slashes.normalizeSlashes(uri.getPath());
        if (normalizeSlashes.length() > 0 && !RemoteSettings.FORWARD_SLASH_STRING.equals(normalizeSlashes)) {
            appendEncodedPath = appendEncodedPath.appendPath("o").appendPath(normalizeSlashes);
        }
        this.httpUri = appendEncodedPath.build();
    }

    public Uri getHttpBaseUri() {
        return this.httpBaseUri;
    }

    public Uri getHttpUri() {
        return this.httpUri;
    }

    public Uri getGsUri() {
        return this.gsUri;
    }
}
