package co.hyperverge.hyperkyc.ui.viewmodels;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VideoStatementV2VM.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM", f = "VideoStatementV2VM.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, l = {TypedValues.PositionType.TYPE_DRAWPATH, 509}, m = "validateCheck", n = {"this", "apiCallState", "requestType", "finishWithErrorCallback", "this", "apiCallState", "requestType", "finishWithErrorCallback", "response", "code"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$5", "I$0"})
/* loaded from: classes2.dex */
public final class VideoStatementV2VM$validateCheck$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VideoStatementV2VM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStatementV2VM$validateCheck$1(VideoStatementV2VM videoStatementV2VM, Continuation<? super VideoStatementV2VM$validateCheck$1> continuation) {
        super(continuation);
        this.this$0 = videoStatementV2VM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object validateCheck;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        validateCheck = this.this$0.validateCheck(null, null, null, this);
        return validateCheck;
    }
}
