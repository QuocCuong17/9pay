package org.apache.pdfbox.pdmodel.graphics.predictor;

/* loaded from: classes5.dex */
public class Average extends PredictorAlgorithm {
    @Override // org.apache.pdfbox.pdmodel.graphics.predictor.PredictorAlgorithm
    public void encodeLine(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4) {
        int width = getWidth() * getBpp();
        for (int i5 = 0; i5 < width; i5++) {
            bArr2[i5 + i4] = (byte) (bArr[i5 + i2] - ((leftPixel(bArr, i2, i, i5) + abovePixel(bArr, i2, i, i5)) >>> 2));
        }
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.predictor.PredictorAlgorithm
    public void decodeLine(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4) {
        int width = getWidth() * getBpp();
        for (int i5 = 0; i5 < width; i5++) {
            bArr2[i5 + i4] = (byte) (bArr[i5 + i2] + ((leftPixel(bArr2, i4, i3, i5) + abovePixel(bArr2, i4, i3, i5)) >>> 2));
        }
    }
}
