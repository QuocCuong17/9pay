package org.apache.pdfbox.pdmodel.common;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSNumber;

/* loaded from: classes5.dex */
public class PDRange implements COSObjectable {
    private COSArray rangeArray;
    private int startingIndex;

    public PDRange() {
        COSArray cOSArray = new COSArray();
        this.rangeArray = cOSArray;
        cOSArray.add((COSBase) new COSFloat(0.0f));
        this.rangeArray.add((COSBase) new COSFloat(1.0f));
        this.startingIndex = 0;
    }

    public PDRange(COSArray cOSArray) {
        this.rangeArray = cOSArray;
    }

    public PDRange(COSArray cOSArray, int i) {
        this.rangeArray = cOSArray;
        this.startingIndex = i;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.rangeArray;
    }

    public COSArray getCOSArray() {
        return this.rangeArray;
    }

    public float getMin() {
        return ((COSNumber) this.rangeArray.getObject(this.startingIndex * 2)).floatValue();
    }

    public void setMin(float f) {
        this.rangeArray.set(this.startingIndex * 2, (COSBase) new COSFloat(f));
    }

    public float getMax() {
        return ((COSNumber) this.rangeArray.getObject((this.startingIndex * 2) + 1)).floatValue();
    }

    public void setMax(float f) {
        this.rangeArray.set((this.startingIndex * 2) + 1, (COSBase) new COSFloat(f));
    }

    public String toString() {
        return "PDRange{" + getMin() + ", " + getMax() + '}';
    }
}
