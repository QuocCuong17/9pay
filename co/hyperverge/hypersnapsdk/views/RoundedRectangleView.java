package co.hyperverge.hypersnapsdk.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.utils.UIUtils;

/* loaded from: classes2.dex */
public class RoundedRectangleView extends View {
    private static final float CORNER_RADIUS = 6.0f;
    private float boxPaddingPercent;
    private float cornerRadius;
    private boolean isInitialized;
    private Bitmap mBitmap;
    private final Paint mCutPaint;
    private Canvas mInternalCanvas;
    private final Paint paint;
    private RectF portHoleRect;

    public RoundedRectangleView(Context context) {
        super(context);
        this.mCutPaint = new Paint(1);
        this.paint = new Paint(1);
        this.boxPaddingPercent = 0.02f;
        init();
    }

    public RoundedRectangleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCutPaint = new Paint(1);
        this.paint = new Paint(1);
        this.boxPaddingPercent = 0.02f;
        init();
    }

    public RoundedRectangleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCutPaint = new Paint(1);
        this.paint = new Paint(1);
        this.boxPaddingPercent = 0.02f;
        init();
    }

    public void setBoxRect(RectF rectF, float f) {
        this.boxPaddingPercent = f;
        Point point = new Point();
        Point point2 = new Point();
        point.x = (int) rectF.left;
        point.y = (int) rectF.top;
        point2.x = (int) rectF.right;
        point2.y = (int) rectF.bottom;
        this.portHoleRect = rectF;
        this.isInitialized = true;
    }

    public int getCameraSize() {
        return Math.min(UIUtils.getScreenWidth(), UIUtils.getScreenHeight()) - UIUtils.getPercentageMargin(getContext());
    }

    private void init() {
        this.cornerRadius = TypedValue.applyDimension(1, CORNER_RADIUS, getResources().getDisplayMetrics());
        this.mCutPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Canvas canvas = this.mInternalCanvas;
        if (canvas != null) {
            canvas.setBitmap(null);
            this.mInternalCanvas = null;
        }
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.mBitmap = null;
        }
        this.mBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        this.mInternalCanvas = new Canvas(this.mBitmap);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (!this.isInitialized || this.mInternalCanvas == null || this.mBitmap == null) {
            return;
        }
        this.paint.setColor(getContext().getResources().getColor(R.color.hv_white));
        this.paint.setStyle(Paint.Style.FILL);
        this.mInternalCanvas.drawPaint(this.paint);
        Canvas canvas2 = this.mInternalCanvas;
        RectF rectF = this.portHoleRect;
        float f = this.cornerRadius;
        canvas2.drawRoundRect(rectF, f, f, this.mCutPaint);
        this.paint.setXfermode(null);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(-16777216);
        this.paint.setStrokeWidth(2.0f);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        Canvas canvas3 = this.mInternalCanvas;
        RectF rectF2 = this.portHoleRect;
        float f2 = this.cornerRadius;
        canvas3.drawRoundRect(rectF2, f2, f2, this.paint);
        canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, (Paint) null);
    }
}
