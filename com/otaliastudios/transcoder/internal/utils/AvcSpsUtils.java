package com.otaliastudios.transcoder.internal.utils;

import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public class AvcSpsUtils {
    public static final byte PROFILE_IDC_BASELINE = 66;
    public static final byte PROFILE_IDC_EXTENDED = 88;
    public static final byte PROFILE_IDC_HIGH = 100;
    public static final byte PROFILE_IDC_MAIN = 77;

    public static byte getProfileIdc(ByteBuffer byteBuffer) {
        return byteBuffer.get(0);
    }

    public static String getProfileName(byte b) {
        if (b == 66) {
            return "Baseline Profile";
        }
        if (b == 77) {
            return "Main Profile";
        }
        if (b == 88) {
            return "Extended Profile";
        }
        if (b == 100) {
            return "High Profile";
        }
        return "Unknown Profile (" + ((int) b) + ")";
    }
}
