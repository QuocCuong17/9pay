package org.apache.pdfbox.pdmodel.common.filespecification;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSString;

/* loaded from: classes5.dex */
public class PDSimpleFileSpecification extends PDFileSpecification {
    private COSString file;

    public PDSimpleFileSpecification() {
        this.file = new COSString("");
    }

    public PDSimpleFileSpecification(COSString cOSString) {
        this.file = cOSString;
    }

    @Override // org.apache.pdfbox.pdmodel.common.filespecification.PDFileSpecification
    public String getFile() {
        return this.file.getString();
    }

    @Override // org.apache.pdfbox.pdmodel.common.filespecification.PDFileSpecification
    public void setFile(String str) {
        this.file = new COSString(str);
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.file;
    }
}
