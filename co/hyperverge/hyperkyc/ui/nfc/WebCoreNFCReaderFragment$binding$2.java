package co.hyperverge.hyperkyc.ui.nfc;

import android.view.View;
import co.hyperverge.hyperkyc.databinding.HkFragmentNfcBinding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WebCoreNFCReaderFragment.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class WebCoreNFCReaderFragment$binding$2 extends FunctionReferenceImpl implements Function1<View, HkFragmentNfcBinding> {
    public static final WebCoreNFCReaderFragment$binding$2 INSTANCE = new WebCoreNFCReaderFragment$binding$2();

    WebCoreNFCReaderFragment$binding$2() {
        super(1, HkFragmentNfcBinding.class, "bind", "bind(Landroid/view/View;)Lco/hyperverge/hyperkyc/databinding/HkFragmentNfcBinding;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final HkFragmentNfcBinding invoke(View p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return HkFragmentNfcBinding.bind(p0);
    }
}
