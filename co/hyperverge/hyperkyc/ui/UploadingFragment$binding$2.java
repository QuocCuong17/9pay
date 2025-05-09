package co.hyperverge.hyperkyc.ui;

import android.view.View;
import co.hyperverge.hyperkyc.databinding.HkFragmentUploadingBinding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UploadingFragment.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class UploadingFragment$binding$2 extends FunctionReferenceImpl implements Function1<View, HkFragmentUploadingBinding> {
    public static final UploadingFragment$binding$2 INSTANCE = new UploadingFragment$binding$2();

    UploadingFragment$binding$2() {
        super(1, HkFragmentUploadingBinding.class, "bind", "bind(Landroid/view/View;)Lco/hyperverge/hyperkyc/databinding/HkFragmentUploadingBinding;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final HkFragmentUploadingBinding invoke(View p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return HkFragmentUploadingBinding.bind(p0);
    }
}
