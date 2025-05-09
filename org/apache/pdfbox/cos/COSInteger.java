package org.apache.pdfbox.cos;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes5.dex */
public final class COSInteger extends COSNumber {
    private static final int HIGH = 256;
    private static final int LOW = -100;
    private final long value;
    private static final COSInteger[] STATIC = new COSInteger[357];
    public static final COSInteger ZERO = get(0);
    public static final COSInteger ONE = get(1);
    public static final COSInteger TWO = get(2);
    public static final COSInteger THREE = get(3);

    public static COSInteger get(long j) {
        if (-100 <= j && j <= 256) {
            int i = ((int) j) + 100;
            COSInteger[] cOSIntegerArr = STATIC;
            if (cOSIntegerArr[i] == null) {
                cOSIntegerArr[i] = new COSInteger(j);
            }
            return cOSIntegerArr[i];
        }
        return new COSInteger(j);
    }

    private COSInteger(long j) {
        this.value = j;
    }

    public boolean equals(Object obj) {
        return (obj instanceof COSInteger) && ((COSInteger) obj).intValue() == intValue();
    }

    public int hashCode() {
        long j = this.value;
        return (int) (j ^ (j >> 32));
    }

    public String toString() {
        return "COSInt{" + this.value + "}";
    }

    @Override // org.apache.pdfbox.cos.COSNumber
    public float floatValue() {
        return (float) this.value;
    }

    @Override // org.apache.pdfbox.cos.COSNumber
    public double doubleValue() {
        return this.value;
    }

    @Override // org.apache.pdfbox.cos.COSNumber
    public int intValue() {
        return (int) this.value;
    }

    @Override // org.apache.pdfbox.cos.COSNumber
    public long longValue() {
        return this.value;
    }

    @Override // org.apache.pdfbox.cos.COSBase
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return iCOSVisitor.visitFromInt(this);
    }

    public void writePDF(OutputStream outputStream) throws IOException {
        outputStream.write(String.valueOf(this.value).getBytes("ISO-8859-1"));
    }
}
