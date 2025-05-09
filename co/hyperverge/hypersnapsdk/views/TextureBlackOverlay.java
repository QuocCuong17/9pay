package co.hyperverge.hypersnapsdk.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.utils.UIUtils;

/* loaded from: classes2.dex */
public class TextureBlackOverlay extends FrameLayout {
    Path clipPath;
    int color;
    private int mStrokeWidth;

    public TextureBlackOverlay(Context context) {
        super(context);
        this.mStrokeWidth = 20;
        this.color = getResources().getColor(R.color.black_transparent);
    }

    public TextureBlackOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mStrokeWidth = 20;
        this.color = getResources().getColor(R.color.black_transparent);
    }

    public TextureBlackOverlay(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mStrokeWidth = 20;
        this.color = getResources().getColor(R.color.black_transparent);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        this.clipPath = new Path();
        this.clipPath.addCircle(getWidth() / 2, getHeight() / 2, getDiameter() / 2.0f, Path.Direction.CW);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(this.color);
        canvas.clipPath(this.clipPath);
        canvas.drawPath(this.clipPath, paint);
        super.dispatchDraw(canvas);
    }

    public void setOverlayBackground(int i) {
        this.color = i;
    }

    public int getDiameter() {
        return Math.min(UIUtils.getScreenWidth(), UIUtils.getScreenHeight()) - UIUtils.getPercentageMargin(getContext());
    }
}
