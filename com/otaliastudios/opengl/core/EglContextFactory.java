package com.otaliastudios.opengl.core;

import android.opengl.GLSurfaceView;
import android.util.Log;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EglContextFactory.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0016\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/otaliastudios/opengl/core/EglContextFactory;", "", "()V", "GLES2", "Landroid/opengl/GLSurfaceView$EGLContextFactory;", "getGLES2$annotations", "GLES3", "getGLES3$annotations", "TAG", "", "kotlin.jvm.PlatformType", "Factory", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class EglContextFactory {
    public static final EglContextFactory INSTANCE = new EglContextFactory();
    private static final String TAG = "EglContextFactory";
    public static final GLSurfaceView.EGLContextFactory GLES2 = new Factory(2);
    public static final GLSurfaceView.EGLContextFactory GLES3 = new Factory(3);

    public static /* synthetic */ void getGLES2$annotations() {
    }

    public static /* synthetic */ void getGLES3$annotations() {
    }

    private EglContextFactory() {
    }

    /* compiled from: EglContextFactory.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/otaliastudios/opengl/core/EglContextFactory$Factory;", "Landroid/opengl/GLSurfaceView$EGLContextFactory;", "version", "", "(I)V", "createContext", "Ljavax/microedition/khronos/egl/EGLContext;", "egl", "Ljavax/microedition/khronos/egl/EGL10;", "display", "Ljavax/microedition/khronos/egl/EGLDisplay;", "eglConfig", "Ljavax/microedition/khronos/egl/EGLConfig;", "destroyContext", "", "context", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    private static final class Factory implements GLSurfaceView.EGLContextFactory {
        private final int version;

        public Factory(int i) {
            this.version = i;
        }

        @Override // android.opengl.GLSurfaceView.EGLContextFactory
        public EGLContext createContext(EGL10 egl, EGLDisplay display, EGLConfig eglConfig) {
            Intrinsics.checkNotNullParameter(egl, "egl");
            Intrinsics.checkNotNullParameter(display, "display");
            Intrinsics.checkNotNullParameter(eglConfig, "eglConfig");
            EGLContext eglCreateContext = egl.eglCreateContext(display, eglConfig, EGL10.EGL_NO_CONTEXT, new int[]{12440, this.version, 12344});
            Intrinsics.checkNotNullExpressionValue(eglCreateContext, "egl.eglCreateContext(display, eglConfig, EGL10.EGL_NO_CONTEXT, attributes)");
            return eglCreateContext;
        }

        @Override // android.opengl.GLSurfaceView.EGLContextFactory
        public void destroyContext(EGL10 egl, EGLDisplay display, EGLContext context) {
            Intrinsics.checkNotNullParameter(egl, "egl");
            Intrinsics.checkNotNullParameter(display, "display");
            Intrinsics.checkNotNullParameter(context, "context");
            if (egl.eglDestroyContext(display, context)) {
                return;
            }
            Log.e(EglContextFactory.TAG, "display:" + display + " context:" + context);
            throw new RuntimeException(Intrinsics.stringPlus("eglDestroyContex", Integer.valueOf(egl.eglGetError())));
        }
    }
}
