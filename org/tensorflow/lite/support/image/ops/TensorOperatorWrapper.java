package org.tensorflow.lite.support.image.ops;

import android.graphics.PointF;
import org.tensorflow.lite.support.common.TensorOperator;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.image.ColorSpaceType;
import org.tensorflow.lite.support.image.ImageOperator;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes6.dex */
public class TensorOperatorWrapper implements ImageOperator {
    private final TensorOperator tensorOp;

    @Override // org.tensorflow.lite.support.image.ImageOperator
    public int getOutputImageHeight(int inputImageHeight, int inputImageWidth) {
        return inputImageHeight;
    }

    @Override // org.tensorflow.lite.support.image.ImageOperator
    public int getOutputImageWidth(int inputImageHeight, int inputImageWidth) {
        return inputImageWidth;
    }

    @Override // org.tensorflow.lite.support.image.ImageOperator
    public PointF inverseTransform(PointF point, int inputImageHeight, int inputImageWidth) {
        return point;
    }

    public TensorOperatorWrapper(TensorOperator op) {
        this.tensorOp = op;
    }

    @Override // org.tensorflow.lite.support.common.Operator
    public TensorImage apply(TensorImage image) {
        SupportPreconditions.checkNotNull(image, "Op cannot apply on null image.");
        TensorBuffer apply = this.tensorOp.apply(image.getTensorBuffer());
        ColorSpaceType colorSpaceType = image.getColorSpaceType();
        TensorImage tensorImage = new TensorImage(apply.getDataType());
        tensorImage.load(apply, colorSpaceType);
        return tensorImage;
    }
}
