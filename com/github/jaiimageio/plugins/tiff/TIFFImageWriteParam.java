package com.github.jaiimageio.plugins.tiff;

import com.github.jaiimageio.impl.plugins.tiff.TIFFImageWriter;
import java.util.Locale;
import javax.imageio.ImageWriteParam;

/* loaded from: classes3.dex */
public class TIFFImageWriteParam extends ImageWriteParam {
    private boolean appendedCompressionType;
    TIFFColorConverter colorConverter;
    TIFFCompressor compressor;
    int photometricInterpretation;

    public TIFFImageWriteParam(Locale locale) {
        super(locale);
        this.compressor = null;
        this.colorConverter = null;
        this.appendedCompressionType = false;
        this.canWriteCompressed = true;
        this.canWriteTiles = true;
        this.compressionTypes = TIFFImageWriter.TIFFCompressionTypes;
    }

    public boolean isCompressionLossless() {
        if (getCompressionMode() != 2) {
            throw new IllegalStateException("Compression mode not MODE_EXPLICIT!");
        }
        if (this.compressionType == null) {
            throw new IllegalStateException("No compression type set!");
        }
        if (this.compressor != null && this.compressionType.equals(this.compressor.getCompressionType())) {
            return this.compressor.isCompressionLossless();
        }
        for (int i = 0; i < this.compressionTypes.length; i++) {
            if (this.compressionType.equals(this.compressionTypes[i])) {
                return TIFFImageWriter.isCompressionLossless[i];
            }
        }
        return false;
    }

    public void setTIFFCompressor(TIFFCompressor tIFFCompressor) {
        boolean z;
        if (getCompressionMode() != 2) {
            throw new IllegalStateException("Compression mode not MODE_EXPLICIT!");
        }
        this.compressor = tIFFCompressor;
        if (this.appendedCompressionType) {
            int length = this.compressionTypes.length - 1;
            String[] strArr = new String[length];
            System.arraycopy(this.compressionTypes, 0, strArr, 0, length);
            this.compressionTypes = strArr;
            this.appendedCompressionType = false;
        }
        if (tIFFCompressor != null) {
            String compressionType = tIFFCompressor.getCompressionType();
            int length2 = this.compressionTypes.length;
            int i = 0;
            while (true) {
                if (i >= length2) {
                    z = true;
                    break;
                } else {
                    if (compressionType.equals(this.compressionTypes[i])) {
                        z = false;
                        break;
                    }
                    i++;
                }
            }
            if (z) {
                String[] strArr2 = new String[length2 + 1];
                System.arraycopy(this.compressionTypes, 0, strArr2, 0, length2);
                strArr2[length2] = compressionType;
                this.compressionTypes = strArr2;
                this.appendedCompressionType = true;
            }
        }
    }

    public TIFFCompressor getTIFFCompressor() {
        if (getCompressionMode() != 2) {
            throw new IllegalStateException("Compression mode not MODE_EXPLICIT!");
        }
        return this.compressor;
    }

    public void setColorConverter(TIFFColorConverter tIFFColorConverter, int i) {
        this.colorConverter = tIFFColorConverter;
        this.photometricInterpretation = i;
    }

    public TIFFColorConverter getColorConverter() {
        return this.colorConverter;
    }

    public int getPhotometricInterpretation() {
        if (this.colorConverter == null) {
            throw new IllegalStateException("Color converter not set!");
        }
        return this.photometricInterpretation;
    }

    public void unsetColorConverter() {
        this.colorConverter = null;
    }
}
