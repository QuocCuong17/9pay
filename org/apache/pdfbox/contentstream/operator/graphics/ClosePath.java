package org.apache.pdfbox.contentstream.operator.graphics;

import androidx.media3.exoplayer.upstream.CmcdData;
import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSBase;

/* loaded from: classes5.dex */
public final class ClosePath extends GraphicsOperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return CmcdData.Factory.STREAMING_FORMAT_HLS;
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.closePath();
    }
}
