package com.otaliastudios.transcoder.resample;

import java.nio.ShortBuffer;

/* loaded from: classes4.dex */
public class PassThroughAudioResampler implements AudioResampler {
    @Override // com.otaliastudios.transcoder.resample.AudioResampler
    public void resample(ShortBuffer shortBuffer, int i, ShortBuffer shortBuffer2, int i2, int i3) {
        if (i != i2) {
            throw new IllegalArgumentException("Illegal use of PassThroughAudioResampler");
        }
        shortBuffer2.put(shortBuffer);
    }
}
