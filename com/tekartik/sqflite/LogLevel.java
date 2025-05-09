package com.tekartik.sqflite;

import io.flutter.plugin.common.MethodCall;

/* loaded from: classes5.dex */
public class LogLevel {
    static final int none = 0;
    static final int sql = 1;
    static final int verbose = 2;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasSqlLevel(int i) {
        return i >= 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasVerboseLevel(int i) {
        return i >= 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Integer getLogLevel(MethodCall methodCall) {
        return (Integer) methodCall.argument("logLevel");
    }
}
