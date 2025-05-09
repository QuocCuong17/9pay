package org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible;

import android.util.Log;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode;
import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;
import org.apache.pdfbox.util.awt.AffineTransform;

/* loaded from: classes5.dex */
public class PDVisibleSigBuilder implements PDFTemplateBuilder {
    private PDFTemplateStructure pdfStructure = new PDFTemplateStructure();

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createPage(PDVisibleSignDesigner pDVisibleSignDesigner) {
        this.pdfStructure.setPage(new PDPage(new PDRectangle(pDVisibleSignDesigner.getPageWidth(), pDVisibleSignDesigner.getPageHeight())));
        Log.i("PdfBoxAndroid", "PDF page has been created");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createTemplate(PDPage pDPage) throws IOException {
        PDDocument pDDocument = new PDDocument();
        pDDocument.addPage(pDPage);
        this.pdfStructure.setTemplate(pDDocument);
    }

    public PDVisibleSigBuilder() {
        Log.i("PdfBoxAndroid", "PDF Strucure has been Created");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createAcroForm(PDDocument pDDocument) {
        PDAcroForm pDAcroForm = new PDAcroForm(pDDocument);
        pDDocument.getDocumentCatalog().setAcroForm(pDAcroForm);
        this.pdfStructure.setAcroForm(pDAcroForm);
        Log.i("PdfBoxAndroid", "Acro form page has been created");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public PDFTemplateStructure getStructure() {
        return this.pdfStructure;
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createSignatureField(PDAcroForm pDAcroForm) throws IOException {
        this.pdfStructure.setSignatureField(new PDSignatureField(pDAcroForm));
        Log.i("PdfBoxAndroid", "Signature field has been created");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createSignature(PDSignatureField pDSignatureField, PDPage pDPage, String str) throws IOException {
        PDSignature pDSignature = new PDSignature();
        pDSignatureField.setSignature(pDSignature);
        pDSignatureField.getWidget().setPage(pDPage);
        pDPage.getAnnotations().add(pDSignatureField.getWidget());
        pDSignature.setName(str);
        pDSignature.setByteRange(new int[]{0, 0, 0, 0});
        pDSignature.setContents(new byte[4096]);
        this.pdfStructure.setPdSignature(pDSignature);
        Log.i("PdfBoxAndroid", "PDSignatur has been created");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createAcroFormDictionary(PDAcroForm pDAcroForm, PDSignatureField pDSignatureField) throws IOException {
        List<PDFieldTreeNode> fields = pDAcroForm.getFields();
        COSDictionary dictionary = pDAcroForm.getDictionary();
        pDAcroForm.setSignaturesExist(true);
        pDAcroForm.setAppendOnly(true);
        dictionary.setDirect(true);
        fields.add(pDSignatureField);
        pDAcroForm.setDefaultAppearance("/sylfaen 0 Tf 0 g");
        this.pdfStructure.setAcroFormFields(fields);
        this.pdfStructure.setAcroFormDictionary(dictionary);
        Log.i("PdfBoxAndroid", "AcroForm dictionary has been created");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createSignatureRectangle(PDSignatureField pDSignatureField, PDVisibleSignDesigner pDVisibleSignDesigner) throws IOException {
        PDRectangle pDRectangle = new PDRectangle();
        pDRectangle.setUpperRightX(pDVisibleSignDesigner.getxAxis() + pDVisibleSignDesigner.getWidth());
        pDRectangle.setUpperRightY(pDVisibleSignDesigner.getTemplateHeight() - pDVisibleSignDesigner.getyAxis());
        pDRectangle.setLowerLeftY((pDVisibleSignDesigner.getTemplateHeight() - pDVisibleSignDesigner.getyAxis()) - pDVisibleSignDesigner.getHeight());
        pDRectangle.setLowerLeftX(pDVisibleSignDesigner.getxAxis());
        pDSignatureField.getWidget().setRectangle(pDRectangle);
        this.pdfStructure.setSignatureRectangle(pDRectangle);
        Log.i("PdfBoxAndroid", "rectangle of signature has been created");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createAffineTransform(byte[] bArr) {
        this.pdfStructure.setAffineTransform(new AffineTransform(bArr[0], bArr[1], bArr[2], bArr[3], bArr[4], bArr[5]));
        Log.i("PdfBoxAndroid", "Matrix has been added");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createProcSetArray() {
        COSArray cOSArray = new COSArray();
        cOSArray.add((COSBase) COSName.getPDFName("PDF"));
        cOSArray.add((COSBase) COSName.getPDFName("Text"));
        cOSArray.add((COSBase) COSName.getPDFName("ImageB"));
        cOSArray.add((COSBase) COSName.getPDFName("ImageC"));
        cOSArray.add((COSBase) COSName.getPDFName("ImageI"));
        this.pdfStructure.setProcSet(cOSArray);
        Log.i("PdfBoxAndroid", "ProcSet array has been created");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createFormaterRectangle(byte[] bArr) {
        PDRectangle pDRectangle = new PDRectangle();
        pDRectangle.setUpperRightX(bArr[0]);
        pDRectangle.setUpperRightY(bArr[1]);
        pDRectangle.setLowerLeftX(bArr[2]);
        pDRectangle.setLowerLeftY(bArr[3]);
        this.pdfStructure.setFormaterRectangle(pDRectangle);
        Log.i("PdfBoxAndroid", "Formater rectangle has been created");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createHolderFormStream(PDDocument pDDocument) {
        this.pdfStructure.setHolderFormStream(new PDStream(pDDocument));
        Log.i("PdfBoxAndroid", "Holder form Stream has been created");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createHolderFormResources() {
        this.pdfStructure.setHolderFormResources(new PDResources());
        Log.i("PdfBoxAndroid", "Holder form resources have been created");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createHolderForm(PDResources pDResources, PDStream pDStream, PDRectangle pDRectangle) {
        PDFormXObject pDFormXObject = new PDFormXObject(pDStream);
        pDFormXObject.setResources(pDResources);
        pDFormXObject.setBBox(pDRectangle);
        pDFormXObject.setFormType(1);
        this.pdfStructure.setHolderForm(pDFormXObject);
        Log.i("PdfBoxAndroid", "Holder form has been created");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createAppearanceDictionary(PDFormXObject pDFormXObject, PDSignatureField pDSignatureField) throws IOException {
        PDAppearanceDictionary pDAppearanceDictionary = new PDAppearanceDictionary();
        pDAppearanceDictionary.getCOSObject().setDirect(true);
        pDAppearanceDictionary.setNormalAppearance(new PDAppearanceStream(pDFormXObject.getCOSStream()));
        pDSignatureField.getWidget().setAppearance(pDAppearanceDictionary);
        this.pdfStructure.setAppearanceDictionary(pDAppearanceDictionary);
        Log.i("PdfBoxAndroid", "PDF appereance Dictionary has been created");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createInnerFormStream(PDDocument pDDocument) {
        this.pdfStructure.setInnterFormStream(new PDStream(pDDocument));
        Log.i("PdfBoxAndroid", "Stream of another form (inner form - it would be inside holder form) has been created");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createInnerFormResource() {
        this.pdfStructure.setInnerFormResources(new PDResources());
        Log.i("PdfBoxAndroid", "Resources of another form (inner form - it would be inside holder form)have been created");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createInnerForm(PDResources pDResources, PDStream pDStream, PDRectangle pDRectangle) {
        PDFormXObject pDFormXObject = new PDFormXObject(pDStream);
        pDFormXObject.setResources(pDResources);
        pDFormXObject.setBBox(pDRectangle);
        pDFormXObject.setFormType(1);
        this.pdfStructure.setInnerForm(pDFormXObject);
        Log.i("PdfBoxAndroid", "Another form (inner form - it would be inside holder form) have been created");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void insertInnerFormToHolerResources(PDFormXObject pDFormXObject, PDResources pDResources) {
        this.pdfStructure.setInnerFormName(pDResources.add(pDFormXObject, "FRM"));
        Log.i("PdfBoxAndroid", "Already inserted inner form  inside holder form");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createImageFormStream(PDDocument pDDocument) {
        this.pdfStructure.setImageFormStream(new PDStream(pDDocument));
        Log.i("PdfBoxAndroid", "Created image form Stream");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createImageFormResources() {
        this.pdfStructure.setImageFormResources(new PDResources());
        Log.i("PdfBoxAndroid", "Created image form Resources");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createImageForm(PDResources pDResources, PDResources pDResources2, PDStream pDStream, PDRectangle pDRectangle, AffineTransform affineTransform, PDImageXObject pDImageXObject) throws IOException {
        PDFormXObject pDFormXObject = new PDFormXObject(pDStream);
        pDFormXObject.setBBox(pDRectangle);
        pDFormXObject.setMatrix(affineTransform);
        pDFormXObject.setResources(pDResources);
        pDFormXObject.setFormType(1);
        pDResources.getCOSObject().setDirect(true);
        COSName add = pDResources2.add(pDFormXObject, "n");
        COSName add2 = pDResources.add(pDImageXObject, "img");
        this.pdfStructure.setImageForm(pDFormXObject);
        this.pdfStructure.setImageFormName(add);
        this.pdfStructure.setImageName(add2);
        Log.i("PdfBoxAndroid", "Created image form");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void injectProcSetArray(PDFormXObject pDFormXObject, PDPage pDPage, PDResources pDResources, PDResources pDResources2, PDResources pDResources3, COSArray cOSArray) {
        pDFormXObject.getResources().getCOSObject().setItem(COSName.PROC_SET, (COSBase) cOSArray);
        pDPage.getCOSObject().setItem(COSName.PROC_SET, (COSBase) cOSArray);
        pDResources.getCOSObject().setItem(COSName.PROC_SET, (COSBase) cOSArray);
        pDResources2.getCOSObject().setItem(COSName.PROC_SET, (COSBase) cOSArray);
        pDResources3.getCOSObject().setItem(COSName.PROC_SET, (COSBase) cOSArray);
        Log.i("PdfBoxAndroid", "inserted ProcSet to PDF");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void injectAppearanceStreams(PDStream pDStream, PDStream pDStream2, PDStream pDStream3, COSName cOSName, COSName cOSName2, COSName cOSName3, PDVisibleSignDesigner pDVisibleSignDesigner) throws IOException {
        String str = "q 100 0 0 50 0 0 cm /" + cOSName2.getName() + " Do Q\n";
        String str2 = "q 1 0 0 1 0 0 cm /" + cOSName3.getName() + " Do Q \n";
        String str3 = "q 1 0 0 1 0 0 cm /" + cOSName.getName() + " Do Q\n";
        appendRawCommands(this.pdfStructure.getHolderFormStream().createOutputStream(), str2);
        appendRawCommands(this.pdfStructure.getInnterFormStream().createOutputStream(), str3);
        appendRawCommands(this.pdfStructure.getImageFormStream().createOutputStream(), str);
        Log.i("PdfBoxAndroid", "Injected apereance stream to pdf");
    }

    public void appendRawCommands(OutputStream outputStream, String str) throws IOException {
        outputStream.write(str.getBytes("UTF-8"));
        outputStream.close();
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createVisualSignature(PDDocument pDDocument) {
        this.pdfStructure.setVisualSignature(pDDocument.getDocument());
        Log.i("PdfBoxAndroid", "Visible signature has been created");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void createWidgetDictionary(PDSignatureField pDSignatureField, PDResources pDResources) throws IOException {
        COSDictionary dictionary = pDSignatureField.getWidget().getDictionary();
        dictionary.setNeedToBeUpdated(true);
        dictionary.setItem(COSName.DR, (COSBase) pDResources.getCOSObject());
        this.pdfStructure.setWidgetDictionary(dictionary);
        Log.i("PdfBoxAndroid", "WidgetDictionary has been crated");
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDFTemplateBuilder
    public void closeTemplate(PDDocument pDDocument) throws IOException {
        pDDocument.close();
        this.pdfStructure.getTemplate().close();
    }
}
