package co.hyperverge.hyperkyc.ui;

import android.view.View;
import co.hyperverge.hyperkyc.databinding.HkFragmentCountryPickerBinding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CountryPickerFragment.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class CountryPickerFragment$binding$2 extends FunctionReferenceImpl implements Function1<View, HkFragmentCountryPickerBinding> {
    public static final CountryPickerFragment$binding$2 INSTANCE = new CountryPickerFragment$binding$2();

    CountryPickerFragment$binding$2() {
        super(1, HkFragmentCountryPickerBinding.class, "bind", "bind(Landroid/view/View;)Lco/hyperverge/hyperkyc/databinding/HkFragmentCountryPickerBinding;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final HkFragmentCountryPickerBinding invoke(View p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return HkFragmentCountryPickerBinding.bind(p0);
    }
}
