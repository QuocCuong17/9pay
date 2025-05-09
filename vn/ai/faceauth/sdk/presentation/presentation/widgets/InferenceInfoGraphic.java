package vn.ai.faceauth.sdk.presentation.presentation.widgets;

import android.graphics.Canvas;
import android.graphics.Paint;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.presentation.presentation.widgets.GraphicOverlay;

/* loaded from: classes6.dex */
public class InferenceInfoGraphic extends GraphicOverlay.Graphic {
    private static int TEXT_COLOR;
    private static float TEXT_SIZE;
    private final long detectorLatency;
    private final long frameLatency;
    private final Integer framesPerSecond;
    private final GraphicOverlay overlay;
    private boolean showLatencyInfo;
    private final Paint textPaint;

    static {
        tfwhgw.rnl(InferenceInfoGraphic.class, 512, 513);
    }

    public InferenceInfoGraphic(GraphicOverlay graphicOverlay) {
        this(graphicOverlay, 0L, 0L, null);
        this.showLatencyInfo = false;
    }

    public InferenceInfoGraphic(GraphicOverlay graphicOverlay, long j, long j2, Integer num) {
        super(graphicOverlay);
        this.showLatencyInfo = true;
        this.overlay = graphicOverlay;
        this.frameLatency = j;
        this.detectorLatency = j2;
        this.framesPerSecond = num;
        Paint paint = new Paint();
        this.textPaint = paint;
        paint.setColor(-1);
        paint.setTextSize(60.0f);
        paint.setShadowLayer(5.0f, 0.0f, 0.0f, -16777216);
        postInvalidate();
    }

    @Override // vn.ai.faceauth.sdk.presentation.presentation.widgets.GraphicOverlay.Graphic
    public void draw(Canvas canvas) {
        String str;
        synchronized (this) {
            canvas.drawText(tfwhgw.rnigpa(231) + this.overlay.getImageHeight() + tfwhgw.rnigpa(232) + this.overlay.getImageWidth(), 30.0f, 90.0f, this.textPaint);
            if (this.showLatencyInfo) {
                if (this.framesPerSecond != null) {
                    str = tfwhgw.rnigpa(233) + this.framesPerSecond + tfwhgw.rnigpa(234) + this.frameLatency + tfwhgw.rnigpa(235);
                } else {
                    str = tfwhgw.rnigpa(236) + this.frameLatency + tfwhgw.rnigpa(237);
                }
                canvas.drawText(str, 30.0f, 150.0f, this.textPaint);
                canvas.drawText(tfwhgw.rnigpa(238) + this.detectorLatency + tfwhgw.rnigpa(239), 30.0f, 210.0f, this.textPaint);
            }
        }
    }
}
