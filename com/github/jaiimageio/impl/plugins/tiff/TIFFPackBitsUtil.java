package com.github.jaiimageio.impl.plugins.tiff;

import java.io.IOException;

/* loaded from: classes3.dex */
public class TIFFPackBitsUtil {
    private static final boolean debug = false;
    byte[] dstData = new byte[8192];
    int dstIndex = 0;

    private void ensureCapacity(int i) {
        int i2 = this.dstIndex;
        if (i2 + i > this.dstData.length) {
            byte[] bArr = new byte[Math.max((int) (r2.length * 1.2f), i2 + i)];
            byte[] bArr2 = this.dstData;
            System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
            this.dstData = bArr;
        }
    }

    public byte[] decode(byte[] bArr) throws IOException {
        int i = 0;
        while (i < bArr.length) {
            int i2 = i + 1;
            byte b = bArr[i];
            if (b >= 0 && b <= Byte.MAX_VALUE) {
                int i3 = b + 1;
                ensureCapacity(i3);
                int i4 = 0;
                while (i4 < i3) {
                    byte[] bArr2 = this.dstData;
                    int i5 = this.dstIndex;
                    this.dstIndex = i5 + 1;
                    bArr2[i5] = bArr[i2];
                    i4++;
                    i2++;
                }
            } else if (b > -1 || b < -127) {
                i2++;
            } else {
                int i6 = i2 + 1;
                byte b2 = bArr[i2];
                int i7 = (-b) + 1;
                ensureCapacity(i7);
                for (int i8 = 0; i8 < i7; i8++) {
                    byte[] bArr3 = this.dstData;
                    int i9 = this.dstIndex;
                    this.dstIndex = i9 + 1;
                    bArr3[i9] = b2;
                }
                i = i6;
            }
            i = i2;
        }
        int i10 = this.dstIndex;
        byte[] bArr4 = new byte[i10];
        System.arraycopy(this.dstData, 0, bArr4, 0, i10);
        return bArr4;
    }
}
