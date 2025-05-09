package com.otaliastudios.transcoder.internal.data;

import android.media.MediaFormat;
import com.otaliastudios.transcoder.internal.pipeline.State;
import com.otaliastudios.transcoder.internal.pipeline.Step;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.source.DataSource;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Bridge.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\r0\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0005H\u0016J$\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u0000X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/otaliastudios/transcoder/internal/data/Bridge;", "Lcom/otaliastudios/transcoder/internal/pipeline/Step;", "Lcom/otaliastudios/transcoder/internal/data/ReaderData;", "Lcom/otaliastudios/transcoder/internal/data/ReaderChannel;", "Lcom/otaliastudios/transcoder/internal/data/WriterData;", "Lcom/otaliastudios/transcoder/internal/data/WriterChannel;", "format", "Landroid/media/MediaFormat;", "(Landroid/media/MediaFormat;)V", "buffer", "Ljava/nio/ByteBuffer;", "kotlin.jvm.PlatformType", "bufferSize", "", "channel", "getChannel", "()Lcom/otaliastudios/transcoder/internal/data/Bridge;", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "Lkotlin/Pair;", "initialize", "", "next", "step", "Lcom/otaliastudios/transcoder/internal/pipeline/State;", "state", "Lcom/otaliastudios/transcoder/internal/pipeline/State$Ok;", "fresh", "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class Bridge implements Step<ReaderData, ReaderChannel, WriterData, WriterChannel>, ReaderChannel {
    private final ByteBuffer buffer;
    private final int bufferSize;
    private final Bridge channel;
    private final MediaFormat format;
    private final Logger log;

    public Bridge(MediaFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        this.format = format;
        this.log = new Logger("Bridge");
        int integer = format.getInteger("max-input-size");
        this.bufferSize = integer;
        this.buffer = ByteBuffer.allocateDirect(integer).order(ByteOrder.nativeOrder());
        this.channel = this;
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public void release() {
        Step.DefaultImpls.release(this);
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public ReaderChannel getChannel() {
        return this.channel;
    }

    @Override // com.otaliastudios.transcoder.internal.data.ReaderChannel
    public Pair<ByteBuffer, Integer> buffer() {
        this.buffer.clear();
        return TuplesKt.to(this.buffer, 0);
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public void initialize(WriterChannel next) {
        Intrinsics.checkNotNullParameter(next, "next");
        this.log.i(Intrinsics.stringPlus("initialize(): format=", this.format));
        next.handleFormat(this.format);
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public State<WriterData> step(State.Ok<ReaderData> state, boolean fresh) {
        Intrinsics.checkNotNullParameter(state, "state");
        DataSource.Chunk chunk = state.getValue().getChunk();
        boolean z = chunk.keyframe;
        ByteBuffer byteBuffer = chunk.buffer;
        Intrinsics.checkNotNullExpressionValue(byteBuffer, "chunk.buffer");
        WriterData writerData = new WriterData(byteBuffer, chunk.timeUs, z ? 1 : 0, new Function0<Unit>() { // from class: com.otaliastudios.transcoder.internal.data.Bridge$step$result$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        });
        return state instanceof State.Eos ? new State.Eos<>(writerData) : new State.Ok<>(writerData);
    }
}
