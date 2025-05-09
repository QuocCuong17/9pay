package co.hyperverge.hyperkyc.ui;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BaseActivity.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.BaseActivity$finishWithResult$6", f = "BaseActivity.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {250, 252}, m = "invokeSuspend$performFinishReview", n = {"$this$launch", "this$0", "$data", "status", "errorCode", "errorMessage", "$performStatePush"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "Z$0"})
/* loaded from: classes2.dex */
public final class BaseActivity$finishWithResult$6$performFinishReview$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseActivity$finishWithResult$6$performFinishReview$1(Continuation<? super BaseActivity$finishWithResult$6$performFinishReview$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return BaseActivity$finishWithResult$6.invokeSuspend$performFinishReview(null, null, null, false, null, null, null, false, this);
    }
}
