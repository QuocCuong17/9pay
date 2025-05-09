package co.hyperverge.hyperkyc.utils;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HSStateHandler.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.utils.HSStateHandler", f = "HSStateHandler.kt", i = {}, l = {61}, m = "retrieveUIConfig$hyperkyc_release", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HSStateHandler$retrieveUIConfig$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HSStateHandler this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HSStateHandler$retrieveUIConfig$1(HSStateHandler hSStateHandler, Continuation<? super HSStateHandler$retrieveUIConfig$1> continuation) {
        super(continuation);
        this.this$0 = hSStateHandler;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.retrieveUIConfig$hyperkyc_release(this);
    }
}
