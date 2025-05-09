package org.apache.pdfbox.pdmodel.interactive.form;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public final class PDListBox extends PDChoice {
    public PDListBox(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
    }

    public PDListBox(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDFieldTreeNode pDFieldTreeNode) {
        super(pDAcroForm, cOSDictionary, pDFieldTreeNode);
    }

    public int getTopIndex() {
        return getDictionary().getInt(COSName.TI, 0);
    }

    public void setTopIndex(Integer num) {
        if (num != null) {
            getDictionary().setInt(COSName.TI, num.intValue());
        } else {
            getDictionary().removeItem(COSName.TI);
        }
    }
}
