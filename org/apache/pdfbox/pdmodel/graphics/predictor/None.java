package org.apache.pdfbox.pdmodel.graphics.predictor;

/* loaded from: classes5.dex */
public class None extends PredictorAlgorithm {
    @Override // org.apache.pdfbox.pdmodel.graphics.predictor.PredictorAlgorithm
    public void encode(byte[] bArr, byte[] bArr2) {
        checkBufsiz(bArr2, bArr);
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.predictor.PredictorAlgorithm
    public void decode(byte[] bArr, byte[] bArr2) {
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.predictor.PredictorAlgorithm
    public void encodeLine(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4) {
        int width = getWidth() * getBpp();
        for (int i5 = 0; i5 < width; i5++) {
            bArr2[i4 + i5] = bArr[i2 + i5];
        }
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.predictor.PredictorAlgorithm
    public void decodeLine(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4) {
        int width = getWidth() * getBpp();
        for (int i5 = 0; i5 < width; i5++) {
            bArr2[i4 + i5] = bArr[i2 + i5];
        }
    }
}
