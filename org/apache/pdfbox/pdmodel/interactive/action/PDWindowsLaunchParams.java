package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;

/* loaded from: classes5.dex */
public class PDWindowsLaunchParams implements COSObjectable {
    public static final String OPERATION_OPEN = "open";
    public static final String OPERATION_PRINT = "print";
    protected COSDictionary params;

    public PDWindowsLaunchParams() {
        this.params = new COSDictionary();
    }

    public PDWindowsLaunchParams(COSDictionary cOSDictionary) {
        this.params = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.params;
    }

    public COSDictionary getCOSDictionary() {
        return this.params;
    }

    public String getFilename() {
        return this.params.getString(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION);
    }

    public void setFilename(String str) {
        this.params.setString(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION, str);
    }

    public String getDirectory() {
        return this.params.getString("D");
    }

    public void setDirectory(String str) {
        this.params.setString("D", str);
    }

    public String getOperation() {
        return this.params.getString(PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE, "open");
    }

    public void setOperation(String str) {
        this.params.setString("D", str);
    }

    public String getExecuteParam() {
        return this.params.getString("P");
    }

    public void setExecuteParam(String str) {
        this.params.setString("P", str);
    }
}
