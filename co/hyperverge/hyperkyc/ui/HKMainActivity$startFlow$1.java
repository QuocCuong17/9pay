package co.hyperverge.hyperkyc.ui;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HKMainActivity.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity", f = "HKMainActivity.kt", i = {0}, l = {657}, m = "startFlow", n = {"this"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class HKMainActivity$startFlow$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HKMainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKMainActivity$startFlow$1(HKMainActivity hKMainActivity, Continuation<? super HKMainActivity$startFlow$1> continuation) {
        super(continuation);
        this.this$0 = hKMainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object startFlow;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        startFlow = this.this$0.startFlow(this);
        return startFlow;
    }
}
