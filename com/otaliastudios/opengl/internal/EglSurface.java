package com.otaliastudios.opengl.internal;

import android.opengl.EGLSurface;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: egl.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/otaliastudios/opengl/internal/EglSurface;", "", "native", "Landroid/opengl/EGLSurface;", "(Landroid/opengl/EGLSurface;)V", "getNative", "()Landroid/opengl/EGLSurface;", "component1", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class EglSurface {
    private final EGLSurface native;

    public static /* synthetic */ EglSurface copy$default(EglSurface eglSurface, EGLSurface eGLSurface, int i, Object obj) {
        if ((i & 1) != 0) {
            eGLSurface = eglSurface.native;
        }
        return eglSurface.copy(eGLSurface);
    }

    /* renamed from: component1, reason: from getter */
    public final EGLSurface getNative() {
        return this.native;
    }

    public final EglSurface copy(EGLSurface r2) {
        return new EglSurface(r2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof EglSurface) && Intrinsics.areEqual(this.native, ((EglSurface) other).native);
    }

    public int hashCode() {
        EGLSurface eGLSurface = this.native;
        if (eGLSurface == null) {
            return 0;
        }
        return eGLSurface.hashCode();
    }

    public String toString() {
        return "EglSurface(native=" + this.native + ')';
    }

    public EglSurface(EGLSurface eGLSurface) {
        this.native = eGLSurface;
    }

    public final EGLSurface getNative() {
        return this.native;
    }
}
