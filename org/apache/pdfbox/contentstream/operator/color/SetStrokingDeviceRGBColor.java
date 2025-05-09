package org.apache.pdfbox.contentstream.operator.color;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public class SetStrokingDeviceRGBColor extends SetStrokingColor {
    @Override // org.apache.pdfbox.contentstream.operator.color.SetStrokingColor, org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "RG";
    }

    @Override // org.apache.pdfbox.contentstream.operator.color.SetColor, org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        this.context.getGraphicsState().setStrokingColorSpace(this.context.getResources().getColorSpace(COSName.DEVICERGB));
        super.process(operator, list);
    }
}
