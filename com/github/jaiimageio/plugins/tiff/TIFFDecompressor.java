package com.github.jaiimageio.plugins.tiff;

import com.github.jaiimageio.impl.common.BogusColorSpace;
import com.github.jaiimageio.impl.common.ImageUtil;
import com.github.jaiimageio.impl.common.SimpleCMYKColorSpace;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.ComponentSampleModel;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferFloat;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferShort;
import java.awt.image.DataBufferUShort;
import java.awt.image.IndexColorModel;
import java.awt.image.MultiPixelPackedSampleModel;
import java.awt.image.PixelInterleavedSampleModel;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import javax.imageio.IIOException;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import kotlin.UShort;

/* loaded from: classes3.dex */
public abstract class TIFFDecompressor {
    private static final boolean DEBUG = false;
    protected int activeSrcHeight;
    protected int activeSrcMinX;
    protected int activeSrcMinY;
    protected int activeSrcWidth;
    boolean adjustBitDepths;
    int[][] bitDepthScale;
    protected int[] bitsPerSample;
    protected int byteCount;
    protected TIFFColorConverter colorConverter;
    protected char[] colorMap;
    protected int compression;
    protected int[] destinationBands;
    protected int dstHeight;
    protected int dstMinX;
    protected int dstMinY;
    protected int dstWidth;
    protected int dstXOffset;
    protected int dstYOffset;
    protected int[] extraSamples;
    protected BufferedImage image;
    boolean isBilevel;
    boolean isContiguous;
    boolean isImageSimple;
    protected IIOMetadata metadata;
    protected long offset;
    protected int photometricInterpretation;
    protected boolean planar;
    protected BufferedImage rawImage;
    protected ImageReader reader;
    protected int samplesPerPixel;
    protected int[] sourceBands;
    protected int sourceXOffset;
    protected int sourceYOffset;
    protected int srcHeight;
    protected int srcMinX;
    protected int srcMinY;
    protected int srcWidth;
    protected ImageInputStream stream;
    protected int subsampleX;
    protected int subsampleY;
    protected int[] sampleFormat = {1};
    private boolean isFirstBitDepthTable = true;
    private boolean planarCache = false;
    private int[] destBitsPerSampleCache = null;
    private int[] sourceBandsCache = null;
    private int[] bitsPerSampleCache = null;
    private int[] destinationBandsCache = null;

    private static int getDataTypeFromNumBits(int i, boolean z) {
        if (i <= 8) {
            return 0;
        }
        if (i <= 16) {
            return z ? 2 : 1;
        }
        return 3;
    }

    public abstract void decodeRaw(byte[] bArr, int i, int i2, int i3) throws IOException;

    static SampleModel createInterleavedSM(int i, int i2) {
        int[] iArr = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i3] = i3;
        }
        return new PixelInterleavedSampleModel(i, 1, 1, i2, i2, iArr);
    }

    static ColorModel createComponentCM(ColorSpace colorSpace, int i, int i2, boolean z, boolean z2) {
        int i3;
        int i4 = z ? 3 : 1;
        if (i2 == 4 || i2 == 5) {
            return new ComponentColorModel(colorSpace, z, z2, i4, i2);
        }
        int[] iArr = new int[i];
        if (i2 == 0) {
            i3 = 8;
        } else if (i2 == 2 || i2 == 1) {
            i3 = 16;
        } else {
            if (i2 != 3) {
                throw new IllegalArgumentException("dataType = " + i2);
            }
            i3 = 32;
        }
        for (int i5 = 0; i5 < i; i5++) {
            iArr[i5] = i3;
        }
        return new ComponentColorModel(colorSpace, iArr, z, z2, i4, i2);
    }

    private static int createMask(int[] iArr, int i) {
        int i2 = (1 << iArr[i]) - 1;
        for (int i3 = i + 1; i3 < iArr.length; i3++) {
            i2 <<= iArr[i3];
        }
        return i2;
    }

    private static boolean areIntArraysEqual(int[] iArr, int[] iArr2) {
        if (iArr == null || iArr2 == null) {
            return iArr == null && iArr2 == null;
        }
        if (iArr.length != iArr2.length) {
            return false;
        }
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    private static int getDataTypeSize(int i) throws IIOException {
        if (i == 0) {
            return 8;
        }
        if (i == 1 || i == 2) {
            return 16;
        }
        if (i == 3 || i == 4) {
            return 32;
        }
        if (i == 5) {
            return 64;
        }
        throw new IIOException("Unknown data type " + i);
    }

    private static int getBitsPerPixel(SampleModel sampleModel) {
        int i = 0;
        for (int i2 : sampleModel.getSampleSize()) {
            i += i2;
        }
        return i;
    }

    private static boolean areSampleSizesEqual(SampleModel sampleModel) {
        int[] sampleSize = sampleModel.getSampleSize();
        int i = sampleSize[0];
        int length = sampleSize.length;
        for (int i2 = 1; i2 < length; i2++) {
            if (sampleSize[i2] != i) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDataBufferBitContiguous(SampleModel sampleModel) throws IIOException {
        int dataTypeSize = getDataTypeSize(sampleModel.getDataType());
        if (sampleModel instanceof ComponentSampleModel) {
            int numBands = sampleModel.getNumBands();
            for (int i = 0; i < numBands; i++) {
                if (sampleModel.getSampleSize(i) != dataTypeSize) {
                    return false;
                }
            }
            return true;
        }
        if (sampleModel instanceof MultiPixelPackedSampleModel) {
            return dataTypeSize % ((MultiPixelPackedSampleModel) sampleModel).getPixelBitStride() == 0;
        }
        if (!(sampleModel instanceof SinglePixelPackedSampleModel)) {
            return false;
        }
        int numBands2 = sampleModel.getNumBands();
        int i2 = 0;
        for (int i3 = 0; i3 < numBands2; i3++) {
            i2 += sampleModel.getSampleSize(i3);
        }
        return i2 == dataTypeSize;
    }

    private static void reformatData(byte[] bArr, int i, int i2, short[] sArr, int[] iArr, int i3, int i4) throws IIOException {
        int i5;
        if (sArr != null) {
            int i6 = i / 2;
            int i7 = i % 2;
            int i8 = 0;
            for (int i9 = 0; i9 < i2; i9++) {
                int i10 = i3;
                int i11 = 0;
                while (i11 < i6) {
                    int i12 = i8 + 1;
                    sArr[i10] = (short) (((bArr[i8] & 255) << 8) | (bArr[i12] & 255));
                    i11++;
                    i10++;
                    i8 = i12 + 1;
                }
                if (i7 != 0) {
                    sArr[i10] = (short) ((bArr[i8] & 255) << 8);
                    i8++;
                }
                i3 += i4;
            }
            return;
        }
        if (iArr != null) {
            int i13 = i / 4;
            int i14 = i % 4;
            int i15 = 0;
            for (int i16 = 0; i16 < i2; i16++) {
                int i17 = i3;
                int i18 = 0;
                while (true) {
                    i5 = 24;
                    if (i18 >= i13) {
                        break;
                    }
                    int i19 = i15 + 1;
                    int i20 = i19 + 1;
                    int i21 = ((bArr[i15] & 255) << 24) | ((bArr[i19] & 255) << 16);
                    int i22 = i20 + 1;
                    iArr[i17] = i21 | ((bArr[i20] & 255) << 8) | (bArr[i22] & 255);
                    i18++;
                    i15 = i22 + 1;
                    i17++;
                }
                if (i14 != 0) {
                    int i23 = 0;
                    int i24 = 0;
                    while (i23 < i14) {
                        i24 |= (bArr[i15] & 255) << i5;
                        i5 -= 8;
                        i23++;
                        i15++;
                    }
                    iArr[i17] = i24;
                }
                i3 += i4;
            }
            return;
        }
        throw new IIOException("shortData == null && intData == null!");
    }

    private static void reformatDiscontiguousData(byte[] bArr, int i, int i2, int i3, WritableRaster writableRaster) throws IOException {
        SampleModel sampleModel = writableRaster.getSampleModel();
        int numBands = sampleModel.getNumBands();
        int[] sampleSize = sampleModel.getSampleSize();
        MemoryCacheImageInputStream memoryCacheImageInputStream = new MemoryCacheImageInputStream(new ByteArrayInputStream(bArr));
        int minY = writableRaster.getMinY();
        long j = 0;
        int i4 = 0;
        while (i4 < i3) {
            memoryCacheImageInputStream.seek(j);
            int minX = writableRaster.getMinX();
            int i5 = 0;
            while (i5 < i2) {
                for (int i6 = 0; i6 < numBands; i6++) {
                    writableRaster.setSample(minX, minY, i6, (int) memoryCacheImageInputStream.readBits(sampleSize[i6]));
                }
                i5++;
                minX++;
            }
            j += i;
            i4++;
            minY++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:171:0x01f6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ImageTypeSpecifier getRawImageTypeSpecifier(int i, int i2, int i3, int[] iArr, int[] iArr2, int[] iArr3, char[] cArr) {
        Object[] objArr;
        Object[] objArr2;
        ColorModel createComponentCM;
        ColorSpace bogusColorSpace;
        boolean z;
        boolean z2 = false;
        if (i3 == 1 && (iArr[0] == 1 || iArr[0] == 2 || iArr[0] == 4 || iArr[0] == 8 || iArr[0] == 16)) {
            if (cArr == null) {
                boolean z3 = iArr2[0] == 2;
                if (iArr[0] <= 8) {
                    r7 = 0;
                } else if (iArr2[0] != 2) {
                    r7 = 1;
                }
                return ImageTypeSpecifier.createGrayscale(iArr[0], r7, z3);
            }
            int i4 = 1 << iArr[0];
            byte[] bArr = new byte[i4];
            byte[] bArr2 = new byte[i4];
            byte[] bArr3 = new byte[i4];
            for (int i5 = 0; i5 < i4; i5++) {
                bArr[i5] = (byte) ((cArr[i5] * 255) / 65535);
                bArr2[i5] = (byte) ((cArr[i4 + i5] * 255) / 65535);
                bArr3[i5] = (byte) ((cArr[(i4 * 2) + i5] * 255) / 65535);
            }
            return ImageTypeSpecifier.createIndexed(bArr, bArr2, bArr3, (byte[]) null, iArr[0], iArr[0] != 8 ? 1 : 0);
        }
        if (i3 == 2 && iArr[0] == 8 && iArr[1] == 8) {
            return ImageTypeSpecifier.createGrayscale(8, 0, false, iArr3 != null && iArr3[0] == 1);
        }
        if (i3 == 2 && iArr[0] == 16 && iArr[1] == 16) {
            int i6 = iArr2[0] == 2 ? 2 : 1;
            return ImageTypeSpecifier.createGrayscale(16, i6, i6 == 2, iArr3 != null && iArr3[0] == 1);
        }
        ColorSpace colorSpace = ColorSpace.getInstance(1000);
        if (i3 == 3 && iArr[0] == 8 && iArr[1] == 8 && iArr[2] == 8) {
            int[] iArr4 = {0, 1, 2};
            if ((i == 6 && i2 != 7 && i2 != 6) || i == 8) {
                colorSpace = ColorSpace.getInstance(1004);
            }
            return ImageTypeSpecifier.createInterleaved(colorSpace, iArr4, 0, false, false);
        }
        if (i3 == 4 && iArr[0] == 8 && iArr[1] == 8 && iArr[2] == 8 && iArr[3] == 8) {
            int[] iArr5 = {0, 1, 2, 3};
            if (i == 5) {
                colorSpace = SimpleCMYKColorSpace.getInstance();
                z = false;
                r8 = false;
            } else {
                z = iArr3 != null && iArr3[0] == 1;
            }
            return ImageTypeSpecifier.createInterleaved(colorSpace, iArr5, 0, r8, z);
        }
        if (i3 == 3 && iArr[0] == 16 && iArr[1] == 16 && iArr[2] == 16) {
            return ImageTypeSpecifier.createInterleaved(colorSpace, new int[]{0, 1, 2}, iArr2[0] != 2 ? 1 : 2, false, false);
        }
        if (i3 == 4 && iArr[0] == 16 && iArr[1] == 16 && iArr[2] == 16 && iArr[3] == 16) {
            int[] iArr6 = {0, 1, 2, 3};
            r7 = iArr2[0] != 2 ? 1 : 2;
            if (iArr3 != null && iArr3[0] == 1) {
                z2 = true;
            }
            return ImageTypeSpecifier.createInterleaved(colorSpace, iArr6, r7, true, z2);
        }
        if (i == 5 && (iArr[0] == 1 || iArr[0] == 2 || iArr[0] == 4)) {
            if (i3 == 4) {
                bogusColorSpace = SimpleCMYKColorSpace.getInstance();
            } else {
                bogusColorSpace = new BogusColorSpace(i3);
            }
            ComponentColorModel componentColorModel = new ComponentColorModel(bogusColorSpace, iArr, false, false, 1, 0);
            return new ImageTypeSpecifier(componentColorModel, componentColorModel.createCompatibleSampleModel(1, 1));
        }
        int i7 = 0;
        for (int i8 : iArr) {
            i7 += i8;
        }
        if ((i3 == 3 || i3 == 4) && (i7 == 8 || i7 == 16)) {
            int createMask = createMask(iArr, 0);
            int createMask2 = createMask(iArr, 1);
            int createMask3 = createMask(iArr, 2);
            int createMask4 = i3 == 4 ? createMask(iArr, 3) : 0;
            int i9 = i7 == 8 ? 0 : 1;
            if (iArr3 != null && iArr3[0] == 1) {
                z2 = true;
            }
            return ImageTypeSpecifier.createPacked(colorSpace, createMask, createMask2, createMask3, createMask4, i9, z2);
        }
        if (iArr[0] % 8 == 0) {
            int i10 = 1;
            while (true) {
                if (i10 >= iArr.length) {
                    objArr = true;
                    break;
                }
                if (iArr[i10] != iArr[i10 - 1]) {
                    objArr = false;
                    break;
                }
                i10++;
            }
            if (objArr != false) {
                int i11 = -1;
                int i12 = iArr[0];
                if (i12 != 8) {
                    if (i12 != 16) {
                        if (i12 == 32) {
                            i11 = iArr2[0] == 3 ? 4 : 3;
                            objArr2 = true;
                        }
                        objArr2 = false;
                    } else {
                        if (iArr2[0] != 3) {
                            i11 = iArr2[0] == 2 ? 2 : 1;
                            objArr2 = true;
                        }
                        objArr2 = false;
                    }
                    if (objArr2 != false) {
                        SampleModel createInterleavedSM = createInterleavedSM(i11, i3);
                        if (i3 >= 1 && i3 <= 4 && (i11 == 3 || i11 == 4)) {
                            if (i3 <= 2) {
                                colorSpace = ColorSpace.getInstance(1003);
                            }
                            boolean z4 = i3 % 2 == 0;
                            createComponentCM = createComponentCM(colorSpace, i3, i11, z4, z4 && iArr3 != null && iArr3[0] == 1);
                        } else {
                            createComponentCM = createComponentCM(new BogusColorSpace(i3), i3, i11, false, false);
                        }
                        return new ImageTypeSpecifier(createComponentCM, createInterleavedSM);
                    }
                } else {
                    if (iArr2[0] != 3) {
                        objArr2 = true;
                        i11 = 0;
                        if (objArr2 != false) {
                        }
                    }
                    objArr2 = false;
                    if (objArr2 != false) {
                    }
                }
            }
        }
        if (cArr != null || iArr2[0] == 3) {
            return null;
        }
        int i13 = 0;
        for (int i14 = 0; i14 < iArr.length; i14++) {
            if (iArr[i14] > i13) {
                i13 = iArr[i14];
            }
        }
        boolean z5 = iArr2[0] == 2;
        if (i3 == 1) {
            return ImageTypeSpecifier.createGrayscale(i13, getDataTypeFromNumBits(i13, z5), z5);
        }
        if (i3 == 2) {
            return ImageTypeSpecifier.createGrayscale(i13, getDataTypeFromNumBits(i13, z5), false, iArr3 != null && iArr3[0] == 1);
        }
        if (i3 != 3 && i3 != 4) {
            int dataTypeFromNumBits = getDataTypeFromNumBits(i13, z5);
            return new ImageTypeSpecifier(createComponentCM(new BogusColorSpace(i3), i3, dataTypeFromNumBits, false, false), createInterleavedSM(dataTypeFromNumBits, i3));
        }
        if (i7 <= 32 && !z5) {
            int createMask5 = createMask(iArr, 0);
            int createMask6 = createMask(iArr, 1);
            int createMask7 = createMask(iArr, 2);
            int createMask8 = i3 == 4 ? createMask(iArr, 3) : 0;
            int dataTypeFromNumBits2 = getDataTypeFromNumBits(i7, false);
            if (iArr3 != null && iArr3[0] == 1) {
                z2 = true;
            }
            return ImageTypeSpecifier.createPacked(colorSpace, createMask5, createMask6, createMask7, createMask8, dataTypeFromNumBits2, z2);
        }
        if (i3 == 3) {
            return ImageTypeSpecifier.createInterleaved(colorSpace, new int[]{0, 1, 2}, getDataTypeFromNumBits(i13, z5), false, false);
        }
        if (i3 != 4) {
            return null;
        }
        int[] iArr7 = {0, 1, 2, 3};
        int dataTypeFromNumBits3 = getDataTypeFromNumBits(i13, z5);
        if (iArr3 != null && iArr3[0] == 1) {
            z2 = true;
        }
        return ImageTypeSpecifier.createInterleaved(colorSpace, iArr7, dataTypeFromNumBits3, true, z2);
    }

    public void setReader(ImageReader imageReader) {
        this.reader = imageReader;
    }

    public void setMetadata(IIOMetadata iIOMetadata) {
        this.metadata = iIOMetadata;
    }

    public void setPhotometricInterpretation(int i) {
        this.photometricInterpretation = i;
    }

    public void setCompression(int i) {
        this.compression = i;
    }

    public void setPlanar(boolean z) {
        this.planar = z;
    }

    public void setSamplesPerPixel(int i) {
        this.samplesPerPixel = i;
    }

    public void setBitsPerSample(int[] iArr) {
        this.bitsPerSample = iArr == null ? null : (int[]) iArr.clone();
    }

    public void setSampleFormat(int[] iArr) {
        this.sampleFormat = iArr == null ? new int[]{1} : (int[]) iArr.clone();
    }

    public void setExtraSamples(int[] iArr) {
        this.extraSamples = iArr == null ? null : (int[]) iArr.clone();
    }

    public void setColorMap(char[] cArr) {
        this.colorMap = cArr == null ? null : (char[]) cArr.clone();
    }

    public void setStream(ImageInputStream imageInputStream) {
        this.stream = imageInputStream;
    }

    public void setOffset(long j) {
        this.offset = j;
    }

    public void setByteCount(int i) {
        this.byteCount = i;
    }

    public void setSrcMinX(int i) {
        this.srcMinX = i;
    }

    public void setSrcMinY(int i) {
        this.srcMinY = i;
    }

    public void setSrcWidth(int i) {
        this.srcWidth = i;
    }

    public void setSrcHeight(int i) {
        this.srcHeight = i;
    }

    public void setSourceXOffset(int i) {
        this.sourceXOffset = i;
    }

    public void setDstXOffset(int i) {
        this.dstXOffset = i;
    }

    public void setSourceYOffset(int i) {
        this.sourceYOffset = i;
    }

    public void setDstYOffset(int i) {
        this.dstYOffset = i;
    }

    public void setSubsampleX(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("subsampleX <= 0!");
        }
        this.subsampleX = i;
    }

    public void setSubsampleY(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("subsampleY <= 0!");
        }
        this.subsampleY = i;
    }

    public void setSourceBands(int[] iArr) {
        this.sourceBands = iArr == null ? null : (int[]) iArr.clone();
    }

    public void setDestinationBands(int[] iArr) {
        this.destinationBands = iArr == null ? null : (int[]) iArr.clone();
    }

    public void setImage(BufferedImage bufferedImage) {
        this.image = bufferedImage;
    }

    public void setDstMinX(int i) {
        this.dstMinX = i;
    }

    public void setDstMinY(int i) {
        this.dstMinY = i;
    }

    public void setDstWidth(int i) {
        this.dstWidth = i;
    }

    public void setDstHeight(int i) {
        this.dstHeight = i;
    }

    public void setActiveSrcMinX(int i) {
        this.activeSrcMinX = i;
    }

    public void setActiveSrcMinY(int i) {
        this.activeSrcMinY = i;
    }

    public void setActiveSrcWidth(int i) {
        this.activeSrcWidth = i;
    }

    public void setActiveSrcHeight(int i) {
        this.activeSrcHeight = i;
    }

    public void setColorConverter(TIFFColorConverter tIFFColorConverter) {
        this.colorConverter = tIFFColorConverter;
    }

    public ImageTypeSpecifier getRawImageType() {
        return getRawImageTypeSpecifier(this.photometricInterpretation, this.compression, this.samplesPerPixel, this.bitsPerSample, this.sampleFormat, this.extraSamples, this.colorMap);
    }

    public BufferedImage createRawImage() {
        ImageTypeSpecifier imageTypeSpecifier;
        if (this.planar) {
            int i = this.bitsPerSample[this.sourceBands[0]];
            int[] iArr = this.sampleFormat;
            int i2 = 3;
            if (iArr[0] == 3) {
                i2 = 4;
            } else if (i <= 8) {
                i2 = 0;
            } else if (i <= 16) {
                i2 = iArr[0] == 2 ? 2 : 1;
            }
            ColorSpace colorSpace = ColorSpace.getInstance(1003);
            if (i == 1 || i == 2 || i == 4) {
                int i3 = 1 << i;
                byte[] bArr = new byte[i3];
                byte[] bArr2 = new byte[i3];
                byte[] bArr3 = new byte[i3];
                for (int i4 = 0; i4 < i3; i4++) {
                    bArr[i4] = 0;
                    bArr2[i4] = 0;
                    bArr3[i4] = 0;
                }
                imageTypeSpecifier = new ImageTypeSpecifier(new IndexColorModel(i, i3, bArr, bArr2, bArr3), new MultiPixelPackedSampleModel(0, 1, 1, i));
            } else {
                imageTypeSpecifier = ImageTypeSpecifier.createInterleaved(colorSpace, new int[]{0}, i2, false, false);
            }
            return imageTypeSpecifier.createBufferedImage(this.srcWidth, this.srcHeight);
        }
        ImageTypeSpecifier rawImageType = getRawImageType();
        if (rawImageType == null) {
            return null;
        }
        return rawImageType.createBufferedImage(this.srcWidth, this.srcHeight);
    }

    public void decodeRaw(short[] sArr, int i, int i2, int i3) throws IOException {
        int i4 = ((this.srcWidth * i2) + 7) / 8;
        int i5 = i4 / 2;
        decodeRaw(new byte[this.srcHeight * i4], 0, i2, i4);
        if (this.stream.getByteOrder() == ByteOrder.BIG_ENDIAN) {
            int i6 = 0;
            for (int i7 = 0; i7 < this.srcHeight; i7++) {
                int i8 = 0;
                while (i8 < i5) {
                    sArr[i + i8] = (short) ((r2[i6] << 8) | (r2[r5] & 255));
                    i8++;
                    i6 = i6 + 1 + 1;
                }
                i += i3;
            }
            return;
        }
        int i9 = 0;
        for (int i10 = 0; i10 < this.srcHeight; i10++) {
            int i11 = 0;
            while (i11 < i5) {
                sArr[i + i11] = (short) ((r2[i9] & 255) | (r2[r5] << 8));
                i11++;
                i9 = i9 + 1 + 1;
            }
            i += i3;
        }
    }

    public void decodeRaw(int[] iArr, int i, int i2, int i3) throws IOException {
        int i4 = this.srcWidth * (i2 / 32);
        int i5 = i4 * 4;
        byte[] bArr = new byte[this.srcHeight * i5];
        decodeRaw(bArr, 0, i2, i5);
        if (this.stream.getByteOrder() == ByteOrder.BIG_ENDIAN) {
            int i6 = 0;
            for (int i7 = 0; i7 < this.srcHeight; i7++) {
                int i8 = 0;
                while (i8 < i4) {
                    int i9 = i6 + 1;
                    int i10 = i9 + 1;
                    int i11 = i10 + 1;
                    iArr[i + i8] = ((bArr[i6] & 255) << 24) | ((bArr[i9] & 255) << 16) | ((bArr[i10] & 255) << 8) | (bArr[i11] & 255);
                    i8++;
                    i6 = i11 + 1;
                }
                i += i3;
            }
            return;
        }
        int i12 = 0;
        for (int i13 = 0; i13 < this.srcHeight; i13++) {
            int i14 = 0;
            while (i14 < i4) {
                int i15 = i12 + 1;
                int i16 = i15 + 1;
                int i17 = i16 + 1;
                iArr[i + i14] = (bArr[i12] & 255) | ((bArr[i15] & 255) << 8) | ((bArr[i16] & 255) << 16) | ((bArr[i17] & 255) << 24);
                i14++;
                i12 = i17 + 1;
            }
            i += i3;
        }
    }

    public void decodeRaw(float[] fArr, int i, int i2, int i3) throws IOException {
        int i4 = this.srcWidth * (i2 / 32);
        int i5 = i4 * 4;
        byte[] bArr = new byte[this.srcHeight * i5];
        decodeRaw(bArr, 0, i2, i5);
        if (this.stream.getByteOrder() == ByteOrder.BIG_ENDIAN) {
            int i6 = 0;
            for (int i7 = 0; i7 < this.srcHeight; i7++) {
                int i8 = 0;
                while (i8 < i4) {
                    int i9 = i6 + 1;
                    int i10 = i9 + 1;
                    int i11 = i10 + 1;
                    fArr[i + i8] = Float.intBitsToFloat(((bArr[i6] & 255) << 24) | ((bArr[i9] & 255) << 16) | ((bArr[i10] & 255) << 8) | (bArr[i11] & 255));
                    i8++;
                    i6 = i11 + 1;
                }
                i += i3;
            }
            return;
        }
        int i12 = 0;
        for (int i13 = 0; i13 < this.srcHeight; i13++) {
            int i14 = 0;
            while (i14 < i4) {
                int i15 = i12 + 1;
                int i16 = i15 + 1;
                int i17 = i16 + 1;
                fArr[i + i14] = Float.intBitsToFloat((bArr[i12] & 255) | ((bArr[i15] & 255) << 8) | ((bArr[i16] & 255) << 16) | ((bArr[i17] & 255) << 24));
                i14++;
                i12 = i17 + 1;
            }
            i += i3;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x003f, code lost:
    
        if (r2[0] != 4) goto L16;
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0107  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void beginDecoding() {
        int[] sampleSize;
        boolean z;
        boolean z2;
        int i;
        int i2;
        boolean z3 = false;
        this.adjustBitDepths = false;
        int length = this.destinationBands.length;
        if (this.planar) {
            int length2 = this.bitsPerSample.length;
            sampleSize = new int[length2];
            int sampleSize2 = this.image.getSampleModel().getSampleSize(0);
            for (int i3 = 0; i3 < length2; i3++) {
                sampleSize[i3] = sampleSize2;
            }
        } else {
            sampleSize = this.image.getSampleModel().getSampleSize();
        }
        if (this.photometricInterpretation == 5) {
            int[] iArr = this.bitsPerSample;
            if (iArr[0] != 1) {
                if (iArr[0] != 2) {
                }
            }
            if (!this.adjustBitDepths) {
                if (this.isFirstBitDepthTable || this.planar != this.planarCache || !areIntArraysEqual(sampleSize, this.destBitsPerSampleCache) || !areIntArraysEqual(this.sourceBands, this.sourceBandsCache) || !areIntArraysEqual(this.bitsPerSample, this.bitsPerSampleCache) || !areIntArraysEqual(this.destinationBands, this.destinationBandsCache)) {
                    this.isFirstBitDepthTable = false;
                    this.planarCache = this.planar;
                    this.destBitsPerSampleCache = (int[]) sampleSize.clone();
                    int[] iArr2 = this.sourceBands;
                    this.sourceBandsCache = iArr2 == null ? null : (int[]) iArr2.clone();
                    int[] iArr3 = this.bitsPerSample;
                    this.bitsPerSampleCache = iArr3 == null ? null : (int[]) iArr3.clone();
                    int[] iArr4 = this.destinationBands;
                    this.destinationBandsCache = iArr4 != null ? (int[]) iArr4.clone() : null;
                    this.bitDepthScale = new int[length];
                    for (int i4 = 0; i4 < length; i4++) {
                        int i5 = (1 << this.bitsPerSample[this.sourceBands[i4]]) - 1;
                        int i6 = i5 / 2;
                        int i7 = (1 << sampleSize[this.destinationBands[i4]]) - 1;
                        this.bitDepthScale[i4] = new int[i5 + 1];
                        for (int i8 = 0; i8 <= i5; i8++) {
                            this.bitDepthScale[i4][i8] = ((i8 * i7) + i6) / i5;
                        }
                    }
                }
            } else {
                this.bitDepthScale = null;
            }
            if (length != this.samplesPerPixel) {
                z = true;
                z2 = true;
                for (int i9 = 0; i9 < length; i9++) {
                    if (this.sourceBands[i9] != i9) {
                        z = false;
                    }
                    if (this.destinationBands[i9] != i9) {
                        z2 = false;
                    }
                }
            } else {
                z = false;
                z2 = false;
            }
            boolean isBinary = ImageUtil.isBinary(this.image.getRaster().getSampleModel());
            this.isBilevel = isBinary;
            this.isContiguous = !isBinary ? true : ImageUtil.imageIsContiguous(this.image);
            if (this.colorConverter == null && this.subsampleX == 1 && this.subsampleY == 1) {
                i = this.srcWidth;
                i2 = this.dstWidth;
                if (i == i2 && this.srcHeight == this.dstHeight && this.dstMinX + i2 <= this.image.getWidth() && this.dstMinY + this.dstHeight <= this.image.getHeight() && z && z2 && !this.adjustBitDepths) {
                    z3 = true;
                }
            }
            this.isImageSimple = z3;
        }
        int i10 = 0;
        while (true) {
            if (i10 >= length) {
                break;
            }
            if (sampleSize[this.destinationBands[i10]] != this.bitsPerSample[this.sourceBands[i10]]) {
                this.adjustBitDepths = true;
                break;
            }
            i10++;
        }
        if (!this.adjustBitDepths) {
        }
        if (length != this.samplesPerPixel) {
        }
        boolean isBinary2 = ImageUtil.isBinary(this.image.getRaster().getSampleModel());
        this.isBilevel = isBinary2;
        this.isContiguous = !isBinary2 ? true : ImageUtil.imageIsContiguous(this.image);
        if (this.colorConverter == null) {
            i = this.srcWidth;
            i2 = this.dstWidth;
            if (i == i2) {
                z3 = true;
            }
        }
        this.isImageSimple = z3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:45:0x041a  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x04dc  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x04fb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x04fc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void decode() throws IOException {
        int[] iArr;
        float[] fArr;
        short[] sArr;
        int[] iArr2;
        int i;
        int i2;
        boolean z;
        int i3;
        int i4;
        byte[] bArr;
        short[] sArr2;
        int[] iArr3;
        int i5;
        short[] data;
        int pixelStride;
        WritableRaster writableRaster;
        boolean z2;
        int i6;
        byte[] bArr2;
        int i7;
        this.rawImage = null;
        if (this.isImageSimple) {
            if (this.isBilevel) {
                this.rawImage = this.image;
            } else if (this.isContiguous) {
                this.rawImage = this.image.getSubimage(this.dstMinX, this.dstMinY, this.dstWidth, this.dstHeight);
            }
        }
        BufferedImage bufferedImage = this.rawImage;
        int i8 = 0;
        boolean z3 = bufferedImage != null;
        if (bufferedImage == null) {
            BufferedImage createRawImage = createRawImage();
            this.rawImage = createRawImage;
            if (createRawImage == null) {
                throw new IIOException("Couldn't create image buffer!");
            }
        }
        WritableRaster raster = this.rawImage.getRaster();
        if (this.isBilevel) {
            Rectangle rectangle = this.isImageSimple ? new Rectangle(this.dstMinX, this.dstMinY, this.dstWidth, this.dstHeight) : raster.getBounds();
            byte[] packedBinaryData = ImageUtil.getPackedBinaryData(raster, rectangle);
            i4 = (rectangle.width + 7) / 8;
            sArr2 = null;
            iArr3 = null;
            fArr = null;
            i2 = 0;
            i3 = 1;
            bArr = packedBinaryData;
        } else {
            ComponentSampleModel sampleModel = raster.getSampleModel();
            DataBufferByte dataBuffer = raster.getDataBuffer();
            if (sampleModel instanceof ComponentSampleModel) {
                ComponentSampleModel componentSampleModel = sampleModel;
                i2 = componentSampleModel.getOffset(-raster.getSampleModelTranslateX(), -raster.getSampleModelTranslateY());
                int scanlineStride = componentSampleModel.getScanlineStride();
                if (dataBuffer instanceof DataBufferByte) {
                    iArr = dataBuffer.getData();
                    i5 = componentSampleModel.getPixelStride() * 8;
                    fArr = null;
                    sArr = null;
                    iArr2 = null;
                    z = true;
                } else {
                    if (dataBuffer instanceof DataBufferUShort) {
                        data = ((DataBufferUShort) dataBuffer).getData();
                        pixelStride = componentSampleModel.getPixelStride();
                    } else if (dataBuffer instanceof DataBufferShort) {
                        data = ((DataBufferShort) dataBuffer).getData();
                        pixelStride = componentSampleModel.getPixelStride();
                    } else if (dataBuffer instanceof DataBufferInt) {
                        int[] data2 = ((DataBufferInt) dataBuffer).getData();
                        i5 = componentSampleModel.getPixelStride() * 32;
                        fArr = null;
                        sArr = null;
                        iArr2 = data2;
                        z = true;
                        iArr = null;
                    } else if (dataBuffer instanceof DataBufferFloat) {
                        float[] data3 = ((DataBufferFloat) dataBuffer).getData();
                        i5 = componentSampleModel.getPixelStride() * 32;
                        sArr = null;
                        iArr2 = null;
                        fArr = data3;
                        z = true;
                        iArr = iArr2;
                    } else {
                        iArr = null;
                        fArr = null;
                        sArr = null;
                        iArr2 = null;
                        z = false;
                        i5 = 1;
                    }
                    i5 = pixelStride * 16;
                    fArr = null;
                    iArr2 = null;
                    sArr = data;
                    z = true;
                    iArr = iArr2;
                }
                i3 = i5;
                i = scanlineStride;
            } else if (sampleModel instanceof MultiPixelPackedSampleModel) {
                MultiPixelPackedSampleModel multiPixelPackedSampleModel = (MultiPixelPackedSampleModel) sampleModel;
                i2 = multiPixelPackedSampleModel.getOffset(-raster.getSampleModelTranslateX(), -raster.getSampleModelTranslateY());
                i3 = multiPixelPackedSampleModel.getPixelBitStride();
                i = multiPixelPackedSampleModel.getScanlineStride();
                if (dataBuffer instanceof DataBufferByte) {
                    iArr = dataBuffer.getData();
                    sArr = null;
                    iArr2 = null;
                    z = true;
                } else if (dataBuffer instanceof DataBufferUShort) {
                    iArr2 = null;
                    sArr = ((DataBufferUShort) dataBuffer).getData();
                    z = true;
                    iArr = null;
                } else if (dataBuffer instanceof DataBufferInt) {
                    sArr = null;
                    iArr2 = ((DataBufferInt) dataBuffer).getData();
                    z = true;
                    iArr = null;
                } else {
                    iArr = null;
                    sArr = null;
                    iArr2 = null;
                    z = false;
                }
                fArr = null;
            } else {
                if (sampleModel instanceof SinglePixelPackedSampleModel) {
                    SinglePixelPackedSampleModel singlePixelPackedSampleModel = (SinglePixelPackedSampleModel) sampleModel;
                    i2 = singlePixelPackedSampleModel.getOffset(-raster.getSampleModelTranslateX(), -raster.getSampleModelTranslateY());
                    i = singlePixelPackedSampleModel.getScanlineStride();
                    if (dataBuffer instanceof DataBufferByte) {
                        fArr = null;
                        sArr = null;
                        iArr2 = null;
                        iArr = dataBuffer.getData();
                        z = true;
                        i3 = 8;
                    } else {
                        if (dataBuffer instanceof DataBufferUShort) {
                            sArr = ((DataBufferUShort) dataBuffer).getData();
                            iArr = null;
                            fArr = null;
                            iArr2 = null;
                            i3 = 16;
                        } else if (dataBuffer instanceof DataBufferInt) {
                            iArr2 = ((DataBufferInt) dataBuffer).getData();
                            iArr = null;
                            fArr = null;
                            sArr = null;
                            i3 = 32;
                        } else {
                            iArr = null;
                            fArr = null;
                            sArr = null;
                            iArr2 = null;
                            z = false;
                        }
                        z = true;
                    }
                } else {
                    iArr = null;
                    fArr = null;
                    sArr = null;
                    iArr2 = null;
                    i = 0;
                    i2 = 0;
                    z = false;
                }
                i3 = 1;
            }
            if (!z) {
                throw new IIOException("Unsupported raw image type: SampleModel = " + sampleModel + "; DataBuffer = " + dataBuffer);
            }
            i4 = i;
            bArr = iArr;
            sArr2 = sArr;
            iArr3 = iArr2;
        }
        if (this.isBilevel) {
            decodeRaw(bArr, i2, i3, i4);
        } else {
            SampleModel sampleModel2 = raster.getSampleModel();
            if (!isDataBufferBitContiguous(sampleModel2)) {
                int bitsPerPixel = getBitsPerPixel(sampleModel2);
                int i9 = ((this.srcWidth * bitsPerPixel) + 7) / 8;
                byte[] bArr3 = new byte[this.srcHeight * i9];
                decodeRaw(bArr3, 0, bitsPerPixel, i9);
                reformatDiscontiguousData(bArr3, i9, this.srcWidth, this.srcHeight, raster);
            } else if (bArr != 0) {
                decodeRaw(bArr, i2, i3, i4);
            } else if (fArr != null) {
                decodeRaw(fArr, i2, i3, i4);
            } else if (sArr2 != null) {
                if (areSampleSizesEqual(sampleModel2) && sampleModel2.getSampleSize(0) == 16) {
                    decodeRaw(sArr2, i2, i3, i4);
                } else {
                    int bitsPerPixel2 = getBitsPerPixel(sampleModel2);
                    int i10 = ((this.srcWidth * bitsPerPixel2) + 7) / 8;
                    byte[] bArr4 = new byte[this.srcHeight * i10];
                    decodeRaw(bArr4, 0, bitsPerPixel2, i10);
                    reformatData(bArr4, i10, this.srcHeight, sArr2, null, i2, i4);
                }
            } else if (iArr3 != null) {
                if (areSampleSizesEqual(sampleModel2) && sampleModel2.getSampleSize(0) == 32) {
                    decodeRaw(iArr3, i2, i3, i4);
                } else {
                    int bitsPerPixel3 = getBitsPerPixel(sampleModel2);
                    int i11 = ((this.srcWidth * bitsPerPixel3) + 7) / 8;
                    byte[] bArr5 = new byte[this.srcHeight * i11];
                    decodeRaw(bArr5, 0, bitsPerPixel3, i11);
                    reformatData(bArr5, i11, this.srcHeight, null, iArr3, i2, i4);
                }
            }
        }
        if (this.colorConverter != null) {
            float[] fArr2 = new float[3];
            if (bArr != 0) {
                int i12 = 0;
                while (i12 < this.dstHeight) {
                    int i13 = i8;
                    int i14 = i2;
                    while (i13 < this.dstWidth) {
                        this.colorConverter.toRGB(bArr[i14] & 255, bArr[r23] & 255, bArr[r25] & 255, fArr2);
                        bArr[i14] = (byte) fArr2[0];
                        bArr[i14 + 1] = (byte) fArr2[1];
                        bArr[i14 + 2] = (byte) fArr2[2];
                        i14 += 3;
                        i13++;
                        z3 = z3;
                    }
                    i2 += i4;
                    i12++;
                    i8 = 0;
                }
                z2 = z3;
                writableRaster = raster;
            } else {
                z2 = z3;
                if (sArr2 != null) {
                    if (this.sampleFormat[0] == 2) {
                        for (int i15 = 0; i15 < this.dstHeight; i15++) {
                            int i16 = i2;
                            int i17 = 0;
                            while (i17 < this.dstWidth) {
                                this.colorConverter.toRGB(sArr2[i16], sArr2[r7], sArr2[r21], fArr2);
                                sArr2[i16] = (short) fArr2[0];
                                sArr2[i16 + 1] = (short) fArr2[1];
                                sArr2[i16 + 2] = (short) fArr2[2];
                                i16 += 3;
                                i17++;
                                raster = raster;
                                i3 = i3;
                            }
                            i2 += i4;
                        }
                        writableRaster = raster;
                        i6 = i3;
                    } else {
                        writableRaster = raster;
                        i6 = i3;
                        for (int i18 = 0; i18 < this.dstHeight; i18++) {
                            int i19 = i2;
                            int i20 = 0;
                            while (i20 < this.dstWidth) {
                                this.colorConverter.toRGB(sArr2[i19] & UShort.MAX_VALUE, sArr2[r7] & UShort.MAX_VALUE, sArr2[r13] & UShort.MAX_VALUE, fArr2);
                                sArr2[i19] = (short) fArr2[0];
                                sArr2[i19 + 1] = (short) fArr2[1];
                                sArr2[i19 + 2] = (short) fArr2[2];
                                i19 += 3;
                                i20++;
                                bArr = bArr;
                            }
                            i2 += i4;
                        }
                    }
                    bArr2 = bArr;
                } else {
                    writableRaster = raster;
                    bArr2 = bArr;
                    i6 = i3;
                    if (iArr3 != null) {
                        for (int i21 = 0; i21 < this.dstHeight; i21++) {
                            int i22 = i2;
                            for (int i23 = 0; i23 < this.dstWidth; i23++) {
                                this.colorConverter.toRGB(iArr3[i22], iArr3[r5], iArr3[r8], fArr2);
                                iArr3[i22] = (int) fArr2[0];
                                iArr3[i22 + 1] = (int) fArr2[1];
                                iArr3[i22 + 2] = (int) fArr2[2];
                                i22 += 3;
                            }
                            i2 += i4;
                        }
                    } else if (fArr != null) {
                        for (int i24 = 0; i24 < this.dstHeight; i24++) {
                            int i25 = i2;
                            for (int i26 = 0; i26 < this.dstWidth; i26++) {
                                int i27 = i25 + 1;
                                int i28 = i25 + 2;
                                this.colorConverter.toRGB(fArr[i25], fArr[i27], fArr[i28], fArr2);
                                fArr[i25] = fArr2[0];
                                fArr[i27] = fArr2[1];
                                fArr[i28] = fArr2[2];
                                i25 += 3;
                            }
                            i2 += i4;
                        }
                    }
                }
                if (this.photometricInterpretation == 0) {
                    if (bArr2 != null) {
                        int i29 = ((this.srcWidth * i6) + 7) / 8;
                        for (int i30 = 0; i30 < this.srcHeight; i30++) {
                            int i31 = (i30 * i4) + i2;
                            for (int i32 = 0; i32 < i29; i32++) {
                                int i33 = i31 + i32;
                                bArr2[i33] = (byte) (bArr2[i33] ^ 255);
                            }
                        }
                    } else {
                        if (sArr2 != null) {
                            int i34 = ((this.srcWidth * i6) + 15) / 16;
                            i7 = 0;
                            if (this.sampleFormat[0] == 2) {
                                for (int i35 = 0; i35 < this.srcHeight; i35++) {
                                    int i36 = (i35 * i4) + i2;
                                    for (int i37 = 0; i37 < i34; i37++) {
                                        int i38 = i36 + i37;
                                        sArr2[i38] = (short) (Short.MAX_VALUE - sArr2[i38]);
                                    }
                                }
                            } else {
                                for (int i39 = 0; i39 < this.srcHeight; i39++) {
                                    int i40 = (i39 * i4) + i2;
                                    for (int i41 = 0; i41 < i34; i41++) {
                                        int i42 = i40 + i41;
                                        sArr2[i42] = (short) (sArr2[i42] ^ UShort.MAX_VALUE);
                                    }
                                }
                            }
                        } else {
                            i7 = 0;
                            if (iArr3 != null) {
                                int i43 = ((this.srcWidth * i6) + 15) / 16;
                                for (int i44 = 0; i44 < this.srcHeight; i44++) {
                                    int i45 = (i44 * i4) + i2;
                                    for (int i46 = 0; i46 < i43; i46++) {
                                        int i47 = i45 + i46;
                                        iArr3[i47] = Integer.MAX_VALUE - iArr3[i47];
                                    }
                                }
                            } else if (fArr != null) {
                                int i48 = ((this.srcWidth * i6) + 15) / 16;
                                for (int i49 = 0; i49 < this.srcHeight; i49++) {
                                    int i50 = (i49 * i4) + i2;
                                    for (int i51 = 0; i51 < i48; i51++) {
                                        int i52 = i50 + i51;
                                        fArr[i52] = 1.0f - fArr[i52];
                                    }
                                }
                            }
                        }
                        if (this.isBilevel) {
                            ImageUtil.setPackedBinaryData(bArr2, writableRaster, this.isImageSimple ? new Rectangle(this.dstMinX, this.dstMinY, this.dstWidth, this.dstHeight) : writableRaster.getBounds());
                        }
                        if (z2) {
                            return;
                        }
                        Raster createChild = this.rawImage.getRaster().createChild(0, 0, this.srcWidth, this.srcHeight, this.srcMinX, this.srcMinY, this.planar ? null : this.sourceBands);
                        WritableRaster raster2 = this.image.getRaster();
                        int i53 = this.dstMinX;
                        int i54 = this.dstMinY;
                        WritableRaster createWritableChild = raster2.createWritableChild(i53, i54, this.dstWidth, this.dstHeight, i53, i54, this.destinationBands);
                        int i55 = this.subsampleX;
                        int i56 = 1;
                        if (i55 == 1 && this.subsampleY == 1) {
                            if (!this.adjustBitDepths) {
                                createWritableChild.setRect(createChild.createChild(this.activeSrcMinX, this.activeSrcMinY, this.activeSrcWidth, this.activeSrcHeight, this.dstMinX, this.dstMinY, (int[]) null));
                                return;
                            }
                            i56 = 1;
                        }
                        if (i55 == i56 && !this.adjustBitDepths) {
                            int i57 = this.activeSrcMinY;
                            int i58 = this.dstMinY;
                            while (i57 < this.srcMinY + this.srcHeight) {
                                createWritableChild.setRect(createChild.createChild(this.activeSrcMinX, i57, this.activeSrcWidth, 1, this.dstMinX, i58, (int[]) null));
                                i57 += this.subsampleY;
                                i58++;
                            }
                            return;
                        }
                        int[] pixel = createChild.getPixel(this.srcMinX, this.srcMinY, (int[]) null);
                        int length = pixel.length;
                        int i59 = this.activeSrcMinY;
                        int i60 = this.dstMinY;
                        while (i59 < this.activeSrcMinY + this.activeSrcHeight) {
                            int i61 = this.activeSrcMinX;
                            int i62 = this.dstMinX;
                            while (i61 < this.activeSrcMinX + this.activeSrcWidth) {
                                createChild.getPixel(i61, i59, pixel);
                                if (this.adjustBitDepths) {
                                    for (int i63 = i7; i63 < length; i63++) {
                                        pixel[i63] = this.bitDepthScale[i63][pixel[i63]];
                                    }
                                }
                                createWritableChild.setPixel(i62, i60, pixel);
                                i61 += this.subsampleX;
                                i62++;
                            }
                            i59 += this.subsampleY;
                            i60++;
                        }
                        return;
                    }
                }
                i7 = 0;
                if (this.isBilevel) {
                }
                if (z2) {
                }
            }
        } else {
            writableRaster = raster;
            z2 = z3;
        }
        bArr2 = bArr;
        i6 = i3;
        if (this.photometricInterpretation == 0) {
        }
        i7 = 0;
        if (this.isBilevel) {
        }
        if (z2) {
        }
    }
}
