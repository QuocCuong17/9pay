package com.baseflow.permissionhandler;

import android.app.Activity;
import android.content.Context;
import com.baseflow.permissionhandler.AppSettingsManager;
import com.baseflow.permissionhandler.PermissionManager;
import com.baseflow.permissionhandler.ServiceManager;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes2.dex */
final class MethodCallHandlerImpl implements MethodChannel.MethodCallHandler {
    private Activity activity;
    private PermissionManager.ActivityRegistry activityRegistry;
    private final AppSettingsManager appSettingsManager;
    private final Context applicationContext;
    private final PermissionManager permissionManager;
    private PermissionManager.PermissionRegistry permissionRegistry;
    private final ServiceManager serviceManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MethodCallHandlerImpl(Context context, AppSettingsManager appSettingsManager, PermissionManager permissionManager, ServiceManager serviceManager) {
        this.applicationContext = context;
        this.appSettingsManager = appSettingsManager;
        this.permissionManager = permissionManager;
        this.serviceManager = serviceManager;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setActivityRegistry(PermissionManager.ActivityRegistry activityRegistry) {
        this.activityRegistry = activityRegistry;
    }

    public void setPermissionRegistry(PermissionManager.PermissionRegistry permissionRegistry) {
        this.permissionRegistry = permissionRegistry;
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, final MethodChannel.Result result) {
        String str = methodCall.method;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1544053025:
                if (str.equals("checkServiceStatus")) {
                    c = 0;
                    break;
                }
                break;
            case -1017315255:
                if (str.equals("shouldShowRequestPermissionRationale")) {
                    c = 1;
                    break;
                }
                break;
            case -576207927:
                if (str.equals("checkPermissionStatus")) {
                    c = 2;
                    break;
                }
                break;
            case 347240634:
                if (str.equals("openAppSettings")) {
                    c = 3;
                    break;
                }
                break;
            case 1669188213:
                if (str.equals("requestPermissions")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                int parseInt = Integer.parseInt(methodCall.arguments.toString());
                ServiceManager serviceManager = this.serviceManager;
                Context context = this.applicationContext;
                Objects.requireNonNull(result);
                serviceManager.checkServiceStatus(parseInt, context, new ServiceManager.SuccessCallback() { // from class: com.baseflow.permissionhandler.MethodCallHandlerImpl$$ExternalSyntheticLambda9
                    @Override // com.baseflow.permissionhandler.ServiceManager.SuccessCallback
                    public final void onSuccess(int i) {
                        MethodChannel.Result.this.success(Integer.valueOf(i));
                    }
                }, new ErrorCallback() { // from class: com.baseflow.permissionhandler.MethodCallHandlerImpl$$ExternalSyntheticLambda1
                    @Override // com.baseflow.permissionhandler.ErrorCallback
                    public final void onError(String str2, String str3) {
                        MethodChannel.Result.this.error(str2, str3, null);
                    }
                });
                return;
            case 1:
                int parseInt2 = Integer.parseInt(methodCall.arguments.toString());
                PermissionManager permissionManager = this.permissionManager;
                Activity activity = this.activity;
                Objects.requireNonNull(result);
                permissionManager.shouldShowRequestPermissionRationale(parseInt2, activity, new PermissionManager.ShouldShowRequestPermissionRationaleSuccessCallback() { // from class: com.baseflow.permissionhandler.MethodCallHandlerImpl$$ExternalSyntheticLambda8
                    @Override // com.baseflow.permissionhandler.PermissionManager.ShouldShowRequestPermissionRationaleSuccessCallback
                    public final void onSuccess(boolean z) {
                        MethodChannel.Result.this.success(Boolean.valueOf(z));
                    }
                }, new ErrorCallback() { // from class: com.baseflow.permissionhandler.MethodCallHandlerImpl$$ExternalSyntheticLambda4
                    @Override // com.baseflow.permissionhandler.ErrorCallback
                    public final void onError(String str2, String str3) {
                        MethodChannel.Result.this.error(str2, str3, null);
                    }
                });
                return;
            case 2:
                int parseInt3 = Integer.parseInt(methodCall.arguments.toString());
                PermissionManager permissionManager2 = this.permissionManager;
                Context context2 = this.applicationContext;
                Activity activity2 = this.activity;
                Objects.requireNonNull(result);
                permissionManager2.checkPermissionStatus(parseInt3, context2, activity2, new PermissionManager.CheckPermissionsSuccessCallback() { // from class: com.baseflow.permissionhandler.MethodCallHandlerImpl$$ExternalSyntheticLambda6
                    @Override // com.baseflow.permissionhandler.PermissionManager.CheckPermissionsSuccessCallback
                    public final void onSuccess(int i) {
                        MethodChannel.Result.this.success(Integer.valueOf(i));
                    }
                }, new ErrorCallback() { // from class: com.baseflow.permissionhandler.MethodCallHandlerImpl$$ExternalSyntheticLambda2
                    @Override // com.baseflow.permissionhandler.ErrorCallback
                    public final void onError(String str2, String str3) {
                        MethodChannel.Result.this.error(str2, str3, null);
                    }
                });
                return;
            case 3:
                AppSettingsManager appSettingsManager = this.appSettingsManager;
                Context context3 = this.applicationContext;
                Objects.requireNonNull(result);
                appSettingsManager.openAppSettings(context3, new AppSettingsManager.OpenAppSettingsSuccessCallback() { // from class: com.baseflow.permissionhandler.MethodCallHandlerImpl$$ExternalSyntheticLambda0
                    @Override // com.baseflow.permissionhandler.AppSettingsManager.OpenAppSettingsSuccessCallback
                    public final void onSuccess(boolean z) {
                        MethodChannel.Result.this.success(Boolean.valueOf(z));
                    }
                }, new ErrorCallback() { // from class: com.baseflow.permissionhandler.MethodCallHandlerImpl$$ExternalSyntheticLambda5
                    @Override // com.baseflow.permissionhandler.ErrorCallback
                    public final void onError(String str2, String str3) {
                        MethodChannel.Result.this.error(str2, str3, null);
                    }
                });
                return;
            case 4:
                List<Integer> list = (List) methodCall.arguments();
                PermissionManager permissionManager3 = this.permissionManager;
                Activity activity3 = this.activity;
                PermissionManager.ActivityRegistry activityRegistry = this.activityRegistry;
                PermissionManager.PermissionRegistry permissionRegistry = this.permissionRegistry;
                Objects.requireNonNull(result);
                permissionManager3.requestPermissions(list, activity3, activityRegistry, permissionRegistry, new PermissionManager.RequestPermissionsSuccessCallback() { // from class: com.baseflow.permissionhandler.MethodCallHandlerImpl$$ExternalSyntheticLambda7
                    @Override // com.baseflow.permissionhandler.PermissionManager.RequestPermissionsSuccessCallback
                    public final void onSuccess(Map map) {
                        MethodChannel.Result.this.success(map);
                    }
                }, new ErrorCallback() { // from class: com.baseflow.permissionhandler.MethodCallHandlerImpl$$ExternalSyntheticLambda3
                    @Override // com.baseflow.permissionhandler.ErrorCallback
                    public final void onError(String str2, String str3) {
                        MethodChannel.Result.this.error(str2, str3, null);
                    }
                });
                return;
            default:
                result.notImplemented();
                return;
        }
    }
}
