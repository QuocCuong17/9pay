package org.apache.pdfbox.util;

/* loaded from: classes5.dex */
public final class Hex {
    private Hex() {
    }

    public static String getString(byte b) {
        return Integer.toHexString((b & 255) | 256).substring(1).toUpperCase();
    }

    public static byte[] getBytes(byte b) {
        return getString(b).getBytes(Charsets.US_ASCII);
    }
}
