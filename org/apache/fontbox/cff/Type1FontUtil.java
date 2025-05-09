package org.apache.fontbox.cff;

/* loaded from: classes5.dex */
public final class Type1FontUtil {
    private Type1FontUtil() {
    }

    public static String hexEncode(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append("0");
            }
            sb.append(hexString.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] hexDecode(String str) {
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        byte[] bArr = new byte[str.length() / 2];
        int i = 0;
        while (i < str.length()) {
            int i2 = i + 2;
            bArr[i / 2] = (byte) Integer.parseInt(str.substring(i, i2), 16);
            i = i2;
        }
        return bArr;
    }

    public static byte[] eexecEncrypt(byte[] bArr) {
        return encrypt(bArr, 55665, 4);
    }

    public static byte[] charstringEncrypt(byte[] bArr, int i) {
        return encrypt(bArr, 4330, i);
    }

    private static byte[] encrypt(byte[] bArr, int i, int i2) {
        int length = bArr.length + i2;
        byte[] bArr2 = new byte[length];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr2[i3] = 0;
        }
        System.arraycopy(bArr, 0, bArr2, i2, length - i2);
        byte[] bArr3 = new byte[length];
        for (int i4 = 0; i4 < length; i4++) {
            int i5 = (bArr2[i4] & 255) ^ (i >> 8);
            bArr3[i4] = (byte) i5;
            i = 65535 & (((i5 + i) * 52845) + 22719);
        }
        return bArr3;
    }

    public static byte[] eexecDecrypt(byte[] bArr) {
        return decrypt(bArr, 55665, 4);
    }

    public static byte[] charstringDecrypt(byte[] bArr, int i) {
        return decrypt(bArr, 4330, i);
    }

    private static byte[] decrypt(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i3 = 0; i3 < bArr.length; i3++) {
            int i4 = bArr[i3] & 255;
            bArr2[i3] = (byte) ((i >> 8) ^ i4);
            i = 65535 & (((i4 + i) * 52845) + 22719);
        }
        int length = bArr.length - i2;
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr2, i2, bArr3, 0, length);
        return bArr3;
    }
}
