package com.otaliastudios.transcoder.internal.audio.remix;

import java.nio.ShortBuffer;

/* loaded from: classes4.dex */
public class UpMixAudioRemixer implements AudioRemixer {
    @Override // com.otaliastudios.transcoder.internal.audio.remix.AudioRemixer
    public int getRemixedSize(int i) {
        return i * 2;
    }

    @Override // com.otaliastudios.transcoder.internal.audio.remix.AudioRemixer
    public void remix(ShortBuffer shortBuffer, ShortBuffer shortBuffer2) {
        int min = Math.min(shortBuffer.remaining(), shortBuffer2.remaining() / 2);
        for (int i = 0; i < min; i++) {
            short s = shortBuffer.get();
            shortBuffer2.put(s);
            shortBuffer2.put(s);
        }
    }
}
