package com.otaliastudios.transcoder.sink;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Build;
import com.otaliastudios.transcoder.common.TrackStatus;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.internal.utils.MutableTrackMap;
import com.otaliastudios.transcoder.internal.utils.TrackMapKt;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class DefaultDataSink implements DataSink {
    private static final int BUFFER_SIZE = 262144;
    private static final Logger LOG = new Logger("DefaultDataSink");
    private final MutableTrackMap<MediaFormat> mLastFormat;
    private final MediaMuxer mMuxer;
    private final DefaultDataSinkChecks mMuxerChecks;
    private final MutableTrackMap<Integer> mMuxerIndex;
    private boolean mMuxerStarted;
    private final List<QueuedSample> mQueue;
    private ByteBuffer mQueueBuffer;
    private final MutableTrackMap<TrackStatus> mStatus;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class QueuedSample {
        private final int mFlags;
        private final int mSize;
        private final long mTimeUs;
        private final TrackType mType;

        private QueuedSample(TrackType trackType, MediaCodec.BufferInfo bufferInfo) {
            this.mType = trackType;
            this.mSize = bufferInfo.size;
            this.mTimeUs = bufferInfo.presentationTimeUs;
            this.mFlags = bufferInfo.flags;
        }
    }

    public DefaultDataSink(String str) {
        this(str, 0);
    }

    public DefaultDataSink(String str, int i) {
        this.mMuxerStarted = false;
        this.mQueue = new ArrayList();
        this.mStatus = TrackMapKt.mutableTrackMapOf(null);
        this.mLastFormat = TrackMapKt.mutableTrackMapOf(null);
        this.mMuxerIndex = TrackMapKt.mutableTrackMapOf(null);
        this.mMuxerChecks = new DefaultDataSinkChecks();
        try {
            this.mMuxer = new MediaMuxer(str, i);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DefaultDataSink(FileDescriptor fileDescriptor) {
        this(fileDescriptor, 0);
    }

    public DefaultDataSink(FileDescriptor fileDescriptor, int i) {
        this.mMuxerStarted = false;
        this.mQueue = new ArrayList();
        this.mStatus = TrackMapKt.mutableTrackMapOf(null);
        this.mLastFormat = TrackMapKt.mutableTrackMapOf(null);
        this.mMuxerIndex = TrackMapKt.mutableTrackMapOf(null);
        this.mMuxerChecks = new DefaultDataSinkChecks();
        try {
            this.mMuxer = new MediaMuxer(fileDescriptor, i);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void setOrientation(int i) {
        this.mMuxer.setOrientationHint(i);
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void setLocation(double d, double d2) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mMuxer.setLocation((float) d, (float) d2);
        }
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void setTrackStatus(TrackType trackType, TrackStatus trackStatus) {
        this.mStatus.set(trackType, trackStatus);
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void setTrackFormat(TrackType trackType, MediaFormat mediaFormat) {
        LOG.i("setTrackFormat(" + trackType + ") format=" + mediaFormat);
        if (this.mStatus.get(trackType) == TrackStatus.COMPRESSING) {
            this.mMuxerChecks.checkOutputFormat(trackType, mediaFormat);
        }
        this.mLastFormat.set(trackType, mediaFormat);
        maybeStart();
    }

    private void maybeStart() {
        if (this.mMuxerStarted) {
            return;
        }
        boolean isTranscoding = this.mStatus.get(TrackType.VIDEO).isTranscoding();
        boolean isTranscoding2 = this.mStatus.get(TrackType.AUDIO).isTranscoding();
        MediaFormat orNull = this.mLastFormat.getOrNull(TrackType.VIDEO);
        MediaFormat orNull2 = this.mLastFormat.getOrNull(TrackType.AUDIO);
        boolean z = (orNull == null && isTranscoding) ? false : true;
        boolean z2 = (orNull2 == null && isTranscoding2) ? false : true;
        if (z && z2) {
            if (isTranscoding) {
                int addTrack = this.mMuxer.addTrack(orNull);
                this.mMuxerIndex.setVideo(Integer.valueOf(addTrack));
                LOG.v("Added track #" + addTrack + " with " + orNull.getString("mime") + " to muxer");
            }
            if (isTranscoding2) {
                int addTrack2 = this.mMuxer.addTrack(orNull2);
                this.mMuxerIndex.setAudio(Integer.valueOf(addTrack2));
                LOG.v("Added track #" + addTrack2 + " with " + orNull2.getString("mime") + " to muxer");
            }
            this.mMuxer.start();
            this.mMuxerStarted = true;
            drainQueue();
        }
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void writeTrack(TrackType trackType, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        if (this.mMuxerStarted) {
            this.mMuxer.writeSampleData(this.mMuxerIndex.get(trackType).intValue(), byteBuffer, bufferInfo);
        } else {
            enqueue(trackType, byteBuffer, bufferInfo);
        }
    }

    private void enqueue(TrackType trackType, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        if (this.mQueueBuffer == null) {
            this.mQueueBuffer = ByteBuffer.allocateDirect(262144).order(ByteOrder.nativeOrder());
        }
        LOG.v("enqueue(" + trackType + "): offset=" + bufferInfo.offset + "\trealOffset=" + byteBuffer.position() + "\tsize=" + bufferInfo.size + "\trealSize=" + byteBuffer.remaining() + "\tavailable=" + this.mQueueBuffer.remaining() + "\ttotal=262144");
        byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
        byteBuffer.position(bufferInfo.offset);
        this.mQueueBuffer.put(byteBuffer);
        this.mQueue.add(new QueuedSample(trackType, bufferInfo));
    }

    private void drainQueue() {
        if (this.mQueue.isEmpty()) {
            return;
        }
        this.mQueueBuffer.flip();
        LOG.i("Output format determined, writing pending data into the muxer. samples:" + this.mQueue.size() + " bytes:" + this.mQueueBuffer.limit());
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        int i = 0;
        for (QueuedSample queuedSample : this.mQueue) {
            bufferInfo.set(i, queuedSample.mSize, queuedSample.mTimeUs, queuedSample.mFlags);
            writeTrack(queuedSample.mType, this.mQueueBuffer, bufferInfo);
            i += queuedSample.mSize;
        }
        this.mQueue.clear();
        this.mQueueBuffer = null;
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void stop() {
        this.mMuxer.stop();
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void release() {
        try {
            this.mMuxer.release();
        } catch (Exception e) {
            LOG.w("Failed to release the muxer.", e);
        }
    }
}
