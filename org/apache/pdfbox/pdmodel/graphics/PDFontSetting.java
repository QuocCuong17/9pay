package org.apache.pdfbox.pdmodel.graphics;

import java.io.IOException;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDFontFactory;

/* loaded from: classes5.dex */
public class PDFontSetting implements COSObjectable {
    private COSArray fontSetting;

    public PDFontSetting() {
        this.fontSetting = null;
        COSArray cOSArray = new COSArray();
        this.fontSetting = cOSArray;
        cOSArray.add((COSBase) null);
        this.fontSetting.add((COSBase) new COSFloat(1.0f));
    }

    public PDFontSetting(COSArray cOSArray) {
        this.fontSetting = null;
        this.fontSetting = cOSArray;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.fontSetting;
    }

    public PDFont getFont() throws IOException {
        COSBase cOSBase = this.fontSetting.get(0);
        if (cOSBase instanceof COSDictionary) {
            return PDFontFactory.createFont((COSDictionary) cOSBase);
        }
        return null;
    }

    public void setFont(PDFont pDFont) {
        this.fontSetting.set(0, pDFont);
    }

    public float getFontSize() {
        return ((COSNumber) this.fontSetting.get(1)).floatValue();
    }

    public void setFontSize(float f) {
        this.fontSetting.set(1, (COSBase) new COSFloat(f));
    }
}
