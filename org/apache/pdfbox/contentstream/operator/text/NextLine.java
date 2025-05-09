package org.apache.pdfbox.contentstream.operator.text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSFloat;

/* loaded from: classes5.dex */
public class NextLine extends OperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "T*";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new COSFloat(0.0f));
        arrayList.add(new COSFloat(this.context.getGraphicsState().getTextState().getLeading() * (-1.0f)));
        this.context.processOperator("Td", arrayList);
    }
}
