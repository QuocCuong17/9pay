package org.apache.pdfbox.pdmodel.graphics.predictor;

/* loaded from: classes5.dex */
public class Sub extends PredictorAlgorithm {
    @Override // org.apache.pdfbox.pdmodel.graphics.predictor.PredictorAlgorithm
    public void encodeLine(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4) {
        int width = getWidth() * getBpp();
        int bpp = getBpp();
        for (int i5 = 0; i5 < width && i5 < bpp; i5++) {
            bArr2[i5 + i4] = bArr[i5 + i2];
        }
        for (int bpp2 = getBpp(); bpp2 < width; bpp2++) {
            int i6 = bpp2 + i2;
            bArr2[bpp2 + i4] = (byte) (bArr[i6] - bArr[i6 - bpp]);
        }
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.predictor.PredictorAlgorithm
    public void decodeLine(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4) {
        int width = getWidth() * getBpp();
        int bpp = getBpp();
        for (int i5 = 0; i5 < width && i5 < bpp; i5++) {
            bArr2[i5 + i4] = bArr[i5 + i2];
        }
        for (int bpp2 = getBpp(); bpp2 < width; bpp2++) {
            int i6 = bpp2 + i4;
            bArr2[i6] = (byte) (bArr[bpp2 + i2] + bArr2[i6 - bpp]);
        }
    }
}
