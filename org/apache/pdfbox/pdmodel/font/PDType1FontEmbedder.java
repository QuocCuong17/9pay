package org.apache.pdfbox.pdmodel.font;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.fontbox.afm.AFMParser;
import org.apache.fontbox.afm.CharMetric;
import org.apache.fontbox.afm.FontMetrics;
import org.apache.fontbox.pfb.PfbParser;
import org.apache.fontbox.type1.Type1Font;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.COSArrayList;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.encoding.DictionaryEncoding;
import org.apache.pdfbox.pdmodel.font.encoding.Encoding;
import org.apache.pdfbox.pdmodel.font.encoding.Type1Encoding;
import org.bouncycastle.crypto.tls.CipherSuite;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class PDType1FontEmbedder {
    private final Encoding fontEncoding;
    private final FontMetrics metrics;
    private final Type1Font type1;

    public PDType1FontEmbedder(PDDocument pDDocument, COSDictionary cOSDictionary, InputStream inputStream, InputStream inputStream2) throws IOException {
        cOSDictionary.setItem(COSName.SUBTYPE, COSName.TYPE1);
        FontMetrics parse = new AFMParser(inputStream).parse();
        this.metrics = parse;
        this.fontEncoding = encodingFromAFM(parse);
        PDFontDescriptor buildFontDescriptor = buildFontDescriptor(parse);
        byte[] byteArray = IOUtils.toByteArray(inputStream2);
        PfbParser pfbParser = new PfbParser(new ByteArrayInputStream(byteArray));
        this.type1 = Type1Font.createWithPFB(new ByteArrayInputStream(byteArray));
        PDStream pDStream = new PDStream(pDDocument, pfbParser.getInputStream(), false);
        pDStream.getStream().setInt("Length", pfbParser.size());
        int i = 0;
        while (i < pfbParser.getLengths().length) {
            COSStream stream = pDStream.getStream();
            StringBuilder sb = new StringBuilder();
            sb.append("Length");
            int i2 = i + 1;
            sb.append(i2);
            stream.setInt(sb.toString(), pfbParser.getLengths()[i]);
            i = i2;
        }
        pDStream.addCompression();
        buildFontDescriptor.setFontFile(pDStream);
        cOSDictionary.setItem(COSName.FONT_DESC, buildFontDescriptor);
        cOSDictionary.setName(COSName.BASE_FONT, this.metrics.getFontName());
        List<CharMetric> charMetrics = this.metrics.getCharMetrics();
        getFontEncoding();
        ArrayList arrayList = new ArrayList(256);
        for (int i3 = 0; i3 < 256; i3++) {
            arrayList.add(250);
        }
        int i4 = 255;
        int i5 = 0;
        for (CharMetric charMetric : charMetrics) {
            int characterCode = charMetric.getCharacterCode();
            if (characterCode > 0) {
                i4 = Math.min(i4, characterCode);
                i5 = Math.max(i5, characterCode);
                if (charMetric.getWx() > 0.0f) {
                    arrayList.set(characterCode, Integer.valueOf(Math.round(charMetric.getWx())));
                }
            }
        }
        cOSDictionary.setInt(COSName.FIRST_CHAR, 0);
        cOSDictionary.setInt(COSName.LAST_CHAR, 255);
        cOSDictionary.setItem(COSName.WIDTHS, COSArrayList.converterToCOSArray(arrayList));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PDFontDescriptor buildFontDescriptor(FontMetrics fontMetrics) {
        boolean equals = fontMetrics.getEncodingScheme().equals("FontSpecific");
        PDFontDescriptor pDFontDescriptor = new PDFontDescriptor();
        pDFontDescriptor.setFontName(fontMetrics.getFontName());
        pDFontDescriptor.setFontFamily(fontMetrics.getFamilyName());
        pDFontDescriptor.setNonSymbolic(!equals);
        pDFontDescriptor.setSymbolic(equals);
        pDFontDescriptor.setFontBoundingBox(new PDRectangle(fontMetrics.getFontBBox()));
        pDFontDescriptor.setItalicAngle(fontMetrics.getItalicAngle());
        pDFontDescriptor.setAscent(fontMetrics.getAscender());
        pDFontDescriptor.setDescent(fontMetrics.getDescender());
        pDFontDescriptor.setCapHeight(fontMetrics.getCapHeight());
        pDFontDescriptor.setXHeight(fontMetrics.getXHeight());
        pDFontDescriptor.setAverageWidth(fontMetrics.getAverageCharacterWidth());
        pDFontDescriptor.setCharacterSet(fontMetrics.getCharacterSet());
        pDFontDescriptor.setStemV(0.0f);
        return pDFontDescriptor;
    }

    private DictionaryEncoding encodingFromAFM(FontMetrics fontMetrics) throws IOException {
        Type1Encoding type1Encoding = new Type1Encoding(fontMetrics);
        COSArray cOSArray = new COSArray();
        cOSArray.add((COSBase) COSInteger.ZERO);
        for (int i = 0; i < 256; i++) {
            cOSArray.add((COSBase) COSName.getPDFName(type1Encoding.getName(i)));
        }
        cOSArray.set(224, (COSBase) COSName.getPDFName("germandbls"));
        cOSArray.set(229, (COSBase) COSName.getPDFName("adieresis"));
        cOSArray.set(247, (COSBase) COSName.getPDFName("odieresis"));
        cOSArray.set(253, (COSBase) COSName.getPDFName("udieresis"));
        cOSArray.set(CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, (COSBase) COSName.getPDFName("Adieresis"));
        cOSArray.set(215, (COSBase) COSName.getPDFName("Odieresis"));
        cOSArray.set(221, (COSBase) COSName.getPDFName("Udieresis"));
        return new DictionaryEncoding(COSName.STANDARD_ENCODING, cOSArray);
    }

    public Encoding getFontEncoding() {
        return this.fontEncoding;
    }

    public FontMetrics getFontMetrics() {
        return this.metrics;
    }

    public Type1Font getType1Font() {
        return this.type1;
    }
}
