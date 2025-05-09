package com.github.jaiimageio.impl.plugins.raw;

import java.util.Locale;
import javax.imageio.ImageWriteParam;

/* loaded from: classes3.dex */
public class RawImageWriteParam extends ImageWriteParam {
    public RawImageWriteParam(Locale locale) {
        super(locale);
        this.canWriteTiles = true;
    }
}
