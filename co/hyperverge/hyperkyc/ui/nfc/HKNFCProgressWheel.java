package co.hyperverge.hyperkyc.ui.nfc;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.media3.exoplayer.upstream.CmcdData;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt;
import io.sentry.Session;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.apache.commons.io.IOUtils;

/* compiled from: HKNFCProgressWheel.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0018\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0006\u00103\u001a\u00020\bJ\u0006\u00104\u001a\u00020\bJ\u0006\u00105\u001a\u00020\bJ\u0006\u00106\u001a\u00020\bJ\u0006\u00107\u001a\u00020\bJ\u0006\u00108\u001a\u00020\u0017J\b\u00109\u001a\u00020\bH\u0016J\b\u0010:\u001a\u00020\bH\u0016J\b\u0010;\u001a\u00020\bH\u0016J\b\u0010<\u001a\u00020\bH\u0016J\u0006\u0010=\u001a\u00020\bJ\u0006\u0010>\u001a\u00020\bJ\u0006\u0010?\u001a\u00020\bJ\u0012\u0010@\u001a\u00020A2\b\b\u0002\u0010B\u001a\u00020\bH\u0007J\u0010\u0010C\u001a\u00020A2\u0006\u0010D\u001a\u00020EH\u0014J\u0018\u0010F\u001a\u00020A2\u0006\u0010G\u001a\u00020\b2\u0006\u0010H\u001a\u00020\bH\u0014J(\u0010I\u001a\u00020A2\u0006\u0010J\u001a\u00020\b2\u0006\u0010K\u001a\u00020\b2\u0006\u0010L\u001a\u00020\b2\u0006\u0010M\u001a\u00020\bH\u0014J\u0010\u0010N\u001a\u00020A2\u0006\u0010O\u001a\u00020PH\u0002J\u0006\u0010Q\u001a\u00020AJ\b\u0010R\u001a\u00020AH\u0002J\u000e\u0010S\u001a\u00020A2\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010T\u001a\u00020A2\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010U\u001a\u00020A2\u0006\u0010\f\u001a\u00020\bJ\u000e\u0010V\u001a\u00020A2\u0006\u0010\u000f\u001a\u00020\bJ\u000e\u0010W\u001a\u00020A2\u0006\u0010\u0014\u001a\u00020\bJ\u000e\u0010X\u001a\u00020A2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010Y\u001a\u00020A2\u0006\u0010\"\u001a\u00020\bJ\u000e\u0010Z\u001a\u00020A2\u0006\u0010#\u001a\u00020\bJ\u000e\u0010[\u001a\u00020A2\u0006\u0010$\u001a\u00020\bJ\u000e\u0010\\\u001a\u00020A2\u0006\u0010%\u001a\u00020\bJ\u000e\u0010]\u001a\u00020A2\u0006\u0010^\u001a\u00020\bJ\u000e\u0010_\u001a\u00020A2\u0006\u0010'\u001a\u00020\bJ\u000e\u0010`\u001a\u00020A2\u0006\u0010)\u001a\u00020\bJ\u0010\u0010a\u001a\u00020A2\b\u0010/\u001a\u0004\u0018\u00010-J\u000e\u0010b\u001a\u00020A2\u0006\u00100\u001a\u00020\bJ\u000e\u0010c\u001a\u00020A2\u0006\u00102\u001a\u00020\bJ\b\u0010d\u001a\u00020AH\u0002J\b\u0010e\u001a\u00020AH\u0002J\u0006\u0010f\u001a\u00020AJ\u0006\u0010g\u001a\u00020AR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,X\u0082\u000e¢\u0006\u0004\n\u0002\u0010.R\u0010\u0010/\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006h"}, d2 = {"Lco/hyperverge/hyperkyc/ui/nfc/HKNFCProgressWheel;", "Landroid/view/View;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "barColor", "", "barLength", "barPaint", "Landroid/graphics/Paint;", "barWidth", "circleBounds", "Landroid/graphics/RectF;", "circleColor", "circleInnerContour", "circleOuterContour", "circlePaint", "circleRadius", "contourColor", "contourPaint", "contourSize", "", "delayMillis", "fullRadius", "innerCircleBounds", "isSpinning", "", "()Z", "setSpinning", "(Z)V", "layoutHeight", "layoutWidth", "paddingBottom", "paddingLeft", "paddingRight", "paddingTop", "progress", "rimColor", "rimPaint", "rimWidth", "spinSpeed", "splitText", "", "", "[Ljava/lang/String;", "text", "textColor", "textPaint", "textSize", "getBarColor", "getBarLength", "getBarWidth", "getCircleColor", "getContourColor", "getContourSize", "getPaddingBottom", "getPaddingLeft", "getPaddingRight", "getPaddingTop", "getProgress", "getRimWidth", "getTextSize", "incrementProgress", "", "amount", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onSizeChanged", "newWidth", "newHeight", "oldWidth", "oldHeight", "parseAttributes", "a", "Landroid/content/res/TypedArray;", "resetCount", "scheduleRedraw", "setBarColor", "setBarLength", "setBarWidth", "setCircleColor", "setContourColor", "setContourSize", "setPaddingBottom", "setPaddingLeft", "setPaddingRight", "setPaddingTop", "setProgress", CmcdData.Factory.OBJECT_TYPE_INIT_SEGMENT, "setRimColor", "setRimWidth", "setText", "setTextColor", "setTextSize", "setupBounds", "setupPaints", "startSpinning", "stopSpinning", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HKNFCProgressWheel extends View {
    private int barColor;
    private int barLength;
    private final Paint barPaint;
    private int barWidth;
    private RectF circleBounds;
    private int circleColor;
    private RectF circleInnerContour;
    private RectF circleOuterContour;
    private final Paint circlePaint;
    private int circleRadius;
    private int contourColor;
    private final Paint contourPaint;
    private float contourSize;
    private int delayMillis;
    private int fullRadius;
    private RectF innerCircleBounds;
    private boolean isSpinning;
    private int layoutHeight;
    private int layoutWidth;
    private int paddingBottom;
    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    private float progress;
    private int rimColor;
    private final Paint rimPaint;
    private int rimWidth;
    private float spinSpeed;
    private String[] splitText;
    private String text;
    private int textColor;
    private final Paint textPaint;
    private int textSize;

    public final void incrementProgress() {
        incrementProgress$default(this, 0, 1, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKNFCProgressWheel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.fullRadius = 100;
        this.circleRadius = 80;
        this.barLength = 60;
        this.barWidth = 20;
        this.rimWidth = 20;
        this.textSize = 20;
        this.paddingTop = 5;
        this.paddingBottom = 5;
        this.paddingLeft = 5;
        this.paddingRight = 5;
        this.barColor = -1442840576;
        this.contourColor = -1442840576;
        this.rimColor = -1428300323;
        this.textColor = -16777216;
        this.barPaint = new Paint();
        this.circlePaint = new Paint();
        this.rimPaint = new Paint();
        this.textPaint = new Paint();
        this.contourPaint = new Paint();
        this.innerCircleBounds = new RectF();
        this.circleBounds = new RectF();
        this.circleOuterContour = new RectF();
        this.circleInnerContour = new RectF();
        this.spinSpeed = 3.5f;
        this.delayMillis = 1;
        this.text = "";
        this.splitText = new String[0];
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.HKNFCProgressWheel);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.obtainStyledAttr…ogressWheel\n            )");
        parseAttributes(obtainStyledAttributes);
    }

    /* renamed from: isSpinning, reason: from getter */
    public final boolean getIsSpinning() {
        return this.isSpinning;
    }

    public final void setSpinning(boolean z) {
        this.isSpinning = z;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int paddingTop = (measuredHeight - getPaddingTop()) - getPaddingBottom();
        int mode = View.MeasureSpec.getMode(heightMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(widthMeasureSpec);
        if (mode == 0 || mode2 == 0) {
            paddingLeft = Math.max(paddingTop, paddingLeft);
        } else if (paddingLeft > paddingTop) {
            paddingLeft = paddingTop;
        }
        setMeasuredDimension(getPaddingLeft() + paddingLeft + getPaddingRight(), paddingLeft + getPaddingTop() + getPaddingBottom());
    }

    @Override // android.view.View
    protected void onSizeChanged(int newWidth, int newHeight, int oldWidth, int oldHeight) {
        super.onSizeChanged(newWidth, newHeight, oldWidth, oldHeight);
        this.layoutWidth = newWidth;
        this.layoutHeight = newHeight;
        setupBounds();
        setupPaints();
        invalidate();
    }

    private final void setupPaints() {
        this.barPaint.setColor(this.barColor);
        this.barPaint.setAntiAlias(true);
        this.barPaint.setStyle(Paint.Style.STROKE);
        this.barPaint.setStrokeWidth(this.barWidth);
        this.rimPaint.setColor(this.rimColor);
        this.rimPaint.setAntiAlias(true);
        this.rimPaint.setStyle(Paint.Style.STROKE);
        this.rimPaint.setStrokeWidth(this.rimWidth);
        this.circlePaint.setColor(this.circleColor);
        this.circlePaint.setAntiAlias(true);
        this.circlePaint.setStyle(Paint.Style.FILL);
        this.textPaint.setColor(this.textColor);
        this.textPaint.setStyle(Paint.Style.FILL);
        this.textPaint.setAntiAlias(true);
        this.textPaint.setTextSize(this.textSize);
        HyperSnapBridgeKt.getUiConfigUtil().customiseNfcProgressTextView(this.textPaint);
        this.contourPaint.setColor(this.contourColor);
        this.contourPaint.setAntiAlias(true);
        this.contourPaint.setStyle(Paint.Style.STROKE);
        this.contourPaint.setStrokeWidth(this.contourSize);
    }

    private final void setupBounds() {
        int min = Math.min(this.layoutWidth, this.layoutHeight);
        int i = this.layoutWidth - min;
        int i2 = (this.layoutHeight - min) / 2;
        this.paddingTop = getPaddingTop() + i2;
        this.paddingBottom = getPaddingBottom() + i2;
        int i3 = i / 2;
        this.paddingLeft = getPaddingLeft() + i3;
        this.paddingRight = getPaddingRight() + i3;
        int width = getWidth();
        int height = getHeight();
        float f = this.paddingLeft;
        int i4 = this.barWidth;
        this.innerCircleBounds = new RectF(f + (i4 * 1.5f), this.paddingTop + (i4 * 1.5f), (width - this.paddingRight) - (i4 * 1.5f), (height - this.paddingBottom) - (i4 * 1.5f));
        int i5 = this.paddingLeft;
        int i6 = this.barWidth;
        this.circleBounds = new RectF(i5 + i6, this.paddingTop + i6, (width - this.paddingRight) - i6, (height - this.paddingBottom) - i6);
        this.circleInnerContour = new RectF(this.circleBounds.left + (this.rimWidth / 2.0f) + (this.contourSize / 2.0f), this.circleBounds.top + (this.rimWidth / 2.0f) + (this.contourSize / 2.0f), (this.circleBounds.right - (this.rimWidth / 2.0f)) - (this.contourSize / 2.0f), (this.circleBounds.bottom - (this.rimWidth / 2.0f)) - (this.contourSize / 2.0f));
        this.circleOuterContour = new RectF((this.circleBounds.left - (this.rimWidth / 2.0f)) - (this.contourSize / 2.0f), (this.circleBounds.top - (this.rimWidth / 2.0f)) - (this.contourSize / 2.0f), this.circleBounds.right + (this.rimWidth / 2.0f) + (this.contourSize / 2.0f), this.circleBounds.bottom + (this.rimWidth / 2.0f) + (this.contourSize / 2.0f));
        int i7 = width - this.paddingRight;
        int i8 = this.barWidth;
        int i9 = (i7 - i8) / 2;
        this.fullRadius = i9;
        this.circleRadius = (i9 - i8) + 1;
    }

    private final void parseAttributes(TypedArray a) {
        this.barWidth = (int) a.getDimension(R.styleable.HKNFCProgressWheel_hk_pwBarWidth, this.barWidth);
        this.rimWidth = (int) a.getDimension(R.styleable.HKNFCProgressWheel_hk_pwRimWidth, this.rimWidth);
        this.spinSpeed = (int) a.getDimension(R.styleable.HKNFCProgressWheel_hk_pwSpinSpeed, this.spinSpeed);
        this.barLength = (int) a.getDimension(R.styleable.HKNFCProgressWheel_hk_pwBarLength, this.barLength);
        int integer = a.getInteger(R.styleable.HKNFCProgressWheel_hk_pwDelayMillis, this.delayMillis);
        this.delayMillis = integer;
        if (integer < 0) {
            this.delayMillis = 10;
        }
        if (a.hasValue(R.styleable.HKNFCProgressWheel_hk_pwText)) {
            setText(a.getString(R.styleable.HKNFCProgressWheel_hk_pwText));
        }
        this.barColor = a.getColor(R.styleable.HKNFCProgressWheel_hk_pwBarColor, this.barColor);
        this.textColor = a.getColor(R.styleable.HKNFCProgressWheel_hk_pwTextColor, this.textColor);
        this.rimColor = a.getColor(R.styleable.HKNFCProgressWheel_hk_pwRimColor, this.rimColor);
        this.circleColor = a.getColor(R.styleable.HKNFCProgressWheel_hk_pwCircleColor, this.circleColor);
        this.contourColor = a.getColor(R.styleable.HKNFCProgressWheel_hk_pwContourColor, this.contourColor);
        this.textSize = (int) a.getDimension(R.styleable.HKNFCProgressWheel_hk_pwTextSize, this.textSize);
        this.contourSize = a.getDimension(R.styleable.HKNFCProgressWheel_hk_pwContourSize, this.contourSize);
        a.recycle();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        canvas.drawArc(this.innerCircleBounds, 360.0f, 360.0f, false, this.circlePaint);
        canvas.drawArc(this.circleBounds, 360.0f, 360.0f, false, this.rimPaint);
        canvas.drawArc(this.circleOuterContour, 360.0f, 360.0f, false, this.contourPaint);
        if (this.isSpinning) {
            canvas.drawArc(this.circleBounds, this.progress - 90, this.barLength, false, this.barPaint);
        } else {
            canvas.drawArc(this.circleBounds, -90.0f, this.progress, false, this.barPaint);
        }
        float f = 2;
        float descent = ((this.textPaint.descent() - this.textPaint.ascent()) / f) - this.textPaint.descent();
        for (String str : this.splitText) {
            canvas.drawText(str, (getWidth() / 2) - (this.textPaint.measureText(str) / f), (getHeight() / 2) + descent, this.textPaint);
        }
        if (this.isSpinning) {
            scheduleRedraw();
        }
    }

    private final void scheduleRedraw() {
        float f = this.progress + this.spinSpeed;
        this.progress = f;
        if (f > 360.0f) {
            this.progress = 0.0f;
        }
        postInvalidateDelayed(this.delayMillis);
    }

    public final void resetCount() {
        this.progress = 0.0f;
        setText("0%");
        invalidate();
    }

    public final void stopSpinning() {
        this.isSpinning = false;
        this.progress = 0.0f;
        postInvalidate();
    }

    public final void startSpinning() {
        this.isSpinning = true;
        postInvalidate();
    }

    public static /* synthetic */ void incrementProgress$default(HKNFCProgressWheel hKNFCProgressWheel, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 1;
        }
        hKNFCProgressWheel.incrementProgress(i);
    }

    public final void incrementProgress(int amount) {
        this.isSpinning = false;
        float f = this.progress + amount;
        this.progress = f;
        if (f > 360.0f) {
            this.progress = f % 360.0f;
        }
        postInvalidate();
    }

    public final void setProgress(int i) {
        this.isSpinning = false;
        this.progress = i;
        postInvalidate();
    }

    public final void setText(String text) {
        List emptyList;
        this.text = text;
        Intrinsics.checkNotNull(text);
        List<String> split = new Regex(IOUtils.LINE_SEPARATOR_UNIX).split(text, 0);
        if (!split.isEmpty()) {
            ListIterator<String> listIterator = split.listIterator(split.size());
            while (listIterator.hasPrevious()) {
                if (!(listIterator.previous().length() == 0)) {
                    emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList = CollectionsKt.emptyList();
        this.splitText = (String[]) emptyList.toArray(new String[0]);
    }

    public final int getBarLength() {
        return this.barLength;
    }

    public final void setBarLength(int barLength) {
        this.barLength = barLength;
    }

    public final int getBarWidth() {
        return this.barWidth;
    }

    public final void setBarWidth(int barWidth) {
        this.barWidth = barWidth;
        this.barPaint.setStrokeWidth(barWidth);
    }

    public final int getTextSize() {
        return this.textSize;
    }

    public final void setTextSize(int textSize) {
        this.textSize = textSize;
        this.textPaint.setTextSize(textSize);
    }

    @Override // android.view.View
    public int getPaddingTop() {
        return this.paddingTop;
    }

    public final void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }

    @Override // android.view.View
    public int getPaddingBottom() {
        return this.paddingBottom;
    }

    public final void setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    @Override // android.view.View
    public int getPaddingLeft() {
        return this.paddingLeft;
    }

    public final void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    @Override // android.view.View
    public int getPaddingRight() {
        return this.paddingRight;
    }

    public final void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
    }

    public final int getBarColor() {
        return this.barColor;
    }

    public final void setBarColor(int barColor) {
        this.barColor = barColor;
        this.barPaint.setColor(barColor);
    }

    public final int getCircleColor() {
        return this.circleColor;
    }

    public final void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
        this.circlePaint.setColor(circleColor);
    }

    public final void setRimColor(int rimColor) {
        this.rimColor = rimColor;
        this.rimPaint.setColor(rimColor);
    }

    public final void setTextColor(int textColor) {
        this.textColor = textColor;
        this.textPaint.setColor(textColor);
    }

    public final int getRimWidth() {
        return this.rimWidth;
    }

    public final void setRimWidth(int rimWidth) {
        this.rimWidth = rimWidth;
        this.rimPaint.setStrokeWidth(rimWidth);
    }

    public final int getContourColor() {
        return this.contourColor;
    }

    public final void setContourColor(int contourColor) {
        this.contourColor = contourColor;
        this.contourPaint.setColor(contourColor);
    }

    public final float getContourSize() {
        return this.contourSize;
    }

    public final void setContourSize(float contourSize) {
        this.contourSize = contourSize;
        this.contourPaint.setStrokeWidth(contourSize);
    }

    public final int getProgress() {
        return (int) this.progress;
    }
}
