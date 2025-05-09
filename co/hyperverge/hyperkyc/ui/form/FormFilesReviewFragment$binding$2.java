package co.hyperverge.hyperkyc.ui.form;

import android.view.View;
import co.hyperverge.hyperkyc.databinding.HkFragmentFormFilesReviewBinding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FormFilesReviewFragment.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class FormFilesReviewFragment$binding$2 extends FunctionReferenceImpl implements Function1<View, HkFragmentFormFilesReviewBinding> {
    public static final FormFilesReviewFragment$binding$2 INSTANCE = new FormFilesReviewFragment$binding$2();

    FormFilesReviewFragment$binding$2() {
        super(1, HkFragmentFormFilesReviewBinding.class, "bind", "bind(Landroid/view/View;)Lco/hyperverge/hyperkyc/databinding/HkFragmentFormFilesReviewBinding;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final HkFragmentFormFilesReviewBinding invoke(View p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return HkFragmentFormFilesReviewBinding.bind(p0);
    }
}
