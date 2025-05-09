package co.hyperverge.hyperkyc.ui;

import android.view.View;
import co.hyperverge.hyperkyc.databinding.HkFragmentApiCallBinding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApiCallFragment.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class ApiCallFragment$binding$2 extends FunctionReferenceImpl implements Function1<View, HkFragmentApiCallBinding> {
    public static final ApiCallFragment$binding$2 INSTANCE = new ApiCallFragment$binding$2();

    ApiCallFragment$binding$2() {
        super(1, HkFragmentApiCallBinding.class, "bind", "bind(Landroid/view/View;)Lco/hyperverge/hyperkyc/databinding/HkFragmentApiCallBinding;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final HkFragmentApiCallBinding invoke(View p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return HkFragmentApiCallBinding.bind(p0);
    }
}
