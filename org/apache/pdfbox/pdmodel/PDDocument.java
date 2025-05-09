package org.apache.pdfbox.pdmodel;

import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.BaseParser;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdfwriter.COSWriter;
import org.apache.pdfbox.pdmodel.common.COSArrayList;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureOptions;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode;
import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;

/* loaded from: classes5.dex */
public class PDDocument implements Closeable {
    private boolean allSecurityToBeRemoved;
    private final COSDocument document;
    private PDDocumentCatalog documentCatalog;
    private Long documentId;
    private PDDocumentInformation documentInformation;
    private final Set<PDFont> fontsToSubset;
    private File incrementalFile;
    private final BaseParser parser;
    private SignatureInterface signInterface;

    public PDDocument() {
        this.fontsToSubset = new HashSet();
        COSDocument cOSDocument = new COSDocument();
        this.document = cOSDocument;
        this.parser = null;
        COSDictionary cOSDictionary = new COSDictionary();
        cOSDocument.setTrailer(cOSDictionary);
        COSDictionary cOSDictionary2 = new COSDictionary();
        cOSDictionary.setItem(COSName.ROOT, (COSBase) cOSDictionary2);
        cOSDictionary2.setItem(COSName.TYPE, (COSBase) COSName.CATALOG);
        cOSDictionary2.setItem(COSName.VERSION, (COSBase) COSName.getPDFName("1.4"));
        COSDictionary cOSDictionary3 = new COSDictionary();
        cOSDictionary2.setItem(COSName.PAGES, (COSBase) cOSDictionary3);
        cOSDictionary3.setItem(COSName.TYPE, (COSBase) COSName.PAGES);
        cOSDictionary3.setItem(COSName.KIDS, (COSBase) new COSArray());
        cOSDictionary3.setItem(COSName.COUNT, (COSBase) COSInteger.ZERO);
    }

    public void addPage(PDPage pDPage) {
        getPages().add(pDPage);
    }

    public void addSignature(PDSignature pDSignature, SignatureInterface signatureInterface) throws IOException {
        addSignature(pDSignature, signatureInterface, new SignatureOptions());
    }

    public void addSignature(PDSignature pDSignature, SignatureInterface signatureInterface, SignatureOptions signatureOptions) throws IOException {
        boolean z;
        PDSignatureField pDSignatureField;
        PDSignature signature;
        int preferedSignatureSize = signatureOptions.getPreferedSignatureSize();
        if (preferedSignatureSize > 0) {
            pDSignature.setContents(new byte[preferedSignatureSize]);
        } else {
            pDSignature.setContents(new byte[9472]);
        }
        pDSignature.setByteRange(new int[]{0, 1000000000, 1000000000, 1000000000});
        this.signInterface = signatureInterface;
        PDDocumentCatalog documentCatalog = getDocumentCatalog();
        int count = documentCatalog.getPages().getCount();
        if (count == 0) {
            throw new IllegalStateException("Cannot sign an empty document");
        }
        PDPage pDPage = documentCatalog.getPages().get(Math.min(Math.max(signatureOptions.getPage(), 0), count - 1));
        PDAcroForm acroForm = documentCatalog.getAcroForm();
        documentCatalog.getCOSObject().setNeedToBeUpdated(true);
        if (acroForm == null) {
            acroForm = new PDAcroForm(this);
            documentCatalog.setAcroForm(acroForm);
        } else {
            acroForm.getDictionary().setNeedToBeUpdated(true);
        }
        List<PDAnnotation> annotations = pDPage.getAnnotations();
        List<PDFieldTreeNode> fields = acroForm.getFields();
        if (fields == null) {
            fields = new ArrayList<>();
            acroForm.setFields(fields);
        }
        PDSignatureField pDSignatureField2 = null;
        for (PDFieldTreeNode pDFieldTreeNode : fields) {
            if ((pDFieldTreeNode instanceof PDSignatureField) && (signature = (pDSignatureField = (PDSignatureField) pDFieldTreeNode).getSignature()) != null && signature.getDictionary().equals(pDSignature.getDictionary())) {
                pDSignatureField2 = pDSignatureField;
            }
        }
        if (pDSignatureField2 == null) {
            pDSignatureField2 = new PDSignatureField(acroForm);
            pDSignatureField2.setSignature(pDSignature);
            pDSignatureField2.getWidget().setPage(pDPage);
        }
        List<PDFieldTreeNode> fields2 = acroForm.getFields();
        acroForm.getDictionary().setDirect(true);
        acroForm.setSignaturesExist(true);
        acroForm.setAppendOnly(true);
        Iterator<PDFieldTreeNode> it = fields2.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            PDFieldTreeNode next = it.next();
            if ((next instanceof PDSignatureField) && ((PDSignatureField) next).getCOSObject().equals(pDSignatureField2.getCOSObject())) {
                pDSignatureField2.getDictionary().setNeedToBeUpdated(true);
                z = true;
                break;
            }
        }
        if (!z) {
            fields2.add(pDSignatureField2);
        }
        COSDocument visualSignature = signatureOptions.getVisualSignature();
        if (visualSignature == null) {
            pDSignatureField2.getWidget().setRectangle(new PDRectangle());
            acroForm.setDefaultResources(null);
            PDAppearanceDictionary pDAppearanceDictionary = new PDAppearanceDictionary();
            COSStream createCOSStream = getDocument().createCOSStream();
            createCOSStream.createUnfilteredStream();
            PDAppearanceStream pDAppearanceStream = new PDAppearanceStream(createCOSStream);
            COSDictionary cOSDictionary = (COSDictionary) pDAppearanceStream.getCOSObject();
            cOSDictionary.setItem(COSName.SUBTYPE, (COSBase) COSName.FORM);
            cOSDictionary.setItem(COSName.BBOX, new PDRectangle());
            pDAppearanceDictionary.setNormalAppearance(pDAppearanceStream);
            pDAppearanceDictionary.getCOSObject().setDirect(true);
            pDSignatureField2.getWidget().setAppearance(pDAppearanceDictionary);
        } else {
            List<COSObject> objects = visualSignature.getObjects();
            COSDictionary dictionary = acroForm.getDictionary();
            boolean z2 = true;
            boolean z3 = true;
            for (COSObject cOSObject : objects) {
                if (!z2 && !z3) {
                    break;
                }
                COSBase object = cOSObject.getObject();
                if (object instanceof COSDictionary) {
                    COSDictionary cOSDictionary2 = (COSDictionary) object;
                    COSBase item = cOSDictionary2.getItem(COSName.FT);
                    COSBase item2 = cOSDictionary2.getItem(COSName.TYPE);
                    COSBase item3 = cOSDictionary2.getItem(COSName.AP);
                    if (z2 && COSName.ANNOT.equals(item2)) {
                        pDSignatureField2.getWidget().setRectangle(new PDRectangle((COSArray) cOSDictionary2.getItem(COSName.RECT)));
                        z2 = false;
                    }
                    if (z3 && COSName.SIG.equals(item) && item3 != null) {
                        PDAppearanceDictionary pDAppearanceDictionary2 = new PDAppearanceDictionary((COSDictionary) cOSDictionary2.getDictionaryObject(COSName.AP));
                        pDAppearanceDictionary2.getCOSObject().setDirect(true);
                        pDSignatureField2.getWidget().setAppearance(pDAppearanceDictionary2);
                        COSDictionary cOSDictionary3 = (COSDictionary) cOSDictionary2.getItem(COSName.DR);
                        if (cOSDictionary3 != null) {
                            cOSDictionary3.setDirect(true);
                            cOSDictionary3.setNeedToBeUpdated(true);
                            dictionary.setItem(COSName.DR, (COSBase) cOSDictionary3);
                        }
                        z3 = false;
                    }
                }
            }
            if (z2 || z3) {
                throw new IllegalArgumentException("Template is missing required objects");
            }
        }
        if (!(annotations instanceof COSArrayList) || !(fields2 instanceof COSArrayList) || !((COSArrayList) annotations).toList().equals(((COSArrayList) fields2).toList()) || !z) {
            annotations.add(pDSignatureField2.getWidget());
        }
        pDPage.getCOSObject().setNeedToBeUpdated(true);
    }

    public void addSignatureField(List<PDSignatureField> list, SignatureInterface signatureInterface, SignatureOptions signatureOptions) throws IOException {
        PDDocumentCatalog documentCatalog = getDocumentCatalog();
        documentCatalog.getCOSObject().setNeedToBeUpdated(true);
        PDAcroForm acroForm = documentCatalog.getAcroForm();
        if (acroForm == null) {
            acroForm = new PDAcroForm(this);
            documentCatalog.setAcroForm(acroForm);
        }
        COSDictionary dictionary = acroForm.getDictionary();
        dictionary.setDirect(true);
        dictionary.setNeedToBeUpdated(true);
        if (!acroForm.isSignaturesExist()) {
            acroForm.setSignaturesExist(true);
        }
        List<PDFieldTreeNode> fields = acroForm.getFields();
        for (PDSignatureField pDSignatureField : list) {
            pDSignatureField.getDictionary().setNeedToBeUpdated(true);
            boolean z = false;
            Iterator<PDFieldTreeNode> it = fields.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                PDFieldTreeNode next = it.next();
                if ((next instanceof PDSignatureField) && next.getCOSObject().equals(pDSignatureField.getCOSObject())) {
                    pDSignatureField.getDictionary().setNeedToBeUpdated(true);
                    z = true;
                    break;
                }
            }
            if (!z) {
                fields.add(pDSignatureField);
            }
            if (pDSignatureField.getSignature() != null) {
                pDSignatureField.getDictionary().setNeedToBeUpdated(true);
                addSignature(pDSignatureField.getSignature(), signatureInterface, signatureOptions);
            }
        }
    }

    public void removePage(PDPage pDPage) {
        getPages().remove(pDPage);
    }

    public void removePage(int i) {
        getPages().remove(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public PDPage importPage(PDPage pDPage) throws IOException {
        OutputStream outputStream;
        PDPage pDPage2 = new PDPage(new COSDictionary(pDPage.getCOSObject()));
        InputStream inputStream = null;
        OutputStream outputStream2 = null;
        inputStream = null;
        try {
            PDStream stream = pDPage.getStream();
            if (stream != null) {
                PDStream pDStream = new PDStream(this.document.createCOSStream());
                pDStream.addCompression();
                pDPage2.setContents(pDStream);
                InputStream createInputStream = stream.createInputStream();
                try {
                    outputStream2 = pDStream.createOutputStream();
                    IOUtils.copy(createInputStream, outputStream2);
                    inputStream = createInputStream;
                    outputStream = outputStream2;
                } catch (Throwable th) {
                    th = th;
                    OutputStream outputStream3 = outputStream2;
                    inputStream = createInputStream;
                    outputStream = outputStream3;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    throw th;
                }
            } else {
                outputStream = null;
            }
            try {
                addPage(pDPage2);
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                return pDPage2;
            } catch (Throwable th2) {
                th = th2;
                if (inputStream != null) {
                }
                if (outputStream != null) {
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            outputStream = null;
        }
    }

    public PDDocument(COSDocument cOSDocument) {
        this(cOSDocument, null);
    }

    public PDDocument(COSDocument cOSDocument, BaseParser baseParser) {
        this(cOSDocument, baseParser, null);
    }

    public PDDocument(COSDocument cOSDocument, BaseParser baseParser, Void r3) {
        this.fontsToSubset = new HashSet();
        this.document = cOSDocument;
        this.parser = baseParser;
    }

    public COSDocument getDocument() {
        return this.document;
    }

    public PDDocumentInformation getDocumentInformation() {
        if (this.documentInformation == null) {
            COSDictionary trailer = this.document.getTrailer();
            COSDictionary cOSDictionary = (COSDictionary) trailer.getDictionaryObject(COSName.INFO);
            if (cOSDictionary == null) {
                cOSDictionary = new COSDictionary();
                trailer.setItem(COSName.INFO, (COSBase) cOSDictionary);
            }
            this.documentInformation = new PDDocumentInformation(cOSDictionary);
        }
        return this.documentInformation;
    }

    public void setDocumentInformation(PDDocumentInformation pDDocumentInformation) {
        this.documentInformation = pDDocumentInformation;
        this.document.getTrailer().setItem(COSName.INFO, (COSBase) pDDocumentInformation.getDictionary());
    }

    public PDDocumentCatalog getDocumentCatalog() {
        if (this.documentCatalog == null) {
            COSBase dictionaryObject = this.document.getTrailer().getDictionaryObject(COSName.ROOT);
            if (dictionaryObject instanceof COSDictionary) {
                this.documentCatalog = new PDDocumentCatalog(this, (COSDictionary) dictionaryObject);
            } else {
                this.documentCatalog = new PDDocumentCatalog(this);
            }
        }
        return this.documentCatalog;
    }

    public boolean isEncrypted() {
        return this.document.isEncrypted();
    }

    public PDSignature getLastSignatureDictionary() throws IOException {
        List<PDSignature> signatureDictionaries = getSignatureDictionaries();
        int size = signatureDictionaries.size();
        if (size > 0) {
            return signatureDictionaries.get(size - 1);
        }
        return null;
    }

    public List<PDSignatureField> getSignatureFields() throws IOException {
        LinkedList linkedList = new LinkedList();
        PDAcroForm acroForm = getDocumentCatalog().getAcroForm();
        if (acroForm != null) {
            Iterator<COSDictionary> it = this.document.getSignatureFields(false).iterator();
            while (it.hasNext()) {
                linkedList.add(new PDSignatureField(acroForm, it.next(), null));
            }
        }
        return linkedList;
    }

    public List<PDSignature> getSignatureDictionaries() throws IOException {
        List<COSDictionary> signatureDictionaries = this.document.getSignatureDictionaries();
        LinkedList linkedList = new LinkedList();
        Iterator<COSDictionary> it = signatureDictionaries.iterator();
        while (it.hasNext()) {
            linkedList.add(new PDSignature(it.next()));
        }
        return linkedList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<PDFont> getFontsToSubset() {
        return this.fontsToSubset;
    }

    public static PDDocument load(File file) throws IOException {
        return load(file, "", false);
    }

    public static PDDocument load(File file, boolean z) throws IOException {
        return load(file, "", (InputStream) null, (String) null, z);
    }

    public static PDDocument load(File file, String str) throws IOException {
        return load(file, str, (InputStream) null, (String) null, false);
    }

    public static PDDocument load(File file, String str, boolean z) throws IOException {
        return load(file, str, (InputStream) null, (String) null, z);
    }

    public static PDDocument load(File file, String str, InputStream inputStream, String str2) throws IOException {
        return load(file, str, inputStream, str2, false);
    }

    public static PDDocument load(File file, String str, InputStream inputStream, String str2, boolean z) throws IOException {
        PDFParser pDFParser = new PDFParser(file, str, inputStream, str2, z);
        pDFParser.parse();
        PDDocument pDDocument = pDFParser.getPDDocument();
        pDDocument.incrementalFile = file;
        return pDDocument;
    }

    public static PDDocument load(InputStream inputStream) throws IOException {
        return load(inputStream, "", (InputStream) null, (String) null, false);
    }

    public static PDDocument load(InputStream inputStream, boolean z) throws IOException {
        return load(inputStream, "", (InputStream) null, (String) null, z);
    }

    public static PDDocument load(InputStream inputStream, String str) throws IOException {
        return load(inputStream, str, false);
    }

    public static PDDocument load(InputStream inputStream, String str, boolean z) throws IOException {
        return load(inputStream, str, (InputStream) null, (String) null, false);
    }

    public static PDDocument load(InputStream inputStream, String str, InputStream inputStream2, String str2) throws IOException {
        return load(inputStream, str, inputStream2, str2, false);
    }

    public static PDDocument load(InputStream inputStream, String str, InputStream inputStream2, String str2, boolean z) throws IOException {
        PDFParser pDFParser = new PDFParser(inputStream, str, inputStream2, str2, z);
        pDFParser.parse();
        return pDFParser.getPDDocument();
    }

    public void save(String str) throws IOException {
        save(new File(str));
    }

    public void save(File file) throws IOException {
        save(new FileOutputStream(file));
    }

    public void save(OutputStream outputStream) throws IOException {
        if (this.document.isClosed()) {
            throw new IOException("Cannot save a document which has been closed");
        }
        Iterator<PDFont> it = this.fontsToSubset.iterator();
        while (it.hasNext()) {
            it.next().subset();
        }
        this.fontsToSubset.clear();
        COSWriter cOSWriter = new COSWriter(outputStream);
        try {
            cOSWriter.write(this);
            cOSWriter.close();
        } finally {
            cOSWriter.close();
        }
    }

    public void saveIncremental(OutputStream outputStream) throws IOException {
        COSWriter cOSWriter = null;
        try {
            COSWriter cOSWriter2 = new COSWriter(outputStream, new RandomAccessBufferedFileInputStream(this.incrementalFile));
            try {
                cOSWriter2.write(this, this.signInterface);
                cOSWriter2.close();
                cOSWriter2.close();
            } catch (Throwable th) {
                th = th;
                cOSWriter = cOSWriter2;
                if (cOSWriter != null) {
                    cOSWriter.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public PDPage getPage(int i) {
        return getDocumentCatalog().getPages().get(i);
    }

    public PDPageTree getPages() {
        return getDocumentCatalog().getPages();
    }

    public int getNumberOfPages() {
        return getDocumentCatalog().getPages().getCount();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.document.isClosed()) {
            return;
        }
        this.document.close();
        BaseParser baseParser = this.parser;
        if (baseParser != null) {
            baseParser.close();
        }
    }

    public boolean isAllSecurityToBeRemoved() {
        return this.allSecurityToBeRemoved;
    }

    public void setAllSecurityToBeRemoved(boolean z) {
        this.allSecurityToBeRemoved = z;
    }

    public Long getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(Long l) {
        this.documentId = l;
    }

    public float getVersion() {
        float version = getDocument().getVersion();
        if (version < 1.4f) {
            return version;
        }
        String version2 = getDocumentCatalog().getVersion();
        float f = -1.0f;
        if (version2 != null) {
            try {
                f = Float.parseFloat(version2);
            } catch (NumberFormatException e) {
                Log.e("PdfBoxAndroid", "Can't extract the version number of the document catalog.", e);
            }
        }
        return Math.max(f, version);
    }

    public void setVersion(float f) {
        float version = getVersion();
        if (f == version) {
            return;
        }
        if (f < version) {
            Log.e("PdfBoxAndroid", "It's not allowed to downgrade the version of a pdf.");
        } else if (getDocument().getVersion() >= 1.4f) {
            getDocumentCatalog().setVersion(Float.toString(f));
        } else {
            getDocument().setVersion(f);
        }
    }
}
