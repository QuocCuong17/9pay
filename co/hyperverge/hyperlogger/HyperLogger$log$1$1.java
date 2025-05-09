package co.hyperverge.hyperlogger;

import co.hyperverge.hyperlogger.data.models.HyperLoggerConfig;
import co.hyperverge.hyperlogger.data.source.local.HyperLoggerFile;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HyperLogger.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperlogger.HyperLogger$log$1$1", f = "HyperLogger.kt", i = {}, l = {62, 63}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HyperLogger$log$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $log;
    final /* synthetic */ HyperLogger $this_runCatching;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HyperLogger$log$1$1(HyperLogger hyperLogger, String str, Continuation<? super HyperLogger$log$1$1> continuation) {
        super(2, continuation);
        this.$this_runCatching = hyperLogger;
        this.$log = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HyperLogger$log$1$1(this.$this_runCatching, this.$log, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HyperLogger$log$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0051  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        boolean z;
        HyperLoggerConfig hyperLoggerConfig;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            z = this.$this_runCatching.isHyperLoggerInitialised;
            if (z) {
                HyperLoggerFile companion = HyperLoggerFile.INSTANCE.getInstance();
                hyperLoggerConfig = this.$this_runCatching.hyperLoggerConfig;
                if (hyperLoggerConfig == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("hyperLoggerConfig");
                    hyperLoggerConfig = null;
                }
                this.label = 1;
                obj = companion.isFileExists$hyperlogger_release(hyperLoggerConfig, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                if (((Boolean) obj).booleanValue()) {
                }
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
            if (((Boolean) obj).booleanValue()) {
                this.label = 2;
                if (HyperLoggerFile.INSTANCE.getInstance().appendData$hyperlogger_release(this.$log, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
