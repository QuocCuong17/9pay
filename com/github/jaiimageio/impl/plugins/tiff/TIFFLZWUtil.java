package com.github.jaiimageio.impl.plugins.tiff;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import java.io.IOException;
import javax.imageio.IIOException;

/* loaded from: classes3.dex */
public class TIFFLZWUtil {
    private static final int[] andTable = {511, AnalyticsListener.EVENT_DRM_KEYS_LOADED, 2047, 4095};
    private static final boolean debug = false;
    byte[] dstData;
    int predictor;
    int samplesPerPixel;
    byte[] srcData;
    int srcIndex;
    byte[][] stringTable;
    int tableIndex;
    int dstIndex = 0;
    int bitsToGet = 9;
    int nextData = 0;
    int nextBits = 0;

    public byte[] decode(byte[] bArr, int i, int i2, int i3, int i4) throws IOException {
        if (bArr[0] == 0 && bArr[1] == 1) {
            throw new IIOException("TIFF 5.0-style LZW compression is not supported!");
        }
        this.srcData = bArr;
        this.srcIndex = 0;
        this.nextData = 0;
        this.nextBits = 0;
        this.dstData = new byte[8192];
        this.dstIndex = 0;
        initializeStringTable();
        int i5 = 0;
        while (true) {
            int nextCode = getNextCode();
            if (nextCode == 257) {
                break;
            }
            if (nextCode == 256) {
                initializeStringTable();
                i5 = getNextCode();
                if (i5 == 257) {
                    break;
                }
                writeString(this.stringTable[i5]);
            } else {
                if (nextCode < this.tableIndex) {
                    byte[] bArr2 = this.stringTable[nextCode];
                    writeString(bArr2);
                    addStringToTable(this.stringTable[i5], bArr2[0]);
                } else {
                    byte[] bArr3 = this.stringTable[i5];
                    byte[] composeString = composeString(bArr3, bArr3[0]);
                    writeString(composeString);
                    addStringToTable(composeString);
                }
                i5 = nextCode;
            }
        }
        if (i == 2) {
            for (int i6 = 0; i6 < i4; i6++) {
                int i7 = ((i6 * i3) + 1) * i2;
                for (int i8 = i2; i8 < i3 * i2; i8++) {
                    byte[] bArr4 = this.dstData;
                    bArr4[i7] = (byte) (bArr4[i7] + bArr4[i7 - i2]);
                    i7++;
                }
            }
        }
        int i9 = this.dstIndex;
        byte[] bArr5 = new byte[i9];
        System.arraycopy(this.dstData, 0, bArr5, 0, i9);
        return bArr5;
    }

    public void initializeStringTable() {
        this.stringTable = new byte[4096];
        for (int i = 0; i < 256; i++) {
            byte[][] bArr = this.stringTable;
            bArr[i] = new byte[1];
            bArr[i][0] = (byte) i;
        }
        this.tableIndex = 258;
        this.bitsToGet = 9;
    }

    private void ensureCapacity(int i) {
        int i2 = this.dstIndex;
        if (i2 + i > this.dstData.length) {
            byte[] bArr = new byte[Math.max((int) (r2.length * 1.2f), i2 + i)];
            byte[] bArr2 = this.dstData;
            System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
            this.dstData = bArr;
        }
    }

    public void writeString(byte[] bArr) {
        ensureCapacity(bArr.length);
        for (byte b : bArr) {
            byte[] bArr2 = this.dstData;
            int i = this.dstIndex;
            this.dstIndex = i + 1;
            bArr2[i] = b;
        }
    }

    public void addStringToTable(byte[] bArr, byte b) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length + 1];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        bArr2[length] = b;
        byte[][] bArr3 = this.stringTable;
        int i = this.tableIndex;
        int i2 = i + 1;
        this.tableIndex = i2;
        bArr3[i] = bArr2;
        if (i2 == 511) {
            this.bitsToGet = 10;
        } else if (i2 == 1023) {
            this.bitsToGet = 11;
        } else if (i2 == 2047) {
            this.bitsToGet = 12;
        }
    }

    public void addStringToTable(byte[] bArr) {
        byte[][] bArr2 = this.stringTable;
        int i = this.tableIndex;
        int i2 = i + 1;
        this.tableIndex = i2;
        bArr2[i] = bArr;
        if (i2 == 511) {
            this.bitsToGet = 10;
        } else if (i2 == 1023) {
            this.bitsToGet = 11;
        } else if (i2 == 2047) {
            this.bitsToGet = 12;
        }
    }

    public byte[] composeString(byte[] bArr, byte b) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length + 1];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        bArr2[length] = b;
        return bArr2;
    }

    public int getNextCode() {
        try {
            int i = this.nextData << 8;
            byte[] bArr = this.srcData;
            int i2 = this.srcIndex;
            int i3 = i2 + 1;
            this.srcIndex = i3;
            int i4 = i | (bArr[i2] & 255);
            this.nextData = i4;
            int i5 = this.nextBits + 8;
            this.nextBits = i5;
            int i6 = this.bitsToGet;
            if (i5 < i6) {
                this.srcIndex = i3 + 1;
                this.nextData = (i4 << 8) | (bArr[i3] & 255);
                this.nextBits = i5 + 8;
            }
            int i7 = this.nextData;
            int i8 = this.nextBits;
            int i9 = (i7 >> (i8 - i6)) & andTable[i6 - 9];
            this.nextBits = i8 - i6;
            return i9;
        } catch (ArrayIndexOutOfBoundsException unused) {
            return 257;
        }
    }
}
