package org.apache.pdfbox.pdmodel.graphics.shading;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.PDRange;

/* loaded from: classes5.dex */
public class PDShadingType5 extends PDTriangleBasedShadingType {
    @Override // org.apache.pdfbox.pdmodel.graphics.shading.PDShading
    public int getShadingType() {
        return 5;
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

    public PDShadingType5(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public int getVerticesPerRow() {
        return getCOSDictionary().getInt(COSName.VERTICES_PER_ROW, -1);
    }

    public void setVerticesPerRow(int i) {
        getCOSDictionary().setInt(COSName.VERTICES_PER_ROW, i);
    }
}
