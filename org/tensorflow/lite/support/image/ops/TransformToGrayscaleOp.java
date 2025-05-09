package org.tensorflow.lite.support.image.ops;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PointF;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.image.ColorSpaceType;
import org.tensorflow.lite.support.image.ImageOperator;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes6.dex */
public class TransformToGrayscaleOp implements ImageOperator {
    private static final float[] BITMAP_RGBA_GRAYSCALE_TRANSFORMATION = {0.299f, 0.587f, 0.114f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};

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

    @Override // org.tensorflow.lite.support.common.Operator
    public TensorImage apply(TensorImage image) {
        if (image.getColorSpaceType() == ColorSpaceType.GRAYSCALE) {
            return image;
        }
        SupportPreconditions.checkArgument(image.getColorSpaceType() == ColorSpaceType.RGB, "Only RGB images are supported in TransformToGrayscaleOp, but not " + image.getColorSpaceType().name());
        int height = image.getHeight();
        int width = image.getWidth();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(BITMAP_RGBA_GRAYSCALE_TRANSFORMATION));
        canvas.drawBitmap(image.getBitmap(), 0.0f, 0.0f, paint);
        int i = width * height;
        int[] iArr = new int[i];
        createBitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int[] iArr2 = {1, height, width, 1};
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = (iArr[i2] >> 16) & 255;
        }
        TensorBuffer createFixedSize = TensorBuffer.createFixedSize(iArr2, image.getDataType());
        createFixedSize.loadArray(iArr, iArr2);
        image.load(createFixedSize, ColorSpaceType.GRAYSCALE);
        return image;
    }
}
