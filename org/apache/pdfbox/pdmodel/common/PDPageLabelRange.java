package org.apache.pdfbox.pdmodel.common;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public class PDPageLabelRange implements COSObjectable {
    public static final String STYLE_DECIMAL = "D";
    public static final String STYLE_LETTERS_LOWER = "a";
    public static final String STYLE_LETTERS_UPPER = "A";
    public static final String STYLE_ROMAN_LOWER = "r";
    public static final String STYLE_ROMAN_UPPER = "R";
    private COSDictionary root;
    private static final COSName KEY_START = COSName.ST;
    private static final COSName KEY_PREFIX = COSName.P;
    private static final COSName KEY_STYLE = COSName.S;

    public PDPageLabelRange() {
        this(new COSDictionary());
    }

    public PDPageLabelRange(COSDictionary cOSDictionary) {
        this.root = cOSDictionary;
    }

    public COSDictionary getCOSDictionary() {
        return this.root;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.root;
    }

    public String getStyle() {
        return this.root.getNameAsString(KEY_STYLE);
    }

    public void setStyle(String str) {
        if (str != null) {
            this.root.setName(KEY_STYLE, str);
        } else {
            this.root.removeItem(KEY_STYLE);
        }
    }

    public int getStart() {
        return this.root.getInt(KEY_START, 1);
    }

    public void setStart(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("The page numbering start value must be a positive integer");
        }
        this.root.setInt(KEY_START, i);
    }

    public String getPrefix() {
        return this.root.getString(KEY_PREFIX);
    }

    public void setPrefix(String str) {
        if (str != null) {
            this.root.setString(KEY_PREFIX, str);
        } else {
            this.root.removeItem(KEY_PREFIX);
        }
    }
}
