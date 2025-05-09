package co.hyperverge.hyperkyc.webCore.ui;

import android.webkit.WebView;
import android.widget.FrameLayout;
import co.hyperverge.hyperkyc.databinding.HkActivityWebCoreBinding;
import co.hyperverge.hyperkyc.utils.extensions.ActivityExtsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HKWebCoreActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity$showWebCoreFragment$2", f = "HKWebCoreActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HKWebCoreActivity$showWebCoreFragment$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $showWebCoreFragment;
    int label;
    final /* synthetic */ HKWebCoreActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKWebCoreActivity$showWebCoreFragment$2(HKWebCoreActivity hKWebCoreActivity, boolean z, Continuation<? super HKWebCoreActivity$showWebCoreFragment$2> continuation) {
        super(2, continuation);
        this.this$0 = hKWebCoreActivity;
        this.$showWebCoreFragment = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HKWebCoreActivity$showWebCoreFragment$2(this.this$0, this.$showWebCoreFragment, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HKWebCoreActivity$showWebCoreFragment$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        HkActivityWebCoreBinding binding$hyperkyc_release = this.this$0.getBinding$hyperkyc_release();
        boolean z = this.$showWebCoreFragment;
        HKWebCoreActivity hKWebCoreActivity = this.this$0;
        WebView webCoreWebView = binding$hyperkyc_release.webCoreWebView;
        Intrinsics.checkNotNullExpressionValue(webCoreWebView, "webCoreWebView");
        webCoreWebView.setVisibility(z ? 0 : 8);
        FrameLayout flContent = binding$hyperkyc_release.flContent;
        Intrinsics.checkNotNullExpressionValue(flContent, "flContent");
        flContent.setVisibility(z ^ true ? 0 : 8);
        if (z) {
            ActivityExtsKt.removeFragment$default(hKWebCoreActivity, 0, 1, null);
            hKWebCoreActivity.getWebCoreVM().setAllowNativeBackPress$hyperkyc_release(true);
        }
        return Unit.INSTANCE;
    }
}
