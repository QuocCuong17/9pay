package io.flutter.plugins.packageinfo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class PackageInfoPlugin implements MethodChannel.MethodCallHandler, FlutterPlugin {
    private Context applicationContext;
    private MethodChannel methodChannel;

    public static void registerWith(PluginRegistry.Registrar registrar) {
        new PackageInfoPlugin().onAttachedToEngine(registrar.context(), registrar.messenger());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        onAttachedToEngine(flutterPluginBinding.getApplicationContext(), flutterPluginBinding.getBinaryMessenger());
    }

    private void onAttachedToEngine(Context context, BinaryMessenger binaryMessenger) {
        this.applicationContext = context;
        MethodChannel methodChannel = new MethodChannel(binaryMessenger, "plugins.flutter.io/package_info");
        this.methodChannel = methodChannel;
        methodChannel.setMethodCallHandler(this);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.applicationContext = null;
        this.methodChannel.setMethodCallHandler(null);
        this.methodChannel = null;
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        try {
            if (methodCall.method.equals("getAll")) {
                PackageManager packageManager = this.applicationContext.getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(this.applicationContext.getPackageName(), 0);
                HashMap hashMap = new HashMap();
                hashMap.put("appName", packageInfo.applicationInfo.loadLabel(packageManager).toString());
                hashMap.put("packageName", this.applicationContext.getPackageName());
                hashMap.put("version", packageInfo.versionName);
                hashMap.put("buildNumber", String.valueOf(getLongVersionCode(packageInfo)));
                result.success(hashMap);
            } else {
                result.notImplemented();
            }
        } catch (PackageManager.NameNotFoundException e) {
            result.error("Name not found", e.getMessage(), null);
        }
    }

    private static long getLongVersionCode(PackageInfo packageInfo) {
        if (Build.VERSION.SDK_INT >= 28) {
            return packageInfo.getLongVersionCode();
        }
        return packageInfo.versionCode;
    }
}
