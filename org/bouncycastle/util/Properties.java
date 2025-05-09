package org.bouncycastle.util;

import com.facebook.internal.ServerProtocol;
import java.math.BigInteger;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/* loaded from: classes6.dex */
public class Properties {
    private static final ThreadLocal threadProperties = new ThreadLocal();

    private Properties() {
    }

    public static BigInteger asBigInteger(String str) {
        String propertyValue = getPropertyValue(str);
        if (propertyValue != null) {
            return new BigInteger(propertyValue);
        }
        return null;
    }

    public static Set<String> asKeySet(String str) {
        HashSet hashSet = new HashSet();
        String propertyValue = getPropertyValue(str);
        if (propertyValue != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(propertyValue, ",");
            while (stringTokenizer.hasMoreElements()) {
                hashSet.add(Strings.toLowerCase(stringTokenizer.nextToken()).trim());
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public static String getPropertyValue(final String str) {
        return (String) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.bouncycastle.util.Properties.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                Map map = (Map) Properties.threadProperties.get();
                return map != null ? map.get(str) : System.getProperty(str);
            }
        });
    }

    public static boolean isOverrideSet(String str) {
        try {
            String propertyValue = getPropertyValue(str);
            if (propertyValue != null) {
                return ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(Strings.toLowerCase(propertyValue));
            }
        } catch (AccessControlException unused) {
        }
        return false;
    }

    public static boolean removeThreadOverride(String str) {
        boolean isOverrideSet = isOverrideSet(str);
        ThreadLocal threadLocal = threadProperties;
        Map map = (Map) threadLocal.get();
        if (map == null) {
            return false;
        }
        map.remove(str);
        if (map.isEmpty()) {
            threadLocal.remove();
        } else {
            threadLocal.set(map);
        }
        return isOverrideSet;
    }

    public static boolean setThreadOverride(String str, boolean z) {
        boolean isOverrideSet = isOverrideSet(str);
        ThreadLocal threadLocal = threadProperties;
        Map map = (Map) threadLocal.get();
        if (map == null) {
            map = new HashMap();
        }
        map.put(str, z ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
        threadLocal.set(map);
        return isOverrideSet;
    }
}
