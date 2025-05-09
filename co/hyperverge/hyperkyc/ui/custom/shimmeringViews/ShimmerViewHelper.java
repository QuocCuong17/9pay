package co.hyperverge.hyperkyc.ui.custom.shimmeringViews;

import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ShimmerViewHelper.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u0000 #2\u00020\u0001:\u0002\"#B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0019\u001a\u00020\nJ\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u001bJ\b\u0010\u001d\u001a\u00020\u001bH\u0002J\u0010\u0010\u001e\u001a\u00020\u001b2\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u000e\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010 \u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010!\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u0017R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000e\"\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/shimmeringViews/ShimmerViewHelper;", "", ViewHierarchyConstants.VIEW_KEY, "Landroid/view/View;", "paint", "Landroid/graphics/Paint;", "(Landroid/view/View;Landroid/graphics/Paint;)V", "callback", "Lco/hyperverge/hyperkyc/ui/custom/shimmeringViews/ShimmerViewHelper$AnimationSetupCallback;", "gradientX", "", "<set-?>", "", "isSetUp", "()Z", "isShimmering", "setShimmering", "(Z)V", "linearGradient", "Landroid/graphics/LinearGradient;", "linearGradientMatrix", "Landroid/graphics/Matrix;", "primaryColor", "", "reflectionColor", "getGradientX", "onDraw", "", "onSizeChanged", "resetLinearGradient", "setAnimationSetupCallback", "setGradientX", "setPrimaryColor", "setReflectionColor", "AnimationSetupCallback", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ShimmerViewHelper {
    private static final int DEFAULT_REFLECTION_COLOR = -1;
    private AnimationSetupCallback callback;
    private float gradientX;
    private boolean isSetUp;
    private boolean isShimmering;
    private LinearGradient linearGradient;
    private Matrix linearGradientMatrix;
    private final Paint paint;
    private int primaryColor;
    private int reflectionColor;
    private final View view;

    /* compiled from: ShimmerViewHelper.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/shimmeringViews/ShimmerViewHelper$AnimationSetupCallback;", "", "onSetupAnimation", "", TypedValues.AttributesType.S_TARGET, "Landroid/view/View;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface AnimationSetupCallback {
        void onSetupAnimation(View target);
    }

    public ShimmerViewHelper(View view, Paint paint) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(paint, "paint");
        this.view = view;
        this.paint = paint;
        this.linearGradientMatrix = new Matrix();
        this.reflectionColor = -1;
    }

    /* renamed from: isShimmering, reason: from getter */
    public final boolean getIsShimmering() {
        return this.isShimmering;
    }

    public final void setShimmering(boolean z) {
        this.isShimmering = z;
    }

    /* renamed from: isSetUp, reason: from getter */
    public final boolean getIsSetUp() {
        return this.isSetUp;
    }

    public final float getGradientX() {
        return this.gradientX;
    }

    public final void setGradientX(float gradientX) {
        this.gradientX = gradientX;
        this.view.invalidate();
    }

    public final void setAnimationSetupCallback(AnimationSetupCallback callback) {
        this.callback = callback;
    }

    public final void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
        if (this.isSetUp) {
            resetLinearGradient();
        }
    }

    public final void setReflectionColor(int reflectionColor) {
        this.reflectionColor = reflectionColor;
        if (this.isSetUp) {
            resetLinearGradient();
        }
    }

    private final void resetLinearGradient() {
        int i = this.primaryColor;
        LinearGradient linearGradient = new LinearGradient(-this.view.getWidth(), 0.0f, 0.0f, 0.0f, new int[]{i, this.reflectionColor, i}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP);
        this.linearGradient = linearGradient;
        this.paint.setShader(linearGradient);
    }

    public final void onSizeChanged() {
        resetLinearGradient();
        if (this.isSetUp) {
            return;
        }
        this.isSetUp = true;
        AnimationSetupCallback animationSetupCallback = this.callback;
        if (animationSetupCallback != null) {
            animationSetupCallback.onSetupAnimation(this.view);
        }
    }

    public final void onDraw() {
        if (this.isShimmering) {
            if (this.paint.getShader() == null) {
                this.paint.setShader(this.linearGradient);
            }
            this.linearGradientMatrix.setTranslate(2 * this.gradientX, 0.0f);
            LinearGradient linearGradient = this.linearGradient;
            if (linearGradient != null) {
                linearGradient.setLocalMatrix(this.linearGradientMatrix);
                return;
            }
            return;
        }
        this.paint.setShader(null);
    }
}
