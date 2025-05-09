package com.github.jaiimageio.impl.plugins.wbmp;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.IndexColorModel;
import java.awt.image.MultiPixelPackedSampleModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.io.IOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageOutputStream;

/* loaded from: classes3.dex */
public class WBMPImageWriter extends ImageWriter {
    private ImageOutputStream stream;

    private static int getNumBits(int i) {
        int i2 = 32;
        for (int i3 = Integer.MIN_VALUE; i3 != 0 && (i & i3) == 0; i3 >>>= 1) {
            i2--;
        }
        return i2;
    }

    public boolean canWriteRasters() {
        return true;
    }

    public IIOMetadata convertImageMetadata(IIOMetadata iIOMetadata, ImageTypeSpecifier imageTypeSpecifier, ImageWriteParam imageWriteParam) {
        return null;
    }

    public IIOMetadata convertStreamMetadata(IIOMetadata iIOMetadata, ImageWriteParam imageWriteParam) {
        return null;
    }

    public IIOMetadata getDefaultStreamMetadata(ImageWriteParam imageWriteParam) {
        return null;
    }

    private static byte[] intToMultiByte(int i) {
        int numBits = (getNumBits(i) + 6) / 7;
        byte[] bArr = new byte[numBits];
        int i2 = numBits - 1;
        for (int i3 = 0; i3 <= i2; i3++) {
            bArr[i3] = (byte) ((i >>> ((i2 - i3) * 7)) & 127);
            if (i3 != i2) {
                bArr[i3] = (byte) (bArr[i3] | Byte.MIN_VALUE);
            }
        }
        return bArr;
    }

    public WBMPImageWriter(ImageWriterSpi imageWriterSpi) {
        super(imageWriterSpi);
        this.stream = null;
    }

    public void setOutput(Object obj) {
        super.setOutput(obj);
        if (obj != null) {
            if (!(obj instanceof ImageOutputStream)) {
                throw new IllegalArgumentException(I18N.getString("WBMPImageWriter"));
            }
            this.stream = (ImageOutputStream) obj;
            return;
        }
        this.stream = null;
    }

    public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageTypeSpecifier, ImageWriteParam imageWriteParam) {
        WBMPMetadata wBMPMetadata = new WBMPMetadata();
        wBMPMetadata.wbmpType = 0;
        return wBMPMetadata;
    }

    public void write(IIOMetadata iIOMetadata, IIOImage iIOImage, ImageWriteParam imageWriteParam) throws IOException {
        SampleModel sampleModel;
        Raster data;
        Rectangle intersection;
        int i;
        boolean z;
        if (this.stream == null) {
            throw new IllegalStateException(I18N.getString("WBMPImageWriter3"));
        }
        if (iIOImage == null) {
            throw new IllegalArgumentException(I18N.getString("WBMPImageWriter4"));
        }
        clearAbortRequest();
        processImageStarted(0);
        ImageWriteParam defaultWriteParam = imageWriteParam == null ? getDefaultWriteParam() : imageWriteParam;
        RenderedImage renderedImage = null;
        boolean hasRaster = iIOImage.hasRaster();
        Rectangle sourceRegion = defaultWriteParam.getSourceRegion();
        if (hasRaster) {
            data = iIOImage.getRaster();
            sampleModel = data.getSampleModel();
        } else {
            renderedImage = iIOImage.getRenderedImage();
            sampleModel = renderedImage.getSampleModel();
            data = renderedImage.getData();
        }
        checkSampleModel(sampleModel);
        if (sourceRegion == null) {
            intersection = data.getBounds();
        } else {
            intersection = sourceRegion.intersection(data.getBounds());
        }
        if (intersection.isEmpty()) {
            throw new RuntimeException(I18N.getString("WBMPImageWriter1"));
        }
        int sourceXSubsampling = defaultWriteParam.getSourceXSubsampling();
        int sourceYSubsampling = defaultWriteParam.getSourceYSubsampling();
        int subsamplingXOffset = defaultWriteParam.getSubsamplingXOffset();
        int subsamplingYOffset = defaultWriteParam.getSubsamplingYOffset();
        intersection.translate(subsamplingXOffset, subsamplingYOffset);
        intersection.width -= subsamplingXOffset;
        intersection.height -= subsamplingYOffset;
        int i2 = intersection.x / sourceXSubsampling;
        int i3 = intersection.y / sourceYSubsampling;
        int i4 = ((intersection.width + sourceXSubsampling) - 1) / sourceXSubsampling;
        int i5 = ((intersection.height + sourceYSubsampling) - 1) / sourceYSubsampling;
        Rectangle rectangle = new Rectangle(i2, i3, i4, i5);
        SampleModel createCompatibleSampleModel = sampleModel.createCompatibleSampleModel(i4, i5);
        SampleModel multiPixelPackedSampleModel = (createCompatibleSampleModel.getDataType() == 0 && (createCompatibleSampleModel instanceof MultiPixelPackedSampleModel) && ((MultiPixelPackedSampleModel) createCompatibleSampleModel).getDataBitOffset() == 0) ? createCompatibleSampleModel : new MultiPixelPackedSampleModel(0, i4, i5, 1, (i4 + 7) >> 3, 0);
        if (rectangle.equals(intersection)) {
            i = i5;
        } else if (sourceXSubsampling == 1 && sourceYSubsampling == 1) {
            i = i5;
            data = data.createChild(data.getMinX(), data.getMinY(), i4, i5, i2, i3, (int[]) null);
        } else {
            i = i5;
            Raster createWritableRaster = Raster.createWritableRaster(multiPixelPackedSampleModel, new Point(i2, i3));
            byte[] data2 = createWritableRaster.getDataBuffer().getData();
            int i6 = intersection.y;
            int i7 = 0;
            int i8 = i3;
            while (i8 < i3 + i) {
                int i9 = intersection.x;
                Rectangle rectangle2 = intersection;
                int i10 = 0;
                while (i10 < i4) {
                    Raster raster = createWritableRaster;
                    int i11 = i7 + (i10 >> 3);
                    data2[i11] = (byte) (data2[i11] | (data.getSample(i9, i6, 0) << (7 - (i10 & 7))));
                    i10++;
                    i9 += sourceXSubsampling;
                    createWritableRaster = raster;
                    data = data;
                }
                i7 += (i4 + 7) >> 3;
                i8++;
                i6 += sourceYSubsampling;
                intersection = rectangle2;
            }
            data = createWritableRaster;
        }
        if (!multiPixelPackedSampleModel.equals(data.getSampleModel())) {
            Raster createWritableRaster2 = Raster.createWritableRaster(multiPixelPackedSampleModel, new Point(data.getMinX(), data.getMinY()));
            createWritableRaster2.setRect(data);
            data = createWritableRaster2;
        }
        if (hasRaster || !(renderedImage.getColorModel() instanceof IndexColorModel)) {
            z = false;
        } else {
            IndexColorModel colorModel = renderedImage.getColorModel();
            z = colorModel.getRed(0) > colorModel.getRed(1);
        }
        int scanlineStride = ((MultiPixelPackedSampleModel) multiPixelPackedSampleModel).getScanlineStride();
        int i12 = (i4 + 7) / 8;
        byte[] data3 = data.getDataBuffer().getData();
        this.stream.write(0);
        this.stream.write(0);
        this.stream.write(intToMultiByte(i4));
        this.stream.write(intToMultiByte(i));
        if (!z && scanlineStride == i12) {
            this.stream.write(data3, 0, i * i12);
            processImageProgress(100.0f);
        } else if (!z) {
            int i13 = 0;
            for (int i14 = 0; i14 < i && !abortRequested(); i14++) {
                this.stream.write(data3, i13, i12);
                i13 += scanlineStride;
                processImageProgress((i14 * 100.0f) / i);
            }
        } else {
            byte[] bArr = new byte[i12];
            int i15 = 0;
            for (int i16 = 0; i16 < i && !abortRequested(); i16++) {
                for (int i17 = 0; i17 < i12; i17++) {
                    bArr[i17] = (byte) (~data3[i17 + i15]);
                }
                this.stream.write(bArr, 0, i12);
                i15 += scanlineStride;
                processImageProgress((i16 * 100.0f) / i);
            }
        }
        if (abortRequested()) {
            processWriteAborted();
            return;
        }
        processImageComplete();
        ImageOutputStream imageOutputStream = this.stream;
        imageOutputStream.flushBefore(imageOutputStream.getStreamPosition());
    }

    public void reset() {
        super.reset();
        this.stream = null;
    }

    private void checkSampleModel(SampleModel sampleModel) {
        int dataType = sampleModel.getDataType();
        if (dataType < 0 || dataType > 3 || sampleModel.getNumBands() != 1 || sampleModel.getSampleSize(0) != 1) {
            throw new IllegalArgumentException(I18N.getString("WBMPImageWriter2"));
        }
    }
}
