package io.flutter.plugins.firebase.dynamiclinks;

import android.net.Uri;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class Utils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> getExceptionDetails(Exception exc) {
        HashMap hashMap = new HashMap();
        hashMap.put("code", "unknown");
        if (exc != null) {
            hashMap.put("message", exc.getMessage());
        } else {
            hashMap.put("message", "An unknown error has occurred.");
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> getMapFromPendingDynamicLinkData(PendingDynamicLinkData pendingDynamicLinkData) {
        if (pendingDynamicLinkData == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Uri link = pendingDynamicLinkData.getLink();
        hashMap.put("link", link != null ? link.toString() : null);
        HashMap hashMap2 = new HashMap();
        for (String str : pendingDynamicLinkData.getUtmParameters().keySet()) {
            hashMap2.put(str, pendingDynamicLinkData.getUtmParameters().get(str).toString());
        }
        hashMap.put("utmParameters", hashMap2);
        HashMap hashMap3 = new HashMap();
        hashMap3.put("clickTimestamp", Long.valueOf(pendingDynamicLinkData.getClickTimestamp()));
        hashMap3.put("minimumVersion", Integer.valueOf(pendingDynamicLinkData.getMinimumAppVersion()));
        hashMap.put("android", hashMap3);
        return hashMap;
    }
}
