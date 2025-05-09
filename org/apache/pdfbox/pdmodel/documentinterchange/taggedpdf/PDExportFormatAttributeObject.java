package org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf;

import org.apache.pdfbox.cos.COSDictionary;

/* loaded from: classes5.dex */
public class PDExportFormatAttributeObject extends PDLayoutAttributeObject {
    public static final String OWNER_CSS_1_00 = "CSS-1.00";
    public static final String OWNER_CSS_2_00 = "CSS-2.00";
    public static final String OWNER_HTML_3_20 = "HTML-3.2";
    public static final String OWNER_HTML_4_01 = "HTML-4.01";
    public static final String OWNER_OEB_1_00 = "OEB-1.00";
    public static final String OWNER_RTF_1_05 = "RTF-1.05";
    public static final String OWNER_XML_1_00 = "XML-1.00";

    public PDExportFormatAttributeObject(String str) {
        setOwner(str);
    }

    public PDExportFormatAttributeObject(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getListNumbering() {
        return getName("ListNumbering", "None");
    }

    public void setListNumbering(String str) {
        setName("ListNumbering", str);
    }

    public int getRowSpan() {
        return getInteger("RowSpan", 1);
    }

    public void setRowSpan(int i) {
        setInteger("RowSpan", i);
    }

    public int getColSpan() {
        return getInteger("ColSpan", 1);
    }

    public void setColSpan(int i) {
        setInteger("ColSpan", i);
    }

    public String[] getHeaders() {
        return getArrayOfString("Headers");
    }

    public void setHeaders(String[] strArr) {
        setArrayOfString("Headers", strArr);
    }

    public String getScope() {
        return getName("Scope");
    }

    public void setScope(String str) {
        setName("Scope", str);
    }

    public String getSummary() {
        return getString("Summary");
    }

    public void setSummary(String str) {
        setString("Summary", str);
    }

    @Override // org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject, org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDAttributeObject
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (isSpecified("ListNumbering")) {
            sb.append(", ListNumbering=");
            sb.append(getListNumbering());
        }
        if (isSpecified("RowSpan")) {
            sb.append(", RowSpan=");
            sb.append(String.valueOf(getRowSpan()));
        }
        if (isSpecified("ColSpan")) {
            sb.append(", ColSpan=");
            sb.append(String.valueOf(getColSpan()));
        }
        if (isSpecified("Headers")) {
            sb.append(", Headers=");
            sb.append(arrayToString(getHeaders()));
        }
        if (isSpecified("Scope")) {
            sb.append(", Scope=");
            sb.append(getScope());
        }
        if (isSpecified("Summary")) {
            sb.append(", Summary=");
            sb.append(getSummary());
        }
        return sb.toString();
    }
}
