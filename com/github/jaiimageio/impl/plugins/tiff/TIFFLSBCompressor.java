package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.plugins.tiff.TIFFCompressor;
import java.io.IOException;

/* loaded from: classes3.dex */
public class TIFFLSBCompressor extends TIFFCompressor {
    public TIFFLSBCompressor() {
        super("", 1, true);
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFCompressor
    public int encode(byte[] bArr, int i, int i2, int i3, int[] iArr, int i4) throws IOException {
        int i5 = 0;
        for (int i6 : iArr) {
            i5 += i6;
        }
        int i7 = ((i5 * i2) + 7) / 8;
        byte[] bArr2 = new byte[i7];
        byte[] bArr3 = TIFFFaxDecompressor.flipTable;
        for (int i8 = 0; i8 < i3; i8++) {
            System.arraycopy(bArr, i, bArr2, 0, i7);
            for (int i9 = 0; i9 < i7; i9++) {
                bArr2[i9] = bArr3[bArr2[i9] & 255];
            }
            this.stream.write(bArr2, 0, i7);
            i += i4;
        }
        return i3 * i7;
    }
}
