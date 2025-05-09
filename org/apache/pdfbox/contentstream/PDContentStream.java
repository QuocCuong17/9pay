package org.apache.pdfbox.contentstream;

import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.util.Matrix;

/* loaded from: classes5.dex */
public interface PDContentStream {
    PDRectangle getBBox();

    COSStream getContentStream();

    Matrix getMatrix();

    PDResources getResources();
}
