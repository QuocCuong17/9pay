package org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible;

import net.sf.scuba.smartcards.ISO7816;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

/* loaded from: classes5.dex */
public class PDVisibleSignDesigner {
    private Float imageHeight;
    private float imageSizeInPercents;
    private Float imageWidth;
    private float pageHeight;
    private float pageWidth;
    private float xAxis;
    private float yAxis;
    private String signatureFieldName = "sig";
    private byte[] formaterRectangleParams = {0, 0, 100, ISO7816.INS_INCREASE};
    private byte[] AffineTransformParams = {1, 0, 0, 1, 0, 0};

    private void calculatePageSize(PDDocument pDDocument, int i) {
        if (i < 1) {
            throw new IllegalArgumentException("First page of pdf is 1, not " + i);
        }
        PDRectangle mediaBox = pDDocument.getPage(i - 1).getMediaBox();
        pageHeight(mediaBox.getHeight());
        float width = mediaBox.getWidth();
        this.pageWidth = width;
        this.pageWidth = width + 0.0f;
        this.imageSizeInPercents = 100.0f - (0.0f / (width + 0.0f));
    }

    public PDVisibleSignDesigner zoom(float f) {
        this.imageHeight = Float.valueOf(this.imageHeight.floatValue() + ((this.imageHeight.floatValue() * f) / 100.0f));
        this.imageWidth = Float.valueOf(this.imageWidth.floatValue() + ((this.imageWidth.floatValue() * f) / 100.0f));
        return this;
    }

    public PDVisibleSignDesigner coordinates(float f, float f2) {
        xAxis(f);
        yAxis(f2);
        return this;
    }

    public float getxAxis() {
        return this.xAxis;
    }

    public PDVisibleSignDesigner xAxis(float f) {
        this.xAxis = f;
        return this;
    }

    public float getyAxis() {
        return this.yAxis;
    }

    public PDVisibleSignDesigner yAxis(float f) {
        this.yAxis = f;
        return this;
    }

    public float getWidth() {
        return this.imageWidth.floatValue();
    }

    public PDVisibleSignDesigner width(float f) {
        this.imageWidth = Float.valueOf(f);
        return this;
    }

    public float getHeight() {
        return this.imageHeight.floatValue();
    }

    public PDVisibleSignDesigner height(float f) {
        this.imageHeight = Float.valueOf(f);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float getTemplateHeight() {
        return getPageHeight();
    }

    private PDVisibleSignDesigner pageHeight(float f) {
        this.pageHeight = f;
        return this;
    }

    public String getSignatureFieldName() {
        return this.signatureFieldName;
    }

    public PDVisibleSignDesigner signatureFieldName(String str) {
        this.signatureFieldName = str;
        return this;
    }

    public byte[] getAffineTransformParams() {
        return this.AffineTransformParams;
    }

    public PDVisibleSignDesigner affineTransformParams(byte[] bArr) {
        this.AffineTransformParams = bArr;
        return this;
    }

    public byte[] getFormaterRectangleParams() {
        return this.formaterRectangleParams;
    }

    public PDVisibleSignDesigner formaterRectangleParams(byte[] bArr) {
        this.formaterRectangleParams = bArr;
        return this;
    }

    public float getPageWidth() {
        return this.pageWidth;
    }

    public PDVisibleSignDesigner pageWidth(float f) {
        this.pageWidth = f;
        return this;
    }

    public float getPageHeight() {
        return this.pageHeight;
    }

    public float getImageSizeInPercents() {
        return this.imageSizeInPercents;
    }

    public void imageSizeInPercents(float f) {
        this.imageSizeInPercents = f;
    }

    public String getSignatureText() {
        throw new UnsupportedOperationException("That method is not yet implemented");
    }

    public PDVisibleSignDesigner signatureText(String str) {
        throw new UnsupportedOperationException("That method is not yet implemented");
    }
}
