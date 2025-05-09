package com.github.jaiimageio.stream;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.github.jaiimageio.impl.common.ImageUtil;
import java.awt.Dimension;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.ComponentSampleModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.MultiPixelPackedSampleModel;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteOrder;
import java.util.StringTokenizer;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.stream.IIOByteBuffer;
import javax.imageio.stream.ImageInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* loaded from: classes3.dex */
public class RawImageInputStream implements ImageInputStream {
    private static final String[] preDefinedColorSpaces = {"GRAY", "sRGB", "LINEAR_RGB", "PYCC", "CIEXYZ"};
    private static final int[] preDefinedTypes = {1003, 1000, 1004, 1002, 1001};
    private Dimension[] imageDimensions;
    private long[] imageOffsets;
    private ImageInputStream source;
    private ImageTypeSpecifier type;

    private static String getAttribute(Node node, String str) {
        Node namedItem = node.getAttributes().getNamedItem(str);
        if (namedItem != null) {
            return namedItem.getNodeValue();
        }
        return null;
    }

    private static boolean getBoolean(Node node, String str) {
        String attribute = getAttribute(node, str);
        if (attribute == null) {
            return false;
        }
        return new Boolean(attribute).booleanValue();
    }

    private static int getInt(Node node, String str) {
        String attribute = getAttribute(node, str);
        if (attribute == null) {
            return 0;
        }
        return new Integer(attribute).intValue();
    }

    private static byte[] getByteArray(Node node, String str) {
        StringTokenizer stringTokenizer;
        int countTokens;
        String attribute = getAttribute(node, str);
        if (attribute == null || (countTokens = (stringTokenizer = new StringTokenizer(attribute)).countTokens()) == 0) {
            return null;
        }
        byte[] bArr = new byte[countTokens];
        int i = 0;
        while (stringTokenizer.hasMoreElements()) {
            bArr[i] = new Byte(stringTokenizer.nextToken()).byteValue();
            i++;
        }
        return bArr;
    }

    private static int[] getIntArray(Node node, String str) {
        StringTokenizer stringTokenizer;
        int countTokens;
        String attribute = getAttribute(node, str);
        if (attribute == null || (countTokens = (stringTokenizer = new StringTokenizer(attribute)).countTokens()) == 0) {
            return null;
        }
        int[] iArr = new int[countTokens];
        int i = 0;
        while (stringTokenizer.hasMoreElements()) {
            iArr[i] = new Integer(stringTokenizer.nextToken()).intValue();
            i++;
        }
        return iArr;
    }

    private static int getTransparency(String str) {
        if ("BITMASK".equals(str)) {
            return 2;
        }
        if ("OPAQUE".equals(str)) {
            return 1;
        }
        return "TRANSLUCENT".equals(str) ? 3 : 0;
    }

    private static ColorSpace getColorSpace(Node node) throws IOException {
        NodeList childNodes = node.getChildNodes();
        int i = 0;
        for (int i2 = 0; i2 < childNodes.getLength(); i2++) {
            Node item = childNodes.item(i2);
            if ("colorSpace".equals(item.getNodeName())) {
                String nodeValue = item.getNodeValue();
                while (true) {
                    String[] strArr = preDefinedColorSpaces;
                    if (i < strArr.length) {
                        if (strArr[i].equals(nodeValue)) {
                            return ColorSpace.getInstance(preDefinedTypes[i]);
                        }
                        i++;
                    } else {
                        InputStream openStream = new URL(nodeValue).openStream();
                        ICC_ColorSpace iCC_ColorSpace = new ICC_ColorSpace(ICC_Profile.getInstance(openStream));
                        openStream.close();
                        return iCC_ColorSpace;
                    }
                }
            }
        }
        return null;
    }

    public RawImageInputStream(ImageInputStream imageInputStream, ImageTypeSpecifier imageTypeSpecifier, long[] jArr, Dimension[] dimensionArr) {
        if (jArr == null || dimensionArr == null || jArr.length != dimensionArr.length) {
            throw new IllegalArgumentException(I18N.getString("RawImageInputStream0"));
        }
        this.source = imageInputStream;
        this.type = imageTypeSpecifier;
        this.imageOffsets = jArr;
        this.imageDimensions = dimensionArr;
    }

    public RawImageInputStream(ImageInputStream imageInputStream, SampleModel sampleModel, long[] jArr, Dimension[] dimensionArr) {
        if (jArr == null || dimensionArr == null || jArr.length != dimensionArr.length) {
            throw new IllegalArgumentException(I18N.getString("RawImageInputStream0"));
        }
        this.source = imageInputStream;
        ColorModel createColorModel = ImageUtil.createColorModel(sampleModel);
        if (createColorModel == null) {
            throw new IllegalArgumentException(I18N.getString("RawImageInputStream4"));
        }
        this.type = new ImageTypeSpecifier(createColorModel, sampleModel);
        this.imageOffsets = jArr;
        this.imageDimensions = dimensionArr;
    }

    public RawImageInputStream(ImageInputStream imageInputStream, InputSource inputSource) throws SAXException, IOException {
        ComponentSampleModel componentSampleModel;
        ComponentColorModel indexColorModel;
        this.source = imageInputStream;
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        int i = 1;
        newInstance.setValidating(true);
        newInstance.setNamespaceAware(true);
        newInstance.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
        try {
            Document parse = newInstance.newDocumentBuilder().parse(inputSource);
            String nodeValue = parse.getElementsByTagName("byteOrder").item(0).getNodeValue();
            if ("NETWORK".equals(nodeValue)) {
                setByteOrder(ByteOrder.BIG_ENDIAN);
                this.source.setByteOrder(ByteOrder.BIG_ENDIAN);
            } else if ("REVERSE".equals(nodeValue)) {
                setByteOrder(ByteOrder.LITTLE_ENDIAN);
                setByteOrder(ByteOrder.LITTLE_ENDIAN);
            }
            NodeList elementsByTagName = parse.getElementsByTagName(TypedValues.CycleType.S_WAVE_OFFSET);
            int length = elementsByTagName.getLength();
            this.imageOffsets = new long[length];
            for (int i2 = 0; i2 < length; i2++) {
                this.imageOffsets[i2] = new Long(elementsByTagName.item(i2).getNodeValue()).longValue();
            }
            NodeList elementsByTagName2 = parse.getElementsByTagName("width");
            NodeList elementsByTagName3 = parse.getElementsByTagName("height");
            int length2 = elementsByTagName2.getLength();
            if (length2 != elementsByTagName3.getLength()) {
                throw new IllegalArgumentException(I18N.getString("RawImageInputStream2"));
            }
            this.imageDimensions = new Dimension[length2];
            for (int i3 = 0; i3 < length2; i3++) {
                this.imageDimensions[i3] = new Dimension(new Integer(elementsByTagName2.item(i3).getNodeValue()).intValue(), new Integer(elementsByTagName3.item(i3).getNodeValue()).intValue());
            }
            NodeList elementsByTagName4 = parse.getElementsByTagName("ComponentSampleModel");
            ComponentColorModel componentColorModel = null;
            if (elementsByTagName4.getLength() > 0) {
                Node item = elementsByTagName4.item(0);
                int[] intArray = getIntArray(item, "bankIndices");
                if (intArray == null) {
                    componentSampleModel = new ComponentSampleModel(getInt(item, "dataType"), getInt(item, "w"), getInt(item, CmcdData.Factory.STREAMING_FORMAT_HLS), getInt(item, "pixelStride"), getInt(item, "scanlineStride"), getIntArray(item, "bandOffsets"));
                } else {
                    componentSampleModel = new ComponentSampleModel(getInt(item, "dataType"), getInt(item, "w"), getInt(item, CmcdData.Factory.STREAMING_FORMAT_HLS), getInt(item, "pixelStride"), getInt(item, "scanlineStride"), intArray, getIntArray(item, "bandOffsets"));
                }
            } else {
                componentSampleModel = null;
            }
            NodeList elementsByTagName5 = parse.getElementsByTagName("MultiPixelPackedSampleModel");
            if (elementsByTagName5.getLength() > 0) {
                Node item2 = elementsByTagName5.item(0);
                componentSampleModel = new MultiPixelPackedSampleModel(getInt(item2, "dataType"), getInt(item2, "w"), getInt(item2, CmcdData.Factory.STREAMING_FORMAT_HLS), getInt(item2, "numberOfBits"), getInt(item2, "scanlineStride"), getInt(item2, "dataBitOffset"));
            }
            NodeList elementsByTagName6 = parse.getElementsByTagName("SinglePixelPackedSampleModel");
            if (elementsByTagName6.getLength() > 0) {
                Node item3 = elementsByTagName6.item(0);
                componentSampleModel = new SinglePixelPackedSampleModel(getInt(item3, "dataType"), getInt(item3, "w"), getInt(item3, CmcdData.Factory.STREAMING_FORMAT_HLS), getInt(item3, "scanlineStride"), getIntArray(item3, "bitMasks"));
            }
            NodeList elementsByTagName7 = parse.getElementsByTagName("ComponentColorModel");
            if (elementsByTagName7.getLength() > 0) {
                Node item4 = elementsByTagName7.item(0);
                componentColorModel = new ComponentColorModel(getColorSpace(item4), getIntArray(item4, "bits"), getBoolean(item4, "hasAlpha"), getBoolean(item4, "isAlphaPremultiplied"), getTransparency(getAttribute(item4, "transparency")), getInt(item4, "transferType"));
            }
            NodeList elementsByTagName8 = parse.getElementsByTagName("DirectColorModel");
            if (elementsByTagName8.getLength() > 0) {
                Node item5 = elementsByTagName8.item(0);
                componentColorModel = new DirectColorModel(getColorSpace(item5), getInt(item5, "bits"), getInt(item5, "rmask"), getInt(item5, "gmask"), getInt(item5, "bmask"), getInt(item5, "amask"), false, 1);
            }
            NodeList elementsByTagName9 = parse.getElementsByTagName("IndexColorModel");
            if (elementsByTagName9.getLength() > 0) {
                Node item6 = elementsByTagName9.item(0);
                byte[] byteArray = getByteArray(item6, "a");
                if (byteArray == null) {
                    indexColorModel = new IndexColorModel(getInt(item6, "bits"), getInt(item6, "size"), getByteArray(item6, PDPageLabelRange.STYLE_ROMAN_LOWER), getByteArray(item6, "g"), getByteArray(item6, "b"));
                } else {
                    indexColorModel = new IndexColorModel(getInt(item6, "bits"), getInt(item6, "size"), getByteArray(item6, PDPageLabelRange.STYLE_ROMAN_LOWER), getByteArray(item6, "g"), getByteArray(item6, "b"), byteArray);
                }
                componentColorModel = indexColorModel;
            }
            this.type = new ImageTypeSpecifier(componentColorModel, componentSampleModel);
            if (this.imageDimensions.length != 0) {
                return;
            }
            Dimension[] dimensionArr = new Dimension[this.imageOffsets.length];
            this.imageDimensions = dimensionArr;
            dimensionArr[0] = new Dimension(componentSampleModel.getWidth(), componentSampleModel.getHeight());
            while (true) {
                Dimension[] dimensionArr2 = this.imageDimensions;
                if (i >= dimensionArr2.length) {
                    return;
                }
                dimensionArr2[i] = dimensionArr2[0];
                i++;
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(I18N.getString("RawImageInputStream1"), e);
        }
    }

    public ImageTypeSpecifier getImageType() {
        return this.type;
    }

    public long getImageOffset(int i) {
        if (i >= 0) {
            long[] jArr = this.imageOffsets;
            if (i < jArr.length) {
                return jArr[i];
            }
        }
        throw new IllegalArgumentException(I18N.getString("RawImageInputStream3"));
    }

    public Dimension getImageDimension(int i) {
        if (i < 0 || i >= this.imageOffsets.length) {
            throw new IllegalArgumentException(I18N.getString("RawImageInputStream3"));
        }
        return this.imageDimensions[i];
    }

    public int getNumImages() {
        return this.imageOffsets.length;
    }

    public void setByteOrder(ByteOrder byteOrder) {
        this.source.setByteOrder(byteOrder);
    }

    public ByteOrder getByteOrder() {
        return this.source.getByteOrder();
    }

    public int read() throws IOException {
        return this.source.read();
    }

    public int read(byte[] bArr) throws IOException {
        return this.source.read(bArr);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.source.read(bArr, i, i2);
    }

    public void readBytes(IIOByteBuffer iIOByteBuffer, int i) throws IOException {
        this.source.readBytes(iIOByteBuffer, i);
    }

    public boolean readBoolean() throws IOException {
        return this.source.readBoolean();
    }

    public byte readByte() throws IOException {
        return this.source.readByte();
    }

    public int readUnsignedByte() throws IOException {
        return this.source.readUnsignedByte();
    }

    public short readShort() throws IOException {
        return this.source.readShort();
    }

    public int readUnsignedShort() throws IOException {
        return this.source.readUnsignedShort();
    }

    public char readChar() throws IOException {
        return this.source.readChar();
    }

    public int readInt() throws IOException {
        return this.source.readInt();
    }

    public long readUnsignedInt() throws IOException {
        return this.source.readUnsignedInt();
    }

    public long readLong() throws IOException {
        return this.source.readLong();
    }

    public float readFloat() throws IOException {
        return this.source.readFloat();
    }

    public double readDouble() throws IOException {
        return this.source.readDouble();
    }

    public String readLine() throws IOException {
        return this.source.readLine();
    }

    public String readUTF() throws IOException {
        return this.source.readUTF();
    }

    public void readFully(byte[] bArr, int i, int i2) throws IOException {
        this.source.readFully(bArr, i, i2);
    }

    public void readFully(byte[] bArr) throws IOException {
        this.source.readFully(bArr);
    }

    public void readFully(short[] sArr, int i, int i2) throws IOException {
        this.source.readFully(sArr, i, i2);
    }

    public void readFully(char[] cArr, int i, int i2) throws IOException {
        this.source.readFully(cArr, i, i2);
    }

    public void readFully(int[] iArr, int i, int i2) throws IOException {
        this.source.readFully(iArr, i, i2);
    }

    public void readFully(long[] jArr, int i, int i2) throws IOException {
        this.source.readFully(jArr, i, i2);
    }

    public void readFully(float[] fArr, int i, int i2) throws IOException {
        this.source.readFully(fArr, i, i2);
    }

    public void readFully(double[] dArr, int i, int i2) throws IOException {
        this.source.readFully(dArr, i, i2);
    }

    public long getStreamPosition() throws IOException {
        return this.source.getStreamPosition();
    }

    public int getBitOffset() throws IOException {
        return this.source.getBitOffset();
    }

    public void setBitOffset(int i) throws IOException {
        this.source.setBitOffset(i);
    }

    public int readBit() throws IOException {
        return this.source.readBit();
    }

    public long readBits(int i) throws IOException {
        return this.source.readBits(i);
    }

    public long length() throws IOException {
        return this.source.length();
    }

    public int skipBytes(int i) throws IOException {
        return this.source.skipBytes(i);
    }

    public long skipBytes(long j) throws IOException {
        return this.source.skipBytes(j);
    }

    public void seek(long j) throws IOException {
        this.source.seek(j);
    }

    public void mark() {
        this.source.mark();
    }

    public void reset() throws IOException {
        this.source.reset();
    }

    public void flushBefore(long j) throws IOException {
        this.source.flushBefore(j);
    }

    public void flush() throws IOException {
        this.source.flush();
    }

    public long getFlushedPosition() {
        return this.source.getFlushedPosition();
    }

    public boolean isCached() {
        return this.source.isCached();
    }

    public boolean isCachedMemory() {
        return this.source.isCachedMemory();
    }

    public boolean isCachedFile() {
        return this.source.isCachedFile();
    }

    public void close() throws IOException {
        this.source.close();
    }
}
