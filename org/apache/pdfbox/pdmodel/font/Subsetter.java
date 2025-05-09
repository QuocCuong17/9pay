package org.apache.pdfbox.pdmodel.font;

import java.io.IOException;

/* loaded from: classes5.dex */
interface Subsetter {
    void addToSubset(int i);

    void subset() throws IOException;
}
