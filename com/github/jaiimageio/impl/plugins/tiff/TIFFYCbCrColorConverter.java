package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.github.jaiimageio.plugins.tiff.TIFFColorConverter;
import com.github.jaiimageio.plugins.tiff.TIFFField;

/* loaded from: classes3.dex */
public class TIFFYCbCrColorConverter extends TIFFColorConverter {
    private float LumaBlue;
    private float LumaGreen;
    private float LumaRed;
    private float referenceBlackCb;
    private float referenceBlackCr;
    private float referenceBlackY;
    private float referenceWhiteCb;
    private float referenceWhiteCr;
    private float referenceWhiteY;
    private float codingRangeY = 255.0f;
    private float codingRangeCbCr = 127.0f;

    public TIFFYCbCrColorConverter(TIFFImageMetadata tIFFImageMetadata) {
        this.LumaRed = 0.299f;
        this.LumaGreen = 0.587f;
        this.LumaBlue = 0.114f;
        this.referenceBlackY = 0.0f;
        this.referenceWhiteY = 255.0f;
        this.referenceBlackCb = 128.0f;
        this.referenceWhiteCb = 255.0f;
        this.referenceBlackCr = 128.0f;
        this.referenceWhiteCr = 255.0f;
        TIFFField tIFFField = tIFFImageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_Y_CB_CR_COEFFICIENTS);
        if (tIFFField != null && tIFFField.getCount() == 3) {
            this.LumaRed = tIFFField.getAsFloat(0);
            this.LumaGreen = tIFFField.getAsFloat(1);
            this.LumaBlue = tIFFField.getAsFloat(2);
        }
        TIFFField tIFFField2 = tIFFImageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_REFERENCE_BLACK_WHITE);
        if (tIFFField2 == null || tIFFField2.getCount() != 6) {
            return;
        }
        this.referenceBlackY = tIFFField2.getAsFloat(0);
        this.referenceWhiteY = tIFFField2.getAsFloat(1);
        this.referenceBlackCb = tIFFField2.getAsFloat(2);
        this.referenceWhiteCb = tIFFField2.getAsFloat(3);
        this.referenceBlackCr = tIFFField2.getAsFloat(4);
        this.referenceWhiteCr = tIFFField2.getAsFloat(5);
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFColorConverter
    public void fromRGB(float f, float f2, float f3, float[] fArr) {
        float f4 = this.LumaRed;
        float f5 = (f4 * f) + (this.LumaGreen * f2);
        float f6 = this.LumaBlue;
        float f7 = f5 + (f6 * f3);
        float f8 = (f3 - f7) / (2.0f - (f6 * 2.0f));
        float f9 = (f - f7) / (2.0f - (f4 * 2.0f));
        float f10 = this.referenceWhiteY;
        float f11 = this.referenceBlackY;
        fArr[0] = ((f7 * (f10 - f11)) / this.codingRangeY) + f11;
        float f12 = this.referenceWhiteCb;
        float f13 = this.referenceBlackCb;
        float f14 = f8 * (f12 - f13);
        float f15 = this.codingRangeCbCr;
        fArr[1] = (f14 / f15) + f13;
        float f16 = this.referenceWhiteCr;
        float f17 = this.referenceBlackCr;
        fArr[2] = ((f9 * (f16 - f17)) / f15) + f17;
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFColorConverter
    public void toRGB(float f, float f2, float f3, float[] fArr) {
        float f4 = this.referenceBlackY;
        float f5 = ((f - f4) * this.codingRangeY) / (this.referenceWhiteY - f4);
        float f6 = this.referenceBlackCb;
        float f7 = this.codingRangeCbCr;
        float f8 = ((f2 - f6) * f7) / (this.referenceWhiteCb - f6);
        float f9 = this.referenceBlackCr;
        float f10 = ((f3 - f9) * f7) / (this.referenceWhiteCr - f9);
        float f11 = this.LumaRed;
        fArr[0] = (f10 * (2.0f - (f11 * 2.0f))) + f5;
        float f12 = this.LumaBlue;
        fArr[2] = (f8 * (2.0f - (f12 * 2.0f))) + f5;
        fArr[1] = ((f5 - (f12 * fArr[2])) - (f11 * fArr[0])) / this.LumaGreen;
    }
}
