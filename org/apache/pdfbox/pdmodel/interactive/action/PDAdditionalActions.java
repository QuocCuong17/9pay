package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;

/* loaded from: classes5.dex */
public class PDAdditionalActions implements COSObjectable {
    private COSDictionary actions;

    public PDAdditionalActions() {
        this.actions = new COSDictionary();
    }

    public PDAdditionalActions(COSDictionary cOSDictionary) {
        this.actions = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.actions;
    }

    public COSDictionary getCOSDictionary() {
        return this.actions;
    }

    public PDAction getF() {
        return PDActionFactory.createAction((COSDictionary) this.actions.getDictionaryObject(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION));
    }

    public void setF(PDAction pDAction) {
        this.actions.setItem(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION, pDAction);
    }
}
