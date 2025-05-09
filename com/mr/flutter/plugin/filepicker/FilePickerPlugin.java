package com.mr.flutter.plugin.filepicker;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.facebook.share.internal.ShareConstants;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.embedding.engine.plugins.lifecycle.FlutterLifecycleAdapter;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class FilePickerPlugin implements MethodChannel.MethodCallHandler, FlutterPlugin, ActivityAware {
    private static final String CHANNEL = "miguelruivo.flutter.plugins.filepicker";
    private static final String EVENT_CHANNEL = "miguelruivo.flutter.plugins.filepickerevent";
    private static final String TAG = "FilePicker";
    private static int compressionQuality = 0;
    private static String fileType = null;
    private static boolean isMultipleSelection = false;
    private static boolean withData = false;
    private Activity activity;
    private ActivityPluginBinding activityBinding;
    private Application application;
    private MethodChannel channel;
    private FilePickerDelegate delegate;
    private Lifecycle lifecycle;
    private LifeCycleObserver observer;
    private FlutterPlugin.FlutterPluginBinding pluginBinding;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class LifeCycleObserver implements Application.ActivityLifecycleCallbacks, DefaultLifecycleObserver {
        private final Activity thisActivity;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onCreate(LifecycleOwner lifecycleOwner) {
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onPause(LifecycleOwner lifecycleOwner) {
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onResume(LifecycleOwner lifecycleOwner) {
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onStart(LifecycleOwner lifecycleOwner) {
        }

        LifeCycleObserver(Activity activity) {
            this.thisActivity = activity;
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onStop(LifecycleOwner lifecycleOwner) {
            onActivityStopped(this.thisActivity);
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onDestroy(LifecycleOwner lifecycleOwner) {
            onActivityDestroyed(this.thisActivity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.thisActivity != activity || activity.getApplicationContext() == null) {
                return;
            }
            ((Application) activity.getApplicationContext()).unregisterActivityLifecycleCallbacks(this);
        }
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String[] mimeTypes;
        if (this.activity == null) {
            result.error("no_activity", "file picker plugin requires a foreground activity", null);
            return;
        }
        MethodResultWrapper methodResultWrapper = new MethodResultWrapper(result);
        HashMap hashMap = (HashMap) methodCall.arguments;
        if (methodCall.method != null && methodCall.method.equals("clear")) {
            methodResultWrapper.success(Boolean.valueOf(FileUtils.clearCache(this.activity.getApplicationContext())));
            return;
        }
        if (methodCall.method != null && methodCall.method.equals("save")) {
            this.delegate.saveFile((String) hashMap.get("fileName"), resolveType((String) hashMap.get("fileType")), (String) hashMap.get("initialDirectory"), FileUtils.getMimeTypes((ArrayList) hashMap.get("allowedExtensions")), (byte[]) hashMap.get("bytes"), methodResultWrapper);
            return;
        }
        String resolveType = resolveType(methodCall.method);
        fileType = resolveType;
        if (resolveType == null) {
            methodResultWrapper.notImplemented();
        } else if (resolveType != "dir") {
            isMultipleSelection = ((Boolean) hashMap.get("allowMultipleSelection")).booleanValue();
            withData = ((Boolean) hashMap.get("withData")).booleanValue();
            compressionQuality = ((Integer) hashMap.get("compressionQuality")).intValue();
            mimeTypes = FileUtils.getMimeTypes((ArrayList) hashMap.get("allowedExtensions"));
            if (methodCall.method == null && methodCall.method.equals("custom") && (mimeTypes == null || mimeTypes.length == 0)) {
                methodResultWrapper.error(TAG, "Unsupported filter. Make sure that you are only using the extension without the dot, (ie., jpg instead of .jpg). This could also have happened because you are using an unsupported file extension.  If the problem persists, you may want to consider using FileType.any instead.", null);
                return;
            } else {
                this.delegate.startFileExplorer(fileType, isMultipleSelection, withData, mimeTypes, compressionQuality, methodResultWrapper);
            }
        }
        mimeTypes = null;
        if (methodCall.method == null) {
        }
        this.delegate.startFileExplorer(fileType, isMultipleSelection, withData, mimeTypes, compressionQuality, methodResultWrapper);
    }

    private static String resolveType(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1349088399:
                if (str.equals("custom")) {
                    c = 0;
                    break;
                }
                break;
            case 96748:
                if (str.equals("any")) {
                    c = 1;
                    break;
                }
                break;
            case 99469:
                if (str.equals("dir")) {
                    c = 2;
                    break;
                }
                break;
            case 93166550:
                if (str.equals("audio")) {
                    c = 3;
                    break;
                }
                break;
            case 100313435:
                if (str.equals("image")) {
                    c = 4;
                    break;
                }
                break;
            case 103772132:
                if (str.equals(ShareConstants.WEB_DIALOG_PARAM_MEDIA)) {
                    c = 5;
                    break;
                }
                break;
            case 112202875:
                if (str.equals("video")) {
                    c = 6;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
                return "*/*";
            case 2:
                return "dir";
            case 3:
                return "audio/*";
            case 4:
                return "image/*";
            case 5:
                return "image/*,video/*";
            case 6:
                return "video/*";
            default:
                return null;
        }
    }

    /* loaded from: classes4.dex */
    private static class MethodResultWrapper implements MethodChannel.Result {
        private final Handler handler = new Handler(Looper.getMainLooper());
        private final MethodChannel.Result methodResult;

        MethodResultWrapper(MethodChannel.Result result) {
            this.methodResult = result;
        }

        @Override // io.flutter.plugin.common.MethodChannel.Result
        public void success(final Object obj) {
            this.handler.post(new Runnable() { // from class: com.mr.flutter.plugin.filepicker.FilePickerPlugin.MethodResultWrapper.1
                @Override // java.lang.Runnable
                public void run() {
                    MethodResultWrapper.this.methodResult.success(obj);
                }
            });
        }

        @Override // io.flutter.plugin.common.MethodChannel.Result
        public void error(final String str, final String str2, final Object obj) {
            this.handler.post(new Runnable() { // from class: com.mr.flutter.plugin.filepicker.FilePickerPlugin.MethodResultWrapper.2
                @Override // java.lang.Runnable
                public void run() {
                    MethodResultWrapper.this.methodResult.error(str, str2, obj);
                }
            });
        }

        @Override // io.flutter.plugin.common.MethodChannel.Result
        public void notImplemented() {
            this.handler.post(new Runnable() { // from class: com.mr.flutter.plugin.filepicker.FilePickerPlugin.MethodResultWrapper.3
                @Override // java.lang.Runnable
                public void run() {
                    MethodResultWrapper.this.methodResult.notImplemented();
                }
            });
        }
    }

    private void setup(BinaryMessenger binaryMessenger, Application application, Activity activity, ActivityPluginBinding activityPluginBinding) {
        this.activity = activity;
        this.application = application;
        this.delegate = new FilePickerDelegate(activity);
        MethodChannel methodChannel = new MethodChannel(binaryMessenger, CHANNEL);
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(this);
        new EventChannel(binaryMessenger, EVENT_CHANNEL).setStreamHandler(new EventChannel.StreamHandler() { // from class: com.mr.flutter.plugin.filepicker.FilePickerPlugin.1
            @Override // io.flutter.plugin.common.EventChannel.StreamHandler
            public void onListen(Object obj, EventChannel.EventSink eventSink) {
                FilePickerPlugin.this.delegate.setEventHandler(eventSink);
            }

            @Override // io.flutter.plugin.common.EventChannel.StreamHandler
            public void onCancel(Object obj) {
                FilePickerPlugin.this.delegate.setEventHandler(null);
            }
        });
        this.observer = new LifeCycleObserver(activity);
        activityPluginBinding.addActivityResultListener(this.delegate);
        activityPluginBinding.addRequestPermissionsResultListener(this.delegate);
        Lifecycle activityLifecycle = FlutterLifecycleAdapter.getActivityLifecycle(activityPluginBinding);
        this.lifecycle = activityLifecycle;
        activityLifecycle.addObserver(this.observer);
    }

    private void tearDown() {
        this.activityBinding.removeActivityResultListener(this.delegate);
        this.activityBinding.removeRequestPermissionsResultListener(this.delegate);
        this.activityBinding = null;
        LifeCycleObserver lifeCycleObserver = this.observer;
        if (lifeCycleObserver != null) {
            this.lifecycle.removeObserver(lifeCycleObserver);
            this.application.unregisterActivityLifecycleCallbacks(this.observer);
        }
        this.lifecycle = null;
        this.delegate.setEventHandler(null);
        this.delegate = null;
        this.channel.setMethodCallHandler(null);
        this.channel = null;
        this.application = null;
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.pluginBinding = flutterPluginBinding;
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.pluginBinding = null;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        this.activityBinding = activityPluginBinding;
        setup(this.pluginBinding.getBinaryMessenger(), (Application) this.pluginBinding.getApplicationContext(), this.activityBinding.getActivity(), this.activityBinding);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        onAttachedToActivity(activityPluginBinding);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        tearDown();
    }
}
