package com.otaliastudios.transcoder.internal.audio.remix;

import java.nio.ShortBuffer;
import kotlin.jvm.internal.ShortCompanionObject;

/* loaded from: classes4.dex */
public class DownMixAudioRemixer implements AudioRemixer {
    private static final int SIGNED_SHORT_LIMIT = 32768;
    private static final int UNSIGNED_SHORT_MAX = 65535;

    @Override // com.otaliastudios.transcoder.internal.audio.remix.AudioRemixer
    public void remix(ShortBuffer shortBuffer, ShortBuffer shortBuffer2) {
        int i;
        int min = Math.min(shortBuffer.remaining() / 2, shortBuffer2.remaining());
        for (int i2 = 0; i2 < min; i2++) {
            int i3 = shortBuffer.get() + ShortCompanionObject.MIN_VALUE;
            int i4 = shortBuffer.get() + ShortCompanionObject.MIN_VALUE;
            int i5 = 65535;
            if (i3 < 32768 || i4 < 32768) {
                i = (i3 * i4) / 32768;
            } else {
                i = (((i3 + i4) * 2) - ((i3 * i4) / 32768)) - 65535;
            }
            if (i != 65536) {
                i5 = i;
            }
            shortBuffer2.put((short) (i5 - 32768));
        }
    }

    @Override // com.otaliastudios.transcoder.internal.audio.remix.AudioRemixer
    public int getRemixedSize(int i) {
        return i / 2;
    }
}
