package com.github.jaiimageio.impl.plugins.bmp;

import com.github.jaiimageio.impl.common.ImageUtil;
import com.github.jaiimageio.plugins.bmp.BMPImageWriteParam;
import java.awt.Rectangle;
import java.awt.image.BandedSampleModel;
import java.awt.image.ColorModel;
import java.awt.image.ComponentSampleModel;
import java.awt.image.DataBuffer;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.MultiPixelPackedSampleModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.Iterator;
import javax.imageio.IIOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.event.IIOWriteProgressListener;
import javax.imageio.event.IIOWriteWarningListener;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageOutputStream;

/* loaded from: classes3.dex */
public class BMPImageWriter extends ImageWriter implements BMPConstants {
    private int[] bitMasks;
    private int[] bitPos;
    private byte[] bpixels;
    private int compImageSize;
    private int compressionType;
    private ByteArrayOutputStream embedded_stream;
    private int h;
    private int[] ipixels;
    private boolean isTopDown;
    private short[] spixels;
    private ImageOutputStream stream;
    private int w;

    private int firstLowBit(int i) {
        int i2 = 0;
        while ((i & 1) == 0) {
            i2++;
            i >>>= 1;
        }
        return i2;
    }

    private int roundBpp(int i) {
        if (i <= 8) {
            return 8;
        }
        if (i <= 16) {
            return 16;
        }
        return i <= 24 ? 24 : 32;
    }

    public boolean canWriteRasters() {
        return true;
    }

    public IIOMetadata convertStreamMetadata(IIOMetadata iIOMetadata, ImageWriteParam imageWriteParam) {
        return null;
    }

    public IIOMetadata getDefaultStreamMetadata(ImageWriteParam imageWriteParam) {
        return null;
    }

    public BMPImageWriter(ImageWriterSpi imageWriterSpi) {
        super(imageWriterSpi);
        this.stream = null;
        this.embedded_stream = null;
        this.compImageSize = 0;
    }

    public void setOutput(Object obj) {
        super.setOutput(obj);
        if (obj != null) {
            if (!(obj instanceof ImageOutputStream)) {
                throw new IllegalArgumentException(I18N.getString("BMPImageWriter0"));
            }
            ImageOutputStream imageOutputStream = (ImageOutputStream) obj;
            this.stream = imageOutputStream;
            imageOutputStream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
            return;
        }
        this.stream = null;
    }

    public ImageWriteParam getDefaultWriteParam() {
        return new BMPImageWriteParam();
    }

    public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageTypeSpecifier, ImageWriteParam imageWriteParam) {
        BMPMetadata bMPMetadata = new BMPMetadata();
        bMPMetadata.initialize(imageTypeSpecifier.getColorModel(), imageTypeSpecifier.getSampleModel(), imageWriteParam);
        return bMPMetadata;
    }

    public IIOMetadata convertImageMetadata(IIOMetadata iIOMetadata, ImageTypeSpecifier imageTypeSpecifier, ImageWriteParam imageWriteParam) {
        BMPMetadata bMPMetadata;
        if (iIOMetadata == null) {
            throw new IllegalArgumentException("inData == null!");
        }
        if (imageTypeSpecifier == null) {
            throw new IllegalArgumentException("imageType == null!");
        }
        if (iIOMetadata instanceof BMPMetadata) {
            bMPMetadata = (BMPMetadata) ((BMPMetadata) iIOMetadata).clone();
        } else {
            try {
                bMPMetadata = new BMPMetadata(iIOMetadata);
            } catch (IIOInvalidTreeException unused) {
                bMPMetadata = new BMPMetadata();
            }
        }
        bMPMetadata.initialize(imageTypeSpecifier.getColorModel(), imageTypeSpecifier.getSampleModel(), imageWriteParam);
        return bMPMetadata;
    }

    public void write(IIOMetadata iIOMetadata, IIOImage iIOImage, ImageWriteParam imageWriteParam) throws IOException {
        SampleModel sampleModel;
        ColorModel colorModel;
        Rectangle intersection;
        RenderedImage renderedImage;
        Raster raster;
        BMPMetadata bMPMetadata;
        int[] iArr;
        int i;
        ColorModel colorModel2;
        boolean z;
        int i2;
        int[] iArr2;
        RenderedImage renderedImage2;
        int[] iArr3;
        boolean z2;
        int i3;
        int i4;
        int i5;
        int i6;
        BMPMetadata bMPMetadata2;
        int i7;
        boolean z3;
        int i8;
        byte[] bArr;
        byte[] bArr2;
        byte[] bArr3;
        int i9;
        byte[] bArr4;
        boolean z4;
        IndexColorModel indexColorModel;
        boolean z5;
        int i10;
        boolean z6;
        long j;
        int i11;
        int i12;
        RenderedImage renderedImage3;
        int i13;
        int i14;
        int i15;
        int i16;
        long j2;
        int i17;
        int i18;
        int i19;
        int[] iArr4;
        int i20;
        int offset;
        int i21;
        boolean z7;
        int i22;
        int i23;
        int i24;
        int i25;
        if (this.stream == null) {
            throw new IllegalStateException(I18N.getString("BMPImageWriter7"));
        }
        if (iIOImage == null) {
            throw new IllegalArgumentException(I18N.getString("BMPImageWriter8"));
        }
        clearAbortRequest();
        processImageStarted(0);
        ImageWriteParam defaultWriteParam = imageWriteParam == null ? getDefaultWriteParam() : imageWriteParam;
        BMPImageWriteParam bMPImageWriteParam = (BMPImageWriteParam) defaultWriteParam;
        boolean hasRaster = iIOImage.hasRaster();
        Rectangle sourceRegion = defaultWriteParam.getSourceRegion();
        this.compImageSize = 0;
        if (hasRaster) {
            Raster raster2 = iIOImage.getRaster();
            sampleModel = raster2.getSampleModel();
            colorModel = ImageUtil.createColorModel(null, sampleModel);
            if (sourceRegion == null) {
                intersection = raster2.getBounds();
            } else {
                intersection = sourceRegion.intersection(raster2.getBounds());
            }
            raster = raster2;
            renderedImage = null;
        } else {
            RenderedImage renderedImage4 = iIOImage.getRenderedImage();
            sampleModel = renderedImage4.getSampleModel();
            colorModel = renderedImage4.getColorModel();
            Rectangle rectangle = new Rectangle(renderedImage4.getMinX(), renderedImage4.getMinY(), renderedImage4.getWidth(), renderedImage4.getHeight());
            if (sourceRegion == null) {
                renderedImage = renderedImage4;
                intersection = rectangle;
            } else {
                intersection = sourceRegion.intersection(rectangle);
                renderedImage = renderedImage4;
            }
            raster = null;
        }
        IIOMetadata metadata = iIOImage.getMetadata();
        ImageTypeSpecifier imageTypeSpecifier = new ImageTypeSpecifier(colorModel, sampleModel);
        if (metadata != null) {
            bMPMetadata = (BMPMetadata) convertImageMetadata(metadata, imageTypeSpecifier, defaultWriteParam);
        } else {
            bMPMetadata = (BMPMetadata) getDefaultImageMetadata(imageTypeSpecifier, defaultWriteParam);
        }
        BMPMetadata bMPMetadata3 = bMPMetadata;
        if (intersection.isEmpty()) {
            throw new RuntimeException(I18N.getString("BMPImageWrite0"));
        }
        int sourceXSubsampling = defaultWriteParam.getSourceXSubsampling();
        int sourceYSubsampling = defaultWriteParam.getSourceYSubsampling();
        int subsamplingXOffset = defaultWriteParam.getSubsamplingXOffset();
        int subsamplingYOffset = defaultWriteParam.getSubsamplingYOffset();
        int dataType = sampleModel.getDataType();
        intersection.translate(subsamplingXOffset, subsamplingYOffset);
        intersection.width -= subsamplingXOffset;
        intersection.height -= subsamplingYOffset;
        int i26 = intersection.x / sourceXSubsampling;
        int i27 = intersection.y / sourceYSubsampling;
        Raster raster3 = raster;
        this.w = ((intersection.width + sourceXSubsampling) - 1) / sourceXSubsampling;
        this.h = ((intersection.height + sourceYSubsampling) - 1) / sourceYSubsampling;
        int i28 = intersection.x % sourceXSubsampling;
        int i29 = intersection.y % sourceYSubsampling;
        ColorModel colorModel3 = colorModel;
        boolean equals = new Rectangle(i26, i27, this.w, this.h).equals(intersection);
        int[] sourceBands = defaultWriteParam.getSourceBands();
        int numBands = sampleModel.getNumBands();
        if (sourceBands != null) {
            sampleModel = sampleModel.createSubsetSampleModel(sourceBands);
            numBands = sampleModel.getNumBands();
            iArr = sourceBands;
            i = i27;
            colorModel2 = null;
            z = false;
        } else {
            int[] iArr5 = new int[numBands];
            for (int i30 = 0; i30 < numBands; i30++) {
                iArr5[i30] = i30;
            }
            iArr = iArr5;
            i = i27;
            colorModel2 = colorModel3;
            z = true;
        }
        if (sampleModel instanceof ComponentSampleModel) {
            iArr3 = ((ComponentSampleModel) sampleModel).getBandOffsets();
            i2 = sourceXSubsampling;
            if (sampleModel instanceof BandedSampleModel) {
                renderedImage2 = renderedImage;
                iArr2 = iArr;
                z2 = false;
            } else {
                int i31 = 0;
                boolean z8 = true;
                while (i31 < iArr3.length) {
                    int[] iArr6 = iArr;
                    z8 &= iArr3[i31] == (iArr3.length - i31) + (-1);
                    i31++;
                    iArr = iArr6;
                }
                iArr2 = iArr;
                z2 = z8;
                renderedImage2 = renderedImage;
            }
        } else {
            i2 = sourceXSubsampling;
            iArr2 = iArr;
            if (sampleModel instanceof SinglePixelPackedSampleModel) {
                int[] bitOffsets = ((SinglePixelPackedSampleModel) sampleModel).getBitOffsets();
                int i32 = 0;
                z2 = true;
                while (i32 < bitOffsets.length - 1) {
                    int i33 = bitOffsets[i32];
                    i32++;
                    RenderedImage renderedImage5 = renderedImage;
                    z2 &= i33 > bitOffsets[i32];
                    renderedImage = renderedImage5;
                }
                renderedImage2 = renderedImage;
                iArr3 = null;
            } else {
                renderedImage2 = renderedImage;
                iArr3 = null;
                z2 = true;
            }
        }
        if (iArr3 == null) {
            iArr3 = new int[numBands];
            for (int i34 = 0; i34 < numBands; i34++) {
                iArr3[i34] = i34;
            }
        }
        int[] iArr7 = iArr3;
        boolean z9 = equals & z2;
        int[] sampleSize = sampleModel.getSampleSize();
        int i35 = this.w * numBands;
        int compressionMode = bMPImageWriteParam.getCompressionMode();
        if (compressionMode == 1) {
            this.compressionType = getPreferredCompressionType(colorModel2, sampleModel);
        } else if (compressionMode == 2) {
            this.compressionType = getCompressionType(bMPImageWriteParam.getCompressionType());
        } else if (compressionMode == 3) {
            this.compressionType = bMPMetadata3.compression;
        } else {
            this.compressionType = 0;
        }
        if (!canEncodeImage(this.compressionType, colorModel2, sampleModel)) {
            if (defaultWriteParam.getCompressionMode() == 2) {
                throw new IIOException("Image can not be encoded with compression type " + compressionTypeNames[this.compressionType]);
            }
            this.compressionType = getPreferredCompressionType(colorModel2, sampleModel);
        }
        if (this.compressionType == 3) {
            int dataTypeSize = DataBuffer.getDataTypeSize(sampleModel.getDataType());
            if (dataTypeSize == 16 || dataTypeSize == 32) {
                z7 = z9;
                i22 = dataTypeSize;
            } else {
                i22 = 32;
                z7 = false;
            }
            int i36 = ((this.w * i22) + 7) >> 3;
            byte[] bArr5 = new byte[3];
            byte[] bArr6 = new byte[3];
            byte[] bArr7 = new byte[3];
            i3 = i26;
            byte[] bArr8 = new byte[3];
            if (i22 != 16) {
                i23 = 65280;
                i24 = 255;
                i25 = 16711680;
            } else if (colorModel2 instanceof DirectColorModel) {
                DirectColorModel directColorModel = (DirectColorModel) colorModel2;
                i25 = directColorModel.getRedMask();
                int greenMask = directColorModel.getGreenMask();
                i24 = directColorModel.getBlueMask();
                i23 = greenMask;
            } else {
                throw new IOException("Image can not be encoded with compression type " + compressionTypeNames[this.compressionType]);
            }
            int i37 = i22;
            ColorModel colorModel4 = colorModel2;
            int i38 = i24;
            int i39 = numBands;
            i5 = i;
            i6 = i2;
            i4 = dataType;
            bMPMetadata2 = bMPMetadata3;
            writeMaskToPalette(i25, 0, bArr5, bArr6, bArr7, bArr8);
            writeMaskToPalette(i23, 1, bArr5, bArr6, bArr7, bArr8);
            writeMaskToPalette(i38, 2, bArr5, bArr6, bArr7, bArr8);
            if (!z7) {
                this.bitMasks = r1;
                int i40 = i23;
                int[] iArr8 = {i25, i40, i38};
                this.bitPos = r1;
                int[] iArr9 = {firstLowBit(i25)};
                this.bitPos[1] = firstLowBit(i40);
                this.bitPos[2] = firstLowBit(i38);
            }
            z3 = z7;
            bArr4 = bArr6;
            bArr3 = bArr5;
            i35 = i36;
            bArr = bArr7;
            i8 = i37;
            i9 = 3;
            z4 = true;
            indexColorModel = colorModel4 instanceof IndexColorModel ? (IndexColorModel) colorModel4 : null;
            bArr2 = bArr8;
            i7 = i39;
        } else {
            ColorModel colorModel5 = colorModel2;
            int i41 = numBands;
            i3 = i26;
            i4 = dataType;
            i5 = i;
            i6 = i2;
            bMPMetadata2 = bMPMetadata3;
            if (colorModel5 instanceof IndexColorModel) {
                IndexColorModel indexColorModel2 = (IndexColorModel) colorModel5;
                int mapSize = indexColorModel2.getMapSize();
                if (mapSize <= 2) {
                    i35 = (this.w + 7) >> 3;
                    i9 = mapSize;
                    z6 = true;
                    z5 = true;
                    i10 = 1;
                } else if (mapSize <= 16) {
                    z5 = true;
                    i35 = (this.w + 1) >> 1;
                    i9 = mapSize;
                    z6 = true;
                    i10 = 4;
                } else {
                    z5 = true;
                    if (mapSize <= 256) {
                        i10 = 8;
                        i9 = mapSize;
                        z6 = true;
                    } else {
                        i35 = this.w * 3;
                        i10 = 24;
                        z6 = false;
                        i9 = 0;
                    }
                }
                if (z6 == z5) {
                    byte[] bArr9 = new byte[i9];
                    byte[] bArr10 = new byte[i9];
                    byte[] bArr11 = new byte[i9];
                    indexColorModel2.getReds(bArr9);
                    indexColorModel2.getGreens(bArr10);
                    indexColorModel2.getBlues(bArr11);
                    z3 = z9;
                    indexColorModel = indexColorModel2;
                    z4 = z6;
                    bArr3 = bArr9;
                    bArr4 = bArr10;
                    bArr = bArr11;
                    i8 = i10;
                    i7 = i41;
                    bArr2 = null;
                } else {
                    z3 = z9;
                    indexColorModel = indexColorModel2;
                    z4 = z6;
                    i8 = i10;
                    i7 = i41;
                    bArr = null;
                    bArr2 = null;
                    bArr3 = null;
                    bArr4 = null;
                }
            } else {
                i7 = i41;
                if (i7 == 1) {
                    int i42 = sampleSize[0];
                    i35 = ((this.w * i42) + 7) >> 3;
                    bArr3 = new byte[256];
                    bArr4 = new byte[256];
                    byte[] bArr12 = new byte[256];
                    int i43 = 0;
                    for (int i44 = 256; i43 < i44; i44 = 256) {
                        byte b = (byte) i43;
                        bArr3[i43] = b;
                        bArr4[i43] = b;
                        bArr12[i43] = b;
                        i43++;
                    }
                    z3 = z9;
                    bArr = bArr12;
                    i8 = i42;
                    bArr2 = null;
                    i9 = 256;
                    z4 = true;
                } else {
                    if ((sampleModel instanceof SinglePixelPackedSampleModel) && z) {
                        int i45 = 0;
                        for (int i46 : sampleModel.getSampleSize()) {
                            i45 += i46;
                        }
                        int roundBpp = roundBpp(i45);
                        if (roundBpp != DataBuffer.getDataTypeSize(sampleModel.getDataType())) {
                            z9 = false;
                        }
                        i35 = ((this.w * roundBpp) + 7) >> 3;
                        z3 = z9;
                        i8 = roundBpp;
                    } else {
                        z3 = z9;
                        i8 = 24;
                    }
                    bArr = null;
                    bArr2 = null;
                    bArr3 = null;
                    i9 = 0;
                    bArr4 = null;
                    z4 = false;
                }
                indexColorModel = null;
            }
        }
        int i47 = bMPMetadata2.xPixelsPerMeter;
        boolean z10 = z3;
        int i48 = bMPMetadata2.yPixelsPerMeter;
        int i49 = bMPMetadata2.colorsUsed > 0 ? bMPMetadata2.colorsUsed : i9;
        int i50 = i35 % 4;
        if (i50 != 0) {
            i50 = 4 - i50;
        }
        int i51 = i7;
        int i52 = (i9 * 4) + 54;
        int i53 = i50;
        int i54 = this.h * (i35 + i50);
        int i55 = i35;
        int i56 = i54 + i52;
        byte[] bArr13 = bArr;
        byte[] bArr14 = bArr2;
        long streamPosition = this.stream.getStreamPosition();
        if (defaultWriteParam instanceof BMPImageWriteParam) {
            this.isTopDown = bMPImageWriteParam.isTopDown();
            int i57 = this.compressionType;
            j = streamPosition;
            if (i57 != 0 && i57 != 3) {
                this.isTopDown = false;
            }
        } else {
            j = streamPosition;
            this.isTopDown = false;
        }
        writeFileHeader(i56, i52);
        writeInfoHeader(40, i8);
        this.stream.writeInt(this.compressionType);
        this.stream.writeInt(i54);
        this.stream.writeInt(i47);
        this.stream.writeInt(i48);
        this.stream.writeInt(i49);
        this.stream.writeInt(i9);
        if (z4) {
            if (this.compressionType == 3) {
                int i58 = 0;
                for (int i59 = 3; i58 < i59; i59 = 3) {
                    this.stream.writeInt((bArr14[i58] & 255) + ((bArr3[i58] & 255) * 256) + ((bArr4[i58] & 255) * 65536) + ((bArr13[i58] & 255) * 16777216));
                    i58++;
                }
            } else {
                for (int i60 = 0; i60 < i9; i60++) {
                    this.stream.writeByte(bArr13[i60]);
                    this.stream.writeByte(bArr4[i60]);
                    this.stream.writeByte(bArr3[i60]);
                    this.stream.writeByte(0);
                }
            }
        }
        int i61 = this.w * i51;
        int i62 = i6;
        int[] iArr10 = new int[i61 * i62];
        int i63 = i55;
        this.bpixels = new byte[i63];
        int i64 = this.compressionType;
        if (i64 == 4 || i64 == 5) {
            long j3 = j;
            this.embedded_stream = new ByteArrayOutputStream();
            writeEmbedded(iIOImage, bMPImageWriteParam);
            this.embedded_stream.flush();
            int size = this.embedded_stream.size();
            long streamPosition2 = this.stream.getStreamPosition();
            this.stream.seek(j3);
            writeSize(i52 + size, 2);
            this.stream.seek(j3);
            writeSize(size, 34);
            this.stream.seek(streamPosition2);
            this.stream.write(this.embedded_stream.toByteArray());
            this.embedded_stream = null;
            if (abortRequested()) {
                processWriteAborted();
                return;
            }
            processImageComplete();
            ImageOutputStream imageOutputStream = this.stream;
            imageOutputStream.flushBefore(imageOutputStream.getStreamPosition());
            return;
        }
        int i65 = iArr7[0];
        for (int i66 = 1; i66 < iArr7.length; i66++) {
            if (iArr7[i66] > i65) {
                i65 = iArr7[i66];
            }
        }
        int i67 = i65 + 1;
        int[] iArr11 = new int[i67];
        if (z10 && z) {
            i63 /= DataBuffer.getDataTypeSize(i4) >> 3;
        }
        int i68 = 0;
        while (i68 < this.h && !abortRequested()) {
            int i69 = i5 + i68;
            if (this.isTopDown) {
                i11 = 1;
            } else {
                i11 = 1;
                i69 = ((i5 + this.h) - i68) - 1;
            }
            Rectangle rectangle2 = new Rectangle((i3 * i62) + i28, (i69 * sourceYSubsampling) + i29, ((this.w - i11) * i62) + i11, i11);
            RenderedImage renderedImage6 = renderedImage2;
            Raster data = !hasRaster ? renderedImage6.getData(rectangle2) : raster3;
            if (z10 && z) {
                ComponentSampleModel sampleModel2 = data.getSampleModel();
                int sampleModelTranslateX = rectangle2.x - data.getSampleModelTranslateX();
                int sampleModelTranslateY = rectangle2.y - data.getSampleModelTranslateY();
                int i70 = i68;
                if (sampleModel2 instanceof ComponentSampleModel) {
                    ComponentSampleModel componentSampleModel = sampleModel2;
                    i12 = i52;
                    int offset2 = componentSampleModel.getOffset(sampleModelTranslateX, sampleModelTranslateY, 0);
                    renderedImage3 = renderedImage6;
                    for (int i71 = 1; i71 < componentSampleModel.getNumBands(); i71++) {
                        if (offset2 > componentSampleModel.getOffset(sampleModelTranslateX, sampleModelTranslateY, i71)) {
                            offset2 = componentSampleModel.getOffset(sampleModelTranslateX, sampleModelTranslateY, i71);
                        }
                    }
                    offset = offset2;
                } else {
                    i12 = i52;
                    renderedImage3 = renderedImage6;
                    if (sampleModel2 instanceof MultiPixelPackedSampleModel) {
                        offset = ((MultiPixelPackedSampleModel) sampleModel2).getOffset(sampleModelTranslateX, sampleModelTranslateY);
                    } else {
                        offset = sampleModel2 instanceof SinglePixelPackedSampleModel ? ((SinglePixelPackedSampleModel) sampleModel2).getOffset(sampleModelTranslateX, sampleModelTranslateY) : 0;
                    }
                }
                int i72 = this.compressionType;
                if (i72 == 0 || i72 == 3) {
                    if (i4 != 0) {
                        i13 = i4;
                        if (i13 == 1) {
                            this.stream.writeShorts(data.getDataBuffer().getData(), offset, i63);
                        } else if (i13 == 2) {
                            this.stream.writeShorts(data.getDataBuffer().getData(), offset, i63);
                        } else if (i13 == 3) {
                            this.stream.writeInts(data.getDataBuffer().getData(), offset, i63);
                        }
                    } else {
                        i13 = i4;
                        this.stream.write(data.getDataBuffer().getData(), offset, i63);
                    }
                    i21 = i53;
                    for (int i73 = 0; i73 < i21; i73++) {
                        this.stream.writeByte(0);
                    }
                } else {
                    if (i72 == 2) {
                        byte[] bArr15 = this.bpixels;
                        if (bArr15 == null || bArr15.length < i61) {
                            this.bpixels = new byte[i61];
                        }
                        data.getPixels(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height, iArr10);
                        for (int i74 = 0; i74 < i61; i74++) {
                            this.bpixels[i74] = (byte) iArr10[i74];
                        }
                        encodeRLE4(this.bpixels, i61);
                    } else if (i72 == 1) {
                        byte[] bArr16 = this.bpixels;
                        if (bArr16 == null || bArr16.length < i61) {
                            this.bpixels = new byte[i61];
                        }
                        data.getPixels(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height, iArr10);
                        for (int i75 = 0; i75 < i61; i75++) {
                            this.bpixels[i75] = (byte) iArr10[i75];
                        }
                        encodeRLE8(this.bpixels, i61);
                    }
                    i21 = i53;
                    i13 = i4;
                }
                i19 = i63;
                iArr4 = iArr11;
                i20 = i8;
                i15 = i65;
                i16 = i62;
                i17 = i70;
                i14 = i51;
                j2 = j;
                i18 = i21;
            } else {
                int i76 = i68;
                i12 = i52;
                renderedImage3 = renderedImage6;
                int i77 = i53;
                i13 = i4;
                data.getPixels(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height, iArr10);
                if (i62 != 1 || i65 != i51 - 1) {
                    int i78 = 0;
                    int i79 = 0;
                    int i80 = 0;
                    while (i78 < this.w) {
                        System.arraycopy(iArr10, i79, iArr11, 0, i67);
                        int i81 = i51;
                        for (int i82 = 0; i82 < i81; i82++) {
                            iArr10[i80 + i82] = iArr11[iArr2[i82]];
                        }
                        i78++;
                        i79 += i62 * i81;
                        i80 += i81;
                        i51 = i81;
                    }
                }
                i14 = i51;
                i15 = i65;
                i16 = i62;
                j2 = j;
                i17 = i76;
                i18 = i77;
                i19 = i63;
                iArr4 = iArr11;
                i20 = i8;
                writePixels(0, i61, i8, iArr10, i18, i14, indexColorModel);
            }
            int i83 = i17;
            processImageProgress((i83 / this.h) * 100.0f);
            i68 = i83 + 1;
            i4 = i13;
            j = j2;
            i51 = i14;
            i52 = i12;
            i65 = i15;
            i63 = i19;
            iArr11 = iArr4;
            i8 = i20;
            i62 = i16;
            i53 = i18;
            renderedImage2 = renderedImage3;
        }
        int i84 = i52;
        long j4 = j;
        int i85 = this.compressionType;
        if (i85 == 2 || i85 == 1) {
            this.stream.writeByte(0);
            this.stream.writeByte(1);
            incCompImageSize(2);
            int i86 = this.compImageSize;
            long streamPosition3 = this.stream.getStreamPosition();
            this.stream.seek(j4);
            writeSize(i86 + i84, 2);
            this.stream.seek(j4);
            writeSize(i86, 34);
            this.stream.seek(streamPosition3);
        }
        if (abortRequested()) {
            processWriteAborted();
            return;
        }
        processImageComplete();
        ImageOutputStream imageOutputStream2 = this.stream;
        imageOutputStream2.flushBefore(imageOutputStream2.getStreamPosition());
    }

    private void writePixels(int i, int i2, int i3, int[] iArr, int i4, int i5, IndexColorModel indexColorModel) throws IOException {
        if (i3 == 1) {
            int i6 = i;
            int i7 = 0;
            int i8 = 0;
            while (i7 < i2 / 8) {
                byte[] bArr = this.bpixels;
                int i9 = i6 + 1;
                int i10 = i9 + 1;
                int i11 = (iArr[i6] << 7) | (iArr[i9] << 6);
                int i12 = i10 + 1;
                int i13 = i11 | (iArr[i10] << 5);
                int i14 = i12 + 1;
                int i15 = i13 | (iArr[i12] << 4);
                int i16 = i14 + 1;
                int i17 = i15 | (iArr[i14] << 3);
                int i18 = i16 + 1;
                int i19 = i17 | (iArr[i16] << 2);
                int i20 = i18 + 1;
                bArr[i8] = (byte) (i19 | (iArr[i18] << 1) | iArr[i20]);
                i7++;
                i8++;
                i6 = i20 + 1;
            }
            int i21 = i2 % 8;
            if (i21 > 0) {
                int i22 = 0;
                int i23 = 0;
                while (i22 < i21) {
                    i23 |= iArr[i6] << (7 - i22);
                    i22++;
                    i6++;
                }
                this.bpixels[i8] = (byte) i23;
            }
            this.stream.write(this.bpixels, 0, (i2 + 7) / 8);
        } else if (i3 != 4) {
            if (i3 != 8) {
                if (i3 == 16) {
                    if (this.spixels == null) {
                        this.spixels = new short[i2 / i5];
                    }
                    int i24 = 0;
                    int i25 = 0;
                    while (i24 < i2) {
                        short[] sArr = this.spixels;
                        sArr[i25] = 0;
                        if (this.compressionType == 0) {
                            sArr[i25] = (short) (((iArr[i24] & 31) << 10) | ((iArr[i24 + 1] & 31) << 5) | (iArr[i24 + 2] & 31));
                            i24 += 3;
                        } else {
                            int i26 = 0;
                            while (i26 < i5) {
                                short[] sArr2 = this.spixels;
                                sArr2[i25] = (short) (sArr2[i25] | ((iArr[i24] << this.bitPos[i26]) & this.bitMasks[i26]));
                                i26++;
                                i24++;
                            }
                        }
                        i25++;
                    }
                    ImageOutputStream imageOutputStream = this.stream;
                    short[] sArr3 = this.spixels;
                    imageOutputStream.writeShorts(sArr3, 0, sArr3.length);
                } else if (i3 != 24) {
                    if (i3 == 32) {
                        if (this.ipixels == null) {
                            this.ipixels = new int[i2 / i5];
                        }
                        if (i5 == 3) {
                            int i27 = 0;
                            int i28 = 0;
                            while (i27 < i2) {
                                int[] iArr2 = this.ipixels;
                                iArr2[i28] = 0;
                                if (this.compressionType == 0) {
                                    iArr2[i28] = ((iArr[i27 + 2] & 255) << 16) | ((iArr[i27 + 1] & 255) << 8) | (iArr[i27] & 255);
                                    i27 += 3;
                                } else {
                                    int i29 = 0;
                                    while (i29 < i5) {
                                        int[] iArr3 = this.ipixels;
                                        iArr3[i28] = iArr3[i28] | ((iArr[i27] << this.bitPos[i29]) & this.bitMasks[i29]);
                                        i29++;
                                        i27++;
                                    }
                                }
                                i28++;
                            }
                        } else {
                            for (int i30 = 0; i30 < i2; i30++) {
                                if (indexColorModel != null) {
                                    this.ipixels[i30] = indexColorModel.getRGB(iArr[i30]);
                                } else {
                                    this.ipixels[i30] = (iArr[i30] << 16) | (iArr[i30] << 8) | iArr[i30];
                                }
                            }
                        }
                        ImageOutputStream imageOutputStream2 = this.stream;
                        int[] iArr4 = this.ipixels;
                        imageOutputStream2.writeInts(iArr4, 0, iArr4.length);
                    }
                } else if (i5 == 3) {
                    int i31 = i;
                    int i32 = 0;
                    int i33 = 0;
                    while (i32 < i2) {
                        byte[] bArr2 = this.bpixels;
                        int i34 = i33 + 1;
                        bArr2[i33] = (byte) iArr[i31 + 2];
                        int i35 = i34 + 1;
                        bArr2[i34] = (byte) iArr[i31 + 1];
                        bArr2[i35] = (byte) iArr[i31];
                        i31 += 3;
                        i32 += 3;
                        i33 = i35 + 1;
                    }
                    this.stream.write(this.bpixels, 0, i2);
                } else {
                    int mapSize = indexColorModel.getMapSize();
                    byte[] bArr3 = new byte[mapSize];
                    byte[] bArr4 = new byte[mapSize];
                    byte[] bArr5 = new byte[mapSize];
                    indexColorModel.getReds(bArr3);
                    indexColorModel.getGreens(bArr4);
                    indexColorModel.getBlues(bArr5);
                    int i36 = i;
                    int i37 = 0;
                    int i38 = 0;
                    while (i37 < i2) {
                        int i39 = iArr[i36];
                        byte[] bArr6 = this.bpixels;
                        int i40 = i38 + 1;
                        bArr6[i38] = bArr5[i39];
                        int i41 = i40 + 1;
                        bArr6[i40] = bArr4[i39];
                        bArr6[i41] = bArr5[i39];
                        i36++;
                        i37++;
                        i38 = i41 + 1;
                    }
                    this.stream.write(this.bpixels, 0, i2 * 3);
                }
            } else if (this.compressionType == 1) {
                int i42 = i;
                int i43 = 0;
                while (i43 < i2) {
                    this.bpixels[i43] = (byte) iArr[i42];
                    i43++;
                    i42++;
                }
                encodeRLE8(this.bpixels, i2);
            } else {
                int i44 = i;
                int i45 = 0;
                while (i45 < i2) {
                    this.bpixels[i45] = (byte) iArr[i44];
                    i45++;
                    i44++;
                }
                this.stream.write(this.bpixels, 0, i2);
            }
        } else if (this.compressionType == 2) {
            byte[] bArr7 = new byte[i2];
            int i46 = i;
            int i47 = 0;
            while (i47 < i2) {
                bArr7[i47] = (byte) iArr[i46];
                i47++;
                i46++;
            }
            encodeRLE4(bArr7, i2);
        } else {
            int i48 = i;
            int i49 = 0;
            int i50 = 0;
            while (i49 < i2 / 2) {
                int i51 = i48 + 1;
                this.bpixels[i50] = (byte) ((iArr[i48] << 4) | iArr[i51]);
                i49++;
                i48 = i51 + 1;
                i50++;
            }
            if (i2 % 2 == 1) {
                this.bpixels[i50] = (byte) (iArr[i48] << 4);
            }
            this.stream.write(this.bpixels, 0, (i2 + 1) / 2);
        }
        int i52 = this.compressionType;
        if (i52 == 0 || i52 == 3) {
            for (int i53 = 0; i53 < i4; i53++) {
                this.stream.writeByte(0);
            }
        }
    }

    private void encodeRLE8(byte[] bArr, int i) throws IOException {
        byte b = bArr[0];
        byte[] bArr2 = new byte[256];
        int i2 = 0;
        int i3 = -1;
        int i4 = 1;
        while (true) {
            int i5 = i - 1;
            if (i2 >= i5) {
                return;
            }
            i2++;
            byte b2 = bArr[i2];
            if (b2 == b) {
                if (i3 >= 3) {
                    this.stream.writeByte(0);
                    this.stream.writeByte(i3);
                    incCompImageSize(2);
                    for (int i6 = 0; i6 < i3; i6++) {
                        this.stream.writeByte(bArr2[i6]);
                        incCompImageSize(1);
                    }
                    if (!isEven(i3)) {
                        this.stream.writeByte(0);
                        incCompImageSize(1);
                    }
                } else if (i3 > -1) {
                    for (int i7 = 0; i7 < i3; i7++) {
                        this.stream.writeByte(1);
                        this.stream.writeByte(bArr2[i7]);
                        incCompImageSize(2);
                    }
                }
                i4++;
                if (i4 == 256) {
                    this.stream.writeByte(i4 - 1);
                    this.stream.writeByte(b);
                    incCompImageSize(2);
                    i3 = -1;
                    i4 = 1;
                } else {
                    i3 = -1;
                }
            } else {
                if (i4 > 1) {
                    this.stream.writeByte(i4);
                    this.stream.writeByte(b);
                    incCompImageSize(2);
                } else if (i3 < 0) {
                    int i8 = i3 + 1;
                    bArr2[i8] = b;
                    i3 = i8 + 1;
                    bArr2[i3] = b2;
                } else if (i3 < 254) {
                    i3++;
                    bArr2[i3] = b2;
                } else {
                    this.stream.writeByte(0);
                    this.stream.writeByte(i3 + 1);
                    incCompImageSize(2);
                    for (int i9 = 0; i9 <= i3; i9++) {
                        this.stream.writeByte(bArr2[i9]);
                        incCompImageSize(1);
                    }
                    this.stream.writeByte(0);
                    incCompImageSize(1);
                    i3 = -1;
                }
                i4 = 1;
                b = b2;
            }
            if (i2 == i5) {
                if (i3 == -1) {
                    this.stream.writeByte(i4);
                    this.stream.writeByte(b);
                    incCompImageSize(2);
                    i4 = 1;
                } else if (i3 >= 2) {
                    this.stream.writeByte(0);
                    int i10 = i3 + 1;
                    this.stream.writeByte(i10);
                    incCompImageSize(2);
                    for (int i11 = 0; i11 <= i3; i11++) {
                        this.stream.writeByte(bArr2[i11]);
                        incCompImageSize(1);
                    }
                    if (!isEven(i10)) {
                        this.stream.writeByte(0);
                        incCompImageSize(1);
                    }
                } else if (i3 > -1) {
                    for (int i12 = 0; i12 <= i3; i12++) {
                        this.stream.writeByte(1);
                        this.stream.writeByte(bArr2[i12]);
                        incCompImageSize(2);
                    }
                }
                this.stream.writeByte(0);
                this.stream.writeByte(0);
                incCompImageSize(2);
            }
        }
    }

    private void encodeRLE4(byte[] bArr, int i) throws IOException {
        int i2;
        int i3;
        int i4;
        byte[] bArr2 = new byte[256];
        byte b = bArr[0];
        byte b2 = bArr[1];
        int i5 = 2;
        int i6 = 1;
        int i7 = 2;
        int i8 = -1;
        while (true) {
            int i9 = i - 2;
            if (i6 >= i9) {
                return;
            }
            int i10 = i6 + 1;
            byte b3 = bArr[i10];
            i6 = i10 + 1;
            byte b4 = bArr[i6];
            if (b3 == b) {
                if (i8 >= 4) {
                    this.stream.writeByte(0);
                    int i11 = i8 - 1;
                    this.stream.writeByte(i11);
                    incCompImageSize(i5);
                    int i12 = 0;
                    while (true) {
                        i4 = i8 - 2;
                        if (i12 >= i4) {
                            break;
                        }
                        this.stream.writeByte((byte) ((bArr2[i12] << 4) | bArr2[i12 + 1]));
                        incCompImageSize(1);
                        i12 += 2;
                    }
                    if (!isEven(i11)) {
                        this.stream.writeByte((bArr2[i4] << 4) | 0);
                        incCompImageSize(1);
                    }
                    if (!isEven((int) Math.ceil(i11 / 2))) {
                        this.stream.writeByte(0);
                        incCompImageSize(1);
                    }
                } else if (i8 > -1) {
                    this.stream.writeByte(2);
                    this.stream.writeByte((bArr2[0] << 4) | bArr2[1]);
                    incCompImageSize(2);
                }
                if (b4 == b2) {
                    i7 += 2;
                    if (i7 == 256) {
                        this.stream.writeByte(i7 - 1);
                        this.stream.writeByte((b << 4) | b2);
                        incCompImageSize(2);
                        if (i6 < i - 1) {
                            i6++;
                            i7 = 2;
                            i8 = -1;
                            byte b5 = b2;
                            b2 = bArr[i6];
                            b = b5;
                        } else {
                            this.stream.writeByte(1);
                            this.stream.writeByte((b2 << 4) | 0);
                            incCompImageSize(2);
                            i7 = -1;
                        }
                    }
                    i8 = -1;
                } else {
                    this.stream.writeByte(i7 + 1);
                    this.stream.writeByte((b << 4) | b2);
                    incCompImageSize(2);
                    if (i6 < i - 1) {
                        i6++;
                        b2 = bArr[i6];
                        b = b4;
                        i7 = 2;
                        i8 = -1;
                    } else {
                        this.stream.writeByte(1);
                        this.stream.writeByte((b4 << 4) | 0);
                        incCompImageSize(2);
                        b = b4;
                        i7 = -1;
                        i8 = -1;
                    }
                }
            } else {
                int i13 = i5;
                if (i7 > i13) {
                    this.stream.writeByte(i7);
                    this.stream.writeByte((b << 4) | b2);
                    incCompImageSize(i13);
                } else if (i8 < 0) {
                    int i14 = i8 + 1;
                    bArr2[i14] = b;
                    int i15 = i14 + 1;
                    bArr2[i15] = b2;
                    int i16 = i15 + 1;
                    bArr2[i16] = b3;
                    i8 = i16 + 1;
                    bArr2[i8] = b4;
                } else if (i8 < 253) {
                    int i17 = i8 + 1;
                    bArr2[i17] = b3;
                    i8 = i17 + 1;
                    bArr2[i8] = b4;
                } else {
                    this.stream.writeByte(0);
                    this.stream.writeByte(i8 + 1);
                    incCompImageSize(2);
                    for (int i18 = 0; i18 < i8; i18 += 2) {
                        this.stream.writeByte((byte) ((bArr2[i18] << 4) | bArr2[i18 + 1]));
                        incCompImageSize(1);
                    }
                    this.stream.writeByte(0);
                    incCompImageSize(1);
                    i8 = -1;
                }
                b = b3;
                b2 = b4;
                i7 = 2;
            }
            if (i6 >= i9) {
                int i19 = -1;
                if (i8 == -1) {
                    if (i7 >= 2) {
                        if (i6 == i9) {
                            i6++;
                            if (bArr[i6] == b) {
                                i7++;
                                this.stream.writeByte(i7);
                                this.stream.writeByte((b << 4) | b2);
                                i3 = 2;
                                incCompImageSize(2);
                            } else {
                                this.stream.writeByte(i7);
                                this.stream.writeByte((b << 4) | b2);
                                this.stream.writeByte(1);
                                this.stream.writeByte((bArr[i6] << 4) | 0);
                                byte b6 = bArr[i6];
                                incCompImageSize(4);
                                i2 = 2;
                                this.stream.writeByte(0);
                                this.stream.writeByte(0);
                                incCompImageSize(i2);
                                i5 = i2;
                            }
                        } else {
                            this.stream.writeByte(i7);
                            this.stream.writeByte((b << 4) | b2);
                            i3 = 2;
                            incCompImageSize(2);
                        }
                        i2 = i3;
                        this.stream.writeByte(0);
                        this.stream.writeByte(0);
                        incCompImageSize(i2);
                        i5 = i2;
                    } else {
                        i19 = -1;
                    }
                }
                if (i8 > i19) {
                    if (i6 == i9) {
                        i8++;
                        i6++;
                        bArr2[i8] = bArr[i6];
                    }
                    if (i8 >= 2) {
                        this.stream.writeByte(0);
                        int i20 = i8 + 1;
                        this.stream.writeByte(i20);
                        incCompImageSize(2);
                        for (int i21 = 0; i21 < i8; i21 += 2) {
                            this.stream.writeByte((byte) ((bArr2[i21] << 4) | bArr2[i21 + 1]));
                            incCompImageSize(1);
                        }
                        if (!isEven(i20)) {
                            this.stream.writeByte((bArr2[i8] << 4) | 0);
                            incCompImageSize(1);
                        }
                        if (!isEven((int) Math.ceil(i20 / 2))) {
                            this.stream.writeByte(0);
                            incCompImageSize(1);
                        }
                    } else {
                        if (i8 == 0) {
                            i2 = 2;
                            this.stream.writeByte(1);
                            this.stream.writeByte((bArr2[0] << 4) | 0);
                            incCompImageSize(2);
                        } else if (i8 == 1) {
                            i2 = 2;
                            this.stream.writeByte(2);
                            this.stream.writeByte((bArr2[0] << 4) | bArr2[1]);
                            incCompImageSize(2);
                        }
                        this.stream.writeByte(0);
                        this.stream.writeByte(0);
                        incCompImageSize(i2);
                        i5 = i2;
                    }
                }
                i2 = 2;
                this.stream.writeByte(0);
                this.stream.writeByte(0);
                incCompImageSize(i2);
                i5 = i2;
            } else {
                i5 = 2;
            }
        }
    }

    private synchronized void incCompImageSize(int i) {
        this.compImageSize += i;
    }

    private boolean isEven(int i) {
        return i % 2 == 0;
    }

    private void writeFileHeader(int i, int i2) throws IOException {
        this.stream.writeByte(66);
        this.stream.writeByte(77);
        this.stream.writeInt(i);
        this.stream.writeInt(0);
        this.stream.writeInt(i2);
    }

    private void writeInfoHeader(int i, int i2) throws IOException {
        this.stream.writeInt(i);
        this.stream.writeInt(this.w);
        if (this.isTopDown) {
            this.stream.writeInt(-this.h);
        } else {
            this.stream.writeInt(this.h);
        }
        this.stream.writeShort(1);
        this.stream.writeShort(i2);
    }

    private void writeSize(int i, int i2) throws IOException {
        this.stream.skipBytes(i2);
        this.stream.writeInt(i);
    }

    public void reset() {
        super.reset();
        this.stream = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getCompressionType(String str) {
        for (int i = 0; i < BMPConstants.compressionTypeNames.length; i++) {
            if (BMPConstants.compressionTypeNames[i].equals(str)) {
                return i;
            }
        }
        return 0;
    }

    private void writeEmbedded(IIOImage iIOImage, ImageWriteParam imageWriteParam) throws IOException {
        String str = this.compressionType == 4 ? "jpeg" : "png";
        Iterator imageWritersByFormatName = ImageIO.getImageWritersByFormatName(str);
        ImageWriter imageWriter = imageWritersByFormatName.hasNext() ? (ImageWriter) imageWritersByFormatName.next() : null;
        if (imageWriter != null) {
            if (this.embedded_stream == null) {
                throw new RuntimeException("No stream for writing embedded image!");
            }
            imageWriter.addIIOWriteProgressListener(new IIOWriteProgressAdapter() { // from class: com.github.jaiimageio.impl.plugins.bmp.BMPImageWriter.1
                @Override // com.github.jaiimageio.impl.plugins.bmp.BMPImageWriter.IIOWriteProgressAdapter
                public void imageProgress(ImageWriter imageWriter2, float f) {
                    BMPImageWriter.this.processImageProgress(f);
                }
            });
            imageWriter.addIIOWriteWarningListener(new IIOWriteWarningListener() { // from class: com.github.jaiimageio.impl.plugins.bmp.BMPImageWriter.2
                public void warningOccurred(ImageWriter imageWriter2, int i, String str2) {
                    BMPImageWriter.this.processWarningOccurred(i, str2);
                }
            });
            ImageOutputStream createImageOutputStream = ImageIO.createImageOutputStream(this.embedded_stream);
            imageWriter.setOutput(createImageOutputStream);
            ImageWriteParam defaultWriteParam = imageWriter.getDefaultWriteParam();
            defaultWriteParam.setDestinationOffset(imageWriteParam.getDestinationOffset());
            defaultWriteParam.setSourceBands(imageWriteParam.getSourceBands());
            defaultWriteParam.setSourceRegion(imageWriteParam.getSourceRegion());
            defaultWriteParam.setSourceSubsampling(imageWriteParam.getSourceXSubsampling(), imageWriteParam.getSourceYSubsampling(), imageWriteParam.getSubsamplingXOffset(), imageWriteParam.getSubsamplingYOffset());
            imageWriter.write((IIOMetadata) null, iIOImage, defaultWriteParam);
            createImageOutputStream.flush();
            return;
        }
        throw new RuntimeException(I18N.getString("BMPImageWrite5") + " " + str);
    }

    /* loaded from: classes3.dex */
    private class IIOWriteProgressAdapter implements IIOWriteProgressListener {
        public void imageComplete(ImageWriter imageWriter) {
        }

        public void imageProgress(ImageWriter imageWriter, float f) {
        }

        public void imageStarted(ImageWriter imageWriter, int i) {
        }

        public void thumbnailComplete(ImageWriter imageWriter) {
        }

        public void thumbnailProgress(ImageWriter imageWriter, float f) {
        }

        public void thumbnailStarted(ImageWriter imageWriter, int i, int i2) {
        }

        public void writeAborted(ImageWriter imageWriter) {
        }

        private IIOWriteProgressAdapter() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getPreferredCompressionType(ColorModel colorModel, SampleModel sampleModel) {
        return getPreferredCompressionType(new ImageTypeSpecifier(colorModel, sampleModel));
    }

    static int getPreferredCompressionType(ImageTypeSpecifier imageTypeSpecifier) {
        int bufferedImageType = imageTypeSpecifier.getBufferedImageType();
        return (bufferedImageType == 8 || bufferedImageType == 9) ? 3 : 0;
    }

    protected boolean canEncodeImage(int i, ColorModel colorModel, SampleModel sampleModel) {
        return canEncodeImage(i, new ImageTypeSpecifier(colorModel, sampleModel));
    }

    protected boolean canEncodeImage(int i, ImageTypeSpecifier imageTypeSpecifier) {
        boolean z;
        boolean z2;
        if (!getOriginatingProvider().canEncodeImage(imageTypeSpecifier)) {
            return false;
        }
        int pixelSize = imageTypeSpecifier.getColorModel().getPixelSize();
        int i2 = this.compressionType;
        if (i2 == 2 && pixelSize != 4) {
            return false;
        }
        if (i2 == 1 && pixelSize != 8) {
            return false;
        }
        if (pixelSize != 16) {
            return true;
        }
        SinglePixelPackedSampleModel sampleModel = imageTypeSpecifier.getSampleModel();
        if (sampleModel instanceof SinglePixelPackedSampleModel) {
            int[] sampleSize = sampleModel.getSampleSize();
            int i3 = 0;
            z = true;
            z2 = true;
            while (i3 < sampleSize.length) {
                z &= sampleSize[i3] == 5;
                z2 &= sampleSize[i3] == 5 || (i3 == 1 && sampleSize[i3] == 6);
                i3++;
            }
        } else {
            z = false;
            z2 = false;
        }
        int i4 = this.compressionType;
        return (i4 == 0 && z) || (i4 == 3 && z2);
    }

    protected void writeMaskToPalette(int i, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        bArr3[i2] = (byte) ((i >> 24) & 255);
        bArr2[i2] = (byte) ((i >> 16) & 255);
        bArr[i2] = (byte) ((i >> 8) & 255);
        bArr4[i2] = (byte) (i & 255);
    }
}
