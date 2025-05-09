package com.otaliastudios.opengl.program;

import android.opengl.GLES20;
import com.otaliastudios.opengl.core.Egloo;
import com.otaliastudios.opengl.internal.GlKt;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlShader.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\u0006\u0010\f\u001a\u00020\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/otaliastudios/opengl/program/GlShader;", "", "type", "", "source", "", "(ILjava/lang/String;)V", "id", "(II)V", "getId", "()I", "getType", "release", "", "Companion", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class GlShader {
    private static final Companion Companion = new Companion(null);
    private final int id;
    private final int type;

    public GlShader(int i, int i2) {
        this.type = i;
        this.id = i2;
    }

    public final int getId() {
        return this.id;
    }

    public final int getType() {
        return this.type;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GlShader(int i, String source) {
        this(i, Companion.compile(i, source));
        Intrinsics.checkNotNullParameter(source, "source");
    }

    public final void release() {
        GLES20.glDeleteShader(UInt.m1297constructorimpl(this.id));
    }

    /* compiled from: GlShader.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\b"}, d2 = {"Lcom/otaliastudios/opengl/program/GlShader$Companion;", "", "()V", "compile", "", "type", "source", "", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int compile(int type, String source) {
            int m1297constructorimpl = UInt.m1297constructorimpl(GLES20.glCreateShader(UInt.m1297constructorimpl(type)));
            Egloo.checkGlError(Intrinsics.stringPlus("glCreateShader type=", Integer.valueOf(type)));
            GLES20.glShaderSource(m1297constructorimpl, source);
            GLES20.glCompileShader(m1297constructorimpl);
            int[] iArr = new int[1];
            GLES20.glGetShaderiv(m1297constructorimpl, GlKt.getGL_COMPILE_STATUS(), iArr, 0);
            if (iArr[0] != 0) {
                return m1297constructorimpl;
            }
            String str = "Could not compile shader " + type + ": '" + ((Object) GLES20.glGetShaderInfoLog(m1297constructorimpl)) + "' source: " + source;
            GLES20.glDeleteShader(m1297constructorimpl);
            throw new RuntimeException(str);
        }
    }
}
