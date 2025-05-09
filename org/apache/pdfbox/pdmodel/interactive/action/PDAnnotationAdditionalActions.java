package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;

/* loaded from: classes5.dex */
public class PDAnnotationAdditionalActions implements COSObjectable {
    private COSDictionary actions;

    public PDAnnotationAdditionalActions() {
        this.actions = new COSDictionary();
    }

    public PDAnnotationAdditionalActions(COSDictionary cOSDictionary) {
        this.actions = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.actions;
    }

    public COSDictionary getCOSDictionary() {
        return this.actions;
    }

    public PDAction getE() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("E");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setE(PDAction pDAction) {
        this.actions.setItem("E", pDAction);
    }

    public PDAction getX() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("X");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setX(PDAction pDAction) {
        this.actions.setItem("X", pDAction);
    }

    public PDAction getD() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("D");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setD(PDAction pDAction) {
        this.actions.setItem("D", pDAction);
    }

    public PDAction getU() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject(PDBorderStyleDictionary.STYLE_UNDERLINE);
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setU(PDAction pDAction) {
        this.actions.setItem(PDBorderStyleDictionary.STYLE_UNDERLINE, pDAction);
    }

    public PDAction getFo() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("Fo");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setFo(PDAction pDAction) {
        this.actions.setItem("Fo", pDAction);
    }

    public PDAction getBl() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("Bl");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setBl(PDAction pDAction) {
        this.actions.setItem("Bl", pDAction);
    }

    public PDAction getPO() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("PO");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setPO(PDAction pDAction) {
        this.actions.setItem("PO", pDAction);
    }

    public PDAction getPC() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("PC");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setPC(PDAction pDAction) {
        this.actions.setItem("PC", pDAction);
    }

    public PDAction getPV() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("PV");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setPV(PDAction pDAction) {
        this.actions.setItem("PV", pDAction);
    }

    public PDAction getPI() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("PI");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setPI(PDAction pDAction) {
        this.actions.setItem("PI", pDAction);
    }
}
