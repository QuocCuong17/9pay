package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.github.jaiimageio.plugins.tiff.TIFFDecompressor;
import com.github.jaiimageio.plugins.tiff.TIFFField;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;

/* loaded from: classes3.dex */
public class TIFFJPEGDecompressor extends TIFFDecompressor {
    private static final boolean DEBUG = false;
    protected static final int EOI = 217;
    protected static final int SOI = 216;
    protected ImageReadParam JPEGParam;
    protected ImageReader JPEGReader = null;
    protected boolean hasJPEGTables = false;
    protected byte[] tables = null;
    private byte[] data = new byte[0];

    @Override // com.github.jaiimageio.plugins.tiff.TIFFDecompressor
    public void beginDecoding() {
        if (this.JPEGReader == null) {
            Iterator imageReadersByFormatName = ImageIO.getImageReadersByFormatName("jpeg");
            if (!imageReadersByFormatName.hasNext()) {
                throw new IllegalStateException("No JPEG readers found!");
            }
            ImageReader imageReader = (ImageReader) imageReadersByFormatName.next();
            this.JPEGReader = imageReader;
            this.JPEGParam = imageReader.getDefaultReadParam();
        }
        TIFFField tIFFField = ((TIFFImageMetadata) this.metadata).getTIFFField(BaselineTIFFTagSet.TAG_JPEG_TABLES);
        if (tIFFField != null) {
            this.hasJPEGTables = true;
            this.tables = tIFFField.getAsBytes();
        } else {
            this.hasJPEGTables = false;
        }
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFDecompressor
    public void decodeRaw(byte[] bArr, int i, int i2, int i3) throws IOException {
        MemoryCacheImageInputStream memoryCacheImageInputStream;
        this.stream.seek(this.offset);
        if (this.hasJPEGTables) {
            int length = this.tables.length + this.byteCount;
            if (this.data.length < length) {
                this.data = new byte[length];
            }
            byte[] bArr2 = this.tables;
            int length2 = bArr2.length;
            int length3 = bArr2.length - 2;
            while (true) {
                if (length3 <= 0) {
                    break;
                }
                byte[] bArr3 = this.tables;
                if ((bArr3[length3] & 255) == 255 && (bArr3[length3 + 1] & 255) == EOI) {
                    length2 = length3;
                    break;
                }
                length3--;
            }
            System.arraycopy(this.tables, 0, this.data, 0, length2);
            byte read = (byte) this.stream.read();
            byte read2 = (byte) this.stream.read();
            if ((read & 255) != 255 || (read2 & 255) != SOI) {
                byte[] bArr4 = this.data;
                int i4 = length2 + 1;
                bArr4[length2] = read;
                length2 = i4 + 1;
                bArr4[i4] = read2;
            }
            this.stream.readFully(this.data, length2, this.byteCount - 2);
            memoryCacheImageInputStream = new MemoryCacheImageInputStream(new ByteArrayInputStream(this.data));
        } else {
            memoryCacheImageInputStream = this.stream;
        }
        this.JPEGReader.setInput(memoryCacheImageInputStream, false, true);
        this.JPEGParam.setDestination(this.rawImage);
        this.JPEGReader.read(0, this.JPEGParam);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        this.JPEGReader.dispose();
    }
}
