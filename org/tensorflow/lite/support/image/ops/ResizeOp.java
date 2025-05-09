package org.tensorflow.lite.support.image.ops;

import android.graphics.Bitmap;
import android.graphics.PointF;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.image.ColorSpaceType;
import org.tensorflow.lite.support.image.ImageOperator;
import org.tensorflow.lite.support.image.TensorImage;

/* loaded from: classes6.dex */
public class ResizeOp implements ImageOperator {
    private final int targetHeight;
    private final int targetWidth;
    private final boolean useBilinear;

    /* loaded from: classes6.dex */
    public enum ResizeMethod {
        BILINEAR,
        NEAREST_NEIGHBOR
    }

    public ResizeOp(int targetHeight, int targetWidth, ResizeMethod resizeMethod) {
        this.targetHeight = targetHeight;
        this.targetWidth = targetWidth;
        this.useBilinear = resizeMethod == ResizeMethod.BILINEAR;
    }

    @Override // org.tensorflow.lite.support.common.Operator
    public TensorImage apply(TensorImage image) {
        SupportPreconditions.checkArgument(image.getColorSpaceType() == ColorSpaceType.RGB, "Only RGB images are supported in ResizeOp, but not " + image.getColorSpaceType().name());
        image.load(Bitmap.createScaledBitmap(image.getBitmap(), this.targetWidth, this.targetHeight, this.useBilinear));
        return image;
    }

    @Override // org.tensorflow.lite.support.image.ImageOperator
    public int getOutputImageHeight(int inputImageHeight, int inputImageWidth) {
        return this.targetHeight;
    }

    @Override // org.tensorflow.lite.support.image.ImageOperator
    public int getOutputImageWidth(int inputImageHeight, int inputImageWidth) {
        return this.targetWidth;
    }

    @Override // org.tensorflow.lite.support.image.ImageOperator
    public PointF inverseTransform(PointF point, int inputImageHeight, int inputImageWidth) {
        return new PointF((point.x * inputImageWidth) / this.targetWidth, (point.y * inputImageHeight) / this.targetHeight);
    }
}
