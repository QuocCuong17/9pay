package com.otaliastudios.transcoder.internal.audio;

import kotlin.Metadata;

/* compiled from: conversions.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\r\u001a\u0018\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0000\u001a \u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0000\u001a\u0018\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0000\u001a \u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0000\u001a \u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0000\u001a \u0010\u000f\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"BYTES_PER_SAMPLE_PER_CHANNEL", "", "MICROSECONDS_PER_SECOND", "", "bitRate", "sampleRate", "channels", "bytesToUs", "bytes", "samplesToBytes", "samples", "shortsToUs", "shorts", "usToBytes", "us", "usToShorts", "lib_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class ConversionsKt {
    private static final int BYTES_PER_SAMPLE_PER_CHANNEL = 2;
    private static final long MICROSECONDS_PER_SECOND = 1000000;

    public static final int bitRate(int i, int i2) {
        return i2 * i * 2 * 8;
    }

    public static final int samplesToBytes(int i, int i2) {
        return i * i2 * 2;
    }

    public static final long bytesToUs(int i, int i2, int i3) {
        return (i * 1000000) / ((i2 * 2) * i3);
    }

    public static final int usToBytes(long j, int i, int i2) {
        return (int) Math.ceil((j * ((i * 2) * i2)) / 1000000);
    }

    public static final long shortsToUs(int i, int i2, int i3) {
        return bytesToUs(i * 2, i2, i3);
    }

    public static final int usToShorts(long j, int i, int i2) {
        return usToBytes(j, i, i2) / 2;
    }
}
