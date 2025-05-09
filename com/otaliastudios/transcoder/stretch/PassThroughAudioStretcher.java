package com.otaliastudios.transcoder.stretch;

import java.nio.ShortBuffer;

/* loaded from: classes4.dex */
public class PassThroughAudioStretcher implements AudioStretcher {
    @Override // com.otaliastudios.transcoder.stretch.AudioStretcher
    public void stretch(ShortBuffer shortBuffer, ShortBuffer shortBuffer2, int i) {
        if (shortBuffer.remaining() > shortBuffer2.remaining()) {
            throw new IllegalArgumentException("Illegal use of PassThroughAudioStretcher");
        }
        shortBuffer2.put(shortBuffer);
    }
}
