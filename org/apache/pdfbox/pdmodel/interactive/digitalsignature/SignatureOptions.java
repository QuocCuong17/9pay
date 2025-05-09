package org.apache.pdfbox.pdmodel.interactive.digitalsignature;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.VisualSignatureParser;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDVisibleSigProperties;

/* loaded from: classes5.dex */
public class SignatureOptions implements Closeable {
    private int pageNo = 0;
    private int preferedSignatureSize;
    private COSDocument visualSignature;

    public void setPage(int i) {
        this.pageNo = i;
    }

    public int getPage() {
        return this.pageNo;
    }

    public void setVisualSignature(InputStream inputStream) throws IOException {
        VisualSignatureParser visualSignatureParser = new VisualSignatureParser(inputStream);
        visualSignatureParser.parse();
        this.visualSignature = visualSignatureParser.getDocument();
    }

    public void setVisualSignature(PDVisibleSigProperties pDVisibleSigProperties) throws IOException {
        setVisualSignature(pDVisibleSigProperties.getVisibleSignature());
    }

    public COSDocument getVisualSignature() {
        return this.visualSignature;
    }

    public int getPreferedSignatureSize() {
        return this.preferedSignatureSize;
    }

    public void setPreferedSignatureSize(int i) {
        if (i > 0) {
            this.preferedSignatureSize = i;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        COSDocument cOSDocument = this.visualSignature;
        if (cOSDocument != null) {
            cOSDocument.close();
        }
    }
}
