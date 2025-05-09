package org.apache.pdfbox.pdmodel.interactive.annotation;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;

/* loaded from: classes5.dex */
public class PDAnnotationSquareCircle extends PDAnnotationMarkup {
    public static final String SUB_TYPE_CIRCLE = "Circle";
    public static final String SUB_TYPE_SQUARE = "Square";

    public PDAnnotationSquareCircle(String str) {
        setSubtype(str);
    }

    public PDAnnotationSquareCircle(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public void setInteriorColor(PDColor pDColor) {
        getDictionary().setItem(COSName.IC, (COSBase) pDColor.toCOSArray());
    }

    public PDColor getInteriorColor() {
        return getColor(COSName.IC);
    }

    public void setBorderEffect(PDBorderEffectDictionary pDBorderEffectDictionary) {
        getDictionary().setItem("BE", pDBorderEffectDictionary);
    }

    public PDBorderEffectDictionary getBorderEffect() {
        COSDictionary cOSDictionary = (COSDictionary) getDictionary().getDictionaryObject("BE");
        if (cOSDictionary != null) {
            return new PDBorderEffectDictionary(cOSDictionary);
        }
        return null;
    }

    public void setRectDifference(PDRectangle pDRectangle) {
        getDictionary().setItem("RD", pDRectangle);
    }

    public PDRectangle getRectDifference() {
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject("RD");
        if (cOSArray != null) {
            return new PDRectangle(cOSArray);
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

    public void setBorderStyle(PDBorderStyleDictionary pDBorderStyleDictionary) {
        getDictionary().setItem("BS", pDBorderStyleDictionary);
    }

    public PDBorderStyleDictionary getBorderStyle() {
        COSDictionary cOSDictionary = (COSDictionary) getDictionary().getItem(COSName.BS);
        if (cOSDictionary != null) {
            return new PDBorderStyleDictionary(cOSDictionary);
        }
        return null;
    }
}
