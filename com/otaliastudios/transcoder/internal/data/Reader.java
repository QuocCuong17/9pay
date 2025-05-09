package com.otaliastudios.transcoder.internal.data;

import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.pipeline.BaseStep;
import com.otaliastudios.transcoder.internal.pipeline.Channel;
import com.otaliastudios.transcoder.internal.pipeline.State;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.source.DataSource;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Reader.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0001B\u0015\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ/\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00142\u001e\u0010\u0015\u001a\u001a\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00140\u0016H\u0082\bJ$\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u00142\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/otaliastudios/transcoder/internal/data/Reader;", "Lcom/otaliastudios/transcoder/internal/pipeline/BaseStep;", "", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "Lcom/otaliastudios/transcoder/internal/data/ReaderData;", "Lcom/otaliastudios/transcoder/internal/data/ReaderChannel;", "source", "Lcom/otaliastudios/transcoder/source/DataSource;", "track", "Lcom/otaliastudios/transcoder/common/TrackType;", "(Lcom/otaliastudios/transcoder/source/DataSource;Lcom/otaliastudios/transcoder/common/TrackType;)V", "channel", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel$Companion;", "getChannel", "()Lcom/otaliastudios/transcoder/internal/pipeline/Channel$Companion;", "chunk", "Lcom/otaliastudios/transcoder/source/DataSource$Chunk;", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "nextBufferOrWait", "Lcom/otaliastudios/transcoder/internal/pipeline/State;", "action", "Lkotlin/Function2;", "Ljava/nio/ByteBuffer;", "", "step", "state", "Lcom/otaliastudios/transcoder/internal/pipeline/State$Ok;", "fresh", "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class Reader extends BaseStep<Unit, Channel, ReaderData, ReaderChannel> {
    private final Channel.Companion channel;
    private final DataSource.Chunk chunk;
    private final Logger log;
    private final DataSource source;
    private final TrackType track;

    public static final /* synthetic */ ReaderChannel access$getNext(Reader reader) {
        return reader.getNext();
    }

    public Reader(DataSource source, TrackType track) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(track, "track");
        this.source = source;
        this.track = track;
        this.log = new Logger("Reader");
        this.channel = Channel.INSTANCE;
        this.chunk = new DataSource.Chunk();
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public Channel.Companion getChannel() {
        return this.channel;
    }

    private final State<ReaderData> nextBufferOrWait(Function2<? super ByteBuffer, ? super Integer, ? extends State<ReaderData>> action) {
        Pair<ByteBuffer, Integer> buffer = access$getNext(this).buffer();
        if (buffer == null) {
            this.log.v("Returning State.Wait because buffer is null.");
            return State.Wait.INSTANCE;
        }
        return action.invoke(buffer.getFirst(), buffer.getSecond());
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public State<ReaderData> step(State.Ok<Unit> state, boolean fresh) {
        Intrinsics.checkNotNullParameter(state, "state");
        if (this.source.isDrained()) {
            this.log.i("Source is drained! Returning Eos as soon as possible.");
            Pair<ByteBuffer, Integer> buffer = access$getNext(this).buffer();
            if (buffer == null) {
                this.log.v("Returning State.Wait because buffer is null.");
                return State.Wait.INSTANCE;
            }
            ByteBuffer first = buffer.getFirst();
            int intValue = buffer.getSecond().intValue();
            ByteBuffer byteBuffer = first;
            byteBuffer.limit(0);
            this.chunk.buffer = byteBuffer;
            this.chunk.keyframe = false;
            this.chunk.render = true;
            return new State.Eos(new ReaderData(this.chunk, intValue));
        }
        if (!this.source.canReadTrack(this.track)) {
            this.log.i("Returning State.Wait because source can't read " + this.track + " right now.");
            return State.Wait.INSTANCE;
        }
        Pair<ByteBuffer, Integer> buffer2 = access$getNext(this).buffer();
        if (buffer2 == null) {
            this.log.v("Returning State.Wait because buffer is null.");
            return State.Wait.INSTANCE;
        }
        ByteBuffer first2 = buffer2.getFirst();
        int intValue2 = buffer2.getSecond().intValue();
        this.chunk.buffer = first2;
        this.source.readTrack(this.chunk);
        return new State.Ok(new ReaderData(this.chunk, intValue2));
    }
}
