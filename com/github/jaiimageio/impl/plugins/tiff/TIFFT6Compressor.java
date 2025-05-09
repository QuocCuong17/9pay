package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.github.jaiimageio.plugins.tiff.TIFFField;
import java.io.IOException;
import javax.imageio.IIOException;

/* loaded from: classes3.dex */
public class TIFFT6Compressor extends TIFFFaxCompressor {
    public TIFFT6Compressor() {
        super("CCITT T.6", 4, true);
    }

    public synchronized int encodeT6(byte[] bArr, int i, int i2, int i3, int i4, byte[] bArr2) {
        int addEOFB;
        initBitBuf();
        byte[] bArr3 = null;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = i4;
        while (true) {
            int i9 = i8 - 1;
            if (i8 == 0) {
                break;
            }
            int i10 = i2 + i3;
            int i11 = i2 >>> 3;
            int i12 = 7 - (i2 & 7);
            int nextState = (((bArr[i6 + i11] & 255) >>> i12) & 1) != 0 ? i2 : nextState(bArr, i6, i2, i10);
            int nextState2 = (bArr3 == null ? 0 : ((bArr3[i11 + i7] & 255) >>> i12) & 1) != 0 ? i2 : nextState(bArr3, i7, i2, i10);
            int i13 = i2;
            int i14 = 0;
            while (true) {
                int nextState3 = nextState(bArr3, i7, nextState2, i10);
                if (nextState3 < nextState) {
                    i5 += add2DBits(bArr2, i5, pass, 0);
                    i13 = nextState3;
                } else {
                    int i15 = (nextState2 - nextState) + 3;
                    if (i15 <= 6 && i15 >= 0) {
                        i5 += add2DBits(bArr2, i5, vert, i15);
                        i13 = nextState;
                    } else {
                        int nextState4 = nextState(bArr, i6, nextState, i10);
                        int add2DBits = i5 + add2DBits(bArr2, i5, horz, 0);
                        int add1DBits = add2DBits + add1DBits(bArr2, add2DBits, nextState - i13, i14);
                        i5 = add1DBits + add1DBits(bArr2, add1DBits, nextState4 - nextState, i14 ^ 1);
                        i13 = nextState4;
                    }
                }
                if (i13 >= i10) {
                    break;
                }
                i14 = ((bArr[(i13 >>> 3) + i6] & 255) >>> (7 - (i13 & 7))) & 1;
                nextState = nextState(bArr, i6, i13, i10);
                nextState2 = nextState(bArr3, i7, i13, i10);
                if ((bArr3 == null ? 0 : ((bArr3[(nextState2 >>> 3) + i7] & 255) >>> (7 - (nextState2 & 7))) & 1) == i14) {
                    nextState2 = nextState(bArr3, i7, nextState2, i10);
                }
            }
            bArr3 = bArr;
            i7 = i6;
            i6 += i;
            i8 = i9;
        }
        addEOFB = i5 + addEOFB(bArr2, i5);
        if (this.inverseFill) {
            for (int i16 = 0; i16 < addEOFB; i16++) {
                bArr2[i16] = TIFFFaxDecompressor.flipTable[bArr2[i16] & 255];
            }
        }
        return addEOFB;
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFCompressor
    public int encode(byte[] bArr, int i, int i2, int i3, int[] iArr, int i4) throws IOException {
        if (iArr.length != 1 || iArr[0] != 1) {
            throw new IIOException("Bits per sample must be 1 for T6 compression!");
        }
        if (this.metadata instanceof TIFFImageMetadata) {
            ((TIFFImageMetadata) this.metadata).rootIFD.addTIFFField(new TIFFField(BaselineTIFFTagSet.getInstance().getTag(BaselineTIFFTagSet.TAG_T6_OPTIONS), 4, 1, new long[]{0}));
        }
        byte[] bArr2 = new byte[((((((((i2 + 1) / 2) * 9) + 2) + 7) / 8) + 2) * i3) + 12];
        int encodeT6 = encodeT6(bArr, i4, i * 8, i2, i3, bArr2);
        this.stream.write(bArr2, 0, encodeT6);
        return encodeT6;
    }
}
