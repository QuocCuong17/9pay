package com.otaliastudios.transcoder.internal.utils;

import android.media.MediaCodec;
import android.media.MediaFormat;
import com.otaliastudios.transcoder.common.TrackStatus;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.sink.DataSink;
import io.sentry.protocol.Device;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: eos.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\t\u0010\t\u001a\u00020\nH\u0096\u0001J\u0019\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0096\u0001J\u0011\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096\u0001J\u001d\u0010\u0012\u001a\u00020\n2\b\b\u0001\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\u0015\u001a\u00020\u0016H\u0096\u0001J\u001d\u0010\u0017\u001a\u00020\n2\b\b\u0001\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\u0018\u001a\u00020\u0019H\u0096\u0001J\t\u0010\u001a\u001a\u00020\nH\u0096\u0001J \u0010\u001b\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\bH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/otaliastudios/transcoder/internal/utils/EosIgnoringDataSink;", "Lcom/otaliastudios/transcoder/sink/DataSink;", "sink", "ignore", "Lkotlin/Function0;", "", "(Lcom/otaliastudios/transcoder/sink/DataSink;Lkotlin/jvm/functions/Function0;)V", "info", "Landroid/media/MediaCodec$BufferInfo;", "release", "", "setLocation", "latitude", "", "longitude", "setOrientation", Device.JsonKeys.ORIENTATION, "", "setTrackFormat", "type", "Lcom/otaliastudios/transcoder/common/TrackType;", "format", "Landroid/media/MediaFormat;", "setTrackStatus", "status", "Lcom/otaliastudios/transcoder/common/TrackStatus;", "stop", "writeTrack", "byteBuffer", "Ljava/nio/ByteBuffer;", "bufferInfo", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
final class EosIgnoringDataSink implements DataSink {
    private final Function0<Boolean> ignore;
    private final MediaCodec.BufferInfo info;
    private final DataSink sink;

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void release() {
        this.sink.release();
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void setLocation(double latitude, double longitude) {
        this.sink.setLocation(latitude, longitude);
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void setOrientation(int orientation) {
        this.sink.setOrientation(orientation);
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void setTrackFormat(TrackType type, MediaFormat format) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(format, "format");
        this.sink.setTrackFormat(type, format);
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void setTrackStatus(TrackType type, TrackStatus status) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(status, "status");
        this.sink.setTrackStatus(type, status);
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void stop() {
        this.sink.stop();
    }

    public EosIgnoringDataSink(DataSink sink, Function0<Boolean> ignore) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        Intrinsics.checkNotNullParameter(ignore, "ignore");
        this.sink = sink;
        this.ignore = ignore;
        this.info = new MediaCodec.BufferInfo();
    }

    @Override // com.otaliastudios.transcoder.sink.DataSink
    public void writeTrack(TrackType type, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(byteBuffer, "byteBuffer");
        Intrinsics.checkNotNullParameter(bufferInfo, "bufferInfo");
        if (this.ignore.invoke().booleanValue()) {
            int i = bufferInfo.flags & (-5);
            if (bufferInfo.size > 0 || i != 0) {
                this.info.set(bufferInfo.offset, bufferInfo.size, bufferInfo.presentationTimeUs, i);
                this.sink.writeTrack(type, byteBuffer, this.info);
                return;
            }
            return;
        }
        this.sink.writeTrack(type, byteBuffer, bufferInfo);
    }
}
