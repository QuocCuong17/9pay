package org.apache.pdfbox.pdmodel.graphics.form;

import org.apache.pdfbox.contentstream.PDContentStream;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.awt.AffineTransform;

/* loaded from: classes5.dex */
public class PDFormXObject extends PDXObject implements PDContentStream {
    private PDGroup group;
    private String name;

    public PDFormXObject(PDStream pDStream) {
        super(pDStream, COSName.FORM);
    }

    public PDFormXObject(PDStream pDStream, String str) {
        super(pDStream, COSName.FORM);
        this.name = str;
    }

    public PDFormXObject(PDDocument pDDocument) {
        super(pDDocument, COSName.FORM);
    }

    public int getFormType() {
        return getCOSStream().getInt(COSName.FORMTYPE, 1);
    }

    public void setFormType(int i) {
        getCOSStream().setInt(COSName.FORMTYPE, i);
    }

    public PDGroup getGroup() {
        COSDictionary cOSDictionary;
        if (this.group == null && (cOSDictionary = (COSDictionary) getCOSStream().getDictionaryObject(COSName.GROUP)) != null) {
            this.group = new PDGroup(cOSDictionary);
        }
        return this.group;
    }

    @Override // org.apache.pdfbox.contentstream.PDContentStream
    public COSStream getContentStream() {
        return getCOSStream();
    }

    @Override // org.apache.pdfbox.contentstream.PDContentStream
    public PDResources getResources() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSStream().getDictionaryObject(COSName.RESOURCES);
        if (cOSDictionary != null) {
            return new PDResources(cOSDictionary);
        }
        return null;
    }

    public void setResources(PDResources pDResources) {
        getCOSStream().setItem(COSName.RESOURCES, pDResources);
    }

    @Override // org.apache.pdfbox.contentstream.PDContentStream
    public PDRectangle getBBox() {
        COSArray cOSArray = (COSArray) getCOSStream().getDictionaryObject(COSName.BBOX);
        if (cOSArray != null) {
            return new PDRectangle(cOSArray);
        }
        return null;
    }

    public void setBBox(PDRectangle pDRectangle) {
        if (pDRectangle == null) {
            getCOSStream().removeItem(COSName.BBOX);
        } else {
            getCOSStream().setItem(COSName.BBOX, (COSBase) pDRectangle.getCOSArray());
        }
    }

    @Override // org.apache.pdfbox.contentstream.PDContentStream
    public Matrix getMatrix() {
        COSArray cOSArray = (COSArray) getCOSStream().getDictionaryObject(COSName.MATRIX);
        if (cOSArray != null) {
            return new Matrix(cOSArray);
        }
        return new Matrix();
    }

    public void setMatrix(AffineTransform affineTransform) {
        COSArray cOSArray = new COSArray();
        double[] dArr = new double[6];
        affineTransform.getMatrix(dArr);
        for (int i = 0; i < 6; i++) {
            cOSArray.add((COSBase) new COSFloat((float) dArr[i]));
        }
        getCOSStream().setItem(COSName.MATRIX, (COSBase) cOSArray);
    }

    public int getStructParents() {
        return getCOSStream().getInt(COSName.STRUCT_PARENTS, 0);
    }

    public void setStructParents(int i) {
        getCOSStream().setInt(COSName.STRUCT_PARENTS, i);
    }
}
