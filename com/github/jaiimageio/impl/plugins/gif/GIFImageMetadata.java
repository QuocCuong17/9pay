package com.github.jaiimageio.impl.plugins.gif;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.internal.ServerProtocol;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadataNode;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import org.w3c.dom.Node;

/* loaded from: classes3.dex */
public class GIFImageMetadata extends GIFMetadata {
    static final String[] disposalMethodNames = {"none", "doNotDispose", "restoreToBackgroundColor", "restoreToPrevious", "undefinedDisposalMethod4", "undefinedDisposalMethod5", "undefinedDisposalMethod6", "undefinedDisposalMethod7"};
    static final String nativeMetadataFormatName = "javax_imageio_gif_image_1.0";
    public List applicationData;
    public List applicationIDs;
    public List authenticationCodes;
    public int characterCellHeight;
    public int characterCellWidth;
    public List comments;
    public int delayTime;
    public int disposalMethod;
    public boolean hasPlainTextExtension;
    public int imageHeight;
    public int imageLeftPosition;
    public int imageTopPosition;
    public int imageWidth;
    public boolean interlaceFlag;
    public byte[] localColorTable;
    public boolean sortFlag;
    public byte[] text;
    public int textBackgroundColor;
    public int textForegroundColor;
    public int textGridHeight;
    public int textGridLeft;
    public int textGridTop;
    public int textGridWidth;
    public boolean transparentColorFlag;
    public int transparentColorIndex;
    public boolean userInputFlag;

    public boolean isReadOnly() {
        return true;
    }

    @Override // com.github.jaiimageio.impl.plugins.gif.GIFMetadata
    public /* bridge */ /* synthetic */ void mergeTree(String str, Node node) throws IIOInvalidTreeException {
        super.mergeTree(str, node);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GIFImageMetadata(boolean z, String str, String str2, String[] strArr, String[] strArr2) {
        super(z, str, str2, strArr, strArr2);
        this.interlaceFlag = false;
        this.sortFlag = false;
        this.localColorTable = null;
        this.disposalMethod = 0;
        this.userInputFlag = false;
        this.transparentColorFlag = false;
        this.delayTime = 0;
        this.transparentColorIndex = 0;
        this.hasPlainTextExtension = false;
        this.applicationIDs = null;
        this.authenticationCodes = null;
        this.applicationData = null;
        this.comments = null;
    }

    public GIFImageMetadata() {
        this(true, nativeMetadataFormatName, "com.github.jaiimageio.impl.plugins.gif.GIFImageMetadataFormat", null, null);
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

    private String toISO8859(byte[] bArr) {
        try {
            return new String(bArr, "ISO-8859-1");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    private Node getNativeTree() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode(nativeMetadataFormatName);
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("ImageDescriptor");
        iIOMetadataNode2.setAttribute("imageLeftPosition", Integer.toString(this.imageLeftPosition));
        iIOMetadataNode2.setAttribute("imageTopPosition", Integer.toString(this.imageTopPosition));
        iIOMetadataNode2.setAttribute("imageWidth", Integer.toString(this.imageWidth));
        iIOMetadataNode2.setAttribute("imageHeight", Integer.toString(this.imageHeight));
        boolean z = this.interlaceFlag;
        String str = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
        iIOMetadataNode2.setAttribute("interlaceFlag", z ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        if (this.localColorTable != null) {
            IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("LocalColorTable");
            int length = this.localColorTable.length / 3;
            iIOMetadataNode3.setAttribute("sizeOfLocalColorTable", Integer.toString(length));
            iIOMetadataNode3.setAttribute("sortFlag", this.sortFlag ? "TRUE" : "FALSE");
            for (int i = 0; i < length; i++) {
                IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("ColorTableEntry");
                iIOMetadataNode4.setAttribute(FirebaseAnalytics.Param.INDEX, Integer.toString(i));
                byte[] bArr = this.localColorTable;
                int i2 = i * 3;
                int i3 = bArr[i2] & 255;
                int i4 = bArr[i2 + 1] & 255;
                int i5 = bArr[i2 + 2] & 255;
                iIOMetadataNode4.setAttribute("red", Integer.toString(i3));
                iIOMetadataNode4.setAttribute("green", Integer.toString(i4));
                iIOMetadataNode4.setAttribute("blue", Integer.toString(i5));
                iIOMetadataNode3.appendChild(iIOMetadataNode4);
            }
            iIOMetadataNode.appendChild(iIOMetadataNode3);
        }
        IIOMetadataNode iIOMetadataNode5 = new IIOMetadataNode("GraphicControlExtension");
        iIOMetadataNode5.setAttribute("disposalMethod", disposalMethodNames[this.disposalMethod]);
        iIOMetadataNode5.setAttribute("userInputFlag", this.userInputFlag ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
        if (!this.transparentColorFlag) {
            str = "false";
        }
        iIOMetadataNode5.setAttribute("transparentColorFlag", str);
        iIOMetadataNode5.setAttribute("delayTime", Integer.toString(this.delayTime));
        iIOMetadataNode5.setAttribute("transparentColorIndex", Integer.toString(this.transparentColorIndex));
        iIOMetadataNode.appendChild(iIOMetadataNode5);
        if (this.hasPlainTextExtension) {
            IIOMetadataNode iIOMetadataNode6 = new IIOMetadataNode("PlainTextExtension");
            iIOMetadataNode6.setAttribute("textGridLeft", Integer.toString(this.textGridLeft));
            iIOMetadataNode6.setAttribute("textGridTop", Integer.toString(this.textGridTop));
            iIOMetadataNode6.setAttribute("textGridWidth", Integer.toString(this.textGridWidth));
            iIOMetadataNode6.setAttribute("textGridHeight", Integer.toString(this.textGridHeight));
            iIOMetadataNode6.setAttribute("characterCellWidth", Integer.toString(this.characterCellWidth));
            iIOMetadataNode6.setAttribute("characterCellHeight", Integer.toString(this.characterCellHeight));
            iIOMetadataNode6.setAttribute("textForegroundColor", Integer.toString(this.textForegroundColor));
            iIOMetadataNode6.setAttribute("textBackgroundColor", Integer.toString(this.textBackgroundColor));
            iIOMetadataNode6.setAttribute("text", toISO8859(this.text));
            iIOMetadataNode.appendChild(iIOMetadataNode6);
        }
        List list = this.applicationIDs;
        int size = list == null ? 0 : list.size();
        if (size > 0) {
            IIOMetadataNode iIOMetadataNode7 = new IIOMetadataNode("ApplicationExtensions");
            for (int i6 = 0; i6 < size; i6++) {
                IIOMetadataNode iIOMetadataNode8 = new IIOMetadataNode("ApplicationExtension");
                iIOMetadataNode8.setAttribute("applicationID", toISO8859((byte[]) this.applicationIDs.get(i6)));
                iIOMetadataNode8.setAttribute("authenticationCode", toISO8859((byte[]) this.authenticationCodes.get(i6)));
                iIOMetadataNode8.setUserObject((byte[]) ((byte[]) this.applicationData.get(i6)).clone());
                iIOMetadataNode7.appendChild(iIOMetadataNode8);
            }
            iIOMetadataNode.appendChild(iIOMetadataNode7);
        }
        List list2 = this.comments;
        int size2 = list2 == null ? 0 : list2.size();
        if (size2 > 0) {
            IIOMetadataNode iIOMetadataNode9 = new IIOMetadataNode("CommentExtensions");
            for (int i7 = 0; i7 < size2; i7++) {
                IIOMetadataNode iIOMetadataNode10 = new IIOMetadataNode("CommentExtension");
                iIOMetadataNode10.setAttribute("value", toISO8859((byte[]) this.comments.get(i7)));
                iIOMetadataNode9.appendChild(iIOMetadataNode10);
            }
            iIOMetadataNode.appendChild(iIOMetadataNode9);
        }
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardChromaNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Chroma");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("ColorSpaceType");
        iIOMetadataNode2.setAttribute("name", "RGB");
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("NumChannels");
        iIOMetadataNode3.setAttribute("value", this.transparentColorFlag ? "4" : "3");
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("BlackIsZero");
        iIOMetadataNode4.setAttribute("value", "TRUE");
        iIOMetadataNode.appendChild(iIOMetadataNode4);
        if (this.localColorTable != null) {
            IIOMetadataNode iIOMetadataNode5 = new IIOMetadataNode("Palette");
            int length = this.localColorTable.length / 3;
            for (int i = 0; i < length; i++) {
                IIOMetadataNode iIOMetadataNode6 = new IIOMetadataNode("PaletteEntry");
                iIOMetadataNode6.setAttribute(FirebaseAnalytics.Param.INDEX, Integer.toString(i));
                int i2 = i * 3;
                iIOMetadataNode6.setAttribute("red", Integer.toString(this.localColorTable[i2] & 255));
                iIOMetadataNode6.setAttribute("green", Integer.toString(this.localColorTable[i2 + 1] & 255));
                iIOMetadataNode6.setAttribute("blue", Integer.toString(this.localColorTable[i2 + 2] & 255));
                iIOMetadataNode5.appendChild(iIOMetadataNode6);
            }
            iIOMetadataNode.appendChild(iIOMetadataNode5);
        }
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardCompressionNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode(ExifInterface.TAG_COMPRESSION);
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("CompressionTypeName");
        iIOMetadataNode2.setAttribute("value", "lzw");
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("Lossless");
        iIOMetadataNode3.setAttribute("value", "TRUE");
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("NumProgressiveScans");
        iIOMetadataNode4.setAttribute("value", this.interlaceFlag ? "4" : "1");
        iIOMetadataNode.appendChild(iIOMetadataNode4);
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardDataNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Data");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("SampleFormat");
        iIOMetadataNode2.setAttribute("value", StandardStructureTypes.INDEX);
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardDimensionNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Dimension");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("ImageOrientation");
        iIOMetadataNode2.setAttribute("value", PDLayoutAttributeObject.LINE_HEIGHT_NORMAL);
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("HorizontalPixelOffset");
        iIOMetadataNode3.setAttribute("value", Integer.toString(this.imageLeftPosition));
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("VerticalPixelOffset");
        iIOMetadataNode4.setAttribute("value", Integer.toString(this.imageTopPosition));
        iIOMetadataNode.appendChild(iIOMetadataNode4);
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardTextNode() {
        List list = this.comments;
        if (list == null) {
            return null;
        }
        Iterator it = list.iterator();
        if (!it.hasNext()) {
            return null;
        }
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Text");
        while (it.hasNext()) {
            try {
                String str = new String((byte[]) it.next(), "ISO-8859-1");
                IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("TextEntry");
                iIOMetadataNode2.setAttribute("value", str);
                iIOMetadataNode2.setAttribute("encoding", "ISO-8859-1");
                iIOMetadataNode2.setAttribute("compression", "none");
                iIOMetadataNode.appendChild(iIOMetadataNode2);
            } catch (UnsupportedEncodingException unused) {
                throw new RuntimeException("Encoding ISO-8859-1 unknown!");
            }
        }
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardTransparencyNode() {
        if (!this.transparentColorFlag) {
            return null;
        }
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Transparency");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("TransparentIndex");
        iIOMetadataNode2.setAttribute("value", Integer.toString(this.transparentColorIndex));
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        return iIOMetadataNode;
    }

    public void setFromTree(String str, Node node) throws IIOInvalidTreeException {
        throw new IllegalStateException("Metadata is read-only!");
    }

    @Override // com.github.jaiimageio.impl.plugins.gif.GIFMetadata
    protected void mergeNativeTree(Node node) throws IIOInvalidTreeException {
        throw new IllegalStateException("Metadata is read-only!");
    }

    @Override // com.github.jaiimageio.impl.plugins.gif.GIFMetadata
    protected void mergeStandardTree(Node node) throws IIOInvalidTreeException {
        throw new IllegalStateException("Metadata is read-only!");
    }

    public void reset() {
        throw new IllegalStateException("Metadata is read-only!");
    }
}
