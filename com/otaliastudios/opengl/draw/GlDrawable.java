package com.otaliastudios.opengl.draw;

import com.otaliastudios.opengl.core.Egloo;
import com.otaliastudios.opengl.core.GlViewportAware;
import com.otaliastudios.opengl.internal.MiscKt;
import com.otaliastudios.opengl.types.BuffersKt;
import java.nio.FloatBuffer;
import kotlin.Metadata;

/* compiled from: GlDrawable.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH&J\b\u0010\u001b\u001a\u00020\u001aH\u0004J\b\u0010\u001c\u001a\u00020\u001aH\u0016R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u00060\fj\u0002`\rX¦\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0006R\u0014\u0010\u0017\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0006¨\u0006\u001d"}, d2 = {"Lcom/otaliastudios/opengl/draw/GlDrawable;", "Lcom/otaliastudios/opengl/core/GlViewportAware;", "()V", "coordsPerVertex", "", "getCoordsPerVertex", "()I", "modelMatrix", "", "getModelMatrix", "()[F", "vertexArray", "Ljava/nio/FloatBuffer;", "Lcom/otaliastudios/opengl/types/FloatBuffer;", "getVertexArray", "()Ljava/nio/FloatBuffer;", "setVertexArray", "(Ljava/nio/FloatBuffer;)V", "<set-?>", "vertexArrayVersion", "getVertexArrayVersion", "vertexCount", "getVertexCount", "vertexStride", "getVertexStride", "draw", "", "notifyVertexArrayChange", "release", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public abstract class GlDrawable extends GlViewportAware {
    private final float[] modelMatrix = MiscKt.matrixClone(Egloo.IDENTITY_MATRIX);
    private int vertexArrayVersion;

    public abstract void draw();

    public abstract int getCoordsPerVertex();

    public abstract FloatBuffer getVertexArray();

    public abstract void setVertexArray(FloatBuffer floatBuffer);

    public final float[] getModelMatrix() {
        return this.modelMatrix;
    }

    public int getVertexStride() {
        return getCoordsPerVertex() * 4;
    }

    public int getVertexCount() {
        return getVertexArray().limit() / getCoordsPerVertex();
    }

    public final int getVertexArrayVersion() {
        return this.vertexArrayVersion;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void notifyVertexArrayChange() {
        this.vertexArrayVersion++;
    }

    public void release() {
        BuffersKt.dispose(getVertexArray());
    }
}
