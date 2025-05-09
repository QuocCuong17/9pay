package co.hyperverge.hyperkyc.ui.viewmodels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VideoStatementVM.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementVM", f = "VideoStatementVM.kt", i = {0, 0, 0, 0, 0, 0}, l = {866, 936}, m = "sendRequest", n = {"this", "requestType", "text", "isSuccess", "currentIndex", "continueIfCheckFails"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "I$1"})
/* loaded from: classes2.dex */
public final class VideoStatementVM$sendRequest$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VideoStatementVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStatementVM$sendRequest$1(VideoStatementVM videoStatementVM, Continuation<? super VideoStatementVM$sendRequest$1> continuation) {
        super(continuation);
        this.this$0 = videoStatementVM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object sendRequest;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        sendRequest = this.this$0.sendRequest(null, null, null, this);
        return sendRequest;
    }
}
