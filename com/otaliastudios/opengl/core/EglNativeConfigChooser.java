package com.otaliastudios.opengl.core;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.util.Log;
import com.otaliastudios.opengl.internal.EglConfig;
import com.otaliastudios.opengl.internal.EglDisplay;
import com.otaliastudios.opengl.internal.EglKt;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EglNativeConfigChooser.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\b\u0016\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J'\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000bJ\u001d\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000e¨\u0006\u0010"}, d2 = {"Lcom/otaliastudios/opengl/core/EglNativeConfigChooser;", "", "()V", "getConfig", "Lcom/otaliastudios/opengl/internal/EglConfig;", "display", "Lcom/otaliastudios/opengl/internal/EglDisplay;", "version", "", "recordable", "", "getConfig$library_release", "getConfigSpec", "", "getConfigSpec$library_release", "Companion", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public class EglNativeConfigChooser {
    private static final int EGL_RECORDABLE_ANDROID = 12610;

    public final EglConfig getConfig$library_release(EglDisplay display, int version, boolean recordable) {
        Intrinsics.checkNotNullParameter(display, "display");
        EglConfig[] eglConfigArr = new EglConfig[1];
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        boolean eglChooseConfig = EGL14.eglChooseConfig(display.getNative(), getConfigSpec$library_release(version, recordable), 0, eGLConfigArr, 0, 1, new int[1], 0);
        if (eglChooseConfig) {
            Iterator<Integer> it = ArraysKt.getIndices(eglConfigArr).iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                EGLConfig eGLConfig = eGLConfigArr[nextInt];
                eglConfigArr[nextInt] = eGLConfig == null ? null : new EglConfig(eGLConfig);
            }
        }
        if (!eglChooseConfig) {
            Log.w("EglConfigChooser", "Unable to find RGB8888 / " + version + " EGLConfig");
            return null;
        }
        return eglConfigArr[0];
    }

    public final int[] getConfigSpec$library_release(int version, boolean recordable) {
        int egl_opengl_es2_bit;
        if (version >= 3) {
            egl_opengl_es2_bit = EglKt.getEGL_OPENGL_ES2_BIT() | EglKt.getEGL_OPENGL_ES3_BIT_KHR();
        } else {
            egl_opengl_es2_bit = EglKt.getEGL_OPENGL_ES2_BIT();
        }
        int[] iArr = new int[15];
        iArr[0] = EglKt.getEGL_RED_SIZE();
        iArr[1] = 8;
        iArr[2] = EglKt.getEGL_GREEN_SIZE();
        iArr[3] = 8;
        iArr[4] = EglKt.getEGL_BLUE_SIZE();
        iArr[5] = 8;
        iArr[6] = EglKt.getEGL_ALPHA_SIZE();
        iArr[7] = 8;
        iArr[8] = EglKt.getEGL_SURFACE_TYPE();
        iArr[9] = EglKt.getEGL_WINDOW_BIT() | EglKt.getEGL_PBUFFER_BIT();
        iArr[10] = EglKt.getEGL_RENDERABLE_TYPE();
        iArr[11] = egl_opengl_es2_bit;
        iArr[12] = recordable ? EGL_RECORDABLE_ANDROID : EglKt.getEGL_NONE();
        iArr[13] = recordable ? 1 : 0;
        iArr[14] = EglKt.getEGL_NONE();
        return iArr;
    }
}
