package com.otaliastudios.opengl.program;

import android.graphics.RectF;
import android.opengl.GLES20;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.otaliastudios.opengl.core.Egloo;
import com.otaliastudios.opengl.draw.Gl2dDrawable;
import com.otaliastudios.opengl.draw.GlDrawable;
import com.otaliastudios.opengl.internal.GlKt;
import com.otaliastudios.opengl.internal.MiscKt;
import com.otaliastudios.opengl.texture.GlTexture;
import com.otaliastudios.opengl.types.BuffersJvmKt;
import com.otaliastudios.opengl.types.BuffersKt;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlTextureProgram.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 82\u00020\u0001:\u00018BG\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tB;\b\u0017\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\fB;\b\u0004\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000fJ8\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u000b2\u0006\u0010-\u001a\u00020\u00112\u0006\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u00020+2\u0006\u00100\u001a\u00020+2\u0006\u00101\u001a\u00020\u000eH\u0014J\u0010\u00102\u001a\u0002032\u0006\u0010-\u001a\u000204H\u0016J\u0018\u00105\u001a\u0002032\u0006\u0010-\u001a\u0002042\u0006\u00106\u001a\u00020\"H\u0016J\b\u00107\u001a\u000203H\u0016R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00060\u0013j\u0002`\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0012\u0010\u001c\u001a\u00060\u001dj\u0002`\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0010\u0010'\u001a\u0004\u0018\u00010 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/otaliastudios/opengl/program/GlTextureProgram;", "Lcom/otaliastudios/opengl/program/GlProgram;", "vertexShader", "", "fragmentShader", "vertexPositionName", "vertexMvpMatrixName", "textureCoordsName", "textureTransformName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "handle", "", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "ownsHandle", "", "(IZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "lastDrawable", "Lcom/otaliastudios/opengl/draw/Gl2dDrawable;", "lastDrawableBounds", "Landroid/graphics/RectF;", "Lcom/otaliastudios/opengl/geometry/RectF;", "lastDrawableVersion", "texture", "Lcom/otaliastudios/opengl/texture/GlTexture;", "getTexture", "()Lcom/otaliastudios/opengl/texture/GlTexture;", "setTexture", "(Lcom/otaliastudios/opengl/texture/GlTexture;)V", "textureCoordsBuffer", "Ljava/nio/FloatBuffer;", "Lcom/otaliastudios/opengl/types/FloatBuffer;", "textureCoordsHandle", "Lcom/otaliastudios/opengl/program/GlProgramLocation;", "textureTransform", "", "getTextureTransform", "()[F", "setTextureTransform", "([F)V", "textureTransformHandle", "vertexMvpMatrixHandle", "vertexPositionHandle", "computeTextureCoordinate", "", "vertex", "drawable", "value", "min", "max", WorkflowModule.Properties.Section.Component.Type.HORIZONTAL, "onPostDraw", "", "Lcom/otaliastudios/opengl/draw/GlDrawable;", "onPreDraw", "modelViewProjectionMatrix", "release", "Companion", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public class GlTextureProgram extends GlProgram {
    public static final String SIMPLE_FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n";
    public static final String SIMPLE_VERTEX_SHADER = "uniform mat4 uMVPMatrix;\nuniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n";
    private Gl2dDrawable lastDrawable;
    private final RectF lastDrawableBounds;
    private int lastDrawableVersion;
    private GlTexture texture;
    private FloatBuffer textureCoordsBuffer;
    private final GlProgramLocation textureCoordsHandle;
    private float[] textureTransform;
    private final GlProgramLocation textureTransformHandle;
    private final GlProgramLocation vertexMvpMatrixHandle;
    private final GlProgramLocation vertexPositionHandle;

    public GlTextureProgram() {
        this(null, null, null, null, null, null, 63, null);
    }

    public GlTextureProgram(int i) {
        this(i, null, null, null, null, 30, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GlTextureProgram(int i, String vertexPositionName) {
        this(i, vertexPositionName, null, null, null, 28, null);
        Intrinsics.checkNotNullParameter(vertexPositionName, "vertexPositionName");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GlTextureProgram(int i, String vertexPositionName, String vertexMvpMatrixName) {
        this(i, vertexPositionName, vertexMvpMatrixName, null, null, 24, null);
        Intrinsics.checkNotNullParameter(vertexPositionName, "vertexPositionName");
        Intrinsics.checkNotNullParameter(vertexMvpMatrixName, "vertexMvpMatrixName");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GlTextureProgram(int i, String vertexPositionName, String vertexMvpMatrixName, String str) {
        this(i, vertexPositionName, vertexMvpMatrixName, str, null, 16, null);
        Intrinsics.checkNotNullParameter(vertexPositionName, "vertexPositionName");
        Intrinsics.checkNotNullParameter(vertexMvpMatrixName, "vertexMvpMatrixName");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GlTextureProgram(String vertexShader) {
        this(vertexShader, null, null, null, null, null, 62, null);
        Intrinsics.checkNotNullParameter(vertexShader, "vertexShader");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GlTextureProgram(String vertexShader, String fragmentShader) {
        this(vertexShader, fragmentShader, null, null, null, null, 60, null);
        Intrinsics.checkNotNullParameter(vertexShader, "vertexShader");
        Intrinsics.checkNotNullParameter(fragmentShader, "fragmentShader");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GlTextureProgram(String vertexShader, String fragmentShader, String vertexPositionName) {
        this(vertexShader, fragmentShader, vertexPositionName, null, null, null, 56, null);
        Intrinsics.checkNotNullParameter(vertexShader, "vertexShader");
        Intrinsics.checkNotNullParameter(fragmentShader, "fragmentShader");
        Intrinsics.checkNotNullParameter(vertexPositionName, "vertexPositionName");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GlTextureProgram(String vertexShader, String fragmentShader, String vertexPositionName, String vertexMvpMatrixName) {
        this(vertexShader, fragmentShader, vertexPositionName, vertexMvpMatrixName, null, null, 48, null);
        Intrinsics.checkNotNullParameter(vertexShader, "vertexShader");
        Intrinsics.checkNotNullParameter(fragmentShader, "fragmentShader");
        Intrinsics.checkNotNullParameter(vertexPositionName, "vertexPositionName");
        Intrinsics.checkNotNullParameter(vertexMvpMatrixName, "vertexMvpMatrixName");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GlTextureProgram(String vertexShader, String fragmentShader, String vertexPositionName, String vertexMvpMatrixName, String str) {
        this(vertexShader, fragmentShader, vertexPositionName, vertexMvpMatrixName, str, null, 32, null);
        Intrinsics.checkNotNullParameter(vertexShader, "vertexShader");
        Intrinsics.checkNotNullParameter(fragmentShader, "fragmentShader");
        Intrinsics.checkNotNullParameter(vertexPositionName, "vertexPositionName");
        Intrinsics.checkNotNullParameter(vertexMvpMatrixName, "vertexMvpMatrixName");
    }

    protected float computeTextureCoordinate(int vertex, Gl2dDrawable drawable, float value, float min, float max, boolean horizontal) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        return (((value - min) / (max - min)) * 1.0f) + 0.0f;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    protected GlTextureProgram(int i, boolean z, String vertexPositionName, String vertexMvpMatrixName, String str, String str2) {
        super(i, z, new GlShader[0]);
        Intrinsics.checkNotNullParameter(vertexPositionName, "vertexPositionName");
        Intrinsics.checkNotNullParameter(vertexMvpMatrixName, "vertexMvpMatrixName");
        this.textureTransform = MiscKt.matrixClone(Egloo.IDENTITY_MATRIX);
        this.textureTransformHandle = str2 == null ? null : getUniformHandle(str2);
        this.textureCoordsBuffer = BuffersJvmKt.floatBuffer(8);
        this.textureCoordsHandle = str != null ? getAttribHandle(str) : null;
        this.vertexPositionHandle = getAttribHandle(vertexPositionName);
        this.vertexMvpMatrixHandle = getUniformHandle(vertexMvpMatrixName);
        this.lastDrawableBounds = new RectF();
        this.lastDrawableVersion = -1;
    }

    public /* synthetic */ GlTextureProgram(String str, String str2, String str3, String str4, String str5, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? SIMPLE_VERTEX_SHADER : str, (i & 2) != 0 ? SIMPLE_FRAGMENT_SHADER : str2, (i & 4) != 0 ? "aPosition" : str3, (i & 8) != 0 ? "uMVPMatrix" : str4, (i & 16) != 0 ? "aTextureCoord" : str5, (i & 32) != 0 ? "uTexMatrix" : str6);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GlTextureProgram(String vertexShader, String fragmentShader, String vertexPositionName, String vertexMvpMatrixName, String str, String str2) {
        this(GlProgram.INSTANCE.create(vertexShader, fragmentShader), true, vertexPositionName, vertexMvpMatrixName, str, str2);
        Intrinsics.checkNotNullParameter(vertexShader, "vertexShader");
        Intrinsics.checkNotNullParameter(fragmentShader, "fragmentShader");
        Intrinsics.checkNotNullParameter(vertexPositionName, "vertexPositionName");
        Intrinsics.checkNotNullParameter(vertexMvpMatrixName, "vertexMvpMatrixName");
    }

    public /* synthetic */ GlTextureProgram(int i, String str, String str2, String str3, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? "aPosition" : str, (i2 & 4) != 0 ? "uMVPMatrix" : str2, (i2 & 8) != 0 ? "aTextureCoord" : str3, (i2 & 16) != 0 ? "uTexMatrix" : str4);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GlTextureProgram(int i, String vertexPositionName, String vertexMvpMatrixName, String str, String str2) {
        this(i, false, vertexPositionName, vertexMvpMatrixName, str, str2);
        Intrinsics.checkNotNullParameter(vertexPositionName, "vertexPositionName");
        Intrinsics.checkNotNullParameter(vertexMvpMatrixName, "vertexMvpMatrixName");
    }

    public final float[] getTextureTransform() {
        return this.textureTransform;
    }

    public final void setTextureTransform(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<set-?>");
        this.textureTransform = fArr;
    }

    public final GlTexture getTexture() {
        return this.texture;
    }

    public final void setTexture(GlTexture glTexture) {
        this.texture = glTexture;
    }

    @Override // com.otaliastudios.opengl.program.GlProgram
    public void onPreDraw(GlDrawable drawable, float[] modelViewProjectionMatrix) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        Intrinsics.checkNotNullParameter(modelViewProjectionMatrix, "modelViewProjectionMatrix");
        super.onPreDraw(drawable, modelViewProjectionMatrix);
        if (!(drawable instanceof Gl2dDrawable)) {
            throw new RuntimeException("GlTextureProgram only supports 2D drawables.");
        }
        GlTexture glTexture = this.texture;
        if (glTexture != null) {
            glTexture.bind();
        }
        boolean z = true;
        GLES20.glUniformMatrix4fv(this.vertexMvpMatrixHandle.getValue(), 1, false, modelViewProjectionMatrix, 0);
        Egloo.checkGlError("glUniformMatrix4fv");
        GlProgramLocation glProgramLocation = this.textureTransformHandle;
        if (glProgramLocation != null) {
            GLES20.glUniformMatrix4fv(glProgramLocation.getValue(), 1, false, getTextureTransform(), 0);
            Egloo.checkGlError("glUniformMatrix4fv");
        }
        GlProgramLocation glProgramLocation2 = this.vertexPositionHandle;
        GLES20.glEnableVertexAttribArray(glProgramLocation2.getUvalue());
        Egloo.checkGlError("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(glProgramLocation2.getUvalue(), 2, GlKt.getGL_FLOAT(), false, drawable.getVertexStride(), (Buffer) drawable.getVertexArray());
        Egloo.checkGlError("glVertexAttribPointer");
        GlProgramLocation glProgramLocation3 = this.textureCoordsHandle;
        if (glProgramLocation3 == null) {
            return;
        }
        if (!Intrinsics.areEqual(drawable, this.lastDrawable) || drawable.getVertexArrayVersion() != this.lastDrawableVersion) {
            Gl2dDrawable gl2dDrawable = (Gl2dDrawable) drawable;
            this.lastDrawable = gl2dDrawable;
            this.lastDrawableVersion = drawable.getVertexArrayVersion();
            gl2dDrawable.getBounds(this.lastDrawableBounds);
            int vertexCount = drawable.getVertexCount() * 2;
            if (this.textureCoordsBuffer.capacity() < vertexCount) {
                BuffersKt.dispose(this.textureCoordsBuffer);
                this.textureCoordsBuffer = BuffersJvmKt.floatBuffer(vertexCount);
            }
            this.textureCoordsBuffer.clear();
            this.textureCoordsBuffer.limit(vertexCount);
            if (vertexCount > 0) {
                int i = 0;
                while (true) {
                    int i2 = i + 1;
                    boolean z2 = i % 2 == 0 ? z : false;
                    float f = drawable.getVertexArray().get(i);
                    RectF rectF = this.lastDrawableBounds;
                    float f2 = z2 ? rectF.left : rectF.bottom;
                    RectF rectF2 = this.lastDrawableBounds;
                    this.textureCoordsBuffer.put(computeTextureCoordinate(i / 2, gl2dDrawable, f, f2, z2 ? rectF2.right : rectF2.top, z2));
                    if (i2 >= vertexCount) {
                        break;
                    }
                    i = i2;
                    z = true;
                }
            }
        }
        this.textureCoordsBuffer.rewind();
        GLES20.glEnableVertexAttribArray(glProgramLocation3.getUvalue());
        Egloo.checkGlError("glEnableVertexAttribArray");
        GLES20.glVertexAttribPointer(glProgramLocation3.getUvalue(), 2, GlKt.getGL_FLOAT(), false, drawable.getVertexStride(), (Buffer) this.textureCoordsBuffer);
        Egloo.checkGlError("glVertexAttribPointer");
    }

    @Override // com.otaliastudios.opengl.program.GlProgram
    public void onPostDraw(GlDrawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        super.onPostDraw(drawable);
        GLES20.glDisableVertexAttribArray(this.vertexPositionHandle.getUvalue());
        GlProgramLocation glProgramLocation = this.textureCoordsHandle;
        if (glProgramLocation != null) {
            GLES20.glDisableVertexAttribArray(glProgramLocation.getUvalue());
        }
        GlTexture glTexture = this.texture;
        if (glTexture != null) {
            glTexture.unbind();
        }
        Egloo.checkGlError("onPostDraw end");
    }

    @Override // com.otaliastudios.opengl.program.GlProgram
    public void release() {
        super.release();
        BuffersKt.dispose(this.textureCoordsBuffer);
        GlTexture glTexture = this.texture;
        if (glTexture != null) {
            glTexture.release();
        }
        this.texture = null;
    }
}
