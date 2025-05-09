package org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf;

import org.apache.pdfbox.cos.COSDictionary;

/* loaded from: classes5.dex */
public class PDPrintFieldAttributeObject extends PDStandardAttributeObject {
    private static final String CHECKED = "checked";
    public static final String CHECKED_STATE_NEUTRAL = "neutral";
    public static final String CHECKED_STATE_OFF = "off";
    public static final String CHECKED_STATE_ON = "on";
    private static final String DESC = "Desc";
    public static final String OWNER_PRINT_FIELD = "PrintField";
    private static final String ROLE = "Role";
    public static final String ROLE_CB = "cb";
    public static final String ROLE_PB = "pb";
    public static final String ROLE_RB = "rb";
    public static final String ROLE_TV = "tv";

    public PDPrintFieldAttributeObject() {
        setOwner(OWNER_PRINT_FIELD);
    }

    public PDPrintFieldAttributeObject(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getRole() {
        return getName(ROLE);
    }

    public void setRole(String str) {
        setName(ROLE, str);
    }

    public String getCheckedState() {
        return getName(CHECKED, "off");
    }

    public void setCheckedState(String str) {
        setName(CHECKED, str);
    }

    public String getAlternateName() {
        return getString(DESC);
    }

    public void setAlternateName(String str) {
        setString(DESC, str);
    }

    @Override // org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDAttributeObject
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (isSpecified(ROLE)) {
            sb.append(", Role=");
            sb.append(getRole());
        }
        if (isSpecified(CHECKED)) {
            sb.append(", Checked=");
            sb.append(getCheckedState());
        }
        if (isSpecified(DESC)) {
            sb.append(", Desc=");
            sb.append(getAlternateName());
        }
        return sb.toString();
    }
}
