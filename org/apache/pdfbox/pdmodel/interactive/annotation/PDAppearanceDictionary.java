package org.apache.pdfbox.pdmodel.interactive.annotation;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public class PDAppearanceDictionary implements COSObjectable {
    private final COSDictionary dictionary;

    public PDAppearanceDictionary() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setItem(COSName.N, (COSBase) new COSDictionary());
    }

    public PDAppearanceDictionary(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public PDAppearanceEntry getNormalAppearance() {
        COSBase dictionaryObject = this.dictionary.getDictionaryObject(COSName.N);
        if (dictionaryObject == null) {
            return null;
        }
        return new PDAppearanceEntry(dictionaryObject);
    }

    public void setNormalAppearance(PDAppearanceEntry pDAppearanceEntry) {
        this.dictionary.setItem(COSName.N, pDAppearanceEntry);
    }

    public void setNormalAppearance(PDAppearanceStream pDAppearanceStream) {
        this.dictionary.setItem(COSName.N, pDAppearanceStream);
    }

    public PDAppearanceEntry getRolloverAppearance() {
        COSBase dictionaryObject = this.dictionary.getDictionaryObject(COSName.R);
        if (dictionaryObject == null) {
            return getNormalAppearance();
        }
        return new PDAppearanceEntry(dictionaryObject);
    }

    public void setRolloverAppearance(PDAppearanceEntry pDAppearanceEntry) {
        this.dictionary.setItem(COSName.R, pDAppearanceEntry);
    }

    public void setRolloverAppearance(PDAppearanceStream pDAppearanceStream) {
        this.dictionary.setItem(COSName.R, pDAppearanceStream);
    }

    public PDAppearanceEntry getDownAppearance() {
        COSBase dictionaryObject = this.dictionary.getDictionaryObject(COSName.D);
        if (dictionaryObject == null) {
            return getNormalAppearance();
        }
        return new PDAppearanceEntry(dictionaryObject);
    }

    public void setDownAppearance(PDAppearanceEntry pDAppearanceEntry) {
        this.dictionary.setItem(COSName.D, pDAppearanceEntry);
    }

    public void setDownAppearance(PDAppearanceStream pDAppearanceStream) {
        this.dictionary.setItem(COSName.D, pDAppearanceStream);
    }
}
