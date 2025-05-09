package com.otaliastudios.transcoder.resample;

import java.nio.ShortBuffer;

/* loaded from: classes4.dex */
public class DownsampleAudioResampler implements AudioResampler {
    private static float ratio(int i, int i2) {
        return i / i2;
    }

    @Override // com.otaliastudios.transcoder.resample.AudioResampler
    public void resample(ShortBuffer shortBuffer, int i, ShortBuffer shortBuffer2, int i2, int i3) {
        if (i < i2) {
            throw new IllegalArgumentException("Illegal use of DownsampleAudioResampler");
        }
        if (i3 != 1 && i3 != 2) {
            throw new IllegalArgumentException("Illegal use of DownsampleAudioResampler. Channels:" + i3);
        }
        int remaining = shortBuffer.remaining() / i3;
        int ceil = (int) Math.ceil(remaining * (i2 / i));
        int i4 = remaining - ceil;
        float ratio = ratio(ceil, ceil);
        float ratio2 = ratio(i4, i4);
        int i5 = ceil;
        int i6 = i4;
        while (i5 > 0 && i6 > 0) {
            if (ratio >= ratio2) {
                shortBuffer2.put(shortBuffer.get());
                if (i3 == 2) {
                    shortBuffer2.put(shortBuffer.get());
                }
                i5--;
                ratio = ratio(i5, ceil);
            } else {
                shortBuffer.position(shortBuffer.position() + i3);
                i6--;
                ratio2 = ratio(i6, i4);
            }
        }
    }
}
