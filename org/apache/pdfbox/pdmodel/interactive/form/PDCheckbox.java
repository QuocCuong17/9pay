package org.apache.pdfbox.pdmodel.interactive.form;

import java.io.IOException;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public final class PDCheckbox extends PDButton {
    public PDCheckbox(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
    }

    public PDCheckbox(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDFieldTreeNode pDFieldTreeNode) {
        super(pDAcroForm, cOSDictionary, pDFieldTreeNode);
    }

    public boolean isChecked() throws IOException {
        String value;
        COSName cOSName;
        String onValue = getOnValue();
        try {
            value = getValue();
            cOSName = (COSName) getDictionary().getDictionaryObject(COSName.AS);
        } catch (IOException unused) {
        }
        return (cOSName == null || value == null || !cOSName.getName().equals(onValue)) ? false : true;
    }

    public void check() {
        String onValue = getOnValue();
        setValue(onValue);
        getDictionary().setItem(COSName.AS, (COSBase) COSName.getPDFName(onValue));
    }

    public void unCheck() {
        getDictionary().setItem(COSName.AS, (COSBase) PDButton.OFF);
    }

    public String getOffValue() {
        return PDButton.OFF.getName();
    }

    public String getOnValue() {
        COSBase dictionaryObject = ((COSDictionary) getDictionary().getDictionaryObject(COSName.AP)).getDictionaryObject(COSName.N);
        if (!(dictionaryObject instanceof COSDictionary)) {
            return "";
        }
        for (COSName cOSName : ((COSDictionary) dictionaryObject).keySet()) {
            if (!cOSName.equals(PDButton.OFF)) {
                return cOSName.getName();
            }
        }
        return "";
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public String getValue() throws IOException {
        COSBase inheritableAttribute = getInheritableAttribute(COSName.V);
        if (inheritableAttribute == null) {
            return "";
        }
        if (inheritableAttribute instanceof COSName) {
            return ((COSName) inheritableAttribute).getName();
        }
        throw new IOException("Expected a COSName entry but got " + inheritableAttribute.getClass().getName());
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public void setValue(String str) {
        if (str == null) {
            getDictionary().removeItem(COSName.V);
            getDictionary().setItem(COSName.AS, (COSBase) PDButton.OFF);
        } else {
            COSName pDFName = COSName.getPDFName(str);
            getDictionary().setItem(COSName.V, (COSBase) pDFName);
            getDictionary().setItem(COSName.AS, (COSBase) pDFName);
        }
    }
}
