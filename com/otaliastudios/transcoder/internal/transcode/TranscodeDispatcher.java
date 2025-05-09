package com.otaliastudios.transcoder.internal.transcode;

import android.os.Handler;
import com.otaliastudios.transcoder.TranscoderListener;
import com.otaliastudios.transcoder.TranscoderOptions;

/* loaded from: classes4.dex */
class TranscodeDispatcher {
    private final Handler mHandler;
    private final TranscoderListener mListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TranscodeDispatcher(TranscoderOptions transcoderOptions) {
        this.mHandler = transcoderOptions.getListenerHandler();
        this.mListener = transcoderOptions.getListener();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchCancel() {
        this.mHandler.post(new Runnable() { // from class: com.otaliastudios.transcoder.internal.transcode.TranscodeDispatcher.1
            @Override // java.lang.Runnable
            public void run() {
                TranscodeDispatcher.this.mListener.onTranscodeCanceled();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchSuccess(final int i) {
        this.mHandler.post(new Runnable() { // from class: com.otaliastudios.transcoder.internal.transcode.TranscodeDispatcher.2
            @Override // java.lang.Runnable
            public void run() {
                TranscodeDispatcher.this.mListener.onTranscodeCompleted(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchFailure(final Throwable th) {
        this.mHandler.post(new Runnable() { // from class: com.otaliastudios.transcoder.internal.transcode.TranscodeDispatcher.3
            @Override // java.lang.Runnable
            public void run() {
                TranscodeDispatcher.this.mListener.onTranscodeFailed(th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchProgress(final double d) {
        this.mHandler.post(new Runnable() { // from class: com.otaliastudios.transcoder.internal.transcode.TranscodeDispatcher.4
            @Override // java.lang.Runnable
            public void run() {
                TranscodeDispatcher.this.mListener.onTranscodeProgress(d);
            }
        });
    }
}
