package org.ejbca.cvc.util;

/* loaded from: classes6.dex */
public final class StringConverter {
    private static final char[] HEXCHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final String HEXINDEX = "0123456789abcdef          ABCDEF";

    private StringConverter() {
    }

    public static byte[] hexToByte(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            bArr[i] = (byte) (((HEXINDEX.indexOf(str.charAt(i2)) & 15) << 4) + (HEXINDEX.indexOf(str.charAt(i3)) & 15));
            i++;
            i2 = i3 + 1;
        }
        return bArr;
    }

    public static String byteToHex(byte[] bArr) {
        return byteToHex(bArr, null);
    }

    public static String byteToHex(byte[] bArr, String str) {
        int length = bArr.length;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(byteToHex(bArr[i]));
            if (str != null && i + 1 < length) {
                stringBuffer.append(str);
            }
        }
        return stringBuffer.toString();
    }

    public static String byteToHex(byte b) {
        int i = b & 255;
        char[] cArr = HEXCHAR;
        char c = cArr[(i >> 4) & 15];
        return Character.toString(c) + cArr[i & 15];
    }
}
