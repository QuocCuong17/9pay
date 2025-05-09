package org.apache.pdfbox.pdmodel.font;

import android.graphics.Path;
import android.graphics.PointF;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.fontbox.cff.CFFParser;
import org.apache.fontbox.cff.CFFType1Font;
import org.apache.fontbox.ttf.Type1Equivalent;
import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.encoding.Encoding;
import org.apache.pdfbox.pdmodel.font.encoding.Type1Encoding;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.awt.AffineTransform;

/* loaded from: classes5.dex */
public class PDType1CFont extends PDSimpleFont implements PDType1Equivalent {
    private Float avgWidth;
    private final CFFType1Font cffFont;
    private Matrix fontMatrix;
    private final AffineTransform fontMatrixTransform;
    private final Map<String, Float> glyphHeights;
    private final boolean isDamaged;
    private final boolean isEmbedded;
    private final Type1Equivalent type1Equivalent;

    private float getAverageCharacterWidth() {
        return 500.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0043 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public PDType1CFont(COSDictionary cOSDictionary) throws IOException {
        super(cOSDictionary);
        byte[] bArr;
        boolean z;
        PDStream fontFile3;
        this.glyphHeights = new HashMap();
        CFFType1Font cFFType1Font = null;
        this.avgWidth = null;
        PDFontDescriptor fontDescriptor = getFontDescriptor();
        if (fontDescriptor != null && (fontFile3 = fontDescriptor.getFontFile3()) != null) {
            bArr = IOUtils.toByteArray(fontFile3.createInputStream());
            if (bArr.length == 0) {
                Log.e("PdfBoxAndroid", "Invalid data for embedded Type1C font " + getName());
            }
            if (bArr != null) {
                try {
                    cFFType1Font = (CFFType1Font) new CFFParser().parse(bArr).get(0);
                } catch (IOException e) {
                    Log.e("PdfBoxAndroid", "Can't read the embedded Type1C font " + getName(), e);
                    z = true;
                }
            }
            z = false;
            this.isDamaged = z;
            this.cffFont = cFFType1Font;
            if (cFFType1Font == null) {
                this.type1Equivalent = cFFType1Font;
                this.isEmbedded = true;
            } else {
                Type1Equivalent type1EquivalentFont = ExternalFonts.getType1EquivalentFont(getBaseFont());
                if (type1EquivalentFont != null) {
                    this.type1Equivalent = type1EquivalentFont;
                } else {
                    Type1Equivalent type1FallbackFont = ExternalFonts.getType1FallbackFont(getFontDescriptor());
                    this.type1Equivalent = type1FallbackFont;
                    Log.w("PdfBoxAndroid", "Using fallback font " + type1FallbackFont.getName() + " for " + getBaseFont());
                }
                this.isEmbedded = false;
            }
            readEncoding();
            AffineTransform createAffineTransform = getFontMatrix().createAffineTransform();
            this.fontMatrixTransform = createAffineTransform;
            createAffineTransform.scale(1000.0d, 1000.0d);
        }
        bArr = null;
        if (bArr != null) {
        }
        z = false;
        this.isDamaged = z;
        this.cffFont = cFFType1Font;
        if (cFFType1Font == null) {
        }
        readEncoding();
        AffineTransform createAffineTransform2 = getFontMatrix().createAffineTransform();
        this.fontMatrixTransform = createAffineTransform2;
        createAffineTransform2.scale(1000.0d, 1000.0d);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDType1Equivalent
    public Type1Equivalent getType1Equivalent() {
        return this.type1Equivalent;
    }

    public String getBaseFont() {
        return this.dict.getNameAsString(COSName.BASE_FONT);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDType1Equivalent
    public Path getPath(String str) throws IOException {
        if (isEmbedded() && str.equals(".notdef") && !isEmbedded() && !isStandard14()) {
            return new Path();
        }
        return this.type1Equivalent.getPath(str);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public String getName() {
        return getBaseFont();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public BoundingBox getBoundingBox() throws IOException {
        return this.type1Equivalent.getFontBBox();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDType1Equivalent
    public String codeToName(int i) {
        return getEncoding().getName(i);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDSimpleFont
    protected Encoding readEncodingFromFont() throws IOException {
        return Type1Encoding.fromFontBox(this.type1Equivalent.getEncoding());
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public int readCode(InputStream inputStream) throws IOException {
        return inputStream.read();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public Matrix getFontMatrix() {
        CFFType1Font cFFType1Font;
        List<Number> fontMatrix;
        if (this.fontMatrix == null && (cFFType1Font = this.cffFont) != null && (fontMatrix = cFFType1Font.getFontMatrix()) != null && fontMatrix.size() == 6) {
            Matrix matrix = new Matrix(fontMatrix.get(0).floatValue(), fontMatrix.get(1).floatValue(), fontMatrix.get(2).floatValue(), fontMatrix.get(3).floatValue(), fontMatrix.get(4).floatValue(), fontMatrix.get(5).floatValue());
            this.fontMatrix = matrix;
            return matrix;
        }
        return this.fontMatrix;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public boolean isDamaged() {
        return this.isDamaged;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getWidthFromFont(int i) throws IOException {
        if (getStandard14AFM() != null) {
            return getStandard14Width(i);
        }
        PointF pointF = new PointF(this.type1Equivalent.getWidth(codeToName(i)), 0.0f);
        this.fontMatrixTransform.transform(pointF, pointF);
        return pointF.x;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public boolean isEmbedded() {
        return this.isEmbedded;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getHeight(int i) throws IOException {
        String codeToName = codeToName(i);
        if (this.glyphHeights.containsKey(codeToName)) {
            return 0.0f;
        }
        float height = this.cffFont.getType1CharString(codeToName).getBounds().height();
        this.glyphHeights.put(codeToName, Float.valueOf(height));
        return height;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    protected byte[] encode(int i) throws IOException {
        throw new UnsupportedOperationException("Not implemented: Type1C");
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public float getStringWidth(String str) throws IOException {
        float f = 0.0f;
        for (int i = 0; i < str.length(); i++) {
            f += this.cffFont.getType1CharString(getGlyphList().codePointToName(str.codePointAt(i))).getWidth();
        }
        return f;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getAverageFontWidth() {
        if (this.avgWidth == null) {
            this.avgWidth = Float.valueOf(getAverageCharacterWidth());
        }
        return this.avgWidth.floatValue();
    }

    public CFFType1Font getCFFType1Font() {
        return this.cffFont;
    }
}
