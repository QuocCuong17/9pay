package org.apache.pdfbox.pdmodel.graphics.predictor;

/* loaded from: classes5.dex */
public class Paeth extends PredictorAlgorithm {
    public int paethPredictor(int i, int i2, int i3) {
        int i4 = (i + i2) - i3;
        int abs = Math.abs(i4 - i);
        int abs2 = Math.abs(i4 - i2);
        int abs3 = Math.abs(i4 - i3);
        return (abs > abs2 || abs > abs3) ? abs2 <= abs3 ? i2 : i3 : i;
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.predictor.PredictorAlgorithm
    public void encodeLine(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4) {
        int width = getWidth() * getBpp();
        for (int i5 = 0; i5 < width; i5++) {
            bArr2[i5 + i4] = (byte) (bArr[i5 + i2] - paethPredictor(leftPixel(bArr, i2, i, i5), abovePixel(bArr, i2, i, i5), aboveLeftPixel(bArr, i2, i, i5)));
        }
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.predictor.PredictorAlgorithm
    public void decodeLine(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4) {
        int width = getWidth() * getBpp();
        for (int i5 = 0; i5 < width; i5++) {
            bArr2[i5 + i4] = (byte) (bArr[i5 + i2] + paethPredictor(leftPixel(bArr2, i4, i3, i5), abovePixel(bArr2, i4, i3, i5), aboveLeftPixel(bArr2, i4, i3, i5)));
        }
    }
}
