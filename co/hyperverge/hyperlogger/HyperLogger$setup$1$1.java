package co.hyperverge.hyperlogger;

import android.util.Log;
import co.hyperverge.hyperlogger.data.models.HyperLoggerConfig;
import co.hyperverge.hyperlogger.data.source.local.HyperLoggerFile;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HyperLogger.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperlogger.HyperLogger$setup$1$1", f = "HyperLogger.kt", i = {0}, l = {30, 32}, m = "invokeSuspend", n = {"logEventsFolder"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class HyperLogger$setup$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ HyperLoggerConfig $config;
    final /* synthetic */ File $rootFolderPath;
    final /* synthetic */ HyperLogger $this_runCatching;
    Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HyperLogger$setup$1$1(File file, HyperLoggerConfig hyperLoggerConfig, HyperLogger hyperLogger, Continuation<? super HyperLogger$setup$1$1> continuation) {
        super(2, continuation);
        this.$rootFolderPath = file;
        this.$config = hyperLoggerConfig;
        this.$this_runCatching = hyperLogger;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HyperLogger$setup$1$1(this.$rootFolderPath, this.$config, this.$this_runCatching, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HyperLogger$setup$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0072  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        File file;
        boolean booleanValue;
        String str;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            file = new File(this.$rootFolderPath, "hv/logData/");
            this.L$0 = file;
            this.label = 1;
            if (HyperLoggerFile.INSTANCE.getInstance().deleteLogFolder$hyperlogger_release(file, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    booleanValue = ((Boolean) obj).booleanValue();
                    str = HyperLogger.TAG;
                    Log.d(str, Intrinsics.stringPlus("setup file created: ", Boxing.boxBoolean(booleanValue)));
                    if (!booleanValue) {
                        this.$this_runCatching.isHyperLoggerInitialised = true;
                    } else {
                        str2 = HyperLogger.TAG;
                        Log.d(str2, "Error while creating files and folders");
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            file = (File) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        this.L$0 = null;
        this.label = 2;
        obj = HyperLoggerFile.INSTANCE.getInstance().createFile$hyperlogger_release(file, this.$config, this);
        if (obj == coroutine_suspended) {
            return coroutine_suspended;
        }
        booleanValue = ((Boolean) obj).booleanValue();
        str = HyperLogger.TAG;
        Log.d(str, Intrinsics.stringPlus("setup file created: ", Boxing.boxBoolean(booleanValue)));
        if (!booleanValue) {
        }
        return Unit.INSTANCE;
    }
}
