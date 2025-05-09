package org.apache.pdfbox.contentstream.operator.text;

import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSNumber;

/* loaded from: classes5.dex */
public class SetTextLeading extends OperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "TL";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) {
        this.context.getGraphicsState().getTextState().setLeading(((COSNumber) list.get(0)).floatValue());
    }
}
