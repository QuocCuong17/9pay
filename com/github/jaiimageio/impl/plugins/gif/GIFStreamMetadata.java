package com.github.jaiimageio.impl.plugins.gif;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.internal.ServerProtocol;
import com.google.firebase.analytics.FirebaseAnalytics;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadataNode;
import org.apache.fontbox.afm.AFMParser;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import org.w3c.dom.Node;

/* loaded from: classes3.dex */
public class GIFStreamMetadata extends GIFMetadata {
    static final String nativeMetadataFormatName = "javax_imageio_gif_stream_1.0";
    public int backgroundColorIndex;
    public int colorResolution;
    public byte[] globalColorTable;
    public int logicalScreenHeight;
    public int logicalScreenWidth;
    public int pixelAspectRatio;
    public boolean sortFlag;
    public String version;
    public static final String[] versionStrings = {"87a", "89a"};
    public static final String[] colorTableSizes = {"2", "4", "8", "16", "32", "64", "128", "256"};

    public IIOMetadataNode getStandardTextNode() {
        return null;
    }

    public IIOMetadataNode getStandardTransparencyNode() {
        return null;
    }

    public boolean isReadOnly() {
        return true;
    }

    @Override // com.github.jaiimageio.impl.plugins.gif.GIFMetadata
    public /* bridge */ /* synthetic */ void mergeTree(String str, Node node) throws IIOInvalidTreeException {
        super.mergeTree(str, node);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GIFStreamMetadata(boolean z, String str, String str2, String[] strArr, String[] strArr2) {
        super(z, str, str2, strArr, strArr2);
        this.globalColorTable = null;
    }

    public GIFStreamMetadata() {
        this(true, nativeMetadataFormatName, "com.github.jaiimageio.impl.plugins.gif.GIFStreamMetadataFormat", null, null);
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
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode(AFMParser.VERSION);
        iIOMetadataNode2.setAttribute("value", this.version);
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("LogicalScreenDescriptor");
        int i = this.logicalScreenWidth;
        iIOMetadataNode3.setAttribute("logicalScreenWidth", i == -1 ? "" : Integer.toString(i));
        int i2 = this.logicalScreenHeight;
        iIOMetadataNode3.setAttribute("logicalScreenHeight", i2 == -1 ? "" : Integer.toString(i2));
        int i3 = this.colorResolution;
        iIOMetadataNode3.setAttribute("colorResolution", i3 != -1 ? Integer.toString(i3) : "");
        iIOMetadataNode3.setAttribute("pixelAspectRatio", Integer.toString(this.pixelAspectRatio));
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        if (this.globalColorTable != null) {
            IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("GlobalColorTable");
            int length = this.globalColorTable.length / 3;
            iIOMetadataNode4.setAttribute("sizeOfGlobalColorTable", Integer.toString(length));
            iIOMetadataNode4.setAttribute("backgroundColorIndex", Integer.toString(this.backgroundColorIndex));
            iIOMetadataNode4.setAttribute("sortFlag", this.sortFlag ? "TRUE" : "FALSE");
            for (int i4 = 0; i4 < length; i4++) {
                IIOMetadataNode iIOMetadataNode5 = new IIOMetadataNode("ColorTableEntry");
                iIOMetadataNode5.setAttribute(FirebaseAnalytics.Param.INDEX, Integer.toString(i4));
                byte[] bArr = this.globalColorTable;
                int i5 = i4 * 3;
                int i6 = bArr[i5] & 255;
                int i7 = bArr[i5 + 1] & 255;
                int i8 = bArr[i5 + 2] & 255;
                iIOMetadataNode5.setAttribute("red", Integer.toString(i6));
                iIOMetadataNode5.setAttribute("green", Integer.toString(i7));
                iIOMetadataNode5.setAttribute("blue", Integer.toString(i8));
                iIOMetadataNode4.appendChild(iIOMetadataNode5);
            }
            iIOMetadataNode.appendChild(iIOMetadataNode4);
        }
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardChromaNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Chroma");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("ColorSpaceType");
        iIOMetadataNode2.setAttribute("name", "RGB");
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("BlackIsZero");
        iIOMetadataNode3.setAttribute("value", "TRUE");
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        if (this.globalColorTable != null) {
            IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("Palette");
            int length = this.globalColorTable.length / 3;
            for (int i = 0; i < length; i++) {
                IIOMetadataNode iIOMetadataNode5 = new IIOMetadataNode("PaletteEntry");
                iIOMetadataNode5.setAttribute(FirebaseAnalytics.Param.INDEX, Integer.toString(i));
                int i2 = i * 3;
                iIOMetadataNode5.setAttribute("red", Integer.toString(this.globalColorTable[i2] & 255));
                iIOMetadataNode5.setAttribute("green", Integer.toString(this.globalColorTable[i2 + 1] & 255));
                iIOMetadataNode5.setAttribute("blue", Integer.toString(this.globalColorTable[i2 + 2] & 255));
                iIOMetadataNode4.appendChild(iIOMetadataNode5);
            }
            iIOMetadataNode.appendChild(iIOMetadataNode4);
            IIOMetadataNode iIOMetadataNode6 = new IIOMetadataNode("BackgroundIndex");
            iIOMetadataNode6.setAttribute("value", Integer.toString(this.backgroundColorIndex));
            iIOMetadataNode.appendChild(iIOMetadataNode6);
        }
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardCompressionNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode(ExifInterface.TAG_COMPRESSION);
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("CompressionTypeName");
        iIOMetadataNode2.setAttribute("value", "lzw");
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("Lossless");
        iIOMetadataNode3.setAttribute("value", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardDataNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Data");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("SampleFormat");
        iIOMetadataNode2.setAttribute("value", StandardStructureTypes.INDEX);
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode(ExifInterface.TAG_BITS_PER_SAMPLE);
        int i = this.colorResolution;
        iIOMetadataNode3.setAttribute("value", i == -1 ? "" : Integer.toString(i));
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardDimensionNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Dimension");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("PixelAspectRatio");
        iIOMetadataNode2.setAttribute("value", Float.toString(this.pixelAspectRatio != 0 ? (r2 + 15) / 64.0f : 1.0f));
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("ImageOrientation");
        iIOMetadataNode3.setAttribute("value", PDLayoutAttributeObject.LINE_HEIGHT_NORMAL);
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("HorizontalScreenSize");
        int i = this.logicalScreenWidth;
        iIOMetadataNode4.setAttribute("value", i == -1 ? "" : Integer.toString(i));
        iIOMetadataNode.appendChild(iIOMetadataNode4);
        IIOMetadataNode iIOMetadataNode5 = new IIOMetadataNode("VerticalScreenSize");
        int i2 = this.logicalScreenHeight;
        iIOMetadataNode5.setAttribute("value", i2 != -1 ? Integer.toString(i2) : "");
        iIOMetadataNode.appendChild(iIOMetadataNode5);
        return iIOMetadataNode;
    }

    public IIOMetadataNode getStandardDocumentNode() {
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode(StandardStructureTypes.DOCUMENT);
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("FormatVersion");
        iIOMetadataNode2.setAttribute("value", this.version);
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
