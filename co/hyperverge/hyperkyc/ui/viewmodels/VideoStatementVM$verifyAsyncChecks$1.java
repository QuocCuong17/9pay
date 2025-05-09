package co.hyperverge.hyperkyc.ui.viewmodels;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VideoStatementVM.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM", f = "VideoStatementVM.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2}, l = {417, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, 427}, m = "verifyAsyncChecks", n = {"this", "failedStatement", "failedStatementId", "it", "isAllChecksCompleted", "this", "failedStatement", "failedStatementId", "it", "isAllChecksCompleted", "this", "failedStatement", "failedStatementId", "isAllChecksCompleted"}, s = {"L$0", "L$1", "L$2", "L$4", "I$0", "L$0", "L$1", "L$2", "L$4", "I$0", "L$0", "L$1", "L$2", "I$0"})
/* loaded from: classes2.dex */
public final class VideoStatementVM$verifyAsyncChecks$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VideoStatementVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStatementVM$verifyAsyncChecks$1(VideoStatementVM videoStatementVM, Continuation<? super VideoStatementVM$verifyAsyncChecks$1> continuation) {
        super(continuation);
        this.this$0 = videoStatementVM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object verifyAsyncChecks;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        verifyAsyncChecks = this.this$0.verifyAsyncChecks(this);
        return verifyAsyncChecks;
    }
}
