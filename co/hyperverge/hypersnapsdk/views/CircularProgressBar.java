package co.hyperverge.hypersnapsdk.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.core.internal.view.SupportMenu;
import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.utils.UIUtils;
import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes2.dex */
public class CircularProgressBar extends View {
    private int backgroundColor;
    private final Paint backgroundPaint;
    private int diameter;
    private int mAnimationDuration;
    private boolean mDrawText;
    private final int mMaxProgress;
    private final float mMaxSweepAngle;
    private final Paint mPaint;
    private int mProgressColor;
    private boolean mRoundedCorners;
    private final float mStartAngle;
    private int mStrokeWidth;
    private float mSweepAngle;
    private int mTextColor;
    private int mViewHeight;
    private int mViewWidth;
    private final ValueAnimator progressBarAnimator;

    private int calcProgressFromSweepAngle(float f) {
        return (int) ((f * 100.0f) / 360.0f);
    }

    private float calcSweepAngleFromProgress(int i) {
        return i * 3.6f;
    }

    public CircularProgressBar(Context context) {
        this(context, null);
    }

    public CircularProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircularProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mStartAngle = -90.0f;
        this.mSweepAngle = 0.0f;
        this.mMaxSweepAngle = 360.0f;
        this.mStrokeWidth = 18;
        this.mAnimationDuration = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
        this.mMaxProgress = 100;
        this.mDrawText = false;
        this.mRoundedCorners = false;
        this.mProgressColor = getResources().getColor(R.color.hv_white);
        this.mTextColor = SupportMenu.CATEGORY_MASK;
        this.backgroundColor = SupportMenu.CATEGORY_MASK;
        this.diameter = getDiameter();
        this.progressBarAnimator = ValueAnimator.ofFloat(0.0f);
        this.mPaint = new Paint(1);
        this.backgroundPaint = new Paint(1);
    }

    public void setmStrokeWidth(int i) {
        this.mStrokeWidth = i;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initMeasurments();
        drawOutlineArc(canvas);
        setProgressBarAnimatorParams();
        if (this.mDrawText) {
            drawText(canvas);
        }
    }

    private void setProgressBarAnimatorParams() {
        this.progressBarAnimator.setInterpolator(new LinearInterpolator());
        this.progressBarAnimator.setDuration(this.mAnimationDuration);
        this.progressBarAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: co.hyperverge.hypersnapsdk.views.CircularProgressBar.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CircularProgressBar.this.mSweepAngle = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                CircularProgressBar.this.invalidate();
            }
        });
    }

    private void initMeasurments() {
        this.mViewWidth = getWidth();
        this.mViewHeight = getHeight();
    }

    public void setDiameter(int i) {
        this.diameter = i;
    }

    private void drawOutlineArc(Canvas canvas) {
        float f = (float) (this.mStrokeWidth / 2.0d);
        int i = this.diameter;
        RectF rectF = new RectF(f, f, i - f, i - f);
        this.mPaint.setColor(this.mProgressColor);
        this.mPaint.setStrokeWidth(this.mStrokeWidth);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeCap(this.mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.backgroundPaint.setColor(this.mProgressColor);
        this.backgroundPaint.setStrokeWidth(this.mStrokeWidth);
        this.backgroundPaint.setAntiAlias(true);
        this.backgroundPaint.setStrokeCap(this.mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        this.backgroundPaint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(rectF, this.backgroundPaint);
        canvas.drawArc(rectF, -90.0f, this.mSweepAngle, false, this.mPaint);
    }

    private void drawText(Canvas canvas) {
        this.mPaint.setTextSize(Math.min(this.mViewWidth, this.mViewHeight) / 5.0f);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mPaint.setStrokeWidth(0.0f);
        this.mPaint.setColor(this.mTextColor);
        canvas.drawText(calcProgressFromSweepAngle(this.mSweepAngle) + "%", canvas.getWidth() / 2, (int) ((canvas.getHeight() / 2) - ((this.mPaint.descent() + this.mPaint.ascent()) / 2.0f)), this.mPaint);
    }

    public int getDiameter() {
        return Math.min(UIUtils.getScreenWidth(), UIUtils.getScreenHeight()) - UIUtils.getPercentageMargin(getContext());
    }

    public void setProgress(int i) {
        this.progressBarAnimator.setFloatValues(this.mSweepAngle, calcSweepAngleFromProgress(i));
        this.progressBarAnimator.start();
    }

    public void setMaxProgress(int i) {
        this.mSweepAngle = calcSweepAngleFromProgress(i);
    }

    public void setProgressColor(int i) {
        this.mProgressColor = i;
        invalidate();
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.backgroundColor = i;
        invalidate();
    }

    public void setProgressWidth(int i) {
        this.mStrokeWidth = i;
        invalidate();
    }

    public void setTextColor(int i) {
        this.mTextColor = i;
        invalidate();
    }

    public void showProgressText(boolean z) {
        this.mDrawText = z;
        invalidate();
    }

    public void setSweepAngle(float f) {
        this.mSweepAngle = f;
        invalidate();
    }

    public ValueAnimator getProgressBarAnimator() {
        return this.progressBarAnimator;
    }

    public void useRoundedCorners(boolean z) {
        this.mRoundedCorners = z;
        invalidate();
    }

    public void setAnimationDuration(int i) {
        this.mAnimationDuration = i;
    }

    public int getAnimationDuration() {
        return this.mAnimationDuration;
    }
}
