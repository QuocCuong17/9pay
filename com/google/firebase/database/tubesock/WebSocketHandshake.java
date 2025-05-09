package com.google.firebase.database.tubesock;

import android.util.Base64;
import com.google.common.net.HttpHeaders;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;

/* loaded from: classes4.dex */
class WebSocketHandshake {
    private static final String WEBSOCKET_VERSION = "13";
    private Map<String, String> extraHeaders;
    private String nonce;
    private String protocol;
    private URI url;

    public WebSocketHandshake(URI uri, String str, Map<String, String> map) {
        this.url = null;
        this.protocol = null;
        this.nonce = null;
        this.extraHeaders = null;
        this.url = uri;
        this.protocol = str;
        this.extraHeaders = map;
        this.nonce = createNonce();
    }

    public byte[] getHandshake() {
        String path = this.url.getPath();
        String query = this.url.getQuery();
        StringBuilder sb = new StringBuilder();
        sb.append(path);
        sb.append(query == null ? "" : "?" + query);
        String sb2 = sb.toString();
        String host = this.url.getHost();
        if (this.url.getPort() != -1) {
            host = host + ":" + this.url.getPort();
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(HttpHeaders.HOST, host);
        linkedHashMap.put(HttpHeaders.UPGRADE, "websocket");
        linkedHashMap.put("Connection", HttpHeaders.UPGRADE);
        linkedHashMap.put(HttpHeaders.SEC_WEBSOCKET_VERSION, WEBSOCKET_VERSION);
        linkedHashMap.put(HttpHeaders.SEC_WEBSOCKET_KEY, this.nonce);
        String str = this.protocol;
        if (str != null) {
            linkedHashMap.put(HttpHeaders.SEC_WEBSOCKET_PROTOCOL, str);
        }
        Map<String, String> map = this.extraHeaders;
        if (map != null) {
            for (String str2 : map.keySet()) {
                if (!linkedHashMap.containsKey(str2)) {
                    linkedHashMap.put(str2, this.extraHeaders.get(str2));
                }
            }
        }
        byte[] bytes = ((("GET " + sb2 + " HTTP/1.1\r\n") + generateHeader(linkedHashMap)) + IOUtils.LINE_SEPARATOR_WINDOWS).getBytes(Charset.defaultCharset());
        byte[] bArr = new byte[bytes.length];
        System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        return bArr;
    }

    private String generateHeader(LinkedHashMap<String, String> linkedHashMap) {
        String str = new String();
        for (String str2 : linkedHashMap.keySet()) {
            str = str + str2 + ": " + linkedHashMap.get(str2) + IOUtils.LINE_SEPARATOR_WINDOWS;
        }
        return str;
    }

    private String createNonce() {
        byte[] bArr = new byte[16];
        for (int i = 0; i < 16; i++) {
            bArr[i] = (byte) rand(0, 255);
        }
        return Base64.encodeToString(bArr, 2);
    }

    public void verifyServerStatusLine(String str) {
        int parseInt = Integer.parseInt(str.substring(9, 12));
        if (parseInt == 407) {
            throw new WebSocketException("connection failed: proxy authentication not supported");
        }
        if (parseInt == 404) {
            throw new WebSocketException("connection failed: 404 not found");
        }
        if (parseInt == 101) {
            return;
        }
        throw new WebSocketException("connection failed: unknown status code " + parseInt);
    }

    public void verifyServerHandshakeHeaders(HashMap<String, String> hashMap) {
        if (!"websocket".equals(hashMap.get("upgrade"))) {
            throw new WebSocketException("connection failed: missing header field in server handshake: Upgrade");
        }
        if (!"upgrade".equals(hashMap.get("connection"))) {
            throw new WebSocketException("connection failed: missing header field in server handshake: Connection");
        }
    }

    private int rand(int i, int i2) {
        return (int) ((Math.random() * i2) + i);
    }
}
