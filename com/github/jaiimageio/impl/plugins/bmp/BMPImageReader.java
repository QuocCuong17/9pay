package com.github.jaiimageio.impl.plugins.bmp;

import com.github.jaiimageio.impl.common.ImageUtil;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
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
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.event.IIOReadProgressListener;
import javax.imageio.event.IIOReadUpdateListener;
import javax.imageio.event.IIOReadWarningListener;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;

/* loaded from: classes3.dex */
public class BMPImageReader extends ImageReader implements BMPConstants {
    private static final int VERSION_2_1_BIT = 0;
    private static final int VERSION_2_24_BIT = 3;
    private static final int VERSION_2_4_BIT = 1;
    private static final int VERSION_2_8_BIT = 2;
    private static final int VERSION_3_1_BIT = 4;
    private static final int VERSION_3_24_BIT = 7;
    private static final int VERSION_3_4_BIT = 5;
    private static final int VERSION_3_8_BIT = 6;
    private static final int VERSION_3_NT_16_BIT = 8;
    private static final int VERSION_3_NT_32_BIT = 9;
    private static final int VERSION_3_XP_EMBEDDED = 16;
    private static final int VERSION_4_16_BIT = 13;
    private static final int VERSION_4_1_BIT = 10;
    private static final int VERSION_4_24_BIT = 14;
    private static final int VERSION_4_32_BIT = 15;
    private static final int VERSION_4_4_BIT = 11;
    private static final int VERSION_4_8_BIT = 12;
    private static final int VERSION_4_XP_EMBEDDED = 17;
    private static final int VERSION_5_XP_EMBEDDED = 18;
    private int alphaMask;
    private BufferedImage bi;
    private long bitmapFileSize;
    private long bitmapOffset;
    private int bitsPerPixel;
    private int blueMask;
    private ColorModel colorModel;
    private long compression;
    private int[] destBands;
    private Rectangle destinationRegion;
    private boolean gotHeader;
    private int greenMask;
    private int height;
    private ImageInputStream iis;
    private long imageDataOffset;
    private long imageSize;
    private int imageType;
    private boolean isBottomUp;
    private BMPMetadata metadata;
    private boolean noTransform;
    private int numBands;
    private ColorModel originalColorModel;
    private SampleModel originalSampleModel;
    private byte[] palette;
    private int redMask;
    private SampleModel sampleModel;
    private int scaleX;
    private int scaleY;
    private boolean seleBand;
    private int[] sourceBands;
    private Rectangle sourceRegion;
    private int width;

    public boolean canReadRaster() {
        return true;
    }

    public IIOMetadata getStreamMetadata() throws IOException {
        return null;
    }

    public BMPImageReader(ImageReaderSpi imageReaderSpi) {
        super(imageReaderSpi);
        this.iis = null;
        this.gotHeader = false;
        this.noTransform = true;
        this.seleBand = false;
    }

    public void setInput(Object obj, boolean z, boolean z2) {
        super.setInput(obj, z, z2);
        ImageInputStream imageInputStream = (ImageInputStream) obj;
        this.iis = imageInputStream;
        if (imageInputStream != null) {
            imageInputStream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
        }
        resetHeaderInfo();
    }

    public int getNumImages(boolean z) throws IOException {
        if (this.iis == null) {
            throw new IllegalStateException(I18N.getString("GetNumImages0"));
        }
        if (this.seekForwardOnly && z) {
            throw new IllegalStateException(I18N.getString("GetNumImages1"));
        }
        return 1;
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

    private void checkIndex(int i) {
        if (i != 0) {
            throw new IndexOutOfBoundsException(I18N.getString("BMPImageReader0"));
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(20:12|(1:14)(1:217)|15|(3:17|(1:19)(1:(1:119)(1:(1:121)(1:(1:123))))|20)(2:124|(2:126|(3:149|(1:151)(2:153|(1:155)(2:156|(1:158)(2:159|(1:161)(2:162|(1:164)(2:165|(1:167))))))|152)(1:(2:133|(1:138)(2:136|137))(5:139|(1:141)(2:146|(1:148))|142|(1:144)|145)))(2:168|(18:(1:175)(2:214|(1:216))|176|(1:178)(1:213)|179|(1:181)|182|(1:(1:210)(1:(1:212)))(3:186|(1:188)(1:(1:192)(2:193|(1:195)(2:196|(2:198|(1:200))(2:201|(1:203)(2:204|(2:206|(1:208)))))))|189)|190|22|(1:24)(1:117)|25|(5:109|110|111|(1:113)(1:115)|114)|29|30|(2:32|(10:36|(2:38|(1:71)(4:42|(1:44)(2:47|(7:49|(1:51)(1:64)|52|(1:63)|58|(1:60)(1:62)|61)(3:65|(2:66|(1:68)(1:69))|70))|45|46))|72|(3:74|(2:75|(1:77)(1:78))|79)(1:107)|80|(6:99|(1:101)|102|(1:104)|105|106)(6:86|(1:88)|89|(2:92|90)|93|94)|95|(1:97)(1:98)|45|46))|108|45|46)(2:172|173)))|21|22|(0)(0)|25|(1:27)|109|110|111|(0)(0)|114|29|30|(0)|108|45|46) */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x047b, code lost:
    
        r5 = java.awt.color.ColorSpace.getInstance(1000);
     */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0461 A[Catch: Exception -> 0x047b, TryCatch #0 {Exception -> 0x047b, blocks: (B:111:0x045a, B:113:0x0461, B:115:0x0470), top: B:110:0x045a }] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0470 A[Catch: Exception -> 0x047b, TRY_LEAVE, TryCatch #0 {Exception -> 0x047b, blocks: (B:111:0x045a, B:113:0x0461, B:115:0x0470), top: B:110:0x045a }] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0420  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x041c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0485  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void readHeader() throws IOException {
        long j;
        long j2;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        ColorSpace iCC_ColorSpace;
        int i7;
        int i8;
        long j3;
        byte[] bArr;
        byte[] bArr2;
        byte[] bArr3;
        int[] iArr;
        int i9;
        if (this.gotHeader) {
            this.iis.seek(this.imageDataOffset);
            return;
        }
        if (this.iis == null) {
            throw new IllegalStateException(I18N.getString("BMPImageReader5"));
        }
        this.metadata = new BMPMetadata();
        this.iis.mark();
        byte[] bArr4 = new byte[2];
        this.iis.read(bArr4);
        if (bArr4[0] != 66 || bArr4[1] != 77) {
            throw new IllegalArgumentException(I18N.getString("BMPImageReader1"));
        }
        this.bitmapFileSize = this.iis.readUnsignedInt();
        this.iis.skipBytes(4);
        this.bitmapOffset = this.iis.readUnsignedInt();
        long readUnsignedInt = this.iis.readUnsignedInt();
        if (readUnsignedInt == 12) {
            this.width = this.iis.readShort();
            this.height = this.iis.readShort();
        } else {
            this.width = this.iis.readInt();
            this.height = this.iis.readInt();
        }
        this.metadata.width = this.width;
        this.metadata.height = this.height;
        this.iis.readUnsignedShort();
        int readUnsignedShort = this.iis.readUnsignedShort();
        this.bitsPerPixel = readUnsignedShort;
        this.metadata.bitsPerPixel = (short) readUnsignedShort;
        this.numBands = 3;
        if (readUnsignedInt == 12) {
            this.metadata.bmpVersion = BMPConstants.VERSION_2;
            int i10 = this.bitsPerPixel;
            if (i10 == 1) {
                this.imageType = 0;
            } else if (i10 == 4) {
                this.imageType = 1;
            } else if (i10 == 8) {
                this.imageType = 2;
            } else if (i10 == 24) {
                this.imageType = 3;
            }
            int i11 = (int) (((this.bitmapOffset - 14) - readUnsignedInt) / 3);
            int i12 = i11 * 3;
            byte[] bArr5 = new byte[i12];
            this.palette = bArr5;
            this.iis.readFully(bArr5, 0, i12);
            this.metadata.palette = this.palette;
            this.metadata.paletteSize = i11;
            j = readUnsignedInt;
        } else {
            this.compression = this.iis.readUnsignedInt();
            this.imageSize = this.iis.readUnsignedInt();
            long readInt = this.iis.readInt();
            long readInt2 = this.iis.readInt();
            long readUnsignedInt2 = this.iis.readUnsignedInt();
            long readUnsignedInt3 = this.iis.readUnsignedInt();
            this.metadata.compression = (int) this.compression;
            j = readUnsignedInt;
            this.metadata.imageSize = (int) this.imageSize;
            this.metadata.xPixelsPerMeter = (int) readInt;
            this.metadata.yPixelsPerMeter = (int) readInt2;
            int i13 = (int) readUnsignedInt2;
            this.metadata.colorsUsed = i13;
            this.metadata.colorsImportant = (int) readUnsignedInt3;
            if (j != 40) {
                if (j == 108 || j == 124) {
                    if (j == 108) {
                        this.metadata.bmpVersion = BMPConstants.VERSION_4;
                    } else if (j == 124) {
                        this.metadata.bmpVersion = BMPConstants.VERSION_5;
                    }
                    this.redMask = (int) this.iis.readUnsignedInt();
                    this.greenMask = (int) this.iis.readUnsignedInt();
                    this.blueMask = (int) this.iis.readUnsignedInt();
                    this.alphaMask = (int) this.iis.readUnsignedInt();
                    long readUnsignedInt4 = this.iis.readUnsignedInt();
                    int readInt3 = this.iis.readInt();
                    int readInt4 = this.iis.readInt();
                    int readInt5 = this.iis.readInt();
                    int readInt6 = this.iis.readInt();
                    int readInt7 = this.iis.readInt();
                    int readInt8 = this.iis.readInt();
                    int readInt9 = this.iis.readInt();
                    int readInt10 = this.iis.readInt();
                    int readInt11 = this.iis.readInt();
                    long readUnsignedInt5 = this.iis.readUnsignedInt();
                    long readUnsignedInt6 = this.iis.readUnsignedInt();
                    long readUnsignedInt7 = this.iis.readUnsignedInt();
                    if (j == 124) {
                        j2 = readUnsignedInt7;
                        this.metadata.intent = this.iis.readInt();
                        int readInt12 = this.iis.readInt();
                        i2 = this.iis.readInt();
                        i3 = readInt12;
                        this.iis.skipBytes(4);
                        i = readInt8;
                    } else {
                        j2 = readUnsignedInt7;
                        i = readInt8;
                        i2 = 0;
                        i3 = 0;
                    }
                    int i14 = i2;
                    this.metadata.colorSpace = (int) readUnsignedInt4;
                    if (readUnsignedInt4 == 0) {
                        this.metadata.redX = readInt3;
                        this.metadata.redY = readInt4;
                        this.metadata.redZ = readInt5;
                        this.metadata.greenX = readInt6;
                        this.metadata.greenY = readInt7;
                        this.metadata.greenZ = i;
                        this.metadata.blueX = readInt9;
                        this.metadata.blueY = readInt10;
                        this.metadata.blueZ = readInt11;
                        this.metadata.gammaRed = (int) readUnsignedInt5;
                        this.metadata.gammaGreen = (int) readUnsignedInt6;
                        this.metadata.gammaBlue = (int) j2;
                    }
                    int i15 = (int) (((this.bitmapOffset - 14) - j) / 4);
                    int i16 = i15 * 4;
                    byte[] bArr6 = new byte[i16];
                    this.palette = bArr6;
                    this.iis.readFully(bArr6, 0, i16);
                    this.metadata.palette = this.palette;
                    this.metadata.paletteSize = i15;
                    long j4 = this.compression;
                    int i17 = (int) j4;
                    if (i17 != 4 && i17 != 5) {
                        int i18 = this.bitsPerPixel;
                        if (i18 == 1) {
                            this.imageType = 10;
                        } else if (i18 == 4) {
                            this.imageType = 11;
                        } else if (i18 == 8) {
                            this.imageType = 12;
                        } else if (i18 == 16) {
                            this.imageType = 13;
                            if (((int) j4) == 0) {
                                this.redMask = 31744;
                                this.greenMask = 992;
                                this.blueMask = 31;
                            }
                        } else if (i18 == 24) {
                            this.imageType = 14;
                        } else if (i18 == 32) {
                            this.imageType = 15;
                            if (((int) j4) == 0) {
                                this.redMask = 16711680;
                                this.greenMask = 65280;
                                this.blueMask = 255;
                            }
                        }
                        this.metadata.redMask = this.redMask;
                        this.metadata.greenMask = this.greenMask;
                        this.metadata.blueMask = this.blueMask;
                        this.metadata.alphaMask = this.alphaMask;
                    } else if (j == 108) {
                        this.imageType = 17;
                    } else if (j == 124) {
                        this.imageType = 18;
                    }
                    i4 = i3;
                    i5 = i14;
                    i6 = this.height;
                    if (i6 <= 0) {
                        this.isBottomUp = true;
                    } else {
                        this.isBottomUp = false;
                        this.height = Math.abs(i6);
                    }
                    ColorSpace colorSpace = ColorSpace.getInstance(1000);
                    if (this.metadata.colorSpace != 3 || this.metadata.colorSpace == 4) {
                        this.iis.mark();
                        this.iis.skipBytes(i4 - j);
                        byte[] bArr7 = new byte[i5];
                        this.iis.readFully(bArr7, 0, i5);
                        this.iis.reset();
                        if (this.metadata.colorSpace != 3) {
                            iCC_ColorSpace = new ICC_ColorSpace(ICC_Profile.getInstance(new String(bArr7)));
                        } else {
                            iCC_ColorSpace = new ICC_ColorSpace(ICC_Profile.getInstance(bArr7));
                        }
                        colorSpace = iCC_ColorSpace;
                    }
                    ColorSpace colorSpace2 = colorSpace;
                    i7 = this.bitsPerPixel;
                    if (i7 != 0) {
                        long j5 = this.compression;
                        if (j5 != 4 && j5 != 5) {
                            int i19 = 1;
                            if (i7 != 1) {
                                if (i7 != 4 && i7 != 8) {
                                    if (i7 == 16) {
                                        this.numBands = 3;
                                        this.sampleModel = new SinglePixelPackedSampleModel(1, this.width, this.height, new int[]{this.redMask, this.greenMask, this.blueMask});
                                        this.colorModel = new DirectColorModel(colorSpace2, 16, this.redMask, this.greenMask, this.blueMask, 0, false, 1);
                                    } else if (i7 == 32) {
                                        int i20 = this.alphaMask == 0 ? 3 : 4;
                                        this.numBands = i20;
                                        if (this.redMask == 0 || this.greenMask == 0 || this.blueMask == 0) {
                                            this.redMask = 16711680;
                                            this.greenMask = 65280;
                                            this.blueMask = 255;
                                            this.alphaMask = -16777216;
                                        }
                                        if (i20 == 3) {
                                            iArr = new int[]{this.redMask, this.greenMask, this.blueMask};
                                            i9 = 3;
                                        } else {
                                            i9 = 3;
                                            iArr = new int[]{this.redMask, this.greenMask, this.blueMask, this.alphaMask};
                                        }
                                        this.sampleModel = new SinglePixelPackedSampleModel(i9, this.width, this.height, iArr);
                                        this.colorModel = new DirectColorModel(colorSpace2, 32, this.redMask, this.greenMask, this.blueMask, this.alphaMask, false, 3);
                                    } else {
                                        this.numBands = 3;
                                        int[] iArr2 = new int[3];
                                        int i21 = 0;
                                        while (true) {
                                            int i22 = this.numBands;
                                            if (i21 >= i22) {
                                                break;
                                            }
                                            iArr2[i21] = (i22 - 1) - i21;
                                            i21++;
                                        }
                                        int i23 = this.width;
                                        int i24 = this.height;
                                        int i25 = this.numBands;
                                        PixelInterleavedSampleModel pixelInterleavedSampleModel = new PixelInterleavedSampleModel(0, i23, i24, i25, i25 * i23, iArr2);
                                        this.sampleModel = pixelInterleavedSampleModel;
                                        this.colorModel = ImageUtil.createColorModel(colorSpace2, pixelInterleavedSampleModel);
                                    }
                                    this.originalSampleModel = this.sampleModel;
                                    this.originalColorModel = this.colorModel;
                                    this.iis.reset();
                                    this.iis.skipBytes(this.bitmapOffset);
                                    this.gotHeader = true;
                                    this.imageDataOffset = this.iis.getStreamPosition();
                                }
                                i19 = 1;
                            }
                            this.numBands = i19;
                            if (i7 == 8) {
                                int[] iArr3 = new int[i19];
                                int i26 = 0;
                                while (true) {
                                    int i27 = this.numBands;
                                    if (i26 >= i27) {
                                        break;
                                    }
                                    iArr3[i26] = (i27 - 1) - i26;
                                    i26++;
                                }
                                int i28 = this.width;
                                int i29 = this.height;
                                int i30 = this.numBands;
                                this.sampleModel = new PixelInterleavedSampleModel(0, i28, i29, i30, i30 * i28, iArr3);
                                i8 = 0;
                            } else {
                                i8 = 0;
                                this.sampleModel = new MultiPixelPackedSampleModel(0, this.width, this.height, this.bitsPerPixel);
                            }
                            int i31 = this.imageType;
                            if (i31 == 0 || i31 == 1 || i31 == 2) {
                                long length = this.palette.length / 3;
                                j3 = length <= 256 ? length : 256L;
                                int i32 = (int) j3;
                                byte[] bArr8 = new byte[i32];
                                bArr = new byte[i32];
                                byte[] bArr9 = new byte[i32];
                                while (i8 < i32) {
                                    int i33 = i8 * 3;
                                    byte[] bArr10 = this.palette;
                                    bArr9[i8] = bArr10[i33];
                                    bArr[i8] = bArr10[i33 + 1];
                                    bArr8[i8] = bArr10[i33 + 2];
                                    i8++;
                                }
                                bArr2 = bArr8;
                                bArr3 = bArr9;
                            } else {
                                long length2 = this.palette.length / 4;
                                j3 = length2 <= 256 ? length2 : 256L;
                                int i34 = (int) j3;
                                byte[] bArr11 = new byte[i34];
                                bArr = new byte[i34];
                                bArr3 = new byte[i34];
                                while (i8 < j3) {
                                    int i35 = i8 * 4;
                                    byte[] bArr12 = this.palette;
                                    bArr3[i8] = bArr12[i35];
                                    bArr[i8] = bArr12[i35 + 1];
                                    bArr11[i8] = bArr12[i35 + 2];
                                    i8++;
                                }
                                bArr2 = bArr11;
                            }
                            if (ImageUtil.isIndicesForGrayscale(bArr2, bArr, bArr3)) {
                                this.colorModel = ImageUtil.createColorModel(null, this.sampleModel);
                            } else {
                                this.colorModel = new IndexColorModel(this.bitsPerPixel, (int) j3, bArr2, bArr, bArr3);
                            }
                            this.originalSampleModel = this.sampleModel;
                            this.originalColorModel = this.colorModel;
                            this.iis.reset();
                            this.iis.skipBytes(this.bitmapOffset);
                            this.gotHeader = true;
                            this.imageDataOffset = this.iis.getStreamPosition();
                        }
                    }
                    this.colorModel = null;
                    this.sampleModel = null;
                    this.originalSampleModel = this.sampleModel;
                    this.originalColorModel = this.colorModel;
                    this.iis.reset();
                    this.iis.skipBytes(this.bitmapOffset);
                    this.gotHeader = true;
                    this.imageDataOffset = this.iis.getStreamPosition();
                }
                throw new RuntimeException(I18N.getString("BMPImageReader3"));
            }
            int i36 = (int) this.compression;
            if (i36 == 0 || i36 == 1 || i36 == 2) {
                int i37 = (int) (((this.bitmapOffset - 14) - j) / 4);
                int i38 = i37 * 4;
                byte[] bArr13 = new byte[i38];
                this.palette = bArr13;
                this.iis.readFully(bArr13, 0, i38);
                this.metadata.palette = this.palette;
                this.metadata.paletteSize = i37;
                int i39 = this.bitsPerPixel;
                if (i39 == 1) {
                    this.imageType = 4;
                } else if (i39 == 4) {
                    this.imageType = 5;
                } else if (i39 == 8) {
                    this.imageType = 6;
                } else if (i39 == 24) {
                    this.imageType = 7;
                } else if (i39 == 16) {
                    this.imageType = 8;
                    this.redMask = 31744;
                    this.greenMask = 992;
                    this.blueMask = 31;
                    this.metadata.redMask = 31744;
                    this.metadata.greenMask = this.greenMask;
                    this.metadata.blueMask = this.blueMask;
                } else if (i39 == 32) {
                    this.imageType = 9;
                    this.redMask = 16711680;
                    this.greenMask = 65280;
                    this.blueMask = 255;
                    this.metadata.redMask = 16711680;
                    this.metadata.greenMask = this.greenMask;
                    this.metadata.blueMask = this.blueMask;
                }
                this.metadata.bmpVersion = BMPConstants.VERSION_3;
            } else if (i36 == 3) {
                int i40 = this.bitsPerPixel;
                if (i40 == 16) {
                    this.imageType = 8;
                } else if (i40 == 32) {
                    this.imageType = 9;
                }
                this.redMask = (int) this.iis.readUnsignedInt();
                this.greenMask = (int) this.iis.readUnsignedInt();
                this.blueMask = (int) this.iis.readUnsignedInt();
                this.metadata.redMask = this.redMask;
                this.metadata.greenMask = this.greenMask;
                this.metadata.blueMask = this.blueMask;
                if (readUnsignedInt2 != 0) {
                    int i41 = i13 * 4;
                    byte[] bArr14 = new byte[i41];
                    this.palette = bArr14;
                    this.iis.readFully(bArr14, 0, i41);
                    this.metadata.palette = this.palette;
                    this.metadata.paletteSize = i13;
                }
                this.metadata.bmpVersion = BMPConstants.VERSION_3_NT;
            } else if (i36 == 4 || i36 == 5) {
                this.metadata.bmpVersion = BMPConstants.VERSION_3;
                this.imageType = 16;
            } else {
                throw new RuntimeException(I18N.getString("BMPImageReader2"));
            }
        }
        i4 = 0;
        i5 = 0;
        i6 = this.height;
        if (i6 <= 0) {
        }
        ColorSpace colorSpace3 = ColorSpace.getInstance(1000);
        if (this.metadata.colorSpace != 3) {
        }
        this.iis.mark();
        this.iis.skipBytes(i4 - j);
        byte[] bArr72 = new byte[i5];
        this.iis.readFully(bArr72, 0, i5);
        this.iis.reset();
        if (this.metadata.colorSpace != 3) {
        }
        colorSpace3 = iCC_ColorSpace;
        ColorSpace colorSpace22 = colorSpace3;
        i7 = this.bitsPerPixel;
        if (i7 != 0) {
        }
        this.colorModel = null;
        this.sampleModel = null;
        this.originalSampleModel = this.sampleModel;
        this.originalColorModel = this.colorModel;
        this.iis.reset();
        this.iis.skipBytes(this.bitmapOffset);
        this.gotHeader = true;
        this.imageDataOffset = this.iis.getStreamPosition();
    }

    public Iterator getImageTypes(int i) throws IOException {
        checkIndex(i);
        readHeader();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new ImageTypeSpecifier(this.originalColorModel, this.originalSampleModel));
        return arrayList.iterator();
    }

    public ImageReadParam getDefaultReadParam() {
        return new ImageReadParam();
    }

    public IIOMetadata getImageMetadata(int i) throws IOException {
        checkIndex(i);
        if (this.metadata == null) {
            readHeader();
        }
        return this.metadata;
    }

    public boolean isRandomAccessEasy(int i) throws IOException {
        checkIndex(i);
        readHeader();
        return this.metadata.compression == 0;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:38:0x0150. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0202  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public BufferedImage read(int i, ImageReadParam imageReadParam) throws IOException {
        WritableRaster writableTile;
        short[] sArr;
        int[] iArr;
        int i2;
        if (this.iis == null) {
            throw new IllegalStateException(I18N.getString("BMPImageReader5"));
        }
        checkIndex(i);
        clearAbortRequest();
        processImageStarted(i);
        if (imageReadParam == null) {
            imageReadParam = getDefaultReadParam();
        }
        readHeader();
        this.sourceRegion = new Rectangle(0, 0, 0, 0);
        this.destinationRegion = new Rectangle(0, 0, 0, 0);
        computeRegions(imageReadParam, this.width, this.height, imageReadParam.getDestination(), this.sourceRegion, this.destinationRegion);
        this.scaleX = imageReadParam.getSourceXSubsampling();
        this.scaleY = imageReadParam.getSourceYSubsampling();
        this.sourceBands = imageReadParam.getSourceBands();
        int[] destinationBands = imageReadParam.getDestinationBands();
        this.destBands = destinationBands;
        this.seleBand = (this.sourceBands == null || destinationBands == null) ? false : true;
        this.noTransform = this.destinationRegion.equals(new Rectangle(0, 0, this.width, this.height)) || this.seleBand;
        if (!this.seleBand) {
            int i3 = this.numBands;
            this.sourceBands = new int[i3];
            this.destBands = new int[i3];
            for (int i4 = 0; i4 < this.numBands; i4++) {
                int[] iArr2 = this.destBands;
                this.sourceBands[i4] = i4;
                iArr2[i4] = i4;
            }
        }
        BufferedImage destination = imageReadParam.getDestination();
        this.bi = destination;
        byte[] bArr = null;
        if (destination == null) {
            SampleModel sampleModel = this.sampleModel;
            if (sampleModel == null || this.colorModel == null) {
                writableTile = null;
            } else {
                SampleModel createCompatibleSampleModel = sampleModel.createCompatibleSampleModel(this.destinationRegion.x + this.destinationRegion.width, this.destinationRegion.y + this.destinationRegion.height);
                this.sampleModel = createCompatibleSampleModel;
                if (this.seleBand) {
                    this.sampleModel = createCompatibleSampleModel.createSubsetSampleModel(this.sourceBands);
                }
                writableTile = Raster.createWritableRaster(this.sampleModel, new Point());
                this.bi = new BufferedImage(this.colorModel, writableTile, false, (Hashtable) null);
            }
        } else {
            writableTile = destination.getWritableTile(0, 0);
            this.sampleModel = this.bi.getSampleModel();
            this.colorModel = this.bi.getColorModel();
            this.noTransform &= this.destinationRegion.equals(writableTile.getBounds());
        }
        SampleModel sampleModel2 = this.sampleModel;
        if (sampleModel2 != null) {
            if (sampleModel2.getDataType() == 0) {
                iArr = null;
                bArr = writableTile.getDataBuffer().getData();
                sArr = null;
            } else if (this.sampleModel.getDataType() == 1) {
                sArr = writableTile.getDataBuffer().getData();
                iArr = null;
            } else if (this.sampleModel.getDataType() == 3) {
                iArr = writableTile.getDataBuffer().getData();
                sArr = null;
            }
            switch (this.imageType) {
                case 0:
                    read1Bit(bArr);
                    if (!abortRequested()) {
                        processReadAborted();
                    } else {
                        processImageComplete();
                    }
                    return this.bi;
                case 1:
                    read4Bit(bArr);
                    if (!abortRequested()) {
                    }
                    return this.bi;
                case 2:
                    read8Bit(bArr);
                    if (!abortRequested()) {
                    }
                    return this.bi;
                case 3:
                    read24Bit(bArr);
                    if (!abortRequested()) {
                    }
                    return this.bi;
                case 4:
                    read1Bit(bArr);
                    if (!abortRequested()) {
                    }
                    return this.bi;
                case 5:
                    int i5 = (int) this.compression;
                    if (i5 == 0) {
                        read4Bit(bArr);
                    } else if (i5 == 2) {
                        readRLE4(bArr);
                    } else {
                        throw new RuntimeException(I18N.getString("BMPImageReader1"));
                    }
                    if (!abortRequested()) {
                    }
                    return this.bi;
                case 6:
                    int i6 = (int) this.compression;
                    if (i6 == 0) {
                        read8Bit(bArr);
                    } else if (i6 == 1) {
                        readRLE8(bArr);
                    } else {
                        throw new RuntimeException(I18N.getString("BMPImageReader1"));
                    }
                    if (!abortRequested()) {
                    }
                    return this.bi;
                case 7:
                    read24Bit(bArr);
                    if (!abortRequested()) {
                    }
                    return this.bi;
                case 8:
                    read16Bit(sArr);
                    if (!abortRequested()) {
                    }
                    return this.bi;
                case 9:
                    read32Bit(iArr);
                    if (!abortRequested()) {
                    }
                    return this.bi;
                case 10:
                    read1Bit(bArr);
                    if (!abortRequested()) {
                    }
                    return this.bi;
                case 11:
                    int i7 = (int) this.compression;
                    if (i7 == 0) {
                        read4Bit(bArr);
                    } else if (i7 == 2) {
                        readRLE4(bArr);
                    } else {
                        throw new RuntimeException(I18N.getString("BMPImageReader1"));
                    }
                    i2 = (int) this.compression;
                    if (i2 != 0) {
                        read8Bit(bArr);
                    } else if (i2 == 1) {
                        readRLE8(bArr);
                    } else {
                        throw new RuntimeException(I18N.getString("BMPImageReader1"));
                    }
                    if (!abortRequested()) {
                    }
                    return this.bi;
                case 12:
                    i2 = (int) this.compression;
                    if (i2 != 0) {
                    }
                    if (!abortRequested()) {
                    }
                    return this.bi;
                case 13:
                    read16Bit(sArr);
                    if (!abortRequested()) {
                    }
                    return this.bi;
                case 14:
                    read24Bit(bArr);
                    if (!abortRequested()) {
                    }
                    return this.bi;
                case 15:
                    read32Bit(iArr);
                    if (!abortRequested()) {
                    }
                    return this.bi;
                case 16:
                case 17:
                case 18:
                    this.bi = readEmbedded((int) this.compression, this.bi, imageReadParam);
                    if (!abortRequested()) {
                    }
                    return this.bi;
                default:
                    if (!abortRequested()) {
                    }
                    return this.bi;
            }
        }
        sArr = null;
        iArr = null;
        switch (this.imageType) {
        }
    }

    public Raster readRaster(int i, ImageReadParam imageReadParam) throws IOException {
        return read(i, imageReadParam).getData();
    }

    private void resetHeaderInfo() {
        this.gotHeader = false;
        this.bi = null;
        this.originalSampleModel = null;
        this.sampleModel = null;
        this.originalColorModel = null;
        this.colorModel = null;
    }

    public void reset() {
        super.reset();
        this.iis = null;
        resetHeaderInfo();
    }

    private void read1Bit(byte[] bArr) throws IOException {
        int i = (this.width + 7) / 8;
        int i2 = i % 4;
        if (i2 != 0) {
            i2 = 4 - i2;
        }
        int i3 = i2;
        int i4 = i + i3;
        int i5 = 0;
        int i6 = 1;
        if (this.noTransform) {
            int i7 = this.isBottomUp ? (this.height - 1) * i : 0;
            int i8 = 0;
            while (i8 < this.height && !abortRequested()) {
                this.iis.readFully(bArr, i7, i);
                this.iis.skipBytes(i3);
                int i9 = i7 + (this.isBottomUp ? -i : i);
                BufferedImage bufferedImage = this.bi;
                int i10 = this.destinationRegion.width;
                int[] iArr = new int[i6];
                iArr[i5] = i5;
                processImageUpdate(bufferedImage, 0, i8, i10, 1, 1, 1, iArr);
                processImageProgress((i8 * 100.0f) / this.destinationRegion.height);
                i8++;
                i6 = i6;
                i7 = i9;
                i5 = 0;
            }
            return;
        }
        byte[] bArr2 = new byte[i4];
        int scanlineStride = this.sampleModel.getScanlineStride();
        if (this.isBottomUp) {
            this.iis.skipBytes(((this.height - 1) - (this.sourceRegion.y + ((this.destinationRegion.height - 1) * this.scaleY))) * i4);
        } else {
            this.iis.skipBytes(this.sourceRegion.y * i4);
        }
        int i11 = i4 * (this.scaleY - 1);
        int[] iArr2 = new int[this.destinationRegion.width];
        int[] iArr3 = new int[this.destinationRegion.width];
        int[] iArr4 = new int[this.destinationRegion.width];
        int[] iArr5 = new int[this.destinationRegion.width];
        int i12 = this.destinationRegion.x;
        int i13 = this.sourceRegion.x;
        int i14 = 0;
        while (i12 < this.destinationRegion.x + this.destinationRegion.width) {
            iArr4[i14] = i13 >> 3;
            iArr2[i14] = 7 - (i13 & 7);
            iArr5[i14] = i12 >> 3;
            iArr3[i14] = 7 - (i12 & 7);
            i12++;
            i14++;
            i13 += this.scaleX;
        }
        int i15 = this.destinationRegion.y * scanlineStride;
        if (this.isBottomUp) {
            i15 += (this.destinationRegion.height - 1) * scanlineStride;
        }
        int i16 = this.sourceRegion.y;
        int i17 = 0;
        while (i17 < this.destinationRegion.height && !abortRequested()) {
            this.iis.read(bArr2, 0, i4);
            for (int i18 = 0; i18 < this.destinationRegion.width; i18++) {
                int i19 = (bArr2[iArr4[i18]] >> iArr2[i18]) & 1;
                int i20 = iArr5[i18] + i15;
                bArr[i20] = (byte) (bArr[i20] | (i19 << iArr3[i18]));
            }
            int i21 = i15 + (this.isBottomUp ? -scanlineStride : scanlineStride);
            this.iis.skipBytes(i11);
            processImageUpdate(this.bi, 0, i17, this.destinationRegion.width, 1, 1, 1, new int[]{0});
            processImageProgress((i17 * 100.0f) / this.destinationRegion.height);
            i17++;
            i15 = i21;
            iArr3 = iArr3;
            iArr2 = iArr2;
            i11 = i11;
            iArr4 = iArr4;
            iArr5 = iArr5;
        }
    }

    private void read4Bit(byte[] bArr) throws IOException {
        int i = (this.width + 1) / 2;
        int i2 = i % 4;
        if (i2 != 0) {
            i2 = 4 - i2;
        }
        int i3 = i2;
        int i4 = i + i3;
        int i5 = 0;
        if (this.noTransform) {
            int i6 = this.isBottomUp ? (this.height - 1) * i : 0;
            int i7 = 0;
            while (i7 < this.height && !abortRequested()) {
                this.iis.readFully(bArr, i6, i);
                this.iis.skipBytes(i3);
                int i8 = i6 + (this.isBottomUp ? -i : i);
                BufferedImage bufferedImage = this.bi;
                int i9 = this.destinationRegion.width;
                int[] iArr = new int[1];
                iArr[i5] = i5;
                processImageUpdate(bufferedImage, 0, i7, i9, 1, 1, 1, iArr);
                processImageProgress((i7 * 100.0f) / this.destinationRegion.height);
                i7++;
                i6 = i8;
                i5 = 0;
            }
            return;
        }
        byte[] bArr2 = new byte[i4];
        int scanlineStride = this.sampleModel.getScanlineStride();
        if (this.isBottomUp) {
            this.iis.skipBytes(((this.height - 1) - (this.sourceRegion.y + ((this.destinationRegion.height - 1) * this.scaleY))) * i4);
        } else {
            this.iis.skipBytes(this.sourceRegion.y * i4);
        }
        int i10 = i4 * (this.scaleY - 1);
        int[] iArr2 = new int[this.destinationRegion.width];
        int[] iArr3 = new int[this.destinationRegion.width];
        int[] iArr4 = new int[this.destinationRegion.width];
        int[] iArr5 = new int[this.destinationRegion.width];
        int i11 = this.destinationRegion.x;
        int i12 = this.sourceRegion.x;
        int i13 = 0;
        while (i11 < this.destinationRegion.x + this.destinationRegion.width) {
            iArr4[i13] = i12 >> 1;
            iArr2[i13] = (1 - (i12 & 1)) << 2;
            iArr5[i13] = i11 >> 1;
            iArr3[i13] = (1 - (i11 & 1)) << 2;
            i11++;
            i13++;
            i12 += this.scaleX;
        }
        int i14 = this.destinationRegion.y * scanlineStride;
        if (this.isBottomUp) {
            i14 += (this.destinationRegion.height - 1) * scanlineStride;
        }
        int i15 = this.sourceRegion.y;
        int i16 = 0;
        while (i16 < this.destinationRegion.height && !abortRequested()) {
            this.iis.read(bArr2, 0, i4);
            for (int i17 = 0; i17 < this.destinationRegion.width; i17++) {
                int i18 = (bArr2[iArr4[i17]] >> iArr2[i17]) & 15;
                int i19 = i14 + iArr5[i17];
                bArr[i19] = (byte) (bArr[i19] | (i18 << iArr3[i17]));
            }
            int i20 = i14 + (this.isBottomUp ? -scanlineStride : scanlineStride);
            this.iis.skipBytes(i10);
            processImageUpdate(this.bi, 0, i16, this.destinationRegion.width, 1, 1, 1, new int[]{0});
            processImageProgress((i16 * 100.0f) / this.destinationRegion.height);
            i16++;
            i14 = i20;
            iArr3 = iArr3;
            iArr2 = iArr2;
            i10 = i10;
            iArr4 = iArr4;
            iArr5 = iArr5;
        }
    }

    private void read8Bit(byte[] bArr) throws IOException {
        int i = this.width;
        int i2 = i % 4;
        if (i2 != 0) {
            i2 = 4 - i2;
        }
        int i3 = i2;
        int i4 = i + i3;
        int i5 = 0;
        if (this.noTransform) {
            int i6 = this.isBottomUp ? (this.height - 1) * i : 0;
            int i7 = 0;
            while (i7 < this.height && !abortRequested()) {
                this.iis.readFully(bArr, i6, this.width);
                this.iis.skipBytes(i3);
                int i8 = i6 + (this.isBottomUp ? -this.width : this.width);
                processImageUpdate(this.bi, 0, i7, this.destinationRegion.width, 1, 1, 1, new int[]{0});
                processImageProgress((i7 * 100.0f) / this.destinationRegion.height);
                i7++;
                i6 = i8;
            }
            return;
        }
        byte[] bArr2 = new byte[i4];
        int scanlineStride = this.sampleModel.getScanlineStride();
        if (this.isBottomUp) {
            this.iis.skipBytes(((this.height - 1) - (this.sourceRegion.y + ((this.destinationRegion.height - 1) * this.scaleY))) * i4);
        } else {
            this.iis.skipBytes(this.sourceRegion.y * i4);
        }
        int i9 = i4 * (this.scaleY - 1);
        int i10 = this.destinationRegion.y * scanlineStride;
        if (this.isBottomUp) {
            i10 += (this.destinationRegion.height - 1) * scanlineStride;
        }
        int i11 = i10 + this.destinationRegion.x;
        int i12 = this.sourceRegion.y;
        int i13 = 0;
        while (i13 < this.destinationRegion.height && !abortRequested()) {
            this.iis.read(bArr2, i5, i4);
            int i14 = this.sourceRegion.x;
            int i15 = i5;
            while (i15 < this.destinationRegion.width) {
                bArr[i11 + i15] = bArr2[i14];
                i15++;
                i14 += this.scaleX;
            }
            int i16 = i11 + (this.isBottomUp ? -scanlineStride : scanlineStride);
            this.iis.skipBytes(i9);
            BufferedImage bufferedImage = this.bi;
            int i17 = this.destinationRegion.width;
            int[] iArr = new int[1];
            iArr[i5] = i5;
            int i18 = i13;
            processImageUpdate(bufferedImage, 0, i13, i17, 1, 1, 1, iArr);
            processImageProgress((i18 * 100.0f) / this.destinationRegion.height);
            i13 = i18 + 1;
            i11 = i16;
            i9 = i9;
            scanlineStride = scanlineStride;
            i5 = 0;
        }
    }

    private void read24Bit(byte[] bArr) throws IOException {
        int i = this.width;
        int i2 = (i * 3) % 4;
        if (i2 != 0) {
            i2 = 4 - i2;
        }
        int i3 = i2;
        int i4 = i * 3;
        int i5 = i4 + i3;
        int i6 = 0;
        int i7 = 1;
        if (this.noTransform) {
            int i8 = this.isBottomUp ? (this.height - 1) * i * 3 : 0;
            int i9 = 0;
            while (i9 < this.height && !abortRequested()) {
                this.iis.readFully(bArr, i8, i4);
                this.iis.skipBytes(i3);
                int i10 = i8 + (this.isBottomUp ? -i4 : i4);
                BufferedImage bufferedImage = this.bi;
                int i11 = this.destinationRegion.width;
                int[] iArr = new int[i7];
                iArr[i6] = i6;
                processImageUpdate(bufferedImage, 0, i9, i11, 1, 1, 1, iArr);
                processImageProgress((i9 * 100.0f) / this.destinationRegion.height);
                i9++;
                i7 = i7;
                i8 = i10;
                i6 = 0;
            }
            return;
        }
        int i12 = 1;
        byte[] bArr2 = new byte[i5];
        int scanlineStride = this.sampleModel.getScanlineStride();
        if (this.isBottomUp) {
            this.iis.skipBytes(((this.height - 1) - (this.sourceRegion.y + ((this.destinationRegion.height - 1) * this.scaleY))) * i5);
        } else {
            this.iis.skipBytes(this.sourceRegion.y * i5);
        }
        int i13 = i5 * (this.scaleY - 1);
        int i14 = this.destinationRegion.y * scanlineStride;
        if (this.isBottomUp) {
            i14 += (this.destinationRegion.height - 1) * scanlineStride;
        }
        int i15 = i14 + (this.destinationRegion.x * 3);
        int i16 = this.sourceRegion.y;
        int i17 = 0;
        while (i17 < this.destinationRegion.height && !abortRequested()) {
            this.iis.read(bArr2, 0, i5);
            int i18 = this.sourceRegion.x * 3;
            int i19 = 0;
            while (i19 < this.destinationRegion.width) {
                int i20 = (i19 * 3) + i15;
                int i21 = 0;
                while (true) {
                    int[] iArr2 = this.destBands;
                    if (i21 < iArr2.length) {
                        bArr[iArr2[i21] + i20] = bArr2[this.sourceBands[i21] + i18];
                        i21++;
                    }
                }
                i19++;
                i18 += this.scaleX * 3;
            }
            int i22 = i15 + (this.isBottomUp ? -scanlineStride : scanlineStride);
            this.iis.skipBytes(i13);
            BufferedImage bufferedImage2 = this.bi;
            int i23 = this.destinationRegion.width;
            int[] iArr3 = new int[i12];
            iArr3[0] = 0;
            int i24 = i17;
            processImageUpdate(bufferedImage2, 0, i17, i23, 1, 1, 1, iArr3);
            processImageProgress((i24 * 100.0f) / this.destinationRegion.height);
            i17 = i24 + 1;
            i15 = i22;
            i13 = i13;
            i12 = 1;
        }
    }

    private void read16Bit(short[] sArr) throws IOException {
        int i = this.width;
        int i2 = (i * 2) % 4;
        if (i2 != 0) {
            i2 = 4 - i2;
        }
        int i3 = i2;
        int i4 = i + (i3 / 2);
        int i5 = 0;
        if (this.noTransform) {
            int i6 = this.isBottomUp ? (this.height - 1) * i : 0;
            int i7 = 0;
            while (i7 < this.height && !abortRequested()) {
                this.iis.readFully(sArr, i6, this.width);
                this.iis.skipBytes(i3);
                int i8 = i6 + (this.isBottomUp ? -this.width : this.width);
                processImageUpdate(this.bi, 0, i7, this.destinationRegion.width, 1, 1, 1, new int[]{0});
                processImageProgress((i7 * 100.0f) / this.destinationRegion.height);
                i7++;
                i6 = i8;
            }
            return;
        }
        short[] sArr2 = new short[i4];
        int scanlineStride = this.sampleModel.getScanlineStride();
        if (this.isBottomUp) {
            this.iis.skipBytes((((this.height - 1) - (this.sourceRegion.y + ((this.destinationRegion.height - 1) * this.scaleY))) * i4) << 1);
        } else {
            this.iis.skipBytes((this.sourceRegion.y * i4) << 1);
        }
        int i9 = ((this.scaleY - 1) * i4) << 1;
        int i10 = this.destinationRegion.y * scanlineStride;
        if (this.isBottomUp) {
            i10 += (this.destinationRegion.height - 1) * scanlineStride;
        }
        int i11 = i10 + this.destinationRegion.x;
        int i12 = this.sourceRegion.y;
        int i13 = 0;
        while (i13 < this.destinationRegion.height && !abortRequested()) {
            this.iis.readFully(sArr2, i5, i4);
            int i14 = this.sourceRegion.x;
            int i15 = i5;
            while (i15 < this.destinationRegion.width) {
                sArr[i11 + i15] = sArr2[i14];
                i15++;
                i14 += this.scaleX;
            }
            int i16 = i11 + (this.isBottomUp ? -scanlineStride : scanlineStride);
            this.iis.skipBytes(i9);
            BufferedImage bufferedImage = this.bi;
            int i17 = this.destinationRegion.width;
            int[] iArr = new int[1];
            iArr[i5] = i5;
            int i18 = i13;
            processImageUpdate(bufferedImage, 0, i13, i17, 1, 1, 1, iArr);
            processImageProgress((i18 * 100.0f) / this.destinationRegion.height);
            i13 = i18 + 1;
            i11 = i16;
            i9 = i9;
            scanlineStride = scanlineStride;
            i5 = 0;
        }
    }

    private void read32Bit(int[] iArr) throws IOException {
        int i = 0;
        if (this.noTransform) {
            int i2 = this.isBottomUp ? (this.height - 1) * this.width : 0;
            int i3 = 0;
            while (i3 < this.height && !abortRequested()) {
                this.iis.readFully(iArr, i2, this.width);
                int i4 = i2 + (this.isBottomUp ? -this.width : this.width);
                processImageUpdate(this.bi, 0, i3, this.destinationRegion.width, 1, 1, 1, new int[]{0});
                processImageProgress((i3 * 100.0f) / this.destinationRegion.height);
                i3++;
                i2 = i4;
            }
            return;
        }
        int[] iArr2 = new int[this.width];
        int scanlineStride = this.sampleModel.getScanlineStride();
        if (this.isBottomUp) {
            this.iis.skipBytes((this.width * ((this.height - 1) - (this.sourceRegion.y + ((this.destinationRegion.height - 1) * this.scaleY)))) << 2);
        } else {
            this.iis.skipBytes((this.width * this.sourceRegion.y) << 2);
        }
        int i5 = (this.width * (this.scaleY - 1)) << 2;
        int i6 = this.destinationRegion.y * scanlineStride;
        if (this.isBottomUp) {
            i6 += (this.destinationRegion.height - 1) * scanlineStride;
        }
        int i7 = i6 + this.destinationRegion.x;
        int i8 = this.sourceRegion.y;
        int i9 = 0;
        while (i9 < this.destinationRegion.height && !abortRequested()) {
            this.iis.readFully(iArr2, i, this.width);
            int i10 = this.sourceRegion.x;
            int i11 = i;
            while (i11 < this.destinationRegion.width) {
                iArr[i7 + i11] = iArr2[i10];
                i11++;
                i10 += this.scaleX;
            }
            int i12 = i7 + (this.isBottomUp ? -scanlineStride : scanlineStride);
            this.iis.skipBytes(i5);
            BufferedImage bufferedImage = this.bi;
            int i13 = this.destinationRegion.width;
            int[] iArr3 = new int[1];
            iArr3[i] = i;
            int i14 = i9;
            processImageUpdate(bufferedImage, 0, i9, i13, 1, 1, 1, iArr3);
            processImageProgress((i14 * 100.0f) / this.destinationRegion.height);
            i9 = i14 + 1;
            i7 = i12;
            i5 = i5;
            i = 0;
        }
    }

    private void readRLE8(byte[] bArr) throws IOException {
        int i = (int) this.imageSize;
        if (i == 0) {
            i = (int) (this.bitmapFileSize - this.bitmapOffset);
        }
        int i2 = this.width % 4;
        int i3 = i2 != 0 ? 4 - i2 : 0;
        byte[] bArr2 = new byte[i];
        this.iis.readFully(bArr2, 0, i);
        decodeRLE8(i, i3, bArr2, bArr);
    }

    private void decodeRLE8(int i, int i2, byte[] bArr, byte[] bArr2) throws IOException {
        int i3 = this.width;
        int i4 = this.height;
        byte[] bArr3 = new byte[i3 * i4];
        int i5 = this.isBottomUp ? i4 - 1 : 0;
        int scanlineStride = this.sampleModel.getScanlineStride();
        int i6 = i5;
        int i7 = 0;
        int i8 = 0;
        boolean z = false;
        int i9 = 0;
        while (i7 != i) {
            int i10 = i7 + 1;
            int i11 = bArr[i7] & 255;
            if (i11 == 0) {
                int i12 = i10 + 1;
                int i13 = bArr[i10] & 255;
                if (i13 == 0 || i13 == 1) {
                    if (i6 >= this.sourceRegion.y && i6 < this.sourceRegion.y + this.sourceRegion.height) {
                        if (this.noTransform) {
                            int i14 = this.width * i6;
                            int i15 = 0;
                            while (i15 < this.width) {
                                bArr2[i14] = bArr3[i15];
                                i15++;
                                i14++;
                            }
                            processImageUpdate(this.bi, 0, i6, this.destinationRegion.width, 1, 1, 1, new int[]{0});
                        } else if ((i6 - this.sourceRegion.y) % this.scaleY == 0) {
                            int i16 = ((i6 - this.sourceRegion.y) / this.scaleY) + this.destinationRegion.y;
                            int i17 = (i16 * scanlineStride) + this.destinationRegion.x;
                            int i18 = this.sourceRegion.x;
                            while (i18 < this.sourceRegion.x + this.sourceRegion.width) {
                                bArr2[i17] = bArr3[i18];
                                i18 += this.scaleX;
                                i17++;
                            }
                            processImageUpdate(this.bi, 0, i16, this.destinationRegion.width, 1, 1, 1, new int[]{0});
                        }
                        i9++;
                    }
                    int i19 = i9;
                    processImageProgress((i19 * 100.0f) / this.destinationRegion.height);
                    i6 += this.isBottomUp ? -1 : 1;
                    if (!abortRequested() && (bArr[i12 - 1] & 255) == 1) {
                        i9 = i19;
                        i8 = 0;
                        z = true;
                    } else {
                        i9 = i19;
                        i8 = 0;
                    }
                } else if (i13 == 2) {
                    int i20 = i12 + 1;
                    i8 += (bArr[i12] & 255) + ((bArr[i20] & 255) * this.width);
                    i12 = i20;
                } else {
                    int i21 = bArr[i12 - 1] & 255;
                    int i22 = 0;
                    while (i22 < i21) {
                        bArr3[i8] = (byte) (bArr[i12] & 255);
                        i22++;
                        i8++;
                        i12++;
                    }
                    if ((i21 & 1) == 1) {
                        i12++;
                    }
                }
                i7 = i12;
            } else {
                int i23 = 0;
                while (i23 < i11) {
                    bArr3[i8] = (byte) (bArr[i10] & 255);
                    i23++;
                    i8++;
                }
                i7 = i10 + 1;
            }
            if (z) {
                return;
            }
        }
    }

    private void readRLE4(byte[] bArr) throws IOException {
        int i = (int) this.imageSize;
        if (i == 0) {
            i = (int) (this.bitmapFileSize - this.bitmapOffset);
        }
        int i2 = this.width % 4;
        int i3 = i2 != 0 ? 4 - i2 : 0;
        byte[] bArr2 = new byte[i];
        this.iis.readFully(bArr2, 0, i);
        decodeRLE4(i, i3, bArr2, bArr);
    }

    private void decodeRLE4(int i, int i2, byte[] bArr, byte[] bArr2) throws IOException {
        int i3;
        int i4;
        byte[] bArr3 = new byte[this.width];
        int i5 = this.isBottomUp ? this.height - 1 : 0;
        int scanlineStride = this.sampleModel.getScanlineStride();
        int i6 = i5;
        int i7 = 0;
        int i8 = 0;
        boolean z = false;
        int i9 = 0;
        while (i7 != i) {
            int i10 = i7 + 1;
            int i11 = bArr[i7] & 255;
            if (i11 == 0) {
                int i12 = i10 + 1;
                int i13 = bArr[i10] & 255;
                if (i13 == 0 || i13 == 1) {
                    if (i6 >= this.sourceRegion.y && i6 < this.sourceRegion.y + this.sourceRegion.height) {
                        if (this.noTransform) {
                            int i14 = ((this.width + 1) >> 1) * i6;
                            int i15 = 0;
                            int i16 = 0;
                            while (true) {
                                i3 = this.width;
                                if (i15 >= (i3 >> 1)) {
                                    break;
                                }
                                int i17 = i16 + 1;
                                bArr2[i14] = (byte) ((bArr3[i16] << 4) | bArr3[i17]);
                                i15++;
                                i14++;
                                i16 = i17 + 1;
                            }
                            if ((i3 & 1) == 1) {
                                bArr2[i14] = (byte) (bArr2[i14] | (bArr3[i3 - 1] << 4));
                            }
                            processImageUpdate(this.bi, 0, i6, this.destinationRegion.width, 1, 1, 1, new int[]{0});
                        } else if ((i6 - this.sourceRegion.y) % this.scaleY == 0) {
                            int i18 = ((i6 - this.sourceRegion.y) / this.scaleY) + this.destinationRegion.y;
                            int i19 = (i18 * scanlineStride) + (this.destinationRegion.x >> 1);
                            int i20 = (1 - (this.destinationRegion.x & 1)) << 2;
                            int i21 = this.sourceRegion.x;
                            while (i21 < this.sourceRegion.x + this.sourceRegion.width) {
                                bArr2[i19] = (byte) (bArr2[i19] | (bArr3[i21] << i20));
                                int i22 = i20 + 4;
                                if (i22 == 4) {
                                    i19++;
                                }
                                i20 = i22 & 7;
                                i21 += this.scaleX;
                            }
                            processImageUpdate(this.bi, 0, i18, this.destinationRegion.width, 1, 1, 1, new int[]{0});
                        }
                        i9++;
                    }
                    int i23 = i9;
                    processImageProgress((i23 * 100.0f) / this.destinationRegion.height);
                    i6 += this.isBottomUp ? -1 : 1;
                    if (!abortRequested() && (bArr[i12 - 1] & 255) == 1) {
                        i9 = i23;
                        i8 = 0;
                        z = true;
                    } else {
                        i9 = i23;
                        i8 = 0;
                    }
                } else if (i13 == 2) {
                    int i24 = i12 + 1;
                    i8 += (bArr[i12] & 255) + ((bArr[i24] & 255) * this.width);
                    i12 = i24;
                } else {
                    int i25 = bArr[i12 - 1] & 255;
                    int i26 = 0;
                    while (i26 < i25) {
                        int i27 = i8 + 1;
                        if ((i26 & 1) == 0) {
                            i4 = (bArr[i12] & 240) >> 4;
                        } else {
                            int i28 = bArr[i12] & 15;
                            i12++;
                            i4 = i28;
                        }
                        bArr3[i8] = (byte) i4;
                        i26++;
                        i8 = i27;
                    }
                    if ((i25 & 1) == 1) {
                        i12++;
                    }
                    if ((((int) Math.ceil(i25 / 2)) & 1) == 1) {
                        i12++;
                    }
                }
                i7 = i12;
            } else {
                int[] iArr = {(bArr[i10] & 240) >> 4, bArr[i10] & 15};
                int i29 = 0;
                while (i29 < i11 && i8 < this.width) {
                    bArr3[i8] = (byte) iArr[i29 & 1];
                    i29++;
                    i8++;
                }
                i7 = i10 + 1;
            }
            if (z) {
                return;
            }
        }
    }

    private BufferedImage readEmbedded(int i, BufferedImage bufferedImage, ImageReadParam imageReadParam) throws IOException {
        String str;
        if (i == 4) {
            str = "JPEG";
        } else {
            if (i != 5) {
                throw new IOException("Unexpected compression type: " + i);
            }
            str = "PNG";
        }
        ImageReader imageReader = (ImageReader) ImageIO.getImageReadersByFormatName(str).next();
        if (imageReader == null) {
            throw new RuntimeException(I18N.getString("BMPImageReader4") + " " + str);
        }
        byte[] bArr = new byte[(int) this.imageSize];
        this.iis.read(bArr);
        imageReader.setInput(ImageIO.createImageInputStream(new ByteArrayInputStream(bArr)));
        if (bufferedImage == null) {
            bufferedImage = ((ImageTypeSpecifier) imageReader.getImageTypes(0).next()).createBufferedImage(this.destinationRegion.x + this.destinationRegion.width, this.destinationRegion.y + this.destinationRegion.height);
        }
        imageReader.addIIOReadProgressListener(new EmbeddedProgressAdapter() { // from class: com.github.jaiimageio.impl.plugins.bmp.BMPImageReader.1
            @Override // com.github.jaiimageio.impl.plugins.bmp.BMPImageReader.EmbeddedProgressAdapter
            public void imageProgress(ImageReader imageReader2, float f) {
                BMPImageReader.this.processImageProgress(f);
            }
        });
        imageReader.addIIOReadUpdateListener(new IIOReadUpdateListener() { // from class: com.github.jaiimageio.impl.plugins.bmp.BMPImageReader.2
            public void thumbnailPassComplete(ImageReader imageReader2, BufferedImage bufferedImage2) {
            }

            public void thumbnailPassStarted(ImageReader imageReader2, BufferedImage bufferedImage2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int[] iArr) {
            }

            public void thumbnailUpdate(ImageReader imageReader2, BufferedImage bufferedImage2, int i2, int i3, int i4, int i5, int i6, int i7, int[] iArr) {
            }

            public void imageUpdate(ImageReader imageReader2, BufferedImage bufferedImage2, int i2, int i3, int i4, int i5, int i6, int i7, int[] iArr) {
                BMPImageReader.this.processImageUpdate(bufferedImage2, i2, i3, i4, i5, i6, i7, iArr);
            }

            public void passComplete(ImageReader imageReader2, BufferedImage bufferedImage2) {
                BMPImageReader.this.processPassComplete(bufferedImage2);
            }

            public void passStarted(ImageReader imageReader2, BufferedImage bufferedImage2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int[] iArr) {
                BMPImageReader.this.processPassStarted(bufferedImage2, i2, i3, i4, i5, i6, i7, i8, iArr);
            }
        });
        imageReader.addIIOReadWarningListener(new IIOReadWarningListener() { // from class: com.github.jaiimageio.impl.plugins.bmp.BMPImageReader.3
            public void warningOccurred(ImageReader imageReader2, String str2) {
                BMPImageReader.this.processWarningOccurred(str2);
            }
        });
        ImageReadParam defaultReadParam = imageReader.getDefaultReadParam();
        defaultReadParam.setDestination(bufferedImage);
        defaultReadParam.setDestinationBands(imageReadParam.getDestinationBands());
        defaultReadParam.setDestinationOffset(imageReadParam.getDestinationOffset());
        defaultReadParam.setSourceBands(imageReadParam.getSourceBands());
        defaultReadParam.setSourceRegion(imageReadParam.getSourceRegion());
        defaultReadParam.setSourceSubsampling(imageReadParam.getSourceXSubsampling(), imageReadParam.getSourceYSubsampling(), imageReadParam.getSubsamplingXOffset(), imageReadParam.getSubsamplingYOffset());
        imageReader.read(0, defaultReadParam);
        return bufferedImage;
    }

    /* loaded from: classes3.dex */
    private class EmbeddedProgressAdapter implements IIOReadProgressListener {
        public void imageComplete(ImageReader imageReader) {
        }

        public void imageProgress(ImageReader imageReader, float f) {
        }

        public void imageStarted(ImageReader imageReader, int i) {
        }

        public void readAborted(ImageReader imageReader) {
        }

        public void sequenceComplete(ImageReader imageReader) {
        }

        public void sequenceStarted(ImageReader imageReader, int i) {
        }

        public void thumbnailComplete(ImageReader imageReader) {
        }

        public void thumbnailProgress(ImageReader imageReader, float f) {
        }

        public void thumbnailStarted(ImageReader imageReader, int i, int i2) {
        }

        private EmbeddedProgressAdapter() {
        }
    }
}
