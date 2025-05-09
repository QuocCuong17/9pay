package co.hyperverge.hyperkyc.ui.form;

import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.ui.form.FormFragment;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.YieldKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FormFragment.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0005*\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "ViewType", "Landroid/view/View;", "ValueType", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.form.FormFragment$CompView$attachDynamicHandlers$1$1", f = "FormFragment.kt", i = {}, l = {752, 754}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class FormFragment$CompView$attachDynamicHandlers$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ WorkflowModule.Properties.Section.Component.DynamicHandlers $this_run;
    int label;
    final /* synthetic */ FormFragment.CompView<ViewType, ValueType> this$0;
    final /* synthetic */ FormFragment this$1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormFragment$CompView$attachDynamicHandlers$1$1(FormFragment.CompView<ViewType, ValueType> compView, WorkflowModule.Properties.Section.Component.DynamicHandlers dynamicHandlers, FormFragment formFragment, Continuation<? super FormFragment$CompView$attachDynamicHandlers$1$1> continuation) {
        super(2, continuation);
        this.this$0 = compView;
        this.$this_run = dynamicHandlers;
        this.this$1 = formFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FormFragment$CompView$attachDynamicHandlers$1$1(this.this$0, this.$this_run, this.this$1, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FormFragment$CompView$attachDynamicHandlers$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0065 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x004c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0063 -> B:6:0x0066). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        FormFragment$CompView$attachDynamicHandlers$1$1 formFragment$CompView$attachDynamicHandlers$1$1;
        boolean z;
        List<String> children;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            WorkflowModule.Properties.Section.Component.DynamicHandlers dynamicHandlers = this.this$0.getComponent().getDynamicHandlers();
            if (dynamicHandlers != null && (children = dynamicHandlers.getChildren()) != null) {
                Boxing.boxBoolean(this.this$1.getMainVM$hyperkyc_release().getWaitingForParentSet$hyperkyc_release().removeAll(children));
            }
            formFragment$CompView$attachDynamicHandlers$1$1 = this;
            z = true;
            if (!z) {
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
            formFragment$CompView$attachDynamicHandlers$1$1 = this;
            formFragment$CompView$attachDynamicHandlers$1$1.label = 2;
            if (DelayKt.delay(100L, formFragment$CompView$attachDynamicHandlers$1$1) == coroutine_suspended) {
            }
            z = invokeSuspend$waiting(formFragment$CompView$attachDynamicHandlers$1$1.$this_run, formFragment$CompView$attachDynamicHandlers$1$1.this$1);
            if (!z) {
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            formFragment$CompView$attachDynamicHandlers$1$1 = this;
            z = invokeSuspend$waiting(formFragment$CompView$attachDynamicHandlers$1$1.$this_run, formFragment$CompView$attachDynamicHandlers$1$1.this$1);
            if (!z) {
                formFragment$CompView$attachDynamicHandlers$1$1.label = 1;
                if (YieldKt.yield(formFragment$CompView$attachDynamicHandlers$1$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                formFragment$CompView$attachDynamicHandlers$1$1.label = 2;
                if (DelayKt.delay(100L, formFragment$CompView$attachDynamicHandlers$1$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                z = invokeSuspend$waiting(formFragment$CompView$attachDynamicHandlers$1$1.$this_run, formFragment$CompView$attachDynamicHandlers$1$1.this$1);
                if (!z) {
                    if (formFragment$CompView$attachDynamicHandlers$1$1.$this_run.getChildren() != null) {
                        formFragment$CompView$attachDynamicHandlers$1$1.this$1.getMainVM$hyperkyc_release().getWaitingForParentSet$hyperkyc_release().removeAll(formFragment$CompView$attachDynamicHandlers$1$1.$this_run.getChildren());
                    }
                    Iterator<WorkflowModule.Properties.Section.Component.Handler> it = formFragment$CompView$attachDynamicHandlers$1$1.$this_run.getHandlers().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        WorkflowModule.Properties.Section.Component.Handler next = it.next();
                        if (Intrinsics.areEqual(formFragment$CompView$attachDynamicHandlers$1$1.this$1.asBoolean$hyperkyc_release(next.getRule(), Boxing.boxBoolean(true)), Boxing.boxBoolean(true))) {
                            FormFragment.CompView.processHandler$hyperkyc_release$default(formFragment$CompView$attachDynamicHandlers$1$1.this$0, next, false, 2, null);
                            break;
                        }
                    }
                    formFragment$CompView$attachDynamicHandlers$1$1.this$0.attachDynamicHandlers();
                    return Unit.INSTANCE;
                }
            }
        }
    }

    private static final boolean invokeSuspend$waiting(WorkflowModule.Properties.Section.Component.DynamicHandlers dynamicHandlers, FormFragment formFragment) {
        if (dynamicHandlers.getChildren() == null) {
            return true;
        }
        List<String> children = dynamicHandlers.getChildren();
        if ((children instanceof Collection) && children.isEmpty()) {
            return true;
        }
        Iterator<T> it = children.iterator();
        while (it.hasNext()) {
            if (formFragment.getMainVM$hyperkyc_release().getWaitingForParentSet$hyperkyc_release().contains((String) it.next())) {
                return false;
            }
        }
        return true;
    }
}
