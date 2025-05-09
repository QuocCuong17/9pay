package co.hyperverge.hyperkyc.ui.nfc;

import android.view.View;
import co.hyperverge.hyperkyc.databinding.HkFragmentNfcReaderInstructionBinding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NFCReaderInstructionFragment.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class NFCReaderInstructionFragment$binding$2 extends FunctionReferenceImpl implements Function1<View, HkFragmentNfcReaderInstructionBinding> {
    public static final NFCReaderInstructionFragment$binding$2 INSTANCE = new NFCReaderInstructionFragment$binding$2();

    NFCReaderInstructionFragment$binding$2() {
        super(1, HkFragmentNfcReaderInstructionBinding.class, "bind", "bind(Landroid/view/View;)Lco/hyperverge/hyperkyc/databinding/HkFragmentNfcReaderInstructionBinding;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final HkFragmentNfcReaderInstructionBinding invoke(View p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return HkFragmentNfcReaderInstructionBinding.bind(p0);
    }
}
