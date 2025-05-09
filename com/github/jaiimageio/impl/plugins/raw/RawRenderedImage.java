package com.github.jaiimageio.impl.plugins.raw;

import com.github.jaiimageio.impl.common.ImageUtil;
import com.github.jaiimageio.impl.common.SimpleRenderedImage;
import com.github.jaiimageio.stream.RawImageInputStream;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BandedSampleModel;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentSampleModel;
import java.awt.image.DataBuffer;
import java.awt.image.MultiPixelPackedSampleModel;
import java.awt.image.PixelInterleavedSampleModel;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageTypeSpecifier;

/* loaded from: classes3.dex */
public class RawRenderedImage extends SimpleRenderedImage {
    private Raster currentTile;
    private Point currentTileGrid;
    private BufferedImage destImage;
    private int[] destinationBands;
    private Rectangle destinationRegion;
    private RawImageInputStream iis;
    private int imageIndex;
    private int maxXTile;
    private int maxYTile;
    private int nComp;
    private boolean noTransform;
    private Dimension originalDimension;
    private int originalNumXTiles;
    private Rectangle originalRegion;
    private SampleModel originalSampleModel;
    private ImageReadParam param;
    private long position;
    private WritableRaster rasForATile;
    private RawImageReader reader;
    private int scaleX;
    private int scaleY;
    private int[] sourceBands;
    private Point sourceOrigin;
    private long tileDataSize;
    private int xOffset;
    private int yOffset;

    private int clip(int i, int i2, int i3) {
        if (i < i2) {
            i = i2;
        }
        return i > i3 ? i3 : i;
    }

    public RawRenderedImage(RawImageInputStream rawImageInputStream, RawImageReader rawImageReader, ImageReadParam imageReadParam, int i) throws IOException {
        this.iis = null;
        this.param = null;
        this.destinationBands = null;
        this.sourceBands = null;
        this.noTransform = true;
        this.iis = rawImageInputStream;
        this.reader = rawImageReader;
        this.param = imageReadParam;
        this.imageIndex = i;
        this.position = rawImageInputStream.getImageOffset(i);
        this.originalDimension = rawImageInputStream.getImageDimension(i);
        ImageTypeSpecifier imageType = rawImageInputStream.getImageType();
        SampleModel sampleModel = imageType.getSampleModel();
        this.originalSampleModel = sampleModel;
        this.sampleModel = sampleModel;
        this.colorModel = imageType.getColorModel();
        int[] sourceBands = imageReadParam == null ? null : imageReadParam.getSourceBands();
        this.sourceBands = sourceBands;
        if (sourceBands == null) {
            int numBands = this.originalSampleModel.getNumBands();
            this.nComp = numBands;
            this.sourceBands = new int[numBands];
            for (int i2 = 0; i2 < this.nComp; i2++) {
                this.sourceBands[i2] = i2;
            }
        } else {
            this.sampleModel = this.originalSampleModel.createSubsetSampleModel(sourceBands);
            this.colorModel = ImageUtil.createColorModel(null, this.sampleModel);
        }
        this.nComp = this.sourceBands.length;
        int[] destinationBands = imageReadParam != null ? imageReadParam.getDestinationBands() : null;
        this.destinationBands = destinationBands;
        if (destinationBands == null) {
            this.destinationBands = new int[this.nComp];
            for (int i3 = 0; i3 < this.nComp; i3++) {
                this.destinationBands[i3] = i3;
            }
        }
        Dimension imageDimension = rawImageInputStream.getImageDimension(i);
        this.width = imageDimension.width;
        this.height = imageDimension.height;
        Rectangle rectangle = new Rectangle(0, 0, this.width, this.height);
        this.originalRegion = (Rectangle) rectangle.clone();
        this.destinationRegion = (Rectangle) rectangle.clone();
        if (imageReadParam != null) {
            RawImageReader.computeRegionsWrapper(imageReadParam, this.width, this.height, imageReadParam.getDestination(), rectangle, this.destinationRegion);
            this.scaleX = imageReadParam.getSourceXSubsampling();
            this.scaleY = imageReadParam.getSourceYSubsampling();
            this.xOffset = imageReadParam.getSubsamplingXOffset();
            this.yOffset = imageReadParam.getSubsamplingYOffset();
        }
        this.sourceOrigin = new Point(rectangle.x, rectangle.y);
        if (!this.destinationRegion.equals(rectangle)) {
            this.noTransform = false;
        }
        this.tileDataSize = ImageUtil.getTileSize(this.originalSampleModel);
        this.tileWidth = this.originalSampleModel.getWidth();
        this.tileHeight = this.originalSampleModel.getHeight();
        this.tileGridXOffset = this.destinationRegion.x;
        this.tileGridYOffset = this.destinationRegion.y;
        this.originalNumXTiles = getNumXTiles();
        this.width = this.destinationRegion.width;
        this.height = this.destinationRegion.height;
        this.minX = this.destinationRegion.x;
        this.minY = this.destinationRegion.y;
        this.sampleModel = this.sampleModel.createCompatibleSampleModel(this.tileWidth, this.tileHeight);
        this.maxXTile = this.originalDimension.width / this.tileWidth;
        this.maxYTile = this.originalDimension.height / this.tileHeight;
    }

    public synchronized Raster getTile(int i, int i2) {
        if (this.currentTile != null && this.currentTileGrid.x == i && this.currentTileGrid.y == i2) {
            return this.currentTile;
        }
        if (i >= getNumXTiles() || i2 >= getNumYTiles()) {
            throw new IllegalArgumentException(I18N.getString("RawRenderedImage0"));
        }
        try {
            RawImageInputStream rawImageInputStream = this.iis;
            long j = this.position;
            long j2 = (this.originalNumXTiles * i2) + i;
            long j3 = this.tileDataSize;
            Long.signum(j2);
            rawImageInputStream.seek(j + (j2 * j3));
            WritableRaster createWritableRaster = Raster.createWritableRaster(this.sampleModel, new Point(tileXToX(i), tileYToY(i2)));
            this.currentTile = createWritableRaster;
            if (this.noTransform) {
                int dataType = this.sampleModel.getDataType();
                if (dataType == 0) {
                    byte[][] bankData = this.currentTile.getDataBuffer().getBankData();
                    for (int i3 = 0; i3 < bankData.length; i3++) {
                        this.iis.readFully(bankData[i3], 0, bankData[i3].length);
                    }
                } else if (dataType == 1) {
                    short[][] bankData2 = this.currentTile.getDataBuffer().getBankData();
                    for (int i4 = 0; i4 < bankData2.length; i4++) {
                        this.iis.readFully(bankData2[i4], 0, bankData2[i4].length);
                    }
                } else if (dataType == 2) {
                    short[][] bankData3 = this.currentTile.getDataBuffer().getBankData();
                    for (int i5 = 0; i5 < bankData3.length; i5++) {
                        this.iis.readFully(bankData3[i5], 0, bankData3[i5].length);
                    }
                } else if (dataType == 3) {
                    int[][] bankData4 = this.currentTile.getDataBuffer().getBankData();
                    for (int i6 = 0; i6 < bankData4.length; i6++) {
                        this.iis.readFully(bankData4[i6], 0, bankData4[i6].length);
                    }
                } else if (dataType == 4) {
                    float[][] bankData5 = this.currentTile.getDataBuffer().getBankData();
                    for (int i7 = 0; i7 < bankData5.length; i7++) {
                        this.iis.readFully(bankData5[i7], 0, bankData5[i7].length);
                    }
                } else if (dataType == 5) {
                    double[][] bankData6 = this.currentTile.getDataBuffer().getBankData();
                    for (int i8 = 0; i8 < bankData6.length; i8++) {
                        this.iis.readFully(bankData6[i8], 0, bankData6[i8].length);
                    }
                }
            } else {
                WritableRaster writableRaster = createWritableRaster;
                this.currentTile = readSubsampledRaster(createWritableRaster);
            }
            Point point = this.currentTileGrid;
            if (point == null) {
                this.currentTileGrid = new Point(i, i2);
            } else {
                point.x = i;
                this.currentTileGrid.y = i2;
            }
            return this.currentTile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readAsRaster(WritableRaster writableRaster) throws IOException {
        readSubsampledRaster(writableRaster);
    }

    /* JADX WARN: Code restructure failed: missing block: B:490:0x054b, code lost:
    
        if (r14.length < r6) goto L169;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x03a1, code lost:
    
        if (r13.length < r6) goto L99;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0581  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0805  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x0c95  */
    /* JADX WARN: Removed duplicated region for block: B:410:0x0cde  */
    /* JADX WARN: Type inference failed for: r10v49, types: [com.github.jaiimageio.stream.RawImageInputStream] */
    /* JADX WARN: Type inference failed for: r10v56, types: [com.github.jaiimageio.stream.RawImageInputStream] */
    /* JADX WARN: Type inference failed for: r12v28 */
    /* JADX WARN: Type inference failed for: r13v21, types: [int[]] */
    /* JADX WARN: Type inference failed for: r13v22 */
    /* JADX WARN: Type inference failed for: r13v50 */
    /* JADX WARN: Type inference failed for: r13v51 */
    /* JADX WARN: Type inference failed for: r13v52, types: [int[]] */
    /* JADX WARN: Type inference failed for: r13v53, types: [int[]] */
    /* JADX WARN: Type inference failed for: r13v54 */
    /* JADX WARN: Type inference failed for: r13v55 */
    /* JADX WARN: Type inference failed for: r13v56 */
    /* JADX WARN: Type inference failed for: r13v57 */
    /* JADX WARN: Type inference failed for: r13v59 */
    /* JADX WARN: Type inference failed for: r13v64 */
    /* JADX WARN: Type inference failed for: r15v69, types: [com.github.jaiimageio.stream.RawImageInputStream] */
    /* JADX WARN: Type inference failed for: r15v72, types: [com.github.jaiimageio.stream.RawImageInputStream] */
    /* JADX WARN: Type inference failed for: r15v74, types: [com.github.jaiimageio.stream.RawImageInputStream] */
    /* JADX WARN: Type inference failed for: r1v39 */
    /* JADX WARN: Type inference failed for: r1v40 */
    /* JADX WARN: Type inference failed for: r1v41 */
    /* JADX WARN: Type inference failed for: r1v45 */
    /* JADX WARN: Type inference failed for: r1v46 */
    /* JADX WARN: Type inference failed for: r1v47 */
    /* JADX WARN: Type inference failed for: r1v48 */
    /* JADX WARN: Type inference failed for: r1v49 */
    /* JADX WARN: Type inference failed for: r1v50, types: [double[]] */
    /* JADX WARN: Type inference failed for: r1v51 */
    /* JADX WARN: Type inference failed for: r1v52 */
    /* JADX WARN: Type inference failed for: r34v2 */
    /* JADX WARN: Type inference failed for: r34v20 */
    /* JADX WARN: Type inference failed for: r34v3 */
    /* JADX WARN: Type inference failed for: r34v31 */
    /* JADX WARN: Type inference failed for: r39v10, types: [int[]] */
    /* JADX WARN: Type inference failed for: r39v13 */
    /* JADX WARN: Type inference failed for: r39v23 */
    /* JADX WARN: Type inference failed for: r39v24 */
    /* JADX WARN: Type inference failed for: r39v25 */
    /* JADX WARN: Type inference failed for: r39v41 */
    /* JADX WARN: Type inference failed for: r39v42 */
    /* JADX WARN: Type inference failed for: r39v6, types: [int[]] */
    /* JADX WARN: Type inference failed for: r39v8 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v34 */
    /* JADX WARN: Type inference failed for: r3v35 */
    /* JADX WARN: Type inference failed for: r3v36 */
    /* JADX WARN: Type inference failed for: r3v40 */
    /* JADX WARN: Type inference failed for: r3v41 */
    /* JADX WARN: Type inference failed for: r3v42 */
    /* JADX WARN: Type inference failed for: r3v44 */
    /* JADX WARN: Type inference failed for: r3v45, types: [float[]] */
    /* JADX WARN: Type inference failed for: r3v47 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9, types: [double[]] */
    /* JADX WARN: Type inference failed for: r40v11 */
    /* JADX WARN: Type inference failed for: r40v13, types: [float[]] */
    /* JADX WARN: Type inference failed for: r40v41 */
    /* JADX WARN: Type inference failed for: r40v42 */
    /* JADX WARN: Type inference failed for: r40v8, types: [float[]] */
    /* JADX WARN: Type inference failed for: r41v11 */
    /* JADX WARN: Type inference failed for: r41v13, types: [double[]] */
    /* JADX WARN: Type inference failed for: r41v45 */
    /* JADX WARN: Type inference failed for: r41v46 */
    /* JADX WARN: Type inference failed for: r41v8, types: [double[]] */
    /* JADX WARN: Type inference failed for: r48v6 */
    /* JADX WARN: Type inference failed for: r6v32, types: [com.github.jaiimageio.stream.RawImageInputStream] */
    /* JADX WARN: Type inference failed for: r7v17, types: [float[]] */
    /* JADX WARN: Type inference failed for: r8v79, types: [int[]] */
    /* JADX WARN: Type inference failed for: r8v84, types: [float[]] */
    /* JADX WARN: Type inference failed for: r8v89, types: [double[]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Raster readSubsampledRaster(WritableRaster writableRaster) throws IOException {
        int i;
        int i2;
        int i3;
        int[] iArr;
        int[] iArr2;
        int i4;
        int i5;
        int i6;
        int[] iArr3;
        int i7;
        byte[] bArr;
        short[] sArr;
        int[] iArr4;
        short[] sArr2;
        short[] sArr3;
        short[] sArr4;
        int i8;
        int i9;
        int i10;
        WritableRaster writableRaster2;
        double[] dArr;
        int i11;
        int i12;
        float[] fArr;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        short[] sArr5;
        Object obj;
        int i24;
        int i25;
        int i26;
        byte[] bArr2;
        Object obj2;
        double[] dArr2;
        WritableRaster writableRaster3;
        short[] sArr6;
        short[] sArr7;
        short[] sArr8;
        int i27;
        int i28;
        int i29;
        int i30;
        int i31;
        int i32;
        int i33;
        int i34;
        int i35;
        int i36;
        int i37;
        int i38;
        int i39;
        int i40;
        int i41;
        int i42;
        short[] sArr9;
        short[] sArr10;
        short[] sArr11;
        short[] sArr12;
        int i43;
        short[] sArr13;
        short[] sArr14;
        short[] sArr15;
        byte[] bArr3;
        short[] sArr16;
        BufferedImage bufferedImage;
        byte[] bArr4;
        int i44;
        int i45;
        byte[] data;
        ?? r41;
        ?? r40;
        ?? r39;
        byte[] bArr5;
        int i46;
        int i47;
        int i48;
        short[] sArr17;
        short[] sArr18;
        short[] sArr19;
        int i49;
        int i50;
        int i51;
        int i52;
        int i53;
        int i54;
        int i55;
        int i56;
        ?? r1;
        int i57;
        int i58;
        int i59;
        ?? r3;
        int i60;
        int i61;
        byte[] bArr6;
        int i62;
        short[] sArr20;
        ?? r392;
        int i63;
        int i64;
        int i65;
        int i66;
        short[] sArr21;
        short[] sArr22;
        int i67;
        byte[] bArr7;
        int i68;
        float f;
        int i69;
        byte[] data2;
        short[] sArr23;
        short[] data3;
        RawRenderedImage rawRenderedImage = this;
        WritableRaster createWritableRaster = writableRaster == null ? Raster.createWritableRaster(rawRenderedImage.sampleModel.createCompatibleSampleModel(rawRenderedImage.destinationRegion.x + rawRenderedImage.destinationRegion.width, rawRenderedImage.destinationRegion.y + rawRenderedImage.destinationRegion.height), new Point(rawRenderedImage.destinationRegion.x, rawRenderedImage.destinationRegion.y)) : writableRaster;
        int length = rawRenderedImage.sourceBands.length;
        int dataType = rawRenderedImage.sampleModel.getDataType();
        int dataTypeSize = (DataBuffer.getDataTypeSize(dataType) + 7) / 8;
        Rectangle intersection = createWritableRaster.getBounds().intersection(rawRenderedImage.destinationRegion);
        int i70 = rawRenderedImage.destinationRegion.x;
        int i71 = rawRenderedImage.destinationRegion.y;
        int i72 = ((intersection.x - i70) * rawRenderedImage.scaleX) + rawRenderedImage.sourceOrigin.x;
        int i73 = ((intersection.y - i71) * rawRenderedImage.scaleY) + rawRenderedImage.sourceOrigin.y;
        int i74 = ((intersection.width - 1) * rawRenderedImage.scaleX) + i72;
        int i75 = ((intersection.height - 1) * rawRenderedImage.scaleY) + i73;
        int i76 = i72 / rawRenderedImage.tileWidth;
        int i77 = i73 / rawRenderedImage.tileHeight;
        int i78 = i74 / rawRenderedImage.tileWidth;
        int i79 = i75 / rawRenderedImage.tileHeight;
        int clip = rawRenderedImage.clip(i76, 0, rawRenderedImage.maxXTile);
        int clip2 = rawRenderedImage.clip(i77, 0, rawRenderedImage.maxYTile);
        int clip3 = rawRenderedImage.clip(i78, 0, rawRenderedImage.maxXTile);
        int clip4 = rawRenderedImage.clip(i79, 0, rawRenderedImage.maxYTile);
        int numXTiles = getNumXTiles();
        int numYTiles = numXTiles * getNumYTiles();
        ComponentSampleModel componentSampleModel = rawRenderedImage.originalSampleModel;
        int i80 = numYTiles;
        byte[] bArr8 = null;
        if (componentSampleModel instanceof ComponentSampleModel) {
            ComponentSampleModel componentSampleModel2 = componentSampleModel;
            int[] bankIndices = componentSampleModel2.getBankIndices();
            i = dataTypeSize;
            i2 = i70;
            i3 = i71;
            int i81 = 0;
            for (int i82 = 0; i82 < bankIndices.length; i82++) {
                if (i81 > bankIndices[i82]) {
                    i81 = bankIndices[i82];
                }
            }
            int pixelStride = componentSampleModel2.getPixelStride();
            i6 = componentSampleModel2.getScanlineStride();
            iArr = componentSampleModel2.getBandOffsets();
            iArr2 = bankIndices;
            i4 = 0;
            for (int i83 = 0; i83 < iArr.length; i83++) {
                if (i4 < iArr[i83]) {
                    i4 = iArr[i83];
                }
            }
            i5 = pixelStride;
        } else {
            i = dataTypeSize;
            i2 = i70;
            i3 = i71;
            if (componentSampleModel instanceof MultiPixelPackedSampleModel) {
                i6 = ((MultiPixelPackedSampleModel) componentSampleModel).getScanlineStride();
                iArr = null;
                iArr2 = null;
                i4 = 0;
                i5 = 0;
            } else if (componentSampleModel instanceof SinglePixelPackedSampleModel) {
                i6 = ((SinglePixelPackedSampleModel) componentSampleModel).getScanlineStride();
                iArr = null;
                iArr2 = null;
                i4 = 0;
                i5 = 1;
            } else {
                iArr = null;
                iArr2 = null;
                i4 = 0;
                i5 = 0;
                i6 = 0;
            }
        }
        int i84 = i4;
        if (createWritableRaster.getSampleModel() instanceof ComponentSampleModel) {
            ComponentSampleModel sampleModel = createWritableRaster.getSampleModel();
            iArr2 = sampleModel.getBankIndices();
            iArr4 = sampleModel.getBandOffsets();
            i9 = sampleModel.getPixelStride();
            i10 = sampleModel.getScanlineStride();
            iArr3 = iArr;
            int offset = sampleModel.getOffset(createWritableRaster.getMinX() - createWritableRaster.getSampleModelTranslateX(), createWritableRaster.getMinY() - createWritableRaster.getSampleModelTranslateY()) - iArr4[0];
            if (dataType != 0) {
                if (dataType == 1) {
                    data3 = createWritableRaster.getDataBuffer().getData();
                } else if (dataType == 2) {
                    data3 = createWritableRaster.getDataBuffer().getData();
                } else if (dataType != 3) {
                    if (dataType == 4) {
                        sArr3 = createWritableRaster.getDataBuffer().getData();
                        data2 = null;
                        sArr23 = null;
                        sArr2 = null;
                        sArr4 = null;
                    } else if (dataType != 5) {
                        data2 = null;
                        sArr23 = null;
                    } else {
                        sArr4 = createWritableRaster.getDataBuffer().getData();
                        data2 = null;
                        sArr23 = null;
                        sArr2 = null;
                        sArr3 = null;
                    }
                    byte[] bArr9 = data2;
                    i8 = offset;
                    i7 = length;
                    sArr = sArr23;
                    bArr = bArr9;
                } else {
                    sArr2 = createWritableRaster.getDataBuffer().getData();
                    data2 = null;
                    sArr23 = null;
                    sArr3 = null;
                    sArr4 = sArr3;
                    byte[] bArr92 = data2;
                    i8 = offset;
                    i7 = length;
                    sArr = sArr23;
                    bArr = bArr92;
                }
                sArr23 = data3;
                data2 = null;
                sArr2 = null;
                sArr3 = sArr2;
                sArr4 = sArr3;
                byte[] bArr922 = data2;
                i8 = offset;
                i7 = length;
                sArr = sArr23;
                bArr = bArr922;
            } else {
                data2 = createWritableRaster.getDataBuffer().getData();
                sArr23 = null;
            }
            sArr2 = sArr23;
            sArr3 = sArr2;
            sArr4 = sArr3;
            byte[] bArr9222 = data2;
            i8 = offset;
            i7 = length;
            sArr = sArr23;
            bArr = bArr9222;
        } else {
            iArr3 = iArr;
            if (createWritableRaster.getSampleModel() instanceof SinglePixelPackedSampleModel) {
                int i85 = 1;
                int i86 = 0;
                int[] iArr5 = {0};
                int[] iArr6 = new int[1];
                int i87 = 0;
                while (i87 < i85) {
                    iArr6[i87] = i86;
                    i87++;
                    i85 = 1;
                    i86 = 0;
                }
                i10 = createWritableRaster.getSampleModel().getScanlineStride();
                iArr4 = iArr6;
                iArr2 = iArr5;
                bArr = null;
                sArr = null;
                sArr2 = null;
                sArr3 = null;
                sArr4 = null;
                i7 = 1;
                i8 = 0;
                i9 = 1;
            } else {
                i7 = length;
                bArr = null;
                sArr = null;
                iArr4 = null;
                sArr2 = null;
                sArr3 = null;
                sArr4 = null;
                i8 = 0;
                i9 = 0;
                i10 = 0;
            }
        }
        double[] dArr3 = null;
        short[] sArr24 = sArr2;
        short[] sArr25 = sArr3;
        short[] sArr26 = sArr4;
        short[] sArr27 = null;
        Object obj3 = null;
        float[] fArr2 = null;
        short[] sArr28 = sArr;
        byte[] bArr10 = bArr;
        int i88 = clip2;
        short[] sArr29 = sArr28;
        while (i88 <= clip4) {
            int i89 = clip4;
            if (rawRenderedImage.reader.getAbortRequest()) {
                break;
            }
            byte[] bArr11 = bArr8;
            short[] sArr30 = sArr24;
            short[] sArr31 = sArr25;
            short[] sArr32 = sArr26;
            int i90 = i7;
            double[] dArr4 = dArr3;
            Object obj4 = obj3;
            int i91 = i8;
            float[] fArr3 = fArr2;
            short[] sArr33 = sArr27;
            int i92 = dataType;
            int i93 = clip;
            while (true) {
                if (i93 > clip3) {
                    writableRaster2 = createWritableRaster;
                    dArr = dArr4;
                    i11 = i5;
                    i12 = i75;
                    fArr = fArr3;
                    i13 = i72;
                    i14 = i73;
                    i15 = i74;
                    i16 = clip;
                    i17 = i88;
                    i18 = clip3;
                    break;
                }
                i18 = clip3;
                if (rawRenderedImage.reader.getAbortRequest()) {
                    writableRaster2 = createWritableRaster;
                    dArr = dArr4;
                    i11 = i5;
                    i12 = i75;
                    fArr = fArr3;
                    i13 = i72;
                    i14 = i73;
                    i15 = i74;
                    i16 = clip;
                    i17 = i88;
                    break;
                }
                double[] dArr5 = dArr4;
                WritableRaster writableRaster4 = createWritableRaster;
                int i94 = i5;
                byte[] bArr12 = bArr11;
                int i95 = i6;
                float[] fArr4 = fArr3;
                long j = rawRenderedImage.position + (((rawRenderedImage.originalNumXTiles * i88) + i93) * rawRenderedImage.tileDataSize);
                rawRenderedImage.iis.seek(j);
                float f2 = ((i93 - clip) + (i88 * numXTiles)) / numXTiles;
                int i96 = rawRenderedImage.tileWidth * i93;
                int i97 = rawRenderedImage.tileHeight * i88;
                int i98 = rawRenderedImage.tileHeight;
                int i99 = clip;
                int i100 = rawRenderedImage.tileWidth;
                int i101 = numXTiles;
                int i102 = i97 + i98 >= rawRenderedImage.originalDimension.height ? rawRenderedImage.originalDimension.height - i97 : i98;
                int i103 = i96 + i100 >= rawRenderedImage.originalDimension.width ? rawRenderedImage.originalDimension.width - i96 : i100;
                if (i72 > i96) {
                    i103 += i96 - i72;
                    i96 = i72;
                }
                if (i73 > i97) {
                    i102 += i97 - i73;
                    i97 = i73;
                }
                if (i74 < (i96 + i103) - 1) {
                    i103 += ((i74 - i96) - i103) + 1;
                }
                if (i75 < (i97 + i102) - 1) {
                    i102 += ((i75 - i97) - i102) + 1;
                }
                int i104 = i75;
                int i105 = (((rawRenderedImage.scaleX + i96) - 1) - rawRenderedImage.sourceOrigin.x) / rawRenderedImage.scaleX;
                int i106 = i72;
                int i107 = (((r6 + i96) - 1) + i103) - rawRenderedImage.sourceOrigin.x;
                int i108 = rawRenderedImage.scaleX;
                int i109 = i107 / i108;
                int i110 = i73;
                int i111 = i109 - i105;
                int i112 = ((i109 - 1) * i108) + rawRenderedImage.sourceOrigin.x;
                int i113 = i74;
                int i114 = (((rawRenderedImage.scaleY + i97) - 1) - rawRenderedImage.sourceOrigin.y) / rawRenderedImage.scaleY;
                int i115 = (rawRenderedImage.scaleX * i105) + rawRenderedImage.sourceOrigin.x;
                int i116 = (rawRenderedImage.scaleY * i114) + rawRenderedImage.sourceOrigin.y;
                int i117 = i105 + i2;
                int i118 = i114 + i3;
                int i119 = i96 - (rawRenderedImage.tileWidth * i93);
                int i120 = i97 - (rawRenderedImage.tileHeight * i88);
                if (rawRenderedImage.sampleModel instanceof MultiPixelPackedSampleModel) {
                    MultiPixelPackedSampleModel multiPixelPackedSampleModel = rawRenderedImage.originalSampleModel;
                    i20 = i88;
                    rawRenderedImage.iis.skipBytes(multiPixelPackedSampleModel.getOffset(i119, i120) * i);
                    int offset2 = ((multiPixelPackedSampleModel.getOffset(i112, 0) - multiPixelPackedSampleModel.getOffset(i115, 0)) + 1) * i;
                    int i121 = ((rawRenderedImage.scaleY * i95) - offset2) * i;
                    int i122 = offset2 * i;
                    if (bArr12 != null) {
                        bArr7 = bArr12;
                        i67 = i95;
                    } else {
                        i67 = i95;
                    }
                    bArr7 = new byte[i122];
                    int bitOffset = multiPixelPackedSampleModel.getBitOffset(i119);
                    int i123 = 0;
                    while (i123 < i102 && !rawRenderedImage.reader.getAbortRequest()) {
                        int i124 = i93;
                        rawRenderedImage.iis.readFully(bArr7, 0, i122);
                        if (rawRenderedImage.scaleX != 1) {
                            i68 = i116;
                            f = f2;
                            i69 = i102;
                            int i125 = i115 & 7;
                            int i126 = 0;
                            int i127 = 0;
                            int i128 = 7;
                            while (i126 < i111) {
                                int i129 = bitOffset;
                                bArr7[i127] = (byte) (((~(1 << i128)) & bArr7[i127]) | (((bArr7[i125 >> 3] >> (7 - (i125 & 7))) & 1) << i128));
                                i128--;
                                if (i128 == -1) {
                                    i127++;
                                    i128 = 7;
                                }
                                i126++;
                                i125 += rawRenderedImage.scaleX;
                                bitOffset = i129;
                            }
                        } else if (bitOffset != 0) {
                            int i130 = (255 << bitOffset) & 255;
                            f = f2;
                            int i131 = 255 & (~i130);
                            int i132 = 8 - bitOffset;
                            i69 = i102;
                            int i133 = 0;
                            while (true) {
                                i68 = i116;
                                if (i133 >= i122 - 1) {
                                    break;
                                }
                                int i134 = i133 + 1;
                                bArr7[i133] = (byte) (((bArr7[i133] & i131) << i132) | ((bArr7[i134] & i130) >> bitOffset));
                                i133 = i134;
                                i116 = i68;
                            }
                            bArr7[i133] = (byte) ((bArr7[i133] & i131) << i132);
                        } else {
                            i68 = i116;
                            f = f2;
                            i69 = i102;
                        }
                        int i135 = bitOffset;
                        WritableRaster writableRaster5 = writableRaster4;
                        ImageUtil.setPackedBinaryData(bArr7, writableRaster5, new Rectangle(i117, i118, i111, 1));
                        rawRenderedImage.iis.skipBytes(i121);
                        BufferedImage bufferedImage2 = rawRenderedImage.destImage;
                        if (bufferedImage2 != null) {
                            rawRenderedImage.reader.processImageUpdateWrapper(bufferedImage2, i117, i118, i103, 1, 1, 1, rawRenderedImage.destinationBands);
                        }
                        i102 = i69;
                        rawRenderedImage.reader.processImageProgressWrapper(f + ((((i123 - i68) + 1.0f) / i102) / i80));
                        i123 += rawRenderedImage.scaleY;
                        i118++;
                        writableRaster4 = writableRaster5;
                        i116 = i68;
                        bitOffset = i135;
                        i93 = i124;
                        f2 = f;
                    }
                    i19 = i93;
                    writableRaster3 = writableRaster4;
                    i27 = i80;
                    bArr2 = bArr7;
                    i28 = i90;
                    i29 = i92;
                    obj2 = obj4;
                    dArr4 = dArr5;
                    i30 = i94;
                    i31 = i67;
                    fArr3 = fArr4;
                    sArr8 = sArr30;
                    sArr7 = sArr31;
                    sArr6 = sArr32;
                } else {
                    i19 = i93;
                    i20 = i88;
                    int i136 = i95;
                    int i137 = i80;
                    int i138 = i94;
                    if (i138 < i136) {
                        i21 = i103 * i138;
                        i22 = rawRenderedImage.scaleY * i136;
                    } else {
                        i21 = i102 * i136;
                        i22 = rawRenderedImage.scaleX * i138;
                    }
                    int i139 = (i22 - i21) * i;
                    int dataType2 = rawRenderedImage.sampleModel.getDataType();
                    if (dataType2 != 0) {
                        i23 = i103;
                        if (dataType2 == 1 || dataType2 == 2) {
                            obj = obj4;
                            if (sArr33 != null) {
                                sArr5 = sArr33;
                            }
                            i24 = i137;
                            i25 = i102;
                            i26 = i111;
                            sArr5 = new short[i21];
                            bArr2 = bArr12;
                            obj2 = obj;
                            dArr2 = dArr5;
                            fArr3 = fArr4;
                            if (rawRenderedImage.sampleModel instanceof PixelInterleavedSampleModel) {
                                rawRenderedImage.iis.skipBytes(((i119 * i138) + (i120 * i136)) * i);
                                if (i138 < i136) {
                                    int i140 = rawRenderedImage.scaleY;
                                    i49 = rawRenderedImage.scaleX * i138;
                                    i51 = i140;
                                    i52 = i118;
                                    i54 = i9;
                                    i53 = i10;
                                    i50 = i25;
                                } else {
                                    int i141 = rawRenderedImage.scaleX;
                                    i49 = rawRenderedImage.scaleY * i136;
                                    i50 = i23;
                                    i51 = i141;
                                    i52 = i117;
                                    i53 = i9;
                                    i54 = i10;
                                }
                                int sampleModelTranslateY = i91 + ((i118 - writableRaster4.getSampleModelTranslateY()) * i10) + ((i117 - writableRaster4.getSampleModelTranslateX()) * i9);
                                writableRaster3 = writableRaster4;
                                int i142 = sampleModelTranslateY;
                                int i143 = 0;
                                sArr8 = sArr30;
                                short[] sArr34 = sArr31;
                                short[] sArr35 = sArr32;
                                while (i143 < i50) {
                                    int i144 = i143;
                                    if (rawRenderedImage.reader.getAbortRequest()) {
                                        break;
                                    }
                                    if (i92 != 0) {
                                        i56 = i92;
                                        i58 = i50;
                                        if (i56 == 1 || i56 == 2) {
                                            i55 = i138;
                                            i59 = i90;
                                            r1 = sArr35;
                                            i57 = i136;
                                            i61 = i117;
                                            r3 = sArr34;
                                            i60 = i139;
                                            if (i49 == i59 && i54 == i59) {
                                                rawRenderedImage.iis.readFully(sArr29, i142, i21);
                                                sArr22 = sArr8;
                                            } else {
                                                sArr29 = sArr29;
                                                rawRenderedImage.iis.readFully(sArr5, 0, i21);
                                                sArr22 = sArr8;
                                            }
                                        } else if (i56 == 3) {
                                            i55 = i138;
                                            i59 = i90;
                                            r1 = sArr35;
                                            i57 = i136;
                                            i61 = i117;
                                            r3 = sArr34;
                                            i60 = i139;
                                            if (i49 == i59 && i54 == i59) {
                                                rawRenderedImage.iis.readFully((int[]) sArr8, i142, i21);
                                                sArr22 = sArr8;
                                            } else {
                                                sArr22 = sArr8;
                                                rawRenderedImage.iis.readFully(obj2, 0, i21);
                                            }
                                        } else if (i56 != 4) {
                                            if (i56 != 5) {
                                                i55 = i138;
                                                i59 = i90;
                                                r1 = sArr35;
                                                i57 = i136;
                                                i61 = i117;
                                            } else {
                                                i59 = i90;
                                                if (i49 == i59 && i54 == i59) {
                                                    i61 = i117;
                                                    i55 = i138;
                                                    r1 = sArr35;
                                                    rawRenderedImage.iis.readFully(r1, i142, i21);
                                                    i57 = i136;
                                                } else {
                                                    i55 = i138;
                                                    i61 = i117;
                                                    r1 = sArr35;
                                                    i57 = i136;
                                                    rawRenderedImage.iis.readFully(dArr2, 0, i21);
                                                }
                                            }
                                            bArr6 = bArr10;
                                            r3 = sArr34;
                                            i62 = i142;
                                            i60 = i139;
                                            r392 = sArr8;
                                        } else {
                                            i55 = i138;
                                            i59 = i90;
                                            r1 = sArr35;
                                            i57 = i136;
                                            i61 = i117;
                                            if (i49 == i59 && i54 == i59) {
                                                r3 = sArr34;
                                                rawRenderedImage.iis.readFully(r3, i142, i21);
                                                i60 = i139;
                                                sArr22 = sArr8;
                                            } else {
                                                r3 = sArr34;
                                                i60 = i139;
                                                rawRenderedImage.iis.readFully(fArr3, 0, i21);
                                                sArr22 = sArr8;
                                            }
                                        }
                                        bArr6 = bArr10;
                                        sArr20 = sArr22;
                                        i62 = i142;
                                        r392 = sArr20;
                                    } else {
                                        i55 = i138;
                                        i56 = i92;
                                        r1 = sArr35;
                                        i57 = i136;
                                        i58 = i50;
                                        i59 = i90;
                                        r3 = sArr34;
                                        i60 = i139;
                                        i61 = i117;
                                        if (i49 == i59 && i54 == i59) {
                                            bArr6 = bArr10;
                                            rawRenderedImage.iis.readFully(bArr6, i142, i21);
                                            sArr20 = sArr8;
                                            i62 = i142;
                                            r392 = sArr20;
                                        } else {
                                            bArr6 = bArr10;
                                            i62 = i142;
                                            rawRenderedImage.iis.readFully(bArr2, 0, i21);
                                            r392 = sArr8;
                                        }
                                    }
                                    if (i49 == i59 && i54 == i59) {
                                        i63 = i62;
                                        i64 = i59;
                                    } else {
                                        i63 = i62;
                                        int i145 = 0;
                                        while (true) {
                                            i64 = i59;
                                            if (i145 >= i59) {
                                                break;
                                            }
                                            int i146 = iArr4[rawRenderedImage.destinationBands[i145]];
                                            int i147 = i63 + i146;
                                            int i148 = iArr3[rawRenderedImage.sourceBands[i145]];
                                            if (i56 == 0) {
                                                int i149 = i147;
                                                int i150 = 0;
                                                while (i150 < i21) {
                                                    bArr6[i149] = bArr2[i150 + i148];
                                                    i150 += i49;
                                                    i149 += i54;
                                                }
                                            } else if (i56 == 1 || i56 == 2) {
                                                int i151 = i147;
                                                int i152 = 0;
                                                while (i152 < i21) {
                                                    sArr29[i151] = sArr5[i152 + i148];
                                                    i152 += i49;
                                                    i151 += i54;
                                                }
                                            } else if (i56 == 3) {
                                                int i153 = i147;
                                                int i154 = 0;
                                                while (i154 < i21) {
                                                    r392[i153] = obj2[i154 + i148];
                                                    i154 += i49;
                                                    i153 += i54;
                                                }
                                            } else if (i56 == 4) {
                                                int i155 = i147;
                                                int i156 = 0;
                                                while (i156 < i21) {
                                                    r3[i155] = fArr3[i156 + i148];
                                                    i156 += i49;
                                                    i155 += i54;
                                                }
                                            } else if (i56 == 5) {
                                                int i157 = i147;
                                                int i158 = 0;
                                                while (i158 < i21) {
                                                    r1[i157] = dArr2[i158 + i148];
                                                    i158 += i49;
                                                    i157 += i54;
                                                }
                                            }
                                            i63 = i147 - i146;
                                            i145++;
                                            rawRenderedImage = this;
                                            i59 = i64;
                                        }
                                        rawRenderedImage = this;
                                    }
                                    i139 = i60;
                                    rawRenderedImage.iis.skipBytes(i139);
                                    int i159 = i63 + i53;
                                    BufferedImage bufferedImage3 = rawRenderedImage.destImage;
                                    short[] sArr36 = r1;
                                    if (bufferedImage3 != null) {
                                        i138 = i55;
                                        int i160 = i57;
                                        sArr21 = r3;
                                        i136 = i160;
                                        if (i138 < i136) {
                                            i66 = i54;
                                            i65 = i49;
                                            rawRenderedImage.reader.processImageUpdateWrapper(bufferedImage3, i61, i52, i58, 1, 1, 1, rawRenderedImage.destinationBands);
                                        } else {
                                            i66 = i54;
                                            i65 = i49;
                                            rawRenderedImage.reader.processImageUpdateWrapper(bufferedImage3, i52, i118, 1, i58, 1, 1, rawRenderedImage.destinationBands);
                                        }
                                    } else {
                                        i65 = i49;
                                        i138 = i55;
                                        i66 = i54;
                                        int i161 = i57;
                                        sArr21 = r3;
                                        i136 = i161;
                                    }
                                    int i162 = i58;
                                    byte[] bArr13 = bArr6;
                                    int i163 = i24;
                                    rawRenderedImage.reader.processImageProgressWrapper(f2 + (((i144 + 1.0f) / i162) / i163));
                                    i52++;
                                    i50 = i162;
                                    i142 = i159;
                                    i49 = i65;
                                    i24 = i163;
                                    i117 = i61;
                                    i90 = i64;
                                    bArr10 = bArr13;
                                    i92 = i56;
                                    i143 = i144 + i51;
                                    i54 = i66;
                                    short[] sArr37 = sArr21;
                                    sArr35 = sArr36;
                                    sArr34 = sArr37;
                                    sArr8 = r392;
                                }
                                i30 = i138;
                                i29 = i92;
                                i31 = i136;
                                dArr4 = dArr2;
                                sArr33 = sArr5;
                                i27 = i24;
                                i28 = i90;
                                bArr10 = bArr10;
                                sArr6 = sArr35;
                                sArr7 = sArr34;
                            } else {
                                writableRaster3 = writableRaster4;
                                int i164 = i90;
                                int i165 = i92;
                                byte[] bArr14 = bArr10;
                                int i166 = i24;
                                short[] sArr38 = sArr32;
                                short[] sArr39 = sArr31;
                                if ((rawRenderedImage.sampleModel instanceof BandedSampleModel) || (rawRenderedImage.sampleModel instanceof SinglePixelPackedSampleModel) || i84 == 0) {
                                    int i167 = i165;
                                    byte[] bArr15 = bArr2;
                                    short[] sArr40 = obj2;
                                    short[] sArr41 = sArr5;
                                    int i168 = i166;
                                    int i169 = i25;
                                    int i170 = i138;
                                    int i171 = i164;
                                    boolean z = rawRenderedImage.sampleModel instanceof BandedSampleModel;
                                    int bandSize = (int) ImageUtil.getBandSize(rawRenderedImage.originalSampleModel);
                                    int i172 = 0;
                                    sArr6 = sArr38;
                                    sArr7 = sArr39;
                                    sArr8 = sArr30;
                                    while (i172 < i171) {
                                        rawRenderedImage.iis.seek(j + (rawRenderedImage.sourceBands[i172] * bandSize * i));
                                        int i173 = iArr4[rawRenderedImage.destinationBands[i172]];
                                        rawRenderedImage.iis.skipBytes(((i120 * i136) + (i119 * i170)) * i);
                                        int i174 = i170;
                                        if (i174 < i136) {
                                            i32 = rawRenderedImage.scaleY;
                                            i33 = rawRenderedImage.scaleX * i174;
                                            i34 = bandSize;
                                            i39 = i169;
                                            i35 = i39;
                                            i38 = i9;
                                            i37 = i10;
                                            i36 = i118;
                                        } else {
                                            i32 = rawRenderedImage.scaleX;
                                            i33 = rawRenderedImage.scaleY * i136;
                                            i34 = bandSize;
                                            i35 = i169;
                                            i36 = i117;
                                            i37 = i9;
                                            i38 = i10;
                                            i39 = i23;
                                        }
                                        int sampleModelTranslateY2 = i91 + ((i118 - writableRaster3.getSampleModelTranslateY()) * i10) + ((i117 - writableRaster3.getSampleModelTranslateX()) * i9) + i173;
                                        int i175 = iArr2[rawRenderedImage.destinationBands[i172]];
                                        if (i167 != 0) {
                                            i40 = i36;
                                            i41 = i167;
                                            i42 = i32;
                                            if (i41 == 1) {
                                                sArr29 = writableRaster3.getDataBuffer().getData(i175);
                                                sArr11 = sArr8;
                                                sArr10 = sArr7;
                                                sArr9 = sArr6;
                                            } else if (i41 == 2) {
                                                sArr29 = writableRaster3.getDataBuffer().getData(i175);
                                                sArr11 = sArr8;
                                                sArr10 = sArr7;
                                                sArr9 = sArr6;
                                            } else if (i41 == 3) {
                                                sArr11 = writableRaster3.getDataBuffer().getData(i175);
                                                sArr10 = sArr7;
                                                sArr9 = sArr6;
                                            } else if (i41 != 4) {
                                                sArr11 = sArr8;
                                                sArr10 = sArr7;
                                                sArr9 = sArr6;
                                                if (i41 == 5) {
                                                    sArr11 = sArr8;
                                                    sArr10 = sArr7;
                                                    sArr9 = writableRaster3.getDataBuffer().getData(i175);
                                                }
                                            } else {
                                                sArr11 = sArr8;
                                                sArr10 = writableRaster3.getDataBuffer().getData(i175);
                                                sArr9 = sArr6;
                                            }
                                        } else {
                                            i40 = i36;
                                            i41 = i167;
                                            i42 = i32;
                                            bArr14 = writableRaster3.getDataBuffer().getData(i175);
                                            sArr11 = sArr8;
                                            sArr10 = sArr7;
                                            sArr9 = sArr6;
                                        }
                                        int i176 = i136;
                                        int i177 = i168;
                                        int i178 = i171;
                                        short[] sArr42 = sArr29;
                                        byte[] bArr16 = bArr14;
                                        short[] sArr43 = sArr11;
                                        ?? r7 = sArr10;
                                        ?? r32 = sArr9;
                                        int i179 = i174;
                                        int i180 = 0;
                                        int i181 = i172;
                                        int i182 = sampleModelTranslateY2;
                                        while (i180 < i39) {
                                            int i183 = i180;
                                            if (rawRenderedImage.reader.getAbortRequest()) {
                                                break;
                                            }
                                            if (i41 != 0) {
                                                if (i41 == 1) {
                                                    i43 = i41;
                                                    sArr13 = sArr40;
                                                    sArr15 = r32;
                                                    i45 = 1;
                                                } else if (i41 != 2) {
                                                    if (i41 == 3) {
                                                        i43 = i41;
                                                        if (i33 == 1 && i38 == 1) {
                                                            rawRenderedImage.iis.readFully((int[]) sArr43, i182, i21);
                                                        } else {
                                                            sArr13 = sArr40;
                                                            sArr15 = r32;
                                                            rawRenderedImage.iis.readFully((int[]) sArr13, 0, i21);
                                                            int i184 = i182;
                                                            int i185 = 0;
                                                            while (i185 < i21) {
                                                                sArr43[i184] = sArr13[i185];
                                                                i185 += i33;
                                                                i184 += i38;
                                                            }
                                                            sArr12 = sArr43;
                                                            bArr3 = bArr15;
                                                            sArr14 = sArr41;
                                                            sArr16 = sArr42;
                                                            rawRenderedImage.iis.skipBytes(i139);
                                                            int i186 = i182 + i37;
                                                            bufferedImage = rawRenderedImage.destImage;
                                                            if (bufferedImage == null) {
                                                            }
                                                            rawRenderedImage.reader.processImageProgressWrapper((f2 + ((((i183 + 1.0f) / i39) / i178) / i177)) * 100.0f);
                                                            i40++;
                                                            sArr41 = sArr14;
                                                            i182 = i186;
                                                            sArr42 = sArr16;
                                                            r32 = sArr15;
                                                            bArr15 = bArr4;
                                                            i39 = i39;
                                                            i180 = i183 + i42;
                                                            i176 = i44;
                                                            sArr40 = sArr13;
                                                            sArr43 = sArr12;
                                                            i41 = i43;
                                                            rawRenderedImage = this;
                                                        }
                                                    } else if (i41 != 4) {
                                                        if (i41 == 5) {
                                                            if (i33 == 1 && i38 == 1) {
                                                                rawRenderedImage.iis.readFully(r32, i182, i21);
                                                            } else {
                                                                i43 = i41;
                                                                rawRenderedImage.iis.readFully(dArr2, 0, i21);
                                                                int i187 = i182;
                                                                int i188 = 0;
                                                                while (i188 < i21) {
                                                                    r32[i187] = dArr2[i188];
                                                                    i188 += i33;
                                                                    i187 += i38;
                                                                }
                                                            }
                                                        }
                                                        sArr12 = sArr43;
                                                        i43 = i41;
                                                        bArr3 = bArr15;
                                                        sArr13 = sArr40;
                                                        sArr14 = sArr41;
                                                        sArr15 = r32;
                                                        sArr16 = sArr42;
                                                        rawRenderedImage.iis.skipBytes(i139);
                                                        int i1862 = i182 + i37;
                                                        bufferedImage = rawRenderedImage.destImage;
                                                        if (bufferedImage == null) {
                                                            int[] iArr7 = {rawRenderedImage.destinationBands[i181]};
                                                            int i189 = i179;
                                                            int i190 = i176;
                                                            bArr4 = bArr3;
                                                            i44 = i190;
                                                            if (i189 < i44) {
                                                                i179 = i189;
                                                                rawRenderedImage.reader.processImageUpdateWrapper(bufferedImage, i117, i40, i39, 1, 1, 1, iArr7);
                                                            } else {
                                                                i179 = i189;
                                                                rawRenderedImage.reader.processImageUpdateWrapper(bufferedImage, i40, i118, 1, i39, 1, 1, iArr7);
                                                            }
                                                        } else {
                                                            int i191 = i176;
                                                            bArr4 = bArr3;
                                                            i44 = i191;
                                                        }
                                                        rawRenderedImage.reader.processImageProgressWrapper((f2 + ((((i183 + 1.0f) / i39) / i178) / i177)) * 100.0f);
                                                        i40++;
                                                        sArr41 = sArr14;
                                                        i182 = i1862;
                                                        sArr42 = sArr16;
                                                        r32 = sArr15;
                                                        bArr15 = bArr4;
                                                        i39 = i39;
                                                        i180 = i183 + i42;
                                                        i176 = i44;
                                                        sArr40 = sArr13;
                                                        sArr43 = sArr12;
                                                        i41 = i43;
                                                        rawRenderedImage = this;
                                                    } else {
                                                        i43 = i41;
                                                        if (i33 == 1 && i38 == 1) {
                                                            rawRenderedImage.iis.readFully(r7, i182, i21);
                                                        } else {
                                                            rawRenderedImage.iis.readFully(fArr3, 0, i21);
                                                            int i192 = i182;
                                                            int i193 = 0;
                                                            while (i193 < i21) {
                                                                r7[i192] = fArr3[i193];
                                                                i193 += i33;
                                                                i192 += i38;
                                                            }
                                                        }
                                                    }
                                                    sArr12 = sArr43;
                                                    bArr3 = bArr15;
                                                    sArr13 = sArr40;
                                                    sArr14 = sArr41;
                                                    sArr15 = r32;
                                                    sArr16 = sArr42;
                                                    rawRenderedImage.iis.skipBytes(i139);
                                                    int i18622 = i182 + i37;
                                                    bufferedImage = rawRenderedImage.destImage;
                                                    if (bufferedImage == null) {
                                                    }
                                                    rawRenderedImage.reader.processImageProgressWrapper((f2 + ((((i183 + 1.0f) / i39) / i178) / i177)) * 100.0f);
                                                    i40++;
                                                    sArr41 = sArr14;
                                                    i182 = i18622;
                                                    sArr42 = sArr16;
                                                    r32 = sArr15;
                                                    bArr15 = bArr4;
                                                    i39 = i39;
                                                    i180 = i183 + i42;
                                                    i176 = i44;
                                                    sArr40 = sArr13;
                                                    sArr43 = sArr12;
                                                    i41 = i43;
                                                    rawRenderedImage = this;
                                                } else {
                                                    i43 = i41;
                                                    sArr13 = sArr40;
                                                    sArr15 = r32;
                                                    i45 = 1;
                                                }
                                                if (i33 == i45 && i38 == i45) {
                                                    rawRenderedImage.iis.readFully(sArr42, i182, i21);
                                                    sArr12 = sArr43;
                                                    bArr3 = bArr15;
                                                    sArr14 = sArr41;
                                                    sArr16 = sArr42;
                                                    rawRenderedImage.iis.skipBytes(i139);
                                                    int i186222 = i182 + i37;
                                                    bufferedImage = rawRenderedImage.destImage;
                                                    if (bufferedImage == null) {
                                                    }
                                                    rawRenderedImage.reader.processImageProgressWrapper((f2 + ((((i183 + 1.0f) / i39) / i178) / i177)) * 100.0f);
                                                    i40++;
                                                    sArr41 = sArr14;
                                                    i182 = i186222;
                                                    sArr42 = sArr16;
                                                    r32 = sArr15;
                                                    bArr15 = bArr4;
                                                    i39 = i39;
                                                    i180 = i183 + i42;
                                                    i176 = i44;
                                                    sArr40 = sArr13;
                                                    sArr43 = sArr12;
                                                    i41 = i43;
                                                    rawRenderedImage = this;
                                                } else {
                                                    sArr12 = sArr43;
                                                    sArr14 = sArr41;
                                                    rawRenderedImage.iis.readFully(sArr14, 0, i21);
                                                    int i194 = i182;
                                                    int i195 = 0;
                                                    while (i195 < i21) {
                                                        sArr42[i194] = sArr14[i195];
                                                        i195 += i33;
                                                        i194 += i38;
                                                    }
                                                }
                                            } else {
                                                sArr12 = sArr43;
                                                i43 = i41;
                                                sArr13 = sArr40;
                                                sArr14 = sArr41;
                                                sArr15 = r32;
                                                if (i33 == 1 && i38 == 1) {
                                                    rawRenderedImage.iis.readFully(bArr16, i182, i21);
                                                } else {
                                                    bArr3 = bArr15;
                                                    sArr16 = sArr42;
                                                    rawRenderedImage.iis.readFully(bArr3, 0, i21);
                                                    int i196 = i182;
                                                    int i197 = 0;
                                                    while (i197 < i21) {
                                                        bArr16[i196] = bArr3[i197];
                                                        i197 += i33;
                                                        i196 += i38;
                                                    }
                                                    rawRenderedImage.iis.skipBytes(i139);
                                                    int i1862222 = i182 + i37;
                                                    bufferedImage = rawRenderedImage.destImage;
                                                    if (bufferedImage == null) {
                                                    }
                                                    rawRenderedImage.reader.processImageProgressWrapper((f2 + ((((i183 + 1.0f) / i39) / i178) / i177)) * 100.0f);
                                                    i40++;
                                                    sArr41 = sArr14;
                                                    i182 = i1862222;
                                                    sArr42 = sArr16;
                                                    r32 = sArr15;
                                                    bArr15 = bArr4;
                                                    i39 = i39;
                                                    i180 = i183 + i42;
                                                    i176 = i44;
                                                    sArr40 = sArr13;
                                                    sArr43 = sArr12;
                                                    i41 = i43;
                                                    rawRenderedImage = this;
                                                }
                                            }
                                            bArr3 = bArr15;
                                            sArr16 = sArr42;
                                            rawRenderedImage.iis.skipBytes(i139);
                                            int i18622222 = i182 + i37;
                                            bufferedImage = rawRenderedImage.destImage;
                                            if (bufferedImage == null) {
                                            }
                                            rawRenderedImage.reader.processImageProgressWrapper((f2 + ((((i183 + 1.0f) / i39) / i178) / i177)) * 100.0f);
                                            i40++;
                                            sArr41 = sArr14;
                                            i182 = i18622222;
                                            sArr42 = sArr16;
                                            r32 = sArr15;
                                            bArr15 = bArr4;
                                            i39 = i39;
                                            i180 = i183 + i42;
                                            i176 = i44;
                                            sArr40 = sArr13;
                                            sArr43 = sArr12;
                                            i41 = i43;
                                            rawRenderedImage = this;
                                        }
                                        int i198 = i41;
                                        short[] sArr44 = sArr40;
                                        short[] sArr45 = r32;
                                        i172 = i181 + 1;
                                        rawRenderedImage = this;
                                        i136 = i176;
                                        sArr7 = r7;
                                        sArr41 = sArr41;
                                        sArr29 = sArr42;
                                        sArr6 = sArr45;
                                        sArr8 = sArr43;
                                        bArr15 = bArr15;
                                        i167 = i198;
                                        i169 = i35;
                                        i171 = i178;
                                        i168 = i177;
                                        bArr14 = bArr16;
                                        sArr40 = sArr44;
                                        bandSize = i34;
                                        i170 = i179;
                                    }
                                    i27 = i168;
                                    i28 = i171;
                                    byte[] bArr17 = bArr15;
                                    i29 = i167;
                                    i30 = i170;
                                    i31 = i136;
                                    sArr33 = sArr41;
                                    dArr4 = dArr2;
                                    obj2 = sArr40;
                                    bArr10 = bArr14;
                                    bArr2 = bArr17;
                                } else if (rawRenderedImage.sampleModel instanceof ComponentSampleModel) {
                                    int i199 = i119;
                                    int i200 = (int) rawRenderedImage.tileDataSize;
                                    int dataType3 = rawRenderedImage.sampleModel.getDataType();
                                    if (dataType3 == 0) {
                                        if (bArr2 != null) {
                                            byte[] bArr18 = bArr2;
                                            if (bArr2.length >= rawRenderedImage.tileDataSize) {
                                                bArr2 = bArr18;
                                                rawRenderedImage.iis.readFully(bArr2, 0, (int) rawRenderedImage.tileDataSize);
                                                obj2 = obj2;
                                                sArr5 = sArr5;
                                            }
                                        }
                                        bArr2 = new byte[(int) rawRenderedImage.tileDataSize];
                                        rawRenderedImage.iis.readFully(bArr2, 0, (int) rawRenderedImage.tileDataSize);
                                        obj2 = obj2;
                                        sArr5 = sArr5;
                                    } else if (dataType3 == 1 || dataType3 == 2) {
                                        int i201 = i200 / 2;
                                        if (sArr5 == null || sArr5.length < i201) {
                                            sArr5 = new short[i201];
                                        }
                                        rawRenderedImage.iis.readFully(sArr5, 0, i201);
                                    } else if (dataType3 == 3) {
                                        int i202 = i200 / 4;
                                        if (obj2 == null || obj2.length < i202) {
                                            obj2 = new int[i202];
                                        }
                                        rawRenderedImage.iis.readFully(obj2, 0, i202);
                                    } else if (dataType3 == 4) {
                                        int i203 = i200 / 4;
                                        if (fArr3 == null || fArr3.length < i203) {
                                            fArr3 = new float[i203];
                                        }
                                        rawRenderedImage.iis.readFully(fArr3, 0, i203);
                                    } else if (dataType3 == 5) {
                                        int i204 = i200 / 8;
                                        if (dArr2 == null || dArr2.length < i204) {
                                            dArr2 = new double[i204];
                                        }
                                        rawRenderedImage.iis.readFully(dArr2, 0, i204);
                                    }
                                    byte[] bArr19 = bArr14;
                                    int i205 = 0;
                                    short[] sArr46 = sArr38;
                                    short[] sArr47 = sArr39;
                                    short[] sArr48 = sArr30;
                                    while (i205 < i164) {
                                        int i206 = iArr4[rawRenderedImage.destinationBands[i205]];
                                        byte[] bArr20 = bArr19;
                                        int i207 = i166;
                                        int i208 = i164;
                                        int offset3 = writableRaster3.getSampleModel().getOffset(i117 - writableRaster3.getSampleModelTranslateX(), i118 - writableRaster3.getSampleModelTranslateY(), rawRenderedImage.destinationBands[i205]);
                                        int i209 = iArr2[rawRenderedImage.destinationBands[i205]];
                                        if (i165 != 0) {
                                            if (i165 == 1) {
                                                sArr29 = writableRaster3.getDataBuffer().getData(i209);
                                                sArr19 = sArr48;
                                                sArr18 = sArr47;
                                                sArr17 = sArr46;
                                            } else if (i165 == 2) {
                                                sArr29 = writableRaster3.getDataBuffer().getData(i209);
                                                sArr19 = sArr48;
                                                sArr18 = sArr47;
                                                sArr17 = sArr46;
                                            } else if (i165 == 3) {
                                                sArr19 = writableRaster3.getDataBuffer().getData(i209);
                                                sArr18 = sArr47;
                                                sArr17 = sArr46;
                                            } else if (i165 != 4) {
                                                sArr19 = sArr48;
                                                sArr18 = sArr47;
                                                sArr17 = sArr46;
                                                if (i165 == 5) {
                                                    sArr19 = sArr48;
                                                    sArr18 = sArr47;
                                                    sArr17 = writableRaster3.getDataBuffer().getData(i209);
                                                }
                                            } else {
                                                sArr19 = sArr48;
                                                sArr18 = writableRaster3.getDataBuffer().getData(i209);
                                                sArr17 = sArr46;
                                            }
                                            data = bArr20;
                                            r39 = sArr19;
                                            r40 = sArr18;
                                            r41 = sArr17;
                                        } else {
                                            data = writableRaster3.getDataBuffer().getData(i209);
                                            r39 = sArr48;
                                            r40 = sArr47;
                                            r41 = sArr46;
                                        }
                                        int offset4 = rawRenderedImage.originalSampleModel.getOffset(i199, i120, rawRenderedImage.sourceBands[i205]);
                                        int i210 = rawRenderedImage.scaleX * i138;
                                        int i211 = i199;
                                        int i212 = i118;
                                        int i213 = i25;
                                        int i214 = i138;
                                        int i215 = 0;
                                        while (true) {
                                            if (i215 >= i213) {
                                                bArr5 = data;
                                                i46 = i120;
                                                break;
                                            }
                                            i46 = i120;
                                            if (rawRenderedImage.reader.getAbortRequest()) {
                                                bArr5 = data;
                                                break;
                                            }
                                            if (i165 == 0) {
                                                int i216 = i26;
                                                i47 = i165;
                                                i48 = i216;
                                                int i217 = offset3;
                                                int i218 = offset4;
                                                int i219 = 0;
                                                while (i219 < i48) {
                                                    data[i217] = bArr2[i218];
                                                    i219++;
                                                    i218 += i210;
                                                    i217 += i9;
                                                }
                                            } else if (i165 == 1 || i165 == 2) {
                                                int i220 = i26;
                                                i47 = i165;
                                                i48 = i220;
                                                int i221 = offset3;
                                                int i222 = offset4;
                                                int i223 = 0;
                                                while (i223 < i48) {
                                                    sArr29[i221] = sArr5[i222];
                                                    i223++;
                                                    i222 += i210;
                                                    i221 += i9;
                                                }
                                            } else if (i165 == 3) {
                                                int i224 = i26;
                                                i47 = i165;
                                                i48 = i224;
                                                int i225 = offset3;
                                                int i226 = offset4;
                                                int i227 = 0;
                                                while (i227 < i48) {
                                                    r39[i225] = obj2[i226];
                                                    i227++;
                                                    i226 += i210;
                                                    i225 += i9;
                                                }
                                            } else if (i165 == 4) {
                                                int i228 = i26;
                                                i47 = i165;
                                                i48 = i228;
                                                int i229 = offset3;
                                                int i230 = offset4;
                                                int i231 = 0;
                                                while (i231 < i48) {
                                                    r40[i229] = fArr3[i230];
                                                    i231++;
                                                    i230 += i210;
                                                    i229 += i9;
                                                }
                                            } else if (i165 != 5) {
                                                int i232 = i26;
                                                i47 = i165;
                                                i48 = i232;
                                            } else {
                                                int i233 = offset3;
                                                int i234 = offset4;
                                                int i235 = 0;
                                                int i236 = i26;
                                                i47 = i165;
                                                i48 = i236;
                                                while (i235 < i48) {
                                                    r41[i233] = dArr2[i234];
                                                    i235++;
                                                    i234 += i210;
                                                    i233 += i9;
                                                }
                                            }
                                            int i237 = offset3 + i10;
                                            offset4 += rawRenderedImage.scaleY * i136;
                                            BufferedImage bufferedImage4 = rawRenderedImage.destImage;
                                            int i238 = i48;
                                            if (bufferedImage4 != null) {
                                                rawRenderedImage.reader.processImageUpdateWrapper(bufferedImage4, i117, i212, i213, 1, 1, 1, new int[]{rawRenderedImage.destinationBands[i205]});
                                            }
                                            rawRenderedImage.reader.processImageProgressWrapper(f2 + ((((i215 + 1.0f) / i213) / i208) / i207));
                                            i215 += rawRenderedImage.scaleY;
                                            i212++;
                                            offset3 = i237;
                                            data = data;
                                            i165 = i47;
                                            fArr3 = fArr3;
                                            i120 = i46;
                                            i26 = i238;
                                        }
                                        i205++;
                                        i166 = i207;
                                        i164 = i208;
                                        bArr19 = bArr5;
                                        i165 = i165;
                                        fArr3 = fArr3;
                                        i138 = i214;
                                        i120 = i46;
                                        i25 = i213;
                                        i26 = i26;
                                        i199 = i211;
                                        sArr48 = r39;
                                        sArr47 = r40;
                                        sArr46 = r41;
                                    }
                                    bArr10 = bArr19;
                                    i30 = i138;
                                    i29 = i165;
                                    i31 = i136;
                                    i28 = i164;
                                    dArr4 = dArr2;
                                    sArr33 = sArr5;
                                    i27 = i166;
                                    sArr8 = sArr48;
                                    sArr7 = sArr47;
                                    sArr6 = sArr46;
                                } else {
                                    throw new IllegalArgumentException(I18N.getString("RawRenderedImage1"));
                                }
                            }
                            i93 = i19 + 1;
                            rawRenderedImage = this;
                            i80 = i27;
                            i6 = i31;
                            bArr11 = bArr2;
                            obj4 = obj2;
                            clip3 = i18;
                            createWritableRaster = writableRaster3;
                            clip = i99;
                            numXTiles = i101;
                            i5 = i30;
                            i92 = i29;
                            i75 = i104;
                            i72 = i106;
                            i73 = i110;
                            i74 = i113;
                            i88 = i20;
                            i90 = i28;
                            sArr30 = sArr8;
                            sArr31 = sArr7;
                            sArr32 = sArr6;
                        } else {
                            if (dataType2 == 3) {
                                if (obj4 != null) {
                                    ?? r12 = obj4;
                                    if (r12.length >= i21) {
                                        obj = r12;
                                        sArr5 = sArr33;
                                    }
                                }
                                i24 = i137;
                                i25 = i102;
                                sArr5 = sArr33;
                                fArr3 = fArr4;
                                i26 = i111;
                                bArr2 = bArr12;
                                obj2 = new int[i21];
                                dArr2 = dArr5;
                                if (rawRenderedImage.sampleModel instanceof PixelInterleavedSampleModel) {
                                }
                                i93 = i19 + 1;
                                rawRenderedImage = this;
                                i80 = i27;
                                i6 = i31;
                                bArr11 = bArr2;
                                obj4 = obj2;
                                clip3 = i18;
                                createWritableRaster = writableRaster3;
                                clip = i99;
                                numXTiles = i101;
                                i5 = i30;
                                i92 = i29;
                                i75 = i104;
                                i72 = i106;
                                i73 = i110;
                                i74 = i113;
                                i88 = i20;
                                i90 = i28;
                                sArr30 = sArr8;
                                sArr31 = sArr7;
                                sArr32 = sArr6;
                            } else if (dataType2 != 4) {
                                if (dataType2 == 5) {
                                    if (dArr5 == null || dArr5.length < i21) {
                                        dArr2 = new double[i21];
                                        i25 = i102;
                                        sArr5 = sArr33;
                                        fArr3 = fArr4;
                                        i26 = i111;
                                        bArr2 = bArr12;
                                        obj2 = obj4;
                                        i24 = i137;
                                        if (rawRenderedImage.sampleModel instanceof PixelInterleavedSampleModel) {
                                        }
                                        i93 = i19 + 1;
                                        rawRenderedImage = this;
                                        i80 = i27;
                                        i6 = i31;
                                        bArr11 = bArr2;
                                        obj4 = obj2;
                                        clip3 = i18;
                                        createWritableRaster = writableRaster3;
                                        clip = i99;
                                        numXTiles = i101;
                                        i5 = i30;
                                        i92 = i29;
                                        i75 = i104;
                                        i72 = i106;
                                        i73 = i110;
                                        i74 = i113;
                                        i88 = i20;
                                        i90 = i28;
                                        sArr30 = sArr8;
                                        sArr31 = sArr7;
                                        sArr32 = sArr6;
                                    } else {
                                        dArr5 = dArr5;
                                    }
                                }
                                sArr5 = sArr33;
                                obj = obj4;
                            } else if (fArr4 == null || fArr4.length < i21) {
                                i25 = i102;
                                fArr3 = new float[i21];
                                sArr5 = sArr33;
                                dArr2 = dArr5;
                                i26 = i111;
                                bArr2 = bArr12;
                                obj2 = obj4;
                                i24 = i137;
                                if (rawRenderedImage.sampleModel instanceof PixelInterleavedSampleModel) {
                                }
                                i93 = i19 + 1;
                                rawRenderedImage = this;
                                i80 = i27;
                                i6 = i31;
                                bArr11 = bArr2;
                                obj4 = obj2;
                                clip3 = i18;
                                createWritableRaster = writableRaster3;
                                clip = i99;
                                numXTiles = i101;
                                i5 = i30;
                                i92 = i29;
                                i75 = i104;
                                i72 = i106;
                                i73 = i110;
                                i74 = i113;
                                i88 = i20;
                                i90 = i28;
                                sArr30 = sArr8;
                                sArr31 = sArr7;
                                sArr32 = sArr6;
                            } else {
                                fArr4 = fArr4;
                                sArr5 = sArr33;
                                obj = obj4;
                            }
                            obj2 = obj;
                            dArr2 = dArr5;
                            fArr3 = fArr4;
                            if (rawRenderedImage.sampleModel instanceof PixelInterleavedSampleModel) {
                            }
                            i93 = i19 + 1;
                            rawRenderedImage = this;
                            i80 = i27;
                            i6 = i31;
                            bArr11 = bArr2;
                            obj4 = obj2;
                            clip3 = i18;
                            createWritableRaster = writableRaster3;
                            clip = i99;
                            numXTiles = i101;
                            i5 = i30;
                            i92 = i29;
                            i75 = i104;
                            i72 = i106;
                            i73 = i110;
                            i74 = i113;
                            i88 = i20;
                            i90 = i28;
                            sArr30 = sArr8;
                            sArr31 = sArr7;
                            sArr32 = sArr6;
                        }
                    } else {
                        i23 = i103;
                        sArr5 = sArr33;
                        obj = obj4;
                        if (bArr12 == null || bArr12.length < i21) {
                            i24 = i137;
                            i25 = i102;
                            i26 = i111;
                            bArr2 = new byte[i21];
                            obj2 = obj;
                            dArr2 = dArr5;
                            fArr3 = fArr4;
                            if (rawRenderedImage.sampleModel instanceof PixelInterleavedSampleModel) {
                            }
                            i93 = i19 + 1;
                            rawRenderedImage = this;
                            i80 = i27;
                            i6 = i31;
                            bArr11 = bArr2;
                            obj4 = obj2;
                            clip3 = i18;
                            createWritableRaster = writableRaster3;
                            clip = i99;
                            numXTiles = i101;
                            i5 = i30;
                            i92 = i29;
                            i75 = i104;
                            i72 = i106;
                            i73 = i110;
                            i74 = i113;
                            i88 = i20;
                            i90 = i28;
                            sArr30 = sArr8;
                            sArr31 = sArr7;
                            sArr32 = sArr6;
                        }
                    }
                    i24 = i137;
                    i25 = i102;
                    i26 = i111;
                    bArr2 = bArr12;
                    obj2 = obj;
                    dArr2 = dArr5;
                    fArr3 = fArr4;
                    if (rawRenderedImage.sampleModel instanceof PixelInterleavedSampleModel) {
                    }
                    i93 = i19 + 1;
                    rawRenderedImage = this;
                    i80 = i27;
                    i6 = i31;
                    bArr11 = bArr2;
                    obj4 = obj2;
                    clip3 = i18;
                    createWritableRaster = writableRaster3;
                    clip = i99;
                    numXTiles = i101;
                    i5 = i30;
                    i92 = i29;
                    i75 = i104;
                    i72 = i106;
                    i73 = i110;
                    i74 = i113;
                    i88 = i20;
                    i90 = i28;
                    sArr30 = sArr8;
                    sArr31 = sArr7;
                    sArr32 = sArr6;
                }
                i93 = i19 + 1;
                rawRenderedImage = this;
                i80 = i27;
                i6 = i31;
                bArr11 = bArr2;
                obj4 = obj2;
                clip3 = i18;
                createWritableRaster = writableRaster3;
                clip = i99;
                numXTiles = i101;
                i5 = i30;
                i92 = i29;
                i75 = i104;
                i72 = i106;
                i73 = i110;
                i74 = i113;
                i88 = i20;
                i90 = i28;
                sArr30 = sArr8;
                sArr31 = sArr7;
                sArr32 = sArr6;
            }
            int i239 = i80;
            int i240 = i90;
            short[] sArr49 = sArr33;
            Object obj5 = obj4;
            i6 = i6;
            bArr8 = bArr11;
            bArr10 = bArr10;
            i8 = i91;
            dArr3 = dArr;
            clip4 = i89;
            sArr25 = sArr31;
            fArr2 = fArr;
            clip = i16;
            numXTiles = numXTiles;
            i5 = i11;
            dataType = i92;
            i75 = i12;
            i72 = i13;
            i73 = i14;
            i74 = i15;
            i7 = i240;
            i88 = i17 + 1;
            sArr27 = sArr49;
            obj3 = obj5;
            clip3 = i18;
            sArr24 = sArr30;
            rawRenderedImage = this;
            i80 = i239;
            createWritableRaster = writableRaster2;
            sArr26 = sArr32;
        }
        return createWritableRaster;
    }

    public void setDestImage(BufferedImage bufferedImage) {
        this.destImage = bufferedImage;
    }

    public void clearDestImage() {
        this.destImage = null;
    }

    private int getTileNum(int i, int i2) {
        int minTileY = (((i2 - getMinTileY()) * getNumXTiles()) + i) - getMinTileX();
        if (minTileY < 0 || minTileY >= getNumXTiles() * getNumYTiles()) {
            throw new IllegalArgumentException(I18N.getString("RawRenderedImage0"));
        }
        return minTileY;
    }
}
