package com.jrai.flutter_keyboard_visibility;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;

/* loaded from: classes4.dex */
public class FlutterKeyboardVisibilityPlugin implements FlutterPlugin, ActivityAware, EventChannel.StreamHandler, ViewTreeObserver.OnGlobalLayoutListener {
    private EventChannel.EventSink eventSink;
    private boolean isVisible;
    private View mainView;

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        init(flutterPluginBinding.getBinaryMessenger());
    }

    private void init(BinaryMessenger binaryMessenger) {
        new EventChannel(binaryMessenger, "flutter_keyboard_visibility").setStreamHandler(this);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        unregisterListener();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        listenForKeyboard(activityPluginBinding.getActivity());
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        unregisterListener();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        listenForKeyboard(activityPluginBinding.getActivity());
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        unregisterListener();
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onListen(Object obj, EventChannel.EventSink eventSink) {
        this.eventSink = eventSink;
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onCancel(Object obj) {
        this.eventSink = null;
    }

    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r0v9 */
    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        if (this.mainView != null) {
            Rect rect = new Rect();
            this.mainView.getWindowVisibleDisplayFrame(rect);
            ?? r0 = ((double) rect.height()) / ((double) this.mainView.getRootView().getHeight()) < 0.85d ? 1 : 0;
            if (r0 != this.isVisible) {
                this.isVisible = r0;
                EventChannel.EventSink eventSink = this.eventSink;
                if (eventSink != null) {
                    eventSink.success(Integer.valueOf((int) r0));
                }
            }
        }
    }

    private void listenForKeyboard(Activity activity) {
        View findViewById = activity.findViewById(android.R.id.content);
        this.mainView = findViewById;
        findViewById.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    private void unregisterListener() {
        View view = this.mainView;
        if (view != null) {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            this.mainView = null;
        }
    }
}
