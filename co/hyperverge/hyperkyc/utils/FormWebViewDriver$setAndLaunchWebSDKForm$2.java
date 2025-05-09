package co.hyperverge.hyperkyc.utils;

import android.webkit.ValueCallback;
import android.webkit.WebView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FormWebViewDriver.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormWebViewDriver$setAndLaunchWebSDKForm$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Function0<Unit> $onLoaded;
    final /* synthetic */ boolean $showBackButton;
    final /* synthetic */ FormWebViewDriver this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormWebViewDriver$setAndLaunchWebSDKForm$2(FormWebViewDriver formWebViewDriver, boolean z, Function0<Unit> function0) {
        super(0);
        this.this$0 = formWebViewDriver;
        this.$showBackButton = z;
        this.$onLoaded = function0;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        String launchFormModuleJS;
        WebView webView$hyperkyc_release = this.this$0.getWebView$hyperkyc_release();
        launchFormModuleJS = this.this$0.launchFormModuleJS(this.$showBackButton);
        final Function0<Unit> function0 = this.$onLoaded;
        webView$hyperkyc_release.evaluateJavascript(launchFormModuleJS, new ValueCallback() { // from class: co.hyperverge.hyperkyc.utils.FormWebViewDriver$setAndLaunchWebSDKForm$2$$ExternalSyntheticLambda0
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                FormWebViewDriver$setAndLaunchWebSDKForm$2.invoke$lambda$0(Function0.this, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(Function0 onLoaded, String str) {
        Intrinsics.checkNotNullParameter(onLoaded, "$onLoaded");
        onLoaded.invoke();
    }
}
