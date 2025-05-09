package org.apache.pdfbox.pdmodel.documentinterchange.markedcontent;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentGroup;

/* loaded from: classes5.dex */
public class PDPropertyList implements COSObjectable {
    protected final COSDictionary dict;

    public static PDPropertyList create(COSDictionary cOSDictionary) {
        if (COSName.OCG.equals(cOSDictionary.getItem(COSName.TYPE))) {
            return new PDOptionalContentGroup(cOSDictionary);
        }
        return new PDPropertyList(cOSDictionary);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PDPropertyList() {
        this.dict = new COSDictionary();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PDPropertyList(COSDictionary cOSDictionary) {
        this.dict = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dict;
    }
}
