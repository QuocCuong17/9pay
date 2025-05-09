package co.hyperverge.hyperkyc.utils;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HSStateHandler.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.utils.HSStateHandler", f = "HSStateHandler.kt", i = {}, l = {44}, m = "storeUIConfig-gIAlu-s$hyperkyc_release", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HSStateHandler$storeUIConfig$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HSStateHandler this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HSStateHandler$storeUIConfig$1(HSStateHandler hSStateHandler, Continuation<? super HSStateHandler$storeUIConfig$1> continuation) {
        super(continuation);
        this.this$0 = hSStateHandler;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object m432storeUIConfiggIAlus$hyperkyc_release = this.this$0.m432storeUIConfiggIAlus$hyperkyc_release(null, this);
        return m432storeUIConfiggIAlus$hyperkyc_release == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? m432storeUIConfiggIAlus$hyperkyc_release : Result.m1201boximpl(m432storeUIConfiggIAlus$hyperkyc_release);
    }
}
