package org.apache.pdfbox.pdmodel.font;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.fontbox.ttf.CmapSubtable;
import org.apache.fontbox.ttf.CmapTable;
import org.apache.fontbox.ttf.GlyphData;
import org.apache.fontbox.ttf.TTFParser;
import org.apache.fontbox.ttf.TrueTypeFont;
import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.encoding.Encoding;
import org.apache.pdfbox.pdmodel.font.encoding.GlyphList;
import org.apache.pdfbox.pdmodel.font.encoding.MacOSRomanEncoding;

/* loaded from: classes5.dex */
public class PDTrueTypeFont extends PDSimpleFont {
    private static final Map<String, Integer> INVERTED_MACOS_ROMAN = new HashMap();
    private static final int START_RANGE_F000 = 61440;
    private static final int START_RANGE_F100 = 61696;
    private static final int START_RANGE_F200 = 61952;
    private boolean cmapInitialized;
    private CmapSubtable cmapMacRoman;
    private CmapSubtable cmapWinSymbol;
    private CmapSubtable cmapWinUnicode;
    private final boolean isDamaged;
    private final boolean isEmbedded;
    private final TrueTypeFont ttf;

    @Override // org.apache.pdfbox.pdmodel.font.PDSimpleFont
    protected Encoding readEncodingFromFont() throws IOException {
        return null;
    }

    static {
        for (Map.Entry<Integer, String> entry : MacOSRomanEncoding.INSTANCE.getCodeToNameMap().entrySet()) {
            Map<String, Integer> map = INVERTED_MACOS_ROMAN;
            if (!map.containsKey(entry.getValue())) {
                map.put(entry.getValue(), entry.getKey());
            }
        }
    }

    @Deprecated
    public static PDTrueTypeFont loadTTF(PDDocument pDDocument, File file) throws IOException {
        return new PDTrueTypeFont(pDDocument, new FileInputStream(file));
    }

    @Deprecated
    public static PDTrueTypeFont loadTTF(PDDocument pDDocument, InputStream inputStream) throws IOException {
        return new PDTrueTypeFont(pDDocument, inputStream);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public PDTrueTypeFont(COSDictionary cOSDictionary) throws IOException {
        super(cOSDictionary);
        boolean z;
        PDStream fontFile2;
        TrueTypeFont trueTypeFont = null;
        this.cmapWinUnicode = null;
        this.cmapWinSymbol = null;
        this.cmapMacRoman = null;
        this.cmapInitialized = false;
        if (getFontDescriptor() != null && (fontFile2 = super.getFontDescriptor().getFontFile2()) != null) {
            try {
                trueTypeFont = new TTFParser(true).parse(fontFile2.createInputStream());
            } catch (IOException e) {
                Log.w("PdfBoxAndroid", "Could not read embedded TTF for font " + getBaseFont(), e);
                z = true;
                this.isEmbedded = trueTypeFont != null;
                this.isDamaged = z;
                if (trueTypeFont == null) {
                    trueTypeFont = ExternalFonts.getTrueTypeFallbackFont(getFontDescriptor());
                    Log.w("PdfBoxAndroid", "Using fallback font '" + trueTypeFont + "' for '" + getBaseFont() + "'");
                }
                this.ttf = trueTypeFont;
                readEncoding();
            } catch (NullPointerException e2) {
                Log.w("PdfBoxAndroid", "Could not read embedded TTF for font " + getBaseFont(), e2);
                z = true;
                this.isEmbedded = trueTypeFont != null;
                this.isDamaged = z;
                if (trueTypeFont == null) {
                }
                this.ttf = trueTypeFont;
                readEncoding();
            }
        }
        z = false;
        this.isEmbedded = trueTypeFont != null;
        this.isDamaged = z;
        if (trueTypeFont == null && (trueTypeFont = ExternalFonts.getTrueTypeFont(getBaseFont())) == null) {
            trueTypeFont = ExternalFonts.getTrueTypeFallbackFont(getFontDescriptor());
            Log.w("PdfBoxAndroid", "Using fallback font '" + trueTypeFont + "' for '" + getBaseFont() + "'");
        }
        this.ttf = trueTypeFont;
        readEncoding();
    }

    public String getBaseFont() {
        return this.dict.getNameAsString(COSName.BASE_FONT);
    }

    private PDTrueTypeFont(PDDocument pDDocument, InputStream inputStream) throws IOException {
        this.cmapWinUnicode = null;
        this.cmapWinSymbol = null;
        this.cmapMacRoman = null;
        this.cmapInitialized = false;
        PDTrueTypeFontEmbedder pDTrueTypeFontEmbedder = new PDTrueTypeFontEmbedder(pDDocument, this.dict, inputStream);
        this.encoding = pDTrueTypeFontEmbedder.getFontEncoding();
        this.ttf = pDTrueTypeFontEmbedder.getTrueTypeFont();
        setFontDescriptor(pDTrueTypeFontEmbedder.getFontDescriptor());
        this.isEmbedded = true;
        this.isDamaged = false;
        this.glyphList = GlyphList.getAdobeGlyphList();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public int readCode(InputStream inputStream) throws IOException {
        return inputStream.read();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public String getName() {
        return getBaseFont();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public BoundingBox getBoundingBox() throws IOException {
        return this.ttf.getFontBBox();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public boolean isDamaged() {
        return this.isDamaged;
    }

    public TrueTypeFont getTrueTypeFont() {
        return this.ttf;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getWidthFromFont(int i) throws IOException {
        if (getStandard14AFM() != null && getEncoding() != null) {
            return getStandard14Width(i);
        }
        float advanceWidth = this.ttf.getAdvanceWidth(codeToGID(i));
        float unitsPerEm = this.ttf.getUnitsPerEm();
        return unitsPerEm != 1000.0f ? advanceWidth * (1000.0f / unitsPerEm) : advanceWidth;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getHeight(int i) throws IOException {
        GlyphData glyph = this.ttf.getGlyph().getGlyph(codeToGID(i));
        if (glyph != null) {
            return glyph.getBoundingBox().getHeight();
        }
        return 0.0f;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    protected byte[] encode(int i) throws IOException {
        if (!getEncoding().contains(getGlyphList().codePointToName(i))) {
            throw new IllegalArgumentException("This font type only supports 8-bit code points");
        }
        if (codeToGID(i) != 0) {
            return new byte[]{(byte) i};
        }
        throw new IllegalArgumentException(String.format("U+%04X is not available in this font's Encoding", Integer.valueOf(i)));
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public boolean isEmbedded() {
        return this.isEmbedded;
    }

    public int codeToGID(int i) throws IOException {
        CmapSubtable cmapSubtable;
        Integer num;
        String unicode;
        extractCmapTable();
        int i2 = 0;
        if (!isSymbolic()) {
            String name = this.encoding.getName(i);
            if (name.equals(".notdef")) {
                return 0;
            }
            if (this.cmapWinUnicode != null && (unicode = GlyphList.getAdobeGlyphList().toUnicode(name)) != null) {
                i2 = this.cmapWinUnicode.getGlyphId(unicode.codePointAt(0));
            }
            if (i2 == 0 && this.cmapMacRoman != null && (num = INVERTED_MACOS_ROMAN.get(name)) != null) {
                i2 = this.cmapMacRoman.getGlyphId(num.intValue());
            }
            if (i2 == 0) {
                i2 = this.ttf.nameToGID(name);
            }
        } else {
            CmapSubtable cmapSubtable2 = this.cmapWinSymbol;
            if (cmapSubtable2 != null) {
                i2 = cmapSubtable2.getGlyphId(i);
                if (i >= 0 && i <= 255) {
                    if (i2 == 0) {
                        i2 = this.cmapWinSymbol.getGlyphId(START_RANGE_F000 + i);
                    }
                    if (i2 == 0) {
                        i2 = this.cmapWinSymbol.getGlyphId(START_RANGE_F100 + i);
                    }
                    if (i2 == 0) {
                        i2 = this.cmapWinSymbol.getGlyphId(START_RANGE_F200 + i);
                    }
                }
            }
            if (i2 == 0 && (cmapSubtable = this.cmapMacRoman) != null) {
                i2 = cmapSubtable.getGlyphId(i);
            }
        }
        if (i2 == 0) {
            Log.w("PdfBoxAndroid", "Can't map code " + i + " in font " + getBaseFont());
        }
        return i2;
    }

    private void extractCmapTable() throws IOException {
        if (this.cmapInitialized) {
            return;
        }
        CmapTable cmap = this.ttf.getCmap();
        if (cmap != null) {
            for (CmapSubtable cmapSubtable : cmap.getCmaps()) {
                if (3 == cmapSubtable.getPlatformId()) {
                    if (1 == cmapSubtable.getPlatformEncodingId()) {
                        this.cmapWinUnicode = cmapSubtable;
                    } else if (cmapSubtable.getPlatformEncodingId() == 0) {
                        this.cmapWinSymbol = cmapSubtable;
                    }
                } else if (1 == cmapSubtable.getPlatformId() && cmapSubtable.getPlatformEncodingId() == 0) {
                    this.cmapMacRoman = cmapSubtable;
                }
            }
        }
        this.cmapInitialized = true;
    }
}
