package com.google.firebase.firestore.util;

import android.util.Log;
import com.google.firebase.firestore.BuildConfig;

/* loaded from: classes4.dex */
public class Logger {
    private static Level logLevel = Level.WARN;

    /* loaded from: classes4.dex */
    public enum Level {
        DEBUG,
        WARN,
        NONE
    }

    public static void setLogLevel(Level level) {
        logLevel = level;
    }

    public static boolean isDebugEnabled() {
        return logLevel.ordinal() >= Level.DEBUG.ordinal();
    }

    private static void doLog(Level level, String str, String str2, Object... objArr) {
        if (level.ordinal() >= logLevel.ordinal()) {
            String str3 = String.format("(%s) [%s]: ", BuildConfig.VERSION_NAME, str) + String.format(str2, objArr);
            int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$util$Logger$Level[level.ordinal()];
            if (i == 1) {
                Log.i("Firestore", str3);
            } else if (i == 2) {
                Log.w("Firestore", str3);
            } else if (i == 3) {
                throw new IllegalStateException("Trying to log something on level NONE");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.firebase.firestore.util.Logger$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$util$Logger$Level;

        static {
            int[] iArr = new int[Level.values().length];
            $SwitchMap$com$google$firebase$firestore$util$Logger$Level = iArr;
            try {
                iArr[Level.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$util$Logger$Level[Level.WARN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$util$Logger$Level[Level.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static void warn(String str, String str2, Object... objArr) {
        doLog(Level.WARN, str, str2, objArr);
    }

    public static void debug(String str, String str2, Object... objArr) {
        doLog(Level.DEBUG, str, str2, objArr);
    }
}
