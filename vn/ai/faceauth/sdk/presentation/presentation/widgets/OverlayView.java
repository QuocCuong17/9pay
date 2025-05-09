package vn.ai.faceauth.sdk.presentation.presentation.widgets;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.media3.extractor.ts.PsExtractor;
import io.sentry.Session;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.R;
import vn.ai.faceauth.sdk.presentation.domain.configs.SDKConfig;
import vn.ai.faceauth.sdk.presentation.navigation.AuthenticationID;

@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0019\b\u0000\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0006\u00101\u001a\u000202J\b\u00103\u001a\u000204H\u0002J\b\u00105\u001a\u00020\u0014H\u0002J\u000e\u00106\u001a\u0002022\u0006\u00107\u001a\u00020\u0014J(\u00108\u001a\u0002022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u00109\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010:\u001a\u00020\u0014H\u0002J@\u0010;\u001a\u0002022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010<\u001a\u00020\n2\u0006\u0010=\u001a\u00020\n2\u0006\u0010>\u001a\u00020\n2\u0006\u0010?\u001a\u00020\n2\u0006\u0010@\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\fH\u0002J\u000e\u0010A\u001a\u0002022\u0006\u00109\u001a\u00020\bJ\u0018\u0010B\u001a\u0004\u0018\u00010C2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010D\u001a\u00020\bJ\b\u0010E\u001a\u00020FH\u0002J\u0006\u0010G\u001a\u000202J\u0006\u0010H\u001a\u000202J\u0006\u0010I\u001a\u000202J.\u0010J\u001a\u0002022\b\b\u0003\u0010K\u001a\u00020L2\b\b\u0003\u0010M\u001a\u00020L2\b\b\u0003\u0010N\u001a\u00020L2\b\b\u0003\u0010O\u001a\u00020LJ\u0006\u0010P\u001a\u00020\u0017J\u0018\u0010Q\u001a\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010R\u001a\u00020\bH\u0002J\u0010\u0010S\u001a\u0002022\u0006\u0010\u000e\u001a\u00020\u000fH\u0015J\b\u0010T\u001a\u00020\bH\u0002J\u0006\u0010U\u001a\u000202J\u000e\u0010V\u001a\u0002022\u0006\u0010W\u001a\u00020\u0017J\u0006\u0010X\u001a\u000202J\u0006\u0010Y\u001a\u000202J\u000e\u0010Z\u001a\u0002022\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010[\u001a\u000202J\u001e\u0010\\\u001a\u0002022\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010]\u001a\u00020\nJ\b\u0010^\u001a\u000202H\u0002J\u0006\u0010_\u001a\u000202J\b\u0010`\u001a\u000202H\u0002J\u0006\u0010a\u001a\u000202J\u000e\u0010b\u001a\u0002022\u0006\u0010c\u001a\u00020\bJ\u0006\u0010d\u001a\u000202R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 R\u000e\u0010$\u001a\u00020%X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010(\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b)\u0010*R\u000e\u0010-\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006e"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/widgets/OverlayView;", "Landroid/view/View;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "TAG", "", "angle1", "", "blurPaint", "Landroid/graphics/Paint;", "borderPaint", "canvas", "Landroid/graphics/Canvas;", "defaultOvalColor", "", "eraser", "faceResult", "Landroid/graphics/RectF;", "failOvalColor", "isFinished", "", "isShowProcess", "onCallbackOverlayListener", "Lvn/ai/faceauth/sdk/presentation/presentation/widgets/OnCallbackOverlayListener;", "paint", "rectZoomIn", "getRectZoomIn", "()Landroid/graphics/RectF;", "setRectZoomIn", "(Landroid/graphics/RectF;)V", "rectZoomOut", "getRectZoomOut", "setRectZoomOut", "scaleAnimator", "Landroid/animation/ValueAnimator;", "scaleFactor", "successOvalColor", "sweepAngle", "getSweepAngle", "()F", "sweepAngle$delegate", "Lkotlin/Lazy;", "textCenter", "textPaint", "transparentBackground", "verticalMargin", "changeColorOvalFailed", "", "createRect", "Landroid/graphics/Rect;", "createRectF", "drawFace", "face", "drawMultiLineTextCentered", "text", "rectF", "drawRotatingHalfOval", "centerX", "centerY", "a", "b", "angle", "drawTextCenter", "getBitmapFromAsset", "Landroid/graphics/Bitmap;", "filePath", "getConfig", "Lvn/ai/faceauth/sdk/presentation/domain/configs/SDKConfig;", "hideBlur", "hideProcess", "hideTextCenter", Session.JsonKeys.INIT, "borderColorRes", "", "backgroundColorRes", "strokeWidthRes", "verticalMarginRes", "isStep1", "listFilesInAssets", "fileName", "onDraw", "ovalColor", "reset", "scale", "isPass", "setConfigColor", "setFinished", "setOnCallbackOverlayListener", "setSystemStop", "setTextSizeInSP", "textSizeSP", "setupAnimator", "showProcess", "startRotation", "stopAnimation", "updateBackgroundColor", "colorCode", "zoomOut", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class OverlayView extends View {
    private final String TAG;
    private float angle1;
    private Paint blurPaint;
    private Paint borderPaint;
    private Canvas canvas;
    private Object defaultOvalColor;
    private Paint eraser;
    private RectF faceResult;
    private Object failOvalColor;
    private boolean isFinished;
    private boolean isShowProcess;
    private OnCallbackOverlayListener onCallbackOverlayListener;
    private Paint paint;
    private RectF rectZoomIn;
    private RectF rectZoomOut;
    private ValueAnimator scaleAnimator;
    private float scaleFactor;
    private Object successOvalColor;

    /* renamed from: sweepAngle$delegate, reason: from kotlin metadata */
    private final Lazy sweepAngle;
    private String textCenter;
    private final Paint textPaint;
    private Paint transparentBackground;
    private float verticalMargin;

    /* JADX WARN: Multi-variable type inference failed */
    public OverlayView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OverlayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        String rnigpa = tfwhgw.rnigpa(462);
        this.scaleFactor = 0.82f;
        this.textCenter = "";
        this.TAG = tfwhgw.rnigpa(463);
        Paint paint = new Paint(1);
        float applyDimension = TypedValue.applyDimension(2, getConfig().getTextSize() > 0 ? getConfig().getTextSize() : context.getResources().getDimension(R.dimen.facesdk_text_size), context.getResources().getDisplayMetrics());
        paint.setColor(-1);
        paint.setTextSize(applyDimension);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setLinearText(true);
        if (getConfig().getFontName().length() > 0) {
            try {
                paint.setTypeface(Typeface.createFromAsset(context.getAssets(), rnigpa + getConfig().getFontName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.textPaint = paint;
        this.sweepAngle = LazyKt.lazy(new Function0<Float>() { // from class: vn.ai.faceauth.sdk.presentation.presentation.widgets.OverlayView$sweepAngle$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                TypedValue typedValue = new TypedValue();
                OverlayView.this.getResources().getValue(R.dimen.facesdk_overlay_sweep_angle, typedValue, true);
                return Float.valueOf(typedValue.getFloat());
            }
        });
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setColor(-16776961);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(10.0f);
        this.paint = paint2;
        setupAnimator();
        setLayerType(1, null);
        startRotation();
    }

    public /* synthetic */ OverlayView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    private final Rect createRect() {
        return new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    private final RectF createRectF() {
        float width = getWidth();
        float f = 0.7f * width;
        float f2 = (4.0f * f) / 3.0f;
        float f3 = 2;
        float f4 = (width - f) / f3;
        float height = ((getHeight() - f2) / f3) - this.verticalMargin;
        return new RectF(f4, height, f + f4, f2 + height);
    }

    private final void drawMultiLineTextCentered(Canvas canvas, String text, Paint paint, RectF rectF) {
        int i = 0;
        List split$default = StringsKt.split$default((CharSequence) text, new String[]{tfwhgw.rnigpa(464)}, false, 0, 6, (Object) null);
        float f = paint.getFontMetrics().descent - paint.getFontMetrics().ascent;
        float size = split$default.size();
        float centerY = rectF.centerY();
        float f2 = 2;
        float f3 = (size * f) / f2;
        float f4 = f / f2;
        for (Object obj : split$default) {
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            canvas.drawText((String) obj, rectF.centerX(), (i * f) + (centerY - f3) + f4, paint);
            i++;
        }
    }

    private final void drawRotatingHalfOval(Canvas canvas, float centerX, float centerY, float a, float b, float angle, Paint paint) {
        Path path = new Path();
        double radians = Math.toRadians(angle);
        path.moveTo((((float) Math.cos(radians)) * a) + centerX, (((float) Math.sin(radians)) * b) + centerY);
        for (int i = 0; i < 121; i++) {
            double radians2 = Math.toRadians(i + angle);
            path.lineTo((((float) Math.cos(radians2)) * a) + centerX, (((float) Math.sin(radians2)) * b) + centerY);
        }
        double d = radians + 3.141592653589793d;
        path.moveTo((((float) Math.cos(d)) * a) + centerX, (((float) Math.sin(d)) * b) + centerY);
        for (int i2 = 0; i2 < 121; i2++) {
            double radians3 = Math.toRadians(180 + angle + i2);
            path.lineTo((((float) Math.cos(radians3)) * a) + centerX, (((float) Math.sin(radians3)) * b) + centerY);
        }
        canvas.drawPath(path, paint);
    }

    private final SDKConfig getConfig() {
        return AuthenticationID.INSTANCE.getSdkConfig$trueface_release();
    }

    private final float getSweepAngle() {
        return ((Number) this.sweepAngle.getValue()).floatValue();
    }

    public static /* synthetic */ void init$default(OverlayView overlayView, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = R.color.facesdk_overlay_border;
        }
        if ((i5 & 2) != 0) {
            i2 = R.color.facesdk_overlay_background;
        }
        if ((i5 & 4) != 0) {
            i3 = R.dimen.facesdk_overlay_border_stroke;
        }
        if ((i5 & 8) != 0) {
            i4 = R.dimen.facesdk_overlay_top_margin;
        }
        overlayView.init(i, i2, i3, i4);
    }

    private final boolean listFilesInAssets(Context context, String fileName) {
        try {
            String[] list = context.getAssets().list("");
            if (list == null) {
                return false;
            }
            Iterator it = ArrayIteratorKt.iterator(list);
            while (it.hasNext()) {
                if (Intrinsics.areEqual((String) it.next(), fileName)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            Log.e(tfwhgw.rnigpa(465), tfwhgw.rnigpa(466), e);
            return false;
        }
    }

    private final String ovalColor() {
        return getConfig().getOvalColor().length() > 0 ? getConfig().getOvalColor() : getConfig().getPrimaryColor();
    }

    private final void setupAnimator() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.82f, 1.107f);
        this.scaleAnimator = ofFloat;
        String rnigpa = tfwhgw.rnigpa(467);
        ValueAnimator valueAnimator = null;
        if (ofFloat == null) {
            Intrinsics.throwUninitializedPropertyAccessException(rnigpa);
            ofFloat = null;
        }
        ofFloat.setDuration(400L);
        ValueAnimator valueAnimator2 = this.scaleAnimator;
        if (valueAnimator2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(rnigpa);
            valueAnimator2 = null;
        }
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: vn.ai.faceauth.sdk.presentation.presentation.widgets.OverlayView$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator3) {
                OverlayView.m2990setupAnimator$lambda16(OverlayView.this, valueAnimator3);
            }
        });
        ValueAnimator valueAnimator3 = this.scaleAnimator;
        if (valueAnimator3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(rnigpa);
        } else {
            valueAnimator = valueAnimator3;
        }
        valueAnimator.addListener(new Animator.AnimatorListener() { // from class: vn.ai.faceauth.sdk.presentation.presentation.widgets.OverlayView$setupAnimator$2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                OnCallbackOverlayListener onCallbackOverlayListener;
                OverlayView overlayView = OverlayView.this;
                overlayView.setRectZoomOut(overlayView.getRectZoomIn());
                onCallbackOverlayListener = OverlayView.this.onCallbackOverlayListener;
                if (onCallbackOverlayListener != null) {
                    onCallbackOverlayListener.onZoomOutAnimationEnd();
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animation) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                OnCallbackOverlayListener onCallbackOverlayListener;
                onCallbackOverlayListener = OverlayView.this.onCallbackOverlayListener;
                if (onCallbackOverlayListener != null) {
                    onCallbackOverlayListener.onZoomOutAnimationStart();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupAnimator$lambda-16, reason: not valid java name */
    public static final void m2990setupAnimator$lambda16(OverlayView overlayView, ValueAnimator valueAnimator) {
        Object animatedValue = valueAnimator.getAnimatedValue();
        if (animatedValue == null) {
            throw new NullPointerException(tfwhgw.rnigpa(468));
        }
        overlayView.scaleFactor = ((Float) animatedValue).floatValue();
        overlayView.invalidate();
    }

    private final void startRotation() {
        final long j = 16;
        post(new Runnable() { // from class: vn.ai.faceauth.sdk.presentation.presentation.widgets.OverlayView$startRotation$1
            @Override // java.lang.Runnable
            public void run() {
                float f;
                OverlayView overlayView = OverlayView.this;
                f = overlayView.angle1;
                overlayView.angle1 = f + 5.0f;
                OverlayView.this.invalidate();
                OverlayView.this.postDelayed(this, j);
            }
        });
    }

    public final void changeColorOvalFailed() {
        Paint paint = this.borderPaint;
        if (paint != null) {
            paint.setColor(Color.parseColor((String) this.failOvalColor));
        }
        invalidate();
    }

    public final void drawFace(RectF face) {
        this.faceResult = face;
        invalidate();
    }

    public final void drawTextCenter(String text) {
        this.textCenter = text;
        invalidate();
    }

    public final Bitmap getBitmapFromAsset(Context context, String filePath) {
        try {
            return BitmapFactory.decodeStream(context.getAssets().open(filePath));
        } catch (IOException unused) {
            return null;
        }
    }

    public final RectF getRectZoomIn() {
        return this.rectZoomIn;
    }

    public final RectF getRectZoomOut() {
        return this.rectZoomOut;
    }

    public final void hideBlur() {
        this.isFinished = false;
        this.isShowProcess = false;
        invalidate();
    }

    public final void hideProcess() {
        this.isShowProcess = false;
        invalidate();
    }

    public final void hideTextCenter() {
        this.textCenter = "";
        invalidate();
    }

    public final void init(int borderColorRes, int backgroundColorRes, int strokeWidthRes, int verticalMarginRes) {
        TypedValue typedValue = new TypedValue();
        getResources().getValue(verticalMarginRes, typedValue, true);
        this.verticalMargin = typedValue.getFloat();
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), backgroundColorRes));
        paint.setStyle(Paint.Style.FILL);
        this.transparentBackground = paint;
        Paint paint2 = new Paint();
        paint2.setColor(ContextCompat.getColor(getContext(), borderColorRes));
        paint2.setStrokeWidth(getResources().getDimension(strokeWidthRes));
        paint2.setStyle(Paint.Style.STROKE);
        this.borderPaint = paint2;
        Paint paint3 = new Paint(1);
        paint3.setColor(getResources().getColor(R.color.facesdk_white_70));
        paint3.setStyle(Paint.Style.FILL);
        paint3.setMaskFilter(new BlurMaskFilter(10.0f, BlurMaskFilter.Blur.NORMAL));
        this.blurPaint = paint3;
        Paint paint4 = new Paint();
        paint4.setAntiAlias(true);
        paint4.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        paint4.setStyle(Paint.Style.FILL);
        this.eraser = paint4;
    }

    public final boolean isStep1() {
        return this.scaleFactor == 0.82f;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        RectF rectF;
        Paint paint;
        super.onDraw(canvas);
        this.canvas = canvas;
        Rect createRect = createRect();
        Paint paint2 = this.transparentBackground;
        if (paint2 != null) {
            canvas.drawRect(createRect, paint2);
        }
        RectF createRectF = createRectF();
        this.rectZoomIn = createRectF;
        if (createRectF != null) {
            float width = createRectF.width();
            float f = this.scaleFactor;
            float height = createRectF.height();
            float f2 = this.scaleFactor;
            float f3 = 2;
            float width2 = (createRectF.width() - (width * f)) / f3;
            float height2 = (createRectF.height() - (height * f2)) / f3;
            createRectF.set(createRectF.left + width2, createRectF.top + height2, createRectF.right - width2, createRectF.bottom - height2);
            Paint paint3 = this.borderPaint;
            if (paint3 != null) {
                canvas.drawArc(createRectF, 0.0f, getSweepAngle(), true, paint3);
            }
            Paint paint4 = this.eraser;
            if (paint4 != null) {
                canvas.drawArc(createRectF, 0.0f, getSweepAngle(), true, paint4);
            }
            if (this.isFinished && (paint = this.blurPaint) != null) {
                canvas.drawOval(createRectF, paint);
            }
            if (this.textCenter.length() > 0) {
                Context context = getContext();
                String rnigpa = tfwhgw.rnigpa(469);
                if (listFilesInAssets(context, rnigpa)) {
                    Bitmap bitmapFromAsset = getBitmapFromAsset(getContext(), rnigpa);
                    if (bitmapFromAsset != null) {
                        float f4 = 120;
                        canvas.drawBitmap(Bitmap.createScaledBitmap(bitmapFromAsset, PsExtractor.VIDEO_STREAM_MASK, PsExtractor.VIDEO_STREAM_MASK, false), new Rect(0, 0, PsExtractor.VIDEO_STREAM_MASK, PsExtractor.VIDEO_STREAM_MASK), new RectF(createRectF.centerX() - f4, createRectF.centerY() - f4, createRectF.centerX() + f4, createRectF.centerY() + f4), (Paint) null);
                    }
                    rectF = new RectF(createRectF.left, createRectF.centerY() + 120, createRectF.right, createRectF.bottom);
                } else {
                    rectF = createRectF;
                }
                drawMultiLineTextCentered(canvas, this.textCenter, this.textPaint, rectF);
            }
            RectF rectF2 = this.faceResult;
            if (rectF2 != null) {
                Paint paint5 = new Paint();
                paint5.setStrokeWidth(10.0f);
                paint5.setColor(0);
                paint5.setStyle(Paint.Style.STROKE);
                paint5.setColor(-16777216);
                canvas.drawRect(rectF2, paint5);
            }
            if (this.isShowProcess) {
                float f5 = 20;
                drawRotatingHalfOval(canvas, createRectF.centerX(), createRectF.centerY(), (createRectF.width() / f3) - f5, (createRectF.height() / f3) - f5, this.angle1, this.paint);
            }
        }
    }

    public final void reset() {
        Paint paint = this.borderPaint;
        if (paint != null) {
            paint.setColor(Color.parseColor((String) this.defaultOvalColor));
        }
        this.scaleFactor = 0.82f;
        invalidate();
        this.rectZoomOut = this.rectZoomIn;
    }

    public final void scale(boolean isPass) {
        Paint paint = this.borderPaint;
        if (paint != null) {
            paint.setColor(Color.parseColor((String) (isPass ? this.successOvalColor : this.defaultOvalColor)));
        }
        invalidate();
    }

    public final void setConfigColor() {
        this.defaultOvalColor = ovalColor();
        this.successOvalColor = ovalColor();
        this.failOvalColor = getConfig().getErrorColor();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor(getConfig().getSecondaryColor()));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10.0f);
        this.paint = paint;
        Paint paint2 = this.borderPaint;
        if (paint2 != null) {
            paint2.setColor(Color.parseColor((String) this.defaultOvalColor));
        }
        invalidate();
    }

    public final void setFinished() {
        scale(true);
        this.isFinished = true;
        invalidate();
    }

    public final void setOnCallbackOverlayListener(OnCallbackOverlayListener onCallbackOverlayListener) {
        this.onCallbackOverlayListener = onCallbackOverlayListener;
    }

    public final void setRectZoomIn(RectF rectF) {
        this.rectZoomIn = rectF;
    }

    public final void setRectZoomOut(RectF rectF) {
        this.rectZoomOut = rectF;
    }

    public final void setSystemStop() {
        scale(true);
        this.isFinished = true;
        invalidate();
    }

    public final void setTextSizeInSP(Context context, Paint paint, float textSizeSP) {
        paint.setTextSize(TypedValue.applyDimension(2, textSizeSP, context.getResources().getDisplayMetrics()));
    }

    public final void showProcess() {
        this.isShowProcess = true;
        invalidate();
    }

    public final void stopAnimation() {
        ValueAnimator valueAnimator = this.scaleAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException(tfwhgw.rnigpa(470));
            valueAnimator = null;
        }
        valueAnimator.cancel();
    }

    public final void updateBackgroundColor(String colorCode) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor(colorCode));
        paint.setStyle(Paint.Style.FILL);
        this.transparentBackground = paint;
        invalidate();
    }

    public final void zoomOut() {
        ValueAnimator valueAnimator = this.scaleAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException(tfwhgw.rnigpa(471));
            valueAnimator = null;
        }
        valueAnimator.start();
    }
}
