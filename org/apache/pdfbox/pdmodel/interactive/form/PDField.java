package org.apache.pdfbox.pdmodel.interactive.form;

import java.io.IOException;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.interactive.action.PDFormFieldAdditionalActions;

/* loaded from: classes5.dex */
public abstract class PDField extends PDFieldTreeNode {
    /* JADX INFO: Access modifiers changed from: protected */
    public PDField(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PDField(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDFieldTreeNode pDFieldTreeNode) {
        super(pDAcroForm, cOSDictionary, pDFieldTreeNode);
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public String toString() {
        return "" + getDictionary().getDictionaryObject(COSName.V);
    }

    public void setActions(PDFormFieldAdditionalActions pDFormFieldAdditionalActions) {
        getDictionary().setItem(COSName.AA, pDFormFieldAdditionalActions);
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public int getFieldFlags() {
        COSInteger cOSInteger = (COSInteger) getDictionary().getDictionaryObject(COSName.FF);
        if (cOSInteger != null) {
            return cOSInteger.intValue();
        }
        if (getParent() != null) {
            return getParent().getFieldFlags();
        }
        return 0;
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public String getFieldType() {
        String nameAsString = getDictionary().getNameAsString(COSName.FT);
        return (nameAsString != null || getParent() == null) ? nameAsString : getParent().getFieldType();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateFieldAppearances() throws IOException {
        if (getAcroForm().isNeedAppearances()) {
            return;
        }
        AppearanceGenerator.generateFieldAppearances(this);
    }
}
