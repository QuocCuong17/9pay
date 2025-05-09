package org.apache.pdfbox.contentstream.operator.color;

import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;

/* loaded from: classes5.dex */
public class SetStrokingColor extends SetColor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "SC";
    }

    @Override // org.apache.pdfbox.contentstream.operator.color.SetColor
    protected PDColor getColor() {
        return this.context.getGraphicsState().getStrokingColor();
    }

    @Override // org.apache.pdfbox.contentstream.operator.color.SetColor
    protected void setColor(PDColor pDColor) {
        this.context.getGraphicsState().setStrokingColor(pDColor);
    }

    @Override // org.apache.pdfbox.contentstream.operator.color.SetColor
    protected PDColorSpace getColorSpace() {
        return this.context.getGraphicsState().getStrokingColorSpace();
    }
}
