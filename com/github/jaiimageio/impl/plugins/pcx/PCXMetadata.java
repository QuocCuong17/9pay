package com.github.jaiimageio.impl.plugins.pcx;

import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import org.w3c.dom.Node;

/* loaded from: classes3.dex */
public class PCXMetadata extends IIOMetadata implements Cloneable, PCXConstants {
    byte bitsPerPixel;
    boolean gotxmin;
    boolean gotymin;
    int hdpi;
    int hsize;
    int vdpi;
    short version;
    int vsize;
    short xmin;
    short ymin;

    public boolean isReadOnly() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PCXMetadata() {
        super(true, (String) null, (String) null, (String[]) null, (String[]) null);
        reset();
    }

    public Node getAsTree(String str) {
        if (str.equals("javax_imageio_1.0")) {
            return getStandardTree();
        }
        throw new IllegalArgumentException("Not a recognized format!");
    }

    public void mergeTree(String str, Node node) throws IIOInvalidTreeException {
        if (!str.equals("javax_imageio_1.0")) {
            throw new IllegalArgumentException("Not a recognized format!");
        }
        if (node == null) {
            throw new IllegalArgumentException("root == null!");
        }
        mergeStandardTree(node);
    }

    public void reset() {
        this.version = (short) 5;
        this.bitsPerPixel = (byte) 0;
        this.gotxmin = false;
        this.gotymin = false;
        this.xmin = (short) 0;
        this.ymin = (short) 0;
        this.vdpi = 72;
        this.hdpi = 72;
        this.hsize = 0;
        this.vsize = 0;
    }

    public IIOMetadataNode getStandardDocumentNode() {
        short s = this.version;
        String str = s != 0 ? s != 2 ? s != 3 ? s != 4 ? s != 5 ? null : "3.0" : "PC Paintbrush for Windows" : "2.8 without palette" : "2.8 with palette" : "2.5";
        if (str == null) {
            return null;
        }
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode(StandardStructureTypes.DOCUMENT);
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("FormatVersion");
        iIOMetadataNode2.setAttribute("value", str);
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardDimensionNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Dimension");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("HorizontalPixelOffset");
        iIOMetadataNode2.setAttribute("value", String.valueOf((int) this.xmin));
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("VerticalPixelOffset");
        iIOMetadataNode3.setAttribute("value", String.valueOf((int) this.ymin));
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("HorizontalPixelSize");
        iIOMetadataNode4.setAttribute("value", String.valueOf(254.0d / this.hdpi));
        iIOMetadataNode.appendChild(iIOMetadataNode4);
        IIOMetadataNode iIOMetadataNode5 = new IIOMetadataNode("VerticalPixelSize");
        iIOMetadataNode5.setAttribute("value", String.valueOf(254.0d / this.vdpi));
        iIOMetadataNode.appendChild(iIOMetadataNode5);
        if (this.hsize != 0) {
            IIOMetadataNode iIOMetadataNode6 = new IIOMetadataNode("HorizontalScreenSize");
            iIOMetadataNode6.setAttribute("value", String.valueOf(this.hsize));
            iIOMetadataNode.appendChild(iIOMetadataNode6);
        }
        if (this.vsize != 0) {
            IIOMetadataNode iIOMetadataNode7 = new IIOMetadataNode("VerticalScreenSize");
            iIOMetadataNode7.setAttribute("value", String.valueOf(this.vsize));
            iIOMetadataNode.appendChild(iIOMetadataNode7);
        }
        return iIOMetadataNode;
    }

    private void mergeStandardTree(Node node) throws IIOInvalidTreeException {
        if (!node.getNodeName().equals("javax_imageio_1.0")) {
            throw new IIOInvalidTreeException("Root must be javax_imageio_1.0", node);
        }
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            if (firstChild.getNodeName().equals("Dimension")) {
                for (Node firstChild2 = firstChild.getFirstChild(); firstChild2 != null; firstChild2 = firstChild2.getNextSibling()) {
                    String nodeName = firstChild2.getNodeName();
                    if (nodeName.equals("HorizontalPixelOffset")) {
                        this.xmin = Short.valueOf(getAttribute(firstChild2, "value")).shortValue();
                        this.gotxmin = true;
                    } else if (nodeName.equals("VerticalPixelOffset")) {
                        this.ymin = Short.valueOf(getAttribute(firstChild2, "value")).shortValue();
                        this.gotymin = true;
                    } else if (nodeName.equals("HorizontalPixelSize")) {
                        this.hdpi = (int) ((254.0f / Float.parseFloat(getAttribute(firstChild2, "value"))) + 0.5f);
                    } else if (nodeName.equals("VerticalPixelSize")) {
                        this.vdpi = (int) ((254.0f / Float.parseFloat(getAttribute(firstChild2, "value"))) + 0.5f);
                    } else if (nodeName.equals("HorizontalScreenSize")) {
                        this.hsize = Integer.valueOf(getAttribute(firstChild2, "value")).intValue();
                    } else if (nodeName.equals("VerticalScreenSize")) {
                        this.vsize = Integer.valueOf(getAttribute(firstChild2, "value")).intValue();
                    }
                }
            }
        }
    }

    private static String getAttribute(Node node, String str) {
        Node namedItem = node.getAttributes().getNamedItem(str);
        if (namedItem != null) {
            return namedItem.getNodeValue();
        }
        return null;
    }
}
