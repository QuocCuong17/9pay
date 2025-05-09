package com.otaliastudios.transcoder.stretch;

import java.nio.ShortBuffer;

/* loaded from: classes4.dex */
public interface AudioStretcher {
    public static final AudioStretcher PASSTHROUGH = new PassThroughAudioStretcher();
    public static final AudioStretcher CUT = new CutAudioStretcher();
    public static final AudioStretcher INSERT = new InsertAudioStretcher();

    void stretch(ShortBuffer shortBuffer, ShortBuffer shortBuffer2, int i);
}
