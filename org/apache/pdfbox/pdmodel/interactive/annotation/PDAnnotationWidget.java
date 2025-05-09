package org.apache.pdfbox.pdmodel.interactive.annotation;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.interactive.action.PDAction;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionFactory;
import org.apache.pdfbox.pdmodel.interactive.action.PDAnnotationAdditionalActions;

/* loaded from: classes5.dex */
public class PDAnnotationWidget extends PDAnnotation {
    public static final String SUB_TYPE = "Widget";

    public PDAnnotationWidget() {
        getDictionary().setName(COSName.SUBTYPE, SUB_TYPE);
    }

    public PDAnnotationWidget(COSDictionary cOSDictionary) {
        super(cOSDictionary);
        getDictionary().setName(COSName.SUBTYPE, SUB_TYPE);
    }

    public String getHighlightingMode() {
        return getDictionary().getNameAsString(COSName.H, "I");
    }

    public void setHighlightingMode(String str) {
        if (str == null || "N".equals(str) || "I".equals(str) || PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE.equals(str) || "P".equals(str) || "T".equals(str)) {
            getDictionary().setName(COSName.H, str);
            return;
        }
        throw new IllegalArgumentException("Valid values for highlighting mode are 'N', 'N', 'O', 'P' or 'T'");
    }

    public PDAppearanceCharacteristicsDictionary getAppearanceCharacteristics() {
        COSBase dictionaryObject = getDictionary().getDictionaryObject(COSName.MK);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDAppearanceCharacteristicsDictionary((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public void setAppearanceCharacteristics(PDAppearanceCharacteristicsDictionary pDAppearanceCharacteristicsDictionary) {
        getDictionary().setItem(COSName.MK, pDAppearanceCharacteristicsDictionary);
    }

    public PDAction getAction() {
        return PDActionFactory.createAction((COSDictionary) getDictionary().getDictionaryObject(COSName.A));
    }

    public void setAction(PDAction pDAction) {
        getDictionary().setItem(COSName.A, pDAction);
    }

    public PDAnnotationAdditionalActions getActions() {
        COSDictionary cOSDictionary = (COSDictionary) getDictionary().getDictionaryObject("AA");
        if (cOSDictionary != null) {
            return new PDAnnotationAdditionalActions(cOSDictionary);
        }
        return null;
    }

    public void setActions(PDAnnotationAdditionalActions pDAnnotationAdditionalActions) {
        getDictionary().setItem("AA", pDAnnotationAdditionalActions);
    }

    public void setBorderStyle(PDBorderStyleDictionary pDBorderStyleDictionary) {
        getDictionary().setItem("BS", pDBorderStyleDictionary);
    }

    public PDBorderStyleDictionary getBorderStyle() {
        COSDictionary cOSDictionary = (COSDictionary) getDictionary().getItem(COSName.BS);
        if (cOSDictionary != null) {
            return new PDBorderStyleDictionary(cOSDictionary);
        }
        return null;
    }
}
