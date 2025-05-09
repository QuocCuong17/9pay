package org.apache.pdfbox.pdmodel.interactive.annotation;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;

/* loaded from: classes5.dex */
public class PDAnnotationLine extends PDAnnotationMarkup {
    public static final String IT_LINE_ARROW = "LineArrow";
    public static final String IT_LINE_DIMENSION = "LineDimension";
    public static final String LE_BUTT = "Butt";
    public static final String LE_CIRCLE = "Circle";
    public static final String LE_CLOSED_ARROW = "ClosedArrow";
    public static final String LE_DIAMOND = "Diamond";
    public static final String LE_NONE = "None";
    public static final String LE_OPEN_ARROW = "OpenArrow";
    public static final String LE_R_CLOSED_ARROW = "RClosedArrow";
    public static final String LE_R_OPEN_ARROW = "ROpenArrow";
    public static final String LE_SLASH = "Slash";
    public static final String LE_SQUARE = "Square";
    public static final String SUB_TYPE = "Line";

    public PDAnnotationLine() {
        getDictionary().setItem(COSName.SUBTYPE, (COSBase) COSName.getPDFName("Line"));
        setLine(new float[]{0.0f, 0.0f, 0.0f, 0.0f});
    }

    public PDAnnotationLine(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public void setLine(float[] fArr) {
        COSArray cOSArray = new COSArray();
        cOSArray.setFloatArray(fArr);
        getDictionary().setItem("L", (COSBase) cOSArray);
    }

    public float[] getLine() {
        return ((COSArray) getDictionary().getDictionaryObject("L")).toFloatArray();
    }

    public void setStartPointEndingStyle(String str) {
        if (str == null) {
            str = "None";
        }
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject("LE");
        if (cOSArray == null) {
            COSArray cOSArray2 = new COSArray();
            cOSArray2.add((COSBase) COSName.getPDFName(str));
            cOSArray2.add((COSBase) COSName.getPDFName("None"));
            getDictionary().setItem("LE", (COSBase) cOSArray2);
            return;
        }
        cOSArray.setName(0, str);
    }

    public String getStartPointEndingStyle() {
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject("LE");
        return cOSArray != null ? cOSArray.getName(0) : "None";
    }

    public void setEndPointEndingStyle(String str) {
        if (str == null) {
            str = "None";
        }
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject("LE");
        if (cOSArray == null) {
            COSArray cOSArray2 = new COSArray();
            cOSArray2.add((COSBase) COSName.getPDFName("None"));
            cOSArray2.add((COSBase) COSName.getPDFName(str));
            getDictionary().setItem("LE", (COSBase) cOSArray2);
            return;
        }
        cOSArray.setName(1, str);
    }

    public String getEndPointEndingStyle() {
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject("LE");
        return cOSArray != null ? cOSArray.getName(1) : "None";
    }

    public void setInteriorColor(PDColor pDColor) {
        getDictionary().setItem(COSName.IC, (COSBase) pDColor.toCOSArray());
    }

    public PDColor getInteriorColor() {
        return getColor(COSName.IC);
    }

    public void setCaption(boolean z) {
        getDictionary().setBoolean("Cap", z);
    }

    public boolean getCaption() {
        return getDictionary().getBoolean("Cap", false);
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

    public float getLeaderLineLength() {
        return getDictionary().getFloat("LL");
    }

    public void setLeaderLineLength(float f) {
        getDictionary().setFloat("LL", f);
    }

    public float getLeaderLineExtensionLength() {
        return getDictionary().getFloat("LLE");
    }

    public void setLeaderLineExtensionLength(float f) {
        getDictionary().setFloat("LLE", f);
    }

    public float getLeaderLineOffsetLength() {
        return getDictionary().getFloat("LLO");
    }

    public void setLeaderLineOffsetLength(float f) {
        getDictionary().setFloat("LLO", f);
    }

    public String getCaptionPositioning() {
        return getDictionary().getString("CP");
    }

    public void setCaptionPositioning(String str) {
        getDictionary().setString("CP", str);
    }

    public void setCaptionHorizontalOffset(float f) {
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject("CO");
        if (cOSArray == null) {
            COSArray cOSArray2 = new COSArray();
            cOSArray2.setFloatArray(new float[]{f, 0.0f});
            getDictionary().setItem("CO", (COSBase) cOSArray2);
            return;
        }
        cOSArray.set(0, (COSBase) new COSFloat(f));
    }

    public float getCaptionHorizontalOffset() {
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject("CO");
        if (cOSArray != null) {
            return cOSArray.toFloatArray()[0];
        }
        return 0.0f;
    }

    public void setCaptionVerticalOffset(float f) {
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject("CO");
        if (cOSArray == null) {
            COSArray cOSArray2 = new COSArray();
            cOSArray2.setFloatArray(new float[]{0.0f, f});
            getDictionary().setItem("CO", (COSBase) cOSArray2);
            return;
        }
        cOSArray.set(1, (COSBase) new COSFloat(f));
    }

    public float getCaptionVerticalOffset() {
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject("CO");
        if (cOSArray != null) {
            return cOSArray.toFloatArray()[1];
        }
        return 0.0f;
    }
}
