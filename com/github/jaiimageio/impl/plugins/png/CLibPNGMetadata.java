package com.github.jaiimageio.impl.plugins.png;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.internal.ServerProtocol;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.sentry.protocol.Device;
import java.awt.image.IndexColorModel;
import java.awt.image.SampleModel;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import org.w3c.dom.Node;

/* loaded from: classes3.dex */
public class CLibPNGMetadata extends IIOMetadata implements Cloneable {
    public static final int PHYS_UNIT_METER = 1;
    public static final int PHYS_UNIT_UNKNOWN = 0;
    static final int PNG_COLOR_GRAY = 0;
    static final int PNG_COLOR_GRAY_ALPHA = 4;
    static final int PNG_COLOR_PALETTE = 3;
    static final int PNG_COLOR_RGB = 2;
    static final int PNG_COLOR_RGB_ALPHA = 6;
    protected static final String nativeMetadataFormatClassName = "com.github.jaiimageio.impl.plugins.png.CLibPNGMetadataFormat";
    public static final String nativeMetadataFormatName = "javax_imageio_png_1.0";
    public int IHDR_bitDepth;
    public int IHDR_colorType;
    public int IHDR_compressionMethod;
    public int IHDR_filterMethod;
    public int IHDR_height;
    public int IHDR_interlaceMethod;
    public boolean IHDR_present;
    public int IHDR_width;
    public byte[] PLTE_blue;
    public byte[] PLTE_green;
    public boolean PLTE_present;
    public byte[] PLTE_red;
    public int bKGD_blue;
    public int bKGD_colorType;
    public int bKGD_gray;
    public int bKGD_green;
    public int bKGD_index;
    public boolean bKGD_present;
    public int bKGD_red;
    public int cHRM_blueX;
    public int cHRM_blueY;
    public int cHRM_greenX;
    public int cHRM_greenY;
    public boolean cHRM_present;
    public int cHRM_redX;
    public int cHRM_redY;
    public int cHRM_whitePointX;
    public int cHRM_whitePointY;
    public int gAMA_gamma;
    public boolean gAMA_present;
    private boolean gotHeader;
    private boolean gotMetadata;
    public char[] hIST_histogram;
    public boolean hIST_present;
    public byte[] iCCP_compressedProfile;
    public int iCCP_compressionMethod;
    public boolean iCCP_present;
    public String iCCP_profileName;
    public ArrayList iTXt_compressionFlag;
    public ArrayList iTXt_compressionMethod;
    public ArrayList iTXt_keyword;
    public ArrayList iTXt_languageTag;
    public ArrayList iTXt_text;
    public ArrayList iTXt_translatedKeyword;
    public int pHYs_pixelsPerUnitXAxis;
    public int pHYs_pixelsPerUnitYAxis;
    public boolean pHYs_present;
    public int pHYs_unitSpecifier;
    public int sBIT_alphaBits;
    public int sBIT_blueBits;
    public int sBIT_colorType;
    public int sBIT_grayBits;
    public int sBIT_greenBits;
    public boolean sBIT_present;
    public int sBIT_redBits;
    public int[] sPLT_alpha;
    public int[] sPLT_blue;
    public int[] sPLT_frequency;
    public int[] sPLT_green;
    public String sPLT_paletteName;
    public boolean sPLT_present;
    public int[] sPLT_red;
    public int sPLT_sampleDepth;
    public boolean sRGB_present;
    public int sRGB_renderingIntent;
    public ArrayList tEXt_keyword;
    public ArrayList tEXt_text;
    public int tIME_day;
    public int tIME_hour;
    public int tIME_minute;
    public int tIME_month;
    public boolean tIME_present;
    public int tIME_second;
    public int tIME_year;
    public byte[] tRNS_alpha;
    public int tRNS_blue;
    public int tRNS_colorType;
    public int tRNS_gray;
    public int tRNS_green;
    public boolean tRNS_present;
    public int tRNS_red;
    public ArrayList unknownChunkData;
    public ArrayList unknownChunkType;
    public ArrayList zTXt_compressionMethod;
    public ArrayList zTXt_keyword;
    public ArrayList zTXt_text;
    public static final String[] IHDR_colorTypeNames = {"Grayscale", null, "RGB", "Palette", "GrayAlpha", null, "RGBAlpha"};
    public static final int[] IHDR_numChannels = {1, 0, 3, 3, 2, 0, 4};
    public static final String[] IHDR_bitDepths = {"1", "2", "4", "8", "16"};
    public static final String[] IHDR_compressionMethodNames = {"deflate"};
    public static final String[] IHDR_filterMethodNames = {"adaptive"};
    public static final String[] IHDR_interlaceMethodNames = {"none", "adam7"};
    public static final String[] iCCP_compressionMethodNames = {"deflate"};
    public static final String[] zTXt_compressionMethodNames = {"deflate"};
    public static final String[] unitSpecifierNames = {"unknown", "meter"};
    public static final String[] renderingIntentNames = {"Perceptual", "Relative colorimetric", ExifInterface.TAG_SATURATION, "Absolute colorimetric"};
    public static final String[] colorSpaceTypeNames = {"GRAY", null, "RGB", "RGB", "GRAY", null, "RGB"};
    static final int IHDR_TYPE = chunkType("IHDR");
    static final int PLTE_TYPE = chunkType("PLTE");
    static final int IDAT_TYPE = chunkType("IDAT");
    static final int IEND_TYPE = chunkType("IEND");
    static final int bKGD_TYPE = chunkType("bKGD");
    static final int cHRM_TYPE = chunkType("cHRM");
    static final int gAMA_TYPE = chunkType("gAMA");
    static final int hIST_TYPE = chunkType("hIST");
    static final int iCCP_TYPE = chunkType("iCCP");
    static final int iTXt_TYPE = chunkType("iTXt");
    static final int pHYs_TYPE = chunkType("pHYs");
    static final int sBIT_TYPE = chunkType("sBIT");
    static final int sPLT_TYPE = chunkType("sPLT");
    static final int sRGB_TYPE = chunkType("sRGB");
    static final int tEXt_TYPE = chunkType("tEXt");
    static final int tIME_TYPE = chunkType("tIME");
    static final int tRNS_TYPE = chunkType("tRNS");
    static final int zTXt_TYPE = chunkType("zTXt");

    public boolean isReadOnly() {
        return false;
    }

    static String toPrintableLatin1(String str) {
        byte[] bytes;
        if (str == null) {
            return null;
        }
        try {
            bytes = str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException unused) {
            bytes = str.getBytes();
        }
        int i = 0;
        int i2 = 0;
        for (byte b : bytes) {
            int i3 = b & 255;
            if (i2 != 32 || i3 != 32) {
                if ((i3 > 32 && i3 <= 126) || ((i3 >= 161 && i3 <= 255) || (i3 == 32 && i != 0))) {
                    bytes[i] = (byte) i3;
                    i++;
                }
                i2 = i3;
            }
        }
        if (i == 0) {
            return "";
        }
        if (bytes[i - 1] == 32) {
            i--;
        }
        return new String(bytes, 0, i);
    }

    public CLibPNGMetadata() {
        super(true, nativeMetadataFormatName, nativeMetadataFormatClassName, (String[]) null, (String[]) null);
        this.iTXt_keyword = new ArrayList();
        this.iTXt_compressionFlag = new ArrayList();
        this.iTXt_compressionMethod = new ArrayList();
        this.iTXt_languageTag = new ArrayList();
        this.iTXt_translatedKeyword = new ArrayList();
        this.iTXt_text = new ArrayList();
        this.tEXt_keyword = new ArrayList();
        this.tEXt_text = new ArrayList();
        this.zTXt_keyword = new ArrayList();
        this.zTXt_compressionMethod = new ArrayList();
        this.zTXt_text = new ArrayList();
        this.unknownChunkType = new ArrayList();
        this.unknownChunkData = new ArrayList();
        this.gotHeader = false;
        this.gotMetadata = false;
    }

    public CLibPNGMetadata(IIOMetadata iIOMetadata) throws IIOInvalidTreeException {
        this();
        if (iIOMetadata != null) {
            if (Arrays.asList(iIOMetadata.getMetadataFormatNames()).contains(nativeMetadataFormatName)) {
                setFromTree(nativeMetadataFormatName, iIOMetadata.getAsTree(nativeMetadataFormatName));
            } else if (iIOMetadata.isStandardMetadataFormatSupported()) {
                setFromTree("javax_imageio_1.0", iIOMetadata.getAsTree("javax_imageio_1.0"));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void initialize(ImageTypeSpecifier imageTypeSpecifier, int i, ImageWriteParam imageWriteParam, int i2) {
        boolean z;
        boolean hasAlpha;
        IndexColorModel colorModel = imageTypeSpecifier.getColorModel();
        SampleModel sampleModel = imageTypeSpecifier.getSampleModel();
        this.IHDR_width = sampleModel.getWidth();
        this.IHDR_height = sampleModel.getHeight();
        int[] sampleSize = sampleModel.getSampleSize();
        int i3 = sampleSize[0];
        for (int i4 = 1; i4 < sampleSize.length; i4++) {
            if (sampleSize[i4] > i3) {
                i3 = sampleSize[i4];
            }
        }
        int i5 = 8;
        if (sampleSize.length > 1 && i3 < 8) {
            i3 = 8;
        }
        if (i3 > 2 && i3 < 4) {
            i5 = 4;
        } else if (i3 <= 4 || i3 >= 8) {
            if (i3 > 8 && i3 < 16) {
                i5 = 16;
            } else {
                if (i3 > 16) {
                    throw new RuntimeException("bitDepth > 16!");
                }
                i5 = i3;
            }
        }
        this.IHDR_bitDepth = i5;
        if (colorModel instanceof IndexColorModel) {
            IndexColorModel indexColorModel = colorModel;
            int mapSize = indexColorModel.getMapSize();
            byte[] bArr = new byte[mapSize];
            indexColorModel.getReds(bArr);
            byte[] bArr2 = new byte[mapSize];
            indexColorModel.getGreens(bArr2);
            byte[] bArr3 = new byte[mapSize];
            indexColorModel.getBlues(bArr3);
            if (!this.IHDR_present || this.IHDR_colorType != 3) {
                int i6 = 255 / ((1 << this.IHDR_bitDepth) - 1);
                for (int i7 = 0; i7 < mapSize; i7++) {
                    byte b = bArr[i7];
                    if (b == ((byte) (i7 * i6)) && b == bArr2[i7] && b == bArr3[i7]) {
                    }
                }
                z = true;
                hasAlpha = colorModel.hasAlpha();
                byte[] bArr4 = null;
                if (hasAlpha) {
                    bArr4 = new byte[mapSize];
                    indexColorModel.getAlphas(bArr4);
                }
                if (!z && hasAlpha) {
                    this.IHDR_colorType = 4;
                } else if (!z) {
                    this.IHDR_colorType = 0;
                } else {
                    this.IHDR_colorType = 3;
                    this.PLTE_present = true;
                    this.PLTE_red = (byte[]) bArr.clone();
                    this.PLTE_green = (byte[]) bArr2.clone();
                    this.PLTE_blue = (byte[]) bArr3.clone();
                    if (hasAlpha) {
                        this.tRNS_present = true;
                        this.tRNS_colorType = 3;
                        this.tRNS_alpha = (byte[]) bArr4.clone();
                    }
                }
            }
            z = false;
            hasAlpha = colorModel.hasAlpha();
            byte[] bArr42 = null;
            if (hasAlpha) {
            }
            if (!z) {
            }
            if (!z) {
            }
        } else if (i == 1) {
            this.IHDR_colorType = 0;
        } else if (i == 2) {
            this.IHDR_colorType = 4;
        } else if (i == 3) {
            this.IHDR_colorType = 2;
        } else if (i == 4) {
            this.IHDR_colorType = 6;
        } else {
            throw new RuntimeException("Number of bands not 1-4!");
        }
        this.IHDR_filterMethod = 0;
        this.IHDR_compressionMethod = 0;
        if (imageWriteParam != null && imageWriteParam.getProgressiveMode() == 0) {
            this.IHDR_interlaceMethod = 0;
        } else if (imageWriteParam != null && imageWriteParam.getProgressiveMode() == 1) {
            this.IHDR_interlaceMethod = 1;
        } else {
            this.IHDR_interlaceMethod = i2;
        }
        this.IHDR_present = true;
    }

    private ArrayList cloneBytesArrayList(ArrayList arrayList) {
        if (arrayList == null) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next == null) {
                arrayList2.add(null);
            } else {
                arrayList2.add(((byte[]) next).clone());
            }
        }
        return arrayList2;
    }

    public Object clone() {
        try {
            CLibPNGMetadata cLibPNGMetadata = (CLibPNGMetadata) super.clone();
            cLibPNGMetadata.unknownChunkData = cloneBytesArrayList(this.unknownChunkData);
            return cLibPNGMetadata;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public Node getAsTree(String str) {
        if (str.equals(nativeMetadataFormatName)) {
            return getNativeTree();
        }
        if (str.equals("javax_imageio_1.0")) {
            return getStandardTree();
        }
        throw new IllegalArgumentException("Not a recognized format!");
    }

    private Node getNativeTree() {
        IIOMetadataNode iIOMetadataNode;
        IIOMetadataNode iIOMetadataNode2;
        String str;
        IIOMetadataNode iIOMetadataNode3;
        IIOMetadataNode iIOMetadataNode4;
        IIOMetadataNode iIOMetadataNode5 = new IIOMetadataNode(nativeMetadataFormatName);
        if (this.IHDR_present) {
            IIOMetadataNode iIOMetadataNode6 = new IIOMetadataNode("IHDR");
            iIOMetadataNode6.setAttribute("width", Integer.toString(this.IHDR_width));
            iIOMetadataNode6.setAttribute("height", Integer.toString(this.IHDR_height));
            iIOMetadataNode6.setAttribute("bitDepth", Integer.toString(this.IHDR_bitDepth));
            iIOMetadataNode6.setAttribute("colorType", IHDR_colorTypeNames[this.IHDR_colorType]);
            iIOMetadataNode6.setAttribute("compressionMethod", IHDR_compressionMethodNames[this.IHDR_compressionMethod]);
            iIOMetadataNode6.setAttribute("filterMethod", IHDR_filterMethodNames[this.IHDR_filterMethod]);
            iIOMetadataNode6.setAttribute("interlaceMethod", IHDR_interlaceMethodNames[this.IHDR_interlaceMethod]);
            iIOMetadataNode5.appendChild(iIOMetadataNode6);
        }
        if (this.PLTE_present) {
            IIOMetadataNode iIOMetadataNode7 = new IIOMetadataNode("PLTE");
            int length = this.PLTE_red.length;
            for (int i = 0; i < length; i++) {
                IIOMetadataNode iIOMetadataNode8 = new IIOMetadataNode("PLTEEntry");
                iIOMetadataNode8.setAttribute(FirebaseAnalytics.Param.INDEX, Integer.toString(i));
                iIOMetadataNode8.setAttribute("red", Integer.toString(this.PLTE_red[i] & 255));
                iIOMetadataNode8.setAttribute("green", Integer.toString(this.PLTE_green[i] & 255));
                iIOMetadataNode8.setAttribute("blue", Integer.toString(this.PLTE_blue[i] & 255));
                iIOMetadataNode7.appendChild(iIOMetadataNode8);
            }
            iIOMetadataNode5.appendChild(iIOMetadataNode7);
        }
        IIOMetadataNode iIOMetadataNode9 = null;
        if (this.bKGD_present) {
            IIOMetadataNode iIOMetadataNode10 = new IIOMetadataNode("bKGD");
            int i2 = this.bKGD_colorType;
            if (i2 == 3) {
                iIOMetadataNode9 = new IIOMetadataNode("bKGD_Palette");
                iIOMetadataNode9.setAttribute(FirebaseAnalytics.Param.INDEX, Integer.toString(this.bKGD_index));
            } else if (i2 == 0) {
                iIOMetadataNode9 = new IIOMetadataNode("bKGD_Grayscale");
                iIOMetadataNode9.setAttribute("gray", Integer.toString(this.bKGD_gray));
            } else if (i2 == 2) {
                iIOMetadataNode9 = new IIOMetadataNode("bKGD_RGB");
                iIOMetadataNode9.setAttribute("red", Integer.toString(this.bKGD_red));
                iIOMetadataNode9.setAttribute("green", Integer.toString(this.bKGD_green));
                iIOMetadataNode9.setAttribute("blue", Integer.toString(this.bKGD_blue));
            }
            iIOMetadataNode10.appendChild(iIOMetadataNode9);
            iIOMetadataNode5.appendChild(iIOMetadataNode10);
        }
        if (this.cHRM_present) {
            IIOMetadataNode iIOMetadataNode11 = new IIOMetadataNode("cHRM");
            iIOMetadataNode11.setAttribute("whitePointX", Integer.toString(this.cHRM_whitePointX));
            iIOMetadataNode11.setAttribute("whitePointY", Integer.toString(this.cHRM_whitePointY));
            iIOMetadataNode11.setAttribute("redX", Integer.toString(this.cHRM_redX));
            iIOMetadataNode11.setAttribute("redY", Integer.toString(this.cHRM_redY));
            iIOMetadataNode11.setAttribute("greenX", Integer.toString(this.cHRM_greenX));
            iIOMetadataNode11.setAttribute("greenY", Integer.toString(this.cHRM_greenY));
            iIOMetadataNode11.setAttribute("blueX", Integer.toString(this.cHRM_blueX));
            iIOMetadataNode11.setAttribute("blueY", Integer.toString(this.cHRM_blueY));
            iIOMetadataNode5.appendChild(iIOMetadataNode11);
        }
        if (this.gAMA_present) {
            IIOMetadataNode iIOMetadataNode12 = new IIOMetadataNode("gAMA");
            iIOMetadataNode12.setAttribute("value", Integer.toString(this.gAMA_gamma));
            iIOMetadataNode5.appendChild(iIOMetadataNode12);
        }
        if (this.hIST_present) {
            IIOMetadataNode iIOMetadataNode13 = new IIOMetadataNode("hIST");
            for (int i3 = 0; i3 < this.hIST_histogram.length; i3++) {
                IIOMetadataNode iIOMetadataNode14 = new IIOMetadataNode("hISTEntry");
                iIOMetadataNode14.setAttribute(FirebaseAnalytics.Param.INDEX, Integer.toString(i3));
                iIOMetadataNode14.setAttribute("value", Integer.toString(this.hIST_histogram[i3]));
                iIOMetadataNode13.appendChild(iIOMetadataNode14);
            }
            iIOMetadataNode5.appendChild(iIOMetadataNode13);
        }
        if (this.iCCP_present) {
            IIOMetadataNode iIOMetadataNode15 = new IIOMetadataNode("iCCP");
            iIOMetadataNode15.setAttribute("profileName", this.iCCP_profileName);
            iIOMetadataNode15.setAttribute("compressionMethod", iCCP_compressionMethodNames[this.iCCP_compressionMethod]);
            Object obj = this.iCCP_compressedProfile;
            if (obj != null) {
                obj = ((byte[]) obj).clone();
            }
            iIOMetadataNode15.setUserObject(obj);
            iIOMetadataNode5.appendChild(iIOMetadataNode15);
        }
        String str2 = "text";
        if (this.iTXt_keyword.size() > 0) {
            IIOMetadataNode iIOMetadataNode16 = new IIOMetadataNode("iTXt");
            int i4 = 0;
            while (i4 < this.iTXt_keyword.size()) {
                IIOMetadataNode iIOMetadataNode17 = new IIOMetadataNode("iTXtEntry");
                iIOMetadataNode17.setAttribute("keyword", (String) this.iTXt_keyword.get(i4));
                iIOMetadataNode17.setAttribute("compressionFlag", ((Integer) this.iTXt_compressionFlag.get(i4)).toString());
                iIOMetadataNode17.setAttribute("compressionMethod", ((Integer) this.iTXt_compressionMethod.get(i4)).toString());
                iIOMetadataNode17.setAttribute("languageTag", (String) this.iTXt_languageTag.get(i4));
                iIOMetadataNode17.setAttribute("translatedKeyword", (String) this.iTXt_translatedKeyword.get(i4));
                iIOMetadataNode17.setAttribute("text", (String) this.iTXt_text.get(i4));
                iIOMetadataNode16.appendChild(iIOMetadataNode17);
                i4++;
                iIOMetadataNode9 = iIOMetadataNode9;
            }
            iIOMetadataNode = iIOMetadataNode9;
            iIOMetadataNode5.appendChild(iIOMetadataNode16);
        } else {
            iIOMetadataNode = iIOMetadataNode9;
        }
        if (this.pHYs_present) {
            IIOMetadataNode iIOMetadataNode18 = new IIOMetadataNode("pHYs");
            iIOMetadataNode18.setAttribute("pixelsPerUnitXAxis", Integer.toString(this.pHYs_pixelsPerUnitXAxis));
            iIOMetadataNode18.setAttribute("pixelsPerUnitYAxis", Integer.toString(this.pHYs_pixelsPerUnitYAxis));
            iIOMetadataNode18.setAttribute("unitSpecifier", unitSpecifierNames[this.pHYs_unitSpecifier]);
            iIOMetadataNode5.appendChild(iIOMetadataNode18);
        }
        if (this.sBIT_present) {
            IIOMetadataNode iIOMetadataNode19 = new IIOMetadataNode("sBIT");
            int i5 = this.sBIT_colorType;
            if (i5 == 0) {
                iIOMetadataNode2 = new IIOMetadataNode("sBIT_Grayscale");
                iIOMetadataNode2.setAttribute("gray", Integer.toString(this.sBIT_grayBits));
            } else if (i5 == 4) {
                iIOMetadataNode2 = new IIOMetadataNode("sBIT_GrayAlpha");
                iIOMetadataNode2.setAttribute("gray", Integer.toString(this.sBIT_grayBits));
                iIOMetadataNode2.setAttribute("alpha", Integer.toString(this.sBIT_alphaBits));
            } else if (i5 == 2) {
                iIOMetadataNode2 = new IIOMetadataNode("sBIT_RGB");
                iIOMetadataNode2.setAttribute("red", Integer.toString(this.sBIT_redBits));
                iIOMetadataNode2.setAttribute("green", Integer.toString(this.sBIT_greenBits));
                iIOMetadataNode2.setAttribute("blue", Integer.toString(this.sBIT_blueBits));
            } else if (i5 == 6) {
                iIOMetadataNode2 = new IIOMetadataNode("sBIT_RGBAlpha");
                iIOMetadataNode2.setAttribute("red", Integer.toString(this.sBIT_redBits));
                iIOMetadataNode2.setAttribute("green", Integer.toString(this.sBIT_greenBits));
                iIOMetadataNode2.setAttribute("blue", Integer.toString(this.sBIT_blueBits));
                iIOMetadataNode2.setAttribute("alpha", Integer.toString(this.sBIT_alphaBits));
            } else if (i5 == 3) {
                iIOMetadataNode2 = new IIOMetadataNode("sBIT_Palette");
                iIOMetadataNode2.setAttribute("red", Integer.toString(this.sBIT_redBits));
                iIOMetadataNode2.setAttribute("green", Integer.toString(this.sBIT_greenBits));
                iIOMetadataNode2.setAttribute("blue", Integer.toString(this.sBIT_blueBits));
            } else {
                iIOMetadataNode2 = iIOMetadataNode;
            }
            iIOMetadataNode19.appendChild(iIOMetadataNode2);
            iIOMetadataNode5.appendChild(iIOMetadataNode19);
        } else {
            iIOMetadataNode2 = iIOMetadataNode;
        }
        if (this.sPLT_present) {
            IIOMetadataNode iIOMetadataNode20 = new IIOMetadataNode("sPLT");
            iIOMetadataNode20.setAttribute("name", this.sPLT_paletteName);
            iIOMetadataNode20.setAttribute("sampleDepth", Integer.toString(this.sPLT_sampleDepth));
            int length2 = this.sPLT_red.length;
            int i6 = 0;
            while (i6 < length2) {
                int i7 = length2;
                IIOMetadataNode iIOMetadataNode21 = new IIOMetadataNode("sPLTEntry");
                iIOMetadataNode21.setAttribute(FirebaseAnalytics.Param.INDEX, Integer.toString(i6));
                iIOMetadataNode21.setAttribute("red", Integer.toString(this.sPLT_red[i6]));
                iIOMetadataNode21.setAttribute("green", Integer.toString(this.sPLT_green[i6]));
                iIOMetadataNode21.setAttribute("blue", Integer.toString(this.sPLT_blue[i6]));
                iIOMetadataNode21.setAttribute("alpha", Integer.toString(this.sPLT_alpha[i6]));
                iIOMetadataNode21.setAttribute("frequency", Integer.toString(this.sPLT_frequency[i6]));
                iIOMetadataNode20.appendChild(iIOMetadataNode21);
                i6++;
                length2 = i7;
                iIOMetadataNode2 = iIOMetadataNode2;
                str2 = str2;
            }
            str = str2;
            iIOMetadataNode3 = iIOMetadataNode2;
            iIOMetadataNode5.appendChild(iIOMetadataNode20);
        } else {
            str = "text";
            iIOMetadataNode3 = iIOMetadataNode2;
        }
        if (this.sRGB_present) {
            IIOMetadataNode iIOMetadataNode22 = new IIOMetadataNode("sRGB");
            iIOMetadataNode22.setAttribute("renderingIntent", renderingIntentNames[this.sRGB_renderingIntent]);
            iIOMetadataNode5.appendChild(iIOMetadataNode22);
        }
        if (this.tEXt_keyword.size() > 0) {
            IIOMetadataNode iIOMetadataNode23 = new IIOMetadataNode("tEXt");
            for (int i8 = 0; i8 < this.tEXt_keyword.size(); i8++) {
                IIOMetadataNode iIOMetadataNode24 = new IIOMetadataNode("tEXtEntry");
                iIOMetadataNode24.setAttribute("keyword", (String) this.tEXt_keyword.get(i8));
                iIOMetadataNode24.setAttribute("value", (String) this.tEXt_text.get(i8));
                iIOMetadataNode23.appendChild(iIOMetadataNode24);
            }
            iIOMetadataNode5.appendChild(iIOMetadataNode23);
        }
        if (this.tIME_present) {
            IIOMetadataNode iIOMetadataNode25 = new IIOMetadataNode("tIME");
            iIOMetadataNode25.setAttribute("year", Integer.toString(this.tIME_year));
            iIOMetadataNode25.setAttribute("month", Integer.toString(this.tIME_month));
            iIOMetadataNode25.setAttribute("day", Integer.toString(this.tIME_day));
            iIOMetadataNode25.setAttribute("hour", Integer.toString(this.tIME_hour));
            iIOMetadataNode25.setAttribute("minute", Integer.toString(this.tIME_minute));
            iIOMetadataNode25.setAttribute("second", Integer.toString(this.tIME_second));
            iIOMetadataNode5.appendChild(iIOMetadataNode25);
        }
        if (this.tRNS_present) {
            IIOMetadataNode iIOMetadataNode26 = new IIOMetadataNode("tRNS");
            int i9 = this.tRNS_colorType;
            if (i9 == 3) {
                iIOMetadataNode4 = new IIOMetadataNode("tRNS_Palette");
                for (int i10 = 0; i10 < this.tRNS_alpha.length; i10++) {
                    IIOMetadataNode iIOMetadataNode27 = new IIOMetadataNode("tRNS_PaletteEntry");
                    iIOMetadataNode27.setAttribute(FirebaseAnalytics.Param.INDEX, Integer.toString(i10));
                    iIOMetadataNode27.setAttribute("alpha", Integer.toString(this.tRNS_alpha[i10] & 255));
                    iIOMetadataNode4.appendChild(iIOMetadataNode27);
                }
            } else if (i9 == 0) {
                iIOMetadataNode4 = new IIOMetadataNode("tRNS_Grayscale");
                iIOMetadataNode4.setAttribute("gray", Integer.toString(this.tRNS_gray));
            } else if (i9 == 2) {
                iIOMetadataNode4 = new IIOMetadataNode("tRNS_RGB");
                iIOMetadataNode4.setAttribute("red", Integer.toString(this.tRNS_red));
                iIOMetadataNode4.setAttribute("green", Integer.toString(this.tRNS_green));
                iIOMetadataNode4.setAttribute("blue", Integer.toString(this.tRNS_blue));
            } else {
                iIOMetadataNode4 = iIOMetadataNode3;
            }
            iIOMetadataNode26.appendChild(iIOMetadataNode4);
            iIOMetadataNode5.appendChild(iIOMetadataNode26);
        }
        if (this.zTXt_keyword.size() > 0) {
            IIOMetadataNode iIOMetadataNode28 = new IIOMetadataNode("zTXt");
            for (int i11 = 0; i11 < this.zTXt_keyword.size(); i11++) {
                IIOMetadataNode iIOMetadataNode29 = new IIOMetadataNode("zTXtEntry");
                iIOMetadataNode29.setAttribute("keyword", (String) this.zTXt_keyword.get(i11));
                iIOMetadataNode29.setAttribute("compressionMethod", zTXt_compressionMethodNames[((Integer) this.zTXt_compressionMethod.get(i11)).intValue()]);
                iIOMetadataNode29.setAttribute(str, (String) this.zTXt_text.get(i11));
                iIOMetadataNode28.appendChild(iIOMetadataNode29);
            }
            iIOMetadataNode5.appendChild(iIOMetadataNode28);
        }
        if (this.unknownChunkType.size() > 0) {
            IIOMetadataNode iIOMetadataNode30 = new IIOMetadataNode("UnknownChunks");
            for (int i12 = 0; i12 < this.unknownChunkType.size(); i12++) {
                IIOMetadataNode iIOMetadataNode31 = new IIOMetadataNode("UnknownChunk");
                iIOMetadataNode31.setAttribute("type", (String) this.unknownChunkType.get(i12));
                iIOMetadataNode31.setUserObject((byte[]) this.unknownChunkData.get(i12));
                iIOMetadataNode30.appendChild(iIOMetadataNode31);
            }
            iIOMetadataNode5.appendChild(iIOMetadataNode30);
        }
        return iIOMetadataNode5;
    }

    private int getNumChannels() {
        int[] iArr = IHDR_numChannels;
        int i = this.IHDR_colorType;
        int i2 = iArr[i];
        if (i == 3 && this.tRNS_present && this.tRNS_colorType == i) {
            return 4;
        }
        return i2;
    }

    public IIOMetadataNode getStandardChromaNode() {
        IIOMetadataNode iIOMetadataNode;
        int i;
        int i2;
        int i3;
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("Chroma");
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("ColorSpaceType");
        iIOMetadataNode3.setAttribute("name", colorSpaceTypeNames[this.IHDR_colorType]);
        iIOMetadataNode2.appendChild(iIOMetadataNode3);
        IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("NumChannels");
        iIOMetadataNode4.setAttribute("value", Integer.toString(getNumChannels()));
        iIOMetadataNode2.appendChild(iIOMetadataNode4);
        if (this.gAMA_present) {
            IIOMetadataNode iIOMetadataNode5 = new IIOMetadataNode(ExifInterface.TAG_GAMMA);
            iIOMetadataNode5.setAttribute("value", Float.toString(this.gAMA_gamma * 1.0E-5f));
            iIOMetadataNode2.appendChild(iIOMetadataNode5);
        }
        IIOMetadataNode iIOMetadataNode6 = new IIOMetadataNode("BlackIsZero");
        iIOMetadataNode6.setAttribute("value", "TRUE");
        iIOMetadataNode2.appendChild(iIOMetadataNode6);
        if (this.PLTE_present) {
            int i4 = 0;
            boolean z = this.tRNS_present && this.tRNS_colorType == 3;
            IIOMetadataNode iIOMetadataNode7 = new IIOMetadataNode("Palette");
            while (i4 < this.PLTE_red.length) {
                IIOMetadataNode iIOMetadataNode8 = new IIOMetadataNode("PaletteEntry");
                iIOMetadataNode8.setAttribute(FirebaseAnalytics.Param.INDEX, Integer.toString(i4));
                iIOMetadataNode8.setAttribute("red", Integer.toString(this.PLTE_red[i4] & 255));
                iIOMetadataNode8.setAttribute("green", Integer.toString(this.PLTE_green[i4] & 255));
                iIOMetadataNode8.setAttribute("blue", Integer.toString(this.PLTE_blue[i4] & 255));
                if (z) {
                    byte[] bArr = this.tRNS_alpha;
                    iIOMetadataNode8.setAttribute("alpha", Integer.toString(i4 < bArr.length ? 255 & bArr[i4] : 255));
                }
                iIOMetadataNode7.appendChild(iIOMetadataNode8);
                i4++;
            }
            iIOMetadataNode2.appendChild(iIOMetadataNode7);
        }
        if (this.bKGD_present) {
            if (this.bKGD_colorType == 3) {
                iIOMetadataNode = new IIOMetadataNode("BackgroundIndex");
                iIOMetadataNode.setAttribute("value", Integer.toString(this.bKGD_index));
            } else {
                iIOMetadataNode = new IIOMetadataNode("BackgroundColor");
                if (this.bKGD_colorType == 0) {
                    i = this.bKGD_gray;
                    i2 = i;
                    i3 = i2;
                } else {
                    i = this.bKGD_red;
                    i2 = this.bKGD_green;
                    i3 = this.bKGD_blue;
                }
                iIOMetadataNode.setAttribute("red", Integer.toString(i));
                iIOMetadataNode.setAttribute("green", Integer.toString(i2));
                iIOMetadataNode.setAttribute("blue", Integer.toString(i3));
            }
            iIOMetadataNode2.appendChild(iIOMetadataNode);
        }
        return iIOMetadataNode2;
    }

    public IIOMetadataNode getStandardCompressionNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode(ExifInterface.TAG_COMPRESSION);
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("CompressionTypeName");
        iIOMetadataNode2.setAttribute("value", "deflate");
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("Lossless");
        iIOMetadataNode3.setAttribute("value", "TRUE");
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("NumProgressiveScans");
        iIOMetadataNode4.setAttribute("value", this.IHDR_interlaceMethod == 0 ? "1" : "7");
        iIOMetadataNode.appendChild(iIOMetadataNode4);
        return iIOMetadataNode;
    }

    private String repeat(String str, int i) {
        if (i == 1) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(((str.length() + 1) * i) - 1);
        stringBuffer.append(str);
        for (int i2 = 1; i2 < i; i2++) {
            stringBuffer.append(" ");
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    public IIOMetadataNode getStandardDataNode() {
        String num;
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Data");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode(ExifInterface.TAG_PLANAR_CONFIGURATION);
        iIOMetadataNode2.setAttribute("value", "PixelInterleaved");
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("SampleFormat");
        iIOMetadataNode3.setAttribute("value", this.IHDR_colorType == 3 ? StandardStructureTypes.INDEX : "UnsignedIntegral");
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        String num2 = Integer.toString(this.IHDR_bitDepth);
        IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode(ExifInterface.TAG_BITS_PER_SAMPLE);
        iIOMetadataNode4.setAttribute("value", repeat(num2, getNumChannels()));
        iIOMetadataNode.appendChild(iIOMetadataNode4);
        if (this.sBIT_present) {
            IIOMetadataNode iIOMetadataNode5 = new IIOMetadataNode("SignificantBitsPerSample");
            int i = this.sBIT_colorType;
            if (i == 0 || i == 4) {
                num = Integer.toString(this.sBIT_grayBits);
            } else {
                num = Integer.toString(this.sBIT_redBits) + " " + Integer.toString(this.sBIT_greenBits) + " " + Integer.toString(this.sBIT_blueBits);
            }
            int i2 = this.sBIT_colorType;
            if (i2 == 4 || i2 == 6) {
                num = num + " " + Integer.toString(this.sBIT_alphaBits);
            }
            iIOMetadataNode5.setAttribute("value", num);
            iIOMetadataNode.appendChild(iIOMetadataNode5);
        }
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardDimensionNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Dimension");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("PixelAspectRatio");
        iIOMetadataNode2.setAttribute("value", Float.toString(this.pHYs_present ? this.pHYs_pixelsPerUnitYAxis / this.pHYs_pixelsPerUnitXAxis : 1.0f));
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("ImageOrientation");
        iIOMetadataNode3.setAttribute("value", PDLayoutAttributeObject.LINE_HEIGHT_NORMAL);
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        if (this.pHYs_present && this.pHYs_unitSpecifier == 1) {
            IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("HorizontalPixelSize");
            iIOMetadataNode4.setAttribute("value", Float.toString(1000.0f / this.pHYs_pixelsPerUnitXAxis));
            iIOMetadataNode.appendChild(iIOMetadataNode4);
            IIOMetadataNode iIOMetadataNode5 = new IIOMetadataNode("VerticalPixelSize");
            iIOMetadataNode5.setAttribute("value", Float.toString(1000.0f / this.pHYs_pixelsPerUnitYAxis));
            iIOMetadataNode.appendChild(iIOMetadataNode5);
        }
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardDocumentNode() {
        if (!this.tIME_present) {
            return null;
        }
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode(StandardStructureTypes.DOCUMENT);
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("ImageModificationTime");
        iIOMetadataNode2.setAttribute("year", Integer.toString(this.tIME_year));
        iIOMetadataNode2.setAttribute("month", Integer.toString(this.tIME_month));
        iIOMetadataNode2.setAttribute("day", Integer.toString(this.tIME_day));
        iIOMetadataNode2.setAttribute("hour", Integer.toString(this.tIME_hour));
        iIOMetadataNode2.setAttribute("minute", Integer.toString(this.tIME_minute));
        iIOMetadataNode2.setAttribute("second", Integer.toString(this.tIME_second));
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardTextNode() {
        if (this.tEXt_keyword.size() + this.iTXt_keyword.size() + this.zTXt_keyword.size() == 0) {
            return null;
        }
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Text");
        for (int i = 0; i < this.tEXt_keyword.size(); i++) {
            IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("TextEntry");
            iIOMetadataNode2.setAttribute("keyword", (String) this.tEXt_keyword.get(i));
            iIOMetadataNode2.setAttribute("value", (String) this.tEXt_text.get(i));
            iIOMetadataNode2.setAttribute("encoding", "ISO-8859-1");
            iIOMetadataNode2.setAttribute("compression", "none");
            iIOMetadataNode.appendChild(iIOMetadataNode2);
        }
        for (int i2 = 0; i2 < this.iTXt_keyword.size(); i2++) {
            IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("TextEntry");
            iIOMetadataNode3.setAttribute("keyword", (String) this.iTXt_keyword.get(i2));
            iIOMetadataNode3.setAttribute("value", (String) this.iTXt_text.get(i2));
            iIOMetadataNode3.setAttribute(Device.JsonKeys.LANGUAGE, (String) this.iTXt_languageTag.get(i2));
            if (((Integer) this.iTXt_compressionFlag.get(i2)).intValue() == 1) {
                iIOMetadataNode3.setAttribute("compression", "deflate");
            } else {
                iIOMetadataNode3.setAttribute("compression", "none");
            }
            iIOMetadataNode.appendChild(iIOMetadataNode3);
        }
        for (int i3 = 0; i3 < this.zTXt_keyword.size(); i3++) {
            IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("TextEntry");
            iIOMetadataNode4.setAttribute("keyword", (String) this.zTXt_keyword.get(i3));
            iIOMetadataNode4.setAttribute("value", (String) this.zTXt_text.get(i3));
            iIOMetadataNode4.setAttribute("compression", "deflate");
            iIOMetadataNode.appendChild(iIOMetadataNode4);
        }
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardTransparencyNode() {
        int i;
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Transparency");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("Alpha");
        int i2 = this.IHDR_colorType;
        iIOMetadataNode2.setAttribute("value", i2 == 6 || i2 == 4 || (i2 == 3 && this.tRNS_present && this.tRNS_colorType == i2 && this.tRNS_alpha != null) ? "nonpremultiplied" : "none");
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        if (this.tRNS_present && ((i = this.tRNS_colorType) == 2 || i == 0)) {
            IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("TransparentColor");
            int i3 = this.tRNS_colorType;
            if (i3 == 2) {
                iIOMetadataNode3.setAttribute("value", Integer.toString(this.tRNS_red) + " " + Integer.toString(this.tRNS_green) + " " + Integer.toString(this.tRNS_blue));
            } else if (i3 == 0) {
                iIOMetadataNode3.setAttribute("value", Integer.toString(this.tRNS_gray));
            }
            iIOMetadataNode.appendChild(iIOMetadataNode3);
        }
        return iIOMetadataNode;
    }

    private void fatal(Node node, String str) throws IIOInvalidTreeException {
        throw new IIOInvalidTreeException(str, node);
    }

    private int getIntAttribute(Node node, String str, int i, boolean z) throws IIOInvalidTreeException {
        String attribute = getAttribute(node, str, null, z);
        return attribute == null ? i : Integer.parseInt(attribute);
    }

    private float getFloatAttribute(Node node, String str, float f, boolean z) throws IIOInvalidTreeException {
        String attribute = getAttribute(node, str, null, z);
        return attribute == null ? f : Float.parseFloat(attribute);
    }

    private int getIntAttribute(Node node, String str) throws IIOInvalidTreeException {
        return getIntAttribute(node, str, -1, true);
    }

    private float getFloatAttribute(Node node, String str) throws IIOInvalidTreeException {
        return getFloatAttribute(node, str, -1.0f, true);
    }

    private boolean getBooleanAttribute(Node node, String str, boolean z, boolean z2) throws IIOInvalidTreeException {
        Node namedItem = node.getAttributes().getNamedItem(str);
        if (namedItem == null) {
            if (!z2) {
                return z;
            }
            fatal(node, "Required attribute " + str + " not present!");
        }
        String nodeValue = namedItem.getNodeValue();
        if (nodeValue.equalsIgnoreCase(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
            return true;
        }
        if (nodeValue.equalsIgnoreCase("false")) {
            return false;
        }
        fatal(node, "Attribute " + str + " must be 'true' or 'false'!");
        return false;
    }

    private boolean getBooleanAttribute(Node node, String str) throws IIOInvalidTreeException {
        return getBooleanAttribute(node, str, false, true);
    }

    private int getEnumeratedAttribute(Node node, String str, String[] strArr, int i, boolean z) throws IIOInvalidTreeException {
        Node namedItem = node.getAttributes().getNamedItem(str);
        if (namedItem == null) {
            if (!z) {
                return i;
            }
            fatal(node, "Required attribute " + str + " not present!");
        }
        String nodeValue = namedItem.getNodeValue();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (nodeValue.equals(strArr[i2])) {
                return i2;
            }
        }
        fatal(node, "Illegal value for attribute " + str + "!");
        return -1;
    }

    private int getEnumeratedAttribute(Node node, String str, String[] strArr) throws IIOInvalidTreeException {
        return getEnumeratedAttribute(node, str, strArr, -1, true);
    }

    private String getAttribute(Node node, String str, String str2, boolean z) throws IIOInvalidTreeException {
        Node namedItem = node.getAttributes().getNamedItem(str);
        if (namedItem == null) {
            if (!z) {
                return str2;
            }
            fatal(node, "Required attribute " + str + " not present!");
        }
        return namedItem.getNodeValue();
    }

    private String getAttribute(Node node, String str) throws IIOInvalidTreeException {
        return getAttribute(node, str, null, true);
    }

    public void mergeTree(String str, Node node) throws IIOInvalidTreeException {
        if (str.equals(nativeMetadataFormatName)) {
            if (node == null) {
                throw new IllegalArgumentException("root == null!");
            }
            mergeNativeTree(node);
        } else {
            if (!str.equals("javax_imageio_1.0")) {
                throw new IllegalArgumentException("Not a recognized format!");
            }
            if (node == null) {
                throw new IllegalArgumentException("root == null!");
            }
            mergeStandardTree(node);
        }
    }

    private void mergeNativeTree(Node node) throws IIOInvalidTreeException {
        if (!node.getNodeName().equals(nativeMetadataFormatName)) {
            fatal(node, "Root must be javax_imageio_png_1.0");
        }
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            String nodeName = firstChild.getNodeName();
            if (nodeName.equals("IHDR")) {
                this.IHDR_width = getIntAttribute(firstChild, "width");
                this.IHDR_height = getIntAttribute(firstChild, "height");
                this.IHDR_bitDepth = getEnumeratedAttribute(firstChild, "bitDepth", IHDR_bitDepths);
                this.IHDR_colorType = getEnumeratedAttribute(firstChild, "colorType", IHDR_colorTypeNames);
                this.IHDR_compressionMethod = getEnumeratedAttribute(firstChild, "compressionMethod", IHDR_compressionMethodNames);
                this.IHDR_filterMethod = getEnumeratedAttribute(firstChild, "filterMethod", IHDR_filterMethodNames);
                this.IHDR_interlaceMethod = getEnumeratedAttribute(firstChild, "interlaceMethod", IHDR_interlaceMethodNames);
                this.IHDR_present = true;
            } else if (nodeName.equals("PLTE")) {
                byte[] bArr = new byte[256];
                byte[] bArr2 = new byte[256];
                byte[] bArr3 = new byte[256];
                Node firstChild2 = firstChild.getFirstChild();
                if (firstChild2 == null) {
                    fatal(firstChild, "Palette has no entries!");
                }
                int i = -1;
                while (firstChild2 != null) {
                    if (!firstChild2.getNodeName().equals("PLTEEntry")) {
                        fatal(firstChild, "Only a PLTEEntry may be a child of a PLTE!");
                    }
                    int intAttribute = getIntAttribute(firstChild2, FirebaseAnalytics.Param.INDEX);
                    if (intAttribute < 0 || intAttribute > 255) {
                        fatal(firstChild, "Bad value for PLTEEntry attribute index!");
                    }
                    if (intAttribute > i) {
                        i = intAttribute;
                    }
                    bArr[intAttribute] = (byte) getIntAttribute(firstChild2, "red");
                    bArr2[intAttribute] = (byte) getIntAttribute(firstChild2, "green");
                    bArr3[intAttribute] = (byte) getIntAttribute(firstChild2, "blue");
                    firstChild2 = firstChild2.getNextSibling();
                }
                int i2 = i + 1;
                byte[] bArr4 = new byte[i2];
                this.PLTE_red = bArr4;
                this.PLTE_green = new byte[i2];
                this.PLTE_blue = new byte[i2];
                System.arraycopy(bArr, 0, bArr4, 0, i2);
                System.arraycopy(bArr2, 0, this.PLTE_green, 0, i2);
                System.arraycopy(bArr3, 0, this.PLTE_blue, 0, i2);
                this.PLTE_present = true;
            } else if (nodeName.equals("bKGD")) {
                this.bKGD_present = false;
                Node firstChild3 = firstChild.getFirstChild();
                if (firstChild3 == null) {
                    fatal(firstChild, "bKGD node has no children!");
                }
                String nodeName2 = firstChild3.getNodeName();
                if (nodeName2.equals("bKGD_Palette")) {
                    this.bKGD_index = getIntAttribute(firstChild3, FirebaseAnalytics.Param.INDEX);
                    this.bKGD_colorType = 3;
                } else if (nodeName2.equals("bKGD_Grayscale")) {
                    this.bKGD_gray = getIntAttribute(firstChild3, "gray");
                    this.bKGD_colorType = 0;
                } else if (nodeName2.equals("bKGD_RGB")) {
                    this.bKGD_red = getIntAttribute(firstChild3, "red");
                    this.bKGD_green = getIntAttribute(firstChild3, "green");
                    this.bKGD_blue = getIntAttribute(firstChild3, "blue");
                    this.bKGD_colorType = 2;
                } else {
                    fatal(firstChild, "Bad child of a bKGD node!");
                }
                if (firstChild3.getNextSibling() != null) {
                    fatal(firstChild, "bKGD node has more than one child!");
                }
                this.bKGD_present = true;
            } else if (nodeName.equals("cHRM")) {
                this.cHRM_whitePointX = getIntAttribute(firstChild, "whitePointX");
                this.cHRM_whitePointY = getIntAttribute(firstChild, "whitePointY");
                this.cHRM_redX = getIntAttribute(firstChild, "redX");
                this.cHRM_redY = getIntAttribute(firstChild, "redY");
                this.cHRM_greenX = getIntAttribute(firstChild, "greenX");
                this.cHRM_greenY = getIntAttribute(firstChild, "greenY");
                this.cHRM_blueX = getIntAttribute(firstChild, "blueX");
                this.cHRM_blueY = getIntAttribute(firstChild, "blueY");
                this.cHRM_present = true;
            } else if (nodeName.equals("gAMA")) {
                this.gAMA_gamma = getIntAttribute(firstChild, "value");
                this.gAMA_present = true;
            } else if (nodeName.equals("hIST")) {
                char[] cArr = new char[256];
                Node firstChild4 = firstChild.getFirstChild();
                if (firstChild4 == null) {
                    fatal(firstChild, "hIST node has no children!");
                }
                int i3 = -1;
                while (firstChild4 != null) {
                    if (!firstChild4.getNodeName().equals("hISTEntry")) {
                        fatal(firstChild, "Only a hISTEntry may be a child of a hIST!");
                    }
                    int intAttribute2 = getIntAttribute(firstChild4, FirebaseAnalytics.Param.INDEX);
                    if (intAttribute2 < 0 || intAttribute2 > 255) {
                        fatal(firstChild, "Bad value for histEntry attribute index!");
                    }
                    if (intAttribute2 > i3) {
                        i3 = intAttribute2;
                    }
                    cArr[intAttribute2] = (char) getIntAttribute(firstChild4, "value");
                    firstChild4 = firstChild4.getNextSibling();
                }
                int i4 = i3 + 1;
                char[] cArr2 = new char[i4];
                this.hIST_histogram = cArr2;
                System.arraycopy(cArr, 0, cArr2, 0, i4);
                this.hIST_present = true;
            } else if (nodeName.equals("iCCP")) {
                this.iCCP_profileName = toPrintableLatin1(getAttribute(firstChild, "profileName"));
                this.iCCP_compressionMethod = getEnumeratedAttribute(firstChild, "compressionMethod", iCCP_compressionMethodNames);
                Object userObject = ((IIOMetadataNode) firstChild).getUserObject();
                if (userObject == null) {
                    fatal(firstChild, "No ICCP profile present in user object!");
                }
                if (!(userObject instanceof byte[])) {
                    fatal(firstChild, "User object not a byte array!");
                }
                this.iCCP_compressedProfile = (byte[]) ((byte[]) userObject).clone();
                this.iCCP_present = true;
            } else if (nodeName.equals("iTXt")) {
                for (Node firstChild5 = firstChild.getFirstChild(); firstChild5 != null; firstChild5 = firstChild5.getNextSibling()) {
                    if (!firstChild5.getNodeName().equals("iTXtEntry")) {
                        fatal(firstChild, "Only an iTXtEntry may be a child of an iTXt!");
                    }
                    this.iTXt_keyword.add(toPrintableLatin1(getAttribute(firstChild5, "keyword")));
                    this.iTXt_compressionFlag.add(new Boolean(getBooleanAttribute(firstChild5, "compressionFlag")));
                    this.iTXt_compressionMethod.add(getAttribute(firstChild5, "compressionMethod"));
                    this.iTXt_languageTag.add(getAttribute(firstChild5, "languageTag"));
                    this.iTXt_translatedKeyword.add(getAttribute(firstChild5, "translatedKeyword"));
                    this.iTXt_text.add(getAttribute(firstChild5, "text"));
                }
            } else if (nodeName.equals("pHYs")) {
                this.pHYs_pixelsPerUnitXAxis = getIntAttribute(firstChild, "pixelsPerUnitXAxis");
                this.pHYs_pixelsPerUnitYAxis = getIntAttribute(firstChild, "pixelsPerUnitYAxis");
                this.pHYs_unitSpecifier = getEnumeratedAttribute(firstChild, "unitSpecifier", unitSpecifierNames);
                this.pHYs_present = true;
            } else if (nodeName.equals("sBIT")) {
                this.sBIT_present = false;
                Node firstChild6 = firstChild.getFirstChild();
                if (firstChild6 == null) {
                    fatal(firstChild, "sBIT node has no children!");
                }
                String nodeName3 = firstChild6.getNodeName();
                if (nodeName3.equals("sBIT_Grayscale")) {
                    this.sBIT_grayBits = getIntAttribute(firstChild6, "gray");
                    this.sBIT_colorType = 0;
                } else if (nodeName3.equals("sBIT_GrayAlpha")) {
                    this.sBIT_grayBits = getIntAttribute(firstChild6, "gray");
                    this.sBIT_alphaBits = getIntAttribute(firstChild6, "alpha");
                    this.sBIT_colorType = 4;
                } else if (nodeName3.equals("sBIT_RGB")) {
                    this.sBIT_redBits = getIntAttribute(firstChild6, "red");
                    this.sBIT_greenBits = getIntAttribute(firstChild6, "green");
                    this.sBIT_blueBits = getIntAttribute(firstChild6, "blue");
                    this.sBIT_colorType = 2;
                } else if (nodeName3.equals("sBIT_RGBAlpha")) {
                    this.sBIT_redBits = getIntAttribute(firstChild6, "red");
                    this.sBIT_greenBits = getIntAttribute(firstChild6, "green");
                    this.sBIT_blueBits = getIntAttribute(firstChild6, "blue");
                    this.sBIT_alphaBits = getIntAttribute(firstChild6, "alpha");
                    this.sBIT_colorType = 6;
                } else if (nodeName3.equals("sBIT_Palette")) {
                    this.sBIT_redBits = getIntAttribute(firstChild6, "red");
                    this.sBIT_greenBits = getIntAttribute(firstChild6, "green");
                    this.sBIT_blueBits = getIntAttribute(firstChild6, "blue");
                    this.sBIT_colorType = 3;
                } else {
                    fatal(firstChild, "Bad child of an sBIT node!");
                }
                if (firstChild6.getNextSibling() != null) {
                    fatal(firstChild, "sBIT node has more than one child!");
                }
                this.sBIT_present = true;
            } else if (nodeName.equals("sPLT")) {
                this.sPLT_paletteName = toPrintableLatin1(getAttribute(firstChild, "name"));
                this.sPLT_sampleDepth = getIntAttribute(firstChild, "sampleDepth");
                int[] iArr = new int[256];
                int[] iArr2 = new int[256];
                int[] iArr3 = new int[256];
                int[] iArr4 = new int[256];
                int[] iArr5 = new int[256];
                Node firstChild7 = firstChild.getFirstChild();
                if (firstChild7 == null) {
                    fatal(firstChild, "sPLT node has no children!");
                }
                int i5 = -1;
                while (firstChild7 != null) {
                    int[] iArr6 = iArr5;
                    if (!firstChild7.getNodeName().equals("sPLTEntry")) {
                        fatal(firstChild, "Only an sPLTEntry may be a child of an sPLT!");
                    }
                    int intAttribute3 = getIntAttribute(firstChild7, FirebaseAnalytics.Param.INDEX);
                    if (intAttribute3 < 0 || intAttribute3 > 255) {
                        fatal(firstChild, "Bad value for PLTEEntry attribute index!");
                    }
                    if (intAttribute3 > i5) {
                        i5 = intAttribute3;
                    }
                    iArr[intAttribute3] = getIntAttribute(firstChild7, "red");
                    iArr2[intAttribute3] = getIntAttribute(firstChild7, "green");
                    iArr3[intAttribute3] = getIntAttribute(firstChild7, "blue");
                    iArr4[intAttribute3] = getIntAttribute(firstChild7, "alpha");
                    iArr6[intAttribute3] = getIntAttribute(firstChild7, "frequency");
                    firstChild7 = firstChild7.getNextSibling();
                    iArr5 = iArr6;
                }
                int[] iArr7 = iArr5;
                int i6 = i5 + 1;
                int[] iArr8 = new int[i6];
                this.sPLT_red = iArr8;
                this.sPLT_green = new int[i6];
                this.sPLT_blue = new int[i6];
                this.sPLT_alpha = new int[i6];
                this.sPLT_frequency = new int[i6];
                System.arraycopy(iArr, 0, iArr8, 0, i6);
                System.arraycopy(iArr2, 0, this.sPLT_green, 0, i6);
                System.arraycopy(iArr3, 0, this.sPLT_blue, 0, i6);
                System.arraycopy(iArr4, 0, this.sPLT_alpha, 0, i6);
                System.arraycopy(iArr7, 0, this.sPLT_frequency, 0, i6);
                this.sPLT_present = true;
            } else if (nodeName.equals("sRGB")) {
                this.sRGB_renderingIntent = getEnumeratedAttribute(firstChild, "renderingIntent", renderingIntentNames);
                this.sRGB_present = true;
            } else if (nodeName.equals("tEXt")) {
                for (Node firstChild8 = firstChild.getFirstChild(); firstChild8 != null; firstChild8 = firstChild8.getNextSibling()) {
                    if (!firstChild8.getNodeName().equals("tEXtEntry")) {
                        fatal(firstChild, "Only an tEXtEntry may be a child of an tEXt!");
                    }
                    this.tEXt_keyword.add(toPrintableLatin1(getAttribute(firstChild8, "keyword")));
                    this.tEXt_text.add(getAttribute(firstChild8, "value"));
                }
            } else if (nodeName.equals("tIME")) {
                this.tIME_year = getIntAttribute(firstChild, "year");
                this.tIME_month = getIntAttribute(firstChild, "month");
                this.tIME_day = getIntAttribute(firstChild, "day");
                this.tIME_hour = getIntAttribute(firstChild, "hour");
                this.tIME_minute = getIntAttribute(firstChild, "minute");
                this.tIME_second = getIntAttribute(firstChild, "second");
                this.tIME_present = true;
            } else if (nodeName.equals("tRNS")) {
                this.tRNS_present = false;
                Node firstChild9 = firstChild.getFirstChild();
                if (firstChild9 == null) {
                    fatal(firstChild, "tRNS node has no children!");
                }
                String nodeName4 = firstChild9.getNodeName();
                if (nodeName4.equals("tRNS_Palette")) {
                    byte[] bArr5 = new byte[256];
                    Node firstChild10 = firstChild9.getFirstChild();
                    if (firstChild10 == null) {
                        fatal(firstChild, "tRNS_Palette node has no children!");
                    }
                    int i7 = -1;
                    while (firstChild10 != null) {
                        if (!firstChild10.getNodeName().equals("tRNS_PaletteEntry")) {
                            fatal(firstChild, "Only a tRNS_PaletteEntry may be a child of a tRNS_Palette!");
                        }
                        int intAttribute4 = getIntAttribute(firstChild10, FirebaseAnalytics.Param.INDEX);
                        if (intAttribute4 < 0 || intAttribute4 > 255) {
                            fatal(firstChild, "Bad value for tRNS_PaletteEntry attribute index!");
                        }
                        if (intAttribute4 > i7) {
                            i7 = intAttribute4;
                        }
                        bArr5[intAttribute4] = (byte) getIntAttribute(firstChild10, "alpha");
                        firstChild10 = firstChild10.getNextSibling();
                    }
                    int i8 = i7 + 1;
                    byte[] bArr6 = new byte[i8];
                    this.tRNS_alpha = bArr6;
                    this.tRNS_colorType = 3;
                    System.arraycopy(bArr5, 0, bArr6, 0, i8);
                } else if (nodeName4.equals("tRNS_Grayscale")) {
                    this.tRNS_gray = getIntAttribute(firstChild9, "gray");
                    this.tRNS_colorType = 0;
                } else if (nodeName4.equals("tRNS_RGB")) {
                    this.tRNS_red = getIntAttribute(firstChild9, "red");
                    this.tRNS_green = getIntAttribute(firstChild9, "green");
                    this.tRNS_blue = getIntAttribute(firstChild9, "blue");
                    this.tRNS_colorType = 2;
                } else {
                    fatal(firstChild, "Bad child of a tRNS node!");
                }
                if (firstChild9.getNextSibling() != null) {
                    fatal(firstChild, "tRNS node has more than one child!");
                }
                this.tRNS_present = true;
            } else if (nodeName.equals("zTXt")) {
                for (Node firstChild11 = firstChild.getFirstChild(); firstChild11 != null; firstChild11 = firstChild11.getNextSibling()) {
                    if (!firstChild11.getNodeName().equals("zTXtEntry")) {
                        fatal(firstChild, "Only an zTXtEntry may be a child of an zTXt!");
                    }
                    this.zTXt_keyword.add(toPrintableLatin1(getAttribute(firstChild11, "keyword")));
                    this.zTXt_compressionMethod.add(new Integer(getEnumeratedAttribute(firstChild11, "compressionMethod", zTXt_compressionMethodNames)));
                    this.zTXt_text.add(getAttribute(firstChild11, "text"));
                }
            } else if (nodeName.equals("UnknownChunks")) {
                for (Node firstChild12 = firstChild.getFirstChild(); firstChild12 != null; firstChild12 = firstChild12.getNextSibling()) {
                    if (!firstChild12.getNodeName().equals("UnknownChunk")) {
                        fatal(firstChild, "Only an UnknownChunk may be a child of an UnknownChunks!");
                    }
                    String attribute = getAttribute(firstChild12, "type");
                    Object userObject2 = ((IIOMetadataNode) firstChild12).getUserObject();
                    if (attribute.length() != 4) {
                        fatal(firstChild12, "Chunk type must be 4 characters!");
                    }
                    if (userObject2 == null) {
                        fatal(firstChild12, "No chunk data present in user object!");
                    }
                    if (!(userObject2 instanceof byte[])) {
                        fatal(firstChild12, "User object not a byte array!");
                    }
                    this.unknownChunkType.add(attribute);
                    this.unknownChunkData.add(((byte[]) userObject2).clone());
                }
            } else {
                fatal(firstChild, "Unknown child of root node!");
            }
        }
    }

    private boolean isISOLatin(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) > 255) {
                return false;
            }
        }
        return true;
    }

    private void mergeStandardTree(Node node) throws IIOInvalidTreeException {
        int intAttribute;
        if (!node.getNodeName().equals("javax_imageio_1.0")) {
            fatal(node, "Root must be javax_imageio_1.0");
        }
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            String nodeName = firstChild.getNodeName();
            int i = 1;
            if (nodeName.equals("Chroma")) {
                for (Node firstChild2 = firstChild.getFirstChild(); firstChild2 != null; firstChild2 = firstChild2.getNextSibling()) {
                    String nodeName2 = firstChild2.getNodeName();
                    if (nodeName2.equals(ExifInterface.TAG_GAMMA)) {
                        float floatAttribute = getFloatAttribute(firstChild2, "value");
                        this.gAMA_present = true;
                        this.gAMA_gamma = (int) ((floatAttribute * 100000.0f) + 0.5d);
                    } else if (nodeName2.equals("Palette")) {
                        byte[] bArr = new byte[256];
                        byte[] bArr2 = new byte[256];
                        byte[] bArr3 = new byte[256];
                        int i2 = -1;
                        for (Node firstChild3 = firstChild2.getFirstChild(); firstChild3 != null; firstChild3 = firstChild3.getNextSibling()) {
                            if (firstChild3.getNodeName().equals("PaletteEntry") && (intAttribute = getIntAttribute(firstChild3, FirebaseAnalytics.Param.INDEX)) >= 0 && intAttribute <= 255) {
                                bArr[intAttribute] = (byte) getIntAttribute(firstChild3, "red");
                                bArr2[intAttribute] = (byte) getIntAttribute(firstChild3, "green");
                                bArr3[intAttribute] = (byte) getIntAttribute(firstChild3, "blue");
                                if (intAttribute > i2) {
                                    i2 = intAttribute;
                                }
                            }
                        }
                        int i3 = i2 + 1;
                        byte[] bArr4 = new byte[i3];
                        this.PLTE_red = bArr4;
                        this.PLTE_green = new byte[i3];
                        this.PLTE_blue = new byte[i3];
                        System.arraycopy(bArr, 0, bArr4, 0, i3);
                        System.arraycopy(bArr2, 0, this.PLTE_green, 0, i3);
                        System.arraycopy(bArr3, 0, this.PLTE_blue, 0, i3);
                        this.PLTE_present = true;
                    } else if (nodeName2.equals("BackgroundIndex")) {
                        this.bKGD_present = true;
                        this.bKGD_colorType = 3;
                        this.bKGD_index = getIntAttribute(firstChild2, "value");
                    } else if (nodeName2.equals("BackgroundColor")) {
                        int intAttribute2 = getIntAttribute(firstChild2, "red");
                        int intAttribute3 = getIntAttribute(firstChild2, "green");
                        int intAttribute4 = getIntAttribute(firstChild2, "blue");
                        if (intAttribute2 == intAttribute3 && intAttribute2 == intAttribute4) {
                            this.bKGD_colorType = 0;
                            this.bKGD_gray = intAttribute2;
                        } else {
                            this.bKGD_colorType = 2;
                            this.bKGD_red = intAttribute2;
                            this.bKGD_green = intAttribute3;
                            this.bKGD_blue = intAttribute4;
                        }
                        this.bKGD_present = true;
                    }
                }
            } else if (nodeName.equals(ExifInterface.TAG_COMPRESSION)) {
                for (Node firstChild4 = firstChild.getFirstChild(); firstChild4 != null; firstChild4 = firstChild4.getNextSibling()) {
                    if (firstChild4.getNodeName().equals("NumProgressiveScans")) {
                        this.IHDR_interlaceMethod = getIntAttribute(firstChild4, "value") > 1 ? 1 : 0;
                    }
                }
            } else if (nodeName.equals("Data")) {
                for (Node firstChild5 = firstChild.getFirstChild(); firstChild5 != null; firstChild5 = firstChild5.getNextSibling()) {
                    String nodeName3 = firstChild5.getNodeName();
                    int i4 = 4;
                    if (nodeName3.equals(ExifInterface.TAG_BITS_PER_SAMPLE)) {
                        StringTokenizer stringTokenizer = new StringTokenizer(getAttribute(firstChild5, "value"));
                        int i5 = -1;
                        while (stringTokenizer.hasMoreTokens()) {
                            int parseInt = Integer.parseInt(stringTokenizer.nextToken());
                            if (parseInt > i5) {
                                i5 = parseInt;
                            }
                        }
                        if (i5 < 1) {
                            i4 = 1;
                        } else if (i5 != 3) {
                            i4 = (i5 <= 4 || i5 >= 8) ? i5 > 8 ? 16 : i5 : 8;
                        }
                        this.IHDR_bitDepth = i4;
                    } else if (nodeName3.equals("SignificantBitsPerSample")) {
                        StringTokenizer stringTokenizer2 = new StringTokenizer(getAttribute(firstChild5, "value"));
                        int countTokens = stringTokenizer2.countTokens();
                        if (countTokens == 1) {
                            this.sBIT_colorType = 0;
                            this.sBIT_grayBits = Integer.parseInt(stringTokenizer2.nextToken());
                        } else if (countTokens == 2) {
                            this.sBIT_colorType = 4;
                            this.sBIT_grayBits = Integer.parseInt(stringTokenizer2.nextToken());
                            this.sBIT_alphaBits = Integer.parseInt(stringTokenizer2.nextToken());
                        } else {
                            if (countTokens == 3) {
                                this.sBIT_colorType = 2;
                                this.sBIT_redBits = Integer.parseInt(stringTokenizer2.nextToken());
                                this.sBIT_greenBits = Integer.parseInt(stringTokenizer2.nextToken());
                                this.sBIT_blueBits = Integer.parseInt(stringTokenizer2.nextToken());
                            } else if (countTokens == 4) {
                                this.sBIT_colorType = 6;
                                this.sBIT_redBits = Integer.parseInt(stringTokenizer2.nextToken());
                                this.sBIT_greenBits = Integer.parseInt(stringTokenizer2.nextToken());
                                this.sBIT_blueBits = Integer.parseInt(stringTokenizer2.nextToken());
                                this.sBIT_alphaBits = Integer.parseInt(stringTokenizer2.nextToken());
                            }
                            if (countTokens >= 1 && countTokens <= 4) {
                                this.sBIT_present = true;
                            }
                        }
                        if (countTokens >= 1) {
                            this.sBIT_present = true;
                        }
                    }
                }
            } else if (nodeName.equals("Dimension")) {
                Node firstChild6 = firstChild.getFirstChild();
                float f = -1.0f;
                float f2 = -1.0f;
                float f3 = -1.0f;
                boolean z = false;
                boolean z2 = false;
                boolean z3 = false;
                while (firstChild6 != null) {
                    String nodeName4 = firstChild6.getNodeName();
                    if (nodeName4.equals("PixelAspectRatio")) {
                        f3 = getFloatAttribute(firstChild6, "value");
                        z3 = true;
                    } else if (nodeName4.equals("HorizontalPixelSize")) {
                        f = getFloatAttribute(firstChild6, "value");
                        z = true;
                    } else if (nodeName4.equals("VerticalPixelSize")) {
                        f2 = getFloatAttribute(firstChild6, "value");
                        z2 = true;
                    }
                    firstChild6 = firstChild6.getNextSibling();
                    z = z;
                    z2 = z2;
                    z3 = z3;
                }
                if (z && z2) {
                    this.pHYs_present = true;
                    this.pHYs_unitSpecifier = 1;
                    this.pHYs_pixelsPerUnitXAxis = (int) ((1000.0f / f) + 0.5f);
                    this.pHYs_pixelsPerUnitYAxis = (int) ((1000.0f / f2) + 0.5f);
                } else if (z3) {
                    this.pHYs_present = true;
                    this.pHYs_unitSpecifier = 0;
                    while (i < 100 && Math.abs((((int) (i * f3)) / i) - f3) >= 0.001d) {
                        i++;
                    }
                    this.pHYs_pixelsPerUnitXAxis = (int) (f3 * i);
                    this.pHYs_pixelsPerUnitYAxis = i;
                }
            } else if (nodeName.equals(StandardStructureTypes.DOCUMENT)) {
                for (Node firstChild7 = firstChild.getFirstChild(); firstChild7 != null; firstChild7 = firstChild7.getNextSibling()) {
                    if (firstChild7.getNodeName().equals("ImageModificationTime")) {
                        this.tIME_present = true;
                        this.tIME_year = getIntAttribute(firstChild7, "year");
                        this.tIME_month = getIntAttribute(firstChild7, "month");
                        this.tIME_day = getIntAttribute(firstChild7, "day");
                        this.tIME_hour = getIntAttribute(firstChild7, "hour", 0, false);
                        this.tIME_minute = getIntAttribute(firstChild7, "minute", 0, false);
                        this.tIME_second = getIntAttribute(firstChild7, "second", 0, false);
                    }
                }
            } else if (nodeName.equals("Text")) {
                for (Node firstChild8 = firstChild.getFirstChild(); firstChild8 != null; firstChild8 = firstChild8.getNextSibling()) {
                    if (firstChild8.getNodeName().equals("TextEntry")) {
                        String attribute = getAttribute(firstChild8, "keyword", "text", false);
                        String attribute2 = getAttribute(firstChild8, "value");
                        getAttribute(firstChild8, "encoding", "unknown", false);
                        String attribute3 = getAttribute(firstChild8, Device.JsonKeys.LANGUAGE, "unknown", false);
                        String attribute4 = getAttribute(firstChild8, "compression", "other", false);
                        if (isISOLatin(attribute2)) {
                            if (attribute4.equals("zip")) {
                                this.zTXt_keyword.add(toPrintableLatin1(attribute));
                                this.zTXt_text.add(attribute2);
                                this.zTXt_compressionMethod.add(new Integer(0));
                            } else {
                                this.tEXt_keyword.add(toPrintableLatin1(attribute));
                                this.tEXt_text.add(attribute2);
                            }
                        } else {
                            boolean equals = attribute4.equals("zip");
                            this.iTXt_keyword.add(toPrintableLatin1(attribute));
                            this.iTXt_compressionFlag.add(new Integer(equals ? 1 : 0));
                            this.iTXt_compressionMethod.add(new Integer(0));
                            this.iTXt_languageTag.add(attribute3);
                            this.iTXt_translatedKeyword.add(attribute);
                            this.iTXt_text.add(attribute2);
                        }
                    }
                }
            }
        }
    }

    public void reset() {
        this.IHDR_present = false;
        this.PLTE_present = false;
        this.bKGD_present = false;
        this.cHRM_present = false;
        this.gAMA_present = false;
        this.hIST_present = false;
        this.iCCP_present = false;
        this.iTXt_keyword = new ArrayList();
        this.iTXt_compressionFlag = new ArrayList();
        this.iTXt_compressionMethod = new ArrayList();
        this.iTXt_languageTag = new ArrayList();
        this.iTXt_translatedKeyword = new ArrayList();
        this.iTXt_text = new ArrayList();
        this.pHYs_present = false;
        this.sBIT_present = false;
        this.sPLT_present = false;
        this.sRGB_present = false;
        this.tEXt_keyword = new ArrayList();
        this.tEXt_text = new ArrayList();
        this.tIME_present = false;
        this.tRNS_present = false;
        this.zTXt_keyword = new ArrayList();
        this.zTXt_compressionMethod = new ArrayList();
        this.zTXt_text = new ArrayList();
        this.unknownChunkType = new ArrayList();
        this.unknownChunkData = new ArrayList();
    }

    private static int chunkType(String str) {
        return str.charAt(3) | (str.charAt(0) << 24) | (str.charAt(1) << 16) | (str.charAt(2) << '\b');
    }

    private String readNullTerminatedString(ImageInputStream imageInputStream) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            int read = imageInputStream.read();
            if (read != 0) {
                stringBuffer.append((char) read);
            } else {
                return stringBuffer.toString();
            }
        }
    }
}
