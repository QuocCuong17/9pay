package org.apache.pdfbox.pdmodel.interactive.pagenavigation;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

/* loaded from: classes5.dex */
public class PDThreadBead implements COSObjectable {
    private COSDictionary bead;

    public PDThreadBead(COSDictionary cOSDictionary) {
        this.bead = cOSDictionary;
    }

    public PDThreadBead() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.bead = cOSDictionary;
        cOSDictionary.setName("Type", "Bead");
        setNextBead(this);
        setPreviousBead(this);
    }

    public COSDictionary getDictionary() {
        return this.bead;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.bead;
    }

    public PDThread getThread() {
        COSDictionary cOSDictionary = (COSDictionary) this.bead.getDictionaryObject("T");
        if (cOSDictionary != null) {
            return new PDThread(cOSDictionary);
        }
        return null;
    }

    public void setThread(PDThread pDThread) {
        this.bead.setItem("T", pDThread);
    }

    public PDThreadBead getNextBead() {
        return new PDThreadBead((COSDictionary) this.bead.getDictionaryObject("N"));
    }

    protected void setNextBead(PDThreadBead pDThreadBead) {
        this.bead.setItem("N", pDThreadBead);
    }

    public PDThreadBead getPreviousBead() {
        return new PDThreadBead((COSDictionary) this.bead.getDictionaryObject("V"));
    }

    protected void setPreviousBead(PDThreadBead pDThreadBead) {
        this.bead.setItem("V", pDThreadBead);
    }

    public void appendBead(PDThreadBead pDThreadBead) {
        PDThreadBead nextBead = getNextBead();
        nextBead.setPreviousBead(pDThreadBead);
        pDThreadBead.setNextBead(nextBead);
        setNextBead(pDThreadBead);
        pDThreadBead.setPreviousBead(this);
    }

    public PDPage getPage() {
        COSDictionary cOSDictionary = (COSDictionary) this.bead.getDictionaryObject("P");
        if (cOSDictionary != null) {
            return new PDPage(cOSDictionary);
        }
        return null;
    }

    public void setPage(PDPage pDPage) {
        this.bead.setItem("P", pDPage);
    }

    public PDRectangle getRectangle() {
        COSArray cOSArray = (COSArray) this.bead.getDictionaryObject(COSName.R);
        if (cOSArray != null) {
            return new PDRectangle(cOSArray);
        }
        return null;
    }

    public void setRectangle(PDRectangle pDRectangle) {
        this.bead.setItem(COSName.R, pDRectangle);
    }
}
