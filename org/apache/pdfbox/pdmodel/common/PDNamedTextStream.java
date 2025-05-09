package org.apache.pdfbox.pdmodel.common;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public class PDNamedTextStream implements DualCOSObjectable {
    private PDTextStream stream;
    private COSName streamName;

    public PDNamedTextStream() {
    }

    public PDNamedTextStream(COSName cOSName, COSBase cOSBase) {
        this.streamName = cOSName;
        this.stream = PDTextStream.createTextStream(cOSBase);
    }

    public String getName() {
        COSName cOSName = this.streamName;
        if (cOSName != null) {
            return cOSName.getName();
        }
        return null;
    }

    public void setName(String str) {
        this.streamName = COSName.getPDFName(str);
    }

    public PDTextStream getStream() {
        return this.stream;
    }

    public void setStream(PDTextStream pDTextStream) {
        this.stream = pDTextStream;
    }

    @Override // org.apache.pdfbox.pdmodel.common.DualCOSObjectable
    public COSBase getFirstCOSObject() {
        return this.streamName;
    }

    @Override // org.apache.pdfbox.pdmodel.common.DualCOSObjectable
    public COSBase getSecondCOSObject() {
        PDTextStream pDTextStream = this.stream;
        if (pDTextStream != null) {
            return pDTextStream.getCOSObject();
        }
        return null;
    }
}
