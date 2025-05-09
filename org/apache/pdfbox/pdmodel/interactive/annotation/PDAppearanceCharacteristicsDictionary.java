package org.apache.pdfbox.pdmodel.interactive.annotation;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;

/* loaded from: classes5.dex */
public class PDAppearanceCharacteristicsDictionary implements COSObjectable {
    private final COSDictionary dictionary;

    public PDAppearanceCharacteristicsDictionary(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    public COSDictionary getDictionary() {
        return this.dictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.dictionary;
    }

    public int getRotation() {
        return getDictionary().getInt(COSName.R, 0);
    }

    public void setRotation(int i) {
        getDictionary().setInt(COSName.R, i);
    }

    public PDColor getBorderColour() {
        return getColor(COSName.BC);
    }

    public void setBorderColour(PDColor pDColor) {
        getDictionary().setItem(COSName.BC, (COSBase) pDColor.toCOSArray());
    }

    public PDColor getBackground() {
        return getColor(COSName.BG);
    }

    public void setBackground(PDColor pDColor) {
        getDictionary().setItem(COSName.BG, (COSBase) pDColor.toCOSArray());
    }

    public String getNormalCaption() {
        return getDictionary().getString("CA");
    }

    public void setNormalCaption(String str) {
        getDictionary().setString("CA", str);
    }

    public String getRolloverCaption() {
        return getDictionary().getString("RC");
    }

    public void setRolloverCaption(String str) {
        getDictionary().setString("RC", str);
    }

    public String getAlternateCaption() {
        return getDictionary().getString("AC");
    }

    public void setAlternateCaption(String str) {
        getDictionary().setString("AC", str);
    }

    public PDFormXObject getNormalIcon() {
        COSBase dictionaryObject = getDictionary().getDictionaryObject("I");
        if (dictionaryObject instanceof COSStream) {
            return new PDFormXObject(new PDStream((COSStream) dictionaryObject), "I");
        }
        return null;
    }

    public PDFormXObject getRolloverIcon() {
        COSBase dictionaryObject = getDictionary().getDictionaryObject("RI");
        if (dictionaryObject instanceof COSStream) {
            return new PDFormXObject(new PDStream((COSStream) dictionaryObject), "RI");
        }
        return null;
    }

    public PDFormXObject getAlternateIcon() {
        COSBase dictionaryObject = getDictionary().getDictionaryObject("IX");
        if (dictionaryObject instanceof COSStream) {
            return new PDFormXObject(new PDStream((COSStream) dictionaryObject), "IX");
        }
        return null;
    }

    private PDColor getColor(COSName cOSName) {
        COSBase item = getDictionary().getItem(cOSName);
        PDColorSpace pDColorSpace = null;
        if (!(item instanceof COSArray)) {
            return null;
        }
        COSArray cOSArray = (COSArray) item;
        int size = cOSArray.size();
        if (size == 1) {
            pDColorSpace = PDDeviceGray.INSTANCE;
        } else if (size == 3) {
            pDColorSpace = PDDeviceRGB.INSTANCE;
        }
        return new PDColor(cOSArray, pDColorSpace);
    }
}
