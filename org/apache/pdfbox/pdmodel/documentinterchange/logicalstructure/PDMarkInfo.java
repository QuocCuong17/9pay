package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public class PDMarkInfo implements COSObjectable {
    private COSDictionary dictionary;

    public PDMarkInfo() {
        this.dictionary = new COSDictionary();
    }

    public PDMarkInfo(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.dictionary;
    }

    public COSDictionary getDictionary() {
        return this.dictionary;
    }

    public boolean isMarked() {
        return this.dictionary.getBoolean("Marked", false);
    }

    public void setMarked(boolean z) {
        this.dictionary.setBoolean("Marked", z);
    }

    public boolean usesUserProperties() {
        return this.dictionary.getBoolean(PDUserAttributeObject.OWNER_USER_PROPERTIES, false);
    }

    public void setUserProperties(boolean z) {
        this.dictionary.setBoolean(PDUserAttributeObject.OWNER_USER_PROPERTIES, z);
    }

    public boolean isSuspect() {
        return this.dictionary.getBoolean("Suspects", false);
    }

    public void setSuspect(boolean z) {
        this.dictionary.setBoolean("Suspects", false);
    }
}
