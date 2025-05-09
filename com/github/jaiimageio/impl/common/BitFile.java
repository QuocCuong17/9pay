package com.github.jaiimageio.impl.common;

import java.io.IOException;
import javax.imageio.stream.ImageOutputStream;

/* loaded from: classes3.dex */
public class BitFile {
    boolean blocks_;
    ImageOutputStream output_;
    byte[] buffer_ = new byte[256];
    int index_ = 0;
    int bitsLeft_ = 8;

    public BitFile(ImageOutputStream imageOutputStream, boolean z) {
        this.blocks_ = false;
        this.output_ = imageOutputStream;
        this.blocks_ = z;
    }

    public void flush() throws IOException {
        int i = this.index_ + (this.bitsLeft_ == 8 ? 0 : 1);
        if (i > 0) {
            if (this.blocks_) {
                this.output_.write(i);
            }
            this.output_.write(this.buffer_, 0, i);
            this.buffer_[0] = 0;
            this.index_ = 0;
            this.bitsLeft_ = 8;
        }
    }

    public void writeBits(int i, int i2) throws IOException {
        do {
            int i3 = this.index_;
            if ((i3 == 254 && this.bitsLeft_ == 0) || i3 > 254) {
                if (this.blocks_) {
                    this.output_.write(255);
                }
                this.output_.write(this.buffer_, 0, 255);
                this.buffer_[0] = 0;
                this.index_ = 0;
                this.bitsLeft_ = 8;
            }
            int i4 = this.bitsLeft_;
            if (i2 <= i4) {
                if (this.blocks_) {
                    byte[] bArr = this.buffer_;
                    int i5 = this.index_;
                    bArr[i5] = (byte) (((i & ((1 << i2) - 1)) << (8 - i4)) | bArr[i5]);
                    this.bitsLeft_ = i4 - i2;
                } else {
                    byte[] bArr2 = this.buffer_;
                    int i6 = this.index_;
                    bArr2[i6] = (byte) (((i & ((1 << i2) - 1)) << (i4 - i2)) | bArr2[i6]);
                    this.bitsLeft_ = i4 - i2;
                }
                i2 = 0;
            } else if (this.blocks_) {
                byte[] bArr3 = this.buffer_;
                int i7 = this.index_;
                bArr3[i7] = (byte) (bArr3[i7] | ((((1 << i4) - 1) & i) << (8 - i4)));
                i >>= i4;
                i2 -= i4;
                int i8 = i7 + 1;
                this.index_ = i8;
                bArr3[i8] = 0;
                this.bitsLeft_ = 8;
            } else {
                byte[] bArr4 = this.buffer_;
                int i9 = this.index_;
                bArr4[i9] = (byte) (((i >>> (i2 - i4)) & ((1 << i4) - 1)) | bArr4[i9]);
                i2 -= i4;
                int i10 = i9 + 1;
                this.index_ = i10;
                bArr4[i10] = 0;
                this.bitsLeft_ = 8;
            }
        } while (i2 != 0);
    }
}
