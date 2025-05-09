package org.apache.pdfbox.pdmodel.documentinterchange.prepress;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.graphics.PDLineDashPattern;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;

/* loaded from: classes5.dex */
public final class PDBoxStyle implements COSObjectable {
    public static final String GUIDELINE_STYLE_DASHED = "D";
    public static final String GUIDELINE_STYLE_SOLID = "S";
    private COSDictionary dictionary;

    public PDBoxStyle() {
        this.dictionary = new COSDictionary();
    }

    public PDBoxStyle(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.dictionary;
    }

    public COSDictionary getDictionary() {
        return this.dictionary;
    }

    public PDColor getGuidelineColor() {
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.C);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            cOSArray.add((COSBase) COSInteger.ZERO);
            cOSArray.add((COSBase) COSInteger.ZERO);
            cOSArray.add((COSBase) COSInteger.ZERO);
            this.dictionary.setItem("C", (COSBase) cOSArray);
        }
        return new PDColor(cOSArray.toFloatArray(), PDDeviceRGB.INSTANCE);
    }

    public void setGuideLineColor(PDColor pDColor) {
        this.dictionary.setItem(COSName.C, (COSBase) (pDColor != null ? pDColor.toCOSArray() : null));
    }

    public float getGuidelineWidth() {
        return this.dictionary.getFloat(COSName.W, 1.0f);
    }

    public void setGuidelineWidth(float f) {
        this.dictionary.setFloat(COSName.W, f);
    }

    public String getGuidelineStyle() {
        return this.dictionary.getNameAsString(COSName.S, "S");
    }

    public void setGuidelineStyle(String str) {
        this.dictionary.setName(COSName.S, str);
    }

    public PDLineDashPattern getLineDashPattern() {
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.D);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            cOSArray.add((COSBase) COSInteger.THREE);
            this.dictionary.setItem(COSName.D, (COSBase) cOSArray);
        }
        COSArray cOSArray2 = new COSArray();
        cOSArray2.add((COSBase) cOSArray);
        return new PDLineDashPattern(cOSArray2, 0);
    }

    public void setLineDashPattern(COSArray cOSArray) {
        if (cOSArray == null) {
            cOSArray = null;
        }
        this.dictionary.setItem(COSName.D, (COSBase) cOSArray);
    }
}
