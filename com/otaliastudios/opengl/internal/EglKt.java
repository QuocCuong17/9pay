package com.otaliastudios.opengl.internal;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLExt;
import co.hyperverge.hypersnapsdk.activities.HVRetakeActivity;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.pqc.crypto.qteslarnd1.Polynomial;

/* compiled from: egl.kt */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\u0011\n\u0002\u0010\t\n\u0002\b\u0007\u001a>\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u00152\u0006\u00103\u001a\u0002042\u000e\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u000107062\u0006\u00108\u001a\u00020\u00012\u0006\u00109\u001a\u000204H\u0080\b¢\u0006\u0002\u0010:\u001a)\u0010;\u001a\u00020\u00112\u0006\u00102\u001a\u00020\u00152\u0006\u0010<\u001a\u0002072\u0006\u0010=\u001a\u00020\u00112\u0006\u00103\u001a\u000204H\u0080\b\u001a!\u0010>\u001a\u00020\u00192\u0006\u00102\u001a\u00020\u00152\u0006\u0010<\u001a\u0002072\u0006\u00103\u001a\u000204H\u0080\b\u001a)\u0010?\u001a\u00020\u00192\u0006\u00102\u001a\u00020\u00152\u0006\u0010<\u001a\u0002072\u0006\u0010@\u001a\u00020A2\u0006\u00103\u001a\u000204H\u0080\b\u001a\u0019\u0010B\u001a\u0002012\u0006\u00102\u001a\u00020\u00152\u0006\u0010C\u001a\u00020\u0011H\u0080\b\u001a\u0019\u0010D\u001a\u0002012\u0006\u00102\u001a\u00020\u00152\u0006\u0010@\u001a\u00020\u0019H\u0080\b\u001a\t\u0010E\u001a\u00020\u0011H\u0080\b\u001a\t\u0010F\u001a\u00020\u0015H\u0080\b\u001a\u0011\u0010G\u001a\u00020\u00192\u0006\u0010H\u001a\u00020\u0001H\u0080\b\u001a\t\u0010I\u001a\u00020\u0015H\u0080\b\u001a\t\u0010J\u001a\u00020\u0001H\u0080\b\u001a!\u0010K\u001a\u0002012\u0006\u00102\u001a\u00020\u00152\u0006\u0010L\u001a\u0002042\u0006\u0010M\u001a\u000204H\u0080\b\u001a)\u0010N\u001a\u0002012\u0006\u00102\u001a\u00020\u00152\u0006\u0010O\u001a\u00020\u00192\u0006\u0010P\u001a\u00020\u00192\u0006\u0010C\u001a\u00020\u0011H\u0080\b\u001a!\u0010Q\u001a\u0002012\u0006\u00102\u001a\u00020\u00152\u0006\u0010@\u001a\u00020\u00192\u0006\u0010R\u001a\u00020SH\u0080\b\u001a)\u0010T\u001a\u0002012\u0006\u00102\u001a\u00020\u00152\u0006\u0010@\u001a\u00020\u00192\u0006\u0010U\u001a\u00020\u00012\u0006\u0010V\u001a\u000204H\u0080\b\u001a\t\u0010W\u001a\u000201H\u0080\b\u001a\u0019\u0010X\u001a\u0002012\u0006\u00102\u001a\u00020\u00152\u0006\u0010@\u001a\u00020\u0019H\u0080\b\u001a\u0011\u0010Y\u001a\u0002012\u0006\u00102\u001a\u00020\u0015H\u0080\b\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0014\u0010\u0006\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0003\"\u0014\u0010\b\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0003\"\u0014\u0010\n\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0003\"\u0014\u0010\f\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0003\"\u0014\u0010\u000e\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0003\"\u0014\u0010\u0010\u001a\u00020\u0011X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0014\u0010\u0014\u001a\u00020\u0015X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0014\u0010\u0018\u001a\u00020\u0019X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0014\u0010\u001c\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0003\"\u0014\u0010\u001e\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0003\"\u0014\u0010 \u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0003\"\u0014\u0010\"\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0003\"\u0014\u0010$\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0003\"\u0014\u0010&\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0003\"\u0014\u0010(\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0003\"\u0014\u0010*\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0003\"\u0014\u0010,\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0003\"\u0014\u0010.\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0003¨\u0006Z"}, d2 = {"EGL_ALPHA_SIZE", "", "getEGL_ALPHA_SIZE", "()I", "EGL_BLUE_SIZE", "getEGL_BLUE_SIZE", "EGL_CONTEXT_CLIENT_VERSION", "getEGL_CONTEXT_CLIENT_VERSION", "EGL_DRAW", "getEGL_DRAW", "EGL_GREEN_SIZE", "getEGL_GREEN_SIZE", "EGL_HEIGHT", "getEGL_HEIGHT", "EGL_NONE", "getEGL_NONE", "EGL_NO_CONTEXT", "Lcom/otaliastudios/opengl/internal/EglContext;", "getEGL_NO_CONTEXT", "()Lcom/otaliastudios/opengl/internal/EglContext;", "EGL_NO_DISPLAY", "Lcom/otaliastudios/opengl/internal/EglDisplay;", "getEGL_NO_DISPLAY", "()Lcom/otaliastudios/opengl/internal/EglDisplay;", "EGL_NO_SURFACE", "Lcom/otaliastudios/opengl/internal/EglSurface;", "getEGL_NO_SURFACE", "()Lcom/otaliastudios/opengl/internal/EglSurface;", "EGL_OPENGL_ES2_BIT", "getEGL_OPENGL_ES2_BIT", "EGL_OPENGL_ES3_BIT_KHR", "getEGL_OPENGL_ES3_BIT_KHR", "EGL_PBUFFER_BIT", "getEGL_PBUFFER_BIT", "EGL_READ", "getEGL_READ", "EGL_RED_SIZE", "getEGL_RED_SIZE", "EGL_RENDERABLE_TYPE", "getEGL_RENDERABLE_TYPE", "EGL_SUCCESS", "getEGL_SUCCESS", "EGL_SURFACE_TYPE", "getEGL_SURFACE_TYPE", "EGL_WIDTH", "getEGL_WIDTH", "EGL_WINDOW_BIT", "getEGL_WINDOW_BIT", "eglChooseConfig", "", "display", "attributes", "", "configs", "", "Lcom/otaliastudios/opengl/internal/EglConfig;", "configsSize", "numConfigs", "(Lcom/otaliastudios/opengl/internal/EglDisplay;[I[Lcom/otaliastudios/opengl/internal/EglConfig;I[I)Z", "eglCreateContext", HVRetakeActivity.CONFIG_TAG, "sharedContext", "eglCreatePbufferSurface", "eglCreateWindowSurface", "surface", "", "eglDestroyContext", "context", "eglDestroySurface", "eglGetCurrentContext", "eglGetCurrentDisplay", "eglGetCurrentSurface", "which", "eglGetDefaultDisplay", "eglGetError", "eglInitialize", "major", "minor", "eglMakeCurrent", "draw", "read", "eglPresentationTime", "nanoseconds", "", "eglQuerySurface", "attribute", "out", "eglReleaseThread", "eglSwapBuffers", "eglTerminate", "library_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class EglKt {
    private static final EglContext EGL_NO_CONTEXT = new EglContext(EGL14.EGL_NO_CONTEXT);
    private static final EglDisplay EGL_NO_DISPLAY = new EglDisplay(EGL14.EGL_NO_DISPLAY);
    private static final EglSurface EGL_NO_SURFACE = new EglSurface(EGL14.EGL_NO_SURFACE);
    private static final int EGL_SUCCESS = 12288;
    private static final int EGL_NONE = 12344;
    private static final int EGL_WIDTH = 12375;
    private static final int EGL_HEIGHT = 12374;
    private static final int EGL_READ = 12378;
    private static final int EGL_DRAW = 12377;
    private static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
    private static final int EGL_OPENGL_ES2_BIT = 4;
    private static final int EGL_OPENGL_ES3_BIT_KHR = 64;
    private static final int EGL_RED_SIZE = 12324;
    private static final int EGL_GREEN_SIZE = 12323;
    private static final int EGL_BLUE_SIZE = 12322;
    private static final int EGL_ALPHA_SIZE = 12321;
    private static final int EGL_SURFACE_TYPE = 12339;
    private static final int EGL_WINDOW_BIT = 4;
    private static final int EGL_PBUFFER_BIT = 1;
    private static final int EGL_RENDERABLE_TYPE = Polynomial.PRIVATE_KEY_III_P;

    public static final EglContext getEGL_NO_CONTEXT() {
        return EGL_NO_CONTEXT;
    }

    public static final EglDisplay getEGL_NO_DISPLAY() {
        return EGL_NO_DISPLAY;
    }

    public static final EglSurface getEGL_NO_SURFACE() {
        return EGL_NO_SURFACE;
    }

    public static final int getEGL_SUCCESS() {
        return EGL_SUCCESS;
    }

    public static final int getEGL_NONE() {
        return EGL_NONE;
    }

    public static final int getEGL_WIDTH() {
        return EGL_WIDTH;
    }

    public static final int getEGL_HEIGHT() {
        return EGL_HEIGHT;
    }

    public static final int getEGL_READ() {
        return EGL_READ;
    }

    public static final int getEGL_DRAW() {
        return EGL_DRAW;
    }

    public static final int getEGL_CONTEXT_CLIENT_VERSION() {
        return EGL_CONTEXT_CLIENT_VERSION;
    }

    public static final int getEGL_OPENGL_ES2_BIT() {
        return EGL_OPENGL_ES2_BIT;
    }

    public static final int getEGL_OPENGL_ES3_BIT_KHR() {
        return EGL_OPENGL_ES3_BIT_KHR;
    }

    public static final int getEGL_RED_SIZE() {
        return EGL_RED_SIZE;
    }

    public static final int getEGL_GREEN_SIZE() {
        return EGL_GREEN_SIZE;
    }

    public static final int getEGL_BLUE_SIZE() {
        return EGL_BLUE_SIZE;
    }

    public static final int getEGL_ALPHA_SIZE() {
        return EGL_ALPHA_SIZE;
    }

    public static final int getEGL_SURFACE_TYPE() {
        return EGL_SURFACE_TYPE;
    }

    public static final int getEGL_WINDOW_BIT() {
        return EGL_WINDOW_BIT;
    }

    public static final int getEGL_PBUFFER_BIT() {
        return EGL_PBUFFER_BIT;
    }

    public static final int getEGL_RENDERABLE_TYPE() {
        return EGL_RENDERABLE_TYPE;
    }

    public static final boolean eglChooseConfig(EglDisplay display, int[] attributes, EglConfig[] configs, int i, int[] numConfigs) {
        Intrinsics.checkNotNullParameter(display, "display");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Intrinsics.checkNotNullParameter(configs, "configs");
        Intrinsics.checkNotNullParameter(numConfigs, "numConfigs");
        EGLConfig[] eGLConfigArr = new EGLConfig[configs.length];
        boolean eglChooseConfig = EGL14.eglChooseConfig(display.getNative(), attributes, 0, eGLConfigArr, 0, i, numConfigs, 0);
        if (eglChooseConfig) {
            Iterator<Integer> it = ArraysKt.getIndices(configs).iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                EGLConfig eGLConfig = eGLConfigArr[nextInt];
                configs[nextInt] = eGLConfig == null ? null : new EglConfig(eGLConfig);
            }
        }
        return eglChooseConfig;
    }

    public static final boolean eglInitialize(EglDisplay display, int[] major, int[] minor) {
        Intrinsics.checkNotNullParameter(display, "display");
        Intrinsics.checkNotNullParameter(major, "major");
        Intrinsics.checkNotNullParameter(minor, "minor");
        return EGL14.eglInitialize(display.getNative(), major, 0, minor, 0);
    }

    public static final EglContext eglCreateContext(EglDisplay display, EglConfig config, EglContext sharedContext, int[] attributes) {
        Intrinsics.checkNotNullParameter(display, "display");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(sharedContext, "sharedContext");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        return new EglContext(EGL14.eglCreateContext(display.getNative(), config.getNative(), sharedContext.getNative(), attributes, 0));
    }

    public static final EglDisplay eglGetDefaultDisplay() {
        return new EglDisplay(EGL14.eglGetDisplay(0));
    }

    public static final EglContext eglGetCurrentContext() {
        return new EglContext(EGL14.eglGetCurrentContext());
    }

    public static final EglDisplay eglGetCurrentDisplay() {
        return new EglDisplay(EGL14.eglGetCurrentDisplay());
    }

    public static final EglSurface eglGetCurrentSurface(int i) {
        return new EglSurface(EGL14.eglGetCurrentSurface(i));
    }

    public static final boolean eglQuerySurface(EglDisplay display, EglSurface surface, int i, int[] out) {
        Intrinsics.checkNotNullParameter(display, "display");
        Intrinsics.checkNotNullParameter(surface, "surface");
        Intrinsics.checkNotNullParameter(out, "out");
        return EGL14.eglQuerySurface(display.getNative(), surface.getNative(), i, out, 0);
    }

    public static final EglSurface eglCreateWindowSurface(EglDisplay display, EglConfig config, Object surface, int[] attributes) {
        Intrinsics.checkNotNullParameter(display, "display");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(surface, "surface");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        return new EglSurface(EGL14.eglCreateWindowSurface(display.getNative(), config.getNative(), surface, attributes, 0));
    }

    public static final EglSurface eglCreatePbufferSurface(EglDisplay display, EglConfig config, int[] attributes) {
        Intrinsics.checkNotNullParameter(display, "display");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        return new EglSurface(EGL14.eglCreatePbufferSurface(display.getNative(), config.getNative(), attributes, 0));
    }

    public static final boolean eglMakeCurrent(EglDisplay display, EglSurface draw, EglSurface read, EglContext context) {
        Intrinsics.checkNotNullParameter(display, "display");
        Intrinsics.checkNotNullParameter(draw, "draw");
        Intrinsics.checkNotNullParameter(read, "read");
        Intrinsics.checkNotNullParameter(context, "context");
        return EGL14.eglMakeCurrent(display.getNative(), draw.getNative(), read.getNative(), context.getNative());
    }

    public static final boolean eglSwapBuffers(EglDisplay display, EglSurface surface) {
        Intrinsics.checkNotNullParameter(display, "display");
        Intrinsics.checkNotNullParameter(surface, "surface");
        return EGL14.eglSwapBuffers(display.getNative(), surface.getNative());
    }

    public static final boolean eglPresentationTime(EglDisplay display, EglSurface surface, long j) {
        Intrinsics.checkNotNullParameter(display, "display");
        Intrinsics.checkNotNullParameter(surface, "surface");
        return EGLExt.eglPresentationTimeANDROID(display.getNative(), surface.getNative(), j);
    }

    public static final boolean eglDestroyContext(EglDisplay display, EglContext context) {
        Intrinsics.checkNotNullParameter(display, "display");
        Intrinsics.checkNotNullParameter(context, "context");
        return EGL14.eglDestroyContext(display.getNative(), context.getNative());
    }

    public static final boolean eglDestroySurface(EglDisplay display, EglSurface surface) {
        Intrinsics.checkNotNullParameter(display, "display");
        Intrinsics.checkNotNullParameter(surface, "surface");
        return EGL14.eglDestroySurface(display.getNative(), surface.getNative());
    }

    public static final boolean eglReleaseThread() {
        return EGL14.eglReleaseThread();
    }

    public static final boolean eglTerminate(EglDisplay display) {
        Intrinsics.checkNotNullParameter(display, "display");
        return EGL14.eglTerminate(display.getNative());
    }

    public static final int eglGetError() {
        return EGL14.eglGetError();
    }
}
