package com.otaliastudios.opengl.core;

import android.opengl.EGL14;
import android.opengl.EGLContext;
import com.otaliastudios.opengl.internal.EglContext;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: EglCore.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u001d\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0004J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\bH\u0016¨\u0006\f"}, d2 = {"Lcom/otaliastudios/opengl/core/EglCore;", "Lcom/otaliastudios/opengl/core/EglNativeCore;", "sharedContext", "Landroid/opengl/EGLContext;", "flags", "", "(Landroid/opengl/EGLContext;I)V", "finalize", "", "makeCurrent", "release", "Companion", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class EglCore extends EglNativeCore {
    public static final int FLAG_RECORDABLE = 1;
    public static final int FLAG_TRY_GLES3 = 2;

    /* JADX WARN: Multi-variable type inference failed */
    public EglCore() {
        this(null, 0, 3, 0 == true ? 1 : 0);
    }

    public EglCore(EGLContext eGLContext) {
        this(eGLContext, 0, 2, null);
    }

    public /* synthetic */ EglCore(EGLContext eGLContext, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? EGL14.EGL_NO_CONTEXT : eGLContext, (i2 & 2) != 0 ? 0 : i);
    }

    public EglCore(EGLContext eGLContext, int i) {
        super(new EglContext(eGLContext), i);
    }

    @Override // com.otaliastudios.opengl.core.EglNativeCore
    /* renamed from: release, reason: merged with bridge method [inline-methods] */
    public void release$library_release() {
        super.release$library_release();
    }

    @Override // com.otaliastudios.opengl.core.EglNativeCore
    /* renamed from: makeCurrent, reason: merged with bridge method [inline-methods] */
    public void makeCurrent$library_release() {
        super.makeCurrent$library_release();
    }

    protected final void finalize() {
        release$library_release();
    }
}
