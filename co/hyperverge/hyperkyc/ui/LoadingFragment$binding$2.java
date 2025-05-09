package co.hyperverge.hyperkyc.ui;

import android.view.View;
import co.hyperverge.hyperkyc.databinding.HkFragmentLoadingBinding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoadingFragment.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class LoadingFragment$binding$2 extends FunctionReferenceImpl implements Function1<View, HkFragmentLoadingBinding> {
    public static final LoadingFragment$binding$2 INSTANCE = new LoadingFragment$binding$2();

    LoadingFragment$binding$2() {
        super(1, HkFragmentLoadingBinding.class, "bind", "bind(Landroid/view/View;)Lco/hyperverge/hyperkyc/databinding/HkFragmentLoadingBinding;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final HkFragmentLoadingBinding invoke(View p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return HkFragmentLoadingBinding.bind(p0);
    }
}
