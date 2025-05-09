package org.apache.pdfbox.pdmodel.interactive.action;

import java.io.IOException;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;

/* loaded from: classes5.dex */
public class PDActionLaunch extends PDAction {
    public static final String SUB_TYPE = "Launch";

    public PDActionLaunch() {
        setSubType(SUB_TYPE);
    }

    public PDActionLaunch(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public PDFileSpecification getFile() throws IOException {
        return PDFileSpecification.createFS(getCOSDictionary().getDictionaryObject(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION));
    }

    public void setFile(PDFileSpecification pDFileSpecification) {
        getCOSDictionary().setItem(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION, pDFileSpecification);
    }

    public PDWindowsLaunchParams getWinLaunchParams() {
        COSDictionary cOSDictionary = (COSDictionary) this.action.getDictionaryObject("Win");
        if (cOSDictionary != null) {
            return new PDWindowsLaunchParams(cOSDictionary);
        }
        return null;
    }

    public void setWinLaunchParams(PDWindowsLaunchParams pDWindowsLaunchParams) {
        this.action.setItem("Win", pDWindowsLaunchParams);
    }

    public String getF() {
        return this.action.getString(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION);
    }

    public void setF(String str) {
        this.action.setString(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION, str);
    }

    public String getD() {
        return this.action.getString("D");
    }

    public void setD(String str) {
        this.action.setString("D", str);
    }

    public String getO() {
        return this.action.getString(PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE);
    }

    public void setO(String str) {
        this.action.setString(PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE, str);
    }

    public String getP() {
        return this.action.getString("P");
    }

    public void setP(String str) {
        this.action.setString("P", str);
    }

    public boolean shouldOpenInNewWindow() {
        return this.action.getBoolean("NewWindow", true);
    }

    public void setOpenInNewWindow(boolean z) {
        this.action.setBoolean("NewWindow", z);
    }
}
