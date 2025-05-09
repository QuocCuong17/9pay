package org.apache.pdfbox.pdmodel.graphics.shading;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.PDRange;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public abstract class PDTriangleBasedShadingType extends PDShading {
    private COSArray decode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PDTriangleBasedShadingType(COSDictionary cOSDictionary) {
        super(cOSDictionary);
        this.decode = null;
    }

    public int getBitsPerComponent() {
        return getCOSDictionary().getInt(COSName.BITS_PER_COMPONENT, -1);
    }

    public void setBitsPerComponent(int i) {
        getCOSDictionary().setInt(COSName.BITS_PER_COMPONENT, i);
    }

    public int getBitsPerCoordinate() {
        return getCOSDictionary().getInt(COSName.BITS_PER_COORDINATE, -1);
    }

    public void setBitsPerCoordinate(int i) {
        getCOSDictionary().setInt(COSName.BITS_PER_COORDINATE, i);
    }

    private COSArray getDecodeValues() {
        if (this.decode == null) {
            this.decode = (COSArray) getCOSDictionary().getDictionaryObject(COSName.DECODE);
        }
        return this.decode;
    }

    public void setDecodeValues(COSArray cOSArray) {
        this.decode = cOSArray;
        getCOSDictionary().setItem(COSName.DECODE, (COSBase) cOSArray);
    }

    public PDRange getDecodeForParameter(int i) {
        COSArray decodeValues = getDecodeValues();
        if (decodeValues == null || decodeValues.size() < (i * 2) + 1) {
            return null;
        }
        return new PDRange(decodeValues, i);
    }
}
