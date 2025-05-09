package com.baseflow.permissionhandler;

import android.app.Activity;
import android.content.Context;
import com.baseflow.permissionhandler.PermissionManager;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class PermissionHandlerPlugin implements FlutterPlugin, ActivityAware {
    private MethodCallHandlerImpl methodCallHandler;
    private MethodChannel methodChannel;

    public static void registerWith(final PluginRegistry.Registrar registrar) {
        PermissionHandlerPlugin permissionHandlerPlugin = new PermissionHandlerPlugin();
        permissionHandlerPlugin.startListening(registrar.context(), registrar.messenger());
        if (registrar.activeContext() instanceof Activity) {
            Activity activity = registrar.activity();
            Objects.requireNonNull(registrar);
            PermissionManager.ActivityRegistry activityRegistry = new PermissionManager.ActivityRegistry() { // from class: com.baseflow.permissionhandler.PermissionHandlerPlugin$$ExternalSyntheticLambda1
                @Override // com.baseflow.permissionhandler.PermissionManager.ActivityRegistry
                public final void addListener(PluginRegistry.ActivityResultListener activityResultListener) {
                    PluginRegistry.Registrar.this.addActivityResultListener(activityResultListener);
                }
            };
            Objects.requireNonNull(registrar);
            permissionHandlerPlugin.startListeningToActivity(activity, activityRegistry, new PermissionManager.PermissionRegistry() { // from class: com.baseflow.permissionhandler.PermissionHandlerPlugin$$ExternalSyntheticLambda3
                @Override // com.baseflow.permissionhandler.PermissionManager.PermissionRegistry
                public final void addListener(PluginRegistry.RequestPermissionsResultListener requestPermissionsResultListener) {
                    PluginRegistry.Registrar.this.addRequestPermissionsResultListener(requestPermissionsResultListener);
                }
            });
        }
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        startListening(flutterPluginBinding.getApplicationContext(), flutterPluginBinding.getBinaryMessenger());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        stopListening();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(final ActivityPluginBinding activityPluginBinding) {
        Activity activity = activityPluginBinding.getActivity();
        Objects.requireNonNull(activityPluginBinding);
        PermissionManager.ActivityRegistry activityRegistry = new PermissionManager.ActivityRegistry() { // from class: com.baseflow.permissionhandler.PermissionHandlerPlugin$$ExternalSyntheticLambda0
            @Override // com.baseflow.permissionhandler.PermissionManager.ActivityRegistry
            public final void addListener(PluginRegistry.ActivityResultListener activityResultListener) {
                ActivityPluginBinding.this.addActivityResultListener(activityResultListener);
            }
        };
        Objects.requireNonNull(activityPluginBinding);
        startListeningToActivity(activity, activityRegistry, new PermissionManager.PermissionRegistry() { // from class: com.baseflow.permissionhandler.PermissionHandlerPlugin$$ExternalSyntheticLambda2
            @Override // com.baseflow.permissionhandler.PermissionManager.PermissionRegistry
            public final void addListener(PluginRegistry.RequestPermissionsResultListener requestPermissionsResultListener) {
                ActivityPluginBinding.this.addRequestPermissionsResultListener(requestPermissionsResultListener);
            }
        });
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        onAttachedToActivity(activityPluginBinding);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        stopListeningToActivity();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity();
    }

    private void startListening(Context context, BinaryMessenger binaryMessenger) {
        this.methodChannel = new MethodChannel(binaryMessenger, "flutter.baseflow.com/permissions/methods");
        MethodCallHandlerImpl methodCallHandlerImpl = new MethodCallHandlerImpl(context, new AppSettingsManager(), new PermissionManager(), new ServiceManager());
        this.methodCallHandler = methodCallHandlerImpl;
        this.methodChannel.setMethodCallHandler(methodCallHandlerImpl);
    }

    private void stopListening() {
        this.methodChannel.setMethodCallHandler(null);
        this.methodChannel = null;
        this.methodCallHandler = null;
    }

    private void startListeningToActivity(Activity activity, PermissionManager.ActivityRegistry activityRegistry, PermissionManager.PermissionRegistry permissionRegistry) {
        MethodCallHandlerImpl methodCallHandlerImpl = this.methodCallHandler;
        if (methodCallHandlerImpl != null) {
            methodCallHandlerImpl.setActivity(activity);
            this.methodCallHandler.setActivityRegistry(activityRegistry);
            this.methodCallHandler.setPermissionRegistry(permissionRegistry);
        }
    }

    private void stopListeningToActivity() {
        MethodCallHandlerImpl methodCallHandlerImpl = this.methodCallHandler;
        if (methodCallHandlerImpl != null) {
            methodCallHandlerImpl.setActivity(null);
            this.methodCallHandler.setActivityRegistry(null);
            this.methodCallHandler.setPermissionRegistry(null);
        }
    }
}
