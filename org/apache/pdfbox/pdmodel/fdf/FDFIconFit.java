package org.apache.pdfbox.pdmodel.fdf;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDRange;

/* loaded from: classes5.dex */
public class FDFIconFit implements COSObjectable {
    public static final String SCALE_OPTION_ALWAYS = "A";
    public static final String SCALE_OPTION_NEVER = "N";
    public static final String SCALE_OPTION_ONLY_WHEN_ICON_IS_BIGGER = "B";
    public static final String SCALE_OPTION_ONLY_WHEN_ICON_IS_SMALLER = "S";
    public static final String SCALE_TYPE_ANAMORPHIC = "A";
    public static final String SCALE_TYPE_PROPORTIONAL = "P";
    private COSDictionary fit;

    public FDFIconFit() {
        this.fit = new COSDictionary();
    }

    public FDFIconFit(COSDictionary cOSDictionary) {
        this.fit = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.fit;
    }

    public COSDictionary getCOSDictionary() {
        return this.fit;
    }

    public String getScaleOption() {
        String nameAsString = this.fit.getNameAsString(COSName.SW);
        return nameAsString == null ? "A" : nameAsString;
    }

    public void setScaleOption(String str) {
        this.fit.setName(COSName.SW, str);
    }

    public String getScaleType() {
        String nameAsString = this.fit.getNameAsString(COSName.S);
        return nameAsString == null ? "P" : nameAsString;
    }

    public void setScaleType(String str) {
        this.fit.setName(COSName.S, str);
    }

    public PDRange getFractionalSpaceToAllocate() {
        COSArray cOSArray = (COSArray) this.fit.getDictionaryObject(COSName.A);
        if (cOSArray == null) {
            PDRange pDRange = new PDRange();
            pDRange.setMin(0.5f);
            pDRange.setMax(0.5f);
            setFractionalSpaceToAllocate(pDRange);
            return pDRange;
        }
        return new PDRange(cOSArray);
    }

    public void setFractionalSpaceToAllocate(PDRange pDRange) {
        this.fit.setItem(COSName.A, pDRange);
    }

    public boolean shouldScaleToFitAnnotation() {
        return this.fit.getBoolean(COSName.FB, false);
    }

    public void setScaleToFitAnnotation(boolean z) {
        this.fit.setBoolean(COSName.FB, z);
    }
}
