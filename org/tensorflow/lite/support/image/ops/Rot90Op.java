package org.tensorflow.lite.support.image.ops;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.image.ColorSpaceType;
import org.tensorflow.lite.support.image.ImageOperator;
import org.tensorflow.lite.support.image.TensorImage;

/* loaded from: classes6.dex */
public class Rot90Op implements ImageOperator {
    private final int numRotation;

    public Rot90Op() {
        this(1);
    }

    public Rot90Op(int k) {
        this.numRotation = k % 4;
    }

    @Override // org.tensorflow.lite.support.common.Operator
    public TensorImage apply(TensorImage image) {
        SupportPreconditions.checkArgument(image.getColorSpaceType() == ColorSpaceType.RGB, "Only RGB images are supported in Rot90Op, but not " + image.getColorSpaceType().name());
        Bitmap bitmap = image.getBitmap();
        if (this.numRotation == 0) {
            return image;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postTranslate(width * 0.5f, height * 0.5f);
        matrix.postRotate(this.numRotation * (-90));
        int i = this.numRotation;
        matrix.postTranslate((i % 2 == 0 ? width : height) * 0.5f, (i % 2 == 0 ? height : width) * 0.5f);
        image.load(Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false));
        return image;
    }

    @Override // org.tensorflow.lite.support.image.ImageOperator
    public int getOutputImageHeight(int inputImageHeight, int inputImageWidth) {
        return this.numRotation % 2 == 0 ? inputImageHeight : inputImageWidth;
    }

    @Override // org.tensorflow.lite.support.image.ImageOperator
    public int getOutputImageWidth(int inputImageHeight, int inputImageWidth) {
        return this.numRotation % 2 == 0 ? inputImageWidth : inputImageHeight;
    }

    @Override // org.tensorflow.lite.support.image.ImageOperator
    public PointF inverseTransform(PointF point, int inputImageHeight, int inputImageWidth) {
        return transformImpl(point, getOutputImageHeight(inputImageHeight, inputImageWidth), getOutputImageWidth(inputImageHeight, inputImageWidth), (4 - this.numRotation) % 4);
    }

    private static PointF transformImpl(PointF point, int height, int width, int numRotation) {
        if (numRotation == 0) {
            return point;
        }
        if (numRotation == 1) {
            return new PointF(point.y, width - point.x);
        }
        if (numRotation == 2) {
            return new PointF(width - point.x, height - point.y);
        }
        return new PointF(height - point.y, point.x);
    }
}
