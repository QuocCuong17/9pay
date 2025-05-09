package org.apache.pdfbox.cos;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import kotlin.text.Typography;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.jmrtd.cbeff.ISO781611;

/* loaded from: classes5.dex */
final class PDFDocEncoding {
    private static final char REPLACEMENT_CHARACTER = 65533;
    private static final int[] CODE_TO_UNI = new int[256];
    private static final Map<Character, Integer> UNI_TO_CODE = new HashMap(256);

    static {
        for (int i = 0; i < 256; i++) {
            set(i, (char) i);
        }
        set(24, (char) 728);
        set(25, (char) 711);
        set(26, (char) 710);
        set(27, (char) 729);
        set(28, (char) 733);
        set(29, (char) 731);
        set(30, (char) 730);
        set(31, (char) 732);
        set(127, (char) 65533);
        set(128, Typography.bullet);
        set(129, Typography.dagger);
        set(130, Typography.doubleDagger);
        set(ISO781611.CREATION_DATE_AND_TIME_TAG, Typography.ellipsis);
        set(CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, Typography.mdash);
        set(133, Typography.ndash);
        set(134, (char) 402);
        set(135, (char) 8260);
        set(136, (char) 8249);
        set(CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA, (char) 8250);
        set(138, (char) 8722);
        set(139, (char) 8240);
        set(CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA, Typography.lowDoubleQuote);
        set(CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA, Typography.leftDoubleQuote);
        set(142, Typography.rightDoubleQuote);
        set(CipherSuite.TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA, Typography.leftSingleQuote);
        set(CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, Typography.rightSingleQuote);
        set(CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA, Typography.lowSingleQuote);
        set(CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA, Typography.tm);
        set(CipherSuite.TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA, (char) 64257);
        set(CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA, (char) 64258);
        set(CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA, (char) 321);
        set(CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA, (char) 338);
        set(151, (char) 352);
        set(CipherSuite.TLS_DH_RSA_WITH_SEED_CBC_SHA, (char) 376);
        set(153, (char) 381);
        set(CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA, (char) 305);
        set(CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA, (char) 322);
        set(CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, (char) 339);
        set(CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, (char) 353);
        set(158, (char) 382);
        set(CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, (char) 65533);
        set(160, Typography.euro);
    }

    private PDFDocEncoding() {
    }

    private static void set(int i, char c) {
        CODE_TO_UNI[i] = c;
        UNI_TO_CODE.put(Character.valueOf(c), Integer.valueOf(i));
    }

    public static String toString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            int i = b & 255;
            int[] iArr = CODE_TO_UNI;
            if (i >= iArr.length) {
                sb.append('?');
            } else {
                sb.append((char) iArr[i]);
            }
        }
        return sb.toString();
    }

    public static byte[] getBytes(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (char c : str.toCharArray()) {
            if (UNI_TO_CODE.get(Character.valueOf(c)) == null) {
                byteArrayOutputStream.write(0);
            } else {
                byteArrayOutputStream.write(c);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static boolean containsChar(char c) {
        return UNI_TO_CODE.containsKey(Character.valueOf(c));
    }
}
