package org.apache.pdfbox.pdmodel.graphics.pattern;

import org.apache.pdfbox.contentstream.PDContentStream;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

/* loaded from: classes5.dex */
public class PDTilingPattern extends PDAbstractPattern implements PDContentStream {
    public static final int PAINT_COLORED = 1;
    public static final int PAINT_UNCOLORED = 2;
    public static final int TILING_CONSTANT_SPACING = 1;
    public static final int TILING_CONSTANT_SPACING_FASTER_TILING = 3;
    public static final int TILING_NO_DISTORTION = 2;

    @Override // org.apache.pdfbox.pdmodel.graphics.pattern.PDAbstractPattern
    public int getPatternType() {
        return 1;
    }

    public PDTilingPattern() {
        getCOSDictionary().setInt(COSName.PATTERN_TYPE, 1);
    }

    public PDTilingPattern(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.pattern.PDAbstractPattern
    public void setLength(int i) {
        getCOSDictionary().setInt(COSName.LENGTH, i);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.pattern.PDAbstractPattern
    public int getLength() {
        return getCOSDictionary().getInt(COSName.LENGTH, 0);
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.pattern.PDAbstractPattern
    public void setPaintType(int i) {
        getCOSDictionary().setInt(COSName.PAINT_TYPE, i);
    }

    public int getPaintType() {
        return getCOSDictionary().getInt(COSName.PAINT_TYPE, 0);
    }

    public void setTilingType(int i) {
        getCOSDictionary().setInt(COSName.TILING_TYPE, i);
    }

    public int getTilingType() {
        return getCOSDictionary().getInt(COSName.TILING_TYPE, 0);
    }

    public void setXStep(float f) {
        getCOSDictionary().setFloat(COSName.X_STEP, f);
    }

    public float getXStep() {
        float f = getCOSDictionary().getFloat(COSName.X_STEP, 0.0f);
        if (f == 32767.0f) {
            return 0.0f;
        }
        return f;
    }

    public void setYStep(float f) {
        getCOSDictionary().setFloat(COSName.Y_STEP, f);
    }

    public float getYStep() {
        float f = getCOSDictionary().getFloat(COSName.Y_STEP, 0.0f);
        if (f == 32767.0f) {
            return 0.0f;
        }
        return f;
    }

    @Override // org.apache.pdfbox.contentstream.PDContentStream
    public COSStream getContentStream() {
        return (COSStream) getCOSObject();
    }

    @Override // org.apache.pdfbox.contentstream.PDContentStream
    public PDResources getResources() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSDictionary().getDictionaryObject(COSName.RESOURCES);
        if (cOSDictionary != null) {
            return new PDResources(cOSDictionary);
        }
        return null;
    }

    public void setResources(PDResources pDResources) {
        if (pDResources != null) {
            getCOSDictionary().setItem(COSName.RESOURCES, pDResources);
        } else {
            getCOSDictionary().removeItem(COSName.RESOURCES);
        }
    }

    @Override // org.apache.pdfbox.contentstream.PDContentStream
    public PDRectangle getBBox() {
        COSArray cOSArray = (COSArray) getCOSDictionary().getDictionaryObject(COSName.BBOX);
        if (cOSArray != null) {
            return new PDRectangle(cOSArray);
        }
        return null;
    }

    public void setBBox(PDRectangle pDRectangle) {
        if (pDRectangle == null) {
            getCOSDictionary().removeItem(COSName.BBOX);
        } else {
            getCOSDictionary().setItem(COSName.BBOX, (COSBase) pDRectangle.getCOSArray());
        }
    }
}
