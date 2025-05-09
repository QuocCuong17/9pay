package com.otaliastudios.opengl.surface;

import com.otaliastudios.opengl.core.EglCore;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EglWindowSurface.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b&\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/otaliastudios/opengl/surface/EglNativeWindowSurface;", "Lcom/otaliastudios/opengl/surface/EglSurface;", "eglCore", "Lcom/otaliastudios/opengl/core/EglCore;", "eglSurface", "Lcom/otaliastudios/opengl/internal/EglSurface;", "(Lcom/otaliastudios/opengl/core/EglCore;Lcom/otaliastudios/opengl/internal/EglSurface;)V", "swapBuffers", "", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public abstract class EglNativeWindowSurface extends EglSurface {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EglNativeWindowSurface(EglCore eglCore, com.otaliastudios.opengl.internal.EglSurface eglSurface) {
        super(eglCore, eglSurface);
        Intrinsics.checkNotNullParameter(eglCore, "eglCore");
        Intrinsics.checkNotNullParameter(eglSurface, "eglSurface");
    }

    public final boolean swapBuffers() {
        return getEglCore().swapSurfaceBuffers$library_release(getEglSurface());
    }
}
