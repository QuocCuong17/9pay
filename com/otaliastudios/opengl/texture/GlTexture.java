package com.otaliastudios.opengl.texture;

import android.opengl.GLES20;
import androidx.constraintlayout.core.motion.utils.TypedValues;
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

/* compiled from: GlTexture.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B'\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006BE\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fBS\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\rJ\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0006\u0010\u001a\u001a\u00020\u0019J\b\u0010\u001b\u001a\u00020\u0019H\u0016R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0017\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/otaliastudios/opengl/texture/GlTexture;", "Lcom/otaliastudios/opengl/core/GlBindable;", "unit", "", TypedValues.AttributesType.S_TARGET, "id", "(IILjava/lang/Integer;)V", "width", "height", "format", "internalFormat", "type", "(IIIIIII)V", "(IILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getFormat", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getHeight", "getId", "()I", "getTarget", "getType", "getUnit", "getWidth", "bind", "", "release", "unbind", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class GlTexture implements GlBindable {
    private final Integer format;
    private final Integer height;
    private final int id;
    private final int target;
    private final Integer type;
    private final int unit;
    private final Integer width;

    public GlTexture() {
        this(0, 0, (Integer) null, 7, (DefaultConstructorMarker) null);
    }

    public GlTexture(int i) {
        this(i, 0, (Integer) null, 6, (DefaultConstructorMarker) null);
    }

    public GlTexture(int i, int i2) {
        this(i, i2, (Integer) null, 4, (DefaultConstructorMarker) null);
    }

    public GlTexture(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, 0, 0, 0, 112, null);
    }

    public GlTexture(int i, int i2, int i3, int i4, int i5) {
        this(i, i2, i3, i4, i5, 0, 0, 96, null);
    }

    public GlTexture(int i, int i2, int i3, int i4, int i5, int i6) {
        this(i, i2, i3, i4, i5, i6, 0, 64, null);
    }

    private GlTexture(int i, int i2, Integer num, Integer num2, Integer num3, Integer num4, final Integer num5, Integer num6) {
        int intValue;
        this.unit = i;
        this.target = i2;
        this.width = num2;
        this.height = num3;
        this.format = num4;
        this.type = num6;
        if (num != null) {
            intValue = num.intValue();
        } else {
            int[] m1351constructorimpl = UIntArray.m1351constructorimpl(1);
            int m1358getSizeimpl = UIntArray.m1358getSizeimpl(m1351constructorimpl);
            int[] iArr = new int[m1358getSizeimpl];
            for (int i3 = 0; i3 < m1358getSizeimpl; i3++) {
                iArr[i3] = UIntArray.m1357getpVg5ArA(m1351constructorimpl, i3);
            }
            GLES20.glGenTextures(1, iArr, 0);
            Unit unit = Unit.INSTANCE;
            UIntArray.m1362setVXSXFK8(m1351constructorimpl, 0, UInt.m1297constructorimpl(iArr[0]));
            Egloo.checkGlError("glGenTextures");
            intValue = UIntArray.m1357getpVg5ArA(m1351constructorimpl, 0);
        }
        this.id = intValue;
        if (num == null) {
            GlBindableKt.use(this, new Function0<Unit>() { // from class: com.otaliastudios.opengl.texture.GlTexture.1
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
                    if (GlTexture.this.getWidth() != null && GlTexture.this.getHeight() != null && GlTexture.this.getFormat() != null && num5 != null && GlTexture.this.getType() != null) {
                        GLES20.glTexImage2D(UInt.m1297constructorimpl(GlTexture.this.getTarget()), 0, num5.intValue(), GlTexture.this.getWidth().intValue(), GlTexture.this.getHeight().intValue(), 0, UInt.m1297constructorimpl(GlTexture.this.getFormat().intValue()), UInt.m1297constructorimpl(GlTexture.this.getType().intValue()), null);
                    }
                    GLES20.glTexParameterf(UInt.m1297constructorimpl(GlTexture.this.getTarget()), GlKt.getGL_TEXTURE_MIN_FILTER(), GlKt.getGL_NEAREST());
                    GLES20.glTexParameterf(UInt.m1297constructorimpl(GlTexture.this.getTarget()), GlKt.getGL_TEXTURE_MAG_FILTER(), GlKt.getGL_LINEAR());
                    GLES20.glTexParameteri(UInt.m1297constructorimpl(GlTexture.this.getTarget()), GlKt.getGL_TEXTURE_WRAP_S(), GlKt.getGL_CLAMP_TO_EDGE());
                    GLES20.glTexParameteri(UInt.m1297constructorimpl(GlTexture.this.getTarget()), GlKt.getGL_TEXTURE_WRAP_T(), GlKt.getGL_CLAMP_TO_EDGE());
                    Egloo.checkGlError("glTexParameter");
                }
            });
        }
    }

    public final int getUnit() {
        return this.unit;
    }

    public final int getTarget() {
        return this.target;
    }

    public final Integer getWidth() {
        return this.width;
    }

    public final Integer getHeight() {
        return this.height;
    }

    public final Integer getFormat() {
        return this.format;
    }

    public final Integer getType() {
        return this.type;
    }

    public /* synthetic */ GlTexture(int i, int i2, Integer num, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? GlKt.getGL_TEXTURE0() : i, (i3 & 2) != 0 ? GlKt.getGL_TEXTURE_EXTERNAL_OES() : i2, (i3 & 4) != 0 ? null : num);
    }

    public GlTexture(int i, int i2, Integer num) {
        this(i, i2, num, null, null, null, null, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ GlTexture(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, i4, r6, (i8 & 32) != 0 ? r6 : i6, (i8 & 64) != 0 ? GlKt.getGL_UNSIGNED_BYTE() : i7);
        int gl_rgba = (i8 & 16) != 0 ? GlKt.getGL_RGBA() : i5;
    }

    public GlTexture(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this(i, i2, null, Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6), Integer.valueOf(i7));
    }

    public final int getId() {
        return this.id;
    }

    @Override // com.otaliastudios.opengl.core.GlBindable
    public void bind() {
        GLES20.glActiveTexture(UInt.m1297constructorimpl(this.unit));
        GLES20.glBindTexture(UInt.m1297constructorimpl(this.target), UInt.m1297constructorimpl(this.id));
        Egloo.checkGlError("bind");
    }

    @Override // com.otaliastudios.opengl.core.GlBindable
    public void unbind() {
        GLES20.glBindTexture(UInt.m1297constructorimpl(this.target), UInt.m1297constructorimpl(0));
        GLES20.glActiveTexture(GlKt.getGL_TEXTURE0());
        Egloo.checkGlError("unbind");
    }

    public final void release() {
        int[] iArr = {UInt.m1297constructorimpl(this.id)};
        int m1358getSizeimpl = UIntArray.m1358getSizeimpl(iArr);
        int[] iArr2 = new int[m1358getSizeimpl];
        for (int i = 0; i < m1358getSizeimpl; i++) {
            iArr2[i] = UIntArray.m1357getpVg5ArA(iArr, i);
        }
        GLES20.glDeleteTextures(1, iArr2, 0);
        Unit unit = Unit.INSTANCE;
        UIntArray.m1362setVXSXFK8(iArr, 0, UInt.m1297constructorimpl(iArr2[0]));
    }
}
