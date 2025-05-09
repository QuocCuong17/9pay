package com.github.jaiimageio.impl.plugins.pnm;

import com.github.jaiimageio.impl.common.ImageUtil;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.MultiPixelPackedSampleModel;
import java.awt.image.PixelInterleavedSampleModel;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import sun.security.action.GetPropertyAction;

/* loaded from: classes3.dex */
public class PNMImageReader extends ImageReader {
    private static final int LINE_FEED = 10;
    private static final int PBM_ASCII = 49;
    private static final int PBM_RAW = 52;
    private static final int PGM_ASCII = 50;
    private static final int PGM_RAW = 53;
    private static final int PPM_ASCII = 51;
    private static final int PPM_RAW = 54;
    private static byte[] lineSeparator;
    private String aLine;
    private boolean gotHeader;
    private int height;
    private ImageInputStream iis;
    private long imageDataOffset;
    private int maxValue;
    private PNMMetadata metadata;
    private StringTokenizer token;
    private int variant;
    private int width;

    private boolean isRaw(int i) {
        return i >= 52;
    }

    public boolean canReadRaster() {
        return true;
    }

    public int getNumImages(boolean z) throws IOException {
        return 1;
    }

    public IIOMetadata getStreamMetadata() throws IOException {
        return null;
    }

    static {
        if (lineSeparator == null) {
            lineSeparator = ((String) AccessController.doPrivileged((PrivilegedAction) new GetPropertyAction("line.separator"))).getBytes();
        }
    }

    public PNMImageReader(ImageReaderSpi imageReaderSpi) {
        super(imageReaderSpi);
        this.iis = null;
        this.gotHeader = false;
    }

    public void setInput(Object obj, boolean z, boolean z2) {
        super.setInput(obj, z, z2);
        this.iis = (ImageInputStream) obj;
    }

    public int getWidth(int i) throws IOException {
        checkIndex(i);
        readHeader();
        return this.width;
    }

    public int getHeight(int i) throws IOException {
        checkIndex(i);
        readHeader();
        return this.height;
    }

    public int getVariant() {
        return this.variant;
    }

    public int getMaxValue() {
        return this.maxValue;
    }

    private void checkIndex(int i) {
        if (i != 0) {
            throw new IndexOutOfBoundsException(I18N.getString("PNMImageReader1"));
        }
    }

    public synchronized void readHeader() throws IOException {
        if (this.gotHeader) {
            this.iis.seek(this.imageDataOffset);
            return;
        }
        ImageInputStream imageInputStream = this.iis;
        if (imageInputStream != null) {
            if (imageInputStream.readByte() != 80) {
                throw new RuntimeException(I18N.getString("PNMImageReader0"));
            }
            byte readByte = this.iis.readByte();
            this.variant = readByte;
            if (readByte < 49 || readByte > 54) {
                throw new RuntimeException(I18N.getString("PNMImageReader0"));
            }
            PNMMetadata pNMMetadata = new PNMMetadata();
            this.metadata = pNMMetadata;
            pNMMetadata.setVariant(this.variant);
            this.iis.readLine();
            readComments(this.iis, this.metadata);
            this.width = readInteger(this.iis);
            this.height = readInteger(this.iis);
            int i = this.variant;
            if (i != 49 && i != 52) {
                this.maxValue = readInteger(this.iis);
                this.metadata.setWidth(this.width);
                this.metadata.setHeight(this.height);
                this.metadata.setMaxBitDepth(this.maxValue);
                this.gotHeader = true;
                this.imageDataOffset = this.iis.getStreamPosition();
            }
            this.maxValue = 1;
            this.metadata.setWidth(this.width);
            this.metadata.setHeight(this.height);
            this.metadata.setMaxBitDepth(this.maxValue);
            this.gotHeader = true;
            this.imageDataOffset = this.iis.getStreamPosition();
        }
    }

    public Iterator getImageTypes(int i) throws IOException {
        PixelInterleavedSampleModel multiPixelPackedSampleModel;
        ColorModel indexColorModel;
        checkIndex(i);
        readHeader();
        int i2 = (this.variant - 49) % 3;
        ArrayList arrayList = new ArrayList(1);
        int i3 = this.maxValue;
        int i4 = i3 < 256 ? 0 : i3 < 65536 ? 1 : 3;
        int i5 = this.variant;
        if (i5 == 49 || i5 == 52) {
            multiPixelPackedSampleModel = new MultiPixelPackedSampleModel(0, this.width, this.height, 1);
            byte[] bArr = {-1, 0};
            indexColorModel = new IndexColorModel(1, 2, bArr, bArr, bArr);
        } else {
            int i6 = this.width;
            multiPixelPackedSampleModel = new PixelInterleavedSampleModel(i4, i6, this.height, i2 == 1 ? 1 : 3, i6 * (i2 == 1 ? 1 : 3), i2 == 1 ? new int[]{0} : new int[]{0, 1, 2});
            indexColorModel = ImageUtil.createColorModel(null, multiPixelPackedSampleModel);
        }
        arrayList.add(new ImageTypeSpecifier(indexColorModel, multiPixelPackedSampleModel));
        return arrayList.iterator();
    }

    public ImageReadParam getDefaultReadParam() {
        return new ImageReadParam();
    }

    public IIOMetadata getImageMetadata(int i) throws IOException {
        checkIndex(i);
        readHeader();
        return this.metadata;
    }

    public boolean isRandomAccessEasy(int i) throws IOException {
        checkIndex(i);
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public BufferedImage read(int i, ImageReadParam imageReadParam) throws IOException {
        int[] iArr;
        int[] iArr2;
        int i2;
        PixelInterleavedSampleModel multiPixelPackedSampleModel;
        ColorModel indexColorModel;
        BufferedImage bufferedImage;
        WritableRaster writableRaster;
        BufferedImage bufferedImage2;
        int i3;
        checkIndex(i);
        clearAbortRequest();
        processImageStarted(i);
        ImageReadParam defaultReadParam = imageReadParam == null ? getDefaultReadParam() : imageReadParam;
        readHeader();
        Rectangle rectangle = new Rectangle(0, 0, 0, 0);
        Rectangle rectangle2 = new Rectangle(0, 0, 0, 0);
        computeRegions(defaultReadParam, this.width, this.height, defaultReadParam.getDestination(), rectangle, rectangle2);
        int sourceXSubsampling = defaultReadParam.getSourceXSubsampling();
        int sourceYSubsampling = defaultReadParam.getSourceYSubsampling();
        int[] sourceBands = defaultReadParam.getSourceBands();
        int[] destinationBands = defaultReadParam.getDestinationBands();
        boolean z = (sourceBands == null || destinationBands == null) ? false : true;
        boolean z2 = rectangle2.equals(new Rectangle(0, 0, this.width, this.height)) || z;
        if (isRaw(this.variant) && this.maxValue >= 256) {
            this.maxValue = 255;
        }
        int i4 = this.variant;
        int i5 = (i4 == 51 || i4 == 54) ? 3 : 1;
        if (z) {
            iArr = sourceBands;
            iArr2 = destinationBands;
        } else {
            int[] iArr3 = new int[i5];
            int[] iArr4 = new int[i5];
            for (int i6 = 0; i6 < i5; i6++) {
                iArr3[i6] = i6;
                iArr4[i6] = i6;
            }
            iArr = iArr3;
            iArr2 = iArr4;
        }
        int i7 = this.maxValue;
        int i8 = i7 < 256 ? 0 : i7 < 65536 ? 1 : 3;
        int i9 = this.variant;
        if (i9 == 49 || i9 == 52) {
            i2 = i5;
            multiPixelPackedSampleModel = new MultiPixelPackedSampleModel(0, rectangle2.width, rectangle2.height, 1);
            byte[] bArr = {-1, 0};
            indexColorModel = new IndexColorModel(1, 2, bArr, bArr, bArr);
        } else {
            i2 = i5;
            multiPixelPackedSampleModel = new PixelInterleavedSampleModel(i8, rectangle2.width, rectangle2.height, iArr.length, rectangle2.width * iArr.length, iArr2);
            indexColorModel = ImageUtil.createColorModel(null, multiPixelPackedSampleModel);
        }
        BufferedImage destination = defaultReadParam.getDestination();
        if (destination == null) {
            SampleModel createCompatibleSampleModel = multiPixelPackedSampleModel.createCompatibleSampleModel(rectangle2.x + rectangle2.width, rectangle2.y + rectangle2.height);
            if (z) {
                createCompatibleSampleModel = createCompatibleSampleModel.createSubsetSampleModel(iArr);
            }
            writableRaster = Raster.createWritableRaster(createCompatibleSampleModel, new Point());
            bufferedImage = new BufferedImage(indexColorModel, writableRaster, false, (Hashtable) null);
        } else {
            WritableRaster writableTile = destination.getWritableTile(0, 0);
            destination.getSampleModel();
            destination.getColorModel();
            z2 &= rectangle2.equals(writableTile.getBounds());
            bufferedImage = destination;
            writableRaster = writableTile;
        }
        float f = 100.0f;
        int i10 = 7;
        switch (this.variant) {
            case 49:
                bufferedImage2 = bufferedImage;
                int i11 = 3;
                byte[] data = writableRaster.getDataBuffer().getData();
                if (z2) {
                    int i12 = 0;
                    int i13 = 0;
                    while (i13 < this.height) {
                        int i14 = 0;
                        int i15 = 0;
                        int i16 = 7;
                        while (true) {
                            int i17 = this.width;
                            if (i14 < i17) {
                                i15 |= (readInteger(this.iis) & 1) << i16;
                                i16--;
                                if (i16 == -1) {
                                    data[i12] = (byte) i15;
                                    i12++;
                                    i15 = 0;
                                    i16 = 7;
                                }
                                i14++;
                            } else {
                                if (i16 != 7) {
                                    data[i12] = (byte) i15;
                                    i3 = i12 + 1;
                                } else {
                                    i3 = i12;
                                }
                                processImageUpdate(bufferedImage2, 0, i13, i17, 1, 1, 1, iArr2);
                                processImageProgress((i13 * 100.0f) / this.height);
                                i13++;
                                i12 = i3;
                            }
                        }
                    }
                    break;
                } else {
                    int i18 = 7;
                    skipInteger(this.iis, (rectangle.y * this.width) + rectangle.x);
                    int i19 = sourceXSubsampling - 1;
                    int i20 = this.width;
                    int i21 = (((sourceYSubsampling - 1) * i20) + i20) - (rectangle2.width * sourceXSubsampling);
                    int width = (((bufferedImage2.getWidth() + 7) >> 3) * rectangle2.y) + (rectangle2.x >> 3);
                    int i22 = 0;
                    while (i22 < rectangle2.height) {
                        int i23 = 7 - (rectangle2.x & i18);
                        int i24 = 0;
                        for (int i25 = 0; i25 < rectangle2.width; i25++) {
                            i24 |= (readInteger(this.iis) & 1) << i23;
                            i23--;
                            if (i23 == -1) {
                                data[width] = (byte) i24;
                                width++;
                                i23 = i18;
                                i24 = 0;
                            }
                            skipInteger(this.iis, i19);
                        }
                        if (i23 != i18) {
                            data[width] = (byte) i24;
                            width++;
                        }
                        int i26 = width + (rectangle2.x >> i11);
                        skipInteger(this.iis, i21);
                        int i27 = i22;
                        processImageUpdate(bufferedImage2, 0, i22, rectangle2.width, 1, 1, 1, iArr2);
                        processImageProgress((i27 * 100.0f) / rectangle2.height);
                        i22 = i27 + 1;
                        width = i26;
                        i11 = i11;
                        i18 = 7;
                    }
                    break;
                }
            case 50:
            case 51:
            case 53:
            case 54:
                int i28 = (sourceXSubsampling - 1) * i2;
                int i29 = ((this.width * sourceYSubsampling) - (rectangle2.width * sourceXSubsampling)) * i2;
                int width2 = ((bufferedImage.getWidth() * rectangle2.y) + rectangle2.x) * i2;
                if (i8 != 0) {
                    if (i8 != 1) {
                        if (i8 == 3) {
                            int[] data2 = writableRaster.getDataBuffer().getData();
                            skipInteger(this.iis, (rectangle.y * this.width * i2) + rectangle.x);
                            if (z) {
                                int i30 = i2;
                                int[] iArr5 = new int[i30];
                                int i31 = 0;
                                while (i31 < rectangle2.height) {
                                    for (int i32 = 0; i32 < rectangle2.width; i32++) {
                                        for (int i33 = 0; i33 < i30; i33++) {
                                            iArr5[i33] = readInteger(this.iis);
                                        }
                                        for (int i34 = 0; i34 < iArr.length; i34++) {
                                            data2[iArr2[i34] + width2] = iArr5[iArr[i34]];
                                        }
                                        width2 += iArr.length;
                                        skipInteger(this.iis, i28);
                                    }
                                    int length = width2 + (rectangle2.x * iArr.length);
                                    skipInteger(this.iis, i29);
                                    int i35 = i31;
                                    processImageUpdate(bufferedImage, 0, i31, rectangle2.width, 1, 1, 1, iArr2);
                                    processImageProgress((i35 * 100.0f) / rectangle2.height);
                                    i31 = i35 + 1;
                                    i29 = i29;
                                    width2 = length;
                                    iArr5 = iArr5;
                                    bufferedImage = bufferedImage;
                                }
                            } else {
                                bufferedImage2 = bufferedImage;
                                int i36 = i2;
                                int i37 = 0;
                                while (i37 < rectangle2.height) {
                                    for (int i38 = 0; i38 < rectangle2.width; i38++) {
                                        int i39 = 0;
                                        while (i39 < i36) {
                                            data2[width2] = readInteger(this.iis);
                                            i39++;
                                            width2++;
                                        }
                                        skipInteger(this.iis, i28);
                                    }
                                    int length2 = width2 + (rectangle2.x * iArr.length);
                                    skipInteger(this.iis, i29);
                                    int i40 = i36;
                                    int i41 = i37;
                                    processImageUpdate(bufferedImage2, 0, i37, rectangle2.width, 1, 1, 1, iArr2);
                                    processImageProgress((i41 * 100.0f) / rectangle2.height);
                                    i37 = i41 + 1;
                                    width2 = length2;
                                    i36 = i40;
                                }
                                break;
                            }
                        }
                        bufferedImage2 = bufferedImage;
                        break;
                    } else {
                        bufferedImage2 = bufferedImage;
                        int i42 = i2;
                        short[] data3 = writableRaster.getDataBuffer().getData();
                        skipInteger(this.iis, (rectangle.y * this.width * i42) + rectangle.x);
                        if (z) {
                            short[] sArr = new short[i42];
                            int i43 = 0;
                            while (i43 < rectangle2.height) {
                                for (int i44 = 0; i44 < rectangle2.width; i44++) {
                                    for (int i45 = 0; i45 < i42; i45++) {
                                        sArr[i45] = (short) readInteger(this.iis);
                                    }
                                    for (int i46 = 0; i46 < iArr.length; i46++) {
                                        data3[iArr2[i46] + width2] = sArr[iArr[i46]];
                                    }
                                    width2 += iArr.length;
                                    skipInteger(this.iis, i28);
                                }
                                int length3 = width2 + (rectangle2.x * iArr.length);
                                skipInteger(this.iis, i29);
                                short[] sArr2 = sArr;
                                int i47 = i43;
                                processImageUpdate(bufferedImage2, 0, i43, rectangle2.width, 1, 1, 1, iArr2);
                                processImageProgress((i47 * 100.0f) / rectangle2.height);
                                i43 = i47 + 1;
                                width2 = length3;
                                sArr = sArr2;
                            }
                            break;
                        } else {
                            int i48 = 0;
                            while (i48 < rectangle2.height) {
                                for (int i49 = 0; i49 < rectangle2.width; i49++) {
                                    int i50 = 0;
                                    while (i50 < i42) {
                                        data3[width2] = (short) readInteger(this.iis);
                                        i50++;
                                        width2++;
                                    }
                                    skipInteger(this.iis, i28);
                                }
                                int length4 = width2 + (rectangle2.x * iArr.length);
                                skipInteger(this.iis, i29);
                                processImageUpdate(bufferedImage2, 0, i48, rectangle2.width, 1, 1, 1, iArr2);
                                processImageProgress((i48 * 100.0f) / rectangle2.height);
                                i48++;
                                width2 = length4;
                            }
                            break;
                        }
                    }
                } else {
                    bufferedImage2 = bufferedImage;
                    int i51 = i2;
                    byte[] data4 = writableRaster.getDataBuffer().getData();
                    if (isRaw(this.variant)) {
                        if (z2) {
                            this.iis.readFully(data4);
                            processImageUpdate(bufferedImage2, 0, 0, this.width, this.height, 1, 1, iArr2);
                            processImageProgress(100.0f);
                            break;
                        } else {
                            this.iis.skipBytes(rectangle.y * this.width * i51);
                            int i52 = this.width;
                            int i53 = (sourceYSubsampling - 1) * i52 * i51;
                            byte[] bArr2 = new byte[i52 * i51];
                            int i54 = sourceXSubsampling * i51;
                            int i55 = rectangle.x * i51;
                            int i56 = 0;
                            while (i56 < rectangle2.height) {
                                this.iis.read(bArr2);
                                int i57 = rectangle.x;
                                int i58 = i55;
                                while (i57 < rectangle.x + rectangle.width) {
                                    for (int i59 = 0; i59 < iArr.length; i59++) {
                                        data4[iArr2[i59] + width2] = bArr2[iArr[i59] + i58];
                                    }
                                    width2 += iArr.length;
                                    i57 += sourceXSubsampling;
                                    i58 += i54;
                                }
                                int i60 = width2 + (rectangle2.x * i51);
                                this.iis.skipBytes(i53);
                                int i61 = i56;
                                processImageUpdate(bufferedImage2, 0, i56, rectangle2.width, 1, 1, 1, iArr2);
                                processImageProgress((i61 * 100.0f) / rectangle2.height);
                                i56 = i61 + 1;
                                i51 = i51;
                                width2 = i60;
                                data4 = data4;
                                sourceXSubsampling = sourceXSubsampling;
                            }
                            break;
                        }
                    } else {
                        skipInteger(this.iis, ((rectangle.y * this.width) + rectangle.x) * i51);
                        if (z) {
                            byte[] bArr3 = new byte[i51];
                            int i62 = 0;
                            while (i62 < rectangle2.height) {
                                for (int i63 = 0; i63 < rectangle2.width; i63++) {
                                    for (int i64 = 0; i64 < i51; i64++) {
                                        bArr3[i64] = (byte) readInteger(this.iis);
                                    }
                                    for (int i65 = 0; i65 < iArr.length; i65++) {
                                        data4[iArr2[i65] + width2] = bArr3[iArr[i65]];
                                    }
                                    width2 += iArr.length;
                                    skipInteger(this.iis, i28);
                                }
                                int length5 = width2 + (rectangle2.x * iArr.length);
                                skipInteger(this.iis, i29);
                                byte[] bArr4 = bArr3;
                                int i66 = i62;
                                processImageUpdate(bufferedImage2, 0, i62, rectangle2.width, 1, 1, 1, iArr2);
                                processImageProgress((i66 * 100.0f) / rectangle2.height);
                                i62 = i66 + 1;
                                width2 = length5;
                                bArr3 = bArr4;
                            }
                            break;
                        } else {
                            int i67 = 0;
                            while (i67 < rectangle2.height) {
                                for (int i68 = 0; i68 < rectangle2.width; i68++) {
                                    int i69 = 0;
                                    while (i69 < i51) {
                                        data4[width2] = (byte) readInteger(this.iis);
                                        i69++;
                                        width2++;
                                    }
                                    skipInteger(this.iis, i28);
                                }
                                int length6 = width2 + (rectangle2.x * iArr.length);
                                skipInteger(this.iis, i29);
                                processImageUpdate(bufferedImage2, 0, i67, rectangle2.width, 1, 1, 1, iArr2);
                                processImageProgress((i67 * 100.0f) / rectangle2.height);
                                i67++;
                                width2 = length6;
                            }
                            break;
                        }
                    }
                }
            case 52:
                byte[] data5 = writableRaster.getDataBuffer().getData();
                if (z2) {
                    this.iis.readFully(data5, 0, data5.length);
                    processImageUpdate(bufferedImage, 0, 0, this.width, this.height, 1, 1, iArr2);
                    processImageProgress(100.0f);
                } else if (sourceXSubsampling == 1 && rectangle.x % 8 == 0) {
                    int i70 = rectangle.x >> 3;
                    int i71 = (this.width + 7) >> 3;
                    int width3 = (writableRaster.getWidth() + 7) >> 3;
                    int i72 = (rectangle.width + 7) >> 3;
                    this.iis.skipBytes((rectangle.y * i71) + i70);
                    int i73 = (((sourceYSubsampling - 1) * i71) + i71) - i72;
                    byte[] bArr5 = new byte[i72];
                    int i74 = rectangle2.x & 7;
                    boolean z3 = i74 != 0;
                    int i75 = (rectangle2.y * width3) + (rectangle2.x >> 3);
                    int i76 = 0;
                    while (i76 < rectangle2.height) {
                        if (z3) {
                            this.iis.read(bArr5, 0, i72);
                            int i77 = (255 << i74) & 255;
                            int i78 = (~i77) & 255;
                            int i79 = 8 - i74;
                            int i80 = i75;
                            int i81 = 0;
                            while (i81 < i72 - 1) {
                                int i82 = (bArr5[i81] & i78) << i79;
                                i81++;
                                data5[i80] = (byte) (i82 | ((bArr5[i81] & i77) >> i74));
                                i80++;
                            }
                            data5[i80] = (byte) ((bArr5[i81] & i78) << i79);
                        } else {
                            this.iis.read(data5, i75, i72);
                        }
                        this.iis.skipBytes(i73);
                        int i83 = i76;
                        int i84 = i72;
                        float f2 = f;
                        processImageUpdate(bufferedImage, 0, i76, rectangle2.width, 1, 1, 1, iArr2);
                        processImageProgress((i83 * f2) / rectangle2.height);
                        i76 = i83 + 1;
                        f = f2;
                        i75 += width3;
                        bArr5 = bArr5;
                        i72 = i84;
                    }
                } else {
                    int i85 = (this.width + 7) >> 3;
                    byte[] bArr6 = new byte[i85];
                    this.iis.skipBytes(rectangle.y * i85);
                    int i86 = i85 * (sourceYSubsampling - 1);
                    int width4 = (((bufferedImage.getWidth() + 7) >> 3) * rectangle2.y) + (rectangle2.x >> 3);
                    int i87 = 0;
                    while (i87 < rectangle2.height) {
                        this.iis.read(bArr6, 0, i85);
                        this.iis.skipBytes(i86);
                        int i88 = 7 - (rectangle2.x & i10);
                        int i89 = 0;
                        for (int i90 = rectangle.x; i90 < rectangle.x + rectangle.width; i90 += sourceXSubsampling) {
                            int i91 = (((bArr6[i90 >> 3] >> (7 - (i90 & 7))) & 1) << i88) | i89;
                            i88--;
                            if (i88 == -1) {
                                data5[width4] = (byte) i91;
                                width4++;
                                i88 = 7;
                                i89 = 0;
                            } else {
                                i89 = i91;
                            }
                        }
                        if (i88 != 7) {
                            data5[width4] = (byte) i89;
                            width4++;
                        }
                        int i92 = width4 + (rectangle2.x >> 3);
                        int i93 = i87;
                        processImageUpdate(bufferedImage, 0, i87, rectangle2.width, 1, 1, 1, iArr2);
                        processImageProgress((i93 * 100.0f) / rectangle2.height);
                        i87 = i93 + 1;
                        i86 = i86;
                        i85 = i85;
                        width4 = i92;
                        bArr6 = bArr6;
                        i10 = 7;
                    }
                }
                bufferedImage2 = bufferedImage;
                break;
            default:
                bufferedImage2 = bufferedImage;
                break;
        }
        if (abortRequested()) {
            processReadAborted();
        } else {
            processImageComplete();
        }
        return bufferedImage2;
    }

    public Raster readRaster(int i, ImageReadParam imageReadParam) throws IOException {
        return read(i, imageReadParam).getData();
    }

    public void reset() {
        super.reset();
        this.iis = null;
        this.gotHeader = false;
        System.gc();
    }

    private void readComments(ImageInputStream imageInputStream, PNMMetadata pNMMetadata) throws IOException {
        int indexOf;
        imageInputStream.mark();
        while (true) {
            String readLine = imageInputStream.readLine();
            if (readLine == null || (indexOf = readLine.indexOf("#")) < 0) {
                break;
            } else {
                pNMMetadata.addComment(readLine.substring(indexOf + 1).trim());
            }
        }
        imageInputStream.reset();
    }

    private int readInteger(ImageInputStream imageInputStream) throws IOException {
        while (this.aLine == null) {
            String readLine = imageInputStream.readLine();
            this.aLine = readLine;
            if (readLine == null) {
                return 0;
            }
            int indexOf = readLine.indexOf("#");
            if (indexOf == 0) {
                this.aLine = null;
            } else if (indexOf > 0) {
                this.aLine = this.aLine.substring(0, indexOf - 1);
            }
            if (this.aLine != null) {
                this.token = new StringTokenizer(this.aLine);
            }
        }
        while (this.token.hasMoreTokens()) {
            try {
                return new Integer(this.token.nextToken()).intValue();
            } catch (NumberFormatException unused) {
            }
        }
        this.aLine = null;
        return readInteger(imageInputStream);
    }

    private void skipInteger(ImageInputStream imageInputStream, int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            readInteger(imageInputStream);
        }
    }
}
