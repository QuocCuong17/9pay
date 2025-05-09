package com.github.jaiimageio.impl.plugins.wbmp;

import com.google.common.net.HttpHeaders;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadataFormat;
import javax.imageio.metadata.IIOMetadataFormatImpl;

/* loaded from: classes3.dex */
class WBMPMetadataFormat extends IIOMetadataFormatImpl {
    private static IIOMetadataFormat instance;

    public boolean canNodeAppear(String str, ImageTypeSpecifier imageTypeSpecifier) {
        return true;
    }

    private WBMPMetadataFormat() {
        super(WBMPMetadata.nativeMetadataFormatName, 2);
        addElement("ImageDescriptor", WBMPMetadata.nativeMetadataFormatName, 0);
        addAttribute("ImageDescriptor", "WBMPType", 2, true, "0");
        addAttribute("ImageDescriptor", HttpHeaders.WIDTH, 2, true, null, "0", "65535", true, true);
        addAttribute("ImageDescriptor", "Height", 2, true, null, "1", "65535", true, true);
    }

    public static synchronized IIOMetadataFormat getInstance() {
        IIOMetadataFormat iIOMetadataFormat;
        synchronized (WBMPMetadataFormat.class) {
            if (instance == null) {
                instance = new WBMPMetadataFormat();
            }
            iIOMetadataFormat = instance;
        }
        return iIOMetadataFormat;
    }
}
