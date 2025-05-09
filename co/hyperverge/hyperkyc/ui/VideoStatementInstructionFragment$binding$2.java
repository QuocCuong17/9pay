package co.hyperverge.hyperkyc.ui;

import android.view.View;
import co.hyperverge.hyperkyc.databinding.HkFragmentVideoStatementInstructionBinding;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoStatementInstructionFragment.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
/* synthetic */ class VideoStatementInstructionFragment$binding$2 extends FunctionReferenceImpl implements Function1<View, HkFragmentVideoStatementInstructionBinding> {
    public static final VideoStatementInstructionFragment$binding$2 INSTANCE = new VideoStatementInstructionFragment$binding$2();

    VideoStatementInstructionFragment$binding$2() {
        super(1, HkFragmentVideoStatementInstructionBinding.class, "bind", "bind(Landroid/view/View;)Lco/hyperverge/hyperkyc/databinding/HkFragmentVideoStatementInstructionBinding;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final HkFragmentVideoStatementInstructionBinding invoke(View p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return HkFragmentVideoStatementInstructionBinding.bind(p0);
    }
}
