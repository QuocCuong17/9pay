package com.github.jaiimageio.plugins.tiff;

import com.github.jaiimageio.impl.plugins.tiff.TIFFFieldNode;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.lang.reflect.Array;
import java.util.StringTokenizer;
import kotlin.jvm.internal.CharCompanionObject;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import org.w3c.dom.Node;

/* loaded from: classes3.dex */
public class TIFFField implements Comparable {
    private int count;
    private Object data;
    private TIFFTag tag;
    private int tagNumber;
    private int type;
    private static final String[] typeNames = {null, "Byte", "Ascii", "Short", "Long", "Rational", "SByte", "Undefined", "SShort", "SLong", "SRational", "Float", PDLayoutAttributeObject.BORDER_STYLE_DOUBLE, "IFDPointer"};
    private static final boolean[] isIntegral = {false, true, false, true, true, false, true, true, true, true, false, false, false, false};

    private TIFFField() {
    }

    private static String getAttribute(Node node, String str) {
        return node.getAttributes().getNamedItem(str).getNodeValue();
    }

    private static void initData(Node node, int[] iArr, int[] iArr2, Object[] objArr) {
        Object obj;
        String substring = node.getNodeName().substring(4);
        String substring2 = substring.substring(0, substring.length() - 1);
        int typeByName = getTypeByName(substring2);
        if (typeByName == -1) {
            throw new IllegalArgumentException("typeName = " + substring2);
        }
        int i = 0;
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            substring2.equals(firstChild.getNodeName().substring(4));
            i++;
        }
        if (i > 0) {
            obj = createArrayForType(typeByName, i);
            int i2 = 0;
            for (Node firstChild2 = node.getFirstChild(); firstChild2 != null; firstChild2 = firstChild2.getNextSibling()) {
                String attribute = getAttribute(firstChild2, "value");
                switch (typeByName) {
                    case 1:
                    case 6:
                        ((byte[]) obj)[i2] = (byte) Integer.parseInt(attribute);
                        break;
                    case 2:
                        ((String[]) obj)[i2] = attribute;
                        break;
                    case 3:
                        ((char[]) obj)[i2] = (char) Integer.parseInt(attribute);
                        break;
                    case 4:
                    case 13:
                        ((long[]) obj)[i2] = Long.parseLong(attribute);
                        break;
                    case 5:
                        int indexOf = attribute.indexOf(RemoteSettings.FORWARD_SLASH_STRING);
                        String substring3 = attribute.substring(0, indexOf);
                        String substring4 = attribute.substring(indexOf + 1);
                        long[][] jArr = (long[][]) obj;
                        jArr[i2] = new long[2];
                        jArr[i2][0] = Long.parseLong(substring3);
                        jArr[i2][1] = Long.parseLong(substring4);
                        break;
                    case 8:
                        ((short[]) obj)[i2] = (short) Integer.parseInt(attribute);
                        break;
                    case 9:
                        ((int[]) obj)[i2] = Integer.parseInt(attribute);
                        break;
                    case 10:
                        int indexOf2 = attribute.indexOf(RemoteSettings.FORWARD_SLASH_STRING);
                        String substring5 = attribute.substring(0, indexOf2);
                        String substring6 = attribute.substring(indexOf2 + 1);
                        int[][] iArr3 = (int[][]) obj;
                        iArr3[i2] = new int[2];
                        iArr3[i2][0] = Integer.parseInt(substring5);
                        iArr3[i2][1] = Integer.parseInt(substring6);
                        break;
                    case 11:
                        ((float[]) obj)[i2] = Float.parseFloat(attribute);
                        break;
                    case 12:
                        ((double[]) obj)[i2] = Double.parseDouble(attribute);
                        break;
                }
                i2++;
            }
        } else {
            obj = null;
        }
        iArr[0] = typeByName;
        iArr2[0] = i;
        objArr[0] = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Object[]] */
    public static TIFFField createFromMetadataNode(TIFFTagSet tIFFTagSet, Node node) {
        TIFFTag tIFFTag;
        int i;
        int i2;
        if (node == null) {
            throw new IllegalArgumentException("node == null!");
        }
        if (!node.getNodeName().equals("TIFFField")) {
            throw new IllegalArgumentException("!name.equals(\"TIFFField\")");
        }
        int parseInt = Integer.parseInt(getAttribute(node, "number"));
        byte[] bArr = null;
        bArr = null;
        int i3 = 0;
        if (tIFFTagSet != null) {
            tIFFTag = tIFFTagSet.getTag(parseInt);
        } else {
            tIFFTag = new TIFFTag("unknown", parseInt, 0, null);
        }
        Node firstChild = node.getFirstChild();
        if (firstChild != null) {
            if (firstChild.getNodeName().equals("TIFFUndefined")) {
                StringTokenizer stringTokenizer = new StringTokenizer(getAttribute(firstChild, "value"), ",");
                i2 = stringTokenizer.countTokens();
                byte[] bArr2 = new byte[i2];
                while (i3 < i2) {
                    bArr2[i3] = (byte) Integer.parseInt(stringTokenizer.nextToken());
                    i3++;
                }
                i = 7;
                bArr = bArr2;
            } else {
                int[] iArr = new int[1];
                int[] iArr2 = new int[1];
                ?? r3 = new Object[1];
                initData(node.getFirstChild(), iArr, iArr2, r3);
                i = iArr[0];
                i2 = iArr2[0];
                bArr = r3[0];
            }
            i3 = i2;
        } else {
            i = 13;
            while (i >= 1 && !tIFFTag.isDataTypeOK(i)) {
                i--;
            }
        }
        return new TIFFField(tIFFTag, i, i3, bArr);
    }

    public TIFFField(TIFFTag tIFFTag, int i, int i2, Object obj) {
        if (tIFFTag == null) {
            throw new IllegalArgumentException("tag == null!");
        }
        if (i < 1 || i > 13) {
            throw new IllegalArgumentException("Unknown data type " + i);
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("count < 0!");
        }
        this.tag = tIFFTag;
        this.tagNumber = tIFFTag.getNumber();
        this.type = i;
        this.count = i2;
        this.data = obj;
    }

    public TIFFField(TIFFTag tIFFTag, int i, int i2) {
        this(tIFFTag, i, i2, createArrayForType(i, i2));
    }

    public TIFFField(TIFFTag tIFFTag, int i) {
        if (tIFFTag == null) {
            throw new IllegalArgumentException("tag == null!");
        }
        if (i < 0) {
            throw new IllegalArgumentException("value < 0!");
        }
        this.tag = tIFFTag;
        this.tagNumber = tIFFTag.getNumber();
        this.count = 1;
        if (i < 65536) {
            this.type = 3;
            this.data = new char[]{(char) i};
        } else {
            this.type = 4;
            this.data = new long[]{i};
        }
    }

    public TIFFTag getTag() {
        return this.tag;
    }

    public int getTagNumber() {
        return this.tagNumber;
    }

    public int getType() {
        return this.type;
    }

    public static String getTypeName(int i) {
        if (i < 1 || i > 13) {
            throw new IllegalArgumentException("Unknown data type " + i);
        }
        return typeNames[i];
    }

    public static int getTypeByName(String str) {
        for (int i = 1; i <= 13; i++) {
            if (str.equals(typeNames[i])) {
                return i;
            }
        }
        return -1;
    }

    public static Object createArrayForType(int i, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("count < 0!");
        }
        switch (i) {
            case 1:
            case 6:
            case 7:
                return new byte[i2];
            case 2:
                return new String[i2];
            case 3:
                return new char[i2];
            case 4:
            case 13:
                return new long[i2];
            case 5:
                return (long[][]) Array.newInstance((Class<?>) long.class, i2, 2);
            case 8:
                return new short[i2];
            case 9:
                return new int[i2];
            case 10:
                return (int[][]) Array.newInstance((Class<?>) int.class, i2, 2);
            case 11:
                return new float[i2];
            case 12:
                return new double[i2];
            default:
                throw new IllegalArgumentException("Unknown data type " + i);
        }
    }

    public Node getAsNativeNode() {
        return new TIFFFieldNode(this);
    }

    public boolean isIntegral() {
        return isIntegral[this.type];
    }

    public int getCount() {
        return this.count;
    }

    public Object getData() {
        return this.data;
    }

    public byte[] getAsBytes() {
        return (byte[]) this.data;
    }

    public char[] getAsChars() {
        return (char[]) this.data;
    }

    public short[] getAsShorts() {
        return (short[]) this.data;
    }

    public int[] getAsInts() {
        Object obj = this.data;
        if (obj instanceof int[]) {
            return (int[]) obj;
        }
        int i = 0;
        if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            int[] iArr = new int[cArr.length];
            while (i < cArr.length) {
                iArr[i] = cArr[i] & CharCompanionObject.MAX_VALUE;
                i++;
            }
            return iArr;
        }
        if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            int[] iArr2 = new int[sArr.length];
            while (i < sArr.length) {
                iArr2[i] = sArr[i];
                i++;
            }
            return iArr2;
        }
        throw new ClassCastException("Data not char[], short[], or int[]!");
    }

    public long[] getAsLongs() {
        return (long[]) this.data;
    }

    public float[] getAsFloats() {
        return (float[]) this.data;
    }

    public double[] getAsDoubles() {
        return (double[]) this.data;
    }

    public int[][] getAsSRationals() {
        return (int[][]) this.data;
    }

    public long[][] getAsRationals() {
        return (long[][]) this.data;
    }

    public int getAsInt(int i) {
        switch (this.type) {
            case 1:
            case 7:
                return ((byte[]) this.data)[i] & 255;
            case 2:
                return (int) Double.parseDouble(((String[]) this.data)[i]);
            case 3:
                return ((char[]) this.data)[i] & CharCompanionObject.MAX_VALUE;
            case 4:
            case 13:
                return (int) ((long[]) this.data)[i];
            case 5:
                long[] asRational = getAsRational(i);
                return (int) (asRational[0] / asRational[1]);
            case 6:
                return ((byte[]) this.data)[i];
            case 8:
                return ((short[]) this.data)[i];
            case 9:
                return ((int[]) this.data)[i];
            case 10:
                int[] asSRational = getAsSRational(i);
                return (int) (asSRational[0] / asSRational[1]);
            case 11:
                return (int) ((float[]) this.data)[i];
            case 12:
                return (int) ((double[]) this.data)[i];
            default:
                throw new ClassCastException();
        }
    }

    public long getAsLong(int i) {
        int i2 = this.type;
        if (i2 != 13) {
            switch (i2) {
                case 1:
                case 7:
                    return ((byte[]) this.data)[i] & 255;
                case 2:
                    return (long) Double.parseDouble(((String[]) this.data)[i]);
                case 3:
                    return ((char[]) this.data)[i] & CharCompanionObject.MAX_VALUE;
                case 4:
                    break;
                case 5:
                    long[] asRational = getAsRational(i);
                    return (long) (asRational[0] / asRational[1]);
                case 6:
                    return ((byte[]) this.data)[i];
                case 8:
                    return ((short[]) this.data)[i];
                case 9:
                    return ((int[]) this.data)[i];
                case 10:
                    int[] asSRational = getAsSRational(i);
                    return (long) (asSRational[0] / asSRational[1]);
                default:
                    throw new ClassCastException();
            }
        }
        return ((long[]) this.data)[i];
    }

    public float getAsFloat(int i) {
        switch (this.type) {
            case 1:
            case 7:
                return ((byte[]) this.data)[i] & 255;
            case 2:
                return (float) Double.parseDouble(((String[]) this.data)[i]);
            case 3:
                return ((char[]) this.data)[i] & CharCompanionObject.MAX_VALUE;
            case 4:
            case 13:
                return (float) ((long[]) this.data)[i];
            case 5:
                long[] asRational = getAsRational(i);
                return (float) (asRational[0] / asRational[1]);
            case 6:
                return ((byte[]) this.data)[i];
            case 8:
                return ((short[]) this.data)[i];
            case 9:
                return ((int[]) this.data)[i];
            case 10:
                int[] asSRational = getAsSRational(i);
                return (float) (asSRational[0] / asSRational[1]);
            case 11:
                return ((float[]) this.data)[i];
            case 12:
                return (float) ((double[]) this.data)[i];
            default:
                throw new ClassCastException();
        }
    }

    public double getAsDouble(int i) {
        double d;
        double d2;
        switch (this.type) {
            case 1:
            case 7:
                return ((byte[]) this.data)[i] & 255;
            case 2:
                return Double.parseDouble(((String[]) this.data)[i]);
            case 3:
                return ((char[]) this.data)[i] & CharCompanionObject.MAX_VALUE;
            case 4:
            case 13:
                return ((long[]) this.data)[i];
            case 5:
                long[] asRational = getAsRational(i);
                d = asRational[0];
                d2 = asRational[1];
                break;
            case 6:
                return ((byte[]) this.data)[i];
            case 8:
                return ((short[]) this.data)[i];
            case 9:
                return ((int[]) this.data)[i];
            case 10:
                int[] asSRational = getAsSRational(i);
                d = asSRational[0];
                d2 = asSRational[1];
                break;
            case 11:
                return ((float[]) this.data)[i];
            case 12:
                return ((double[]) this.data)[i];
            default:
                throw new ClassCastException();
        }
        return d / d2;
    }

    public String getAsString(int i) {
        return ((String[]) this.data)[i];
    }

    public int[] getAsSRational(int i) {
        return ((int[][]) this.data)[i];
    }

    public long[] getAsRational(int i) {
        return ((long[][]) this.data)[i];
    }

    public String getValueAsString(int i) {
        switch (this.type) {
            case 1:
            case 7:
                return Integer.toString(((byte[]) this.data)[i] & 255);
            case 2:
                return ((String[]) this.data)[i];
            case 3:
                return Integer.toString(((char[]) this.data)[i] & CharCompanionObject.MAX_VALUE);
            case 4:
            case 13:
                return Long.toString(((long[]) this.data)[i]);
            case 5:
                long[] asRational = getAsRational(i);
                if (asRational[1] != 0 && asRational[0] % asRational[1] == 0) {
                    return Long.toString(asRational[0] / asRational[1]) + "/1";
                }
                return Long.toString(asRational[0]) + RemoteSettings.FORWARD_SLASH_STRING + Long.toString(asRational[1]);
            case 6:
                return Integer.toString(((byte[]) this.data)[i]);
            case 8:
                return Integer.toString(((short[]) this.data)[i]);
            case 9:
                return Integer.toString(((int[]) this.data)[i]);
            case 10:
                int[] asSRational = getAsSRational(i);
                if (asSRational[1] != 0 && asSRational[0] % asSRational[1] == 0) {
                    return Integer.toString(asSRational[0] / asSRational[1]) + "/1";
                }
                return Integer.toString(asSRational[0]) + RemoteSettings.FORWARD_SLASH_STRING + Integer.toString(asSRational[1]);
            case 11:
                return Float.toString(((float[]) this.data)[i]);
            case 12:
                return Double.toString(((double[]) this.data)[i]);
            default:
                throw new ClassCastException();
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        int tagNumber = ((TIFFField) obj).getTagNumber();
        int i = this.tagNumber;
        if (i < tagNumber) {
            return -1;
        }
        return i > tagNumber ? 1 : 0;
    }
}
