package org.apache.pdfbox.pdmodel.font;

import org.apache.pdfbox.contentstream.PDContentStream;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.util.Matrix;

/* loaded from: classes5.dex */
public final class PDType3CharProc implements COSObjectable, PDContentStream {
    private final COSStream charStream;
    private final PDType3Font font;

    public PDType3CharProc(PDType3Font pDType3Font, COSStream cOSStream) {
        this.font = pDType3Font;
        this.charStream = cOSStream;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSStream getCOSObject() {
        return this.charStream;
    }

    public PDType3Font getFont() {
        return this.font;
    }

    @Override // org.apache.pdfbox.contentstream.PDContentStream
    public COSStream getContentStream() {
        return this.charStream;
    }

    @Override // org.apache.pdfbox.contentstream.PDContentStream
    public PDResources getResources() {
        return this.font.getResources();
    }

    @Override // org.apache.pdfbox.contentstream.PDContentStream
    public PDRectangle getBBox() {
        return this.font.getFontBBox();
    }

    @Override // org.apache.pdfbox.contentstream.PDContentStream
    public Matrix getMatrix() {
        return this.font.getFontMatrix();
    }
}
