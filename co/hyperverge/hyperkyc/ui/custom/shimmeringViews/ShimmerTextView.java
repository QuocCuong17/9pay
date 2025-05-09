package co.hyperverge.hyperkyc.ui.custom.shimmeringViews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.media3.exoplayer.upstream.CmcdData;
import co.hyperverge.hyperkyc.ui.custom.shimmeringViews.ShimmerViewHelper;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ShimmerTextView.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J(\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\nH\u0014J\u0012\u0010#\u001a\u00020\u001b2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u0010\u0010&\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020(H\u0016J\u0010\u0010&\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020\nH\u0016R$\u0010\f\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0014R$\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00138V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0014\"\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/shimmeringViews/ShimmerTextView;", "Landroidx/appcompat/widget/AppCompatTextView;", "Lco/hyperverge/hyperkyc/ui/custom/shimmeringViews/ShimmerViewBase;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "gradientX", "", "getGradientX", "()F", "setGradientX", "(F)V", "isSetUp", "", "()Z", "isShimmering", "setShimmering", "(Z)V", "shimmerViewHelper", "Lco/hyperverge/hyperkyc/ui/custom/shimmeringViews/ShimmerViewHelper;", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "onSizeChanged", "w", CmcdData.Factory.STREAMING_FORMAT_HLS, "oldw", "oldh", "setAnimationSetupCallback", "callback", "Lco/hyperverge/hyperkyc/ui/custom/shimmeringViews/ShimmerViewHelper$AnimationSetupCallback;", "setTextColor", "colors", "Landroid/content/res/ColorStateList;", "color", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ShimmerTextView extends AppCompatTextView implements ShimmerViewBase {
    private ShimmerViewHelper shimmerViewHelper;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShimmerTextView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        TextPaint paint = getPaint();
        Intrinsics.checkNotNullExpressionValue(paint, "paint");
        ShimmerViewHelper shimmerViewHelper = new ShimmerViewHelper(this, paint);
        this.shimmerViewHelper = shimmerViewHelper;
        Intrinsics.checkNotNull(shimmerViewHelper);
        shimmerViewHelper.setPrimaryColor(getCurrentTextColor());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShimmerTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        TextPaint paint = getPaint();
        Intrinsics.checkNotNullExpressionValue(paint, "paint");
        ShimmerViewHelper shimmerViewHelper = new ShimmerViewHelper(this, paint);
        this.shimmerViewHelper = shimmerViewHelper;
        Intrinsics.checkNotNull(shimmerViewHelper);
        shimmerViewHelper.setPrimaryColor(getCurrentTextColor());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShimmerTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        TextPaint paint = getPaint();
        Intrinsics.checkNotNullExpressionValue(paint, "paint");
        ShimmerViewHelper shimmerViewHelper = new ShimmerViewHelper(this, paint);
        this.shimmerViewHelper = shimmerViewHelper;
        Intrinsics.checkNotNull(shimmerViewHelper);
        shimmerViewHelper.setPrimaryColor(getCurrentTextColor());
    }

    @Override // co.hyperverge.hyperkyc.ui.custom.shimmeringViews.ShimmerViewBase
    public float getGradientX() {
        ShimmerViewHelper shimmerViewHelper = this.shimmerViewHelper;
        Intrinsics.checkNotNull(shimmerViewHelper);
        return shimmerViewHelper.getGradientX();
    }

    @Override // co.hyperverge.hyperkyc.ui.custom.shimmeringViews.ShimmerViewBase
    public void setGradientX(float f) {
        ShimmerViewHelper shimmerViewHelper = this.shimmerViewHelper;
        Intrinsics.checkNotNull(shimmerViewHelper);
        shimmerViewHelper.setGradientX(f);
    }

    @Override // co.hyperverge.hyperkyc.ui.custom.shimmeringViews.ShimmerViewBase
    public boolean isShimmering() {
        ShimmerViewHelper shimmerViewHelper = this.shimmerViewHelper;
        Intrinsics.checkNotNull(shimmerViewHelper);
        return shimmerViewHelper.getIsShimmering();
    }

    @Override // co.hyperverge.hyperkyc.ui.custom.shimmeringViews.ShimmerViewBase
    public void setShimmering(boolean z) {
        ShimmerViewHelper shimmerViewHelper = this.shimmerViewHelper;
        if (shimmerViewHelper == null) {
            return;
        }
        shimmerViewHelper.setShimmering(z);
    }

    @Override // co.hyperverge.hyperkyc.ui.custom.shimmeringViews.ShimmerViewBase
    public boolean isSetUp() {
        ShimmerViewHelper shimmerViewHelper = this.shimmerViewHelper;
        Intrinsics.checkNotNull(shimmerViewHelper);
        return shimmerViewHelper.getIsSetUp();
    }

    @Override // co.hyperverge.hyperkyc.ui.custom.shimmeringViews.ShimmerViewBase
    public void setAnimationSetupCallback(ShimmerViewHelper.AnimationSetupCallback callback) {
        ShimmerViewHelper shimmerViewHelper = this.shimmerViewHelper;
        Intrinsics.checkNotNull(shimmerViewHelper);
        shimmerViewHelper.setAnimationSetupCallback(callback);
    }

    @Override // android.widget.TextView
    public void setTextColor(int color) {
        super.setTextColor(color);
        ShimmerViewHelper shimmerViewHelper = this.shimmerViewHelper;
        if (shimmerViewHelper != null) {
            Intrinsics.checkNotNull(shimmerViewHelper);
            shimmerViewHelper.setPrimaryColor(getCurrentTextColor());
        }
    }

    @Override // android.widget.TextView
    public void setTextColor(ColorStateList colors) {
        Intrinsics.checkNotNullParameter(colors, "colors");
        super.setTextColor(colors);
        ShimmerViewHelper shimmerViewHelper = this.shimmerViewHelper;
        if (shimmerViewHelper != null) {
            Intrinsics.checkNotNull(shimmerViewHelper);
            shimmerViewHelper.setPrimaryColor(getCurrentTextColor());
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        ShimmerViewHelper shimmerViewHelper = this.shimmerViewHelper;
        if (shimmerViewHelper != null) {
            Intrinsics.checkNotNull(shimmerViewHelper);
            shimmerViewHelper.onSizeChanged();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        ShimmerViewHelper shimmerViewHelper = this.shimmerViewHelper;
        if (shimmerViewHelper != null) {
            Intrinsics.checkNotNull(shimmerViewHelper);
            shimmerViewHelper.onDraw();
        }
        super.onDraw(canvas);
    }
}
