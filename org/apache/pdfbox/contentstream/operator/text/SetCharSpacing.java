package org.apache.pdfbox.contentstream.operator.text;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.MissingOperandException;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSNumber;

/* loaded from: classes5.dex */
public class SetCharSpacing extends OperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "Tc";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.size() == 0) {
            throw new MissingOperandException(operator, list);
        }
        COSBase cOSBase = list.get(list.size() - 1);
        if (cOSBase instanceof COSNumber) {
            this.context.getGraphicsState().getTextState().setCharacterSpacing(((COSNumber) cOSBase).floatValue());
        }
    }
}
