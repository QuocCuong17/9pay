package com.otaliastudios.transcoder.stretch;

import java.nio.ShortBuffer;
import java.util.Random;

/* loaded from: classes4.dex */
public class InsertAudioStretcher implements AudioStretcher {
    private static final Random NOISE = new Random();

    private static float ratio(int i, int i2) {
        return i / i2;
    }

    private static short noise() {
        return (short) NOISE.nextInt(300);
    }

    @Override // com.otaliastudios.transcoder.stretch.AudioStretcher
    public void stretch(ShortBuffer shortBuffer, ShortBuffer shortBuffer2, int i) {
        if (shortBuffer.remaining() >= shortBuffer2.remaining()) {
            throw new IllegalArgumentException("Illegal use of AudioStretcher.INSERT");
        }
        if (i != 1 && i != 2) {
            throw new IllegalArgumentException("Illegal use of AudioStretcher.INSERT. Channels:" + i);
        }
        int remaining = shortBuffer.remaining() / i;
        int floor = (int) Math.floor((shortBuffer2.remaining() - shortBuffer.remaining()) / i);
        float ratio = ratio(remaining, remaining);
        float ratio2 = ratio(floor, floor);
        int i2 = remaining;
        while (i2 > 0 && floor > 0) {
            if (ratio >= ratio2) {
                shortBuffer2.put(shortBuffer.get());
                if (i == 2) {
                    shortBuffer2.put(shortBuffer.get());
                }
                i2--;
                ratio = ratio(i2, remaining);
            } else {
                shortBuffer2.put(noise());
                if (i == 2) {
                    shortBuffer2.put(noise());
                }
                floor--;
                ratio2 = ratio(floor, remaining);
            }
        }
    }
}
