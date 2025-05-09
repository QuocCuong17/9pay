package co.hyperverge.hypersnapsdk.views;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;
import co.hyperverge.hypersnapsdk.R;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class RippleBackground extends RelativeLayout {
    private static final int DEFAULT_DURATION_TIME = 1500;
    private static final int DEFAULT_FILL_TYPE = 0;
    private static final int DEFAULT_RIPPLE_COUNT = 6;
    private static final float DEFAULT_SCALE = 6.0f;
    private boolean animationRunning;
    private ArrayList<Animator> animatorList;
    private AnimatorSet animatorSet;
    Handler delayHandler;
    private Paint paint;
    private int rippleAmount;
    private int rippleColor;
    private int rippleDelay;
    private int rippleDurationTime;
    private RelativeLayout.LayoutParams rippleParams;
    private float rippleRadius;
    private float rippleScale;
    private float rippleStrokeWidth;
    private int rippleType;
    private ArrayList<RippleView> rippleViewList;

    public RippleBackground(Context context) {
        super(context);
        this.animationRunning = false;
        this.rippleViewList = new ArrayList<>();
        this.delayHandler = new Handler();
    }

    public RippleBackground(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.animationRunning = false;
        this.rippleViewList = new ArrayList<>();
        this.delayHandler = new Handler();
        init(context, attributeSet);
    }

    public RippleBackground(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.animationRunning = false;
        this.rippleViewList = new ArrayList<>();
        this.delayHandler = new Handler();
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (isInEditMode()) {
            return;
        }
        if (attributeSet == null) {
            throw new IllegalArgumentException("Attributes should be provided to this view,");
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RippleBackground);
        this.rippleColor = obtainStyledAttributes.getColor(R.styleable.RippleBackground_rb_color, getResources().getColor(R.color.ripple_color));
        this.rippleStrokeWidth = obtainStyledAttributes.getDimension(R.styleable.RippleBackground_rb_strokeWidth, getResources().getDimension(R.dimen.rippleStrokeWidth));
        this.rippleRadius = obtainStyledAttributes.getDimension(R.styleable.RippleBackground_rb_radius, getResources().getDimension(R.dimen.rippleRadius));
        this.rippleDurationTime = obtainStyledAttributes.getInt(R.styleable.RippleBackground_rb_duration, 1500);
        this.rippleAmount = obtainStyledAttributes.getInt(R.styleable.RippleBackground_rb_rippleAmount, 6);
        this.rippleScale = obtainStyledAttributes.getFloat(R.styleable.RippleBackground_rb_scale, DEFAULT_SCALE);
        this.rippleType = obtainStyledAttributes.getInt(R.styleable.RippleBackground_rb_type, 0);
        obtainStyledAttributes.recycle();
        this.rippleDelay = this.rippleDurationTime / this.rippleAmount;
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        if (this.rippleType == 0) {
            this.rippleStrokeWidth = 0.0f;
            this.paint.setStyle(Paint.Style.FILL);
        } else {
            this.paint.setStyle(Paint.Style.STROKE);
        }
        this.paint.setColor(this.rippleColor);
        float f = this.rippleRadius;
        float f2 = this.rippleStrokeWidth;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) ((f + f2) * 2.0f), (int) ((f + f2) * 2.0f));
        this.rippleParams = layoutParams;
        layoutParams.addRule(13, -1);
        AnimatorSet animatorSet = new AnimatorSet();
        this.animatorSet = animatorSet;
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        this.animatorList = new ArrayList<>();
        for (int i = 0; i < this.rippleAmount; i++) {
            RippleView rippleView = new RippleView(getContext());
            addView(rippleView, this.rippleParams);
            this.rippleViewList.add(rippleView);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(rippleView, "ScaleX", 1.0f, this.rippleScale);
            ofFloat.setStartDelay(this.rippleDelay * i);
            ofFloat.setDuration(this.rippleDurationTime);
            this.animatorList.add(ofFloat);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(rippleView, "ScaleY", 1.0f, this.rippleScale);
            ofFloat2.setStartDelay(this.rippleDelay * i);
            ofFloat2.setDuration(this.rippleDurationTime);
            this.animatorList.add(ofFloat2);
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(rippleView, "Alpha", 1.0f, 0.0f);
            ofFloat3.setStartDelay(this.rippleDelay * i);
            ofFloat3.setDuration(this.rippleDurationTime);
            this.animatorList.add(ofFloat3);
        }
        this.animatorSet.playTogether(this.animatorList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class RippleView extends View {
        public RippleView(Context context) {
            super(context);
            setVisibility(4);
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            float min = Math.min(getWidth(), getHeight()) / 2;
            canvas.drawCircle(min, min, min - RippleBackground.this.rippleStrokeWidth, RippleBackground.this.paint);
        }
    }

    public void startRippleAnimation() {
        if (isRippleAnimationRunning()) {
            return;
        }
        Iterator<RippleView> it = this.rippleViewList.iterator();
        while (it.hasNext()) {
            it.next().setVisibility(0);
        }
        this.animatorSet.start();
        this.animationRunning = true;
        this.delayHandler.postDelayed(new Runnable() { // from class: co.hyperverge.hypersnapsdk.views.RippleBackground.1
            @Override // java.lang.Runnable
            public void run() {
                RippleBackground.this.stopRippleAnimation();
            }
        }, 1500L);
    }

    public void stopRippleAnimation() {
        if (isRippleAnimationRunning()) {
            this.animatorSet.end();
            this.animationRunning = false;
        }
    }

    public boolean isRippleAnimationRunning() {
        return this.animationRunning;
    }
}
