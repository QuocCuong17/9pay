package com.otaliastudios.transcoder.internal.audio.remix;

import java.nio.ShortBuffer;

/* loaded from: classes4.dex */
public class PassThroughAudioRemixer implements AudioRemixer {
    @Override // com.otaliastudios.transcoder.internal.audio.remix.AudioRemixer
    public int getRemixedSize(int i) {
        return i;
    }

    @Override // com.otaliastudios.transcoder.internal.audio.remix.AudioRemixer
    public void remix(ShortBuffer shortBuffer, ShortBuffer shortBuffer2) {
        shortBuffer2.put(shortBuffer);
    }
}
