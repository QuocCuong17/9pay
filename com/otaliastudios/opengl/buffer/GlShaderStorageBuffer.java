package com.otaliastudios.opengl.buffer;

import android.opengl.GLES20;
import android.opengl.GLES30;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.otaliastudios.opengl.core.Egloo;
import com.otaliastudios.opengl.core.GlBindableKt;
import com.otaliastudios.opengl.internal.GlKt;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlShaderStorageBuffer.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003J\u001c\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u000f"}, d2 = {"Lcom/otaliastudios/opengl/buffer/GlShaderStorageBuffer;", "Lcom/otaliastudios/opengl/buffer/GlBuffer;", "size", "", "usage", "(II)V", "getSize", "()I", "getUsage", "bind", "", FirebaseAnalytics.Param.INDEX, "use", "block", "Lkotlin/Function0;", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class GlShaderStorageBuffer extends GlBuffer {
    private final int size;
    private final int usage;

    public final int getSize() {
        return this.size;
    }

    public final int getUsage() {
        return this.usage;
    }

    public GlShaderStorageBuffer(int i, int i2) {
        super(GlKt.getGL_SHADER_STORAGE_BUFFER(), null, 2, null);
        this.size = i;
        this.usage = i2;
        GlBindableKt.use(this, new Function0<Unit>() { // from class: com.otaliastudios.opengl.buffer.GlShaderStorageBuffer.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                GLES20.glBufferData(UInt.m1297constructorimpl(GlShaderStorageBuffer.this.getTarget()), GlShaderStorageBuffer.this.getSize(), null, UInt.m1297constructorimpl(GlShaderStorageBuffer.this.getUsage()));
                Egloo.checkGlError("glBufferData");
            }
        });
    }

    public final void bind(int index) {
        GLES30.glBindBufferBase(UInt.m1297constructorimpl(getTarget()), UInt.m1297constructorimpl(index), UInt.m1297constructorimpl(getId()));
        Egloo.checkGlError("glBindBufferBase");
    }

    public final void use(int index, Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        bind(index);
        block.invoke();
        unbind();
    }
}
