package org.apache.pdfbox.pdmodel.graphics.predictor;

/* loaded from: classes5.dex */
public class Optimum extends PredictorAlgorithm {
    PredictorAlgorithm[] filter = {new None(), new Sub(), new Up(), new Average(), new Paeth()};

    @Override // org.apache.pdfbox.pdmodel.graphics.predictor.PredictorAlgorithm
    public void checkBufsiz(byte[] bArr, byte[] bArr2) {
        if (bArr.length != ((getWidth() * getBpp()) + 1) * getHeight()) {
            throw new IllegalArgumentException("filtered.length != (width*bpp + 1) * height, " + bArr.length + " " + (((getWidth() * getBpp()) + 1) * getHeight()) + "w,h,bpp=" + getWidth() + "," + getHeight() + "," + getBpp());
        }
        if (bArr2.length == getWidth() * getHeight() * getBpp()) {
            return;
        }
        throw new IllegalArgumentException("raw.length != width * height * bpp, raw.length=" + bArr2.length + " w,h,bpp=" + getWidth() + "," + getHeight() + "," + getBpp());
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.predictor.PredictorAlgorithm
    public void encodeLine(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4) {
        throw new UnsupportedOperationException("encodeLine");
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.predictor.PredictorAlgorithm
    public void decodeLine(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4) {
        throw new UnsupportedOperationException("decodeLine");
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.predictor.PredictorAlgorithm
    public void encode(byte[] bArr, byte[] bArr2) {
        checkBufsiz(bArr2, bArr);
        throw new UnsupportedOperationException("encode");
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.predictor.PredictorAlgorithm
    public void setBpp(int i) {
        super.setBpp(i);
        int i2 = 0;
        while (true) {
            PredictorAlgorithm[] predictorAlgorithmArr = this.filter;
            if (i2 >= predictorAlgorithmArr.length) {
                return;
            }
            predictorAlgorithmArr[i2].setBpp(i);
            i2++;
        }
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.predictor.PredictorAlgorithm
    public void setHeight(int i) {
        super.setHeight(i);
        int i2 = 0;
        while (true) {
            PredictorAlgorithm[] predictorAlgorithmArr = this.filter;
            if (i2 >= predictorAlgorithmArr.length) {
                return;
            }
            predictorAlgorithmArr[i2].setHeight(i);
            i2++;
        }
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.predictor.PredictorAlgorithm
    public void setWidth(int i) {
        super.setWidth(i);
        int i2 = 0;
        while (true) {
            PredictorAlgorithm[] predictorAlgorithmArr = this.filter;
            if (i2 >= predictorAlgorithmArr.length) {
                return;
            }
            predictorAlgorithmArr[i2].setWidth(i);
            i2++;
        }
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.predictor.PredictorAlgorithm
    public void decode(byte[] bArr, byte[] bArr2) {
        checkBufsiz(bArr, bArr2);
        int width = getWidth() * getBpp();
        int i = width + 1;
        for (int i2 = 0; i2 < getHeight(); i2++) {
            int i3 = i2 * i;
            this.filter[bArr[i3]].decodeLine(bArr, bArr2, i, i3 + 1, width, i2 * width);
        }
    }
}
