package org.apache.pdfbox.cos;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

/* loaded from: classes5.dex */
public class COSFloat extends COSNumber {
    private BigDecimal value;
    private String valueAsString;

    public COSFloat(float f) {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(f));
        this.value = bigDecimal;
        this.valueAsString = removeNullDigits(bigDecimal.toPlainString());
    }

    public COSFloat(String str) throws IOException {
        try {
            this.valueAsString = str;
            this.value = new BigDecimal(this.valueAsString);
        } catch (NumberFormatException e) {
            throw new IOException("Error expected floating point number actual='" + str + "'", e);
        }
    }

    private static String removeNullDigits(String str) {
        if (str.indexOf(46) > -1 && !str.endsWith(".0")) {
            while (str.endsWith("0") && !str.endsWith(".0")) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    @Override // org.apache.pdfbox.cos.COSNumber
    public float floatValue() {
        return this.value.floatValue();
    }

    @Override // org.apache.pdfbox.cos.COSNumber
    public double doubleValue() {
        return this.value.doubleValue();
    }

    @Override // org.apache.pdfbox.cos.COSNumber
    public long longValue() {
        return this.value.longValue();
    }

    @Override // org.apache.pdfbox.cos.COSNumber
    public int intValue() {
        return this.value.intValue();
    }

    public boolean equals(Object obj) {
        return (obj instanceof COSFloat) && Float.floatToIntBits(((COSFloat) obj).value.floatValue()) == Float.floatToIntBits(this.value.floatValue());
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toString() {
        return "COSFloat{" + this.valueAsString + "}";
    }

    @Override // org.apache.pdfbox.cos.COSBase
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return iCOSVisitor.visitFromFloat(this);
    }

    public void writePDF(OutputStream outputStream) throws IOException {
        outputStream.write(this.valueAsString.getBytes("ISO-8859-1"));
    }
}
