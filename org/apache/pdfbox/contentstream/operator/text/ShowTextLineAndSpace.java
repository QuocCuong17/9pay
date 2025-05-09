package org.apache.pdfbox.contentstream.operator.text;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSBase;

/* loaded from: classes5.dex */
public class ShowTextLineAndSpace extends OperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "\"";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.processOperator("Tw", list.subList(0, 1));
        this.context.processOperator("Tc", list.subList(1, 2));
        this.context.processOperator("'", list.subList(2, 3));
    }
}
