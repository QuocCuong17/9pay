package org.apache.pdfbox.contentstream.operator.color;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;

/* loaded from: classes5.dex */
public class SetStrokingColorSpace extends OperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "CS";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        PDColorSpace colorSpace = this.context.getResources().getColorSpace((COSName) list.get(0));
        this.context.getGraphicsState().setStrokingColorSpace(colorSpace);
        this.context.getGraphicsState().setStrokingColor(colorSpace.getInitialColor());
    }
}
