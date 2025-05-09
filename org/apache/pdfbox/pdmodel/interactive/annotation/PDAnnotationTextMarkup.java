package org.apache.pdfbox.pdmodel.interactive.annotation;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public class PDAnnotationTextMarkup extends PDAnnotationMarkup {
    public static final String SUB_TYPE_HIGHLIGHT = "Highlight";
    public static final String SUB_TYPE_SQUIGGLY = "Squiggly";
    public static final String SUB_TYPE_STRIKEOUT = "StrikeOut";
    public static final String SUB_TYPE_UNDERLINE = "Underline";

    private PDAnnotationTextMarkup() {
    }

    public PDAnnotationTextMarkup(String str) {
        setSubtype(str);
        setQuadPoints(new float[0]);
    }

    public PDAnnotationTextMarkup(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public void setQuadPoints(float[] fArr) {
        COSArray cOSArray = new COSArray();
        cOSArray.setFloatArray(fArr);
        getDictionary().setItem("QuadPoints", (COSBase) cOSArray);
    }

    public float[] getQuadPoints() {
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject("QuadPoints");
        if (cOSArray != null) {
            return cOSArray.toFloatArray();
        }
        return null;
    }

    public void setSubtype(String str) {
        getDictionary().setName(COSName.SUBTYPE, str);
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation
    public String getSubtype() {
        return getDictionary().getNameAsString(COSName.SUBTYPE);
    }
}
