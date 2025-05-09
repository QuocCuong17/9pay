package com.otaliastudios.transcoder.internal.codec;

import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.pipeline.DataStep;
import com.otaliastudios.transcoder.internal.pipeline.State;
import com.otaliastudios.transcoder.time.TimeInterpolator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DecoderTimer.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ$\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\f\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/otaliastudios/transcoder/internal/codec/DecoderTimer;", "Lcom/otaliastudios/transcoder/internal/pipeline/DataStep;", "Lcom/otaliastudios/transcoder/internal/codec/DecoderData;", "Lcom/otaliastudios/transcoder/internal/codec/DecoderChannel;", "track", "Lcom/otaliastudios/transcoder/common/TrackType;", "interpolator", "Lcom/otaliastudios/transcoder/time/TimeInterpolator;", "(Lcom/otaliastudios/transcoder/common/TrackType;Lcom/otaliastudios/transcoder/time/TimeInterpolator;)V", "lastRawTimeUs", "", "Ljava/lang/Long;", "lastTimeUs", "step", "Lcom/otaliastudios/transcoder/internal/pipeline/State;", "state", "Lcom/otaliastudios/transcoder/internal/pipeline/State$Ok;", "fresh", "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class DecoderTimer extends DataStep<DecoderData, DecoderChannel> {
    private final TimeInterpolator interpolator;
    private Long lastRawTimeUs;
    private Long lastTimeUs;
    private final TrackType track;

    public DecoderTimer(TrackType track, TimeInterpolator interpolator) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(interpolator, "interpolator");
        this.track = track;
        this.interpolator = interpolator;
    }

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public State<DecoderData> step(State.Ok<DecoderData> state, boolean fresh) {
        double longValue;
        Intrinsics.checkNotNullParameter(state, "state");
        if (state instanceof State.Eos) {
            return state;
        }
        if (!(!(state.getValue() instanceof DecoderTimerData))) {
            throw new IllegalArgumentException("Can't apply DecoderTimer twice.".toString());
        }
        long timeUs = state.getValue().getTimeUs();
        long interpolate = this.interpolator.interpolate(this.track, timeUs);
        Long l = this.lastTimeUs;
        if (l == null) {
            longValue = 1.0d;
        } else {
            Intrinsics.checkNotNull(l);
            long longValue2 = interpolate - l.longValue();
            Intrinsics.checkNotNull(this.lastRawTimeUs);
            longValue = longValue2 / (timeUs - r12.longValue());
        }
        double d = longValue;
        this.lastTimeUs = Long.valueOf(interpolate);
        this.lastRawTimeUs = Long.valueOf(timeUs);
        return new State.Ok(new DecoderTimerData(state.getValue().getBuffer(), timeUs, interpolate, d, state.getValue().getRelease()));
    }
}
