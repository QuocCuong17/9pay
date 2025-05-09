package co.hyperverge.hypersnapsdk.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.media3.common.C;

/* loaded from: classes2.dex */
public class ScanningIndicator extends AppCompatImageView {
    ObjectAnimator animator;
    private Handler handler;
    private boolean isGoingDown;
    private int mHeight;
    private int mPosY;
    private final Paint paint;
    private Runnable refreshRunnable;
    private final boolean runAnimation;
    private final boolean showLine;

    public ScanningIndicator(Context context) {
        super(context);
        this.paint = new Paint();
        this.mPosY = 0;
        this.runAnimation = true;
        this.showLine = true;
        this.isGoingDown = true;
    }

    public ScanningIndicator(Context context, int i) {
        super(context);
        this.paint = new Paint();
        this.mPosY = 0;
        this.runAnimation = true;
        this.showLine = true;
        this.isGoingDown = true;
        init(i);
    }

    public ScanningIndicator(Context context, int i, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.paint = new Paint();
        this.mPosY = 0;
        this.runAnimation = true;
        this.showLine = true;
        this.isGoingDown = true;
        init(i);
    }

    public ScanningIndicator(Context context, int i, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.paint = new Paint();
        this.mPosY = 0;
        this.runAnimation = true;
        this.showLine = true;
        this.isGoingDown = true;
        init(i);
    }

    public void setmHeight(int i) {
        this.mHeight = i;
    }

    public void startAnimation() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "translationY", 0.0f, this.mHeight - 20);
        this.animator = ofFloat;
        ofFloat.setRepeatMode(2);
        this.animator.setRepeatCount(-1);
        this.animator.setInterpolator(new AccelerateDecelerateInterpolator());
        this.animator.setDuration(C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
        this.animator.start();
    }

    public void stopAnimation() {
        this.animator.cancel();
    }

    private void init(int i) {
        this.mHeight = i;
    }

    private void reset() {
        this.mPosY = 0;
        this.isGoingDown = true;
    }

    private void refreshView() {
        if (this.isGoingDown) {
            int i = this.mPosY + 5;
            this.mPosY = i;
            int i2 = this.mHeight;
            if (i > i2) {
                this.mPosY = i2;
                this.isGoingDown = false;
            }
        } else {
            int i3 = this.mPosY - 5;
            this.mPosY = i3;
            if (i3 < 0) {
                this.mPosY = 0;
                this.isGoingDown = true;
            }
        }
        invalidate();
    }
}
