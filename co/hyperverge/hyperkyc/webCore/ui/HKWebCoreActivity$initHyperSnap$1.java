package co.hyperverge.hyperkyc.webCore.ui;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HKWebCoreActivity.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity", f = "HKWebCoreActivity.kt", i = {0}, l = {1040}, m = "initHyperSnap", n = {"this"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class HKWebCoreActivity$initHyperSnap$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HKWebCoreActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKWebCoreActivity$initHyperSnap$1(HKWebCoreActivity hKWebCoreActivity, Continuation<? super HKWebCoreActivity$initHyperSnap$1> continuation) {
        super(continuation);
        this.this$0 = hKWebCoreActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object initHyperSnap;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        initHyperSnap = this.this$0.initHyperSnap(this);
        return initHyperSnap;
    }
}
