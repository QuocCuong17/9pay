package com.github.jaiimageio.impl.plugins.tiff;

import java.nio.ByteOrder;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import org.w3c.dom.Node;

/* loaded from: classes3.dex */
public class TIFFStreamMetadata extends IIOMetadata {
    private static final String bigEndianString = ByteOrder.BIG_ENDIAN.toString();
    private static final String littleEndianString = ByteOrder.LITTLE_ENDIAN.toString();
    static final String nativeMetadataFormatClassName = "com.github.jaiimageio.impl.plugins.tiff.TIFFStreamMetadataFormat";
    static final String nativeMetadataFormatName = "com_sun_media_imageio_plugins_tiff_stream_1.0";
    public ByteOrder byteOrder;

    public boolean isReadOnly() {
        return false;
    }

    public TIFFStreamMetadata() {
        super(false, nativeMetadataFormatName, nativeMetadataFormatClassName, (String[]) null, (String[]) null);
        this.byteOrder = ByteOrder.BIG_ENDIAN;
    }

    private static void fatal(Node node, String str) throws IIOInvalidTreeException {
        throw new IIOInvalidTreeException(str, node);
    }

    public Node getAsTree(String str) {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode(nativeMetadataFormatName);
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("ByteOrder");
        iIOMetadataNode2.setAttribute("value", this.byteOrder.toString());
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        return iIOMetadataNode;
    }

    private void mergeNativeTree(Node node) throws IIOInvalidTreeException {
        if (!node.getNodeName().equals(nativeMetadataFormatName)) {
            fatal(node, "Root must be com_sun_media_imageio_plugins_tiff_stream_1.0");
        }
        Node firstChild = node.getFirstChild();
        if (firstChild == null || !firstChild.getNodeName().equals("ByteOrder")) {
            fatal(firstChild, "Root must have \"ByteOrder\" child");
        }
        String nodeValue = firstChild.getAttributes().getNamedItem("value").getNodeValue();
        if (nodeValue == null) {
            fatal(firstChild, "ByteOrder node must have a \"value\" attribute");
        }
        if (nodeValue.equals(bigEndianString)) {
            this.byteOrder = ByteOrder.BIG_ENDIAN;
        } else if (nodeValue.equals(littleEndianString)) {
            this.byteOrder = ByteOrder.LITTLE_ENDIAN;
        } else {
            fatal(firstChild, "Incorrect value for ByteOrder \"value\" attribute");
        }
    }

    public void mergeTree(String str, Node node) throws IIOInvalidTreeException {
        if (!str.equals(nativeMetadataFormatName)) {
            throw new IllegalArgumentException("Not a recognized format!");
        }
        if (node == null) {
            throw new IllegalArgumentException("root == null!");
        }
        mergeNativeTree(node);
    }

    public void reset() {
        this.byteOrder = ByteOrder.BIG_ENDIAN;
    }
}
