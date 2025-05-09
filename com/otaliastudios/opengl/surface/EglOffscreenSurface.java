package com.otaliastudios.opengl.surface;

import com.otaliastudios.opengl.core.EglCore;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EglOffscreenSurface.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/otaliastudios/opengl/surface/EglOffscreenSurface;", "Lcom/otaliastudios/opengl/surface/EglSurface;", "eglCore", "Lcom/otaliastudios/opengl/core/EglCore;", "width", "", "height", "(Lcom/otaliastudios/opengl/core/EglCore;II)V", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public class EglOffscreenSurface extends EglSurface {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EglOffscreenSurface(EglCore eglCore, int i, int i2) {
        super(eglCore, eglCore.createOffscreenSurface$library_release(i, i2));
        Intrinsics.checkNotNullParameter(eglCore, "eglCore");
        setWidth(i);
        setHeight(i2);
    }
}
