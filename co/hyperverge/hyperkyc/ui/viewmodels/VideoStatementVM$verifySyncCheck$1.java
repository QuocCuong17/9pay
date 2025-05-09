package co.hyperverge.hyperkyc.ui.viewmodels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VideoStatementVM.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM", f = "VideoStatementVM.kt", i = {0, 0, 1, 1, 2, 2}, l = {476, 477, 478}, m = "verifySyncCheck", n = {"this", "check", "this", "check", "this", "check"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes2.dex */
public final class VideoStatementVM$verifySyncCheck$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VideoStatementVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStatementVM$verifySyncCheck$1(VideoStatementVM videoStatementVM, Continuation<? super VideoStatementVM$verifySyncCheck$1> continuation) {
        super(continuation);
        this.this$0 = videoStatementVM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object verifySyncCheck;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        verifySyncCheck = this.this$0.verifySyncCheck(null, this);
        return verifySyncCheck;
    }
}
