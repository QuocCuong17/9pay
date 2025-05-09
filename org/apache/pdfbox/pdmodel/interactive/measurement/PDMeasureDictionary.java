package org.apache.pdfbox.pdmodel.interactive.measurement;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public class PDMeasureDictionary implements COSObjectable {
    public static final String TYPE = "Measure";
    private COSDictionary measureDictionary;

    public String getType() {
        return TYPE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PDMeasureDictionary() {
        this.measureDictionary = new COSDictionary();
        getDictionary().setName(COSName.TYPE, TYPE);
    }

    public PDMeasureDictionary(COSDictionary cOSDictionary) {
        this.measureDictionary = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.measureDictionary;
    }

    public COSDictionary getDictionary() {
        return this.measureDictionary;
    }

    public String getSubtype() {
        return getDictionary().getNameAsString(COSName.SUBTYPE, PDRectlinearMeasureDictionary.SUBTYPE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setSubtype(String str) {
        getDictionary().setName(COSName.SUBTYPE, str);
    }
}
