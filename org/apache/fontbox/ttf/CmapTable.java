package org.apache.fontbox.ttf;

import java.io.IOException;

/* loaded from: classes5.dex */
public class CmapTable extends TTFTable {
    public static final int ENCODING_MAC_ROMAN = 0;
    public static final int ENCODING_UNICODE_1_0 = 0;
    public static final int ENCODING_UNICODE_1_1 = 1;
    public static final int ENCODING_UNICODE_2_0_BMP = 3;
    public static final int ENCODING_UNICODE_2_0_FULL = 4;
    public static final int ENCODING_WIN_BIG5 = 3;
    public static final int ENCODING_WIN_JOHAB = 6;
    public static final int ENCODING_WIN_PRC = 4;
    public static final int ENCODING_WIN_SHIFT_JIS = 2;
    public static final int ENCODING_WIN_SYMBOL = 0;
    public static final int ENCODING_WIN_UNICODE_BMP = 1;
    public static final int ENCODING_WIN_WANSUNG = 5;
    public static final int PLATFORM_MACINTOSH = 1;
    public static final int PLATFORM_UNICODE = 0;
    public static final int PLATFORM_WINDOWS = 3;
    public static final String TAG = "cmap";
    private CmapSubtable[] cmaps;

    @Override // org.apache.fontbox.ttf.TTFTable
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        tTFDataStream.readUnsignedShort();
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        this.cmaps = new CmapSubtable[readUnsignedShort];
        for (int i = 0; i < readUnsignedShort; i++) {
            CmapSubtable cmapSubtable = new CmapSubtable();
            cmapSubtable.initData(tTFDataStream);
            this.cmaps[i] = cmapSubtable;
        }
        for (int i2 = 0; i2 < readUnsignedShort; i2++) {
            this.cmaps[i2].initSubtable(this, trueTypeFont.getNumberOfGlyphs(), tTFDataStream);
        }
        this.initialized = true;
    }

    public CmapSubtable[] getCmaps() {
        return this.cmaps;
    }

    public void setCmaps(CmapSubtable[] cmapSubtableArr) {
        this.cmaps = cmapSubtableArr;
    }

    public CmapSubtable getSubtable(int i, int i2) {
        for (CmapSubtable cmapSubtable : this.cmaps) {
            if (cmapSubtable.getPlatformId() == i && cmapSubtable.getPlatformEncodingId() == i2) {
                return cmapSubtable;
            }
        }
        return null;
    }
}
