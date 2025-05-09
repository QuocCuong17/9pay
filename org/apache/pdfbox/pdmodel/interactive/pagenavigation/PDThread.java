package org.apache.pdfbox.pdmodel.interactive.pagenavigation;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;

/* loaded from: classes5.dex */
public class PDThread implements COSObjectable {
    private COSDictionary thread;

    public PDThread(COSDictionary cOSDictionary) {
        this.thread = cOSDictionary;
    }

    public PDThread() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.thread = cOSDictionary;
        cOSDictionary.setName("Type", "Thread");
    }

    public COSDictionary getDictionary() {
        return this.thread;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.thread;
    }

    public PDDocumentInformation getThreadInfo() {
        COSDictionary cOSDictionary = (COSDictionary) this.thread.getDictionaryObject("I");
        if (cOSDictionary != null) {
            return new PDDocumentInformation(cOSDictionary);
        }
        return null;
    }

    public void setThreadInfo(PDDocumentInformation pDDocumentInformation) {
        this.thread.setItem("I", pDDocumentInformation);
    }

    public PDThreadBead getFirstBead() {
        COSDictionary cOSDictionary = (COSDictionary) this.thread.getDictionaryObject(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION);
        if (cOSDictionary != null) {
            return new PDThreadBead(cOSDictionary);
        }
        return null;
    }

    public void setFirstBead(PDThreadBead pDThreadBead) {
        if (pDThreadBead != null) {
            pDThreadBead.setThread(this);
        }
        this.thread.setItem(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION, pDThreadBead);
    }
}
