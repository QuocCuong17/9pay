package org.apache.pdfbox.util;

import android.graphics.PointF;
import java.lang.reflect.Array;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.util.awt.AffineTransform;

/* loaded from: classes5.dex */
public final class Matrix implements Cloneable {
    static final float[] DEFAULT_SINGLE = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private float[] single;

    public Matrix() {
        float[] fArr = DEFAULT_SINGLE;
        float[] fArr2 = new float[fArr.length];
        this.single = fArr2;
        System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
    }

    public Matrix(COSArray cOSArray) {
        float[] fArr = new float[DEFAULT_SINGLE.length];
        this.single = fArr;
        fArr[0] = ((COSNumber) cOSArray.get(0)).floatValue();
        this.single[1] = ((COSNumber) cOSArray.get(1)).floatValue();
        this.single[3] = ((COSNumber) cOSArray.get(2)).floatValue();
        this.single[4] = ((COSNumber) cOSArray.get(3)).floatValue();
        this.single[6] = ((COSNumber) cOSArray.get(4)).floatValue();
        this.single[7] = ((COSNumber) cOSArray.get(5)).floatValue();
        this.single[8] = 1.0f;
    }

    public Matrix(float f, float f2, float f3, float f4, float f5, float f6) {
        float[] fArr = new float[DEFAULT_SINGLE.length];
        this.single = fArr;
        fArr[0] = f;
        fArr[1] = f2;
        fArr[3] = f3;
        fArr[4] = f4;
        fArr[6] = f5;
        fArr[7] = f6;
        fArr[8] = 1.0f;
    }

    public Matrix(AffineTransform affineTransform) {
        float[] fArr = DEFAULT_SINGLE;
        float[] fArr2 = new float[fArr.length];
        this.single = fArr2;
        System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
        this.single[0] = (float) affineTransform.getScaleX();
        this.single[1] = (float) affineTransform.getShearY();
        this.single[3] = (float) affineTransform.getShearX();
        this.single[4] = (float) affineTransform.getScaleY();
        this.single[6] = (float) affineTransform.getTranslateX();
        this.single[7] = (float) affineTransform.getTranslateY();
    }

    @Deprecated
    public void reset() {
        float[] fArr = DEFAULT_SINGLE;
        System.arraycopy(fArr, 0, this.single, 0, fArr.length);
    }

    public AffineTransform createAffineTransform() {
        float[] fArr = this.single;
        return new AffineTransform(fArr[0], fArr[1], fArr[3], fArr[4], fArr[6], fArr[7]);
    }

    @Deprecated
    public void setFromAffineTransform(AffineTransform affineTransform) {
        this.single[0] = (float) affineTransform.getScaleX();
        this.single[1] = (float) affineTransform.getShearY();
        this.single[3] = (float) affineTransform.getShearX();
        this.single[4] = (float) affineTransform.getScaleY();
        this.single[6] = (float) affineTransform.getTranslateX();
        this.single[7] = (float) affineTransform.getTranslateY();
    }

    public float getValue(int i, int i2) {
        return this.single[(i * 3) + i2];
    }

    public void setValue(int i, int i2, float f) {
        this.single[(i * 3) + i2] = f;
    }

    public float[][] getValues() {
        float[][] fArr = (float[][]) Array.newInstance((Class<?>) float.class, 3, 3);
        float[] fArr2 = fArr[0];
        float[] fArr3 = this.single;
        fArr2[0] = fArr3[0];
        fArr[0][1] = fArr3[1];
        fArr[0][2] = fArr3[2];
        fArr[1][0] = fArr3[3];
        fArr[1][1] = fArr3[4];
        fArr[1][2] = fArr3[5];
        fArr[2][0] = fArr3[6];
        fArr[2][1] = fArr3[7];
        fArr[2][2] = fArr3[8];
        return fArr;
    }

    @Deprecated
    public double[][] getValuesAsDouble() {
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, 3, 3);
        double[] dArr2 = dArr[0];
        float[] fArr = this.single;
        dArr2[0] = fArr[0];
        dArr[0][1] = fArr[1];
        dArr[0][2] = fArr[2];
        dArr[1][0] = fArr[3];
        dArr[1][1] = fArr[4];
        dArr[1][2] = fArr[5];
        dArr[2][0] = fArr[6];
        dArr[2][1] = fArr[7];
        dArr[2][2] = fArr[8];
        return dArr;
    }

    public void concatenate(Matrix matrix) {
        matrix.multiply(this, this);
    }

    public void translate(Vector vector) {
        concatenate(getTranslateInstance(vector.getX(), vector.getY()));
    }

    public void translate(float f, float f2) {
        concatenate(getTranslateInstance(f, f2));
    }

    public void scale(float f, float f2) {
        concatenate(getScaleInstance(f, f2));
    }

    public void rotate(double d) {
        concatenate(getRotateInstance(d, 0.0f, 0.0f));
    }

    public Matrix multiply(Matrix matrix) {
        return multiply(matrix, new Matrix());
    }

    public Matrix multiply(Matrix matrix, Matrix matrix2) {
        float[] fArr;
        Matrix matrix3 = matrix2 == null ? new Matrix() : matrix2;
        if (matrix != null && (fArr = matrix.single) != null) {
            float[] fArr2 = this.single;
            if (this == matrix3) {
                float[] fArr3 = new float[fArr2.length];
                System.arraycopy(fArr2, 0, fArr3, 0, fArr2.length);
                fArr2 = fArr3;
            }
            if (matrix == matrix3) {
                float[] fArr4 = matrix.single;
                fArr = new float[fArr4.length];
                System.arraycopy(fArr4, 0, fArr, 0, fArr4.length);
            }
            float[] fArr5 = matrix3.single;
            fArr5[0] = (fArr2[0] * fArr[0]) + (fArr2[1] * fArr[3]) + (fArr2[2] * fArr[6]);
            fArr5[1] = (fArr2[0] * fArr[1]) + (fArr2[1] * fArr[4]) + (fArr2[2] * fArr[7]);
            fArr5[2] = (fArr2[0] * fArr[2]) + (fArr2[1] * fArr[5]) + (fArr2[2] * fArr[8]);
            fArr5[3] = (fArr2[3] * fArr[0]) + (fArr2[4] * fArr[3]) + (fArr2[5] * fArr[6]);
            fArr5[4] = (fArr2[3] * fArr[1]) + (fArr2[4] * fArr[4]) + (fArr2[5] * fArr[7]);
            fArr5[5] = (fArr2[3] * fArr[2]) + (fArr2[4] * fArr[5]) + (fArr2[5] * fArr[8]);
            fArr5[6] = (fArr2[6] * fArr[0]) + (fArr2[7] * fArr[3]) + (fArr2[8] * fArr[6]);
            fArr5[7] = (fArr2[6] * fArr[1]) + (fArr2[7] * fArr[4]) + (fArr2[8] * fArr[7]);
            fArr5[8] = (fArr2[6] * fArr[2]) + (fArr2[7] * fArr[5]) + (fArr2[8] * fArr[8]);
        }
        return matrix3;
    }

    public void transform(PointF pointF) {
        float f = pointF.x;
        float f2 = pointF.y;
        float[] fArr = this.single;
        float f3 = fArr[0];
        float f4 = fArr[1];
        float f5 = fArr[3];
        float f6 = fArr[4];
        pointF.set((f3 * f) + (f5 * f2) + fArr[6], (f * f4) + (f2 * f6) + fArr[7]);
    }

    public PointF transformPoint(double d, double d2) {
        float[] fArr = this.single;
        float f = fArr[0];
        float f2 = fArr[1];
        return new PointF((float) ((f * d) + (fArr[3] * d2) + fArr[6]), (float) ((d * f2) + (d2 * fArr[4]) + fArr[7]));
    }

    public Vector transform(Vector vector) {
        float[] fArr = this.single;
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[3];
        float f4 = fArr[4];
        float f5 = fArr[6];
        float f6 = fArr[7];
        float x = vector.getX();
        float y = vector.getY();
        return new Vector((f * x) + (f3 * y) + f5, (x * f2) + (y * f4) + f6);
    }

    @Deprecated
    public Matrix extractScaling() {
        Matrix matrix = new Matrix();
        float[] fArr = matrix.single;
        float[] fArr2 = this.single;
        fArr[0] = fArr2[0];
        fArr[4] = fArr2[4];
        return matrix;
    }

    public static Matrix getScaleInstance(float f, float f2) {
        Matrix matrix = new Matrix();
        float[] fArr = matrix.single;
        fArr[0] = f;
        fArr[4] = f2;
        return matrix;
    }

    @Deprecated
    public Matrix extractTranslating() {
        Matrix matrix = new Matrix();
        float[] fArr = matrix.single;
        float[] fArr2 = this.single;
        fArr[6] = fArr2[6];
        fArr[7] = fArr2[7];
        return matrix;
    }

    public static Matrix getTranslatingInstance(float f, float f2) {
        return getTranslateInstance(f, f2);
    }

    public static Matrix getTranslateInstance(float f, float f2) {
        Matrix matrix = new Matrix();
        float[] fArr = matrix.single;
        fArr[6] = f;
        fArr[7] = f2;
        return matrix;
    }

    public static Matrix getRotateInstance(double d, float f, float f2) {
        float cos = (float) Math.cos(d);
        float sin = (float) Math.sin(d);
        Matrix matrix = new Matrix();
        float[] fArr = matrix.single;
        fArr[0] = cos;
        fArr[1] = sin;
        fArr[3] = -sin;
        fArr[4] = cos;
        fArr[6] = f;
        fArr[7] = f2;
        return matrix;
    }

    public static Matrix concatenate(Matrix matrix, Matrix matrix2) {
        Matrix clone = matrix.clone();
        clone.concatenate(matrix2);
        return clone;
    }

    public Matrix clone() {
        Matrix matrix = new Matrix();
        System.arraycopy(this.single, 0, matrix.single, 0, 9);
        return matrix;
    }

    public float getScalingFactorX() {
        float[] fArr = this.single;
        return (fArr[1] == 0.0f && fArr[3] == 0.0f) ? fArr[0] : (float) Math.sqrt(Math.pow(fArr[0], 2.0d) + Math.pow(this.single[1], 2.0d));
    }

    public float getScalingFactorY() {
        float[] fArr = this.single;
        return (fArr[1] == 0.0f && fArr[3] == 0.0f) ? fArr[4] : (float) Math.sqrt(Math.pow(fArr[3], 2.0d) + Math.pow(this.single[4], 2.0d));
    }

    public float getScaleX() {
        return this.single[0];
    }

    public float getShearY() {
        return this.single[1];
    }

    public float getShearX() {
        return this.single[3];
    }

    public float getScaleY() {
        return this.single[4];
    }

    public float getTranslateX() {
        return this.single[6];
    }

    public float getTranslateY() {
        return this.single[7];
    }

    @Deprecated
    public float getXPosition() {
        return this.single[6];
    }

    @Deprecated
    public float getYPosition() {
        return this.single[7];
    }

    public COSArray toCOSArray() {
        COSArray cOSArray = new COSArray();
        cOSArray.add((COSBase) new COSFloat(0.0f));
        cOSArray.add((COSBase) new COSFloat(1.0f));
        cOSArray.add((COSBase) new COSFloat(3.0f));
        cOSArray.add((COSBase) new COSFloat(4.0f));
        cOSArray.add((COSBase) new COSFloat(6.0f));
        cOSArray.add((COSBase) new COSFloat(7.0f));
        return cOSArray;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("");
        stringBuffer.append("[");
        stringBuffer.append(this.single[0] + ",");
        stringBuffer.append(this.single[1] + ",");
        stringBuffer.append(this.single[3] + ",");
        stringBuffer.append(this.single[4] + ",");
        stringBuffer.append(this.single[6] + ",");
        stringBuffer.append(this.single[7] + "]");
        return stringBuffer.toString();
    }
}
