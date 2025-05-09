package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public class PDMarkedContentReference implements COSObjectable {
    public static final String TYPE = "MCR";
    private COSDictionary dictionary;

    protected COSDictionary getCOSDictionary() {
        return this.dictionary;
    }

    public PDMarkedContentReference() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setName(COSName.TYPE, TYPE);
    }

    public PDMarkedContentReference(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.dictionary;
    }

    public PDPage getPage() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSDictionary().getDictionaryObject(COSName.PG);
        if (cOSDictionary != null) {
            return new PDPage(cOSDictionary);
        }
        return null;
    }

    public void setPage(PDPage pDPage) {
        getCOSDictionary().setItem(COSName.PG, pDPage);
    }

    public int getMCID() {
        return getCOSDictionary().getInt(COSName.MCID);
    }

    public void setMCID(int i) {
        getCOSDictionary().setInt(COSName.MCID, i);
    }

    public String toString() {
        return "mcid=" + getMCID();
    }
}
