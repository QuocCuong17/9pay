package co.hyperverge.hyperkyc.ui.form;

import android.view.View;
import co.hyperverge.hyperkyc.databinding.HkFragmentFormBinding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FormFragment.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class FormFragment$binding$2 extends FunctionReferenceImpl implements Function1<View, HkFragmentFormBinding> {
    public static final FormFragment$binding$2 INSTANCE = new FormFragment$binding$2();

    FormFragment$binding$2() {
        super(1, HkFragmentFormBinding.class, "bind", "bind(Landroid/view/View;)Lco/hyperverge/hyperkyc/databinding/HkFragmentFormBinding;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final HkFragmentFormBinding invoke(View p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return HkFragmentFormBinding.bind(p0);
    }
}
