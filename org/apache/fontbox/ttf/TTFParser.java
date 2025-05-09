package org.apache.fontbox.ttf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;

/* loaded from: classes5.dex */
public class TTFParser {
    private boolean isEmbedded;
    private boolean parseOnDemandOnly;

    public TTFParser() {
        this(false);
    }

    public TTFParser(boolean z) {
        this(z, false);
    }

    public TTFParser(boolean z, boolean z2) {
        this.isEmbedded = false;
        this.parseOnDemandOnly = false;
        this.isEmbedded = z;
        this.parseOnDemandOnly = z2;
    }

    public TrueTypeFont parse(String str) throws IOException {
        return parse(new File(str));
    }

    public TrueTypeFont parse(File file) throws IOException {
        return parse(new RAFDataStream(file, PDPageLabelRange.STYLE_ROMAN_LOWER));
    }

    public TrueTypeFont parse(InputStream inputStream) throws IOException {
        return parse(new MemoryTTFDataStream(inputStream));
    }

    public TrueTypeFont parseEmbedded(InputStream inputStream) throws IOException {
        this.isEmbedded = true;
        return parse(new MemoryTTFDataStream(inputStream));
    }

    public TrueTypeFont parse(TTFDataStream tTFDataStream) throws IOException {
        TrueTypeFont newFont = newFont(tTFDataStream);
        newFont.setVersion(tTFDataStream.read32Fixed());
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        tTFDataStream.readUnsignedShort();
        tTFDataStream.readUnsignedShort();
        tTFDataStream.readUnsignedShort();
        for (int i = 0; i < readUnsignedShort; i++) {
            newFont.addTable(readTableDirectory(tTFDataStream));
        }
        if (!this.parseOnDemandOnly) {
            parseTables(newFont, tTFDataStream);
        }
        return newFont;
    }

    protected TrueTypeFont newFont(TTFDataStream tTFDataStream) {
        return new TrueTypeFont(tTFDataStream);
    }

    private void parseTables(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        for (TTFTable tTFTable : trueTypeFont.getTables()) {
            if (!tTFTable.getInitialized()) {
                trueTypeFont.readTable(tTFTable);
            }
        }
        if (trueTypeFont.getHeader() == null) {
            throw new IOException("head is mandatory");
        }
        if (trueTypeFont.getHorizontalHeader() == null) {
            throw new IOException("hhead is mandatory");
        }
        if (trueTypeFont.getMaximumProfile() == null) {
            throw new IOException("maxp is mandatory");
        }
        if (trueTypeFont.getPostScript() == null && !this.isEmbedded) {
            throw new IOException("post is mandatory");
        }
        if (trueTypeFont.getIndexToLocation() == null) {
            throw new IOException("loca is mandatory");
        }
        if (trueTypeFont.getGlyph() == null) {
            throw new IOException("glyf is mandatory");
        }
        if (trueTypeFont.getNaming() == null && !this.isEmbedded) {
            throw new IOException("name is mandatory");
        }
        if (trueTypeFont.getHorizontalMetrics() == null) {
            throw new IOException("hmtx is mandatory");
        }
        if (!this.isEmbedded && trueTypeFont.getCmap() == null) {
            throw new IOException("cmap is mandatory");
        }
    }

    private TTFTable readTableDirectory(TTFDataStream tTFDataStream) throws IOException {
        TTFTable readTable;
        String readString = tTFDataStream.readString(4);
        if (readString.equals(CmapTable.TAG)) {
            readTable = new CmapTable();
        } else if (readString.equals(GlyphTable.TAG)) {
            readTable = new GlyphTable();
        } else if (readString.equals("head")) {
            readTable = new HeaderTable();
        } else if (readString.equals(HorizontalHeaderTable.TAG)) {
            readTable = new HorizontalHeaderTable();
        } else if (readString.equals(HorizontalMetricsTable.TAG)) {
            readTable = new HorizontalMetricsTable();
        } else if (readString.equals(IndexToLocationTable.TAG)) {
            readTable = new IndexToLocationTable();
        } else if (readString.equals(MaximumProfileTable.TAG)) {
            readTable = new MaximumProfileTable();
        } else if (readString.equals("name")) {
            readTable = new NamingTable();
        } else if (readString.equals(OS2WindowsMetricsTable.TAG)) {
            readTable = new OS2WindowsMetricsTable();
        } else if (readString.equals("post")) {
            readTable = new PostScriptTable();
        } else if (readString.equals(DigitalSignatureTable.TAG)) {
            readTable = new DigitalSignatureTable();
        } else {
            readTable = readTable(readString);
        }
        readTable.setTag(readString);
        readTable.setCheckSum(tTFDataStream.readUnsignedInt());
        readTable.setOffset(tTFDataStream.readUnsignedInt());
        readTable.setLength(tTFDataStream.readUnsignedInt());
        return readTable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TTFTable readTable(String str) {
        return new TTFTable();
    }
}
