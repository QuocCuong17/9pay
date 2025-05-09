package org.apache.pdfbox.contentstream.operator.markedcontent;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.text.PDFMarkedContentExtractor;

/* loaded from: classes5.dex */
public class BeginMarkedContentSequenceWithProperties extends OperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "BDC";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        COSName cOSName = null;
        COSDictionary cOSDictionary = null;
        for (COSBase cOSBase : list) {
            if (cOSBase instanceof COSName) {
                cOSName = (COSName) cOSBase;
            } else if (cOSBase instanceof COSDictionary) {
                cOSDictionary = (COSDictionary) cOSBase;
            }
        }
        if (this.context instanceof PDFMarkedContentExtractor) {
            ((PDFMarkedContentExtractor) this.context).beginMarkedContentSequence(cOSName, cOSDictionary);
        }
    }
}
