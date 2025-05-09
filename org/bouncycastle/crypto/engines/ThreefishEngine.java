package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.TweakableBlockCipherParameters;

/* loaded from: classes5.dex */
public class ThreefishEngine implements BlockCipher {
    public static final int BLOCKSIZE_1024 = 1024;
    public static final int BLOCKSIZE_256 = 256;
    public static final int BLOCKSIZE_512 = 512;
    private static final long C_240 = 2004413935125273122L;
    private static final int MAX_ROUNDS = 80;
    private static int[] MOD17 = null;
    private static int[] MOD3 = null;
    private static int[] MOD5 = null;
    private static int[] MOD9 = null;
    private static final int ROUNDS_1024 = 80;
    private static final int ROUNDS_256 = 72;
    private static final int ROUNDS_512 = 72;
    private static final int TWEAK_SIZE_BYTES = 16;
    private static final int TWEAK_SIZE_WORDS = 2;
    private int blocksizeBytes;
    private int blocksizeWords;
    private ThreefishCipher cipher;
    private long[] currentBlock;
    private boolean forEncryption;
    private long[] kw;
    private long[] t;

    /* loaded from: classes5.dex */
    private static final class Threefish1024Cipher extends ThreefishCipher {
        private static final int ROTATION_0_0 = 24;
        private static final int ROTATION_0_1 = 13;
        private static final int ROTATION_0_2 = 8;
        private static final int ROTATION_0_3 = 47;
        private static final int ROTATION_0_4 = 8;
        private static final int ROTATION_0_5 = 17;
        private static final int ROTATION_0_6 = 22;
        private static final int ROTATION_0_7 = 37;
        private static final int ROTATION_1_0 = 38;
        private static final int ROTATION_1_1 = 19;
        private static final int ROTATION_1_2 = 10;
        private static final int ROTATION_1_3 = 55;
        private static final int ROTATION_1_4 = 49;
        private static final int ROTATION_1_5 = 18;
        private static final int ROTATION_1_6 = 23;
        private static final int ROTATION_1_7 = 52;
        private static final int ROTATION_2_0 = 33;
        private static final int ROTATION_2_1 = 4;
        private static final int ROTATION_2_2 = 51;
        private static final int ROTATION_2_3 = 13;
        private static final int ROTATION_2_4 = 34;
        private static final int ROTATION_2_5 = 41;
        private static final int ROTATION_2_6 = 59;
        private static final int ROTATION_2_7 = 17;
        private static final int ROTATION_3_0 = 5;
        private static final int ROTATION_3_1 = 20;
        private static final int ROTATION_3_2 = 48;
        private static final int ROTATION_3_3 = 41;
        private static final int ROTATION_3_4 = 47;
        private static final int ROTATION_3_5 = 28;
        private static final int ROTATION_3_6 = 16;
        private static final int ROTATION_3_7 = 25;
        private static final int ROTATION_4_0 = 41;
        private static final int ROTATION_4_1 = 9;
        private static final int ROTATION_4_2 = 37;
        private static final int ROTATION_4_3 = 31;
        private static final int ROTATION_4_4 = 12;
        private static final int ROTATION_4_5 = 47;
        private static final int ROTATION_4_6 = 44;
        private static final int ROTATION_4_7 = 30;
        private static final int ROTATION_5_0 = 16;
        private static final int ROTATION_5_1 = 34;
        private static final int ROTATION_5_2 = 56;
        private static final int ROTATION_5_3 = 51;
        private static final int ROTATION_5_4 = 4;
        private static final int ROTATION_5_5 = 53;
        private static final int ROTATION_5_6 = 42;
        private static final int ROTATION_5_7 = 41;
        private static final int ROTATION_6_0 = 31;
        private static final int ROTATION_6_1 = 44;
        private static final int ROTATION_6_2 = 47;
        private static final int ROTATION_6_3 = 46;
        private static final int ROTATION_6_4 = 19;
        private static final int ROTATION_6_5 = 42;
        private static final int ROTATION_6_6 = 44;
        private static final int ROTATION_6_7 = 25;
        private static final int ROTATION_7_0 = 9;
        private static final int ROTATION_7_1 = 48;
        private static final int ROTATION_7_2 = 35;
        private static final int ROTATION_7_3 = 52;
        private static final int ROTATION_7_4 = 23;
        private static final int ROTATION_7_5 = 31;
        private static final int ROTATION_7_6 = 37;
        private static final int ROTATION_7_7 = 20;

        public Threefish1024Cipher(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        void decryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.kw;
            long[] jArr4 = this.t;
            int[] iArr = ThreefishEngine.MOD17;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 33) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            int i = 1;
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = jArr[8];
            long j10 = jArr[9];
            long j11 = jArr[10];
            long j12 = jArr[11];
            long j13 = jArr[12];
            long j14 = jArr[13];
            long j15 = jArr[14];
            long j16 = jArr[15];
            int i2 = 19;
            while (i2 >= i) {
                int i3 = iArr[i2];
                int i4 = iArr2[i2];
                int i5 = i3 + 1;
                long j17 = j - jArr3[i5];
                int i6 = i3 + 2;
                long j18 = j2 - jArr3[i6];
                int i7 = i3 + 3;
                long j19 = j3 - jArr3[i7];
                int i8 = i3 + 4;
                long j20 = j4 - jArr3[i8];
                int i9 = i3 + 5;
                long j21 = j5 - jArr3[i9];
                int i10 = i3 + 6;
                int[] iArr3 = iArr;
                int[] iArr4 = iArr2;
                long j22 = j6 - jArr3[i10];
                int i11 = i3 + 7;
                long j23 = j7 - jArr3[i11];
                int i12 = i3 + 8;
                long j24 = j8 - jArr3[i12];
                int i13 = i3 + 9;
                long j25 = j9 - jArr3[i13];
                int i14 = i3 + 10;
                long j26 = j10 - jArr3[i14];
                int i15 = i3 + 11;
                long j27 = j11 - jArr3[i15];
                int i16 = i3 + 12;
                long j28 = j12 - jArr3[i16];
                int i17 = i3 + 13;
                long j29 = j13 - jArr3[i17];
                int i18 = i3 + 14;
                int i19 = i4 + 1;
                long j30 = j14 - (jArr3[i18] + jArr4[i19]);
                int i20 = i3 + 15;
                long j31 = j15 - (jArr3[i20] + jArr4[i4 + 2]);
                long[] jArr5 = jArr3;
                long j32 = i2;
                long xorRotr = ThreefishEngine.xorRotr(j16 - ((jArr3[i3 + 16] + j32) + 1), 9, j17);
                long j33 = j17 - xorRotr;
                long xorRotr2 = ThreefishEngine.xorRotr(j28, 48, j19);
                long j34 = j19 - xorRotr2;
                long xorRotr3 = ThreefishEngine.xorRotr(j30, 35, j23);
                long j35 = j23 - xorRotr3;
                long[] jArr6 = jArr4;
                long xorRotr4 = ThreefishEngine.xorRotr(j26, 52, j21);
                long j36 = j21 - xorRotr4;
                long xorRotr5 = ThreefishEngine.xorRotr(j18, 23, j31);
                long j37 = j31 - xorRotr5;
                long xorRotr6 = ThreefishEngine.xorRotr(j22, 31, j25);
                long j38 = j25 - xorRotr6;
                long xorRotr7 = ThreefishEngine.xorRotr(j20, 37, j27);
                long j39 = j27 - xorRotr7;
                long xorRotr8 = ThreefishEngine.xorRotr(j24, 20, j29);
                long j40 = j29 - xorRotr8;
                long xorRotr9 = ThreefishEngine.xorRotr(xorRotr8, 31, j33);
                long j41 = j33 - xorRotr9;
                long xorRotr10 = ThreefishEngine.xorRotr(xorRotr6, 44, j34);
                long j42 = j34 - xorRotr10;
                long xorRotr11 = ThreefishEngine.xorRotr(xorRotr7, 47, j36);
                long j43 = j36 - xorRotr11;
                long xorRotr12 = ThreefishEngine.xorRotr(xorRotr5, 46, j35);
                long j44 = j35 - xorRotr12;
                long xorRotr13 = ThreefishEngine.xorRotr(xorRotr, 19, j40);
                long j45 = j40 - xorRotr13;
                long xorRotr14 = ThreefishEngine.xorRotr(xorRotr3, 42, j37);
                long j46 = j37 - xorRotr14;
                long xorRotr15 = ThreefishEngine.xorRotr(xorRotr2, 44, j38);
                long j47 = j38 - xorRotr15;
                long xorRotr16 = ThreefishEngine.xorRotr(xorRotr4, 25, j39);
                long j48 = j39 - xorRotr16;
                long xorRotr17 = ThreefishEngine.xorRotr(xorRotr16, 16, j41);
                long j49 = j41 - xorRotr17;
                long xorRotr18 = ThreefishEngine.xorRotr(xorRotr14, 34, j42);
                long j50 = j42 - xorRotr18;
                long xorRotr19 = ThreefishEngine.xorRotr(xorRotr15, 56, j44);
                long j51 = j44 - xorRotr19;
                long xorRotr20 = ThreefishEngine.xorRotr(xorRotr13, 51, j43);
                long j52 = j43 - xorRotr20;
                long xorRotr21 = ThreefishEngine.xorRotr(xorRotr9, 4, j48);
                long j53 = j48 - xorRotr21;
                long xorRotr22 = ThreefishEngine.xorRotr(xorRotr11, 53, j45);
                long j54 = j45 - xorRotr22;
                long xorRotr23 = ThreefishEngine.xorRotr(xorRotr10, 42, j46);
                long j55 = j46 - xorRotr23;
                long xorRotr24 = ThreefishEngine.xorRotr(xorRotr12, 41, j47);
                long j56 = j47 - xorRotr24;
                long xorRotr25 = ThreefishEngine.xorRotr(xorRotr24, 41, j49);
                long xorRotr26 = ThreefishEngine.xorRotr(xorRotr22, 9, j50);
                long xorRotr27 = ThreefishEngine.xorRotr(xorRotr23, 37, j52);
                long j57 = j52 - xorRotr27;
                long xorRotr28 = ThreefishEngine.xorRotr(xorRotr21, 31, j51);
                long j58 = j51 - xorRotr28;
                long xorRotr29 = ThreefishEngine.xorRotr(xorRotr17, 12, j56);
                long j59 = j56 - xorRotr29;
                long xorRotr30 = ThreefishEngine.xorRotr(xorRotr19, 47, j53);
                long j60 = j53 - xorRotr30;
                long xorRotr31 = ThreefishEngine.xorRotr(xorRotr18, 44, j54);
                long j61 = j54 - xorRotr31;
                long xorRotr32 = ThreefishEngine.xorRotr(xorRotr20, 30, j55);
                long j62 = j55 - xorRotr32;
                long j63 = (j49 - xorRotr25) - jArr5[i3];
                long j64 = xorRotr25 - jArr5[i5];
                long j65 = (j50 - xorRotr26) - jArr5[i6];
                long j66 = xorRotr26 - jArr5[i7];
                long j67 = j57 - jArr5[i8];
                long j68 = xorRotr27 - jArr5[i9];
                long j69 = j58 - jArr5[i10];
                long j70 = xorRotr28 - jArr5[i11];
                long j71 = j59 - jArr5[i12];
                long j72 = xorRotr29 - jArr5[i13];
                long j73 = j60 - jArr5[i14];
                long j74 = xorRotr30 - jArr5[i15];
                long j75 = j61 - jArr5[i16];
                long j76 = xorRotr31 - (jArr5[i17] + jArr6[i4]);
                long j77 = j62 - (jArr5[i18] + jArr6[i19]);
                long xorRotr33 = ThreefishEngine.xorRotr(xorRotr32 - (jArr5[i20] + j32), 5, j63);
                long j78 = j63 - xorRotr33;
                long xorRotr34 = ThreefishEngine.xorRotr(j74, 20, j65);
                long j79 = j65 - xorRotr34;
                long xorRotr35 = ThreefishEngine.xorRotr(j76, 48, j69);
                long j80 = j69 - xorRotr35;
                long xorRotr36 = ThreefishEngine.xorRotr(j72, 41, j67);
                long j81 = j67 - xorRotr36;
                long xorRotr37 = ThreefishEngine.xorRotr(j64, 47, j77);
                long j82 = j77 - xorRotr37;
                long xorRotr38 = ThreefishEngine.xorRotr(j68, 28, j71);
                long j83 = j71 - xorRotr38;
                long xorRotr39 = ThreefishEngine.xorRotr(j66, 16, j73);
                long j84 = j73 - xorRotr39;
                long xorRotr40 = ThreefishEngine.xorRotr(j70, 25, j75);
                long j85 = j75 - xorRotr40;
                long xorRotr41 = ThreefishEngine.xorRotr(xorRotr40, 33, j78);
                long j86 = j78 - xorRotr41;
                long xorRotr42 = ThreefishEngine.xorRotr(xorRotr38, 4, j79);
                long j87 = j79 - xorRotr42;
                long xorRotr43 = ThreefishEngine.xorRotr(xorRotr39, 51, j81);
                long j88 = j81 - xorRotr43;
                long xorRotr44 = ThreefishEngine.xorRotr(xorRotr37, 13, j80);
                long j89 = j80 - xorRotr44;
                long xorRotr45 = ThreefishEngine.xorRotr(xorRotr33, 34, j85);
                long j90 = j85 - xorRotr45;
                long xorRotr46 = ThreefishEngine.xorRotr(xorRotr35, 41, j82);
                long j91 = j82 - xorRotr46;
                long xorRotr47 = ThreefishEngine.xorRotr(xorRotr34, 59, j83);
                long j92 = j83 - xorRotr47;
                long xorRotr48 = ThreefishEngine.xorRotr(xorRotr36, 17, j84);
                long j93 = j84 - xorRotr48;
                long xorRotr49 = ThreefishEngine.xorRotr(xorRotr48, 38, j86);
                long j94 = j86 - xorRotr49;
                long xorRotr50 = ThreefishEngine.xorRotr(xorRotr46, 19, j87);
                long j95 = j87 - xorRotr50;
                long xorRotr51 = ThreefishEngine.xorRotr(xorRotr47, 10, j89);
                long j96 = j89 - xorRotr51;
                long xorRotr52 = ThreefishEngine.xorRotr(xorRotr45, 55, j88);
                long j97 = j88 - xorRotr52;
                long xorRotr53 = ThreefishEngine.xorRotr(xorRotr41, 49, j93);
                long j98 = j93 - xorRotr53;
                long xorRotr54 = ThreefishEngine.xorRotr(xorRotr43, 18, j90);
                long j99 = j90 - xorRotr54;
                long xorRotr55 = ThreefishEngine.xorRotr(xorRotr42, 23, j91);
                long j100 = j91 - xorRotr55;
                long xorRotr56 = ThreefishEngine.xorRotr(xorRotr44, 52, j92);
                long j101 = j92 - xorRotr56;
                long xorRotr57 = ThreefishEngine.xorRotr(xorRotr56, 24, j94);
                long j102 = j94 - xorRotr57;
                long xorRotr58 = ThreefishEngine.xorRotr(xorRotr54, 13, j95);
                j3 = j95 - xorRotr58;
                long xorRotr59 = ThreefishEngine.xorRotr(xorRotr55, 8, j97);
                long j103 = j97 - xorRotr59;
                long xorRotr60 = ThreefishEngine.xorRotr(xorRotr53, 47, j96);
                long j104 = j96 - xorRotr60;
                long xorRotr61 = ThreefishEngine.xorRotr(xorRotr49, 8, j101);
                long j105 = j101 - xorRotr61;
                long xorRotr62 = ThreefishEngine.xorRotr(xorRotr51, 17, j98);
                long j106 = j98 - xorRotr62;
                j14 = ThreefishEngine.xorRotr(xorRotr50, 22, j99);
                j16 = ThreefishEngine.xorRotr(xorRotr52, 37, j100);
                j15 = j100 - j16;
                j12 = xorRotr62;
                j13 = j99 - j14;
                iArr = iArr3;
                jArr4 = jArr6;
                jArr3 = jArr5;
                j9 = j105;
                j10 = xorRotr61;
                i = 1;
                j5 = j103;
                j2 = xorRotr57;
                i2 -= 2;
                j4 = xorRotr58;
                iArr2 = iArr4;
                j8 = xorRotr60;
                j11 = j106;
                j6 = xorRotr59;
                j7 = j104;
                j = j102;
            }
            long[] jArr7 = jArr3;
            long[] jArr8 = jArr4;
            long j107 = j - jArr7[0];
            long j108 = j2 - jArr7[1];
            long j109 = j3 - jArr7[2];
            long j110 = j4 - jArr7[3];
            long j111 = j5 - jArr7[4];
            long j112 = j6 - jArr7[5];
            long j113 = j7 - jArr7[6];
            long j114 = j8 - jArr7[7];
            long j115 = j9 - jArr7[8];
            long j116 = j10 - jArr7[9];
            long j117 = j11 - jArr7[10];
            long j118 = j12 - jArr7[11];
            long j119 = j13 - jArr7[12];
            long j120 = j14 - (jArr7[13] + jArr8[0]);
            long j121 = j15 - (jArr7[14] + jArr8[1]);
            long j122 = j16 - jArr7[15];
            jArr2[0] = j107;
            jArr2[1] = j108;
            jArr2[2] = j109;
            jArr2[3] = j110;
            jArr2[4] = j111;
            jArr2[5] = j112;
            jArr2[6] = j113;
            jArr2[7] = j114;
            jArr2[8] = j115;
            jArr2[9] = j116;
            jArr2[10] = j117;
            jArr2[11] = j118;
            jArr2[12] = j119;
            jArr2[13] = j120;
            jArr2[14] = j121;
            jArr2[15] = j122;
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        void encryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.kw;
            long[] jArr4 = this.t;
            int[] iArr = ThreefishEngine.MOD17;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 33) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            int i = 1;
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = jArr[8];
            long j10 = jArr[9];
            long j11 = jArr[10];
            long j12 = jArr[11];
            long j13 = jArr[12];
            long j14 = jArr[13];
            long j15 = jArr[14];
            long j16 = jArr[15];
            long j17 = j + jArr3[0];
            long j18 = j2 + jArr3[1];
            long j19 = j3 + jArr3[2];
            long j20 = j4 + jArr3[3];
            long j21 = j5 + jArr3[4];
            long j22 = j6 + jArr3[5];
            long j23 = j7 + jArr3[6];
            long j24 = j8 + jArr3[7];
            long j25 = j9 + jArr3[8];
            long j26 = j10 + jArr3[9];
            long j27 = j11 + jArr3[10];
            long j28 = j12 + jArr3[11];
            long j29 = j13 + jArr3[12];
            long j30 = j14 + jArr3[13] + jArr4[0];
            long j31 = j15 + jArr3[14] + jArr4[1];
            long j32 = j20;
            long j33 = j22;
            long j34 = j24;
            long j35 = j26;
            long j36 = j28;
            long j37 = j30;
            long j38 = j16 + jArr3[15];
            while (i < 20) {
                int i2 = iArr[i];
                int i3 = iArr2[i];
                long j39 = j17 + j18;
                long rotlXor = ThreefishEngine.rotlXor(j18, 24, j39);
                long j40 = j19 + j32;
                long rotlXor2 = ThreefishEngine.rotlXor(j32, 13, j40);
                long[] jArr5 = jArr3;
                int[] iArr3 = iArr;
                int[] iArr4 = iArr2;
                long j41 = j33;
                long j42 = j21 + j41;
                long rotlXor3 = ThreefishEngine.rotlXor(j41, 8, j42);
                int i4 = i;
                long j43 = j34;
                long j44 = j23 + j43;
                long rotlXor4 = ThreefishEngine.rotlXor(j43, 47, j44);
                long[] jArr6 = jArr4;
                long j45 = j35;
                long j46 = j25 + j45;
                long rotlXor5 = ThreefishEngine.rotlXor(j45, 8, j46);
                long j47 = j36;
                long j48 = j27 + j47;
                long rotlXor6 = ThreefishEngine.rotlXor(j47, 17, j48);
                long j49 = j37;
                long j50 = j29 + j49;
                long rotlXor7 = ThreefishEngine.rotlXor(j49, 22, j50);
                long j51 = j38;
                long j52 = j31 + j51;
                long rotlXor8 = ThreefishEngine.rotlXor(j51, 37, j52);
                long j53 = j39 + rotlXor5;
                long rotlXor9 = ThreefishEngine.rotlXor(rotlXor5, 38, j53);
                long j54 = j40 + rotlXor7;
                long rotlXor10 = ThreefishEngine.rotlXor(rotlXor7, 19, j54);
                long j55 = j44 + rotlXor6;
                long rotlXor11 = ThreefishEngine.rotlXor(rotlXor6, 10, j55);
                long j56 = j42 + rotlXor8;
                long rotlXor12 = ThreefishEngine.rotlXor(rotlXor8, 55, j56);
                long j57 = j48 + rotlXor4;
                long rotlXor13 = ThreefishEngine.rotlXor(rotlXor4, 49, j57);
                long j58 = j50 + rotlXor2;
                long rotlXor14 = ThreefishEngine.rotlXor(rotlXor2, 18, j58);
                long j59 = j52 + rotlXor3;
                long rotlXor15 = ThreefishEngine.rotlXor(rotlXor3, 23, j59);
                long j60 = j46 + rotlXor;
                long rotlXor16 = ThreefishEngine.rotlXor(rotlXor, 52, j60);
                long j61 = j53 + rotlXor13;
                long rotlXor17 = ThreefishEngine.rotlXor(rotlXor13, 33, j61);
                long j62 = j54 + rotlXor15;
                long rotlXor18 = ThreefishEngine.rotlXor(rotlXor15, 4, j62);
                long j63 = j56 + rotlXor14;
                long rotlXor19 = ThreefishEngine.rotlXor(rotlXor14, 51, j63);
                long j64 = j55 + rotlXor16;
                long rotlXor20 = ThreefishEngine.rotlXor(rotlXor16, 13, j64);
                long j65 = j58 + rotlXor12;
                long rotlXor21 = ThreefishEngine.rotlXor(rotlXor12, 34, j65);
                long j66 = j59 + rotlXor10;
                long rotlXor22 = ThreefishEngine.rotlXor(rotlXor10, 41, j66);
                long j67 = j60 + rotlXor11;
                long rotlXor23 = ThreefishEngine.rotlXor(rotlXor11, 59, j67);
                long j68 = j57 + rotlXor9;
                long rotlXor24 = ThreefishEngine.rotlXor(rotlXor9, 17, j68);
                long j69 = j61 + rotlXor21;
                long rotlXor25 = ThreefishEngine.rotlXor(rotlXor21, 5, j69);
                long j70 = j62 + rotlXor23;
                long rotlXor26 = ThreefishEngine.rotlXor(rotlXor23, 20, j70);
                long j71 = j64 + rotlXor22;
                long rotlXor27 = ThreefishEngine.rotlXor(rotlXor22, 48, j71);
                long j72 = j63 + rotlXor24;
                long rotlXor28 = ThreefishEngine.rotlXor(rotlXor24, 41, j72);
                long j73 = j66 + rotlXor20;
                long rotlXor29 = ThreefishEngine.rotlXor(rotlXor20, 47, j73);
                long j74 = j67 + rotlXor18;
                long rotlXor30 = ThreefishEngine.rotlXor(rotlXor18, 28, j74);
                long j75 = j68 + rotlXor19;
                long rotlXor31 = ThreefishEngine.rotlXor(rotlXor19, 16, j75);
                long j76 = j65 + rotlXor17;
                long rotlXor32 = ThreefishEngine.rotlXor(rotlXor17, 25, j76);
                long j77 = j69 + jArr5[i2];
                int i5 = i2 + 1;
                long j78 = rotlXor29 + jArr5[i5];
                int i6 = i2 + 2;
                long j79 = j70 + jArr5[i6];
                int i7 = i2 + 3;
                long j80 = rotlXor31 + jArr5[i7];
                int i8 = i2 + 4;
                long j81 = j72 + jArr5[i8];
                int i9 = i2 + 5;
                long j82 = rotlXor30 + jArr5[i9];
                int i10 = i2 + 6;
                long j83 = j71 + jArr5[i10];
                int i11 = i2 + 7;
                long j84 = rotlXor32 + jArr5[i11];
                int i12 = i2 + 8;
                long j85 = j74 + jArr5[i12];
                int i13 = i2 + 9;
                long j86 = rotlXor28 + jArr5[i13];
                int i14 = i2 + 10;
                long j87 = j75 + jArr5[i14];
                int i15 = i2 + 11;
                long j88 = rotlXor26 + jArr5[i15];
                int i16 = i2 + 12;
                long j89 = j76 + jArr5[i16];
                int i17 = i2 + 13;
                long j90 = rotlXor27 + jArr5[i17] + jArr6[i3];
                int i18 = i2 + 14;
                int i19 = i3 + 1;
                long j91 = j73 + jArr5[i18] + jArr6[i19];
                int i20 = i2 + 15;
                long j92 = jArr5[i20];
                long j93 = i4;
                long j94 = rotlXor25 + j92 + j93;
                long j95 = j77 + j78;
                long rotlXor33 = ThreefishEngine.rotlXor(j78, 41, j95);
                long j96 = j79 + j80;
                long rotlXor34 = ThreefishEngine.rotlXor(j80, 9, j96);
                long j97 = j81 + j82;
                long rotlXor35 = ThreefishEngine.rotlXor(j82, 37, j97);
                long j98 = j83 + j84;
                long rotlXor36 = ThreefishEngine.rotlXor(j84, 31, j98);
                long j99 = j85 + j86;
                long rotlXor37 = ThreefishEngine.rotlXor(j86, 12, j99);
                long j100 = j87 + j88;
                long rotlXor38 = ThreefishEngine.rotlXor(j88, 47, j100);
                long j101 = j89 + j90;
                long rotlXor39 = ThreefishEngine.rotlXor(j90, 44, j101);
                long j102 = j91 + j94;
                long rotlXor40 = ThreefishEngine.rotlXor(j94, 30, j102);
                long j103 = j95 + rotlXor37;
                long rotlXor41 = ThreefishEngine.rotlXor(rotlXor37, 16, j103);
                long j104 = j96 + rotlXor39;
                long rotlXor42 = ThreefishEngine.rotlXor(rotlXor39, 34, j104);
                long j105 = j98 + rotlXor38;
                long rotlXor43 = ThreefishEngine.rotlXor(rotlXor38, 56, j105);
                long j106 = j97 + rotlXor40;
                long rotlXor44 = ThreefishEngine.rotlXor(rotlXor40, 51, j106);
                long j107 = j100 + rotlXor36;
                long rotlXor45 = ThreefishEngine.rotlXor(rotlXor36, 4, j107);
                long j108 = j101 + rotlXor34;
                long rotlXor46 = ThreefishEngine.rotlXor(rotlXor34, 53, j108);
                long j109 = j102 + rotlXor35;
                long rotlXor47 = ThreefishEngine.rotlXor(rotlXor35, 42, j109);
                long j110 = j99 + rotlXor33;
                long rotlXor48 = ThreefishEngine.rotlXor(rotlXor33, 41, j110);
                long j111 = j103 + rotlXor45;
                long rotlXor49 = ThreefishEngine.rotlXor(rotlXor45, 31, j111);
                long j112 = j104 + rotlXor47;
                long rotlXor50 = ThreefishEngine.rotlXor(rotlXor47, 44, j112);
                long j113 = j106 + rotlXor46;
                long rotlXor51 = ThreefishEngine.rotlXor(rotlXor46, 47, j113);
                long j114 = j105 + rotlXor48;
                long rotlXor52 = ThreefishEngine.rotlXor(rotlXor48, 46, j114);
                long j115 = j108 + rotlXor44;
                long rotlXor53 = ThreefishEngine.rotlXor(rotlXor44, 19, j115);
                long j116 = j109 + rotlXor42;
                long rotlXor54 = ThreefishEngine.rotlXor(rotlXor42, 42, j116);
                long j117 = j110 + rotlXor43;
                long rotlXor55 = ThreefishEngine.rotlXor(rotlXor43, 44, j117);
                long j118 = j107 + rotlXor41;
                long rotlXor56 = ThreefishEngine.rotlXor(rotlXor41, 25, j118);
                long j119 = j111 + rotlXor53;
                long rotlXor57 = ThreefishEngine.rotlXor(rotlXor53, 9, j119);
                long j120 = j112 + rotlXor55;
                long rotlXor58 = ThreefishEngine.rotlXor(rotlXor55, 48, j120);
                long j121 = j114 + rotlXor54;
                long rotlXor59 = ThreefishEngine.rotlXor(rotlXor54, 35, j121);
                long j122 = j113 + rotlXor56;
                long rotlXor60 = ThreefishEngine.rotlXor(rotlXor56, 52, j122);
                long j123 = j116 + rotlXor52;
                long rotlXor61 = ThreefishEngine.rotlXor(rotlXor52, 23, j123);
                long j124 = j117 + rotlXor50;
                long rotlXor62 = ThreefishEngine.rotlXor(rotlXor50, 31, j124);
                long j125 = j118 + rotlXor51;
                long rotlXor63 = ThreefishEngine.rotlXor(rotlXor51, 37, j125);
                long j126 = j115 + rotlXor49;
                long rotlXor64 = ThreefishEngine.rotlXor(rotlXor49, 20, j126);
                long j127 = j119 + jArr5[i5];
                long j128 = rotlXor61 + jArr5[i6];
                long j129 = j120 + jArr5[i7];
                long j130 = rotlXor63 + jArr5[i8];
                long j131 = j122 + jArr5[i9];
                long j132 = rotlXor62 + jArr5[i10];
                long j133 = j121 + jArr5[i11];
                long j134 = rotlXor64 + jArr5[i12];
                long j135 = j124 + jArr5[i13];
                j35 = rotlXor60 + jArr5[i14];
                j27 = j125 + jArr5[i15];
                j36 = rotlXor58 + jArr5[i16];
                long j136 = j126 + jArr5[i17];
                j37 = rotlXor59 + jArr5[i18] + jArr6[i19];
                long j137 = j123 + jArr5[i20] + jArr6[i3 + 2];
                j38 = rotlXor57 + jArr5[i2 + 16] + j93 + 1;
                j34 = j134;
                j32 = j130;
                j33 = j132;
                j31 = j137;
                iArr2 = iArr4;
                j25 = j135;
                j23 = j133;
                j29 = j136;
                j18 = j128;
                iArr = iArr3;
                jArr4 = jArr6;
                jArr3 = jArr5;
                i = i4 + 2;
                j21 = j131;
                j17 = j127;
                j19 = j129;
            }
            jArr2[0] = j17;
            jArr2[1] = j18;
            jArr2[2] = j19;
            jArr2[3] = j32;
            jArr2[4] = j21;
            jArr2[5] = j33;
            jArr2[6] = j23;
            jArr2[7] = j34;
            jArr2[8] = j25;
            jArr2[9] = j35;
            jArr2[10] = j27;
            jArr2[11] = j36;
            jArr2[12] = j29;
            jArr2[13] = j37;
            jArr2[14] = j31;
            jArr2[15] = j38;
        }
    }

    /* loaded from: classes5.dex */
    private static final class Threefish256Cipher extends ThreefishCipher {
        private static final int ROTATION_0_0 = 14;
        private static final int ROTATION_0_1 = 16;
        private static final int ROTATION_1_0 = 52;
        private static final int ROTATION_1_1 = 57;
        private static final int ROTATION_2_0 = 23;
        private static final int ROTATION_2_1 = 40;
        private static final int ROTATION_3_0 = 5;
        private static final int ROTATION_3_1 = 37;
        private static final int ROTATION_4_0 = 25;
        private static final int ROTATION_4_1 = 33;
        private static final int ROTATION_5_0 = 46;
        private static final int ROTATION_5_1 = 12;
        private static final int ROTATION_6_0 = 58;
        private static final int ROTATION_6_1 = 22;
        private static final int ROTATION_7_0 = 32;
        private static final int ROTATION_7_1 = 32;

        public Threefish256Cipher(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        void decryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.kw;
            long[] jArr4 = this.t;
            int[] iArr = ThreefishEngine.MOD5;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 9) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            boolean z = false;
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            int i = 17;
            for (int i2 = 1; i >= i2; i2 = 1) {
                int i3 = iArr[i];
                int i4 = iArr2[i];
                int i5 = i3 + 1;
                long j5 = j - jArr3[i5];
                int i6 = i3 + 2;
                int i7 = i4 + 1;
                long j6 = j2 - (jArr3[i6] + jArr4[i7]);
                int i8 = i3 + 3;
                long j7 = j3 - (jArr3[i8] + jArr4[i4 + 2]);
                long j8 = i;
                long xorRotr = ThreefishEngine.xorRotr(j4 - ((jArr3[i3 + 4] + j8) + 1), 32, j5);
                long j9 = j5 - xorRotr;
                int[] iArr3 = iArr;
                long xorRotr2 = ThreefishEngine.xorRotr(j6, 32, j7);
                long j10 = j7 - xorRotr2;
                long xorRotr3 = ThreefishEngine.xorRotr(xorRotr2, 58, j9);
                long j11 = j9 - xorRotr3;
                long xorRotr4 = ThreefishEngine.xorRotr(xorRotr, 22, j10);
                long j12 = j10 - xorRotr4;
                long xorRotr5 = ThreefishEngine.xorRotr(xorRotr4, 46, j11);
                long j13 = j11 - xorRotr5;
                long xorRotr6 = ThreefishEngine.xorRotr(xorRotr3, 12, j12);
                long j14 = j12 - xorRotr6;
                long xorRotr7 = ThreefishEngine.xorRotr(xorRotr6, 25, j13);
                long xorRotr8 = ThreefishEngine.xorRotr(xorRotr5, 33, j14);
                long j15 = (j13 - xorRotr7) - jArr3[i3];
                long j16 = xorRotr7 - (jArr3[i5] + jArr4[i4]);
                long j17 = (j14 - xorRotr8) - (jArr3[i6] + jArr4[i7]);
                long xorRotr9 = ThreefishEngine.xorRotr(xorRotr8 - (jArr3[i8] + j8), 5, j15);
                long j18 = j15 - xorRotr9;
                long xorRotr10 = ThreefishEngine.xorRotr(j16, 37, j17);
                long j19 = j17 - xorRotr10;
                long xorRotr11 = ThreefishEngine.xorRotr(xorRotr10, 23, j18);
                long j20 = j18 - xorRotr11;
                long xorRotr12 = ThreefishEngine.xorRotr(xorRotr9, 40, j19);
                long j21 = j19 - xorRotr12;
                long xorRotr13 = ThreefishEngine.xorRotr(xorRotr12, 52, j20);
                long j22 = j20 - xorRotr13;
                long xorRotr14 = ThreefishEngine.xorRotr(xorRotr11, 57, j21);
                long j23 = j21 - xorRotr14;
                long xorRotr15 = ThreefishEngine.xorRotr(xorRotr14, 14, j22);
                j = j22 - xorRotr15;
                j4 = ThreefishEngine.xorRotr(xorRotr13, 16, j23);
                j3 = j23 - j4;
                i -= 2;
                j2 = xorRotr15;
                iArr = iArr3;
                iArr2 = iArr2;
                z = false;
            }
            boolean z2 = z;
            long j24 = j - jArr3[z2 ? 1 : 0];
            long j25 = j2 - (jArr3[1] + jArr4[z2 ? 1 : 0]);
            long j26 = j3 - (jArr3[2] + jArr4[1]);
            long j27 = j4 - jArr3[3];
            jArr2[z2 ? 1 : 0] = j24;
            jArr2[1] = j25;
            jArr2[2] = j26;
            jArr2[3] = j27;
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        void encryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.kw;
            long[] jArr4 = this.t;
            int[] iArr = ThreefishEngine.MOD5;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 9) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = j + jArr3[0];
            long j6 = j2 + jArr3[1] + jArr4[0];
            long j7 = j3 + jArr3[2] + jArr4[1];
            int i = 1;
            long j8 = j4 + jArr3[3];
            while (i < 18) {
                int i2 = iArr[i];
                int i3 = iArr2[i];
                long j9 = j5 + j6;
                long rotlXor = ThreefishEngine.rotlXor(j6, 14, j9);
                long j10 = j7 + j8;
                long rotlXor2 = ThreefishEngine.rotlXor(j8, 16, j10);
                long j11 = j9 + rotlXor2;
                long rotlXor3 = ThreefishEngine.rotlXor(rotlXor2, 52, j11);
                long j12 = j10 + rotlXor;
                long rotlXor4 = ThreefishEngine.rotlXor(rotlXor, 57, j12);
                long j13 = j11 + rotlXor4;
                long rotlXor5 = ThreefishEngine.rotlXor(rotlXor4, 23, j13);
                long j14 = j12 + rotlXor3;
                long rotlXor6 = ThreefishEngine.rotlXor(rotlXor3, 40, j14);
                long j15 = j13 + rotlXor6;
                long rotlXor7 = ThreefishEngine.rotlXor(rotlXor6, 5, j15);
                long j16 = j14 + rotlXor5;
                long rotlXor8 = ThreefishEngine.rotlXor(rotlXor5, 37, j16);
                long j17 = j15 + jArr3[i2];
                int i4 = i2 + 1;
                long j18 = rotlXor8 + jArr3[i4] + jArr4[i3];
                int i5 = i2 + 2;
                int i6 = i3 + 1;
                long j19 = j16 + jArr3[i5] + jArr4[i6];
                int i7 = i2 + 3;
                int[] iArr3 = iArr;
                long j20 = i;
                long j21 = rotlXor7 + jArr3[i7] + j20;
                long j22 = j17 + j18;
                long rotlXor9 = ThreefishEngine.rotlXor(j18, 25, j22);
                long j23 = j19 + j21;
                long rotlXor10 = ThreefishEngine.rotlXor(j21, 33, j23);
                long j24 = j22 + rotlXor10;
                long rotlXor11 = ThreefishEngine.rotlXor(rotlXor10, 46, j24);
                long j25 = j23 + rotlXor9;
                long rotlXor12 = ThreefishEngine.rotlXor(rotlXor9, 12, j25);
                long j26 = j24 + rotlXor12;
                long rotlXor13 = ThreefishEngine.rotlXor(rotlXor12, 58, j26);
                long j27 = j25 + rotlXor11;
                long rotlXor14 = ThreefishEngine.rotlXor(rotlXor11, 22, j27);
                long j28 = j26 + rotlXor14;
                long rotlXor15 = ThreefishEngine.rotlXor(rotlXor14, 32, j28);
                long j29 = j27 + rotlXor13;
                long rotlXor16 = ThreefishEngine.rotlXor(rotlXor13, 32, j29);
                j5 = j28 + jArr3[i4];
                j6 = rotlXor16 + jArr3[i5] + jArr4[i6];
                j7 = j29 + jArr3[i7] + jArr4[i3 + 2];
                j8 = rotlXor15 + jArr3[i2 + 4] + j20 + 1;
                i += 2;
                iArr = iArr3;
                iArr2 = iArr2;
            }
            jArr2[0] = j5;
            jArr2[1] = j6;
            jArr2[2] = j7;
            jArr2[3] = j8;
        }
    }

    /* loaded from: classes5.dex */
    private static final class Threefish512Cipher extends ThreefishCipher {
        private static final int ROTATION_0_0 = 46;
        private static final int ROTATION_0_1 = 36;
        private static final int ROTATION_0_2 = 19;
        private static final int ROTATION_0_3 = 37;
        private static final int ROTATION_1_0 = 33;
        private static final int ROTATION_1_1 = 27;
        private static final int ROTATION_1_2 = 14;
        private static final int ROTATION_1_3 = 42;
        private static final int ROTATION_2_0 = 17;
        private static final int ROTATION_2_1 = 49;
        private static final int ROTATION_2_2 = 36;
        private static final int ROTATION_2_3 = 39;
        private static final int ROTATION_3_0 = 44;
        private static final int ROTATION_3_1 = 9;
        private static final int ROTATION_3_2 = 54;
        private static final int ROTATION_3_3 = 56;
        private static final int ROTATION_4_0 = 39;
        private static final int ROTATION_4_1 = 30;
        private static final int ROTATION_4_2 = 34;
        private static final int ROTATION_4_3 = 24;
        private static final int ROTATION_5_0 = 13;
        private static final int ROTATION_5_1 = 50;
        private static final int ROTATION_5_2 = 10;
        private static final int ROTATION_5_3 = 17;
        private static final int ROTATION_6_0 = 25;
        private static final int ROTATION_6_1 = 29;
        private static final int ROTATION_6_2 = 39;
        private static final int ROTATION_6_3 = 43;
        private static final int ROTATION_7_0 = 8;
        private static final int ROTATION_7_1 = 35;
        private static final int ROTATION_7_2 = 56;
        private static final int ROTATION_7_3 = 22;

        protected Threefish512Cipher(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        public void decryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.kw;
            long[] jArr4 = this.t;
            int[] iArr = ThreefishEngine.MOD9;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 17) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            boolean z = false;
            long j = jArr[0];
            int i = 1;
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            int i2 = 17;
            while (i2 >= i) {
                int i3 = iArr[i2];
                int i4 = iArr2[i2];
                int i5 = i3 + 1;
                long j9 = j - jArr3[i5];
                int i6 = i3 + 2;
                long j10 = j2 - jArr3[i6];
                int i7 = i3 + 3;
                long j11 = j3 - jArr3[i7];
                int i8 = i3 + 4;
                long j12 = j4 - jArr3[i8];
                int i9 = i3 + 5;
                long j13 = j5 - jArr3[i9];
                int i10 = i3 + 6;
                int i11 = i4 + 1;
                long j14 = j6 - (jArr3[i10] + jArr4[i11]);
                int i12 = i3 + 7;
                int[] iArr3 = iArr;
                int[] iArr4 = iArr2;
                long j15 = j7 - (jArr3[i12] + jArr4[i4 + 2]);
                long[] jArr5 = jArr3;
                long j16 = i2;
                long j17 = j8 - ((jArr3[i3 + 8] + j16) + 1);
                int i13 = i2;
                long xorRotr = ThreefishEngine.xorRotr(j10, 8, j15);
                long j18 = j15 - xorRotr;
                long xorRotr2 = ThreefishEngine.xorRotr(j17, 35, j9);
                long j19 = j9 - xorRotr2;
                long xorRotr3 = ThreefishEngine.xorRotr(j14, 56, j11);
                long j20 = j11 - xorRotr3;
                long xorRotr4 = ThreefishEngine.xorRotr(j12, 22, j13);
                long j21 = j13 - xorRotr4;
                long xorRotr5 = ThreefishEngine.xorRotr(xorRotr, 25, j21);
                long j22 = j21 - xorRotr5;
                long xorRotr6 = ThreefishEngine.xorRotr(xorRotr4, 29, j18);
                long j23 = j18 - xorRotr6;
                long xorRotr7 = ThreefishEngine.xorRotr(xorRotr3, 39, j19);
                long j24 = j19 - xorRotr7;
                long xorRotr8 = ThreefishEngine.xorRotr(xorRotr2, 43, j20);
                long j25 = j20 - xorRotr8;
                long xorRotr9 = ThreefishEngine.xorRotr(xorRotr5, 13, j25);
                long j26 = j25 - xorRotr9;
                long xorRotr10 = ThreefishEngine.xorRotr(xorRotr8, 50, j22);
                long j27 = j22 - xorRotr10;
                long xorRotr11 = ThreefishEngine.xorRotr(xorRotr7, 10, j23);
                long j28 = j23 - xorRotr11;
                long xorRotr12 = ThreefishEngine.xorRotr(xorRotr6, 17, j24);
                long j29 = j24 - xorRotr12;
                long xorRotr13 = ThreefishEngine.xorRotr(xorRotr9, 39, j29);
                long xorRotr14 = ThreefishEngine.xorRotr(xorRotr12, 30, j26);
                long xorRotr15 = ThreefishEngine.xorRotr(xorRotr11, 34, j27);
                long j30 = j27 - xorRotr15;
                long xorRotr16 = ThreefishEngine.xorRotr(xorRotr10, 24, j28);
                long j31 = (j29 - xorRotr13) - jArr5[i3];
                long j32 = xorRotr13 - jArr5[i5];
                long j33 = (j26 - xorRotr14) - jArr5[i6];
                long j34 = xorRotr14 - jArr5[i7];
                long j35 = j30 - jArr5[i8];
                long j36 = xorRotr15 - (jArr5[i9] + jArr4[i4]);
                long j37 = (j28 - xorRotr16) - (jArr5[i10] + jArr4[i11]);
                long j38 = xorRotr16 - (jArr5[i12] + j16);
                long xorRotr17 = ThreefishEngine.xorRotr(j32, 44, j37);
                long j39 = j37 - xorRotr17;
                long xorRotr18 = ThreefishEngine.xorRotr(j38, 9, j31);
                long j40 = j31 - xorRotr18;
                long xorRotr19 = ThreefishEngine.xorRotr(j36, 54, j33);
                long j41 = j33 - xorRotr19;
                long xorRotr20 = ThreefishEngine.xorRotr(j34, 56, j35);
                long j42 = j35 - xorRotr20;
                long xorRotr21 = ThreefishEngine.xorRotr(xorRotr17, 17, j42);
                long j43 = j42 - xorRotr21;
                long xorRotr22 = ThreefishEngine.xorRotr(xorRotr20, 49, j39);
                long j44 = j39 - xorRotr22;
                long xorRotr23 = ThreefishEngine.xorRotr(xorRotr19, 36, j40);
                long j45 = j40 - xorRotr23;
                long xorRotr24 = ThreefishEngine.xorRotr(xorRotr18, 39, j41);
                long j46 = j41 - xorRotr24;
                long xorRotr25 = ThreefishEngine.xorRotr(xorRotr21, 33, j46);
                long j47 = j46 - xorRotr25;
                long xorRotr26 = ThreefishEngine.xorRotr(xorRotr24, 27, j43);
                long j48 = j43 - xorRotr26;
                long xorRotr27 = ThreefishEngine.xorRotr(xorRotr23, 14, j44);
                long j49 = j44 - xorRotr27;
                long[] jArr6 = jArr4;
                long xorRotr28 = ThreefishEngine.xorRotr(xorRotr22, 42, j45);
                long j50 = j45 - xorRotr28;
                long xorRotr29 = ThreefishEngine.xorRotr(xorRotr25, 46, j50);
                long j51 = j50 - xorRotr29;
                j4 = ThreefishEngine.xorRotr(xorRotr28, 36, j47);
                long xorRotr30 = ThreefishEngine.xorRotr(xorRotr27, 19, j48);
                j5 = j48 - xorRotr30;
                j8 = ThreefishEngine.xorRotr(xorRotr26, 37, j49);
                j7 = j49 - j8;
                j3 = j47 - j4;
                j2 = xorRotr29;
                j6 = xorRotr30;
                i2 = i13 - 2;
                iArr2 = iArr4;
                jArr3 = jArr5;
                z = false;
                i = 1;
                j = j51;
                jArr4 = jArr6;
                iArr = iArr3;
            }
            long[] jArr7 = jArr3;
            long[] jArr8 = jArr4;
            boolean z2 = z;
            long j52 = j - jArr7[z2 ? 1 : 0];
            long j53 = j2 - jArr7[1];
            long j54 = j3 - jArr7[2];
            long j55 = j4 - jArr7[3];
            long j56 = j5 - jArr7[4];
            long j57 = j6 - (jArr7[5] + jArr8[z2 ? 1 : 0]);
            long j58 = j7 - (jArr7[6] + jArr8[1]);
            long j59 = j8 - jArr7[7];
            jArr2[z2 ? 1 : 0] = j52;
            jArr2[1] = j53;
            jArr2[2] = j54;
            jArr2[3] = j55;
            jArr2[4] = j56;
            jArr2[5] = j57;
            jArr2[6] = j58;
            jArr2[7] = j59;
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        public void encryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.kw;
            long[] jArr4 = this.t;
            int[] iArr = ThreefishEngine.MOD9;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 17) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = j + jArr3[0];
            long j10 = j2 + jArr3[1];
            long j11 = j3 + jArr3[2];
            long j12 = j4 + jArr3[3];
            long j13 = j5 + jArr3[4];
            long j14 = j6 + jArr3[5] + jArr4[0];
            long j15 = j7 + jArr3[6] + jArr4[1];
            int i = 1;
            long j16 = j12;
            long j17 = j14;
            long j18 = j8 + jArr3[7];
            while (i < 18) {
                int i2 = iArr[i];
                int i3 = iArr2[i];
                long j19 = j9 + j10;
                long rotlXor = ThreefishEngine.rotlXor(j10, 46, j19);
                long j20 = j11 + j16;
                long rotlXor2 = ThreefishEngine.rotlXor(j16, 36, j20);
                long[] jArr5 = jArr3;
                int[] iArr3 = iArr;
                long j21 = j17;
                long j22 = j13 + j21;
                long rotlXor3 = ThreefishEngine.rotlXor(j21, 19, j22);
                int i4 = i;
                long j23 = j18;
                long j24 = j15 + j23;
                long rotlXor4 = ThreefishEngine.rotlXor(j23, 37, j24);
                long j25 = j20 + rotlXor;
                long rotlXor5 = ThreefishEngine.rotlXor(rotlXor, 33, j25);
                long j26 = j22 + rotlXor4;
                long rotlXor6 = ThreefishEngine.rotlXor(rotlXor4, 27, j26);
                long j27 = j24 + rotlXor3;
                long rotlXor7 = ThreefishEngine.rotlXor(rotlXor3, 14, j27);
                long j28 = j19 + rotlXor2;
                long rotlXor8 = ThreefishEngine.rotlXor(rotlXor2, 42, j28);
                long j29 = j26 + rotlXor5;
                long rotlXor9 = ThreefishEngine.rotlXor(rotlXor5, 17, j29);
                long j30 = j27 + rotlXor8;
                long rotlXor10 = ThreefishEngine.rotlXor(rotlXor8, 49, j30);
                long j31 = j28 + rotlXor7;
                long rotlXor11 = ThreefishEngine.rotlXor(rotlXor7, 36, j31);
                long j32 = j25 + rotlXor6;
                long rotlXor12 = ThreefishEngine.rotlXor(rotlXor6, 39, j32);
                long j33 = j30 + rotlXor9;
                long rotlXor13 = ThreefishEngine.rotlXor(rotlXor9, 44, j33);
                long j34 = j31 + rotlXor12;
                long rotlXor14 = ThreefishEngine.rotlXor(rotlXor12, 9, j34);
                long j35 = j32 + rotlXor11;
                long rotlXor15 = ThreefishEngine.rotlXor(rotlXor11, 54, j35);
                long j36 = j29 + rotlXor10;
                long rotlXor16 = ThreefishEngine.rotlXor(rotlXor10, 56, j36);
                long j37 = j34 + jArr5[i2];
                int i5 = i2 + 1;
                long j38 = rotlXor13 + jArr5[i5];
                int i6 = i2 + 2;
                long j39 = j35 + jArr5[i6];
                int i7 = i2 + 3;
                long j40 = rotlXor16 + jArr5[i7];
                int i8 = i2 + 4;
                long j41 = j36 + jArr5[i8];
                int i9 = i2 + 5;
                long j42 = rotlXor15 + jArr5[i9] + jArr4[i3];
                int i10 = i2 + 6;
                int i11 = i3 + 1;
                long j43 = j33 + jArr5[i10] + jArr4[i11];
                int i12 = i2 + 7;
                long j44 = i4;
                long j45 = rotlXor14 + jArr5[i12] + j44;
                long j46 = j37 + j38;
                long rotlXor17 = ThreefishEngine.rotlXor(j38, 39, j46);
                long j47 = j39 + j40;
                long rotlXor18 = ThreefishEngine.rotlXor(j40, 30, j47);
                long j48 = j41 + j42;
                long rotlXor19 = ThreefishEngine.rotlXor(j42, 34, j48);
                long j49 = j43 + j45;
                long rotlXor20 = ThreefishEngine.rotlXor(j45, 24, j49);
                long j50 = j47 + rotlXor17;
                long rotlXor21 = ThreefishEngine.rotlXor(rotlXor17, 13, j50);
                long j51 = j48 + rotlXor20;
                long rotlXor22 = ThreefishEngine.rotlXor(rotlXor20, 50, j51);
                long j52 = j49 + rotlXor19;
                long rotlXor23 = ThreefishEngine.rotlXor(rotlXor19, 10, j52);
                long j53 = j46 + rotlXor18;
                long rotlXor24 = ThreefishEngine.rotlXor(rotlXor18, 17, j53);
                long j54 = j51 + rotlXor21;
                long rotlXor25 = ThreefishEngine.rotlXor(rotlXor21, 25, j54);
                long j55 = j52 + rotlXor24;
                long rotlXor26 = ThreefishEngine.rotlXor(rotlXor24, 29, j55);
                long j56 = j53 + rotlXor23;
                long rotlXor27 = ThreefishEngine.rotlXor(rotlXor23, 39, j56);
                long j57 = j50 + rotlXor22;
                long rotlXor28 = ThreefishEngine.rotlXor(rotlXor22, 43, j57);
                long j58 = j55 + rotlXor25;
                long rotlXor29 = ThreefishEngine.rotlXor(rotlXor25, 8, j58);
                long j59 = j56 + rotlXor28;
                long rotlXor30 = ThreefishEngine.rotlXor(rotlXor28, 35, j59);
                long j60 = j57 + rotlXor27;
                long rotlXor31 = ThreefishEngine.rotlXor(rotlXor27, 56, j60);
                long j61 = j54 + rotlXor26;
                long rotlXor32 = ThreefishEngine.rotlXor(rotlXor26, 22, j61);
                long j62 = j59 + jArr5[i5];
                j10 = rotlXor29 + jArr5[i6];
                long j63 = j60 + jArr5[i7];
                long j64 = rotlXor32 + jArr5[i8];
                long j65 = j61 + jArr5[i9];
                long j66 = rotlXor31 + jArr5[i10] + jArr4[i11];
                j15 = j58 + jArr5[i12] + jArr4[i3 + 2];
                j18 = rotlXor30 + jArr5[i2 + 8] + j44 + 1;
                j13 = j65;
                j17 = j66;
                iArr = iArr3;
                iArr2 = iArr2;
                jArr3 = jArr5;
                i = i4 + 2;
                j16 = j64;
                j11 = j63;
                j9 = j62;
            }
            jArr2[0] = j9;
            jArr2[1] = j10;
            jArr2[2] = j11;
            jArr2[3] = j16;
            jArr2[4] = j13;
            jArr2[5] = j17;
            jArr2[6] = j15;
            jArr2[7] = j18;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static abstract class ThreefishCipher {
        protected final long[] kw;
        protected final long[] t;

        protected ThreefishCipher(long[] jArr, long[] jArr2) {
            this.kw = jArr;
            this.t = jArr2;
        }

        abstract void decryptBlock(long[] jArr, long[] jArr2);

        abstract void encryptBlock(long[] jArr, long[] jArr2);
    }

    static {
        int[] iArr = new int[80];
        MOD9 = iArr;
        MOD17 = new int[iArr.length];
        MOD5 = new int[iArr.length];
        MOD3 = new int[iArr.length];
        int i = 0;
        while (true) {
            int[] iArr2 = MOD9;
            if (i >= iArr2.length) {
                return;
            }
            MOD17[i] = i % 17;
            iArr2[i] = i % 9;
            MOD5[i] = i % 5;
            MOD3[i] = i % 3;
            i++;
        }
    }

    public ThreefishEngine(int i) {
        ThreefishCipher threefish256Cipher;
        long[] jArr = new long[5];
        this.t = jArr;
        int i2 = i / 8;
        this.blocksizeBytes = i2;
        int i3 = i2 / 8;
        this.blocksizeWords = i3;
        this.currentBlock = new long[i3];
        long[] jArr2 = new long[(i3 * 2) + 1];
        this.kw = jArr2;
        if (i == 256) {
            threefish256Cipher = new Threefish256Cipher(jArr2, jArr);
        } else if (i == 512) {
            threefish256Cipher = new Threefish512Cipher(jArr2, jArr);
        } else {
            if (i != 1024) {
                throw new IllegalArgumentException("Invalid blocksize - Threefish is defined with block size of 256, 512, or 1024 bits");
            }
            threefish256Cipher = new Threefish1024Cipher(jArr2, jArr);
        }
        this.cipher = threefish256Cipher;
    }

    public static long bytesToWord(byte[] bArr, int i) {
        if (i + 8 > bArr.length) {
            throw new IllegalArgumentException();
        }
        long j = bArr[i] & 255;
        int i2 = i + 1 + 1 + 1;
        long j2 = j | ((bArr[r0] & 255) << 8) | ((bArr[r8] & 255) << 16);
        long j3 = j2 | ((bArr[i2] & 255) << 24);
        long j4 = j3 | ((bArr[r8] & 255) << 32);
        long j5 = j4 | ((bArr[r2] & 255) << 40);
        int i3 = i2 + 1 + 1 + 1 + 1;
        return ((bArr[i3] & 255) << 56) | j5 | ((bArr[r8] & 255) << 48);
    }

    static long rotlXor(long j, int i, long j2) {
        return ((j >>> (-i)) | (j << i)) ^ j2;
    }

    private void setKey(long[] jArr) {
        if (jArr.length != this.blocksizeWords) {
            throw new IllegalArgumentException("Threefish key must be same size as block (" + this.blocksizeWords + " words)");
        }
        long j = C_240;
        int i = 0;
        while (true) {
            int i2 = this.blocksizeWords;
            if (i >= i2) {
                long[] jArr2 = this.kw;
                jArr2[i2] = j;
                System.arraycopy(jArr2, 0, jArr2, i2 + 1, i2);
                return;
            } else {
                long[] jArr3 = this.kw;
                jArr3[i] = jArr[i];
                j ^= jArr3[i];
                i++;
            }
        }
    }

    private void setTweak(long[] jArr) {
        if (jArr.length != 2) {
            throw new IllegalArgumentException("Tweak must be 2 words.");
        }
        long[] jArr2 = this.t;
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
        jArr2[2] = jArr2[0] ^ jArr2[1];
        jArr2[3] = jArr2[0];
        jArr2[4] = jArr2[1];
    }

    public static void wordToBytes(long j, byte[] bArr, int i) {
        if (i + 8 > bArr.length) {
            throw new IllegalArgumentException();
        }
        int i2 = i + 1;
        bArr[i] = (byte) j;
        int i3 = i2 + 1;
        bArr[i2] = (byte) (j >> 8);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (j >> 16);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (j >> 24);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (j >> 32);
        int i7 = i6 + 1;
        bArr[i6] = (byte) (j >> 40);
        bArr[i7] = (byte) (j >> 48);
        bArr[i7 + 1] = (byte) (j >> 56);
    }

    static long xorRotr(long j, int i, long j2) {
        long j3 = j ^ j2;
        return (j3 << (-i)) | (j3 >>> i);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Threefish-" + (this.blocksizeBytes * 8);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.blocksizeBytes;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] key;
        byte[] bArr;
        long[] jArr;
        long[] jArr2 = null;
        if (cipherParameters instanceof TweakableBlockCipherParameters) {
            TweakableBlockCipherParameters tweakableBlockCipherParameters = (TweakableBlockCipherParameters) cipherParameters;
            key = tweakableBlockCipherParameters.getKey().getKey();
            bArr = tweakableBlockCipherParameters.getTweak();
        } else {
            if (!(cipherParameters instanceof KeyParameter)) {
                throw new IllegalArgumentException("Invalid parameter passed to Threefish init - " + cipherParameters.getClass().getName());
            }
            key = ((KeyParameter) cipherParameters).getKey();
            bArr = null;
        }
        if (key == null) {
            jArr = null;
        } else {
            if (key.length != this.blocksizeBytes) {
                throw new IllegalArgumentException("Threefish key must be same size as block (" + this.blocksizeBytes + " bytes)");
            }
            int i = this.blocksizeWords;
            jArr = new long[i];
            for (int i2 = 0; i2 < i; i2++) {
                jArr[i2] = bytesToWord(key, i2 * 8);
            }
        }
        if (bArr != null) {
            if (bArr.length != 16) {
                throw new IllegalArgumentException("Threefish tweak must be 16 bytes");
            }
            jArr2 = new long[]{bytesToWord(bArr, 0), bytesToWord(bArr, 8)};
        }
        init(z, jArr, jArr2);
    }

    public void init(boolean z, long[] jArr, long[] jArr2) {
        this.forEncryption = z;
        if (jArr != null) {
            setKey(jArr);
        }
        if (jArr2 != null) {
            setTweak(jArr2);
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        int i3 = this.blocksizeBytes;
        if (i + i3 > bArr.length) {
            throw new DataLengthException("Input buffer too short");
        }
        if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("Output buffer too short");
        }
        int i4 = 0;
        for (int i5 = 0; i5 < this.blocksizeBytes; i5 += 8) {
            this.currentBlock[i5 >> 3] = bytesToWord(bArr, i + i5);
        }
        long[] jArr = this.currentBlock;
        processBlock(jArr, jArr);
        while (true) {
            int i6 = this.blocksizeBytes;
            if (i4 >= i6) {
                return i6;
            }
            wordToBytes(this.currentBlock[i4 >> 3], bArr2, i2 + i4);
            i4 += 8;
        }
    }

    public int processBlock(long[] jArr, long[] jArr2) throws DataLengthException, IllegalStateException {
        long[] jArr3 = this.kw;
        int i = this.blocksizeWords;
        if (jArr3[i] == 0) {
            throw new IllegalStateException("Threefish engine not initialised");
        }
        if (jArr.length != i) {
            throw new DataLengthException("Input buffer too short");
        }
        if (jArr2.length != i) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (this.forEncryption) {
            this.cipher.encryptBlock(jArr, jArr2);
        } else {
            this.cipher.decryptBlock(jArr, jArr2);
        }
        return this.blocksizeWords;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
