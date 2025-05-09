package com.pichillilorenzo.flutter_inappwebview_android.types;

import android.net.http.SslCertificate;
import android.net.http.SslError;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class SslErrorExt extends SslError {
    private SslErrorExt(int i, SslCertificate sslCertificate, String str) {
        super(i, sslCertificate, str);
    }

    public static Map<String, Object> toMap(SslError sslError) {
        if (sslError == null) {
            return null;
        }
        int primaryError = sslError.getPrimaryError();
        String str = primaryError != 0 ? primaryError != 1 ? primaryError != 2 ? primaryError != 3 ? primaryError != 4 ? primaryError != 5 ? null : "A generic error occurred" : "The date of the certificate is invalid" : "The certificate authority is not trusted" : "Hostname mismatch" : "The certificate has expired" : "The certificate is not yet valid";
        HashMap hashMap = new HashMap();
        hashMap.put("code", primaryError >= 0 ? Integer.valueOf(primaryError) : null);
        hashMap.put("message", str);
        return hashMap;
    }
}
