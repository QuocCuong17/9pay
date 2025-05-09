package co.hyperverge.hypersnapsdk.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.utils.UIUtils;

/* loaded from: classes2.dex */
public class TextureCamera extends FrameLayout {
    Path clipPath;
    private int mStrokeWidth;

    public TextureCamera(Context context) {
        super(context);
        this.mStrokeWidth = 20;
    }

    public TextureCamera(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mStrokeWidth = 20;
    }

    public TextureCamera(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mStrokeWidth = 20;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        this.clipPath = new Path();
        this.clipPath.addCircle(getWidth() / 2, getHeight() / 2, getDiameter() / 2.0f, Path.Direction.CW);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.black));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.clipPath(this.clipPath);
        canvas.drawPath(this.clipPath, paint);
        super.dispatchDraw(canvas);
    }

    public int getDiameter() {
        return Math.min(UIUtils.getScreenWidth(), UIUtils.getScreenHeight()) - UIUtils.getPercentageMargin(getContext());
    }
}
