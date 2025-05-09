package co.hyperverge.hyperkyc.ui;

import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HKMainActivity.kt */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$handleDocModuleFailure$2", f = "HKMainActivity.kt", i = {}, l = {1962}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HKMainActivity$handleDocModuleFailure$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ WorkflowUIState.DocCapture $docFlowUIState;
    int label;
    final /* synthetic */ HKMainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKMainActivity$handleDocModuleFailure$2(HKMainActivity hKMainActivity, WorkflowUIState.DocCapture docCapture, Continuation<? super HKMainActivity$handleDocModuleFailure$2> continuation) {
        super(1, continuation);
        this.this$0 = hKMainActivity;
        this.$docFlowUIState = docCapture;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new HKMainActivity$handleDocModuleFailure$2(this.this$0, this.$docFlowUIState, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((HKMainActivity$handleDocModuleFailure$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object m412startDocFlowgIAlus;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            m412startDocFlowgIAlus = this.this$0.m412startDocFlowgIAlus(this.$docFlowUIState, this);
            if (m412startDocFlowgIAlus == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ((Result) obj).getValue();
        }
        return Unit.INSTANCE;
    }
}
