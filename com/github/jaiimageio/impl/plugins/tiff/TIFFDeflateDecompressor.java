package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.plugins.tiff.TIFFDecompressor;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import javax.imageio.IIOException;

/* loaded from: classes3.dex */
public class TIFFDeflateDecompressor extends TIFFDecompressor {
    private static final boolean DEBUG = false;
    Inflater inflater;
    int predictor;

    public TIFFDeflateDecompressor(int i) throws IIOException {
        this.inflater = null;
        this.inflater = new Inflater();
        if (i != 1 && i != 2) {
            throw new IIOException("Illegal value for Predictor in TIFF file");
        }
        this.predictor = i;
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFDecompressor
    public synchronized void decodeRaw(byte[] bArr, int i, int i2, int i3) throws IOException {
        byte[] bArr2;
        int i4;
        if (this.predictor == 2) {
            int length = this.bitsPerSample.length;
            for (int i5 = 0; i5 < length; i5++) {
                if (this.bitsPerSample[i5] != 8) {
                    throw new IIOException(this.bitsPerSample[i5] + "-bit samples are not supported for Horizontal differencing Predictor");
                }
            }
        }
        this.stream.seek(this.offset);
        byte[] bArr3 = new byte[this.byteCount];
        this.stream.readFully(bArr3);
        int i6 = ((this.srcWidth * i2) + 7) / 8;
        if (i6 == i3) {
            bArr2 = bArr;
            i4 = i;
        } else {
            bArr2 = new byte[this.srcHeight * i6];
            i4 = 0;
        }
        this.inflater.setInput(bArr3);
        try {
            this.inflater.inflate(bArr2, i4, this.srcHeight * i6);
            this.inflater.reset();
            if (this.predictor == 2) {
                for (int i7 = 0; i7 < this.srcHeight; i7++) {
                    int i8 = (this.samplesPerPixel * ((this.srcWidth * i7) + 1)) + i4;
                    for (int i9 = this.samplesPerPixel; i9 < this.srcWidth * this.samplesPerPixel; i9++) {
                        bArr2[i8] = (byte) (bArr2[i8] + bArr2[i8 - this.samplesPerPixel]);
                        i8++;
                    }
                }
            }
            if (i6 != i3) {
                int i10 = 0;
                for (int i11 = 0; i11 < this.srcHeight; i11++) {
                    System.arraycopy(bArr2, i10, bArr, i, i6);
                    i10 += i6;
                    i += i3;
                }
            }
        } catch (DataFormatException e) {
            throw new IIOException(I18N.getString("TIFFDeflateDecompressor0"), e);
        }
    }
}
