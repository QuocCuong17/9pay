package org.apache.pdfbox.pdmodel.interactive.form;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.COSArrayList;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.fdf.FDFCatalog;
import org.apache.pdfbox.pdmodel.fdf.FDFDictionary;
import org.apache.pdfbox.pdmodel.fdf.FDFDocument;
import org.apache.pdfbox.pdmodel.fdf.FDFField;

/* loaded from: classes5.dex */
public final class PDAcroForm implements COSObjectable {
    private static final int FLAG_APPEND_ONLY = 2;
    private static final int FLAG_SIGNATURES_EXIST = 1;
    private COSDictionary acroForm;
    private PDDocument document;
    private Map<String, PDFieldTreeNode> fieldCache;

    public PDAcroForm(PDDocument pDDocument) {
        this.document = pDDocument;
        this.acroForm = new COSDictionary();
        this.acroForm.setItem(COSName.FIELDS, (COSBase) new COSArray());
    }

    public PDAcroForm(PDDocument pDDocument, COSDictionary cOSDictionary) {
        this.document = pDDocument;
        this.acroForm = cOSDictionary;
    }

    public PDDocument getDocument() {
        return this.document;
    }

    public COSDictionary getDictionary() {
        return this.acroForm;
    }

    public void importFDF(FDFDocument fDFDocument) throws IOException {
        List<FDFField> fields = fDFDocument.getCatalog().getFDF().getFields();
        if (fields != null) {
            for (FDFField fDFField : fields) {
                PDFieldTreeNode field = getField(fDFField.getPartialFieldName());
                if (field != null) {
                    field.importFDF(fDFField);
                }
            }
        }
    }

    public FDFDocument exportFDF() throws IOException {
        FDFDocument fDFDocument = new FDFDocument();
        FDFCatalog catalog = fDFDocument.getCatalog();
        FDFDictionary fDFDictionary = new FDFDictionary();
        catalog.setFDF(fDFDictionary);
        ArrayList arrayList = new ArrayList();
        Iterator<PDFieldTreeNode> it = getFields().iterator();
        while (it.hasNext()) {
            addFieldAndChildren(it.next(), arrayList);
        }
        fDFDictionary.setID(this.document.getDocument().getDocumentID());
        if (!arrayList.isEmpty()) {
            fDFDictionary.setFields(arrayList);
        }
        return fDFDocument;
    }

    private void addFieldAndChildren(PDFieldTreeNode pDFieldTreeNode, List<FDFField> list) throws IOException {
        Object value = pDFieldTreeNode.getValue();
        FDFField fDFField = new FDFField();
        fDFField.setPartialFieldName(pDFieldTreeNode.getPartialName());
        fDFField.setValue(value);
        List<COSObjectable> kids = pDFieldTreeNode.getKids();
        ArrayList arrayList = new ArrayList();
        if (kids != null) {
            Iterator<COSObjectable> it = kids.iterator();
            while (it.hasNext()) {
                addFieldAndChildren((PDFieldTreeNode) it.next(), arrayList);
            }
            if (!arrayList.isEmpty()) {
                fDFField.setKids(arrayList);
            }
        }
        if (value == null && arrayList.isEmpty()) {
            return;
        }
        list.add(fDFField);
    }

    public List<PDFieldTreeNode> getFields() {
        PDFieldTreeNode createField;
        COSArray cOSArray = (COSArray) this.acroForm.getDictionaryObject(COSName.FIELDS);
        if (cOSArray == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            COSDictionary cOSDictionary = (COSDictionary) cOSArray.getObject(i);
            if (cOSDictionary != null && (createField = PDFieldTreeNode.createField(this, cOSDictionary, null)) != null) {
                arrayList.add(createField);
            }
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setFields(List<PDFieldTreeNode> list) {
        this.acroForm.setItem(COSName.FIELDS, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public void setCacheFields(boolean z) throws IOException {
        if (z) {
            this.fieldCache = new HashMap();
            for (PDFieldTreeNode pDFieldTreeNode : getFields()) {
                this.fieldCache.put(pDFieldTreeNode.getFullyQualifiedName(), pDFieldTreeNode);
            }
            return;
        }
        this.fieldCache = null;
    }

    public boolean isCachingFields() {
        return this.fieldCache != null;
    }

    public PDFieldTreeNode getField(String str) throws IOException {
        PDFieldTreeNode findKid;
        Map<String, PDFieldTreeNode> map = this.fieldCache;
        if (map != null) {
            return map.get(str);
        }
        String[] split = str.split("\\.");
        COSArray cOSArray = (COSArray) this.acroForm.getDictionaryObject(COSName.FIELDS);
        PDFieldTreeNode pDFieldTreeNode = null;
        for (int i = 0; i < cOSArray.size() && pDFieldTreeNode == null; i++) {
            COSDictionary cOSDictionary = (COSDictionary) cOSArray.getObject(i);
            if (cOSDictionary != null) {
                COSString cOSString = (COSString) cOSDictionary.getDictionaryObject(COSName.T);
                if (cOSString.getString().equals(str) || cOSString.getString().equals(split[0])) {
                    pDFieldTreeNode = PDFieldTreeNode.createField(this, cOSDictionary, null);
                    if (split.length > 1 && (findKid = pDFieldTreeNode.findKid(split, 1)) != null) {
                        pDFieldTreeNode = findKid;
                    }
                }
            }
        }
        return pDFieldTreeNode;
    }

    public String getDefaultAppearance() {
        return ((COSString) getDictionary().getItem(COSName.DA)).getString();
    }

    public void setDefaultAppearance(String str) {
        if (str != null) {
            getDictionary().setString(COSName.DA, str);
        } else {
            getDictionary().removeItem(COSName.DA);
        }
    }

    public boolean isNeedAppearances() {
        return getDictionary().getBoolean(COSName.NEED_APPEARANCES, false);
    }

    public void setNeedAppearances(Boolean bool) {
        if (bool != null) {
            getDictionary().setBoolean(COSName.NEED_APPEARANCES, bool.booleanValue());
        } else {
            getDictionary().removeItem(COSName.NEED_APPEARANCES);
        }
    }

    public PDResources getDefaultResources() {
        COSDictionary cOSDictionary = (COSDictionary) this.acroForm.getDictionaryObject(COSName.DR);
        if (cOSDictionary != null) {
            return new PDResources(cOSDictionary);
        }
        return null;
    }

    public void setDefaultResources(PDResources pDResources) {
        this.acroForm.setItem(COSName.DR, (COSBase) (pDResources != null ? pDResources.getCOSObject() : null));
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.acroForm;
    }

    public boolean hasXFA() {
        return this.acroForm.containsKey(COSName.XFA);
    }

    public boolean xfaIsDynamic() {
        return hasXFA() && getFields().isEmpty();
    }

    public PDXFAResource getXFA() {
        COSBase dictionaryObject = this.acroForm.getDictionaryObject(COSName.XFA);
        if (dictionaryObject != null) {
            return new PDXFAResource(dictionaryObject);
        }
        return null;
    }

    public void setXFA(PDXFAResource pDXFAResource) {
        this.acroForm.setItem(COSName.XFA, pDXFAResource);
    }

    public int getQ() {
        COSNumber cOSNumber = (COSNumber) getDictionary().getDictionaryObject(COSName.Q);
        if (cOSNumber != null) {
            return cOSNumber.intValue();
        }
        return 0;
    }

    public void setQ(int i) {
        getDictionary().setInt(COSName.Q, i);
    }

    public boolean isSignaturesExist() {
        return getDictionary().getFlag(COSName.SIG_FLAGS, 1);
    }

    public void setSignaturesExist(boolean z) {
        getDictionary().setFlag(COSName.SIG_FLAGS, 1, z);
    }

    public boolean isAppendOnly() {
        return getDictionary().getFlag(COSName.SIG_FLAGS, 2);
    }

    public void setAppendOnly(boolean z) {
        getDictionary().setFlag(COSName.SIG_FLAGS, 2, z);
    }
}
