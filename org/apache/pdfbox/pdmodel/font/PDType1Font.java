package org.apache.pdfbox.pdmodel.font;

import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.fontbox.ttf.Type1Equivalent;
import org.apache.fontbox.type1.DamagedFontException;
import org.apache.fontbox.type1.Type1Font;
import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.encoding.Encoding;
import org.apache.pdfbox.pdmodel.font.encoding.StandardEncoding;
import org.apache.pdfbox.pdmodel.font.encoding.Type1Encoding;
import org.apache.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;
import org.apache.pdfbox.util.Matrix;

/* loaded from: classes5.dex */
public class PDType1Font extends PDSimpleFont implements PDType1Equivalent {
    private static final Map<String, String> ALT_NAMES;
    public static final PDType1Font COURIER;
    public static final PDType1Font COURIER_BOLD;
    public static final PDType1Font COURIER_BOLD_OBLIQUE;
    public static final PDType1Font COURIER_OBLIQUE;
    public static final PDType1Font HELVETICA;
    public static final PDType1Font HELVETICA_BOLD;
    public static final PDType1Font HELVETICA_BOLD_OBLIQUE;
    public static final PDType1Font HELVETICA_OBLIQUE;
    private static final int PFB_START_MARKER = 128;
    public static final PDType1Font SYMBOL;
    public static final PDType1Font TIMES_BOLD;
    public static final PDType1Font TIMES_BOLD_ITALIC;
    public static final PDType1Font TIMES_ITALIC;
    public static final PDType1Font TIMES_ROMAN;
    public static final PDType1Font ZAPF_DINGBATS;
    private Matrix fontMatrix;
    private Map<String, Integer> invertedEncoding;
    private final boolean isDamaged;
    private final boolean isEmbedded;
    private final Type1Equivalent type1Equivalent;
    private final Type1Font type1font;

    static {
        HashMap hashMap = new HashMap();
        ALT_NAMES = hashMap;
        hashMap.put("ff", "f_f");
        hashMap.put("ffi", "f_f_i");
        hashMap.put("ffl", "f_f_l");
        hashMap.put("fi", "f_i");
        hashMap.put("fl", "f_l");
        hashMap.put("st", "s_t");
        hashMap.put("IJ", "I_J");
        hashMap.put("ij", "i_j");
        hashMap.put("ellipsis", "elipsis");
        TIMES_ROMAN = new PDType1Font("Times-Roman");
        TIMES_BOLD = new PDType1Font("Times-Bold");
        TIMES_ITALIC = new PDType1Font("Times-Italic");
        TIMES_BOLD_ITALIC = new PDType1Font("Times-BoldItalic");
        HELVETICA = new PDType1Font("Helvetica");
        HELVETICA_BOLD = new PDType1Font("Helvetica-Bold");
        HELVETICA_OBLIQUE = new PDType1Font("Helvetica-Oblique");
        HELVETICA_BOLD_OBLIQUE = new PDType1Font("Helvetica-BoldOblique");
        COURIER = new PDType1Font("Courier");
        COURIER_BOLD = new PDType1Font("Courier-Bold");
        COURIER_OBLIQUE = new PDType1Font("Courier-Oblique");
        COURIER_BOLD_OBLIQUE = new PDType1Font("Courier-BoldOblique");
        SYMBOL = new PDType1Font("Symbol");
        ZAPF_DINGBATS = new PDType1Font("ZapfDingbats");
    }

    private PDType1Font(String str) {
        super(str);
        this.dict.setItem(COSName.SUBTYPE, (COSBase) COSName.TYPE1);
        this.dict.setName(COSName.BASE_FONT, str);
        this.encoding = new WinAnsiEncoding();
        this.dict.setItem(COSName.ENCODING, (COSBase) COSName.WIN_ANSI_ENCODING);
        this.type1font = null;
        this.type1Equivalent = ExternalFonts.getType1EquivalentFont(getBaseFont());
        this.isEmbedded = false;
        this.isDamaged = false;
    }

    public PDType1Font(PDDocument pDDocument, InputStream inputStream, InputStream inputStream2) throws IOException {
        PDType1FontEmbedder pDType1FontEmbedder = new PDType1FontEmbedder(pDDocument, this.dict, inputStream, inputStream2);
        this.encoding = pDType1FontEmbedder.getFontEncoding();
        this.type1font = pDType1FontEmbedder.getType1Font();
        this.type1Equivalent = pDType1FontEmbedder.getType1Font();
        this.isEmbedded = true;
        this.isDamaged = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ac  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public PDType1Font(COSDictionary cOSDictionary) throws IOException {
        super(cOSDictionary);
        boolean z;
        Type1Font createWithSegments;
        PDFontDescriptor fontDescriptor = getFontDescriptor();
        Type1Font type1Font = null;
        if (fontDescriptor != null) {
            if (fontDescriptor.getFontFile3() != null) {
                throw new IllegalArgumentException("Use PDType1CFont for FontFile3");
            }
            PDStream fontFile = fontDescriptor.getFontFile();
            if (fontFile != null) {
                try {
                    COSStream stream = fontFile.getStream();
                    int i = stream.getInt(COSName.LENGTH1);
                    int i2 = stream.getInt(COSName.LENGTH2);
                    byte[] byteArray = fontFile.getByteArray();
                    int repairLength1 = repairLength1(byteArray, i);
                    if (byteArray.length > 0 && (byteArray[0] & 255) == 128) {
                        createWithSegments = Type1Font.createWithPFB(byteArray);
                    } else {
                        createWithSegments = (repairLength1 > 0 && i2 > 0) ? Type1Font.createWithSegments(Arrays.copyOfRange(byteArray, 0, repairLength1), Arrays.copyOfRange(byteArray, repairLength1, repairLength1 + i2)) : createWithSegments;
                    }
                    type1Font = createWithSegments;
                } catch (DamagedFontException unused) {
                    Log.w("PdfBoxAndroid", "Can't read damaged embedded Type1 font " + fontDescriptor.getFontName());
                    z = true;
                    this.isEmbedded = type1Font != null;
                    this.isDamaged = z;
                    if (type1Font == null) {
                    }
                    this.type1font = type1Font;
                    if (type1Font != null) {
                    }
                    readEncoding();
                } catch (IOException e) {
                    Log.e("PdfBoxAndroid", "Can't read the embedded Type1 font " + fontDescriptor.getFontName(), e);
                    z = true;
                    this.isEmbedded = type1Font != null;
                    this.isDamaged = z;
                    if (type1Font == null) {
                    }
                    this.type1font = type1Font;
                    if (type1Font != null) {
                    }
                    readEncoding();
                }
            }
        }
        z = false;
        this.isEmbedded = type1Font != null;
        this.isDamaged = z;
        type1Font = type1Font == null ? ExternalFonts.getType1Font(getBaseFont()) : type1Font;
        this.type1font = type1Font;
        if (type1Font != null) {
            this.type1Equivalent = type1Font;
        } else {
            Type1Equivalent type1EquivalentFont = ExternalFonts.getType1EquivalentFont(getBaseFont());
            if (type1EquivalentFont != null) {
                this.type1Equivalent = type1EquivalentFont;
            } else {
                Type1Equivalent type1FallbackFont = ExternalFonts.getType1FallbackFont(getFontDescriptor());
                this.type1Equivalent = type1FallbackFont;
                Log.w("PdfBoxAndroid", "Using fallback font " + type1FallbackFont.getName() + " for " + getBaseFont());
            }
        }
        readEncoding();
    }

    private int repairLength1(byte[] bArr, int i) {
        int max = Math.max(0, i - 4);
        while (true) {
            if (max <= 0) {
                break;
            }
            if (bArr[max + 0] == 101 && bArr[max + 1] == 120 && bArr[max + 2] == 101 && bArr[max + 3] == 99) {
                max += 4;
                while (max < i && (bArr[max] == 13 || bArr[max] == 10 || bArr[max] == 32)) {
                    max++;
                }
            } else {
                max--;
            }
        }
        if (i - max == 0 || max <= 0) {
            return i;
        }
        Log.w("PdfBoxAndroid", "Ignored invalid Length1 for Type 1 font " + getName());
        return max;
    }

    public String getBaseFont() {
        return this.dict.getNameAsString(COSName.BASE_FONT);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getHeight(int i) throws IOException {
        String codeToName = codeToName(i);
        if (getStandard14AFM() != null) {
            return getStandard14AFM().getCharacterHeight(getEncoding().getName(i));
        }
        RectF rectF = new RectF();
        this.type1Equivalent.getPath(codeToName).computeBounds(rectF, true);
        return rectF.height();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    protected byte[] encode(int i) throws IOException {
        if (i > 255) {
            throw new IllegalArgumentException("This font type only supports 8-bit code points");
        }
        String codePointToName = getGlyphList().codePointToName(i);
        String nameInFont = getNameInFont(codePointToName);
        Map<String, Integer> invertedEncoding = getInvertedEncoding();
        if (nameInFont.equals(".notdef") || !this.type1Equivalent.hasGlyph(nameInFont)) {
            throw new IllegalArgumentException(String.format("No glyph for U+%04X in font %s", Integer.valueOf(i), getName()));
        }
        return new byte[]{(byte) invertedEncoding.get(codePointToName).intValue()};
    }

    private Map<String, Integer> getInvertedEncoding() {
        Map<String, Integer> map = this.invertedEncoding;
        if (map != null) {
            return map;
        }
        this.invertedEncoding = new HashMap();
        for (Map.Entry<Integer, String> entry : this.encoding.getCodeToNameMap().entrySet()) {
            if (!this.invertedEncoding.containsKey(entry.getValue())) {
                this.invertedEncoding.put(entry.getValue(), entry.getKey());
            }
        }
        return this.invertedEncoding;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getWidthFromFont(int i) throws IOException {
        String codeToName = codeToName(i);
        if (getStandard14AFM() != null) {
            return getStandard14Width(i);
        }
        return this.type1Equivalent.getWidth(codeToName);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public boolean isEmbedded() {
        return this.isEmbedded;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getAverageFontWidth() {
        if (getStandard14AFM() != null) {
            return getStandard14AFM().getAverageCharacterWidth();
        }
        return super.getAverageFontWidth();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public int readCode(InputStream inputStream) throws IOException {
        return inputStream.read();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDSimpleFont
    protected Encoding readEncodingFromFont() throws IOException {
        if (getStandard14AFM() != null) {
            return new Type1Encoding(getStandard14AFM());
        }
        if (this.type1Equivalent.getEncoding() != null) {
            return Type1Encoding.fromFontBox(this.type1Equivalent.getEncoding());
        }
        return StandardEncoding.INSTANCE;
    }

    public Type1Font getType1Font() {
        return this.type1font;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDType1Equivalent
    public Type1Equivalent getType1Equivalent() {
        return this.type1Equivalent;
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
    public String codeToName(int i) throws IOException {
        return getNameInFont(getEncoding().getName(i));
    }

    private String getNameInFont(String str) throws IOException {
        if (isEmbedded() || this.type1Equivalent.hasGlyph(str)) {
            return str;
        }
        String str2 = ALT_NAMES.get(str);
        if (str2 != null && !str.equals(".notdef") && this.type1Equivalent.hasGlyph(str2)) {
            return str2;
        }
        String unicode = getGlyphList().toUnicode(str);
        if (unicode != null && unicode.length() == 1) {
            String format = String.format("uni%04X", Integer.valueOf(unicode.codePointAt(0)));
            if (this.type1Equivalent.hasGlyph(format)) {
                return format;
            }
        }
        return ".notdef";
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDType1Equivalent
    public Path getPath(String str) throws IOException {
        if (str.equals(".notdef") && !this.isEmbedded) {
            return new Path();
        }
        return this.type1Equivalent.getPath(str);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public Matrix getFontMatrix() {
        if (this.fontMatrix == null) {
            Type1Font type1Font = this.type1font;
            if (type1Font != null) {
                List<Number> fontMatrix = type1Font.getFontMatrix();
                if (fontMatrix != null && fontMatrix.size() == 6) {
                    this.fontMatrix = new Matrix(fontMatrix.get(0).floatValue(), fontMatrix.get(1).floatValue(), fontMatrix.get(2).floatValue(), fontMatrix.get(3).floatValue(), fontMatrix.get(4).floatValue(), fontMatrix.get(5).floatValue());
                } else {
                    return super.getFontMatrix();
                }
            } else {
                this.fontMatrix = DEFAULT_FONT_MATRIX;
            }
        }
        return this.fontMatrix;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public boolean isDamaged() {
        return this.isDamaged;
    }
}
