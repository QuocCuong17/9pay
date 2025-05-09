package co.hyperverge.hyperlogger.data.source.local;

import android.util.Log;
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

/* compiled from: HyperLoggerFile.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperlogger.data.source.local.HyperLoggerFile$deleteFile$2", f = "HyperLoggerFile.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class HyperLoggerFile$deleteFile$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ String $path;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HyperLoggerFile$deleteFile$2(String str, Continuation<? super HyperLoggerFile$deleteFile$2> continuation) {
        super(2, continuation);
        this.$path = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HyperLoggerFile$deleteFile$2 hyperLoggerFile$deleteFile$2 = new HyperLoggerFile$deleteFile$2(this.$path, continuation);
        hyperLoggerFile$deleteFile$2.L$0 = obj;
        return hyperLoggerFile$deleteFile$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((HyperLoggerFile$deleteFile$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Object m1202constructorimpl;
        String str2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        str = HyperLoggerFile.TAG;
        Log.d(str, "deleteFile() called");
        String str3 = this.$path;
        try {
            Result.Companion companion = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(Boxing.boxBoolean(new File(str3).delete()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
        if (m1205exceptionOrNullimpl != null) {
            str2 = HyperLoggerFile.TAG;
            Log.e(str2, Intrinsics.stringPlus("deleteFile: ", m1205exceptionOrNullimpl.getMessage()));
        }
        return Result.m1208isFailureimpl(m1202constructorimpl) ? Boxing.boxBoolean(false) : m1202constructorimpl;
    }
}
