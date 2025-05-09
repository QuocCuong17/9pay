package org.apache.pdfbox.contentstream.operator.state;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.util.Matrix;

/* loaded from: classes5.dex */
public class Concatenate extends OperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "cm";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.getGraphicsState().getCurrentTransformationMatrix().concatenate(new Matrix(((COSNumber) list.get(0)).floatValue(), ((COSNumber) list.get(1)).floatValue(), ((COSNumber) list.get(2)).floatValue(), ((COSNumber) list.get(3)).floatValue(), ((COSNumber) list.get(4)).floatValue(), ((COSNumber) list.get(5)).floatValue()));
    }
}
