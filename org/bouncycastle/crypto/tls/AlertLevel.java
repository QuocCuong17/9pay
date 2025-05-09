package org.bouncycastle.crypto.tls;

import io.flutter.plugins.firebase.crashlytics.Constants;

/* loaded from: classes6.dex */
public class AlertLevel {
    public static final short fatal = 2;
    public static final short warning = 1;

    public static String getName(short s) {
        return s != 1 ? s != 2 ? "UNKNOWN" : Constants.FATAL : "warning";
    }

    public static String getText(short s) {
        return getName(s) + "(" + ((int) s) + ")";
    }
}
