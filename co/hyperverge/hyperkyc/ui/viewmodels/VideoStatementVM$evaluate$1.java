package co.hyperverge.hyperkyc.ui.viewmodels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VideoStatementVM.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM", f = "VideoStatementVM.kt", i = {1, 2, 3, 4}, l = {376, 380, 383, 386, 389}, m = "evaluate", n = {"this", "this", "this", "this"}, s = {"L$0", "L$0", "L$0", "L$0"})
/* loaded from: classes2.dex */
public final class VideoStatementVM$evaluate$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VideoStatementVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStatementVM$evaluate$1(VideoStatementVM videoStatementVM, Continuation<? super VideoStatementVM$evaluate$1> continuation) {
        super(continuation);
        this.this$0 = videoStatementVM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.evaluate(this);
    }
}
