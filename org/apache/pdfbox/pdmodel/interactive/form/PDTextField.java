package org.apache.pdfbox.pdmodel.interactive.form;

import java.io.IOException;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.common.PDTextStream;

/* loaded from: classes5.dex */
public final class PDTextField extends PDVariableText {
    private static final int FLAG_COMB = 16777216;
    private static final int FLAG_DO_NOT_SCROLL = 8388608;
    private static final int FLAG_DO_NOT_SPELL_CHECK = 4194304;
    private static final int FLAG_FILE_SELECT = 1048576;
    private static final int FLAG_MULTILINE = 4096;
    private static final int FLAG_PASSWORD = 8192;
    private static final int FLAG_RICH_TEXT = 33554432;

    public PDTextField(PDAcroForm pDAcroForm) {
        super(pDAcroForm);
        getDictionary().setItem(COSName.FT, (COSBase) COSName.TX);
    }

    public PDTextField(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDFieldTreeNode pDFieldTreeNode) {
        super(pDAcroForm, cOSDictionary, pDFieldTreeNode);
    }

    public boolean isMultiline() {
        return getDictionary().getFlag(COSName.FF, 4096);
    }

    public void setMultiline(boolean z) {
        getDictionary().setFlag(COSName.FF, 4096, z);
    }

    public boolean isPassword() {
        return getDictionary().getFlag(COSName.FF, 8192);
    }

    public void setPassword(boolean z) {
        getDictionary().setFlag(COSName.FF, 8192, z);
    }

    public boolean isFileSelect() {
        return getDictionary().getFlag(COSName.FF, 1048576);
    }

    public void setFileSelect(boolean z) {
        getDictionary().setFlag(COSName.FF, 1048576, z);
    }

    public boolean doNotSpellCheck() {
        return getDictionary().getFlag(COSName.FF, 4194304);
    }

    public void setDoNotSpellCheck(boolean z) {
        getDictionary().setFlag(COSName.FF, 4194304, z);
    }

    public boolean doNotScroll() {
        return getDictionary().getFlag(COSName.FF, 8388608);
    }

    public void setDoNotScroll(boolean z) {
        getDictionary().setFlag(COSName.FF, 8388608, z);
    }

    public boolean isComb() {
        return getDictionary().getFlag(COSName.FF, 16777216);
    }

    public void setComb(boolean z) {
        getDictionary().setFlag(COSName.FF, 16777216, z);
    }

    public boolean isRichText() {
        return getDictionary().getFlag(COSName.FF, 33554432);
    }

    public void setRichText(boolean z) {
        getDictionary().setFlag(COSName.FF, 33554432, z);
    }

    public int getMaxLen() {
        return getDictionary().getInt(COSName.MAX_LEN);
    }

    public void setMaxLen(int i) {
        getDictionary().setInt(COSName.MAX_LEN, i);
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public void setDefaultValue(String str) {
        if (str != null) {
            setInheritableAttribute(COSName.DV, new COSString(str));
        } else {
            removeInheritableAttribute(COSName.DV);
        }
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public String getDefaultValue() {
        COSBase inheritableAttribute = getInheritableAttribute(COSName.DV);
        return inheritableAttribute instanceof COSString ? ((COSString) inheritableAttribute).getString() : "";
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public void setValue(String str) throws IOException {
        if (str != null && !str.isEmpty()) {
            setInheritableAttribute(COSName.V, new COSString(str));
        } else {
            removeInheritableAttribute(COSName.DV);
        }
        updateFieldAppearances();
    }

    public void setValue(PDTextStream pDTextStream) throws IOException {
        if (pDTextStream != null) {
            setInheritableAttribute(COSName.V, pDTextStream.getCOSObject());
        } else {
            removeInheritableAttribute(COSName.V);
        }
        updateFieldAppearances();
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode
    public String getValue() throws IOException {
        PDTextStream asTextStream = getAsTextStream(getInheritableAttribute(COSName.V));
        return asTextStream != null ? asTextStream.getAsString() : "";
    }

    public PDTextStream getValueAsStream() throws IOException {
        return getAsTextStream(getInheritableAttribute(COSName.V));
    }
}
