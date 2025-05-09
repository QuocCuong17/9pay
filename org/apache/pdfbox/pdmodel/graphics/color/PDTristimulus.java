package org.apache.pdfbox.pdmodel.graphics.color;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public final class PDTristimulus implements COSObjectable {
    private COSArray values;

    public PDTristimulus() {
        this.values = null;
        COSArray cOSArray = new COSArray();
        this.values = cOSArray;
        cOSArray.add((COSBase) new COSFloat(0.0f));
        this.values.add((COSBase) new COSFloat(0.0f));
        this.values.add((COSBase) new COSFloat(0.0f));
    }

    public PDTristimulus(COSArray cOSArray) {
        this.values = null;
        this.values = cOSArray;
    }

    public PDTristimulus(float[] fArr) {
        this.values = null;
        this.values = new COSArray();
        for (int i = 0; i < fArr.length && i < 3; i++) {
            this.values.add((COSBase) new COSFloat(fArr[i]));
        }
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.values;
    }

    public float getX() {
        return ((COSNumber) this.values.get(0)).floatValue();
    }

    public void setX(float f) {
        this.values.set(0, (COSBase) new COSFloat(f));
    }

    public float getY() {
        return ((COSNumber) this.values.get(1)).floatValue();
    }

    public void setY(float f) {
        this.values.set(1, (COSBase) new COSFloat(f));
    }

    public float getZ() {
        return ((COSNumber) this.values.get(2)).floatValue();
    }

    public void setZ(float f) {
        this.values.set(2, (COSBase) new COSFloat(f));
    }
}
