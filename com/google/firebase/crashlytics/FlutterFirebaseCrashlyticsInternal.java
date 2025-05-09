package com.google.firebase.crashlytics;

import com.google.firebase.crashlytics.internal.Logger;

/* loaded from: classes4.dex */
public final class FlutterFirebaseCrashlyticsInternal {
    private static final String FLUTTER_BUILD_ID_KEY = "com.crashlytics.flutter.build-id.0";

    public static void recordFatalException(Throwable th) {
        if (th == null) {
            Logger.getLogger().w("A null value was passed to recordFatalException. Ignoring.");
        } else {
            FirebaseCrashlytics.getInstance().core.logFatalException(th);
        }
    }

    public static void setFlutterBuildId(String str) {
        FirebaseCrashlytics.getInstance().core.setInternalKey(FLUTTER_BUILD_ID_KEY, str);
    }

    private FlutterFirebaseCrashlyticsInternal() {
    }
}
