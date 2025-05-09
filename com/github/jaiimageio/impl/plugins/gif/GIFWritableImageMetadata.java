package com.github.jaiimageio.impl.plugins.gif;

import androidx.exifinterface.media.ExifInterface;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadataNode;
import org.w3c.dom.Node;

/* loaded from: classes3.dex */
class GIFWritableImageMetadata extends GIFImageMetadata {
    static final String NATIVE_FORMAT_NAME = "javax_imageio_gif_image_1.0";

    @Override // com.github.jaiimageio.impl.plugins.gif.GIFImageMetadata
    public boolean isReadOnly() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GIFWritableImageMetadata() {
        super(true, NATIVE_FORMAT_NAME, "com.github.jaiimageio.impl.plugins.gif.GIFImageMetadataFormat", null, null);
    }

    @Override // com.github.jaiimageio.impl.plugins.gif.GIFImageMetadata
    public void reset() {
        this.imageLeftPosition = 0;
        this.imageTopPosition = 0;
        this.imageWidth = 0;
        this.imageHeight = 0;
        this.interlaceFlag = false;
        this.sortFlag = false;
        this.localColorTable = null;
        this.disposalMethod = 0;
        this.userInputFlag = false;
        this.transparentColorFlag = false;
        this.delayTime = 0;
        this.transparentColorIndex = 0;
        this.hasPlainTextExtension = false;
        this.textGridLeft = 0;
        this.textGridTop = 0;
        this.textGridWidth = 0;
        this.textGridHeight = 0;
        this.characterCellWidth = 0;
        this.characterCellHeight = 0;
        this.textForegroundColor = 0;
        this.textBackgroundColor = 0;
        this.text = null;
        this.applicationIDs = null;
        this.authenticationCodes = null;
        this.applicationData = null;
        this.comments = null;
    }

    private byte[] fromISO8859(String str) {
        try {
            return str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException unused) {
            return new String("").getBytes();
        }
    }

    @Override // com.github.jaiimageio.impl.plugins.gif.GIFImageMetadata, com.github.jaiimageio.impl.plugins.gif.GIFMetadata
    protected void mergeNativeTree(Node node) throws IIOInvalidTreeException {
        if (!node.getNodeName().equals(NATIVE_FORMAT_NAME)) {
            fatal(node, "Root must be javax_imageio_gif_image_1.0");
        }
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            String nodeName = firstChild.getNodeName();
            if (nodeName.equals("ImageDescriptor")) {
                this.imageLeftPosition = getIntAttribute(firstChild, "imageLeftPosition", -1, true, true, 0, 65535);
                this.imageTopPosition = getIntAttribute(firstChild, "imageTopPosition", -1, true, true, 0, 65535);
                this.imageWidth = getIntAttribute(firstChild, "imageWidth", -1, true, true, 1, 65535);
                this.imageHeight = getIntAttribute(firstChild, "imageHeight", -1, true, true, 1, 65535);
                this.interlaceFlag = getBooleanAttribute(firstChild, "interlaceFlag", false, true);
            } else if (nodeName.equals("LocalColorTable")) {
                int intAttribute = getIntAttribute(firstChild, "sizeOfLocalColorTable", true, 2, 256);
                if (intAttribute != 2 && intAttribute != 4 && intAttribute != 8 && intAttribute != 16 && intAttribute != 32 && intAttribute != 64 && intAttribute != 128 && intAttribute != 256) {
                    fatal(firstChild, "Bad value for LocalColorTable attribute sizeOfLocalColorTable!");
                }
                this.sortFlag = getBooleanAttribute(firstChild, "sortFlag", false, true);
                this.localColorTable = getColorTable(firstChild, "ColorTableEntry", true, intAttribute);
            } else if (nodeName.equals("GraphicControlExtension")) {
                String stringAttribute = getStringAttribute(firstChild, "disposalMethod", null, true, disposalMethodNames);
                this.disposalMethod = 0;
                while (!stringAttribute.equals(disposalMethodNames[this.disposalMethod])) {
                    this.disposalMethod++;
                }
                this.userInputFlag = getBooleanAttribute(firstChild, "userInputFlag", false, true);
                this.transparentColorFlag = getBooleanAttribute(firstChild, "transparentColorFlag", false, true);
                this.delayTime = getIntAttribute(firstChild, "delayTime", -1, true, true, 0, 65535);
                this.transparentColorIndex = getIntAttribute(firstChild, "transparentColorIndex", -1, true, true, 0, 65535);
            } else if (nodeName.equals("PlainTextExtension")) {
                this.hasPlainTextExtension = true;
                this.textGridLeft = getIntAttribute(firstChild, "textGridLeft", -1, true, true, 0, 65535);
                this.textGridTop = getIntAttribute(firstChild, "textGridTop", -1, true, true, 0, 65535);
                this.textGridWidth = getIntAttribute(firstChild, "textGridWidth", -1, true, true, 1, 65535);
                this.textGridHeight = getIntAttribute(firstChild, "textGridHeight", -1, true, true, 1, 65535);
                this.characterCellWidth = getIntAttribute(firstChild, "characterCellWidth", -1, true, true, 1, 65535);
                this.characterCellHeight = getIntAttribute(firstChild, "characterCellHeight", -1, true, true, 1, 65535);
                this.textForegroundColor = getIntAttribute(firstChild, "textForegroundColor", -1, true, true, 0, 255);
                this.textBackgroundColor = getIntAttribute(firstChild, "textBackgroundColor", -1, true, true, 0, 255);
                this.text = fromISO8859(getStringAttribute(firstChild, "text", "", false, null));
            } else if (nodeName.equals("ApplicationExtensions")) {
                IIOMetadataNode firstChild2 = firstChild.getFirstChild();
                if (!firstChild2.getNodeName().equals("ApplicationExtension")) {
                    fatal(firstChild, "Only a ApplicationExtension may be a child of a ApplicationExtensions!");
                }
                String stringAttribute2 = getStringAttribute(firstChild2, "applicationID", null, true, null);
                String stringAttribute3 = getStringAttribute(firstChild2, "authenticationCode", null, true, null);
                Object userObject = firstChild2.getUserObject();
                if (userObject == null || !(userObject instanceof byte[])) {
                    fatal(firstChild2, "Bad user object in ApplicationExtension!");
                }
                if (this.applicationIDs == null) {
                    this.applicationIDs = new ArrayList();
                    this.authenticationCodes = new ArrayList();
                    this.applicationData = new ArrayList();
                }
                this.applicationIDs.add(fromISO8859(stringAttribute2));
                this.authenticationCodes.add(fromISO8859(stringAttribute3));
                this.applicationData.add(userObject);
            } else if (nodeName.equals("CommentExtensions")) {
                Node firstChild3 = firstChild.getFirstChild();
                if (firstChild3 != null) {
                    while (firstChild3 != null) {
                        if (!firstChild3.getNodeName().equals("CommentExtension")) {
                            fatal(firstChild, "Only a CommentExtension may be a child of a CommentExtensions!");
                        }
                        if (this.comments == null) {
                            this.comments = new ArrayList();
                        }
                        this.comments.add(fromISO8859(getStringAttribute(firstChild3, "value", null, true, null)));
                        firstChild3 = firstChild3.getNextSibling();
                    }
                }
            } else {
                fatal(firstChild, "Unknown child of root node!");
            }
        }
    }

    @Override // com.github.jaiimageio.impl.plugins.gif.GIFImageMetadata, com.github.jaiimageio.impl.plugins.gif.GIFMetadata
    protected void mergeStandardTree(Node node) throws IIOInvalidTreeException {
        if (!node.getNodeName().equals("javax_imageio_1.0")) {
            fatal(node, "Root must be javax_imageio_1.0");
        }
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            String nodeName = firstChild.getNodeName();
            if (nodeName.equals("Chroma")) {
                Node firstChild2 = firstChild.getFirstChild();
                while (true) {
                    if (firstChild2 == null) {
                        break;
                    }
                    if (firstChild2.getNodeName().equals("Palette")) {
                        this.localColorTable = getColorTable(firstChild2, "PaletteEntry", false, -1);
                        break;
                    }
                    firstChild2 = firstChild2.getNextSibling();
                }
            } else if (nodeName.equals(ExifInterface.TAG_COMPRESSION)) {
                Node firstChild3 = firstChild.getFirstChild();
                while (true) {
                    if (firstChild3 == null) {
                        break;
                    }
                    if (firstChild3.getNodeName().equals("NumProgressiveScans")) {
                        if (getIntAttribute(firstChild3, "value", 4, false, true, 1, Integer.MAX_VALUE) > 1) {
                            this.interlaceFlag = true;
                        }
                    } else {
                        firstChild3 = firstChild3.getNextSibling();
                    }
                }
            } else if (nodeName.equals("Dimension")) {
                for (Node firstChild4 = firstChild.getFirstChild(); firstChild4 != null; firstChild4 = firstChild4.getNextSibling()) {
                    String nodeName2 = firstChild4.getNodeName();
                    if (nodeName2.equals("HorizontalPixelOffset")) {
                        this.imageLeftPosition = getIntAttribute(firstChild4, "value", -1, true, true, 0, 65535);
                    } else if (nodeName2.equals("VerticalPixelOffset")) {
                        this.imageTopPosition = getIntAttribute(firstChild4, "value", -1, true, true, 0, 65535);
                    }
                }
            } else if (nodeName.equals("Text")) {
                for (Node firstChild5 = firstChild.getFirstChild(); firstChild5 != null; firstChild5 = firstChild5.getNextSibling()) {
                    if (firstChild5.getNodeName().equals("TextEntry") && getAttribute(firstChild5, "compression", "none", false).equals("none") && Charset.isSupported(getAttribute(firstChild5, "encoding", "ISO-8859-1", false))) {
                        byte[] fromISO8859 = fromISO8859(getAttribute(firstChild5, "value"));
                        if (this.comments == null) {
                            this.comments = new ArrayList();
                        }
                        this.comments.add(fromISO8859);
                    }
                }
            } else if (nodeName.equals("Transparency")) {
                Node firstChild6 = firstChild.getFirstChild();
                while (true) {
                    if (firstChild6 == null) {
                        break;
                    }
                    if (firstChild6.getNodeName().equals("TransparentIndex")) {
                        this.transparentColorIndex = getIntAttribute(firstChild6, "value", -1, true, true, 0, 255);
                        this.transparentColorFlag = true;
                        break;
                    }
                    firstChild6 = firstChild6.getNextSibling();
                }
            }
        }
    }

    @Override // com.github.jaiimageio.impl.plugins.gif.GIFImageMetadata
    public void setFromTree(String str, Node node) throws IIOInvalidTreeException {
        reset();
        mergeTree(str, node);
    }
}
