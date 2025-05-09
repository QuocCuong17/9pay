package com.github.jaiimageio.impl.plugins.bmp;

import androidx.exifinterface.media.ExifInterface;
import com.github.jaiimageio.impl.common.ImageUtil;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.SampleModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import javax.imageio.ImageWriteParam;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import org.w3c.dom.Node;

/* loaded from: classes3.dex */
public class BMPMetadata extends IIOMetadata implements Cloneable, BMPConstants {
    public static final String nativeMetadataFormatName = "com_sun_media_imageio_plugins_bmp_image_1.0";
    public int alphaMask;
    public short bitsPerPixel;
    public int blue;
    public int blueMask;
    public double blueX;
    public double blueY;
    public double blueZ;
    public String bmpVersion;
    public int colorSpace;
    public int colorsImportant;
    public int colorsUsed;
    public List comments;
    public int compression;
    public int gammaBlue;
    public int gammaGreen;
    public int gammaRed;
    public int green;
    public int greenMask;
    public double greenX;
    public double greenY;
    public double greenZ;
    public int height;
    public int imageSize;
    public int intent;
    public byte[] palette;
    public int paletteSize;
    public int red;
    public int redMask;
    public double redX;
    public double redY;
    public double redZ;
    public int width;
    public int xPixelsPerMeter;
    public int yPixelsPerMeter;

    public boolean isReadOnly() {
        return false;
    }

    public BMPMetadata() {
        super(true, nativeMetadataFormatName, "com.github.jaiimageio.impl.bmp.BMPMetadataFormat", (String[]) null, (String[]) null);
        this.palette = null;
        this.comments = null;
    }

    public BMPMetadata(IIOMetadata iIOMetadata) throws IIOInvalidTreeException {
        this();
        if (iIOMetadata != null) {
            if (Arrays.asList(iIOMetadata.getMetadataFormatNames()).contains(nativeMetadataFormatName)) {
                setFromTree(nativeMetadataFormatName, iIOMetadata.getAsTree(nativeMetadataFormatName));
            } else if (iIOMetadata.isStandardMetadataFormatSupported()) {
                setFromTree("javax_imageio_1.0", iIOMetadata.getAsTree("javax_imageio_1.0"));
            }
        }
    }

    public Object clone() {
        try {
            return (BMPMetadata) super.clone();
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
        throw new IllegalArgumentException(I18N.getString("BMPMetadata0"));
    }

    private Node getNativeTree() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode(nativeMetadataFormatName);
        addChildNode(iIOMetadataNode, "BMPVersion", this.bmpVersion);
        addChildNode(iIOMetadataNode, HttpHeaders.WIDTH, new Integer(this.width));
        addChildNode(iIOMetadataNode, "Height", new Integer(this.height));
        addChildNode(iIOMetadataNode, "BitsPerPixel", new Short(this.bitsPerPixel));
        addChildNode(iIOMetadataNode, ExifInterface.TAG_COMPRESSION, new Integer(this.compression));
        addChildNode(iIOMetadataNode, "ImageSize", new Integer(this.imageSize));
        if (this.xPixelsPerMeter > 0 && this.yPixelsPerMeter > 0) {
            IIOMetadataNode addChildNode = addChildNode(iIOMetadataNode, "PixelsPerMeter", null);
            addChildNode(addChildNode, "X", new Integer(this.xPixelsPerMeter));
            addChildNode(addChildNode, "Y", new Integer(this.yPixelsPerMeter));
        }
        addChildNode(iIOMetadataNode, "ColorsUsed", new Integer(this.colorsUsed));
        addChildNode(iIOMetadataNode, "ColorsImportant", new Integer(this.colorsImportant));
        int i = 0;
        for (int i2 = 0; i2 < this.bmpVersion.length(); i2++) {
            if (Character.isDigit(this.bmpVersion.charAt(i2))) {
                i = this.bmpVersion.charAt(i2) - '0';
            }
        }
        if (i >= 4) {
            IIOMetadataNode addChildNode2 = addChildNode(iIOMetadataNode, "Mask", null);
            addChildNode(addChildNode2, "Red", new Integer(this.redMask));
            addChildNode(addChildNode2, "Green", new Integer(this.greenMask));
            addChildNode(addChildNode2, "Blue", new Integer(this.blueMask));
            addChildNode(addChildNode2, "Alpha", new Integer(this.alphaMask));
            addChildNode(iIOMetadataNode, "ColorSpaceType", new Integer(this.colorSpace));
            IIOMetadataNode addChildNode3 = addChildNode(iIOMetadataNode, "CIEXYZEndpoints", null);
            addXYZPoints(addChildNode3, "Red", this.redX, this.redY, this.redZ);
            addXYZPoints(addChildNode3, "Green", this.greenX, this.greenY, this.greenZ);
            addXYZPoints(addChildNode3, "Blue", this.blueX, this.blueY, this.blueZ);
            IIOMetadataNode addChildNode4 = addChildNode(iIOMetadataNode, ExifInterface.TAG_GAMMA, null);
            addChildNode(addChildNode4, "Red", new Integer(this.gammaRed));
            addChildNode(addChildNode4, "Green", new Integer(this.gammaGreen));
            addChildNode(addChildNode4, "Blue", new Integer(this.gammaBlue));
            addChildNode(iIOMetadataNode, "Intent", new Integer(this.intent));
        }
        if (this.palette != null && this.paletteSize > 0) {
            IIOMetadataNode addChildNode5 = addChildNode(iIOMetadataNode, "Palette", null);
            String str = this.bmpVersion;
            boolean z = str != null && str.equals(BMPConstants.VERSION_2);
            int i3 = 0;
            for (int i4 = 0; i4 < this.paletteSize; i4++) {
                IIOMetadataNode addChildNode6 = addChildNode(addChildNode5, "PaletteEntry", null);
                byte[] bArr = this.palette;
                int i5 = i3 + 1;
                this.blue = bArr[i3] & 255;
                int i6 = i5 + 1;
                this.green = bArr[i5] & 255;
                int i7 = i6 + 1;
                int i8 = bArr[i6] & 255;
                this.red = i8;
                addChildNode(addChildNode6, "Red", new Integer(i8));
                addChildNode(addChildNode6, "Green", new Integer(this.green));
                addChildNode(addChildNode6, "Blue", new Integer(this.blue));
                if (!z) {
                    i7++;
                }
                i3 = i7;
            }
        }
        return iIOMetadataNode;
    }

    protected IIOMetadataNode getStandardChromaNode() {
        String str;
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Chroma");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("ColorSpaceType");
        iIOMetadataNode2.setAttribute("name", ((this.palette == null || this.paletteSize <= 0) && this.redMask == 0 && this.greenMask == 0 && this.blueMask == 0 && this.bitsPerPixel <= 8) ? "GRAY" : "RGB");
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("NumChannels");
        if ((this.palette == null || this.paletteSize <= 0) && this.redMask == 0 && this.greenMask == 0 && this.blueMask == 0 && this.bitsPerPixel <= 8) {
            str = "1";
        } else {
            str = this.alphaMask != 0 ? "4" : "3";
        }
        iIOMetadataNode3.setAttribute("value", str);
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        if (this.gammaRed != 0 && this.gammaGreen != 0 && this.gammaBlue != 0) {
            IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode(ExifInterface.TAG_GAMMA);
            iIOMetadataNode4.setAttribute("value", new Double(((this.gammaRed + this.gammaGreen) + this.gammaBlue) / 3.0d).toString());
            iIOMetadataNode.appendChild(iIOMetadataNode4);
        }
        if (str.equals("1") && (this.palette == null || this.paletteSize == 0)) {
            IIOMetadataNode iIOMetadataNode5 = new IIOMetadataNode("BlackIsZero");
            iIOMetadataNode5.setAttribute("value", "TRUE");
            iIOMetadataNode.appendChild(iIOMetadataNode5);
        }
        if (this.palette != null && this.paletteSize > 0) {
            IIOMetadataNode iIOMetadataNode6 = new IIOMetadataNode("Palette");
            String str2 = this.bmpVersion;
            boolean z = str2 != null && str2.equals(BMPConstants.VERSION_2);
            int i = 0;
            for (int i2 = 0; i2 < this.paletteSize; i2++) {
                IIOMetadataNode iIOMetadataNode7 = new IIOMetadataNode("PaletteEntry");
                iIOMetadataNode7.setAttribute(FirebaseAnalytics.Param.INDEX, "" + i2);
                StringBuilder sb = new StringBuilder();
                sb.append("");
                int i3 = i + 1;
                sb.append(this.palette[i] & 255);
                iIOMetadataNode7.setAttribute("blue", sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append("");
                int i4 = i3 + 1;
                sb2.append(this.palette[i3] & 255);
                iIOMetadataNode7.setAttribute("green", sb2.toString());
                StringBuilder sb3 = new StringBuilder();
                sb3.append("");
                int i5 = i4 + 1;
                sb3.append(this.palette[i4] & 255);
                iIOMetadataNode7.setAttribute("red", sb3.toString());
                if (!z) {
                    i5++;
                }
                i = i5;
                iIOMetadataNode6.appendChild(iIOMetadataNode7);
            }
            iIOMetadataNode.appendChild(iIOMetadataNode6);
        }
        return iIOMetadataNode;
    }

    protected IIOMetadataNode getStandardCompressionNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode(ExifInterface.TAG_COMPRESSION);
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("CompressionTypeName");
        iIOMetadataNode2.setAttribute("value", compressionTypeNames[this.compression]);
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("Lossless");
        iIOMetadataNode3.setAttribute("value", this.compression == 4 ? "FALSE" : "TRUE");
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        return iIOMetadataNode;
    }

    protected IIOMetadataNode getStandardDataNode() {
        String str;
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Data");
        String str2 = (this.palette == null || this.paletteSize <= 0) ? "UnsignedIntegral" : StandardStructureTypes.INDEX;
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("SampleFormat");
        iIOMetadataNode2.setAttribute("value", str2);
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        if (this.redMask != 0 || this.greenMask != 0 || this.blueMask != 0) {
            str = countBits(this.redMask) + " " + countBits(this.greenMask) + " " + countBits(this.blueMask);
            if (this.alphaMask != 0) {
                str = str + " " + countBits(this.alphaMask);
            }
        } else {
            if (this.palette == null || this.paletteSize <= 0) {
                short s = this.bitsPerPixel;
                str = s == 1 ? "1" : s == 4 ? "4" : s == 8 ? "8" : s == 16 ? "5 6 5" : s == 24 ? "8 8 8" : s == 32 ? "8 8 8 8" : "";
            } else {
                str = "";
                for (int i = 1; i <= 3; i++) {
                    str = str + ((int) this.bitsPerPixel);
                    if (i != 3) {
                        str = str + " ";
                    }
                }
            }
        }
        if (!str.equals("")) {
            IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode(ExifInterface.TAG_BITS_PER_SAMPLE);
            iIOMetadataNode3.setAttribute("value", str);
            iIOMetadataNode.appendChild(iIOMetadataNode3);
        }
        return iIOMetadataNode;
    }

    protected IIOMetadataNode getStandardDimensionNode() {
        if (this.yPixelsPerMeter <= 0 || this.xPixelsPerMeter <= 0) {
            return null;
        }
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Dimension");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("PixelAspectRatio");
        iIOMetadataNode2.setAttribute("value", "" + (this.yPixelsPerMeter / this.xPixelsPerMeter));
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("HorizontalPixelSize");
        iIOMetadataNode3.setAttribute("value", "" + (1000.0f / this.xPixelsPerMeter));
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("VerticalPixelSize");
        iIOMetadataNode4.setAttribute("value", "" + (1000.0f / this.yPixelsPerMeter));
        iIOMetadataNode.appendChild(iIOMetadataNode4);
        IIOMetadataNode iIOMetadataNode5 = new IIOMetadataNode("HorizontalPhysicalPixelSpacing");
        iIOMetadataNode5.setAttribute("value", "" + (1000.0f / this.xPixelsPerMeter));
        iIOMetadataNode.appendChild(iIOMetadataNode5);
        IIOMetadataNode iIOMetadataNode6 = new IIOMetadataNode("VerticalPhysicalPixelSpacing");
        iIOMetadataNode6.setAttribute("value", "" + (1000.0f / this.yPixelsPerMeter));
        iIOMetadataNode.appendChild(iIOMetadataNode6);
        return iIOMetadataNode;
    }

    protected IIOMetadataNode getStandardDocumentNode() {
        if (this.bmpVersion == null) {
            return null;
        }
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode(StandardStructureTypes.DOCUMENT);
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("FormatVersion");
        iIOMetadataNode2.setAttribute("value", this.bmpVersion);
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        return iIOMetadataNode;
    }

    protected IIOMetadataNode getStandardTextNode() {
        if (this.comments == null) {
            return null;
        }
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Text");
        for (String str : this.comments) {
            IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("TextEntry");
            iIOMetadataNode2.setAttribute("keyword", "comment");
            iIOMetadataNode2.setAttribute("value", str);
            iIOMetadataNode.appendChild(iIOMetadataNode2);
        }
        return iIOMetadataNode;
    }

    protected IIOMetadataNode getStandardTransparencyNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Transparency");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("Alpha");
        iIOMetadataNode2.setAttribute("value", this.alphaMask != 0 ? "nonpremultiplied" : "none");
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        return iIOMetadataNode;
    }

    private void fatal(Node node, String str) throws IIOInvalidTreeException {
        throw new IIOInvalidTreeException(str, node);
    }

    private int getIntAttribute(Node node, String str, int i, boolean z) throws IIOInvalidTreeException {
        String attribute = getAttribute(node, str, null, z);
        return attribute == null ? i : Integer.parseInt(attribute);
    }

    private double getDoubleAttribute(Node node, String str, double d, boolean z) throws IIOInvalidTreeException {
        String attribute = getAttribute(node, str, null, z);
        return attribute == null ? d : Double.parseDouble(attribute);
    }

    private int getIntAttribute(Node node, String str) throws IIOInvalidTreeException {
        return getIntAttribute(node, str, -1, true);
    }

    private double getDoubleAttribute(Node node, String str) throws IIOInvalidTreeException {
        return getDoubleAttribute(node, str, -1.0d, true);
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initialize(ColorModel colorModel, SampleModel sampleModel, ImageWriteParam imageWriteParam) {
        if (imageWriteParam != null) {
            this.bmpVersion = BMPConstants.VERSION_3;
            if (imageWriteParam.getCompressionMode() == 2) {
                this.compression = BMPImageWriter.getCompressionType(imageWriteParam.getCompressionType());
            }
        } else {
            this.bmpVersion = BMPConstants.VERSION_3;
            this.compression = BMPImageWriter.getPreferredCompressionType(colorModel, sampleModel);
        }
        this.width = sampleModel.getWidth();
        this.height = sampleModel.getHeight();
        this.bitsPerPixel = (short) colorModel.getPixelSize();
        if (colorModel instanceof DirectColorModel) {
            DirectColorModel directColorModel = (DirectColorModel) colorModel;
            this.redMask = directColorModel.getRedMask();
            this.greenMask = directColorModel.getGreenMask();
            this.blueMask = directColorModel.getBlueMask();
            this.alphaMask = directColorModel.getAlphaMask();
        }
        if (colorModel instanceof IndexColorModel) {
            IndexColorModel indexColorModel = (IndexColorModel) colorModel;
            int mapSize = indexColorModel.getMapSize();
            this.paletteSize = mapSize;
            byte[] bArr = new byte[mapSize];
            byte[] bArr2 = new byte[mapSize];
            byte[] bArr3 = new byte[mapSize];
            indexColorModel.getReds(bArr);
            indexColorModel.getGreens(bArr2);
            indexColorModel.getBlues(bArr3);
            String str = this.bmpVersion;
            boolean z = str != null && str.equals(BMPConstants.VERSION_2);
            this.palette = new byte[(z ? 3 : 4) * this.paletteSize];
            int i = 0;
            for (int i2 = 0; i2 < this.paletteSize; i2++) {
                byte[] bArr4 = this.palette;
                int i3 = i + 1;
                bArr4[i] = bArr3[i2];
                int i4 = i3 + 1;
                bArr4[i3] = bArr2[i2];
                int i5 = i4 + 1;
                bArr4[i4] = bArr[i2];
                if (!z) {
                    i5++;
                }
                i = i5;
            }
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

    private void mergeNativeTree(Node node) throws IIOInvalidTreeException {
        int i;
        byte[] bArr;
        Integer integerValue;
        Integer integerValue2;
        byte[] bArr2;
        Double doubleValue;
        Double doubleValue2;
        byte[] bArr3;
        Double doubleValue3;
        Integer integerValue3;
        Integer integerValue4;
        if (!node.getNodeName().equals(nativeMetadataFormatName)) {
            fatal(node, "Root must be com_sun_media_imageio_plugins_bmp_image_1.0");
        }
        Node firstChild = node.getFirstChild();
        byte[] bArr4 = null;
        byte[] bArr5 = null;
        byte[] bArr6 = null;
        int i2 = -1;
        while (true) {
            i = 0;
            if (firstChild == null) {
                break;
            }
            String nodeName = firstChild.getNodeName();
            if (nodeName.equals("BMPVersion")) {
                String stringValue = getStringValue(firstChild);
                if (stringValue != null) {
                    this.bmpVersion = stringValue;
                }
            } else if (nodeName.equals(HttpHeaders.WIDTH)) {
                Integer integerValue5 = getIntegerValue(firstChild);
                if (integerValue5 != null) {
                    this.width = integerValue5.intValue();
                }
            } else if (nodeName.equals("Height")) {
                Integer integerValue6 = getIntegerValue(firstChild);
                if (integerValue6 != null) {
                    this.height = integerValue6.intValue();
                }
            } else if (nodeName.equals("BitsPerPixel")) {
                Short shortValue = getShortValue(firstChild);
                if (shortValue != null) {
                    this.bitsPerPixel = shortValue.shortValue();
                }
            } else if (nodeName.equals(ExifInterface.TAG_COMPRESSION)) {
                Integer integerValue7 = getIntegerValue(firstChild);
                if (integerValue7 != null) {
                    this.compression = integerValue7.intValue();
                }
            } else if (nodeName.equals("ImageSize")) {
                Integer integerValue8 = getIntegerValue(firstChild);
                if (integerValue8 != null) {
                    this.imageSize = integerValue8.intValue();
                }
            } else if (nodeName.equals("PixelsPerMeter")) {
                for (Node firstChild2 = firstChild.getFirstChild(); firstChild2 != null; firstChild2 = firstChild2.getNextSibling()) {
                    String nodeName2 = firstChild2.getNodeName();
                    if (nodeName2.equals("X")) {
                        Integer integerValue9 = getIntegerValue(firstChild2);
                        if (integerValue9 != null) {
                            this.xPixelsPerMeter = integerValue9.intValue();
                        }
                    } else if (nodeName2.equals("Y") && (integerValue4 = getIntegerValue(firstChild2)) != null) {
                        this.yPixelsPerMeter = integerValue4.intValue();
                    }
                }
            } else if (nodeName.equals("ColorsUsed")) {
                Integer integerValue10 = getIntegerValue(firstChild);
                if (integerValue10 != null) {
                    this.colorsUsed = integerValue10.intValue();
                }
            } else if (nodeName.equals("ColorsImportant")) {
                Integer integerValue11 = getIntegerValue(firstChild);
                if (integerValue11 != null) {
                    this.colorsImportant = integerValue11.intValue();
                }
            } else if (nodeName.equals("Mask")) {
                for (Node firstChild3 = firstChild.getFirstChild(); firstChild3 != null; firstChild3 = firstChild3.getNextSibling()) {
                    String nodeName3 = firstChild3.getNodeName();
                    if (nodeName3.equals("Red")) {
                        Integer integerValue12 = getIntegerValue(firstChild3);
                        if (integerValue12 != null) {
                            this.redMask = integerValue12.intValue();
                        }
                    } else if (nodeName3.equals("Green")) {
                        Integer integerValue13 = getIntegerValue(firstChild3);
                        if (integerValue13 != null) {
                            this.greenMask = integerValue13.intValue();
                        }
                    } else if (nodeName3.equals("Blue")) {
                        Integer integerValue14 = getIntegerValue(firstChild3);
                        if (integerValue14 != null) {
                            this.blueMask = integerValue14.intValue();
                        }
                    } else if (nodeName3.equals("Alpha") && (integerValue3 = getIntegerValue(firstChild3)) != null) {
                        this.alphaMask = integerValue3.intValue();
                    }
                }
            } else if (nodeName.equals(ExifInterface.TAG_COLOR_SPACE)) {
                Integer integerValue15 = getIntegerValue(firstChild);
                if (integerValue15 != null) {
                    this.colorSpace = integerValue15.intValue();
                }
            } else if (nodeName.equals("CIEXYZEndpoints")) {
                Node firstChild4 = firstChild.getFirstChild();
                while (firstChild4 != null) {
                    String nodeName4 = firstChild4.getNodeName();
                    if (nodeName4.equals("Red")) {
                        Node firstChild5 = firstChild4.getFirstChild();
                        while (firstChild5 != null) {
                            String nodeName5 = firstChild5.getNodeName();
                            if (nodeName5.equals("X")) {
                                Double doubleValue4 = getDoubleValue(firstChild5);
                                bArr3 = bArr5;
                                if (doubleValue4 != null) {
                                    this.redX = doubleValue4.doubleValue();
                                }
                            } else {
                                bArr3 = bArr5;
                                if (nodeName5.equals("Y")) {
                                    Double doubleValue5 = getDoubleValue(firstChild5);
                                    if (doubleValue5 != null) {
                                        this.redY = doubleValue5.doubleValue();
                                    }
                                } else if (nodeName5.equals("Z") && (doubleValue3 = getDoubleValue(firstChild5)) != null) {
                                    this.redZ = doubleValue3.doubleValue();
                                }
                            }
                            firstChild5 = firstChild5.getNextSibling();
                            bArr5 = bArr3;
                        }
                        bArr2 = bArr5;
                    } else {
                        bArr2 = bArr5;
                        if (nodeName4.equals("Green")) {
                            for (Node firstChild6 = firstChild4.getFirstChild(); firstChild6 != null; firstChild6 = firstChild6.getNextSibling()) {
                                String nodeName6 = firstChild6.getNodeName();
                                if (nodeName6.equals("X")) {
                                    Double doubleValue6 = getDoubleValue(firstChild6);
                                    if (doubleValue6 != null) {
                                        this.greenX = doubleValue6.doubleValue();
                                    }
                                } else if (nodeName6.equals("Y")) {
                                    Double doubleValue7 = getDoubleValue(firstChild6);
                                    if (doubleValue7 != null) {
                                        this.greenY = doubleValue7.doubleValue();
                                    }
                                } else if (nodeName6.equals("Z") && (doubleValue2 = getDoubleValue(firstChild6)) != null) {
                                    this.greenZ = doubleValue2.doubleValue();
                                }
                            }
                        } else if (nodeName4.equals("Blue")) {
                            for (Node firstChild7 = firstChild4.getFirstChild(); firstChild7 != null; firstChild7 = firstChild7.getNextSibling()) {
                                String nodeName7 = firstChild7.getNodeName();
                                if (nodeName7.equals("X")) {
                                    Double doubleValue8 = getDoubleValue(firstChild7);
                                    if (doubleValue8 != null) {
                                        this.blueX = doubleValue8.doubleValue();
                                    }
                                } else if (nodeName7.equals("Y")) {
                                    Double doubleValue9 = getDoubleValue(firstChild7);
                                    if (doubleValue9 != null) {
                                        this.blueY = doubleValue9.doubleValue();
                                    }
                                } else if (nodeName7.equals("Z") && (doubleValue = getDoubleValue(firstChild7)) != null) {
                                    this.blueZ = doubleValue.doubleValue();
                                }
                            }
                        }
                    }
                    firstChild4 = firstChild4.getNextSibling();
                    bArr5 = bArr2;
                }
            } else {
                bArr = bArr5;
                if (nodeName.equals(ExifInterface.TAG_GAMMA)) {
                    for (Node firstChild8 = firstChild.getFirstChild(); firstChild8 != null; firstChild8 = firstChild8.getNextSibling()) {
                        String nodeName8 = firstChild8.getNodeName();
                        if (nodeName8.equals("Red")) {
                            Integer integerValue16 = getIntegerValue(firstChild8);
                            if (integerValue16 != null) {
                                this.gammaRed = integerValue16.intValue();
                            }
                        } else if (nodeName8.equals("Green")) {
                            Integer integerValue17 = getIntegerValue(firstChild8);
                            if (integerValue17 != null) {
                                this.gammaGreen = integerValue17.intValue();
                            }
                        } else if (nodeName8.equals("Blue") && (integerValue2 = getIntegerValue(firstChild8)) != null) {
                            this.gammaBlue = integerValue2.intValue();
                        }
                    }
                } else if (nodeName.equals("Intent")) {
                    Integer integerValue18 = getIntegerValue(firstChild);
                    if (integerValue18 != null) {
                        this.intent = integerValue18.intValue();
                    }
                } else if (nodeName.equals("Palette")) {
                    int intAttribute = getIntAttribute(firstChild, "sizeOfPalette");
                    this.paletteSize = intAttribute;
                    byte[] bArr7 = new byte[intAttribute];
                    bArr5 = new byte[intAttribute];
                    byte[] bArr8 = new byte[intAttribute];
                    Node firstChild9 = firstChild.getFirstChild();
                    if (firstChild9 == null) {
                        fatal(firstChild, "Palette has no entries!");
                    }
                    i2 = -1;
                    while (firstChild9 != null) {
                        if (!firstChild9.getNodeName().equals("PaletteEntry")) {
                            fatal(firstChild, "Only a PaletteEntry may be a child of a Palette!");
                        }
                        int i3 = -1;
                        for (Node firstChild10 = firstChild9.getFirstChild(); firstChild10 != null; firstChild10 = firstChild10.getNextSibling()) {
                            String nodeName9 = firstChild10.getNodeName();
                            if (nodeName9.equals(StandardStructureTypes.INDEX)) {
                                Integer integerValue19 = getIntegerValue(firstChild10);
                                if (integerValue19 != null) {
                                    i3 = integerValue19.intValue();
                                }
                                if (i3 < 0 || i3 > this.paletteSize - 1) {
                                    fatal(firstChild, "Bad value for PaletteEntry attribute index!");
                                }
                            } else if (nodeName9.equals("Red")) {
                                Integer integerValue20 = getIntegerValue(firstChild10);
                                if (integerValue20 != null) {
                                    this.red = integerValue20.intValue();
                                }
                            } else if (nodeName9.equals("Green")) {
                                Integer integerValue21 = getIntegerValue(firstChild10);
                                if (integerValue21 != null) {
                                    this.green = integerValue21.intValue();
                                }
                            } else if (nodeName9.equals("Blue") && (integerValue = getIntegerValue(firstChild10)) != null) {
                                this.blue = integerValue.intValue();
                            }
                        }
                        if (i3 == -1) {
                            i3 = i;
                        }
                        if (i3 > i2) {
                            i2 = i3;
                        }
                        bArr7[i3] = (byte) this.red;
                        bArr5[i3] = (byte) this.green;
                        bArr8[i3] = (byte) this.blue;
                        i++;
                        firstChild9 = firstChild9.getNextSibling();
                    }
                    bArr6 = bArr8;
                    bArr4 = bArr7;
                    firstChild = firstChild.getNextSibling();
                } else {
                    if (nodeName.equals("CommentExtensions")) {
                        Node firstChild11 = firstChild.getFirstChild();
                        if (firstChild11 == null) {
                            fatal(firstChild, "CommentExtensions has no entries!");
                        }
                        if (this.comments == null) {
                            this.comments = new ArrayList();
                        }
                        while (firstChild11 != null) {
                            if (!firstChild11.getNodeName().equals("CommentExtension")) {
                                fatal(firstChild, "Only a CommentExtension may be a child of a CommentExtensions!");
                            }
                            this.comments.add(getAttribute(firstChild11, "value"));
                            firstChild11 = firstChild11.getNextSibling();
                        }
                    } else {
                        fatal(firstChild, "Unknown child of root node!");
                    }
                    bArr5 = bArr;
                    firstChild = firstChild.getNextSibling();
                }
                bArr5 = bArr;
                firstChild = firstChild.getNextSibling();
            }
            bArr = bArr5;
            bArr5 = bArr;
            firstChild = firstChild.getNextSibling();
        }
        byte[] bArr9 = bArr5;
        if (bArr4 == null || bArr9 == null || bArr6 == null) {
            return;
        }
        String str = this.bmpVersion;
        boolean z = str != null && str.equals(BMPConstants.VERSION_2);
        int i4 = i2 + 1;
        this.palette = new byte[(z ? 3 : 4) * i4];
        int i5 = 0;
        while (i < i4) {
            byte[] bArr10 = this.palette;
            int i6 = i5 + 1;
            bArr10[i5] = bArr6[i];
            int i7 = i6 + 1;
            bArr10[i6] = bArr9[i];
            int i8 = i7 + 1;
            bArr10[i7] = bArr4[i];
            if (!z) {
                i8++;
            }
            i5 = i8;
            i++;
        }
    }

    private void mergeStandardTree(Node node) throws IIOInvalidTreeException {
        int i;
        int i2;
        int i3;
        double d;
        if (!node.getNodeName().equals("javax_imageio_1.0")) {
            fatal(node, "Root must be javax_imageio_1.0");
        }
        Node firstChild = node.getFirstChild();
        int[] iArr = null;
        String str = null;
        byte[] bArr = null;
        byte[] bArr2 = null;
        byte[] bArr3 = null;
        int i4 = 0;
        boolean z = false;
        int i5 = -1;
        while (true) {
            if (firstChild == null) {
                break;
            }
            String nodeName = firstChild.getNodeName();
            if (nodeName.equals("Chroma")) {
                for (Node firstChild2 = firstChild.getFirstChild(); firstChild2 != null; firstChild2 = firstChild2.getNextSibling()) {
                    String nodeName2 = firstChild2.getNodeName();
                    if (nodeName2.equals("ColorSpaceType")) {
                        str = getAttribute(firstChild2, "name");
                    } else if (nodeName2.equals("NumChannels")) {
                        i4 = getIntAttribute(firstChild2, "value");
                    } else if (nodeName2.equals(ExifInterface.TAG_GAMMA)) {
                        int doubleAttribute = (int) (getDoubleAttribute(firstChild2, "value") + 0.5d);
                        this.gammaBlue = doubleAttribute;
                        this.gammaGreen = doubleAttribute;
                        this.gammaRed = doubleAttribute;
                    } else if (nodeName2.equals("Palette")) {
                        byte[] bArr4 = new byte[256];
                        byte[] bArr5 = new byte[256];
                        byte[] bArr6 = new byte[256];
                        Node firstChild3 = firstChild2.getFirstChild();
                        if (firstChild3 == null) {
                            fatal(firstChild, "Palette has no entries!");
                        }
                        int i6 = -1;
                        while (firstChild3 != null) {
                            if (!firstChild3.getNodeName().equals("PaletteEntry")) {
                                fatal(firstChild, "Only a PaletteEntry may be a child of a Palette!");
                            }
                            int intAttribute = getIntAttribute(firstChild3, FirebaseAnalytics.Param.INDEX);
                            if (intAttribute < 0 || intAttribute > 255) {
                                fatal(firstChild, "Bad value for PaletteEntry attribute index!");
                            }
                            if (intAttribute > i6) {
                                i6 = intAttribute;
                            }
                            bArr4[intAttribute] = (byte) getIntAttribute(firstChild3, "red");
                            bArr5[intAttribute] = (byte) getIntAttribute(firstChild3, "green");
                            bArr6[intAttribute] = (byte) getIntAttribute(firstChild3, "blue");
                            firstChild3 = firstChild3.getNextSibling();
                        }
                        i5 = i6;
                        bArr3 = bArr6;
                        bArr = bArr4;
                        bArr2 = bArr5;
                    }
                }
            } else if (nodeName.equals(ExifInterface.TAG_COMPRESSION)) {
                for (Node firstChild4 = firstChild.getFirstChild(); firstChild4 != null; firstChild4 = firstChild4.getNextSibling()) {
                    if (firstChild4.getNodeName().equals("CompressionTypeName")) {
                        this.compression = BMPImageWriter.getCompressionType(getAttribute(firstChild4, "value"));
                    }
                }
            } else if (nodeName.equals("Data")) {
                Node firstChild5 = firstChild.getFirstChild();
                while (true) {
                    if (firstChild5 == null) {
                        break;
                    }
                    if (firstChild5.getNodeName().equals(ExifInterface.TAG_BITS_PER_SAMPLE)) {
                        ArrayList arrayList = new ArrayList(4);
                        StringTokenizer stringTokenizer = new StringTokenizer(getAttribute(firstChild5, "value"));
                        while (stringTokenizer.hasMoreTokens()) {
                            arrayList.add(Integer.valueOf(stringTokenizer.nextToken()));
                        }
                        int size = arrayList.size();
                        int[] iArr2 = new int[size];
                        for (int i7 = 0; i7 < size; i7++) {
                            iArr2[i7] = ((Integer) arrayList.get(i7)).intValue();
                        }
                        iArr = iArr2;
                    } else {
                        firstChild5 = firstChild5.getNextSibling();
                    }
                }
            } else if (nodeName.equals("Dimension")) {
                double d2 = -1.0d;
                double d3 = -1.0d;
                double d4 = -1.0d;
                double d5 = -1.0d;
                double d6 = -1.0d;
                boolean z2 = false;
                boolean z3 = false;
                boolean z4 = false;
                boolean z5 = false;
                boolean z6 = false;
                for (Node firstChild6 = firstChild.getFirstChild(); firstChild6 != null; firstChild6 = firstChild6.getNextSibling()) {
                    String nodeName3 = firstChild6.getNodeName();
                    double d7 = d2;
                    if (nodeName3.equals("PixelAspectRatio")) {
                        d4 = getDoubleAttribute(firstChild6, "value");
                        d2 = d7;
                        z6 = true;
                    } else if (nodeName3.equals("HorizontalPixelSize")) {
                        d2 = getDoubleAttribute(firstChild6, "value");
                        z2 = true;
                    } else if (nodeName3.equals("VerticalPixelSize")) {
                        d3 = getDoubleAttribute(firstChild6, "value");
                        d2 = d7;
                        z3 = true;
                    } else if (nodeName3.equals("HorizontalPhysicalPixelSpacing")) {
                        d5 = getDoubleAttribute(firstChild6, "value");
                        d2 = d7;
                        z4 = true;
                    } else if (nodeName3.equals("VerticalPhysicalPixelSpacing")) {
                        d6 = getDoubleAttribute(firstChild6, "value");
                        d2 = d7;
                        z5 = true;
                    } else {
                        d2 = d7;
                    }
                }
                double d8 = d2;
                if (z2 || z3 || !(z4 || z5)) {
                    d = d8;
                } else {
                    d = d5;
                    d3 = d6;
                    z2 = z4;
                    z3 = z5;
                }
                if (z2 && z3) {
                    this.xPixelsPerMeter = (int) ((1000.0d / d) + 0.5d);
                    this.yPixelsPerMeter = (int) ((1000.0d / d3) + 0.5d);
                } else if (z6 && d4 != 0.0d) {
                    if (z2) {
                        double d9 = 1000.0d / d;
                        this.xPixelsPerMeter = (int) (d9 + 0.5d);
                        this.yPixelsPerMeter = (int) ((d4 * d9) + 0.5d);
                    } else if (z3) {
                        double d10 = 1000.0d / d3;
                        this.xPixelsPerMeter = (int) ((d10 / d4) + 0.5d);
                        this.yPixelsPerMeter = (int) (d10 + 0.5d);
                    }
                }
            } else if (nodeName.equals(StandardStructureTypes.DOCUMENT)) {
                Node firstChild7 = firstChild.getFirstChild();
                while (true) {
                    if (firstChild7 == null) {
                        break;
                    }
                    if (firstChild7.getNodeName().equals("FormatVersion")) {
                        this.bmpVersion = getAttribute(firstChild7, "value");
                        break;
                    }
                    firstChild7 = firstChild7.getNextSibling();
                }
            } else if (nodeName.equals("Text")) {
                for (Node firstChild8 = firstChild.getFirstChild(); firstChild8 != null; firstChild8 = firstChild8.getNextSibling()) {
                    if (firstChild8.getNodeName().equals("TextEntry")) {
                        if (this.comments == null) {
                            this.comments = new ArrayList();
                        }
                        this.comments.add(getAttribute(firstChild8, "value"));
                    }
                }
            } else if (nodeName.equals("Transparency")) {
                Node firstChild9 = firstChild.getFirstChild();
                while (true) {
                    if (firstChild9 == null) {
                        break;
                    }
                    if (firstChild9.getNodeName().equals("Alpha")) {
                        z = !getAttribute(firstChild9, "value").equals("none");
                        break;
                    }
                    firstChild9 = firstChild9.getNextSibling();
                }
            }
            firstChild = firstChild.getNextSibling();
        }
        if (iArr != null) {
            if (this.palette != null && this.paletteSize > 0) {
                i = 0;
                this.bitsPerPixel = (short) iArr[0];
            } else {
                i = 0;
                this.bitsPerPixel = (short) 0;
                for (int i8 : iArr) {
                    this.bitsPerPixel = (short) (this.bitsPerPixel + i8);
                }
            }
        } else {
            i = 0;
            if (this.palette != null) {
                this.bitsPerPixel = (short) 8;
            } else if (i4 == 1) {
                this.bitsPerPixel = (short) 8;
            } else if (i4 == 3) {
                this.bitsPerPixel = (short) 24;
            } else if (i4 == 4) {
                this.bitsPerPixel = (short) 32;
            } else if (str.equals("GRAY")) {
                this.bitsPerPixel = (short) 8;
            } else if (str.equals("RGB")) {
                this.bitsPerPixel = (short) (z ? 32 : 24);
            }
        }
        if ((iArr != null && iArr.length == 4) || this.bitsPerPixel >= 24) {
            this.redMask = 16711680;
            this.greenMask = 65280;
            this.blueMask = 255;
        }
        if ((iArr != null && iArr.length == 4) || this.bitsPerPixel > 24) {
            this.alphaMask = -16777216;
        }
        if (bArr == null || bArr2 == null || bArr3 == null) {
            return;
        }
        String str2 = this.bmpVersion;
        if (str2 == null || !str2.equals(BMPConstants.VERSION_2)) {
            i2 = i;
            i3 = 1;
        } else {
            i3 = 1;
            i2 = 1;
        }
        int i9 = i5 + i3;
        this.paletteSize = i9;
        this.palette = new byte[(i2 != 0 ? 3 : 4) * i9];
        int i10 = i;
        while (i < this.paletteSize) {
            byte[] bArr7 = this.palette;
            int i11 = i10 + 1;
            bArr7[i10] = bArr3[i];
            int i12 = i11 + 1;
            bArr7[i11] = bArr2[i];
            int i13 = i12 + 1;
            bArr7[i12] = bArr[i];
            if (i2 == 0) {
                i13++;
            }
            i10 = i13;
            i++;
        }
    }

    public void reset() {
        this.bmpVersion = null;
        this.width = 0;
        this.height = 0;
        this.bitsPerPixel = (short) 0;
        this.compression = 0;
        this.imageSize = 0;
        this.xPixelsPerMeter = 0;
        this.yPixelsPerMeter = 0;
        this.colorsUsed = 0;
        this.colorsImportant = 0;
        this.redMask = 0;
        this.greenMask = 0;
        this.blueMask = 0;
        this.alphaMask = 0;
        this.colorSpace = 0;
        this.redX = 0.0d;
        this.redY = 0.0d;
        this.redZ = 0.0d;
        this.greenX = 0.0d;
        this.greenY = 0.0d;
        this.greenZ = 0.0d;
        this.blueX = 0.0d;
        this.blueY = 0.0d;
        this.blueZ = 0.0d;
        this.gammaRed = 0;
        this.gammaGreen = 0;
        this.gammaBlue = 0;
        this.intent = 0;
        this.palette = null;
        this.paletteSize = 0;
        this.red = 0;
        this.green = 0;
        this.blue = 0;
        this.comments = null;
    }

    private String countBits(int i) {
        int i2 = 0;
        while (i != 0) {
            if ((i & 1) == 1) {
                i2++;
            }
            i >>>= 1;
        }
        if (i2 == 0) {
            return "0";
        }
        return "" + i2;
    }

    private void addXYZPoints(IIOMetadataNode iIOMetadataNode, String str, double d, double d2, double d3) {
        IIOMetadataNode addChildNode = addChildNode(iIOMetadataNode, str, null);
        addChildNode(addChildNode, "X", new Double(d));
        addChildNode(addChildNode, "Y", new Double(d2));
        addChildNode(addChildNode, "Z", new Double(d3));
    }

    private IIOMetadataNode addChildNode(IIOMetadataNode iIOMetadataNode, String str, Object obj) {
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode(str);
        if (obj != null) {
            iIOMetadataNode2.setUserObject(obj);
            iIOMetadataNode2.setNodeValue(ImageUtil.convertObjectToString(obj));
        }
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        return iIOMetadataNode2;
    }

    private Object getObjectValue(Node node) {
        String nodeValue = node.getNodeValue();
        return (nodeValue == null && (node instanceof IIOMetadataNode)) ? ((IIOMetadataNode) node).getUserObject() : nodeValue;
    }

    private String getStringValue(Node node) {
        Object objectValue = getObjectValue(node);
        if (objectValue instanceof String) {
            return (String) objectValue;
        }
        return null;
    }

    private Byte getByteValue(Node node) {
        Object objectValue = getObjectValue(node);
        if (objectValue instanceof String) {
            return Byte.valueOf((String) objectValue);
        }
        if (objectValue instanceof Byte) {
            return (Byte) objectValue;
        }
        return null;
    }

    private Short getShortValue(Node node) {
        Object objectValue = getObjectValue(node);
        if (objectValue instanceof String) {
            return Short.valueOf((String) objectValue);
        }
        if (objectValue instanceof Short) {
            return (Short) objectValue;
        }
        return null;
    }

    private Integer getIntegerValue(Node node) {
        Object objectValue = getObjectValue(node);
        if (objectValue instanceof String) {
            return Integer.valueOf((String) objectValue);
        }
        if (objectValue instanceof Integer) {
            return (Integer) objectValue;
        }
        if (objectValue instanceof Byte) {
            return new Integer(((Byte) objectValue).byteValue() & 255);
        }
        return null;
    }

    private Double getDoubleValue(Node node) {
        Object objectValue = getObjectValue(node);
        if (objectValue instanceof String) {
            return Double.valueOf((String) objectValue);
        }
        if (objectValue instanceof Double) {
            return (Double) objectValue;
        }
        return null;
    }
}
