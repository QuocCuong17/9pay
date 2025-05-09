package org.apache.pdfbox.contentstream.operator.text;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.MissingOperandException;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNumber;

/* loaded from: classes5.dex */
public class SetFontAndSize extends OperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "Tf";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (list.size() < 2) {
            throw new MissingOperandException(operator, list);
        }
        COSName cOSName = (COSName) list.get(0);
        this.context.getGraphicsState().getTextState().setFontSize(((COSNumber) list.get(1)).floatValue());
        this.context.getGraphicsState().getTextState().setFont(this.context.getResources().getFont(cOSName));
    }
}
