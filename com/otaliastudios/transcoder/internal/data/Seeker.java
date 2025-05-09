package com.otaliastudios.transcoder.internal.data;

import com.otaliastudios.transcoder.internal.pipeline.BaseStep;
import com.otaliastudios.transcoder.internal.pipeline.Channel;
import com.otaliastudios.transcoder.internal.pipeline.State;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.source.DataSource;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Seeker.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B/\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\fJ$\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000bH\u0016R\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/otaliastudios/transcoder/internal/data/Seeker;", "Lcom/otaliastudios/transcoder/internal/pipeline/BaseStep;", "", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "source", "Lcom/otaliastudios/transcoder/source/DataSource;", "positions", "", "", "seek", "Lkotlin/Function1;", "", "(Lcom/otaliastudios/transcoder/source/DataSource;Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "channel", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel$Companion;", "getChannel", "()Lcom/otaliastudios/transcoder/internal/pipeline/Channel$Companion;", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "", "step", "Lcom/otaliastudios/transcoder/internal/pipeline/State;", "state", "Lcom/otaliastudios/transcoder/internal/pipeline/State$Ok;", "fresh", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class Seeker extends BaseStep<Unit, Channel, Unit, Channel> {
    private final Channel.Companion channel;
    private final Logger log;
    private final List<Long> positions;
    private final Function1<Long, Boolean> seek;
    private final DataSource source;

    /* JADX WARN: Multi-variable type inference failed */
    public Seeker(DataSource source, List<Long> positions, Function1<? super Long, Boolean> seek) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(positions, "positions");
        Intrinsics.checkNotNullParameter(seek, "seek");
        this.source = source;
        this.seek = seek;
        this.log = new Logger("Seeker");
        this.channel = Channel.INSTANCE;
        this.positions = CollectionsKt.toMutableList((Collection) positions);
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public Channel.Companion getChannel() {
        return this.channel;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public State<Unit> step(State.Ok<Unit> state, boolean fresh) {
        Intrinsics.checkNotNullParameter(state, "state");
        if ((!this.positions.isEmpty()) && ((Boolean) this.seek.invoke(CollectionsKt.first((List) this.positions))).booleanValue()) {
            this.log.i(Intrinsics.stringPlus("Seeking to next position ", CollectionsKt.first((List) this.positions)));
            this.source.seekTo(((Number) CollectionsKt.removeFirst(this.positions)).longValue());
        }
        return state;
    }
}
