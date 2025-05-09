package org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination;

import java.io.IOException;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;

/* loaded from: classes5.dex */
public class PDNamedDestination extends PDDestination {
    private COSBase namedDestination;

    public PDNamedDestination(COSString cOSString) {
        this.namedDestination = cOSString;
    }

    public PDNamedDestination(COSName cOSName) {
        this.namedDestination = cOSName;
    }

    public PDNamedDestination() {
    }

    public PDNamedDestination(String str) {
        this.namedDestination = new COSString(str);
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.namedDestination;
    }

    public String getNamedDestination() {
        COSBase cOSBase = this.namedDestination;
        if (cOSBase instanceof COSString) {
            return ((COSString) cOSBase).getString();
        }
        if (cOSBase instanceof COSName) {
            return ((COSName) cOSBase).getName();
        }
        return null;
    }

    public void setNamedDestination(String str) throws IOException {
        if (str == null) {
            this.namedDestination = null;
        } else {
            this.namedDestination = new COSString(str);
        }
    }
}
