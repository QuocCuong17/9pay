package org.apache.pdfbox.pdmodel.common;

import android.graphics.Path;
import android.graphics.PointF;
import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.util.Matrix;

/* loaded from: classes5.dex */
public class PDRectangle implements COSObjectable {
    private static final float MM_PER_INCH = 2.8346457f;
    private static final float POINTS_PER_INCH = 72.0f;
    private final COSArray rectArray;
    public static final PDRectangle LETTER = new PDRectangle(612.0f, 792.0f);
    public static final PDRectangle LEGAL = new PDRectangle(612.0f, 1008.0f);
    public static final PDRectangle A0 = new PDRectangle(2383.937f, 3370.3938f);
    public static final PDRectangle A1 = new PDRectangle(1683.7795f, 2383.937f);
    public static final PDRectangle A2 = new PDRectangle(1190.5513f, 1683.7795f);
    public static final PDRectangle A3 = new PDRectangle(841.8898f, 1190.5513f);
    public static final PDRectangle A4 = new PDRectangle(595.27563f, 841.8898f);
    public static final PDRectangle A5 = new PDRectangle(419.52756f, 595.27563f);
    public static final PDRectangle A6 = new PDRectangle(297.63782f, 419.52756f);

    public PDRectangle() {
        this(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public PDRectangle(float f, float f2) {
        this(0.0f, 0.0f, f, f2);
    }

    public PDRectangle(float f, float f2, float f3, float f4) {
        COSArray cOSArray = new COSArray();
        this.rectArray = cOSArray;
        cOSArray.add((COSBase) new COSFloat(f));
        cOSArray.add((COSBase) new COSFloat(f2));
        cOSArray.add((COSBase) new COSFloat(f + f3));
        cOSArray.add((COSBase) new COSFloat(f2 + f4));
    }

    public PDRectangle(BoundingBox boundingBox) {
        COSArray cOSArray = new COSArray();
        this.rectArray = cOSArray;
        cOSArray.add((COSBase) new COSFloat(boundingBox.getLowerLeftX()));
        cOSArray.add((COSBase) new COSFloat(boundingBox.getLowerLeftY()));
        cOSArray.add((COSBase) new COSFloat(boundingBox.getUpperRightX()));
        cOSArray.add((COSBase) new COSFloat(boundingBox.getUpperRightY()));
    }

    public PDRectangle(COSArray cOSArray) {
        float[] floatArray = cOSArray.toFloatArray();
        COSArray cOSArray2 = new COSArray();
        this.rectArray = cOSArray2;
        cOSArray2.add((COSBase) new COSFloat(Math.min(floatArray[0], floatArray[2])));
        cOSArray2.add((COSBase) new COSFloat(Math.min(floatArray[1], floatArray[3])));
        cOSArray2.add((COSBase) new COSFloat(Math.max(floatArray[0], floatArray[2])));
        cOSArray2.add((COSBase) new COSFloat(Math.max(floatArray[1], floatArray[3])));
    }

    public boolean contains(float f, float f2) {
        return f >= getLowerLeftX() && f <= getUpperRightX() && f2 >= getLowerLeftY() && f2 <= getUpperRightY();
    }

    public PDRectangle createRetranslatedRectangle() {
        PDRectangle pDRectangle = new PDRectangle();
        pDRectangle.setUpperRightX(getWidth());
        pDRectangle.setUpperRightY(getHeight());
        return pDRectangle;
    }

    public COSArray getCOSArray() {
        return this.rectArray;
    }

    public float getLowerLeftX() {
        return ((COSNumber) this.rectArray.get(0)).floatValue();
    }

    public void setLowerLeftX(float f) {
        this.rectArray.set(0, (COSBase) new COSFloat(f));
    }

    public float getLowerLeftY() {
        return ((COSNumber) this.rectArray.get(1)).floatValue();
    }

    public void setLowerLeftY(float f) {
        this.rectArray.set(1, (COSBase) new COSFloat(f));
    }

    public float getUpperRightX() {
        return ((COSNumber) this.rectArray.get(2)).floatValue();
    }

    public void setUpperRightX(float f) {
        this.rectArray.set(2, (COSBase) new COSFloat(f));
    }

    public float getUpperRightY() {
        return ((COSNumber) this.rectArray.get(3)).floatValue();
    }

    public void setUpperRightY(float f) {
        this.rectArray.set(3, (COSBase) new COSFloat(f));
    }

    public float getWidth() {
        return getUpperRightX() - getLowerLeftX();
    }

    public float getHeight() {
        return getUpperRightY() - getLowerLeftY();
    }

    public void move(float f, float f2) {
        setUpperRightX(getUpperRightX() + f);
        setLowerLeftX(getLowerLeftX() + f);
        setUpperRightY(getUpperRightY() + f2);
        setLowerLeftY(getLowerLeftY() + f2);
    }

    public Path transform(Matrix matrix) {
        float lowerLeftX = getLowerLeftX();
        float lowerLeftY = getLowerLeftY();
        float upperRightX = getUpperRightX();
        float upperRightY = getUpperRightY();
        double d = lowerLeftX;
        double d2 = lowerLeftY;
        PointF transformPoint = matrix.transformPoint(d, d2);
        double d3 = upperRightX;
        PointF transformPoint2 = matrix.transformPoint(d3, d2);
        double d4 = upperRightY;
        PointF transformPoint3 = matrix.transformPoint(d3, d4);
        PointF transformPoint4 = matrix.transformPoint(d, d4);
        Path path = new Path();
        path.moveTo(transformPoint.x, transformPoint.y);
        path.lineTo(transformPoint2.x, transformPoint2.y);
        path.lineTo(transformPoint3.x, transformPoint3.y);
        path.lineTo(transformPoint4.x, transformPoint4.y);
        path.close();
        return path;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.rectArray;
    }

    public Path toGeneralPath() {
        float lowerLeftX = getLowerLeftX();
        float lowerLeftY = getLowerLeftY();
        float upperRightX = getUpperRightX();
        float upperRightY = getUpperRightY();
        Path path = new Path();
        path.moveTo(lowerLeftX, lowerLeftY);
        path.lineTo(upperRightX, lowerLeftY);
        path.lineTo(upperRightX, upperRightY);
        path.lineTo(lowerLeftX, upperRightY);
        path.close();
        return path;
    }

    public String toString() {
        return "[" + getLowerLeftX() + "," + getLowerLeftY() + "," + getUpperRightX() + "," + getUpperRightY() + "]";
    }
}
