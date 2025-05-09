package com.github.jaiimageio.impl.plugins.gif;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadataFormat;
import javax.imageio.metadata.IIOMetadataFormatImpl;
import org.apache.fontbox.afm.AFMParser;

/* loaded from: classes3.dex */
public class GIFStreamMetadataFormat extends IIOMetadataFormatImpl {
    private static IIOMetadataFormat instance;

    public boolean canNodeAppear(String str, ImageTypeSpecifier imageTypeSpecifier) {
        return true;
    }

    private GIFStreamMetadataFormat() {
        super("javax_imageio_gif_stream_1.0", 2);
        addElement(AFMParser.VERSION, "javax_imageio_gif_stream_1.0", 0);
        addAttribute(AFMParser.VERSION, "value", 0, true, null, Arrays.asList(GIFStreamMetadata.versionStrings));
        addElement("LogicalScreenDescriptor", "javax_imageio_gif_stream_1.0", 0);
        addAttribute("LogicalScreenDescriptor", "logicalScreenWidth", 2, true, null, "1", "65535", true, true);
        addAttribute("LogicalScreenDescriptor", "logicalScreenHeight", 2, true, null, "1", "65535", true, true);
        addAttribute("LogicalScreenDescriptor", "colorResolution", 2, true, null, "1", "8", true, true);
        addAttribute("LogicalScreenDescriptor", "pixelAspectRatio", 2, true, null, "0", "255", true, true);
        addElement("GlobalColorTable", "javax_imageio_gif_stream_1.0", 2, 256);
        addAttribute("GlobalColorTable", "sizeOfGlobalColorTable", 2, true, null, Arrays.asList(GIFStreamMetadata.colorTableSizes));
        addAttribute("GlobalColorTable", "backgroundColorIndex", 2, true, null, "0", "255", true, true);
        addBooleanAttribute("GlobalColorTable", "sortFlag", false, false);
        addElement("ColorTableEntry", "GlobalColorTable", 0);
        addAttribute("ColorTableEntry", FirebaseAnalytics.Param.INDEX, 2, true, null, "0", "255", true, true);
        addAttribute("ColorTableEntry", "red", 2, true, null, "0", "255", true, true);
        addAttribute("ColorTableEntry", "green", 2, true, null, "0", "255", true, true);
        addAttribute("ColorTableEntry", "blue", 2, true, null, "0", "255", true, true);
    }

    public static synchronized IIOMetadataFormat getInstance() {
        IIOMetadataFormat iIOMetadataFormat;
        synchronized (GIFStreamMetadataFormat.class) {
            if (instance == null) {
                instance = new GIFStreamMetadataFormat();
            }
            iIOMetadataFormat = instance;
        }
        return iIOMetadataFormat;
    }
}
