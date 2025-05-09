package com.otaliastudios.transcoder.resample;

import java.nio.ShortBuffer;

/* loaded from: classes4.dex */
public class UpsampleAudioResampler implements AudioResampler {
    private static float ratio(int i, int i2) {
        return i / i2;
    }

    @Override // com.otaliastudios.transcoder.resample.AudioResampler
    public void resample(ShortBuffer shortBuffer, int i, ShortBuffer shortBuffer2, int i2, int i3) {
        if (i > i2) {
            throw new IllegalArgumentException("Illegal use of UpsampleAudioResampler");
        }
        if (i3 != 1 && i3 != 2) {
            throw new IllegalArgumentException("Illegal use of UpsampleAudioResampler. Channels:" + i3);
        }
        int remaining = shortBuffer.remaining() / i3;
        int ceil = ((int) Math.ceil(remaining * (i2 / i))) - remaining;
        float ratio = ratio(remaining, remaining);
        float ratio2 = ratio(ceil, ceil);
        int i4 = ceil;
        int i5 = remaining;
        while (i5 > 0 && i4 > 0) {
            if (ratio >= ratio2) {
                shortBuffer2.put(shortBuffer.get());
                if (i3 == 2) {
                    shortBuffer2.put(shortBuffer.get());
                }
                i5--;
                ratio = ratio(i5, remaining);
            } else {
                shortBuffer2.put(fakeSample(shortBuffer2, shortBuffer, 1, i3));
                if (i3 == 2) {
                    shortBuffer2.put(fakeSample(shortBuffer2, shortBuffer, 2, i3));
                }
                i4--;
                ratio2 = ratio(i4, ceil);
            }
        }
    }

    private static short fakeSample(ShortBuffer shortBuffer, ShortBuffer shortBuffer2, int i, int i2) {
        return shortBuffer.get(shortBuffer.position() - i2);
    }
}
