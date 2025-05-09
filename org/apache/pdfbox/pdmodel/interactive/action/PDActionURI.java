package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSDictionary;

/* loaded from: classes5.dex */
public class PDActionURI extends PDAction {
    public static final String SUB_TYPE = "URI";

    public PDActionURI() {
        this.action = new COSDictionary();
        setSubType(SUB_TYPE);
    }

    public PDActionURI(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getS() {
        return this.action.getNameAsString("S");
    }

    public void setS(String str) {
        this.action.setName("S", str);
    }

    public String getURI() {
        return this.action.getString(SUB_TYPE);
    }

    public void setURI(String str) {
        this.action.setString(SUB_TYPE, str);
    }

    public boolean shouldTrackMousePosition() {
        return this.action.getBoolean("IsMap", false);
    }

    public void setTrackMousePosition(boolean z) {
        this.action.setBoolean("IsMap", z);
    }
}
