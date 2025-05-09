package org.apache.pdfbox.pdmodel.interactive.form;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSeedValue;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;

/* loaded from: classes5.dex */
public class PDSignatureField extends PDField {
    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public Object getDefaultValue() {
        return null;
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDField, org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public String toString() {
        return "PDSignatureField";
    }

    public PDSignatureField(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDFieldTreeNode pDFieldTreeNode) {
        super(pDAcroForm, cOSDictionary, pDFieldTreeNode);
    }

    public PDSignatureField(PDAcroForm pDAcroForm) throws IOException {
        super(pDAcroForm);
        getDictionary().setItem(COSName.FT, (COSBase) COSName.SIG);
        getWidget().setLocked(true);
        getWidget().setPrinted(true);
        setPartialName(generatePartialName());
    }

    private String generatePartialName() {
        List<PDFieldTreeNode> fields = getAcroForm().getFields();
        HashSet hashSet = new HashSet();
        for (PDFieldTreeNode pDFieldTreeNode : fields) {
            if (pDFieldTreeNode instanceof PDSignatureField) {
                hashSet.add(pDFieldTreeNode.getPartialName());
            }
        }
        int i = 1;
        while (true) {
            if (!hashSet.contains("Signature" + i)) {
                return "Signature" + i;
            }
            i++;
        }
    }

    public void setSignature(PDSignature pDSignature) {
        setValue(pDSignature);
    }

    public PDSignature getSignature() {
        return getValue();
    }

    public void setValue(PDSignature pDSignature) {
        if (pDSignature == null) {
            getDictionary().removeItem(COSName.V);
        } else {
            getDictionary().setItem(COSName.V, pDSignature);
        }
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public void setValue(String str) {
        throw new IllegalArgumentException("Signature fields don't support a string for the value entry.");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public PDSignature getValue() {
        COSBase dictionaryObject = getDictionary().getDictionaryObject(COSName.V);
        if (dictionaryObject == null) {
            return null;
        }
        return new PDSignature((COSDictionary) dictionaryObject);
    }

    public PDSeedValue getSeedValue() {
        COSDictionary cOSDictionary = (COSDictionary) getDictionary().getDictionaryObject(COSName.SV);
        if (cOSDictionary != null) {
            return new PDSeedValue(cOSDictionary);
        }
        return null;
    }

    public void setSeedValue(PDSeedValue pDSeedValue) {
        if (pDSeedValue != null) {
            getDictionary().setItem(COSName.SV, pDSeedValue.getCOSObject());
        }
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public void setDefaultValue(String str) {
        throw new IllegalArgumentException("Signature fields don't support the \"DV\" entry.");
    }
}
