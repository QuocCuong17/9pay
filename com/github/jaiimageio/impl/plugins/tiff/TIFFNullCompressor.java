package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.plugins.tiff.TIFFCompressor;
import java.io.IOException;

/* loaded from: classes3.dex */
public class TIFFNullCompressor extends TIFFCompressor {
    public TIFFNullCompressor() {
        super("", 1, true);
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFCompressor
    public int encode(byte[] bArr, int i, int i2, int i3, int[] iArr, int i4) throws IOException {
        int i5 = 0;
        for (int i6 : iArr) {
            i5 += i6;
        }
        int i7 = ((i5 * i2) + 7) / 8;
        int i8 = i3 * i7;
        if (i7 == i4) {
            this.stream.write(bArr, i, i8);
        } else {
            for (int i9 = 0; i9 < i3; i9++) {
                this.stream.write(bArr, i, i7);
                i += i4;
            }
        }
        return i8;
    }
}
