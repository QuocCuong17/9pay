package org.apache.pdfbox.contentstream.operator.text;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSBase;

/* loaded from: classes5.dex */
public class ShowTextLine extends OperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "'";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.processOperator("T*", (List<COSBase>) null);
        this.context.processOperator("Tj", list);
    }
}
