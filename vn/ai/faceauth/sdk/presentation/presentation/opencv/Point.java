package vn.ai.faceauth.sdk.presentation.presentation.opencv;

import lmlf.ayxnhy.tfwhgw;

/* loaded from: classes6.dex */
public class Point {
    public double x;
    public double y;

    public Point() {
        this(0.0d, 0.0d);
    }

    public Point(double d, double d2) {
        this.x = d;
        this.y = d2;
    }

    public Point(double[] dArr) {
        this();
        set(dArr);
    }

    public Point clone() {
        return new Point(this.x, this.y);
    }

    public double dot(Point point) {
        return (this.y * point.y) + (this.x * point.x);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Point)) {
            return false;
        }
        Point point = (Point) obj;
        return this.x == point.x && this.y == point.y;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.x);
        int i = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
        long doubleToLongBits2 = Double.doubleToLongBits(this.y);
        return ((i + 31) * 31) + ((int) ((doubleToLongBits2 >>> 32) ^ doubleToLongBits2));
    }

    public void set(double[] dArr) {
        double d = 0.0d;
        if (dArr != null) {
            this.x = dArr.length > 0 ? dArr[0] : 0.0d;
            if (dArr.length > 1) {
                d = dArr[1];
            }
        } else {
            this.x = 0.0d;
        }
        this.y = d;
    }

    public String toString() {
        return tfwhgw.rnigpa(6) + this.x + tfwhgw.rnigpa(7) + this.y + tfwhgw.rnigpa(8);
    }
}
