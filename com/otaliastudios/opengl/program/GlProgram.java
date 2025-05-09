package com.otaliastudios.opengl.program;

import android.opengl.GLES20;
import com.otaliastudios.opengl.core.Egloo;
import com.otaliastudios.opengl.core.GlBindable;
import com.otaliastudios.opengl.core.GlBindableKt;
import com.otaliastudios.opengl.draw.GlDrawable;
import com.otaliastudios.opengl.internal.GlKt;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlProgram.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000 $2\u00020\u0001:\u0001$B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bB\u001b\b\u0016\u0012\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n\"\u00020\u000b¢\u0006\u0002\u0010\fB+\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n\"\u00020\u000b¢\u0006\u0002\u0010\u000fJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u001a\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0007J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0006H\u0004J\u0010\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0006H\u0004J\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010 \u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010!\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\"\u001a\u00020\u0015H\u0016J\b\u0010#\u001a\u00020\u0015H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013¨\u0006%"}, d2 = {"Lcom/otaliastudios/opengl/program/GlProgram;", "Lcom/otaliastudios/opengl/core/GlBindable;", "handle", "", "(I)V", "vertexShader", "", "fragmentShader", "(Ljava/lang/String;Ljava/lang/String;)V", "shaders", "", "Lcom/otaliastudios/opengl/program/GlShader;", "([Lcom/otaliastudios/opengl/program/GlShader;)V", "ownsHandle", "", "(IZ[Lcom/otaliastudios/opengl/program/GlShader;)V", "getHandle", "()I", "isReleased", "[Lcom/otaliastudios/opengl/program/GlShader;", "bind", "", "draw", "drawable", "Lcom/otaliastudios/opengl/draw/GlDrawable;", "modelViewProjectionMatrix", "", "getAttribHandle", "Lcom/otaliastudios/opengl/program/GlProgramLocation;", "name", "getUniformHandle", "onDraw", "onPostDraw", "onPreDraw", "release", "unbind", "Companion", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public class GlProgram implements GlBindable {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int handle;
    private boolean isReleased;
    private final boolean ownsHandle;
    private final GlShader[] shaders;

    @Deprecated(message = "Use create(GlShader) signature.")
    @JvmStatic
    public static final int create(String str, String str2) {
        return INSTANCE.create(str, str2);
    }

    @JvmStatic
    public static final int create(GlShader... glShaderArr) {
        return INSTANCE.create(glShaderArr);
    }

    public final void draw(GlDrawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        draw$default(this, drawable, null, 2, null);
    }

    public void onPostDraw(GlDrawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
    }

    public void onPreDraw(GlDrawable drawable, float[] modelViewProjectionMatrix) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        Intrinsics.checkNotNullParameter(modelViewProjectionMatrix, "modelViewProjectionMatrix");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GlProgram(int i, boolean z, GlShader... shaders) {
        Intrinsics.checkNotNullParameter(shaders, "shaders");
        this.handle = i;
        this.ownsHandle = z;
        this.shaders = shaders;
    }

    public final int getHandle() {
        return this.handle;
    }

    public GlProgram(int i) {
        this(i, false, new GlShader[0]);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GlProgram(String vertexShader, String fragmentShader) {
        this(new GlShader(GlKt.getGL_VERTEX_SHADER(), vertexShader), new GlShader(GlKt.getGL_FRAGMENT_SHADER(), fragmentShader));
        Intrinsics.checkNotNullParameter(vertexShader, "vertexShader");
        Intrinsics.checkNotNullParameter(fragmentShader, "fragmentShader");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GlProgram(GlShader... shaders) {
        this(INSTANCE.create((GlShader[]) Arrays.copyOf(shaders, shaders.length)), true, (GlShader[]) Arrays.copyOf(shaders, shaders.length));
        Intrinsics.checkNotNullParameter(shaders, "shaders");
    }

    public void release() {
        if (this.isReleased) {
            return;
        }
        if (this.ownsHandle) {
            GLES20.glDeleteProgram(UInt.m1297constructorimpl(this.handle));
        }
        for (GlShader glShader : this.shaders) {
            glShader.release();
        }
        this.isReleased = true;
    }

    @Override // com.otaliastudios.opengl.core.GlBindable
    public void bind() {
        GLES20.glUseProgram(UInt.m1297constructorimpl(this.handle));
        Egloo.checkGlError("glUseProgram");
    }

    public static /* synthetic */ void draw$default(GlProgram glProgram, GlDrawable glDrawable, float[] fArr, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: draw");
        }
        if ((i & 2) != 0) {
            fArr = glDrawable.getModelMatrix();
        }
        glProgram.draw(glDrawable, fArr);
    }

    public final void draw(final GlDrawable drawable, final float[] modelViewProjectionMatrix) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        Intrinsics.checkNotNullParameter(modelViewProjectionMatrix, "modelViewProjectionMatrix");
        Egloo.checkGlError("draw start");
        GlBindableKt.use(this, new Function0<Unit>() { // from class: com.otaliastudios.opengl.program.GlProgram$draw$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                GlProgram.this.onPreDraw(drawable, modelViewProjectionMatrix);
                GlProgram.this.onDraw(drawable);
                GlProgram.this.onPostDraw(drawable);
            }
        });
        Egloo.checkGlError("draw end");
    }

    public void onDraw(GlDrawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        drawable.draw();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final GlProgramLocation getAttribHandle(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return GlProgramLocation.INSTANCE.getAttrib(this.handle, name);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final GlProgramLocation getUniformHandle(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return GlProgramLocation.INSTANCE.getUniform(this.handle, name);
    }

    /* compiled from: GlProgram.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\"\u00020\u0007H\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0007¨\u0006\f"}, d2 = {"Lcom/otaliastudios/opengl/program/GlProgram$Companion;", "", "()V", "create", "", "shaders", "", "Lcom/otaliastudios/opengl/program/GlShader;", "([Lcom/otaliastudios/opengl/program/GlShader;)I", "vertexShaderSource", "", "fragmentShaderSource", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Deprecated(message = "Use create(GlShader) signature.")
        @JvmStatic
        public final int create(String vertexShaderSource, String fragmentShaderSource) {
            Intrinsics.checkNotNullParameter(vertexShaderSource, "vertexShaderSource");
            Intrinsics.checkNotNullParameter(fragmentShaderSource, "fragmentShaderSource");
            return create(new GlShader(GlKt.getGL_VERTEX_SHADER(), vertexShaderSource), new GlShader(GlKt.getGL_FRAGMENT_SHADER(), fragmentShaderSource));
        }

        @JvmStatic
        public final int create(GlShader... shaders) {
            Intrinsics.checkNotNullParameter(shaders, "shaders");
            int m1297constructorimpl = UInt.m1297constructorimpl(GLES20.glCreateProgram());
            Egloo.checkGlError("glCreateProgram");
            if (m1297constructorimpl == 0) {
                throw new RuntimeException("Could not create program");
            }
            for (GlShader glShader : shaders) {
                GLES20.glAttachShader(m1297constructorimpl, UInt.m1297constructorimpl(glShader.getId()));
                Egloo.checkGlError("glAttachShader");
            }
            GLES20.glLinkProgram(m1297constructorimpl);
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(m1297constructorimpl, GlKt.getGL_LINK_STATUS(), iArr, 0);
            if (iArr[0] == GlKt.getGL_TRUE()) {
                return m1297constructorimpl;
            }
            String stringPlus = Intrinsics.stringPlus("Could not link program: ", GLES20.glGetProgramInfoLog(m1297constructorimpl));
            GLES20.glDeleteProgram(m1297constructorimpl);
            throw new RuntimeException(stringPlus);
        }
    }

    @Override // com.otaliastudios.opengl.core.GlBindable
    public void unbind() {
        GLES20.glUseProgram(0);
    }
}
