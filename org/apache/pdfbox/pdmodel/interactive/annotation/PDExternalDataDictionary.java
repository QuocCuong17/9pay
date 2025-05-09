package org.apache.pdfbox.pdmodel.interactive.annotation;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public class PDExternalDataDictionary implements COSObjectable {
    private COSDictionary dataDictionary;

    public PDExternalDataDictionary() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dataDictionary = cOSDictionary;
        cOSDictionary.setName(COSName.TYPE, "ExData");
    }

    public PDExternalDataDictionary(COSDictionary cOSDictionary) {
        this.dataDictionary = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.dataDictionary;
    }

    public COSDictionary getDictionary() {
        return this.dataDictionary;
    }

    public String getType() {
        return getDictionary().getNameAsString(COSName.TYPE, "ExData");
    }

    public String getSubtype() {
        return getDictionary().getNameAsString(COSName.SUBTYPE);
    }

    public void setSubtype(String str) {
        getDictionary().setName(COSName.SUBTYPE, str);
    }
}
