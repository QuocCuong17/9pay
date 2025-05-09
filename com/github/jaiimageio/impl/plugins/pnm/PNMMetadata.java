package com.github.jaiimageio.impl.plugins.pnm;

import androidx.exifinterface.media.ExifInterface;
import com.github.jaiimageio.impl.common.ImageUtil;
import com.github.jaiimageio.plugins.pnm.PNMImageWriteParam;
import com.google.common.net.HttpHeaders;
import java.awt.image.SampleModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: classes3.dex */
public class PNMMetadata extends IIOMetadata implements Cloneable {
    static final String nativeMetadataFormatName = "com_sun_media_imageio_plugins_pnm_image_1.0";
    private ArrayList comments;
    private int height;
    private int maxSample;
    private int maxSampleSize;
    private int variant;
    private int width;

    public boolean isReadOnly() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PNMMetadata() {
        super(true, nativeMetadataFormatName, "com.github.jaiimageio.impl.plugins.pnm.PNMMetadataFormat", (String[]) null, (String[]) null);
    }

    public PNMMetadata(IIOMetadata iIOMetadata) throws IIOInvalidTreeException {
        this();
        if (iIOMetadata != null) {
            if (Arrays.asList(iIOMetadata.getMetadataFormatNames()).contains(nativeMetadataFormatName)) {
                setFromTree(nativeMetadataFormatName, iIOMetadata.getAsTree(nativeMetadataFormatName));
            } else if (iIOMetadata.isStandardMetadataFormatSupported()) {
                setFromTree("javax_imageio_1.0", iIOMetadata.getAsTree("javax_imageio_1.0"));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PNMMetadata(ImageTypeSpecifier imageTypeSpecifier, ImageWriteParam imageWriteParam) {
        this();
        initialize(imageTypeSpecifier, imageWriteParam);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initialize(ImageTypeSpecifier imageTypeSpecifier, ImageWriteParam imageWriteParam) {
        ImageTypeSpecifier destinationType;
        if (imageWriteParam != null && (destinationType = imageWriteParam.getDestinationType()) != null) {
            imageTypeSpecifier = destinationType;
        }
        if (imageTypeSpecifier != null) {
            SampleModel sampleModel = imageTypeSpecifier.getSampleModel();
            int[] sampleSize = sampleModel.getSampleSize();
            this.width = sampleModel.getWidth();
            this.height = sampleModel.getHeight();
            for (int i = 0; i < sampleSize.length; i++) {
                if (sampleSize[i] > this.maxSampleSize) {
                    this.maxSampleSize = sampleSize[i];
                }
            }
            this.maxSample = (1 << this.maxSampleSize) - 1;
            boolean raw = imageWriteParam instanceof PNMImageWriteParam ? ((PNMImageWriteParam) imageWriteParam).getRaw() : true;
            if (this.maxSampleSize == 1) {
                this.variant = 49;
            } else if (sampleModel.getNumBands() == 1) {
                this.variant = 50;
            } else if (sampleModel.getNumBands() == 3) {
                this.variant = 51;
            }
            int i2 = this.variant;
            if (i2 > 51 || !raw || this.maxSampleSize > 8) {
                return;
            }
            this.variant = i2 + 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object clone() {
        PNMMetadata pNMMetadata;
        try {
            pNMMetadata = (PNMMetadata) super.clone();
        } catch (CloneNotSupportedException unused) {
            pNMMetadata = null;
        }
        ArrayList arrayList = this.comments;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                pNMMetadata.addComment((String) this.comments.get(i));
            }
        }
        return pNMMetadata;
    }

    public Node getAsTree(String str) {
        if (str == null) {
            throw new IllegalArgumentException(I18N.getString("PNMMetadata0"));
        }
        if (str.equals(nativeMetadataFormatName)) {
            return getNativeTree();
        }
        if (str.equals("javax_imageio_1.0")) {
            return getStandardTree();
        }
        throw new IllegalArgumentException(I18N.getString("PNMMetadata1") + " " + str);
    }

    IIOMetadataNode getNativeTree() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode(nativeMetadataFormatName);
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("FormatName");
        iIOMetadataNode2.setUserObject(getFormatName());
        iIOMetadataNode2.setNodeValue(getFormatName());
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("Variant");
        iIOMetadataNode3.setUserObject(getVariant());
        iIOMetadataNode3.setNodeValue(getVariant());
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode(HttpHeaders.WIDTH);
        Integer num = new Integer(this.width);
        iIOMetadataNode4.setUserObject(num);
        iIOMetadataNode4.setNodeValue(ImageUtil.convertObjectToString(num));
        iIOMetadataNode.appendChild(iIOMetadataNode4);
        IIOMetadataNode iIOMetadataNode5 = new IIOMetadataNode("Height");
        Integer num2 = new Integer(this.height);
        iIOMetadataNode5.setUserObject(num2);
        iIOMetadataNode5.setNodeValue(ImageUtil.convertObjectToString(num2));
        iIOMetadataNode.appendChild(iIOMetadataNode5);
        IIOMetadataNode iIOMetadataNode6 = new IIOMetadataNode("MaximumSample");
        iIOMetadataNode6.setUserObject(new Byte((byte) this.maxSample));
        iIOMetadataNode6.setNodeValue(ImageUtil.convertObjectToString(new Integer(this.maxSample)));
        iIOMetadataNode.appendChild(iIOMetadataNode6);
        if (this.comments != null) {
            for (int i = 0; i < this.comments.size(); i++) {
                IIOMetadataNode iIOMetadataNode7 = new IIOMetadataNode("Comment");
                Object obj = this.comments.get(i);
                iIOMetadataNode7.setUserObject(obj);
                iIOMetadataNode7.setNodeValue(ImageUtil.convertObjectToString(obj));
                iIOMetadataNode.appendChild(iIOMetadataNode7);
            }
        }
        return iIOMetadataNode;
    }

    protected IIOMetadataNode getStandardChromaNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Chroma");
        int i = ((this.variant - 49) % 3) + 1;
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("ColorSpaceType");
        if (i == 3) {
            iIOMetadataNode2.setAttribute("name", "RGB");
        } else {
            iIOMetadataNode2.setAttribute("name", "GRAY");
        }
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("NumChannels");
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(i == 3 ? 3 : 1);
        iIOMetadataNode3.setAttribute("value", sb.toString());
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        if (i != 3) {
            IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("BlackIsZero");
            iIOMetadataNode4.setAttribute("value", "TRUE");
            iIOMetadataNode.appendChild(iIOMetadataNode4);
        }
        return iIOMetadataNode;
    }

    protected IIOMetadataNode getStandardDataNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Data");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("SampleFormat");
        iIOMetadataNode2.setAttribute("value", "UnsignedIntegral");
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        int i = ((this.variant - 49) % 3) + 1;
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode(ExifInterface.TAG_BITS_PER_SAMPLE);
        if (i == 1) {
            iIOMetadataNode3.setAttribute("value", "1");
        } else if (i == 2) {
            iIOMetadataNode3.setAttribute("value", "8");
        } else {
            iIOMetadataNode3.setAttribute("value", "8 8 8");
        }
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("SignificantBitsPerSample");
        if (i == 1 || i == 2) {
            iIOMetadataNode4.setAttribute("value", "" + this.maxSampleSize);
        } else {
            iIOMetadataNode4.setAttribute("value", this.maxSampleSize + " " + this.maxSampleSize + " " + this.maxSampleSize);
        }
        iIOMetadataNode.appendChild(iIOMetadataNode4);
        return iIOMetadataNode;
    }

    protected IIOMetadataNode getStandardDimensionNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Dimension");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("ImageOrientation");
        iIOMetadataNode2.setAttribute("value", PDLayoutAttributeObject.LINE_HEIGHT_NORMAL);
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        return iIOMetadataNode;
    }

    protected IIOMetadataNode getStandardTextNode() {
        if (this.comments == null) {
            return null;
        }
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Text");
        Iterator it = this.comments.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("TextEntry");
            iIOMetadataNode2.setAttribute("keyword", "comment");
            iIOMetadataNode2.setAttribute("value", str);
            iIOMetadataNode.appendChild(iIOMetadataNode2);
        }
        return iIOMetadataNode;
    }

    public void mergeTree(String str, Node node) throws IIOInvalidTreeException {
        if (str == null) {
            throw new IllegalArgumentException(I18N.getString("PNMMetadata0"));
        }
        if (node == null) {
            throw new IllegalArgumentException(I18N.getString("PNMMetadata2"));
        }
        if (str.equals(nativeMetadataFormatName) && node.getNodeName().equals(nativeMetadataFormatName)) {
            mergeNativeTree(node);
            return;
        }
        if (str.equals("javax_imageio_1.0")) {
            mergeStandardTree(node);
            return;
        }
        throw new IllegalArgumentException(I18N.getString("PNMMetadata1") + " " + str);
    }

    public void setFromTree(String str, Node node) throws IIOInvalidTreeException {
        if (str == null) {
            throw new IllegalArgumentException(I18N.getString("PNMMetadata0"));
        }
        if (node == null) {
            throw new IllegalArgumentException(I18N.getString("PNMMetadata2"));
        }
        if (str.equals(nativeMetadataFormatName) && node.getNodeName().equals(nativeMetadataFormatName)) {
            mergeNativeTree(node);
            return;
        }
        if (str.equals("javax_imageio_1.0")) {
            mergeStandardTree(node);
            return;
        }
        throw new IllegalArgumentException(I18N.getString("PNMMetadata2") + " " + str);
    }

    public void reset() {
        this.maxSampleSize = 0;
        this.variant = 0;
        this.height = 0;
        this.width = 0;
        this.maxSample = 0;
        this.comments = null;
    }

    public String getFormatName() {
        int i = ((this.variant - 49) % 3) + 1;
        if (i == 1) {
            return "PBM";
        }
        if (i == 2) {
            return "PGM";
        }
        if (i == 3) {
            return "PPM";
        }
        return null;
    }

    public String getVariant() {
        return this.variant > 51 ? "RAWBITS" : "ASCII";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isRaw() {
        return getVariant().equals("RAWBITS");
    }

    public void setVariant(int i) {
        this.variant = i;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMaxBitDepth() {
        return this.maxSampleSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMaxValue() {
        return this.maxSample;
    }

    public void setMaxBitDepth(int i) {
        this.maxSample = i;
        this.maxSampleSize = 0;
        while (i > 0) {
            i >>>= 1;
            this.maxSampleSize++;
        }
    }

    public synchronized void addComment(String str) {
        if (this.comments == null) {
            this.comments = new ArrayList();
        }
        this.comments.add(str.replaceAll("[\n\r\f]", " "));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Iterator getComments() {
        ArrayList arrayList = this.comments;
        if (arrayList == null) {
            return null;
        }
        return arrayList.iterator();
    }

    private void mergeNativeTree(Node node) throws IIOInvalidTreeException {
        NodeList childNodes = node.getChildNodes();
        String str = null;
        String str2 = null;
        for (int length = childNodes.getLength() - 1; length >= 0; length--) {
            IIOMetadataNode item = childNodes.item(length);
            String nodeName = item.getNodeName();
            if (nodeName.equals("Comment")) {
                addComment((String) item.getUserObject());
            } else if (nodeName.equals(HttpHeaders.WIDTH)) {
                this.width = ((Integer) item.getUserObject()).intValue();
            } else if (nodeName.equals("Height")) {
                this.width = ((Integer) item.getUserObject()).intValue();
            } else if (nodeName.equals("MaximumSample")) {
                setMaxBitDepth(((Integer) item.getUserObject()).intValue());
            } else if (nodeName.equals("FormatName")) {
                str = (String) item.getUserObject();
            } else if (nodeName.equals("Variant")) {
                str2 = (String) item.getUserObject();
            }
        }
        if (str.equals("PBM")) {
            this.variant = 49;
        } else if (str.equals("PGM")) {
            this.variant = 50;
        } else if (str.equals("PPM")) {
            this.variant = 51;
        }
        if (str2.equals("RAWBITS")) {
            this.variant += 3;
        }
    }

    private void mergeStandardTree(Node node) throws IIOInvalidTreeException {
        NodeList childNodes = node.getChildNodes();
        String str = null;
        int[] iArr = null;
        int i = 0;
        for (int i2 = 0; i2 < childNodes.getLength(); i2++) {
            Node item = childNodes.item(i2);
            String nodeName = item.getNodeName();
            if (nodeName.equals("Chroma")) {
                NodeList childNodes2 = item.getChildNodes();
                for (int i3 = 0; i3 < childNodes2.getLength(); i3++) {
                    Node item2 = childNodes2.item(i3);
                    String nodeName2 = item2.getNodeName();
                    if (nodeName2.equals("NumChannels")) {
                        i = new Integer((String) getAttribute(item2, "value")).intValue();
                    } else if (nodeName2.equals("ColorSpaceType")) {
                        str = (String) getAttribute(item2, "name");
                    }
                }
            } else if (nodeName.equals(ExifInterface.TAG_COMPRESSION)) {
                continue;
            } else if (nodeName.equals("Data")) {
                NodeList childNodes3 = item.getChildNodes();
                int i4 = -1;
                for (int i5 = 0; i5 < childNodes3.getLength(); i5++) {
                    Node item3 = childNodes3.item(i5);
                    String nodeName3 = item3.getNodeName();
                    if (nodeName3.equals(ExifInterface.TAG_BITS_PER_SAMPLE)) {
                        ArrayList arrayList = new ArrayList(3);
                        StringTokenizer stringTokenizer = new StringTokenizer((String) getAttribute(item3, "value"));
                        while (stringTokenizer.hasMoreTokens()) {
                            arrayList.add(Integer.valueOf(stringTokenizer.nextToken()));
                        }
                        int size = arrayList.size();
                        int[] iArr2 = new int[size];
                        for (int i6 = 0; i6 < size; i6++) {
                            iArr2[i6] = ((Integer) arrayList.get(i6)).intValue();
                        }
                        iArr = iArr2;
                    } else if (nodeName3.equals("SignificantBitsPerSample")) {
                        StringTokenizer stringTokenizer2 = new StringTokenizer((String) getAttribute(item3, "value"));
                        while (stringTokenizer2.hasMoreTokens()) {
                            i4 = Math.max(Integer.valueOf(stringTokenizer2.nextToken()).intValue(), i4);
                        }
                    }
                }
                if (i4 > 0) {
                    setMaxBitDepth((1 << i4) - 1);
                } else if (iArr != null) {
                    for (int i7 = 0; i7 < iArr.length; i7++) {
                        if (iArr[i7] > i4) {
                            i4 = iArr[i7];
                        }
                    }
                    setMaxBitDepth((1 << i4) - 1);
                }
            } else if (!nodeName.equals("Dimension") && !nodeName.equals(StandardStructureTypes.DOCUMENT)) {
                if (nodeName.equals("Text")) {
                    NodeList childNodes4 = item.getChildNodes();
                    for (int i8 = 0; i8 < childNodes4.getLength(); i8++) {
                        Node item4 = childNodes4.item(i8);
                        if (item4.getNodeName().equals("TextEntry")) {
                            addComment((String) getAttribute(item4, "value"));
                        }
                    }
                } else if (!nodeName.equals("Transparency")) {
                    throw new IIOInvalidTreeException(I18N.getString("PNMMetadata3") + " " + nodeName, item);
                }
            }
        }
        if ((str != null && str.equals("RGB")) || i > 1 || iArr.length > 1) {
            this.variant = 51;
        } else if (this.maxSampleSize > 1) {
            this.variant = 50;
        } else {
            this.variant = 49;
        }
    }

    public Object getAttribute(Node node, String str) {
        Node namedItem = node.getAttributes().getNamedItem(str);
        if (namedItem != null) {
            return namedItem.getNodeValue();
        }
        return null;
    }
}
