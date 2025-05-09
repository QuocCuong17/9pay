package com.github.jaiimageio.impl.plugins.tiff;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.github.jaiimageio.plugins.tiff.TIFFDecompressor;
import java.io.IOException;
import javax.imageio.IIOException;

/* loaded from: classes3.dex */
public class TIFFLZWDecompressor extends TIFFDecompressor {
    private static final boolean DEBUG = false;
    private static final int[] andTable = {511, AnalyticsListener.EVENT_DRM_KEYS_LOADED, 2047, 4095};
    byte[] dstData;
    int dstIndex;
    int predictor;
    byte[] srcData;
    int srcIndex;
    byte[][] stringTable;
    int tableIndex;
    int bitsToGet = 9;
    int nextData = 0;
    int nextBits = 0;

    public TIFFLZWDecompressor(int i) throws IIOException {
        if (i != 1 && i != 2) {
            throw new IIOException("Illegal value for Predictor in TIFF file");
        }
        this.predictor = i;
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFDecompressor
    public void decodeRaw(byte[] bArr, int i, int i2, int i3) throws IOException {
        byte[] bArr2;
        int i4;
        if (this.predictor == 2) {
            int length = this.bitsPerSample.length;
            for (int i5 = 0; i5 < length; i5++) {
                if (this.bitsPerSample[i5] != 8) {
                    throw new IIOException(this.bitsPerSample[i5] + "-bit samples are not supported for Horizontal differencing Predictor");
                }
            }
        }
        this.stream.seek(this.offset);
        byte[] bArr3 = new byte[this.byteCount];
        this.stream.readFully(bArr3);
        int i6 = ((this.srcWidth * i2) + 7) / 8;
        if (i6 == i3) {
            bArr2 = bArr;
            i4 = i;
        } else {
            bArr2 = new byte[this.srcHeight * i6];
            i4 = 0;
        }
        decode(bArr3, 0, bArr2, i4);
        if (i6 != i3) {
            int i7 = 0;
            for (int i8 = 0; i8 < this.srcHeight; i8++) {
                System.arraycopy(bArr2, i7, bArr, i, i6);
                i7 += i6;
                i += i3;
            }
        }
    }

    public int decode(byte[] bArr, int i, byte[] bArr2, int i2) throws IOException {
        if (bArr[0] == 0 && bArr[1] == 1) {
            throw new IIOException("TIFF 5.0-style LZW compression is not supported!");
        }
        this.srcData = bArr;
        this.dstData = bArr2;
        this.srcIndex = i;
        this.dstIndex = i2;
        this.nextData = 0;
        this.nextBits = 0;
        initializeStringTable();
        int i3 = 0;
        while (true) {
            int nextCode = getNextCode();
            if (nextCode == 257) {
                break;
            }
            if (nextCode == 256) {
                initializeStringTable();
                i3 = getNextCode();
                if (i3 == 257) {
                    break;
                }
                writeString(this.stringTable[i3]);
            } else {
                if (nextCode < this.tableIndex) {
                    byte[] bArr3 = this.stringTable[nextCode];
                    writeString(bArr3);
                    addStringToTable(this.stringTable[i3], bArr3[0]);
                } else {
                    byte[] bArr4 = this.stringTable[i3];
                    byte[] composeString = composeString(bArr4, bArr4[0]);
                    writeString(composeString);
                    addStringToTable(composeString);
                }
                i3 = nextCode;
            }
        }
        if (this.predictor == 2) {
            for (int i4 = 0; i4 < this.srcHeight; i4++) {
                int i5 = (this.samplesPerPixel * ((this.srcWidth * i4) + 1)) + i2;
                for (int i6 = this.samplesPerPixel; i6 < this.srcWidth * this.samplesPerPixel; i6++) {
                    byte[] bArr5 = this.dstData;
                    bArr5[i5] = (byte) (bArr5[i5] + bArr5[i5 - this.samplesPerPixel]);
                    i5++;
                }
            }
        }
        return this.dstIndex - i2;
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

    public void writeString(byte[] bArr) {
        int i = this.dstIndex;
        byte[] bArr2 = this.dstData;
        if (i < bArr2.length) {
            int min = Math.min(bArr.length, bArr2.length - i);
            for (int i2 = 0; i2 < min; i2++) {
                byte[] bArr3 = this.dstData;
                int i3 = this.dstIndex;
                this.dstIndex = i3 + 1;
                bArr3[i3] = bArr[i2];
            }
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
