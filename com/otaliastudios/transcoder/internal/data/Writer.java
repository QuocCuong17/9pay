package com.otaliastudios.transcoder.internal.data;

import android.media.MediaCodec;
import android.media.MediaFormat;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.pipeline.Channel;
import com.otaliastudios.transcoder.internal.pipeline.State;
import com.otaliastudios.transcoder.internal.pipeline.Step;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.sink.DataSink;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Writer.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00012\u00020\u0003B\u0015\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J$\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u0014\u0010\u000b\u001a\u00020\u0000X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/otaliastudios/transcoder/internal/data/Writer;", "Lcom/otaliastudios/transcoder/internal/pipeline/Step;", "Lcom/otaliastudios/transcoder/internal/data/WriterData;", "Lcom/otaliastudios/transcoder/internal/data/WriterChannel;", "", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "sink", "Lcom/otaliastudios/transcoder/sink/DataSink;", "track", "Lcom/otaliastudios/transcoder/common/TrackType;", "(Lcom/otaliastudios/transcoder/sink/DataSink;Lcom/otaliastudios/transcoder/common/TrackType;)V", "channel", "getChannel", "()Lcom/otaliastudios/transcoder/internal/data/Writer;", "info", "Landroid/media/MediaCodec$BufferInfo;", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "handleFormat", "format", "Landroid/media/MediaFormat;", "step", "Lcom/otaliastudios/transcoder/internal/pipeline/State;", "state", "Lcom/otaliastudios/transcoder/internal/pipeline/State$Ok;", "fresh", "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class Writer implements Step<WriterData, WriterChannel, Unit, Channel>, WriterChannel {
    private final Writer channel;
    private final MediaCodec.BufferInfo info;
    private final Logger log;
    private final DataSink sink;
    private final TrackType track;

    public Writer(DataSink sink, TrackType track) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        Intrinsics.checkNotNullParameter(track, "track");
        this.sink = sink;
        this.track = track;
        this.channel = this;
        this.log = new Logger("Writer");
        this.info = new MediaCodec.BufferInfo();
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public void initialize(Channel channel) {
        Step.DefaultImpls.initialize(this, channel);
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public void release() {
        Step.DefaultImpls.release(this);
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public WriterChannel getChannel() {
        return this.channel;
    }

    @Override // com.otaliastudios.transcoder.internal.data.WriterChannel
    public void handleFormat(MediaFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        this.log.i("handleFormat(" + format + ')');
        this.sink.setTrackFormat(this.track, format);
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public State<Unit> step(State.Ok<WriterData> state, boolean fresh) {
        Intrinsics.checkNotNullParameter(state, "state");
        WriterData value = state.getValue();
        ByteBuffer buffer = value.getBuffer();
        long timeUs = value.getTimeUs();
        int flags = value.getFlags();
        boolean z = state instanceof State.Eos;
        MediaCodec.BufferInfo bufferInfo = this.info;
        int position = buffer.position();
        int remaining = buffer.remaining();
        if (z) {
            flags &= 4;
        }
        bufferInfo.set(position, remaining, timeUs, flags);
        this.sink.writeTrack(this.track, buffer, this.info);
        state.getValue().getRelease().invoke();
        return z ? new State.Eos<>(Unit.INSTANCE) : new State.Ok<>(Unit.INSTANCE);
    }
}
