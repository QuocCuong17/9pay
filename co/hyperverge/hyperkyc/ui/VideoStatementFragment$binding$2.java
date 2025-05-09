package co.hyperverge.hyperkyc.ui;

import android.view.View;
import co.hyperverge.hyperkyc.databinding.HkFragmentVideoStatementBinding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoStatementFragment.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class VideoStatementFragment$binding$2 extends FunctionReferenceImpl implements Function1<View, HkFragmentVideoStatementBinding> {
    public static final VideoStatementFragment$binding$2 INSTANCE = new VideoStatementFragment$binding$2();

    VideoStatementFragment$binding$2() {
        super(1, HkFragmentVideoStatementBinding.class, "bind", "bind(Landroid/view/View;)Lco/hyperverge/hyperkyc/databinding/HkFragmentVideoStatementBinding;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final HkFragmentVideoStatementBinding invoke(View p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return HkFragmentVideoStatementBinding.bind(p0);
    }
}
