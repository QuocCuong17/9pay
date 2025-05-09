package com.ngovang.otp_listener;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.api.Status;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

/* loaded from: classes4.dex */
public class OtpListenerPlugin implements FlutterPlugin, EventChannel.StreamHandler, MethodChannel.MethodCallHandler, PluginRegistry.ActivityResultListener, ActivityAware {
    private static final int SMS_CONSENT_REQUEST = 2;
    private ActivityPluginBinding activityPluginBinding;
    private EventChannel eventChannel;
    private EventChannel.EventSink events;
    private Activity mActivity;
    private Context mContext;
    private MethodChannel methodChannel;
    private BroadcastReceiver receiver;
    private SmsRetrieverClient smsRetrieverClient;
    private final String NAME_METHOD_CHANNEl = "NAME_METHOD_CHANNEl";
    private final String NAME_EVENT_CHANNEl = "NAME_EVENT_CHANNEl";

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        Context applicationContext = flutterPluginBinding.getApplicationContext();
        this.mContext = applicationContext;
        this.smsRetrieverClient = SmsRetriever.getClient(applicationContext);
        this.methodChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "NAME_METHOD_CHANNEl");
        this.eventChannel = new EventChannel(flutterPluginBinding.getBinaryMessenger(), "NAME_EVENT_CHANNEl");
        this.methodChannel.setMethodCallHandler(this);
        this.eventChannel.setStreamHandler(this);
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        if (methodCall.method.equals("setSenderPhone")) {
            this.smsRetrieverClient.startSmsUserConsent((String) methodCall.argument(WorkflowModule.Properties.Section.Component.Keyboard.PHONE));
            result.success(null);
        } else {
            if (methodCall.method.equals("unListener")) {
                BroadcastReceiver broadcastReceiver = this.receiver;
                if (broadcastReceiver != null) {
                    this.mContext.unregisterReceiver(broadcastReceiver);
                    this.receiver = null;
                }
                result.success(null);
                return;
            }
            result.notImplemented();
        }
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.methodChannel.setMethodCallHandler(null);
    }

    private BroadcastReceiver createSmsVerificationReceiver() {
        return new BroadcastReceiver() { // from class: com.ngovang.otp_listener.OtpListenerPlugin.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
                    Bundle extras = intent.getExtras();
                    if (((Status) extras.get("com.google.android.gms.auth.api.phone.EXTRA_STATUS")).getStatusCode() != 0) {
                        return;
                    }
                    Intent intent2 = (Intent) extras.getParcelable(SmsRetriever.EXTRA_CONSENT_INTENT);
                    ComponentName resolveActivity = intent2.resolveActivity(OtpListenerPlugin.this.mActivity.getPackageManager());
                    int flags = intent2.getFlags();
                    if (resolveActivity.getPackageName().equals("com.google.android.gms") && resolveActivity.getClassName().equals("com.google.android.gms.auth.api.phone.ui.UserConsentPromptActivity") && (flags & 1) == 0 && (flags & 2) == 0) {
                        OtpListenerPlugin.this.mActivity.startActivityForResult(intent2, 2);
                    }
                }
            }
        };
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onListen(Object obj, EventChannel.EventSink eventSink) {
        this.events = eventSink;
        this.receiver = createSmsVerificationReceiver();
        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        if (Build.VERSION.SDK_INT > 26) {
            this.mContext.registerReceiver(this.receiver, intentFilter, SmsRetriever.SEND_PERMISSION, null, 0);
        } else {
            this.mContext.registerReceiver(this.receiver, intentFilter);
        }
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onCancel(Object obj) {
        BroadcastReceiver broadcastReceiver = this.receiver;
        if (broadcastReceiver != null) {
            this.mContext.unregisterReceiver(broadcastReceiver);
            this.receiver = null;
        }
    }

    @Override // io.flutter.plugin.common.PluginRegistry.ActivityResultListener
    public boolean onActivityResult(int i, int i2, Intent intent) {
        if (i != 2 || i2 != -1) {
            return false;
        }
        this.events.success(intent.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE));
        return false;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        this.mActivity = activityPluginBinding.getActivity();
        this.activityPluginBinding = activityPluginBinding;
        activityPluginBinding.addActivityResultListener(this);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        this.activityPluginBinding.removeActivityResultListener(this);
    }
}
