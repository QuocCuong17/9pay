package org.apache.pdfbox.pdmodel.graphics.optionalcontent;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;

/* loaded from: classes5.dex */
public class PDOptionalContentGroup extends PDPropertyList {
    public PDOptionalContentGroup(String str) {
        this.dict.setItem(COSName.TYPE, (COSBase) COSName.OCG);
        setName(str);
    }

    public PDOptionalContentGroup(COSDictionary cOSDictionary) {
        super(cOSDictionary);
        if (cOSDictionary.getItem(COSName.TYPE).equals(COSName.OCG)) {
            return;
        }
        throw new IllegalArgumentException("Provided dictionary is not of type '" + COSName.OCG + "'");
    }

    public String getName() {
        return this.dict.getString(COSName.NAME);
    }

    public void setName(String str) {
        this.dict.setString(COSName.NAME, str);
    }

    public String toString() {
        return super.toString() + " (" + getName() + ")";
    }
}
