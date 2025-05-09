package org.apache.fontbox.ttf;

import android.graphics.Path;
import java.io.IOException;
import org.apache.fontbox.encoding.Encoding;
import org.apache.fontbox.util.BoundingBox;

/* loaded from: classes5.dex */
public interface Type1Equivalent {
    Encoding getEncoding() throws IOException;

    BoundingBox getFontBBox() throws IOException;

    String getName() throws IOException;

    Path getPath(String str) throws IOException;

    float getWidth(String str) throws IOException;

    boolean hasGlyph(String str) throws IOException;
}
