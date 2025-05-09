package com.ngovang.flutter_lifecycle_detector;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.media3.exoplayer.offline.DownloadService;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.EventChannel;

/* loaded from: classes4.dex */
public class FlutterLifecycleDetectorPlugin implements FlutterPlugin, EventChannel.StreamHandler, LifecycleObserver, ActivityAware {
    private EventChannel eventChannel;
    private EventChannel.EventSink eventSink;

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onEnteredForeground() {
        sendEvent(DownloadService.KEY_FOREGROUND);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onEnteredBackground() {
        sendEvent("background");
    }

    private void sendEvent(String str) {
        EventChannel.EventSink eventSink = this.eventSink;
        if (eventSink == null) {
            return;
        }
        eventSink.success(str);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        EventChannel eventChannel = new EventChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_lifecycle_detector");
        this.eventChannel = eventChannel;
        eventChannel.setStreamHandler(this);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.eventChannel.setStreamHandler(null);
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onListen(Object obj, EventChannel.EventSink eventSink) {
        this.eventSink = eventSink;
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onCancel(Object obj) {
        this.eventSink = null;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        ProcessLifecycleOwner.get().getLifecycle().removeObserver(this);
    }
}
