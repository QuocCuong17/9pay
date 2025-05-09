package org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdfwriter.COSWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode;
import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;
import org.apache.pdfbox.util.awt.AffineTransform;

/* loaded from: classes5.dex */
public class PDFTemplateStructure {
    private PDAcroForm acroForm;
    private COSDictionary acroFormDictionary;
    private List<PDFieldTreeNode> acroFormFields;
    private AffineTransform affineTransform;
    private PDAppearanceDictionary appearanceDictionary;
    private PDRectangle formaterRectangle;
    private PDFormXObject holderForm;
    private PDResources holderFormResources;
    private PDStream holderFormStream;
    private PDImageXObject image;
    private PDFormXObject imageForm;
    private COSName imageFormName;
    private PDResources imageFormResources;
    private PDStream imageFormStream;
    private COSName imageName;
    private PDFormXObject innerForm;
    private COSName innerFormName;
    private PDResources innerFormResources;
    private PDStream innterFormStream;
    private PDPage page;
    private PDSignature pdSignature;
    private COSArray procSet;
    private PDSignatureField signatureField;
    private PDRectangle singatureRectangle;
    private PDDocument template;
    private COSDocument visualSignature;
    private COSDictionary widgetDictionary;

    public PDPage getPage() {
        return this.page;
    }

    public void setPage(PDPage pDPage) {
        this.page = pDPage;
    }

    public PDDocument getTemplate() {
        return this.template;
    }

    public void setTemplate(PDDocument pDDocument) {
        this.template = pDDocument;
    }

    public PDAcroForm getAcroForm() {
        return this.acroForm;
    }

    public void setAcroForm(PDAcroForm pDAcroForm) {
        this.acroForm = pDAcroForm;
    }

    public PDSignatureField getSignatureField() {
        return this.signatureField;
    }

    public void setSignatureField(PDSignatureField pDSignatureField) {
        this.signatureField = pDSignatureField;
    }

    public PDSignature getPdSignature() {
        return this.pdSignature;
    }

    public void setPdSignature(PDSignature pDSignature) {
        this.pdSignature = pDSignature;
    }

    public COSDictionary getAcroFormDictionary() {
        return this.acroFormDictionary;
    }

    public void setAcroFormDictionary(COSDictionary cOSDictionary) {
        this.acroFormDictionary = cOSDictionary;
    }

    public PDRectangle getSingatureRectangle() {
        return this.singatureRectangle;
    }

    public void setSignatureRectangle(PDRectangle pDRectangle) {
        this.singatureRectangle = pDRectangle;
    }

    public AffineTransform getAffineTransform() {
        return this.affineTransform;
    }

    public void setAffineTransform(AffineTransform affineTransform) {
        this.affineTransform = affineTransform;
    }

    public COSArray getProcSet() {
        return this.procSet;
    }

    public void setProcSet(COSArray cOSArray) {
        this.procSet = cOSArray;
    }

    public PDImageXObject getImage() {
        return this.image;
    }

    public void setImage(PDImageXObject pDImageXObject) {
        this.image = pDImageXObject;
    }

    public PDRectangle getFormaterRectangle() {
        return this.formaterRectangle;
    }

    public void setFormaterRectangle(PDRectangle pDRectangle) {
        this.formaterRectangle = pDRectangle;
    }

    public PDStream getHolderFormStream() {
        return this.holderFormStream;
    }

    public void setHolderFormStream(PDStream pDStream) {
        this.holderFormStream = pDStream;
    }

    public PDFormXObject getHolderForm() {
        return this.holderForm;
    }

    public void setHolderForm(PDFormXObject pDFormXObject) {
        this.holderForm = pDFormXObject;
    }

    public PDResources getHolderFormResources() {
        return this.holderFormResources;
    }

    public void setHolderFormResources(PDResources pDResources) {
        this.holderFormResources = pDResources;
    }

    public PDAppearanceDictionary getAppearanceDictionary() {
        return this.appearanceDictionary;
    }

    public void setAppearanceDictionary(PDAppearanceDictionary pDAppearanceDictionary) {
        this.appearanceDictionary = pDAppearanceDictionary;
    }

    public PDStream getInnterFormStream() {
        return this.innterFormStream;
    }

    public void setInnterFormStream(PDStream pDStream) {
        this.innterFormStream = pDStream;
    }

    public PDResources getInnerFormResources() {
        return this.innerFormResources;
    }

    public void setInnerFormResources(PDResources pDResources) {
        this.innerFormResources = pDResources;
    }

    public PDFormXObject getInnerForm() {
        return this.innerForm;
    }

    public void setInnerForm(PDFormXObject pDFormXObject) {
        this.innerForm = pDFormXObject;
    }

    public COSName getInnerFormName() {
        return this.innerFormName;
    }

    public void setInnerFormName(COSName cOSName) {
        this.innerFormName = cOSName;
    }

    public PDStream getImageFormStream() {
        return this.imageFormStream;
    }

    public void setImageFormStream(PDStream pDStream) {
        this.imageFormStream = pDStream;
    }

    public PDResources getImageFormResources() {
        return this.imageFormResources;
    }

    public void setImageFormResources(PDResources pDResources) {
        this.imageFormResources = pDResources;
    }

    public PDFormXObject getImageForm() {
        return this.imageForm;
    }

    public void setImageForm(PDFormXObject pDFormXObject) {
        this.imageForm = pDFormXObject;
    }

    public COSName getImageFormName() {
        return this.imageFormName;
    }

    public void setImageFormName(COSName cOSName) {
        this.imageFormName = cOSName;
    }

    public COSName getImageName() {
        return this.imageName;
    }

    public void setImageName(COSName cOSName) {
        this.imageName = cOSName;
    }

    public COSDocument getVisualSignature() {
        return this.visualSignature;
    }

    public void setVisualSignature(COSDocument cOSDocument) {
        this.visualSignature = cOSDocument;
    }

    public List<PDFieldTreeNode> getAcroFormFields() {
        return this.acroFormFields;
    }

    public void setAcroFormFields(List<PDFieldTreeNode> list) {
        this.acroFormFields = list;
    }

    public ByteArrayInputStream getTemplateAppearanceStream() throws IOException {
        COSDocument visualSignature = getVisualSignature();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new COSWriter(byteArrayOutputStream).write(visualSignature);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        getTemplate().close();
        return byteArrayInputStream;
    }

    public COSDictionary getWidgetDictionary() {
        return this.widgetDictionary;
    }

    public void setWidgetDictionary(COSDictionary cOSDictionary) {
        this.widgetDictionary = cOSDictionary;
    }
}
