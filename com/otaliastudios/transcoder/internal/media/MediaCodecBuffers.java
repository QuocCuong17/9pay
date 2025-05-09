package com.otaliastudios.transcoder.internal.media;

import android.media.MediaCodec;
import android.os.Build;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public class MediaCodecBuffers {
    private final ByteBuffer[] mInputBuffers;
    private final MediaCodec mMediaCodec;
    private ByteBuffer[] mOutputBuffers;

    public MediaCodecBuffers(MediaCodec mediaCodec) {
        this.mMediaCodec = mediaCodec;
        if (Build.VERSION.SDK_INT < 21) {
            this.mInputBuffers = mediaCodec.getInputBuffers();
            this.mOutputBuffers = mediaCodec.getOutputBuffers();
        } else {
            this.mOutputBuffers = null;
            this.mInputBuffers = null;
        }
    }

    public ByteBuffer getInputBuffer(int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.mMediaCodec.getInputBuffer(i);
        }
        ByteBuffer byteBuffer = this.mInputBuffers[i];
        byteBuffer.clear();
        return byteBuffer;
    }

    public ByteBuffer getOutputBuffer(int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.mMediaCodec.getOutputBuffer(i);
        }
        return this.mOutputBuffers[i];
    }

    public void onOutputBuffersChanged() {
        if (Build.VERSION.SDK_INT < 21) {
            this.mOutputBuffers = this.mMediaCodec.getOutputBuffers();
        }
    }
}
