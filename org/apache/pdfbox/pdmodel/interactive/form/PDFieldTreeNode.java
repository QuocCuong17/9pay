package org.apache.pdfbox.pdmodel.interactive.form;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSArrayList;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDTextStream;
import org.apache.pdfbox.pdmodel.fdf.FDFField;
import org.apache.pdfbox.pdmodel.interactive.action.PDFormFieldAdditionalActions;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;

/* loaded from: classes5.dex */
public abstract class PDFieldTreeNode implements COSObjectable {
    private static final String FIELD_TYPE_BUTTON = "Btn";
    private static final String FIELD_TYPE_CHOICE = "Ch";
    private static final String FIELD_TYPE_SIGNATURE = "Sig";
    private static final String FIELD_TYPE_TEXT = "Tx";
    private static final int FLAG_NO_EXPORT = 4;
    private static final int FLAG_READ_ONLY = 1;
    private static final int FLAG_REQUIRED = 2;
    private PDAcroForm acroForm;
    private COSDictionary dictionary;
    private PDFieldTreeNode parent;

    public abstract Object getDefaultValue() throws IOException;

    public abstract int getFieldFlags();

    public abstract String getFieldType();

    public abstract Object getValue() throws IOException;

    public abstract void setDefaultValue(String str);

    public abstract void setValue(String str) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public PDFieldTreeNode(PDAcroForm pDAcroForm) {
        this(pDAcroForm, new COSDictionary(), null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PDFieldTreeNode(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDFieldTreeNode pDFieldTreeNode) {
        this.parent = null;
        this.acroForm = pDAcroForm;
        this.dictionary = cOSDictionary;
        this.parent = pDFieldTreeNode;
    }

    public PDFieldTreeNode getInheritableAttributesNode(PDFieldTreeNode pDFieldTreeNode, COSName cOSName) {
        if (pDFieldTreeNode.getDictionary().containsKey(cOSName)) {
            return pDFieldTreeNode;
        }
        PDFieldTreeNode parent = pDFieldTreeNode.getParent();
        if (parent == null) {
            return null;
        }
        getInheritableAttributesNode(parent, cOSName);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public COSBase getInheritableAttribute(COSName cOSName) {
        PDFieldTreeNode inheritableAttributesNode = getInheritableAttributesNode(this, cOSName);
        if (inheritableAttributesNode != null) {
            return inheritableAttributesNode.getDictionary().getDictionaryObject(cOSName);
        }
        return getAcroForm().getDictionary().getDictionaryObject(cOSName);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInheritableAttribute(COSName cOSName, COSBase cOSBase) {
        if (cOSBase == null) {
            removeInheritableAttribute(cOSName);
            return;
        }
        PDFieldTreeNode inheritableAttributesNode = getInheritableAttributesNode(this, cOSName);
        if (inheritableAttributesNode != null) {
            inheritableAttributesNode.getDictionary().setItem(cOSName, cOSBase);
        } else {
            getDictionary().setItem(cOSName, cOSBase);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeInheritableAttribute(COSName cOSName) {
        PDFieldTreeNode inheritableAttributesNode = getInheritableAttributesNode(this, cOSName);
        if (inheritableAttributesNode != null) {
            inheritableAttributesNode.getDictionary().removeItem(cOSName);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PDTextStream getAsTextStream(COSBase cOSBase) throws IOException {
        if (cOSBase == null) {
            return null;
        }
        PDTextStream createTextStream = PDTextStream.createTextStream(cOSBase);
        if (createTextStream != null) {
            return createTextStream;
        }
        throw new IOException("Invalid field value. Unexpected type " + cOSBase.getClass().getName());
    }

    public void setReadonly(boolean z) {
        getDictionary().setFlag(COSName.FF, 1, z);
    }

    public boolean isReadonly() {
        return getDictionary().getFlag(COSName.FF, 1);
    }

    public void setRequired(boolean z) {
        getDictionary().setFlag(COSName.FF, 2, z);
    }

    public boolean isRequired() {
        return getDictionary().getFlag(COSName.FF, 2);
    }

    public void setNoExport(boolean z) {
        getDictionary().setFlag(COSName.FF, 4, z);
    }

    public boolean isNoExport() {
        return getDictionary().getFlag(COSName.FF, 4);
    }

    public void setFieldFlags(int i) {
        getDictionary().setInt(COSName.FF, i);
    }

    public PDFormFieldAdditionalActions getActions() {
        COSDictionary cOSDictionary = (COSDictionary) getDictionary().getDictionaryObject(COSName.AA);
        if (cOSDictionary != null) {
            return new PDFormFieldAdditionalActions(cOSDictionary);
        }
        return null;
    }

    public void importFDF(FDFField fDFField) throws IOException {
        Object value = fDFField.getValue();
        int fieldFlags = getFieldFlags();
        if (value != null) {
            if (value instanceof String) {
                setValue((String) value);
            } else if (value instanceof PDTextStream) {
                setValue(((PDTextStream) value).getAsString());
            } else {
                throw new IOException("Unknown field type:" + value.getClass().getName());
            }
        }
        Integer fieldFlags2 = fDFField.getFieldFlags();
        if (fieldFlags2 != null) {
            setFieldFlags(fieldFlags2.intValue());
        } else {
            Integer setFieldFlags = fDFField.getSetFieldFlags();
            if (setFieldFlags != null) {
                fieldFlags |= setFieldFlags.intValue();
                setFieldFlags(fieldFlags);
            }
            Integer clearFieldFlags = fDFField.getClearFieldFlags();
            if (clearFieldFlags != null) {
                setFieldFlags((~clearFieldFlags.intValue()) & fieldFlags);
            }
        }
        PDAnnotationWidget widget = getWidget();
        if (widget != null) {
            int annotationFlags = widget.getAnnotationFlags();
            Integer widgetFieldFlags = fDFField.getWidgetFieldFlags();
            if (widgetFieldFlags != null) {
                widget.setAnnotationFlags(widgetFieldFlags.intValue());
            } else {
                Integer setWidgetFieldFlags = fDFField.getSetWidgetFieldFlags();
                if (setWidgetFieldFlags != null) {
                    annotationFlags |= setWidgetFieldFlags.intValue();
                    widget.setAnnotationFlags(annotationFlags);
                }
                if (fDFField.getClearWidgetFieldFlags() != null) {
                    widget.setAnnotationFlags(annotationFlags & ((int) (r2.intValue() ^ 4294967295L)));
                }
            }
        }
        List<FDFField> kids = fDFField.getKids();
        List<COSObjectable> kids2 = getKids();
        for (int i = 0; kids != null && i < kids.size(); i++) {
            FDFField fDFField2 = kids.get(i);
            String partialFieldName = fDFField2.getPartialFieldName();
            for (COSObjectable cOSObjectable : kids2) {
                if (cOSObjectable instanceof PDFieldTreeNode) {
                    PDFieldTreeNode pDFieldTreeNode = (PDFieldTreeNode) cOSObjectable;
                    if (partialFieldName != null && partialFieldName.equals(pDFieldTreeNode.getPartialName())) {
                        pDFieldTreeNode.importFDF(fDFField2);
                    }
                }
            }
        }
    }

    public PDAnnotationWidget getWidget() {
        List<COSObjectable> kids = getKids();
        if (kids == null) {
            return new PDAnnotationWidget(getDictionary());
        }
        if (kids.isEmpty()) {
            return null;
        }
        COSObjectable cOSObjectable = kids.get(0);
        if (cOSObjectable instanceof PDAnnotationWidget) {
            return (PDAnnotationWidget) cOSObjectable;
        }
        return ((PDFieldTreeNode) cOSObjectable).getWidget();
    }

    public PDFieldTreeNode getParent() {
        return this.parent;
    }

    public void setParent(PDFieldTreeNode pDFieldTreeNode) {
        this.parent = pDFieldTreeNode;
        if (pDFieldTreeNode != null) {
            getDictionary().setItem(COSName.PARENT, (COSBase) this.parent.getDictionary());
        } else {
            getDictionary().removeItem(COSName.PARENT);
        }
    }

    public PDFieldTreeNode findKid(String[] strArr, int i) {
        COSArray cOSArray = (COSArray) getDictionary().getDictionaryObject(COSName.KIDS);
        PDFieldTreeNode pDFieldTreeNode = null;
        if (cOSArray != null) {
            for (int i2 = 0; pDFieldTreeNode == null && i2 < cOSArray.size(); i2++) {
                COSDictionary cOSDictionary = (COSDictionary) cOSArray.getObject(i2);
                if (strArr[i].equals(cOSDictionary.getString(COSName.T))) {
                    pDFieldTreeNode = createField(this.acroForm, cOSDictionary, this);
                    int i3 = i + 1;
                    if (strArr.length > i3) {
                        pDFieldTreeNode = pDFieldTreeNode.findKid(strArr, i3);
                    }
                }
            }
        }
        return pDFieldTreeNode;
    }

    public List<COSObjectable> getKids() {
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.KIDS);
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            COSDictionary cOSDictionary = (COSDictionary) cOSArray.getObject(i);
            if (cOSDictionary != null) {
                if (cOSDictionary.getDictionaryObject(COSName.T) != null) {
                    PDFieldTreeNode createField = createField(this.acroForm, cOSDictionary, this);
                    if (createField != null) {
                        arrayList.add(createField);
                    }
                } else {
                    arrayList.add(new PDAnnotationWidget(cOSDictionary));
                }
            }
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setKids(List<COSObjectable> list) {
        getDictionary().setItem(COSName.KIDS, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public String toString() {
        return "" + getDictionary().getDictionaryObject(COSName.V);
    }

    public PDAcroForm getAcroForm() {
        return this.acroForm;
    }

    public void setAcroForm(PDAcroForm pDAcroForm) {
        this.acroForm = pDAcroForm;
    }

    public COSDictionary getDictionary() {
        return this.dictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.dictionary;
    }

    public String getPartialName() {
        return getDictionary().getString(COSName.T);
    }

    public void setPartialName(String str) {
        getDictionary().setString(COSName.T, str);
    }

    public String getFullyQualifiedName() throws IOException {
        String partialName = getPartialName();
        String fullyQualifiedName = getParent() != null ? getParent().getFullyQualifiedName() : null;
        if (fullyQualifiedName == null) {
            return partialName;
        }
        if (partialName == null) {
            return fullyQualifiedName;
        }
        return fullyQualifiedName + "." + partialName;
    }

    public String getAlternateFieldName() {
        return getDictionary().getString(COSName.TU);
    }

    public void setAlternateFieldName(String str) {
        getDictionary().setString(COSName.TU, str);
    }

    public String getMappingName() {
        return getDictionary().getString(COSName.TM);
    }

    public void setMappingName(String str) {
        getDictionary().setString(COSName.TM, str);
    }

    public static PDFieldTreeNode createField(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDFieldTreeNode pDFieldTreeNode) {
        String findFieldType = findFieldType(cOSDictionary);
        if (FIELD_TYPE_CHOICE.equals(findFieldType)) {
            return createChoiceSubType(pDAcroForm, cOSDictionary, pDFieldTreeNode);
        }
        if (FIELD_TYPE_TEXT.equals(findFieldType)) {
            return new PDTextField(pDAcroForm, cOSDictionary, pDFieldTreeNode);
        }
        if (FIELD_TYPE_SIGNATURE.equals(findFieldType)) {
            return new PDSignatureField(pDAcroForm, cOSDictionary, pDFieldTreeNode);
        }
        if (FIELD_TYPE_BUTTON.equals(findFieldType)) {
            return createButtonSubType(pDAcroForm, cOSDictionary, pDFieldTreeNode);
        }
        return new PDNonTerminalField(pDAcroForm, cOSDictionary, pDFieldTreeNode);
    }

    private static PDFieldTreeNode createChoiceSubType(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDFieldTreeNode pDFieldTreeNode) {
        if ((cOSDictionary.getInt(COSName.FF, 0) & 131072) != 0) {
            return new PDComboBox(pDAcroForm, cOSDictionary, pDFieldTreeNode);
        }
        return new PDListBox(pDAcroForm, cOSDictionary, pDFieldTreeNode);
    }

    private static PDFieldTreeNode createButtonSubType(PDAcroForm pDAcroForm, COSDictionary cOSDictionary, PDFieldTreeNode pDFieldTreeNode) {
        int i = cOSDictionary.getInt(COSName.FF, 0);
        if ((32768 & i) != 0) {
            return new PDRadioButton(pDAcroForm, cOSDictionary, pDFieldTreeNode);
        }
        if ((i & 65536) != 0) {
            return new PDPushButton(pDAcroForm, cOSDictionary, pDFieldTreeNode);
        }
        return new PDCheckbox(pDAcroForm, cOSDictionary, pDFieldTreeNode);
    }

    private static String findFieldType(COSDictionary cOSDictionary) {
        COSDictionary cOSDictionary2;
        String nameAsString = cOSDictionary.getNameAsString(COSName.FT);
        return (nameAsString != null || (cOSDictionary2 = (COSDictionary) cOSDictionary.getDictionaryObject(COSName.PARENT, COSName.P)) == null) ? nameAsString : findFieldType(cOSDictionary2);
    }
}
