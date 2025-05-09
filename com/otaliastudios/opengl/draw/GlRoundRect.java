package com.otaliastudios.opengl.draw;

import android.graphics.RectF;
import android.opengl.GLES20;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.otaliastudios.opengl.core.Egloo;
import com.otaliastudios.opengl.internal.GlKt;
import com.otaliastudios.opengl.types.BuffersJvmKt;
import java.nio.FloatBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlRoundRect.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J<\u0010\u0013\u001a\u00020\u00142\n\u0010\u0015\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u0014H\u0016J\b\u0010\u001d\u001a\u00020\u0014H\u0014J\b\u0010\u001e\u001a\u00020\u0014H\u0002J\u000e\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u001bJ&\u0010\u001f\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u001bJ\u0012\u0010%\u001a\u00020\u00142\n\u0010&\u001a\u00060'j\u0002`(J&\u0010%\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00060\rj\u0002`\u000eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006*"}, d2 = {"Lcom/otaliastudios/opengl/draw/GlRoundRect;", "Lcom/otaliastudios/opengl/draw/Gl2dDrawable;", "()V", "bottom", "", "bottomLeftCorner", "bottomRightCorner", "left", TtmlNode.RIGHT, ViewHierarchyConstants.DIMENSION_TOP_KEY, "topLeftCorner", "topRightCorner", "vertexArray", "Ljava/nio/FloatBuffer;", "Lcom/otaliastudios/opengl/types/FloatBuffer;", "getVertexArray", "()Ljava/nio/FloatBuffer;", "setVertexArray", "(Ljava/nio/FloatBuffer;)V", "addCornerArc", "", "array", "pivotX", "pivotY", "width", "height", "startAngle", "", "draw", "onViewportSizeChanged", "recompute", "setCornersPx", "corners", "topLeft", "topRight", "bottomLeft", "bottomRight", "setRect", "rect", "Landroid/graphics/RectF;", "Lcom/otaliastudios/opengl/geometry/RectF;", "Companion", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public class GlRoundRect extends Gl2dDrawable {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    private static final int POINTS_PER_CORNER = 20;
    private float bottomLeftCorner;
    private float bottomRightCorner;
    private float topLeftCorner;
    private float topRightCorner;
    private float top = 1.0f;
    private float bottom = -1.0f;
    private float left = -1.0f;
    private float right = 1.0f;
    private FloatBuffer vertexArray = BuffersJvmKt.floatBuffer(getCoordsPerVertex() * 82);

    public GlRoundRect() {
        recompute();
    }

    /* compiled from: GlRoundRect.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/otaliastudios/opengl/draw/GlRoundRect$Companion;", "", "()V", "POINTS_PER_CORNER", "", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
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

    public final void setCornersPx(int corners) {
        setCornersPx(corners, corners, corners, corners);
    }

    public final void setCornersPx(int topLeft, int topRight, int bottomLeft, int bottomRight) {
        this.topLeftCorner = topLeft;
        this.topRightCorner = topRight;
        this.bottomLeftCorner = bottomLeft;
        this.bottomRightCorner = bottomRight;
        recompute();
    }

    public final void setRect(RectF rect) {
        Intrinsics.checkNotNullParameter(rect, "rect");
        setRect(rect.left, rect.top, rect.right, rect.bottom);
    }

    public final void setRect(float left, float top, float right, float bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        recompute();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.otaliastudios.opengl.core.GlViewportAware
    public void onViewportSizeChanged() {
        super.onViewportSizeChanged();
        recompute();
    }

    private final void recompute() {
        FloatBuffer vertexArray = getVertexArray();
        vertexArray.clear();
        float f = (this.right + this.left) / 2.0f;
        float f2 = (this.top + this.bottom) / 2.0f;
        vertexArray.put(f);
        vertexArray.put(f2);
        boolean z = getViewportHeight() > 0 && getViewportWidth() > 0;
        if (z && this.topLeftCorner > 0.0f) {
            float viewportWidth = (this.topLeftCorner / getViewportWidth()) * 2.0f;
            float viewportHeight = (this.topLeftCorner / getViewportHeight()) * 2.0f;
            addCornerArc(vertexArray, this.left + viewportWidth, this.top - viewportHeight, viewportWidth, viewportHeight, 180);
        } else {
            vertexArray.put(this.left);
            vertexArray.put(this.top);
        }
        if (z && this.topRightCorner > 0.0f) {
            float viewportWidth2 = (this.topRightCorner / getViewportWidth()) * 2.0f;
            float viewportHeight2 = (this.topRightCorner / getViewportHeight()) * 2.0f;
            addCornerArc(vertexArray, this.right - viewportWidth2, this.top - viewportHeight2, viewportWidth2, viewportHeight2, 90);
        } else {
            vertexArray.put(this.right);
            vertexArray.put(this.top);
        }
        if (z && this.bottomRightCorner > 0.0f) {
            float viewportWidth3 = (this.bottomRightCorner / getViewportWidth()) * 2.0f;
            float viewportHeight3 = (this.bottomRightCorner / getViewportHeight()) * 2.0f;
            addCornerArc(vertexArray, this.right - viewportWidth3, this.bottom + viewportHeight3, viewportWidth3, viewportHeight3, 0);
        } else {
            vertexArray.put(this.right);
            vertexArray.put(this.bottom);
        }
        if (z && this.bottomLeftCorner > 0.0f) {
            float viewportWidth4 = (this.bottomLeftCorner / getViewportWidth()) * 2.0f;
            float viewportHeight4 = (this.bottomLeftCorner / getViewportHeight()) * 2.0f;
            addCornerArc(vertexArray, this.left + viewportWidth4, this.bottom + viewportHeight4, viewportWidth4, viewportHeight4, -90);
        } else {
            vertexArray.put(this.left);
            vertexArray.put(this.bottom);
        }
        vertexArray.put(vertexArray.get(2));
        vertexArray.put(vertexArray.get(3));
        vertexArray.flip();
        notifyVertexArrayChange();
    }

    private final void addCornerArc(FloatBuffer array, float pivotX, float pivotY, float width, float height, int startAngle) {
        int i = startAngle - 90;
        float f = 1.0f / 19;
        float f2 = 0.0f;
        for (int i2 = 0; i2 < 20; i2++) {
            double d = (float) (((startAngle + ((i - startAngle) * f2)) * 3.141592653589793d) / 180);
            double d2 = 2;
            float sqrt = (width * height) / ((float) Math.sqrt(((float) Math.pow(((float) Math.sin(d)) * width, d2)) + ((float) Math.pow(((float) Math.cos(d)) * height, d2))));
            array.put(pivotX + (((float) Math.cos(d)) * sqrt));
            array.put(pivotY + (sqrt * ((float) Math.sin(d))));
            f2 += f;
        }
    }

    @Override // com.otaliastudios.opengl.draw.GlDrawable
    public void draw() {
        GLES20.glDrawArrays(GlKt.getGL_TRIANGLE_FAN(), 0, getVertexCount());
        Egloo.checkGlError("glDrawArrays");
    }
}
