package org.apache.pdfbox.contentstream.operator.state;

import android.graphics.Paint;
import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSNumber;

/* loaded from: classes5.dex */
public class SetLineCapStyle extends OperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "J";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        Paint.Cap cap;
        int intValue = ((COSNumber) list.get(0)).intValue();
        if (intValue == 0) {
            cap = Paint.Cap.BUTT;
        } else if (intValue == 1) {
            cap = Paint.Cap.ROUND;
        } else {
            cap = intValue != 2 ? null : Paint.Cap.SQUARE;
        }
        this.context.getGraphicsState().setLineCap(cap);
    }
}
