package org.apache.pdfbox.contentstream.operator.text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;

/* loaded from: classes5.dex */
public class MoveTextSetLeading extends OperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return StandardStructureTypes.TD;
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        COSNumber cOSNumber = (COSNumber) list.get(1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new COSFloat(cOSNumber.floatValue() * (-1.0f)));
        this.context.processOperator("TL", arrayList);
        this.context.processOperator("Td", list);
    }
}
