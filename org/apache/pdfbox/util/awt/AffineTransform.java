package org.apache.pdfbox.util.awt;

import android.graphics.Matrix;
import android.graphics.PointF;

/* loaded from: classes5.dex */
public class AffineTransform {
    private double m00;
    private double m01;
    private double m02;
    private double m10;
    private double m11;
    private double m12;

    public AffineTransform() {
        this.m11 = 1.0d;
        this.m00 = 1.0d;
    }

    public AffineTransform(AffineTransform affineTransform) {
        setTransform(affineTransform);
    }

    public AffineTransform(double d, double d2, double d3, double d4, double d5, double d6) {
        this.m00 = d;
        this.m10 = d2;
        this.m01 = d3;
        this.m11 = d4;
        this.m02 = d5;
        this.m12 = d6;
    }

    public AffineTransform(double[] dArr) {
        this.m00 = dArr[0];
        this.m10 = dArr[1];
        this.m01 = dArr[2];
        this.m11 = dArr[3];
        if (dArr.length >= 6) {
            this.m02 = dArr[4];
            this.m12 = dArr[5];
        }
    }

    public AffineTransform(Matrix matrix) {
        matrix.getValues(new float[9]);
        this.m00 = r0[0];
        this.m01 = r0[1];
        this.m02 = r0[2];
        this.m10 = r0[3];
        this.m11 = r0[4];
        this.m12 = r0[5];
    }

    public static AffineTransform getTranslateInstance(double d, double d2) {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.m02 = d;
        affineTransform.m12 = d2;
        return affineTransform;
    }

    public static AffineTransform getScaleInstance(double d, double d2) {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.setToScale(d, d2);
        return affineTransform;
    }

    public void setTransform(AffineTransform affineTransform) {
        this.m00 = affineTransform.m00;
        this.m01 = affineTransform.m01;
        this.m02 = affineTransform.m02;
        this.m10 = affineTransform.m10;
        this.m11 = affineTransform.m11;
        this.m12 = affineTransform.m12;
    }

    public double getScaleX() {
        return this.m00;
    }

    public double getShearY() {
        return this.m10;
    }

    public double getShearX() {
        return this.m01;
    }

    public double getScaleY() {
        return this.m11;
    }

    public double getTranslateX() {
        return this.m02;
    }

    public double getTranslateY() {
        return this.m12;
    }

    public void getMatrix(double[] dArr) {
        dArr[0] = this.m00;
        dArr[1] = this.m10;
        dArr[2] = this.m01;
        dArr[3] = this.m11;
        dArr[4] = this.m02;
        dArr[5] = this.m12;
    }

    public PointF transform(PointF pointF, PointF pointF2) {
        if (pointF2 == null) {
            pointF2 = new PointF();
        }
        double d = pointF.x;
        double d2 = pointF.y;
        pointF2.set((float) ((this.m00 * d) + (this.m01 * d2) + this.m02), (float) ((this.m10 * d) + (this.m11 * d2) + this.m12));
        return pointF2;
    }

    public void transform(double[] dArr, int i, double[] dArr2, int i2, int i3) {
        if (dArr == dArr2 && i2 > i && i3 > 1) {
            int i4 = i3 * 2;
            if (i + i4 > i2) {
                double[] dArr3 = new double[i4];
                System.arraycopy(dArr, i, dArr3, 0, i4);
                dArr = dArr3;
            }
        }
        while (true) {
            i3--;
            if (i3 < 0) {
                return;
            }
            int i5 = i + 1;
            double d = dArr[i];
            i = i5 + 1;
            double d2 = dArr[i5];
            int i6 = i2 + 1;
            dArr2[i2] = (this.m00 * d) + (this.m01 * d2) + this.m02;
            i2 = i6 + 1;
            dArr2[i6] = (this.m10 * d) + (this.m11 * d2) + this.m12;
        }
    }

    public void transform(float[] fArr, int i, float[] fArr2, int i2, int i3) {
        float[] fArr3 = fArr;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        if (fArr3 == fArr2 && i5 > i4 && i6 > 1) {
            int i7 = i6 * 2;
            if (i4 + i7 > i5) {
                float[] fArr4 = new float[i7];
                System.arraycopy(fArr3, i4, fArr4, 0, i7);
                fArr3 = fArr4;
            }
        }
        while (true) {
            i6--;
            if (i6 < 0) {
                return;
            }
            int i8 = i4 + 1;
            int i9 = i5 + 1;
            double d = fArr3[i4];
            float[] fArr5 = fArr3;
            double d2 = fArr3[i8];
            fArr2[i5] = (float) ((this.m00 * d) + (this.m01 * d2) + this.m02);
            i5 = i9 + 1;
            fArr2[i9] = (float) ((this.m10 * d) + (this.m11 * d2) + this.m12);
            fArr3 = fArr5;
            i4 = i8 + 1;
        }
    }

    public void scale(double d, double d2) {
        this.m00 *= d;
        this.m01 *= d2;
        this.m10 *= d;
        this.m11 *= d2;
    }

    public void translate(double d, double d2) {
        this.m02 += (this.m00 * d) + (this.m01 * d2);
        this.m12 += (d * this.m10) + (d2 * this.m11);
    }

    public float[] transform(float[] fArr, float[] fArr2) {
        if (fArr2 == null) {
            fArr2 = new float[2];
        }
        double d = fArr[0];
        double d2 = fArr[1];
        float f = (float) ((this.m00 * d) + (this.m01 * d2) + this.m02);
        float f2 = (float) ((this.m10 * d) + (this.m11 * d2) + this.m12);
        fArr2[0] = f;
        fArr2[1] = f2;
        return fArr2;
    }

    public void rotate(double d) {
        double cos = Math.cos(d);
        double sin = Math.sin(d);
        double d2 = this.m00;
        double d3 = this.m01;
        double d4 = (d2 * cos) + (d3 * sin);
        double d5 = -sin;
        double d6 = (d2 * d5) + (d3 * cos);
        double d7 = this.m10;
        double d8 = this.m11;
        this.m00 = d4;
        this.m01 = d6;
        this.m10 = (d7 * cos) + (sin * d8);
        this.m11 = (d7 * d5) + (d8 * cos);
    }

    public void setToScale(double d, double d2) {
        this.m00 = d;
        this.m12 = 0.0d;
        this.m10 = 0.0d;
        this.m02 = 0.0d;
        this.m01 = 0.0d;
        this.m11 = d2;
    }

    public boolean isIdentity() {
        return this.m00 == 1.0d && this.m01 == 0.0d && this.m02 == 0.0d && this.m10 == 0.0d && this.m11 == 1.0d && this.m12 == 0.0d;
    }

    public Matrix toMatrix() {
        Matrix matrix = new Matrix();
        matrix.setValues(new float[]{(float) this.m00, (float) this.m01, (float) this.m02, (float) this.m10, (float) this.m11, (float) this.m12, 0.0f, 0.0f, 1.0f});
        return matrix;
    }
}
