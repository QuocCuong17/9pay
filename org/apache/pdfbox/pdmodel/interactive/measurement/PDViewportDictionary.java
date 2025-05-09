package org.apache.pdfbox.pdmodel.interactive.measurement;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

/* loaded from: classes5.dex */
public class PDViewportDictionary implements COSObjectable {
    public static final String TYPE = "Viewport";
    private COSDictionary viewportDictionary;

    public String getType() {
        return TYPE;
    }

    public PDViewportDictionary() {
        this.viewportDictionary = new COSDictionary();
    }

    public PDViewportDictionary(COSDictionary cOSDictionary) {
        this.viewportDictionary = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.viewportDictionary;
    }

    public COSDictionary getDictionary() {
        return this.viewportDictionary;
    }

    public PDRectangle getBBox() {
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject("BBox");
        if (cOSArray != null) {
            return new PDRectangle(cOSArray);
        }
        return null;
    }

    public void setBBox(PDRectangle pDRectangle) {
        getDictionary().setItem("BBox", pDRectangle);
    }

    public String getName() {
        return getDictionary().getNameAsString(COSName.NAME);
    }

    public void setName(String str) {
        getDictionary().setName(COSName.NAME, str);
    }

    public PDMeasureDictionary getMeasure() {
        COSDictionary cOSDictionary = (COSDictionary) getDictionary().getDictionaryObject(PDMeasureDictionary.TYPE);
        if (cOSDictionary != null) {
            return new PDMeasureDictionary(cOSDictionary);
        }
        return null;
    }

    public void setMeasure(PDMeasureDictionary pDMeasureDictionary) {
        getDictionary().setItem(PDMeasureDictionary.TYPE, pDMeasureDictionary);
    }
}
