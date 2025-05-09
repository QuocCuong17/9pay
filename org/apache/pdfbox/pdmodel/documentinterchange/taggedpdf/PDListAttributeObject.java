package org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf;

import org.apache.pdfbox.cos.COSDictionary;

/* loaded from: classes5.dex */
public class PDListAttributeObject extends PDStandardAttributeObject {
    protected static final String LIST_NUMBERING = "ListNumbering";
    public static final String LIST_NUMBERING_CIRCLE = "Circle";
    public static final String LIST_NUMBERING_DECIMAL = "Decimal";
    public static final String LIST_NUMBERING_DISC = "Disc";
    public static final String LIST_NUMBERING_LOWER_ALPHA = "LowerAlpha";
    public static final String LIST_NUMBERING_LOWER_ROMAN = "LowerRoman";
    public static final String LIST_NUMBERING_NONE = "None";
    public static final String LIST_NUMBERING_SQUARE = "Square";
    public static final String LIST_NUMBERING_UPPER_ALPHA = "UpperAlpha";
    public static final String LIST_NUMBERING_UPPER_ROMAN = "UpperRoman";
    public static final String OWNER_LIST = "List";

    public PDListAttributeObject() {
        setOwner(OWNER_LIST);
    }

    public PDListAttributeObject(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getListNumbering() {
        return getName(LIST_NUMBERING, "None");
    }

    public void setListNumbering(String str) {
        setName(LIST_NUMBERING, str);
    }

    @Override // org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDAttributeObject
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (isSpecified(LIST_NUMBERING)) {
            sb.append(", ListNumbering=");
            sb.append(getListNumbering());
        }
        return sb.toString();
    }
}
