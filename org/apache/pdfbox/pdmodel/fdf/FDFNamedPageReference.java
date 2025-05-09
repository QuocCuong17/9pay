package org.apache.pdfbox.pdmodel.fdf;

import java.io.IOException;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.filespecification.PDFileSpecification;

/* loaded from: classes5.dex */
public class FDFNamedPageReference implements COSObjectable {
    private COSDictionary ref;

    public FDFNamedPageReference() {
        this.ref = new COSDictionary();
    }

    public FDFNamedPageReference(COSDictionary cOSDictionary) {
        this.ref = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.ref;
    }

    public COSDictionary getCOSDictionary() {
        return this.ref;
    }

    public String getName() {
        return this.ref.getString(COSName.NAME);
    }

    public void setName(String str) {
        this.ref.setString(COSName.NAME, str);
    }

    public PDFileSpecification getFileSpecification() throws IOException {
        return PDFileSpecification.createFS(this.ref.getDictionaryObject(COSName.F));
    }

    public void setFileSpecification(PDFileSpecification pDFileSpecification) {
        this.ref.setItem(COSName.F, pDFileSpecification);
    }
}
