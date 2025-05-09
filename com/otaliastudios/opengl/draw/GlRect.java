package com.otaliastudios.opengl.draw;

import android.graphics.RectF;
import android.opengl.GLES20;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.otaliastudios.opengl.core.Egloo;
import com.otaliastudios.opengl.internal.GlKt;
import com.otaliastudios.opengl.types.BuffersJvmKt;
import java.nio.FloatBuffer;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlRect.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u00020\u000b2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000fJ&\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0011J\u0014\u0010\b\u001a\u00020\u000b2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000fH\u0017J\u0010\u0010\b\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0017R\u001e\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/otaliastudios/opengl/draw/GlRect;", "Lcom/otaliastudios/opengl/draw/Gl2dDrawable;", "()V", "vertexArray", "Ljava/nio/FloatBuffer;", "Lcom/otaliastudios/opengl/types/FloatBuffer;", "getVertexArray", "()Ljava/nio/FloatBuffer;", "setVertexArray", "(Ljava/nio/FloatBuffer;)V", "draw", "", "setRect", "rect", "Landroid/graphics/RectF;", "Lcom/otaliastudios/opengl/geometry/RectF;", "left", "", ViewHierarchyConstants.DIMENSION_TOP_KEY, TtmlNode.RIGHT, "bottom", "array", "", "Companion", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public class GlRect extends Gl2dDrawable {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    private static final float[] FULL_RECTANGLE_COORDS = {-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
    private FloatBuffer vertexArray;

    public GlRect() {
        float[] fArr = FULL_RECTANGLE_COORDS;
        FloatBuffer floatBuffer = BuffersJvmKt.floatBuffer(fArr.length);
        floatBuffer.put(fArr);
        floatBuffer.clear();
        Unit unit = Unit.INSTANCE;
        this.vertexArray = floatBuffer;
    }

    /* compiled from: GlRect.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/otaliastudios/opengl/draw/GlRect$Companion;", "", "()V", "FULL_RECTANGLE_COORDS", "", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
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

    @Deprecated(message = "Use setRect", replaceWith = @ReplaceWith(expression = "setRect(rect)", imports = {}))
    public void setVertexArray(float[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        if (array.length != getCoordsPerVertex() * 4) {
            throw new IllegalArgumentException("Vertex array should have 8 values.");
        }
        getVertexArray().clear();
        getVertexArray().put(array);
        getVertexArray().flip();
        notifyVertexArrayChange();
    }

    @Deprecated(message = "Use setRect", replaceWith = @ReplaceWith(expression = "setRect(rect)", imports = {}))
    public void setVertexArray(RectF rect) {
        Intrinsics.checkNotNullParameter(rect, "rect");
        setRect(rect);
    }

    public final void setRect(RectF rect) {
        Intrinsics.checkNotNullParameter(rect, "rect");
        setRect(rect.left, rect.top, rect.right, rect.bottom);
    }

    public final void setRect(float left, float top, float right, float bottom) {
        getVertexArray().clear();
        getVertexArray().put(left);
        getVertexArray().put(bottom);
        getVertexArray().put(right);
        getVertexArray().put(bottom);
        getVertexArray().put(left);
        getVertexArray().put(top);
        getVertexArray().put(right);
        getVertexArray().put(top);
        getVertexArray().flip();
        notifyVertexArrayChange();
    }

    @Override // com.otaliastudios.opengl.draw.GlDrawable
    public void draw() {
        Egloo.checkGlError("glDrawArrays start");
        GLES20.glDrawArrays(GlKt.getGL_TRIANGLE_STRIP(), 0, getVertexCount());
        Egloo.checkGlError("glDrawArrays end");
    }
}
