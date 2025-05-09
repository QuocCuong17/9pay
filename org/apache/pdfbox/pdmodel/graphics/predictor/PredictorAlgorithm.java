package org.apache.pdfbox.pdmodel.graphics.predictor;

import java.util.Random;

/* loaded from: classes5.dex */
public abstract class PredictorAlgorithm {
    private int bpp;
    private int height;
    private int width;

    public abstract void decodeLine(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4);

    public abstract void encodeLine(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4);

    public void checkBufsiz(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            throw new IllegalArgumentException("src.length != dest.length");
        }
        if (bArr.length != getWidth() * getHeight() * getBpp()) {
            throw new IllegalArgumentException("src.length != width * height * bpp");
        }
    }

    public static void main(String[] strArr) {
        byte[] bArr = new byte[75];
        new Random().nextBytes(bArr);
        System.out.println("raw:   ");
        dump(bArr);
        for (int i = 10; i < 15; i++) {
            byte[] bArr2 = new byte[75];
            byte[] bArr3 = new byte[75];
            PredictorAlgorithm filter = getFilter(i);
            filter.setWidth(5);
            filter.setHeight(5);
            filter.setBpp(3);
            filter.encode(bArr, bArr3);
            filter.decode(bArr3, bArr2);
            System.out.println(filter.getClass().getName());
            dump(bArr2);
        }
    }

    public int leftPixel(byte[] bArr, int i, int i2, int i3) {
        if (i3 >= getBpp()) {
            return bArr[(i + i3) - getBpp()];
        }
        return 0;
    }

    public int abovePixel(byte[] bArr, int i, int i2, int i3) {
        if (i >= i2) {
            return bArr[(i + i3) - i2];
        }
        return 0;
    }

    public int aboveLeftPixel(byte[] bArr, int i, int i2, int i3) {
        if (i < i2 || i3 < getBpp()) {
            return 0;
        }
        return bArr[((i + i3) - i2) - getBpp()];
    }

    private static void dump(byte[] bArr) {
        for (byte b : bArr) {
            System.out.print(((int) b) + " ");
        }
        System.out.println();
    }

    public int getBpp() {
        return this.bpp;
    }

    public void setBpp(int i) {
        this.bpp = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public void encode(byte[] bArr, byte[] bArr2) {
        checkBufsiz(bArr2, bArr);
        int width = getWidth() * getBpp();
        for (int i = 0; i < this.height; i++) {
            int i2 = i * width;
            encodeLine(bArr, bArr2, width, i2, width, i2);
        }
    }

    public void decode(byte[] bArr, byte[] bArr2) {
        checkBufsiz(bArr, bArr2);
        int i = this.width * this.bpp;
        for (int i2 = 0; i2 < this.height; i2++) {
            int i3 = i2 * i;
            decodeLine(bArr, bArr2, i, i3, i, i3);
        }
    }

    public static PredictorAlgorithm getFilter(int i) {
        switch (i) {
            case 10:
                return new None();
            case 11:
                return new Sub();
            case 12:
                return new Up();
            case 13:
                return new Average();
            case 14:
                return new Paeth();
            case 15:
                return new Optimum();
            default:
                return new None();
        }
    }
}
