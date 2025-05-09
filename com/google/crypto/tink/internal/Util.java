package com.google.crypto.tink.internal;

import com.google.crypto.tink.util.Bytes;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Objects;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public final class Util {
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    public static int randKeyId() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bArr = new byte[4];
        int i = 0;
        while (i == 0) {
            secureRandom.nextBytes(bArr);
            i = ((bArr[0] & Byte.MAX_VALUE) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
        }
        return i;
    }

    private static final byte toByteFromPrintableAscii(char c) {
        if (c >= '!' && c <= '~') {
            return (byte) c;
        }
        throw new TinkBugException("Not a printable ASCII character: " + c);
    }

    public static final Bytes toBytesFromPrintableAscii(String s) {
        byte[] bArr = new byte[s.length()];
        for (int i = 0; i < s.length(); i++) {
            bArr[i] = toByteFromPrintableAscii(s.charAt(i));
        }
        return Bytes.copyFrom(bArr);
    }

    public static boolean isAndroid() {
        return Objects.equals(System.getProperty("java.vendor"), "The Android Project");
    }

    @Nullable
    public static Integer getAndroidApiLevel() {
        if (isAndroid()) {
            return BuildDispatchedCode.getApiLevel();
        }
        return null;
    }

    private Util() {
    }
}
