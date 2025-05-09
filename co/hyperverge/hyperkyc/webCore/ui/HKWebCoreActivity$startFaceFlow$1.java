package co.hyperverge.hyperkyc.webCore.ui;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HKWebCoreActivity.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity", f = "HKWebCoreActivity.kt", i = {}, l = {708}, m = "startFaceFlow-gIAlu-s", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HKWebCoreActivity$startFaceFlow$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HKWebCoreActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKWebCoreActivity$startFaceFlow$1(HKWebCoreActivity hKWebCoreActivity, Continuation<? super HKWebCoreActivity$startFaceFlow$1> continuation) {
        super(continuation);
        this.this$0 = hKWebCoreActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object m443startFaceFlowgIAlus;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        m443startFaceFlowgIAlus = this.this$0.m443startFaceFlowgIAlus(null, this);
        return m443startFaceFlowgIAlus == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? m443startFaceFlowgIAlus : Result.m1201boximpl(m443startFaceFlowgIAlus);
    }
}
