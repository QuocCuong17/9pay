package org.apache.fontbox.afm;

/* loaded from: classes5.dex */
public class KernPair {
    private String firstKernCharacter;
    private String secondKernCharacter;
    private float x;
    private float y;

    public String getFirstKernCharacter() {
        return this.firstKernCharacter;
    }

    public void setFirstKernCharacter(String str) {
        this.firstKernCharacter = str;
    }

    public String getSecondKernCharacter() {
        return this.secondKernCharacter;
    }

    public void setSecondKernCharacter(String str) {
        this.secondKernCharacter = str;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float f) {
        this.x = f;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float f) {
        this.y = f;
    }
}
