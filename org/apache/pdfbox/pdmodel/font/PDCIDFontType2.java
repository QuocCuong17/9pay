package org.apache.pdfbox.pdmodel.font;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.fontbox.cmap.CMap;
import org.apache.fontbox.ttf.CmapSubtable;
import org.apache.fontbox.ttf.OTFParser;
import org.apache.fontbox.ttf.OpenTypeFont;
import org.apache.fontbox.ttf.TTFParser;
import org.apache.fontbox.ttf.TrueTypeFont;
import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.util.Matrix;

/* loaded from: classes5.dex */
public class PDCIDFontType2 extends PDCIDFont {
    private final int[] cid2gid;
    private final CmapSubtable cmap;
    private Matrix fontMatrix;
    private final Map<Integer, Integer> gid2cid;
    private final boolean hasIdentityCid2Gid;
    private final boolean isDamaged;
    private final boolean isEmbedded;
    private final TrueTypeFont ttf;

    /* JADX WARN: Removed duplicated region for block: B:10:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00ed  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public PDCIDFontType2(COSDictionary cOSDictionary, PDType0Font pDType0Font) throws IOException {
        super(cOSDictionary, pDType0Font);
        boolean z;
        COSBase dictionaryObject;
        PDFontDescriptor fontDescriptor = getFontDescriptor();
        PDStream fontFile2 = fontDescriptor.getFontFile2();
        PDStream fontFile3 = fontDescriptor.getFontFile3();
        if (fontFile2 == null && fontFile3 == null) {
            fontFile2 = fontDescriptor.getFontFile();
        }
        TrueTypeFont trueTypeFont = null;
        TrueTypeFont trueTypeFont2 = null;
        boolean z2 = false;
        if (fontFile2 != null) {
            try {
                trueTypeFont = new TTFParser(true).parse(fontFile2.createInputStream());
            } catch (IOException e) {
                Log.w("PdfBoxAndroid", "Could not read embedded TTF for font " + getBaseFont(), e);
                z = true;
                this.isEmbedded = trueTypeFont2 != null;
                this.isDamaged = z;
                TrueTypeFont trueTypeFont3 = trueTypeFont2;
                if (trueTypeFont2 == null) {
                }
                this.ttf = trueTypeFont3;
                this.cmap = trueTypeFont3.getUnicodeCmap(false);
                int[] readCIDToGIDMap = readCIDToGIDMap();
                this.cid2gid = readCIDToGIDMap;
                this.gid2cid = invert(readCIDToGIDMap);
                dictionaryObject = this.dict.getDictionaryObject(COSName.CID_TO_GID_MAP);
                if (dictionaryObject instanceof COSName) {
                }
                this.hasIdentityCid2Gid = z2;
            } catch (NullPointerException e2) {
                Log.w("PdfBoxAndroid", "Could not read embedded TTF for font " + getBaseFont(), e2);
                z = true;
                this.isEmbedded = trueTypeFont2 != null;
                this.isDamaged = z;
                TrueTypeFont trueTypeFont32 = trueTypeFont2;
                if (trueTypeFont2 == null) {
                }
                this.ttf = trueTypeFont32;
                this.cmap = trueTypeFont32.getUnicodeCmap(false);
                int[] readCIDToGIDMap2 = readCIDToGIDMap();
                this.cid2gid = readCIDToGIDMap2;
                this.gid2cid = invert(readCIDToGIDMap2);
                dictionaryObject = this.dict.getDictionaryObject(COSName.CID_TO_GID_MAP);
                if (dictionaryObject instanceof COSName) {
                }
                this.hasIdentityCid2Gid = z2;
            }
        } else if (fontFile3 != null) {
            try {
                OpenTypeFont parse = new OTFParser(true).parse(fontFile3.createInputStream());
                if (parse.isPostScript()) {
                    throw new IOException("Not implemented: OpenType font with CFF table " + getBaseFont());
                }
                boolean hasLayoutTables = parse.hasLayoutTables();
                trueTypeFont = parse;
                if (hasLayoutTables) {
                    Log.e("PdfBoxAndroid", "OpenType Layout tables used in font " + getBaseFont() + " are not implemented in PDFBox and will be ignored");
                    trueTypeFont = parse;
                }
            } catch (IOException e3) {
                Log.w("PdfBoxAndroid", "Could not read embedded OTF for font " + getBaseFont(), e3);
                z = true;
                this.isEmbedded = trueTypeFont2 != null;
                this.isDamaged = z;
                TrueTypeFont trueTypeFont322 = trueTypeFont2;
                if (trueTypeFont2 == null) {
                }
                this.ttf = trueTypeFont322;
                this.cmap = trueTypeFont322.getUnicodeCmap(false);
                int[] readCIDToGIDMap22 = readCIDToGIDMap();
                this.cid2gid = readCIDToGIDMap22;
                this.gid2cid = invert(readCIDToGIDMap22);
                dictionaryObject = this.dict.getDictionaryObject(COSName.CID_TO_GID_MAP);
                if (dictionaryObject instanceof COSName) {
                    z2 = true;
                }
                this.hasIdentityCid2Gid = z2;
            } catch (NullPointerException e4) {
                Log.w("PdfBoxAndroid", "Could not read embedded OTF for font " + getBaseFont(), e4);
                z = true;
                this.isEmbedded = trueTypeFont2 != null;
                this.isDamaged = z;
                TrueTypeFont trueTypeFont3222 = trueTypeFont2;
                if (trueTypeFont2 == null) {
                }
                this.ttf = trueTypeFont3222;
                this.cmap = trueTypeFont3222.getUnicodeCmap(false);
                int[] readCIDToGIDMap222 = readCIDToGIDMap();
                this.cid2gid = readCIDToGIDMap222;
                this.gid2cid = invert(readCIDToGIDMap222);
                dictionaryObject = this.dict.getDictionaryObject(COSName.CID_TO_GID_MAP);
                if (dictionaryObject instanceof COSName) {
                }
                this.hasIdentityCid2Gid = z2;
            }
        }
        z = false;
        trueTypeFont2 = trueTypeFont;
        this.isEmbedded = trueTypeFont2 != null;
        this.isDamaged = z;
        TrueTypeFont trueTypeFont32222 = trueTypeFont2;
        if (trueTypeFont2 == null) {
            TrueTypeFont trueTypeFont4 = ExternalFonts.getTrueTypeFont(getBaseFont());
            trueTypeFont32222 = trueTypeFont4;
            if (trueTypeFont4 == null) {
                TrueTypeFont trueTypeFallbackFont = ExternalFonts.getTrueTypeFallbackFont(getFontDescriptor());
                Log.w("PdfBoxAndroid", "Using fallback font '" + trueTypeFallbackFont + "' for '" + getBaseFont() + "'");
                trueTypeFont32222 = trueTypeFallbackFont;
            }
        }
        this.ttf = trueTypeFont32222;
        this.cmap = trueTypeFont32222.getUnicodeCmap(false);
        int[] readCIDToGIDMap2222 = readCIDToGIDMap();
        this.cid2gid = readCIDToGIDMap2222;
        this.gid2cid = invert(readCIDToGIDMap2222);
        dictionaryObject = this.dict.getDictionaryObject(COSName.CID_TO_GID_MAP);
        if ((dictionaryObject instanceof COSName) && ((COSName) dictionaryObject).getName().equals("Identity")) {
            z2 = true;
        }
        this.hasIdentityCid2Gid = z2;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDCIDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public Matrix getFontMatrix() {
        if (this.fontMatrix == null) {
            this.fontMatrix = new Matrix(0.001f, 0.0f, 0.0f, 0.001f, 0.0f, 0.0f);
        }
        return this.fontMatrix;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDCIDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public BoundingBox getBoundingBox() throws IOException {
        return this.ttf.getFontBBox();
    }

    private int[] readCIDToGIDMap() throws IOException {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.CID_TO_GID_MAP);
        if (!(dictionaryObject instanceof COSStream)) {
            return null;
        }
        InputStream unfilteredStream = ((COSStream) dictionaryObject).getUnfilteredStream();
        byte[] byteArray = IOUtils.toByteArray(unfilteredStream);
        IOUtils.closeQuietly(unfilteredStream);
        int length = byteArray.length / 2;
        int[] iArr = new int[length];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = ((byteArray[i] & 255) << 8) | (byteArray[i + 1] & 255);
            i += 2;
        }
        return iArr;
    }

    private Map<Integer, Integer> invert(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < iArr.length; i++) {
            hashMap.put(Integer.valueOf(iArr[i]), Integer.valueOf(i));
        }
        return hashMap;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDCIDFont
    public int codeToCID(int i) {
        CMap cMap = this.parent.getCMap();
        if (!cMap.hasCIDMappings() && cMap.hasUnicodeMappings()) {
            return cMap.toUnicode(i).codePointAt(0);
        }
        return cMap.toCID(i);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDCIDFont
    public int codeToGID(int i) throws IOException {
        if (!this.isEmbedded) {
            boolean z = this.parent.getCMapUCS2() != null;
            if (this.cid2gid != null) {
                return this.cid2gid[codeToCID(i)];
            }
            if (this.hasIdentityCid2Gid || !z) {
                return codeToCID(i);
            }
            String unicode = this.parent.toUnicode(i);
            if (unicode == null) {
                Log.w("PdfBoxAndroid", "Failed to find a character mapping for " + i + " in " + getName());
                return 0;
            }
            if (unicode.length() > 1) {
                Log.w("PdfBoxAndroid", "Trying to map multi-byte character using 'cmap', result will be poor");
            }
            return this.cmap.getGlyphId(unicode.codePointAt(0));
        }
        int codeToCID = codeToCID(i);
        int[] iArr = this.cid2gid;
        if (iArr != null) {
            if (codeToCID < iArr.length) {
                return iArr[codeToCID];
            }
            return 0;
        }
        if (codeToCID < this.ttf.getNumberOfGlyphs()) {
            return codeToCID;
        }
        return 0;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getHeight(int i) throws IOException {
        return (this.ttf.getHorizontalHeader().getAscender() + (-this.ttf.getHorizontalHeader().getDescender())) / this.ttf.getUnitsPerEm();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDCIDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getWidthFromFont(int i) throws IOException {
        int advanceWidth = this.ttf.getAdvanceWidth(codeToGID(i));
        int unitsPerEm = this.ttf.getUnitsPerEm();
        if (unitsPerEm != 1000) {
            advanceWidth = (int) (advanceWidth * (1000.0f / unitsPerEm));
        }
        return advanceWidth;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0037  */
    @Override // org.apache.pdfbox.pdmodel.font.PDCIDFont
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public byte[] encode(int i) {
        int glyphId;
        if (this.isEmbedded) {
            if (this.parent.getCMap().getName().startsWith("Identity-")) {
                CmapSubtable cmapSubtable = this.cmap;
                if (cmapSubtable != null) {
                    glyphId = cmapSubtable.getGlyphId(i);
                    if (glyphId == -1) {
                        glyphId = 0;
                    }
                }
                glyphId = -1;
                if (glyphId == -1) {
                }
            } else {
                if (this.parent.getCMapUCS2() != null) {
                    glyphId = this.parent.getCMapUCS2().toCID(i);
                    if (glyphId == -1) {
                    }
                }
                glyphId = -1;
                if (glyphId == -1) {
                }
            }
        } else {
            glyphId = this.cmap.getGlyphId(i);
        }
        if (glyphId != 0) {
            return new byte[]{(byte) ((glyphId >> 8) & 255), (byte) (glyphId & 255)};
        }
        throw new IllegalArgumentException(String.format("No glyph for U+%04X in font %s", Integer.valueOf(i), getName()));
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDCIDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public boolean isEmbedded() {
        return this.isEmbedded;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public boolean isDamaged() {
        return this.isDamaged;
    }

    public TrueTypeFont getTrueTypeFont() {
        return this.ttf;
    }
}
