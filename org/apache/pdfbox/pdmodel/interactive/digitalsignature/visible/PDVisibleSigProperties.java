package org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class PDVisibleSigProperties {
    private int page;
    private PDVisibleSignDesigner pdVisibleSignature;
    private int preferredSize;
    private String signatureReason;
    private String signerLocation;
    private String signerName;
    private InputStream visibleSignature;
    private boolean visualSignEnabled;

    public void buildSignature() throws IOException {
        new PDFTemplateCreator(new PDVisibleSigBuilder());
    }

    public String getSignerName() {
        return this.signerName;
    }

    public PDVisibleSigProperties signerName(String str) {
        this.signerName = str;
        return this;
    }

    public String getSignerLocation() {
        return this.signerLocation;
    }

    public PDVisibleSigProperties signerLocation(String str) {
        this.signerLocation = str;
        return this;
    }

    public String getSignatureReason() {
        return this.signatureReason;
    }

    public PDVisibleSigProperties signatureReason(String str) {
        this.signatureReason = str;
        return this;
    }

    public int getPage() {
        return this.page;
    }

    public PDVisibleSigProperties page(int i) {
        this.page = i;
        return this;
    }

    public int getPreferredSize() {
        return this.preferredSize;
    }

    public PDVisibleSigProperties preferredSize(int i) {
        this.preferredSize = i;
        return this;
    }

    public boolean isVisualSignEnabled() {
        return this.visualSignEnabled;
    }

    public PDVisibleSigProperties visualSignEnabled(boolean z) {
        this.visualSignEnabled = z;
        return this;
    }

    public PDVisibleSignDesigner getPdVisibleSignature() {
        return this.pdVisibleSignature;
    }

    public PDVisibleSigProperties setPdVisibleSignature(PDVisibleSignDesigner pDVisibleSignDesigner) {
        this.pdVisibleSignature = pDVisibleSignDesigner;
        return this;
    }

    public InputStream getVisibleSignature() {
        return this.visibleSignature;
    }

    public void setVisibleSignature(InputStream inputStream) {
        this.visibleSignature = inputStream;
    }
}
