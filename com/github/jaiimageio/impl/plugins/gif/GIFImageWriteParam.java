package com.github.jaiimageio.impl.plugins.gif;

import java.util.Locale;
import javax.imageio.ImageWriteParam;

/* compiled from: GIFImageWriter.java */
/* loaded from: classes3.dex */
class GIFImageWriteParam extends ImageWriteParam {
    /* JADX INFO: Access modifiers changed from: package-private */
    public GIFImageWriteParam(Locale locale) {
        super(locale);
        this.canWriteCompressed = true;
        this.canWriteProgressive = true;
        this.compressionTypes = new String[]{"LZW", "lzw"};
        this.compressionType = this.compressionTypes[0];
    }

    public void setCompressionMode(int i) {
        if (i == 0) {
            throw new UnsupportedOperationException("MODE_DISABLED is not supported.");
        }
        super.setCompressionMode(i);
    }
}
