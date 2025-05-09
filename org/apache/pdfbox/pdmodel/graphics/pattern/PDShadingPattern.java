package org.apache.pdfbox.pdmodel.graphics.pattern;

import java.io.IOException;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.graphics.shading.PDShading;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;

/* loaded from: classes5.dex */
public class PDShadingPattern extends PDAbstractPattern {
    private PDExtendedGraphicsState extendedGraphicsState;
    private PDShading shading;

    @Override // org.apache.pdfbox.pdmodel.graphics.pattern.PDAbstractPattern
    public int getPatternType() {
        return 2;
    }

    public PDShadingPattern() {
        getCOSDictionary().setInt(COSName.PATTERN_TYPE, 2);
    }

    public PDShadingPattern(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public PDExtendedGraphicsState getExtendedGraphicsState() {
        COSDictionary cOSDictionary;
        if (this.extendedGraphicsState == null && (cOSDictionary = (COSDictionary) getCOSDictionary().getDictionaryObject(COSName.EXT_G_STATE)) != null) {
            this.extendedGraphicsState = new PDExtendedGraphicsState(cOSDictionary);
        }
        return this.extendedGraphicsState;
    }

    public void setExternalGraphicsState(PDExtendedGraphicsState pDExtendedGraphicsState) {
        this.extendedGraphicsState = pDExtendedGraphicsState;
        if (pDExtendedGraphicsState != null) {
            getCOSDictionary().setItem(COSName.EXT_G_STATE, pDExtendedGraphicsState);
        } else {
            getCOSDictionary().removeItem(COSName.EXT_G_STATE);
        }
    }

    public PDShading getShading() throws IOException {
        COSDictionary cOSDictionary;
        if (this.shading == null && (cOSDictionary = (COSDictionary) getCOSDictionary().getDictionaryObject(COSName.SHADING)) != null) {
            this.shading = PDShading.create(cOSDictionary);
        }
        return this.shading;
    }

    public void setShading(PDShading pDShading) {
        this.shading = pDShading;
        if (pDShading != null) {
            getCOSDictionary().setItem(COSName.SHADING, pDShading);
        } else {
            getCOSDictionary().removeItem(COSName.SHADING);
        }
    }
}
