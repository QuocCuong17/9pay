package org.apache.pdfbox.contentstream;

import android.graphics.PointF;
import java.io.IOException;
import org.apache.pdfbox.contentstream.operator.color.SetNonStrokingColor;
import org.apache.pdfbox.contentstream.operator.color.SetNonStrokingColorSpace;
import org.apache.pdfbox.contentstream.operator.color.SetNonStrokingDeviceGrayColor;
import org.apache.pdfbox.contentstream.operator.color.SetNonStrokingDeviceRGBColor;
import org.apache.pdfbox.contentstream.operator.color.SetStrokingColor;
import org.apache.pdfbox.contentstream.operator.color.SetStrokingColorSpace;
import org.apache.pdfbox.contentstream.operator.color.SetStrokingDeviceGrayColor;
import org.apache.pdfbox.contentstream.operator.color.SetStrokingDeviceRGBColor;
import org.apache.pdfbox.contentstream.operator.graphics.AppendRectangleToPath;
import org.apache.pdfbox.contentstream.operator.graphics.CloseAndStrokePath;
import org.apache.pdfbox.contentstream.operator.graphics.CloseFillEvenOddAndStrokePath;
import org.apache.pdfbox.contentstream.operator.graphics.CloseFillNonZeroAndStrokePath;
import org.apache.pdfbox.contentstream.operator.graphics.ClosePath;
import org.apache.pdfbox.contentstream.operator.graphics.CurveTo;
import org.apache.pdfbox.contentstream.operator.graphics.CurveToReplicateFinalPoint;
import org.apache.pdfbox.contentstream.operator.graphics.CurveToReplicateInitialPoint;
import org.apache.pdfbox.contentstream.operator.graphics.DrawObject;
import org.apache.pdfbox.contentstream.operator.graphics.EndPath;
import org.apache.pdfbox.contentstream.operator.graphics.LineTo;
import org.apache.pdfbox.contentstream.operator.graphics.MoveTo;
import org.apache.pdfbox.contentstream.operator.graphics.ShadingFill;
import org.apache.pdfbox.contentstream.operator.graphics.StrokePath;
import org.apache.pdfbox.contentstream.operator.state.Concatenate;
import org.apache.pdfbox.contentstream.operator.state.Restore;
import org.apache.pdfbox.contentstream.operator.state.Save;
import org.apache.pdfbox.contentstream.operator.state.SetFlatness;
import org.apache.pdfbox.contentstream.operator.state.SetGraphicsStateParameters;
import org.apache.pdfbox.contentstream.operator.state.SetLineCapStyle;
import org.apache.pdfbox.contentstream.operator.state.SetLineDashPattern;
import org.apache.pdfbox.contentstream.operator.state.SetLineJoinStyle;
import org.apache.pdfbox.contentstream.operator.state.SetLineMiterLimit;
import org.apache.pdfbox.contentstream.operator.state.SetLineWidth;
import org.apache.pdfbox.contentstream.operator.state.SetMatrix;
import org.apache.pdfbox.contentstream.operator.state.SetRenderingIntent;
import org.apache.pdfbox.contentstream.operator.text.BeginText;
import org.apache.pdfbox.contentstream.operator.text.EndText;
import org.apache.pdfbox.contentstream.operator.text.MoveText;
import org.apache.pdfbox.contentstream.operator.text.MoveTextSetLeading;
import org.apache.pdfbox.contentstream.operator.text.NextLine;
import org.apache.pdfbox.contentstream.operator.text.SetCharSpacing;
import org.apache.pdfbox.contentstream.operator.text.SetFontAndSize;
import org.apache.pdfbox.contentstream.operator.text.SetTextHorizontalScaling;
import org.apache.pdfbox.contentstream.operator.text.SetTextLeading;
import org.apache.pdfbox.contentstream.operator.text.SetTextRenderingMode;
import org.apache.pdfbox.contentstream.operator.text.SetTextRise;
import org.apache.pdfbox.contentstream.operator.text.SetWordSpacing;
import org.apache.pdfbox.contentstream.operator.text.ShowText;
import org.apache.pdfbox.contentstream.operator.text.ShowTextAdjusted;
import org.apache.pdfbox.contentstream.operator.text.ShowTextLine;
import org.apache.pdfbox.contentstream.operator.text.ShowTextLineAndSpace;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.image.PDImage;

/* loaded from: classes5.dex */
public abstract class PDFGraphicsStreamEngine extends PDFStreamEngine {
    private final PDPage page;

    public abstract void appendRectangle(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4) throws IOException;

    public abstract void clip(int i) throws IOException;

    public abstract void closePath() throws IOException;

    public abstract void curveTo(float f, float f2, float f3, float f4, float f5, float f6) throws IOException;

    public abstract void drawImage(PDImage pDImage) throws IOException;

    public abstract void endPath() throws IOException;

    public abstract void fillAndStrokePath(int i) throws IOException;

    public abstract void fillPath(int i) throws IOException;

    public abstract PointF getCurrentPoint() throws IOException;

    public abstract void lineTo(float f, float f2) throws IOException;

    public abstract void moveTo(float f, float f2) throws IOException;

    public abstract void shadingFill(COSName cOSName) throws IOException;

    public abstract void strokePath() throws IOException;

    protected PDFGraphicsStreamEngine(PDPage pDPage) {
        this.page = pDPage;
        addOperator(new CloseFillNonZeroAndStrokePath());
        addOperator(new CloseFillEvenOddAndStrokePath());
        addOperator(new BeginText());
        addOperator(new CurveTo());
        addOperator(new Concatenate());
        addOperator(new SetStrokingColorSpace());
        addOperator(new SetNonStrokingColorSpace());
        addOperator(new SetLineDashPattern());
        addOperator(new DrawObject());
        addOperator(new EndText());
        addOperator(new SetStrokingDeviceGrayColor());
        addOperator(new SetNonStrokingDeviceGrayColor());
        addOperator(new SetGraphicsStateParameters());
        addOperator(new ClosePath());
        addOperator(new SetFlatness());
        addOperator(new SetLineJoinStyle());
        addOperator(new SetLineCapStyle());
        addOperator(new LineTo());
        addOperator(new MoveTo());
        addOperator(new SetLineMiterLimit());
        addOperator(new EndPath());
        addOperator(new Save());
        addOperator(new Restore());
        addOperator(new AppendRectangleToPath());
        addOperator(new SetStrokingDeviceRGBColor());
        addOperator(new SetNonStrokingDeviceRGBColor());
        addOperator(new SetRenderingIntent());
        addOperator(new CloseAndStrokePath());
        addOperator(new StrokePath());
        addOperator(new SetStrokingColor());
        addOperator(new SetNonStrokingColor());
        addOperator(new ShadingFill());
        addOperator(new NextLine());
        addOperator(new SetCharSpacing());
        addOperator(new MoveText());
        addOperator(new MoveTextSetLeading());
        addOperator(new SetFontAndSize());
        addOperator(new ShowText());
        addOperator(new ShowTextAdjusted());
        addOperator(new SetTextLeading());
        addOperator(new SetMatrix());
        addOperator(new SetTextRenderingMode());
        addOperator(new SetTextRise());
        addOperator(new SetWordSpacing());
        addOperator(new SetTextHorizontalScaling());
        addOperator(new CurveToReplicateInitialPoint());
        addOperator(new SetLineWidth());
        addOperator(new CurveToReplicateFinalPoint());
        addOperator(new ShowTextLine());
        addOperator(new ShowTextLineAndSpace());
    }

    protected final PDPage getPage() {
        return this.page;
    }
}
