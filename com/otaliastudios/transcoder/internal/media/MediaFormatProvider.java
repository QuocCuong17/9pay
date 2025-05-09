package com.otaliastudios.transcoder.internal.media;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.source.DataSource;
import java.io.IOException;

/* loaded from: classes4.dex */
public class MediaFormatProvider {
    public MediaFormat provideMediaFormat(DataSource dataSource, TrackType trackType, MediaFormat mediaFormat) {
        if (isComplete(trackType, mediaFormat)) {
            return mediaFormat;
        }
        MediaFormat decodeMediaFormat = decodeMediaFormat(dataSource, trackType, mediaFormat);
        if (isComplete(trackType, decodeMediaFormat)) {
            return decodeMediaFormat;
        }
        String str = "Could not get a complete format! hasMimeType:" + decodeMediaFormat.containsKey("mime");
        if (trackType == TrackType.VIDEO) {
            str = ((str + " hasWidth:" + decodeMediaFormat.containsKey("width")) + " hasHeight:" + decodeMediaFormat.containsKey("height")) + " hasFrameRate:" + decodeMediaFormat.containsKey("frame-rate");
        } else if (trackType == TrackType.AUDIO) {
            str = (str + " hasChannels:" + decodeMediaFormat.containsKey("channel-count")) + " hasSampleRate:" + decodeMediaFormat.containsKey("sample-rate");
        }
        throw new RuntimeException(str);
    }

    private boolean isComplete(TrackType trackType, MediaFormat mediaFormat) {
        if (trackType == TrackType.VIDEO && !mediaFormat.containsKey("frame-rate")) {
            mediaFormat.setInteger("frame-rate", 24);
        }
        int i = AnonymousClass1.$SwitchMap$com$otaliastudios$transcoder$common$TrackType[trackType.ordinal()];
        if (i == 1) {
            return isCompleteAudioFormat(mediaFormat);
        }
        if (i == 2) {
            return isCompleteVideoFormat(mediaFormat);
        }
        throw new RuntimeException("Unexpected type: " + trackType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.otaliastudios.transcoder.internal.media.MediaFormatProvider$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$otaliastudios$transcoder$common$TrackType;

        static {
            int[] iArr = new int[TrackType.values().length];
            $SwitchMap$com$otaliastudios$transcoder$common$TrackType = iArr;
            try {
                iArr[TrackType.AUDIO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$otaliastudios$transcoder$common$TrackType[TrackType.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private boolean isCompleteVideoFormat(MediaFormat mediaFormat) {
        return mediaFormat.containsKey("mime") && mediaFormat.containsKey("height") && mediaFormat.containsKey("width") && mediaFormat.containsKey("frame-rate");
    }

    private boolean isCompleteAudioFormat(MediaFormat mediaFormat) {
        return mediaFormat.containsKey("mime") && mediaFormat.containsKey("channel-count") && mediaFormat.containsKey("sample-rate");
    }

    private MediaFormat decodeMediaFormat(DataSource dataSource, TrackType trackType, MediaFormat mediaFormat) {
        dataSource.selectTrack(trackType);
        try {
            MediaCodec createDecoderByType = MediaCodec.createDecoderByType(mediaFormat.getString("mime"));
            MediaFormat mediaFormat2 = null;
            createDecoderByType.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 0);
            createDecoderByType.start();
            MediaCodecBuffers mediaCodecBuffers = new MediaCodecBuffers(createDecoderByType);
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            DataSource.Chunk chunk = new DataSource.Chunk();
            while (mediaFormat2 == null) {
                mediaFormat2 = decodeOnce(trackType, dataSource, chunk, createDecoderByType, mediaCodecBuffers, bufferInfo);
            }
            dataSource.deinitialize();
            dataSource.initialize();
            return mediaFormat2;
        } catch (IOException e) {
            throw new RuntimeException("Can't decode this track", e);
        }
    }

    private MediaFormat decodeOnce(TrackType trackType, DataSource dataSource, DataSource.Chunk chunk, MediaCodec mediaCodec, MediaCodecBuffers mediaCodecBuffers, MediaCodec.BufferInfo bufferInfo) {
        MediaFormat drainOnce = drainOnce(mediaCodec, mediaCodecBuffers, bufferInfo);
        if (drainOnce != null) {
            return drainOnce;
        }
        feedOnce(trackType, dataSource, chunk, mediaCodec, mediaCodecBuffers);
        return null;
    }

    private MediaFormat drainOnce(MediaCodec mediaCodec, MediaCodecBuffers mediaCodecBuffers, MediaCodec.BufferInfo bufferInfo) {
        int dequeueOutputBuffer = mediaCodec.dequeueOutputBuffer(bufferInfo, 0L);
        if (dequeueOutputBuffer == -3) {
            mediaCodecBuffers.onOutputBuffersChanged();
            return drainOnce(mediaCodec, mediaCodecBuffers, bufferInfo);
        }
        if (dequeueOutputBuffer == -2) {
            return mediaCodec.getOutputFormat();
        }
        if (dequeueOutputBuffer != -1) {
            mediaCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
        }
        return null;
    }

    private void feedOnce(TrackType trackType, DataSource dataSource, DataSource.Chunk chunk, MediaCodec mediaCodec, MediaCodecBuffers mediaCodecBuffers) {
        if (!dataSource.canReadTrack(trackType)) {
            throw new RuntimeException("This should never happen!");
        }
        int dequeueInputBuffer = mediaCodec.dequeueInputBuffer(0L);
        if (dequeueInputBuffer < 0) {
            return;
        }
        chunk.buffer = mediaCodecBuffers.getInputBuffer(dequeueInputBuffer);
        dataSource.readTrack(chunk);
        mediaCodec.queueInputBuffer(dequeueInputBuffer, chunk.buffer.position(), chunk.buffer.remaining(), chunk.timeUs, chunk.keyframe ? 1 : 0);
    }
}
