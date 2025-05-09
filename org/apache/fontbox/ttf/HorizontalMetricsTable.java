package org.apache.fontbox.ttf;

import java.io.IOException;

/* loaded from: classes5.dex */
public class HorizontalMetricsTable extends TTFTable {
    public static final String TAG = "hmtx";
    private int[] advanceWidth;
    private short[] leftSideBearing;
    private short[] nonHorizontalLeftSideBearing;
    private int numHMetrics;

    @Override // org.apache.fontbox.ttf.TTFTable
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        this.numHMetrics = trueTypeFont.getHorizontalHeader().getNumberOfHMetrics();
        int numberOfGlyphs = trueTypeFont.getNumberOfGlyphs();
        int i = this.numHMetrics;
        this.advanceWidth = new int[i];
        this.leftSideBearing = new short[i];
        int i2 = 0;
        for (int i3 = 0; i3 < this.numHMetrics; i3++) {
            this.advanceWidth[i3] = tTFDataStream.readUnsignedShort();
            this.leftSideBearing[i3] = tTFDataStream.readSignedShort();
            i2 += 4;
        }
        if (i2 < getLength()) {
            int i4 = numberOfGlyphs - this.numHMetrics;
            if (i4 >= 0) {
                numberOfGlyphs = i4;
            }
            this.nonHorizontalLeftSideBearing = new short[numberOfGlyphs];
            for (int i5 = 0; i5 < numberOfGlyphs; i5++) {
                if (i2 < getLength()) {
                    this.nonHorizontalLeftSideBearing[i5] = tTFDataStream.readSignedShort();
                    i2 += 2;
                }
            }
        }
        this.initialized = true;
    }

    public int getAdvanceWidth(int i) {
        if (i < this.numHMetrics) {
            return this.advanceWidth[i];
        }
        return this.advanceWidth[r2.length - 1];
    }
}
