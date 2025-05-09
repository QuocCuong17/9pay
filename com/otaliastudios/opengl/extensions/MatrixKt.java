package com.otaliastudios.opengl.extensions;

import com.otaliastudios.opengl.internal.MiscKt;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Matrix.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0010\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\n\u0010\u0003\u001a\u00020\u0002*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0002*\u00020\u0002\u001a*\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007\u001a\u0012\u0010\u000b\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0012\u0010\f\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0012\u0010\r\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007\u001a(\u0010\u000e\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0007\u001a\u0012\u0010\u000f\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0007\u001a\u0012\u0010\u0010\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0007\u001a\u0012\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0007\u001a(\u0010\u0012\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0007\u001a\u0012\u0010\u0013\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0007\u001a\u0012\u0010\u0015\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0007\u001a\u0012\u0010\u0016\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0007¨\u0006\u0017"}, d2 = {"checkSize", "", "", "clear", "makeIdentity", "rotate", "angle", "", ViewHierarchyNode.JsonKeys.X, ViewHierarchyNode.JsonKeys.Y, "z", "rotateX", "rotateY", "rotateZ", "scale", "scaleX", "scaleY", "scaleZ", "translate", "translateX", "translation", "translateY", "translateZ", "library_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class MatrixKt {
    private static final void checkSize(float[] fArr) {
        if (fArr.length != 16) {
            throw new RuntimeException("Need a 16 values matrix.");
        }
    }

    public static final float[] makeIdentity(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        checkSize(fArr);
        MiscKt.matrixMakeIdentity(fArr);
        return fArr;
    }

    public static final float[] clear(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return makeIdentity(fArr);
    }

    public static /* synthetic */ float[] translate$default(float[] fArr, float f, float f2, float f3, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 0.0f;
        }
        if ((i & 2) != 0) {
            f2 = 0.0f;
        }
        if ((i & 4) != 0) {
            f3 = 0.0f;
        }
        return translate(fArr, f, f2, f3);
    }

    public static final float[] translate(float[] fArr, float f, float f2, float f3) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        checkSize(fArr);
        MiscKt.matrixTranslate(fArr, f, f2, f3);
        return fArr;
    }

    public static final float[] translateX(float[] fArr, float f) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return translate$default(fArr, f, 0.0f, 0.0f, 6, null);
    }

    public static final float[] translateY(float[] fArr, float f) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return translate$default(fArr, 0.0f, f, 0.0f, 5, null);
    }

    public static final float[] translateZ(float[] fArr, float f) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return translate$default(fArr, 0.0f, 0.0f, f, 3, null);
    }

    public static /* synthetic */ float[] scale$default(float[] fArr, float f, float f2, float f3, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 1.0f;
        }
        if ((i & 2) != 0) {
            f2 = 1.0f;
        }
        if ((i & 4) != 0) {
            f3 = 1.0f;
        }
        return scale(fArr, f, f2, f3);
    }

    public static final float[] scale(float[] fArr, float f, float f2, float f3) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        checkSize(fArr);
        MiscKt.matrixScale(fArr, f, f2, f3);
        return fArr;
    }

    public static final float[] scaleX(float[] fArr, float f) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return scale$default(fArr, f, 0.0f, 0.0f, 6, null);
    }

    public static final float[] scaleY(float[] fArr, float f) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return scale$default(fArr, 0.0f, f, 0.0f, 5, null);
    }

    public static final float[] scaleZ(float[] fArr, float f) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return scale$default(fArr, 0.0f, 0.0f, f, 3, null);
    }

    public static final float[] rotate(float[] fArr, float f, float f2, float f3, float f4) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        checkSize(fArr);
        MiscKt.matrixRotate(fArr, f, f2, f3, f4);
        return fArr;
    }

    public static final float[] rotateX(float[] fArr, float f) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return rotate(fArr, f, 1.0f, 0.0f, 0.0f);
    }

    public static final float[] rotateY(float[] fArr, float f) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return rotate(fArr, f, 0.0f, 1.0f, 0.0f);
    }

    public static final float[] rotateZ(float[] fArr, float f) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return rotate(fArr, f, 0.0f, 0.0f, 1.0f);
    }
}
