package com.github.jaiimageio.impl.plugins.pnm;

import com.github.jaiimageio.impl.common.ImageUtil;
import com.github.jaiimageio.plugins.pnm.PNMImageWriteParam;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.image.ColorModel;
import java.awt.image.ComponentSampleModel;
import java.awt.image.IndexColorModel;
import java.awt.image.MultiPixelPackedSampleModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Iterator;
import javax.imageio.IIOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageOutputStream;
import sun.security.action.GetPropertyAction;

/* loaded from: classes3.dex */
public class PNMImageWriter extends ImageWriter {
    private static final String COMMENT = "# written by com.github.jaiimageio.impl.PNMImageWriter";
    private static final int PBM_ASCII = 49;
    private static final int PBM_RAW = 52;
    private static final int PGM_ASCII = 50;
    private static final int PGM_RAW = 53;
    private static final int PPM_ASCII = 51;
    private static final int PPM_RAW = 54;
    private static final int SPACE = 32;
    private static byte[] lineSeparator;
    private int maxValue;
    private ImageOutputStream stream;
    private int variant;

    private boolean isRaw(int i) {
        return i >= 52;
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

    static {
        if (lineSeparator == null) {
            lineSeparator = ((String) AccessController.doPrivileged((PrivilegedAction) new GetPropertyAction("line.separator"))).getBytes();
        }
    }

    public PNMImageWriter(ImageWriterSpi imageWriterSpi) {
        super(imageWriterSpi);
        this.stream = null;
    }

    public void setOutput(Object obj) {
        super.setOutput(obj);
        if (obj != null) {
            if (!(obj instanceof ImageOutputStream)) {
                throw new IllegalArgumentException(I18N.getString("PNMImageWriter0"));
            }
            this.stream = (ImageOutputStream) obj;
            return;
        }
        this.stream = null;
    }

    public ImageWriteParam getDefaultWriteParam() {
        return new PNMImageWriteParam();
    }

    public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageTypeSpecifier, ImageWriteParam imageWriteParam) {
        return new PNMMetadata(imageTypeSpecifier, imageWriteParam);
    }

    public IIOMetadata convertImageMetadata(IIOMetadata iIOMetadata, ImageTypeSpecifier imageTypeSpecifier, ImageWriteParam imageWriteParam) {
        PNMMetadata pNMMetadata;
        if (iIOMetadata == null) {
            throw new IllegalArgumentException("inData == null!");
        }
        if (imageTypeSpecifier == null) {
            throw new IllegalArgumentException("imageType == null!");
        }
        if (iIOMetadata instanceof PNMMetadata) {
            pNMMetadata = (PNMMetadata) ((PNMMetadata) iIOMetadata).clone();
        } else {
            try {
                pNMMetadata = new PNMMetadata(iIOMetadata);
            } catch (IIOInvalidTreeException unused) {
                pNMMetadata = new PNMMetadata();
            }
        }
        pNMMetadata.initialize(imageTypeSpecifier, imageWriteParam);
        return pNMMetadata;
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x042a  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0260 A[LOOP:1: B:54:0x0260->B:56:0x0266, LOOP_START] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x034f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void write(IIOMetadata iIOMetadata, IIOImage iIOImage, ImageWriteParam imageWriteParam) throws IOException {
        RenderedImage renderedImage;
        SampleModel sampleModel;
        ColorModel colorModel;
        Rectangle intersection;
        Raster raster;
        int[] iArr;
        Rectangle rectangle;
        int i;
        int i2;
        boolean z;
        byte[] bArr;
        byte[] bArr2;
        byte[] bArr3;
        IIOMetadata metadata;
        boolean z2;
        byte[] bArr4;
        ImageTypeSpecifier createGrayscale;
        PNMMetadata pNMMetadata;
        boolean isRaw;
        Iterator comments;
        int i3;
        int i4;
        int i5;
        Rectangle rectangle2;
        int i6;
        ComponentSampleModel componentSampleModel;
        boolean z3;
        boolean z4;
        int i7;
        boolean z5;
        int i8;
        byte[] bArr5;
        int[] iArr2;
        int i9;
        int i10;
        int i11;
        Raster data;
        char c;
        int i12;
        Rectangle rectangle3;
        int i13;
        Raster createTranslatedChild;
        int offset;
        int minX;
        int i14;
        byte[] bArr6;
        byte[] bArr7;
        boolean z6;
        clearAbortRequest();
        processImageStarted(0);
        ImageWriteParam defaultWriteParam = imageWriteParam == null ? getDefaultWriteParam() : imageWriteParam;
        boolean hasRaster = iIOImage.hasRaster();
        Rectangle sourceRegion = defaultWriteParam.getSourceRegion();
        if (hasRaster) {
            Raster raster2 = iIOImage.getRaster();
            sampleModel = raster2.getSampleModel();
            if (sourceRegion == null) {
                intersection = raster2.getBounds();
            } else {
                intersection = sourceRegion.intersection(raster2.getBounds());
            }
            raster = raster2;
            renderedImage = null;
            colorModel = null;
        } else {
            renderedImage = iIOImage.getRenderedImage();
            sampleModel = renderedImage.getSampleModel();
            colorModel = renderedImage.getColorModel();
            Rectangle rectangle4 = new Rectangle(renderedImage.getMinX(), renderedImage.getMinY(), renderedImage.getWidth(), renderedImage.getHeight());
            intersection = sourceRegion == null ? rectangle4 : sourceRegion.intersection(rectangle4);
            raster = null;
        }
        if (intersection.isEmpty()) {
            throw new RuntimeException(I18N.getString("PNMImageWrite1"));
        }
        ImageUtil.canEncodeImage(this, colorModel, sampleModel);
        int sourceXSubsampling = defaultWriteParam.getSourceXSubsampling();
        int sourceYSubsampling = defaultWriteParam.getSourceYSubsampling();
        int subsamplingXOffset = defaultWriteParam.getSubsamplingXOffset();
        int subsamplingYOffset = defaultWriteParam.getSubsamplingYOffset();
        intersection.translate(subsamplingXOffset, subsamplingYOffset);
        intersection.width -= subsamplingXOffset;
        intersection.height -= subsamplingYOffset;
        int i15 = intersection.x / sourceXSubsampling;
        int i16 = intersection.y / sourceYSubsampling;
        int i17 = ((intersection.width + sourceXSubsampling) - 1) / sourceXSubsampling;
        int i18 = ((intersection.height + sourceYSubsampling) - 1) / sourceYSubsampling;
        new Rectangle(i15, i16, i17, i18);
        sampleModel.getHeight();
        int width = sampleModel.getWidth();
        int[] sampleSize = sampleModel.getSampleSize();
        int[] sourceBands = defaultWriteParam.getSourceBands();
        int numBands = sampleModel.getNumBands();
        if (sourceBands != null) {
            sampleModel = sampleModel.createSubsetSampleModel(sourceBands);
            numBands = sampleModel.getNumBands();
            iArr = sourceBands;
            colorModel = null;
        } else {
            int[] iArr3 = new int[numBands];
            for (int i19 = 0; i19 < numBands; i19++) {
                iArr3[i19] = i19;
            }
            iArr = iArr3;
        }
        if (numBands != 1) {
            rectangle = intersection;
            i = width;
            i2 = sourceXSubsampling;
            if (numBands == 3) {
                if (sampleSize[0] <= 8 && sampleSize[1] <= 8 && sampleSize[2] <= 8) {
                    this.variant = 54;
                } else {
                    this.variant = 51;
                }
            } else {
                throw new RuntimeException(I18N.getString("PNMImageWrite3"));
            }
        } else {
            if (colorModel instanceof IndexColorModel) {
                IndexColorModel indexColorModel = (IndexColorModel) colorModel;
                i2 = sourceXSubsampling;
                int mapSize = indexColorModel.getMapSize();
                i = width;
                rectangle = intersection;
                if (mapSize < (1 << sampleSize[0])) {
                    throw new RuntimeException(I18N.getString("PNMImageWrite2"));
                }
                if (sampleSize[0] == 1) {
                    this.variant = 52;
                    z6 = indexColorModel.getRed(1) > indexColorModel.getRed(0);
                    bArr6 = null;
                    bArr = null;
                    bArr7 = null;
                } else {
                    this.variant = 54;
                    bArr = new byte[mapSize];
                    bArr6 = new byte[mapSize];
                    bArr7 = new byte[mapSize];
                    indexColorModel.getReds(bArr);
                    indexColorModel.getGreens(bArr6);
                    indexColorModel.getBlues(bArr7);
                    z6 = false;
                }
                bArr2 = bArr6;
                z = z6;
                bArr3 = bArr7;
                metadata = iIOImage.getMetadata();
                if (colorModel == null) {
                    createGrayscale = new ImageTypeSpecifier(colorModel, sampleModel);
                    z2 = z;
                    bArr4 = bArr;
                } else {
                    int dataType = sampleModel.getDataType();
                    if (numBands == 1) {
                        z2 = z;
                        bArr4 = bArr;
                        createGrayscale = ImageTypeSpecifier.createGrayscale(sampleSize[0], dataType, false);
                    } else if (numBands == 3) {
                        bArr4 = bArr;
                        z2 = z;
                        createGrayscale = ImageTypeSpecifier.createInterleaved(ColorSpace.getInstance(1000), new int[]{0, 1, 2}, dataType, false, false);
                    } else {
                        throw new IIOException("Cannot encode image with " + numBands + " bands!");
                    }
                }
                if (metadata == null) {
                    pNMMetadata = (PNMMetadata) convertImageMetadata(metadata, createGrayscale, defaultWriteParam);
                } else {
                    pNMMetadata = (PNMMetadata) getDefaultImageMetadata(createGrayscale, defaultWriteParam);
                }
                if (!(defaultWriteParam instanceof PNMImageWriteParam)) {
                    isRaw = ((PNMImageWriteParam) defaultWriteParam).getRaw();
                } else {
                    isRaw = pNMMetadata.isRaw();
                }
                this.maxValue = pNMMetadata.getMaxValue();
                for (int i20 : sampleSize) {
                    int i21 = (1 << i20) - 1;
                    if (i21 > this.maxValue) {
                        this.maxValue = i21;
                    }
                }
                if (!isRaw) {
                    int maxBitDepth = pNMMetadata.getMaxBitDepth();
                    if (isRaw(this.variant)) {
                        i14 = 8;
                    } else {
                        i14 = 8;
                        if (maxBitDepth <= 8) {
                            this.variant += 3;
                        }
                    }
                    if (isRaw(this.variant) && maxBitDepth > i14) {
                        this.variant -= 3;
                    }
                } else if (isRaw(this.variant)) {
                    this.variant -= 3;
                }
                this.stream.writeByte(80);
                this.stream.writeByte(this.variant);
                this.stream.write(lineSeparator);
                this.stream.write(COMMENT.getBytes());
                comments = pNMMetadata.getComments();
                if (comments != null) {
                    while (comments.hasNext()) {
                        this.stream.write(lineSeparator);
                        this.stream.write(("# " + ((String) comments.next())).getBytes());
                    }
                }
                this.stream.write(lineSeparator);
                writeInteger(this.stream, i17);
                this.stream.write(32);
                writeInteger(this.stream, i18);
                i3 = this.variant;
                if (i3 != 52 && i3 != 49) {
                    this.stream.write(lineSeparator);
                    writeInteger(this.stream, this.maxValue);
                }
                i4 = this.variant;
                if (i4 != 52 || i4 == 53 || i4 == 54) {
                    this.stream.write(10);
                }
                if (this.variant != 52 && sampleModel.getTransferType() == 0 && (sampleModel instanceof MultiPixelPackedSampleModel)) {
                    MultiPixelPackedSampleModel multiPixelPackedSampleModel = (MultiPixelPackedSampleModel) sampleModel;
                    if (hasRaster) {
                        minX = raster.getMinX();
                    } else {
                        minX = renderedImage.getMinX();
                    }
                    rectangle2 = rectangle;
                    if (multiPixelPackedSampleModel.getBitOffset((rectangle2.x - minX) % i) == 0 && multiPixelPackedSampleModel.getPixelBitStride() == 1) {
                        i5 = i2;
                        if (i5 == 1) {
                            z4 = true;
                            z3 = true;
                        }
                    } else {
                        i5 = i2;
                    }
                    z4 = false;
                    z3 = true;
                } else {
                    i5 = i2;
                    rectangle2 = rectangle;
                    i6 = this.variant;
                    if ((i6 != 53 || i6 == 54) && (sampleModel instanceof ComponentSampleModel) && !(colorModel instanceof IndexColorModel)) {
                        componentSampleModel = (ComponentSampleModel) sampleModel;
                        if (componentSampleModel.getPixelStride() == numBands) {
                            z3 = true;
                            if (i5 == 1) {
                                if (this.variant == 54) {
                                    int[] bandOffsets = componentSampleModel.getBandOffsets();
                                    for (int i22 = 0; i22 < numBands; i22++) {
                                        if (bandOffsets[i22] == i22) {
                                        }
                                    }
                                }
                                z4 = true;
                            }
                            z4 = false;
                            break;
                        }
                    }
                    z3 = true;
                    z4 = false;
                    break;
                }
                if (!z4) {
                    int numBands2 = this.variant == 52 ? (i17 + 7) / 8 : sampleModel.getNumBands() * i17;
                    byte[] bArr8 = new byte[numBands2];
                    int i23 = 0;
                    while (i23 < rectangle2.height && !abortRequested()) {
                        if (hasRaster) {
                            rectangle3 = rectangle2;
                            createTranslatedChild = raster.createChild(rectangle2.x, i23, rectangle2.width, 1, 0, 0, (int[]) null);
                            i13 = i17;
                        } else {
                            rectangle3 = rectangle2;
                            i13 = i17;
                            createTranslatedChild = renderedImage.getData(new Rectangle(rectangle3.x, rectangle3.y + i23, i13, 1)).createTranslatedChild(0, 0);
                        }
                        byte[] data2 = createTranslatedChild.getDataBuffer().getData();
                        ComponentSampleModel sampleModel2 = createTranslatedChild.getSampleModel();
                        if (sampleModel2 instanceof ComponentSampleModel) {
                            offset = sampleModel2.getOffset(createTranslatedChild.getMinX() - createTranslatedChild.getSampleModelTranslateX(), createTranslatedChild.getMinY() - createTranslatedChild.getSampleModelTranslateY());
                        } else {
                            offset = sampleModel2 instanceof MultiPixelPackedSampleModel ? ((MultiPixelPackedSampleModel) sampleModel2).getOffset(createTranslatedChild.getMinX() - createTranslatedChild.getSampleModelTranslateX(), createTranslatedChild.getMinX() - createTranslatedChild.getSampleModelTranslateY()) : 0;
                        }
                        if (z2) {
                            for (int i24 = 0; i24 < numBands2; i24++) {
                                bArr8[i24] = (byte) (~data2[offset]);
                                offset++;
                            }
                            data2 = bArr8;
                            offset = 0;
                        }
                        this.stream.write(data2, offset, numBands2);
                        processImageProgress((i23 * 100.0f) / rectangle3.height);
                        i23++;
                        i17 = i13;
                        z3 = true;
                        rectangle2 = rectangle3;
                    }
                    this.stream.flush();
                    if (abortRequested()) {
                        processWriteAborted();
                        return;
                    } else {
                        processImageComplete();
                        return;
                    }
                }
                Rectangle rectangle5 = rectangle2;
                int i25 = i17;
                int i26 = rectangle5.width * numBands;
                int[] iArr4 = new int[i26];
                byte[] bArr9 = new byte[bArr4 == null ? i25 * numBands : i25 * 3];
                int i27 = rectangle5.y + rectangle5.height;
                int i28 = rectangle5.y;
                int i29 = 0;
                while (i28 < i27 && !abortRequested()) {
                    if (hasRaster) {
                        i8 = i27;
                        i7 = i5;
                        bArr5 = bArr9;
                        iArr2 = iArr4;
                        i9 = i26;
                        i11 = i28;
                        z5 = hasRaster;
                        i10 = i25;
                        data = raster.createChild(rectangle5.x, i28, rectangle5.width, 1, rectangle5.x, i11, iArr);
                    } else {
                        i7 = i5;
                        z5 = hasRaster;
                        i8 = i27;
                        bArr5 = bArr9;
                        iArr2 = iArr4;
                        i9 = i26;
                        i10 = i25;
                        i11 = i28;
                        data = renderedImage.getData(new Rectangle(rectangle5.x, i11, rectangle5.width, 1));
                    }
                    data.getPixels(rectangle5.x, i11, rectangle5.width, 1, iArr2);
                    if (z2) {
                        for (int i30 = 0; i30 < i9; i30 += i7) {
                            bArr5[i30] = (byte) (bArr5[i30] ^ 1);
                        }
                    }
                    switch (this.variant) {
                        case 49:
                        case 50:
                            int i31 = 0;
                            while (i31 < i9) {
                                int i32 = i29 + 1;
                                if (i29 % 16 == 0) {
                                    this.stream.write(lineSeparator);
                                } else {
                                    this.stream.write(32);
                                }
                                writeInteger(this.stream, iArr2[i31]);
                                i31 += i7;
                                i29 = i32;
                            }
                            c = ' ';
                            this.stream.write(lineSeparator);
                            continue;
                        case 51:
                            if (bArr4 == null) {
                                for (int i33 = 0; i33 < i9; i33 += i7 * numBands) {
                                    int i34 = 0;
                                    while (i34 < numBands) {
                                        int i35 = i29 + 1;
                                        if (i29 % 16 == 0) {
                                            this.stream.write(lineSeparator);
                                        } else {
                                            this.stream.write(32);
                                        }
                                        writeInteger(this.stream, iArr2[i33 + i34]);
                                        i34++;
                                        i29 = i35;
                                    }
                                }
                            } else {
                                int i36 = 0;
                                while (i36 < i9) {
                                    int i37 = i29 + 1;
                                    if (i29 % 5 == 0) {
                                        this.stream.write(lineSeparator);
                                        i12 = 32;
                                    } else {
                                        i12 = 32;
                                        this.stream.write(32);
                                    }
                                    writeInteger(this.stream, bArr4[iArr2[i36]] & 255);
                                    this.stream.write(i12);
                                    writeInteger(this.stream, bArr2[iArr2[i36]] & 255);
                                    this.stream.write(i12);
                                    writeInteger(this.stream, bArr3[iArr2[i36]] & 255);
                                    i36 += i7;
                                    i29 = i37;
                                }
                            }
                            this.stream.write(lineSeparator);
                            break;
                        case 52:
                            int i38 = 7;
                            int i39 = 0;
                            int i40 = 0;
                            for (int i41 = 0; i41 < i9; i41 += i7) {
                                i39 |= iArr2[i41] << i38;
                                i38--;
                                if (i38 == -1) {
                                    bArr5[i40] = (byte) i39;
                                    i40++;
                                    i39 = 0;
                                    i38 = 7;
                                }
                            }
                            if (i38 != 7) {
                                bArr5[i40] = (byte) i39;
                                i40++;
                            }
                            this.stream.write(bArr5, 0, i40);
                            break;
                        case 53:
                            int i42 = 0;
                            int i43 = 0;
                            while (i42 < i9) {
                                bArr5[i43] = (byte) iArr2[i42];
                                i42 += i7;
                                i43++;
                            }
                            this.stream.write(bArr5, 0, i10);
                            break;
                        case 54:
                            if (bArr4 == null) {
                                int i44 = 0;
                                for (int i45 = 0; i45 < i9; i45 += i7 * numBands) {
                                    int i46 = 0;
                                    while (i46 < numBands) {
                                        bArr5[i44] = (byte) (iArr2[i45 + i46] & 255);
                                        i46++;
                                        i44++;
                                    }
                                }
                            } else {
                                int i47 = 0;
                                int i48 = 0;
                                while (i47 < i9) {
                                    int i49 = i48 + 1;
                                    bArr5[i48] = bArr4[iArr2[i47]];
                                    int i50 = i49 + 1;
                                    bArr5[i49] = bArr2[iArr2[i47]];
                                    bArr5[i50] = bArr3[iArr2[i47]];
                                    i47 += i7;
                                    i48 = i50 + 1;
                                }
                            }
                            this.stream.write(bArr5, 0, bArr5.length);
                            break;
                    }
                    c = ' ';
                    processImageProgress(((i11 - rectangle5.y) * 100.0f) / rectangle5.height);
                    int i51 = i11 + sourceYSubsampling;
                    bArr9 = bArr5;
                    i25 = i10;
                    i26 = i9;
                    i28 = i51;
                    iArr4 = iArr2;
                    i5 = i7;
                    i27 = i8;
                    hasRaster = z5;
                }
                this.stream.flush();
                if (abortRequested()) {
                    processWriteAborted();
                    return;
                } else {
                    processImageComplete();
                    return;
                }
            }
            rectangle = intersection;
            i = width;
            i2 = sourceXSubsampling;
            if (sampleSize[0] == 1) {
                this.variant = 52;
            } else if (sampleSize[0] <= 8) {
                this.variant = 53;
            } else {
                this.variant = 50;
            }
        }
        z = false;
        bArr = null;
        bArr2 = null;
        bArr3 = null;
        metadata = iIOImage.getMetadata();
        if (colorModel == null) {
        }
        if (metadata == null) {
        }
        if (!(defaultWriteParam instanceof PNMImageWriteParam)) {
        }
        this.maxValue = pNMMetadata.getMaxValue();
        while (r5 < sampleSize.length) {
        }
        if (!isRaw) {
        }
        this.stream.writeByte(80);
        this.stream.writeByte(this.variant);
        this.stream.write(lineSeparator);
        this.stream.write(COMMENT.getBytes());
        comments = pNMMetadata.getComments();
        if (comments != null) {
        }
        this.stream.write(lineSeparator);
        writeInteger(this.stream, i17);
        this.stream.write(32);
        writeInteger(this.stream, i18);
        i3 = this.variant;
        if (i3 != 52) {
            this.stream.write(lineSeparator);
            writeInteger(this.stream, this.maxValue);
        }
        i4 = this.variant;
        if (i4 != 52) {
        }
        this.stream.write(10);
        if (this.variant != 52) {
        }
        i5 = i2;
        rectangle2 = rectangle;
        i6 = this.variant;
        if (i6 != 53) {
        }
        componentSampleModel = (ComponentSampleModel) sampleModel;
        if (componentSampleModel.getPixelStride() == numBands) {
        }
        z3 = true;
        z4 = false;
        if (!z4) {
        }
    }

    public void reset() {
        super.reset();
        this.stream = null;
    }

    private void writeInteger(ImageOutputStream imageOutputStream, int i) throws IOException {
        imageOutputStream.write(Integer.toString(i).getBytes());
    }

    private void writeByte(ImageOutputStream imageOutputStream, byte b) throws IOException {
        imageOutputStream.write(Byte.toString(b).getBytes());
    }
}
