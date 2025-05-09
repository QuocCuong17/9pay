package org.apache.pdfbox.pdmodel.font;

import android.graphics.Path;
import java.io.IOException;
import org.apache.fontbox.ttf.Type1Equivalent;

/* loaded from: classes5.dex */
public interface PDType1Equivalent extends PDFontLike {
    String codeToName(int i) throws IOException;

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    String getName();

    Path getPath(String str) throws IOException;

    Type1Equivalent getType1Equivalent();
}
