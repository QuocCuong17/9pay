package io.grpc.okhttp;

import io.grpc.okhttp.internal.framed.Settings;

/* loaded from: classes5.dex */
class OkHttpSettingsUtil {
    public static final int ENABLE_PUSH = 2;
    public static final int INITIAL_WINDOW_SIZE = 7;
    public static final int MAX_CONCURRENT_STREAMS = 4;
    public static final int MAX_HEADER_LIST_SIZE = 6;

    OkHttpSettingsUtil() {
    }

    public static boolean isSet(Settings settings, int i) {
        return settings.isSet(i);
    }

    public static int get(Settings settings, int i) {
        return settings.get(i);
    }

    public static void set(Settings settings, int i, int i2) {
        settings.set(i, 0, i2);
    }
}
