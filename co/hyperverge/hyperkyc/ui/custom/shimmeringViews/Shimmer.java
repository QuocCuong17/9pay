package co.hyperverge.hyperkyc.ui.custom.shimmeringViews;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import com.tekartik.sqflite.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: Shimmer.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u000bJ)\u0010\f\u001a\u00020\u000b\"\f\b\u0000\u0010\r*\u00020\u000e*\u00020\u000f2\u0006\u0010\u0010\u001a\u0002H\r2\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\t¨\u0006\u0015"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/shimmeringViews/Shimmer;", "", "()V", "animator", "Landroid/animation/ObjectAnimator;", "animatorListener", "Landroid/animation/Animator$AnimatorListener;", "isAnimating", "", "()Z", Constant.PARAM_CANCEL, "", "start", "V", "Landroid/view/View;", "Lco/hyperverge/hyperkyc/ui/custom/shimmeringViews/ShimmerViewBase;", "shimmerView", "lifecycleScope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroid/view/View;Lkotlinx/coroutines/CoroutineScope;)V", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Shimmer {
    private static final long DEFAULT_DURATION = 1500;
    private static final int DEFAULT_REPEAT_COUNT = -1;
    private ObjectAnimator animator;
    private Animator.AnimatorListener animatorListener;

    public final <V extends View & ShimmerViewBase> void start(V shimmerView, CoroutineScope lifecycleScope) {
        Intrinsics.checkNotNullParameter(shimmerView, "shimmerView");
        Intrinsics.checkNotNullParameter(lifecycleScope, "lifecycleScope");
        if (isAnimating()) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(lifecycleScope, Dispatchers.getMain(), null, new Shimmer$start$1(shimmerView, lifecycleScope, this, null), 2, null);
    }

    public final void cancel() {
        ObjectAnimator objectAnimator = this.animator;
        if (objectAnimator != null) {
            Intrinsics.checkNotNull(objectAnimator);
            objectAnimator.cancel();
        }
    }

    private final boolean isAnimating() {
        ObjectAnimator objectAnimator = this.animator;
        if (objectAnimator != null) {
            Intrinsics.checkNotNull(objectAnimator);
            if (objectAnimator.isRunning()) {
                return true;
            }
        }
        return false;
    }
}
