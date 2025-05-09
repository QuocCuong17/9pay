package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public class PDURIDictionary implements COSObjectable {
    private COSDictionary uriDictionary;

    public PDURIDictionary() {
        this.uriDictionary = new COSDictionary();
    }

    public PDURIDictionary(COSDictionary cOSDictionary) {
        this.uriDictionary = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.uriDictionary;
    }

    public COSDictionary getDictionary() {
        return this.uriDictionary;
    }

    public String getBase() {
        return getDictionary().getString("Base");
    }

    public void setBase(String str) {
        getDictionary().setString("Base", str);
    }
}
