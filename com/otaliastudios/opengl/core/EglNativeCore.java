package com.otaliastudios.opengl.core;

import android.opengl.EGL14;
import android.opengl.EGLExt;
import android.util.Log;
import com.otaliastudios.opengl.internal.EglConfig;
import com.otaliastudios.opengl.internal.EglContext;
import com.otaliastudios.opengl.internal.EglDisplay;
import com.otaliastudios.opengl.internal.EglKt;
import com.otaliastudios.opengl.internal.EglSurface;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EglNativeCore.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b\u0005\b\u0016\u0018\u0000 -2\u00020\u0001:\u0001-B\u001b\b\u0000\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001d\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0011J\u0015\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0001H\u0000¢\u0006\u0002\b\u0014J\u0015\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u0018J\r\u0010\u0019\u001a\u00020\u001aH\u0010¢\u0006\u0002\b\u001bJ\u0015\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u001dJ\u001d\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u001dJ\u001d\u0010 \u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\"J\r\u0010#\u001a\u00020\u001aH\u0010¢\u0006\u0002\b$J\u0015\u0010%\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u000eH\u0000¢\u0006\u0002\b&J\u001d\u0010'\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020)H\u0000¢\u0006\u0002\b*J\u0015\u0010+\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH\u0000¢\u0006\u0002\b,R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/otaliastudios/opengl/core/EglNativeCore;", "", "sharedContext", "Lcom/otaliastudios/opengl/internal/EglContext;", "flags", "", "(Lcom/otaliastudios/opengl/internal/EglContext;I)V", "eglConfig", "Lcom/otaliastudios/opengl/internal/EglConfig;", "eglContext", "eglDisplay", "Lcom/otaliastudios/opengl/internal/EglDisplay;", "glVersion", "createOffscreenSurface", "Lcom/otaliastudios/opengl/internal/EglSurface;", "width", "height", "createOffscreenSurface$library_release", "createWindowSurface", "surface", "createWindowSurface$library_release", "isSurfaceCurrent", "", "eglSurface", "isSurfaceCurrent$library_release", "makeCurrent", "", "makeCurrent$library_release", "makeSurfaceCurrent", "makeSurfaceCurrent$library_release", "drawSurface", "readSurface", "querySurface", "what", "querySurface$library_release", "release", "release$library_release", "releaseSurface", "releaseSurface$library_release", "setSurfacePresentationTime", "nsecs", "", "setSurfacePresentationTime$library_release", "swapSurfaceBuffers", "swapSurfaceBuffers$library_release", "Companion", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public class EglNativeCore {
    public static final int FLAG_RECORDABLE = 1;
    public static final int FLAG_TRY_GLES3 = 2;
    private EglConfig eglConfig;
    private EglContext eglContext;
    private EglDisplay eglDisplay;
    private int glVersion;

    /* JADX WARN: Multi-variable type inference failed */
    public EglNativeCore() {
        this(null, 0, 3, 0 == true ? 1 : 0);
    }

    public EglNativeCore(EglContext sharedContext, int i) {
        EglConfig config$library_release;
        Intrinsics.checkNotNullParameter(sharedContext, "sharedContext");
        this.eglDisplay = EglKt.getEGL_NO_DISPLAY();
        this.eglContext = EglKt.getEGL_NO_CONTEXT();
        this.glVersion = -1;
        EglDisplay eglDisplay = new EglDisplay(EGL14.eglGetDisplay(0));
        this.eglDisplay = eglDisplay;
        if (eglDisplay == EglKt.getEGL_NO_DISPLAY()) {
            throw new RuntimeException("unable to get EGL14 display");
        }
        if (!EGL14.eglInitialize(this.eglDisplay.getNative(), new int[1], 0, new int[1], 0)) {
            throw new RuntimeException("unable to initialize EGL14");
        }
        EglNativeConfigChooser eglNativeConfigChooser = new EglNativeConfigChooser();
        boolean z = (i & 1) != 0;
        if (((i & 2) != 0) && (config$library_release = eglNativeConfigChooser.getConfig$library_release(this.eglDisplay, 3, z)) != null) {
            EglContext eglContext = new EglContext(EGL14.eglCreateContext(this.eglDisplay.getNative(), config$library_release.getNative(), sharedContext.getNative(), new int[]{EglKt.getEGL_CONTEXT_CLIENT_VERSION(), 3, EglKt.getEGL_NONE()}, 0));
            try {
                Egloo.checkEglError("eglCreateContext (3)");
                this.eglConfig = config$library_release;
                this.eglContext = eglContext;
                this.glVersion = 3;
            } catch (Exception unused) {
            }
        }
        if (this.eglContext == EglKt.getEGL_NO_CONTEXT()) {
            EglConfig config$library_release2 = eglNativeConfigChooser.getConfig$library_release(this.eglDisplay, 2, z);
            if (config$library_release2 == null) {
                throw new RuntimeException("Unable to find a suitable EGLConfig");
            }
            EglContext eglContext2 = new EglContext(EGL14.eglCreateContext(this.eglDisplay.getNative(), config$library_release2.getNative(), sharedContext.getNative(), new int[]{EglKt.getEGL_CONTEXT_CLIENT_VERSION(), 2, EglKt.getEGL_NONE()}, 0));
            Egloo.checkEglError("eglCreateContext (2)");
            this.eglConfig = config$library_release2;
            this.eglContext = eglContext2;
            this.glVersion = 2;
        }
    }

    public /* synthetic */ EglNativeCore(EglContext eglContext, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? EglKt.getEGL_NO_CONTEXT() : eglContext, (i2 & 2) != 0 ? 0 : i);
    }

    public void release$library_release() {
        if (this.eglDisplay != EglKt.getEGL_NO_DISPLAY()) {
            EGL14.eglMakeCurrent(this.eglDisplay.getNative(), EglKt.getEGL_NO_SURFACE().getNative(), EglKt.getEGL_NO_SURFACE().getNative(), EglKt.getEGL_NO_CONTEXT().getNative());
            EGL14.eglDestroyContext(this.eglDisplay.getNative(), this.eglContext.getNative());
            EGL14.eglReleaseThread();
            EGL14.eglTerminate(this.eglDisplay.getNative());
        }
        this.eglDisplay = EglKt.getEGL_NO_DISPLAY();
        this.eglContext = EglKt.getEGL_NO_CONTEXT();
        this.eglConfig = null;
    }

    public void makeCurrent$library_release() {
        if (!EGL14.eglMakeCurrent(this.eglDisplay.getNative(), EglKt.getEGL_NO_SURFACE().getNative(), EglKt.getEGL_NO_SURFACE().getNative(), this.eglContext.getNative())) {
            throw new RuntimeException("eglMakeCurrent failed");
        }
    }

    public final void releaseSurface$library_release(EglSurface eglSurface) {
        Intrinsics.checkNotNullParameter(eglSurface, "eglSurface");
        EGL14.eglDestroySurface(this.eglDisplay.getNative(), eglSurface.getNative());
    }

    public final EglSurface createWindowSurface$library_release(Object surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        int[] iArr = {EglKt.getEGL_NONE()};
        EglDisplay eglDisplay = this.eglDisplay;
        EglConfig eglConfig = this.eglConfig;
        Intrinsics.checkNotNull(eglConfig);
        EglSurface eglSurface = new EglSurface(EGL14.eglCreateWindowSurface(eglDisplay.getNative(), eglConfig.getNative(), surface, iArr, 0));
        Egloo.checkEglError("eglCreateWindowSurface");
        if (eglSurface != EglKt.getEGL_NO_SURFACE()) {
            return eglSurface;
        }
        throw new RuntimeException("surface was null");
    }

    public final EglSurface createOffscreenSurface$library_release(int width, int height) {
        int[] iArr = {EglKt.getEGL_WIDTH(), width, EglKt.getEGL_HEIGHT(), height, EglKt.getEGL_NONE()};
        EglDisplay eglDisplay = this.eglDisplay;
        EglConfig eglConfig = this.eglConfig;
        Intrinsics.checkNotNull(eglConfig);
        EglSurface eglSurface = new EglSurface(EGL14.eglCreatePbufferSurface(eglDisplay.getNative(), eglConfig.getNative(), iArr, 0));
        Egloo.checkEglError("eglCreatePbufferSurface");
        if (eglSurface != EglKt.getEGL_NO_SURFACE()) {
            return eglSurface;
        }
        throw new RuntimeException("surface was null");
    }

    public final void makeSurfaceCurrent$library_release(EglSurface eglSurface) {
        Intrinsics.checkNotNullParameter(eglSurface, "eglSurface");
        if (this.eglDisplay == EglKt.getEGL_NO_DISPLAY()) {
            Log.v("EglCore", "NOTE: makeSurfaceCurrent w/o display");
        }
        if (!EGL14.eglMakeCurrent(this.eglDisplay.getNative(), eglSurface.getNative(), eglSurface.getNative(), this.eglContext.getNative())) {
            throw new RuntimeException("eglMakeCurrent failed");
        }
    }

    public final void makeSurfaceCurrent$library_release(EglSurface drawSurface, EglSurface readSurface) {
        Intrinsics.checkNotNullParameter(drawSurface, "drawSurface");
        Intrinsics.checkNotNullParameter(readSurface, "readSurface");
        if (this.eglDisplay == EglKt.getEGL_NO_DISPLAY()) {
            Log.v("EglCore", "NOTE: makeSurfaceCurrent w/o display");
        }
        if (!EGL14.eglMakeCurrent(this.eglDisplay.getNative(), drawSurface.getNative(), readSurface.getNative(), this.eglContext.getNative())) {
            throw new RuntimeException("eglMakeCurrent(draw,read) failed");
        }
    }

    public final boolean swapSurfaceBuffers$library_release(EglSurface eglSurface) {
        Intrinsics.checkNotNullParameter(eglSurface, "eglSurface");
        return EGL14.eglSwapBuffers(this.eglDisplay.getNative(), eglSurface.getNative());
    }

    public final void setSurfacePresentationTime$library_release(EglSurface eglSurface, long nsecs) {
        Intrinsics.checkNotNullParameter(eglSurface, "eglSurface");
        EGLExt.eglPresentationTimeANDROID(this.eglDisplay.getNative(), eglSurface.getNative(), nsecs);
    }

    public final boolean isSurfaceCurrent$library_release(EglSurface eglSurface) {
        Intrinsics.checkNotNullParameter(eglSurface, "eglSurface");
        return Intrinsics.areEqual(this.eglContext, new EglContext(EGL14.eglGetCurrentContext())) && Intrinsics.areEqual(eglSurface, new EglSurface(EGL14.eglGetCurrentSurface(EglKt.getEGL_DRAW())));
    }

    public final int querySurface$library_release(EglSurface eglSurface, int what) {
        Intrinsics.checkNotNullParameter(eglSurface, "eglSurface");
        int[] iArr = new int[1];
        EGL14.eglQuerySurface(this.eglDisplay.getNative(), eglSurface.getNative(), what, iArr, 0);
        return iArr[0];
    }
}
