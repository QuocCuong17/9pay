package com.w3conext.jailbreak_root_detection;

import android.app.Activity;
import androidx.core.app.NotificationCompat;
import com.anish.trust_fall.emulator.EmulatorCheck;
import com.anish.trust_fall.externalstorage.ExternalStorageCheck;
import com.tekartik.sqflite.Constant;
import com.w3conext.jailbreak_root_detection.debuger.Debugger;
import com.w3conext.jailbreak_root_detection.devmode.DevMode;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;

/* compiled from: JailbreakRootDetectionPlugin.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\b\u0010\u0011\u001a\u00020\nH\u0016J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000fH\u0016J\u0018\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\u0019\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/w3conext/jailbreak_root_detection/JailbreakRootDetectionPlugin;", "Lio/flutter/embedding/engine/plugins/FlutterPlugin;", "Lio/flutter/plugin/common/MethodChannel$MethodCallHandler;", "Lio/flutter/embedding/engine/plugins/activity/ActivityAware;", "()V", "activity", "Landroid/app/Activity;", "channel", "Lio/flutter/plugin/common/MethodChannel;", "onAttachedToActivity", "", "binding", "Lio/flutter/embedding/engine/plugins/activity/ActivityPluginBinding;", "onAttachedToEngine", "flutterPluginBinding", "Lio/flutter/embedding/engine/plugins/FlutterPlugin$FlutterPluginBinding;", "onDetachedFromActivity", "onDetachedFromActivityForConfigChanges", "onDetachedFromEngine", "onMethodCall", NotificationCompat.CATEGORY_CALL, "Lio/flutter/plugin/common/MethodCall;", Constant.PARAM_RESULT, "Lio/flutter/plugin/common/MethodChannel$Result;", "onReattachedToActivityForConfigChanges", "processCheckIssues", "processJailBroken", "jailbreak_root_detection_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class JailbreakRootDetectionPlugin implements FlutterPlugin, MethodChannel.MethodCallHandler, ActivityAware {
    private Activity activity;
    private MethodChannel channel;

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        Intrinsics.checkNotNullParameter(flutterPluginBinding, "flutterPluginBinding");
        MethodChannel methodChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "jailbreak_root_detection");
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(this);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0012. Please report as an issue. */
    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(result, "result");
        String str = call.method;
        if (str != null) {
            switch (str.hashCode()) {
                case -1657482053:
                    if (str.equals("checkForIssues")) {
                        processCheckIssues(result);
                        return;
                    }
                    break;
                case -414259129:
                    if (str.equals("isOnExternalStorage")) {
                        result.success(Boolean.valueOf(ExternalStorageCheck.INSTANCE.isOnExternalStorage(this.activity)));
                        return;
                    }
                    break;
                case -262000034:
                    if (str.equals("isRealDevice")) {
                        result.success(Boolean.valueOf(!EmulatorCheck.INSTANCE.isEmulator()));
                        return;
                    }
                    break;
                case -245458083:
                    if (str.equals("isDebugged")) {
                        result.success(Boolean.valueOf(Debugger.INSTANCE.isDebugged(this.activity)));
                        return;
                    }
                    break;
                case 962112985:
                    if (str.equals("isJailBroken")) {
                        processJailBroken(result);
                        return;
                    }
                    break;
                case 979199694:
                    if (str.equals("isDevMode")) {
                        result.success(Boolean.valueOf(DevMode.INSTANCE.isDevMode(this.activity)));
                        return;
                    }
                    break;
            }
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

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        this.activity = binding.getActivity();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        this.activity = binding.getActivity();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        this.activity = null;
    }

    private final void processCheckIssues(MethodChannel.Result result) {
        CompletableJob Job$default;
        Job$default = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Job$default.plus(Dispatchers.getDefault())), null, null, new JailbreakRootDetectionPlugin$processCheckIssues$1(this, result, null), 3, null);
    }

    private final void processJailBroken(MethodChannel.Result result) {
        CompletableJob Job$default;
        Job$default = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Job$default.plus(Dispatchers.getDefault())), null, null, new JailbreakRootDetectionPlugin$processJailBroken$1(this, result, null), 3, null);
    }
}
