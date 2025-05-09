package com.google.firebase.database.connection;

import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.webkit.ProxyConfig;
import java.net.URI;

/* loaded from: classes4.dex */
public class HostInfo {
    private static final String LAST_SESSION_ID_PARAM = "ls";
    private static final String VERSION_PARAM = "v";
    private final String host;
    private final String namespace;
    private final boolean secure;

    public HostInfo(String str, String str2, boolean z) {
        this.host = str;
        this.namespace = str2;
        this.secure = z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ProxyConfig.MATCH_HTTP);
        sb.append(this.secure ? CmcdData.Factory.STREAMING_FORMAT_SS : "");
        sb.append("://");
        sb.append(this.host);
        return sb.toString();
    }

    public static URI getConnectionUrl(String str, boolean z, String str2, String str3) {
        String str4 = (z ? "wss" : "ws") + "://" + str + "/.ws?ns=" + str2 + "&v=5";
        if (str3 != null) {
            str4 = str4 + "&ls=" + str3;
        }
        return URI.create(str4);
    }

    public String getHost() {
        return this.host;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public boolean isSecure() {
        return this.secure;
    }
}
