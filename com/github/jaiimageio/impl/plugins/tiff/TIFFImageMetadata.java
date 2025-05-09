package com.github.jaiimageio.impl.plugins.tiff;

import androidx.exifinterface.media.ExifInterface;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.github.jaiimageio.plugins.tiff.EXIFParentTIFFTagSet;
import com.github.jaiimageio.plugins.tiff.TIFFField;
import com.github.jaiimageio.plugins.tiff.TIFFTag;
import com.github.jaiimageio.plugins.tiff.TIFFTagSet;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;
import kotlin.jvm.internal.CharCompanionObject;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: classes3.dex */
public class TIFFImageMetadata extends IIOMetadata {
    public static final String nativeMetadataFormatClassName = "com.github.jaiimageio.impl.plugins.tiff.TIFFImageMetadataFormat";
    public static final String nativeMetadataFormatName = "com_sun_media_imageio_plugins_tiff_image_1.0";
    TIFFIFD rootIFD;
    List tagSets;
    private static final String[] colorSpaceNames = {"GRAY", "GRAY", "RGB", "RGB", "GRAY", "CMYK", "YCbCr", "Lab", "Lab"};
    private static final String[] orientationNames = {null, PDLayoutAttributeObject.LINE_HEIGHT_NORMAL, "FlipH", "Rotate180", "FlipV", "FlipHRotate90", "Rotate270", "FlipVRotate90", "Rotate90"};

    public boolean isReadOnly() {
        return false;
    }

    public TIFFImageMetadata(List list) {
        super(true, nativeMetadataFormatName, nativeMetadataFormatClassName, (String[]) null, (String[]) null);
        this.tagSets = list;
        this.rootIFD = new TIFFIFD(list);
    }

    public TIFFImageMetadata(TIFFIFD tiffifd) {
        super(true, nativeMetadataFormatName, nativeMetadataFormatClassName, (String[]) null, (String[]) null);
        this.tagSets = tiffifd.getTagSetList();
        this.rootIFD = tiffifd;
    }

    public void initializeFromStream(ImageInputStream imageInputStream, boolean z) throws IOException {
        this.rootIFD.initialize(imageInputStream, z);
    }

    public void addShortOrLongField(int i, int i2) {
        this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(i), i2));
    }

    private Node getIFDAsTree(TIFFIFD tiffifd, String str, int i) {
        Node asNativeNode;
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("TIFFIFD");
        if (i != 0) {
            iIOMetadataNode.setAttribute("parentTagNumber", Integer.toString(i));
        }
        if (str != null) {
            iIOMetadataNode.setAttribute("parentTagName", str);
        }
        List tagSetList = tiffifd.getTagSetList();
        if (tagSetList.size() > 0) {
            Iterator it = tagSetList.iterator();
            String str2 = "";
            while (it.hasNext()) {
                str2 = str2 + ((TIFFTagSet) it.next()).getClass().getName();
                if (it.hasNext()) {
                    str2 = str2 + ",";
                }
            }
            iIOMetadataNode.setAttribute("tagSets", str2);
        }
        Iterator it2 = tiffifd.iterator();
        while (it2.hasNext()) {
            TIFFField tIFFField = (TIFFField) it2.next();
            TIFFTag tag = TIFFIFD.getTag(tIFFField.getTagNumber(), tagSetList);
            if (tag == null) {
                asNativeNode = tIFFField.getAsNativeNode();
            } else if (tag.isIFDPointer()) {
                asNativeNode = getIFDAsTree((TIFFIFD) tIFFField.getData(), tag.getName(), tag.getNumber());
            } else {
                asNativeNode = tIFFField.getAsNativeNode();
            }
            if (asNativeNode != null) {
                iIOMetadataNode.appendChild(asNativeNode);
            }
        }
        return iIOMetadataNode;
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
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode(nativeMetadataFormatName);
        iIOMetadataNode.appendChild(getIFDAsTree(this.rootIFD, null, 0));
        return iIOMetadataNode;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public IIOMetadataNode getStandardChromaNode() {
        int i;
        boolean z;
        int count;
        TIFFField tIFFField;
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Chroma");
        TIFFField tIFFField2 = getTIFFField(BaselineTIFFTagSet.TAG_PHOTOMETRIC_INTERPRETATION);
        if (tIFFField2 != null) {
            i = tIFFField2.getAsInt(0);
            if (i == 3) {
                z = true;
                if (z) {
                    TIFFField tIFFField3 = getTIFFField(BaselineTIFFTagSet.TAG_SAMPLES_PER_PIXEL);
                    if (tIFFField3 != null) {
                        count = tIFFField3.getAsInt(0);
                    } else {
                        TIFFField tIFFField4 = getTIFFField(258);
                        count = tIFFField4 != null ? tIFFField4.getCount() : -1;
                    }
                } else {
                    count = 3;
                }
                if (i != -1) {
                    if (i >= 0) {
                        String[] strArr = colorSpaceNames;
                        if (i < strArr.length) {
                            IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("ColorSpaceType");
                            iIOMetadataNode2.setAttribute("name", (i == 5 && count == 3) ? "CMY" : strArr[i]);
                            iIOMetadataNode.appendChild(iIOMetadataNode2);
                        }
                    }
                    IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("BlackIsZero");
                    iIOMetadataNode3.setAttribute("value", i == 0 ? "FALSE" : "TRUE");
                    iIOMetadataNode.appendChild(iIOMetadataNode3);
                }
                if (count != -1) {
                    IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("NumChannels");
                    iIOMetadataNode4.setAttribute("value", Integer.toString(count));
                    iIOMetadataNode.appendChild(iIOMetadataNode4);
                }
                tIFFField = getTIFFField(BaselineTIFFTagSet.TAG_COLOR_MAP);
                if (tIFFField != null) {
                    IIOMetadataNode iIOMetadataNode5 = new IIOMetadataNode("Palette");
                    int count2 = tIFFField.getCount() / 3;
                    for (int i2 = 0; i2 < count2; i2++) {
                        IIOMetadataNode iIOMetadataNode6 = new IIOMetadataNode("PaletteEntry");
                        iIOMetadataNode6.setAttribute(FirebaseAnalytics.Param.INDEX, Integer.toString(i2));
                        int asInt = (tIFFField.getAsInt(i2) * 255) / 65535;
                        int asInt2 = (tIFFField.getAsInt(count2 + i2) * 255) / 65535;
                        int asInt3 = (tIFFField.getAsInt((count2 * 2) + i2) * 255) / 65535;
                        iIOMetadataNode6.setAttribute("red", Integer.toString(asInt));
                        iIOMetadataNode6.setAttribute("green", Integer.toString(asInt2));
                        iIOMetadataNode6.setAttribute("blue", Integer.toString(asInt3));
                        iIOMetadataNode5.appendChild(iIOMetadataNode6);
                    }
                    iIOMetadataNode.appendChild(iIOMetadataNode5);
                }
                return iIOMetadataNode;
            }
        } else {
            i = -1;
        }
        z = false;
        if (z) {
        }
        if (i != -1) {
        }
        if (count != -1) {
        }
        tIFFField = getTIFFField(BaselineTIFFTagSet.TAG_COLOR_MAP);
        if (tIFFField != null) {
        }
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardCompressionNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode(ExifInterface.TAG_COMPRESSION);
        TIFFField tIFFField = getTIFFField(BaselineTIFFTagSet.TAG_COMPRESSION);
        if (tIFFField != null) {
            String str = null;
            int i = 0;
            int asInt = tIFFField.getAsInt(0);
            boolean z = true;
            if (asInt == 1) {
                str = "None";
            } else {
                int[] iArr = TIFFImageWriter.compressionNumbers;
                while (true) {
                    if (i >= iArr.length) {
                        break;
                    }
                    if (asInt == iArr[i]) {
                        str = TIFFImageWriter.compressionTypes[i];
                        z = TIFFImageWriter.isCompressionLossless[i];
                        break;
                    }
                    i++;
                }
            }
            if (str != null) {
                IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("CompressionTypeName");
                iIOMetadataNode2.setAttribute("value", str);
                iIOMetadataNode.appendChild(iIOMetadataNode2);
                IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("Lossless");
                iIOMetadataNode3.setAttribute("value", z ? "TRUE" : "FALSE");
                iIOMetadataNode.appendChild(iIOMetadataNode3);
            }
        }
        IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("NumProgressiveScans");
        iIOMetadataNode4.setAttribute("value", "1");
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
        int[] iArr;
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Data");
        TIFFField tIFFField = getTIFFField(BaselineTIFFTagSet.TAG_PHOTOMETRIC_INTERPRETATION);
        boolean z = tIFFField != null && tIFFField.getAsInt(0) == 3;
        TIFFField tIFFField2 = getTIFFField(BaselineTIFFTagSet.TAG_PLANAR_CONFIGURATION);
        String str = (tIFFField2 == null || tIFFField2.getAsInt(0) != 2) ? "PixelInterleaved" : "PlaneInterleaved";
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode(ExifInterface.TAG_PLANAR_CONFIGURATION);
        iIOMetadataNode2.setAttribute("value", str);
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        TIFFField tIFFField3 = getTIFFField(BaselineTIFFTagSet.TAG_PHOTOMETRIC_INTERPRETATION);
        if (tIFFField3 != null) {
            String str2 = "UnsignedIntegral";
            if (tIFFField3.getAsInt(0) == 3) {
                str2 = StandardStructureTypes.INDEX;
            } else {
                TIFFField tIFFField4 = getTIFFField(BaselineTIFFTagSet.TAG_SAMPLE_FORMAT);
                if (tIFFField4 != null) {
                    int asInt = tIFFField4.getAsInt(0);
                    if (asInt == 2) {
                        str2 = "SignedIntegral";
                    } else if (asInt != 1) {
                        str2 = asInt == 3 ? "Real" : null;
                    }
                }
            }
            if (str2 != null) {
                IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("SampleFormat");
                iIOMetadataNode3.setAttribute("value", str2);
                iIOMetadataNode.appendChild(iIOMetadataNode3);
            }
        }
        TIFFField tIFFField5 = getTIFFField(258);
        if (tIFFField5 != null) {
            iArr = tIFFField5.getAsInts();
        } else {
            TIFFField tIFFField6 = getTIFFField(BaselineTIFFTagSet.TAG_COMPRESSION);
            int asInt2 = tIFFField6 != null ? tIFFField6.getAsInt(0) : 1;
            if (getTIFFField(EXIFParentTIFFTagSet.TAG_EXIF_IFD_POINTER) != null || asInt2 == 7 || asInt2 == 6 || getTIFFField(513) != null) {
                TIFFField tIFFField7 = getTIFFField(BaselineTIFFTagSet.TAG_PHOTOMETRIC_INTERPRETATION);
                iArr = (tIFFField7 == null || !(tIFFField7.getAsInt(0) == 0 || tIFFField7.getAsInt(0) == 1)) ? new int[]{8, 8, 8} : new int[]{8};
            } else {
                iArr = new int[]{1};
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < iArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(" ");
            }
            stringBuffer.append(Integer.toString(iArr[i]));
        }
        IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode(ExifInterface.TAG_BITS_PER_SAMPLE);
        if (z) {
            iIOMetadataNode4.setAttribute("value", repeat(stringBuffer.toString(), 3));
        } else {
            iIOMetadataNode4.setAttribute("value", stringBuffer.toString());
        }
        iIOMetadataNode.appendChild(iIOMetadataNode4);
        TIFFField tIFFField8 = getTIFFField(BaselineTIFFTagSet.TAG_FILL_ORDER);
        int asInt3 = tIFFField8 != null ? tIFFField8.getAsInt(0) : 1;
        StringBuffer stringBuffer2 = new StringBuffer();
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (i2 > 0) {
                stringBuffer2.append(" ");
            }
            int i3 = iArr[i2] == 1 ? 7 : iArr[i2] - 1;
            if (asInt3 != 1) {
                i3 = 0;
            }
            stringBuffer2.append(Integer.toString(i3));
        }
        IIOMetadataNode iIOMetadataNode5 = new IIOMetadataNode("SampleMSB");
        if (z) {
            iIOMetadataNode5.setAttribute("value", repeat(stringBuffer2.toString(), 3));
        } else {
            iIOMetadataNode5.setAttribute("value", stringBuffer2.toString());
        }
        iIOMetadataNode.appendChild(iIOMetadataNode5);
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardDimensionNode() {
        int asInt;
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Dimension");
        TIFFField tIFFField = getTIFFField(BaselineTIFFTagSet.TAG_X_RESOLUTION);
        long[] jArr = tIFFField != null ? (long[]) tIFFField.getAsRational(0).clone() : null;
        TIFFField tIFFField2 = getTIFFField(BaselineTIFFTagSet.TAG_Y_RESOLUTION);
        long[] jArr2 = tIFFField2 != null ? (long[]) tIFFField2.getAsRational(0).clone() : null;
        if (jArr != null && jArr2 != null) {
            IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("PixelAspectRatio");
            iIOMetadataNode2.setAttribute("value", Float.toString(((float) (jArr[1] * jArr2[0])) / ((float) (jArr[0] * jArr2[1]))));
            iIOMetadataNode.appendChild(iIOMetadataNode2);
        }
        if (jArr != null || jArr2 != null) {
            TIFFField tIFFField3 = getTIFFField(BaselineTIFFTagSet.TAG_RESOLUTION_UNIT);
            int asInt2 = tIFFField3 != null ? tIFFField3.getAsInt(0) : 2;
            boolean z = asInt2 != 1;
            if (asInt2 == 2) {
                if (jArr != null) {
                    jArr[0] = jArr[0] * 100;
                    jArr[1] = jArr[1] * 254;
                }
                if (jArr2 != null) {
                    jArr2[0] = jArr2[0] * 100;
                    jArr2[1] = jArr2[1] * 254;
                }
            }
            if (z) {
                if (jArr != null) {
                    IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("HorizontalPixelSize");
                    iIOMetadataNode3.setAttribute("value", Float.toString((float) ((jArr[1] * 10.0d) / jArr[0])));
                    iIOMetadataNode.appendChild(iIOMetadataNode3);
                }
                if (jArr2 != null) {
                    float f = (float) ((jArr2[1] * 10.0d) / jArr2[0]);
                    IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("VerticalPixelSize");
                    iIOMetadataNode4.setAttribute("value", Float.toString(f));
                    iIOMetadataNode.appendChild(iIOMetadataNode4);
                }
            }
        }
        TIFFField tIFFField4 = getTIFFField(BaselineTIFFTagSet.TAG_RESOLUTION_UNIT);
        int asInt3 = tIFFField4 != null ? tIFFField4.getAsInt(0) : 2;
        if (asInt3 == 2 || asInt3 == 3) {
            TIFFField tIFFField5 = getTIFFField(BaselineTIFFTagSet.TAG_X_POSITION);
            if (tIFFField5 != null) {
                long[] asRational = tIFFField5.getAsRational(0);
                float f2 = ((float) asRational[0]) / ((float) asRational[1]);
                float f3 = asInt3 == 2 ? f2 * 254.0f : f2 * 10.0f;
                IIOMetadataNode iIOMetadataNode5 = new IIOMetadataNode("HorizontalPosition");
                iIOMetadataNode5.setAttribute("value", Float.toString(f3));
                iIOMetadataNode.appendChild(iIOMetadataNode5);
            }
            TIFFField tIFFField6 = getTIFFField(BaselineTIFFTagSet.TAG_Y_POSITION);
            if (tIFFField6 != null) {
                long[] asRational2 = tIFFField6.getAsRational(0);
                float f4 = ((float) asRational2[0]) / ((float) asRational2[1]);
                float f5 = asInt3 == 2 ? f4 * 254.0f : f4 * 10.0f;
                IIOMetadataNode iIOMetadataNode6 = new IIOMetadataNode("VerticalPosition");
                iIOMetadataNode6.setAttribute("value", Float.toString(f5));
                iIOMetadataNode.appendChild(iIOMetadataNode6);
            }
        }
        TIFFField tIFFField7 = getTIFFField(BaselineTIFFTagSet.TAG_ORIENTATION);
        if (tIFFField7 != null && (asInt = tIFFField7.getAsInt(0)) >= 0) {
            String[] strArr = orientationNames;
            if (asInt < strArr.length) {
                IIOMetadataNode iIOMetadataNode7 = new IIOMetadataNode("ImageOrientation");
                iIOMetadataNode7.setAttribute("value", strArr[asInt]);
                iIOMetadataNode.appendChild(iIOMetadataNode7);
            }
        }
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardDocumentNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode(StandardStructureTypes.DOCUMENT);
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("FormatVersion");
        iIOMetadataNode2.setAttribute("value", "6.0");
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        TIFFField tIFFField = getTIFFField(254);
        boolean z = false;
        if (tIFFField != null) {
            int asInt = tIFFField.getAsInt(0);
            String str = null;
            if ((asInt & 4) != 0) {
                str = "TransparencyMask";
            } else if ((asInt & 1) != 0) {
                str = "ReducedResolution";
            } else if ((asInt & 2) != 0) {
                str = "SinglePage";
            }
            if (str != null) {
                IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("SubimageInterpretation");
                iIOMetadataNode3.setAttribute("value", str);
                iIOMetadataNode.appendChild(iIOMetadataNode3);
            }
        }
        TIFFField tIFFField2 = getTIFFField(306);
        if (tIFFField2 != null) {
            String asString = tIFFField2.getAsString(0);
            if (asString.length() == 19) {
                IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("ImageCreationTime");
                try {
                    iIOMetadataNode4.setAttribute("year", asString.substring(0, 4));
                    iIOMetadataNode4.setAttribute("month", asString.substring(5, 7));
                    iIOMetadataNode4.setAttribute("day", asString.substring(8, 10));
                    iIOMetadataNode4.setAttribute("hour", asString.substring(11, 13));
                    iIOMetadataNode4.setAttribute("minute", asString.substring(14, 16));
                    iIOMetadataNode4.setAttribute("second", asString.substring(17, 19));
                    z = true;
                } catch (IndexOutOfBoundsException unused) {
                }
                if (z) {
                    iIOMetadataNode.appendChild(iIOMetadataNode4);
                }
            }
        }
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardTextNode() {
        int[] iArr = {BaselineTIFFTagSet.TAG_DOCUMENT_NAME, 270, BaselineTIFFTagSet.TAG_MAKE, BaselineTIFFTagSet.TAG_MODEL, BaselineTIFFTagSet.TAG_PAGE_NAME, 305, 315, 316, BaselineTIFFTagSet.TAG_INK_NAMES, BaselineTIFFTagSet.TAG_COPYRIGHT};
        IIOMetadataNode iIOMetadataNode = null;
        for (int i = 0; i < 10; i++) {
            TIFFField tIFFField = getTIFFField(iArr[i]);
            if (tIFFField != null) {
                String asString = tIFFField.getAsString(0);
                if (iIOMetadataNode == null) {
                    iIOMetadataNode = new IIOMetadataNode("Text");
                }
                IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("TextEntry");
                iIOMetadataNode2.setAttribute("keyword", tIFFField.getTag().getName());
                iIOMetadataNode2.setAttribute("value", asString);
                iIOMetadataNode.appendChild(iIOMetadataNode2);
            }
        }
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardTransparencyNode() {
        String str;
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Transparency");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("Alpha");
        TIFFField tIFFField = getTIFFField(BaselineTIFFTagSet.TAG_EXTRA_SAMPLES);
        if (tIFFField != null) {
            int[] asInts = tIFFField.getAsInts();
            for (int i = 0; i < asInts.length; i++) {
                if (asInts[i] == 1) {
                    str = "premultiplied";
                    break;
                }
                if (asInts[i] == 2) {
                    str = "nonpremultiplied";
                    break;
                }
            }
        }
        str = "none";
        iIOMetadataNode2.setAttribute("value", str);
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        return iIOMetadataNode;
    }

    private static void fatal(Node node, String str) throws IIOInvalidTreeException {
        throw new IIOInvalidTreeException(str, node);
    }

    private int[] listToIntArray(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, " ");
        ArrayList arrayList = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(new Integer(stringTokenizer.nextToken()));
        }
        int size = arrayList.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = ((Integer) arrayList.get(i)).intValue();
        }
        return iArr;
    }

    private char[] listToCharArray(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, " ");
        ArrayList arrayList = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(new Integer(stringTokenizer.nextToken()));
        }
        int size = arrayList.size();
        char[] cArr = new char[size];
        for (int i = 0; i < size; i++) {
            cArr[i] = (char) ((Integer) arrayList.get(i)).intValue();
        }
        return cArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:319:0x077e  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x08a4  */
    /* JADX WARN: Removed duplicated region for block: B:418:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x020f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void mergeStandardTree(Node node) throws IIOInvalidTreeException {
        String str;
        boolean z;
        int i;
        int i2;
        int count;
        Class<long> cls;
        String str2;
        String str3;
        String str4;
        String str5;
        boolean z2;
        Node node2;
        Class<long> cls2;
        TIFFField tIFFField;
        Node namedItem;
        String str6;
        int i3;
        int i4;
        int i5;
        int i6;
        boolean z3;
        float f;
        Node node3;
        boolean z4;
        TIFFField tIFFField2;
        int i7;
        int i8;
        String str7;
        int i9;
        boolean z5;
        Class<long> cls3;
        String str8;
        String str9;
        String str10;
        Class<long> cls4;
        String str11;
        String str12;
        Node childNode;
        Node childNode2;
        Node node4 = node;
        Class<long> cls5 = long.class;
        if (!node.getNodeName().equals("javax_imageio_1.0")) {
            fatal(node4, "Root must be javax_imageio_1.0");
        }
        String str13 = "Data";
        Node childNode3 = getChildNode(node4, "Data");
        String str14 = StandardStructureTypes.INDEX;
        if (childNode3 == null || (childNode2 = getChildNode(childNode3, "SampleFormat")) == null) {
            str = null;
            z = false;
        } else {
            str = getAttribute(childNode2, "value");
            z = str.equals(StandardStructureTypes.INDEX);
        }
        String str15 = "Chroma";
        if (!z && (childNode = getChildNode(node4, "Chroma")) != null && getChildNode(childNode, "Palette") != null) {
            z = true;
        }
        Node firstChild = node.getFirstChild();
        while (firstChild != null) {
            String nodeName = firstChild.getNodeName();
            if (nodeName.equals(str15)) {
                Node firstChild2 = firstChild.getFirstChild();
                String str16 = null;
                String str17 = null;
                boolean z6 = false;
                while (firstChild2 != null) {
                    String nodeName2 = firstChild2.getNodeName();
                    if (nodeName2.equals("ColorSpaceType")) {
                        str16 = getAttribute(firstChild2, "name");
                    } else if (nodeName2.equals("NumChannels")) {
                        this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(BaselineTIFFTagSet.TAG_SAMPLES_PER_PIXEL), z ? 1 : Integer.parseInt(getAttribute(firstChild2, "value"))));
                    } else if (nodeName2.equals("BlackIsZero")) {
                        str17 = getAttribute(firstChild2, "value");
                    } else if (nodeName2.equals("Palette")) {
                        Node firstChild3 = firstChild2.getFirstChild();
                        HashMap hashMap = new HashMap();
                        int i10 = -1;
                        while (firstChild3 != null) {
                            String str18 = str15;
                            if (firstChild3.getNodeName().equals("PaletteEntry")) {
                                int parseInt = Integer.parseInt(getAttribute(firstChild3, FirebaseAnalytics.Param.INDEX));
                                if (parseInt > i10) {
                                    i10 = parseInt;
                                }
                                str12 = str14;
                                str11 = str;
                                cls4 = cls5;
                                hashMap.put(new Integer(parseInt), new char[]{(char) Integer.parseInt(getAttribute(firstChild3, "red")), (char) Integer.parseInt(getAttribute(firstChild3, "green")), (char) Integer.parseInt(getAttribute(firstChild3, "blue"))});
                                i10 = i10;
                                z6 = true;
                            } else {
                                cls4 = cls5;
                                str11 = str;
                                str12 = str14;
                            }
                            firstChild3 = firstChild3.getNextSibling();
                            str15 = str18;
                            str14 = str12;
                            str = str11;
                            cls5 = cls4;
                        }
                        cls3 = cls5;
                        str8 = str;
                        str9 = str14;
                        str10 = str15;
                        if (z6) {
                            int i11 = i10 + 1;
                            int i12 = i11 * 3;
                            char[] cArr = new char[i12];
                            Iterator it = hashMap.keySet().iterator();
                            while (it.hasNext()) {
                                Integer num = (Integer) it.next();
                                char[] cArr2 = (char[]) hashMap.get(num);
                                int intValue = num.intValue();
                                cArr[intValue] = (char) ((cArr2[0] * CharCompanionObject.MAX_VALUE) / 255);
                                cArr[i11 + intValue] = (char) ((cArr2[1] * CharCompanionObject.MAX_VALUE) / 255);
                                cArr[(i11 * 2) + intValue] = (char) ((cArr2[2] * CharCompanionObject.MAX_VALUE) / 255);
                                it = it;
                                hashMap = hashMap;
                            }
                            this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(BaselineTIFFTagSet.TAG_COLOR_MAP), 3, i12, cArr));
                        }
                        firstChild2 = firstChild2.getNextSibling();
                        str15 = str10;
                        str14 = str9;
                        str = str8;
                        cls5 = cls3;
                    }
                    cls3 = cls5;
                    str8 = str;
                    str9 = str14;
                    str10 = str15;
                    firstChild2 = firstChild2.getNextSibling();
                    str15 = str10;
                    str14 = str9;
                    str = str8;
                    cls5 = cls3;
                }
                cls = cls5;
                str2 = str;
                str3 = str14;
                str4 = str15;
                if ((str16 == null || str16.equals("GRAY")) && (str7 = str17) != null && str7.equalsIgnoreCase("FALSE")) {
                    i9 = 0;
                } else {
                    if (str16 != null) {
                        if (str16.equals("GRAY")) {
                            if (node4 instanceof IIOMetadataNode) {
                                NodeList elementsByTagName = ((IIOMetadataNode) node4).getElementsByTagName("SubimageInterpretation");
                                if (elementsByTagName.getLength() == 1 && getAttribute(elementsByTagName.item(0), "value").equals("TransparencyMask")) {
                                    z5 = true;
                                    i9 = !z5 ? 4 : 1;
                                }
                            }
                            z5 = false;
                            if (!z5) {
                            }
                        } else if (str16.equals("RGB")) {
                            i9 = z6 ? 3 : 2;
                        } else if (str16.equals("YCbCr")) {
                            i9 = 6;
                        } else if (str16.equals("CMYK")) {
                            i9 = 5;
                        } else if (str16.equals("Lab")) {
                            i9 = 8;
                        }
                    }
                    i9 = -1;
                }
                if (i9 != -1) {
                    this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(BaselineTIFFTagSet.TAG_PHOTOMETRIC_INTERPRETATION), i9));
                }
            } else {
                cls = cls5;
                str2 = str;
                str3 = str14;
                str4 = str15;
                if (nodeName.equals(ExifInterface.TAG_COMPRESSION)) {
                    for (Node firstChild4 = firstChild.getFirstChild(); firstChild4 != null; firstChild4 = firstChild4.getNextSibling()) {
                        if (firstChild4.getNodeName().equals("CompressionTypeName")) {
                            String attribute = getAttribute(firstChild4, "value");
                            if (!attribute.equalsIgnoreCase("None")) {
                                String[] strArr = TIFFImageWriter.compressionTypes;
                                int i13 = 0;
                                while (true) {
                                    if (i13 >= strArr.length) {
                                        i8 = -1;
                                        break;
                                    } else {
                                        if (strArr[i13].equalsIgnoreCase(attribute)) {
                                            i8 = TIFFImageWriter.compressionNumbers[i13];
                                            break;
                                        }
                                        i13++;
                                    }
                                }
                            } else {
                                i8 = 1;
                            }
                            if (i8 != -1) {
                                this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(BaselineTIFFTagSet.TAG_COMPRESSION), i8));
                            }
                        }
                    }
                } else if (nodeName.equals(str13)) {
                    for (Node firstChild5 = firstChild.getFirstChild(); firstChild5 != null; firstChild5 = firstChild5.getNextSibling()) {
                        String nodeName3 = firstChild5.getNodeName();
                        if (nodeName3.equals(ExifInterface.TAG_PLANAR_CONFIGURATION)) {
                            String attribute2 = getAttribute(firstChild5, "value");
                            if (attribute2.equals("PixelInterleaved")) {
                                i7 = 1;
                            } else {
                                i7 = attribute2.equals("PlaneInterleaved") ? 2 : -1;
                            }
                            if (i7 != -1) {
                                this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(BaselineTIFFTagSet.TAG_PLANAR_CONFIGURATION), i7));
                            }
                        } else if (nodeName3.equals(ExifInterface.TAG_BITS_PER_SAMPLE)) {
                            char[] listToCharArray = listToCharArray(getAttribute(firstChild5, "value"));
                            TIFFTag tag = this.rootIFD.getTag(258);
                            if (z) {
                                tIFFField2 = new TIFFField(tag, 3, 1, new char[]{listToCharArray[0]});
                            } else {
                                tIFFField2 = new TIFFField(tag, 3, listToCharArray.length, listToCharArray);
                            }
                            this.rootIFD.addTIFFField(tIFFField2);
                        } else if (nodeName3.equals("SampleMSB")) {
                            int[] listToIntArray = listToIntArray(getAttribute(firstChild5, "value"));
                            int i14 = 0;
                            while (true) {
                                if (i14 >= listToIntArray.length) {
                                    z4 = true;
                                    break;
                                } else {
                                    if (listToIntArray[i14] != 0) {
                                        z4 = false;
                                        break;
                                    }
                                    i14++;
                                }
                            }
                            this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(BaselineTIFFTagSet.TAG_FILL_ORDER), z4 ? 2 : 1));
                        }
                    }
                } else {
                    if (nodeName.equals("Dimension")) {
                        Node firstChild6 = firstChild.getFirstChild();
                        float f2 = -1.0f;
                        float f3 = -1.0f;
                        float f4 = -1.0f;
                        float f5 = -1.0f;
                        float f6 = -1.0f;
                        boolean z7 = false;
                        boolean z8 = false;
                        boolean z9 = false;
                        boolean z10 = false;
                        boolean z11 = false;
                        while (firstChild6 != null) {
                            String nodeName4 = firstChild6.getNodeName();
                            String str19 = str13;
                            if (nodeName4.equals("PixelAspectRatio")) {
                                f3 = Float.parseFloat(getAttribute(firstChild6, "value"));
                                z3 = z;
                                node3 = firstChild;
                                z9 = true;
                            } else {
                                if (nodeName4.equals("ImageOrientation")) {
                                    String attribute3 = getAttribute(firstChild6, "value");
                                    z3 = z;
                                    int i15 = 0;
                                    while (true) {
                                        String[] strArr2 = orientationNames;
                                        node3 = firstChild;
                                        if (i15 >= strArr2.length) {
                                            f = f6;
                                            break;
                                        } else if (attribute3.equals(strArr2[i15])) {
                                            f = f6;
                                            this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(BaselineTIFFTagSet.TAG_ORIENTATION), 3, 1, new char[]{(char) i15}));
                                            break;
                                        } else {
                                            i15++;
                                            firstChild = node3;
                                        }
                                    }
                                } else {
                                    z3 = z;
                                    f = f6;
                                    node3 = firstChild;
                                    if (nodeName4.equals("HorizontalPixelSize")) {
                                        f2 = Float.parseFloat(getAttribute(firstChild6, "value"));
                                        f6 = f;
                                        z7 = true;
                                    } else if (nodeName4.equals("VerticalPixelSize")) {
                                        f4 = Float.parseFloat(getAttribute(firstChild6, "value"));
                                        f6 = f;
                                        z8 = true;
                                    } else if (nodeName4.equals("HorizontalPosition")) {
                                        f5 = Float.parseFloat(getAttribute(firstChild6, "value"));
                                        f6 = f;
                                        z10 = true;
                                    } else if (nodeName4.equals("VerticalPosition")) {
                                        f6 = Float.parseFloat(getAttribute(firstChild6, "value"));
                                        z11 = true;
                                    }
                                }
                                f6 = f;
                            }
                            firstChild6 = firstChild6.getNextSibling();
                            str13 = str19;
                            z = z3;
                            firstChild = node3;
                        }
                        str5 = str13;
                        z2 = z;
                        float f7 = f6;
                        node2 = firstChild;
                        boolean z12 = z7 || z8;
                        if (z9) {
                            if (z7 && !z8) {
                                f4 = f2 / f3;
                            } else if (z8 && !z7) {
                                f2 = f4 * f3;
                                z7 = true;
                            } else if (!z7 && !z8) {
                                f2 = f3;
                                f4 = 1.0f;
                                z7 = true;
                            }
                            z8 = true;
                        }
                        if (z7) {
                            float f8 = z12 ? 10.0f : 1.0f;
                            cls2 = cls;
                            long[][] jArr = (long[][]) Array.newInstance((Class<?>) cls2, 1, 2);
                            jArr[0] = new long[2];
                            jArr[0][0] = (f8 / f2) * 10000.0f;
                            jArr[0][1] = 10000;
                            this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(BaselineTIFFTagSet.TAG_X_RESOLUTION), 5, 1, jArr));
                        } else {
                            cls2 = cls;
                        }
                        if (z8) {
                            float f9 = z12 ? 10.0f : 1.0f;
                            long[][] jArr2 = (long[][]) Array.newInstance((Class<?>) cls2, 1, 2);
                            jArr2[0] = new long[2];
                            jArr2[0][0] = (f9 / f4) * 10000.0f;
                            i6 = 1;
                            jArr2[0][1] = 10000;
                            this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(BaselineTIFFTagSet.TAG_Y_RESOLUTION), 5, 1, jArr2));
                        } else {
                            i6 = 1;
                        }
                        char[] cArr3 = new char[i6];
                        cArr3[0] = (char) (z12 ? 3 : i6);
                        this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(BaselineTIFFTagSet.TAG_RESOLUTION_UNIT), 3, i6, cArr3));
                        if (z12) {
                            if (z10) {
                                long[][] jArr3 = (long[][]) Array.newInstance((Class<?>) cls2, 1, 2);
                                jArr3[0][0] = f5 * 10000.0f;
                                jArr3[0][1] = 100000;
                                this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(BaselineTIFFTagSet.TAG_X_POSITION), 5, 1, jArr3));
                            }
                            if (z11) {
                                long[][] jArr4 = (long[][]) Array.newInstance((Class<?>) cls2, 1, 2);
                                jArr4[0][0] = 10000.0f * f7;
                                jArr4[0][1] = 100000;
                                this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(BaselineTIFFTagSet.TAG_Y_POSITION), 5, 1, jArr4));
                            }
                        }
                    } else {
                        str5 = str13;
                        z2 = z;
                        node2 = firstChild;
                        cls2 = cls;
                        if (nodeName.equals(StandardStructureTypes.DOCUMENT)) {
                            for (Node firstChild7 = node2.getFirstChild(); firstChild7 != null; firstChild7 = firstChild7.getNextSibling()) {
                                String nodeName5 = firstChild7.getNodeName();
                                if (nodeName5.equals("SubimageInterpretation")) {
                                    String attribute4 = getAttribute(firstChild7, "value");
                                    if (attribute4.equals("TransparencyMask")) {
                                        i5 = 4;
                                    } else if (attribute4.equals("ReducedResolution")) {
                                        i5 = 1;
                                    } else {
                                        i5 = attribute4.equals("SinglePage") ? 2 : -1;
                                    }
                                    if (i5 != -1) {
                                        this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(254), i5));
                                    }
                                }
                                if (nodeName5.equals("ImageCreationTime")) {
                                    String attribute5 = getAttribute(firstChild7, "year");
                                    String attribute6 = getAttribute(firstChild7, "month");
                                    String attribute7 = getAttribute(firstChild7, "day");
                                    String attribute8 = getAttribute(firstChild7, "hour");
                                    String attribute9 = getAttribute(firstChild7, "minute");
                                    String attribute10 = getAttribute(firstChild7, "second");
                                    StringBuffer stringBuffer = new StringBuffer();
                                    stringBuffer.append(attribute5);
                                    stringBuffer.append(":");
                                    if (attribute6.length() == 1) {
                                        stringBuffer.append("0");
                                    }
                                    stringBuffer.append(attribute6);
                                    stringBuffer.append(":");
                                    if (attribute7.length() == 1) {
                                        stringBuffer.append("0");
                                    }
                                    stringBuffer.append(attribute7);
                                    stringBuffer.append(" ");
                                    if (attribute8.length() == 1) {
                                        stringBuffer.append("0");
                                    }
                                    stringBuffer.append(attribute8);
                                    stringBuffer.append(":");
                                    if (attribute9.length() == 1) {
                                        stringBuffer.append("0");
                                    }
                                    stringBuffer.append(attribute9);
                                    stringBuffer.append(":");
                                    if (attribute10.length() == 1) {
                                        stringBuffer.append("0");
                                    }
                                    stringBuffer.append(attribute10);
                                    this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(306), 2, 1, new String[]{stringBuffer.toString()}));
                                }
                            }
                        } else if (nodeName.equals("Text")) {
                            Node firstChild8 = node2.getFirstChild();
                            String str20 = null;
                            String str21 = null;
                            String str22 = null;
                            while (true) {
                                int i16 = BaselineTIFFTagSet.TAG_DOCUMENT_NAME;
                                if (firstChild8 == null) {
                                    break;
                                }
                                if (firstChild8.getNodeName().equals("TextEntry") && (namedItem = firstChild8.getAttributes().getNamedItem("keyword")) != null) {
                                    String nodeValue = namedItem.getNodeValue();
                                    String attribute11 = getAttribute(firstChild8, "value");
                                    if (!nodeValue.equals("") && !attribute11.equals("")) {
                                        if (!nodeValue.equalsIgnoreCase("DocumentName")) {
                                            if (nodeValue.equalsIgnoreCase(ExifInterface.TAG_IMAGE_DESCRIPTION)) {
                                                str6 = str22;
                                                i3 = 270;
                                            } else if (nodeValue.equalsIgnoreCase(ExifInterface.TAG_MAKE)) {
                                                i16 = BaselineTIFFTagSet.TAG_MAKE;
                                            } else if (nodeValue.equalsIgnoreCase(ExifInterface.TAG_MODEL)) {
                                                i16 = BaselineTIFFTagSet.TAG_MODEL;
                                            } else if (nodeValue.equalsIgnoreCase("PageName")) {
                                                i16 = BaselineTIFFTagSet.TAG_PAGE_NAME;
                                            } else if (nodeValue.equalsIgnoreCase(ExifInterface.TAG_SOFTWARE)) {
                                                i16 = 305;
                                            } else if (nodeValue.equalsIgnoreCase(ExifInterface.TAG_ARTIST)) {
                                                str6 = str22;
                                                i3 = 315;
                                            } else if (nodeValue.equalsIgnoreCase("HostComputer")) {
                                                i16 = 316;
                                            } else if (nodeValue.equalsIgnoreCase("InkNames")) {
                                                i16 = BaselineTIFFTagSet.TAG_INK_NAMES;
                                            } else if (nodeValue.equalsIgnoreCase(ExifInterface.TAG_COPYRIGHT)) {
                                                i16 = BaselineTIFFTagSet.TAG_COPYRIGHT;
                                            } else {
                                                if (nodeValue.equalsIgnoreCase("author")) {
                                                    str6 = str22;
                                                    str20 = attribute11;
                                                } else if (nodeValue.equalsIgnoreCase("description")) {
                                                    str6 = str22;
                                                    str21 = attribute11;
                                                } else {
                                                    str6 = nodeValue.equalsIgnoreCase("title") ? attribute11 : str22;
                                                }
                                                i3 = -1;
                                            }
                                            i4 = -1;
                                            if (i3 != i4) {
                                                this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(i3), 2, 1, new String[]{attribute11}));
                                            }
                                            str22 = str6;
                                        }
                                        i4 = -1;
                                        int i17 = i16;
                                        str6 = str22;
                                        i3 = i17;
                                        if (i3 != i4) {
                                        }
                                        str22 = str6;
                                    }
                                }
                                firstChild8 = firstChild8.getNextSibling();
                            }
                            if (str20 != null && getTIFFField(315) == null) {
                                this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(315), 2, 1, new String[]{str20}));
                            }
                            if (str21 != null && getTIFFField(270) == null) {
                                this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(270), 2, 1, new String[]{str21}));
                            }
                            if (str22 != null && getTIFFField(BaselineTIFFTagSet.TAG_DOCUMENT_NAME) == null) {
                                this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(BaselineTIFFTagSet.TAG_DOCUMENT_NAME), 2, 1, new String[]{str22}));
                            }
                        } else if (nodeName.equals("Transparency")) {
                            for (Node firstChild9 = node2.getFirstChild(); firstChild9 != null; firstChild9 = firstChild9.getNextSibling()) {
                                if (firstChild9.getNodeName().equals("Alpha")) {
                                    String attribute12 = getAttribute(firstChild9, "value");
                                    if (attribute12.equals("premultiplied")) {
                                        tIFFField = new TIFFField(this.rootIFD.getTag(BaselineTIFFTagSet.TAG_EXTRA_SAMPLES), 1);
                                    } else {
                                        tIFFField = attribute12.equals("nonpremultiplied") ? new TIFFField(this.rootIFD.getTag(BaselineTIFFTagSet.TAG_EXTRA_SAMPLES), 2) : null;
                                    }
                                    if (tIFFField != null) {
                                        this.rootIFD.addTIFFField(tIFFField);
                                    }
                                }
                            }
                        }
                    }
                    firstChild = node2.getNextSibling();
                    node4 = node;
                    cls5 = cls2;
                    str13 = str5;
                    z = z2;
                    str15 = str4;
                    str14 = str3;
                    str = str2;
                }
            }
            str5 = str13;
            z2 = z;
            node2 = firstChild;
            cls2 = cls;
            firstChild = node2.getNextSibling();
            node4 = node;
            cls5 = cls2;
            str13 = str5;
            z = z2;
            str15 = str4;
            str14 = str3;
            str = str2;
        }
        String str23 = str;
        String str24 = str14;
        if (str23 == null) {
            return;
        }
        if (str23.equals("SignedIntegral")) {
            i2 = 2;
        } else {
            if (!str23.equals("UnsignedIntegral")) {
                if (str23.equals("Real")) {
                    i = -1;
                    i2 = 3;
                } else if (!str23.equals(str24)) {
                    i = -1;
                    i2 = -1;
                }
                if (i2 == i) {
                    TIFFField tIFFField3 = getTIFFField(BaselineTIFFTagSet.TAG_SAMPLES_PER_PIXEL);
                    if (tIFFField3 != null) {
                        count = tIFFField3.getAsInt(0);
                    } else {
                        TIFFField tIFFField4 = getTIFFField(258);
                        count = tIFFField4 != null ? tIFFField4.getCount() : 1;
                    }
                    char[] cArr4 = new char[count];
                    Arrays.fill(cArr4, (char) i2);
                    this.rootIFD.addTIFFField(new TIFFField(this.rootIFD.getTag(BaselineTIFFTagSet.TAG_SAMPLE_FORMAT), 3, count, cArr4));
                    return;
                }
                return;
            }
            i2 = 1;
        }
        i = -1;
        if (i2 == i) {
        }
    }

    private static String getAttribute(Node node, String str) {
        Node namedItem = node.getAttributes().getNamedItem(str);
        if (namedItem != null) {
            return namedItem.getNodeValue();
        }
        return null;
    }

    private Node getChildNode(Node node, String str) {
        if (node.hasChildNodes()) {
            NodeList childNodes = node.getChildNodes();
            int length = childNodes.getLength();
            for (int i = 0; i < length; i++) {
                Node item = childNodes.item(i);
                if (item.getNodeName().equals(str)) {
                    return item;
                }
            }
        }
        return null;
    }

    public static TIFFIFD parseIFD(Node node) throws IIOInvalidTreeException {
        TIFFField tIFFField;
        TIFFTagSet tIFFTagSet;
        TIFFTag tag;
        if (!node.getNodeName().equals("TIFFIFD")) {
            fatal(node, "Expected \"TIFFIFD\" node");
        }
        String attribute = getAttribute(node, "tagSets");
        ArrayList arrayList = new ArrayList(5);
        if (attribute != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(attribute, ",");
            while (stringTokenizer.hasMoreTokens()) {
                String nextToken = stringTokenizer.nextToken();
                try {
                    Object invoke = Class.forName(nextToken).getMethod("getInstance", null).invoke(null, null);
                    if (invoke instanceof TIFFTagSet) {
                        arrayList.add((TIFFTagSet) invoke);
                    } else {
                        fatal(node, "Specified tag set class \"" + nextToken + "\" is not an instance of TIFFTagSet");
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e2) {
                    throw new RuntimeException(e2);
                } catch (NoSuchMethodException e3) {
                    throw new RuntimeException(e3);
                } catch (InvocationTargetException e4) {
                    throw new RuntimeException(e4);
                }
            }
        }
        TIFFIFD tiffifd = new TIFFIFD(arrayList);
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            String nodeName = firstChild.getNodeName();
            if (nodeName.equals("TIFFIFD")) {
                TIFFIFD parseIFD = parseIFD(firstChild);
                String attribute2 = getAttribute(firstChild, "parentTagName");
                String attribute3 = getAttribute(firstChild, "parentTagNumber");
                if (attribute2 != null) {
                    tag = TIFFIFD.getTag(attribute2, arrayList);
                } else {
                    tag = attribute3 != null ? TIFFIFD.getTag(Integer.valueOf(attribute3).intValue(), arrayList) : null;
                }
                if (tag == null) {
                    tag = new TIFFTag("unknown", 0, 0, null);
                }
                tIFFField = new TIFFField(tag, tag.isDataTypeOK(13) ? 13 : 4, 1, parseIFD);
            } else if (nodeName.equals("TIFFField")) {
                int parseInt = Integer.parseInt(getAttribute(firstChild, "number"));
                Iterator it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        tIFFTagSet = null;
                        break;
                    }
                    tIFFTagSet = (TIFFTagSet) it.next();
                    if (tIFFTagSet.getTag(parseInt) != null) {
                        break;
                    }
                }
                tIFFField = TIFFField.createFromMetadataNode(tIFFTagSet, firstChild);
            } else {
                fatal(firstChild, "Expected either \"TIFFIFD\" or \"TIFFField\" node, got " + nodeName);
                tIFFField = null;
            }
            tiffifd.addTIFFField(tIFFField);
        }
        return tiffifd;
    }

    private void mergeNativeTree(Node node) throws IIOInvalidTreeException {
        if (!node.getNodeName().equals(nativeMetadataFormatName)) {
            fatal(node, "Root must be com_sun_media_imageio_plugins_tiff_image_1.0");
        }
        Node firstChild = node.getFirstChild();
        if (firstChild == null || !firstChild.getNodeName().equals("TIFFIFD")) {
            fatal(node, "Root must have \"TIFFIFD\" child");
        }
        TIFFIFD parseIFD = parseIFD(firstChild);
        List tagSetList = this.rootIFD.getTagSetList();
        for (Object obj : parseIFD.getTagSetList()) {
            if ((obj instanceof TIFFTagSet) && !tagSetList.contains(obj)) {
                this.rootIFD.addTagSet((TIFFTagSet) obj);
            }
        }
        Iterator it = parseIFD.iterator();
        while (it.hasNext()) {
            this.rootIFD.addTIFFField((TIFFField) it.next());
        }
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

    public void reset() {
        this.rootIFD = new TIFFIFD(this.tagSets);
    }

    public TIFFIFD getRootIFD() {
        return this.rootIFD;
    }

    public TIFFField getTIFFField(int i) {
        return this.rootIFD.getTIFFField(i);
    }

    public void removeTIFFField(int i) {
        this.rootIFD.removeTIFFField(i);
    }

    public TIFFImageMetadata getShallowClone() {
        return new TIFFImageMetadata(this.rootIFD.getShallowClone());
    }
}
