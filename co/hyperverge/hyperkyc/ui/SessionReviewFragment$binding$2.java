package co.hyperverge.hyperkyc.ui;

import android.view.View;
import co.hyperverge.hyperkyc.databinding.HkFragmentSessionReviewBinding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SessionReviewFragment.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class SessionReviewFragment$binding$2 extends FunctionReferenceImpl implements Function1<View, HkFragmentSessionReviewBinding> {
    public static final SessionReviewFragment$binding$2 INSTANCE = new SessionReviewFragment$binding$2();

    SessionReviewFragment$binding$2() {
        super(1, HkFragmentSessionReviewBinding.class, "bind", "bind(Landroid/view/View;)Lco/hyperverge/hyperkyc/databinding/HkFragmentSessionReviewBinding;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final HkFragmentSessionReviewBinding invoke(View p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return HkFragmentSessionReviewBinding.bind(p0);
    }
}
