package org.apache.pdfbox.contentstream.operator.markedcontent;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.text.PDFMarkedContentExtractor;

/* loaded from: classes5.dex */
public class EndMarkedContentSequence extends OperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "EMC";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (this.context instanceof PDFMarkedContentExtractor) {
            ((PDFMarkedContentExtractor) this.context).endMarkedContentSequence();
        }
    }
}
