package com.otaliastudios.transcoder.resample;

import java.nio.ShortBuffer;

/* loaded from: classes4.dex */
public class DefaultAudioResampler implements AudioResampler {
    @Override // com.otaliastudios.transcoder.resample.AudioResampler
    public void resample(ShortBuffer shortBuffer, int i, ShortBuffer shortBuffer2, int i2, int i3) {
        if (i < i2) {
            UPSAMPLE.resample(shortBuffer, i, shortBuffer2, i2, i3);
        } else if (i > i2) {
            DOWNSAMPLE.resample(shortBuffer, i, shortBuffer2, i2, i3);
        } else {
            PASSTHROUGH.resample(shortBuffer, i, shortBuffer2, i2, i3);
        }
    }
}
