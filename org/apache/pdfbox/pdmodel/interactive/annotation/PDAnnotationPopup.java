package org.apache.pdfbox.pdmodel.interactive.annotation;

import java.io.IOException;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public class PDAnnotationPopup extends PDAnnotation {
    public static final String SUB_TYPE = "Popup";

    public PDAnnotationPopup() {
        getDictionary().setItem(COSName.SUBTYPE, (COSBase) COSName.getPDFName(SUB_TYPE));
    }

    public PDAnnotationPopup(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public void setOpen(boolean z) {
        getDictionary().setBoolean("Open", z);
    }

    public boolean getOpen() {
        return getDictionary().getBoolean("Open", false);
    }

    public void setParent(PDAnnotationMarkup pDAnnotationMarkup) {
        getDictionary().setItem(COSName.PARENT, (COSBase) pDAnnotationMarkup.getDictionary());
    }

    public PDAnnotationMarkup getParent() {
        try {
            return (PDAnnotationMarkup) PDAnnotation.createAnnotation(getDictionary().getDictionaryObject(COSName.PARENT, COSName.P));
        } catch (IOException unused) {
            return null;
        }
    }
}
