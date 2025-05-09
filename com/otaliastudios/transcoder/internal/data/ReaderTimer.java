package com.otaliastudios.transcoder.internal.data;

import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.pipeline.DataStep;
import com.otaliastudios.transcoder.internal.pipeline.State;
import com.otaliastudios.transcoder.time.TimeInterpolator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReaderTimer.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/otaliastudios/transcoder/internal/data/ReaderTimer;", "Lcom/otaliastudios/transcoder/internal/pipeline/DataStep;", "Lcom/otaliastudios/transcoder/internal/data/ReaderData;", "Lcom/otaliastudios/transcoder/internal/data/ReaderChannel;", "track", "Lcom/otaliastudios/transcoder/common/TrackType;", "interpolator", "Lcom/otaliastudios/transcoder/time/TimeInterpolator;", "(Lcom/otaliastudios/transcoder/common/TrackType;Lcom/otaliastudios/transcoder/time/TimeInterpolator;)V", "step", "Lcom/otaliastudios/transcoder/internal/pipeline/State;", "state", "Lcom/otaliastudios/transcoder/internal/pipeline/State$Ok;", "fresh", "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class ReaderTimer extends DataStep<ReaderData, ReaderChannel> {
    private final TimeInterpolator interpolator;
    private final TrackType track;

    public ReaderTimer(TrackType track, TimeInterpolator interpolator) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(interpolator, "interpolator");
        this.track = track;
        this.interpolator = interpolator;
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public State<ReaderData> step(State.Ok<ReaderData> state, boolean fresh) {
        Intrinsics.checkNotNullParameter(state, "state");
        if (state instanceof State.Eos) {
            return state;
        }
        state.getValue().getChunk().timeUs = this.interpolator.interpolate(this.track, state.getValue().getChunk().timeUs);
        return state;
    }
}
