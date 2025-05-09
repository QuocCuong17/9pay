package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.plugins.tiff.TIFFDecompressor;
import java.io.IOException;

/* loaded from: classes3.dex */
public class TIFFNullDecompressor extends TIFFDecompressor {
    private static final boolean DEBUG = false;
    private boolean isReadActiveOnly = false;
    private int originalSrcHeight;
    private int originalSrcMinX;
    private int originalSrcMinY;
    private int originalSrcWidth;

    @Override // com.github.jaiimageio.plugins.tiff.TIFFDecompressor
    public void beginDecoding() {
        int i = 0;
        for (int i2 = 0; i2 < this.bitsPerSample.length; i2++) {
            i += this.bitsPerSample[i2];
        }
        if ((this.activeSrcMinX != this.srcMinX || this.activeSrcMinY != this.srcMinY || this.activeSrcWidth != this.srcWidth || this.activeSrcHeight != this.srcHeight) && ((this.activeSrcMinX - this.srcMinX) * i) % 8 == 0) {
            this.isReadActiveOnly = true;
            this.originalSrcMinX = this.srcMinX;
            this.originalSrcMinY = this.srcMinY;
            this.originalSrcWidth = this.srcWidth;
            this.originalSrcHeight = this.srcHeight;
            this.srcMinX = this.activeSrcMinX;
            this.srcMinY = this.activeSrcMinY;
            this.srcWidth = this.activeSrcWidth;
            this.srcHeight = this.activeSrcHeight;
        } else {
            this.isReadActiveOnly = false;
        }
        super.beginDecoding();
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFDecompressor
    public void decode() throws IOException {
        super.decode();
        if (this.isReadActiveOnly) {
            this.srcMinX = this.originalSrcMinX;
            this.srcMinY = this.originalSrcMinY;
            this.srcWidth = this.originalSrcWidth;
            this.srcHeight = this.originalSrcHeight;
            this.isReadActiveOnly = false;
        }
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFDecompressor
    public void decodeRaw(byte[] bArr, int i, int i2, int i3) throws IOException {
        int i4 = 0;
        if (this.isReadActiveOnly) {
            int i5 = ((this.activeSrcWidth * i2) + 7) / 8;
            int i6 = (((this.originalSrcWidth * i2) + 7) / 8) - i5;
            this.stream.seek(this.offset + ((this.activeSrcMinY - this.originalSrcMinY) * r2) + (((this.activeSrcMinX - this.originalSrcMinX) * i2) / 8));
            int i7 = this.activeSrcHeight - 1;
            while (i4 < this.activeSrcHeight) {
                this.stream.read(bArr, i, i5);
                i += i3;
                if (i4 != i7) {
                    this.stream.skipBytes(i6);
                }
                i4++;
            }
            return;
        }
        this.stream.seek(this.offset);
        int i8 = ((this.srcWidth * i2) + 7) / 8;
        if (i8 == i3) {
            this.stream.read(bArr, i, i8 * this.srcHeight);
            return;
        }
        while (i4 < this.srcHeight) {
            this.stream.read(bArr, i, i8);
            i += i3;
            i4++;
        }
    }
}
