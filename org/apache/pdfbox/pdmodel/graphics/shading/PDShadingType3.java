package org.apache.pdfbox.pdmodel.graphics.shading;

import org.apache.pdfbox.cos.COSDictionary;

/* loaded from: classes5.dex */
public class PDShadingType3 extends PDShadingType2 {
    @Override // org.apache.pdfbox.pdmodel.graphics.shading.PDShadingType2, org.apache.pdfbox.pdmodel.graphics.shading.PDShading
    public int getShadingType() {
        return 3;
    }

    public PDShadingType3(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }
}
