package vn.ai.faceauth.sdk.presentation.presentation.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class GraphicOverlay extends View {
    private final List<Graphic> graphics;
    private int imageHeight;
    private int imageWidth;
    private boolean isImageFlipped;
    private final Object lock;
    private boolean needUpdateTransformation;
    private float postScaleHeightOffset;
    private float postScaleWidthOffset;
    private float scaleFactor;
    private final Matrix transformationMatrix;

    /* loaded from: classes6.dex */
    public static abstract class Graphic {
        private GraphicOverlay overlay;

        public Graphic(GraphicOverlay graphicOverlay) {
            this.overlay = graphicOverlay;
        }

        public abstract void draw(Canvas canvas);

        public void drawRect(Canvas canvas, float f, float f2, float f3, float f4, Paint paint) {
            canvas.drawRect(f, f2, f3, f4, paint);
        }

        public void drawText(Canvas canvas, String str, float f, float f2, Paint paint) {
            canvas.drawText(str, f, f2, paint);
        }

        public Context getApplicationContext() {
            return this.overlay.getContext().getApplicationContext();
        }

        public Matrix getTransformationMatrix() {
            return this.overlay.transformationMatrix;
        }

        public boolean isImageFlipped() {
            return this.overlay.isImageFlipped;
        }

        public void postInvalidate() {
            this.overlay.postInvalidate();
        }

        public float scale(float f) {
            return this.overlay.scaleFactor * f;
        }

        public float translateX(float f) {
            return this.overlay.isImageFlipped ? this.overlay.getWidth() - (scale(f) - this.overlay.postScaleWidthOffset) : scale(f) - this.overlay.postScaleWidthOffset;
        }

        public float translateY(float f) {
            return scale(f) - this.overlay.postScaleHeightOffset;
        }
    }

    public GraphicOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.lock = new Object();
        this.graphics = new ArrayList();
        this.transformationMatrix = new Matrix();
        this.scaleFactor = 1.0f;
        this.needUpdateTransformation = true;
        addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: vn.ai.faceauth.sdk.presentation.presentation.widgets.GraphicOverlay$$ExternalSyntheticLambda0
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                GraphicOverlay.this.m2989xbcc1951c(view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
    }

    private void updateTransformationIfNeeded() {
        if (!this.needUpdateTransformation || this.imageWidth <= 0 || this.imageHeight <= 0) {
            return;
        }
        float width = getWidth() / getHeight();
        float f = this.imageWidth / this.imageHeight;
        this.postScaleWidthOffset = 0.0f;
        this.postScaleHeightOffset = 0.0f;
        if (width > f) {
            this.scaleFactor = getWidth() / this.imageWidth;
            this.postScaleHeightOffset = ((getWidth() / f) - getHeight()) / 2.0f;
        } else {
            this.scaleFactor = getHeight() / this.imageHeight;
            this.postScaleWidthOffset = ((getHeight() * f) - getWidth()) / 2.0f;
        }
        this.transformationMatrix.reset();
        Matrix matrix = this.transformationMatrix;
        float f2 = this.scaleFactor;
        matrix.setScale(f2, f2);
        this.transformationMatrix.postTranslate(-this.postScaleWidthOffset, -this.postScaleHeightOffset);
        if (this.isImageFlipped) {
            this.transformationMatrix.postScale(-1.0f, 1.0f, getWidth() / 2.0f, getHeight() / 2.0f);
        }
        this.needUpdateTransformation = false;
    }

    public void add(Graphic graphic) {
        synchronized (this.lock) {
            this.graphics.add(graphic);
        }
    }

    public void clear() {
        synchronized (this.lock) {
            this.graphics.clear();
        }
        postInvalidate();
    }

    public int getImageHeight() {
        return this.imageHeight;
    }

    public int getImageWidth() {
        return this.imageWidth;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$vn-ai-faceauth-sdk-presentation-presentation-widgets-GraphicOverlay, reason: not valid java name */
    public /* synthetic */ void m2989xbcc1951c(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.needUpdateTransformation = true;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        synchronized (this.lock) {
            updateTransformationIfNeeded();
            Iterator<Graphic> it = this.graphics.iterator();
            while (it.hasNext()) {
                it.next().draw(canvas);
            }
        }
    }

    public void remove(Graphic graphic) {
        synchronized (this.lock) {
            this.graphics.remove(graphic);
        }
        postInvalidate();
    }

    public void setImageSourceInfo(int i, int i2, boolean z) {
        synchronized (this.lock) {
            this.imageWidth = i;
            this.imageHeight = i2;
            this.isImageFlipped = z;
            this.needUpdateTransformation = true;
        }
        postInvalidate();
    }
}
