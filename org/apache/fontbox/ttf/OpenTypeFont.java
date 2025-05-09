package org.apache.fontbox.ttf;

import java.io.IOException;

/* loaded from: classes5.dex */
public class OpenTypeFont extends TrueTypeFont {
    /* JADX INFO: Access modifiers changed from: package-private */
    public OpenTypeFont(TTFDataStream tTFDataStream) {
        super(tTFDataStream);
    }

    public synchronized CFFTable getCFF() throws IOException {
        CFFTable cFFTable;
        cFFTable = (CFFTable) this.tables.get(CFFTable.TAG);
        if (cFFTable != null && !cFFTable.getInitialized()) {
            readTable(cFFTable);
        }
        return cFFTable;
    }

    public boolean isPostScript() {
        return this.tables.containsKey(CFFTable.TAG);
    }

    public boolean hasLayoutTables() {
        return this.tables.containsKey("BASE") || this.tables.containsKey("GDEF") || this.tables.containsKey("GPOS") || this.tables.containsKey("GSUB") || this.tables.containsKey("JSTF");
    }
}
