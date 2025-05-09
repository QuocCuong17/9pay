package co.hyperverge.hyperkyc.ui.custom.shimmeringViews;

import co.hyperverge.hyperkyc.ui.custom.shimmeringViews.ShimmerViewHelper;
import kotlin.Metadata;

/* compiled from: ShimmerViewBase.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u0018\u0010\u000b\u001a\u00020\tX¦\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\n\"\u0004\b\f\u0010\r¨\u0006\u0012"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/shimmeringViews/ShimmerViewBase;", "", "gradientX", "", "getGradientX", "()F", "setGradientX", "(F)V", "isSetUp", "", "()Z", "isShimmering", "setShimmering", "(Z)V", "setAnimationSetupCallback", "", "callback", "Lco/hyperverge/hyperkyc/ui/custom/shimmeringViews/ShimmerViewHelper$AnimationSetupCallback;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ShimmerViewBase {
    float getGradientX();

    boolean isSetUp();

    boolean isShimmering();

    void setAnimationSetupCallback(ShimmerViewHelper.AnimationSetupCallback callback);

    void setGradientX(float f);

    void setShimmering(boolean z);
}
