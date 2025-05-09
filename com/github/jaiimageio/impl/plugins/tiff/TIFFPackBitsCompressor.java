package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.plugins.tiff.TIFFCompressor;
import java.io.IOException;

/* loaded from: classes3.dex */
public class TIFFPackBitsCompressor extends TIFFCompressor {
    public TIFFPackBitsCompressor() {
        super("PackBits", 32773, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0055, code lost:
    
        r11[r12] = (byte) (r3 - 1);
        r4 = r4 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int packBits(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = (i2 + i) - 1;
        int i5 = i4 - 1;
        while (i <= i4) {
            byte b = bArr[i];
            int i6 = 1;
            while (i6 < 127 && i < i4) {
                int i7 = i + 1;
                if (bArr[i] != bArr[i7]) {
                    break;
                }
                i6++;
                i = i7;
            }
            if (i6 > 1) {
                i++;
                int i8 = i3 + 1;
                bArr2[i3] = (byte) (-(i6 - 1));
                i3 = i8 + 1;
                bArr2[i8] = b;
            }
            int i9 = i3;
            int i10 = 0;
            while (i10 < 128 && ((i < i4 && bArr[i] != bArr[i + 1]) || (i < i5 && bArr[i] != bArr[i + 2]))) {
                i10++;
                i9++;
                bArr2[i9] = bArr[i];
                i++;
            }
            if (i != i4) {
                i3 = i9;
            } else if (i10 > 0 && i10 < 128) {
                bArr2[i3] = (byte) (bArr2[i3] + 1);
                i3 = i9 + 1;
                bArr2[i9] = bArr[i];
                i++;
            } else {
                int i11 = i9 + 1;
                bArr2[i9] = 0;
                bArr2[i11] = bArr[i];
                i3 = i11 + 1;
                i++;
            }
        }
        return i3;
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFCompressor
    public int encode(byte[] bArr, int i, int i2, int i3, int[] iArr, int i4) throws IOException {
        int i5 = 0;
        for (int i6 : iArr) {
            i5 += i6;
        }
        int i7 = ((i5 * i2) + 7) / 8;
        byte[] bArr2 = new byte[i7 + ((i7 + 127) / 128)];
        int i8 = 0;
        for (int i9 = 0; i9 < i3; i9++) {
            int packBits = packBits(bArr, i, i4, bArr2, 0);
            i += i4;
            i8 += packBits;
            this.stream.write(bArr2, 0, packBits);
        }
        return i8;
    }
}
