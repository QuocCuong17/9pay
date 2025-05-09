package co.hyperverge.hyperlogger.data.source.local;

import android.util.Log;
import co.hyperverge.hyperlogger.data.models.HyperLoggerConfig;
import java.io.File;
import kotlin.Metadata;
import kotlin.Result;
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
/* compiled from: HyperLoggerFile.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperlogger.data.source.local.HyperLoggerFile$isFileExists$2", f = "HyperLoggerFile.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HyperLoggerFile$isFileExists$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ HyperLoggerConfig $config;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HyperLoggerFile this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HyperLoggerFile$isFileExists$2(HyperLoggerFile hyperLoggerFile, HyperLoggerConfig hyperLoggerConfig, Continuation<? super HyperLoggerFile$isFileExists$2> continuation) {
        super(2, continuation);
        this.this$0 = hyperLoggerFile;
        this.$config = hyperLoggerConfig;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HyperLoggerFile$isFileExists$2 hyperLoggerFile$isFileExists$2 = new HyperLoggerFile$isFileExists$2(this.this$0, this.$config, continuation);
        hyperLoggerFile$isFileExists$2.L$0 = obj;
        return hyperLoggerFile$isFileExists$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((HyperLoggerFile$isFileExists$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object m1202constructorimpl;
        String str;
        File file;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        HyperLoggerFile hyperLoggerFile = this.this$0;
        HyperLoggerConfig hyperLoggerConfig = this.$config;
        try {
            Result.Companion companion = Result.INSTANCE;
            file = hyperLoggerFile.logEventsFolder;
            if (file == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logEventsFolder");
                file = null;
            }
            m1202constructorimpl = Result.m1202constructorimpl(Boxing.boxBoolean(new File(file, Intrinsics.stringPlus(hyperLoggerConfig.getFileName(), ".txt")).exists()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
        if (m1205exceptionOrNullimpl != null) {
            str = HyperLoggerFile.TAG;
            Log.e(str, Intrinsics.stringPlus("isFileExists: ", m1205exceptionOrNullimpl.getMessage()));
        }
        return Result.m1208isFailureimpl(m1202constructorimpl) ? Boxing.boxBoolean(false) : m1202constructorimpl;
    }
}
