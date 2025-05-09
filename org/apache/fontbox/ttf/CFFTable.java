package org.apache.fontbox.ttf;

import java.io.IOException;
import org.apache.fontbox.cff.CFFFont;
import org.apache.fontbox.cff.CFFParser;

/* loaded from: classes5.dex */
public class CFFTable extends TTFTable {
    public static final String TAG = "CFF ";
    private CFFFont cffFont;

    @Override // org.apache.fontbox.ttf.TTFTable
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        this.cffFont = new CFFParser().parse(tTFDataStream.read((int) getLength())).get(0);
        this.initialized = true;
    }

    public CFFFont getFont() {
        return this.cffFont;
    }
}
