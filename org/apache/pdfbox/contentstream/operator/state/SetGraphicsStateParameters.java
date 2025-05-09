package org.apache.pdfbox.contentstream.operator.state;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public class SetGraphicsStateParameters extends OperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "gs";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.getResources().getExtGState((COSName) list.get(0)).copyIntoGraphicsState(this.context.getGraphicsState());
    }
}
