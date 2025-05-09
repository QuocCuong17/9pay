package org.apache.pdfbox.contentstream.operator.state;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSBase;

/* loaded from: classes5.dex */
public class Restore extends OperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "Q";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        if (this.context.getGraphicsStackSize() > 1) {
            this.context.restoreGraphicsState();
            return;
        }
        throw new EmptyGraphicsStackException();
    }
}
