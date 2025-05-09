package org.apache.fontbox.ttf;

import android.graphics.Path;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.fontbox.encoding.Encoding;
import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.util.awt.AffineTransform;

/* loaded from: classes5.dex */
public class TrueTypeFont implements Type1Equivalent {
    private TTFDataStream data;
    private Map<String, Integer> postScriptNames;
    private float version;
    private int numberOfGlyphs = -1;
    private int unitsPerEm = -1;
    protected Map<String, TTFTable> tables = new HashMap();

    @Override // org.apache.fontbox.ttf.Type1Equivalent
    public Encoding getEncoding() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TrueTypeFont(TTFDataStream tTFDataStream) {
        this.data = tTFDataStream;
    }

    public void close() throws IOException {
        this.data.close();
    }

    public float getVersion() {
        return this.version;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setVersion(float f) {
        this.version = f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addTable(TTFTable tTFTable) {
        this.tables.put(tTFTable.getTag(), tTFTable);
    }

    public Collection<TTFTable> getTables() {
        return this.tables.values();
    }

    public Map<String, TTFTable> getTableMap() {
        return this.tables;
    }

    public synchronized byte[] getTableBytes(TTFTable tTFTable) throws IOException {
        byte[] read;
        long currentPosition = this.data.getCurrentPosition();
        this.data.seek(tTFTable.getOffset());
        read = this.data.read((int) tTFTable.getLength());
        this.data.seek(currentPosition);
        return read;
    }

    public synchronized NamingTable getNaming() throws IOException {
        NamingTable namingTable;
        namingTable = (NamingTable) this.tables.get("name");
        if (namingTable != null && !namingTable.getInitialized()) {
            readTable(namingTable);
        }
        return namingTable;
    }

    public synchronized PostScriptTable getPostScript() throws IOException {
        PostScriptTable postScriptTable;
        postScriptTable = (PostScriptTable) this.tables.get("post");
        if (postScriptTable != null && !postScriptTable.getInitialized()) {
            readTable(postScriptTable);
        }
        return postScriptTable;
    }

    public synchronized OS2WindowsMetricsTable getOS2Windows() throws IOException {
        OS2WindowsMetricsTable oS2WindowsMetricsTable;
        oS2WindowsMetricsTable = (OS2WindowsMetricsTable) this.tables.get(OS2WindowsMetricsTable.TAG);
        if (oS2WindowsMetricsTable != null && !oS2WindowsMetricsTable.getInitialized()) {
            readTable(oS2WindowsMetricsTable);
        }
        return oS2WindowsMetricsTable;
    }

    public synchronized MaximumProfileTable getMaximumProfile() throws IOException {
        MaximumProfileTable maximumProfileTable;
        maximumProfileTable = (MaximumProfileTable) this.tables.get(MaximumProfileTable.TAG);
        if (maximumProfileTable != null && !maximumProfileTable.getInitialized()) {
            readTable(maximumProfileTable);
        }
        return maximumProfileTable;
    }

    public synchronized HeaderTable getHeader() throws IOException {
        HeaderTable headerTable;
        headerTable = (HeaderTable) this.tables.get("head");
        if (headerTable != null && !headerTable.getInitialized()) {
            readTable(headerTable);
        }
        return headerTable;
    }

    public synchronized HorizontalHeaderTable getHorizontalHeader() throws IOException {
        HorizontalHeaderTable horizontalHeaderTable;
        horizontalHeaderTable = (HorizontalHeaderTable) this.tables.get(HorizontalHeaderTable.TAG);
        if (horizontalHeaderTable != null && !horizontalHeaderTable.getInitialized()) {
            readTable(horizontalHeaderTable);
        }
        return horizontalHeaderTable;
    }

    public synchronized HorizontalMetricsTable getHorizontalMetrics() throws IOException {
        HorizontalMetricsTable horizontalMetricsTable;
        horizontalMetricsTable = (HorizontalMetricsTable) this.tables.get(HorizontalMetricsTable.TAG);
        if (horizontalMetricsTable != null && !horizontalMetricsTable.getInitialized()) {
            readTable(horizontalMetricsTable);
        }
        return horizontalMetricsTable;
    }

    public synchronized IndexToLocationTable getIndexToLocation() throws IOException {
        IndexToLocationTable indexToLocationTable;
        indexToLocationTable = (IndexToLocationTable) this.tables.get(IndexToLocationTable.TAG);
        if (indexToLocationTable != null && !indexToLocationTable.getInitialized()) {
            readTable(indexToLocationTable);
        }
        return indexToLocationTable;
    }

    public synchronized GlyphTable getGlyph() throws IOException {
        GlyphTable glyphTable;
        glyphTable = (GlyphTable) this.tables.get(GlyphTable.TAG);
        if (glyphTable != null && !glyphTable.getInitialized()) {
            readTable(glyphTable);
        }
        return glyphTable;
    }

    public synchronized CmapTable getCmap() throws IOException {
        CmapTable cmapTable;
        cmapTable = (CmapTable) this.tables.get(CmapTable.TAG);
        if (cmapTable != null && !cmapTable.getInitialized()) {
            readTable(cmapTable);
        }
        return cmapTable;
    }

    public InputStream getOriginalData() throws IOException {
        return this.data.getOriginalData();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void readTable(TTFTable tTFTable) throws IOException {
        long currentPosition = this.data.getCurrentPosition();
        this.data.seek(tTFTable.getOffset());
        tTFTable.read(this, this.data);
        this.data.seek(currentPosition);
    }

    public int getNumberOfGlyphs() throws IOException {
        if (this.numberOfGlyphs == -1) {
            MaximumProfileTable maximumProfile = getMaximumProfile();
            if (maximumProfile != null) {
                this.numberOfGlyphs = maximumProfile.getNumGlyphs();
            } else {
                this.numberOfGlyphs = 0;
            }
        }
        return this.numberOfGlyphs;
    }

    public int getUnitsPerEm() throws IOException {
        if (this.unitsPerEm == -1) {
            HeaderTable header = getHeader();
            if (header != null) {
                this.unitsPerEm = header.getUnitsPerEm();
            } else {
                this.unitsPerEm = 0;
            }
        }
        return this.unitsPerEm;
    }

    public int getAdvanceWidth(int i) throws IOException {
        HorizontalMetricsTable horizontalMetrics = getHorizontalMetrics();
        if (horizontalMetrics != null) {
            return horizontalMetrics.getAdvanceWidth(i);
        }
        return 250;
    }

    @Override // org.apache.fontbox.ttf.Type1Equivalent
    public String getName() throws IOException {
        if (getNaming() != null) {
            return getNaming().getPostScriptName();
        }
        return null;
    }

    private synchronized void readPostScriptNames() throws IOException {
        String[] glyphNames;
        if (this.postScriptNames == null) {
            this.postScriptNames = new HashMap();
            if (getPostScript() != null && (glyphNames = getPostScript().getGlyphNames()) != null) {
                for (int i = 0; i < glyphNames.length; i++) {
                    this.postScriptNames.put(glyphNames[i], Integer.valueOf(i));
                }
            }
        }
    }

    public CmapSubtable getUnicodeCmap() throws IOException {
        return getUnicodeCmap(true);
    }

    public CmapSubtable getUnicodeCmap(boolean z) throws IOException {
        CmapTable cmap = getCmap();
        if (cmap == null) {
            return null;
        }
        CmapSubtable subtable = cmap.getSubtable(0, 4);
        if (subtable == null) {
            subtable = cmap.getSubtable(0, 3);
        }
        if (subtable == null) {
            subtable = cmap.getSubtable(3, 1);
        }
        if (subtable == null) {
            subtable = cmap.getSubtable(3, 0);
        }
        if (subtable != null) {
            return subtable;
        }
        if (z) {
            throw new IOException("The TrueType font does not contain a Unicode cmap");
        }
        return cmap.getCmaps()[0];
    }

    public int nameToGID(String str) throws IOException {
        readPostScriptNames();
        Integer num = this.postScriptNames.get(str);
        if (num != null && num.intValue() > 0 && num.intValue() < getMaximumProfile().getNumGlyphs()) {
            return num.intValue();
        }
        int parseUniName = parseUniName(str);
        if (parseUniName > -1) {
            return getUnicodeCmap(false).getGlyphId(parseUniName);
        }
        return 0;
    }

    private int parseUniName(String str) throws IOException {
        if (str.startsWith("uni") && str.length() == 7) {
            int length = str.length();
            StringBuilder sb = new StringBuilder();
            int i = 3;
            while (true) {
                int i2 = i + 4;
                if (i2 > length) {
                    break;
                }
                try {
                    int parseInt = Integer.parseInt(str.substring(i, i2), 16);
                    if (parseInt <= 55295 || parseInt >= 57344) {
                        sb.append((char) parseInt);
                    }
                    i = i2;
                } catch (NumberFormatException unused) {
                }
            }
            String sb2 = sb.toString();
            if (sb2.length() == 0) {
                return -1;
            }
            return sb2.codePointAt(0);
        }
        return -1;
    }

    @Override // org.apache.fontbox.ttf.Type1Equivalent
    public Path getPath(String str) throws IOException {
        readPostScriptNames();
        int nameToGID = nameToGID(str);
        if (nameToGID < 0 || nameToGID >= getMaximumProfile().getNumGlyphs()) {
            nameToGID = 0;
        }
        GlyphData glyph = getGlyph().getGlyph(nameToGID);
        if (glyph == null) {
            return new Path();
        }
        Path path = glyph.getPath();
        double unitsPerEm = 1000.0f / getUnitsPerEm();
        path.transform(AffineTransform.getScaleInstance(unitsPerEm, unitsPerEm).toMatrix());
        return path;
    }

    @Override // org.apache.fontbox.ttf.Type1Equivalent
    public float getWidth(String str) throws IOException {
        int advanceWidth = getAdvanceWidth(Integer.valueOf(nameToGID(str)).intValue());
        int unitsPerEm = getUnitsPerEm();
        if (unitsPerEm != 1000) {
            advanceWidth = (int) (advanceWidth * (1000.0f / unitsPerEm));
        }
        return advanceWidth;
    }

    @Override // org.apache.fontbox.ttf.Type1Equivalent
    public boolean hasGlyph(String str) throws IOException {
        return nameToGID(str) != 0;
    }

    @Override // org.apache.fontbox.ttf.Type1Equivalent
    public BoundingBox getFontBBox() throws IOException {
        short xMin = getHeader().getXMin();
        short xMax = getHeader().getXMax();
        float unitsPerEm = 1000.0f / getUnitsPerEm();
        return new BoundingBox(xMin * unitsPerEm, getHeader().getYMin() * unitsPerEm, xMax * unitsPerEm, getHeader().getYMax() * unitsPerEm);
    }

    public String toString() {
        try {
            return getNaming() != null ? getNaming().getPostScriptName() : "(null)";
        } catch (IOException e) {
            return "(null - " + e.getMessage() + ")";
        }
    }
}
