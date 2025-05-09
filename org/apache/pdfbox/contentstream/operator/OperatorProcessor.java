package org.apache.pdfbox.contentstream.operator;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.PDFStreamEngine;
import org.apache.pdfbox.cos.COSBase;

/* loaded from: classes5.dex */
public abstract class OperatorProcessor {
    protected PDFStreamEngine context;

    public abstract String getName();

    public abstract void process(Operator operator, List<COSBase> list) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public final PDFStreamEngine getContext() {
        return this.context;
    }

    public void setContext(PDFStreamEngine pDFStreamEngine) {
        this.context = pDFStreamEngine;
    }
}
