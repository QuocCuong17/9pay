package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.plugins.tiff.TIFFCompressor;
import java.io.IOException;
import java.util.zip.Deflater;
import javax.imageio.ImageWriteParam;

/* loaded from: classes3.dex */
public class TIFFDeflater extends TIFFCompressor {
    Deflater deflater;
    int predictor;

    public TIFFDeflater(String str, int i, ImageWriteParam imageWriteParam, int i2) {
        super(str, i, true);
        this.predictor = i2;
        this.deflater = new Deflater((imageWriteParam == null || imageWriteParam.getCompressionMode() != 2) ? -1 : (int) ((imageWriteParam.getCompressionQuality() * 8.0f) + 1.0f));
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFCompressor
    public int encode(byte[] bArr, int i, int i2, int i3, int[] iArr, int i4) throws IOException {
        int deflate;
        int i5 = i3 * i4;
        int i6 = (((i5 + 32767) / 32768) * 5) + i5 + 6;
        byte[] bArr2 = new byte[i6];
        if (this.predictor == 2) {
            int length = iArr.length;
            int i7 = 0;
            for (int i8 : iArr) {
                i7 += i8;
            }
            int i9 = ((i7 * i2) + 7) / 8;
            byte[] bArr3 = new byte[i9];
            int i10 = i3 - 1;
            int i11 = i;
            deflate = 0;
            for (int i12 = 0; i12 < i3; i12++) {
                System.arraycopy(bArr, i11, bArr3, 0, i9);
                for (int i13 = i9 - 1; i13 >= length; i13--) {
                    bArr3[i13] = (byte) (bArr3[i13] - bArr3[i13 - length]);
                }
                this.deflater.setInput(bArr3);
                if (i12 == i10) {
                    this.deflater.finish();
                }
                while (true) {
                    int deflate2 = this.deflater.deflate(bArr2, deflate, i6 - deflate);
                    if (deflate2 != 0) {
                        deflate += deflate2;
                    }
                }
                i11 += i4;
            }
        } else {
            this.deflater.setInput(bArr, i, i5);
            this.deflater.finish();
            deflate = this.deflater.deflate(bArr2);
        }
        this.deflater.reset();
        this.stream.write(bArr2, 0, deflate);
        return deflate;
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFCompressor
    public void dispose() {
        Deflater deflater = this.deflater;
        if (deflater != null) {
            deflater.end();
            this.deflater = null;
        }
        super.dispose();
    }
}
