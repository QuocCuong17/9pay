package com.otaliastudios.opengl.core;

import android.opengl.EGLConfig;
import android.opengl.EGLDisplay;
import android.opengl.GLSurfaceView;
import co.hyperverge.hypersnapsdk.activities.HVRetakeActivity;
import com.otaliastudios.opengl.internal.EglConfig;
import com.otaliastudios.opengl.internal.EglDisplay;
import java.util.Objects;
import javax.microedition.khronos.egl.EGL10;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EglConfigChooser.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0016\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002¨\u0006\u0011"}, d2 = {"Lcom/otaliastudios/opengl/core/EglConfigChooser;", "Lcom/otaliastudios/opengl/core/EglNativeConfigChooser;", "()V", "GLES2", "Landroid/opengl/GLSurfaceView$EGLConfigChooser;", "getGLES2$annotations", "GLES3", "getGLES3$annotations", "getConfig", "Landroid/opengl/EGLConfig;", "display", "Landroid/opengl/EGLDisplay;", "version", "", "recordable", "", "Chooser", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class EglConfigChooser extends EglNativeConfigChooser {
    public static final EglConfigChooser INSTANCE = new EglConfigChooser();
    public static final GLSurfaceView.EGLConfigChooser GLES2 = new Chooser(2);
    public static final GLSurfaceView.EGLConfigChooser GLES3 = new Chooser(3);

    public static /* synthetic */ void getGLES2$annotations() {
    }

    public static /* synthetic */ void getGLES3$annotations() {
    }

    private EglConfigChooser() {
    }

    @JvmStatic
    public static final EGLConfig getConfig(EGLDisplay display, int version, boolean recordable) {
        Intrinsics.checkNotNullParameter(display, "display");
        EglConfig config$library_release = super.getConfig$library_release(new EglDisplay(display), version, recordable);
        if (config$library_release == null) {
            return null;
        }
        return config$library_release.getNative();
    }

    /* compiled from: EglConfigChooser.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J-\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\fH\u0002¢\u0006\u0002\u0010\rJ,\u0010\u000e\u001a\u00020\u0003*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/otaliastudios/opengl/core/EglConfigChooser$Chooser;", "Landroid/opengl/GLSurfaceView$EGLConfigChooser;", "version", "", "(I)V", "chooseConfig", "Ljavax/microedition/khronos/egl/EGLConfig;", "egl", "Ljavax/microedition/khronos/egl/EGL10;", "display", "Ljavax/microedition/khronos/egl/EGLDisplay;", "configs", "", "(Ljavax/microedition/khronos/egl/EGL10;Ljavax/microedition/khronos/egl/EGLDisplay;[Ljavax/microedition/khronos/egl/EGLConfig;)Ljavax/microedition/khronos/egl/EGLConfig;", "findConfigAttrib", HVRetakeActivity.CONFIG_TAG, "attribute", "defaultValue", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    private static final class Chooser implements GLSurfaceView.EGLConfigChooser {
        private final int version;

        public Chooser(int i) {
            this.version = i;
        }

        @Override // android.opengl.GLSurfaceView.EGLConfigChooser
        public javax.microedition.khronos.egl.EGLConfig chooseConfig(EGL10 egl, javax.microedition.khronos.egl.EGLDisplay display) {
            Intrinsics.checkNotNullParameter(egl, "egl");
            Intrinsics.checkNotNullParameter(display, "display");
            int[] iArr = new int[1];
            int[] configSpec$library_release = EglConfigChooser.INSTANCE.getConfigSpec$library_release(this.version, true);
            if (!egl.eglChooseConfig(display, configSpec$library_release, null, 0, iArr)) {
                throw new IllegalArgumentException("eglChooseConfig failed");
            }
            int i = iArr[0];
            if (i <= 0) {
                throw new IllegalArgumentException("No configs match configSpec");
            }
            javax.microedition.khronos.egl.EGLConfig[] eGLConfigArr = new javax.microedition.khronos.egl.EGLConfig[i];
            if (!egl.eglChooseConfig(display, configSpec$library_release, eGLConfigArr, i, iArr)) {
                throw new IllegalArgumentException("eglChooseConfig#2 failed");
            }
            Object[] array = ArraysKt.filterNotNull(eGLConfigArr).toArray(new javax.microedition.khronos.egl.EGLConfig[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
            javax.microedition.khronos.egl.EGLConfig chooseConfig = chooseConfig(egl, display, (javax.microedition.khronos.egl.EGLConfig[]) array);
            if (chooseConfig != null) {
                return chooseConfig;
            }
            throw new IllegalArgumentException("No config chosen");
        }

        private final javax.microedition.khronos.egl.EGLConfig chooseConfig(EGL10 egl, javax.microedition.khronos.egl.EGLDisplay display, javax.microedition.khronos.egl.EGLConfig[] configs) {
            int length = configs.length;
            int i = 0;
            while (i < length) {
                javax.microedition.khronos.egl.EGLConfig eGLConfig = configs[i];
                i++;
                int findConfigAttrib = findConfigAttrib(egl, display, eGLConfig, 12325, 0);
                int findConfigAttrib2 = findConfigAttrib(egl, display, eGLConfig, 12326, 0);
                if (findConfigAttrib >= 0 && findConfigAttrib2 >= 0) {
                    int findConfigAttrib3 = findConfigAttrib(egl, display, eGLConfig, 12324, 0);
                    int findConfigAttrib4 = findConfigAttrib(egl, display, eGLConfig, 12323, 0);
                    int findConfigAttrib5 = findConfigAttrib(egl, display, eGLConfig, 12322, 0);
                    int findConfigAttrib6 = findConfigAttrib(egl, display, eGLConfig, 12321, 0);
                    if (findConfigAttrib3 == 8 && findConfigAttrib4 == 8 && findConfigAttrib5 == 8 && findConfigAttrib6 == 8) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }

        private final int findConfigAttrib(EGL10 egl10, javax.microedition.khronos.egl.EGLDisplay eGLDisplay, javax.microedition.khronos.egl.EGLConfig eGLConfig, int i, int i2) {
            int[] iArr = new int[1];
            return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, iArr) ? iArr[0] : i2;
        }
    }
}
