package org.apache.pdfbox.pdmodel.interactive.annotation;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public class PDBorderEffectDictionary implements COSObjectable {
    public static final String STYLE_CLOUDY = "C";
    public static final String STYLE_SOLID = "S";
    private COSDictionary dictionary;

    public PDBorderEffectDictionary() {
        this.dictionary = new COSDictionary();
    }

    public PDBorderEffectDictionary(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    public COSDictionary getDictionary() {
        return this.dictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.dictionary;
    }

    public void setIntensity(float f) {
        getDictionary().setFloat("I", f);
    }

    public float getIntensity() {
        return getDictionary().getFloat("I", 0.0f);
    }

    public void setStyle(String str) {
        getDictionary().setName("S", str);
    }

    public String getStyle() {
        return getDictionary().getNameAsString("S", "S");
    }
}
