package com.github.jaiimageio.impl.plugins.raw;

import com.github.jaiimageio.impl.common.ImageUtil;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.ComponentSampleModel;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferDouble;
import java.awt.image.DataBufferFloat;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferShort;
import java.awt.image.DataBufferUShort;
import java.awt.image.MultiPixelPackedSampleModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageOutputStream;

/* loaded from: classes3.dex */
public class RawImageWriter extends ImageWriter {
    private int bandStride;
    private Rectangle destinationRegion;
    private int imageIndex;
    private RenderedImage input;
    private Raster inputRaster;
    private int lineStride;
    private boolean noSubband;
    private boolean noTransform;
    private int numBands;
    private boolean optimal;
    private int pxlStride;
    private SampleModel sampleModel;
    private int scaleX;
    private int scaleY;
    private int[] sourceBands;
    private ImageOutputStream stream;
    private int tileHeight;
    private int tileWidth;
    private int tileXOffset;
    private int tileYOffset;
    private boolean writeRaster;
    private int xOffset;
    private int yOffset;

    public boolean canWriteRasters() {
        return true;
    }

    public IIOMetadata convertImageMetadata(IIOMetadata iIOMetadata, ImageTypeSpecifier imageTypeSpecifier, ImageWriteParam imageWriteParam) {
        return null;
    }

    public IIOMetadata convertStreamMetadata(IIOMetadata iIOMetadata, ImageWriteParam imageWriteParam) {
        return null;
    }

    public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageTypeSpecifier, ImageWriteParam imageWriteParam) {
        return null;
    }

    public IIOMetadata getDefaultStreamMetadata(ImageWriteParam imageWriteParam) {
        return null;
    }

    public RawImageWriter(ImageWriterSpi imageWriterSpi) {
        super(imageWriterSpi);
        this.stream = null;
        this.sourceBands = null;
        this.destinationRegion = null;
        this.noTransform = true;
        this.noSubband = true;
        this.writeRaster = false;
        this.optimal = false;
    }

    public void setOutput(Object obj) {
        super.setOutput(obj);
        if (obj != null) {
            if (!(obj instanceof ImageOutputStream)) {
                throw new IllegalArgumentException(I18N.getString("RawImageWriter0"));
            }
            this.stream = (ImageOutputStream) obj;
            return;
        }
        this.stream = null;
    }

    public ImageWriteParam getDefaultWriteParam() {
        return new RawImageWriteParam(getLocale());
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x01b5, code lost:
    
        if (r0 == (r5.numBands * r6.getHeight())) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01b9, code lost:
    
        if ((r6 instanceof java.awt.image.BandedSampleModel) != false) goto L62;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void write(IIOMetadata iIOMetadata, IIOImage iIOImage, ImageWriteParam imageWriteParam) throws IOException {
        Rectangle rectangle;
        Rectangle intersection;
        int i;
        clearAbortRequest();
        int i2 = this.imageIndex;
        this.imageIndex = i2 + 1;
        processImageStarted(i2);
        if (imageWriteParam == null) {
            imageWriteParam = getDefaultWriteParam();
        }
        this.writeRaster = iIOImage.hasRaster();
        Rectangle sourceRegion = imageWriteParam.getSourceRegion();
        if (this.writeRaster) {
            Raster raster = iIOImage.getRaster();
            this.inputRaster = raster;
            this.sampleModel = raster.getSampleModel();
            rectangle = this.inputRaster.getBounds();
        } else {
            RenderedImage renderedImage = iIOImage.getRenderedImage();
            this.input = renderedImage;
            this.sampleModel = renderedImage.getSampleModel();
            rectangle = new Rectangle(this.input.getMinX(), this.input.getMinY(), this.input.getWidth(), this.input.getHeight());
            this.input.getColorModel();
        }
        if (sourceRegion == null) {
            intersection = (Rectangle) rectangle.clone();
        } else {
            intersection = sourceRegion.intersection(rectangle);
        }
        if (intersection.isEmpty()) {
            throw new RuntimeException(I18N.getString("RawImageWriter1"));
        }
        this.scaleX = imageWriteParam.getSourceXSubsampling();
        this.scaleY = imageWriteParam.getSourceYSubsampling();
        this.xOffset = imageWriteParam.getSubsamplingXOffset();
        int subsamplingYOffset = imageWriteParam.getSubsamplingYOffset();
        this.yOffset = subsamplingYOffset;
        intersection.translate(this.xOffset, subsamplingYOffset);
        intersection.width -= this.xOffset;
        intersection.height -= this.yOffset;
        this.xOffset = intersection.x % this.scaleX;
        this.yOffset = intersection.y % this.scaleY;
        int i3 = intersection.x / this.scaleX;
        int i4 = intersection.y / this.scaleY;
        int i5 = intersection.width;
        int i6 = this.scaleX;
        int i7 = ((i5 + i6) - 1) / i6;
        int i8 = intersection.height;
        int i9 = this.scaleY;
        Rectangle rectangle2 = new Rectangle(i3, i4, i7, ((i8 + i9) - 1) / i9);
        this.destinationRegion = rectangle2;
        this.noTransform = rectangle2.equals(rectangle);
        this.tileHeight = this.sampleModel.getHeight();
        this.tileWidth = this.sampleModel.getWidth();
        if (this.noTransform) {
            if (this.writeRaster) {
                this.tileXOffset = this.inputRaster.getMinX();
                this.tileYOffset = this.inputRaster.getMinY();
            } else {
                this.tileXOffset = this.input.getTileGridXOffset();
                this.tileYOffset = this.input.getTileGridYOffset();
            }
        } else {
            this.tileXOffset = this.destinationRegion.x;
            this.tileYOffset = this.destinationRegion.y;
        }
        this.sourceBands = imageWriteParam.getSourceBands();
        int numBands = this.sampleModel.getNumBands();
        this.numBands = numBands;
        int[] iArr = this.sourceBands;
        boolean z = false;
        if (iArr != null) {
            SampleModel createSubsetSampleModel = this.sampleModel.createSubsetSampleModel(iArr);
            this.sampleModel = createSubsetSampleModel;
            this.numBands = createSubsetSampleModel.getNumBands();
        } else {
            this.sourceBands = new int[numBands];
            for (int i10 = 0; i10 < this.numBands; i10++) {
                this.sourceBands[i10] = i10;
            }
        }
        ComponentSampleModel componentSampleModel = this.sampleModel;
        if (componentSampleModel instanceof ComponentSampleModel) {
            ComponentSampleModel componentSampleModel2 = componentSampleModel;
            int[] bandOffsets = componentSampleModel2.getBandOffsets();
            this.bandStride = bandOffsets[0];
            for (int i11 = 1; i11 < bandOffsets.length; i11++) {
                if (this.bandStride > bandOffsets[i11]) {
                    this.bandStride = bandOffsets[i11];
                }
            }
            int[] bankIndices = componentSampleModel2.getBankIndices();
            int i12 = bankIndices[0];
            for (int i13 = 1; i13 < bankIndices.length; i13++) {
                if (i12 > bankIndices[i13]) {
                    i12 = bankIndices[i13];
                }
            }
            this.pxlStride = componentSampleModel2.getPixelStride();
            int scanlineStride = componentSampleModel2.getScanlineStride();
            this.lineStride = scanlineStride;
            if (this.bandStride != 0 && (((i = this.pxlStride) >= scanlineStride || i != this.numBands) && ((scanlineStride >= i || scanlineStride != this.numBands) && (i >= scanlineStride || scanlineStride != this.numBands * componentSampleModel2.getWidth())))) {
                int i14 = this.lineStride;
                int i15 = this.pxlStride;
                if (i14 < i15) {
                }
            }
            z = true;
            this.optimal = z;
        } else if ((componentSampleModel instanceof SinglePixelPackedSampleModel) || (componentSampleModel instanceof MultiPixelPackedSampleModel)) {
            this.optimal = true;
        }
        int maxTileY = ((getMaxTileY() - getMinTileY()) + 1) * ((getMaxTileX() - getMinTileX()) + 1);
        for (int minTileY = getMinTileY(); minTileY <= getMaxTileY(); minTileY++) {
            for (int minTileX = getMinTileX(); minTileX <= getMaxTileX(); minTileX++) {
                writeRaster(getTile(minTileX, minTileY));
                processImageProgress(((((minTileY * r6) + minTileX) + 1.0f) / maxTileY) * 100.0f);
            }
        }
        this.stream.flush();
        if (abortRequested()) {
            processWriteAborted();
        } else {
            processImageComplete();
        }
    }

    public int getWidth() {
        return this.destinationRegion.width;
    }

    public int getHeight() {
        return this.destinationRegion.height;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x018d  */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v19 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r17v5 */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r1v38, types: [int[]] */
    /* JADX WARN: Type inference failed for: r1v40, types: [float[]] */
    /* JADX WARN: Type inference failed for: r1v57 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9, types: [double[]] */
    /* JADX WARN: Type inference failed for: r5v31, types: [int[]] */
    /* JADX WARN: Type inference failed for: r5v34, types: [float[]] */
    /* JADX WARN: Type inference failed for: r5v37, types: [double[]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void writeRaster(Raster raster) throws IOException {
        int i;
        int i2;
        int i3;
        int[] iArr;
        int[] iArr2;
        Raster raster2;
        byte[] data;
        ?? r10;
        short[] sArr;
        short[] sArr2;
        short[] sArr3;
        int i4;
        int i5;
        int i6;
        byte[] bArr;
        short[] sArr4;
        byte[] bArr2;
        short[] sArr5;
        int i7;
        short[] sArr6;
        int i8;
        short[] sArr7;
        short[] sArr8;
        short[] sArr9;
        ?? r1;
        short[] sArr10;
        short[] data2;
        byte[] data3;
        float[] fArr;
        int[] iArr3;
        short[] sArr11;
        double[] dArr;
        short[] data4;
        int numBands = this.sampleModel.getNumBands();
        int dataType = this.sampleModel.getDataType();
        ComponentSampleModel componentSampleModel = this.sampleModel;
        if (componentSampleModel instanceof ComponentSampleModel) {
            ComponentSampleModel componentSampleModel2 = componentSampleModel;
            iArr2 = componentSampleModel2.getBandOffsets();
            i2 = 0;
            for (int i9 = 0; i9 < numBands; i9++) {
                if (i2 < iArr2[i9]) {
                    i2 = iArr2[i9];
                }
            }
            iArr = componentSampleModel2.getBankIndices();
            i3 = 0;
            for (int i10 = 0; i10 < numBands; i10++) {
                if (i3 < iArr[i10]) {
                    i3 = iArr[i10];
                }
            }
            i = (int) ImageUtil.getBandSize(this.sampleModel);
        } else {
            i = 0;
            i2 = 0;
            i3 = 0;
            iArr = null;
            iArr2 = null;
        }
        if (raster.getParent() == null || this.sampleModel.equals(raster.getParent().getSampleModel())) {
            raster2 = raster;
        } else {
            raster2 = Raster.createWritableRaster(this.sampleModel, new Point(raster.getMinX(), raster.getMinY()));
            raster2.setRect(raster);
        }
        DataBufferDouble dataBuffer = raster2.getDataBuffer();
        if (this.optimal) {
            if (i3 > 0) {
                for (int i11 = 0; i11 < this.numBands; i11++) {
                    int i12 = iArr[this.sourceBands[i11]];
                    if (dataType == 0) {
                        byte[] data5 = ((DataBufferByte) dataBuffer).getData(i12);
                        this.stream.write(data5, 0, data5.length);
                    } else if (dataType == 1) {
                        short[] data6 = ((DataBufferUShort) dataBuffer).getData(i12);
                        this.stream.writeShorts(data6, 0, data6.length);
                    } else if (dataType == 2) {
                        short[] data7 = ((DataBufferShort) dataBuffer).getData(i12);
                        this.stream.writeShorts(data7, 0, data7.length);
                    } else if (dataType == 3) {
                        int[] data8 = ((DataBufferInt) dataBuffer).getData(i12);
                        this.stream.writeInts(data8, 0, data8.length);
                    } else if (dataType == 4) {
                        float[] data9 = ((DataBufferFloat) dataBuffer).getData(i12);
                        this.stream.writeFloats(data9, 0, data9.length);
                    } else if (dataType == 5) {
                        double[] data10 = dataBuffer.getData(i12);
                        this.stream.writeDoubles(data10, 0, data10.length);
                    }
                }
                return;
            }
            if (dataType == 0) {
                data3 = ((DataBufferByte) dataBuffer).getData();
            } else {
                if (dataType == 1) {
                    data4 = ((DataBufferUShort) dataBuffer).getData();
                } else if (dataType == 2) {
                    data4 = ((DataBufferShort) dataBuffer).getData();
                } else if (dataType == 3) {
                    iArr3 = ((DataBufferInt) dataBuffer).getData();
                    data3 = null;
                    dArr = null;
                    sArr11 = null;
                    fArr = null;
                    if (this.noSubband) {
                    }
                    if (dataType == 0) {
                    }
                } else if (dataType == 4) {
                    fArr = ((DataBufferFloat) dataBuffer).getData();
                    data3 = null;
                    dArr = null;
                    sArr11 = null;
                    iArr3 = null;
                    if (this.noSubband) {
                    }
                    if (dataType == 0) {
                    }
                } else {
                    if (dataType == 5) {
                        dArr = dataBuffer.getData();
                        data3 = null;
                        sArr11 = null;
                        iArr3 = null;
                        fArr = null;
                        if (this.noSubband && i2 >= raster2.getWidth() * raster2.getHeight() * (this.numBands - 1)) {
                            for (int i13 = 0; i13 < this.numBands; i13++) {
                                int i14 = iArr2[this.sourceBands[i13]];
                                if (dataType == 0) {
                                    this.stream.write(data3, i14, i);
                                } else if (dataType == 1 || dataType == 2) {
                                    this.stream.writeShorts(sArr11, i14, i);
                                } else if (dataType == 3) {
                                    this.stream.writeInts(iArr3, i14, i);
                                } else if (dataType == 4) {
                                    this.stream.writeFloats(fArr, i14, i);
                                } else if (dataType == 5) {
                                    this.stream.writeDoubles(dArr, i14, i);
                                }
                            }
                            return;
                        }
                        if (dataType == 0) {
                            this.stream.write(data3, 0, data3.length);
                            return;
                        }
                        if (dataType == 1 || dataType == 2) {
                            this.stream.writeShorts(sArr11, 0, sArr11.length);
                            return;
                        }
                        if (dataType == 3) {
                            this.stream.writeInts(iArr3, 0, iArr3.length);
                            return;
                        } else if (dataType == 4) {
                            this.stream.writeFloats(fArr, 0, fArr.length);
                            return;
                        } else {
                            if (dataType != 5) {
                                return;
                            }
                            this.stream.writeDoubles(dArr, 0, dArr.length);
                            return;
                        }
                    }
                    data3 = null;
                }
                sArr11 = data4;
                data3 = null;
                dArr = null;
                iArr3 = null;
                fArr = null;
                if (this.noSubband) {
                }
                if (dataType == 0) {
                }
            }
            dArr = null;
            sArr11 = null;
            iArr3 = null;
            fArr = null;
            if (this.noSubband) {
            }
            if (dataType == 0) {
            }
        } else {
            if (!(this.sampleModel instanceof ComponentSampleModel)) {
                return;
            }
            if (dataType == 0) {
                data = ((DataBufferByte) dataBuffer).getData();
            } else {
                if (dataType == 1) {
                    data2 = ((DataBufferUShort) dataBuffer).getData();
                } else if (dataType == 2) {
                    data2 = ((DataBufferShort) dataBuffer).getData();
                } else if (dataType == 3) {
                    sArr2 = ((DataBufferInt) dataBuffer).getData();
                    data = null;
                    sArr3 = null;
                    sArr = null;
                    r10 = 0;
                    int offset = this.sampleModel.getOffset(raster2.getMinX() - raster2.getSampleModelTranslateX(), raster2.getMinY() - raster2.getSampleModelTranslateY()) - iArr2[0];
                    i4 = this.pxlStride;
                    int width = raster2.getWidth();
                    int height = raster2.getHeight();
                    i5 = this.lineStride;
                    if (i4 < i5) {
                    }
                    int i15 = this.numBands * width;
                    if (dataType == 0) {
                    }
                    if (i6 > i8) {
                    }
                } else if (dataType == 4) {
                    sArr = ((DataBufferFloat) dataBuffer).getData();
                    data = null;
                    sArr3 = null;
                    sArr2 = null;
                    r10 = 0;
                    int offset2 = this.sampleModel.getOffset(raster2.getMinX() - raster2.getSampleModelTranslateX(), raster2.getMinY() - raster2.getSampleModelTranslateY()) - iArr2[0];
                    i4 = this.pxlStride;
                    int width2 = raster2.getWidth();
                    int height2 = raster2.getHeight();
                    i5 = this.lineStride;
                    if (i4 < i5) {
                    }
                    int i152 = this.numBands * width2;
                    if (dataType == 0) {
                    }
                    if (i6 > i8) {
                    }
                } else {
                    if (dataType == 5) {
                        r10 = dataBuffer.getData();
                        data = null;
                        sArr3 = null;
                        sArr2 = null;
                        sArr = null;
                        int offset22 = this.sampleModel.getOffset(raster2.getMinX() - raster2.getSampleModelTranslateX(), raster2.getMinY() - raster2.getSampleModelTranslateY()) - iArr2[0];
                        i4 = this.pxlStride;
                        int width22 = raster2.getWidth();
                        int height22 = raster2.getHeight();
                        i5 = this.lineStride;
                        if (i4 < i5) {
                            i6 = i2 > this.pxlStride ? width22 : 1;
                            i5 = i4;
                            i4 = i5;
                        } else {
                            i6 = i2 > i5 ? height22 : 1;
                            width22 = height22;
                            height22 = width22;
                        }
                        int i1522 = this.numBands * width22;
                        if (dataType == 0) {
                            bArr = data;
                            sArr4 = sArr3;
                            bArr2 = new byte[i1522];
                            sArr5 = sArr2;
                            i7 = offset22;
                            sArr6 = null;
                            i8 = 1;
                            sArr7 = null;
                            sArr8 = null;
                            sArr9 = bArr2;
                            r1 = 0;
                            sArr10 = bArr;
                        } else if (dataType == 1 || dataType == 2) {
                            bArr = data;
                            sArr4 = sArr3;
                            sArr7 = new short[i1522];
                            sArr5 = sArr2;
                            i7 = offset22;
                            r1 = 0;
                            i8 = 1;
                            bArr2 = null;
                            sArr8 = null;
                            sArr9 = sArr7;
                            sArr10 = sArr4;
                            sArr6 = null;
                        } else if (dataType == 3) {
                            bArr = data;
                            sArr4 = sArr3;
                            sArr8 = new int[i1522];
                            sArr5 = sArr2;
                            i7 = offset22;
                            r1 = 0;
                            sArr6 = null;
                            bArr2 = null;
                            sArr7 = null;
                            sArr9 = sArr8;
                            sArr10 = sArr5;
                            i8 = 1;
                        } else if (dataType == 4) {
                            bArr = data;
                            sArr4 = sArr3;
                            sArr6 = new float[i1522];
                            sArr5 = sArr2;
                            i7 = offset22;
                            r1 = 0;
                            i8 = 1;
                            bArr2 = null;
                            sArr7 = null;
                            sArr8 = null;
                            sArr9 = sArr6;
                            sArr10 = sArr;
                        } else if (dataType != 5) {
                            bArr = data;
                            sArr4 = sArr3;
                            sArr5 = sArr2;
                            i7 = offset22;
                            r1 = 0;
                            sArr6 = null;
                            sArr10 = null;
                            i8 = 1;
                            sArr9 = null;
                            bArr2 = null;
                            sArr7 = null;
                            sArr8 = null;
                        } else {
                            bArr = data;
                            sArr4 = sArr3;
                            short[] sArr12 = new double[i1522];
                            sArr5 = sArr2;
                            i7 = offset22;
                            sArr6 = null;
                            i8 = 1;
                            bArr2 = null;
                            sArr7 = null;
                            sArr8 = null;
                            sArr9 = sArr12;
                            sArr10 = r10;
                            r1 = sArr12;
                        }
                        if (i6 > i8) {
                            for (int i16 = 0; i16 < height22; i16++) {
                                for (int i17 = 0; i17 < this.numBands; i17++) {
                                    System.arraycopy(sArr10, i7 + iArr2[i17], sArr9, i17 * width22, width22);
                                }
                                if (dataType == 0) {
                                    this.stream.write((byte[]) sArr9, 0, i1522);
                                } else if (dataType == 1 || dataType == 2) {
                                    this.stream.writeShorts(sArr9, 0, i1522);
                                } else if (dataType == 3) {
                                    this.stream.writeInts(sArr9, 0, i1522);
                                } else if (dataType == 4) {
                                    this.stream.writeFloats(sArr9, 0, i1522);
                                } else if (dataType == 5) {
                                    this.stream.writeDoubles(sArr9, 0, i1522);
                                }
                                i7 += i4;
                            }
                            return;
                        }
                        if (dataType == 0) {
                            int i18 = i7;
                            int i19 = 0;
                            while (i19 < height22) {
                                int i20 = 0;
                                int i21 = 0;
                                while (i20 < this.numBands) {
                                    int i22 = iArr2[i20];
                                    int i23 = i18;
                                    int i24 = 0;
                                    while (i24 < width22) {
                                        bArr2[i21] = bArr[i23 + i22];
                                        i24++;
                                        i23 += i5;
                                        i21++;
                                        iArr2 = iArr2;
                                    }
                                    i20++;
                                    iArr2 = iArr2;
                                }
                                this.stream.write(bArr2, 0, i1522);
                                i18 += i4;
                                i19++;
                                iArr2 = iArr2;
                            }
                            return;
                        }
                        if (dataType == 1 || dataType == 2) {
                            int i25 = i7;
                            for (int i26 = 0; i26 < height22; i26++) {
                                int i27 = 0;
                                for (int i28 = 0; i28 < this.numBands; i28++) {
                                    int i29 = iArr2[i28];
                                    int i30 = i25;
                                    int i31 = 0;
                                    while (i31 < width22) {
                                        sArr7[i27] = sArr4[i30 + i29];
                                        i31++;
                                        i30 += i5;
                                        i27++;
                                    }
                                }
                                this.stream.writeShorts(sArr7, 0, i1522);
                                i25 += i4;
                            }
                            return;
                        }
                        if (dataType == 3) {
                            int i32 = i7;
                            for (int i33 = 0; i33 < height22; i33++) {
                                int i34 = 0;
                                for (int i35 = 0; i35 < this.numBands; i35++) {
                                    int i36 = iArr2[i35];
                                    int i37 = i32;
                                    int i38 = 0;
                                    while (i38 < width22) {
                                        sArr8[i34] = sArr5[i37 + i36];
                                        i38++;
                                        i37 += i5;
                                        i34++;
                                    }
                                }
                                this.stream.writeInts(sArr8, 0, i1522);
                                i32 += i4;
                            }
                            return;
                        }
                        if (dataType == 4) {
                            int i39 = i7;
                            for (int i40 = 0; i40 < height22; i40++) {
                                int i41 = 0;
                                for (int i42 = 0; i42 < this.numBands; i42++) {
                                    int i43 = iArr2[i42];
                                    int i44 = i39;
                                    int i45 = 0;
                                    while (i45 < width22) {
                                        sArr6[i41] = sArr[i44 + i43];
                                        i45++;
                                        i44 += i5;
                                        i41++;
                                    }
                                }
                                this.stream.writeFloats(sArr6, 0, i1522);
                                i39 += i4;
                            }
                            return;
                        }
                        if (dataType != 5) {
                            return;
                        }
                        int i46 = i7;
                        for (int i47 = 0; i47 < height22; i47++) {
                            int i48 = 0;
                            for (int i49 = 0; i49 < this.numBands; i49++) {
                                int i50 = iArr2[i49];
                                int i51 = i46;
                                int i52 = 0;
                                while (i52 < width22) {
                                    r1[i48] = r10[i51 + i50];
                                    i52++;
                                    i51 += i5;
                                    i48++;
                                }
                            }
                            this.stream.writeDoubles((double[]) r1, 0, i1522);
                            i46 += i4;
                        }
                        return;
                    }
                    data = null;
                }
                sArr3 = data2;
                data = null;
                sArr2 = null;
                sArr = null;
                r10 = 0;
                int offset222 = this.sampleModel.getOffset(raster2.getMinX() - raster2.getSampleModelTranslateX(), raster2.getMinY() - raster2.getSampleModelTranslateY()) - iArr2[0];
                i4 = this.pxlStride;
                int width222 = raster2.getWidth();
                int height222 = raster2.getHeight();
                i5 = this.lineStride;
                if (i4 < i5) {
                }
                int i15222 = this.numBands * width222;
                if (dataType == 0) {
                }
                if (i6 > i8) {
                }
            }
            sArr3 = null;
            sArr2 = null;
            sArr = null;
            r10 = 0;
            int offset2222 = this.sampleModel.getOffset(raster2.getMinX() - raster2.getSampleModelTranslateX(), raster2.getMinY() - raster2.getSampleModelTranslateY()) - iArr2[0];
            i4 = this.pxlStride;
            int width2222 = raster2.getWidth();
            int height2222 = raster2.getHeight();
            i5 = this.lineStride;
            if (i4 < i5) {
            }
            int i152222 = this.numBands * width2222;
            if (dataType == 0) {
            }
            if (i6 > i8) {
            }
        }
    }

    private Raster getTile(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        Rectangle rectangle = new Rectangle(this.tileXOffset + (this.tileWidth * i), this.tileYOffset + (this.tileHeight * i2), this.tileWidth, this.tileHeight);
        int i8 = 1;
        if (this.writeRaster) {
            Rectangle intersection = rectangle.intersection(this.destinationRegion);
            if (this.noTransform) {
                return this.inputRaster.createChild(intersection.x, intersection.y, intersection.width, intersection.height, intersection.x, intersection.y, this.sourceBands);
            }
            int i9 = intersection.x;
            int i10 = intersection.y;
            WritableRaster createWritableRaster = Raster.createWritableRaster(this.sampleModel, new Point(i9, i10));
            int mapToSourceX = mapToSourceX(i9);
            int mapToSourceY = mapToSourceY(i10);
            int minY = this.inputRaster.getMinY();
            int minY2 = this.inputRaster.getMinY() + this.inputRaster.getHeight();
            int i11 = intersection.width;
            int i12 = 1 + ((i11 - 1) * this.scaleX);
            int i13 = mapToSourceY;
            int i14 = 0;
            while (i14 < intersection.height) {
                if (i13 < minY || i13 >= minY2) {
                    i3 = i14;
                    i4 = i11;
                    i5 = minY2;
                    i6 = minY;
                    i7 = i13;
                } else {
                    i3 = i14;
                    int i15 = i13;
                    i4 = i11;
                    i5 = minY2;
                    i6 = minY;
                    Raster createChild = this.inputRaster.createChild(mapToSourceX, i13, i12, 1, mapToSourceX, i15, (int[]) null);
                    int i16 = i9;
                    int i17 = mapToSourceX;
                    int i18 = 0;
                    while (i18 < i4) {
                        for (int i19 = 0; i19 < this.numBands; i19++) {
                            createWritableRaster.setSample(i16, i10, i19, createChild.getSample(i17, i15, this.sourceBands[i19]));
                        }
                        i18++;
                        i16++;
                        i17 += this.scaleX;
                    }
                    i7 = i15;
                }
                i14 = i3 + 1;
                i10++;
                i13 = i7 + this.scaleY;
                i11 = i4;
                minY2 = i5;
                minY = i6;
            }
            return createWritableRaster;
        }
        if (this.noTransform) {
            Raster tile = this.input.getTile(i, i2);
            if (this.destinationRegion.contains(rectangle) && this.noSubband) {
                return tile;
            }
            Rectangle intersection2 = rectangle.intersection(this.destinationRegion);
            return tile.createChild(intersection2.x, intersection2.y, intersection2.width, intersection2.height, intersection2.x, intersection2.y, this.sourceBands);
        }
        Rectangle intersection3 = rectangle.intersection(this.destinationRegion);
        int i20 = intersection3.x;
        int i21 = intersection3.y;
        WritableRaster createWritableRaster2 = Raster.createWritableRaster(this.sampleModel, new Point(i20, i21));
        int mapToSourceX2 = mapToSourceX(i20);
        int mapToSourceY2 = mapToSourceY(i21);
        int minY3 = this.input.getMinY();
        int minY4 = this.input.getMinY() + this.input.getHeight();
        int i22 = intersection3.width;
        int i23 = ((i22 - 1) * this.scaleX) + 1;
        int i24 = 0;
        while (i24 < intersection3.height) {
            if (mapToSourceY2 >= minY3 && mapToSourceY2 < minY4) {
                Raster data = this.input.getData(new Rectangle(mapToSourceX2, mapToSourceY2, i23, i8));
                int i25 = i20;
                int i26 = mapToSourceX2;
                int i27 = 0;
                while (i27 < i22) {
                    Rectangle rectangle2 = intersection3;
                    int i28 = i20;
                    for (int i29 = 0; i29 < this.numBands; i29++) {
                        createWritableRaster2.setSample(i25, i21, i29, data.getSample(i26, mapToSourceY2, this.sourceBands[i29]));
                    }
                    i27++;
                    i25++;
                    i26 += this.scaleX;
                    intersection3 = rectangle2;
                    i20 = i28;
                }
            }
            i24++;
            i21++;
            mapToSourceY2 += this.scaleY;
            intersection3 = intersection3;
            i20 = i20;
            i8 = 1;
        }
        return createWritableRaster2;
    }

    private int mapToSourceX(int i) {
        return (i * this.scaleX) + this.xOffset;
    }

    private int mapToSourceY(int i) {
        return (i * this.scaleY) + this.yOffset;
    }

    private int getMinTileX() {
        return ToTile(this.destinationRegion.x, this.tileXOffset, this.tileWidth);
    }

    private int getMaxTileX() {
        return ToTile((this.destinationRegion.x + this.destinationRegion.width) - 1, this.tileXOffset, this.tileWidth);
    }

    private int getMinTileY() {
        return ToTile(this.destinationRegion.y, this.tileYOffset, this.tileHeight);
    }

    private int getMaxTileY() {
        return ToTile((this.destinationRegion.y + this.destinationRegion.height) - 1, this.tileYOffset, this.tileHeight);
    }

    private static int ToTile(int i, int i2, int i3) {
        int i4 = i - i2;
        if (i4 < 0) {
            i4 += 1 - i3;
        }
        return i4 / i3;
    }

    public void reset() {
        super.reset();
        this.stream = null;
        this.optimal = false;
        this.sourceBands = null;
        this.destinationRegion = null;
        this.noTransform = true;
        this.noSubband = true;
        this.writeRaster = false;
    }
}
