package org.apache.pdfbox.pdmodel.interactive.annotation;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.graphics.PDLineDashPattern;

/* loaded from: classes5.dex */
public class PDBorderStyleDictionary implements COSObjectable {
    public static final String STYLE_BEVELED = "B";
    public static final String STYLE_DASHED = "D";
    public static final String STYLE_INSET = "I";
    public static final String STYLE_SOLID = "S";
    public static final String STYLE_UNDERLINE = "U";
    private COSDictionary dictionary;

    public PDBorderStyleDictionary() {
        this.dictionary = new COSDictionary();
    }

    public PDBorderStyleDictionary(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    public COSDictionary getDictionary() {
        return this.dictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.dictionary;
    }

    public void setWidth(float f) {
        getDictionary().setFloat("W", f);
    }

    public float getWidth() {
        return getDictionary().getFloat("W", 1.0f);
    }

    public void setStyle(String str) {
        getDictionary().setName("S", str);
    }

    public String getStyle() {
        return getDictionary().getNameAsString("S", "S");
    }

    public void setDashStyle(COSArray cOSArray) {
        if (cOSArray == null) {
            cOSArray = null;
        }
        getDictionary().setItem("D", (COSBase) cOSArray);
    }

    public PDLineDashPattern getDashStyle() {
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject("D");
        if (cOSArray == null) {
            cOSArray = new COSArray();
            cOSArray.add((COSBase) COSInteger.THREE);
            getDictionary().setItem("D", (COSBase) cOSArray);
        }
        return new PDLineDashPattern(cOSArray, 0);
    }
}
