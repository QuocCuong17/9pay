package com.otaliastudios.transcoder.sink;

import android.media.MediaCodec;
import android.media.MediaFormat;
import com.otaliastudios.transcoder.common.TrackStatus;
import com.otaliastudios.transcoder.common.TrackType;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public interface DataSink {
    void release();

    void setLocation(double d, double d2);

    void setOrientation(int i);

    void setTrackFormat(TrackType trackType, MediaFormat mediaFormat);

    void setTrackStatus(TrackType trackType, TrackStatus trackStatus);

    void stop();

    void writeTrack(TrackType trackType, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo);
}
