package org.apache.pdfbox.multipdf;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentGroup;
import org.apache.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentProperties;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.awt.AffineTransform;

/* loaded from: classes5.dex */
public class LayerUtility {
    private static final boolean DEBUG = true;
    private static final Set<String> PAGE_TO_FORM_FILTER = new HashSet(Arrays.asList(PDAnnotationMarkup.RT_GROUP, "LastModified", "Metadata"));
    private PDFCloneUtility cloner;
    private PDDocument targetDoc;

    public LayerUtility(PDDocument pDDocument) {
        this.targetDoc = pDDocument;
        this.cloner = new PDFCloneUtility(pDDocument);
    }

    public PDDocument getDocument() {
        return this.targetDoc;
    }

    public void wrapInSaveRestore(PDPage pDPage) throws IOException {
        COSDictionary cOSDictionary = new COSDictionary();
        COSStream createCOSStream = getDocument().getDocument().createCOSStream(cOSDictionary);
        OutputStream createUnfilteredStream = createCOSStream.createUnfilteredStream();
        createUnfilteredStream.write("q\n".getBytes("ISO-8859-1"));
        createUnfilteredStream.flush();
        COSStream createCOSStream2 = getDocument().getDocument().createCOSStream(cOSDictionary);
        OutputStream createUnfilteredStream2 = createCOSStream2.createUnfilteredStream();
        createUnfilteredStream2.write("Q\n".getBytes("ISO-8859-1"));
        createUnfilteredStream2.flush();
        COSDictionary cOSObject = pDPage.getCOSObject();
        COSBase dictionaryObject = cOSObject.getDictionaryObject(COSName.CONTENTS);
        if (dictionaryObject instanceof COSStream) {
            COSArray cOSArray = new COSArray();
            cOSArray.add((COSBase) createCOSStream);
            cOSArray.add(dictionaryObject);
            cOSArray.add((COSBase) createCOSStream2);
            cOSObject.setItem(COSName.CONTENTS, (COSBase) cOSArray);
            return;
        }
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray2 = (COSArray) dictionaryObject;
            cOSArray2.add(0, createCOSStream);
            cOSArray2.add((COSBase) createCOSStream2);
        } else {
            throw new IOException("Contents are unknown type: " + dictionaryObject.getClass().getName());
        }
    }

    public PDFormXObject importPageAsForm(PDDocument pDDocument, int i) throws IOException {
        return importPageAsForm(pDDocument, pDDocument.getPage(i));
    }

    public PDFormXObject importPageAsForm(PDDocument pDDocument, PDPage pDPage) throws IOException {
        PDFormXObject pDFormXObject = new PDFormXObject(new PDStream(this.targetDoc, ((COSStream) pDPage.getStream().getCOSObject()).getUnfilteredStream(), false));
        PDResources resources = pDPage.getResources();
        PDResources pDResources = new PDResources();
        this.cloner.cloneMerge(resources, pDResources);
        pDFormXObject.setResources(pDResources);
        transferDict(pDPage.getCOSObject(), pDFormXObject.getCOSStream(), PAGE_TO_FORM_FILTER, true);
        AffineTransform createAffineTransform = pDFormXObject.getMatrix().createAffineTransform();
        PDRectangle mediaBox = pDPage.getMediaBox();
        PDRectangle cropBox = pDPage.getCropBox();
        if (cropBox == null) {
            cropBox = mediaBox;
        }
        int rotation = pDPage.getRotation();
        createAffineTransform.translate(mediaBox.getLowerLeftX() - cropBox.getLowerLeftX(), mediaBox.getLowerLeftY() - cropBox.getLowerLeftY());
        if (rotation == 90) {
            createAffineTransform.scale(cropBox.getWidth() / cropBox.getHeight(), cropBox.getHeight() / cropBox.getWidth());
            createAffineTransform.translate(0.0d, cropBox.getWidth());
            createAffineTransform.rotate(-1.5707963705062866d);
        } else if (rotation == 180) {
            createAffineTransform.translate(cropBox.getWidth(), cropBox.getHeight());
            createAffineTransform.rotate(-3.1415927410125732d);
        } else if (rotation == 270) {
            createAffineTransform.scale(cropBox.getWidth() / cropBox.getHeight(), cropBox.getHeight() / cropBox.getWidth());
            createAffineTransform.translate(cropBox.getHeight(), 0.0d);
            createAffineTransform.rotate(-4.71238899230957d);
        }
        createAffineTransform.translate(-cropBox.getLowerLeftX(), -cropBox.getLowerLeftY());
        if (!createAffineTransform.isIdentity()) {
            pDFormXObject.setMatrix(createAffineTransform);
        }
        BoundingBox boundingBox = new BoundingBox();
        boundingBox.setLowerLeftX(cropBox.getLowerLeftX());
        boundingBox.setLowerLeftY(cropBox.getLowerLeftY());
        boundingBox.setUpperRightX(cropBox.getUpperRightX());
        boundingBox.setUpperRightY(cropBox.getUpperRightY());
        pDFormXObject.setBBox(new PDRectangle(boundingBox));
        return pDFormXObject;
    }

    public PDOptionalContentGroup appendFormAsLayer(PDPage pDPage, PDFormXObject pDFormXObject, AffineTransform affineTransform, String str) throws IOException {
        PDDocumentCatalog documentCatalog = this.targetDoc.getDocumentCatalog();
        PDOptionalContentProperties oCProperties = documentCatalog.getOCProperties();
        if (oCProperties == null) {
            oCProperties = new PDOptionalContentProperties();
            documentCatalog.setOCProperties(oCProperties);
        }
        if (oCProperties.hasGroup(str)) {
            throw new IllegalArgumentException("Optional group (layer) already exists: " + str);
        }
        PDOptionalContentGroup pDOptionalContentGroup = new PDOptionalContentGroup(str);
        oCProperties.addGroup(pDOptionalContentGroup);
        PDPageContentStream pDPageContentStream = new PDPageContentStream(this.targetDoc, pDPage, true, false);
        pDPageContentStream.beginMarkedContent(COSName.OC, pDOptionalContentGroup);
        pDPageContentStream.saveGraphicsState();
        pDPageContentStream.transform(new Matrix(affineTransform));
        pDPageContentStream.drawForm(pDFormXObject);
        pDPageContentStream.restoreGraphicsState();
        pDPageContentStream.endMarkedContent();
        pDPageContentStream.close();
        return pDOptionalContentGroup;
    }

    private void transferDict(COSDictionary cOSDictionary, COSDictionary cOSDictionary2, Set<String> set, boolean z) throws IOException {
        for (Map.Entry<COSName, COSBase> entry : cOSDictionary.entrySet()) {
            COSName key = entry.getKey();
            if (!z || set.contains(key.getName())) {
                if (z || !set.contains(key.getName())) {
                    cOSDictionary2.setItem(key, this.cloner.cloneForNewDocument(entry.getValue()));
                }
            }
        }
    }
}
