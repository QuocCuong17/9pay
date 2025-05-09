package org.apache.pdfbox.contentstream.operator.graphics;

import android.graphics.PointF;
import androidx.media3.exoplayer.upstream.CmcdData;
import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSNumber;

/* loaded from: classes5.dex */
public class LineTo extends GraphicsOperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return CmcdData.Factory.STREAM_TYPE_LIVE;
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        PointF transformedPoint = this.context.transformedPoint(((COSNumber) list.get(0)).floatValue(), ((COSNumber) list.get(1)).floatValue());
        this.context.lineTo(transformedPoint.x, transformedPoint.y);
    }
}
