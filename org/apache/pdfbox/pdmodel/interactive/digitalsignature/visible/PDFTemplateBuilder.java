package org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible;

import java.io.IOException;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;
import org.apache.pdfbox.util.awt.AffineTransform;

/* loaded from: classes5.dex */
public interface PDFTemplateBuilder {
    void closeTemplate(PDDocument pDDocument) throws IOException;

    void createAcroForm(PDDocument pDDocument);

    void createAcroFormDictionary(PDAcroForm pDAcroForm, PDSignatureField pDSignatureField) throws IOException;

    void createAffineTransform(byte[] bArr);

    void createAppearanceDictionary(PDFormXObject pDFormXObject, PDSignatureField pDSignatureField) throws IOException;

    void createFormaterRectangle(byte[] bArr);

    void createHolderForm(PDResources pDResources, PDStream pDStream, PDRectangle pDRectangle);

    void createHolderFormResources();

    void createHolderFormStream(PDDocument pDDocument);

    void createImageForm(PDResources pDResources, PDResources pDResources2, PDStream pDStream, PDRectangle pDRectangle, AffineTransform affineTransform, PDImageXObject pDImageXObject) throws IOException;

    void createImageFormResources();

    void createImageFormStream(PDDocument pDDocument);

    void createInnerForm(PDResources pDResources, PDStream pDStream, PDRectangle pDRectangle);

    void createInnerFormResource();

    void createInnerFormStream(PDDocument pDDocument);

    void createPage(PDVisibleSignDesigner pDVisibleSignDesigner);

    void createProcSetArray();

    void createSignature(PDSignatureField pDSignatureField, PDPage pDPage, String str) throws IOException;

    void createSignatureField(PDAcroForm pDAcroForm) throws IOException;

    void createSignatureRectangle(PDSignatureField pDSignatureField, PDVisibleSignDesigner pDVisibleSignDesigner) throws IOException;

    void createTemplate(PDPage pDPage) throws IOException;

    void createVisualSignature(PDDocument pDDocument);

    void createWidgetDictionary(PDSignatureField pDSignatureField, PDResources pDResources) throws IOException;

    PDFTemplateStructure getStructure();

    void injectAppearanceStreams(PDStream pDStream, PDStream pDStream2, PDStream pDStream3, COSName cOSName, COSName cOSName2, COSName cOSName3, PDVisibleSignDesigner pDVisibleSignDesigner) throws IOException;

    void injectProcSetArray(PDFormXObject pDFormXObject, PDPage pDPage, PDResources pDResources, PDResources pDResources2, PDResources pDResources3, COSArray cOSArray);

    void insertInnerFormToHolerResources(PDFormXObject pDFormXObject, PDResources pDResources);
}
