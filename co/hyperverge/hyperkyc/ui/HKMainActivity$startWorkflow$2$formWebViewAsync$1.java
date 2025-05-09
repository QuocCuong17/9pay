package co.hyperverge.hyperkyc.ui;

import androidx.lifecycle.LifecycleOwnerKt;
import co.hyperverge.hyperkyc.utils.FormWebViewDriver;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: HKMainActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$startWorkflow$2$formWebViewAsync$1", f = "HKMainActivity.kt", i = {}, l = {449}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class HKMainActivity$startWorkflow$2$formWebViewAsync$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    int label;
    final /* synthetic */ HKMainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKMainActivity$startWorkflow$2$formWebViewAsync$1(HKMainActivity hKMainActivity, Continuation<? super HKMainActivity$startWorkflow$2$formWebViewAsync$1> continuation) {
        super(2, continuation);
        this.this$0 = hKMainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HKMainActivity$startWorkflow$2$formWebViewAsync$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((HKMainActivity$startWorkflow$2$formWebViewAsync$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean z;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            z = this.this$0.useWebForm;
            if (z) {
                HKMainActivity hKMainActivity = this.this$0;
                HKMainActivity hKMainActivity2 = this.this$0;
                hKMainActivity.setFormWebViewDriver$hyperkyc_release(new FormWebViewDriver(hKMainActivity2, LifecycleOwnerKt.getLifecycleScope(hKMainActivity2), this.this$0.getMainVM()));
                this.label = 1;
                obj = this.this$0.getFormWebViewDriver$hyperkyc_release().initWebView$hyperkyc_release(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                return Boxing.boxBoolean(false);
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
