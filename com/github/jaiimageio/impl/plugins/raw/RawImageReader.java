package com.github.jaiimageio.impl.plugins.raw;

import com.github.jaiimageio.stream.RawImageInputStream;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;

/* loaded from: classes3.dex */
public class RawImageReader extends ImageReader {
    private RawImageInputStream iis;

    public boolean canReadRaster() {
        return true;
    }

    public IIOMetadata getImageMetadata(int i) throws IOException {
        return null;
    }

    public IIOMetadata getStreamMetadata() throws IOException {
        return null;
    }

    public static void computeRegionsWrapper(ImageReadParam imageReadParam, int i, int i2, BufferedImage bufferedImage, Rectangle rectangle, Rectangle rectangle2) {
        computeRegions(imageReadParam, i, i2, bufferedImage, rectangle, rectangle2);
    }

    public RawImageReader(ImageReaderSpi imageReaderSpi) {
        super(imageReaderSpi);
        this.iis = null;
    }

    public void setInput(Object obj, boolean z, boolean z2) {
        super.setInput(obj, z, z2);
        this.iis = (RawImageInputStream) obj;
    }

    public int getNumImages(boolean z) throws IOException {
        return this.iis.getNumImages();
    }

    public int getWidth(int i) throws IOException {
        checkIndex(i);
        return this.iis.getImageDimension(i).width;
    }

    public int getHeight(int i) throws IOException {
        checkIndex(i);
        return this.iis.getImageDimension(i).height;
    }

    public int getTileWidth(int i) throws IOException {
        checkIndex(i);
        return this.iis.getImageType().getSampleModel().getWidth();
    }

    public int getTileHeight(int i) throws IOException {
        checkIndex(i);
        return this.iis.getImageType().getSampleModel().getHeight();
    }

    private void checkIndex(int i) throws IOException {
        if (i < 0 || i >= getNumImages(true)) {
            throw new IndexOutOfBoundsException(I18N.getString("RawImageReader0"));
        }
    }

    public Iterator getImageTypes(int i) throws IOException {
        checkIndex(i);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(this.iis.getImageType());
        return arrayList.iterator();
    }

    public ImageReadParam getDefaultReadParam() {
        return new ImageReadParam();
    }

    public boolean isRandomAccessEasy(int i) throws IOException {
        checkIndex(i);
        return true;
    }

    public BufferedImage read(int i, ImageReadParam imageReadParam) throws IOException {
        WritableRaster writableTile;
        if (imageReadParam == null) {
            imageReadParam = getDefaultReadParam();
        }
        checkIndex(i);
        clearAbortRequest();
        processImageStarted(i);
        BufferedImage destination = imageReadParam.getDestination();
        RawRenderedImage rawRenderedImage = new RawRenderedImage(this.iis, this, imageReadParam, i);
        imageReadParam.getDestinationOffset();
        if (destination == null) {
            ColorModel colorModel = rawRenderedImage.getColorModel();
            SampleModel sampleModel = rawRenderedImage.getSampleModel();
            ImageTypeSpecifier destinationType = imageReadParam.getDestinationType();
            if (destinationType != null) {
                colorModel = destinationType.getColorModel();
            }
            writableTile = Raster.createWritableRaster(sampleModel.createCompatibleSampleModel(rawRenderedImage.getMinX() + rawRenderedImage.getWidth(), rawRenderedImage.getMinY() + rawRenderedImage.getHeight()), new Point(0, 0));
            destination = new BufferedImage(colorModel, writableTile, colorModel != null ? colorModel.isAlphaPremultiplied() : false, new Hashtable());
        } else {
            writableTile = destination.getWritableTile(0, 0);
        }
        rawRenderedImage.setDestImage(destination);
        rawRenderedImage.readAsRaster(writableTile);
        rawRenderedImage.clearDestImage();
        if (abortRequested()) {
            processReadAborted();
        } else {
            processImageComplete();
        }
        return destination;
    }

    public RenderedImage readAsRenderedImage(int i, ImageReadParam imageReadParam) throws IOException {
        if (imageReadParam == null) {
            imageReadParam = getDefaultReadParam();
        }
        checkIndex(i);
        clearAbortRequest();
        processImageStarted(0);
        RawRenderedImage rawRenderedImage = new RawRenderedImage(this.iis, this, imageReadParam, i);
        if (abortRequested()) {
            processReadAborted();
        } else {
            processImageComplete();
        }
        return rawRenderedImage;
    }

    public Raster readRaster(int i, ImageReadParam imageReadParam) throws IOException {
        return read(i, imageReadParam).getData();
    }

    public void reset() {
        super.reset();
        this.iis = null;
    }

    public void processImageUpdateWrapper(BufferedImage bufferedImage, int i, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        processImageUpdate(bufferedImage, i, i2, i3, i4, i5, i6, iArr);
    }

    public void processImageProgressWrapper(float f) {
        processImageProgress(f);
    }

    public boolean getAbortRequest() {
        return abortRequested();
    }
}
