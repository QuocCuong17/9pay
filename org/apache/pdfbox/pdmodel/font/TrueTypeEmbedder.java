package org.apache.pdfbox.pdmodel.font;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.fontbox.ttf.CmapSubtable;
import org.apache.fontbox.ttf.GlyphTable;
import org.apache.fontbox.ttf.HeaderTable;
import org.apache.fontbox.ttf.HorizontalHeaderTable;
import org.apache.fontbox.ttf.HorizontalMetricsTable;
import org.apache.fontbox.ttf.IndexToLocationTable;
import org.apache.fontbox.ttf.MaximumProfileTable;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;
import org.apache.fontbox.ttf.PostScriptTable;
import org.apache.fontbox.ttf.TTFParser;
import org.apache.fontbox.ttf.TTFSubsetter;
import org.apache.fontbox.ttf.TrueTypeFont;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public abstract class TrueTypeEmbedder implements Subsetter {
    private static final String BASE25 = "BCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int ITALIC = 1;
    private static final int OBLIQUE = 256;
    protected final CmapSubtable cmap;
    private final PDDocument document;
    private final boolean embedSubset;
    protected PDFontDescriptor fontDescriptor;
    private final Set<Integer> subsetCodePoints = new HashSet();
    protected TrueTypeFont ttf;

    protected abstract void buildSubset(InputStream inputStream, String str, Map<Integer, Integer> map) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TrueTypeEmbedder(PDDocument pDDocument, COSDictionary cOSDictionary, InputStream inputStream, boolean z) throws IOException {
        this.document = pDDocument;
        this.embedSubset = z;
        buildFontFile2(inputStream);
        cOSDictionary.setName(COSName.BASE_FONT, this.ttf.getName());
        this.cmap = this.ttf.getUnicodeCmap();
    }

    public void buildFontFile2(InputStream inputStream) throws IOException {
        InputStream inputStream2;
        PDStream pDStream = new PDStream(this.document, inputStream, false);
        pDStream.getStream().setInt(COSName.LENGTH1, pDStream.getByteArray().length);
        pDStream.addCompression();
        try {
            inputStream2 = pDStream.createInputStream();
            try {
                TrueTypeFont parseEmbedded = new TTFParser().parseEmbedded(inputStream2);
                this.ttf = parseEmbedded;
                if (!isEmbeddingPermitted(parseEmbedded)) {
                    throw new IOException("This font does not permit embedding");
                }
                if (this.fontDescriptor == null) {
                    this.fontDescriptor = createFontDescriptor(this.ttf);
                }
                IOUtils.closeQuietly(inputStream2);
                this.fontDescriptor.setFontFile2(pDStream);
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly(inputStream2);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream2 = null;
        }
    }

    private boolean isEmbeddingPermitted(TrueTypeFont trueTypeFont) throws IOException {
        if (trueTypeFont.getOS2Windows() != null) {
            int fsType = trueTypeFont.getOS2Windows().getFsType() & 8;
            if ((fsType & 1) == 1 || (fsType & 512) == 512) {
                return false;
            }
        }
        return true;
    }

    private boolean isSubsettingPermitted(TrueTypeFont trueTypeFont) throws IOException {
        return trueTypeFont.getOS2Windows() == null || (trueTypeFont.getOS2Windows().getFsType() & OS2WindowsMetricsTable.FSTYPE_NO_SUBSETTING) != 256;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0058, code lost:
    
        if (r3 != 5) goto L31;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00e6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private PDFontDescriptor createFontDescriptor(TrueTypeFont trueTypeFont) throws IOException {
        PDFontDescriptor pDFontDescriptor = new PDFontDescriptor();
        pDFontDescriptor.setFontName(trueTypeFont.getName());
        OS2WindowsMetricsTable oS2Windows = trueTypeFont.getOS2Windows();
        PostScriptTable postScript = trueTypeFont.getPostScript();
        pDFontDescriptor.setFixedPitch(postScript.getIsFixedPitch() > 0 || trueTypeFont.getHorizontalHeader().getNumberOfHMetrics() == 1);
        int fsSelection = oS2Windows.getFsSelection();
        pDFontDescriptor.setItalic((fsSelection & 1) == fsSelection || (fsSelection & 256) == fsSelection);
        int familyClass = oS2Windows.getFamilyClass();
        if (familyClass != 1 && familyClass != 7) {
            if (familyClass == 10) {
                pDFontDescriptor.setScript(true);
            } else if (familyClass != 3) {
                if (familyClass != 4) {
                }
            }
            pDFontDescriptor.setFontWeight(oS2Windows.getWeightClass());
            pDFontDescriptor.setSymbolic(true);
            pDFontDescriptor.setNonSymbolic(false);
            pDFontDescriptor.setItalicAngle(postScript.getItalicAngle());
            HeaderTable header = trueTypeFont.getHeader();
            PDRectangle pDRectangle = new PDRectangle();
            float unitsPerEm = 1000.0f / header.getUnitsPerEm();
            pDRectangle.setLowerLeftX(header.getXMin() * unitsPerEm);
            pDRectangle.setLowerLeftY(header.getYMin() * unitsPerEm);
            pDRectangle.setUpperRightX(header.getXMax() * unitsPerEm);
            pDRectangle.setUpperRightY(header.getYMax() * unitsPerEm);
            pDFontDescriptor.setFontBoundingBox(pDRectangle);
            HorizontalHeaderTable horizontalHeader = trueTypeFont.getHorizontalHeader();
            pDFontDescriptor.setAscent(horizontalHeader.getAscender() * unitsPerEm);
            pDFontDescriptor.setDescent(horizontalHeader.getDescender() * unitsPerEm);
            if (oS2Windows.getVersion() < 1.2d) {
                pDFontDescriptor.setCapHeight(oS2Windows.getCapHeight() / unitsPerEm);
                pDFontDescriptor.setXHeight(oS2Windows.getHeight() / unitsPerEm);
            } else {
                pDFontDescriptor.setCapHeight((oS2Windows.getTypoAscender() + oS2Windows.getTypoDescender()) / unitsPerEm);
                pDFontDescriptor.setXHeight((oS2Windows.getTypoAscender() / 2.0f) / unitsPerEm);
            }
            pDFontDescriptor.setStemV(pDFontDescriptor.getFontBoundingBox().getWidth() * 0.13f);
            return pDFontDescriptor;
        }
        pDFontDescriptor.setSerif(true);
        pDFontDescriptor.setFontWeight(oS2Windows.getWeightClass());
        pDFontDescriptor.setSymbolic(true);
        pDFontDescriptor.setNonSymbolic(false);
        pDFontDescriptor.setItalicAngle(postScript.getItalicAngle());
        HeaderTable header2 = trueTypeFont.getHeader();
        PDRectangle pDRectangle2 = new PDRectangle();
        float unitsPerEm2 = 1000.0f / header2.getUnitsPerEm();
        pDRectangle2.setLowerLeftX(header2.getXMin() * unitsPerEm2);
        pDRectangle2.setLowerLeftY(header2.getYMin() * unitsPerEm2);
        pDRectangle2.setUpperRightX(header2.getXMax() * unitsPerEm2);
        pDRectangle2.setUpperRightY(header2.getYMax() * unitsPerEm2);
        pDFontDescriptor.setFontBoundingBox(pDRectangle2);
        HorizontalHeaderTable horizontalHeader2 = trueTypeFont.getHorizontalHeader();
        pDFontDescriptor.setAscent(horizontalHeader2.getAscender() * unitsPerEm2);
        pDFontDescriptor.setDescent(horizontalHeader2.getDescender() * unitsPerEm2);
        if (oS2Windows.getVersion() < 1.2d) {
        }
        pDFontDescriptor.setStemV(pDFontDescriptor.getFontBoundingBox().getWidth() * 0.13f);
        return pDFontDescriptor;
    }

    public TrueTypeFont getTrueTypeFont() {
        return this.ttf;
    }

    public PDFontDescriptor getFontDescriptor() {
        return this.fontDescriptor;
    }

    @Override // org.apache.pdfbox.pdmodel.font.Subsetter
    public void addToSubset(int i) {
        this.subsetCodePoints.add(Integer.valueOf(i));
    }

    @Override // org.apache.pdfbox.pdmodel.font.Subsetter
    public void subset() throws IOException {
        if (!isSubsettingPermitted(this.ttf)) {
            throw new IOException("This font does not permit subsetting");
        }
        if (!this.embedSubset) {
            throw new IllegalStateException("Subsetting is disabled");
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("head");
        arrayList.add(HorizontalHeaderTable.TAG);
        arrayList.add(IndexToLocationTable.TAG);
        arrayList.add(MaximumProfileTable.TAG);
        arrayList.add("cvt ");
        arrayList.add("prep");
        arrayList.add(GlyphTable.TAG);
        arrayList.add(HorizontalMetricsTable.TAG);
        arrayList.add("fpgm");
        arrayList.add("gasp");
        TTFSubsetter tTFSubsetter = new TTFSubsetter(getTrueTypeFont(), arrayList);
        tTFSubsetter.addAll(this.subsetCodePoints);
        Map<Integer, Integer> gIDMap = tTFSubsetter.getGIDMap();
        String tag = getTag(gIDMap);
        tTFSubsetter.setPrefix(tag);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        tTFSubsetter.writeToStream(byteArrayOutputStream);
        buildSubset(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), tag, gIDMap);
    }

    public boolean needsSubset() {
        return this.embedSubset;
    }

    public String getTag(Map<Integer, Integer> map) {
        long hashCode = map.hashCode();
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j = hashCode / 25;
            sb.append(BASE25.charAt((int) (hashCode % 25)));
            if (j == 0 || sb.length() >= 6) {
                break;
            }
            hashCode = j;
        }
        while (sb.length() < 6) {
            sb.insert(0, 'A');
        }
        sb.append('+');
        return sb.toString();
    }
}
