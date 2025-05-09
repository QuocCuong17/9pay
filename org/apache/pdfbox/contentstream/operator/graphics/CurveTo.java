package org.apache.pdfbox.contentstream.operator.graphics;

import android.graphics.PointF;
import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSNumber;

/* loaded from: classes5.dex */
public class CurveTo extends GraphicsOperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "c";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        COSNumber cOSNumber = (COSNumber) list.get(0);
        COSNumber cOSNumber2 = (COSNumber) list.get(1);
        COSNumber cOSNumber3 = (COSNumber) list.get(2);
        COSNumber cOSNumber4 = (COSNumber) list.get(3);
        COSNumber cOSNumber5 = (COSNumber) list.get(4);
        COSNumber cOSNumber6 = (COSNumber) list.get(5);
        PointF transformedPoint = this.context.transformedPoint(cOSNumber.floatValue(), cOSNumber2.floatValue());
        PointF transformedPoint2 = this.context.transformedPoint(cOSNumber3.floatValue(), cOSNumber4.floatValue());
        PointF transformedPoint3 = this.context.transformedPoint(cOSNumber5.floatValue(), cOSNumber6.floatValue());
        this.context.curveTo(transformedPoint.x, transformedPoint.y, transformedPoint2.x, transformedPoint2.y, transformedPoint3.x, transformedPoint3.y);
    }
}
