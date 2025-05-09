package co.hyperverge.hyperkyc.ui.viewmodels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VideoStatementV2VM.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM", f = "VideoStatementV2VM.kt", i = {0, 0}, l = {134}, m = "startProcessing$hyperkyc_release", n = {"this", "isFaceDetectionCheckFailed"}, s = {"L$0", "Z$0"})
/* loaded from: classes2.dex */
public final class VideoStatementV2VM$startProcessing$1 extends ContinuationImpl {
    Object L$0;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VideoStatementV2VM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStatementV2VM$startProcessing$1(VideoStatementV2VM videoStatementV2VM, Continuation<? super VideoStatementV2VM$startProcessing$1> continuation) {
        super(continuation);
        this.this$0 = videoStatementV2VM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.startProcessing$hyperkyc_release(null, null, null, null, 0L, 0L, false, null, null, this);
    }
}
