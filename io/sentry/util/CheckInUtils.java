package io.sentry.util;

import java.util.List;

/* loaded from: classes5.dex */
public final class CheckInUtils {
    public static boolean isIgnored(List<String> list, String str) {
        if (list != null && !list.isEmpty()) {
            for (String str2 : list) {
                if (str2.equalsIgnoreCase(str)) {
                    return true;
                }
                if (str.matches(str2)) {
                    return true;
                }
            }
        }
        return false;
    }
}
