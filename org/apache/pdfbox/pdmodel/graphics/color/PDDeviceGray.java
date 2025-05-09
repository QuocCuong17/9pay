package org.apache.pdfbox.pdmodel.graphics.color;

import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public final class PDDeviceGray extends PDDeviceColorSpace {
    public static final PDDeviceGray INSTANCE = new PDDeviceGray();
    private final PDColor initialColor = new PDColor(new float[]{0.0f}, this);

    @Override // org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace
    public int getNumberOfComponents() {
        return 1;
    }

    private PDDeviceGray() {
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace
    public String getName() {
        return COSName.DEVICEGRAY.getName();
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace
    public float[] getDefaultDecode(int i) {
        return new float[]{0.0f, 1.0f};
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace
    public PDColor getInitialColor() {
        return this.initialColor;
    }

    @Override // org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace
    public float[] toRGB(float[] fArr) {
        return new float[]{fArr[0], fArr[0], fArr[0]};
    }
}
