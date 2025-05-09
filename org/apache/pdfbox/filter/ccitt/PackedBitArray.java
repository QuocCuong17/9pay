package org.apache.pdfbox.filter.ccitt;

/* loaded from: classes5.dex */
final class PackedBitArray {
    private int bitCount;
    private byte[] data;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PackedBitArray(int i) {
        this.bitCount = i;
        this.data = new byte[(i + 7) / 8];
    }

    private int byteOffset(int i) {
        return i / 8;
    }

    private int bitOffset(int i) {
        return i % 8;
    }

    public void set(int i) {
        int byteOffset = byteOffset(i);
        byte[] bArr = this.data;
        bArr[byteOffset] = (byte) ((1 << bitOffset(i)) | bArr[byteOffset]);
    }

    public void clear(int i) {
        int byteOffset = byteOffset(i);
        int bitOffset = bitOffset(i);
        byte[] bArr = this.data;
        bArr[byteOffset] = (byte) ((~(1 << bitOffset)) & bArr[byteOffset]);
    }

    public void setBits(int i, int i2, int i3) {
        if (i3 == 0) {
            clearBits(i, i2);
        } else {
            setBits(i, i2);
        }
    }

    public void setBits(int i, int i2) {
        if (i2 == 0) {
            return;
        }
        int bitOffset = bitOffset(i);
        int byteOffset = byteOffset(i);
        int i3 = i + i2;
        if (i3 > getBitCount()) {
            throw new IndexOutOfBoundsException("offset + length > bit count");
        }
        int byteOffset2 = byteOffset(i3);
        int bitOffset2 = bitOffset(i3);
        if (byteOffset == byteOffset2) {
            byte[] bArr = this.data;
            bArr[byteOffset] = (byte) (((1 << bitOffset2) - (1 << bitOffset)) | bArr[byteOffset]);
            return;
        }
        byte[] bArr2 = this.data;
        bArr2[byteOffset] = (byte) ((255 << bitOffset) | bArr2[byteOffset]);
        for (int i4 = byteOffset + 1; i4 < byteOffset2; i4++) {
            this.data[i4] = -1;
        }
        if (bitOffset2 > 0) {
            byte[] bArr3 = this.data;
            bArr3[byteOffset2] = (byte) ((255 >> (8 - bitOffset2)) | bArr3[byteOffset2]);
        }
    }

    public void clearBits(int i, int i2) {
        if (i2 == 0) {
            return;
        }
        int i3 = i % 8;
        int byteOffset = byteOffset(i);
        int i4 = i + i2;
        int byteOffset2 = byteOffset(i4);
        int i5 = i4 % 8;
        if (byteOffset == byteOffset2) {
            int i6 = (1 << i5) - (1 << i3);
            byte[] bArr = this.data;
            bArr[byteOffset] = (byte) ((~i6) & bArr[byteOffset]);
            return;
        }
        byte[] bArr2 = this.data;
        bArr2[byteOffset] = (byte) ((~(255 << i3)) & bArr2[byteOffset]);
        for (int i7 = byteOffset + 1; i7 < byteOffset2; i7++) {
            this.data[i7] = 0;
        }
        if (i5 > 0) {
            byte[] bArr3 = this.data;
            bArr3[byteOffset2] = (byte) ((~(255 >> (8 - i5))) & bArr3[byteOffset2]);
        }
    }

    public void clear() {
        clearBits(0, getBitCount());
    }

    public int getBitCount() {
        return this.bitCount;
    }

    public int getByteCount() {
        return this.data.length;
    }

    public byte[] getData() {
        return this.data;
    }

    public String toString() {
        return toBitString(this.data).substring(0, this.bitCount);
    }

    public static String toBitString(byte b) {
        return toBitString(new byte[]{b});
    }

    public static String toBitString(byte[] bArr) {
        return toBitString(bArr, 0, bArr.length);
    }

    public static String toBitString(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        int i3 = i2 + i;
        while (i < i3) {
            for (int i4 = 0; i4 < 8; i4++) {
                stringBuffer.append(((1 << i4) & bArr[i]) != 0 ? '1' : '0');
            }
            i++;
        }
        return stringBuffer.toString();
    }
}
