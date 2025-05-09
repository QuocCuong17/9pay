package org.tensorflow.lite.support.image;

import android.graphics.Bitmap;
import android.media.Image;
import java.nio.ByteBuffer;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes6.dex */
public class TensorImage {
    private ImageContainer container;
    private final DataType dataType;

    public TensorImage() {
        this(DataType.UINT8);
    }

    public TensorImage(DataType dataType) {
        this.container = null;
        SupportPreconditions.checkArgument(dataType == DataType.UINT8 || dataType == DataType.FLOAT32, "Illegal data type for TensorImage: Only FLOAT32 and UINT8 are accepted");
        this.dataType = dataType;
    }

    public static TensorImage fromBitmap(Bitmap bitmap) {
        TensorImage tensorImage = new TensorImage();
        tensorImage.load(bitmap);
        return tensorImage;
    }

    public static TensorImage createFrom(TensorImage src, DataType dataType) {
        TensorImage tensorImage = new TensorImage(dataType);
        tensorImage.container = src.container.clone();
        return tensorImage;
    }

    public void load(Bitmap bitmap) {
        this.container = BitmapContainer.create(bitmap);
    }

    public void load(float[] pixels, int[] shape) {
        TensorBuffer createDynamic = TensorBuffer.createDynamic(getDataType());
        createDynamic.loadArray(pixels, shape);
        load(createDynamic);
    }

    public void load(int[] pixels, int[] shape) {
        TensorBuffer createDynamic = TensorBuffer.createDynamic(getDataType());
        createDynamic.loadArray(pixels, shape);
        load(createDynamic);
    }

    public void load(TensorBuffer buffer) {
        load(buffer, ColorSpaceType.RGB);
    }

    public void load(TensorBuffer buffer, ColorSpaceType colorSpaceType) {
        SupportPreconditions.checkArgument(colorSpaceType == ColorSpaceType.RGB || colorSpaceType == ColorSpaceType.GRAYSCALE, "Only ColorSpaceType.RGB and ColorSpaceType.GRAYSCALE are supported. Use `load(TensorBuffer, ImageProperties)` for other color space types.");
        this.container = TensorBufferContainer.create(buffer, colorSpaceType);
    }

    public void load(TensorBuffer buffer, ImageProperties imageProperties) {
        this.container = TensorBufferContainer.create(buffer, imageProperties);
    }

    public void load(ByteBuffer buffer, ImageProperties imageProperties) {
        TensorBuffer createDynamic = TensorBuffer.createDynamic(DataType.UINT8);
        createDynamic.loadBuffer(buffer, new int[]{buffer.limit()});
        this.container = TensorBufferContainer.create(createDynamic, imageProperties);
    }

    public void load(Image image) {
        this.container = MediaImageContainer.create(image);
    }

    public Bitmap getBitmap() {
        ImageContainer imageContainer = this.container;
        if (imageContainer == null) {
            throw new IllegalStateException("No image has been loaded yet.");
        }
        return imageContainer.getBitmap();
    }

    public ByteBuffer getBuffer() {
        return getTensorBuffer().getBuffer();
    }

    public TensorBuffer getTensorBuffer() {
        ImageContainer imageContainer = this.container;
        if (imageContainer == null) {
            throw new IllegalStateException("No image has been loaded yet.");
        }
        return imageContainer.getTensorBuffer(this.dataType);
    }

    public Image getMediaImage() {
        ImageContainer imageContainer = this.container;
        if (imageContainer == null) {
            throw new IllegalStateException("No image has been loaded yet.");
        }
        return imageContainer.getMediaImage();
    }

    public DataType getDataType() {
        return this.dataType;
    }

    public ColorSpaceType getColorSpaceType() {
        ImageContainer imageContainer = this.container;
        if (imageContainer == null) {
            throw new IllegalStateException("No image has been loaded yet.");
        }
        return imageContainer.getColorSpaceType();
    }

    public int getWidth() {
        ImageContainer imageContainer = this.container;
        if (imageContainer == null) {
            throw new IllegalStateException("No image has been loaded yet.");
        }
        return imageContainer.getWidth();
    }

    public int getHeight() {
        ImageContainer imageContainer = this.container;
        if (imageContainer == null) {
            throw new IllegalStateException("No image has been loaded yet.");
        }
        return imageContainer.getHeight();
    }
}
