package co.hyperverge.hyperkyc.utils;

import android.webkit.ValueCallback;
import android.webkit.WebView;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FormWebViewDriver.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.utils.FormWebViewDriver$setWebFormData$3", f = "FormWebViewDriver.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class FormWebViewDriver$setWebFormData$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $isChild;
    final /* synthetic */ String $moduleId;
    final /* synthetic */ Function0<Unit> $onDataSet;
    int label;
    final /* synthetic */ FormWebViewDriver this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormWebViewDriver$setWebFormData$3(FormWebViewDriver formWebViewDriver, String str, boolean z, Function0<Unit> function0, Continuation<? super FormWebViewDriver$setWebFormData$3> continuation) {
        super(2, continuation);
        this.this$0 = formWebViewDriver;
        this.$moduleId = str;
        this.$isChild = z;
        this.$onDataSet = function0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FormWebViewDriver$setWebFormData$3(this.this$0, this.$moduleId, this.$isChild, this.$onDataSet, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FormWebViewDriver$setWebFormData$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String moduleDataJS;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        WebView webView$hyperkyc_release = this.this$0.getWebView$hyperkyc_release();
        FormWebViewDriver formWebViewDriver = this.this$0;
        moduleDataJS = formWebViewDriver.setModuleDataJS(formWebViewDriver.getMainVM(), this.$moduleId, this.$isChild);
        final Function0<Unit> function0 = this.$onDataSet;
        webView$hyperkyc_release.evaluateJavascript(moduleDataJS, new ValueCallback() { // from class: co.hyperverge.hyperkyc.utils.FormWebViewDriver$setWebFormData$3$$ExternalSyntheticLambda0
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj2) {
                Function0.this.invoke();
            }
        });
        return Unit.INSTANCE;
    }
}
