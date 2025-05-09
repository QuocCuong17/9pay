package org.apache.pdfbox.pdmodel.graphics.shading;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public class PDShadingType2 extends PDShading {
    private COSArray coords;
    private COSArray domain;
    private COSArray extend;

    @Override // org.apache.pdfbox.pdmodel.graphics.shading.PDShading
    public int getShadingType() {
        return 2;
    }

    public PDShadingType2(COSDictionary cOSDictionary) {
        super(cOSDictionary);
        this.coords = null;
        this.domain = null;
        this.extend = null;
    }

    public COSArray getExtend() {
        if (this.extend == null) {
            this.extend = (COSArray) getCOSDictionary().getDictionaryObject(COSName.EXTEND);
        }
        return this.extend;
    }

    public void setExtend(COSArray cOSArray) {
        this.extend = cOSArray;
        if (cOSArray == null) {
            getCOSDictionary().removeItem(COSName.EXTEND);
        } else {
            getCOSDictionary().setItem(COSName.EXTEND, (COSBase) cOSArray);
        }
    }

    public COSArray getDomain() {
        if (this.domain == null) {
            this.domain = (COSArray) getCOSDictionary().getDictionaryObject(COSName.DOMAIN);
        }
        return this.domain;
    }

    public void setDomain(COSArray cOSArray) {
        this.domain = cOSArray;
        if (cOSArray == null) {
            getCOSDictionary().removeItem(COSName.DOMAIN);
        } else {
            getCOSDictionary().setItem(COSName.DOMAIN, (COSBase) cOSArray);
        }
    }

    public COSArray getCoords() {
        if (this.coords == null) {
            this.coords = (COSArray) getCOSDictionary().getDictionaryObject(COSName.COORDS);
        }
        return this.coords;
    }

    public void setCoords(COSArray cOSArray) {
        this.coords = cOSArray;
        if (cOSArray == null) {
            getCOSDictionary().removeItem(COSName.COORDS);
        } else {
            getCOSDictionary().setItem(COSName.COORDS, (COSBase) cOSArray);
        }
    }
}
