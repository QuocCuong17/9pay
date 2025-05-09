package org.apache.pdfbox.text;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import org.apache.pdfbox.contentstream.PDFStreamEngine;
import org.apache.pdfbox.contentstream.operator.DrawObject;
import org.apache.pdfbox.contentstream.operator.state.Concatenate;
import org.apache.pdfbox.contentstream.operator.state.Restore;
import org.apache.pdfbox.contentstream.operator.state.Save;
import org.apache.pdfbox.contentstream.operator.state.SetGraphicsStateParameters;
import org.apache.pdfbox.contentstream.operator.state.SetMatrix;
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
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDSimpleFont;
import org.apache.pdfbox.pdmodel.font.PDType3Font;
import org.apache.pdfbox.pdmodel.font.encoding.GlyphList;
import org.apache.pdfbox.pdmodel.graphics.state.PDGraphicsState;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.PDFBoxResourceLoader;
import org.apache.pdfbox.util.Vector;

/* loaded from: classes5.dex */
class PDFTextStreamEngine extends PDFStreamEngine {
    private final GlyphList glyphList;
    private Matrix legacyCTM;
    private int pageRotation;
    private PDRectangle pageSize;

    protected void processTextPosition(TextPosition textPosition) {
    }

    public PDFTextStreamEngine() throws IOException {
        InputStream resourceAsStream;
        addOperator(new BeginText());
        addOperator(new Concatenate());
        addOperator(new DrawObject());
        addOperator(new EndText());
        addOperator(new SetGraphicsStateParameters());
        addOperator(new Save());
        addOperator(new Restore());
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
        addOperator(new ShowTextLine());
        addOperator(new ShowTextLineAndSpace());
        if (PDFBoxResourceLoader.isReady()) {
            resourceAsStream = GlyphList.class.getClassLoader().getResourceAsStream("org/apache/pdfbox/resources/glyphlist/additional.txt");
        } else {
            resourceAsStream = GlyphList.class.getClassLoader().getResourceAsStream("org/apache/pdfbox/resources/glyphlist/additional.txt");
        }
        this.glyphList = new GlyphList(GlyphList.getAdobeGlyphList(), resourceAsStream);
    }

    @Override // org.apache.pdfbox.contentstream.PDFStreamEngine
    public void processPage(PDPage pDPage) throws IOException {
        this.pageRotation = pDPage.getRotation();
        this.pageSize = pDPage.getCropBox();
        super.processPage(pDPage);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.pdfbox.contentstream.PDFStreamEngine
    public void showText(byte[] bArr) throws IOException {
        this.legacyCTM = getGraphicsState().getCurrentTransformationMatrix().clone();
        super.showText(bArr);
    }

    @Override // org.apache.pdfbox.contentstream.PDFStreamEngine
    protected void showGlyph(Matrix matrix, PDFont pDFont, int i, String str, Vector vector) throws IOException {
        float f;
        String str2;
        PDGraphicsState graphicsState = getGraphicsState();
        Matrix matrix2 = this.legacyCTM;
        float fontSize = graphicsState.getTextState().getFontSize();
        float horizontalScaling = graphicsState.getTextState().getHorizontalScaling() / 100.0f;
        Matrix textMatrix = getTextMatrix();
        float f2 = pDFont.getFontMatrix().transformPoint(0.0d, pDFont.getBoundingBox().getHeight() / 2.0f).y;
        Matrix multiply = Matrix.getTranslateInstance(vector.getX() * fontSize * horizontalScaling, 0.0f).multiply(textMatrix).multiply(matrix2);
        float translateX = multiply.getTranslateX();
        float translateY = multiply.getTranslateY();
        float translateX2 = translateX - matrix.getTranslateX();
        float scalingFactorY = f2 * matrix.getScalingFactorY();
        float fontSize2 = getGraphicsState().getTextState().getFontSize();
        float horizontalScaling2 = getGraphicsState().getTextState().getHorizontalScaling() / 100.0f;
        float scaleX = pDFont instanceof PDType3Font ? 1.0f / pDFont.getFontMatrix().getScaleX() : 0.001f;
        try {
            f = pDFont.getSpaceWidth() * scaleX;
        } catch (Throwable th) {
            Log.w("PdfBoxAndroid", th.getMessage(), th);
            f = 0.0f;
        }
        if (f == 0.0f) {
            f = pDFont.getAverageFontWidth() * scaleX * 0.8f;
        }
        float scalingFactorX = matrix2.getScalingFactorX() * (f == 0.0f ? 1.0f : f) * fontSize2 * horizontalScaling2 * matrix.getScalingFactorX();
        String unicode = pDFont.toUnicode(i, this.glyphList);
        if (unicode != null) {
            str2 = unicode;
        } else if (!(pDFont instanceof PDSimpleFont)) {
            return;
        } else {
            str2 = new String(new char[]{(char) i});
        }
        processTextPosition(new TextPosition(this.pageRotation, this.pageSize.getWidth(), this.pageSize.getHeight(), matrix, translateX, translateY, scalingFactorY, translateX2, scalingFactorX, str2, new int[]{i}, pDFont, fontSize, (int) (matrix.getScalingFactorX() * fontSize)));
    }
}
