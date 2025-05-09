package kent.chien.client_information;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import androidx.core.app.NotificationCompat;
import androidx.core.content.pm.PackageInfoCompat;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import com.tekartik.sqflite.Constant;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.sentry.protocol.App;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ClientInformationPlugin.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u00012\u00020\u0002:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0002J\u0012\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\f\u001a\u00020\rH\u0016J\u0012\u0010\u000e\u001a\u00020\u000b2\b\b\u0001\u0010\u000f\u001a\u00020\rH\u0016J\u001c\u0010\u0010\u001a\u00020\u000b2\b\b\u0001\u0010\u0011\u001a\u00020\u00122\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lkent/chien/client_information/ClientInformationPlugin;", "Lio/flutter/embedding/engine/plugins/FlutterPlugin;", "Lio/flutter/plugin/common/MethodChannel$MethodCallHandler;", "()V", "channel", "Lio/flutter/plugin/common/MethodChannel;", "context", "Landroid/content/Context;", "getDeviceId", "", "onAttachedToEngine", "", "flutterPluginBinding", "Lio/flutter/embedding/engine/plugins/FlutterPlugin$FlutterPluginBinding;", "onDetachedFromEngine", "binding", "onMethodCall", NotificationCompat.CATEGORY_CALL, "Lio/flutter/plugin/common/MethodCall;", Constant.PARAM_RESULT, "Lio/flutter/plugin/common/MethodChannel$Result;", "Companion", "client_information_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ClientInformationPlugin implements FlutterPlugin, MethodChannel.MethodCallHandler {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private MethodChannel channel;
    private Context context;

    @JvmStatic
    public static final void registerWith(PluginRegistry.Registrar registrar) {
        INSTANCE.registerWith(registrar);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        Intrinsics.checkNotNullParameter(flutterPluginBinding, "flutterPluginBinding");
        MethodChannel methodChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "client_information");
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(this);
        Context applicationContext = flutterPluginBinding.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "flutterPluginBinding.applicationContext");
        this.context = applicationContext;
    }

    /* compiled from: ClientInformationPlugin.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lkent/chien/client_information/ClientInformationPlugin$Companion;", "", "()V", "registerWith", "", "registrar", "Lio/flutter/plugin/common/PluginRegistry$Registrar;", "client_information_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void registerWith(PluginRegistry.Registrar registrar) {
            Intrinsics.checkNotNullParameter(registrar, "registrar");
            new MethodChannel(registrar.messenger(), "client_information").setMethodCallHandler(new ClientInformationPlugin());
        }
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        String str = "unknown_version";
        String str2 = "unknown_application_id";
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(result, "result");
        if (Intrinsics.areEqual(call.method, "getInformation")) {
            Context context = this.context;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
                context = null;
            }
            PackageManager packageManager = context.getPackageManager();
            Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
            Context context2 = this.context;
            if (context2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
                context2 = null;
            }
            String obj = context2.getApplicationInfo().loadLabel(packageManager).toString();
            String RELEASE = Build.VERSION.RELEASE;
            Intrinsics.checkNotNullExpressionValue(RELEASE, "RELEASE");
            int i = Build.VERSION.SDK_INT;
            String deviceId = getDeviceId();
            String deviceName = Build.MODEL;
            long j = 0;
            try {
                Context context3 = this.context;
                if (context3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context3 = null;
                }
                PackageInfo packageInfo = packageManager.getPackageInfo(context3.getPackageName(), 0);
                Context context4 = this.context;
                if (context4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context4 = null;
                }
                String packageName = context4.getPackageName();
                if (packageName != null) {
                    str2 = packageName;
                }
                String str3 = packageInfo != null ? packageInfo.versionName : null;
                if (str3 != null) {
                    str = str3;
                }
                if (packageInfo != null) {
                    j = PackageInfoCompat.getLongVersionCode(packageInfo);
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = hashMap;
            hashMap2.put(AppConstants.DEVICE_ID, deviceId);
            Intrinsics.checkNotNullExpressionValue(deviceName, "deviceName");
            hashMap2.put("deviceName", deviceName);
            hashMap2.put("osName", "Android");
            hashMap2.put("osVersion", RELEASE);
            hashMap2.put("osVersionCode", String.valueOf(i));
            hashMap2.put("softwareName", obj);
            hashMap2.put("softwareVersion", str);
            hashMap2.put("applicationId", str2);
            hashMap2.put("applicationType", App.TYPE);
            hashMap2.put("applicationName", obj);
            hashMap2.put("applicationVersion", str);
            hashMap2.put("applicationBuildCode", String.valueOf(j));
            result.success(hashMap);
            return;
        }
        result.notImplemented();
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        MethodChannel methodChannel = this.channel;
        if (methodChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("channel");
            methodChannel = null;
        }
        methodChannel.setMethodCallHandler(null);
    }

    private final String getDeviceId() {
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        Intrinsics.checkNotNullExpressionValue(string, "getString(context.conten…ttings.Secure.ANDROID_ID)");
        return string;
    }
}
