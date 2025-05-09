package com.github.jaiimageio.impl.common;

import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.ComponentSampleModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferShort;
import java.awt.image.DataBufferUShort;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.MultiPixelPackedSampleModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.imageio.IIOException;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.spi.IIORegistry;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ImageReaderWriterSpi;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.spi.ServiceRegistry;
import javax.imageio.stream.ImageInputStream;
import kotlin.UShort;

/* loaded from: classes3.dex */
public class ImageUtil {
    public static final ColorModel createColorModel(SampleModel sampleModel) {
        int i;
        int i2;
        int i3;
        int i4;
        ColorSpace bogusColorSpace;
        if (sampleModel == null) {
            throw new IllegalArgumentException("sampleModel == null!");
        }
        int dataType = sampleModel.getDataType();
        if (dataType != 0 && dataType != 1 && dataType != 2 && dataType != 3 && dataType != 4 && dataType != 5) {
            return null;
        }
        int[] sampleSize = sampleModel.getSampleSize();
        int i5 = 0;
        if (sampleModel instanceof ComponentSampleModel) {
            int numBands = sampleModel.getNumBands();
            if (numBands <= 2) {
                bogusColorSpace = ColorSpace.getInstance(1003);
            } else if (numBands <= 4) {
                bogusColorSpace = ColorSpace.getInstance(1000);
            } else {
                bogusColorSpace = new BogusColorSpace(numBands);
            }
            ColorSpace colorSpace = bogusColorSpace;
            boolean z = numBands == 2 || numBands == 4;
            return new ComponentColorModel(colorSpace, sampleSize, z, false, z ? 3 : 1, dataType);
        }
        if (sampleModel.getNumBands() <= 4 && (sampleModel instanceof SinglePixelPackedSampleModel)) {
            int[] bitMasks = ((SinglePixelPackedSampleModel) sampleModel).getBitMasks();
            int length = bitMasks.length;
            if (length <= 2) {
                int i6 = bitMasks[0];
                if (length == 2) {
                    i4 = bitMasks[1];
                    i3 = i6;
                    i2 = i3;
                    i = i2;
                } else {
                    i3 = i6;
                    i2 = i3;
                    i = i2;
                    i4 = 0;
                }
            } else {
                int i7 = bitMasks[0];
                int i8 = bitMasks[1];
                int i9 = bitMasks[2];
                if (length == 4) {
                    i4 = bitMasks[3];
                    i = i9;
                    i2 = i8;
                    i3 = i7;
                } else {
                    i = i9;
                    i2 = i8;
                    i3 = i7;
                    i4 = 0;
                }
            }
            int i10 = 0;
            while (i5 < sampleSize.length) {
                i10 += sampleSize[i5];
                i5++;
            }
            return new DirectColorModel(i10, i3, i2, i, i4);
        }
        if (!(sampleModel instanceof MultiPixelPackedSampleModel)) {
            return null;
        }
        int i11 = sampleSize[0];
        int i12 = 1 << i11;
        byte[] bArr = new byte[i12];
        while (i5 < i12) {
            bArr[i5] = (byte) ((i5 * 255) / (i12 - 1));
            i5++;
        }
        return new IndexColorModel(i11, i12, bArr, bArr, bArr);
    }

    public static byte[] getPackedBinaryData(Raster raster, Rectangle rectangle) {
        short[] data;
        short[] data2;
        MultiPixelPackedSampleModel sampleModel = raster.getSampleModel();
        if (!isBinary(sampleModel)) {
            throw new IllegalArgumentException(I18N.getString("ImageUtil0"));
        }
        int i = rectangle.x;
        int i2 = rectangle.y;
        int i3 = rectangle.width;
        int i4 = rectangle.height;
        DataBufferByte dataBuffer = raster.getDataBuffer();
        int sampleModelTranslateX = i - raster.getSampleModelTranslateX();
        int sampleModelTranslateY = i2 - raster.getSampleModelTranslateY();
        MultiPixelPackedSampleModel multiPixelPackedSampleModel = sampleModel;
        int scanlineStride = multiPixelPackedSampleModel.getScanlineStride();
        int offset = dataBuffer.getOffset() + multiPixelPackedSampleModel.getOffset(sampleModelTranslateX, sampleModelTranslateY);
        int bitOffset = multiPixelPackedSampleModel.getBitOffset(sampleModelTranslateX);
        int i5 = (i3 + 7) / 8;
        boolean z = dataBuffer instanceof DataBufferByte;
        if (z && offset == 0 && bitOffset == 0 && i5 == scanlineStride) {
            DataBufferByte dataBufferByte = dataBuffer;
            if (dataBufferByte.getData().length == i5 * i4) {
                return dataBufferByte.getData();
            }
        }
        byte[] bArr = new byte[i5 * i4];
        int i6 = 0;
        if (bitOffset == 0) {
            if (z) {
                byte[] data3 = dataBuffer.getData();
                int i7 = 0;
                while (i6 < i4) {
                    System.arraycopy(data3, offset, bArr, i7, i5);
                    i7 += i5;
                    offset += scanlineStride;
                    i6++;
                }
            } else {
                boolean z2 = dataBuffer instanceof DataBufferShort;
                if (z2 || (dataBuffer instanceof DataBufferUShort)) {
                    if (z2) {
                        data2 = ((DataBufferShort) dataBuffer).getData();
                    } else {
                        data2 = ((DataBufferUShort) dataBuffer).getData();
                    }
                    int i8 = 0;
                    while (i6 < i4) {
                        int i9 = i3;
                        int i10 = offset;
                        while (i9 > 8) {
                            int i11 = i10 + 1;
                            short s = data2[i10];
                            int i12 = i8 + 1;
                            bArr[i8] = (byte) ((s >>> 8) & 255);
                            i8 = i12 + 1;
                            bArr[i12] = (byte) (s & 255);
                            i9 -= 16;
                            i10 = i11;
                        }
                        if (i9 > 0) {
                            bArr[i8] = (byte) ((data2[i10] >>> 8) & 255);
                            i8++;
                        }
                        offset += scanlineStride;
                        i6++;
                    }
                } else if (dataBuffer instanceof DataBufferInt) {
                    int[] data4 = ((DataBufferInt) dataBuffer).getData();
                    int i13 = 0;
                    while (i6 < i4) {
                        int i14 = i3;
                        int i15 = offset;
                        while (i14 > 24) {
                            int i16 = i15 + 1;
                            int i17 = data4[i15];
                            int i18 = i13 + 1;
                            bArr[i13] = (byte) ((i17 >>> 24) & 255);
                            int i19 = i18 + 1;
                            bArr[i18] = (byte) ((i17 >>> 16) & 255);
                            int i20 = i19 + 1;
                            bArr[i19] = (byte) ((i17 >>> 8) & 255);
                            i13 = i20 + 1;
                            bArr[i20] = (byte) (i17 & 255);
                            i14 -= 32;
                            i15 = i16;
                        }
                        int i21 = 24;
                        while (i14 > 0) {
                            bArr[i13] = (byte) ((data4[i15] >>> i21) & 255);
                            i21 -= 8;
                            i14 -= 8;
                            i13++;
                        }
                        offset += scanlineStride;
                        i6++;
                    }
                }
            }
        } else if (z) {
            byte[] data5 = dataBuffer.getData();
            int i22 = bitOffset & 7;
            if (i22 == 0) {
                int i23 = 0;
                while (i6 < i4) {
                    System.arraycopy(data5, offset, bArr, i23, i5);
                    i23 += i5;
                    offset += scanlineStride;
                    i6++;
                }
            } else {
                int i24 = 8 - i22;
                int i25 = 0;
                while (i6 < i4) {
                    int i26 = offset;
                    for (int i27 = i3; i27 > 0; i27 -= 8) {
                        if (i27 > i24) {
                            int i28 = i26 + 1;
                            bArr[i25] = (byte) (((data5[i26] & 255) << i22) | ((data5[i28] & 255) >>> i24));
                            i25++;
                            i26 = i28;
                        } else {
                            bArr[i25] = (byte) ((data5[i26] & 255) << i22);
                            i25++;
                        }
                    }
                    offset += scanlineStride;
                    i6++;
                }
            }
        } else {
            boolean z3 = dataBuffer instanceof DataBufferShort;
            if (z3 || (dataBuffer instanceof DataBufferUShort)) {
                if (z3) {
                    data = ((DataBufferShort) dataBuffer).getData();
                } else {
                    data = ((DataBufferUShort) dataBuffer).getData();
                }
                int i29 = 0;
                for (int i30 = 0; i30 < i4; i30++) {
                    int i31 = bitOffset;
                    int i32 = 0;
                    while (i32 < i3) {
                        int i33 = (i31 / 16) + offset;
                        int i34 = i31 % 16;
                        int i35 = data[i33] & UShort.MAX_VALUE;
                        if (i34 <= 8) {
                            bArr[i29] = (byte) (i35 >>> (8 - i34));
                            i29++;
                        } else {
                            int i36 = i34 - 8;
                            bArr[i29] = (byte) (((data[i33 + 1] & UShort.MAX_VALUE) >>> (16 - i36)) | (i35 << i36));
                            i29++;
                        }
                        i32 += 8;
                        i31 += 8;
                    }
                    offset += scanlineStride;
                }
            } else if (dataBuffer instanceof DataBufferInt) {
                int[] data6 = ((DataBufferInt) dataBuffer).getData();
                int i37 = 0;
                for (int i38 = 0; i38 < i4; i38++) {
                    int i39 = bitOffset;
                    int i40 = 0;
                    while (i40 < i3) {
                        int i41 = (i39 / 32) + offset;
                        int i42 = i39 % 32;
                        int i43 = data6[i41];
                        if (i42 <= 24) {
                            bArr[i37] = (byte) (i43 >>> (24 - i42));
                            i37++;
                        } else {
                            int i44 = i42 - 24;
                            bArr[i37] = (byte) ((data6[i41 + 1] >>> (32 - i44)) | (i43 << i44));
                            i37++;
                        }
                        i40 += 8;
                        i39 += 8;
                    }
                    offset += scanlineStride;
                }
            }
        }
        return bArr;
    }

    public static byte[] getUnpackedBinaryData(Raster raster, Rectangle rectangle) {
        short[] data;
        MultiPixelPackedSampleModel sampleModel = raster.getSampleModel();
        if (!isBinary(sampleModel)) {
            throw new IllegalArgumentException(I18N.getString("ImageUtil0"));
        }
        int i = rectangle.x;
        int i2 = rectangle.y;
        int i3 = rectangle.width;
        int i4 = rectangle.height;
        DataBufferByte dataBuffer = raster.getDataBuffer();
        int sampleModelTranslateX = i - raster.getSampleModelTranslateX();
        int sampleModelTranslateY = i2 - raster.getSampleModelTranslateY();
        MultiPixelPackedSampleModel multiPixelPackedSampleModel = sampleModel;
        int scanlineStride = multiPixelPackedSampleModel.getScanlineStride();
        int offset = dataBuffer.getOffset() + multiPixelPackedSampleModel.getOffset(sampleModelTranslateX, sampleModelTranslateY);
        int bitOffset = multiPixelPackedSampleModel.getBitOffset(sampleModelTranslateX);
        byte[] bArr = new byte[i3 * i4];
        int i5 = i4 + i2;
        int i6 = i3 + i;
        int i7 = 0;
        if (dataBuffer instanceof DataBufferByte) {
            byte[] data2 = dataBuffer.getData();
            while (i2 < i5) {
                int i8 = (offset * 8) + bitOffset;
                int i9 = i;
                while (i9 < i6) {
                    bArr[i7] = (byte) ((data2[i8 / 8] >>> ((7 - i8) & 7)) & 1);
                    i8++;
                    i9++;
                    i7++;
                }
                offset += scanlineStride;
                i2++;
            }
        } else {
            boolean z = dataBuffer instanceof DataBufferShort;
            if (z || (dataBuffer instanceof DataBufferUShort)) {
                if (z) {
                    data = ((DataBufferShort) dataBuffer).getData();
                } else {
                    data = ((DataBufferUShort) dataBuffer).getData();
                }
                while (i2 < i5) {
                    int i10 = (offset * 16) + bitOffset;
                    int i11 = i;
                    while (i11 < i6) {
                        bArr[i7] = (byte) ((data[i10 / 16] >>> (15 - (i10 % 16))) & 1);
                        i10++;
                        i11++;
                        i7++;
                    }
                    offset += scanlineStride;
                    i2++;
                }
            } else if (dataBuffer instanceof DataBufferInt) {
                int[] data3 = ((DataBufferInt) dataBuffer).getData();
                while (i2 < i5) {
                    int i12 = (offset * 32) + bitOffset;
                    int i13 = i;
                    while (i13 < i6) {
                        bArr[i7] = (byte) ((data3[i12 / 32] >>> (31 - (i12 % 32))) & 1);
                        i12++;
                        i13++;
                        i7++;
                    }
                    offset += scanlineStride;
                    i2++;
                }
            }
        }
        return bArr;
    }

    public static void setPackedBinaryData(byte[] bArr, WritableRaster writableRaster, Rectangle rectangle) {
        short[] data;
        int i;
        int i2;
        int i3;
        short[] data2;
        byte[] bArr2 = bArr;
        MultiPixelPackedSampleModel sampleModel = writableRaster.getSampleModel();
        if (!isBinary(sampleModel)) {
            throw new IllegalArgumentException(I18N.getString("ImageUtil0"));
        }
        int i4 = rectangle.x;
        int i5 = rectangle.y;
        int i6 = rectangle.width;
        int i7 = rectangle.height;
        DataBufferByte dataBuffer = writableRaster.getDataBuffer();
        int sampleModelTranslateX = i4 - writableRaster.getSampleModelTranslateX();
        int sampleModelTranslateY = i5 - writableRaster.getSampleModelTranslateY();
        MultiPixelPackedSampleModel multiPixelPackedSampleModel = sampleModel;
        int scanlineStride = multiPixelPackedSampleModel.getScanlineStride();
        int offset = dataBuffer.getOffset() + multiPixelPackedSampleModel.getOffset(sampleModelTranslateX, sampleModelTranslateY);
        int bitOffset = multiPixelPackedSampleModel.getBitOffset(sampleModelTranslateX);
        byte b = 255;
        if (bitOffset == 0) {
            if (dataBuffer instanceof DataBufferByte) {
                byte[] data3 = dataBuffer.getData();
                if (data3 == bArr2) {
                    return;
                }
                int i8 = (i6 + 7) / 8;
                int i9 = 0;
                for (int i10 = 0; i10 < i7; i10++) {
                    System.arraycopy(bArr2, i9, data3, offset, i8);
                    i9 += i8;
                    offset += scanlineStride;
                }
                return;
            }
            boolean z = dataBuffer instanceof DataBufferShort;
            if (z || (dataBuffer instanceof DataBufferUShort)) {
                if (z) {
                    data2 = ((DataBufferShort) dataBuffer).getData();
                } else {
                    data2 = ((DataBufferUShort) dataBuffer).getData();
                }
                int i11 = 0;
                for (int i12 = 0; i12 < i7; i12++) {
                    int i13 = i6;
                    int i14 = offset;
                    while (i13 > 8) {
                        int i15 = i11 + 1;
                        data2[i14] = (short) (((bArr2[i11] & 255) << 8) | (bArr2[i15] & 255));
                        i13 -= 16;
                        i14++;
                        i11 = i15 + 1;
                    }
                    if (i13 > 0) {
                        data2[i14] = (short) ((bArr2[i11] & 255) << 8);
                        i11++;
                    }
                    offset += scanlineStride;
                }
                return;
            }
            if (dataBuffer instanceof DataBufferInt) {
                int[] data4 = ((DataBufferInt) dataBuffer).getData();
                int i16 = 0;
                for (int i17 = 0; i17 < i7; i17++) {
                    int i18 = i6;
                    int i19 = offset;
                    while (i18 > 24) {
                        int i20 = i16 + 1;
                        int i21 = i20 + 1;
                        int i22 = ((bArr2[i16] & 255) << 24) | ((bArr2[i20] & 255) << 16);
                        int i23 = i21 + 1;
                        data4[i19] = i22 | ((bArr2[i21] & 255) << 8) | (bArr2[i23] & 255);
                        i18 -= 32;
                        i19++;
                        i16 = i23 + 1;
                    }
                    int i24 = 24;
                    while (i18 > 0) {
                        data4[i19] = ((bArr2[i16] & 255) << i24) | data4[i19];
                        i24 -= 8;
                        i18 -= 8;
                        i16++;
                    }
                    offset += scanlineStride;
                }
                return;
            }
            return;
        }
        int i25 = (i6 + 7) / 8;
        if (dataBuffer instanceof DataBufferByte) {
            byte[] data5 = dataBuffer.getData();
            int i26 = bitOffset & 7;
            if (i26 == 0) {
                int i27 = 0;
                for (int i28 = 0; i28 < i7; i28++) {
                    System.arraycopy(bArr2, i27, data5, offset, i25);
                    i27 += i25;
                    offset += scanlineStride;
                }
                return;
            }
            int i29 = 8 - i26;
            int i30 = i29 + 8;
            byte b2 = (byte) (255 << i29);
            byte b3 = (byte) (~b2);
            int i31 = offset;
            int i32 = 0;
            for (int i33 = 0; i33 < i7; i33++) {
                int i34 = i6;
                int i35 = i31;
                while (i34 > 0) {
                    int i36 = i32 + 1;
                    byte b4 = bArr2[i32];
                    if (i34 > i30) {
                        int i37 = b4 & 255;
                        data5[i35] = (byte) ((data5[i35] & b2) | (i37 >>> i26));
                        i35++;
                        data5[i35] = (byte) (i37 << i29);
                    } else if (i34 > i29) {
                        int i38 = b4 & 255;
                        data5[i35] = (byte) ((data5[i35] & b2) | (i38 >>> i26));
                        i35++;
                        data5[i35] = (byte) ((i38 << i29) | (data5[i35] & b3));
                    } else {
                        int i39 = (1 << (i29 - i34)) - 1;
                        data5[i35] = (byte) ((data5[i35] & (b2 | i39)) | (((b4 & 255) >>> i26) & (~i39)));
                    }
                    i34 -= 8;
                    i32 = i36;
                }
                i31 += scanlineStride;
            }
            return;
        }
        boolean z2 = dataBuffer instanceof DataBufferShort;
        if (!z2 && !(dataBuffer instanceof DataBufferUShort)) {
            if (dataBuffer instanceof DataBufferInt) {
                int[] data6 = ((DataBufferInt) dataBuffer).getData();
                int i40 = bitOffset & 7;
                int i41 = 8 - i40;
                int i42 = i41 + 32;
                int i43 = (-1) << i41;
                int i44 = ~i43;
                int i45 = 0;
                int i46 = 0;
                while (i45 < i7) {
                    int i47 = bitOffset;
                    int i48 = i6;
                    int i49 = 0;
                    while (i49 < i6) {
                        int i50 = offset + (i47 >> 5);
                        int i51 = i47 & 31;
                        int i52 = i46 + 1;
                        int i53 = i6;
                        int i54 = bArr2[i46] & b;
                        if (i51 <= 24) {
                            int i55 = 24 - i51;
                            i = i48;
                            if (i < 8) {
                                i3 = 255;
                                i54 &= 255 << (8 - i);
                            } else {
                                i3 = 255;
                            }
                            i2 = i7;
                            data6[i50] = (data6[i50] & (~(i3 << i55))) | (i54 << i55);
                        } else {
                            i = i48;
                            i2 = i7;
                            if (i > i42) {
                                data6[i50] = (data6[i50] & i43) | (i54 >>> i40);
                                data6[i50 + 1] = i54 << i41;
                            } else if (i > i41) {
                                data6[i50] = (data6[i50] & i43) | (i54 >>> i40);
                                int i56 = i50 + 1;
                                data6[i56] = (data6[i56] & i44) | (i54 << i41);
                            } else {
                                int i57 = (1 << (i41 - i)) - 1;
                                data6[i50] = ((~i57) & (i54 >>> i40)) | (data6[i50] & (i43 | i57));
                            }
                        }
                        i49 += 8;
                        i47 += 8;
                        int i58 = i - 8;
                        i7 = i2;
                        i46 = i52;
                        i6 = i53;
                        b = 255;
                        i48 = i58;
                        bArr2 = bArr;
                    }
                    offset += scanlineStride;
                    i45++;
                    bArr2 = bArr;
                    b = 255;
                }
                return;
            }
            return;
        }
        int i59 = i6;
        if (z2) {
            data = ((DataBufferShort) dataBuffer).getData();
        } else {
            data = ((DataBufferUShort) dataBuffer).getData();
        }
        int i60 = bitOffset & 7;
        int i61 = 8 - i60;
        int i62 = i61 + 16;
        short s = (short) (~(255 << i61));
        short s2 = (short) (65535 << i61);
        short s3 = (short) (~s2);
        int i63 = i7;
        int i64 = 0;
        int i65 = 0;
        while (i64 < i63) {
            int i66 = bitOffset;
            int i67 = i59;
            int i68 = 0;
            while (i68 < i67) {
                int i69 = offset + (i66 >> 4);
                int i70 = bitOffset;
                int i71 = i66 & 15;
                int i72 = i65 + 1;
                int i73 = i63;
                int i74 = bArr[i65] & 255;
                if (i71 <= 8) {
                    if (i67 < 8) {
                        i74 &= 255 << (8 - i67);
                    }
                    data[i69] = (short) ((data[i69] & s) | (i74 << i61));
                } else if (i67 > i62) {
                    data[i69] = (short) ((data[i69] & s2) | ((i74 >>> i60) & 65535));
                    data[i69 + 1] = (short) ((i74 << i61) & 65535);
                    i68 += 8;
                    i66 += 8;
                    i67 -= 8;
                    bitOffset = i70;
                    i65 = i72;
                    i63 = i73;
                } else if (i67 > i61) {
                    data[i69] = (short) ((data[i69] & s2) | ((i74 >>> i60) & 65535));
                    int i75 = i69 + 1;
                    data[i75] = (short) ((data[i75] & s3) | ((i74 << i61) & 65535));
                } else {
                    int i76 = (1 << (i61 - i67)) - 1;
                    data[i69] = (short) ((data[i69] & (s2 | i76)) | ((~i76) & (i74 >>> i60) & 65535));
                    i68 += 8;
                    i66 += 8;
                    i67 -= 8;
                    bitOffset = i70;
                    i65 = i72;
                    i63 = i73;
                }
                i68 += 8;
                i66 += 8;
                i67 -= 8;
                bitOffset = i70;
                i65 = i72;
                i63 = i73;
            }
            offset += scanlineStride;
            i64++;
            i59 = i67;
            i63 = i63;
        }
    }

    public static void setUnpackedBinaryData(byte[] bArr, WritableRaster writableRaster, Rectangle rectangle) {
        short[] data;
        MultiPixelPackedSampleModel sampleModel = writableRaster.getSampleModel();
        if (!isBinary(sampleModel)) {
            throw new IllegalArgumentException(I18N.getString("ImageUtil0"));
        }
        int i = rectangle.x;
        int i2 = rectangle.y;
        int i3 = rectangle.width;
        int i4 = rectangle.height;
        DataBufferByte dataBuffer = writableRaster.getDataBuffer();
        int sampleModelTranslateX = i - writableRaster.getSampleModelTranslateX();
        int sampleModelTranslateY = i2 - writableRaster.getSampleModelTranslateY();
        MultiPixelPackedSampleModel multiPixelPackedSampleModel = sampleModel;
        int scanlineStride = multiPixelPackedSampleModel.getScanlineStride();
        int offset = dataBuffer.getOffset() + multiPixelPackedSampleModel.getOffset(sampleModelTranslateX, sampleModelTranslateY);
        int bitOffset = multiPixelPackedSampleModel.getBitOffset(sampleModelTranslateX);
        if (dataBuffer instanceof DataBufferByte) {
            byte[] data2 = dataBuffer.getData();
            int i5 = 0;
            for (int i6 = 0; i6 < i4; i6++) {
                int i7 = (offset * 8) + bitOffset;
                int i8 = 0;
                while (i8 < i3) {
                    int i9 = i5 + 1;
                    if (bArr[i5] != 0) {
                        int i10 = i7 / 8;
                        data2[i10] = (byte) (data2[i10] | ((byte) (1 << ((7 - i7) & 7))));
                    }
                    i7++;
                    i8++;
                    i5 = i9;
                }
                offset += scanlineStride;
            }
            return;
        }
        boolean z = dataBuffer instanceof DataBufferShort;
        if (z || (dataBuffer instanceof DataBufferUShort)) {
            if (z) {
                data = ((DataBufferShort) dataBuffer).getData();
            } else {
                data = ((DataBufferUShort) dataBuffer).getData();
            }
            int i11 = 0;
            for (int i12 = 0; i12 < i4; i12++) {
                int i13 = (offset * 16) + bitOffset;
                int i14 = 0;
                while (i14 < i3) {
                    int i15 = i11 + 1;
                    if (bArr[i11] != 0) {
                        int i16 = i13 / 16;
                        data[i16] = (short) (data[i16] | ((short) (1 << (15 - (i13 % 16)))));
                    }
                    i13++;
                    i14++;
                    i11 = i15;
                }
                offset += scanlineStride;
            }
            return;
        }
        if (dataBuffer instanceof DataBufferInt) {
            int[] data3 = ((DataBufferInt) dataBuffer).getData();
            int i17 = 0;
            for (int i18 = 0; i18 < i4; i18++) {
                int i19 = (offset * 32) + bitOffset;
                int i20 = 0;
                while (i20 < i3) {
                    int i21 = i17 + 1;
                    if (bArr[i17] != 0) {
                        int i22 = i19 / 32;
                        data3[i22] = data3[i22] | (1 << (31 - (i19 % 32)));
                    }
                    i19++;
                    i20++;
                    i17 = i21;
                }
                offset += scanlineStride;
            }
        }
    }

    public static boolean isBinary(SampleModel sampleModel) {
        return (sampleModel instanceof MultiPixelPackedSampleModel) && ((MultiPixelPackedSampleModel) sampleModel).getPixelBitStride() == 1 && sampleModel.getNumBands() == 1;
    }

    public static ColorModel createColorModel(ColorSpace colorSpace, SampleModel sampleModel) {
        int i;
        int i2;
        int i3;
        int i4;
        ColorSpace colorSpace2;
        ColorSpace colorSpace3;
        if (sampleModel == null) {
            throw new IllegalArgumentException(I18N.getString("ImageUtil1"));
        }
        int numBands = sampleModel.getNumBands();
        if (numBands < 1 || numBands > 4) {
            return null;
        }
        int dataType = sampleModel.getDataType();
        int i5 = 0;
        if (sampleModel instanceof ComponentSampleModel) {
            if (dataType < 0 || dataType > 5) {
                return null;
            }
            if (colorSpace == null) {
                if (numBands <= 2) {
                    colorSpace3 = ColorSpace.getInstance(1003);
                } else {
                    colorSpace3 = ColorSpace.getInstance(1000);
                }
                colorSpace2 = colorSpace3;
            } else {
                colorSpace2 = colorSpace;
            }
            boolean z = numBands == 2 || numBands == 4;
            int i6 = z ? 3 : 1;
            int dataTypeSize = DataBuffer.getDataTypeSize(dataType);
            int[] iArr = new int[numBands];
            while (i5 < numBands) {
                iArr[i5] = dataTypeSize;
                i5++;
            }
            return new ComponentColorModel(colorSpace2, iArr, z, false, i6, dataType);
        }
        if (sampleModel instanceof SinglePixelPackedSampleModel) {
            SinglePixelPackedSampleModel singlePixelPackedSampleModel = (SinglePixelPackedSampleModel) sampleModel;
            int[] bitMasks = singlePixelPackedSampleModel.getBitMasks();
            int length = bitMasks.length;
            if (length <= 2) {
                int i7 = bitMasks[0];
                if (length == 2) {
                    i3 = bitMasks[1];
                    i4 = i7;
                    i = i4;
                    i2 = i;
                } else {
                    i4 = i7;
                    i = i4;
                    i2 = i;
                    i3 = 0;
                }
            } else {
                int i8 = bitMasks[0];
                int i9 = bitMasks[1];
                int i10 = bitMasks[2];
                if (length == 4) {
                    i3 = bitMasks[3];
                    i = i9;
                    i2 = i10;
                } else {
                    i = i9;
                    i2 = i10;
                    i3 = 0;
                }
                i4 = i8;
            }
            int[] sampleSize = singlePixelPackedSampleModel.getSampleSize();
            int i11 = 0;
            while (i5 < sampleSize.length) {
                i11 += sampleSize[i5];
                i5++;
            }
            return new DirectColorModel(colorSpace == null ? ColorSpace.getInstance(1000) : colorSpace, i11, i4, i, i2, i3, false, sampleModel.getDataType());
        }
        if (!(sampleModel instanceof MultiPixelPackedSampleModel)) {
            return null;
        }
        int pixelBitStride = ((MultiPixelPackedSampleModel) sampleModel).getPixelBitStride();
        int i12 = 1 << pixelBitStride;
        byte[] bArr = new byte[i12];
        while (i5 < i12) {
            bArr[i5] = (byte) ((i5 * 255) / (i12 - 1));
            i5++;
        }
        return new IndexColorModel(pixelBitStride, i12, bArr, bArr, bArr);
    }

    public static int getElementSize(SampleModel sampleModel) {
        int numBands;
        int dataTypeSize = DataBuffer.getDataTypeSize(sampleModel.getDataType());
        if (sampleModel instanceof MultiPixelPackedSampleModel) {
            MultiPixelPackedSampleModel multiPixelPackedSampleModel = (MultiPixelPackedSampleModel) sampleModel;
            dataTypeSize = multiPixelPackedSampleModel.getSampleSize(0);
            numBands = multiPixelPackedSampleModel.getNumBands();
        } else {
            if (sampleModel instanceof ComponentSampleModel) {
                return sampleModel.getNumBands() * dataTypeSize;
            }
            if (sampleModel instanceof SinglePixelPackedSampleModel) {
                return dataTypeSize;
            }
            numBands = sampleModel.getNumBands();
        }
        return dataTypeSize * numBands;
    }

    public static long getTileSize(SampleModel sampleModel) {
        int dataTypeSize = DataBuffer.getDataTypeSize(sampleModel.getDataType());
        if (sampleModel instanceof MultiPixelPackedSampleModel) {
            MultiPixelPackedSampleModel multiPixelPackedSampleModel = (MultiPixelPackedSampleModel) sampleModel;
            return ((multiPixelPackedSampleModel.getScanlineStride() * multiPixelPackedSampleModel.getHeight()) + (((multiPixelPackedSampleModel.getDataBitOffset() + dataTypeSize) - 1) / dataTypeSize)) * ((dataTypeSize + 7) / 8);
        }
        if (sampleModel instanceof ComponentSampleModel) {
            ComponentSampleModel componentSampleModel = (ComponentSampleModel) sampleModel;
            int[] bandOffsets = componentSampleModel.getBandOffsets();
            int i = bandOffsets[0];
            for (int i2 = 1; i2 < bandOffsets.length; i2++) {
                i = Math.max(i, bandOffsets[i2]);
            }
            int pixelStride = componentSampleModel.getPixelStride();
            int scanlineStride = componentSampleModel.getScanlineStride();
            long j = i >= 0 ? 0 + i + 1 : 0L;
            if (pixelStride > 0) {
                j += pixelStride * (sampleModel.getWidth() - 1);
            }
            if (scanlineStride > 0) {
                j += scanlineStride * (sampleModel.getHeight() - 1);
            }
            int[] bankIndices = componentSampleModel.getBankIndices();
            int i3 = bankIndices[0];
            for (int i4 = 1; i4 < bankIndices.length; i4++) {
                i3 = Math.max(i3, bankIndices[i4]);
            }
            return j * (i3 + 1) * ((dataTypeSize + 7) / 8);
        }
        if (!(sampleModel instanceof SinglePixelPackedSampleModel)) {
            return 0L;
        }
        SinglePixelPackedSampleModel singlePixelPackedSampleModel = (SinglePixelPackedSampleModel) sampleModel;
        return ((singlePixelPackedSampleModel.getScanlineStride() * (singlePixelPackedSampleModel.getHeight() - 1)) + singlePixelPackedSampleModel.getWidth()) * ((dataTypeSize + 7) / 8);
    }

    public static long getBandSize(SampleModel sampleModel) {
        int dataTypeSize = DataBuffer.getDataTypeSize(sampleModel.getDataType());
        if (sampleModel instanceof ComponentSampleModel) {
            ComponentSampleModel componentSampleModel = (ComponentSampleModel) sampleModel;
            int pixelStride = componentSampleModel.getPixelStride();
            int scanlineStride = componentSampleModel.getScanlineStride();
            long min = Math.min(pixelStride, scanlineStride);
            if (pixelStride > 0) {
                min += pixelStride * (sampleModel.getWidth() - 1);
            }
            if (scanlineStride > 0) {
                min += scanlineStride * (sampleModel.getHeight() - 1);
            }
            return min * ((dataTypeSize + 7) / 8);
        }
        return getTileSize(sampleModel);
    }

    public static boolean isGrayscaleMapping(IndexColorModel indexColorModel) {
        boolean z;
        if (indexColorModel == null) {
            throw new IllegalArgumentException("icm == null!");
        }
        int mapSize = indexColorModel.getMapSize();
        byte[] bArr = new byte[mapSize];
        byte[] bArr2 = new byte[mapSize];
        byte[] bArr3 = new byte[mapSize];
        indexColorModel.getReds(bArr);
        indexColorModel.getGreens(bArr2);
        indexColorModel.getBlues(bArr3);
        for (int i = 0; i < mapSize; i++) {
            byte b = (byte) ((i * 255) / (mapSize - 1));
            if (bArr[i] != b || bArr2[i] != b || bArr3[i] != b) {
                z = false;
                break;
            }
        }
        z = true;
        if (z) {
            return z;
        }
        int i2 = mapSize - 1;
        int i3 = 0;
        int i4 = i2;
        while (i3 < mapSize) {
            byte b2 = (byte) ((i4 * 255) / i2);
            if (bArr[i3] != b2 || bArr2[i3] != b2 || bArr3[i3] != b2) {
                return false;
            }
            i3++;
            i4--;
        }
        return true;
    }

    public static boolean isIndicesForGrayscale(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int length;
        if (bArr.length != bArr2.length || bArr.length != bArr3.length || (length = bArr.length) != 256) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            byte b = (byte) i;
            if (bArr[i] != b || bArr2[i] != b || bArr3[i] != b) {
                return false;
            }
        }
        return true;
    }

    public static String convertObjectToString(Object obj) {
        String str = "";
        if (obj == null) {
            return "";
        }
        int i = 0;
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            while (i < bArr.length) {
                str = str + ((int) bArr[i]) + " ";
                i++;
            }
            return str;
        }
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            while (i < iArr.length) {
                str = str + iArr[i] + " ";
                i++;
            }
            return str;
        }
        if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            while (i < sArr.length) {
                str = str + ((int) sArr[i]) + " ";
                i++;
            }
            return str;
        }
        return obj.toString();
    }

    public static final void canEncodeImage(ImageWriter imageWriter, ImageTypeSpecifier imageTypeSpecifier) throws IIOException {
        ImageWriterSpi originatingProvider = imageWriter.getOriginatingProvider();
        if (imageTypeSpecifier == null || originatingProvider == null || originatingProvider.canEncodeImage(imageTypeSpecifier)) {
            return;
        }
        throw new IIOException(I18N.getString("ImageUtil2") + " " + imageWriter.getClass().getName());
    }

    public static final void canEncodeImage(ImageWriter imageWriter, ColorModel colorModel, SampleModel sampleModel) throws IIOException {
        canEncodeImage(imageWriter, (colorModel == null || sampleModel == null) ? null : new ImageTypeSpecifier(colorModel, sampleModel));
    }

    public static final boolean imageIsContiguous(RenderedImage renderedImage) {
        SampleModel sampleModel;
        if (renderedImage instanceof BufferedImage) {
            sampleModel = ((BufferedImage) renderedImage).getRaster().getSampleModel();
        } else {
            sampleModel = renderedImage.getSampleModel();
        }
        if (sampleModel instanceof ComponentSampleModel) {
            ComponentSampleModel componentSampleModel = (ComponentSampleModel) sampleModel;
            if (componentSampleModel.getPixelStride() != componentSampleModel.getNumBands()) {
                return false;
            }
            int[] bandOffsets = componentSampleModel.getBandOffsets();
            for (int i = 0; i < bandOffsets.length; i++) {
                if (bandOffsets[i] != i) {
                    return false;
                }
            }
            int[] bankIndices = componentSampleModel.getBankIndices();
            for (int i2 = 0; i2 < bandOffsets.length; i2++) {
                if (bankIndices[i2] != 0) {
                    return false;
                }
            }
            return true;
        }
        return isBinary(sampleModel);
    }

    public static final ImageTypeSpecifier getDestinationType(ImageReadParam imageReadParam, Iterator it) throws IIOException {
        if (it == null || !it.hasNext()) {
            throw new IllegalArgumentException("imageTypes null or empty!");
        }
        ImageTypeSpecifier destinationType = imageReadParam != null ? imageReadParam.getDestinationType() : null;
        if (destinationType == null) {
            Object next = it.next();
            if (!(next instanceof ImageTypeSpecifier)) {
                throw new IllegalArgumentException("Non-ImageTypeSpecifier retrieved from imageTypes!");
            }
            return (ImageTypeSpecifier) next;
        }
        boolean z = false;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (((ImageTypeSpecifier) it.next()).equals(destinationType)) {
                z = true;
                break;
            }
        }
        if (z) {
            return destinationType;
        }
        throw new IIOException("Destination type from ImageReadParam does not match!");
    }

    public static boolean isNonStandardICCColorSpace(ColorSpace colorSpace) {
        try {
            if (!(colorSpace instanceof ICC_ColorSpace) || colorSpace.isCS_sRGB() || colorSpace.equals(ColorSpace.getInstance(1004)) || colorSpace.equals(ColorSpace.getInstance(1003)) || colorSpace.equals(ColorSpace.getInstance(1001))) {
                return false;
            }
            return !colorSpace.equals(ColorSpace.getInstance(1002));
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static List getJDKImageReaderWriterSPI(ServiceRegistry serviceRegistry, String str, boolean z) {
        Class<ImageReaderSpi> cls;
        String str2;
        IIORegistry iIORegistry = (IIORegistry) serviceRegistry;
        if (z) {
            cls = ImageReaderSpi.class;
            str2 = " image reader";
        } else {
            cls = ImageWriterSpi.class;
            str2 = " image writer";
        }
        Iterator serviceProviders = iIORegistry.getServiceProviders(cls, true);
        String str3 = "standard " + str + str2;
        Locale locale = Locale.getDefault();
        ArrayList arrayList = new ArrayList();
        while (serviceProviders.hasNext()) {
            ImageReaderWriterSpi imageReaderWriterSpi = (ImageReaderWriterSpi) serviceProviders.next();
            if (imageReaderWriterSpi.getVendorName().startsWith("Sun Microsystems") && str3.equalsIgnoreCase(imageReaderWriterSpi.getDescription(locale)) && !imageReaderWriterSpi.getPluginClassName().startsWith("com.github.jaiimageio.impl")) {
                String[] formatNames = imageReaderWriterSpi.getFormatNames();
                int i = 0;
                while (true) {
                    if (i >= formatNames.length) {
                        break;
                    }
                    if (formatNames[i].equalsIgnoreCase(str)) {
                        arrayList.add(imageReaderWriterSpi);
                        break;
                    }
                    i++;
                }
            }
        }
        return arrayList;
    }

    public static void processOnRegistration(ServiceRegistry serviceRegistry, Class cls, String str, ImageReaderWriterSpi imageReaderWriterSpi, int i, int i2) {
        List jDKImageReaderWriterSPI;
        String property = System.getProperty("java.vendor");
        String property2 = System.getProperty("java.specification.version");
        int parseInt = Integer.parseInt(property2.substring(property2.indexOf("1.") + 2));
        if (property.equals("Sun Microsystems Inc.")) {
            if (imageReaderWriterSpi instanceof ImageReaderSpi) {
                jDKImageReaderWriterSPI = getJDKImageReaderWriterSPI(serviceRegistry, str, true);
            } else {
                jDKImageReaderWriterSPI = getJDKImageReaderWriterSPI(serviceRegistry, str, false);
            }
            if (parseInt >= i && jDKImageReaderWriterSPI.size() != 0) {
                serviceRegistry.deregisterServiceProvider(imageReaderWriterSpi, cls);
                return;
            }
            for (int i3 = 0; i3 < jDKImageReaderWriterSPI.size(); i3++) {
                if (parseInt >= i2) {
                    serviceRegistry.setOrdering(cls, jDKImageReaderWriterSPI.get(i3), imageReaderWriterSpi);
                } else {
                    serviceRegistry.setOrdering(cls, imageReaderWriterSpi, jDKImageReaderWriterSPI.get(i3));
                }
            }
        }
    }

    public static int readMultiByteInteger(ImageInputStream imageInputStream) throws IOException {
        byte readByte = imageInputStream.readByte();
        int i = readByte & Byte.MAX_VALUE;
        while ((readByte & 128) == 128) {
            int i2 = i << 7;
            byte readByte2 = imageInputStream.readByte();
            i = i2 | (readByte2 & Byte.MAX_VALUE);
            readByte = readByte2;
        }
        return i;
    }
}
