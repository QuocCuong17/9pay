package com.github.jaiimageio.impl.plugins.pcx;

import com.github.jaiimageio.impl.common.ImageUtil;
import com.google.firebase.database.core.ValidationPath;
import java.awt.Rectangle;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.ByteOrder;
import javax.imageio.IIOImage;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageOutputStream;

/* loaded from: classes3.dex */
public class PCXImageWriter extends ImageWriter implements PCXConstants {
    private int bytesPerLine;
    private int colorPlanes;
    private Rectangle destinationRegion;
    private Raster inputRaster;
    private ImageOutputStream ios;
    private int scaleX;
    private int scaleY;
    private Rectangle sourceRegion;

    public IIOMetadata convertStreamMetadata(IIOMetadata iIOMetadata, ImageWriteParam imageWriteParam) {
        return null;
    }

    public IIOMetadata getDefaultStreamMetadata(ImageWriteParam imageWriteParam) {
        return null;
    }

    public PCXImageWriter(PCXImageWriterSpi pCXImageWriterSpi) {
        super(pCXImageWriterSpi);
        this.inputRaster = null;
    }

    public void setOutput(Object obj) {
        super.setOutput(obj);
        if (obj != null) {
            if (!(obj instanceof ImageOutputStream)) {
                throw new IllegalArgumentException("output not instance of ImageOutputStream");
            }
            ImageOutputStream imageOutputStream = (ImageOutputStream) obj;
            this.ios = imageOutputStream;
            imageOutputStream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
            return;
        }
        this.ios = null;
    }

    public IIOMetadata convertImageMetadata(IIOMetadata iIOMetadata, ImageTypeSpecifier imageTypeSpecifier, ImageWriteParam imageWriteParam) {
        if (iIOMetadata instanceof PCXMetadata) {
            return iIOMetadata;
        }
        return null;
    }

    public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageTypeSpecifier, ImageWriteParam imageWriteParam) {
        PCXMetadata pCXMetadata = new PCXMetadata();
        pCXMetadata.bitsPerPixel = (byte) imageTypeSpecifier.getSampleModel().getSampleSize()[0];
        return pCXMetadata;
    }

    public void write(IIOMetadata iIOMetadata, IIOImage iIOImage, ImageWriteParam imageWriteParam) throws IOException {
        SampleModel sampleModel;
        ColorModel colorModel;
        PCXMetadata pCXMetadata;
        if (this.ios == null) {
            throw new IllegalStateException("output stream is null");
        }
        if (iIOImage == null) {
            throw new IllegalArgumentException("image is null");
        }
        clearAbortRequest();
        processImageStarted(0);
        if (imageWriteParam == null) {
            imageWriteParam = getDefaultWriteParam();
        }
        boolean hasRaster = iIOImage.hasRaster();
        this.sourceRegion = imageWriteParam.getSourceRegion();
        ColorModel colorModel2 = null;
        if (hasRaster) {
            Raster raster = iIOImage.getRaster();
            this.inputRaster = raster;
            sampleModel = raster.getSampleModel();
            colorModel = ImageUtil.createColorModel(null, sampleModel);
            Rectangle rectangle = this.sourceRegion;
            if (rectangle == null) {
                this.sourceRegion = this.inputRaster.getBounds();
            } else {
                this.sourceRegion = rectangle.intersection(this.inputRaster.getBounds());
            }
        } else {
            RenderedImage renderedImage = iIOImage.getRenderedImage();
            this.inputRaster = renderedImage.getData();
            SampleModel sampleModel2 = renderedImage.getSampleModel();
            ColorModel colorModel3 = renderedImage.getColorModel();
            Rectangle rectangle2 = new Rectangle(renderedImage.getMinX(), renderedImage.getMinY(), renderedImage.getWidth(), renderedImage.getHeight());
            Rectangle rectangle3 = this.sourceRegion;
            if (rectangle3 == null) {
                this.sourceRegion = rectangle2;
            } else {
                this.sourceRegion = rectangle3.intersection(rectangle2);
            }
            sampleModel = sampleModel2;
            colorModel = colorModel3;
        }
        if (this.sourceRegion.isEmpty()) {
            throw new IllegalArgumentException("source region is empty");
        }
        IIOMetadata metadata = iIOImage.getMetadata();
        ImageTypeSpecifier imageTypeSpecifier = new ImageTypeSpecifier(colorModel, sampleModel);
        if (metadata != null) {
            pCXMetadata = (PCXMetadata) convertImageMetadata(metadata, imageTypeSpecifier, imageWriteParam);
        } else {
            pCXMetadata = (PCXMetadata) getDefaultImageMetadata(imageTypeSpecifier, imageWriteParam);
        }
        this.scaleX = imageWriteParam.getSourceXSubsampling();
        this.scaleY = imageWriteParam.getSourceYSubsampling();
        int subsamplingXOffset = imageWriteParam.getSubsamplingXOffset();
        int subsamplingYOffset = imageWriteParam.getSubsamplingYOffset();
        sampleModel.getDataType();
        this.sourceRegion.translate(subsamplingXOffset, subsamplingYOffset);
        this.sourceRegion.width -= subsamplingXOffset;
        this.sourceRegion.height -= subsamplingYOffset;
        int i = this.sourceRegion.x / this.scaleX;
        int i2 = this.sourceRegion.y / this.scaleY;
        int i3 = this.sourceRegion.width;
        int i4 = this.scaleX;
        int i5 = ((i3 + i4) - 1) / i4;
        int i6 = this.sourceRegion.height;
        int i7 = this.scaleY;
        int i8 = ((i6 + i7) - 1) / i7;
        int i9 = this.sourceRegion.x % this.scaleX;
        int i10 = this.sourceRegion.y % this.scaleY;
        Rectangle rectangle4 = new Rectangle(i, i2, i5, i8);
        this.destinationRegion = rectangle4;
        rectangle4.equals(this.sourceRegion);
        int[] sourceBands = imageWriteParam.getSourceBands();
        int numBands = sampleModel.getNumBands();
        if (sourceBands != null) {
            sampleModel = sampleModel.createSubsetSampleModel(sourceBands);
            sampleModel.getNumBands();
        } else {
            int[] iArr = new int[numBands];
            for (int i11 = 0; i11 < numBands; i11++) {
                iArr[i11] = i11;
            }
            colorModel2 = colorModel;
        }
        this.ios.writeByte(10);
        this.ios.writeByte(5);
        this.ios.writeByte(1);
        int sampleSize = sampleModel.getSampleSize(0);
        this.ios.writeByte(sampleSize);
        this.ios.writeShort(this.destinationRegion.x);
        this.ios.writeShort(this.destinationRegion.y);
        this.ios.writeShort((this.destinationRegion.x + this.destinationRegion.width) - 1);
        this.ios.writeShort((this.destinationRegion.y + this.destinationRegion.height) - 1);
        this.ios.writeShort(pCXMetadata.hdpi);
        this.ios.writeShort(pCXMetadata.vdpi);
        this.ios.write(createSmallPalette(colorModel2));
        this.ios.writeByte(0);
        int numBands2 = sampleModel.getNumBands();
        this.colorPlanes = numBands2;
        this.ios.writeByte(numBands2);
        int i12 = (this.destinationRegion.width * sampleSize) / 8;
        this.bytesPerLine = i12;
        int i13 = i12 + (i12 % 2);
        this.bytesPerLine = i13;
        this.ios.writeShort(i13);
        if (colorModel2.getColorSpace().getType() == 6) {
            this.ios.writeShort(2);
        } else {
            this.ios.writeShort(1);
        }
        this.ios.writeShort(pCXMetadata.hsize);
        this.ios.writeShort(pCXMetadata.vsize);
        for (int i14 = 0; i14 < 54; i14++) {
            this.ios.writeByte(0);
        }
        int i15 = this.colorPlanes;
        if (i15 == 1 && sampleSize == 1) {
            write1Bit();
        } else if (i15 == 1 && sampleSize == 4) {
            write4Bit();
        } else {
            write8Bit();
        }
        if (this.colorPlanes == 1 && sampleSize == 8 && colorModel2.getColorSpace().getType() != 6) {
            this.ios.writeByte(12);
            this.ios.write(createLargePalette(colorModel2));
        }
        if (abortRequested()) {
            processWriteAborted();
        } else {
            processImageComplete();
        }
    }

    private void write4Bit() throws IOException {
        int[] iArr = new int[this.sourceRegion.width];
        int[] iArr2 = new int[this.bytesPerLine];
        int i = 0;
        while (i < this.sourceRegion.height) {
            this.inputRaster.getSamples(this.sourceRegion.x, this.sourceRegion.y + i, this.sourceRegion.width, 1, 0, iArr);
            int i2 = 0;
            int i3 = 0;
            boolean z = false;
            int i4 = 0;
            while (i2 < this.sourceRegion.width) {
                int i5 = i3 | (iArr[i2] & 15);
                if (z) {
                    iArr2[i4] = i5;
                    i4++;
                    i3 = 0;
                    z = false;
                } else {
                    i3 = i5 << 4;
                    z = true;
                }
                i2 += this.scaleX;
            }
            int i6 = iArr2[0];
            int i7 = 0;
            int i8 = 0;
            while (i7 < this.bytesPerLine) {
                int i9 = iArr2[i7];
                if (i9 != i6 || i8 == 63) {
                    writeRLE(i6, i8);
                    i6 = i9;
                    i8 = 1;
                } else {
                    i8++;
                }
                i7 += this.scaleX;
            }
            if (i8 >= 1) {
                writeRLE(i6, i8);
            }
            processImageProgress((i * 100.0f) / this.sourceRegion.height);
            i += this.scaleY;
        }
    }

    private void write1Bit() throws IOException {
        int[] iArr = new int[this.sourceRegion.width];
        int[] iArr2 = new int[this.bytesPerLine];
        int i = 0;
        while (i < this.sourceRegion.height) {
            this.inputRaster.getSamples(this.sourceRegion.x, this.sourceRegion.y + i, this.sourceRegion.width, 1, 0, iArr);
            int i2 = 128;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (i3 < this.sourceRegion.width) {
                if (iArr[i3] > 0) {
                    i4 |= i2;
                }
                if (i2 == 1) {
                    iArr2[i5] = i4;
                    i5++;
                    i4 = 0;
                    i2 = 128;
                } else {
                    i2 >>= 1;
                }
                i3 += this.scaleX;
            }
            int i6 = iArr2[0];
            int i7 = 0;
            int i8 = 0;
            while (i7 < this.bytesPerLine) {
                int i9 = iArr2[i7];
                if (i9 != i6 || i8 == 63) {
                    writeRLE(i6, i8);
                    i6 = i9;
                    i8 = 1;
                } else {
                    i8++;
                }
                i7 += this.scaleX;
            }
            if (i8 >= 1) {
                writeRLE(i6, i8);
            }
            processImageProgress((i * 100.0f) / this.sourceRegion.height);
            i += this.scaleY;
        }
    }

    private void write8Bit() throws IOException {
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) int.class, this.colorPlanes, this.bytesPerLine);
        int i = 0;
        while (i < this.sourceRegion.height) {
            for (int i2 = 0; i2 < this.colorPlanes; i2++) {
                this.inputRaster.getSamples(this.sourceRegion.x, this.sourceRegion.y + i, this.sourceRegion.width, 1, i2, iArr[i2]);
            }
            int i3 = iArr[0][0];
            int i4 = 0;
            for (int i5 = 0; i5 < this.colorPlanes; i5++) {
                int i6 = 0;
                while (i6 < this.bytesPerLine) {
                    int i7 = iArr[i5][i6];
                    if (i7 != i3 || i4 == 63) {
                        writeRLE(i3, i4);
                        i4 = 1;
                        i3 = i7;
                    } else {
                        i4++;
                    }
                    i6 += this.scaleX;
                }
            }
            if (i4 >= 1) {
                writeRLE(i3, i4);
            }
            processImageProgress((i * 100.0f) / this.sourceRegion.height);
            i += this.scaleY;
        }
    }

    private void writeRLE(int i, int i2) throws IOException {
        if (i2 == 1 && (i & 192) != 192) {
            this.ios.writeByte(i);
        } else {
            this.ios.writeByte(i2 | 192);
            this.ios.writeByte(i);
        }
    }

    private byte[] createSmallPalette(ColorModel colorModel) {
        byte[] bArr = new byte[48];
        if (!(colorModel instanceof IndexColorModel)) {
            return bArr;
        }
        IndexColorModel indexColorModel = (IndexColorModel) colorModel;
        if (indexColorModel.getMapSize() > 16) {
            return bArr;
        }
        int i = 0;
        int i2 = 0;
        while (i < indexColorModel.getMapSize()) {
            int i3 = i2 + 1;
            bArr[i2] = (byte) indexColorModel.getRed(i);
            int i4 = i3 + 1;
            bArr[i3] = (byte) indexColorModel.getGreen(i);
            bArr[i4] = (byte) indexColorModel.getBlue(i);
            i++;
            i2 = i4 + 1;
        }
        return bArr;
    }

    private byte[] createLargePalette(ColorModel colorModel) {
        byte[] bArr = new byte[ValidationPath.MAX_PATH_LENGTH_BYTES];
        if (!(colorModel instanceof IndexColorModel)) {
            return bArr;
        }
        IndexColorModel indexColorModel = (IndexColorModel) colorModel;
        int i = 0;
        int i2 = 0;
        while (i < indexColorModel.getMapSize()) {
            int i3 = i2 + 1;
            bArr[i2] = (byte) indexColorModel.getRed(i);
            int i4 = i3 + 1;
            bArr[i3] = (byte) indexColorModel.getGreen(i);
            bArr[i4] = (byte) indexColorModel.getBlue(i);
            i++;
            i2 = i4 + 1;
        }
        return bArr;
    }
}
