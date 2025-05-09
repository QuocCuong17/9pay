package org.apache.pdfbox.pdmodel.interactive.form;

import java.util.Collections;
import java.util.List;
import org.apache.pdfbox.cos.COSDictionary;

/* loaded from: classes5.dex */
public class PDPushButton extends PDButton {
    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDButton, org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public String getDefaultValue() {
        return "";
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public String getValue() {
        return "";
    }

    public PDPushButton(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
        setPushButton(true);
    }

    public PDPushButton(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDFieldTreeNode pDFieldTreeNode) {
        super(pDAcroForm, cOSDictionary, pDFieldTreeNode);
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDButton, org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public void setDefaultValue(String str) {
        if (str != null && !str.isEmpty()) {
            throw new IllegalArgumentException("A PDPushButton shall not use the DV entry in the field dictionary");
        }
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDButton
    public List<String> getOptions() {
        return Collections.emptyList();
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDButton
    public void setOptions(List<String> list) {
        if (list != null && !list.isEmpty()) {
            throw new IllegalArgumentException("A PDPushButton shall not use the Opt entry in the field dictionary");
        }
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public void setValue(String str) {
        if (str != null && !str.isEmpty()) {
            throw new IllegalArgumentException("A PDPushButton shall not use the V entry in the field dictionary");
        }
    }
}
