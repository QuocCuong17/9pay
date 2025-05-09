package com.otaliastudios.opengl.texture;

import android.opengl.GLES20;
import com.otaliastudios.opengl.core.Egloo;
import com.otaliastudios.opengl.core.GlBindable;
import com.otaliastudios.opengl.core.GlBindableKt;
import com.otaliastudios.opengl.internal.GlKt;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlFramebuffer.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u0003H\u0007J\b\u0010\f\u001a\u00020\bH\u0016J\u0006\u0010\r\u001a\u00020\bJ\b\u0010\u000e\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/otaliastudios/opengl/texture/GlFramebuffer;", "Lcom/otaliastudios/opengl/core/GlBindable;", "id", "", "(Ljava/lang/Integer;)V", "getId", "()I", "attach", "", "texture", "Lcom/otaliastudios/opengl/texture/GlTexture;", "attachment", "bind", "release", "unbind", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class GlFramebuffer implements GlBindable {
    private final int id;

    /* JADX WARN: Multi-variable type inference failed */
    public GlFramebuffer() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public final void attach(GlTexture texture) {
        Intrinsics.checkNotNullParameter(texture, "texture");
        attach$default(this, texture, 0, 2, null);
    }

    public GlFramebuffer(Integer num) {
        int intValue;
        if (num != null) {
            intValue = num.intValue();
        } else {
            int[] m1351constructorimpl = UIntArray.m1351constructorimpl(1);
            int m1358getSizeimpl = UIntArray.m1358getSizeimpl(m1351constructorimpl);
            int[] iArr = new int[m1358getSizeimpl];
            for (int i = 0; i < m1358getSizeimpl; i++) {
                iArr[i] = UIntArray.m1357getpVg5ArA(m1351constructorimpl, i);
            }
            GLES20.glGenFramebuffers(1, iArr, 0);
            Unit unit = Unit.INSTANCE;
            UIntArray.m1362setVXSXFK8(m1351constructorimpl, 0, UInt.m1297constructorimpl(iArr[0]));
            Egloo.checkGlError("glGenFramebuffers");
            intValue = UIntArray.m1357getpVg5ArA(m1351constructorimpl, 0);
        }
        this.id = intValue;
    }

    public /* synthetic */ GlFramebuffer(Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num);
    }

    public final int getId() {
        return this.id;
    }

    public static /* synthetic */ void attach$default(GlFramebuffer glFramebuffer, GlTexture glTexture, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = GlKt.getGL_COLOR_ATTACHMENT0();
        }
        glFramebuffer.attach(glTexture, i);
    }

    public final void attach(final GlTexture texture, final int attachment) {
        Intrinsics.checkNotNullParameter(texture, "texture");
        GlBindableKt.use(this, new Function0<Unit>() { // from class: com.otaliastudios.opengl.texture.GlFramebuffer$attach$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                GLES20.glFramebufferTexture2D(GlKt.getGL_FRAMEBUFFER(), UInt.m1297constructorimpl(attachment), UInt.m1297constructorimpl(texture.getTarget()), UInt.m1297constructorimpl(texture.getId()), 0);
                int m1297constructorimpl = UInt.m1297constructorimpl(GLES20.glCheckFramebufferStatus(GlKt.getGL_FRAMEBUFFER()));
                if (m1297constructorimpl != GlKt.getGL_FRAMEBUFFER_COMPLETE()) {
                    throw new RuntimeException(Intrinsics.stringPlus("Invalid framebuffer generation. Error:", UInt.m1343toStringimpl(m1297constructorimpl)));
                }
            }
        });
    }

    @Override // com.otaliastudios.opengl.core.GlBindable
    public void bind() {
        GLES20.glBindFramebuffer(GlKt.getGL_FRAMEBUFFER(), UInt.m1297constructorimpl(this.id));
    }

    @Override // com.otaliastudios.opengl.core.GlBindable
    public void unbind() {
        GLES20.glBindFramebuffer(GlKt.getGL_FRAMEBUFFER(), 0);
    }

    public final void release() {
        int[] iArr = {UInt.m1297constructorimpl(this.id)};
        int m1358getSizeimpl = UIntArray.m1358getSizeimpl(iArr);
        int[] iArr2 = new int[m1358getSizeimpl];
        for (int i = 0; i < m1358getSizeimpl; i++) {
            iArr2[i] = UIntArray.m1357getpVg5ArA(iArr, i);
        }
        GLES20.glDeleteFramebuffers(1, iArr2, 0);
        Unit unit = Unit.INSTANCE;
        UIntArray.m1362setVXSXFK8(iArr, 0, UInt.m1297constructorimpl(iArr2[0]));
    }
}
