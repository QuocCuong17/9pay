package co.hyperverge.hyperkyc.ui;

import android.view.View;
import co.hyperverge.hyperkyc.databinding.HkFragmentVideoStatementV2Binding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoStatementV2Fragment.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class VideoStatementV2Fragment$binding$2 extends FunctionReferenceImpl implements Function1<View, HkFragmentVideoStatementV2Binding> {
    public static final VideoStatementV2Fragment$binding$2 INSTANCE = new VideoStatementV2Fragment$binding$2();

    VideoStatementV2Fragment$binding$2() {
        super(1, HkFragmentVideoStatementV2Binding.class, "bind", "bind(Landroid/view/View;)Lco/hyperverge/hyperkyc/databinding/HkFragmentVideoStatementV2Binding;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final HkFragmentVideoStatementV2Binding invoke(View p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return HkFragmentVideoStatementV2Binding.bind(p0);
    }
}
