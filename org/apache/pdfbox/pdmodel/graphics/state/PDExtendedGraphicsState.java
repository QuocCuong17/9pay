package org.apache.pdfbox.pdmodel.graphics.state;

import android.graphics.Paint;
import java.io.IOException;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.graphics.PDFontSetting;
import org.apache.pdfbox.pdmodel.graphics.PDLineDashPattern;
import org.apache.pdfbox.pdmodel.graphics.blend.BlendMode;

/* loaded from: classes5.dex */
public class PDExtendedGraphicsState implements COSObjectable {
    private final COSDictionary dict;

    public PDExtendedGraphicsState() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dict = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.EXT_G_STATE);
    }

    public PDExtendedGraphicsState(COSDictionary cOSDictionary) {
        this.dict = cOSDictionary;
    }

    public void copyIntoGraphicsState(PDGraphicsState pDGraphicsState) throws IOException {
        for (COSName cOSName : this.dict.keySet()) {
            if (cOSName.equals(COSName.LW)) {
                pDGraphicsState.setLineWidth(getLineWidth().floatValue());
            } else if (cOSName.equals(COSName.LC)) {
                pDGraphicsState.setLineCap(getLineCapStyle());
            } else if (cOSName.equals(COSName.LJ)) {
                pDGraphicsState.setLineJoin(getLineJoinStyle());
            } else if (cOSName.equals(COSName.ML)) {
                pDGraphicsState.setMiterLimit(getMiterLimit().floatValue());
            } else if (cOSName.equals(COSName.D)) {
                pDGraphicsState.setLineDashPattern(getLineDashPattern());
            } else if (cOSName.equals(COSName.RI)) {
                pDGraphicsState.setRenderingIntent(getRenderingIntent());
            } else if (cOSName.equals(COSName.OPM)) {
                pDGraphicsState.setOverprintMode(getOverprintMode().doubleValue());
            } else if (cOSName.equals(COSName.FONT)) {
                PDFontSetting fontSetting = getFontSetting();
                if (fontSetting != null) {
                    pDGraphicsState.getTextState().setFont(fontSetting.getFont());
                    pDGraphicsState.getTextState().setFontSize(fontSetting.getFontSize());
                }
            } else if (cOSName.equals(COSName.FL)) {
                pDGraphicsState.setFlatness(getFlatnessTolerance().floatValue());
            } else if (cOSName.equals(COSName.SM)) {
                pDGraphicsState.setSmoothness(getSmoothnessTolerance().floatValue());
            } else if (cOSName.equals(COSName.SA)) {
                pDGraphicsState.setStrokeAdjustment(getAutomaticStrokeAdjustment());
            } else if (cOSName.equals(COSName.CA)) {
                pDGraphicsState.setAlphaConstant(getStrokingAlphaConstant().floatValue());
            } else if (cOSName.equals(COSName.CA_NS)) {
                pDGraphicsState.setNonStrokeAlphaConstant(getNonStrokingAlphaConstant().floatValue());
            } else if (cOSName.equals(COSName.AIS)) {
                pDGraphicsState.setAlphaSource(getAlphaSourceFlag());
            } else if (cOSName.equals(COSName.TK)) {
                pDGraphicsState.getTextState().setKnockoutFlag(getTextKnockoutFlag());
            } else if (cOSName.equals(COSName.SMASK)) {
                pDGraphicsState.setSoftMask(getSoftMask());
            } else if (cOSName.equals(COSName.BM)) {
                pDGraphicsState.setBlendMode(getBlendMode());
            }
        }
    }

    public COSDictionary getCOSDictionary() {
        return this.dict;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.dict;
    }

    public Float getLineWidth() {
        return getFloatItem(COSName.LW);
    }

    public void setLineWidth(Float f) {
        setFloatItem(COSName.LW, f);
    }

    public Paint.Cap getLineCapStyle() {
        int i = this.dict.getInt(COSName.LC);
        if (i == 0) {
            return Paint.Cap.BUTT;
        }
        if (i == 1) {
            return Paint.Cap.ROUND;
        }
        if (i != 2) {
            return null;
        }
        return Paint.Cap.SQUARE;
    }

    public void setLineCapStyle(int i) {
        this.dict.setInt(COSName.LC, i);
    }

    public Paint.Join getLineJoinStyle() {
        int i = this.dict.getInt(COSName.LJ);
        if (i == 0) {
            return Paint.Join.MITER;
        }
        if (i == 1) {
            return Paint.Join.ROUND;
        }
        if (i != 2) {
            return null;
        }
        return Paint.Join.BEVEL;
    }

    public void setLineJoinStyle(int i) {
        this.dict.setInt(COSName.LJ, i);
    }

    public Float getMiterLimit() {
        return getFloatItem(COSName.ML);
    }

    public void setMiterLimit(Float f) {
        setFloatItem(COSName.ML, f);
    }

    public PDLineDashPattern getLineDashPattern() {
        COSArray cOSArray = (COSArray) this.dict.getDictionaryObject(COSName.D);
        if (cOSArray == null) {
            return null;
        }
        COSArray cOSArray2 = new COSArray();
        cOSArray.addAll(cOSArray);
        cOSArray.remove(cOSArray.size() - 1);
        return new PDLineDashPattern(cOSArray2, cOSArray.getInt(cOSArray.size() - 1));
    }

    public void setLineDashPattern(PDLineDashPattern pDLineDashPattern) {
        this.dict.setItem(COSName.D, pDLineDashPattern.getCOSObject());
    }

    public RenderingIntent getRenderingIntent() {
        String nameAsString = this.dict.getNameAsString("RI");
        if (nameAsString != null) {
            return RenderingIntent.fromString(nameAsString);
        }
        return null;
    }

    public void setRenderingIntent(String str) {
        this.dict.setName("RI", str);
    }

    public boolean getStrokingOverprintControl() {
        return this.dict.getBoolean(COSName.OP, false);
    }

    public void setStrokingOverprintControl(boolean z) {
        this.dict.setBoolean(COSName.OP, z);
    }

    public boolean getNonStrokingOverprintControl() {
        return this.dict.getBoolean(COSName.OP_NS, getStrokingOverprintControl());
    }

    public void setNonStrokingOverprintControl(boolean z) {
        this.dict.setBoolean(COSName.OP_NS, z);
    }

    public Float getOverprintMode() {
        return getFloatItem(COSName.OPM);
    }

    public void setOverprintMode(Float f) {
        setFloatItem(COSName.OPM, f);
    }

    public PDFontSetting getFontSetting() {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.FONT);
        if (dictionaryObject instanceof COSArray) {
            return new PDFontSetting((COSArray) dictionaryObject);
        }
        return null;
    }

    public void setFontSetting(PDFontSetting pDFontSetting) {
        this.dict.setItem(COSName.FONT, pDFontSetting);
    }

    public Float getFlatnessTolerance() {
        return getFloatItem(COSName.FL);
    }

    public void setFlatnessTolerance(Float f) {
        setFloatItem(COSName.FL, f);
    }

    public Float getSmoothnessTolerance() {
        return getFloatItem(COSName.SM);
    }

    public void setSmoothnessTolerance(Float f) {
        setFloatItem(COSName.SM, f);
    }

    public boolean getAutomaticStrokeAdjustment() {
        return this.dict.getBoolean(COSName.SA, false);
    }

    public void setAutomaticStrokeAdjustment(boolean z) {
        this.dict.setBoolean(COSName.SA, z);
    }

    public Float getStrokingAlphaConstant() {
        return getFloatItem(COSName.CA);
    }

    public void setStrokingAlphaConstant(Float f) {
        setFloatItem(COSName.CA, f);
    }

    public Float getNonStrokingAlphaConstant() {
        return getFloatItem(COSName.CA_NS);
    }

    public void setNonStrokingAlphaConstant(Float f) {
        setFloatItem(COSName.CA_NS, f);
    }

    public boolean getAlphaSourceFlag() {
        return this.dict.getBoolean(COSName.AIS, false);
    }

    public void setAlphaSourceFlag(boolean z) {
        this.dict.setBoolean(COSName.AIS, z);
    }

    public BlendMode getBlendMode() {
        return BlendMode.getInstance(this.dict.getDictionaryObject(COSName.BM));
    }

    public PDSoftMask getSoftMask() {
        return PDSoftMask.create(this.dict.getDictionaryObject(COSName.SMASK));
    }

    public boolean getTextKnockoutFlag() {
        return this.dict.getBoolean(COSName.TK, true);
    }

    public void setTextKnockoutFlag(boolean z) {
        this.dict.setBoolean(COSName.TK, z);
    }

    private Float getFloatItem(COSName cOSName) {
        COSNumber cOSNumber = (COSNumber) this.dict.getDictionaryObject(cOSName);
        if (cOSNumber != null) {
            return Float.valueOf(cOSNumber.floatValue());
        }
        return null;
    }

    private void setFloatItem(COSName cOSName, Float f) {
        if (f == null) {
            this.dict.removeItem(cOSName);
        } else {
            this.dict.setItem(cOSName, (COSBase) new COSFloat(f.floatValue()));
        }
    }
}
