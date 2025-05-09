package com.otaliastudios.transcoder;

/* loaded from: classes4.dex */
public interface TranscoderListener {
    void onTranscodeCanceled();

    void onTranscodeCompleted(int i);

    void onTranscodeFailed(Throwable th);

    void onTranscodeProgress(double d);
}
