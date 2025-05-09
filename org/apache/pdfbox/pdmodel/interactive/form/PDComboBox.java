package org.apache.pdfbox.pdmodel.interactive.form;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public final class PDComboBox extends PDChoice {
    private static final int FLAG_EDIT = 262144;

    public PDComboBox(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
        setCombo(true);
    }

    public PDComboBox(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDFieldTreeNode pDFieldTreeNode) {
        super(pDAcroForm, cOSDictionary, pDFieldTreeNode);
    }

    public boolean isEdit() {
        return getDictionary().getFlag(COSName.FF, 262144);
    }

    public void setEdit(boolean z) {
        getDictionary().setFlag(COSName.FF, 262144, z);
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDChoice, org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public void setValue(String str) {
        if (str != null) {
            if (!isEdit() && getOptions().indexOf(str) == -1) {
                throw new IllegalArgumentException("The list box does not contain the given value.");
            }
            getDictionary().setString(COSName.V, str);
            setSelectedOptionsIndex(null);
            return;
        }
        getDictionary().removeItem(COSName.V);
    }
}
