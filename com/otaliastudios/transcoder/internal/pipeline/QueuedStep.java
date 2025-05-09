package com.otaliastudios.transcoder.internal.pipeline;

import com.otaliastudios.transcoder.internal.pipeline.Channel;
import com.otaliastudios.transcoder.internal.pipeline.State;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: steps.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b \u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0004*\b\b\u0002\u0010\u0005*\u00020\u0002*\b\b\u0003\u0010\u0006*\u00020\u00042\u001a\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u00060\u0007B\u0005¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00020\nH$J\u0015\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u000eJ\"\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00020\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00122\u0006\u0010\u0013\u001a\u00020\u0014¨\u0006\u0015"}, d2 = {"Lcom/otaliastudios/transcoder/internal/pipeline/QueuedStep;", "Input", "", "InputChannel", "Lcom/otaliastudios/transcoder/internal/pipeline/Channel;", "Output", "OutputChannel", "Lcom/otaliastudios/transcoder/internal/pipeline/BaseStep;", "()V", "drain", "Lcom/otaliastudios/transcoder/internal/pipeline/State;", "enqueue", "", "data", "(Ljava/lang/Object;)V", "enqueueEos", "step", "state", "Lcom/otaliastudios/transcoder/internal/pipeline/State$Ok;", "fresh", "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public abstract class QueuedStep<Input, InputChannel extends Channel, Output, OutputChannel extends Channel> extends BaseStep<Input, InputChannel, Output, OutputChannel> {
    protected abstract State<Output> drain();

    protected abstract void enqueue(Input data);

    protected abstract void enqueueEos(Input data);

    @Override // com.otaliastudios.transcoder.internal.pipeline.Step
    public final State<Output> step(State.Ok<Input> state, boolean fresh) {
        Intrinsics.checkNotNullParameter(state, "state");
        if (fresh) {
            if (state instanceof State.Eos) {
                enqueueEos(state.getValue());
            } else {
                enqueue(state.getValue());
            }
        }
        return drain();
    }
}
