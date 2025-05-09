package co.hyperverge.hyperkyc.webCore.ui;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: WebCoreVM.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.webCore.ui.WebCoreVM", f = "WebCoreVM.kt", i = {0, 0, 0, 0, 0}, l = {111}, m = "initWebSDKJS$hyperkyc_release", n = {"this", "configString", "callbackString", "destination$iv$iv", "value"}, s = {"L$0", "L$1", "L$2", "L$3", "L$5"})
/* loaded from: classes2.dex */
public final class WebCoreVM$initWebSDKJS$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ WebCoreVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebCoreVM$initWebSDKJS$1(WebCoreVM webCoreVM, Continuation<? super WebCoreVM$initWebSDKJS$1> continuation) {
        super(continuation);
        this.this$0 = webCoreVM;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.initWebSDKJS$hyperkyc_release(this);
    }
}
