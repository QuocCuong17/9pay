package com.otaliastudios.transcoder.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.pipeline.Pipeline;
import com.otaliastudios.transcoder.internal.pipeline.State;
import com.otaliastudios.transcoder.internal.utils.Logger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Segment.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\u0006\u0010\u0015\u001a\u00020\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0016"}, d2 = {"Lcom/otaliastudios/transcoder/internal/Segment;", "", "type", "Lcom/otaliastudios/transcoder/common/TrackType;", FirebaseAnalytics.Param.INDEX, "", "pipeline", "Lcom/otaliastudios/transcoder/internal/pipeline/Pipeline;", "(Lcom/otaliastudios/transcoder/common/TrackType;ILcom/otaliastudios/transcoder/internal/pipeline/Pipeline;)V", "getIndex", "()I", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "state", "Lcom/otaliastudios/transcoder/internal/pipeline/State;", "", "getType", "()Lcom/otaliastudios/transcoder/common/TrackType;", "advance", "", "canAdvance", "release", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class Segment {
    private final int index;
    private final Logger log;
    private final Pipeline pipeline;
    private State<Unit> state;
    private final TrackType type;

    public Segment(TrackType type, int i, Pipeline pipeline) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(pipeline, "pipeline");
        this.type = type;
        this.index = i;
        this.pipeline = pipeline;
        this.log = new Logger("Segment(" + type + ',' + i + ')');
    }

    public final TrackType getType() {
        return this.type;
    }

    public final int getIndex() {
        return this.index;
    }

    public final boolean advance() {
        State<Unit> execute = this.pipeline.execute();
        this.state = execute;
        return execute instanceof State.Ok;
    }

    public final boolean canAdvance() {
        this.log.v(Intrinsics.stringPlus("canAdvance(): state=", this.state));
        State<Unit> state = this.state;
        return state == null || !(state instanceof State.Eos);
    }

    public final void release() {
        this.pipeline.release();
    }
}
