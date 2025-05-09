package org.apache.pdfbox.contentstream.operator.graphics;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSNumber;

/* loaded from: classes5.dex */
public final class AppendRectangleToPath extends GraphicsOperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "re";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        COSNumber cOSNumber = (COSNumber) list.get(0);
        COSNumber cOSNumber2 = (COSNumber) list.get(1);
        COSNumber cOSNumber3 = (COSNumber) list.get(2);
        COSNumber cOSNumber4 = (COSNumber) list.get(3);
        float floatValue = cOSNumber.floatValue();
        float floatValue2 = cOSNumber2.floatValue();
        float floatValue3 = cOSNumber3.floatValue() + floatValue;
        float floatValue4 = cOSNumber4.floatValue() + floatValue2;
        this.context.appendRectangle(this.context.transformedPoint(floatValue, floatValue2), this.context.transformedPoint(floatValue3, floatValue2), this.context.transformedPoint(floatValue3, floatValue4), this.context.transformedPoint(floatValue, floatValue4));
    }
}
