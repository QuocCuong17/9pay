package com.github.jaiimageio.impl.plugins.png;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadataFormat;
import javax.imageio.metadata.IIOMetadataFormatImpl;

/* loaded from: classes3.dex */
public class CLibPNGMetadataFormat extends IIOMetadataFormatImpl {
    private static String VALUE_0 = "0";
    private static String VALUE_1 = "1";
    private static String VALUE_12 = "12";
    private static String VALUE_23 = "23";
    private static String VALUE_255 = "255";
    private static String VALUE_31 = "31";
    private static String VALUE_59 = "59";
    private static String VALUE_60 = "60";
    private static String VALUE_MAX_16 = "65535";
    private static String VALUE_MAX_32 = "2147483647";
    private static IIOMetadataFormat instance;

    public boolean canNodeAppear(String str, ImageTypeSpecifier imageTypeSpecifier) {
        return true;
    }

    private CLibPNGMetadataFormat() {
        super(CLibPNGMetadata.nativeMetadataFormatName, 2);
        addElement("IHDR", CLibPNGMetadata.nativeMetadataFormatName, 0);
        addAttribute("IHDR", "width", 2, true, null, VALUE_1, VALUE_MAX_32, true, true);
        addAttribute("IHDR", "height", 2, true, null, VALUE_1, VALUE_MAX_32, true, true);
        addAttribute("IHDR", "bitDepth", 2, true, null, Arrays.asList(CLibPNGMetadata.IHDR_bitDepths));
        addAttribute("IHDR", "colorType", 0, true, null, Arrays.asList("Grayscale", "RGB", "Palette", "GrayAlpha", "RGBAlpha"));
        addAttribute("IHDR", "compressionMethod", 0, true, null, Arrays.asList(CLibPNGMetadata.IHDR_compressionMethodNames));
        addAttribute("IHDR", "filterMethod", 0, true, null, Arrays.asList(CLibPNGMetadata.IHDR_filterMethodNames));
        addAttribute("IHDR", "interlaceMethod", 0, true, null, Arrays.asList(CLibPNGMetadata.IHDR_interlaceMethodNames));
        addElement("PLTE", CLibPNGMetadata.nativeMetadataFormatName, 1, 256);
        addElement("PLTEEntry", "PLTE", 0);
        addAttribute("PLTEEntry", FirebaseAnalytics.Param.INDEX, 2, true, null, VALUE_0, VALUE_255, true, true);
        addAttribute("PLTEEntry", "red", 2, true, null, VALUE_0, VALUE_255, true, true);
        addAttribute("PLTEEntry", "green", 2, true, null, VALUE_0, VALUE_255, true, true);
        addAttribute("PLTEEntry", "blue", 2, true, null, VALUE_0, VALUE_255, true, true);
        addElement("bKGD", CLibPNGMetadata.nativeMetadataFormatName, 3);
        addElement("bKGD_Grayscale", "bKGD", 0);
        addAttribute("bKGD_Grayscale", "gray", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
        addElement("bKGD_RGB", "bKGD", 0);
        addAttribute("bKGD_RGB", "red", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
        addAttribute("bKGD_RGB", "green", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
        addAttribute("bKGD_RGB", "blue", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
        addElement("bKGD_Palette", "bKGD", 0);
        addAttribute("bKGD_Palette", FirebaseAnalytics.Param.INDEX, 2, true, null, VALUE_0, VALUE_255, true, true);
        addElement("cHRM", CLibPNGMetadata.nativeMetadataFormatName, 0);
        addAttribute("cHRM", "whitePointX", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
        addAttribute("cHRM", "whitePointY", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
        addAttribute("cHRM", "redX", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
        addAttribute("cHRM", "redY", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
        addAttribute("cHRM", "greenX", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
        addAttribute("cHRM", "greenY", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
        addAttribute("cHRM", "blueX", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
        addAttribute("cHRM", "blueY", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
        addElement("gAMA", CLibPNGMetadata.nativeMetadataFormatName, 0);
        addAttribute("gAMA", "value", 2, true, null, VALUE_0, VALUE_MAX_32, true, true);
        addElement("hIST", CLibPNGMetadata.nativeMetadataFormatName, 1, 256);
        addElement("hISTEntry", "hIST", 0);
        addAttribute("hISTEntry", FirebaseAnalytics.Param.INDEX, 2, true, null, VALUE_0, VALUE_255, true, true);
        addAttribute("hISTEntry", "value", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
        addElement("iCCP", CLibPNGMetadata.nativeMetadataFormatName, 0);
        addAttribute("iCCP", "profileName", 0, true, null);
        addAttribute("iCCP", "compressionMethod", 0, true, null, Arrays.asList(CLibPNGMetadata.iCCP_compressionMethodNames));
        addObjectValue("iCCP", Byte.TYPE, 0, Integer.MAX_VALUE);
        addElement("iTXt", CLibPNGMetadata.nativeMetadataFormatName, 1, Integer.MAX_VALUE);
        addElement("iTXtEntry", "iTXt", 0);
        addAttribute("iTXtEntry", "keyword", 0, true, null);
        addBooleanAttribute("iTXtEntry", "compressionFlag", false, false);
        addAttribute("iTXtEntry", "compressionMethod", 0, true, null);
        addAttribute("iTXtEntry", "languageTag", 0, true, null);
        addAttribute("iTXtEntry", "translatedKeyword", 0, true, null);
        addAttribute("iTXtEntry", "text", 0, true, null);
        addElement("pHYS", CLibPNGMetadata.nativeMetadataFormatName, 0);
        addAttribute("pHYS", "pixelsPerUnitXAxis", 2, true, null, VALUE_0, VALUE_MAX_32, true, true);
        addAttribute("pHYS", "pixelsPerUnitYAxis", 2, true, null, VALUE_0, VALUE_MAX_32, true, true);
        addAttribute("pHYS", "unitSpecifier", 0, true, null, Arrays.asList(CLibPNGMetadata.unitSpecifierNames));
        addElement("sBIT", CLibPNGMetadata.nativeMetadataFormatName, 3);
        addElement("sBIT_Grayscale", "sBIT", 0);
        addAttribute("sBIT_Grayscale", "gray", 2, true, null, VALUE_0, VALUE_255, true, true);
        addElement("sBIT_GrayAlpha", "sBIT", 0);
        addAttribute("sBIT_GrayAlpha", "gray", 2, true, null, VALUE_0, VALUE_255, true, true);
        addAttribute("sBIT_GrayAlpha", "alpha", 2, true, null, VALUE_0, VALUE_255, true, true);
        addElement("sBIT_RGB", "sBIT", 0);
        addAttribute("sBIT_RGB", "red", 2, true, null, VALUE_0, VALUE_255, true, true);
        addAttribute("sBIT_RGB", "green", 2, true, null, VALUE_0, VALUE_255, true, true);
        addAttribute("sBIT_RGB", "blue", 2, true, null, VALUE_0, VALUE_255, true, true);
        addElement("sBIT_RGBAlpha", "sBIT", 0);
        addAttribute("sBIT_RGBAlpha", "red", 2, true, null, VALUE_0, VALUE_255, true, true);
        addAttribute("sBIT_RGBAlpha", "green", 2, true, null, VALUE_0, VALUE_255, true, true);
        addAttribute("sBIT_RGBAlpha", "blue", 2, true, null, VALUE_0, VALUE_255, true, true);
        addAttribute("sBIT_RGBAlpha", "alpha", 2, true, null, VALUE_0, VALUE_255, true, true);
        addElement("sBIT_Palette", "sBIT", 0);
        addAttribute("sBIT_Palette", "red", 2, true, null, VALUE_0, VALUE_255, true, true);
        addAttribute("sBIT_Palette", "green", 2, true, null, VALUE_0, VALUE_255, true, true);
        addAttribute("sBIT_Palette", "blue", 2, true, null, VALUE_0, VALUE_255, true, true);
        addElement("sPLT", CLibPNGMetadata.nativeMetadataFormatName, 1, 256);
        addElement("sPLTEntry", "sPLT", 0);
        addAttribute("sPLTEntry", FirebaseAnalytics.Param.INDEX, 2, true, null, VALUE_0, VALUE_255, true, true);
        addAttribute("sPLTEntry", "red", 2, true, null, VALUE_0, VALUE_255, true, true);
        addAttribute("sPLTEntry", "green", 2, true, null, VALUE_0, VALUE_255, true, true);
        addAttribute("sPLTEntry", "blue", 2, true, null, VALUE_0, VALUE_255, true, true);
        addAttribute("sPLTEntry", "alpha", 2, true, null, VALUE_0, VALUE_255, true, true);
        addElement("sRGB", CLibPNGMetadata.nativeMetadataFormatName, 0);
        addAttribute("sRGB", "renderingIntent", 0, true, null, Arrays.asList(CLibPNGMetadata.renderingIntentNames));
        addElement("tEXt", CLibPNGMetadata.nativeMetadataFormatName, 1, Integer.MAX_VALUE);
        addElement("tEXtEntry", "tEXt", 0);
        addAttribute("tEXtEntry", "keyword", 0, true, null);
        addAttribute("tEXtEntry", "value", 0, true, null);
        addElement("tIME", CLibPNGMetadata.nativeMetadataFormatName, 0);
        addAttribute("tIME", "year", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
        addAttribute("tIME", "month", 2, true, null, VALUE_1, VALUE_12, true, true);
        addAttribute("tIME", "day", 2, true, null, VALUE_1, VALUE_31, true, true);
        addAttribute("tIME", "hour", 2, true, null, VALUE_0, VALUE_23, true, true);
        addAttribute("tIME", "minute", 2, true, null, VALUE_0, VALUE_59, true, true);
        addAttribute("tIME", "second", 2, true, null, VALUE_0, VALUE_60, true, true);
        addElement("tRNS", CLibPNGMetadata.nativeMetadataFormatName, 3);
        addElement("tRNS_Grayscale", "tRNS", 0);
        addAttribute("tRNS_Grayscale", "gray", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
        addElement("tRNS_RGB", "tRNS", 0);
        addAttribute("tRNS_RGB", "red", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
        addAttribute("tRNS_RGB", "green", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
        addAttribute("tRNS_RGB", "blue", 2, true, null, VALUE_0, VALUE_MAX_16, true, true);
        addElement("tRNS_Palette", "tRNS", 0);
        addAttribute("tRNS_Palette", FirebaseAnalytics.Param.INDEX, 2, true, null, VALUE_0, VALUE_255, true, true);
        addAttribute("tRNS_Palette", "alpha", 2, true, null, VALUE_0, VALUE_255, true, true);
        addElement("zTXt", CLibPNGMetadata.nativeMetadataFormatName, 1, Integer.MAX_VALUE);
        addElement("zTXtEntry", "zTXt", 0);
        addAttribute("zTXtEntry", "keyword", 0, true, null);
        addAttribute("zTXtEntry", "compressionMethod", 0, true, null, Arrays.asList(CLibPNGMetadata.zTXt_compressionMethodNames));
        addAttribute("zTXtEntry", "text", 0, true, null);
        addElement("UnknownChunks", CLibPNGMetadata.nativeMetadataFormatName, 1, Integer.MAX_VALUE);
        addElement("UnknownChunk", "UnknownChunks", 0);
        addAttribute("UnknownChunk", "type", 0, true, null);
        addObjectValue("UnknownChunk", Byte.TYPE, 0, Integer.MAX_VALUE);
    }

    public static synchronized IIOMetadataFormat getInstance() {
        IIOMetadataFormat iIOMetadataFormat;
        synchronized (CLibPNGMetadataFormat.class) {
            if (instance == null) {
                instance = new CLibPNGMetadataFormat();
            }
            iIOMetadataFormat = instance;
        }
        return iIOMetadataFormat;
    }
}
