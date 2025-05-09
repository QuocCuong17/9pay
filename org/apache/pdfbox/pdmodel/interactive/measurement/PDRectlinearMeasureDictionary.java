package org.apache.pdfbox.pdmodel.interactive.measurement;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;

/* loaded from: classes5.dex */
public class PDRectlinearMeasureDictionary extends PDMeasureDictionary {
    public static final String SUBTYPE = "RL";

    public PDRectlinearMeasureDictionary() {
        setSubtype(SUBTYPE);
    }

    public PDRectlinearMeasureDictionary(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getScaleRatio() {
        return getDictionary().getString(COSName.R);
    }

    public void setScaleRatio(String str) {
        getDictionary().setString(COSName.R, str);
    }

    public PDNumberFormatDictionary[] getChangeXs() {
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject("X");
        if (cOSArray == null) {
            return null;
        }
        PDNumberFormatDictionary[] pDNumberFormatDictionaryArr = new PDNumberFormatDictionary[cOSArray.size()];
        for (int i = 0; i < cOSArray.size(); i++) {
            pDNumberFormatDictionaryArr[i] = new PDNumberFormatDictionary((COSDictionary) cOSArray.get(i));
        }
        return pDNumberFormatDictionaryArr;
    }

    public void setChangeXs(PDNumberFormatDictionary[] pDNumberFormatDictionaryArr) {
        COSArray cOSArray = new COSArray();
        for (PDNumberFormatDictionary pDNumberFormatDictionary : pDNumberFormatDictionaryArr) {
            cOSArray.add(pDNumberFormatDictionary);
        }
        getDictionary().setItem("X", (COSBase) cOSArray);
    }

    public PDNumberFormatDictionary[] getChangeYs() {
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject("Y");
        if (cOSArray == null) {
            return null;
        }
        PDNumberFormatDictionary[] pDNumberFormatDictionaryArr = new PDNumberFormatDictionary[cOSArray.size()];
        for (int i = 0; i < cOSArray.size(); i++) {
            pDNumberFormatDictionaryArr[i] = new PDNumberFormatDictionary((COSDictionary) cOSArray.get(i));
        }
        return pDNumberFormatDictionaryArr;
    }

    public void setChangeYs(PDNumberFormatDictionary[] pDNumberFormatDictionaryArr) {
        COSArray cOSArray = new COSArray();
        for (PDNumberFormatDictionary pDNumberFormatDictionary : pDNumberFormatDictionaryArr) {
            cOSArray.add(pDNumberFormatDictionary);
        }
        getDictionary().setItem("Y", (COSBase) cOSArray);
    }

    public PDNumberFormatDictionary[] getDistances() {
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject("D");
        if (cOSArray == null) {
            return null;
        }
        PDNumberFormatDictionary[] pDNumberFormatDictionaryArr = new PDNumberFormatDictionary[cOSArray.size()];
        for (int i = 0; i < cOSArray.size(); i++) {
            pDNumberFormatDictionaryArr[i] = new PDNumberFormatDictionary((COSDictionary) cOSArray.get(i));
        }
        return pDNumberFormatDictionaryArr;
    }

    public void setDistances(PDNumberFormatDictionary[] pDNumberFormatDictionaryArr) {
        COSArray cOSArray = new COSArray();
        for (PDNumberFormatDictionary pDNumberFormatDictionary : pDNumberFormatDictionaryArr) {
            cOSArray.add(pDNumberFormatDictionary);
        }
        getDictionary().setItem("D", (COSBase) cOSArray);
    }

    public PDNumberFormatDictionary[] getAreas() {
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject(COSName.A);
        if (cOSArray == null) {
            return null;
        }
        PDNumberFormatDictionary[] pDNumberFormatDictionaryArr = new PDNumberFormatDictionary[cOSArray.size()];
        for (int i = 0; i < cOSArray.size(); i++) {
            pDNumberFormatDictionaryArr[i] = new PDNumberFormatDictionary((COSDictionary) cOSArray.get(i));
        }
        return pDNumberFormatDictionaryArr;
    }

    public void setAreas(PDNumberFormatDictionary[] pDNumberFormatDictionaryArr) {
        COSArray cOSArray = new COSArray();
        for (PDNumberFormatDictionary pDNumberFormatDictionary : pDNumberFormatDictionaryArr) {
            cOSArray.add(pDNumberFormatDictionary);
        }
        getDictionary().setItem(COSName.A, (COSBase) cOSArray);
    }

    public PDNumberFormatDictionary[] getAngles() {
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject("T");
        if (cOSArray == null) {
            return null;
        }
        PDNumberFormatDictionary[] pDNumberFormatDictionaryArr = new PDNumberFormatDictionary[cOSArray.size()];
        for (int i = 0; i < cOSArray.size(); i++) {
            pDNumberFormatDictionaryArr[i] = new PDNumberFormatDictionary((COSDictionary) cOSArray.get(i));
        }
        return pDNumberFormatDictionaryArr;
    }

    public void setAngles(PDNumberFormatDictionary[] pDNumberFormatDictionaryArr) {
        COSArray cOSArray = new COSArray();
        for (PDNumberFormatDictionary pDNumberFormatDictionary : pDNumberFormatDictionaryArr) {
            cOSArray.add(pDNumberFormatDictionary);
        }
        getDictionary().setItem("T", (COSBase) cOSArray);
    }

    public PDNumberFormatDictionary[] getLineSloaps() {
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject("S");
        if (cOSArray == null) {
            return null;
        }
        PDNumberFormatDictionary[] pDNumberFormatDictionaryArr = new PDNumberFormatDictionary[cOSArray.size()];
        for (int i = 0; i < cOSArray.size(); i++) {
            pDNumberFormatDictionaryArr[i] = new PDNumberFormatDictionary((COSDictionary) cOSArray.get(i));
        }
        return pDNumberFormatDictionaryArr;
    }

    public void setLineSloaps(PDNumberFormatDictionary[] pDNumberFormatDictionaryArr) {
        COSArray cOSArray = new COSArray();
        for (PDNumberFormatDictionary pDNumberFormatDictionary : pDNumberFormatDictionaryArr) {
            cOSArray.add(pDNumberFormatDictionary);
        }
        getDictionary().setItem("S", (COSBase) cOSArray);
    }

    public float[] getCoordSystemOrigin() {
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject(PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE);
        if (cOSArray != null) {
            return cOSArray.toFloatArray();
        }
        return null;
    }

    public void setCoordSystemOrigin(float[] fArr) {
        COSArray cOSArray = new COSArray();
        cOSArray.setFloatArray(fArr);
        getDictionary().setItem(PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE, (COSBase) cOSArray);
    }

    public float getCYX() {
        return getDictionary().getFloat("CYX");
    }

    public void setCYX(float f) {
        getDictionary().setFloat("CYX", f);
    }
}
