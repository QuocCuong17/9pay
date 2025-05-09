package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.plugins.tiff.TIFFDecompressor;
import java.io.IOException;

/* loaded from: classes3.dex */
public class TIFFLSBDecompressor extends TIFFDecompressor {
    private static byte[] flipTable = TIFFFaxDecompressor.flipTable;

    @Override // com.github.jaiimageio.plugins.tiff.TIFFDecompressor
    public void decodeRaw(byte[] bArr, int i, int i2, int i3) throws IOException {
        this.stream.seek(this.offset);
        int i4 = ((this.srcWidth * i2) + 7) / 8;
        if (i4 == i3) {
            int i5 = i4 * this.srcHeight;
            this.stream.readFully(bArr, i, i5);
            int i6 = i5 + i;
            while (i < i6) {
                bArr[i] = flipTable[bArr[i] & 255];
                i++;
            }
            return;
        }
        for (int i7 = 0; i7 < this.srcHeight; i7++) {
            this.stream.readFully(bArr, i, i4);
            int i8 = i + i4;
            for (int i9 = i; i9 < i8; i9++) {
                bArr[i9] = flipTable[bArr[i9] & 255];
            }
            i += i3;
        }
    }
}
