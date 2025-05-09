package com.otaliastudios.transcoder.sink;

import android.media.MediaCodec;
import android.media.MediaFormat;
import com.otaliastudios.transcoder.common.TrackStatus;
import com.otaliastudios.transcoder.common.TrackType;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class MultiDataSink implements DataSink {
    private final List<DataSink> sinks;

    public MultiDataSink(DataSink... dataSinkArr) {
        this.sinks = Arrays.asList(dataSinkArr);
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void setOrientation(int i) {
        Iterator<DataSink> it = this.sinks.iterator();
        while (it.hasNext()) {
            it.next().setOrientation(i);
        }
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void setLocation(double d, double d2) {
        Iterator<DataSink> it = this.sinks.iterator();
        while (it.hasNext()) {
            it.next().setLocation(d, d2);
        }
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void setTrackStatus(TrackType trackType, TrackStatus trackStatus) {
        Iterator<DataSink> it = this.sinks.iterator();
        while (it.hasNext()) {
            it.next().setTrackStatus(trackType, trackStatus);
        }
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void setTrackFormat(TrackType trackType, MediaFormat mediaFormat) {
        Iterator<DataSink> it = this.sinks.iterator();
        while (it.hasNext()) {
            it.next().setTrackFormat(trackType, mediaFormat);
        }
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void writeTrack(TrackType trackType, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        Iterator<DataSink> it = this.sinks.iterator();
        while (it.hasNext()) {
            it.next().writeTrack(trackType, byteBuffer, bufferInfo);
            byteBuffer.position(position);
            byteBuffer.limit(limit);
        }
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void stop() {
        Iterator<DataSink> it = this.sinks.iterator();
        while (it.hasNext()) {
            it.next().stop();
        }
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void release() {
        Iterator<DataSink> it = this.sinks.iterator();
        while (it.hasNext()) {
            it.next().release();
        }
    }
}
