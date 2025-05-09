package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;

import java.io.IOException;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationUnknown;

/* loaded from: classes5.dex */
public class PDObjectReference implements COSObjectable {
    public static final String TYPE = "OBJR";
    private COSDictionary dictionary;

    protected COSDictionary getCOSDictionary() {
        return this.dictionary;
    }

    public PDObjectReference() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setName(COSName.TYPE, TYPE);
    }

    public PDObjectReference(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.dictionary;
    }

    public COSObjectable getReferencedObject() {
        PDXObject createXObject;
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(COSName.OBJ);
        if (!(dictionaryObject instanceof COSDictionary)) {
            return null;
        }
        try {
            createXObject = PDXObject.createXObject(dictionaryObject, null, null);
        } catch (IOException unused) {
        }
        if (createXObject != null) {
            return createXObject;
        }
        COSDictionary cOSDictionary = (COSDictionary) dictionaryObject;
        PDAnnotation createAnnotation = PDAnnotation.createAnnotation(dictionaryObject);
        if (createAnnotation instanceof PDAnnotationUnknown) {
            if (!COSName.ANNOT.equals(cOSDictionary.getDictionaryObject(COSName.TYPE))) {
                return null;
            }
        }
        return createAnnotation;
    }

    public void setReferencedObject(PDAnnotation pDAnnotation) {
        getCOSDictionary().setItem(COSName.OBJ, pDAnnotation);
    }

    public void setReferencedObject(PDXObject pDXObject) {
        getCOSDictionary().setItem(COSName.OBJ, pDXObject);
    }
}
