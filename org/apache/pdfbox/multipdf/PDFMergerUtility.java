package org.apache.pdfbox.multipdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.PageMode;
import org.apache.pdfbox.pdmodel.common.COSArrayList;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDNumberTreeNode;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDMarkInfo;
import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDStructureTreeRoot;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDFieldTreeNode;

/* loaded from: classes5.dex */
public class PDFMergerUtility {
    private static final String STRUCTURETYPE_DOCUMENT = "Document";
    private String destinationFileName;
    private OutputStream destinationStream;
    private boolean ignoreAcroFormErrors = false;
    private int nextFieldNum = 1;
    private final List<InputStream> sources = new ArrayList();

    public String getDestinationFileName() {
        return this.destinationFileName;
    }

    public void setDestinationFileName(String str) {
        this.destinationFileName = str;
    }

    public OutputStream getDestinationStream() {
        return this.destinationStream;
    }

    public void setDestinationStream(OutputStream outputStream) {
        this.destinationStream = outputStream;
    }

    public void addSource(String str) throws FileNotFoundException {
        this.sources.add(new FileInputStream(new File(str)));
    }

    public void addSource(File file) throws FileNotFoundException {
        this.sources.add(new FileInputStream(file));
    }

    public void addSource(InputStream inputStream) {
        this.sources.add(inputStream);
    }

    public void addSources(List<InputStream> list) {
        this.sources.addAll(list);
    }

    public void mergeDocuments() throws IOException {
        PDDocument pDDocument;
        Throwable th;
        List<InputStream> list = this.sources;
        if (list == null || list.size() <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        try {
            Iterator<InputStream> it = this.sources.iterator();
            pDDocument = new PDDocument();
            while (it.hasNext()) {
                try {
                    PDDocument load = PDDocument.load(it.next());
                    arrayList.add(load);
                    appendDocument(pDDocument, load);
                } catch (Throwable th2) {
                    th = th2;
                    if (pDDocument != null) {
                        pDDocument.close();
                    }
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        ((PDDocument) it2.next()).close();
                    }
                    throw th;
                }
            }
            OutputStream outputStream = this.destinationStream;
            if (outputStream == null) {
                pDDocument.save(this.destinationFileName);
            } else {
                pDDocument.save(outputStream);
            }
            pDDocument.close();
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                ((PDDocument) it3.next()).close();
            }
        } catch (Throwable th3) {
            pDDocument = null;
            th = th3;
        }
    }

    public void appendDocument(PDDocument pDDocument, PDDocument pDDocument2) throws IOException {
        boolean z;
        COSDictionary cOSDictionary;
        COSArray cOSArray;
        Iterator<PDPage> it;
        COSArray cOSArray2;
        PDNumberTreeNode parentTree;
        COSArray cOSArray3;
        if (pDDocument.isEncrypted()) {
            throw new IOException("Error: destination PDF is encrypted, can't append encrypted PDF documents.");
        }
        if (pDDocument2.isEncrypted()) {
            throw new IOException("Error: source PDF is encrypted, can't append encrypted PDF documents.");
        }
        PDDocumentCatalog documentCatalog = pDDocument.getDocumentCatalog();
        PDDocumentCatalog documentCatalog2 = pDDocument2.getDocumentCatalog();
        if (isDynamicXfa(documentCatalog2.getAcroForm())) {
            throw new IOException("Error: can't merge source document containing dynamic XFA form content.");
        }
        pDDocument.getDocumentInformation().getDictionary().mergeInto(pDDocument2.getDocumentInformation().getDictionary());
        float version = pDDocument.getVersion();
        float version2 = pDDocument2.getVersion();
        if (version < version2) {
            pDDocument.setVersion(version2);
        }
        if (documentCatalog.getOpenAction() == null) {
            documentCatalog.setOpenAction(documentCatalog2.getOpenAction());
        }
        PDFCloneUtility pDFCloneUtility = new PDFCloneUtility(pDDocument);
        try {
            PDAcroForm acroForm = documentCatalog.getAcroForm();
            PDAcroForm acroForm2 = documentCatalog2.getAcroForm();
            if (acroForm == null && acroForm2 != null) {
                documentCatalog.getCOSObject().setItem(COSName.ACRO_FORM, pDFCloneUtility.cloneForNewDocument(acroForm2.getDictionary()));
            } else if (acroForm2 != null) {
                mergeAcroForm(pDFCloneUtility, acroForm, acroForm2);
            }
        } catch (IOException e) {
            if (!this.ignoreAcroFormErrors) {
                throw new IOException(e);
            }
        }
        COSArray cOSArray4 = (COSArray) documentCatalog.getCOSObject().getDictionaryObject(COSName.THREADS);
        COSArray cOSArray5 = (COSArray) pDFCloneUtility.cloneForNewDocument(documentCatalog.getCOSObject().getDictionaryObject(COSName.THREADS));
        if (cOSArray4 == null) {
            documentCatalog.getCOSObject().setItem(COSName.THREADS, (COSBase) cOSArray5);
        } else {
            cOSArray4.addAll(cOSArray5);
        }
        COSObjectable names = documentCatalog.getNames();
        COSObjectable names2 = documentCatalog2.getNames();
        if (names2 != null) {
            if (names == null) {
                documentCatalog.getCOSObject().setItem(COSName.NAMES, pDFCloneUtility.cloneForNewDocument(names2));
            } else {
                pDFCloneUtility.cloneMerge(names2, names);
            }
        }
        PDDocumentOutline documentOutline = documentCatalog.getDocumentOutline();
        PDDocumentOutline documentOutline2 = documentCatalog2.getDocumentOutline();
        if (documentOutline2 != null) {
            if (documentOutline == null) {
                documentCatalog.setDocumentOutline(new PDDocumentOutline((COSDictionary) pDFCloneUtility.cloneForNewDocument(documentOutline2)));
            } else {
                Object firstChild = documentOutline2.getFirstChild();
                if (firstChild != null) {
                    documentOutline.addLast(new PDOutlineItem((COSDictionary) pDFCloneUtility.cloneForNewDocument(firstChild)));
                }
            }
        }
        PageMode pageMode = documentCatalog.getPageMode();
        PageMode pageMode2 = documentCatalog2.getPageMode();
        if (pageMode == null) {
            documentCatalog.setPageMode(pageMode2);
        }
        COSDictionary cOSDictionary2 = (COSDictionary) documentCatalog.getCOSObject().getDictionaryObject(COSName.PAGE_LABELS);
        COSDictionary cOSDictionary3 = (COSDictionary) documentCatalog2.getCOSObject().getDictionaryObject(COSName.PAGE_LABELS);
        if (cOSDictionary3 != null) {
            int numberOfPages = pDDocument.getNumberOfPages();
            if (cOSDictionary2 == null) {
                COSDictionary cOSDictionary4 = new COSDictionary();
                cOSArray3 = new COSArray();
                cOSDictionary4.setItem(COSName.NUMS, (COSBase) cOSArray3);
                documentCatalog.getCOSObject().setItem(COSName.PAGE_LABELS, (COSBase) cOSDictionary4);
            } else {
                cOSArray3 = (COSArray) cOSDictionary2.getDictionaryObject(COSName.NUMS);
            }
            COSArray cOSArray6 = (COSArray) cOSDictionary3.getDictionaryObject(COSName.NUMS);
            if (cOSArray6 != null) {
                for (int i = 0; i < cOSArray6.size(); i += 2) {
                    cOSArray3.add((COSBase) COSInteger.get(((COSNumber) cOSArray6.getObject(i)).intValue() + numberOfPages));
                    cOSArray3.add(pDFCloneUtility.cloneForNewDocument(cOSArray6.getObject(i + 1)));
                }
            }
        }
        COSStream cOSStream = (COSStream) documentCatalog.getCOSObject().getDictionaryObject(COSName.METADATA);
        COSStream cOSStream2 = (COSStream) documentCatalog2.getCOSObject().getDictionaryObject(COSName.METADATA);
        if (cOSStream == null && cOSStream2 != null) {
            PDStream pDStream = new PDStream(pDDocument, cOSStream2.getUnfilteredStream(), false);
            pDStream.getStream().mergeInto(cOSStream2);
            pDStream.addCompression();
            documentCatalog.getCOSObject().setItem(COSName.METADATA, pDStream);
        }
        int i2 = -1;
        PDMarkInfo markInfo = documentCatalog.getMarkInfo();
        PDStructureTreeRoot structureTreeRoot = documentCatalog.getStructureTreeRoot();
        documentCatalog2.getMarkInfo();
        PDStructureTreeRoot structureTreeRoot2 = documentCatalog2.getStructureTreeRoot();
        COSArray cOSArray7 = null;
        if (structureTreeRoot != null) {
            PDNumberTreeNode parentTree2 = structureTreeRoot.getParentTree();
            int parentTreeNextKey = structureTreeRoot.getParentTreeNextKey();
            if (parentTree2 != null) {
                COSDictionary cOSDictionary5 = parentTree2.getCOSDictionary();
                cOSArray = (COSArray) cOSDictionary5.getDictionaryObject(COSName.NUMS);
                if (cOSArray != null) {
                    if (parentTreeNextKey < 0) {
                        parentTreeNextKey = cOSArray.size() / 2;
                    }
                    if (parentTreeNextKey > 0 && structureTreeRoot2 != null && (parentTree = structureTreeRoot2.getParentTree()) != null) {
                        cOSArray2 = (COSArray) parentTree.getCOSDictionary().getDictionaryObject(COSName.NUMS);
                        z = cOSArray2 != null;
                        int i3 = parentTreeNextKey;
                        cOSDictionary = cOSDictionary5;
                        i2 = i3;
                    }
                }
                z = false;
                cOSArray2 = null;
                int i32 = parentTreeNextKey;
                cOSDictionary = cOSDictionary5;
                i2 = i32;
            } else {
                z = false;
                cOSArray = null;
                cOSArray2 = null;
                i2 = parentTreeNextKey;
                cOSDictionary = null;
            }
            if (markInfo != null && markInfo.isMarked() && !z) {
                markInfo.setMarked(false);
            }
            if (!z) {
                documentCatalog.setStructureTreeRoot(null);
            }
            cOSArray7 = cOSArray2;
        } else {
            z = false;
            cOSDictionary = null;
            cOSArray = null;
        }
        Map<COSDictionary, COSDictionary> hashMap = new HashMap<>();
        Iterator<PDPage> it2 = documentCatalog2.getPages().iterator();
        while (it2.hasNext()) {
            PDPage next = it2.next();
            PDPage pDPage = new PDPage((COSDictionary) pDFCloneUtility.cloneForNewDocument(next.getCOSObject()));
            pDPage.setCropBox(next.getCropBox());
            pDPage.setMediaBox(next.getMediaBox());
            pDPage.setRotation(next.getRotation());
            pDPage.setResources(new PDResources((COSDictionary) pDFCloneUtility.cloneForNewDocument(next.getResources())));
            if (z) {
                updateStructParentEntries(pDPage, i2);
                hashMap.put(next.getCOSObject(), pDPage.getCOSObject());
                List<PDAnnotation> annotations = next.getAnnotations();
                List<PDAnnotation> annotations2 = pDPage.getAnnotations();
                it = it2;
                int i4 = 0;
                while (i4 < annotations.size()) {
                    hashMap.put(annotations.get(i4).getDictionary(), annotations2.get(i4).getDictionary());
                    i4++;
                    pDFCloneUtility = pDFCloneUtility;
                }
            } else {
                it = it2;
            }
            pDDocument.addPage(pDPage);
            it2 = it;
            pDFCloneUtility = pDFCloneUtility;
        }
        if (z) {
            updatePageReferences(cOSArray7, hashMap);
            for (int i5 = 0; i5 < cOSArray7.size() / 2; i5++) {
                cOSArray.add((COSBase) COSInteger.get(i2 + i5));
                cOSArray.add(cOSArray7.getObject((i5 * 2) + 1));
            }
            int size = i2 + (cOSArray7.size() / 2);
            cOSDictionary.setItem(COSName.NUMS, (COSBase) cOSArray);
            structureTreeRoot.setParentTree(new PDNumberTreeNode(cOSDictionary, COSBase.class));
            structureTreeRoot.setParentTreeNextKey(size);
            COSDictionary cOSDictionary6 = new COSDictionary();
            COSArray cOSArray8 = new COSArray();
            COSArray kArray = structureTreeRoot.getKArray();
            COSArray kArray2 = structureTreeRoot2.getKArray();
            if (kArray != null && kArray2 != null) {
                updateParentEntry(kArray, cOSDictionary6);
                cOSArray8.addAll(kArray);
                if (z) {
                    updateParentEntry(kArray2, cOSDictionary6);
                }
                cOSArray8.addAll(kArray2);
            }
            cOSDictionary6.setItem(COSName.K, (COSBase) cOSArray8);
            cOSDictionary6.setItem(COSName.P, structureTreeRoot);
            cOSDictionary6.setItem(COSName.S, new COSString("Document"));
            structureTreeRoot.setK(cOSDictionary6);
        }
    }

    private void mergeAcroForm(PDFCloneUtility pDFCloneUtility, PDAcroForm pDAcroForm, PDAcroForm pDAcroForm2) throws IOException {
        List<PDFieldTreeNode> fields = pDAcroForm.getFields();
        List<PDFieldTreeNode> fields2 = pDAcroForm2.getFields();
        if (fields2 != null) {
            if (fields == null) {
                fields = new COSArrayList<>();
                pDAcroForm.setFields(fields);
            }
            Iterator<PDFieldTreeNode> it = fields2.iterator();
            while (it.hasNext()) {
                PDFieldTreeNode createField = PDFieldTreeNode.createField(pDAcroForm, (COSDictionary) pDFCloneUtility.cloneForNewDocument(it.next().getDictionary()), null);
                if (pDAcroForm.getField(createField.getFullyQualifiedName()) != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("dummyFieldName");
                    int i = this.nextFieldNum;
                    this.nextFieldNum = i + 1;
                    sb.append(i);
                    createField.setPartialName(sb.toString());
                }
                fields.add(createField);
            }
        }
    }

    public boolean isIgnoreAcroFormErrors() {
        return this.ignoreAcroFormErrors;
    }

    public void setIgnoreAcroFormErrors(boolean z) {
        this.ignoreAcroFormErrors = z;
    }

    private void updatePageReferences(COSDictionary cOSDictionary, Map<COSDictionary, COSDictionary> map) {
        COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.PG);
        if ((dictionaryObject instanceof COSDictionary) && map.containsKey(dictionaryObject)) {
            cOSDictionary.setItem(COSName.PG, (COSBase) map.get(dictionaryObject));
        }
        COSBase dictionaryObject2 = cOSDictionary.getDictionaryObject(COSName.OBJ);
        if ((dictionaryObject2 instanceof COSDictionary) && map.containsKey(dictionaryObject)) {
            cOSDictionary.setItem(COSName.OBJ, (COSBase) map.get(dictionaryObject2));
        }
        COSBase dictionaryObject3 = cOSDictionary.getDictionaryObject(COSName.K);
        if (dictionaryObject3 instanceof COSArray) {
            updatePageReferences((COSArray) dictionaryObject3, map);
        } else if (dictionaryObject3 instanceof COSDictionary) {
            updatePageReferences((COSDictionary) dictionaryObject3, map);
        }
    }

    private void updatePageReferences(COSArray cOSArray, Map<COSDictionary, COSDictionary> map) {
        for (int i = 0; i < cOSArray.size(); i++) {
            COSBase object = cOSArray.getObject(i);
            if (object instanceof COSArray) {
                updatePageReferences((COSArray) object, map);
            } else if (object instanceof COSDictionary) {
                updatePageReferences((COSDictionary) object, map);
            }
        }
    }

    private void updateParentEntry(COSArray cOSArray, COSDictionary cOSDictionary) {
        for (int i = 0; i < cOSArray.size(); i++) {
            COSBase object = cOSArray.getObject(i);
            if (object instanceof COSDictionary) {
                COSDictionary cOSDictionary2 = (COSDictionary) object;
                if (cOSDictionary2.getDictionaryObject(COSName.P) != null) {
                    cOSDictionary2.setItem(COSName.P, (COSBase) cOSDictionary);
                }
            }
        }
    }

    private void updateStructParentEntries(PDPage pDPage, int i) throws IOException {
        pDPage.setStructParents(pDPage.getStructParents() + i);
        List<PDAnnotation> annotations = pDPage.getAnnotations();
        ArrayList arrayList = new ArrayList();
        for (PDAnnotation pDAnnotation : annotations) {
            pDAnnotation.setStructParent(pDAnnotation.getStructParent() + i);
            arrayList.add(pDAnnotation);
        }
        pDPage.setAnnotations(arrayList);
    }

    private boolean isDynamicXfa(PDAcroForm pDAcroForm) {
        return pDAcroForm != null && pDAcroForm.xfaIsDynamic();
    }
}
