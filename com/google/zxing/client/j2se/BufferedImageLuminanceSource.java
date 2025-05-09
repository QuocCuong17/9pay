package com.google.zxing.client.j2se;

import com.google.zxing.LuminanceSource;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.WritableRaster;

/* loaded from: classes4.dex */
public final class BufferedImageLuminanceSource extends LuminanceSource {
    private static final double MINUS_45_IN_RADIANS = -0.7853981633974483d;
    private final BufferedImage image;
    private final int left;
    private final int top;

    @Override // com.google.zxing.LuminanceSource
    public boolean isCropSupported() {
        return true;
    }

    @Override // com.google.zxing.LuminanceSource
    public boolean isRotateSupported() {
        return true;
    }

    public BufferedImageLuminanceSource(BufferedImage bufferedImage) {
        this(bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
    }

    public BufferedImageLuminanceSource(BufferedImage bufferedImage, int i, int i2, int i3, int i4) {
        super(i3, i4);
        int i5;
        if (bufferedImage.getType() == 10) {
            this.image = bufferedImage;
        } else {
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            if (i + i3 > width || (i5 = i2 + i4) > height) {
                throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
            }
            BufferedImage bufferedImage2 = new BufferedImage(width, height, 10);
            this.image = bufferedImage2;
            WritableRaster raster = bufferedImage2.getRaster();
            int[] iArr = new int[i3];
            int i6 = i2;
            while (i6 < i5) {
                int i7 = i6;
                int[] iArr2 = iArr;
                bufferedImage.getRGB(i, i6, i3, 1, iArr, 0, width);
                for (int i8 = 0; i8 < i3; i8++) {
                    int i9 = iArr2[i8];
                    if (((-16777216) & i9) == 0) {
                        i9 = -1;
                    }
                    iArr2[i8] = ((((((i9 >> 16) & 255) * 306) + (((i9 >> 8) & 255) * 601)) + ((i9 & 255) * 117)) + 512) >> 10;
                }
                raster.setPixels(i, i7, i3, 1, iArr2);
                i6 = i7 + 1;
                iArr = iArr2;
            }
        }
        this.left = i;
        this.top = i2;
    }

    @Override // com.google.zxing.LuminanceSource
    public byte[] getRow(int i, byte[] bArr) {
        if (i < 0 || i >= getHeight()) {
            throw new IllegalArgumentException("Requested row is outside the image: " + i);
        }
        int width = getWidth();
        if (bArr == null || bArr.length < width) {
            bArr = new byte[width];
        }
        this.image.getRaster().getDataElements(this.left, this.top + i, width, 1, bArr);
        return bArr;
    }

    @Override // com.google.zxing.LuminanceSource
    public byte[] getMatrix() {
        int width = getWidth();
        int height = getHeight();
        byte[] bArr = new byte[width * height];
        this.image.getRaster().getDataElements(this.left, this.top, width, height, bArr);
        return bArr;
    }

    @Override // com.google.zxing.LuminanceSource
    public LuminanceSource crop(int i, int i2, int i3, int i4) {
        return new BufferedImageLuminanceSource(this.image, this.left + i, this.top + i2, i3, i4);
    }

    @Override // com.google.zxing.LuminanceSource
    public LuminanceSource rotateCounterClockwise() {
        int width = this.image.getWidth();
        int height = this.image.getHeight();
        AffineTransform affineTransform = new AffineTransform(0.0d, -1.0d, 1.0d, 0.0d, 0.0d, width);
        BufferedImage bufferedImage = new BufferedImage(height, width, 10);
        Graphics2D createGraphics = bufferedImage.createGraphics();
        createGraphics.drawImage(this.image, affineTransform, (ImageObserver) null);
        createGraphics.dispose();
        int width2 = getWidth();
        return new BufferedImageLuminanceSource(bufferedImage, this.top, width - (this.left + width2), getHeight(), width2);
    }

    @Override // com.google.zxing.LuminanceSource
    public LuminanceSource rotateCounterClockwise45() {
        int width = getWidth();
        int height = getHeight();
        int i = this.left + (width / 2);
        int i2 = this.top + (height / 2);
        AffineTransform rotateInstance = AffineTransform.getRotateInstance(MINUS_45_IN_RADIANS, i, i2);
        int max = Math.max(this.image.getWidth(), this.image.getHeight());
        BufferedImage bufferedImage = new BufferedImage(max, max, 10);
        Graphics2D createGraphics = bufferedImage.createGraphics();
        createGraphics.drawImage(this.image, rotateInstance, (ImageObserver) null);
        createGraphics.dispose();
        int max2 = Math.max(width, height) / 2;
        int max3 = Math.max(0, i - max2);
        int max4 = Math.max(0, i2 - max2);
        int i3 = max - 1;
        return new BufferedImageLuminanceSource(bufferedImage, max3, max4, Math.min(i3, i + max2) - max3, Math.min(i3, i2 + max2) - max4);
    }
}
