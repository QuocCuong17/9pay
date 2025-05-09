package com.github.jaiimageio.impl.plugins.tiff;

import androidx.media3.common.C;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.github.jaiimageio.plugins.tiff.TIFFCompressor;
import com.github.jaiimageio.plugins.tiff.TIFFField;
import javax.imageio.metadata.IIOMetadata;

/* loaded from: classes3.dex */
public abstract class TIFFFaxCompressor extends TIFFCompressor {
    public static final int BLACK = 1;
    public static final int WHITE = 0;
    public static byte[] byteTable = {8, 7, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static int[][] horz;
    public static int[] horzMode;
    public static int[][] makeupCodes;
    public static int[] makeupCodesBlack;
    public static int[] makeupCodesWhite;
    public static int[][] pass;
    public static int[] passMode;
    public static int[][] termCodes;
    public static int[] termCodesBlack;
    public static int[] termCodesWhite;
    public static int[][] vert;
    public static int[] vertMode;
    public int bits;
    public boolean inverseFill;
    public int ndex;

    static {
        int[] iArr = {230686730, 1073741827, -1073741822, -2147483646, 1610612739, 805306372, 536870916, 402653189, 335544326, 268435462, 134217735, 167772167, 234881031, 67108872, 117440520, 201326601, 96469002, 100663306, 33554442, 216006667, 218103819, 226492427, 115343371, 83886091, 48234507, 50331659, 211812364, 212860940, 213909516, 214958092, 109051916, 110100492, 111149068, 112197644, 220200972, 221249548, 222298124, 223346700, 224395276, 225443852, 113246220, 114294796, 228589580, 229638156, 88080396, 89128972, 90177548, 91226124, 104857612, 105906188, 85983244, 87031820, 37748748, 57671692, 58720268, 40894476, 41943052, 92274700, 93323276, 45088780, 46137356, 94371852, 106954764, 108003340};
        termCodesBlack = iArr;
        int[] iArr2 = {889192456, 469762054, 1879048196, -2147483644, -1342177276, -1073741820, -536870908, -268435452, -1744830459, -1610612731, 939524101, 1073741829, 536870918, 201326598, -805306362, -738197498, -1476395002, -1409286138, 1308622855, 402653191, 268435463, 771751943, 100663303, 134217735, 1342177287, 1442840583, 637534215, 1207959559, 805306375, 33554440, 50331656, 436207624, 452984840, 301989896, 318767112, 335544328, 352321544, 369098760, 385875976, 671088648, 687865864, 704643080, 721420296, 738197512, 754974728, 67108872, 83886088, 167772168, 184549384, 1375731720, 1392508936, 1409286152, 1426063368, 603979784, 620757000, 1476395016, 1493172232, 1509949448, 1526726664, 1241513992, 1258291208, 838860808, 855638024, 872415240};
        termCodesWhite = iArr2;
        int[] iArr3 = {0, 62914570, 209715212, 210763788, 95420428, 53477388, 54525964, 55574540, 56623117, 57147405, 38797325, 39321613, 39845901, 40370189, 59768845, 60293133, 60817421, 61341709, 61865997, 62390285, 42991629, 43515917, 44040205, 44564493, 47185933, 47710221, 52428813, 52953101, 16777227, 25165835, 27262987, 18874380, 19922956, 20971532, 22020108, 23068684, 24117260, 29360140, 30408716, 31457292, 32505868, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        makeupCodesBlack = iArr3;
        int[] iArr4 = {0, -671088635, -1879048187, 1543503878, 1845493767, 905969672, 922746888, 1677721608, 1694498824, 1744830472, 1728053256, 1711276041, 1719664649, 1761607689, 1769996297, 1778384905, 1786773513, 1795162121, 1803550729, 1811939337, 1820327945, 1828716553, 1837105161, 1275068425, 1283457033, 1291845641, 1610612742, 1300234249, 16777227, 25165835, 27262987, 18874380, 19922956, 20971532, 22020108, 23068684, 24117260, 29360140, 30408716, 31457292, 32505868, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        makeupCodesWhite = iArr4;
        int[] iArr5 = {268435460};
        passMode = iArr5;
        int[] iArr6 = {100663303, 201326598, 1610612739, C.RATE_UNSET_INT, 1073741827, 134217734, 67108871};
        vertMode = iArr6;
        int[] iArr7 = {536870915};
        horzMode = iArr7;
        termCodes = new int[][]{iArr2, iArr};
        makeupCodes = new int[][]{iArr4, iArr3};
        pass = new int[][]{iArr5, iArr5};
        vert = new int[][]{iArr6, iArr6};
        horz = new int[][]{iArr7, iArr7};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TIFFFaxCompressor(String str, int i, boolean z) {
        super(str, i, z);
        this.inverseFill = false;
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFCompressor
    public void setMetadata(IIOMetadata iIOMetadata) {
        super.setMetadata(iIOMetadata);
        if (iIOMetadata instanceof TIFFImageMetadata) {
            TIFFField tIFFField = ((TIFFImageMetadata) iIOMetadata).getTIFFField(BaselineTIFFTagSet.TAG_FILL_ORDER);
            boolean z = false;
            if (tIFFField != null && tIFFField.getAsInt(0) == 2) {
                z = true;
            }
            this.inverseFill = z;
        }
    }

    public int nextState(byte[] bArr, int i, int i2, int i3) {
        int i4;
        int i5;
        if (bArr == null || (i4 = (i2 >>> 3) + i) >= bArr.length) {
            return i3;
        }
        int i6 = (i3 >>> 3) + i;
        if (i6 == bArr.length) {
            i6--;
        }
        int i7 = i2 & 7;
        if ((bArr[i4] & (128 >>> i7)) != 0) {
            i5 = (255 >>> i7) & (~bArr[i4]);
            while (i4 < i6 && i5 == 0) {
                i4++;
                i5 = (~bArr[i4]) & 255;
            }
        } else {
            i5 = (255 >>> i7) & bArr[i4];
            if (i5 != 0) {
                int i8 = ((i4 - i) * 8) + byteTable[i5];
                return i8 < i3 ? i8 : i3;
            }
            while (i4 < i6) {
                i4++;
                i5 = bArr[i4] & 255;
                if (i5 != 0) {
                    int i9 = ((i4 - i) * 8) + byteTable[i5];
                    return i9 < i3 ? i9 : i3;
                }
            }
        }
        int i10 = ((i4 - i) * 8) + byteTable[i5];
        return i10 < i3 ? i10 : i3;
    }

    public void initBitBuf() {
        this.ndex = 0;
        this.bits = 0;
    }

    public int add1DBits(byte[] bArr, int i, int i2, int i3) {
        int i4 = i2 >>> 6;
        int i5 = i2 & 63;
        int i6 = i;
        if (i4 != 0) {
            while (i4 > 40) {
                int i7 = makeupCodes[i3][40];
                int i8 = this.bits;
                int i9 = this.ndex;
                this.bits = i8 | ((i7 & (-524288)) >>> i9);
                this.ndex = i9 + (i7 & 65535);
                while (true) {
                    int i10 = this.ndex;
                    if (i10 > 7) {
                        int i11 = this.bits;
                        bArr[i6] = (byte) (i11 >>> 24);
                        this.bits = i11 << 8;
                        this.ndex = i10 - 8;
                        i6++;
                    }
                }
                i4 -= 40;
            }
            int i12 = makeupCodes[i3][i4];
            int i13 = this.bits;
            int i14 = this.ndex;
            this.bits = i13 | ((i12 & (-524288)) >>> i14);
            this.ndex = i14 + (i12 & 65535);
            while (true) {
                int i15 = this.ndex;
                if (i15 <= 7) {
                    break;
                }
                int i16 = this.bits;
                bArr[i6] = (byte) (i16 >>> 24);
                this.bits = i16 << 8;
                this.ndex = i15 - 8;
                i6++;
            }
        }
        int i17 = termCodes[i3][i5];
        int i18 = this.bits;
        int i19 = this.ndex;
        this.bits = i18 | ((i17 & (-524288)) >>> i19);
        this.ndex = i19 + (i17 & 65535);
        while (true) {
            int i20 = this.ndex;
            if (i20 <= 7) {
                return i6 - i;
            }
            int i21 = this.bits;
            bArr[i6] = (byte) (i21 >>> 24);
            this.bits = i21 << 8;
            this.ndex = i20 - 8;
            i6++;
        }
    }

    public int add2DBits(byte[] bArr, int i, int[][] iArr, int i2) {
        int i3 = iArr[0][i2];
        int i4 = this.bits;
        int i5 = this.ndex;
        this.bits = i4 | (((-524288) & i3) >>> i5);
        this.ndex = i5 + (i3 & 65535);
        int i6 = i;
        while (true) {
            int i7 = this.ndex;
            if (i7 <= 7) {
                return i6 - i;
            }
            int i8 = this.bits;
            bArr[i6] = (byte) (i8 >>> 24);
            this.bits = i8 << 8;
            this.ndex = i7 - 8;
            i6++;
        }
    }

    public int addEOL(boolean z, boolean z2, boolean z3, byte[] bArr, int i) {
        if (z2) {
            int i2 = this.ndex;
            this.ndex = i2 + (i2 <= 4 ? 4 - i2 : 12 - i2);
        }
        if (z) {
            int i3 = this.bits;
            int i4 = this.ndex;
            this.bits = i3 | (1048576 >>> i4);
            this.ndex = i4 + 12;
        } else {
            int i5 = this.bits;
            int i6 = z3 ? 1572864 : 1048576;
            int i7 = this.ndex;
            this.bits = i5 | (i6 >>> i7);
            this.ndex = i7 + 13;
        }
        int i8 = i;
        while (true) {
            int i9 = this.ndex;
            if (i9 <= 7) {
                return i8 - i;
            }
            int i10 = this.bits;
            bArr[i8] = (byte) (i10 >>> 24);
            this.bits = i10 << 8;
            this.ndex = i9 - 8;
            i8++;
        }
    }

    public int addEOFB(byte[] bArr, int i) {
        int i2 = this.bits;
        int i3 = this.ndex;
        this.bits = i2 | (1048832 >>> i3);
        this.ndex = i3 + 24;
        int i4 = i;
        while (true) {
            int i5 = this.ndex;
            if (i5 <= 0) {
                return i4 - i;
            }
            int i6 = this.bits;
            bArr[i4] = (byte) (i6 >>> 24);
            this.bits = i6 << 8;
            this.ndex = i5 - 8;
            i4++;
        }
    }

    public int encode1D(byte[] bArr, int i, int i2, int i3, byte[] bArr2, int i4) {
        int i5;
        int i6 = i3 + i2;
        int i7 = 0;
        if ((((bArr[(i2 >>> 3) + i] & 255) >>> (7 - (i2 & 7))) & 1) != 0) {
            i5 = add1DBits(bArr2, i4, 0, 0) + i4;
            i7 = 1;
        } else {
            i5 = i4;
        }
        while (i2 < i6) {
            int nextState = nextState(bArr, i, i2, i6) - i2;
            i5 += add1DBits(bArr2, i5, nextState, i7);
            i2 += nextState;
            i7 ^= 1;
        }
        return i5 - i4;
    }
}
