package org.apache.pdfbox.util;

/* loaded from: classes5.dex */
public final class Vector {
    private final float x;
    private final float y;

    public Vector(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public Vector scale(float f) {
        return new Vector(this.x * f, this.y * f);
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
