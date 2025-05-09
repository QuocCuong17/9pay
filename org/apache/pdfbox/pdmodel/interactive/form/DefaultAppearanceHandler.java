package org.apache.pdfbox.pdmodel.interactive.form;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.pdfparser.PDFStreamParser;

/* loaded from: classes5.dex */
class DefaultAppearanceHandler {
    private List<Object> appearanceTokens;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultAppearanceHandler(String str) throws IOException {
        this.appearanceTokens = getStreamTokens(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getFontSize() {
        int indexOf;
        if (this.appearanceTokens.isEmpty() || (indexOf = this.appearanceTokens.indexOf(Operator.getOperator("Tf"))) == -1) {
            return 0.0f;
        }
        return ((COSNumber) this.appearanceTokens.get(indexOf - 1)).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFontSize(float f) {
        int indexOf = this.appearanceTokens.indexOf(Operator.getOperator("Tf"));
        if (indexOf != -1) {
            this.appearanceTokens.set(indexOf - 1, new COSFloat(f));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public COSName getFontName() {
        return (COSName) this.appearanceTokens.get(this.appearanceTokens.indexOf(Operator.getOperator("Tf")) - 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Object> getTokens() {
        return this.appearanceTokens;
    }

    private List<Object> getStreamTokens(String str) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (str == null || str.isEmpty()) {
            return arrayList;
        }
        PDFStreamParser pDFStreamParser = new PDFStreamParser(new ByteArrayInputStream(str.getBytes()));
        pDFStreamParser.parse();
        List<Object> tokens = pDFStreamParser.getTokens();
        pDFStreamParser.close();
        return tokens;
    }
}
