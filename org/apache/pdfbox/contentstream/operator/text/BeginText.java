package org.apache.pdfbox.contentstream.operator.text;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.util.Matrix;

/* loaded from: classes5.dex */
public class BeginText extends OperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "BT";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.setTextMatrix(new Matrix());
        this.context.setTextLineMatrix(new Matrix());
        this.context.beginText();
    }
}
