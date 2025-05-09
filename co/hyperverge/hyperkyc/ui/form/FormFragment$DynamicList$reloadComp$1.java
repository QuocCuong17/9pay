package co.hyperverge.hyperkyc.ui.form;

import co.hyperverge.hyperkyc.ui.form.FormFragment;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FormFragment.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.form.FormFragment$DynamicList", f = "FormFragment.kt", i = {0, 0, 0, 1, 1}, l = {3202, 3208}, m = "reloadComp", n = {"this", "compView", "data", "this", "data"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1"})
/* loaded from: classes2.dex */
public final class FormFragment$DynamicList$reloadComp$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FormFragment.DynamicList this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormFragment$DynamicList$reloadComp$1(FormFragment.DynamicList dynamicList, Continuation<? super FormFragment$DynamicList$reloadComp$1> continuation) {
        super(continuation);
        this.this$0 = dynamicList;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object reloadComp;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        reloadComp = this.this$0.reloadComp(null, null, this);
        return reloadComp;
    }
}
