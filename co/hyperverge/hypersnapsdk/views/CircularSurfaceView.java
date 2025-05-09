package co.hyperverge.hypersnapsdk.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.SurfaceView;
import co.hyperverge.hypersnapsdk.utils.UIUtils;

/* loaded from: classes2.dex */
public class CircularSurfaceView extends SurfaceView {
    Path clipPath;

    public CircularSurfaceView(Context context) {
        super(context);
    }

    public CircularSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CircularSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        this.clipPath = new Path();
        float diameter = getDiameter() / 2.0f;
        this.clipPath.addCircle(getWidth() / 2, UIUtils.getTopMargin(getContext()) + diameter, diameter, Path.Direction.CW);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.clipPath(this.clipPath);
        canvas.drawPath(this.clipPath, paint);
        setZOrderOnTop(true);
        super.dispatchDraw(canvas);
    }

    public int getDiameter() {
        return Math.min(UIUtils.getScreenWidth(), UIUtils.getScreenHeight()) - UIUtils.getPercentageMargin(getContext());
    }
}
