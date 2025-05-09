package org.apache.fontbox.ttf;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class GlyphTable extends TTFTable {
    public static final String TAG = "glyf";
    protected Map<Integer, GlyphData> cache = new ConcurrentHashMap();
    private TTFDataStream data;
    private GlyphData[] glyphs;
    private IndexToLocationTable loca;
    private int numGlyphs;

    @Override // org.apache.fontbox.ttf.TTFTable
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        this.loca = trueTypeFont.getIndexToLocation();
        this.numGlyphs = trueTypeFont.getNumberOfGlyphs();
        this.data = tTFDataStream;
        this.initialized = true;
    }

    private void readAll() throws IOException {
        long[] offsets = this.loca.getOffsets();
        long j = offsets[this.numGlyphs];
        long offset = getOffset();
        this.glyphs = new GlyphData[this.numGlyphs];
        int i = 0;
        while (i < this.numGlyphs && (j == 0 || j != offsets[i])) {
            int i2 = i + 1;
            if (offsets[i2] > offsets[i]) {
                this.glyphs[i] = new GlyphData();
                this.data.seek(offsets[i] + offset);
                this.glyphs[i].initData(this, this.data);
            }
            i = i2;
        }
        for (int i3 = 0; i3 < this.numGlyphs; i3++) {
            GlyphData glyphData = this.glyphs[i3];
            if (glyphData != null && glyphData.getDescription().isComposite()) {
                glyphData.getDescription().resolve();
            }
        }
        this.initialized = true;
    }

    public synchronized GlyphData[] getGlyphs() throws IOException {
        if (this.glyphs == null) {
            readAll();
        }
        return this.glyphs;
    }

    public void setGlyphs(GlyphData[] glyphDataArr) {
        this.glyphs = glyphDataArr;
    }

    public GlyphData getGlyph(int i) throws IOException {
        GlyphData glyphData = null;
        if (i < 0 || i >= this.numGlyphs) {
            return null;
        }
        if (this.cache.containsKey(Integer.valueOf(i))) {
            return this.cache.get(Integer.valueOf(i));
        }
        synchronized (this) {
            long currentPosition = this.data.getCurrentPosition();
            long[] offsets = this.loca.getOffsets();
            if (offsets[i] != offsets[i + 1]) {
                this.data.seek(getOffset() + offsets[i]);
                glyphData = new GlyphData();
                glyphData.initData(this, this.data);
                if (glyphData.getDescription().isComposite()) {
                    glyphData.getDescription().resolve();
                }
            }
            this.data.seek(currentPosition);
        }
        return glyphData;
    }
}
