package org.apache.pdfbox.pdmodel.font;

import android.graphics.PointF;
import android.util.Log;
import com.beust.jcommander.Parameters;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.fontbox.cff.CFFCIDFont;
import org.apache.fontbox.cff.CFFFont;
import org.apache.fontbox.cff.CFFParser;
import org.apache.fontbox.cff.CFFType1Font;
import org.apache.fontbox.cff.Type2CharString;
import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.awt.AffineTransform;

/* loaded from: classes5.dex */
public class PDCIDFontType0 extends PDCIDFont {
    private Float avgWidth;
    private final CFFCIDFont cidFont;
    private Matrix fontMatrix;
    private AffineTransform fontMatrixTransform;
    private final Map<Integer, Float> glyphHeights;
    private final boolean isDamaged;
    private final boolean isEmbedded;
    private final CFFType1Font t1Font;

    private float getAverageCharacterWidth() {
        return 500.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x010b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public PDCIDFontType0(COSDictionary cOSDictionary, PDType0Font pDType0Font) throws IOException {
        super(cOSDictionary, pDType0Font);
        CFFFont cFFFont;
        boolean z;
        String str;
        CFFCIDFont cFFCIDFontFallback;
        PDStream fontFile3;
        this.glyphHeights = new HashMap();
        this.avgWidth = null;
        PDFontDescriptor fontDescriptor = getFontDescriptor();
        byte[] byteArray = (fontDescriptor == null || (fontFile3 = fontDescriptor.getFontFile3()) == null) ? null : IOUtils.toByteArray(fontFile3.createInputStream());
        if (byteArray != null && byteArray.length > 0 && (byteArray[0] & 255) == 37) {
            Log.e("PdfBoxAndroid", "Unsupported: Type1 font instead of CFF in " + fontDescriptor.getFontName());
        } else {
            if (byteArray != null) {
                try {
                    cFFFont = new CFFParser().parse(byteArray).get(0);
                } catch (IOException e) {
                    Log.e("PdfBoxAndroid", "Can't read the embedded CFF font " + fontDescriptor.getFontName(), e);
                }
            } else {
                cFFFont = null;
            }
            z = false;
            if (cFFFont == null) {
                if (cFFFont instanceof CFFCIDFont) {
                    this.cidFont = (CFFCIDFont) cFFFont;
                    this.t1Font = null;
                } else {
                    this.cidFont = null;
                    this.t1Font = (CFFType1Font) cFFFont;
                }
                this.isEmbedded = true;
                this.isDamaged = false;
            } else {
                CFFCIDFont cFFCIDFont = ExternalFonts.getCFFCIDFont(getBaseFont());
                if (cFFCIDFont != null) {
                    this.cidFont = cFFCIDFont;
                    this.t1Font = null;
                } else {
                    COSDictionary cOSDictionary2 = (COSDictionary) this.dict.getDictionaryObject(COSName.CIDSYSTEMINFO);
                    if (cOSDictionary2 != null) {
                        String nameAsString = cOSDictionary2.getNameAsString(COSName.REGISTRY);
                        String nameAsString2 = cOSDictionary2.getNameAsString(COSName.ORDERING);
                        if (nameAsString != null && nameAsString2 != null) {
                            str = nameAsString + Parameters.DEFAULT_OPTION_PREFIXES + nameAsString2;
                            cFFCIDFontFallback = ExternalFonts.getCFFCIDFontFallback(str, getFontDescriptor());
                            this.cidFont = cFFCIDFontFallback;
                            this.t1Font = null;
                            if (cFFCIDFontFallback.getName().equals("AdobeBlank")) {
                                Log.w("PdfBoxAndroid", "Using fallback for CID-keyed font " + getBaseFont());
                            } else if (!z) {
                                Log.e("PdfBoxAndroid", "Missing CID-keyed font " + getBaseFont());
                            }
                        }
                    }
                    str = null;
                    cFFCIDFontFallback = ExternalFonts.getCFFCIDFontFallback(str, getFontDescriptor());
                    this.cidFont = cFFCIDFontFallback;
                    this.t1Font = null;
                    if (cFFCIDFontFallback.getName().equals("AdobeBlank")) {
                    }
                }
                this.isEmbedded = false;
                this.isDamaged = z;
            }
            AffineTransform createAffineTransform = getFontMatrix().createAffineTransform();
            this.fontMatrixTransform = createAffineTransform;
            createAffineTransform.scale(1000.0d, 1000.0d);
        }
        cFFFont = null;
        z = true;
        if (cFFFont == null) {
        }
        AffineTransform createAffineTransform2 = getFontMatrix().createAffineTransform();
        this.fontMatrixTransform = createAffineTransform2;
        createAffineTransform2.scale(1000.0d, 1000.0d);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDCIDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public Matrix getFontMatrix() {
        List<Number> fontMatrix;
        if (this.fontMatrix == null) {
            CFFCIDFont cFFCIDFont = this.cidFont;
            if (cFFCIDFont != null) {
                fontMatrix = cFFCIDFont.getFontMatrix();
            } else {
                fontMatrix = this.t1Font.getFontMatrix();
            }
            if (fontMatrix != null && fontMatrix.size() == 6) {
                this.fontMatrix = new Matrix(fontMatrix.get(0).floatValue(), fontMatrix.get(1).floatValue(), fontMatrix.get(2).floatValue(), fontMatrix.get(3).floatValue(), fontMatrix.get(4).floatValue(), fontMatrix.get(5).floatValue());
            } else {
                this.fontMatrix = new Matrix(0.001f, 0.0f, 0.0f, 0.001f, 0.0f, 0.0f);
            }
        }
        return this.fontMatrix;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDCIDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public BoundingBox getBoundingBox() {
        CFFCIDFont cFFCIDFont = this.cidFont;
        if (cFFCIDFont != null) {
            return cFFCIDFont.getFontBBox();
        }
        return this.t1Font.getFontBBox();
    }

    public CFFFont getCFFFont() {
        CFFCIDFont cFFCIDFont = this.cidFont;
        return cFFCIDFont != null ? cFFCIDFont : this.t1Font;
    }

    public Type2CharString getType2CharString(int i) throws IOException {
        CFFCIDFont cFFCIDFont = this.cidFont;
        if (cFFCIDFont != null) {
            return cFFCIDFont.getType2CharString(i);
        }
        return this.t1Font.getType2CharString(i);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDCIDFont
    public int codeToCID(int i) {
        return this.parent.getCMap().toCID(i);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDCIDFont
    public int codeToGID(int i) {
        int codeToCID = codeToCID(i);
        CFFCIDFont cFFCIDFont = this.cidFont;
        return cFFCIDFont != null ? cFFCIDFont.getCharset().getGIDForCID(codeToCID) : codeToCID;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDCIDFont
    public byte[] encode(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDCIDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getWidthFromFont(int i) throws IOException {
        PointF pointF = new PointF(getType2CharString(codeToCID(i)).getWidth(), 0.0f);
        this.fontMatrixTransform.transform(pointF, pointF);
        return pointF.x;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDCIDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public boolean isEmbedded() {
        return this.isEmbedded;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public boolean isDamaged() {
        return this.isDamaged;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getHeight(int i) throws IOException {
        int codeToCID = codeToCID(i);
        if (this.glyphHeights.containsKey(Integer.valueOf(codeToCID))) {
            return 0.0f;
        }
        float height = getType2CharString(codeToCID).getBounds().height();
        this.glyphHeights.put(Integer.valueOf(codeToCID), Float.valueOf(height));
        return height;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDCIDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getAverageFontWidth() {
        if (this.avgWidth == null) {
            this.avgWidth = Float.valueOf(getAverageCharacterWidth());
        }
        return this.avgWidth.floatValue();
    }
}
