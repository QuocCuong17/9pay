package org.apache.pdfbox.pdmodel.fdf;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public class FDFPageInfo implements COSObjectable {
    private COSDictionary pageInfo;

    public FDFPageInfo() {
        this.pageInfo = new COSDictionary();
    }

    public FDFPageInfo(COSDictionary cOSDictionary) {
        this.pageInfo = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.pageInfo;
    }

    public COSDictionary getCOSDictionary() {
        return this.pageInfo;
    }
}
