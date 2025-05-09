package com.github.jaiimageio.impl.plugins.gif;

import com.google.firebase.analytics.FirebaseAnalytics;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import org.w3c.dom.Node;

/* loaded from: classes3.dex */
abstract class GIFMetadata extends IIOMetadata {
    static final int UNDEFINED_INTEGER_VALUE = -1;

    protected abstract void mergeNativeTree(Node node) throws IIOInvalidTreeException;

    protected abstract void mergeStandardTree(Node node) throws IIOInvalidTreeException;

    /* JADX INFO: Access modifiers changed from: protected */
    public static void fatal(Node node, String str) throws IIOInvalidTreeException {
        throw new IIOInvalidTreeException(str, node);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String getStringAttribute(Node node, String str, String str2, boolean z, String[] strArr) throws IIOInvalidTreeException {
        Node namedItem = node.getAttributes().getNamedItem(str);
        if (namedItem == null) {
            if (!z) {
                return str2;
            }
            fatal(node, "Required attribute " + str + " not present!");
        }
        String nodeValue = namedItem.getNodeValue();
        if (strArr != null) {
            if (nodeValue == null) {
                fatal(node, "Null value for " + node.getNodeName() + " attribute " + str + "!");
            }
            int length = strArr.length;
            boolean z2 = false;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (nodeValue.equals(strArr[i])) {
                    z2 = true;
                    break;
                }
                i++;
            }
            if (!z2) {
                fatal(node, "Bad value for " + node.getNodeName() + " attribute " + str + "!");
            }
        }
        return nodeValue;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int getIntAttribute(Node node, String str, int i, boolean z, boolean z2, int i2, int i3) throws IIOInvalidTreeException {
        String stringAttribute = getStringAttribute(node, str, null, z, null);
        if (stringAttribute != null && !"".equals(stringAttribute)) {
            try {
                i = Integer.parseInt(stringAttribute);
            } catch (NumberFormatException unused) {
                fatal(node, "Bad value for " + node.getNodeName() + " attribute " + str + "!");
            }
            if (z2 && (i < i2 || i > i3)) {
                fatal(node, "Bad value for " + node.getNodeName() + " attribute " + str + "!");
            }
        }
        return i;
    }

    protected static float getFloatAttribute(Node node, String str, float f, boolean z) throws IIOInvalidTreeException {
        String stringAttribute = getStringAttribute(node, str, null, z, null);
        return stringAttribute == null ? f : Float.parseFloat(stringAttribute);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int getIntAttribute(Node node, String str, boolean z, int i, int i2) throws IIOInvalidTreeException {
        return getIntAttribute(node, str, -1, true, z, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static float getFloatAttribute(Node node, String str) throws IIOInvalidTreeException {
        return getFloatAttribute(node, str, -1.0f, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean getBooleanAttribute(Node node, String str, boolean z, boolean z2) throws IIOInvalidTreeException {
        Node namedItem = node.getAttributes().getNamedItem(str);
        if (namedItem == null) {
            if (!z2) {
                return z;
            }
            fatal(node, "Required attribute " + str + " not present!");
        }
        String nodeValue = namedItem.getNodeValue();
        if (nodeValue.equalsIgnoreCase("TRUE")) {
            return true;
        }
        if (nodeValue.equalsIgnoreCase("FALSE")) {
            return false;
        }
        fatal(node, "Attribute " + str + " must be 'TRUE' or 'FALSE'!");
        return false;
    }

    protected static boolean getBooleanAttribute(Node node, String str) throws IIOInvalidTreeException {
        return getBooleanAttribute(node, str, false, true);
    }

    protected static int getEnumeratedAttribute(Node node, String str, String[] strArr, int i, boolean z) throws IIOInvalidTreeException {
        Node namedItem = node.getAttributes().getNamedItem(str);
        if (namedItem == null) {
            if (!z) {
                return i;
            }
            fatal(node, "Required attribute " + str + " not present!");
        }
        String nodeValue = namedItem.getNodeValue();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (nodeValue.equals(strArr[i2])) {
                return i2;
            }
        }
        fatal(node, "Illegal value for attribute " + str + "!");
        return -1;
    }

    protected static int getEnumeratedAttribute(Node node, String str, String[] strArr) throws IIOInvalidTreeException {
        return getEnumeratedAttribute(node, str, strArr, -1, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String getAttribute(Node node, String str, String str2, boolean z) throws IIOInvalidTreeException {
        Node namedItem = node.getAttributes().getNamedItem(str);
        if (namedItem == null) {
            if (!z) {
                return str2;
            }
            fatal(node, "Required attribute " + str + " not present!");
        }
        return namedItem.getNodeValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String getAttribute(Node node, String str) throws IIOInvalidTreeException {
        return getAttribute(node, str, null, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GIFMetadata(boolean z, String str, String str2, String[] strArr, String[] strArr2) {
        super(z, str, str2, strArr, strArr2);
    }

    public void mergeTree(String str, Node node) throws IIOInvalidTreeException {
        if (str.equals(this.nativeMetadataFormatName)) {
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

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] getColorTable(Node node, String str, boolean z, int i) throws IIOInvalidTreeException {
        int i2;
        byte[] bArr = new byte[256];
        byte[] bArr2 = new byte[256];
        byte[] bArr3 = new byte[256];
        Node firstChild = node.getFirstChild();
        if (firstChild == null) {
            fatal(node, "Palette has no entries!");
        }
        int i3 = -1;
        while (true) {
            i2 = 0;
            if (firstChild == null) {
                break;
            }
            if (!firstChild.getNodeName().equals(str)) {
                fatal(node, "Only a " + str + " may be a child of a " + firstChild.getNodeName() + "!");
            }
            int intAttribute = getIntAttribute(firstChild, FirebaseAnalytics.Param.INDEX, true, 0, 255);
            if (intAttribute > i3) {
                i3 = intAttribute;
            }
            bArr[intAttribute] = (byte) getIntAttribute(firstChild, "red", true, 0, 255);
            bArr2[intAttribute] = (byte) getIntAttribute(firstChild, "green", true, 0, 255);
            bArr3[intAttribute] = (byte) getIntAttribute(firstChild, "blue", true, 0, 255);
            firstChild = firstChild.getNextSibling();
        }
        int i4 = i3 + 1;
        if (z && i4 != i) {
            fatal(node, "Unexpected length for palette!");
        }
        byte[] bArr4 = new byte[i4 * 3];
        int i5 = 0;
        while (i2 < i4) {
            int i6 = i5 + 1;
            bArr4[i5] = bArr[i2];
            int i7 = i6 + 1;
            bArr4[i6] = bArr2[i2];
            bArr4[i7] = bArr3[i2];
            i2++;
            i5 = i7 + 1;
        }
        return bArr4;
    }
}
