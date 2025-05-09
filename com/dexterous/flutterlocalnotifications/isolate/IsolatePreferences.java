package com.dexterous.flutterlocalnotifications.isolate;

import android.content.Context;
import android.content.SharedPreferences;
import io.flutter.view.FlutterCallbackInformation;

/* loaded from: classes2.dex */
public class IsolatePreferences {
    private static final String SHARED_PREFS_FILE_NAME = "flutter_local_notifications_plugin";
    private final String CALLBACK_DISPATCHER_HANDLE_KEY = "com.dexterous.flutterlocalnotifications.CALLBACK_DISPATCHER_HANDLE_KEY";
    private final String CALLBACK_HANDLE_KEY = "com.dexterous.flutterlocalnotifications.CALLBACK_HANDLE_KEY";
    private final Context context;

    public IsolatePreferences(Context context) {
        this.context = context;
    }

    private SharedPreferences get() {
        return this.context.getSharedPreferences(SHARED_PREFS_FILE_NAME, 0);
    }

    public void saveCallbackKeys(Long l, Long l2) {
        get().edit().putLong("com.dexterous.flutterlocalnotifications.CALLBACK_DISPATCHER_HANDLE_KEY", l.longValue()).apply();
        get().edit().putLong("com.dexterous.flutterlocalnotifications.CALLBACK_HANDLE_KEY", l2.longValue()).apply();
    }

    public Long getCallbackDispatcherHandle() {
        return Long.valueOf(get().getLong("com.dexterous.flutterlocalnotifications.CALLBACK_DISPATCHER_HANDLE_KEY", -1L));
    }

    public Long getCallbackHandle() {
        return Long.valueOf(get().getLong("com.dexterous.flutterlocalnotifications.CALLBACK_HANDLE_KEY", -1L));
    }

    public FlutterCallbackInformation lookupDispatcherHandle() {
        return FlutterCallbackInformation.lookupCallbackInformation(getCallbackDispatcherHandle().longValue());
    }
}
