package org.apache.pdfbox.pdmodel.graphics.color;

import java.util.Arrays;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNumber;

/* loaded from: classes5.dex */
public final class PDColor {
    private final PDColorSpace colorSpace;
    private final float[] components;
    private final COSName patternName;

    public PDColor(COSArray cOSArray, PDColorSpace pDColorSpace) {
        int i = 0;
        if (cOSArray.size() > 0 && (cOSArray.get(cOSArray.size() - 1) instanceof COSName)) {
            this.components = new float[cOSArray.size() - 1];
            while (i < cOSArray.size() - 1) {
                this.components[i] = ((COSNumber) cOSArray.get(i)).floatValue();
                i++;
            }
            this.patternName = (COSName) cOSArray.get(cOSArray.size() - 1);
        } else {
            this.components = new float[cOSArray.size()];
            while (i < cOSArray.size()) {
                this.components[i] = ((COSNumber) cOSArray.get(i)).floatValue();
                i++;
            }
            this.patternName = null;
        }
        this.colorSpace = pDColorSpace;
    }

    public PDColor(float[] fArr, PDColorSpace pDColorSpace) {
        this.components = (float[]) fArr.clone();
        this.patternName = null;
        this.colorSpace = pDColorSpace;
    }

    public PDColor(COSName cOSName, PDColorSpace pDColorSpace) {
        this.components = new float[0];
        this.patternName = cOSName;
        this.colorSpace = pDColorSpace;
    }

    public PDColor(float[] fArr, COSName cOSName, PDColorSpace pDColorSpace) {
        this.components = (float[]) fArr.clone();
        this.patternName = cOSName;
        this.colorSpace = pDColorSpace;
    }

    public float[] getComponents() {
        return (float[]) this.components.clone();
    }

    public COSName getPatternName() {
        return this.patternName;
    }

    public boolean isPattern() {
        return this.patternName != null;
    }

    public COSArray toCOSArray() {
        COSArray cOSArray = new COSArray();
        cOSArray.setFloatArray(this.components);
        cOSArray.add((COSBase) this.patternName);
        return cOSArray;
    }

    public PDColorSpace getColorSpace() {
        return this.colorSpace;
    }

    public String toString() {
        return "PDColor{components=" + Arrays.toString(this.components) + ", patternName=" + this.patternName + "}";
    }
}
