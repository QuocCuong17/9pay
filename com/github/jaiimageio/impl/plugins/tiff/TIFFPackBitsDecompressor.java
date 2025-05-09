package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.plugins.tiff.TIFFDecompressor;
import java.io.IOException;

/* loaded from: classes3.dex */
public class TIFFPackBitsDecompressor extends TIFFDecompressor {
    private static final boolean DEBUG = false;

    /* JADX WARN: Removed duplicated region for block: B:22:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int decode(byte[] bArr, int i, byte[] bArr2, int i2) throws IOException {
        int length = bArr2.length;
        int length2 = bArr.length;
        int i3 = i2;
        while (i3 < length && i < length2) {
            int i4 = i + 1;
            try {
                byte b = bArr[i];
                int i5 = 0;
                if (b >= 0 && b <= Byte.MAX_VALUE) {
                    while (i5 < b + 1) {
                        int i6 = i3 + 1;
                        int i7 = i4 + 1;
                        try {
                            bArr2[i3] = bArr[i4];
                            i5++;
                            i3 = i6;
                            i4 = i7;
                        } catch (ArrayIndexOutOfBoundsException unused) {
                            i3 = i6;
                            if (this.reader instanceof TIFFImageReader) {
                                ((TIFFImageReader) this.reader).forwardWarningMessage("ArrayIndexOutOfBoundsException ignored in TIFFPackBitsDecompressor.decode()");
                            }
                            return i3 - i2;
                        }
                    }
                } else if (b > -1 || b < -127) {
                    i4++;
                } else {
                    int i8 = i4 + 1;
                    byte b2 = bArr[i4];
                    while (i5 < (-b) + 1) {
                        int i9 = i3 + 1;
                        try {
                            bArr2[i3] = b2;
                            i5++;
                            i3 = i9;
                        } catch (ArrayIndexOutOfBoundsException unused2) {
                            i3 = i9;
                            if (this.reader instanceof TIFFImageReader) {
                            }
                            return i3 - i2;
                        }
                    }
                    i = i8;
                }
                i = i4;
            } catch (ArrayIndexOutOfBoundsException unused3) {
            }
        }
        return i3 - i2;
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFDecompressor
    public void decodeRaw(byte[] bArr, int i, int i2, int i3) throws IOException {
        byte[] bArr2;
        int i4;
        this.stream.seek(this.offset);
        byte[] bArr3 = new byte[this.byteCount];
        this.stream.readFully(bArr3);
        int i5 = ((this.srcWidth * i2) + 7) / 8;
        if (i5 == i3) {
            bArr2 = bArr;
            i4 = i;
        } else {
            bArr2 = new byte[this.srcHeight * i5];
            i4 = 0;
        }
        decode(bArr3, 0, bArr2, i4);
        if (i5 != i3) {
            int i6 = 0;
            for (int i7 = 0; i7 < this.srcHeight; i7++) {
                System.arraycopy(bArr2, i6, bArr, i, i5);
                i6 += i5;
                i += i3;
            }
        }
    }
}
