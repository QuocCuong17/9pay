package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;
import org.bouncycastle.crypto.tls.CipherSuite;

/* loaded from: classes4.dex */
public final class Version {
    private static final Version[] VERSIONS = buildVersions();
    private final int dataRegionSizeColumns;
    private final int dataRegionSizeRows;
    private final ECBlocks ecBlocks;
    private final int symbolSizeColumns;
    private final int symbolSizeRows;
    private final int totalCodewords;
    private final int versionNumber;

    private Version(int i, int i2, int i3, int i4, int i5, ECBlocks eCBlocks) {
        this.versionNumber = i;
        this.symbolSizeRows = i2;
        this.symbolSizeColumns = i3;
        this.dataRegionSizeRows = i4;
        this.dataRegionSizeColumns = i5;
        this.ecBlocks = eCBlocks;
        int eCCodewords = eCBlocks.getECCodewords();
        int i6 = 0;
        for (ECB ecb : eCBlocks.getECBlocks()) {
            i6 += ecb.getCount() * (ecb.getDataCodewords() + eCCodewords);
        }
        this.totalCodewords = i6;
    }

    public int getVersionNumber() {
        return this.versionNumber;
    }

    public int getSymbolSizeRows() {
        return this.symbolSizeRows;
    }

    public int getSymbolSizeColumns() {
        return this.symbolSizeColumns;
    }

    public int getDataRegionSizeRows() {
        return this.dataRegionSizeRows;
    }

    public int getDataRegionSizeColumns() {
        return this.dataRegionSizeColumns;
    }

    public int getTotalCodewords() {
        return this.totalCodewords;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ECBlocks getECBlocks() {
        return this.ecBlocks;
    }

    public static Version getVersionForDimensions(int i, int i2) throws FormatException {
        if ((i & 1) != 0 || (i2 & 1) != 0) {
            throw FormatException.getFormatInstance();
        }
        for (Version version : VERSIONS) {
            if (version.symbolSizeRows == i && version.symbolSizeColumns == i2) {
                return version;
            }
        }
        throw FormatException.getFormatInstance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static final class ECBlocks {
        private final ECB[] ecBlocks;
        private final int ecCodewords;

        private ECBlocks(int i, ECB ecb) {
            this.ecCodewords = i;
            this.ecBlocks = new ECB[]{ecb};
        }

        private ECBlocks(int i, ECB ecb, ECB ecb2) {
            this.ecCodewords = i;
            this.ecBlocks = new ECB[]{ecb, ecb2};
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int getECCodewords() {
            return this.ecCodewords;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ECB[] getECBlocks() {
            return this.ecBlocks;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static final class ECB {
        private final int count;
        private final int dataCodewords;

        private ECB(int i, int i2) {
            this.count = i;
            this.dataCodewords = i2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int getCount() {
            return this.count;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int getDataCodewords() {
            return this.dataCodewords;
        }
    }

    public String toString() {
        return String.valueOf(this.versionNumber);
    }

    private static Version[] buildVersions() {
        int i = 48;
        int i2 = 1;
        int i3 = 5;
        int i4 = 8;
        Version version = new Version(3, 14, 14, 12, 12, new ECBlocks(10, new ECB(i2, i4)));
        int i5 = 2;
        int i6 = 12;
        int i7 = 18;
        Version version2 = new Version(5, 18, 18, 16, 16, new ECBlocks(14, new ECB(i2, i7)));
        int i8 = 4;
        int i9 = 36;
        ECB ecb = new ECB(i2, 44);
        int i10 = 62;
        ECB ecb2 = new ECB(i2, 86);
        int i11 = 42;
        ECB ecb3 = new ECB(i2, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA);
        int i12 = 56;
        ECB ecb4 = new ECB(i2, CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256);
        Version version3 = new Version(15, 52, 52, 24, 24, new ECBlocks(i11, new ECB(i5, 102)));
        ECB ecb5 = new ECB(6, CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384);
        Version version4 = new Version(23, CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, 20, 20, new ECBlocks(i10, new ECB(i4, CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384)));
        int i13 = 22;
        ECB ecb6 = new ECB(i2, 5);
        ECB ecb7 = new ECB(i2, 10);
        int i14 = 32;
        int i15 = 38;
        return new Version[]{new Version(1, 10, 10, 8, 8, new ECBlocks(i3, new ECB(i2, 3))), new Version(2, 12, 12, 10, 10, new ECBlocks(7, new ECB(i2, i3))), version, new Version(4, 16, 16, 14, 14, new ECBlocks(i6, new ECB(i2, i6))), version2, new Version(6, 20, 20, 18, 18, new ECBlocks(i7, new ECB(i2, 22))), new Version(7, 22, 22, 20, 20, new ECBlocks(20, new ECB(i2, 30))), new Version(8, 24, 24, 22, 22, new ECBlocks(24, new ECB(i2, i9))), new Version(9, 26, 26, 24, 24, new ECBlocks(28, ecb)), new Version(10, 32, 32, 14, 14, new ECBlocks(i9, new ECB(i2, i10))), new Version(11, 36, 36, 16, 16, new ECBlocks(i11, ecb2)), new Version(12, 40, 40, 18, 18, new ECBlocks(i, new ECB(i2, 114))), new Version(13, 44, 44, 20, 20, new ECBlocks(i12, ecb3)), new Version(14, 48, 48, 22, 22, new ECBlocks(68, ecb4)), version3, new Version(16, 64, 64, 14, 14, new ECBlocks(i12, new ECB(i5, CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA))), new Version(17, 72, 72, 16, 16, new ECBlocks(i9, new ECB(i8, 92))), new Version(18, 80, 80, 18, 18, new ECBlocks(i, new ECB(i8, 114))), new Version(19, 88, 88, 20, 20, new ECBlocks(i12, new ECB(i8, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA))), new Version(20, 96, 96, 22, 22, new ECBlocks(68, new ECB(i8, CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256))), new Version(21, 104, 104, 24, 24, new ECBlocks(i12, new ECB(6, 136))), new Version(22, 120, 120, 18, 18, new ECBlocks(68, ecb5)), version4, new Version(24, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, 22, 22, new ECBlocks(i10, new ECB(i4, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256), new ECB(i5, CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA))), new Version(25, 8, 18, 6, 16, new ECBlocks(7, ecb6)), new Version(26, 8, 32, 6, 14, new ECBlocks(11, ecb7)), new Version(27, 12, 26, 10, 24, new ECBlocks(14, new ECB(i2, 16))), new Version(28, 12, 36, 10, 16, new ECBlocks(i7, new ECB(i2, i13))), new Version(29, 16, 36, 14, 16, new ECBlocks(24, new ECB(i2, i14))), new Version(30, 16, 48, 14, 22, new ECBlocks(28, new ECB(i2, 49))), new Version(31, 8, 48, 6, 22, new ECBlocks(15, new ECB(i2, i7))), new Version(32, 8, 64, 6, 14, new ECBlocks(i7, new ECB(i2, 24))), new Version(33, 8, 80, 6, 18, new ECBlocks(i13, new ECB(i2, i14))), new Version(34, 8, 96, 6, 22, new ECBlocks(28, new ECB(i2, i15))), new Version(35, 8, 120, 6, 18, new ECBlocks(i14, new ECB(i2, 49))), new Version(36, 8, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, 6, 22, new ECBlocks(i9, new ECB(i2, 63))), new Version(37, 12, 64, 10, 14, new ECBlocks(27, new ECB(i2, 43))), new Version(38, 12, 88, 10, 20, new ECBlocks(i9, new ECB(i2, 64))), new Version(39, 16, 64, 14, 14, new ECBlocks(i9, new ECB(i2, i10))), new Version(40, 20, 36, 18, 16, new ECBlocks(28, new ECB(i2, 44))), new Version(41, 20, 44, 18, 20, new ECBlocks(34, new ECB(i2, i12))), new Version(42, 20, 64, 18, 14, new ECBlocks(42, new ECB(i2, 84))), new Version(43, 22, 48, 20, 22, new ECBlocks(i15, new ECB(i2, 72))), new Version(44, 24, 48, 22, 22, new ECBlocks(41, new ECB(i2, 80))), new Version(45, 24, 64, 22, 14, new ECBlocks(46, new ECB(i2, 108))), new Version(46, 26, 40, 24, 18, new ECBlocks(i15, new ECB(i2, 70))), new Version(47, 26, 48, 24, 22, new ECBlocks(42, new ECB(i2, 90))), new Version(48, 26, 64, 24, 14, new ECBlocks(50, new ECB(i2, 118)))};
    }
}
