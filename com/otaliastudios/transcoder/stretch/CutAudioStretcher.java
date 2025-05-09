package com.otaliastudios.transcoder.stretch;

import java.nio.ShortBuffer;

/* loaded from: classes4.dex */
public class CutAudioStretcher implements AudioStretcher {
    @Override // com.otaliastudios.transcoder.stretch.AudioStretcher
    public void stretch(ShortBuffer shortBuffer, ShortBuffer shortBuffer2, int i) {
        if (shortBuffer.remaining() < shortBuffer2.remaining()) {
            throw new IllegalArgumentException("Illegal use of CutAudioStretcher");
        }
        int remaining = shortBuffer.remaining() - shortBuffer2.remaining();
        shortBuffer.limit(shortBuffer.limit() - remaining);
        shortBuffer2.put(shortBuffer);
        shortBuffer.limit(shortBuffer.limit() + remaining);
        shortBuffer.position(shortBuffer.limit());
    }
}
