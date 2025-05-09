package com.otaliastudios.opengl.surface;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import com.otaliastudios.opengl.core.EglCore;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EglWindowSurface.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B!\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/otaliastudios/opengl/surface/EglWindowSurface;", "Lcom/otaliastudios/opengl/surface/EglNativeWindowSurface;", "eglCore", "Lcom/otaliastudios/opengl/core/EglCore;", "surface", "Landroid/view/Surface;", "releaseSurface", "", "(Lcom/otaliastudios/opengl/core/EglCore;Landroid/view/Surface;Z)V", "surfaceTexture", "Landroid/graphics/SurfaceTexture;", "(Lcom/otaliastudios/opengl/core/EglCore;Landroid/graphics/SurfaceTexture;)V", "release", "", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public class EglWindowSurface extends EglNativeWindowSurface {
    private boolean releaseSurface;
    private Surface surface;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public EglWindowSurface(EglCore eglCore, Surface surface) {
        this(eglCore, surface, false, 4, null);
        Intrinsics.checkNotNullParameter(eglCore, "eglCore");
        Intrinsics.checkNotNullParameter(surface, "surface");
    }

    public /* synthetic */ EglWindowSurface(EglCore eglCore, Surface surface, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(eglCore, surface, (i & 4) != 0 ? false : z);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EglWindowSurface(EglCore eglCore, Surface surface, boolean z) {
        super(eglCore, eglCore.createWindowSurface$library_release(surface));
        Intrinsics.checkNotNullParameter(eglCore, "eglCore");
        Intrinsics.checkNotNullParameter(surface, "surface");
        this.surface = surface;
        this.releaseSurface = z;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EglWindowSurface(EglCore eglCore, SurfaceTexture surfaceTexture) {
        super(eglCore, eglCore.createWindowSurface$library_release(surfaceTexture));
        Intrinsics.checkNotNullParameter(eglCore, "eglCore");
        Intrinsics.checkNotNullParameter(surfaceTexture, "surfaceTexture");
    }

    @Override // com.otaliastudios.opengl.surface.EglNativeSurface
    public void release() {
        super.release();
        if (this.releaseSurface) {
            Surface surface = this.surface;
            if (surface != null) {
                surface.release();
            }
            this.surface = null;
        }
    }
}
