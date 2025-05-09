package org.tensorflow.lite.support.image;

import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes6.dex */
final class TensorBufferContainer implements ImageContainer {
    private static final String TAG = "TensorBufferContainer";
    private final TensorBuffer buffer;
    private final ColorSpaceType colorSpaceType;
    private final int height;
    private final int width;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TensorBufferContainer create(TensorBuffer buffer, ColorSpaceType colorSpaceType) {
        SupportPreconditions.checkArgument(colorSpaceType == ColorSpaceType.RGB || colorSpaceType == ColorSpaceType.GRAYSCALE, "Only ColorSpaceType.RGB and ColorSpaceType.GRAYSCALE are supported. Use `create(TensorBuffer, ImageProperties)` for other color space types.");
        return new TensorBufferContainer(buffer, colorSpaceType, colorSpaceType.getHeight(buffer.getShape()), colorSpaceType.getWidth(buffer.getShape()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TensorBufferContainer create(TensorBuffer buffer, ImageProperties imageProperties) {
        return new TensorBufferContainer(buffer, imageProperties.getColorSpaceType(), imageProperties.getHeight(), imageProperties.getWidth());
    }

    private TensorBufferContainer(TensorBuffer buffer, ColorSpaceType colorSpaceType, int height, int width) {
        SupportPreconditions.checkArgument(colorSpaceType != ColorSpaceType.YUV_420_888, "The actual encoding format of YUV420 is required. Choose a ColorSpaceType from: NV12, NV21, YV12, YV21. Use YUV_420_888 only when loading an android.media.Image.");
        colorSpaceType.assertNumElements(buffer.getFlatSize(), height, width);
        this.buffer = buffer;
        this.colorSpaceType = colorSpaceType;
        this.height = height;
        this.width = width;
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public TensorBufferContainer clone() {
        TensorBuffer tensorBuffer = this.buffer;
        return new TensorBufferContainer(TensorBuffer.createFrom(tensorBuffer, tensorBuffer.getDataType()), this.colorSpaceType, getHeight(), getWidth());
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public Bitmap getBitmap() {
        if (this.buffer.getDataType() != DataType.UINT8) {
            Log.w(TAG, "<Warning> TensorBufferContainer is holding a non-uint8 image. The conversion to Bitmap will cause numeric casting and clamping on the data value.");
        }
        return this.colorSpaceType.convertTensorBufferToBitmap(this.buffer);
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public TensorBuffer getTensorBuffer(DataType dataType) {
        return this.buffer.getDataType() == dataType ? this.buffer : TensorBuffer.createFrom(this.buffer, dataType);
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public Image getMediaImage() {
        throw new UnsupportedOperationException("Converting from TensorBuffer to android.media.Image is unsupported.");
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public int getWidth() {
        this.colorSpaceType.assertNumElements(this.buffer.getFlatSize(), this.height, this.width);
        return this.width;
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public int getHeight() {
        this.colorSpaceType.assertNumElements(this.buffer.getFlatSize(), this.height, this.width);
        return this.height;
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public ColorSpaceType getColorSpaceType() {
        return this.colorSpaceType;
    }
}
