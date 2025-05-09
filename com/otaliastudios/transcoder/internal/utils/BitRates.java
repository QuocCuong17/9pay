package com.otaliastudios.transcoder.internal.utils;

/* loaded from: classes4.dex */
public class BitRates {
    public static long estimateAudioBitRate(int i, int i2) {
        return (long) (16 * i2 * i * 0.75d);
    }

    public static long estimateVideoBitRate(int i, int i2, int i3) {
        return i * 0.14f * i2 * i3;
    }
}
