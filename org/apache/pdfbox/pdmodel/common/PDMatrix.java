package org.apache.pdfbox.pdmodel.common;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSNumber;

/* loaded from: classes5.dex */
public class PDMatrix implements COSObjectable {
    private COSArray matrix;
    private int numberOfRowElements;

    public PDMatrix() {
        this.numberOfRowElements = 3;
        COSArray cOSArray = new COSArray();
        this.matrix = cOSArray;
        cOSArray.add((COSBase) new COSFloat(1.0f));
        this.matrix.add((COSBase) new COSFloat(0.0f));
        this.matrix.add((COSBase) new COSFloat(0.0f));
        this.matrix.add((COSBase) new COSFloat(0.0f));
        this.matrix.add((COSBase) new COSFloat(1.0f));
        this.matrix.add((COSBase) new COSFloat(0.0f));
        this.matrix.add((COSBase) new COSFloat(0.0f));
        this.matrix.add((COSBase) new COSFloat(0.0f));
        this.matrix.add((COSBase) new COSFloat(1.0f));
    }

    public PDMatrix(COSArray cOSArray) {
        this.numberOfRowElements = 3;
        if (cOSArray.size() == 6) {
            this.numberOfRowElements = 2;
        }
        this.matrix = cOSArray;
    }

    public COSArray getCOSArray() {
        return this.matrix;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.matrix;
    }

    public float getValue(int i, int i2) {
        return ((COSNumber) this.matrix.get((i * this.numberOfRowElements) + i2)).floatValue();
    }

    public void setValue(int i, int i2, float f) {
        this.matrix.set((i * this.numberOfRowElements) + i2, (COSBase) new COSFloat(f));
    }
}
