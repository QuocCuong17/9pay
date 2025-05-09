package co.hyperverge.hyperkyc.ui;

import android.view.View;
import co.hyperverge.hyperkyc.databinding.HkFragmentDocumentPickerBinding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DocumentPickerFragment.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class DocumentPickerFragment$binding$2 extends FunctionReferenceImpl implements Function1<View, HkFragmentDocumentPickerBinding> {
    public static final DocumentPickerFragment$binding$2 INSTANCE = new DocumentPickerFragment$binding$2();

    DocumentPickerFragment$binding$2() {
        super(1, HkFragmentDocumentPickerBinding.class, "bind", "bind(Landroid/view/View;)Lco/hyperverge/hyperkyc/databinding/HkFragmentDocumentPickerBinding;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final HkFragmentDocumentPickerBinding invoke(View p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return HkFragmentDocumentPickerBinding.bind(p0);
    }
}
