package org.apache.pdfbox.contentstream.operator.text;

import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.util.Matrix;

/* loaded from: classes5.dex */
public class MoveText extends OperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "Td";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) {
        this.context.getTextLineMatrix().concatenate(new Matrix(1.0f, 0.0f, 0.0f, 1.0f, ((COSNumber) list.get(0)).floatValue(), ((COSNumber) list.get(1)).floatValue()));
        this.context.setTextMatrix(this.context.getTextLineMatrix().clone());
    }
}
