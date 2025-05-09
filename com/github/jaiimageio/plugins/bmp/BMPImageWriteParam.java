package com.github.jaiimageio.plugins.bmp;

import com.github.jaiimageio.impl.plugins.bmp.BMPConstants;
import java.util.Locale;
import javax.imageio.ImageWriteParam;

/* loaded from: classes3.dex */
public class BMPImageWriteParam extends ImageWriteParam {
    public static final int VERSION_2 = 0;
    public static final int VERSION_3 = 1;
    public static final int VERSION_4 = 2;
    public static final int VERSION_5 = 3;
    private boolean topDown;

    public int getVersion() {
        return 1;
    }

    public BMPImageWriteParam(Locale locale) {
        super(locale);
        this.topDown = false;
        this.compressionTypes = BMPConstants.compressionTypeNames;
        this.canWriteCompressed = true;
        this.compressionMode = 3;
        this.compressionType = this.compressionTypes[0];
    }

    public BMPImageWriteParam() {
        this(null);
    }

    public void setTopDown(boolean z) {
        this.topDown = z;
    }

    public boolean isTopDown() {
        return this.topDown;
    }

    public void setCompressionType(String str) {
        super.setCompressionType(str);
        if (str.equals("BI_RGB") || str.equals("BI_BITFIELDS") || !this.topDown) {
            return;
        }
        this.topDown = false;
    }
}
