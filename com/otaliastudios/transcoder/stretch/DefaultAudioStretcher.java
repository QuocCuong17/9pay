package com.otaliastudios.transcoder.stretch;

import java.nio.ShortBuffer;

/* loaded from: classes4.dex */
public class DefaultAudioStretcher implements AudioStretcher {
    @Override // com.otaliastudios.transcoder.stretch.AudioStretcher
    public void stretch(ShortBuffer shortBuffer, ShortBuffer shortBuffer2, int i) {
        if (shortBuffer.remaining() < shortBuffer2.remaining()) {
            INSERT.stretch(shortBuffer, shortBuffer2, i);
        } else if (shortBuffer.remaining() > shortBuffer2.remaining()) {
            CUT.stretch(shortBuffer, shortBuffer2, i);
        } else {
            PASSTHROUGH.stretch(shortBuffer, shortBuffer2, i);
        }
    }
}
