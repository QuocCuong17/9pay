package org.apache.pdfbox.util;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

/* loaded from: classes5.dex */
public class PageExtractor {
    protected int endPage;
    protected PDDocument sourceDocument;
    protected int startPage;

    public PageExtractor(PDDocument pDDocument) {
        this.startPage = 1;
        this.endPage = 0;
        this.sourceDocument = pDDocument;
        this.endPage = pDDocument.getNumberOfPages();
    }

    public PageExtractor(PDDocument pDDocument, int i, int i2) {
        this(pDDocument);
        this.startPage = i;
        this.endPage = i2;
    }

    public PDDocument extract() throws IOException {
        PDDocument pDDocument = new PDDocument();
        pDDocument.setDocumentInformation(this.sourceDocument.getDocumentInformation());
        pDDocument.getDocumentCatalog().setViewerPreferences(this.sourceDocument.getDocumentCatalog().getViewerPreferences());
        for (int i = this.startPage; i <= this.endPage; i++) {
            PDPage page = this.sourceDocument.getPage(i - 1);
            PDPage importPage = pDDocument.importPage(page);
            importPage.setCropBox(page.getCropBox());
            importPage.setMediaBox(page.getMediaBox());
            importPage.setResources(page.getResources());
            importPage.setRotation(page.getRotation());
        }
        return pDDocument;
    }

    public int getStartPage() {
        return this.startPage;
    }

    public void setStartPage(int i) {
        this.startPage = i;
    }

    public int getEndPage() {
        return this.endPage;
    }

    public void setEndPage(int i) {
        this.endPage = i;
    }
}
