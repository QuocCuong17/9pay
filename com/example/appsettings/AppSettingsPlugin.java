package com.example.appsettings;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.facebook.gamingservices.cloudgaming.internal.SDKConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.tekartik.sqflite.Constant;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.sentry.protocol.SentryStackFrame;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppSettingsPlugin.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \"2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001\"B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000bH\u0016J\b\u0010\u0011\u001a\u00020\u000bH\u0016J\u0010\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000fH\u0016J\u0018\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0012\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0002J\u001a\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0002J\u001a\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020!2\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/example/appsettings/AppSettingsPlugin;", "Lio/flutter/plugin/common/MethodChannel$MethodCallHandler;", "Lio/flutter/embedding/engine/plugins/FlutterPlugin;", "Lio/flutter/embedding/engine/plugins/activity/ActivityAware;", "registrar", "Lio/flutter/plugin/common/PluginRegistry$Registrar;", "(Lio/flutter/plugin/common/PluginRegistry$Registrar;)V", "()V", "activity", "Landroid/app/Activity;", "onAttachedToActivity", "", "binding", "Lio/flutter/embedding/engine/plugins/activity/ActivityPluginBinding;", "onAttachedToEngine", "Lio/flutter/embedding/engine/plugins/FlutterPlugin$FlutterPluginBinding;", "onDetachedFromActivity", "onDetachedFromActivityForConfigChanges", "onDetachedFromEngine", "onMethodCall", NotificationCompat.CATEGORY_CALL, "Lio/flutter/plugin/common/MethodCall;", Constant.PARAM_RESULT, "Lio/flutter/plugin/common/MethodChannel$Result;", "onReattachedToActivityForConfigChanges", "openAppSettings", "asAnotherTask", "", "openSettings", "url", "", "openSettingsWithCustomIntent", SDKConstants.PARAM_INTENT, "Landroid/content/Intent;", "Companion", "app_settings_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AppSettingsPlugin implements MethodChannel.MethodCallHandler, FlutterPlugin, ActivityAware {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Activity activity;

    @JvmStatic
    public static final void registerWith(PluginRegistry.Registrar registrar) {
        INSTANCE.registerWith(registrar);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
    }

    public AppSettingsPlugin() {
    }

    static /* synthetic */ void openSettings$default(AppSettingsPlugin appSettingsPlugin, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        appSettingsPlugin.openSettings(str, z);
    }

    private final void openSettings(String url, boolean asAnotherTask) {
        try {
            Intent intent = new Intent(url);
            if (asAnotherTask) {
                intent.addFlags(268435456);
            }
            Activity activity = this.activity;
            if (activity == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activity");
                activity = null;
            }
            activity.startActivity(intent);
        } catch (Exception unused) {
            openAppSettings(asAnotherTask);
        }
    }

    static /* synthetic */ void openSettingsWithCustomIntent$default(AppSettingsPlugin appSettingsPlugin, Intent intent, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        appSettingsPlugin.openSettingsWithCustomIntent(intent, z);
    }

    private final void openSettingsWithCustomIntent(Intent intent, boolean asAnotherTask) {
        if (asAnotherTask) {
            try {
                intent.addFlags(268435456);
            } catch (Exception unused) {
                openAppSettings(asAnotherTask);
                return;
            }
        }
        Activity activity = this.activity;
        if (activity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activity");
            activity = null;
        }
        activity.startActivity(intent);
    }

    static /* synthetic */ void openAppSettings$default(AppSettingsPlugin appSettingsPlugin, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        appSettingsPlugin.openAppSettings(z);
    }

    private final void openAppSettings(boolean asAnotherTask) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        if (asAnotherTask) {
            intent.addFlags(268435456);
        }
        Activity activity = this.activity;
        Activity activity2 = null;
        if (activity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activity");
            activity = null;
        }
        intent.setData(Uri.fromParts(SentryStackFrame.JsonKeys.PACKAGE, activity.getPackageName(), null));
        Activity activity3 = this.activity;
        if (activity3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activity");
        } else {
            activity2 = activity3;
        }
        activity2.startActivity(intent);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AppSettingsPlugin(PluginRegistry.Registrar registrar) {
        this();
        Intrinsics.checkNotNullParameter(registrar, "registrar");
    }

    /* compiled from: AppSettingsPlugin.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/example/appsettings/AppSettingsPlugin$Companion;", "", "()V", "registerWith", "", "registrar", "Lio/flutter/plugin/common/PluginRegistry$Registrar;", "app_settings_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void registerWith(PluginRegistry.Registrar registrar) {
            Intrinsics.checkNotNullParameter(registrar, "registrar");
            new MethodChannel(registrar.messenger(), "app_settings").setMethodCallHandler(new AppSettingsPlugin(registrar));
        }
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        new MethodChannel(binding.getBinaryMessenger(), "app_settings").setMethodCallHandler(this);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        Activity activity = binding.getActivity();
        Intrinsics.checkNotNullExpressionValue(activity, "binding.activity");
        this.activity = activity;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        Activity activity = binding.getActivity();
        Intrinsics.checkNotNullExpressionValue(activity, "binding.activity");
        this.activity = activity;
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(result, "result");
        Boolean bool = (Boolean) call.argument("asAnotherTask");
        boolean booleanValue = bool == null ? false : bool.booleanValue();
        if (Intrinsics.areEqual(call.method, "wifi")) {
            openSettings("android.settings.WIFI_SETTINGS", booleanValue);
        } else if (Intrinsics.areEqual(call.method, "wireless")) {
            openSettings("android.settings.WIRELESS_SETTINGS", booleanValue);
        } else if (Intrinsics.areEqual(call.method, FirebaseAnalytics.Param.LOCATION)) {
            openSettings("android.settings.LOCATION_SOURCE_SETTINGS", booleanValue);
        } else if (Intrinsics.areEqual(call.method, "security")) {
            openSettings("android.settings.SECURITY_SETTINGS", booleanValue);
        } else if (Intrinsics.areEqual(call.method, "locksettings")) {
            openSettings("android.app.action.SET_NEW_PASSWORD", booleanValue);
        } else if (Intrinsics.areEqual(call.method, "bluetooth")) {
            openSettings("android.settings.BLUETOOTH_SETTINGS", booleanValue);
        } else if (Intrinsics.areEqual(call.method, "data_roaming")) {
            openSettings("android.settings.DATA_ROAMING_SETTINGS", booleanValue);
        } else if (Intrinsics.areEqual(call.method, WorkflowModule.Properties.Section.Component.Type.DATE)) {
            openSettings("android.settings.DATE_SETTINGS", booleanValue);
        } else if (Intrinsics.areEqual(call.method, "display")) {
            openSettings("android.settings.DISPLAY_SETTINGS", booleanValue);
        } else {
            Activity activity = null;
            if (Intrinsics.areEqual(call.method, "notification")) {
                if (Build.VERSION.SDK_INT >= 26) {
                    Intent intent = new Intent("android.settings.APP_NOTIFICATION_SETTINGS");
                    Activity activity2 = this.activity;
                    if (activity2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activity");
                        activity2 = null;
                    }
                    Intent putExtra = intent.putExtra("android.provider.extra.APP_PACKAGE", activity2.getPackageName());
                    Intrinsics.checkNotNullExpressionValue(putExtra, "Intent(Settings.ACTION_A…his.activity.packageName)");
                    if (booleanValue) {
                        putExtra.addFlags(268435456);
                    }
                    Activity activity3 = this.activity;
                    if (activity3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activity");
                    } else {
                        activity = activity3;
                    }
                    activity.startActivity(putExtra);
                } else {
                    openAppSettings(booleanValue);
                }
            } else if (Intrinsics.areEqual(call.method, WorkflowModule.TYPE_NFC)) {
                openSettings("android.settings.NFC_SETTINGS", booleanValue);
            } else if (Intrinsics.areEqual(call.method, "sound")) {
                openSettings("android.settings.SOUND_SETTINGS", booleanValue);
            } else if (Intrinsics.areEqual(call.method, "internal_storage")) {
                openSettings("android.settings.INTERNAL_STORAGE_SETTINGS", booleanValue);
            } else if (Intrinsics.areEqual(call.method, "battery_optimization")) {
                openSettings("android.settings.IGNORE_BATTERY_OPTIMIZATION_SETTINGS", booleanValue);
            } else if (Intrinsics.areEqual(call.method, "vpn")) {
                if (Build.VERSION.SDK_INT >= 24) {
                    openSettings("android.settings.VPN_SETTINGS", booleanValue);
                } else {
                    openSettings("android.net.vpn.SETTINGS", booleanValue);
                }
            } else if (Intrinsics.areEqual(call.method, "app_settings")) {
                openAppSettings(booleanValue);
            } else if (Intrinsics.areEqual(call.method, "device_settings")) {
                openSettings("android.settings.SETTINGS", booleanValue);
            } else if (Intrinsics.areEqual(call.method, "accessibility")) {
                openSettings("android.settings.ACCESSIBILITY_SETTINGS", booleanValue);
            } else if (Intrinsics.areEqual(call.method, "development")) {
                openSettings("android.settings.APPLICATION_DEVELOPMENT_SETTINGS", booleanValue);
            } else if (Intrinsics.areEqual(call.method, "hotspot")) {
                Intent intent2 = new Intent();
                intent2.setClassName("com.android.settings", "com.android.settings.TetherSettings");
                openSettingsWithCustomIntent(intent2, booleanValue);
            } else if (Intrinsics.areEqual(call.method, DynamicLink.AndroidParameters.KEY_ANDROID_PACKAGE_NAME)) {
                openSettings("android.settings.APN_SETTINGS", booleanValue);
            } else if (Intrinsics.areEqual(call.method, NotificationCompat.CATEGORY_ALARM)) {
                Activity activity4 = this.activity;
                if (activity4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activity");
                    activity4 = null;
                }
                openSettingsWithCustomIntent(new Intent("android.settings.REQUEST_SCHEDULE_EXACT_ALARM", Uri.fromParts(SentryStackFrame.JsonKeys.PACKAGE, activity4.getPackageName(), null)), booleanValue);
            }
        }
        result.success("Done");
    }
}
