package org.apache.pdfbox.pdmodel.graphics.color;

import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public final class PDDeviceRGB extends PDDeviceColorSpace {
    public static final PDDeviceRGB INSTANCE = new PDDeviceRGB();
    private final PDColor initialColor = new PDColor(new float[]{0.0f, 0.0f, 0.0f}, this);

    @Override // org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace
    public int getNumberOfComponents() {
        return 3;
    }

    private PDDeviceRGB() {
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace
    public String getName() {
        return COSName.DEVICERGB.getName();
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace
    public float[] getDefaultDecode(int i) {
        return new float[]{0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f};
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace
    public PDColor getInitialColor() {
        return this.initialColor;
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace
    public float[] toRGB(float[] fArr) {
        return fArr.length == 3 ? fArr : this.initialColor.getComponents();
    }
}
