package com.w3conext.jailbreak_root_detection;

import android.app.Activity;
import com.anish.trust_fall.emulator.EmulatorCheck;
import com.anish.trust_fall.externalstorage.ExternalStorageCheck;
import com.anish.trust_fall.rooted.RootedCheck;
import com.scottyab.rootbeer.util.QLog;
import com.w3conext.jailbreak_root_detection.debuger.Debugger;
import com.w3conext.jailbreak_root_detection.devmode.DevMode;
import com.w3conext.jailbreak_root_detection.frida.AntiFridaChecker;
import com.w3conext.jailbreak_root_detection.magisk.MagiskChecker;
import io.flutter.plugin.common.MethodChannel;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: JailbreakRootDetectionPlugin.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.w3conext.jailbreak_root_detection.JailbreakRootDetectionPlugin$processCheckIssues$1", f = "JailbreakRootDetectionPlugin.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class JailbreakRootDetectionPlugin$processCheckIssues$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MethodChannel.Result $result;
    int label;
    final /* synthetic */ JailbreakRootDetectionPlugin this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JailbreakRootDetectionPlugin$processCheckIssues$1(JailbreakRootDetectionPlugin jailbreakRootDetectionPlugin, MethodChannel.Result result, Continuation<? super JailbreakRootDetectionPlugin$processCheckIssues$1> continuation) {
        super(2, continuation);
        this.this$0 = jailbreakRootDetectionPlugin;
        this.$result = result;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new JailbreakRootDetectionPlugin$processCheckIssues$1(this.this$0, this.$result, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((JailbreakRootDetectionPlugin$processCheckIssues$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Activity activity;
        Activity activity2;
        Activity activity3;
        Activity activity4;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        QLog.LOGGING_LEVEL = 0;
        ArrayList arrayList = new ArrayList();
        RootedCheck rootedCheck = RootedCheck.INSTANCE;
        activity = this.this$0.activity;
        if (rootedCheck.isJailBroken(activity)) {
            arrayList.add("jailbreak");
        }
        if (AntiFridaChecker.INSTANCE.checkFrida()) {
            arrayList.add("fridaFound");
        }
        Debugger debugger = Debugger.INSTANCE;
        activity2 = this.this$0.activity;
        if (debugger.isDebugged(activity2)) {
            arrayList.add("debugged");
        }
        DevMode devMode = DevMode.INSTANCE;
        activity3 = this.this$0.activity;
        if (devMode.isDevMode(activity3)) {
            arrayList.add("devMode");
        }
        if (MagiskChecker.INSTANCE.isInstalled()) {
            arrayList.add("magiskFound");
        }
        if (EmulatorCheck.INSTANCE.isEmulator()) {
            arrayList.add("notRealDevice");
        }
        ExternalStorageCheck externalStorageCheck = ExternalStorageCheck.INSTANCE;
        activity4 = this.this$0.activity;
        if (externalStorageCheck.isOnExternalStorage(activity4)) {
            arrayList.add("onExternalStorage");
        }
        this.$result.success(arrayList);
        return Unit.INSTANCE;
    }
}
