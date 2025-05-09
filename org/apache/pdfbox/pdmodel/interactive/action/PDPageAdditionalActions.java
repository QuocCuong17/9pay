package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;

/* loaded from: classes5.dex */
public class PDPageAdditionalActions implements COSObjectable {
    private final COSDictionary actions;

    public PDPageAdditionalActions() {
        this.actions = new COSDictionary();
    }

    public PDPageAdditionalActions(COSDictionary cOSDictionary) {
        this.actions = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.actions;
    }

    public COSDictionary getCOSDictionary() {
        return this.actions;
    }

    public PDAction getO() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject(PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE);
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setO(PDAction pDAction) {
        this.actions.setItem(PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE, pDAction);
    }

    public PDAction getC() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("C");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setC(PDAction pDAction) {
        this.actions.setItem("C", pDAction);
    }
}
