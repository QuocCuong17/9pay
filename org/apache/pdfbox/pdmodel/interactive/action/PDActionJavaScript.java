package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.common.PDTextStream;

/* loaded from: classes5.dex */
public class PDActionJavaScript extends PDAction {
    public static final String SUB_TYPE = "JavaScript";

    public PDActionJavaScript() {
        setSubType(SUB_TYPE);
    }

    public PDActionJavaScript(String str) {
        this();
        setAction(str);
    }

    public PDActionJavaScript(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public void setAction(PDTextStream pDTextStream) {
        this.action.setItem("JS", pDTextStream);
    }

    public void setAction(String str) {
        this.action.setString("JS", str);
    }

    public PDTextStream getAction() {
        return PDTextStream.createTextStream(this.action.getDictionaryObject("JS"));
    }
}
