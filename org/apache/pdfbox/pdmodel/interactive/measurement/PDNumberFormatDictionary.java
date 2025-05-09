package org.apache.pdfbox.pdmodel.interactive.measurement;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;

/* loaded from: classes5.dex */
public class PDNumberFormatDictionary implements COSObjectable {
    public static final String FRACTIONAL_DISPLAY_DECIMAL = "D";
    public static final String FRACTIONAL_DISPLAY_FRACTION = "F";
    public static final String FRACTIONAL_DISPLAY_ROUND = "R";
    public static final String FRACTIONAL_DISPLAY_TRUNCATE = "T";
    public static final String LABEL_PREFIX_TO_VALUE = "P";
    public static final String LABEL_SUFFIX_TO_VALUE = "S";
    public static final String TYPE = "NumberFormat";
    private COSDictionary numberFormatDictionary;

    public String getType() {
        return TYPE;
    }

    public PDNumberFormatDictionary() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.numberFormatDictionary = cOSDictionary;
        cOSDictionary.setName(COSName.TYPE, TYPE);
    }

    public PDNumberFormatDictionary(COSDictionary cOSDictionary) {
        this.numberFormatDictionary = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.numberFormatDictionary;
    }

    public COSDictionary getDictionary() {
        return this.numberFormatDictionary;
    }

    public String getUnits() {
        return getDictionary().getString(PDBorderStyleDictionary.STYLE_UNDERLINE);
    }

    public void setUnits(String str) {
        getDictionary().setString(PDBorderStyleDictionary.STYLE_UNDERLINE, str);
    }

    public float getConversionFactor() {
        return getDictionary().getFloat("C");
    }

    public void setConversionFactor(float f) {
        getDictionary().setFloat("C", f);
    }

    public String getFractionalDisplay() {
        return getDictionary().getString(FRACTIONAL_DISPLAY_FRACTION, "D");
    }

    public void setFractionalDisplay(String str) {
        if (str == null || "D".equals(str) || FRACTIONAL_DISPLAY_FRACTION.equals(str) || "R".equals(str) || "T".equals(str)) {
            getDictionary().setString(FRACTIONAL_DISPLAY_FRACTION, str);
            return;
        }
        throw new IllegalArgumentException("Value must be \"D\", \"F\", \"R\", or \"T\", (or null).");
    }

    public int getDenominator() {
        return getDictionary().getInt("D");
    }

    public void setDenominator(int i) {
        getDictionary().setInt("D", i);
    }

    public boolean isFD() {
        return getDictionary().getBoolean("FD", false);
    }

    public void setFD(boolean z) {
        getDictionary().setBoolean("FD", z);
    }

    public String getThousandsSeparator() {
        return getDictionary().getString(StandardStructureTypes.RT, ",");
    }

    public void setThousandsSeparator(String str) {
        getDictionary().setString(StandardStructureTypes.RT, str);
    }

    public String getDecimalSeparator() {
        return getDictionary().getString("RD", ".");
    }

    public void setDecimalSeparator(String str) {
        getDictionary().setString("RD", str);
    }

    public String getLabelPrefixString() {
        return getDictionary().getString("PS", " ");
    }

    public void setLabelPrefixString(String str) {
        getDictionary().setString("PS", str);
    }

    public String getLabelSuffixString() {
        return getDictionary().getString("SS", " ");
    }

    public void setLabelSuffixString(String str) {
        getDictionary().setString("SS", str);
    }

    public String getLabelPositionToValue() {
        return getDictionary().getString(PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE, "S");
    }

    public void setLabelPositionToValue(String str) {
        if (str == null || "P".equals(str) || "S".equals(str)) {
            getDictionary().setString(PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE, str);
            return;
        }
        throw new IllegalArgumentException("Value must be \"S\", or \"P\" (or null).");
    }
}
