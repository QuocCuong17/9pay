package com.otaliastudios.opengl.core;

import android.opengl.EGL14;
import android.opengl.GLES20;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.otaliastudios.opengl.extensions.MatrixKt;
import com.otaliastudios.opengl.internal.EglContext;
import com.otaliastudios.opengl.internal.EglDisplay;
import com.otaliastudios.opengl.internal.EglKt;
import com.otaliastudios.opengl.internal.EglSurface;
import com.otaliastudios.opengl.internal.GlKt;
import com.otaliastudios.opengl.internal.MiscKt;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Egloo.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0018\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\rH\u0007J\u0010\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\rH\u0007R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/otaliastudios/opengl/core/Egloo;", "", "()V", "IDENTITY_MATRIX", "", "SIZE_OF_BYTE", "", "SIZE_OF_FLOAT", "SIZE_OF_INT", "SIZE_OF_SHORT", "checkEglError", "", "opName", "", "checkGlError", "checkGlProgramLocation", FirebaseAnalytics.Param.LOCATION, "label", "logCurrent", "msg", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class Egloo {
    public static final float[] IDENTITY_MATRIX;
    public static final Egloo INSTANCE = new Egloo();
    public static final int SIZE_OF_BYTE = 1;
    public static final int SIZE_OF_FLOAT = 4;
    public static final int SIZE_OF_INT = 4;
    public static final int SIZE_OF_SHORT = 2;

    private Egloo() {
    }

    static {
        float[] fArr = new float[16];
        MatrixKt.makeIdentity(fArr);
        IDENTITY_MATRIX = fArr;
    }

    @JvmStatic
    public static final void checkGlProgramLocation(int location, String label) {
        Intrinsics.checkNotNullParameter(label, "label");
        if (location >= 0) {
            return;
        }
        String str = "Unable to locate " + label + " in program";
        Log.e("Egloo", str);
        throw new RuntimeException(str);
    }

    @JvmStatic
    public static final void checkGlError(String opName) {
        Intrinsics.checkNotNullParameter(opName, "opName");
        int m1297constructorimpl = UInt.m1297constructorimpl(GLES20.glGetError());
        if (m1297constructorimpl == GlKt.getGL_NO_ERROR()) {
            return;
        }
        String str = "Error during " + opName + ": glError 0x" + MiscKt.intToHexString(m1297constructorimpl) + ": " + MiscKt.gluErrorString(m1297constructorimpl);
        Log.e("Egloo", str);
        throw new RuntimeException(str);
    }

    @JvmStatic
    public static final void checkEglError(String opName) {
        Intrinsics.checkNotNullParameter(opName, "opName");
        int eglGetError = EGL14.eglGetError();
        if (eglGetError == EglKt.getEGL_SUCCESS()) {
            return;
        }
        String str = "Error during " + opName + ": EGL error 0x" + MiscKt.intToHexString(eglGetError);
        Log.e("Egloo", str);
        throw new RuntimeException(str);
    }

    @JvmStatic
    public static final void logCurrent(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Log.i("Egloo", "Current EGL (" + msg + "): display=" + new EglDisplay(EGL14.eglGetCurrentDisplay()) + ", context=" + new EglContext(EGL14.eglGetCurrentContext()) + ", surface=" + new EglSurface(EGL14.eglGetCurrentSurface(EglKt.getEGL_DRAW())));
    }
}
