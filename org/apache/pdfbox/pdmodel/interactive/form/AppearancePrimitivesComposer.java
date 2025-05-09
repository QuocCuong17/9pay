package org.apache.pdfbox.pdmodel.interactive.form;

import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.Locale;
import org.apache.pdfbox.pdfwriter.COSWriter;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.util.Charsets;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class AppearancePrimitivesComposer {
    private final NumberFormat formatDecimal = NumberFormat.getNumberInstance(Locale.US);
    private boolean inTextMode = false;
    private final OutputStream outputstream;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppearancePrimitivesComposer(OutputStream outputStream) {
        this.outputstream = outputStream;
    }

    public void addRect(PDRectangle pDRectangle) throws IOException {
        addRect(pDRectangle.getLowerLeftX(), pDRectangle.getLowerLeftY(), pDRectangle.getWidth(), pDRectangle.getHeight());
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

    public void beginText() throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: Nested beginText() calls are not allowed.");
        }
        writeOperator("BT");
        this.inTextMode = true;
    }

    public void clip() throws IOException {
        if (this.inTextMode) {
            throw new IOException("Error: clip is not allowed within a text block.");
        }
        writeOperator("W");
        writeOperator("n");
    }

    public void endText() throws IOException {
        if (!this.inTextMode) {
            throw new IOException("Error: You must call beginText() before calling endText.");
        }
        writeOperator("ET");
        this.inTextMode = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void newLineAtOffset(float f, float f2) throws IOException {
        writeOperand(f);
        writeOperand(f2);
        writeOperator("Td");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void showText(String str, PDFont pDFont) throws IOException {
        COSWriter.writeString(pDFont.encode(str), this.outputstream);
        write(" ");
        writeOperator("Tj");
    }

    private void write(String str) throws IOException {
        this.outputstream.write(str.getBytes(Charsets.US_ASCII));
    }

    private void writeOperator(String str) throws IOException {
        this.outputstream.write(str.getBytes(Charsets.US_ASCII));
        this.outputstream.write(10);
    }

    private void writeOperand(float f) throws IOException {
        write(this.formatDecimal.format(f));
        this.outputstream.write(32);
    }
}
