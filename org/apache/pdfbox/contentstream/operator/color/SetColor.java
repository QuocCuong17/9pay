package org.apache.pdfbox.contentstream.operator.color;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.MissingOperandException;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceColorSpace;

/* loaded from: classes5.dex */
public abstract class SetColor extends OperatorProcessor {
    protected abstract PDColor getColor();

    protected abstract PDColorSpace getColorSpace();

    protected abstract void setColor(PDColor pDColor);

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        PDColorSpace colorSpace = getColorSpace();
        if ((colorSpace instanceof PDDeviceColorSpace) && list.size() < colorSpace.getNumberOfComponents()) {
            throw new MissingOperandException(operator, list);
        }
        COSArray cOSArray = new COSArray();
        cOSArray.addAll(list);
        setColor(new PDColor(cOSArray, colorSpace));
    }
}
