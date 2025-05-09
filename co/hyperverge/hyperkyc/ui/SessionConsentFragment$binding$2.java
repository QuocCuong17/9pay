package co.hyperverge.hyperkyc.ui;

import android.view.View;
import co.hyperverge.hyperkyc.databinding.HkFragmentSessionConsentBinding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SessionConsentFragment.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class SessionConsentFragment$binding$2 extends FunctionReferenceImpl implements Function1<View, HkFragmentSessionConsentBinding> {
    public static final SessionConsentFragment$binding$2 INSTANCE = new SessionConsentFragment$binding$2();

    SessionConsentFragment$binding$2() {
        super(1, HkFragmentSessionConsentBinding.class, "bind", "bind(Landroid/view/View;)Lco/hyperverge/hyperkyc/databinding/HkFragmentSessionConsentBinding;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final HkFragmentSessionConsentBinding invoke(View p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return HkFragmentSessionConsentBinding.bind(p0);
    }
}
