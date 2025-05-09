package co.hyperverge.hyperkyc.ui;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HKMainActivity.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity", f = "HKMainActivity.kt", i = {}, l = {1588}, m = "startFaceFlow-gIAlu-s", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HKMainActivity$startFaceFlow$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HKMainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKMainActivity$startFaceFlow$1(HKMainActivity hKMainActivity, Continuation<? super HKMainActivity$startFaceFlow$1> continuation) {
        super(continuation);
        this.this$0 = hKMainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object m413startFaceFlowgIAlus;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        m413startFaceFlowgIAlus = this.this$0.m413startFaceFlowgIAlus(null, this);
        return m413startFaceFlowgIAlus == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? m413startFaceFlowgIAlus : Result.m1201boximpl(m413startFaceFlowgIAlus);
    }
}
