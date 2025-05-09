package com.github.jaiimageio.impl.plugins.wbmp;

import androidx.exifinterface.media.ExifInterface;
import com.github.jaiimageio.impl.common.ImageUtil;
import com.google.common.net.HttpHeaders;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import org.w3c.dom.Node;

/* loaded from: classes3.dex */
public class WBMPMetadata extends IIOMetadata {
    public static final String nativeMetadataFormatName = "com_sun_media_imageio_plugins_wbmp_image_1.0";
    public int height;
    public int wbmpType;
    public int width;

    public boolean isReadOnly() {
        return true;
    }

    public WBMPMetadata() {
        super(true, nativeMetadataFormatName, "com.github.jaiimageio.impl.plugins.wbmp.WBMPMetadataFormat", (String[]) null, (String[]) null);
    }

    public Node getAsTree(String str) {
        if (str.equals(nativeMetadataFormatName)) {
            return getNativeTree();
        }
        if (str.equals("javax_imageio_1.0")) {
            return getStandardTree();
        }
        throw new IllegalArgumentException(I18N.getString("WBMPMetadata0"));
    }

    private Node getNativeTree() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode(nativeMetadataFormatName);
        addChildNode(iIOMetadataNode, "WBMPType", new Integer(this.wbmpType));
        addChildNode(iIOMetadataNode, HttpHeaders.WIDTH, new Integer(this.width));
        addChildNode(iIOMetadataNode, "Height", new Integer(this.height));
        return iIOMetadataNode;
    }

    public void setFromTree(String str, Node node) {
        throw new IllegalStateException(I18N.getString("WBMPMetadata1"));
    }

    public void mergeTree(String str, Node node) {
        throw new IllegalStateException(I18N.getString("WBMPMetadata1"));
    }

    public void reset() {
        throw new IllegalStateException(I18N.getString("WBMPMetadata1"));
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

    protected IIOMetadataNode getStandardChromaNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Chroma");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("ColorSpaceType");
        iIOMetadataNode2.setAttribute("name", "GRAY");
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("NumChannels");
        iIOMetadataNode3.setAttribute("value", "1");
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("BlackIsZero");
        iIOMetadataNode4.setAttribute("value", "TRUE");
        iIOMetadataNode.appendChild(iIOMetadataNode4);
        return iIOMetadataNode;
    }

    protected IIOMetadataNode getStandardDataNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Data");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("SampleFormat");
        iIOMetadataNode2.setAttribute("value", "UnsignedIntegral");
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode(ExifInterface.TAG_BITS_PER_SAMPLE);
        iIOMetadataNode3.setAttribute("value", "1");
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        return iIOMetadataNode;
    }

    protected IIOMetadataNode getStandardDimensionNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Dimension");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("ImageOrientation");
        iIOMetadataNode2.setAttribute("value", PDLayoutAttributeObject.LINE_HEIGHT_NORMAL);
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        return iIOMetadataNode;
    }
}
