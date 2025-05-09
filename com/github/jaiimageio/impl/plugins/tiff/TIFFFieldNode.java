package com.github.jaiimageio.impl.plugins.tiff;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.github.jaiimageio.plugins.tiff.TIFFDirectory;
import com.github.jaiimageio.plugins.tiff.TIFFField;
import com.github.jaiimageio.plugins.tiff.TIFFTag;
import com.github.jaiimageio.plugins.tiff.TIFFTagSet;
import java.util.Arrays;
import java.util.List;
import javax.imageio.metadata.IIOMetadataNode;
import org.w3c.dom.Node;

/* loaded from: classes3.dex */
public class TIFFFieldNode extends IIOMetadataNode {
    private TIFFField field;
    private boolean isIFD;
    private Boolean isInitialized;

    private static String getNodeName(TIFFField tIFFField) {
        return tIFFField.getData() instanceof TIFFDirectory ? "TIFFIFD" : "TIFFField";
    }

    public TIFFFieldNode(TIFFField tIFFField) {
        super(getNodeName(tIFFField));
        this.isInitialized = Boolean.FALSE;
        this.isIFD = tIFFField.getData() instanceof TIFFDirectory;
        this.field = tIFFField;
        TIFFTag tag = tIFFField.getTag();
        int number = tag.getNumber();
        String name = tag.getName();
        if (this.isIFD) {
            if (number != 0) {
                setAttribute("parentTagNumber", Integer.toString(number));
            }
            if (name != null) {
                setAttribute("parentTagName", name);
            }
            TIFFTagSet[] tagSets = ((TIFFDirectory) tIFFField.getData()).getTagSets();
            if (tagSets != null) {
                String str = "";
                for (int i = 0; i < tagSets.length; i++) {
                    str = str + tagSets[i].getClass().getName();
                    if (i != tagSets.length - 1) {
                        str = str + ",";
                    }
                }
                setAttribute("tagSets", str);
                return;
            }
            return;
        }
        setAttribute("number", Integer.toString(number));
        setAttribute("name", name);
    }

    private synchronized void initialize() {
        IIOMetadataNode iIOMetadataNode;
        String valueName;
        if (this.isInitialized == Boolean.TRUE) {
            return;
        }
        int i = 0;
        if (this.isIFD) {
            TIFFDirectory tIFFDirectory = (TIFFDirectory) this.field.getData();
            TIFFField[] tIFFFields = tIFFDirectory.getTIFFFields();
            if (tIFFFields != null) {
                List asList = Arrays.asList(tIFFDirectory.getTagSets());
                int length = tIFFFields.length;
                while (i < length) {
                    TIFFField tIFFField = tIFFFields[i];
                    TIFFIFD.getTag(tIFFField.getTagNumber(), asList);
                    Node asNativeNode = tIFFField.getAsNativeNode();
                    if (asNativeNode != null) {
                        appendChild(asNativeNode);
                    }
                    i++;
                }
            }
        } else {
            int count = this.field.getCount();
            if (this.field.getType() == 7) {
                iIOMetadataNode = new IIOMetadataNode("TIFFUndefined");
                byte[] asBytes = this.field.getAsBytes();
                StringBuffer stringBuffer = new StringBuffer();
                while (i < count) {
                    stringBuffer.append(Integer.toString(asBytes[i] & 255));
                    if (i < count - 1) {
                        stringBuffer.append(",");
                    }
                    i++;
                }
                iIOMetadataNode.setAttribute("value", stringBuffer.toString());
            } else {
                iIOMetadataNode = new IIOMetadataNode("TIFF" + TIFFField.getTypeName(this.field.getType()) + CmcdData.Factory.STREAMING_FORMAT_SS);
                TIFFTag tag = this.field.getTag();
                while (i < count) {
                    IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("TIFF" + TIFFField.getTypeName(this.field.getType()));
                    iIOMetadataNode2.setAttribute("value", this.field.getValueAsString(i));
                    if (tag.hasValueNames() && this.field.isIntegral() && (valueName = tag.getValueName(this.field.getAsInt(i))) != null) {
                        iIOMetadataNode2.setAttribute("description", valueName);
                    }
                    iIOMetadataNode.appendChild(iIOMetadataNode2);
                    i++;
                }
            }
            appendChild(iIOMetadataNode);
        }
        this.isInitialized = Boolean.TRUE;
    }

    public Node appendChild(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("newChild == null!");
        }
        return super.insertBefore(node, (Node) null);
    }

    public boolean hasChildNodes() {
        initialize();
        return super.hasChildNodes();
    }

    public int getLength() {
        initialize();
        return super.getLength();
    }

    public Node getFirstChild() {
        initialize();
        return super.getFirstChild();
    }

    public Node getLastChild() {
        initialize();
        return super.getLastChild();
    }

    public Node getPreviousSibling() {
        initialize();
        return super.getPreviousSibling();
    }

    public Node getNextSibling() {
        initialize();
        return super.getNextSibling();
    }

    public Node insertBefore(Node node, Node node2) {
        initialize();
        return super.insertBefore(node, node2);
    }

    public Node replaceChild(Node node, Node node2) {
        initialize();
        return super.replaceChild(node, node2);
    }

    public Node removeChild(Node node) {
        initialize();
        return super.removeChild(node);
    }

    public Node cloneNode(boolean z) {
        initialize();
        return super.cloneNode(z);
    }
}
