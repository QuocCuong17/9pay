package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.github.jaiimageio.plugins.tiff.TIFFField;
import java.io.IOException;
import javax.imageio.IIOException;
import javax.imageio.metadata.IIOMetadata;

/* loaded from: classes3.dex */
public class TIFFT4Compressor extends TIFFFaxCompressor {
    private boolean is1DMode;
    private boolean isEOLAligned;

    public TIFFT4Compressor() {
        super("CCITT T.4", 3, true);
        this.is1DMode = false;
        this.isEOLAligned = false;
    }

    @Override // com.github.jaiimageio.impl.plugins.tiff.TIFFFaxCompressor, com.github.jaiimageio.plugins.tiff.TIFFCompressor
    public void setMetadata(IIOMetadata iIOMetadata) {
        super.setMetadata(iIOMetadata);
        if (iIOMetadata instanceof TIFFImageMetadata) {
            TIFFImageMetadata tIFFImageMetadata = (TIFFImageMetadata) iIOMetadata;
            TIFFField tIFFField = tIFFImageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_T4_OPTIONS);
            if (tIFFField != null) {
                int asInt = tIFFField.getAsInt(0);
                this.is1DMode = (asInt & 1) == 0;
                this.isEOLAligned = (asInt & 4) == 4;
            } else {
                long[] jArr = new long[1];
                jArr[0] = (this.isEOLAligned ? 4 : 0) | (!this.is1DMode ? 1 : 0);
                tIFFImageMetadata.rootIFD.addTIFFField(new TIFFField(BaselineTIFFTagSet.getInstance().getTag(BaselineTIFFTagSet.TAG_T4_OPTIONS), 4, 1, jArr));
            }
        }
    }

    public int encodeT4(boolean z, boolean z2, byte[] bArr, int i, int i2, int i3, int i4, byte[] bArr2) {
        initBitBuf();
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < i4; i7++) {
            if (z || i7 % 2 == 0) {
                int addEOL = i5 + addEOL(z, z2, true, bArr2, i5);
                i5 = addEOL + encode1D(bArr, i6, i2, i3, bArr2, addEOL);
            } else {
                i5 += addEOL(z, z2, false, bArr2, i5);
                int i8 = i6 - i;
                int i9 = i2 + i3;
                int i10 = i2 >>> 3;
                int i11 = 7 - (i2 & 7);
                int nextState = (((bArr[i6 + i10] & 255) >>> i11) & 1) != 0 ? i2 : nextState(bArr, i6, i2, i9);
                int nextState2 = (((bArr[i10 + i8] & 255) >>> i11) & 1) != 0 ? i2 : nextState(bArr, i8, i2, i9);
                int i12 = i2;
                int i13 = 0;
                while (true) {
                    int nextState3 = nextState(bArr, i8, nextState2, i9);
                    if (nextState3 < nextState) {
                        i5 += add2DBits(bArr2, i5, pass, 0);
                        i12 = nextState3;
                    } else {
                        int i14 = (nextState2 - nextState) + 3;
                        if (i14 <= 6 && i14 >= 0) {
                            i5 += add2DBits(bArr2, i5, vert, i14);
                            i12 = nextState;
                        } else {
                            int nextState4 = nextState(bArr, i6, nextState, i9);
                            int add2DBits = i5 + add2DBits(bArr2, i5, horz, 0);
                            int add1DBits = add2DBits + add1DBits(bArr2, add2DBits, nextState - i12, i13);
                            i5 = add1DBits + add1DBits(bArr2, add1DBits, nextState4 - nextState, i13 ^ 1);
                            i12 = nextState4;
                        }
                    }
                    if (i12 >= i9) {
                        break;
                    }
                    i13 = ((bArr[(i12 >>> 3) + i6] & 255) >>> (7 - (i12 & 7))) & 1;
                    nextState = nextState(bArr, i6, i12, i9);
                    nextState2 = nextState(bArr, i8, i12, i9);
                    if ((((bArr[(nextState2 >>> 3) + i8] & 255) >>> (7 - (nextState2 & 7))) & 1) == i13) {
                        nextState2 = nextState(bArr, i8, nextState2, i9);
                    }
                }
            }
            i6 += i;
        }
        for (int i15 = 0; i15 < 6; i15++) {
            i5 += addEOL(z, z2, true, bArr2, i5);
        }
        while (this.ndex > 0) {
            bArr2[i5] = (byte) (this.bits >>> 24);
            this.bits <<= 8;
            this.ndex -= 8;
            i5++;
        }
        if (this.inverseFill) {
            for (int i16 = 0; i16 < i5; i16++) {
                bArr2[i16] = TIFFFaxDecompressor.flipTable[bArr2[i16] & 255];
            }
        }
        return i5;
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFCompressor
    public int encode(byte[] bArr, int i, int i2, int i3, int[] iArr, int i4) throws IOException {
        if (iArr.length != 1 || iArr[0] != 1) {
            throw new IIOException("Bits per sample must be 1 for T4 compression!");
        }
        byte[] bArr2 = new byte[((((((((i2 + 1) / 2) * 9) + 2) + 7) / 8) + 2) * i3) + 12];
        int encodeT4 = encodeT4(this.is1DMode, this.isEOLAligned, bArr, i4, i * 8, i2, i3, bArr2);
        this.stream.write(bArr2, 0, encodeT4);
        return encodeT4;
    }
}
