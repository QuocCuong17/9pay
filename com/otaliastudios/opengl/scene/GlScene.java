package com.otaliastudios.opengl.scene;

import com.otaliastudios.opengl.core.Egloo;
import com.otaliastudios.opengl.core.GlViewportAware;
import com.otaliastudios.opengl.draw.GlDrawable;
import com.otaliastudios.opengl.internal.MiscKt;
import com.otaliastudios.opengl.program.GlProgram;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlScene.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0016\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\u00020\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\u00020\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\u0002\u001a\u0004\b\f\u0010\t¨\u0006\u0014"}, d2 = {"Lcom/otaliastudios/opengl/scene/GlScene;", "Lcom/otaliastudios/opengl/core/GlViewportAware;", "()V", "modelViewMatrix", "", "modelViewProjectionMatrix", "projectionMatrix", "getProjectionMatrix$annotations", "getProjectionMatrix", "()[F", "viewMatrix", "getViewMatrix$annotations", "getViewMatrix", "computeModelViewProjectionMatrix", "", "drawable", "Lcom/otaliastudios/opengl/draw/GlDrawable;", "draw", "program", "Lcom/otaliastudios/opengl/program/GlProgram;", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public class GlScene extends GlViewportAware {
    private final float[] projectionMatrix = MiscKt.matrixClone(Egloo.IDENTITY_MATRIX);
    private final float[] viewMatrix = MiscKt.matrixClone(Egloo.IDENTITY_MATRIX);
    private final float[] modelViewMatrix = new float[16];
    private final float[] modelViewProjectionMatrix = new float[16];

    public static /* synthetic */ void getProjectionMatrix$annotations() {
    }

    public static /* synthetic */ void getViewMatrix$annotations() {
    }

    public final float[] getProjectionMatrix() {
        return this.projectionMatrix;
    }

    public final float[] getViewMatrix() {
        return this.viewMatrix;
    }

    private final void computeModelViewProjectionMatrix(GlDrawable drawable) {
        MiscKt.matrixMultiply(this.modelViewMatrix, this.viewMatrix, drawable.getModelMatrix());
        MiscKt.matrixMultiply(this.modelViewProjectionMatrix, this.projectionMatrix, this.modelViewMatrix);
    }

    public final void draw(GlProgram program, GlDrawable drawable) {
        Intrinsics.checkNotNullParameter(program, "program");
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        ensureViewportSize();
        drawable.setViewportSize(getViewportWidth(), getViewportHeight());
        computeModelViewProjectionMatrix(drawable);
        program.draw(drawable, this.modelViewProjectionMatrix);
    }
}
