package org.apache.pdfbox.pdmodel.common.filespecification;

import java.io.IOException;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public abstract class PDFileSpecification implements COSObjectable {
    public abstract String getFile();

    public abstract void setFile(String str);

    public static PDFileSpecification createFS(COSBase cOSBase) throws IOException {
        PDFileSpecification pDComplexFileSpecification;
        if (cOSBase == null) {
            return null;
        }
        if (cOSBase instanceof COSString) {
            pDComplexFileSpecification = new PDSimpleFileSpecification((COSString) cOSBase);
        } else if (cOSBase instanceof COSDictionary) {
            pDComplexFileSpecification = new PDComplexFileSpecification((COSDictionary) cOSBase);
        } else {
            throw new IOException("Error: Unknown file specification " + cOSBase);
        }
        return pDComplexFileSpecification;
    }
}
