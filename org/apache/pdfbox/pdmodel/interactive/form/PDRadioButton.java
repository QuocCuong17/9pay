package org.apache.pdfbox.pdmodel.interactive.form;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;

/* loaded from: classes5.dex */
public final class PDRadioButton extends PDButton {
    public static final int FLAG_NO_TOGGLE_TO_OFF = 16384;

    public PDRadioButton(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
        setRadioButton(true);
    }

    public PDRadioButton(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDFieldTreeNode pDFieldTreeNode) {
        super(pDAcroForm, cOSDictionary, pDFieldTreeNode);
    }

    public void setRadiosInUnison(boolean z) {
        getDictionary().setFlag(COSName.FF, PDButton.FLAG_RADIOS_IN_UNISON, z);
    }

    public boolean isRadiosInUnison() {
        return getDictionary().getFlag(COSName.FF, PDButton.FLAG_RADIOS_IN_UNISON);
    }

    public String getExportValue() throws IOException {
        List<String> options = getOptions();
        if (options.isEmpty()) {
            return getValue();
        }
        String value = getValue();
        int i = 0;
        for (COSObjectable cOSObjectable : getKids()) {
            if (cOSObjectable instanceof PDCheckbox) {
                if (((PDCheckbox) cOSObjectable).getOnValue().equals(value)) {
                    break;
                }
                i++;
            }
        }
        return i <= options.size() ? options.get(i) : "";
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
            removeInheritableAttribute(COSName.V);
            return;
        }
        COSName pDFName = COSName.getPDFName(str);
        setInheritableAttribute(COSName.V, pDFName);
        for (COSObjectable cOSObjectable : getKids()) {
            if (cOSObjectable instanceof PDAnnotationWidget) {
                if (((COSDictionary) ((PDAnnotationWidget) cOSObjectable).getAppearance().getNormalAppearance().getCOSObject()).containsKey(pDFName)) {
                    ((COSDictionary) cOSObjectable.getCOSObject()).setName(COSName.AS, str);
                } else {
                    ((COSDictionary) cOSObjectable.getCOSObject()).setName(COSName.AS, "Off");
                }
            }
        }
    }
}
