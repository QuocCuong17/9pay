package org.apache.pdfbox.pdmodel;

import android.graphics.Path;
import android.util.Log;
import androidx.media3.exoplayer.upstream.CmcdData;
import io.sentry.protocol.ViewHierarchyNode;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Stack;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdfwriter.COSWriter;
import org.apache.pdfbox.pdmodel.common.COSStreamArray;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.shading.PDShading;
import org.apache.pdfbox.util.Charsets;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.awt.AWTColor;
import org.apache.pdfbox.util.awt.AffineTransform;

/* loaded from: classes5.dex */
public final class PDPageContentStream implements Closeable {
    private final PDDocument document;
    private final Stack<PDFont> fontStack;
    private final NumberFormat formatDecimal;
    private boolean inTextMode;
    private final Stack<PDColorSpace> nonStrokingColorSpaceStack;
    private OutputStream output;
    private PDResources resources;
    private Stack<PDColorSpace> strokingColorSpaceStack;

    public PDPageContentStream(PDDocument pDDocument, PDPage pDPage) throws IOException {
        this(pDDocument, pDPage, false, true);
    }

    public PDPageContentStream(PDDocument pDDocument, PDPage pDPage, boolean z, boolean z2) throws IOException {
        this(pDDocument, pDPage, z, z2, false);
    }

    public PDPageContentStream(PDDocument pDDocument, PDPage pDPage, boolean z, boolean z2, boolean z3) throws IOException {
        COSStreamArray cOSStreamArray;
        this.inTextMode = false;
        this.fontStack = new Stack<>();
        this.nonStrokingColorSpaceStack = new Stack<>();
        this.strokingColorSpaceStack = new Stack<>();
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.formatDecimal = numberInstance;
        this.document = pDDocument;
        PDStream stream = pDPage.getStream();
        boolean z4 = stream != null;
        if (z && z4) {
            PDStream pDStream = new PDStream(pDDocument);
            if (stream.getStream() instanceof COSStreamArray) {
                cOSStreamArray = (COSStreamArray) stream.getStream();
                cOSStreamArray.appendStream(pDStream.getStream());
            } else {
                COSArray cOSArray = new COSArray();
                cOSArray.add(stream.getCOSObject());
                cOSArray.add(pDStream.getCOSObject());
                cOSStreamArray = new COSStreamArray(cOSArray);
            }
            if (z2) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(COSName.FLATE_DECODE);
                pDStream.setFilters(arrayList);
            }
            if (z3) {
                PDStream pDStream2 = new PDStream(pDDocument);
                this.output = pDStream2.createOutputStream();
                saveGraphicsState();
                close();
                if (z2) {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(COSName.FLATE_DECODE);
                    pDStream2.setFilters(arrayList2);
                }
                cOSStreamArray.insertCOSStream(pDStream2);
            }
            pDPage.setContents(new PDStream(cOSStreamArray));
            this.output = pDStream.createOutputStream();
            if (z3) {
                restoreGraphicsState();
            }
        } else {
            if (z4) {
                Log.w("PdfBoxAndroid", "You are overwriting an existing content, you should use the append mode");
            }
            PDStream pDStream3 = new PDStream(pDDocument);
            if (z2) {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(COSName.FLATE_DECODE);
                pDStream3.setFilters(arrayList3);
            }
            pDPage.setContents(pDStream3);
            this.output = pDStream3.createOutputStream();
        }
        numberInstance.setMaximumFractionDigits(10);
        numberInstance.setGroupingUsed(false);
        PDResources resources = pDPage.getResources();
        this.resources = resources;
        if (resources == null) {
            PDResources pDResources = new PDResources();
            this.resources = pDResources;
            pDPage.setResources(pDResources);
        }
    }

    public void beginText() throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: Nested beginText() calls are not allowed.");
        }
        writeOperator("BT");
        this.inTextMode = true;
    }

    public void endText() throws IOException {
        if (!this.inTextMode) {
            throw new IOException("Error: You must call beginText() before calling endText.");
        }
        writeOperator("ET");
        this.inTextMode = false;
    }

    public void setFont(PDFont pDFont, float f) throws IOException {
        if (this.fontStack.isEmpty()) {
            this.fontStack.add(pDFont);
        } else {
            this.fontStack.setElementAt(pDFont, r0.size() - 1);
        }
        if (pDFont.willBeSubset() && !this.document.getFontsToSubset().contains(pDFont)) {
            this.document.getFontsToSubset().add(pDFont);
        }
        writeOperand(this.resources.add(pDFont));
        writeOperand(f);
        writeOperator("Tf");
    }

    @Deprecated
    public void drawString(String str) throws IOException {
        showText(str);
    }

    public void showText(String str) throws IOException {
        if (!this.inTextMode) {
            throw new IllegalStateException("Must call beginText() before showText()");
        }
        if (this.fontStack.isEmpty()) {
            throw new IllegalStateException("Must call setFont() before showText()");
        }
        PDFont peek = this.fontStack.peek();
        if (peek.willBeSubset()) {
            int i = 0;
            while (i < str.length()) {
                int codePointAt = str.codePointAt(i);
                peek.addToSubset(codePointAt);
                i += Character.charCount(codePointAt);
            }
        }
        COSWriter.writeString(peek.encode(str), this.output);
        write(" ");
        writeOperator("Tj");
    }

    public void setLeading(double d) throws IOException {
        writeOperand((float) d);
        writeOperator("TL");
    }

    public void newLine() throws IOException {
        if (!this.inTextMode) {
            throw new IllegalStateException("Must call beginText() before newLine()");
        }
        writeOperator("T*");
    }

    @Deprecated
    public void moveTextPositionByAmount(float f, float f2) throws IOException {
        newLineAtOffset(f, f2);
    }

    public void newLineAtOffset(float f, float f2) throws IOException {
        if (!this.inTextMode) {
            throw new IOException("Error: must call beginText() before newLineAtOffset()");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperator("Td");
    }

    @Deprecated
    public void setTextMatrix(double d, double d2, double d3, double d4, double d5, double d6) throws IOException {
        setTextMatrix(new Matrix((float) d, (float) d2, (float) d3, (float) d4, (float) d5, (float) d6));
    }

    @Deprecated
    public void setTextMatrix(AffineTransform affineTransform) throws IOException {
        setTextMatrix(new Matrix(affineTransform));
    }

    public void setTextMatrix(Matrix matrix) throws IOException {
        if (!this.inTextMode) {
            throw new IOException("Error: must call beginText() before setTextMatrix");
        }
        writeAffineTransform(matrix.createAffineTransform());
        writeOperator("Tm");
    }

    @Deprecated
    public void setTextScaling(double d, double d2, double d3, double d4) throws IOException {
        setTextMatrix(new Matrix((float) d, 0.0f, 0.0f, (float) d2, (float) d3, (float) d4));
    }

    @Deprecated
    public void setTextTranslation(double d, double d2) throws IOException {
        setTextMatrix(Matrix.getTranslateInstance((float) d, (float) d2));
    }

    @Deprecated
    public void setTextRotation(double d, double d2, double d3) throws IOException {
        setTextMatrix(Matrix.getRotateInstance(d, (float) d2, (float) d3));
    }

    public void drawImage(PDImageXObject pDImageXObject, float f, float f2) throws IOException {
        drawImage(pDImageXObject, f, f2, pDImageXObject.getWidth(), pDImageXObject.getHeight());
    }

    public void drawImage(PDImageXObject pDImageXObject, float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: drawImage is not allowed within a text block.");
        }
        saveGraphicsState();
        transform(new Matrix(new AffineTransform(f3, 0.0d, 0.0d, f4, f, f2)));
        writeOperand(this.resources.add(pDImageXObject));
        writeOperator("Do");
        restoreGraphicsState();
    }

    @Deprecated
    public void drawXObject(PDXObject pDXObject, float f, float f2, float f3, float f4) throws IOException {
        drawXObject(pDXObject, new AffineTransform(f3, 0.0d, 0.0d, f4, f, f2));
    }

    @Deprecated
    public void drawXObject(PDXObject pDXObject, AffineTransform affineTransform) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: drawXObject is not allowed within a text block.");
        }
        COSName add = this.resources.add(pDXObject, pDXObject instanceof PDImageXObject ? "Im" : StandardStructureTypes.FORM);
        saveGraphicsState();
        transform(new Matrix(affineTransform));
        writeOperand(add);
        writeOperator("Do");
        restoreGraphicsState();
    }

    public void drawForm(PDFormXObject pDFormXObject) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: drawForm is not allowed within a text block.");
        }
        writeOperand(this.resources.add(pDFormXObject));
        writeOperator("Do");
    }

    @Deprecated
    public void concatenate2CTM(double d, double d2, double d3, double d4, double d5, double d6) throws IOException {
        transform(new Matrix((float) d, (float) d2, (float) d3, (float) d4, (float) d5, (float) d6));
    }

    @Deprecated
    public void concatenate2CTM(AffineTransform affineTransform) throws IOException {
        transform(new Matrix(affineTransform));
    }

    public void transform(Matrix matrix) throws IOException {
        writeAffineTransform(matrix.createAffineTransform());
        writeOperator("cm");
    }

    public void saveGraphicsState() throws IOException {
        if (!this.fontStack.isEmpty()) {
            Stack<PDFont> stack = this.fontStack;
            stack.push(stack.peek());
        }
        writeOperator("q");
    }

    public void restoreGraphicsState() throws IOException {
        if (!this.fontStack.isEmpty()) {
            this.fontStack.pop();
        }
        writeOperator("Q");
    }

    @Deprecated
    public void setStrokingColorSpace(PDColorSpace pDColorSpace) throws IOException {
        if (this.strokingColorSpaceStack.isEmpty()) {
            this.strokingColorSpaceStack.add(pDColorSpace);
        } else {
            this.strokingColorSpaceStack.setElementAt(pDColorSpace, this.nonStrokingColorSpaceStack.size() - 1);
        }
        writeOperand(getName(pDColorSpace));
        writeOperator("CS");
    }

    @Deprecated
    public void setNonStrokingColorSpace(PDColorSpace pDColorSpace) throws IOException {
        if (this.nonStrokingColorSpaceStack.isEmpty()) {
            this.nonStrokingColorSpaceStack.add(pDColorSpace);
        } else {
            this.nonStrokingColorSpaceStack.setElementAt(pDColorSpace, r0.size() - 1);
        }
        writeOperand(getName(pDColorSpace));
        writeOperator("cs");
    }

    private COSName getName(PDColorSpace pDColorSpace) throws IOException {
        if ((pDColorSpace instanceof PDDeviceGray) || (pDColorSpace instanceof PDDeviceRGB)) {
            return COSName.getPDFName(pDColorSpace.getName());
        }
        return this.resources.add(pDColorSpace);
    }

    public void setStrokingColor(PDColor pDColor) throws IOException {
        if (this.strokingColorSpaceStack.isEmpty() || this.strokingColorSpaceStack.peek() != pDColor.getColorSpace()) {
            writeOperand(getName(pDColor.getColorSpace()));
            writeOperator("CS");
            if (this.strokingColorSpaceStack.isEmpty()) {
                this.strokingColorSpaceStack.add(pDColor.getColorSpace());
            } else {
                this.strokingColorSpaceStack.setElementAt(pDColor.getColorSpace(), this.nonStrokingColorSpaceStack.size() - 1);
            }
        }
        for (float f : pDColor.getComponents()) {
            writeOperand(f);
        }
        writeOperator("SC");
    }

    public void setStrokingColor(AWTColor aWTColor) throws IOException {
        setStrokingColor(new PDColor(new float[]{aWTColor.getRed() / 255.0f, aWTColor.getGreen() / 255.0f, aWTColor.getBlue() / 255.0f}, PDDeviceRGB.INSTANCE));
    }

    @Deprecated
    public void setStrokingColor(float[] fArr) throws IOException {
        if (this.strokingColorSpaceStack.isEmpty()) {
            throw new IllegalStateException("The color space must be set before setting a color");
        }
        for (float f : fArr) {
            writeOperand(f);
        }
        this.strokingColorSpaceStack.peek();
        writeOperator("SC");
    }

    public void setStrokingColor(int i, int i2, int i3) throws IOException {
        writeOperand(i / 255.0f);
        writeOperand(i2 / 255.0f);
        writeOperand(i3 / 255.0f);
        writeOperator("RG");
    }

    @Deprecated
    public void setStrokingColor(int i, int i2, int i3, int i4) throws IOException {
        setStrokingColor(i / 255.0f, i2 / 255.0f, i3 / 255.0f, i4 / 255.0f);
    }

    public void setStrokingColor(float f, float f2, float f3, float f4) throws IOException {
        writeOperand(f);
        writeOperand(f2);
        writeOperand(f3);
        writeOperand(f4);
        writeOperator("K");
    }

    @Deprecated
    public void setStrokingColor(int i) throws IOException {
        setStrokingColor(i / 255.0f);
    }

    public void setStrokingColor(double d) throws IOException {
        writeOperand((float) d);
        writeOperator("G");
    }

    public void setNonStrokingColor(PDColor pDColor) throws IOException {
        if (this.nonStrokingColorSpaceStack.isEmpty() || this.nonStrokingColorSpaceStack.peek() != pDColor.getColorSpace()) {
            writeOperand(getName(pDColor.getColorSpace()));
            writeOperator("cs");
            if (this.nonStrokingColorSpaceStack.isEmpty()) {
                this.nonStrokingColorSpaceStack.add(pDColor.getColorSpace());
            } else {
                this.nonStrokingColorSpaceStack.setElementAt(pDColor.getColorSpace(), this.nonStrokingColorSpaceStack.size() - 1);
            }
        }
        for (float f : pDColor.getComponents()) {
            writeOperand(f);
        }
        writeOperator("sc");
    }

    public void setNonStrokingColor(AWTColor aWTColor) throws IOException {
        setNonStrokingColor(new PDColor(new float[]{aWTColor.getRed() / 255.0f, aWTColor.getGreen() / 255.0f, aWTColor.getBlue() / 255.0f}, PDDeviceRGB.INSTANCE));
    }

    @Deprecated
    public void setNonStrokingColor(float[] fArr) throws IOException {
        if (this.nonStrokingColorSpaceStack.isEmpty()) {
            throw new IllegalStateException("The color space must be set before setting a color");
        }
        this.nonStrokingColorSpaceStack.peek();
        writeOperator("sc");
    }

    public void setNonStrokingColor(int i, int i2, int i3) throws IOException {
        writeOperand(i / 255.0f);
        writeOperand(i2 / 255.0f);
        writeOperand(i3 / 255.0f);
        writeOperator("rg");
    }

    public void setNonStrokingColor(int i, int i2, int i3, int i4) throws IOException {
        setNonStrokingColor(i / 255.0f, i2 / 255.0f, i3 / 255.0f, i4 / 255.0f);
    }

    public void setNonStrokingColor(double d, double d2, double d3, double d4) throws IOException {
        writeOperand((float) d);
        writeOperand((float) d2);
        writeOperand((float) d3);
        writeOperand((float) d4);
        writeOperator("k");
    }

    public void setNonStrokingColor(int i) throws IOException {
        setNonStrokingColor(i / 255.0f);
    }

    public void setNonStrokingColor(double d) throws IOException {
        writeOperand((float) d);
        writeOperator("g");
    }

    public void addRect(float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: addRect is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperand(f3);
        writeOperand(f4);
        writeOperator("re");
    }

    @Deprecated
    public void fillRect(float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: fillRect is not allowed within a text block.");
        }
        addRect(f, f2, f3, f4);
        fill();
    }

    @Deprecated
    public void addBezier312(float f, float f2, float f3, float f4, float f5, float f6) throws IOException {
        curveTo(f, f2, f3, f4, f5, f6);
    }

    public void curveTo(float f, float f2, float f3, float f4, float f5, float f6) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: curveTo is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperand(f3);
        writeOperand(f4);
        writeOperand(f5);
        writeOperand(f6);
        writeOperator("c");
    }

    @Deprecated
    public void addBezier32(float f, float f2, float f3, float f4) throws IOException {
        curveTo2(f, f2, f3, f4);
    }

    public void curveTo2(float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: curveTo2 is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperand(f3);
        writeOperand(f4);
        writeOperator("v");
    }

    @Deprecated
    public void addBezier31(float f, float f2, float f3, float f4) throws IOException {
        curveTo1(f, f2, f3, f4);
    }

    public void curveTo1(float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: curveTo1 is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperand(f3);
        writeOperand(f4);
        writeOperator(ViewHierarchyNode.JsonKeys.Y);
    }

    public void moveTo(float f, float f2) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: moveTo is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperator("m");
    }

    public void lineTo(float f, float f2) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: lineTo is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperand(f2);
        writeOperator(CmcdData.Factory.STREAM_TYPE_LIVE);
    }

    @Deprecated
    public void addLine(float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: addLine is not allowed within a text block.");
        }
        moveTo(f, f2);
        lineTo(f3, f4);
    }

    @Deprecated
    public void drawLine(float f, float f2, float f3, float f4) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: drawLine is not allowed within a text block.");
        }
        moveTo(f, f2);
        lineTo(f3, f4);
        stroke();
    }

    @Deprecated
    public void addPolygon(float[] fArr, float[] fArr2) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: addPolygon is not allowed within a text block.");
        }
        if (fArr.length != fArr2.length) {
            throw new IOException("Error: some points are missing coordinate");
        }
        for (int i = 0; i < fArr.length; i++) {
            if (i == 0) {
                moveTo(fArr[i], fArr2[i]);
            } else {
                lineTo(fArr[i], fArr2[i]);
            }
        }
        closeSubPath();
    }

    @Deprecated
    public void drawPolygon(float[] fArr, float[] fArr2) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: drawPolygon is not allowed within a text block.");
        }
        addPolygon(fArr, fArr2);
        stroke();
    }

    @Deprecated
    public void fillPolygon(float[] fArr, float[] fArr2) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: fillPolygon is not allowed within a text block.");
        }
        addPolygon(fArr, fArr2);
        fill();
    }

    public void stroke() throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: stroke is not allowed within a text block.");
        }
        writeOperator("S");
    }

    public void closeAndStroke() throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: closeAndStroke is not allowed within a text block.");
        }
        writeOperator(CmcdData.Factory.STREAMING_FORMAT_SS);
    }

    @Deprecated
    public void fill(Path.FillType fillType) throws IOException {
        if (fillType == Path.FillType.WINDING) {
            fill();
        } else {
            if (fillType == Path.FillType.EVEN_ODD) {
                fillEvenOdd();
                return;
            }
            throw new IOException("Error: unknown value for winding rule");
        }
    }

    public void fill() throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: fill is not allowed within a text block.");
        }
        writeOperator("f");
    }

    public void fillEvenOdd() throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: fill is not allowed within a text block.");
        }
        writeOperator("f*");
    }

    public void shadingFill(PDShading pDShading) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: shadingFill is not allowed within a text block.");
        }
        writeOperand(this.resources.add(pDShading));
        writeOperator("sh");
    }

    @Deprecated
    public void closeSubPath() throws IOException {
        closePath();
    }

    public void closePath() throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: closePath is not allowed within a text block.");
        }
        writeOperator(CmcdData.Factory.STREAMING_FORMAT_HLS);
    }

    @Deprecated
    public void clipPath(Path.FillType fillType) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: clipPath is not allowed within a text block.");
        }
        if (fillType == Path.FillType.WINDING) {
            writeOperator("W");
        } else if (fillType == Path.FillType.EVEN_ODD) {
            writeOperator("W");
        } else {
            throw new IOException("Error: unknown value for winding rule");
        }
        writeOperator("n");
    }

    public void clip() throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: clip is not allowed within a text block.");
        }
        writeOperator("W");
        writeOperator("n");
    }

    public void clipEvenOdd() throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: clipEvenOdd is not allowed within a text block.");
        }
        writeOperator("W*");
        writeOperator("n");
    }

    public void setLineWidth(float f) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: setLineWidth is not allowed within a text block.");
        }
        writeOperand(f);
        writeOperator("w");
    }

    public void setLineJoinStyle(int i) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: setLineJoinStyle is not allowed within a text block.");
        }
        if (i >= 0 && i <= 2) {
            writeOperand(i);
            writeOperator("j");
            return;
        }
        throw new IOException("Error: unknown value for line join style");
    }

    public void setLineCapStyle(int i) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: setLineCapStyle is not allowed within a text block.");
        }
        if (i >= 0 && i <= 2) {
            writeOperand(i);
            writeOperator("J");
            return;
        }
        throw new IOException("Error: unknown value for line cap style");
    }

    public void setLineDashPattern(float[] fArr, float f) throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: setLineDashPattern is not allowed within a text block.");
        }
        write("[");
        for (float f2 : fArr) {
            writeOperand(f2);
        }
        write("] ");
        writeOperand(f);
        writeOperator("d");
    }

    @Deprecated
    public void beginMarkedContentSequence(COSName cOSName) throws IOException {
        beginMarkedContent(cOSName);
    }

    public void beginMarkedContent(COSName cOSName) throws IOException {
        writeOperand(cOSName);
        writeOperator("BMC");
    }

    @Deprecated
    public void beginMarkedContentSequence(COSName cOSName, COSName cOSName2) throws IOException {
        writeOperand(cOSName);
        writeOperand(cOSName2);
        writeOperator("BDC");
    }

    public void beginMarkedContent(COSName cOSName, PDPropertyList pDPropertyList) throws IOException {
        writeOperand(cOSName);
        writeOperand(this.resources.add(pDPropertyList));
        writeOperator("BDC");
    }

    @Deprecated
    public void endMarkedContentSequence() throws IOException {
        endMarkedContent();
    }

    public void endMarkedContent() throws IOException {
        writeOperator("EMC");
    }

    @Deprecated
    public void appendRawCommands(String str) throws IOException {
        this.output.write(str.getBytes(Charsets.US_ASCII));
    }

    @Deprecated
    public void appendRawCommands(byte[] bArr) throws IOException {
        this.output.write(bArr);
    }

    @Deprecated
    public void appendRawCommands(int i) throws IOException {
        this.output.write(i);
    }

    @Deprecated
    public void appendRawCommands(double d) throws IOException {
        this.output.write(this.formatDecimal.format(d).getBytes(Charsets.US_ASCII));
    }

    @Deprecated
    public void appendRawCommands(float f) throws IOException {
        this.output.write(this.formatDecimal.format(f).getBytes(Charsets.US_ASCII));
    }

    @Deprecated
    public void appendCOSName(COSName cOSName) throws IOException {
        cOSName.writePDF(this.output);
    }

    private void writeOperand(float f) throws IOException {
        writeOperator(this.formatDecimal.format(f));
        this.output.write(32);
    }

    private void writeOperand(int i) throws IOException {
        writeOperator(this.formatDecimal.format(i));
        this.output.write(32);
    }

    private void writeOperand(COSName cOSName) throws IOException {
        cOSName.writePDF(this.output);
        this.output.write(32);
    }

    private void writeOperator(String str) throws IOException {
        this.output.write(str.getBytes(Charsets.US_ASCII));
        this.output.write(10);
    }

    private void write(String str) throws IOException {
        this.output.write(str.getBytes(Charsets.US_ASCII));
    }

    private void writeLine() throws IOException {
        this.output.write(10);
    }

    private void writeBytes(byte[] bArr) throws IOException {
        this.output.write(bArr);
    }

    private void writeAffineTransform(AffineTransform affineTransform) throws IOException {
        double[] dArr = new double[6];
        affineTransform.getMatrix(dArr);
        for (int i = 0; i < 6; i++) {
            writeOperand((float) dArr[i]);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.output.close();
    }
}
