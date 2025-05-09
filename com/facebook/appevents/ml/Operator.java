package com.facebook.appevents.ml;

import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Operator.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J\u001b\u0010\b\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nH\u0007¢\u0006\u0002\u0010\u000bJ\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0007J \u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J+\u0010\u000f\u001a\u00020\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u0006H\u0007¢\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0013H\u0007J\u0018\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0013H\u0007J\u0018\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0007J\u0010\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u001e"}, d2 = {"Lcom/facebook/appevents/ml/Operator;", "", "()V", "addmv", "", ViewHierarchyNode.JsonKeys.X, "Lcom/facebook/appevents/ml/MTensor;", "b", "concatenate", "tensors", "", "([Lcom/facebook/appevents/ml/MTensor;)Lcom/facebook/appevents/ml/MTensor;", "conv1D", "w", "dense", "embedding", "texts", "", "seqLength", "", "([Ljava/lang/String;ILcom/facebook/appevents/ml/MTensor;)Lcom/facebook/appevents/ml/MTensor;", "flatten", "startDim", "maxPool1D", "poolSize", "mul", "relu", "softmax", "transpose2D", "transpose3D", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Operator {
    public static final Operator INSTANCE = new Operator();

    private Operator() {
    }

    @JvmStatic
    public static final void addmv(MTensor x, MTensor b) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            Intrinsics.checkNotNullParameter(b, "b");
            int shape = x.getShape(0);
            int shape2 = x.getShape(1);
            int shape3 = x.getShape(2);
            float[] data = x.getData();
            float[] data2 = b.getData();
            for (int i = 0; i < shape; i++) {
                for (int i2 = 0; i2 < shape2; i2++) {
                    for (int i3 = 0; i3 < shape3; i3++) {
                        int i4 = (i * shape2 * shape3) + (i2 * shape3) + i3;
                        data[i4] = data[i4] + data2[i3];
                    }
                }
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
        }
    }

    @JvmStatic
    public static final MTensor mul(MTensor x, MTensor w) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            Intrinsics.checkNotNullParameter(w, "w");
            int shape = x.getShape(0);
            int shape2 = w.getShape(0);
            int shape3 = w.getShape(1);
            MTensor mTensor = new MTensor(new int[]{shape, shape3});
            float[] data = x.getData();
            float[] data2 = w.getData();
            float[] data3 = mTensor.getData();
            for (int i = 0; i < shape; i++) {
                for (int i2 = 0; i2 < shape3; i2++) {
                    int i3 = (i * shape3) + i2;
                    data3[i3] = 0.0f;
                    for (int i4 = 0; i4 < shape2; i4++) {
                        data3[i3] = data3[i3] + (data[(i * shape2) + i4] * data2[(i4 * shape3) + i2]);
                    }
                }
            }
            return mTensor;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
            return null;
        }
    }

    @JvmStatic
    public static final void relu(MTensor x) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            float[] data = x.getData();
            int length = data.length;
            for (int i = 0; i < length; i++) {
                if (data[i] < 0.0f) {
                    data[i] = 0.0f;
                }
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
        }
    }

    @JvmStatic
    public static final void flatten(MTensor x, int startDim) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            if (startDim >= x.getShapeSize()) {
                return;
            }
            int shapeSize = x.getShapeSize();
            int i = 1;
            for (int i2 = startDim; i2 < shapeSize; i2++) {
                i *= x.getShape(i2);
            }
            int[] iArr = new int[startDim + 1];
            for (int i3 = 0; i3 < startDim; i3++) {
                iArr[i3] = x.getShape(i3);
            }
            iArr[startDim] = i;
            x.reshape(iArr);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
        }
    }

    @JvmStatic
    public static final MTensor concatenate(MTensor[] tensors) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(tensors, "tensors");
            int shape = tensors[0].getShape(0);
            int i = 0;
            for (MTensor mTensor : tensors) {
                i += mTensor.getShape(1);
            }
            MTensor mTensor2 = new MTensor(new int[]{shape, i});
            float[] data = mTensor2.getData();
            for (int i2 = 0; i2 < shape; i2++) {
                int i3 = i2 * i;
                int length = tensors.length;
                for (int i4 = 0; i4 < length; i4++) {
                    float[] data2 = tensors[i4].getData();
                    int shape2 = tensors[i4].getShape(1);
                    System.arraycopy(data2, i2 * shape2, data, i3, shape2);
                    i3 += shape2;
                }
            }
            return mTensor2;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
            return null;
        }
    }

    @JvmStatic
    public static final void softmax(MTensor x) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            int shape = x.getShape(0);
            int shape2 = x.getShape(1);
            float[] data = x.getData();
            for (int i = 0; i < shape; i++) {
                int i2 = i * shape2;
                int i3 = i2 + shape2;
                float f = Float.MIN_VALUE;
                float f2 = 0.0f;
                for (int i4 = i2; i4 < i3; i4++) {
                    if (data[i4] > f) {
                        f = data[i4];
                    }
                }
                for (int i5 = i2; i5 < i3; i5++) {
                    data[i5] = (float) Math.exp(data[i5] - f);
                    f2 += data[i5];
                }
                while (i2 < i3) {
                    data[i2] = data[i2] / f2;
                    i2++;
                }
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
        }
    }

    @JvmStatic
    public static final MTensor dense(MTensor x, MTensor w, MTensor b) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            Intrinsics.checkNotNullParameter(w, "w");
            Intrinsics.checkNotNullParameter(b, "b");
            int shape = x.getShape(0);
            int shape2 = b.getShape(0);
            MTensor mul = mul(x, w);
            float[] data = b.getData();
            float[] data2 = mul.getData();
            for (int i = 0; i < shape; i++) {
                for (int i2 = 0; i2 < shape2; i2++) {
                    int i3 = (i * shape2) + i2;
                    data2[i3] = data2[i3] + data[i2];
                }
            }
            return mul;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
            return null;
        }
    }

    @JvmStatic
    public static final MTensor embedding(String[] texts, int seqLength, MTensor w) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(texts, "texts");
            Intrinsics.checkNotNullParameter(w, "w");
            int length = texts.length;
            int shape = w.getShape(1);
            MTensor mTensor = new MTensor(new int[]{length, seqLength, shape});
            float[] data = mTensor.getData();
            float[] data2 = w.getData();
            for (int i = 0; i < length; i++) {
                int[] vectorize = Utils.INSTANCE.vectorize(texts[i], seqLength);
                for (int i2 = 0; i2 < seqLength; i2++) {
                    System.arraycopy(data2, vectorize[i2] * shape, data, (shape * seqLength * i) + (shape * i2), shape);
                }
            }
            return mTensor;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
            return null;
        }
    }

    @JvmStatic
    public static final MTensor transpose2D(MTensor x) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            int shape = x.getShape(0);
            int shape2 = x.getShape(1);
            MTensor mTensor = new MTensor(new int[]{shape2, shape});
            float[] data = x.getData();
            float[] data2 = mTensor.getData();
            for (int i = 0; i < shape; i++) {
                for (int i2 = 0; i2 < shape2; i2++) {
                    data2[(i2 * shape) + i] = data[(i * shape2) + i2];
                }
            }
            return mTensor;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
            return null;
        }
    }

    @JvmStatic
    public static final MTensor transpose3D(MTensor x) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            int shape = x.getShape(0);
            int shape2 = x.getShape(1);
            int shape3 = x.getShape(2);
            MTensor mTensor = new MTensor(new int[]{shape3, shape2, shape});
            float[] data = x.getData();
            float[] data2 = mTensor.getData();
            for (int i = 0; i < shape; i++) {
                for (int i2 = 0; i2 < shape2; i2++) {
                    for (int i3 = 0; i3 < shape3; i3++) {
                        data2[(i3 * shape * shape2) + (i2 * shape) + i] = data[(i * shape2 * shape3) + (i2 * shape3) + i3];
                    }
                }
            }
            return mTensor;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
            return null;
        }
    }

    @JvmStatic
    public static final MTensor conv1D(MTensor x, MTensor w) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            Intrinsics.checkNotNullParameter(w, "w");
            int i = 0;
            int shape = x.getShape(0);
            int shape2 = x.getShape(1);
            int shape3 = x.getShape(2);
            int shape4 = w.getShape(0);
            int i2 = (shape2 - shape4) + 1;
            int shape5 = w.getShape(2);
            MTensor mTensor = new MTensor(new int[]{shape, i2, shape5});
            float[] data = x.getData();
            float[] data2 = mTensor.getData();
            float[] data3 = w.getData();
            int i3 = 0;
            while (i3 < shape) {
                int i4 = i;
                while (i4 < shape5) {
                    int i5 = i;
                    while (i5 < i2) {
                        float f = 0.0f;
                        while (i < shape4) {
                            for (int i6 = 0; i6 < shape3; i6++) {
                                f += data[(shape2 * shape3 * i3) + ((i + i5) * shape3) + i6] * data3[(((i * shape3) + i6) * shape5) + i4];
                            }
                            i++;
                        }
                        data2[(i2 * shape5 * i3) + (i5 * shape5) + i4] = f;
                        i5++;
                        i = 0;
                    }
                    i4++;
                    i = 0;
                }
                i3++;
                i = 0;
            }
            return mTensor;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
            return null;
        }
    }

    @JvmStatic
    public static final MTensor maxPool1D(MTensor x, int poolSize) {
        if (CrashShieldHandler.isObjectCrashing(Operator.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(x, "x");
            int i = 0;
            int shape = x.getShape(0);
            int shape2 = x.getShape(1);
            int shape3 = x.getShape(2);
            int i2 = (shape2 - poolSize) + 1;
            MTensor mTensor = new MTensor(new int[]{shape, i2, shape3});
            float[] data = x.getData();
            float[] data2 = mTensor.getData();
            int i3 = 0;
            while (i3 < shape) {
                int i4 = i;
                while (i4 < shape3) {
                    int i5 = i;
                    while (i5 < i2) {
                        int i6 = i5 * shape3;
                        int i7 = (i3 * i2 * shape3) + i6 + i4;
                        int i8 = (i3 * shape2 * shape3) + i6 + i4;
                        data2[i7] = Float.MIN_VALUE;
                        for (int i9 = i; i9 < poolSize; i9++) {
                            data2[i7] = Math.max(data2[i7], data[i8 + (i9 * shape3)]);
                        }
                        i5++;
                        i = 0;
                    }
                    i4++;
                    i = 0;
                }
                i3++;
                i = 0;
            }
            return mTensor;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, Operator.class);
            return null;
        }
    }
}
