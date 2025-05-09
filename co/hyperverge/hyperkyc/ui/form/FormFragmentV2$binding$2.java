package co.hyperverge.hyperkyc.ui.form;

import android.view.View;
import co.hyperverge.hyperkyc.databinding.HkFragmentFormV2Binding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FormFragmentV2.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class FormFragmentV2$binding$2 extends FunctionReferenceImpl implements Function1<View, HkFragmentFormV2Binding> {
    public static final FormFragmentV2$binding$2 INSTANCE = new FormFragmentV2$binding$2();

    FormFragmentV2$binding$2() {
        super(1, HkFragmentFormV2Binding.class, "bind", "bind(Landroid/view/View;)Lco/hyperverge/hyperkyc/databinding/HkFragmentFormV2Binding;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final HkFragmentFormV2Binding invoke(View p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return HkFragmentFormV2Binding.bind(p0);
    }
}
