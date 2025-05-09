package org.apache.fontbox.util;

import java.util.List;

/* loaded from: classes5.dex */
public class BoundingBox {
    private float lowerLeftX;
    private float lowerLeftY;
    private float upperRightX;
    private float upperRightY;

    public BoundingBox() {
    }

    public BoundingBox(float f, float f2, float f3, float f4) {
        this.lowerLeftX = f;
        this.lowerLeftY = f2;
        this.upperRightX = f3;
        this.upperRightY = f4;
    }

    public BoundingBox(List<Number> list) {
        this.lowerLeftX = list.get(0).floatValue();
        this.lowerLeftY = list.get(1).floatValue();
        this.upperRightX = list.get(2).floatValue();
        this.upperRightY = list.get(3).floatValue();
    }

    public float getLowerLeftX() {
        return this.lowerLeftX;
    }

    public void setLowerLeftX(float f) {
        this.lowerLeftX = f;
    }

    public float getLowerLeftY() {
        return this.lowerLeftY;
    }

    public void setLowerLeftY(float f) {
        this.lowerLeftY = f;
    }

    public float getUpperRightX() {
        return this.upperRightX;
    }

    public void setUpperRightX(float f) {
        this.upperRightX = f;
    }

    public float getUpperRightY() {
        return this.upperRightY;
    }

    public void setUpperRightY(float f) {
        this.upperRightY = f;
    }

    public float getWidth() {
        return getUpperRightX() - getLowerLeftX();
    }

    public float getHeight() {
        return getUpperRightY() - getLowerLeftY();
    }

    public boolean contains(float f, float f2) {
        return f >= this.lowerLeftX && f <= this.upperRightX && f2 >= this.lowerLeftY && f2 <= this.upperRightY;
    }

    public String toString() {
        return "[" + getLowerLeftX() + "," + getLowerLeftY() + "," + getUpperRightX() + "," + getUpperRightY() + "]";
    }
}
