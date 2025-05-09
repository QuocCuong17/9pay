package com.github.jaiimageio.impl.plugins.tiff;

import java.io.IOException;
import javax.imageio.IIOException;

/* loaded from: classes3.dex */
public class TIFFRLECompressor extends TIFFFaxCompressor {
    public TIFFRLECompressor() {
        super("CCITT RLE", 2, true);
    }

    public int encodeRLE(byte[] bArr, int i, int i2, int i3, byte[] bArr2) {
        initBitBuf();
        int encode1D = encode1D(bArr, i, i2, i3, bArr2, 0);
        while (this.ndex > 0) {
            bArr2[encode1D] = (byte) (this.bits >>> 24);
            this.bits <<= 8;
            this.ndex -= 8;
            encode1D++;
        }
        if (this.inverseFill) {
            byte[] bArr3 = TIFFFaxDecompressor.flipTable;
            for (int i4 = 0; i4 < encode1D; i4++) {
                bArr2[i4] = bArr3[bArr2[i4] & 255];
            }
        }
        return encode1D;
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFCompressor
    public int encode(byte[] bArr, int i, int i2, int i3, int[] iArr, int i4) throws IOException {
        if (iArr.length != 1 || iArr[0] != 1) {
            throw new IIOException("Bits per sample must be 1 for RLE compression!");
        }
        byte[] bArr2 = new byte[(((((i2 + 1) / 2) * 9) + 2) + 7) / 8];
        int i5 = 0;
        for (int i6 = 0; i6 < i3; i6++) {
            int encodeRLE = encodeRLE(bArr, i, 0, i2, bArr2);
            this.stream.write(bArr2, 0, encodeRLE);
            i += i4;
            i5 += encodeRLE;
        }
        return i5;
    }
}
