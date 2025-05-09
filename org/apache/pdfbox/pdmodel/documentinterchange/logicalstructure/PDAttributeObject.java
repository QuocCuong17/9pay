package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.PDDictionaryWrapper;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDExportFormatAttributeObject;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDListAttributeObject;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDPrintFieldAttributeObject;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDTableAttributeObject;

/* loaded from: classes5.dex */
public abstract class PDAttributeObject extends PDDictionaryWrapper {
    private PDStructureElement structureElement;

    public static PDAttributeObject create(COSDictionary cOSDictionary) {
        String nameAsString = cOSDictionary.getNameAsString(COSName.O);
        if (PDUserAttributeObject.OWNER_USER_PROPERTIES.equals(nameAsString)) {
            return new PDUserAttributeObject(cOSDictionary);
        }
        if (PDListAttributeObject.OWNER_LIST.equals(nameAsString)) {
            return new PDListAttributeObject(cOSDictionary);
        }
        if (PDPrintFieldAttributeObject.OWNER_PRINT_FIELD.equals(nameAsString)) {
            return new PDPrintFieldAttributeObject(cOSDictionary);
        }
        if ("Table".equals(nameAsString)) {
            return new PDTableAttributeObject(cOSDictionary);
        }
        if (PDLayoutAttributeObject.OWNER_LAYOUT.equals(nameAsString)) {
            return new PDLayoutAttributeObject(cOSDictionary);
        }
        if (PDExportFormatAttributeObject.OWNER_XML_1_00.equals(nameAsString) || PDExportFormatAttributeObject.OWNER_HTML_3_20.equals(nameAsString) || PDExportFormatAttributeObject.OWNER_HTML_4_01.equals(nameAsString) || PDExportFormatAttributeObject.OWNER_OEB_1_00.equals(nameAsString) || PDExportFormatAttributeObject.OWNER_RTF_1_05.equals(nameAsString) || PDExportFormatAttributeObject.OWNER_CSS_1_00.equals(nameAsString) || PDExportFormatAttributeObject.OWNER_CSS_2_00.equals(nameAsString)) {
            return new PDExportFormatAttributeObject(cOSDictionary);
        }
        return new PDDefaultAttributeObject(cOSDictionary);
    }

    private PDStructureElement getStructureElement() {
        return this.structureElement;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setStructureElement(PDStructureElement pDStructureElement) {
        this.structureElement = pDStructureElement;
    }

    public PDAttributeObject() {
    }

    public PDAttributeObject(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getOwner() {
        return getCOSDictionary().getNameAsString(COSName.O);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setOwner(String str) {
        getCOSDictionary().setName(COSName.O, str);
    }

    public boolean isEmpty() {
        return getCOSDictionary().size() == 1 && getOwner() != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void potentiallyNotifyChanged(COSBase cOSBase, COSBase cOSBase2) {
        if (isValueChanged(cOSBase, cOSBase2)) {
            notifyChanged();
        }
    }

    private static boolean isValueChanged(COSBase cOSBase, COSBase cOSBase2) {
        if (cOSBase == null) {
            return cOSBase2 != null;
        }
        return !cOSBase.equals(cOSBase2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyChanged() {
        if (getStructureElement() != null) {
            getStructureElement().attributeChanged(this);
        }
    }

    public String toString() {
        return "O=" + getOwner();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String arrayToString(Object[] objArr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < objArr.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(objArr[i]);
        }
        sb.append(']');
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String arrayToString(float[] fArr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < fArr.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(fArr[i]);
        }
        sb.append(']');
        return sb.toString();
    }
}
