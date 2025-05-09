package co.hyperverge.hyperkyc.ui.form;

import android.view.View;
import co.hyperverge.hyperkyc.databinding.HkFragmentBsFormFileBinding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FormFileBSDFragment.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class FormFileBSDFragment$binding$2 extends FunctionReferenceImpl implements Function1<View, HkFragmentBsFormFileBinding> {
    public static final FormFileBSDFragment$binding$2 INSTANCE = new FormFileBSDFragment$binding$2();

    FormFileBSDFragment$binding$2() {
        super(1, HkFragmentBsFormFileBinding.class, "bind", "bind(Landroid/view/View;)Lco/hyperverge/hyperkyc/databinding/HkFragmentBsFormFileBinding;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final HkFragmentBsFormFileBinding invoke(View p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return HkFragmentBsFormFileBinding.bind(p0);
    }
}
