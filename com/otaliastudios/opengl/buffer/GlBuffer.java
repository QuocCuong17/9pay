package com.otaliastudios.opengl.buffer;

import android.opengl.GLES20;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.otaliastudios.opengl.core.Egloo;
import com.otaliastudios.opengl.core.GlBindable;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: GlBuffer.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016J\u0006\u0010\u000b\u001a\u00020\nJ\b\u0010\f\u001a\u00020\nH\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\r"}, d2 = {"Lcom/otaliastudios/opengl/buffer/GlBuffer;", "Lcom/otaliastudios/opengl/core/GlBindable;", TypedValues.AttributesType.S_TARGET, "", "id", "(ILjava/lang/Integer;)V", "getId", "()I", "getTarget", "bind", "", "release", "unbind", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public class GlBuffer implements GlBindable {
    private final int id;
    private final int target;

    public GlBuffer(int i, Integer num) {
        int intValue;
        this.target = i;
        if (num != null) {
            intValue = num.intValue();
        } else {
            int[] m1351constructorimpl = UIntArray.m1351constructorimpl(1);
            int m1358getSizeimpl = UIntArray.m1358getSizeimpl(m1351constructorimpl);
            int[] iArr = new int[m1358getSizeimpl];
            for (int i2 = 0; i2 < m1358getSizeimpl; i2++) {
                iArr[i2] = UIntArray.m1357getpVg5ArA(m1351constructorimpl, i2);
            }
            GLES20.glGenBuffers(1, iArr, 0);
            Unit unit = Unit.INSTANCE;
            UIntArray.m1362setVXSXFK8(m1351constructorimpl, 0, UInt.m1297constructorimpl(iArr[0]));
            Egloo.checkGlError("glGenBuffers");
            intValue = UIntArray.m1357getpVg5ArA(m1351constructorimpl, 0);
        }
        this.id = intValue;
    }

    public /* synthetic */ GlBuffer(int i, Integer num, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : num);
    }

    public final int getTarget() {
        return this.target;
    }

    public final int getId() {
        return this.id;
    }

    @Override // com.otaliastudios.opengl.core.GlBindable
    public void bind() {
        GLES20.glBindBuffer(UInt.m1297constructorimpl(this.target), UInt.m1297constructorimpl(this.id));
    }

    @Override // com.otaliastudios.opengl.core.GlBindable
    public void unbind() {
        GLES20.glBindBuffer(UInt.m1297constructorimpl(this.target), 0);
    }

    public final void release() {
        int[] iArr = {UInt.m1297constructorimpl(this.id)};
        int m1358getSizeimpl = UIntArray.m1358getSizeimpl(iArr);
        int[] iArr2 = new int[m1358getSizeimpl];
        for (int i = 0; i < m1358getSizeimpl; i++) {
            iArr2[i] = UIntArray.m1357getpVg5ArA(iArr, i);
        }
        GLES20.glDeleteBuffers(1, iArr2, 0);
        Unit unit = Unit.INSTANCE;
        UIntArray.m1362setVXSXFK8(iArr, 0, UInt.m1297constructorimpl(iArr2[0]));
    }
}
