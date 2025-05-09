package co.hyperverge.hyperlogger.data.source.local;

import android.util.Log;
import co.hyperverge.hyperlogger.utils.HyperLoggerExtsKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.Result;
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
/* compiled from: HyperLoggerFile.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Result;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperlogger.data.source.local.HyperLoggerFile$appendData$2", f = "HyperLoggerFile.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HyperLoggerFile$appendData$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object> {
    final /* synthetic */ String $hyperLogEvent;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HyperLoggerFile this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HyperLoggerFile$appendData$2(HyperLoggerFile hyperLoggerFile, String str, Continuation<? super HyperLoggerFile$appendData$2> continuation) {
        super(2, continuation);
        this.this$0 = hyperLoggerFile;
        this.$hyperLogEvent = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HyperLoggerFile$appendData$2 hyperLoggerFile$appendData$2 = new HyperLoggerFile$appendData$2(this.this$0, this.$hyperLogEvent, continuation);
        hyperLoggerFile$appendData$2.L$0 = obj;
        return hyperLoggerFile$appendData$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends Unit>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Result<Unit>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Result<Unit>> continuation) {
        return ((HyperLoggerFile$appendData$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object m1202constructorimpl;
        String str;
        String str2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        HyperLoggerFile hyperLoggerFile = this.this$0;
        String str3 = this.$hyperLogEvent;
        try {
            Result.Companion companion = Result.INSTANCE;
            str2 = hyperLoggerFile.logEventsFilePath;
            if (str2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logEventsFilePath");
                str2 = null;
            }
            HyperLoggerExtsKt.appendLog(new File(str2), str3);
            m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
        if (m1205exceptionOrNullimpl != null) {
            str = HyperLoggerFile.TAG;
            Log.e(str, Intrinsics.stringPlus("addLog failed: ", m1205exceptionOrNullimpl.getMessage()));
        }
        return Result.m1201boximpl(m1202constructorimpl);
    }
}
