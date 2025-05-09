package org.apache.pdfbox.pdmodel.font;

import java.io.IOException;
import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.Vector;

/* loaded from: classes5.dex */
public interface PDFontLike {
    float getAverageFontWidth();

    BoundingBox getBoundingBox() throws IOException;

    PDFontDescriptor getFontDescriptor();

    Matrix getFontMatrix();

    float getHeight(int i) throws IOException;

    String getName();

    Vector getPositionVector(int i);

    float getWidth(int i) throws IOException;

    float getWidthFromFont(int i) throws IOException;

    boolean isDamaged();

    boolean isEmbedded();
}
