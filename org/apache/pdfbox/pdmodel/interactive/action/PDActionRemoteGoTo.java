package org.apache.pdfbox.pdmodel.interactive.action;

import java.io.IOException;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;

/* loaded from: classes5.dex */
public class PDActionRemoteGoTo extends PDAction {
    public static final String SUB_TYPE = "GoToR";

    public PDActionRemoteGoTo() {
        this.action = new COSDictionary();
        setSubType(SUB_TYPE);
    }

    public PDActionRemoteGoTo(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getS() {
        return this.action.getNameAsString("S");
    }

    public void setS(String str) {
        this.action.setName("S", str);
    }

    public PDFileSpecification getFile() throws IOException {
        return PDFileSpecification.createFS(this.action.getDictionaryObject(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION));
    }

    public void setFile(PDFileSpecification pDFileSpecification) {
        this.action.setItem(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION, pDFileSpecification);
    }

    public COSBase getD() {
        return this.action.getDictionaryObject("D");
    }

    public void setD(COSBase cOSBase) {
        this.action.setItem("D", cOSBase);
    }

    public boolean shouldOpenInNewWindow() {
        return this.action.getBoolean("NewWindow", true);
    }

    public void setOpenInNewWindow(boolean z) {
        this.action.setBoolean("NewWindow", z);
    }
}
