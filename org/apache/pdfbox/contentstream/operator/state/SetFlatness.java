package org.apache.pdfbox.contentstream.operator.state;

import androidx.media3.exoplayer.upstream.CmcdData;
import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSNumber;

/* loaded from: classes5.dex */
public class SetFlatness extends OperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return CmcdData.Factory.OBJECT_TYPE_INIT_SEGMENT;
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.getGraphicsState().setFlatness(((COSNumber) list.get(0)).floatValue());
    }
}
