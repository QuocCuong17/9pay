package co.hyperverge.hyperkyc.ui;

import android.view.View;
import co.hyperverge.hyperkyc.databinding.HkFragmentWebviewBinding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WebViewFragment.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class WebViewFragment$binding$2 extends FunctionReferenceImpl implements Function1<View, HkFragmentWebviewBinding> {
    public static final WebViewFragment$binding$2 INSTANCE = new WebViewFragment$binding$2();

    WebViewFragment$binding$2() {
        super(1, HkFragmentWebviewBinding.class, "bind", "bind(Landroid/view/View;)Lco/hyperverge/hyperkyc/databinding/HkFragmentWebviewBinding;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final HkFragmentWebviewBinding invoke(View p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return HkFragmentWebviewBinding.bind(p0);
    }
}
