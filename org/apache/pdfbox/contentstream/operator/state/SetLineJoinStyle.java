package org.apache.pdfbox.contentstream.operator.state;

import android.graphics.Paint;
import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSNumber;

/* loaded from: classes5.dex */
public class SetLineJoinStyle extends OperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "j";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        Paint.Join join;
        int intValue = ((COSNumber) list.get(0)).intValue();
        if (intValue == 0) {
            join = Paint.Join.MITER;
        } else if (intValue == 1) {
            join = Paint.Join.ROUND;
        } else {
            join = intValue != 2 ? null : Paint.Join.BEVEL;
        }
        this.context.getGraphicsState().setLineJoin(join);
    }
}
