package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSDictionary;

/* loaded from: classes5.dex */
public class PDActionNamed extends PDAction {
    public static final String SUB_TYPE = "Named";

    public PDActionNamed() {
        this.action = new COSDictionary();
        setSubType(SUB_TYPE);
    }

    public PDActionNamed(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getN() {
        return this.action.getNameAsString("N");
    }

    public void setN(String str) {
        this.action.setName("N", str);
    }
}
