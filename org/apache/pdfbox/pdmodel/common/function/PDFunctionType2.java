package org.apache.pdfbox.pdmodel.common.function;

import java.io.IOException;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNumber;

/* loaded from: classes5.dex */
public class PDFunctionType2 extends PDFunction {
    private final COSArray c0;
    private final COSArray c1;
    private final float exponent;

    @Override // org.apache.pdfbox.pdmodel.common.function.PDFunction
    public int getFunctionType() {
        return 2;
    }

    public PDFunctionType2(COSBase cOSBase) {
        super(cOSBase);
        if (getDictionary().getDictionaryObject(COSName.C0) == null) {
            COSArray cOSArray = new COSArray();
            this.c0 = cOSArray;
            cOSArray.add((COSBase) new COSFloat(0.0f));
        } else {
            this.c0 = (COSArray) getDictionary().getDictionaryObject(COSName.C0);
        }
        if (getDictionary().getDictionaryObject(COSName.C1) == null) {
            COSArray cOSArray2 = new COSArray();
            this.c1 = cOSArray2;
            cOSArray2.add((COSBase) new COSFloat(1.0f));
        } else {
            this.c1 = (COSArray) getDictionary().getDictionaryObject(COSName.C1);
        }
        this.exponent = getDictionary().getFloat(COSName.N);
    }

    @Override // org.apache.pdfbox.pdmodel.common.function.PDFunction
    public float[] eval(float[] fArr) throws IOException {
        float pow = (float) Math.pow(fArr[0], this.exponent);
        int size = this.c0.size();
        float[] fArr2 = new float[size];
        for (int i = 0; i < size; i++) {
            float floatValue = ((COSNumber) this.c0.get(i)).floatValue();
            fArr2[i] = floatValue + ((((COSNumber) this.c1.get(i)).floatValue() - floatValue) * pow);
        }
        return clipToRange(fArr2);
    }

    public COSArray getC0() {
        return this.c0;
    }

    public COSArray getC1() {
        return this.c1;
    }

    public float getN() {
        return this.exponent;
    }

    @Override // org.apache.pdfbox.pdmodel.common.function.PDFunction
    public String toString() {
        return "FunctionType2{C0: " + getC0() + " C1: " + getC1() + " N: " + getN() + "}";
    }
}
