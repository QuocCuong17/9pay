package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;

/* loaded from: classes5.dex */
public class PDFormFieldAdditionalActions implements COSObjectable {
    private final COSDictionary actions;

    public PDFormFieldAdditionalActions() {
        this.actions = new COSDictionary();
    }

    public PDFormFieldAdditionalActions(COSDictionary cOSDictionary) {
        this.actions = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.actions;
    }

    public COSDictionary getCOSDictionary() {
        return this.actions;
    }

    public PDAction getK() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("K");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setK(PDAction pDAction) {
        this.actions.setItem("K", pDAction);
    }

    public PDAction getF() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION);
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setF(PDAction pDAction) {
        this.actions.setItem(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION, pDAction);
    }

    public PDAction getV() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("V");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setV(PDAction pDAction) {
        this.actions.setItem("V", pDAction);
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
