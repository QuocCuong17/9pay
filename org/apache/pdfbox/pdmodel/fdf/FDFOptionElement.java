package org.apache.pdfbox.pdmodel.fdf;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public class FDFOptionElement implements COSObjectable {
    private COSArray option;

    public FDFOptionElement() {
        COSArray cOSArray = new COSArray();
        this.option = cOSArray;
        cOSArray.add((COSBase) new COSString(""));
        this.option.add((COSBase) new COSString(""));
    }

    public FDFOptionElement(COSArray cOSArray) {
        this.option = cOSArray;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.option;
    }

    public COSArray getCOSArray() {
        return this.option;
    }

    public String getOption() {
        return ((COSString) this.option.getObject(0)).getString();
    }

    public void setOption(String str) {
        this.option.set(0, (COSBase) new COSString(str));
    }

    public String getDefaultAppearanceString() {
        return ((COSString) this.option.getObject(1)).getString();
    }

    public void setDefaultAppearanceString(String str) {
        this.option.set(1, (COSBase) new COSString(str));
    }
}
