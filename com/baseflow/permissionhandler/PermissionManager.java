package com.baseflow.permissionhandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import io.flutter.plugin.common.PluginRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class PermissionManager {
    private boolean ongoing = false;

    @FunctionalInterface
    /* loaded from: classes2.dex */
    interface ActivityRegistry {
        void addListener(PluginRegistry.ActivityResultListener activityResultListener);
    }

    @FunctionalInterface
    /* loaded from: classes2.dex */
    interface CheckPermissionsSuccessCallback {
        void onSuccess(int i);
    }

    @FunctionalInterface
    /* loaded from: classes2.dex */
    interface PermissionRegistry {
        void addListener(PluginRegistry.RequestPermissionsResultListener requestPermissionsResultListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @FunctionalInterface
    /* loaded from: classes2.dex */
    public interface RequestPermissionsSuccessCallback {
        void onSuccess(Map<Integer, Integer> map);
    }

    @FunctionalInterface
    /* loaded from: classes2.dex */
    interface ShouldShowRequestPermissionRationaleSuccessCallback {
        void onSuccess(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void checkPermissionStatus(int i, Context context, Activity activity, CheckPermissionsSuccessCallback checkPermissionsSuccessCallback, ErrorCallback errorCallback) {
        checkPermissionsSuccessCallback.onSuccess(determinePermissionStatus(i, context, activity));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void requestPermissions(List<Integer> list, Activity activity, ActivityRegistry activityRegistry, PermissionRegistry permissionRegistry, final RequestPermissionsSuccessCallback requestPermissionsSuccessCallback, ErrorCallback errorCallback) {
        if (this.ongoing) {
            errorCallback.onError("PermissionHandler.PermissionManager", "A request for permissions is already running, please wait for it to finish before doing another request (note that you can request multiple permissions at the same time).");
            return;
        }
        if (activity == null) {
            Log.d("permissions_handler", "Unable to detect current Activity.");
            errorCallback.onError("PermissionHandler.PermissionManager", "Unable to detect current Android Activity.");
            return;
        }
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (Integer num : list) {
            if (determinePermissionStatus(num.intValue(), activity, activity) == 1) {
                if (!hashMap.containsKey(num)) {
                    hashMap.put(num, 1);
                }
            } else {
                List<String> manifestNames = PermissionUtils.getManifestNames(activity, num.intValue());
                if (manifestNames == null || manifestNames.isEmpty()) {
                    if (!hashMap.containsKey(num)) {
                        if (num.intValue() == 16 && Build.VERSION.SDK_INT < 23) {
                            hashMap.put(num, 2);
                        } else {
                            hashMap.put(num, 0);
                        }
                    }
                } else if (Build.VERSION.SDK_INT >= 23 && num.intValue() == 16) {
                    activityRegistry.addListener(new ActivityResultListener(requestPermissionsSuccessCallback));
                    String packageName = activity.getPackageName();
                    Intent intent = new Intent();
                    intent.setAction("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
                    intent.setData(Uri.parse("package:" + packageName));
                    activity.startActivityForResult(intent, 5672353);
                } else {
                    arrayList.addAll(manifestNames);
                }
            }
        }
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        if (arrayList.size() > 0) {
            permissionRegistry.addListener(new RequestPermissionsListener(activity, hashMap, new RequestPermissionsSuccessCallback() { // from class: com.baseflow.permissionhandler.PermissionManager$$ExternalSyntheticLambda0
                @Override // com.baseflow.permissionhandler.PermissionManager.RequestPermissionsSuccessCallback
                public final void onSuccess(Map map) {
                    PermissionManager.this.m569x52cb2ef0(requestPermissionsSuccessCallback, map);
                }
            }));
            this.ongoing = true;
            ActivityCompat.requestPermissions(activity, strArr, 24);
        } else {
            this.ongoing = false;
            if (hashMap.size() > 0) {
                requestPermissionsSuccessCallback.onSuccess(hashMap);
            }
        }
    }

    /* renamed from: lambda$requestPermissions$0$com-baseflow-permissionhandler-PermissionManager, reason: not valid java name */
    public /* synthetic */ void m569x52cb2ef0(RequestPermissionsSuccessCallback requestPermissionsSuccessCallback, Map map) {
        this.ongoing = false;
        requestPermissionsSuccessCallback.onSuccess(map);
    }

    private int determinePermissionStatus(int i, Context context, Activity activity) {
        if (i == 17) {
            return checkNotificationPermissionStatus(context);
        }
        if (i == 21) {
            return checkBluetoothPermissionStatus(context);
        }
        List<String> manifestNames = PermissionUtils.getManifestNames(context, i);
        if (manifestNames == null) {
            Log.d("permissions_handler", "No android specific permissions needed for: " + i);
            return 1;
        }
        if (manifestNames.size() == 0) {
            Log.d("permissions_handler", "No permissions found in manifest for: " + i);
            return (i != 16 || Build.VERSION.SDK_INT >= 23) ? 0 : 2;
        }
        boolean z = context.getApplicationInfo().targetSdkVersion >= 23;
        for (String str : manifestNames) {
            if (z) {
                if (i == 16) {
                    String packageName = context.getPackageName();
                    PowerManager powerManager = (PowerManager) context.getSystemService("power");
                    if (Build.VERSION.SDK_INT >= 23) {
                        return (powerManager == null || !powerManager.isIgnoringBatteryOptimizations(packageName)) ? 0 : 1;
                    }
                    return 2;
                }
                if (ContextCompat.checkSelfPermission(context, str) != 0) {
                    return 0;
                }
            }
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void shouldShowRequestPermissionRationale(int i, Activity activity, ShouldShowRequestPermissionRationaleSuccessCallback shouldShowRequestPermissionRationaleSuccessCallback, ErrorCallback errorCallback) {
        if (activity == null) {
            Log.d("permissions_handler", "Unable to detect current Activity.");
            errorCallback.onError("PermissionHandler.PermissionManager", "Unable to detect current Android Activity.");
            return;
        }
        List<String> manifestNames = PermissionUtils.getManifestNames(activity, i);
        if (manifestNames == null) {
            Log.d("permissions_handler", "No android specific permissions needed for: " + i);
            shouldShowRequestPermissionRationaleSuccessCallback.onSuccess(false);
            return;
        }
        if (manifestNames.isEmpty()) {
            Log.d("permissions_handler", "No permissions found in manifest for: " + i + " no need to show request rationale");
            shouldShowRequestPermissionRationaleSuccessCallback.onSuccess(false);
            return;
        }
        shouldShowRequestPermissionRationaleSuccessCallback.onSuccess(ActivityCompat.shouldShowRequestPermissionRationale(activity, manifestNames.get(0)));
    }

    private int checkNotificationPermissionStatus(Context context) {
        return NotificationManagerCompat.from(context).areNotificationsEnabled() ? 1 : 0;
    }

    private int checkBluetoothPermissionStatus(Context context) {
        List<String> manifestNames = PermissionUtils.getManifestNames(context, 21);
        if (!(manifestNames == null || manifestNames.isEmpty())) {
            return 1;
        }
        Log.d("permissions_handler", "Bluetooth permission missing in manifest");
        return 0;
    }

    /* loaded from: classes2.dex */
    static final class ActivityResultListener implements PluginRegistry.ActivityResultListener {
        boolean alreadyCalled = false;
        final RequestPermissionsSuccessCallback callback;

        ActivityResultListener(RequestPermissionsSuccessCallback requestPermissionsSuccessCallback) {
            this.callback = requestPermissionsSuccessCallback;
        }

        @Override // io.flutter.plugin.common.PluginRegistry.ActivityResultListener
        public boolean onActivityResult(int i, int i2, Intent intent) {
            if (this.alreadyCalled || i != 5672353) {
                return false;
            }
            this.alreadyCalled = true;
            int i3 = i2 == -1 ? 1 : 0;
            HashMap hashMap = new HashMap();
            hashMap.put(16, Integer.valueOf(i3));
            this.callback.onSuccess(hashMap);
            return true;
        }
    }

    /* loaded from: classes2.dex */
    static final class RequestPermissionsListener implements PluginRegistry.RequestPermissionsResultListener {
        final Activity activity;
        boolean alreadyCalled = false;
        final RequestPermissionsSuccessCallback callback;
        final Map<Integer, Integer> requestResults;

        RequestPermissionsListener(Activity activity, Map<Integer, Integer> map, RequestPermissionsSuccessCallback requestPermissionsSuccessCallback) {
            this.activity = activity;
            this.callback = requestPermissionsSuccessCallback;
            this.requestResults = map;
        }

        @Override // io.flutter.plugin.common.PluginRegistry.RequestPermissionsResultListener
        public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
            if (this.alreadyCalled || i != 24) {
                return false;
            }
            this.alreadyCalled = true;
            for (int i2 = 0; i2 < strArr.length; i2++) {
                String str = strArr[i2];
                int parseManifestName = PermissionUtils.parseManifestName(str);
                if (parseManifestName != 20) {
                    int i3 = iArr[i2];
                    if (parseManifestName == 7) {
                        if (!this.requestResults.containsKey(7)) {
                            this.requestResults.put(7, Integer.valueOf(PermissionUtils.toPermissionStatus(this.activity, str, i3)));
                        }
                        if (!this.requestResults.containsKey(14)) {
                            this.requestResults.put(14, Integer.valueOf(PermissionUtils.toPermissionStatus(this.activity, str, i3)));
                        }
                    } else if (parseManifestName == 4) {
                        int permissionStatus = PermissionUtils.toPermissionStatus(this.activity, str, i3);
                        if (!this.requestResults.containsKey(4)) {
                            this.requestResults.put(4, Integer.valueOf(permissionStatus));
                        }
                    } else if (parseManifestName == 3) {
                        int permissionStatus2 = PermissionUtils.toPermissionStatus(this.activity, str, i3);
                        if (Build.VERSION.SDK_INT < 29 && !this.requestResults.containsKey(4)) {
                            this.requestResults.put(4, Integer.valueOf(permissionStatus2));
                        }
                        if (!this.requestResults.containsKey(5)) {
                            this.requestResults.put(5, Integer.valueOf(permissionStatus2));
                        }
                        this.requestResults.put(Integer.valueOf(parseManifestName), Integer.valueOf(permissionStatus2));
                    } else if (!this.requestResults.containsKey(Integer.valueOf(parseManifestName))) {
                        this.requestResults.put(Integer.valueOf(parseManifestName), Integer.valueOf(PermissionUtils.toPermissionStatus(this.activity, str, i3)));
                    }
                    PermissionUtils.updatePermissionShouldShowStatus(this.activity, parseManifestName);
                }
            }
            this.callback.onSuccess(this.requestResults);
            return true;
        }
    }
}
