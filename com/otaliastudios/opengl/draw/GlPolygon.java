package com.otaliastudios.opengl.draw;

import android.graphics.PointF;
import android.opengl.GLES20;
import androidx.constraintlayout.motion.widget.Key;
import androidx.media3.extractor.text.ttml.TtmlNode;
import co.hyperverge.hypersnapsdk.activities.HVRetakeActivity;
import com.otaliastudios.opengl.core.Egloo;
import com.otaliastudios.opengl.extensions.MatrixKt;
import com.otaliastudios.opengl.internal.GlKt;
import com.otaliastudios.opengl.types.BuffersJvmKt;
import java.nio.FloatBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlPolygon.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020(H\u0014J\b\u0010*\u001a\u00020(H\u0002J\b\u0010+\u001a\u00020(H\u0002R,\u0010\b\u001a\u00060\u0006j\u0002`\u00072\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR$\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R$\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R$\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0010\"\u0004\b\u001b\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001c\u001a\u00060\u001dj\u0002`\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/otaliastudios/opengl/draw/GlPolygon;", "Lcom/otaliastudios/opengl/draw/Gl2dDrawable;", "sides", "", "(I)V", "value", "Landroid/graphics/PointF;", "Lcom/otaliastudios/opengl/geometry/PointF;", TtmlNode.CENTER, "getCenter", "()Landroid/graphics/PointF;", "setCenter", "(Landroid/graphics/PointF;)V", "", "centerX", "getCenterX", "()F", "setCenterX", "(F)V", "centerY", "getCenterY", "setCenterY", HVRetakeActivity.RADIUS_TAG, "getRadius", "setRadius", Key.ROTATION, "getRotation", "setRotation", "vertexArray", "Ljava/nio/FloatBuffer;", "Lcom/otaliastudios/opengl/types/FloatBuffer;", "getVertexArray", "()Ljava/nio/FloatBuffer;", "setVertexArray", "(Ljava/nio/FloatBuffer;)V", "viewportScaleX", "viewportScaleY", "viewportTranslationX", "viewportTranslationY", "draw", "", "onViewportSizeChanged", "onViewportSizeOrCenterChanged", "updateArray", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public class GlPolygon extends Gl2dDrawable {
    private float centerX;
    private float centerY;
    private float radius;
    private float rotation;
    private final int sides;
    private FloatBuffer vertexArray;
    private float viewportScaleX;
    private float viewportScaleY;
    private float viewportTranslationX;
    private float viewportTranslationY;

    public GlPolygon(int i) {
        this.sides = i;
        if (i < 3) {
            throw new IllegalArgumentException("Polygon should have at least 3 sides.");
        }
        this.viewportScaleX = 1.0f;
        this.viewportScaleY = 1.0f;
        this.radius = 1.0f;
        this.vertexArray = BuffersJvmKt.floatBuffer((i + 2) * getCoordsPerVertex());
        updateArray();
    }

    public final float getRadius() {
        return this.radius;
    }

    public final void setRadius(float f) {
        this.radius = f;
        updateArray();
    }

    public final float getRotation() {
        return this.rotation;
    }

    public final void setRotation(float f) {
        this.rotation = f % 360;
        updateArray();
    }

    public final float getCenterX() {
        return this.centerX;
    }

    public final void setCenterX(float f) {
        this.centerX = f;
        updateArray();
        onViewportSizeOrCenterChanged();
    }

    public final float getCenterY() {
        return this.centerY;
    }

    public final void setCenterY(float f) {
        this.centerY = f;
        updateArray();
        onViewportSizeOrCenterChanged();
    }

    public final PointF getCenter() {
        return new PointF(this.centerX, this.centerY);
    }

    public final void setCenter(PointF value) {
        Intrinsics.checkNotNullParameter(value, "value");
        setCenterX(value.x);
        setCenterY(value.y);
    }

    @Override // com.otaliastudios.opengl.draw.GlDrawable
    public FloatBuffer getVertexArray() {
        return this.vertexArray;
    }

    @Override // com.otaliastudios.opengl.draw.GlDrawable
    public void setVertexArray(FloatBuffer floatBuffer) {
        Intrinsics.checkNotNullParameter(floatBuffer, "<set-?>");
        this.vertexArray = floatBuffer;
    }

    private final void updateArray() {
        FloatBuffer vertexArray = getVertexArray();
        vertexArray.clear();
        vertexArray.put(this.centerX);
        vertexArray.put(this.centerY);
        float f = this.rotation * 0.017453292f;
        int i = this.sides;
        float f2 = 6.2831855f / i;
        for (int i2 = 0; i2 < i; i2++) {
            double d = f;
            vertexArray.put(getCenterX() + (getRadius() * ((float) Math.cos(d))));
            vertexArray.put(getCenterY() + (getRadius() * ((float) Math.sin(d))));
            f += f2;
        }
        vertexArray.put(vertexArray.get(2));
        vertexArray.put(vertexArray.get(3));
        vertexArray.flip();
        notifyVertexArrayChange();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.otaliastudios.opengl.core.GlViewportAware
    public void onViewportSizeChanged() {
        super.onViewportSizeChanged();
        onViewportSizeOrCenterChanged();
    }

    private final void onViewportSizeOrCenterChanged() {
        MatrixKt.scale$default(getModelMatrix(), 1.0f / this.viewportScaleX, 1.0f / this.viewportScaleY, 0.0f, 4, null);
        MatrixKt.translate$default(getModelMatrix(), -this.viewportTranslationX, -this.viewportTranslationY, 0.0f, 4, null);
        if (getViewportWidth() > getViewportHeight()) {
            float viewportHeight = getViewportHeight() / getViewportWidth();
            this.viewportScaleX = viewportHeight;
            this.viewportScaleY = 1.0f;
            this.viewportTranslationX = this.centerX * (1 - viewportHeight);
            this.viewportTranslationY = 0.0f;
        } else if (getViewportWidth() < getViewportHeight()) {
            float viewportWidth = getViewportWidth() / getViewportHeight();
            this.viewportScaleY = viewportWidth;
            this.viewportScaleX = 1.0f;
            this.viewportTranslationY = this.centerY * (1 - viewportWidth);
            this.viewportTranslationX = 0.0f;
        } else {
            this.viewportScaleX = 1.0f;
            this.viewportScaleY = 1.0f;
            this.viewportTranslationX = 0.0f;
            this.viewportTranslationY = 0.0f;
        }
        MatrixKt.translate$default(getModelMatrix(), this.viewportTranslationX, this.viewportTranslationY, 0.0f, 4, null);
        MatrixKt.scale$default(getModelMatrix(), this.viewportScaleX, this.viewportScaleY, 0.0f, 4, null);
    }

    @Override // com.otaliastudios.opengl.draw.GlDrawable
    public void draw() {
        GLES20.glDrawArrays(GlKt.getGL_TRIANGLE_FAN(), 0, getVertexCount());
        Egloo.checkGlError("glDrawArrays");
    }
}
