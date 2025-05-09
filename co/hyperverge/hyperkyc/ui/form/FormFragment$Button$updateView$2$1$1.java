package co.hyperverge.hyperkyc.ui.form;

import android.widget.Button;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.ui.form.FormFragment;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: FormFragment.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/Button;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.form.FormFragment$Button$updateView$2$1$1", f = "FormFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class FormFragment$Button$updateView$2$1$1 extends SuspendLambda implements Function2<Button, Continuation<? super Unit>, Object> {
    final /* synthetic */ WorkflowModule.Properties.Section.Component.Handler $it;
    int label;
    final /* synthetic */ FormFragment.Button this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormFragment$Button$updateView$2$1$1(FormFragment.Button button, WorkflowModule.Properties.Section.Component.Handler handler, Continuation<? super FormFragment$Button$updateView$2$1$1> continuation) {
        super(2, continuation);
        this.this$0 = button;
        this.$it = handler;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FormFragment$Button$updateView$2$1$1(this.this$0, this.$it, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Button button, Continuation<? super Unit> continuation) {
        return ((FormFragment$Button$updateView$2$1$1) create(button, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.updateValue("yes");
        FormFragment.CompView.processHandler$hyperkyc_release$default(this.this$0, this.$it, false, 2, null);
        return Unit.INSTANCE;
    }
}
