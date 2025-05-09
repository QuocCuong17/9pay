package com.otaliastudios.opengl.program;

import android.opengl.GLES20;
import com.otaliastudios.opengl.core.Egloo;
import com.otaliastudios.opengl.draw.GlDrawable;
import com.otaliastudios.opengl.internal.GlKt;
import java.nio.Buffer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlFlatProgram.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0004H\u0016R \u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/otaliastudios/opengl/program/GlNativeFlatProgram;", "Lcom/otaliastudios/opengl/program/GlProgram;", "()V", "color", "", "getColor$annotations", "getColor", "()[F", "setColor", "([F)V", "fragmentColorHandle", "Lcom/otaliastudios/opengl/program/GlProgramLocation;", "vertexMvpMatrixHandle", "vertexPositionHandle", "onPostDraw", "", "drawable", "Lcom/otaliastudios/opengl/draw/GlDrawable;", "onPreDraw", "modelViewProjectionMatrix", "Companion", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public class GlNativeFlatProgram extends GlProgram {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    private static final String FRAGMENT_SHADER = "precision mediump float;\nuniform vec4 uColor;\nvoid main() {\n    gl_FragColor = uColor;\n}\n";

    @Deprecated
    private static final String VERTEX_SHADER = "uniform mat4 uMVPMatrix;\nattribute vec4 aPosition;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n}\n";
    private float[] color;
    private final GlProgramLocation fragmentColorHandle;
    private final GlProgramLocation vertexMvpMatrixHandle;
    private final GlProgramLocation vertexPositionHandle;

    public static /* synthetic */ void getColor$annotations() {
    }

    public GlNativeFlatProgram() {
        super(VERTEX_SHADER, FRAGMENT_SHADER);
        this.vertexPositionHandle = getAttribHandle("aPosition");
        this.vertexMvpMatrixHandle = getUniformHandle("uMVPMatrix");
        this.fragmentColorHandle = getUniformHandle("uColor");
        this.color = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
    }

    public final float[] getColor() {
        return this.color;
    }

    public final void setColor(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<set-?>");
        this.color = fArr;
    }

    @Override // com.otaliastudios.opengl.program.GlProgram
    public void onPreDraw(GlDrawable drawable, float[] modelViewProjectionMatrix) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        Intrinsics.checkNotNullParameter(modelViewProjectionMatrix, "modelViewProjectionMatrix");
        super.onPreDraw(drawable, modelViewProjectionMatrix);
        GLES20.glUniformMatrix4fv(this.vertexMvpMatrixHandle.getValue(), 1, false, modelViewProjectionMatrix, 0);
        Egloo.checkGlError("glUniformMatrix4fv");
        GLES20.glUniform4fv(this.fragmentColorHandle.getValue(), 1, this.color, 0);
        Egloo.checkGlError("glUniform4fv");
        GLES20.glEnableVertexAttribArray(this.vertexPositionHandle.getUvalue());
        Egloo.checkGlError("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(this.vertexPositionHandle.getUvalue(), drawable.getCoordsPerVertex(), GlKt.getGL_FLOAT(), false, drawable.getVertexStride(), (Buffer) drawable.getVertexArray());
        Egloo.checkGlError("glVertexAttribPointer");
    }

    @Override // com.otaliastudios.opengl.program.GlProgram
    public void onPostDraw(GlDrawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        super.onPostDraw(drawable);
        GLES20.glDisableVertexAttribArray(this.vertexPositionHandle.getUvalue());
    }

    /* compiled from: GlFlatProgram.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/otaliastudios/opengl/program/GlNativeFlatProgram$Companion;", "", "()V", "FRAGMENT_SHADER", "", "VERTEX_SHADER", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
