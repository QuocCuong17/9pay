package org.apache.fontbox.ttf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class OTFParser extends TTFParser {
    public OTFParser() {
    }

    public OTFParser(boolean z) {
        this(z, false);
    }

    public OTFParser(boolean z, boolean z2) {
        super(z, z2);
    }

    @Override // org.apache.fontbox.ttf.TTFParser
    public OpenTypeFont parse(String str) throws IOException {
        return (OpenTypeFont) super.parse(str);
    }

    @Override // org.apache.fontbox.ttf.TTFParser
    public OpenTypeFont parse(File file) throws IOException {
        return (OpenTypeFont) super.parse(file);
    }

    @Override // org.apache.fontbox.ttf.TTFParser
    public OpenTypeFont parse(InputStream inputStream) throws IOException {
        return (OpenTypeFont) super.parse(inputStream);
    }

    @Override // org.apache.fontbox.ttf.TTFParser
    public OpenTypeFont parse(TTFDataStream tTFDataStream) throws IOException {
        return (OpenTypeFont) super.parse(tTFDataStream);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.fontbox.ttf.TTFParser
    public OpenTypeFont newFont(TTFDataStream tTFDataStream) {
        return new OpenTypeFont(tTFDataStream);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.fontbox.ttf.TTFParser
    public TTFTable readTable(String str) {
        if (str.equals("BASE") || str.equals("GDEF") || str.equals("GPOS") || str.equals("GSUB") || str.equals("JSTF")) {
            return new OTLTable();
        }
        if (str.equals(CFFTable.TAG)) {
            return new CFFTable();
        }
        return super.readTable(str);
    }
}
