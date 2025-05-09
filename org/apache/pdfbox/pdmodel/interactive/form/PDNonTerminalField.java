package org.apache.pdfbox.pdmodel.interactive.form;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public class PDNonTerminalField extends PDFieldTreeNode {
    public PDNonTerminalField(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
    }

    public PDNonTerminalField(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDFieldTreeNode pDFieldTreeNode) {
        super(pDAcroForm, cOSDictionary, pDFieldTreeNode);
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public int getFieldFlags() {
        COSInteger cOSInteger = (COSInteger) getDictionary().getDictionaryObject(COSName.FF);
        if (cOSInteger != null) {
            return cOSInteger.intValue();
        }
        return 0;
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public String getFieldType() {
        return getDictionary().getNameAsString(COSName.FT);
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public Object getValue() {
        return getDictionary().getNameAsString(COSName.V);
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public void setValue(String str) {
        getDictionary().setString(COSName.V, str);
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public Object getDefaultValue() {
        return getDictionary().getNameAsString(COSName.V);
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public void setDefaultValue(String str) {
        getDictionary().setString(COSName.V, str);
    }
}
