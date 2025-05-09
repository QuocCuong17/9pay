package org.tensorflow.lite.support.image;

import android.graphics.Bitmap;
import android.media.Image;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes6.dex */
final class MediaImageContainer implements ImageContainer {
    private final Image image;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MediaImageContainer create(Image image) {
        return new MediaImageContainer(image);
    }

    private MediaImageContainer(Image image) {
        SupportPreconditions.checkNotNull(image, "Cannot load null Image.");
        SupportPreconditions.checkArgument(image.getFormat() == 35, "Only supports loading YUV_420_888 Image.");
        this.image = image;
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public MediaImageContainer clone() {
        throw new UnsupportedOperationException("android.media.Image is an abstract class and cannot be cloned.");
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public Bitmap getBitmap() {
        throw new UnsupportedOperationException("Converting an android.media.Image to Bitmap is not supported.");
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public TensorBuffer getTensorBuffer(DataType dataType) {
        throw new UnsupportedOperationException("Converting an android.media.Image to TesorBuffer is not supported.");
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public Image getMediaImage() {
        return this.image;
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public int getWidth() {
        return this.image.getWidth();
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public int getHeight() {
        return this.image.getHeight();
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public ColorSpaceType getColorSpaceType() {
        return ColorSpaceType.fromImageFormat(this.image.getFormat());
    }
}
