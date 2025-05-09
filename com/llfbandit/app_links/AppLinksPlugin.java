package com.llfbandit.app_links;

import android.content.Intent;
import android.util.Log;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

/* loaded from: classes4.dex */
public class AppLinksPlugin implements FlutterPlugin, MethodChannel.MethodCallHandler, EventChannel.StreamHandler, ActivityAware, PluginRegistry.NewIntentListener {
    private static final String EVENTS_CHANNEL = "com.llfbandit.app_links/events";
    private static final String MESSAGES_CHANNEL = "com.llfbandit.app_links/messages";
    private static final String TAG = "com.llfbandit.app_links";
    ActivityPluginBinding binding;
    private EventChannel eventChannel;
    private EventChannel.EventSink eventSink;
    private String initialLink;
    private String latestLink;
    private MethodChannel methodChannel;

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        MethodChannel methodChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), MESSAGES_CHANNEL);
        this.methodChannel = methodChannel;
        methodChannel.setMethodCallHandler(this);
        EventChannel eventChannel = new EventChannel(flutterPluginBinding.getBinaryMessenger(), EVENTS_CHANNEL);
        this.eventChannel = eventChannel;
        eventChannel.setStreamHandler(this);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.methodChannel.setMethodCallHandler(null);
        this.eventChannel.setStreamHandler(null);
        this.initialLink = null;
        this.latestLink = null;
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        if (methodCall.method.equals("getLatestAppLink")) {
            result.success(this.latestLink);
        } else if (methodCall.method.equals("getInitialAppLink")) {
            result.success(this.initialLink);
        } else {
            result.notImplemented();
        }
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        this.binding = activityPluginBinding;
        activityPluginBinding.addOnNewIntentListener(this);
        handleIntent(activityPluginBinding.getActivity().getIntent());
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        this.binding = activityPluginBinding;
        activityPluginBinding.addOnNewIntentListener(this);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        ActivityPluginBinding activityPluginBinding = this.binding;
        if (activityPluginBinding != null) {
            activityPluginBinding.removeOnNewIntentListener(this);
        }
        this.binding = null;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity();
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onListen(Object obj, EventChannel.EventSink eventSink) {
        this.eventSink = eventSink;
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onCancel(Object obj) {
        this.eventSink = null;
    }

    @Override // io.flutter.plugin.common.PluginRegistry.NewIntentListener
    public boolean onNewIntent(Intent intent) {
        return handleIntent(intent);
    }

    private boolean handleIntent(Intent intent) {
        String deepLinkFromIntent;
        if (intent == null) {
            return false;
        }
        Log.d("com.llfbandit.app_links", intent.toString());
        if ((intent.getFlags() & 1048576) == 1048576 || (deepLinkFromIntent = AppLinksHelper.getDeepLinkFromIntent(intent)) == null) {
            return false;
        }
        if (this.initialLink == null) {
            this.initialLink = deepLinkFromIntent;
        }
        this.latestLink = deepLinkFromIntent;
        EventChannel.EventSink eventSink = this.eventSink;
        if (eventSink == null) {
            return true;
        }
        eventSink.success(deepLinkFromIntent);
        return true;
    }
}
