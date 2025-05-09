package org.tensorflow.lite.support.image.ops;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.image.ColorSpaceType;
import org.tensorflow.lite.support.image.ImageOperator;
import org.tensorflow.lite.support.image.TensorImage;

/* loaded from: classes6.dex */
public class ResizeWithCropOrPadOp implements ImageOperator {
    private final Bitmap output;
    private final int targetHeight;
    private final int targetWidth;

    public ResizeWithCropOrPadOp(int targetHeight, int targetWidth) {
        this.targetHeight = targetHeight;
        this.targetWidth = targetWidth;
        this.output = Bitmap.createBitmap(targetWidth, targetHeight, Bitmap.Config.ARGB_8888);
    }

    @Override // org.tensorflow.lite.support.common.Operator
    public TensorImage apply(TensorImage image) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = 0;
        SupportPreconditions.checkArgument(image.getColorSpaceType() == ColorSpaceType.RGB, "Only RGB images are supported in ResizeWithCropOrPadOp, but not " + image.getColorSpaceType().name());
        Bitmap bitmap = image.getBitmap();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i8 = this.targetWidth;
        if (i8 > width) {
            i4 = (i8 - width) / 2;
            i3 = i4 + width;
            i2 = width;
            i = 0;
        } else {
            i = (width - i8) / 2;
            i2 = i + i8;
            i3 = i8;
            i4 = 0;
        }
        int i9 = this.targetHeight;
        if (i9 > height) {
            i5 = (i9 - height) / 2;
            i6 = i5 + height;
        } else {
            int i10 = (height - i9) / 2;
            int i11 = i10 + i9;
            i5 = 0;
            i7 = i10;
            height = i11;
            i6 = i9;
        }
        new Canvas(this.output).drawBitmap(bitmap, new Rect(i, i7, i2, height), new Rect(i4, i5, i3, i6), (Paint) null);
        image.load(this.output);
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
        return transformImpl(point, this.targetHeight, this.targetWidth, inputImageHeight, inputImageWidth);
    }

    private static PointF transformImpl(PointF point, int srcH, int srcW, int dstH, int dstW) {
        return new PointF(point.x + ((dstW - srcW) / 2), point.y + ((dstH - srcH) / 2));
    }
}
