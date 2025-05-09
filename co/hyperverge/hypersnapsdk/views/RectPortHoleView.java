package co.hyperverge.hypersnapsdk.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.Log;
import android.view.View;
import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.utils.Utils;

/* loaded from: classes2.dex */
public class RectPortHoleView extends View {
    private static final String TAG = "co.hyperverge.hypersnapsdk.views.RectPortHoleView";
    private final float ROUNDED_RECT_RADIUS;
    private int borderColor;
    private float boxPaddingPercent;
    private final Xfermode clearXFerMode;
    private boolean isInitialized;
    private final Paint paint;
    private RectF portHoleRect;
    private int strokeWidth;

    public RectPortHoleView(Context context) {
        super(context);
        this.ROUNDED_RECT_RADIUS = 20.0f;
        this.boxPaddingPercent = 0.02f;
        this.isInitialized = false;
        this.borderColor = -16777216;
        this.strokeWidth = 2;
        this.paint = new Paint(1);
        this.clearXFerMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    }

    public void setBoxRect(RectF rectF, float f) {
        this.boxPaddingPercent = f;
        Point point = new Point();
        Point point2 = new Point();
        point.x = ((int) rectF.left) + ((int) (rectF.width() * f));
        point.y = ((int) rectF.top) + ((int) (rectF.height() * f));
        point2.x = ((int) rectF.right) - ((int) (rectF.width() * f));
        point2.y = ((int) rectF.bottom) - ((int) (f * ((int) rectF.height())));
        this.portHoleRect = new RectF(point.x, point.y, point2.x, point2.y);
        this.isInitialized = true;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.isInitialized) {
            try {
                Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                if (createBitmap == null) {
                    return;
                }
                Canvas canvas2 = new Canvas(createBitmap);
                this.paint.setColor(getContext().getResources().getColor(R.color.hv_white));
                this.paint.setStyle(Paint.Style.FILL);
                canvas2.drawPaint(this.paint);
                this.paint.setXfermode(this.clearXFerMode);
                canvas2.drawRoundRect(this.portHoleRect, 20.0f, 20.0f, this.paint);
                this.paint.setXfermode(null);
                this.paint.setStyle(Paint.Style.STROKE);
                this.paint.setColor(this.borderColor);
                this.paint.setStrokeWidth(this.strokeWidth);
                this.paint.setStrokeJoin(Paint.Join.ROUND);
                canvas2.drawRoundRect(this.portHoleRect, 20.0f, 20.0f, this.paint);
                canvas.drawBitmap(createBitmap, 0.0f, 0.0f, this.paint);
            } catch (Exception | OutOfMemoryError e) {
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
    }

    public void setBorderColor(int i) {
        this.borderColor = i;
        invalidate();
    }

    public void setStrokeWidth(int i) {
        this.strokeWidth = i;
        invalidate();
    }

    public RectF getPortHoleRect() {
        return this.portHoleRect;
    }
}
