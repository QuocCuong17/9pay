package com.otaliastudios.opengl.internal;

import android.opengl.GLU;
import android.opengl.Matrix;
import android.util.Log;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.tekartik.sqflite.Constant;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: misc.kt */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0006\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0080\b\u001a\u0019\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0080\b\u001a\u0019\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0080\b\u001a\u0019\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0080\b\u001a\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0000\u001a\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\rH\u0000\u001a \u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\rH\u0000\u001a0\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0016H\u0000\u001a(\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0016H\u0000\u001a(\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0016H\u0000¨\u0006\u001c"}, d2 = {"gluErrorString", "", "value", "", "intToHexString", "loge", "", "tag", "message", "logi", "logv", "logw", "matrixClone", "", "matrix", "matrixMakeIdentity", "matrixMultiply", Constant.PARAM_RESULT, "left", TtmlNode.RIGHT, "matrixRotate", "angle", "", ViewHierarchyNode.JsonKeys.X, ViewHierarchyNode.JsonKeys.Y, "z", "matrixScale", "matrixTranslate", "library_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class MiscKt {
    public static final void logv(String tag, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        Log.v(tag, message);
    }

    public static final void logi(String tag, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        Log.i(tag, message);
    }

    public static final void logw(String tag, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        Log.w(tag, message);
    }

    public static final void loge(String tag, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        Log.e(tag, message);
    }

    public static final String intToHexString(int i) {
        String hexString = Integer.toHexString(i);
        Intrinsics.checkNotNullExpressionValue(hexString, "toHexString(value)");
        return hexString;
    }

    public static final String gluErrorString(int i) {
        String gluErrorString = GLU.gluErrorString(i);
        Intrinsics.checkNotNullExpressionValue(gluErrorString, "gluErrorString(value)");
        return gluErrorString;
    }

    public static final void matrixMakeIdentity(float[] matrix) {
        Intrinsics.checkNotNullParameter(matrix, "matrix");
        Matrix.setIdentityM(matrix, 0);
    }

    public static final void matrixTranslate(float[] matrix, float f, float f2, float f3) {
        Intrinsics.checkNotNullParameter(matrix, "matrix");
        Matrix.translateM(matrix, 0, f, f2, f3);
    }

    public static final void matrixScale(float[] matrix, float f, float f2, float f3) {
        Intrinsics.checkNotNullParameter(matrix, "matrix");
        Matrix.scaleM(matrix, 0, f, f2, f3);
    }

    public static final void matrixRotate(float[] matrix, float f, float f2, float f3, float f4) {
        Intrinsics.checkNotNullParameter(matrix, "matrix");
        Matrix.rotateM(matrix, 0, f, f2, f3, f4);
    }

    public static final float[] matrixClone(float[] matrix) {
        Intrinsics.checkNotNullParameter(matrix, "matrix");
        return (float[]) matrix.clone();
    }

    public static final void matrixMultiply(float[] result, float[] left, float[] right) {
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(left, "left");
        Intrinsics.checkNotNullParameter(right, "right");
        Matrix.multiplyMM(result, 0, left, 0, right, 0);
    }
}
