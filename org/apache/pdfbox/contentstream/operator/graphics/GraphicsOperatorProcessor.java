package org.apache.pdfbox.contentstream.operator.graphics;

import org.apache.pdfbox.contentstream.PDFGraphicsStreamEngine;
import org.apache.pdfbox.contentstream.PDFStreamEngine;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;

/* loaded from: classes5.dex */
public abstract class GraphicsOperatorProcessor extends OperatorProcessor {
    protected PDFGraphicsStreamEngine context;

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void setContext(PDFStreamEngine pDFStreamEngine) {
        super.setContext(pDFStreamEngine);
        this.context = (PDFGraphicsStreamEngine) pDFStreamEngine;
    }
}
