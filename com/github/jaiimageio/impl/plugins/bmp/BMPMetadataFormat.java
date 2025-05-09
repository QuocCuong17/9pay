package com.github.jaiimageio.impl.plugins.bmp;

import androidx.exifinterface.media.ExifInterface;
import co.hyperverge.hypersnapsdk.analytics.mixpanel.Keys;
import com.google.firebase.analytics.FirebaseAnalytics;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadataFormat;
import javax.imageio.metadata.IIOMetadataFormatImpl;

/* loaded from: classes3.dex */
public class BMPMetadataFormat extends IIOMetadataFormatImpl {
    private static IIOMetadataFormat instance;

    public boolean canNodeAppear(String str, ImageTypeSpecifier imageTypeSpecifier) {
        return true;
    }

    private BMPMetadataFormat() {
        super(BMPMetadata.nativeMetadataFormatName, 2);
        addElement("ImageDescriptor", BMPMetadata.nativeMetadataFormatName, 0);
        addAttribute("ImageDescriptor", "bmpVersion", 0, true, null);
        addAttribute("ImageDescriptor", "width", 2, true, null, "0", "65535", true, true);
        addAttribute("ImageDescriptor", "height", 2, true, null, "1", "65535", true, true);
        addAttribute("ImageDescriptor", "bitsPerPixel", 2, true, null, "1", "65535", true, true);
        addAttribute("ImageDescriptor", "compression", 2, false, null);
        addAttribute("ImageDescriptor", Keys.IMAGE_SIZE, 2, true, null, "1", "65535", true, true);
        addElement("PixelsPerMeter", BMPMetadata.nativeMetadataFormatName, 0);
        addAttribute("PixelsPerMeter", "X", 2, false, null, "1", "65535", true, true);
        addAttribute("PixelsPerMeter", "Y", 2, false, null, "1", "65535", true, true);
        addElement("ColorsUsed", BMPMetadata.nativeMetadataFormatName, 0);
        addAttribute("ColorsUsed", "value", 2, true, null, "0", "65535", true, true);
        addElement("ColorsImportant", BMPMetadata.nativeMetadataFormatName, 0);
        addAttribute("ColorsImportant", "value", 2, false, null, "0", "65535", true, true);
        addElement("BI_BITFIELDS_Mask", BMPMetadata.nativeMetadataFormatName, 0);
        addAttribute("BI_BITFIELDS_Mask", "red", 2, false, null, "0", "65535", true, true);
        addAttribute("BI_BITFIELDS_Mask", "green", 2, false, null, "0", "65535", true, true);
        addAttribute("BI_BITFIELDS_Mask", "blue", 2, false, null, "0", "65535", true, true);
        addElement(ExifInterface.TAG_COLOR_SPACE, BMPMetadata.nativeMetadataFormatName, 0);
        addAttribute(ExifInterface.TAG_COLOR_SPACE, "value", 2, false, null, "0", "65535", true, true);
        addElement("LCS_CALIBRATED_RGB", BMPMetadata.nativeMetadataFormatName, 0);
        addAttribute("LCS_CALIBRATED_RGB", "redX", 4, false, null, "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB", "redY", 4, false, null, "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB", "redZ", 4, false, null, "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB", "greenX", 4, false, null, "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB", "greenY", 4, false, null, "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB", "greenZ", 4, false, null, "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB", "blueX", 4, false, null, "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB", "blueY", 4, false, null, "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB", "blueZ", 4, false, null, "0", "65535", true, true);
        addElement("LCS_CALIBRATED_RGB_GAMMA", BMPMetadata.nativeMetadataFormatName, 0);
        addAttribute("LCS_CALIBRATED_RGB_GAMMA", "red", 2, false, null, "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB_GAMMA", "green", 2, false, null, "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB_GAMMA", "blue", 2, false, null, "0", "65535", true, true);
        addElement("Intent", BMPMetadata.nativeMetadataFormatName, 0);
        addAttribute("Intent", "value", 2, false, null, "0", "65535", true, true);
        addElement("Palette", BMPMetadata.nativeMetadataFormatName, 2, 256);
        addAttribute("Palette", "sizeOfPalette", 2, true, null);
        addBooleanAttribute("Palette", "sortFlag", false, false);
        addElement("PaletteEntry", "Palette", 0);
        addAttribute("PaletteEntry", FirebaseAnalytics.Param.INDEX, 2, true, null, "0", "255", true, true);
        addAttribute("PaletteEntry", "red", 2, true, null, "0", "255", true, true);
        addAttribute("PaletteEntry", "green", 2, true, null, "0", "255", true, true);
        addAttribute("PaletteEntry", "blue", 2, true, null, "0", "255", true, true);
        addElement("CommentExtensions", BMPMetadata.nativeMetadataFormatName, 1, Integer.MAX_VALUE);
        addElement("CommentExtension", "CommentExtensions", 0);
        addAttribute("CommentExtension", "value", 0, true, null);
    }

    public static synchronized IIOMetadataFormat getInstance() {
        IIOMetadataFormat iIOMetadataFormat;
        synchronized (BMPMetadataFormat.class) {
            if (instance == null) {
                instance = new BMPMetadataFormat();
            }
            iIOMetadataFormat = instance;
        }
        return iIOMetadataFormat;
    }
}
