package vn.ai.faceauth.sdk.presentation.presentation.widgets;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import vn.ai.faceauth.sdk.presentation.presentation.widgets.GraphicOverlay;

/* loaded from: classes6.dex */
public class CameraImageGraphic extends GraphicOverlay.Graphic {
    private final Bitmap bitmap;

    public CameraImageGraphic(GraphicOverlay graphicOverlay, Bitmap bitmap) {
        super(graphicOverlay);
        this.bitmap = bitmap;
    }

    @Override // vn.ai.faceauth.sdk.presentation.presentation.widgets.GraphicOverlay.Graphic
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.bitmap, getTransformationMatrix(), null);
    }
}
