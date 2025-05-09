package org.apache.pdfbox.pdmodel.graphics;

import java.util.Arrays;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.pdmodel.common.COSArrayList;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public final class PDLineDashPattern implements COSObjectable {
    private final float[] array;
    private final int phase;

    public PDLineDashPattern() {
        this.array = new float[0];
        this.phase = 0;
    }

    public PDLineDashPattern(COSArray cOSArray, int i) {
        this.array = cOSArray.toFloatArray();
        this.phase = i;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        COSArray cOSArray = new COSArray();
        cOSArray.add((COSBase) COSArrayList.converterToCOSArray(Arrays.asList(this.array)));
        cOSArray.add((COSBase) COSInteger.get(this.phase));
        return cOSArray;
    }

    public int getPhase() {
        return this.phase;
    }

    public float[] getDashArray() {
        return (float[]) this.array.clone();
    }
}
