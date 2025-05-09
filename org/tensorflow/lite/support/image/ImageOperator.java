package org.tensorflow.lite.support.image;

import android.graphics.PointF;
import org.tensorflow.lite.support.common.Operator;

/* loaded from: classes6.dex */
public interface ImageOperator extends Operator<TensorImage> {
    TensorImage apply(TensorImage image);

    int getOutputImageHeight(int inputImageHeight, int inputImageWidth);

    int getOutputImageWidth(int inputImageHeight, int inputImageWidth);

    PointF inverseTransform(PointF point, int inputImageHeight, int inputImageWidth);

    /* renamed from: org.tensorflow.lite.support.image.ImageOperator$-CC, reason: invalid class name */
    /* loaded from: classes6.dex */
    public final /* synthetic */ class CC {
    }
}
