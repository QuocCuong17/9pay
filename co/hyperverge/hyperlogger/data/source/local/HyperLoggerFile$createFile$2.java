package co.hyperverge.hyperlogger.data.source.local;

import android.util.Log;
import co.hyperverge.hyperlogger.data.models.HyperLoggerConfig;
import java.io.File;
import java.io.IOException;
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
@DebugMetadata(c = "co.hyperverge.hyperlogger.data.source.local.HyperLoggerFile$createFile$2", f = "HyperLoggerFile.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HyperLoggerFile$createFile$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ HyperLoggerConfig $config;
    final /* synthetic */ File $logEventsFolder;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HyperLoggerFile this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HyperLoggerFile$createFile$2(HyperLoggerFile hyperLoggerFile, HyperLoggerConfig hyperLoggerConfig, File file, Continuation<? super HyperLoggerFile$createFile$2> continuation) {
        super(2, continuation);
        this.this$0 = hyperLoggerFile;
        this.$config = hyperLoggerConfig;
        this.$logEventsFolder = file;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HyperLoggerFile$createFile$2 hyperLoggerFile$createFile$2 = new HyperLoggerFile$createFile$2(this.this$0, this.$config, this.$logEventsFolder, continuation);
        hyperLoggerFile$createFile$2.L$0 = obj;
        return hyperLoggerFile$createFile$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((HyperLoggerFile$createFile$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Object m1202constructorimpl;
        String message;
        String str2;
        String str3;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        str = HyperLoggerFile.TAG;
        Log.d(str, "createFile() called");
        this.this$0.hyperLoggerConfig = this.$config;
        this.this$0.logEventsFolder = this.$logEventsFolder;
        File file = this.$logEventsFolder;
        HyperLoggerConfig hyperLoggerConfig = this.$config;
        HyperLoggerFile hyperLoggerFile = this.this$0;
        try {
            Result.Companion companion = Result.INSTANCE;
            File file2 = new File(file, Intrinsics.stringPlus(hyperLoggerConfig.getFileName(), ".txt"));
            if (!file.exists() && !file.mkdirs()) {
                str3 = HyperLoggerFile.TAG;
                Log.e(str3, Intrinsics.stringPlus("getOrCreateSyncFile: ", new IOException("Sync dir: " + ((Object) file.getPath()) + " could not be created")));
                throw new IOException("Sync dir: " + ((Object) file.getPath()) + " could not be created");
            }
            String path = file2.getPath();
            Intrinsics.checkNotNullExpressionValue(path, "logEventsFile.path");
            hyperLoggerFile.logEventsFilePath = path;
            m1202constructorimpl = Result.m1202constructorimpl(Boxing.boxBoolean(file2.createNewFile()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
        if (m1205exceptionOrNullimpl != null && (message = m1205exceptionOrNullimpl.getMessage()) != null) {
            str2 = HyperLoggerFile.TAG;
            Log.e(str2, Intrinsics.stringPlus("createFile: ", message));
        }
        return Result.m1208isFailureimpl(m1202constructorimpl) ? Boxing.boxBoolean(false) : m1202constructorimpl;
    }
}
