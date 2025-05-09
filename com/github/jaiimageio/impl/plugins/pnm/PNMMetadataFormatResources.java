package com.github.jaiimageio.impl.plugins.pnm;

import com.google.common.net.HttpHeaders;
import java.util.ListResourceBundle;

/* loaded from: classes3.dex */
public class PNMMetadataFormatResources extends ListResourceBundle {
    static final Object[][] contents = {new Object[]{"FormatName", "The format name. One of PBM, PGM or PPM"}, new Object[]{"Variant", "The variant: RAWBITS or ASCII"}, new Object[]{HttpHeaders.WIDTH, "The image width"}, new Object[]{"Height", "The image height"}, new Object[]{"MaximumSample", "The maximum bit depth of one sample."}, new Object[]{"Comment", "A comment."}};

    @Override // java.util.ListResourceBundle
    protected Object[][] getContents() {
        return contents;
    }
}
