package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;

/* loaded from: classes5.dex */
public class PDDocumentCatalogAdditionalActions implements COSObjectable {
    private final COSDictionary actions;

    public PDDocumentCatalogAdditionalActions() {
        this.actions = new COSDictionary();
    }

    public PDDocumentCatalogAdditionalActions(COSDictionary cOSDictionary) {
        this.actions = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.actions;
    }

    public COSDictionary getCOSDictionary() {
        return this.actions;
    }

    public PDAction getWC() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("WC");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setWC(PDAction pDAction) {
        this.actions.setItem("WC", pDAction);
    }

    public PDAction getWS() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("WS");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setWS(PDAction pDAction) {
        this.actions.setItem("WS", pDAction);
    }

    public PDAction getDS() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("DS");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setDS(PDAction pDAction) {
        this.actions.setItem("DS", pDAction);
    }

    public PDAction getWP() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject(StandardStructureTypes.WP);
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setWP(PDAction pDAction) {
        this.actions.setItem(StandardStructureTypes.WP, pDAction);
    }

    public PDAction getDP() {
        COSDictionary cOSDictionary = (COSDictionary) this.actions.getDictionaryObject("DP");
        if (cOSDictionary != null) {
            return PDActionFactory.createAction(cOSDictionary);
        }
        return null;
    }

    public void setDP(PDAction pDAction) {
        this.actions.setItem("DP", pDAction);
    }
}
