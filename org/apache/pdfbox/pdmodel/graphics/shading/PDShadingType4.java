package org.apache.pdfbox.pdmodel.graphics.shading;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.PDRange;

/* loaded from: classes5.dex */
public class PDShadingType4 extends PDTriangleBasedShadingType {
    @Override // org.apache.pdfbox.pdmodel.graphics.shading.PDShading
    public int getShadingType() {
        return 4;
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.shading.PDTriangleBasedShadingType
    public /* bridge */ /* synthetic */ int getBitsPerComponent() {
        return super.getBitsPerComponent();
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.shading.PDTriangleBasedShadingType
    public /* bridge */ /* synthetic */ int getBitsPerCoordinate() {
        return super.getBitsPerCoordinate();
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.shading.PDTriangleBasedShadingType
    public /* bridge */ /* synthetic */ PDRange getDecodeForParameter(int i) {
        return super.getDecodeForParameter(i);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.shading.PDTriangleBasedShadingType
    public /* bridge */ /* synthetic */ void setBitsPerComponent(int i) {
        super.setBitsPerComponent(i);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.shading.PDTriangleBasedShadingType
    public /* bridge */ /* synthetic */ void setBitsPerCoordinate(int i) {
        super.setBitsPerCoordinate(i);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.shading.PDTriangleBasedShadingType
    public /* bridge */ /* synthetic */ void setDecodeValues(COSArray cOSArray) {
        super.setDecodeValues(cOSArray);
    }

    public PDShadingType4(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public int getBitsPerFlag() {
        return getCOSDictionary().getInt(COSName.BITS_PER_FLAG, -1);
    }

    public void setBitsPerFlag(int i) {
        getCOSDictionary().setInt(COSName.BITS_PER_FLAG, i);
    }
}
